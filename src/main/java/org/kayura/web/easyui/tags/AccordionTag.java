/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/7.
 */
public class AccordionTag extends TagRender {

	private static final long serialVersionUID = 3101906113382632812L;

	private Boolean fit;
	private String classStyle;
	private String style;
	private Boolean border;
	private Boolean animate;
	private Boolean multiple;
	private int selected;

	@Override
	public String getEasyuiTag() {
		return "accordion";
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> optionsMap = new HashMap<String, Object>();
		optionsMap.put("fit", fit);
		optionsMap.put("border", border);
		optionsMap.put("animate", animate);
		optionsMap.put("multiple", multiple);
		optionsMap.put("selected", selected);
		return optionsMap;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<div");
			if (getId() != null) {
				out.write(" id=\"" + getId() + "\"");
			}
			if (classStyle != null) {
				out.write(" class=\"easyui-" + getEasyuiTag() + " " + getClassStyle() + "\"");
			} else {
				out.write(" class=\"easyui-" + getEasyuiTag() + "\"");
			}
			if (style != null) {
				out.write(" style=\"" + style + "\"");
			}
			out.write(" data-options=\"" + optionsToString());
			out.write("\"");
			out.write(">\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) {
		try {
			out.write("</div>\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public Boolean getBorder() {
		return border;
	}

	public void setBorder(Boolean border) {
		this.border = border;
	}

	public Boolean getAnimate() {
		return animate;
	}

	public void setAnimate(Boolean animate) {
		this.animate = animate;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}
}
