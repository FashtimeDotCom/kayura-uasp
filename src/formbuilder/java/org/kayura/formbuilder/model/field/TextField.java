package org.kayura.formbuilder.model.field;

/**
 * 
 * @author liangxia@live.com
 * 
 * <pre>
 * {
 * 		"cid":"组件Id",
 * 		"name":"名称",
 * 		"label":"标签",
 * 		"placeholder": "备注信息",
 * 		"description":"123"
 * 		"field_type":"text",
 * 		"required":true,
 * 		"field_options":{
 * 			"minlength":"1",
 * 			"maxlength":"12",
 * 			"length_units": "words"
 * 		}
 * }
 * </pre>
 */
public class TextField extends LableField {

	private String placeHolder;
	private Integer minLength;
	private Integer maxLength;
	private String lengthUnits;

	public Integer getMinlength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public String getLengthUnits() {
		return lengthUnits;
	}

	public void setLengthUnits(String lengthUnits) {
		this.lengthUnits = lengthUnits;
	}

	public String getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(String placeHolder) {
		this.placeHolder = placeHolder;
	}

}
