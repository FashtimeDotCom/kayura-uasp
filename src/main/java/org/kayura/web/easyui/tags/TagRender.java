package org.kayura.web.easyui.tags;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.kayura.web.model.JsFunction;
import org.kayura.web.model.JsonSerializeConfig;
import org.kayura.web.model.RawString;
import org.kayura.web.model.RenderContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * 所有控件渲染的基类。 针对EasyUI控件的代码写法，对代码渲染过程进行了重构。 由于发现对于
 * {@link org.kayura.web.easyui.tags.EventListenerTag}以及
 * {@link org.kayura.web.easyui.tags.EventTag}控件，将他们放入
 * {@link org.kayura.web.model.RenderContext}后，是同一个对象！！！ 所以这里我们实现了
 * {@link java.lang.Cloneable}来解决这个问题
 * </p>
 * Created by JiangFeng on 2014/8/7.
 */
public abstract class TagRender extends BodyTagSupport implements Cloneable {

	private static final long serialVersionUID = 740381573905825354L;

	/**
	 * 返回Easyui控件的类型，这里必须是easyui js的对象名
	 *
	 * @return
	 */
	public abstract String getEasyuiTag();

	/**
	 * @return
	 * @see javax.servlet.jsp.tagext.TagSupport doStartTag
	 */
	public abstract int renderBody();

	/**
	 * 返回每个控件的easyui属性参数
	 *
	 * @return
	 */
	public Map<String, Object> getOptions() {
		return null;
	}

	/**
	 * 渲染控件start部分
	 *
	 * @param out
	 * @see javax.servlet.jsp.tagext.TagSupport doStartTag
	 */
	public abstract void renderStart(JspWriter out);

	/**
	 * 渲染控件end部分
	 *
	 * @param out
	 * @see javax.servlet.jsp.tagext.TagSupport doStartEnd
	 */
	public abstract void renderEnd(JspWriter out) throws JspException;

	/**
	 * 在renderEnd之后，doStartEnd结束之前输出js脚本。
	 *
	 * @param out
	 * @see javax.servlet.jsp.tagext.TagSupport doStartEnd
	 */
	public void renderScript(JspWriter out) {

	}

	/**
	 * 在RenderContext中输出当前控件的渲染js代码后，输出额外js代码
	 *
	 * @return
	 * @see org.kayura.web.model.RenderContext write
	 */
	public String doAfterTagRenderScript() {
		return null;
	}

	/**
	 * 改控件的所有事件是否都是通过JQuery绑定，用于按钮控件以及其他自定义控件
	 *
	 * @return
	 * @see org.kayura.web.model.RenderContext getTagScript
	 */
	public boolean isJqueryEventBind() {
		return false;
	}

	/**
	 * 获取当前请求的RenderContext
	 *
	 * @return
	 */
	public RenderContext getRenderContext() {
		Object o = this.pageContext.getRequest().getAttribute(RenderContext.REQUEST_TAG_NAME);
		if (o != null) {
			return (RenderContext) o;
		} else {
			return null;
		}
	}

	/**
	 * 将控件的属性转换成 data-options 的字符串
	 *
	 * @return
	 */
	public String optionsToString() {
		Map<String, Object> optionMap = getOptions();
		if (optionMap == null) {
			return "";
		}
		String options = "";
		for (Iterator<Map.Entry<String, Object>> iterator = optionMap.entrySet().iterator(); iterator.hasNext();) {
			Map.Entry<String, Object> option = iterator.next();
			Object value = option.getValue();
			if (value != null) {
				if (value instanceof Integer) {
					if ((Integer) value >= 0) {
						options += "," + option.getKey() + ":" + value;
					}
				} else if (value instanceof RawString) {
					options += "," + option.getKey() + ":" + value.toString();
				} else if (value instanceof String) {
					options += "," + option.getKey() + ":'" + value + "'";
				} else if (value instanceof Enum) {
					options += "," + option.getKey() + ":'" + value + "'";
				} else if (value instanceof Boolean) {
					options += "," + option.getKey() + ":" + value;
				} else if (value instanceof JsFunction) {
					options += "," + option.getKey() + ":" + JSON.toJSONString(value, JsonSerializeConfig.mapping);
				} else if (value instanceof Object) {
					options += "," + option.getKey() + ":"
							+ JSON.toJSONString(value, JsonSerializeConfig.mapping, SerializerFeature.UseSingleQuotes);
				}
			}
		}
		if (options.length() > 0) {
			options = options.substring(1);
		}
		return options;
	}

	@Override
	public int doStartTag() throws JspException {
		RenderContext renderContext = getRenderContext();
		if (renderContext == null) {
			renderContext = new RenderContext((HttpServletRequest) this.pageContext.getRequest());
			this.pageContext.getRequest().setAttribute(RenderContext.REQUEST_TAG_NAME, renderContext);
		}
		try {
			renderContext.putTag((TagRender) this.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		JspWriter out = this.pageContext.getOut();
		renderStart(out);
		return renderBody();
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		renderEnd(out);
		renderScript(out);
		return Tag.EVAL_PAGE;
	}

	public int getRequestId() {
		return pageContext.getRequest().hashCode();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
