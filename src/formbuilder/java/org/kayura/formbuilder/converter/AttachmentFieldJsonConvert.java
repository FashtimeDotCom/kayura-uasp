package org.kayura.formbuilder.converter;

import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.field.AttachmentField;

import com.fasterxml.jackson.databind.JsonNode;

public class AttachmentFieldJsonConvert extends LableFieldJsonConvert {

	@Override
	public FormField makeFormField() {
		return new AttachmentField();
	}

	@Override
	public String getFieldType() {
		return EDITOR_FIELDTYPE_ATTACHMENT;
	}

	@Override
	public void convertToModel(FormField formField, JsonNode elementNode) {

		super.convertToModel(formField, elementNode);
		formField.setFieldType(FormField.TYPE_ATTACHMENT);
	}
}
