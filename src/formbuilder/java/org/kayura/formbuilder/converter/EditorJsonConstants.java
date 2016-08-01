package org.kayura.formbuilder.converter;

/**
 * 编辑器 JSON 常量定义
 * 
 * @author liangxia@live.com
 *
 */
public interface EditorJsonConstants {

	// 根属性
	final String EDITOR_FIELDS = "fields";

	/** 字段属性  */
	
	// base
	final String EDITOR_PROP_ID = "cid";
	final String EDITOR_PROP_NAME = "name";
	// lable
	final String EDITOR_PROP_LABEL = "label";
	// text
	final String EDITOR_INPUT_PLACEHOLDER = "placeholder";
	
	
	final String EDITOR_INPUT_SIZE = "size";
	final String EDITOR_MIN_MAX_LENGTH_UNITS = "min_max_length_units";

	
	final String EDITOR_PROP_STARTLABLE = "start_lable";
	final String EDITOR_PROP_ENDLABLE = "end_lable";

	final String EDITOR_PROP_DESCRIPTION = "description";
	final String EDITOR_PROP_FIELDTYPE = "field_type";
	final String EDITOR_PROP_REQUIRED = "required";
	final String EDITOR_PROP_FIELDOPTIONS = "field_options";

	final String EDITOR_PROP_FORMAT = "format";
	final String EDITOR_PROP_UNIT = "unit";

	// 字段类型
	final String EDITOR_FIELDTYPE_LABLE = "lable";
	final String EDITOR_FIELDTYPE_TEXT = "text";
	final String EDITOR_FIELDTYPE_TEXTAREA = "textarea";
	final String EDITOR_FIELDTYPE_DATE = "date";
	final String EDITOR_FIELDTYPE_DATERANGE = "daterange";
	final String EDITOR_FIELDTYPE_NUMBER = "number";
	final String EDITOR_FIELDTYPE_MONEY = "money";
	final String EDITOR_FIELDTYPE_SELECT = "select";
	final String EDITOR_FIELDTYPE_MULTISELECT = "multiselect";
	final String EDITOR_FIELDTYPE_TABLE = "table";
	final String EDITOR_FIELDTYPE_PHOTO = "photo";
	final String EDITOR_FIELDTYPE_ATTACHMENT = "attachment";

	// 下挂选项
	final String EDITOR_OPTIONS = "options";
	final String EDITOR_OPTION_LABEL = "label";
	final String EDITOR_OPTION_VALUE = "value";
	final String EDITOR_OPTION_CHECKED = "checked";

}
