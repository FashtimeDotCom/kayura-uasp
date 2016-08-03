package org.kayura.formbuilder.model.field;

/**
 * 多项选择框.
 * 
 * @author liangxia@live.com
 * 
 * <pre>
 * JSON 格式
 * {
 *     "label":"你的爱好",
 *     "field_type":"multiselect",
 *     "required":true,
 *     "cid":"c9",
 *     "name":"interest",
 *     "description":"请选择你的兴趣爱好.",
 *     "field_options":{
 *         "options":[
 *             {"label":"选项一","checked":true},
 *             {"label":"选项二","checked":false}
 *         ]
 *     }
 * }
 * </pre>
 */
public class MultiSelectField extends SelectField {

}
