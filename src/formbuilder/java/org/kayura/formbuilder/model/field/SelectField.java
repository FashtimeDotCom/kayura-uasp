package org.kayura.formbuilder.model.field;

import java.util.Map;

public class SelectField extends TextFiled {

	private Map<String, String> options;

	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

}
