package org.kayura.web.easyui.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

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
			out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + location + "/easyui/themes/" + theme + "/easyui.css\">\r");
			out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + location + "/easyui/themes/icon.css\">\r");
			out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + location + "/js/juasp.css\">\r");
			out.write("<script type=\"text/javascript\" src=\"" + location + "/js/jquery.min.js\"></script>\r");
			out.write("<script type=\"text/javascript\" src=\"" + location + "/easyui/jquery.easyui.min.js\"></script>\r");
			out.write("<script type=\"text/javascript\" src=\"" + location + "/easyui/locale/easyui-lang-zh_CN.js\"></script>\r");
			out.write("<script type=\"text/javascript\" src=\"" + location + "/easyui/jeasyui.utils.js\"></script>\r");
			out.write("<script type=\"text/javascript\" src=\"" + location + "/js/juasp-core.js\"></script>\r");
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
