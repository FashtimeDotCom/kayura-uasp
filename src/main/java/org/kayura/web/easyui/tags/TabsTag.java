package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/7.
 */
public class TabsTag extends TagRender {

	private static final long serialVersionUID = -2890164714250461010L;
	public static final String TAG = "tabs";

	private Boolean fit;
	private String classStyle;
	private String style;
	private Boolean plain;
	private Boolean border;
	private String tools;
	private String toolPosition;
	private String tabPosition;
	/**
	 * The tab header width, it is valid only when tabPosition is set to 'left'
	 * or 'right'. This property is available since version 1.3.2.
	 */
	private int headerWidth = -1;
	/**
	 * The width of tab strip. This property is available since version 1.3.4.
	 * default value -1 means 'auto'
	 */
	private int tabWidth = -1;
	private int tabHeight = -1;

	private int selected = -1;
	private Boolean showHeader;

	/**
	 * tabs new style from EasyUI 1.4.2
	 */
	private Boolean justified;
	private Boolean narrow;
	private Boolean pill;

	@Override
	public String getEasyuiTag() {
		return TAG;
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("fit", isFit());
		options.put("plain", isPlain());
		options.put("border", isBorder());
		options.put("tools", getTools());
		options.put("toolPosition", getToolPosition());
		options.put("tabPosition", getTabPosition());
		options.put("headerWidth", getHeaderWidth());
		options.put("tabWidth", getTabWidth());
		options.put("tabHeight", getTabHeight());
		options.put("selected", getSelected());
		options.put("showHeader", isShowHeader());
		options.put("justified", getJustified());
		options.put("narrow", getNarrow());
		options.put("pill", getPill());
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
			out.write(" data-options=\"");
			out.write(optionsToString());
			out.write("\"");
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

	public Boolean isFit() {
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

	public Boolean isPlain() {
		return plain;
	}

	public void setPlain(Boolean plain) {
		this.plain = plain;
	}

	public Boolean isBorder() {
		return border;
	}

	public void setBorder(Boolean border) {
		this.border = border;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public String getToolPosition() {
		return toolPosition;
	}

	public void setToolPosition(String toolPosition) {
		this.toolPosition = toolPosition;
	}

	public String getTabPosition() {
		return tabPosition;
	}

	public void setTabPosition(String tabPosition) {
		this.tabPosition = tabPosition;
	}

	public int getHeaderWidth() {
		return headerWidth;
	}

	public void setHeaderWidth(int headerWidth) {
		this.headerWidth = headerWidth;
	}

	public int getTabWidth() {
		return tabWidth;
	}

	public void setTabWidth(int tabWidth) {
		this.tabWidth = tabWidth;
	}

	public int getTabHeight() {
		return tabHeight;
	}

	public void setTabHeight(int tabHeight) {
		this.tabHeight = tabHeight;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public Boolean isShowHeader() {
		return showHeader;
	}

	public void setShowHeader(Boolean showHeader) {
		this.showHeader = showHeader;
	}

	public Boolean getPill() {
		return pill;
	}

	public void setPill(Boolean pill) {
		this.pill = pill;
	}

	public Boolean getNarrow() {
		return narrow;
	}

	public void setNarrow(Boolean narrow) {
		this.narrow = narrow;
	}

	public Boolean getJustified() {
		return justified;
	}

	public void setJustified(Boolean justified) {
		this.justified = justified;
	}
}
