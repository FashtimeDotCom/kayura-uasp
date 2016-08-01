package org.kayura.formbuilder.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.FormModel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class FormJsonConverter implements EditorJsonConstants {

	private List<BaseFormJsonConvert> jsonConverters;

	public FormJsonConverter() {
		jsonConverters = new ArrayList<BaseFormJsonConvert>();
		jsonConverters.add(new LableFieldJsonConvert());
		jsonConverters.add(new TextFieldJsonConvert());
	}

	public ObjectNode convertToJson(FormModel model) {

		return null;
	}

	public FormModel convertToModel(JsonNode modelNode) {

		FormModel formModel = new FormModel();
		JsonNode fieldsArray = modelNode.get(EDITOR_FIELDS);
		if (fieldsArray != null) {
			for (JsonNode fieldNode : fieldsArray) {
				String fieldType = FormJsonConverterUtil.getFieldType(fieldNode);
				if (StringUtils.isNotEmpty(fieldType)) {
					for (BaseFormJsonConvert converter : this.jsonConverters) {
						if (converter.getFieldType().equalsIgnoreCase(fieldType)) {
							FormField formField = converter.makeFormField();
							converter.convertToModel(formField, fieldNode);
							formModel.addField(formField);
							break;
						}
					}
				}
			}
		}
		return formModel;
	}

}
