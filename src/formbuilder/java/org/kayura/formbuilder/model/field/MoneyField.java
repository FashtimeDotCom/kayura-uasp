package org.kayura.formbuilder.model.field;

/**
 * 金额字段
 * 
 * @author liangxia@live.com
 *
 * <pre>
 * {
 *     "cid":"c68",
 *     "label":"金额",
 *     "required":true,
 *     "name":"money",
 *     "description":"请输入金额",
 *     "field_type":"money",
 *     "field_options":{
 *         "units":"元"
 *     }
 * }
 * </pre>
 * 
 */
public class MoneyField extends LableField {

	private String unit;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
