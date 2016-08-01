package org.kayura.formbuilder.converter;

import com.fasterxml.jackson.databind.JsonNode;

public class FormJsonConverterUtil implements EditorJsonConstants {

	public static String getId(JsonNode objectNode) {

		return getValueAsString(EDITOR_PROP_ID, objectNode);
	}

	public static String getFieldType(JsonNode objectNode) {

		return getValueAsString(EDITOR_PROP_FIELDTYPE, objectNode);
	}

	public static String getName(JsonNode objectNode) {

		return getValueAsString(EDITOR_PROP_NAME, objectNode);
	}

	public static String getLable(JsonNode objectNode) {

		return getValueAsString(EDITOR_PROP_LABEL, objectNode);
	}

	public static String getDescription(JsonNode objectNode) {

		return getValueAsString(EDITOR_PROP_DESCRIPTION, objectNode);
	}

	public static String getPlaceHolder(JsonNode objectNode) {

		return getValueAsString(EDITOR_INPUT_PLACEHOLDER, objectNode);
	}

	protected static String getValueAsString(String name, JsonNode objectNode) {
		String propertyValue = null;
		JsonNode propertyNode = objectNode.get(name);
		if (propertyNode != null && propertyNode.isNull() == false) {
			propertyValue = propertyNode.asText();
		}
		return propertyValue;
	}

	protected static Boolean getValueAsBoolean(String name, JsonNode objectNode) {
		Boolean propertyValue = null;
		JsonNode propertyNode = objectNode.get(name);
		if (propertyNode != null && propertyNode.isNull() == false) {
			propertyValue = propertyNode.asBoolean();
		}
		return propertyValue;
	}

}
