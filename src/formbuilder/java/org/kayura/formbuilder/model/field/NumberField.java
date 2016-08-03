package org.kayura.formbuilder.model.field;

/**
 * 数字格式字段.
 * 
 * @author liangxia@live.com
 * <pre>
 * JSON 格式
 * {
 *     "cid":"c59",
 *     "label":"数字",
 *     "field_type":"number",
 *     "name":"amount",
 *     "description":"请输入数量"
 *     "required":true,
 *     "field_options":{
 *         "min":"1",
 *         "max":"200",
 *         "units":"公斤",
 *         "integer_only":false
 *     }
 * }
 * </pre>
 */
public class NumberField extends LableField {

	private Integer min;
	private Integer max;
	private String unit;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

}
