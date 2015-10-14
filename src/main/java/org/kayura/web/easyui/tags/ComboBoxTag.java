package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;

import org.kayura.web.model.RawString;

import java.io.IOException;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/10.
 */
public class ComboBoxTag extends ComboTag {

	private static final long serialVersionUID = -7466933922705893152L;
	public static final String TAG = "combobox";

	private String valueField;
	private String textField;
	private String groupField;
	private String mode;
	private String url;
	private String method;
	private Object data;
	private String onSelect;
	private Map<String, String> queryParams;

	@Override
	public String getEasyuiTag() {
		return TAG;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = super.getOptions();
		options.put("valueField", getValueField());
		options.put("textField", getTextField());
		options.put("groupField", getGroupField());
		options.put("mode", getMode());
		options.put("url", getUrl());
		options.put("method", getMethod());
		options.put("data", getData());
		// options.put("value",getValue());
		options.put("required", getRequired());
		options.put("queryParams", getQueryParams());
		if(getOnSelect() != null){
			options.put("onSelect", new RawString(getOnSelect()));
		}
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			if (getUrl() == null) {
				out.write("<select");
			} else {
				out.write("<input");
			}
			if (getId() != null) {
				out.write(" id=\"" + getId() + "\"");
			}
			if (getName() != null) {
				out.write(" name=\"" + getName() + "\"");
			} else {
				out.write(" name=\"" + getId() + "\"");
			}
			if (getValue() != null) {
				out.write(" value='" + getValue() + "'");
			}
			if (getClassStyle() != null) {
				out.write(" class=\"easyui-" + getEasyuiTag() + " " + getClassStyle() + "\"");
			} else {
				out.write(" class=\"easyui-" + getEasyuiTag() + "\"");
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
			if (getUrl() == null) {
				out.write("</select>");
			} else {
				out.write("</input>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String doAfterTagRenderScript() {
		return null;
	}

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getTextField() {
		return textField;
	}

	public void setTextField(String textField) {
		this.textField = textField;
	}

	public String getGroupField() {
		return groupField;
	}

	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, String> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, String> queryParams) {
		this.queryParams = queryParams;
	}

	public String getOnSelect() {
		return onSelect;
	}

	public void setOnSelect(String onSelect) {
		this.onSelect = onSelect;
	}
}
