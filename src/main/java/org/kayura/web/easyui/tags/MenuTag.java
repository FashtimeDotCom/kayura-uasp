package org.kayura.web.easyui.tags;

//import org.kayura.web.model.ScriptWriter;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/7.
 */
public class MenuTag extends TagRender {

	private static final long serialVersionUID = 5783478232720341984L;
	private String classStyle;
	private String style;
	private int zIndex = -1;
	private int left = -1;
	private int top = -1;
	private int minWidth = -1;
	/**
	 * Defines duration time in milliseconds to hide when the mouse leaves the
	 * menu. This property is available since version 1.4.
	 */
	private int duration = -1;
	/**
	 * When true, automatically hides the menu when mouse exits it. This
	 * property is available since version 1.3.5.
	 */
	private Boolean hideOnUnhover;

	private Boolean show = false;

	private String target;
	private Boolean inline;
	private int itemHeight = -1;

	@Override
	public String getEasyuiTag() {
		return "menu";
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("zIndex", zIndex);
		options.put("left", left);
		options.put("top", top);
		options.put("minWidth", minWidth);
		options.put("duration", duration);
		options.put("hideOnUnhover", hideOnUnhover);
		options.put("inline", getInline());
		options.put("itemHeight", getItemHeight());
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
			out.write(" data-options=\"" + optionsToString());
			out.write("\"");
			out.write(">");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) {
		//ScriptWriter sw = new ScriptWriter();
		try {
			out.write("</div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String doAfterTagRenderScript() {
		StringBuilder sb = new StringBuilder();
		if (getTarget() != null) {
			sb.append("$('#").append(getTarget()).append("')");
		} else {
			sb.append("$(document)");
		}
		sb.append(".bind('contextmenu',function(e){\n").append("                e.preventDefault();\n")
				.append("                $('#").append(getId()).append("').menu('show', {\n")
				.append("                    left: e.pageX,\n").append("                    top: e.pageY\n")
				.append("                });\n").append("            });");
		return sb.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getzIndex() {
		return zIndex;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Boolean isHideOnUnhover() {
		return hideOnUnhover;
	}

	public void setHideOnUnhover(Boolean hideOnUnhover) {
		this.hideOnUnhover = hideOnUnhover;
	}

	public Boolean isShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(int itemHeight) {
		this.itemHeight = itemHeight;
	}

	public Boolean getInline() {
		return inline;
	}

	public void setInline(Boolean inline) {
		this.inline = inline;
	}
}
