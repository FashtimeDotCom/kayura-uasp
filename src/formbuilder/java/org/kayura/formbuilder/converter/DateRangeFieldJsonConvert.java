package org.kayura.formbuilder.converter;

import org.apache.commons.lang.StringUtils;
import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.field.DateRangeField;

import com.fasterxml.jackson.databind.JsonNode;

public class DateRangeFieldJsonConvert extends DateFieldJsonConvert {

	@Override
	public FormField makeFormField() {
		return new DateRangeField();
	}

	@Override
	public String getFieldType() {
		return EDITOR_FIELDTYPE_DATERANGE;
	}

	@Override
	public void convertToModel(FormField formField, JsonNode objectNode) {

		super.convertToModel(formField, objectNode);

		DateRangeField dateRangeField = (DateRangeField) formField;

		String startLable = getValueAsString(EDITOR_PROP_STARTLABLE, objectNode);
		if (StringUtils.isEmpty(startLable)) {
			dateRangeField.setStartLable(startLable);
		}

		String endLable = getValueAsString(EDITOR_PROP_ENDLABLE, objectNode);
		if (StringUtils.isEmpty(endLable)) {
			dateRangeField.setEndLable(endLable);
		}

		dateRangeField.setFieldType(FormField.TYPE_DATERANGE);
	}

}
