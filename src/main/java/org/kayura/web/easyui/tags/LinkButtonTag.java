package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/7.
 */
public class LinkButtonTag extends TagRender {

	private static final long serialVersionUID = 6677483353163581009L;
	public final static String TAG = "linkbutton";

	private String classStyle;
	private String style;
	private Boolean disabled;
	private Boolean toggle;
	private Boolean selected;
	private String group;
	private Boolean plain;
	private String text;
	private String iconCls;
	private String iconAlign;
	private String size;
	private String href;
	private String onclick;
	private String target;

	@Override
	public String getEasyuiTag() {
		return TAG;
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public boolean isJqueryEventBind() {
		return true;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("disabled", isDisabled());
		options.put("toggle", isToggle());
		options.put("selected", isSelected());
		options.put("group", getGroup());
		options.put("plain", isPlain());
		options.put("iconCls", getIconCls());
		options.put("iconAlign", getIconAlign());
		options.put("size", getSize());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<a");
			if (getId() != null) {
				out.write(" id=\"" + getId() + "\"");
			}
			if (getClassStyle() != null) {
				out.write(" class=\"easyui-" + getEasyuiTag() + " " + getClassStyle() + "\"");
			} else {
				out.write(" class=\"easyui-" + getEasyuiTag() + "\"");
			}
			if (getStyle() != null) {
				out.write(" style=\"" + getStyle() + "\"");
			}
			if (getOnclick() != null) {
				out.write(" onclick=\"" + getOnclick() + "\"");
			}
			if (getHref() == null) {
				out.write(" href='javascript:void(0)'");
			} else {
				out.write(" href='" + getHref() + "' target='" + (getTarget() == null ? "_blank" : getTarget()) + "'");
			}
			out.write(" data-options=\"" + optionsToString() + "\"");
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
			out.println("</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public Boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean isToggle() {
		return toggle;
	}

	public void setToggle(Boolean toggle) {
		this.toggle = toggle;
	}

	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Boolean isPlain() {
		return plain;
	}

	public void setPlain(Boolean plain) {
		this.plain = plain;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getIconAlign() {
		return iconAlign;
	}

	public void setIconAlign(String iconAlign) {
		this.iconAlign = iconAlign;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
