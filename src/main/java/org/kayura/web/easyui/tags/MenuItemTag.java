package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/7.
 */
public class MenuItemTag extends TagRender {

	private static final long serialVersionUID = -3842488323145263729L;
	private String text;
	private String href;
	private String style;
	private String classStyle;
	private String iconCls;
	private String name;
	private Boolean selected;

	@Override
	public String getEasyuiTag() {
		return null;
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("href", getHref());
		options.put("iconCls", getIconCls());
		options.put("name", getName());
		options.put("selected", getSelected());
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
				out.write(" class=\"" + classStyle + "\"");
			}

			if (style != null) {
				out.write(" style=\"" + style + "\"");
			}
			String options = optionsToString();
			if (options.length() > 0) {
				out.write(" data-options=\"");
				out.write(options);
				out.write("\"");
			}
			out.write(">");
			if (getText() != null) {
				out.write(getText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) {
		try {
			BodyContent body = getBodyContent();
			if (body != null && body.getString() != null) {
				out.write(body.getString());
			}
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
