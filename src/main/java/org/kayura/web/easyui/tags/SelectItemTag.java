package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;

/**
 * Created by JiangFeng on 2014/8/12.
 */
public class SelectItemTag extends TagRender {

	private static final long serialVersionUID = 4026077087904522362L;
	private String label;
	private String value;
	private Boolean selected;

	@Override
	public String getEasyuiTag() {
		return null;
	}

	@Override
	public int renderBody() {
		return SKIP_BODY;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<option ");
			out.write(" value='" + getValue() + "'");
			String select = (selected != null && selected.booleanValue()) ? " selected='selected'" : "";
			out.write(select);
			out.write(">");
			out.write(getLabel());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) {
		try {
			out.write("</option>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
