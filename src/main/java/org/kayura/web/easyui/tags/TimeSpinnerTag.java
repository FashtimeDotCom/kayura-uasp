package org.kayura.web.easyui.tags;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/13.
 */
public class TimeSpinnerTag extends SpinnerTag {

	private static final long serialVersionUID = -6507551401833899535L;
	public static final String TAG = "timespinner";

    private String separator;
    private Boolean showSeconds;
    private int highlight = -1;
    private Object selections;
    //private String formatter;

    @Override
    public String getEasyuiTag() {
        return TAG;
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options =  super.getOptions();
        options.put("separator",getSeparator());
        options.put("showSeconds",getShowSeconds());
        options.put("highlight",getHighlight());
        if(getSelections() != null){
            if(getSelections() instanceof String){
                List<int[]> aa = JSON.parseArray(getSelections().toString(), int[].class);
                int[][] bb = aa.toArray(new int[][]{});
                options.put("selections", bb);
            }else {
                options.put("selections", getSelections());
            }
        }
        return options;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public Boolean getShowSeconds() {
        return showSeconds;
    }

    public void setShowSeconds(Boolean showSeconds) {
        this.showSeconds = showSeconds;
    }

    public int getHighlight() {
        return highlight;
    }

    public void setHighlight(int highlight) {
        this.highlight = highlight;
    }

    public Object getSelections() {
        return selections;
    }

    public void setSelections(Object selections) {
        this.selections = selections;
    }
}
