package org.kayura.formbuilder.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kayura.formbuilder.model.FormField;

public class FormInstance {

	private String dataId;
	private String modelId;
	private String title;
	private List<FormFieldInstance> fields;
	private Date createTime;
	private String creator;
	private Date updateTime;

	public FormInstance() {
		fields = new ArrayList<FormFieldInstance>();
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<FormFieldInstance> getFields() {
		return fields;
	}

	public void setFields(List<FormFieldInstance> fields) {
		this.fields = fields;
	}

	public void addField(FormField model, Object value) {

		FormFieldInstance field = new FormFieldInstance();
		field.setModel(model);
		field.setValue(value);

		this.fields.add(field);
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
