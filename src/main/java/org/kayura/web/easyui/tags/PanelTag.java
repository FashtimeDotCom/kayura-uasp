package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/7.
 */
public class PanelTag extends TagRender {

	private static final long serialVersionUID = -5747959693397775128L;
	public final static String TAG = "panel";

	private String title;
	private String iconCls;
	private String classStyle;
	private String style;
	private int left = -1;
	private int top = -1;
	private String cls;
	private String headerCls;
	private String bodyCls;
	private Boolean fit;
	private Boolean border;
	/**
	 * If set to true,the panel will be resize and do layout when created.
	 */
	private Boolean doSize;
	private String content;
	private Boolean collapsible;
	private Boolean minimizable;
	private Boolean maximizable;
	private Boolean closable;
	private String tools;
	private Boolean collapsed;
	private Boolean minimized;
	private Boolean maximized;
	private Boolean closed;
	private String href;
	private Boolean cache;
	private String loadingMessage;
	private String extractor;
	private String method;
	private Map<String, Object> queryParams;
	private String loader;
	private String header;
	private String footer;

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
		options.put("left", getLeft());
		options.put("top", getTop());
		options.put("title", getTitle());
		options.put("cls", getCls());
		options.put("iconCls", getIconCls());
		options.put("headerCls", getHeaderCls());
		options.put("bodyCls", getBodyCls());
		options.put("content", getContent());
		options.put("tools", getTools());
		options.put("href", getHref());
		options.put("loadingMessage", getLoadingMessage());
		options.put("extractor", getExtractor());
		options.put("method", getMethod());
		options.put("queryParams", getQueryParams());
		options.put("loader", getLoader());
		options.put("doSize", isDoSize());
		options.put("collapsible", isCollapsible());
		options.put("minimizable", isMinimizable());
		options.put("maximizable", isMaximizable());
		options.put("closable", isClosable());
		options.put("collapsed", isCollapsed());
		options.put("minimized", isMinimized());
		options.put("cache", isCache());
		options.put("closed", isClosed());
		options.put("border", isBorder());
		options.put("header", getHeader());
		options.put("footer", getFooter());
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
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

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getHeaderCls() {
		return headerCls;
	}

	public void setHeaderCls(String headerCls) {
		this.headerCls = headerCls;
	}

	public String getBodyCls() {
		return bodyCls;
	}

	public void setBodyCls(String bodyCls) {
		this.bodyCls = bodyCls;
	}

	public Boolean isFit() {
		return fit;
	}

	public void setFit(Boolean fit) {
		this.fit = fit;
	}

	public Boolean isBorder() {
		return border;
	}

	public void setBorder(Boolean border) {
		this.border = border;
	}

	public Boolean isDoSize() {
		return doSize;
	}

	public void setDoSize(Boolean doSize) {
		this.doSize = doSize;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean isCollapsible() {
		return collapsible;
	}

	public void setCollapsible(Boolean collapsible) {
		this.collapsible = collapsible;
	}

	public Boolean isMinimizable() {
		return minimizable;
	}

	public void setMinimizable(Boolean minimizable) {
		this.minimizable = minimizable;
	}

	public Boolean isMaximizable() {
		return maximizable;
	}

	public void setMaximizable(Boolean maximizable) {
		this.maximizable = maximizable;
	}

	public Boolean isClosable() {
		return closable;
	}

	public void setClosable(Boolean closable) {
		this.closable = closable;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public Boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(Boolean collapsed) {
		this.collapsed = collapsed;
	}

	public Boolean isMinimized() {
		return minimized;
	}

	public void setMinimized(Boolean minimized) {
		this.minimized = minimized;
	}

	public Boolean isMaximized() {
		return maximized;
	}

	public void setMaximized(Boolean maximized) {
		this.maximized = maximized;
	}

	public Boolean isClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
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

	public String getLoadingMessage() {
		return loadingMessage;
	}

	public void setLoadingMessage(String loadingMessage) {
		this.loadingMessage = loadingMessage;
	}

	public String getExtractor() {
		return extractor;
	}

	public void setExtractor(String extractor) {
		this.extractor = extractor;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}

	public String getLoader() {
		return loader;
	}

	public void setLoader(String loader) {
		this.loader = loader;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
}
