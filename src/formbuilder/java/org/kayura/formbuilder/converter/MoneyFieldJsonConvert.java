package org.kayura.formbuilder.converter;

import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.field.MoneyField;

import com.fasterxml.jackson.databind.JsonNode;

public class MoneyFieldJsonConvert extends NumberFieldJsonConvert {

	@Override
	public FormField makeFormField() {
		return new MoneyField();
	}

	@Override
	public String getFieldType() {
		return EDITOR_FIELDTYPE_MONEY;
	}

	@Override
	public void convertToModel(FormField formField, JsonNode elementNode) {
		super.convertToModel(formField, elementNode);
		formField.setFieldType(FormField.TYPE_MONEY);
	}
}
