package org.kayura.formbuilder.converter;

import org.apache.commons.lang.StringUtils;
import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.field.NumberField;

import com.fasterxml.jackson.databind.JsonNode;

public class NumberFieldJsonConvert extends TextFieldJsonConvert {

	@Override
	public FormField makeFormField() {
		return new NumberField();
	}

	@Override
	public String getFieldType() {
		return EDITOR_FIELDTYPE_NUMBER;
	}

	@Override
	public void convertToModel(FormField formField, JsonNode elementNode) {
		super.convertToModel(formField, elementNode);

		NumberField numberField = (NumberField) formField;

		String unit = getValueAsString(EDITOR_PROP_UNIT, elementNode);
		if (StringUtils.isNotEmpty(unit)) {
			numberField.setPlaceholder(unit);
		}

		formField.setFieldType(FormField.TYPE_NUMBER);
	}

}
