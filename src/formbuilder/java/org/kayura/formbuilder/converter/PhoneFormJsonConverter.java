package org.kayura.formbuilder.converter;

import org.kayura.formbuilder.model.FormModel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class PhoneFormJsonConverter {

	public ObjectNode convertToJson(FormModel model) {

		return null;
	}

	public FormModel convertToModel(JsonNode modelNode) {

		FormModel formModel = new FormModel();

		return formModel;
	}

}
