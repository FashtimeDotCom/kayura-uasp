package org.kayura.formbuilder.model.field;

public abstract class InputField extends LableField {

	private Boolean required;

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

}
