package org.kayura.formbuilder.converter;

import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.field.TextField;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;

public class TextFieldJsonConvert extends LableFieldJsonConvert {

	@Override
	public FormField makeFormField() {
		return new TextField();
	}

	@Override
	public String getFieldType() {
		return EDITOR_FIELDTYPE_TEXT;
	}

	@Override
	public void convertToModel(FormField formField, JsonNode elementNode) {

		super.convertToModel(formField, elementNode);

		String placeHolder = FormJsonConverterUtil.getPlaceHolder(elementNode);
		if (StringUtils.isNotEmpty(placeHolder)) {

			TextField textField = (TextField) formField;
			textField.setPlaceHolder(placeHolder);
		}

		formField.setFieldType(FormField.TYPE_TEXT);
	}

	@Override
	public void convertOptionsToModel(FormField formField, JsonNode optionNode) {
		super.convertOptionsToModel(formField, optionNode);

		TextField textField = (TextField) formField;

		String lengthUnits = getValueAsString(EDITOR_PROP_LENGTH_UNITS, optionNode);
		if (StringUtils.isNotEmpty(lengthUnits)) {
			textField.setLengthUnits(lengthUnits);
		}

		Integer maxlength = getValueAsInteger(EDITOR_PROP_MAXLENGTH, optionNode);
		if (maxlength != null) {
			textField.setMaxLength(maxlength);
		}

		Integer minlength = getValueAsInteger(EDITOR_PROP_MINLENGTH, optionNode);
		if (minlength != null) {
			textField.setMinLength(minlength);
		}

	}
}
