package org.kayura.formbuilder.model.field;

import java.util.ArrayList;
import java.util.List;

/**
 * 单项选择
 * 
 * @author liangxia@live.com
 * 
 * <pre>
 * {
 *     "cid":"c86",
 *     "label":"分类类型",
 *     "field_type":"select",
 *     "required":true,
 *     "name":"selectType",
 *     "description":"请选择其中一项",
 *     "field_options":{
 *         "options":[
 *             {"label":"选项1","checked":false,"value":"v1"},
 *             {"label":"选项2","checked":false,"value":"v2"}
 *         ],
 *         "include_other_option":false
 *     }
 * }
 * 
 * </pre>
 *
 */
public class SelectField extends InputField {

	private List<Option> options;

	public void addOption(String lable, String value, Boolean checked) {

		if (options == null) {
			options = new ArrayList<Option>();
		}

		Option option = new Option();
		option.setLable(lable);
		option.setValue(value);
		option.setChecked(checked);

		options.add(option);
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public static class Option {

		private String lable;
		private String value;
		private Boolean checked;

		public String getLable() {
			return lable;
		}

		public void setLable(String lable) {
			this.lable = lable;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Boolean getChecked() {
			return checked;
		}

		public void setChecked(Boolean checked) {
			this.checked = checked;
		}

	}
}
