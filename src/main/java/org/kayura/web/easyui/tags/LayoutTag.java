package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/6.
 */
public class LayoutTag extends TagRender {

	private static final long serialVersionUID = 6580790607798362742L;
	private Boolean fit = false;
	private String classStyle;
	private String style;

	@Override
	public String getEasyuiTag() {
		return "layout";
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("fit", getFit());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<div");
			if (id != null) {
				out.write(" id=\"" + id + "\"");
			}
			if (classStyle != null) {
				out.write(" class=\"" + getClassStyle() + "\"");
			}
			if (style != null) {
				out.write(" style=\"" + style + "\"");
			}
			out.write(" data-options=\"" + optionsToString() + "\"");
			out.write(">");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) {
		try {
			out.write("</div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getFit() {
		return fit;
	}

	public void setFit(Boolean fit) {
		this.fit = fit;
	}

	public String getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
