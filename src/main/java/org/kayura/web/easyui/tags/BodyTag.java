package org.kayura.web.easyui.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.jsp.JspWriter;

/**
 * Created by 枫 on 2014/8/6.
 */
public class BodyTag extends TagRender {

	private static final long serialVersionUID = 6560129221527471913L;
	private boolean full = true;
    private String var;
    private boolean animation = true;
    private String loadingText = "加载中···";
    private int closeDuration = 500;
    private String classStyle;
    private String style;

    @Override
    public String getEasyuiTag() {
        return "layout";
    }

    @Override
    public int renderBody() {
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public Map<String, Object> getOptions() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("full", full);
        return options;
    }

    @Override
    public void renderStart(JspWriter out) {
        try {
            out.write("<body");
            if (isFull()) {
                out.write(" class=\"easyui-" + getEasyuiTag() + " "+(getClassStyle()==null ? "":getClassStyle())+"\"");
            }else{
                out.write(" class=\"" +(getClassStyle()==null ? "":getClassStyle())+"\"");
            }
            out.write(">");
            if (isFull()) {
                out.write("<div data-options=\"region:'center',border:false\" ");
                if(getStyle()!=null){
                    out.write(" style=\""+getStyle()+"\"");
                }
                out.write(">");
            }
            if (isAnimation()) {
                out.write("<div class='jeasyui-body-mask'><div class='jeasyui-body-mask-text'><span></span>");
                out.write(getLoadingText());
                out.write("</div></div>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void renderEnd(JspWriter out) {
        try {
            if (isFull()) {
                out.write("</div>");
            }
            if (getVar() == null) {
                setVar("body" + UUID.randomUUID().toString().replace("-", ""));
            }
            if(isAnimation()){
                getRenderContext().getScriptBeforeWriter().append("$.parser.onComplete = function(){setTimeout(function(){$('.jeasyui-body-mask').fadeOut('normal',function(){$(this).remove();});},")
                        .append(getCloseDuration()).append(");};\n");
            }
            getRenderContext().write(out, getVar());
            out.write("</body>");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public boolean isAnimation() {
        return animation;
    }

    public void setAnimation(boolean animation) {
        this.animation = animation;
    }

    public String getLoadingText() {
        return loadingText;
    }

    public void setLoadingText(String loadingText) {
        this.loadingText = loadingText;
    }

    public int getCloseDuration() {
        return closeDuration;
    }

    public void setCloseDuration(int closeDuration) {
        this.closeDuration = closeDuration;
    }
}
