package org.kayura.formbuilder.model;

public abstract class FormField {

	public static final String TYPE_LABLE = "Lable";
	public static final String TYPE_TEXT = "Text";
	public static final String TYPE_TEXTAREA = "TextArea";
	public static final String TYPE_DATE = "Date";
	public static final String TYPE_DATERANGE = "DateRange";
	public static final String TYPE_NUMBER = "Number";
	public static final String TYPE_MONEY = "Money";
	public static final String TYPE_SELECT = "Select";
	public static final String TYPE_MULTISELECT = "MultiSelect";
	public static final String TYPE_TABLE = "Table";
	public static final String TYPE_PHOTO = "Photo";
	public static final String TYPE_ATTACHMENT = "Attachment";

	private String id;
	private String name;
	private String label;
	private String description;
	private String fieldType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
