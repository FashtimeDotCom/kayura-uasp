package org.kayura.web.easyui.tags;

import java.util.Map;

/**
 * Created by 枫 on 2014/8/12.
 */
public class DateBoxTag extends ValidateBoxTag {

	private static final long serialVersionUID = -3931797810452131160L;
	public static final String TAG = "datebox";

    private String currentText;
    private String closeText;
    private String okText;
    private String formatter = "yyyy-MM-dd";

    @Override
    public String getEasyuiTag() {
        return TAG;
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options = super.getOptions();
        options.put("currentText", getCurrentText());
        options.put("closeText", getCloseText());
        options.put("okText", getOkText());
        return options;
    }
    
    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    public String getCloseText() {
        return closeText;
    }

    public void setCloseText(String closeText) {
        this.closeText = closeText;
    }

    public String getOkText() {
        return okText;
    }

    public void setOkText(String okText) {
        this.okText = okText;
    }

}
