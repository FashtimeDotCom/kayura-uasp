package org.kayura.web.model;

/**
 * Created by JiangFeng on 2014/8/7.
 */
public class ScriptWriter {
    private StringBuilder sb = new StringBuilder();
    private boolean ready;
    private boolean tag;

    public ScriptWriter() {
        this(true);
    }

    public ScriptWriter(boolean tag) {
        this.tag = tag;
        if(tag){
            sb.append("<script>");
        }
    }

    public ScriptWriter write(Object o) {
        sb.append(o);
        return this;
    }

    public ScriptWriter ready(){
        this.ready = true;
        sb.append("$(function(){");
        return this;
    }

    @Override
    public String toString() {
        if(ready){
            sb.append("});");
        }
        if(tag){
            sb.append("</script>");
        }
        return sb.toString();
    }

}
