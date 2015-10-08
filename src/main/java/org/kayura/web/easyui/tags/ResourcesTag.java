package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by 枫 on 2014/8/6.
 */
public class ResourcesTag extends TagSupport {

	private static final long serialVersionUID = -2679745027336062493L;

	private String location;
	private String theme = "default";

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		location = pageContext.getRequest().getServletContext().getContextPath() + "/" + location;
		try {
			out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + location + "/themes/" + theme + "/easyui.css\">");
			out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + location + "/themes/icon.css\">");
			out.write("<script type=\"text/javascript\" src=\"" + location + "/jquery.easyui.min.js\"></script>");
			out.write("<script type=\"text/javascript\" src=\"" + location + "/jquery.easyui.patch.js\"></script>");
			out.write("<script type=\"text/javascript\" src=\"" + location + "/locale/easyui-lang-zh_CN.js\"></script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
}
