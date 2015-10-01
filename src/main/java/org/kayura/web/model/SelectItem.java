package org.kayura.web.model;

/**
 * Created by JiangFeng on 2014/8/12.
 */
public class SelectItem {

    private Object id;
    private Object text;
    private Boolean selected;

    public SelectItem() {
    }

    public SelectItem(Object id, Object text) {
        this.id = id;
        this.text = text;
    }

    public SelectItem(Object id, Object text, Boolean selected) {
        this.id = id;
        this.text = text;
        this.selected = selected;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
