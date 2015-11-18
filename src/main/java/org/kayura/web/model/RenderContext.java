package org.kayura.web.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;

import org.kayura.web.easyui.tags.EventListenerTag;
import org.kayura.web.easyui.tags.EventTag;
import org.kayura.web.easyui.tags.FacetTag;
import org.kayura.web.easyui.tags.TagRender;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 一次JSP页面EasyUI控件解析的上下文。 从解析Body开始到Body结束，所有控件的一些属性会被暂存
 * 在解析body结束的时候，写出所有控件前端渲染的方法。 Created by 枫 on 2014/8/7.
 */
public class RenderContext {

	public static final String REQUEST_TAG_NAME = "_jeasyui_rendercontext__";
	public static final String JEASYUI_TAG_NAME = "__jeasyui__";

	private final Map<String, TagRender> tags = new LinkedHashMap<String, TagRender>();
	private final List<EventListenerTag> eventListeners = new ArrayList<EventListenerTag>();
	private final List<EventTag> events = new ArrayList<EventTag>();
	private final List<FacetTag> facets = new ArrayList<FacetTag>();
	private AtomicInteger atomic = new AtomicInteger(1000);
	private StringBuilder scriptBeforeWriter = new StringBuilder();

	private String userAgent;

	public RenderContext(HttpServletRequest request) {
		this.userAgent = request.getHeader("user-agent");

	}

	private boolean isIE8() {
		return userAgent.contains("MSIE 8.0");
	}

	private boolean isIE9() {
		return userAgent.contains("MSIE 9.0");
	}

	/**
	 * 将当前jsp页面解析过程中的所有控件放入上下文中 <br />
	 * {@link org.kayura.web.easyui.tags.TagRender}实现了
	 * {@linkplain java.lang.Cloneable}接口，由于
	 * {@link javax.servlet.jsp.tagext.TagSupport} 本身没有实现Clone，所以需要注意parent
	 * Tag无法clone。
	 *
	 * @param tag
	 * @throws JspTagException
	 */
	public void putTag(TagRender tag) throws JspTagException {
		if (tag != null && tag.getId() != null) {
			if (tags.containsKey(tag.getId())) {
				// 由于涉及到控件的事件绑定，所以我们强制控件的ID不可以重复
				throw new JspTagException("存在重复的ID：" + tag.getId());
			}
			tags.put(tag.getId(), tag);
		}
	}

	/**
	 * 通过控件id获取{@linkplain org.kayura.web.easyui.tags.TagRender}
	 *
	 * @param id
	 * @return TagRender
	 */
	public TagRender getTag(String id) {
		return tags.get(id);
	}

	/**
	 * 添加EventListenerTag到eventListeners
	 *
	 * @param eventListenerTag
	 */
	public void putEventListener(EventListenerTag eventListenerTag) {
		eventListeners.add(eventListenerTag);
	}

	/**
	 * 添加EventTag到events
	 *
	 * @param eventTag
	 */
	public void putEvent(EventTag eventTag) {
		events.add(eventTag);
	}

	/**
	 * 输出所有控件初始化的js代码
	 *
	 * @param out
	 * @param var
	 * @throws IOException
	 */
	public void write(JspWriter out, String var) throws IOException {
		
		// 输出Facet的Html代码
		for (FacetTag facet : facets) {
			if (facet.getBody() != null) {
				out.write(facet.getBody());
				out.write("\n");
			}
		}

		ScriptWriter sw = new ScriptWriter();
		if (isIE8() || isIE9()) {
			//sw.ready();
		}
		sw.write(getScriptBeforeWriter().toString());
		// loop all tags
		for (Iterator<String> iterator = tags.keySet().iterator(); iterator.hasNext();) {
			String tagId = iterator.next();
			TagRender tag = tags.get(tagId);

			if (tag.getEasyuiTag() != null) {
				// get one tag's event listeners
				List<EventListenerTag> listeners = getEventListeners(tag);
				// get one tag's event actions
				List<EventTag> events = getEvents(tag);
				// get one tag's facet
				List<FacetTag> facetTags = getFacets(tag);

				String tagScript = getTagScript(tag, listeners, events, facetTags);
				//sw.write(tagScript);
			} else {
				List<EventListenerTag> listeners = getEventListeners(tag);
				// get one tag's event actions
				List<EventTag> events = getEvents(tag);
				String tagScript = getJQueryScript(tag, listeners, events);
				//sw.write(tagScript);
			}

			//String afterScript = tag.doAfterTagRenderScript();
			//sw.write(afterScript == null ? "" : afterScript);
			//sw.write("\n");
		}
		if (tags != null) {
			Map<String, String> tagsJs = new HashMap<String, String>();
			for (Iterator<String> iterator = tags.keySet().iterator(); iterator.hasNext();) {
				String tagId = iterator.next();
				if (tags.get(tagId).getEasyuiTag() != null) {
					tagsJs.put(tagId, tags.get(tagId).getEasyuiTag());
				}
			}
			//sw.write("var " + var + " = jeasyui(" + JSON.toJSONString(tagsJs) + ");");
		}
		//out.write(sw.toString());
	}

