package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 枫 on 2014/9/29.
 */
public class FileUploadTag extends TagRender {

	private static final long serialVersionUID = 498445329615498244L;
	public static final String TAG = "fileUpload";

	private String style;
	private String classStyle;
	private String url;
	private String filters;
	private String basePath;
	private Boolean multiFile;
	private Object params;
	private String chooseBtnText;
	private String uploadBtnText;
	private String chooseBtnCls;
	private String uploadBtnCls;
	private String maxFileSize;
	private String prompt;

	private final Map<String, Object> paramMap = new HashMap<String, Object>();

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
		if (getFilters() != null) {
			Map<String, String> filterMap = new HashMap<String, String>();
			filterMap.put("title", "File Types");
			filterMap.put("extensions", getFilters());
			options.put("filters", Arrays.asList(filterMap));
		}
		options.put("basePath", getBasePath());
		options.put("multiFile;", getMultiFile());
		options.put("params", getParams());
		if (getParamMap().size() > 0) {
			options.put("params", getParamMap());
		}
		options.put("chooseBtnText", getChooseBtnText());
		options.put("uploadBtnText", getUploadBtnText());
		options.put("chooseBtnCls", getChooseBtnCls());
		options.put("uploadBtnCls", getUploadBtnCls());
		options.put("maxFileSize", getMaxFileSize());
		options.put("prompt", getPrompt());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {

	}

	@Override
	public void renderEnd(JspWriter out) throws JspException {
		try {
			out.write("<input");
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public Boolean getMultiFile() {
		return multiFile;
	}

	public void setMultiFile(Boolean multiFile) {
		this.multiFile = multiFile;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public String getChooseBtnText() {
		return chooseBtnText;
	}

	public void setChooseBtnText(String chooseBtnText) {
		this.chooseBtnText = chooseBtnText;
	}

	public String getUploadBtnText() {
		return uploadBtnText;
	}

	public void setUploadBtnText(String uploadBtnText) {
		this.uploadBtnText = uploadBtnText;
	}

	public String getChooseBtnCls() {
		return chooseBtnCls;
	}

	public void setChooseBtnCls(String chooseBtnCls) {
		this.chooseBtnCls = chooseBtnCls;
	}

	public String getUploadBtnCls() {
		return uploadBtnCls;
	}

	public void setUploadBtnCls(String uploadBtnCls) {
		this.uploadBtnCls = uploadBtnCls;
	}

	public String getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
}
