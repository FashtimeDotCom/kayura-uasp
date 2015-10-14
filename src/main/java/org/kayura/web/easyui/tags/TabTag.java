package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/7.
 */
public class TabTag extends TagRender {

	private static final long serialVersionUID = -4014358344194447750L;

	private String classStyle;
	private String style;
	private String title;
	private String content;
	private String href;
	private Boolean cache;
	private String iconCls;
	private Boolean collapsible;
	private Boolean closable;
	private Boolean selected;
	private String tools;
	private Boolean iframe;
	private Boolean collapsed;
	private int tabWidth = -1;

	public TabTag() {
		super();
	}

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
		if (collapsible == null) {
			Tag tag = this.getParent();
			if (tag != null) {
				if (tag instanceof TabsTag) {
					collapsible = false;
				} else if (tag instanceof AccordionTag) {
					collapsible = true;
				}
			}
		}
		options.put("cache", isCache());
		options.put("content", getContent());
		if (getIframe() == null || !getIframe().booleanValue()) {
			options.put("href", getHref());
		}
		options.put("iconCls", getIconCls());
		options.put("collapsible", getCollapsible());
		options.put("collapsed", getCollapsed());
		options.put("closable", getClosable());
		options.put("selected", getSelected());
		options.put("tools", getTools());
		options.put("tabWidth", getTabWidth());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {

	}

	@Override
	public void renderEnd(JspWriter out) {
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
			if (getTitle() != null) {
				out.write(" title=\"" + getTitle() + "\"");
			}
			out.write(" data-options=\"");
			out.write(optionsToString());
			out.write("\"");
			out.println(">");
			if (getHref() != null && getIframe() != null && getIframe().booleanValue()) {
				out.println("<iframe scrolling=\"yes\" frameborder=\"0\"  src=\"" + getHref()
						+ "\" style=\"width:100%;height:100%;\"></iframe>");
			} else {
				if (bodyContent != null) {
					out.write(bodyContent.getString());
				}
			}
			out.println("</div>");
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Boolean isCache() {
		return cache;
	}

	public void setCache(Boolean cache) {
		this.cache = cache;
	}

	public Boolean getCache() {
		return cache;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Boolean getCollapsible() {
		return collapsible;
	}

	public void setCollapsible(Boolean collapsible) {
		this.collapsible = collapsible;
	}

	public Boolean getClosable() {
		return closable;
	}

	public void setClosable(Boolean closable) {
		this.closable = closable;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public Boolean getIframe() {
		return iframe;
	}

	public void setIframe(Boolean iframe) {
		this.iframe = iframe;
	}

	public int getTabWidth() {
		return tabWidth;
	}

	public void setTabWidth(int tabWidth) {
		this.tabWidth = tabWidth;
	}

	public Boolean getCollapsed() {
		return collapsed;
	}

	public void setCollapsed(Boolean collapsed) {
		this.collapsed = collapsed;
	}
}
