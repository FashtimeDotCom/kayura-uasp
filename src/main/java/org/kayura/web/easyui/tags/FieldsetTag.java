package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 枫 on 2014/9/17.
 */
public class FieldsetTag extends TagRender {

	private static final long serialVersionUID = -6842334189200167368L;
	public static final String TAG = "fieldset";

	private String legend;
	private String classStyle;
	private String style;
	private Boolean closed;
	private Boolean toggleable;
	private int toggleSpeed = -1;
	private String legendCls;

	@Override
	public String getEasyuiTag() {
		return TAG;
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("closed", getClosed());
		options.put("toggleable", getToggleable());
		options.put("toggleSpeed", getToggleSpeed());
		options.put("legendCls", getLegendCls());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<fieldset ");
			out.write(" id='" + getId() + "'");
			out.write(" class=\"jeasyui-fieldset");
			if (getClassStyle() != null) {
				out.write(" " + getClassStyle());
			}
			out.write("\"");
			out.write(" data-options=\"");
			out.write(optionsToString());
			out.write("\"");
			out.write(">\n");
			out.write("                    <legend class=\"jeasyui-fieldset-legend\">\n");
			if (getToggleable() != null && getToggleable().booleanValue()) {
				if (getClosed() != null && getClosed().booleanValue()) {
					out.write("<i class=\"fa fa-plus\"></i> ");
				} else {
					out.write("<i class=\"fa fa-minus\"></i> ");
				}
			}
			out.write(getLegend() + "\n");
			out.write("                    </legend>\n");
			out.write("                    <div class=\"jeasyui-fieldset-content\"");
			if (getStyle() != null) {
				out.write(" style=\"" + getStyle() + "\"");
			}
			out.write(">");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) throws JspException {
		try {
			out.write("</div></fieldset>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getLegend() {
		return legend;
	}

	public void setLegend(String legend) {
		this.legend = legend;
	}

	public String getClassStyle() {
		return classStyle;
	}

	public void setClassStyle(String classStyle) {
		this.classStyle = classStyle;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public Boolean getToggleable() {
		return toggleable;
	}

	public void setToggleable(Boolean toggleable) {
		this.toggleable = toggleable;
	}

	public int getToggleSpeed() {
		return toggleSpeed;
	}

	public void setToggleSpeed(int toggleSpeed) {
		this.toggleSpeed = toggleSpeed;
	}

	public String getLegendCls() {
		return legendCls;
	}

	public void setLegendCls(String legendCls) {
		this.legendCls = legendCls;
	}
}
