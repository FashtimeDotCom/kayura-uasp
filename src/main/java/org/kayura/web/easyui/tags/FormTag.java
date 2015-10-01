package org.kayura.web.easyui.tags;

import com.alibaba.fastjson.JSON;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/9.
 */
public class FormTag extends TagRender {

	private static final long serialVersionUID = -4820907451046567443L;
	public static final String TAG = "form";

	private String classStyle;
	private String style;
	private String method = "post";
	private Boolean novalidate;
	private Boolean ajax;
	private Object queryParams;
	private String url;
	private Object data;
	private String loadUrl;
	private Boolean multipart;

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
		options.put("novalidate", getNovalidate());
		options.put("ajax", getAjax());
		options.put("queryParams", getQueryParams());
		options.put("url", getUrl());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<form");
			if (getId() != null) {
				out.write(" id=\"" + getId() + "\"");
			}
			if (getClassStyle() != null) {
				out.write(" class=\"" + getClassStyle() + "\"");
			}
			if (getStyle() != null) {
				out.write(" style=\"" + getStyle() + "\"");
			}
			if (getMethod() != null) {
				out.write(" method='" + getMethod() + "'");
			}
			if (getMultipart() != null && getMultipart()) {
				out.write(" enctype=\"multipart/form-data\"");
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
			out.write("</form>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String doAfterTagRenderScript() {
		StringBuilder sw = new StringBuilder();
		if (getData() != null) {
			sw.append("$('#").append(getId()).append("').form('load',").append(JSON.toJSONString(getData()))
					.append(");");
		}
		if (getLoadUrl() != null) {
			sw.append("$('#").append(getId()).append("').form('load',").append(getLoadUrl()).append(");");
		}
		return sw.toString();
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Boolean getNovalidate() {
		return novalidate;
	}

	public void setNovalidate(Boolean novalidate) {
		this.novalidate = novalidate;
	}

	public Boolean getAjax() {
		return ajax;
	}

	public void setAjax(Boolean ajax) {
		this.ajax = ajax;
	}

	public Object getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Object queryParams) {
		this.queryParams = queryParams;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getLoadUrl() {
		return loadUrl;
	}

	public void setLoadUrl(String loadUrl) {
		this.loadUrl = loadUrl;
	}

	public Boolean getMultipart() {
		return multipart;
	}

	public void setMultipart(Boolean multipart) {
		this.multipart = multipart;
	}
}
