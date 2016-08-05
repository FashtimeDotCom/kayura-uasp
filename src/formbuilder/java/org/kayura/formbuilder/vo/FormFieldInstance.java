package org.kayura.formbuilder.vo;

import org.kayura.formbuilder.model.FormField;

public class FormFieldInstance {

	private FormField model;
	private Object value;

	public FormField getModel() {
		return model;
	}

	public void setModel(FormField model) {
		this.model = model;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
