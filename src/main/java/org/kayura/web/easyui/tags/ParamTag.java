package org.kayura.web.easyui.tags;

import org.kayura.web.model.Expression;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import java.util.Set;

/**
 * Created by JiangFeng on 2014/8/19.
 */
public class ParamTag extends TagRender {

	private static final long serialVersionUID = 716103626842318012L;
	private String name;
	private Object value;
	private String expression;

	@Override
	public String getEasyuiTag() {
		return null;
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public void renderStart(JspWriter out) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void renderEnd(JspWriter out) throws JspException {
		Tag tag = getParent();
		if (tag instanceof EventTag || tag instanceof EventListenerTag) {
			TagRender eventTag = (TagRender) tag;
			Set<ParamTag> paramArray = (Set<ParamTag>) pageContext.getAttribute(eventTag.getId());
			try {
				paramArray.add((ParamTag) this.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		} else if (tag instanceof FileUploadTag) {
			FileUploadTag fileUploadTag = (FileUploadTag) tag;
			fileUploadTag.getParamMap().put(getName(),
					getValue() == null ? new Expression(getExpression()) : getValue());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
}
