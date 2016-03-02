package org.kayura.web.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/8.
 */
public class TreeNode {

	public static final String STATE_OPEN = "open";
	public static final String STATE_Closed = "closed";

	private String id;
	private String text;
	private String iconCls = "";
	private boolean checked;
	private String state;
	private final List<TreeNode> children = new ArrayList<TreeNode>();
	private final Map<String, Object> attributes = new HashMap<String, Object>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void addAttr(String key, Object value) {
		this.attributes.put(key, value);
	}

}
