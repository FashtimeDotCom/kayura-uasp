package org.kayura.formbuilder.converter;

import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.field.MultiSelectField;
import com.fasterxml.jackson.databind.JsonNode;

public class MultiSelectFieldJsonConvert extends SelectFieldJsonConvert {

	@Override
	public FormField makeFormField() {
		return new MultiSelectField();
	}

	@Override
	public String getFieldType() {
		return EDITOR_FIELDTYPE_MULTISELECT;
	}

	@Override
	public void convertToModel(FormField formField, JsonNode elementNode) {
		super.convertToModel(formField, elementNode);
		formField.setFieldType(FormField.TYPE_MULTISELECT);
	}

}
