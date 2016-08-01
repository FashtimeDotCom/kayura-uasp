package org.kayura.formbuilder.converter;

import org.apache.commons.lang.StringUtils;
import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.field.DateField;

import com.fasterxml.jackson.databind.JsonNode;

public class DateFieldJsonConvert extends LableFieldJsonConvert {

	@Override
	public FormField makeFormField() {
		return new DateField();
	}

	@Override
	public String getFieldType() {
		return EDITOR_FIELDTYPE_DATE;
	}

	@Override
	public void convertToModel(FormField formField, JsonNode elementNode) {

		super.convertToModel(formField, elementNode);

		DateField dateField = (DateField) formField;

		String format = getValueAsString(EDITOR_PROP_FORMAT, elementNode);
		if (StringUtils.isNotEmpty(format)) {
			dateField.setFormat(format);
		}

		String unit = getValueAsString(EDITOR_PROP_UNIT, elementNode);
		if (StringUtils.isNotEmpty(unit)) {
			dateField.setUnit(unit);
		}

		String placeholder = FormJsonConverterUtil.getPlaceHolder(elementNode);
		if (StringUtils.isNotEmpty(placeholder)) {
			dateField.setPlaceholder(placeholder);
		}

		dateField.setFieldType(FormField.TYPE_DATE);
	}
}
