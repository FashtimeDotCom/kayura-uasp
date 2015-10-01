package org.kayura.web.easyui.tags;

import com.alibaba.fastjson.JSON;
import org.kayura.web.model.Expression;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import java.util.*;

/**
 * Created by JiangFeng on 2014/8/7.
 */
public class EventListenerTag extends TagRender implements Cloneable {

	private static final long serialVersionUID = 1198449988343924530L;
	private String forId;
	private String event;
	private String listener;
	private String parentId;
	private boolean needReturn;

	public EventListenerTag() {

	}

	@Override
	public String getEasyuiTag() {
		return null;
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public Map<String, Object> getOptions() {
		return null;
	}

	@Override
	public void renderStart(JspWriter out) {
		String id = UUID.randomUUID().toString().replace("-", "");
		setId(id);
		Set<ParamTag> paramArray = new HashSet<ParamTag>();
		pageContext.setAttribute(id, paramArray);
	}

	@Override
	public void renderEnd(JspWriter out) {
		Tag parent = getParent();
		if (parent instanceof TagRender) {
			setParentId(((TagRender) parent).getId());
		}
		try {
			getRenderContext().putEventListener((EventListenerTag) this.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public String getParam() {
		String params = null;
		Set<ParamTag> paramArray = (Set<ParamTag>) pageContext.getAttribute(getId());
		if (!paramArray.isEmpty()) {
			Map<String, Object> param = new HashMap<String, Object>();
			List<Object> paramArr = new ArrayList<Object>();
			for (ParamTag p : paramArray) {
				if (p.getName() != null) {
					if (p.getValue() != null || p.getExpression() != null) {
						param.put(p.getName(), p.getValue() != null ? p.getValue() : new Expression(p.getExpression()));
					}
				} else {
					if (p.getValue() != null || p.getExpression() != null) {
						paramArr.add(p.getValue() != null ? p.getValue() : new Expression(p.getExpression()));
					}
				}
			}
			paramArr.add(param);
			String s = JSON.toJSONString(paramArr, Expression.mapping);

			params = s.substring(1, s.length() - 1);
		}
		return params;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		EventListenerTag o = null;
		try {
			o = (EventListenerTag) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getListener() {
		return listener;
	}

	public void setListener(String listener) {
		this.listener = listener;
	}

	public String getFor() {
		return forId;
	}

	public void setFor(String forId) {
		this.forId = forId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public boolean isNeedReturn() {
		return needReturn;
	}

	public void setNeedReturn(boolean needReturn) {
		this.needReturn = needReturn;
	}
}
