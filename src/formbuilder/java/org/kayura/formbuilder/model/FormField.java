package org.kayura.formbuilder.model;

public abstract class FormField {

	private String id;
	private String filedType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFiledType() {
		return filedType;
	}

	public void setFiledType(String filedType) {
		this.filedType = filedType;
	}

}
