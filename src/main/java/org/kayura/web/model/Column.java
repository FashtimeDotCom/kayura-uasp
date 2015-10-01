package org.kayura.web.model;

import org.kayura.web.easyui.tags.ColumnTag;

/**
 * Created by JiangFeng on 2014/9/29.
 */
public class Column {

    private String field;
    private String title;
    private Object width;
    private String align;
    private JsFunction formatter;

    public Column() {
    }

    public Column(ColumnTag columnTag){
        this.field = columnTag.getField();
        this.title = columnTag.getTitle();
        this.align = columnTag.getAlign();
        try{
            this.width = Integer.valueOf(columnTag.getWidth());
        }catch (Exception e){
            this.width = columnTag.getWidth();
        }
        if(columnTag.getFormatter()!=null){
            this.formatter =new JsFunction(columnTag.getFormatter());
        }
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getWidth() {
        return width;
    }

    public void setWidth(Object width) {
        this.width = width;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public JsFunction getFormatter() {
        return formatter;
    }

    public void setFormatter(JsFunction formatter) {
        this.formatter = formatter;
    }
}
