package org.kayura.web.easyui.tags;

import java.util.Map;

/**
 * Created by 枫 on 2014/8/9.
 */
public class DialogTag extends WindowTag {

	private static final long serialVersionUID = -2768298432045893090L;
	public static final String TAG = "dialog";

    private Object toolbar;
    private Object buttons;
    //private Boolean show;

    @Override
    public String getEasyuiTag() {
        return TAG;
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options = super.getOptions();
        options.put("toolbar", getToolbar());
        options.put("buttons", getButtons());
        return options;
    }

    @Override
    public String doAfterTagRenderScript() {
        return "$('#"+getId()+"').dialog('close');";
    }

    public Object getToolbar() {
        return toolbar;
    }

    public void setToolbar(Object toolbar) {
        this.toolbar = toolbar;
    }

    public Object getButtons() {
        return buttons;
    }

    public void setButtons(Object buttons) {
        this.buttons = buttons;
    }
}
