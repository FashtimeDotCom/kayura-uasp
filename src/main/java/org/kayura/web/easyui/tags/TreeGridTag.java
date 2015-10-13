package org.kayura.web.easyui.tags;

import java.util.Map;

/**
 * Created by 枫 on 2014/8/8.
 */
public class TreeGridTag extends DataGridTag {

	private static final long serialVersionUID = -1901813621601677277L;
	public static final String TAG = "treegrid";

    private String idField;
    private String treeField;
    private Boolean animate;

    @Override
    public String getEasyuiTag() {
        return TAG;
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options =  super.getOptions();
        options.put("idField",getIdField());
        options.put("treeField",getTreeField());
        options.put("animate",getAnimate());
        return options;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getTreeField() {
        return treeField;
    }

    public void setTreeField(String treeField) {
        this.treeField = treeField;
    }

    public Boolean getAnimate() {
        return animate;
    }

    public void setAnimate(Boolean animate) {
        this.animate = animate;
    }
}
