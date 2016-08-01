package org.kayura.formbuilder.converter;

import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.field.SelectField;

import com.fasterxml.jackson.databind.JsonNode;

public class SelectFieldJsonConvert extends TextFieldJsonConvert {

	@Override
	public FormField makeFormField() {
		return new SelectField();
	}

	@Override
	public String getFieldType() {
		return EDITOR_FIELDTYPE_SELECT;
	}

	@Override
	public void convertToModel(FormField formField, JsonNode elementNode) {
		super.convertToModel(formField, elementNode);

		SelectField selectField = (SelectField) formField;

		JsonNode optionsArray = elementNode.get(EDITOR_OPTIONS);
		if (optionsArray != null) {
			for (JsonNode optionNode : optionsArray) {

				String lable = getValueAsString(EDITOR_OPTION_LABEL, optionNode);
				String value = getValueAsString(EDITOR_OPTION_VALUE, optionNode);
				Boolean checked = getValueAsBoolean(EDITOR_OPTION_CHECKED, optionNode);

				selectField.addOption(lable, value, checked);
			}
		}

		selectField.setFieldType(FormField.TYPE_SELECT);
	}
}
