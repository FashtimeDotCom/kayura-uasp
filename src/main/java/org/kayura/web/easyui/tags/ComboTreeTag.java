package org.kayura.web.easyui.tags;

import org.kayura.web.model.ScriptWriter;
import org.kayura.web.model.TreeNode;

import java.util.List;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/12.
 */
public class ComboTreeTag extends ComboTag {

	private static final long serialVersionUID = 1352964914840322023L;
	public static final String TAG = "combotree";

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
	private Boolean editable;
	private String panelHeight;

	@Override
	public String getEasyuiTag() {
		return TAG;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = super.getOptions();
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
		options.put("editable", getEditable());
		options.put("panelHeight", getPanelHeight());
		return options;
	}

	@Override
	public String doAfterTagRenderScript() {
		if (getMultiple() != null && getMultiple().booleanValue()) {
			String[] values = new String[] {};
			if (getValue() != null && getValue() instanceof String) {
				String split = getSeparator() == null ? "," : getSeparator();
				String value = getValue().toString();
				values = value.split(split);

			} else if (getValue() != null && getValue() instanceof String[]) {
				values = (String[]) getValue();
			}
			ScriptWriter sw = new ScriptWriter(false);
			sw.write("$('#").write(getId()).write("').").write(getEasyuiTag()).write("('setValues',[");
			for (int i = 0, size = values.length; i < size; i++) {
				sw.write("'").write(values[i]).write("'");
				if (i != size - 1) {
					sw.write(",");
				}
			}
			sw.write("]);");
			return sw.toString();
		}
		return super.doAfterTagRenderScript();
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

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public String getPanelHeight() {
		return panelHeight;
	}

	public void setPanelHeight(String panelHeight) {
		this.panelHeight = panelHeight;
	}
}
