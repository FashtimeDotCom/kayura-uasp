package org.kayura.web.easyui.tags;

import java.util.Map;

/**
 * Created by JiangFeng on 2014/9/4.
 */
public class SelectManyCheckBoxTag extends ComboBoxTag {

	private static final long serialVersionUID = 6942930267008655690L;

	public static final String TAG = "selectManyCheckbox";
	private int columns = -1;
	private Boolean checkAllable;

	@Override
	public String getEasyuiTag() {
		return TAG;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = super.getOptions();
		options.put("columns", getColumns());
		options.put("checkAllable", getCheckAllable());
		return options;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Boolean getCheckAllable() {
		return checkAllable;
	}

	public void setCheckAllable(Boolean checkAllable) {
		this.checkAllable = checkAllable;
	}
}
