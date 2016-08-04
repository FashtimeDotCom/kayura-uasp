package org.kayura.formbuilder.converter;

import org.apache.commons.lang.StringUtils;
import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.field.LabelField;

import com.fasterxml.jackson.databind.JsonNode;

public class LabelFieldJsonConvert extends BaseFormJsonConvert {

	@Override
	public FormField makeFormField() {
		return new LabelField();
	}

	@Override
	public String getFieldType() {
		return EDITOR_FIELDTYPE_LABEL;
	}

	public void convertToModel(FormField formField, JsonNode elementNode) {

		super.convertToModel(formField, elementNode);

		LabelField labelField = (LabelField) formField;

		String label = FormJsonConverterUtil.getLable(elementNode);
		if (StringUtils.isNotEmpty(label)) {
			labelField.setLabel(label);
		}

		labelField.setFieldType(FormField.TYPE_LABLE);
	}

	public void convertToJson(JsonNode elementNode, FormField baseField) {

	}

}
