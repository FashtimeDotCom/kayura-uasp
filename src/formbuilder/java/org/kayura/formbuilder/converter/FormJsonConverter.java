package org.kayura.formbuilder.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kayura.formbuilder.model.FormField;
import org.kayura.formbuilder.model.FormModel;
import org.kayura.formbuilder.model.field.TableField;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class FormJsonConverter implements EditorJsonConstants {

	private List<BaseFormJsonConvert> jsonConverters;

	public FormJsonConverter() {
		jsonConverters = new ArrayList<BaseFormJsonConvert>();
		jsonConverters.add(new LableFieldJsonConvert());
		jsonConverters.add(new TextFieldJsonConvert());
		jsonConverters.add(new TextAreaFieldJsonConvert());
		jsonConverters.add(new DateFieldJsonConvert());
		jsonConverters.add(new DateRangeFieldJsonConvert());
		jsonConverters.add(new NumberFieldJsonConvert());
		jsonConverters.add(new MoneyFieldJsonConvert());
		jsonConverters.add(new SelectFieldJsonConvert());
		jsonConverters.add(new MultiSelectFieldJsonConvert());
		jsonConverters.add(new PhotoFieldJsonConvert());
		jsonConverters.add(new AttachmentFieldJsonConvert());
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

					FormField tableField = null;
					for (BaseFormJsonConvert converter : this.jsonConverters) {

						if (converter.getFieldType().equalsIgnoreCase(EDITOR_FIELDTYPE_TABLE_START)) {

							tableField = converter.makeFormField();
							converter.convertToModel(tableField, fieldNode);
							formModel.addField(tableField);
						} else if (converter.getFieldType().equalsIgnoreCase(EDITOR_FIELDTYPE_TABLE_END)) {

							tableField = null;
						} else if (tableField != null) {

							FormField formField = converter.makeFormField();
							converter.convertToModel(formField, fieldNode);
							((TableField) tableField).addChildren(formField);
						} else {

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
		}
		return formModel;
	}

}
