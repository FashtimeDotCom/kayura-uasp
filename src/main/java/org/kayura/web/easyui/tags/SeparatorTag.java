package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

/**
 * Created by 枫 on 2014/8/27.
 */
public class SeparatorTag extends TagRender {

	private static final long serialVersionUID = -7489643703500830922L;

	@Override
	public String getEasyuiTag() {
		return null;
	}

	@Override
	public int renderBody() {
		return SKIP_BODY;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<div class=\"menu-sep\"></div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) throws JspException {

	}
}
