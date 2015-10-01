package org.kayura.web.model;

/**
 * Created by 枫 on 2014/9/16.
 */
public class JsFunction {

    private String value;

    public JsFunction(String value) {
        this(value, false);
    }

    public JsFunction(String value, boolean f) {
        if (f) {
            this.value = value;
        } else {
            if (value.contains("function")) {
                this.value = value;
            } else {
                this.value = "function(){return " + value + ".apply(this,arguments);}";
            }
        }

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value != null) {
            return value;
        } else {
            return "";
        }
    }
}
