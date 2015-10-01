package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/9.
 */
public class ValidateBoxTag extends TagRender {

	private static final long serialVersionUID = 8085903292482406181L;
	public static final String TAG = "validatebox";

	private String classStyle;
	private String style;
	private String name;
	private String type;
	private Object value;
	private Boolean required;
	private Object validType;
	private int delay = -1;
	private String missingMessage;
	private String invalidMessage;
	private String tipPosition;
	private int deltaX = -1;
	private Boolean novalidate;

	@Override
	public String getEasyuiTag() {
		return TAG;
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("required", getRequired());
		options.put("validType", getValidType());
		options.put("delay", getDelay());
		options.put("missingMessage", getMissingMessage());
		options.put("invalidMessage", getInvalidMessage());
		options.put("tipPosition", getTipPosition());
		options.put("deltaX", getDeltaX());
		options.put("novalidate", getNovalidate());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<input");
			if (getId() != null) {
				out.write(" id=\"" + getId() + "\"");
			}
			if (getName() != null) {
				out.write(" name=\"" + getName() + "\"");
			} else {
				out.write(" name=\"" + getId() + "\"");
			}
			if (getValue() != null) {
				out.write(" value='" + getValue() + "'");
			}
			if (getClassStyle() != null) {
				out.write(" class=\"" + getClassStyle() + "\"");
			}
			if (getStyle() != null) {
				out.write(" style=\"" + getStyle() + "\"");
			}
			if (getType() != null) {
				out.write(" type='" + getType() + "'");
			}
			out.write(" data-options=\"");
			out.write(optionsToString());
			out.write("\"");
			out.write(">");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) {

	}

	public String getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Object getValidType() {
		return validType;
	}

	public void setValidType(Object validType) {
		this.validType = validType;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getMissingMessage() {
		return missingMessage;
	}

	public void setMissingMessage(String missingMessage) {
		this.missingMessage = missingMessage;
	}

	public String getInvalidMessage() {
		return invalidMessage;
	}

	public void setInvalidMessage(String invalidMessage) {
		this.invalidMessage = invalidMessage;
	}

	public String getTipPosition() {
		return tipPosition;
	}

	public void setTipPosition(String tipPosition) {
		this.tipPosition = tipPosition;
	}

	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public Boolean getNovalidate() {
		return novalidate;
	}

	public void setNovalidate(Boolean novalidate) {
		this.novalidate = novalidate;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
