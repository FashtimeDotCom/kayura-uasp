package org.kayura.formbuilder.converter;

import org.apache.commons.lang.StringUtils;
import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.field.LableField;

import com.fasterxml.jackson.databind.JsonNode;

public class LableFieldJsonConvert extends BaseFormJsonConvert {

	@Override
	public FormField makeFormField() {
		return new LableField();
	}

	@Override
	public String getFieldType() {
		return EDITOR_FIELDTYPE_LABLE;
	}

	public void convertToModel(FormField formField, JsonNode elementNode) {

		super.convertToModel(formField, elementNode);

		LableField lableField = (LableField) formField;

		String lable = FormJsonConverterUtil.getLable(elementNode);
		if (StringUtils.isNotEmpty(lable)) {
			lableField.setLable(lable);
		}

		lableField.setFieldType(FormField.TYPE_LABLE);
	}

	public void convertToJson(JsonNode elementNode, FormField baseField) {

	}

}
