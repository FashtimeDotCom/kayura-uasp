package org.kayura.formbuilder.model;

import java.util.ArrayList;
import java.util.List;

public class FormModel {

	private String formId;
	private String title;
	private String description;
	private String icon;
	private String creator;
	private List<FormField> items;

	public FormModel() {
		this.items = new ArrayList<FormField>();
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public List<FormField> getItems() {
		return items;
	}

	public void setItems(List<FormField> items) {
		this.items = items;
	}

	public void addField(FormField formField) {
		this.items.add(formField);
	}
}
