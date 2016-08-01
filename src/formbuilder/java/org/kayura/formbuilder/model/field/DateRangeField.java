package org.kayura.formbuilder.model.field;

public class DateRangeField extends DateField {

	private String endLable;
	private String placeholder;
	private String format;
	private String unit;

	public String getEndLable() {
		return endLable;
	}

	public void setEndLable(String endLable) {
		this.endLable = endLable;
	}

	public void setStartLable(String lable) {

		this.setLable(lable);
	}

	public String getStartLable() {

		return this.getLable();
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
