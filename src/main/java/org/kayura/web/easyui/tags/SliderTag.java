package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/13.
 */
public class SliderTag extends TagRender {

	private static final long serialVersionUID = 964708038307046029L;
	public static final String TAG = "slider";

	private String classStyle;
	private String style;
	private String mode;
	private Boolean reversed;
	private Boolean showTip;
	private Boolean disabled;
	private String value;
	private Integer min;
	private Integer max;
	private Integer step;
	private Object rule;

	private Boolean range;

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
		options.put("mode", getMode());
		options.put("reversed", getReversed());
		options.put("showTip", getShowTip());
		options.put("disabled", getDisabled());
		options.put("min", getMin());
		options.put("max", getMax());
		options.put("step", getStep());
		options.put("rule", getRule());
		options.put("range", getRange());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<input ");
			if (id != null) {
				out.write(" id=\"" + id + "\"");
			}
			if (getClassStyle() != null) {
				out.write(" class=\"" + getClassStyle() + "\"");
			}
			if (getStyle() != null) {
				out.write(" style=\"" + getStyle() + "\"");
			}
			if (getValue() != null) {
				out.write(" value=\"" + getValue() + "\"");
			}
			out.write(" data-options=\"" + optionsToString());
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Boolean getReversed() {
		return reversed;
	}

	public void setReversed(Boolean reversed) {
		this.reversed = reversed;
	}

	public Boolean getShowTip() {
		return showTip;
	}

	public void setShowTip(Boolean showTip) {
		this.showTip = showTip;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Object getRule() {
		return rule;
	}

	public void setRule(Object rule) {
		this.rule = rule;
	}

	public Boolean getRange() {
		return range;
	}

	public void setRange(Boolean range) {
		this.range = range;
	}
}