	private List<FacetTag> getFacets(TagRender tag) {
		List<FacetTag> facetTags = new ArrayList<FacetTag>();
		for (FacetTag facet : facets) {
			if (facet.getParentId() != null && facet.getParentId().equals(tag.getId())) {
				facetTags.add(facet);
			}
		}
		return facetTags;
	}

	/**
	 * 获取tag的渲染构建js代码，同时注册相关事件监听
	 *
	 * @param tag
	 * @param listeners
	 * @param events
	 * @param facetTags
	 * @return
	 */
	private String getTagScript(TagRender tag, List<EventListenerTag> listeners, List<EventTag> events,
			List<FacetTag> facetTags) {
		StringBuilder sb = new StringBuilder();
		sb.append("$('#").append(tag.getId()).append("').").append(tag.getEasyuiTag()).append("(");

		Map<String, String> eventBindString = buildEasyEventScript(listeners, events);
		if (!tag.isJqueryEventBind()) {
			if (eventBindString.size() > 0 || facetTags.size() > 0) {
				sb.append("{\n");
			}
			for (Iterator<String> iterator = eventBindString.keySet().iterator(); iterator.hasNext();) {
				String event = iterator.next();
				String binds = eventBindString.get(event);
				sb.append(event).append(":").append("function(){").append("\n");
				sb.append("var args = arguments;\n");
				sb.append(binds);
				sb.append("}");
				if (iterator.hasNext() || facetTags.size() > 0) {
					sb.append(",");
				}
			}
			for (Iterator<FacetTag> iterator = facetTags.iterator(); iterator.hasNext();) {
				FacetTag facet = iterator.next();
				sb.append(facet.getName()).append(":").append("'#").append(facet.getId()).append("'");
				if (iterator.hasNext()) {
					sb.append(",");
				}
			}
			if (eventBindString.size() > 0 || facetTags.size() > 0) {
				sb.append("\n}");
			}
		}
		sb.append(");");

		if (tag.isJqueryEventBind()) {
			for (Iterator<String> iterator = eventBindString.keySet().iterator(); iterator.hasNext();) {
				String event = iterator.next();
				String binds = eventBindString.get(event);
				sb.append("$('#").append(tag.getId()).append("').bind(");
				sb.append("'").append(event).append("',").append("function(){\n");
				sb.append(binds);
				sb.append("\n}");
				sb.append(");");
			}
		}
		return sb.toString();
	}

	private String getJQueryScript(TagRender tag, List<EventListenerTag> listeners, List<EventTag> events) {
		StringBuilder sb = new StringBuilder();
		Map<String, String> eventBindString = buildEasyEventScript(listeners, events);
		for (Iterator<String> iterator = eventBindString.keySet().iterator(); iterator.hasNext();) {
			String event = iterator.next();
			String binds = eventBindString.get(event);
			sb.append("$('#").append(tag.getId()).append("').bind(");
			sb.append("'").append(event).append("',").append("function(){\n");
			sb.append(binds);
			sb.append("\n}");
			sb.append(");");
		}
		return sb.toString();
	}

