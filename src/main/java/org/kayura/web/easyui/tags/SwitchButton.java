package org.kayura.web.easyui.tags;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

public class SwitchButton extends TagRender {

	private static final long serialVersionUID = -5851381366209207061L;
	public static final String TAG = "switchbutton";

	private Number width;
	private Number height;
	private Number handleWidth;
	private Boolean checked;
	private Boolean disabled;
	private Boolean readonly;
	private Boolean reversed;
	private String onText;
	private String offText;
	private String handleText;
	private String value;

	private String onChange;

	@Override
	public String getEasyuiTag() {
		return TAG;
	}

	@Override
	public int renderBody() {
		return SKIP_BODY;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("width", getWidth());
		options.put("height", getHeight());
		options.put("handleWidth", getHandleWidth());

		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderEnd(JspWriter out) throws JspException {
		// TODO Auto-generated method stub

	}

	public Number getWidth() {
		return width;
	}

	public void setWidth(Number width) {
		this.width = width;
	}

	public Number getHeight() {
		return height;
	}

	public void setHeight(Number height) {
		this.height = height;
	}

	public Number getHandleWidth() {
		return handleWidth;
	}

	public void setHandleWidth(Number handleWidth) {
		this.handleWidth = handleWidth;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Boolean getReadonly() {
		return readonly;
	}

	public void setReadonly(Boolean readonly) {
		this.readonly = readonly;
	}

	public Boolean getReversed() {
		return reversed;
	}

	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
	}

	public String getOnText() {
		return onText;
	}

	public void setOnText(String onText) {
		this.onText = onText;
	}

	public String getOffText() {
		return offText;
	}

	public void setOffText(String offText) {
		this.offText = offText;
	}

	public String getHandleText() {
		return handleText;
	}

	public void setHandleText(String handleText) {
		this.handleText = handleText;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

}
