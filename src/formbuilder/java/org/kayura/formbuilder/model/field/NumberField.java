package org.kayura.formbuilder.model.field;

import org.kayura.formbuilder.model.FormField;

public class NumberField extends FormField {

	private String lable;
	private String placeholder;

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

}
