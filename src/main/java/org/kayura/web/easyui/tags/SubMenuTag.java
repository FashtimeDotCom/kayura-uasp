package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/7.
 */
public class SubMenuTag extends TagRender {

	private static final long serialVersionUID = 8141069937078203992L;
	private String label;
	private String style;
	private String classStyle;
	private String iconCls;
	private Boolean custom;

	@Override
	public String getEasyuiTag() {
		return null;
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("iconCls", getIconCls());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<div");
			if (getId() != null) {
				out.write(" id=\"" + getId() + "\"");
			}
			if (getClassStyle() != null) {
				out.write(" class=\"" + getClassStyle() + "\"");
			}

			if (getStyle() != null) {
				out.write(" style=\"" + getStyle() + "\"");
			}

			String options = optionsToString();
			if (options.length() > 0) {
				out.write(" data-options=\"");
				out.write(options);
				out.write("\"");
			}
			out.write(">");
			if (getLabel() != null) {
				out.write("<span>");
				out.write(getLabel());
				out.write("</span>");
			}
			out.write("<div");
			if (getCustom() != null && getCustom().booleanValue()) {
				out.write(" class='menu-content'");
			}
			out.write(">");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) {
		try {
			out.write("</div>");
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Boolean getCustom() {
		return custom;
	}

	public void setCustom(Boolean custom) {
		this.custom = custom;
	}
}
