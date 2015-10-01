package org.kayura.web.easyui.tags;

import org.kayura.web.model.TreeNode;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/8.
 */
public class TreeTag extends TagRender {

	private static final long serialVersionUID = -6534097948304234849L;
	public static final String TAG = "tree";

	private String classStyle;
	private String style;

	private String url;
	private String method;
	private Boolean animate;
	private Boolean checkbox;
	private Boolean cascadeCheck;
	private Boolean onlyLeafCheck;
	private Boolean lines;
	private Boolean dnd;
	private List<TreeNode> data;
	private Map<String, Object> queryParams;

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
		options.put("url", getUrl());
		options.put("method", getMethod());
		options.put("animate", getAnimate());
		options.put("checkbox", getCheckbox());
		options.put("cascadeCheck", getCascadeCheck());
		options.put("onlyLeafCheck", getOnlyLeafCheck());
		options.put("lines", getLines());
		options.put("dnd", getDnd());
		options.put("data", getData());
		options.put("queryParams", getQueryParams());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<ul ");
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
			out.write("</ul>");
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Boolean getAnimate() {
		return animate;
	}

	public void setAnimate(Boolean animate) {
		this.animate = animate;
	}

	public Boolean getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(Boolean checkbox) {
		this.checkbox = checkbox;
	}

	public Boolean getCascadeCheck() {
		return cascadeCheck;
	}

	public void setCascadeCheck(Boolean cascadeCheck) {
		this.cascadeCheck = cascadeCheck;
	}

	public Boolean getOnlyLeafCheck() {
		return onlyLeafCheck;
	}

	public void setOnlyLeafCheck(Boolean onlyLeafCheck) {
		this.onlyLeafCheck = onlyLeafCheck;
	}

	public Boolean getLines() {
		return lines;
	}

	public void setLines(Boolean lines) {
		this.lines = lines;
	}

	public Boolean getDnd() {
		return dnd;
	}

	public void setDnd(Boolean dnd) {
		this.dnd = dnd;
	}

	public List<TreeNode> getData() {
		return data;
	}

	public void setData(List<TreeNode> data) {
		this.data = data;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}
}
