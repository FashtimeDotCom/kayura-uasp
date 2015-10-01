package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/9.
 */
public class ComboTag extends TextBoxTag {

	private static final long serialVersionUID = -7065711993913113825L;
	public final static String TAG = "combo";

    private int panelWidth = -1;
    private String panelHeight = "auto";
    private int panelMinWidth = -1;
    private int panelMaxWidth = -1;
    private int panelMinHeight = -1;
    private int panelMaxHeight = -1;
    private String panelAlign;
    private Boolean multiple;
    private Boolean selectOnNavigation;
    private String separator;
    private Boolean hasDownArrow;
    private int delay = -1;
    private Object keyHandler;


    @Override
    public String getEasyuiTag() {
        return TAG;
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options = super.getOptions();
        options.put("panelWidth", getPanelWidth());
        options.put("panelHeight", getPanelHeight());
        options.put("panelMinWidth", getPanelMinWidth());
        options.put("panelMaxWidth", getPanelMaxWidth());
        options.put("panelMinHeight", getPanelMinHeight());
        options.put("panelMaxHeight", getPanelMaxHeight());
        options.put("panelAlign", getPanelAlign());
        options.put("multiple", getMultiple());
        options.put("selectOnNavigation", getSelectOnNavigation());
        options.put("separator", getSeparator());
        options.put("hasDownArrow", getHasDownArrow());
        options.put("delay", getDelay());
        options.put("keyHandler", getKeyHandler());
        return options;
    }

    @Override
    public void renderStart(JspWriter out) {
        super.renderStart(out);
        try {
            out.write("<div");
            out.write(" id='" + getId() + getEasyuiTag()+"'");
            out.write(">");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void renderEnd(JspWriter out) {
        try {
            out.write("</div>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String doAfterTagRenderScript() {
        return "$('#" + getId() + getEasyuiTag() + "').appendTo($('#" + getId() + "').combo('panel'));";
    }

    public int getPanelWidth() {
        return panelWidth;
    }

    public void setPanelWidth(int panelWidth) {
        this.panelWidth = panelWidth;
    }

    public String getPanelHeight() {
        return panelHeight;
    }

    public void setPanelHeight(String panelHeight) {
        this.panelHeight = panelHeight;
    }

    public int getPanelMinWidth() {
        return panelMinWidth;
    }

    public void setPanelMinWidth(int panelMinWidth) {
        this.panelMinWidth = panelMinWidth;
    }

    public int getPanelMaxWidth() {
        return panelMaxWidth;
    }

    public void setPanelMaxWidth(int panelMaxWidth) {
        this.panelMaxWidth = panelMaxWidth;
    }

    public int getPanelMinHeight() {
        return panelMinHeight;
    }

    public void setPanelMinHeight(int panelMinHeight) {
        this.panelMinHeight = panelMinHeight;
    }

    public int getPanelMaxHeight() {
        return panelMaxHeight;
    }

    public void setPanelMaxHeight(int panelMaxHeight) {
        this.panelMaxHeight = panelMaxHeight;
    }

    public String getPanelAlign() {
        return panelAlign;
    }

    public void setPanelAlign(String panelAlign) {
        this.panelAlign = panelAlign;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public Boolean getSelectOnNavigation() {
        return selectOnNavigation;
    }

    public void setSelectOnNavigation(Boolean selectOnNavigation) {
        this.selectOnNavigation = selectOnNavigation;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public Boolean getHasDownArrow() {
        return hasDownArrow;
    }

    public void setHasDownArrow(Boolean hasDownArrow) {
        this.hasDownArrow = hasDownArrow;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Object getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(Object keyHandler) {
        this.keyHandler = keyHandler;
    }
}
