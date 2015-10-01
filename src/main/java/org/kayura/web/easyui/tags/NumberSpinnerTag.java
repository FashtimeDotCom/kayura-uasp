package org.kayura.web.easyui.tags;

import java.util.Map;

/**
 * Created by 枫 on 2014/8/13.
 */
public class NumberSpinnerTag extends SpinnerTag {

	private static final long serialVersionUID = -7376520909127123843L;
	public static final String TAG = "numberspinner";

	private Double min;
	private Double max;
	private int precision = -1;
	private String decimalSeparator;
	private String groupSeparator;
	private String prefix;
	private String suffix;

	@Override
	public String getEasyuiTag() {
		return TAG;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = super.getOptions();
		options.put("min", getMin());
		options.put("max", getMax());
		options.put("precision", getPrecision());
		options.put("decimalSeparator", getDecimalSeparator());
		options.put("groupSeparator", getGroupSeparator());
		options.put("prefix", getPrefix());
		options.put("suffix", getSuffix());
		return options;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public String getDecimalSeparator() {
		return decimalSeparator;
	}

	public void setDecimalSeparator(String decimalSeparator) {
		this.decimalSeparator = decimalSeparator;
	}

	public String getGroupSeparator() {
		return groupSeparator;
	}

	public void setGroupSeparator(String groupSeparator) {
		this.groupSeparator = groupSeparator;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}
