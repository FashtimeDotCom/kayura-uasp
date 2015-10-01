package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by JiangFeng on 2014/8/18.
 */
public class ResourceTag extends TagSupport {

	private static final long serialVersionUID = -2264868683519894012L;

	private String location;
	private String name;
	private String type;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		if (getName().endsWith(".css")) {
			type = "text/css";
		} else if (getName().endsWith(".js")) {
			type = "text/javascript";
		}
		try {
			location = pageContext.getRequest().getServletContext().getContextPath() + "/" + location;
			if (type.equals("text/javascript") || type.equals("js")) {
				out.write("<script type=\"text/javascript\" src=\"" + location + "/" + name + "\"></script>");
			} else if (type.equals("text/css") || type.equals("css")) {
				out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + location + "/" + name + "\">");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	@Override
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