	/**
	 * 构建每个事件的所有监听注册以及行为触发
	 *
	 * @param listeners
	 * @param events
	 * @return
	 */
	private Map<String, String> buildEasyEventScript(List<EventListenerTag> listeners, List<EventTag> events) {
		StringBuilder sb = new StringBuilder("");
		Map<String, String> eventBinds = new HashMap<String, String>();
		for (EventTag event : events) {
			if (event.isBeforeListener()) {
				TagRender target = tags.get(event.getTarget());
				if (target != null && target.getId() != null && target.getEasyuiTag() != null) {
					String param = event.getParam();
					sb.append("$('#").append(target.getId()).append("').").append(target.getEasyuiTag()).append("(");
					if (event.getAction() != null) {
						sb.append("'").append(event.getAction()).append("'");
					}
					if (event.getAction() != null && param != null) {
						sb.append(",");
					}
					if (param != null) {
						sb.append(param);
					}
					sb.append(");");
				}
				eventBinds.put(event.getEvent(), sb.toString());
				sb.delete(0, sb.length());
			}
		}
		for (EventListenerTag listener : listeners) {
			String param = listener.getParam();
			if (param == null) {
				sb.append(listener.isNeedReturn() ? "return " : "").append(listener.getListener())
						.append(".apply(this,arguments);").append("\n");
			} else {
				sb.append(listener.isNeedReturn() ? "return " : "").append(listener.getListener())
						.append(".apply(this,[").append(param).append("]);").append("\n");
			}

			String binds = eventBinds.get(listener.getEvent());
			eventBinds.put(listener.getEvent(), binds == null ? (sb.toString()) : (binds + sb.toString()));
			sb.delete(0, sb.length());
		}
		for (EventTag event : events) {
			if (!event.isBeforeListener()) {
				TagRender target = tags.get(event.getTarget());
				if (target != null && target.getId() != null && target.getEasyuiTag() != null) {
					sb.append("$('#").append(target.getId()).append("').").append(target.getEasyuiTag()).append("(");
					if (event.getAction() != null) {
						sb.append("'").append(event.getAction()).append("'");
					}
					if (event.getAction() != null && event.getParam() != null) {
						sb.append(",");
					}
					if (event.getParam() != null) {
						sb.append(event.getParam());
					}
					sb.append(");");
				}
				String binds = eventBinds.get(event.getEvent());
				eventBinds.put(event.getEvent(), binds == null ? (sb.toString()) : (binds + sb.toString()));
				sb.delete(0, sb.length());
			}
		}

		return eventBinds;
	}

	/**
	 * 获取一个控件的所有Event Listener
	 *
	 * @param tag
	 * @return
	 */
	private List<EventListenerTag> getEventListeners(TagRender tag) {
		List<EventListenerTag> listeners = new ArrayList<EventListenerTag>();
		for (EventListenerTag event : eventListeners) {
			if (event.getFor() != null) {
				if (event.getFor().equals(tag.getId()))
					listeners.add(event);
			} else {
				String parentId = event.getParentId();
				if (parentId != null && parentId.equals(tag.getId())) {
					listeners.add(event);
				}
			}
		}
		return listeners;
	}

	/**
	 * 获取一个控件所有的event action
	 *
	 * @param tag
	 * @return
	 */
	private List<EventTag> getEvents(TagRender tag) {
		List<EventTag> eventTags = new ArrayList<EventTag>();
		for (EventTag event : events) {
			if (event.getFor() != null && event.getFor().equals(tag.getId())) {
				eventTags.add(event);
			} else {
				String parentId = event.getParentId();
				if (parentId != null && parentId.equals(tag.getId())) {
					eventTags.add(event);
				}
			}
		}
		return eventTags;
	}

	/**
	 * 获取父控件的ID
	 *
	 * @param tag
	 * @return
	 */
	public String getParentId(Tag tag) {
		Tag parent = tag.getParent();
		if (parent != null) {
			if (parent instanceof TagRender) {
				TagRender parentTag = (TagRender) parent;
				if (parentTag.getId() != null) {
					return parentTag.getId();
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		return null;
	}

	public void putFacet(FacetTag facetTag) {
		facets.add(facetTag);
	}

	public int getUniqueId() {
		return atomic.incrementAndGet();
	}

	public StringBuilder getScriptBeforeWriter() {
		return scriptBeforeWriter;
	}

}
