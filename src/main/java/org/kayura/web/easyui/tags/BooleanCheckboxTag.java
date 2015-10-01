package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

/**
 * Created by 枫 on 2014/9/23.
 */
public class BooleanCheckboxTag extends TagRender {

	private static final long serialVersionUID = 3088903022816648125L;
	public static final String TAG = "selectBooleanCheckbox";

    private String name;
    private String style;
    private String classStyle;
    private  Boolean value;

    @Override
    public String getEasyuiTag() {
        return TAG;
    }

    @Override
    public int renderBody() {
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public boolean isJqueryEventBind() {
        return true;
    }

    @Override
    public void renderStart(JspWriter out) {
        try {
            out.write("<input");
            if (getId() != null) {
                out.write(" id=\"" + getId() + "\"");
            }
            if (getName() != null) {
                out.write(" name=\"" + getName() + "\"");
            } else {
                out.write(" name=\"" + getId() + "\"");
            }
            if (getValue() != null && getValue()) {
                out.write(" checked='checked'");
            }
            if (getClassStyle() != null) {
                out.write(" class=\"" + getClassStyle() + "\"");
            }
            if (getStyle() != null) {
                out.write(" style=\"" + getStyle() + "\"");
            }
            out.write(" type='checkbox'");
            out.write(" data-options=\"");
            out.write(optionsToString());
            out.write("\"");
            out.write(">");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void renderEnd(JspWriter out) throws JspException {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getClassStyle() {
        return classStyle;
    }

    public void setClassStyle(String classStyle) {
        this.classStyle = classStyle;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
