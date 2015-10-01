package org.kayura.web.easyui.tags;

/**
 * Created by 枫 on 2014/8/13.
 */
public class DateTimeSpinnerTag extends TimeSpinnerTag {

	private static final long serialVersionUID = 8764909564352802167L;
	public static final String TAG = "datetimespinner";

//    private String formatter;

    @Override
    public String getEasyuiTag() {
        return TAG;
    }

//    @Override
//    public String getFormatter() {
//        if (formatter == null) {
//            formatter = (getShowSeconds() != null && getShowSeconds()) ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd HH:mm";
//        }
//        return formatter;
//    }
//
//    @Override
//    public void setFormatter(String formatter) {
//        this.formatter = formatter;
//    }
    
}
