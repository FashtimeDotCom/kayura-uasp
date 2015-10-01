package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/7.
 */
public class MenuButtonTag extends ButtonTag {

	private static final long serialVersionUID = -4039214937912046885L;
	private Boolean plain = true;
	private String menu;
	private String menuAlign;
	private int duration = -1;
	private Boolean custom;
	private Boolean hasDownArrow;

	@Override
	public String getEasyuiTag() {
		return "menubutton";
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = super.getOptions();
		options.put("menu", "#" + getMenu());
		options.put("menuAlign", getMenuAlign());
		options.put("duration", getDuration());
		options.put("hasDownArrow", getHasDownArrow());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		super.renderStart(out);
		try {
			super.renderEnd(out);
			out.write("<div ");
			out.write(" id='" + getMenu() + "'");
			if (getCustom() != null && getCustom().booleanValue()) {
				out.write(" class=\"menu-content\"");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean isPlain() {
		return plain;
	}

	public void setPlain(Boolean plain) {
		this.plain = plain;
	}

	public String getMenu() {
		if (menu == null) {
			return getId() + "menu";
		}
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getMenuAlign() {
		return menuAlign;
	}

	public void setMenuAlign(String menuAlign) {
		this.menuAlign = menuAlign;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Boolean getCustom() {
		return custom;
	}

	public void setCustom(Boolean custom) {
		this.custom = custom;
	}

	public Boolean getHasDownArrow() {
		return hasDownArrow;
	}

	public void setHasDownArrow(Boolean hasDownArrow) {
		this.hasDownArrow = hasDownArrow;
	}
}
