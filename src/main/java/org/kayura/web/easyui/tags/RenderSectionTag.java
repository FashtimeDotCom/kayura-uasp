package org.kayura.web.easyui.tags;

import org.kayura.web.model.RenderContext;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

/**
 * Created by 枫 on 2014/8/17.
 */
public class RenderSectionTag extends TagRender {

	private static final long serialVersionUID = -2080574669825913692L;
	private String name;

	@Override
	public String getEasyuiTag() {
		return null;
	}

	@Override
	public int renderBody() {
		return getOverriedContent() == null ? EVAL_BODY_INCLUDE : SKIP_BODY;
	}

	@Override
	public void renderStart(JspWriter out) {
	}

	@Override
	public void renderEnd(JspWriter out) throws JspException {
		String overriedContent = getOverriedContent();
		if (overriedContent != null) {
			try {
				pageContext.getOut().write(overriedContent);
			} catch (IOException e) {
				throw new JspException("write override Content throw IOException,block name:" + name, e);
			}
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String getOverriedContent() {
		String varName = RenderContext.JEASYUI_TAG_NAME + name;
		return (String) pageContext.getAttribute(varName);
	}
}
