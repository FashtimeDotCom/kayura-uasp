package org.kayura.formbuilder.model.field;

import org.kayura.formbuilder.model.FormField;

public class DateRangeField extends FormField {

	private String[] lable = new String[2];
	private String placeholder;
	private String format;
	private String unit;

	public String[] getLable() {
		return lable;
	}

	public void setLable(String[] lable) {
		this.lable = lable;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
