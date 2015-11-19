package org.kayura.web.easyui.tags;

import java.util.Map;

/**
 * Created by 枫 on 2014/8/9.
 */
public class TextBoxTag extends ValidateBoxTag {

	private static final long serialVersionUID = -5057820389105988362L;
	public static final String TAG = "textbox";

	private String prompt;
	private Boolean multiline;
	private Boolean editable;
	private Boolean disabled;
	private Boolean readonly;
	private Object icons;
	private String iconCls;
	private String iconAlign;
	private int iconWidth = -1;
	private String buttonText;
	private String buttonIcon;
	private String buttonAlign;

	@Override
	public String getEasyuiTag() {
		if (getType() != null && getType().equals("hidden")) {
			return ValidateBoxTag.TAG;
		}
		return TAG;
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public Map<String, Object> getOptions() {
		
		//Map<String, Object> options = new HashMap<String, Object>();
		
		Map<String, Object> options = super.getOptions();
		
		options.put("prompt", getPrompt());
		options.put("multiline", getMultiline());
		options.put("editable", getEditable());
		options.put("disabled", getDisabled());
		options.put("readonly", getReadonly());
		options.put("icons", getIcons());
		options.put("iconCls", getIconCls());
		options.put("iconAlign", getIconAlign());
		options.put("iconWidth", getIconWidth());
		options.put("buttonText", getButtonText());
		options.put("buttonIcon", getButtonIcon());
		options.put("buttonAlign", getButtonAlign());
		//options.put("required", getRequired());
		//options.put("validType", getValidType());
		return options;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public Boolean getMultiline() {
		return multiline;
	}

	public void setMultiline(Boolean multiline) {
		this.multiline = multiline;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
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

	public Object getIcons() {
		return icons;
	}

	public void setIcons(Object icons) {
		this.icons = icons;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getIconAlign() {
		return iconAlign;
	}

	public void setIconAlign(String iconAlign) {
		this.iconAlign = iconAlign;
	}

	public int getIconWidth() {
		return iconWidth;
	}

	public void setIconWidth(int iconWidth) {
		this.iconWidth = iconWidth;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public String getButtonIcon() {
		return buttonIcon;
	}

	public void setButtonIcon(String buttonIcon) {
		this.buttonIcon = buttonIcon;
	}

	public String getButtonAlign() {
		return buttonAlign;
	}

	public void setButtonAlign(String buttonAlign) {
		this.buttonAlign = buttonAlign;
	}
}
