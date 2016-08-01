package org.kayura.formbuilder.converter;

import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.field.PhotoField;

import com.fasterxml.jackson.databind.JsonNode;

public class PhotoFieldJsonConvert extends LableFieldJsonConvert {

	@Override
	public FormField makeFormField() {
		return new PhotoField();
	}

	@Override
	public String getFieldType() {
		return EDITOR_FIELDTYPE_PHOTO;
	}

	@Override
	public void convertToModel(FormField formField, JsonNode elementNode) {
		super.convertToModel(formField, elementNode);
		formField.setFieldType(FormField.TYPE_PHOTO);
	}
}
