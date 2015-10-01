package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by jf on 15/3/17.
 */
public class DataListTag extends DataGridTag {

	private static final long serialVersionUID = -7257707185234852180L;
	public static final String TAG = "datalist";

	private Boolean lines;
	private Boolean checkbox;
	private String valueField;
	private String textField;
	private String groupField;

	@Override
	public String getEasyuiTag() {
		return TAG;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = super.getOptions();
		options.put("lines", getLines());
		options.put("checkbox", getCheckbox());
		options.put("valueField", getValueField());
		options.put("textField", getTextField());
		options.put("groupField", getGroupField());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.println("<div");
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
			out.write(">");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) {
		try {
			out.println("</div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean getLines() {
		return lines;
	}

	public void setLines(Boolean lines) {
		this.lines = lines;
	}

	public Boolean getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(Boolean checkbox) {
		this.checkbox = checkbox;
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
}
