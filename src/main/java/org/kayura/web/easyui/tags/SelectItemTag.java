package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;

/**
 * Created by JiangFeng on 2014/8/12.
 */
public class SelectItemTag extends TagRender {

	private static final long serialVersionUID = 4026077087904522362L;
	private String itemLabel;
	private String itemValue;
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
			out.write(" value='" + itemValue + "'");
			String select = (selected != null && selected.booleanValue()) ? " selected='selected'" : "";
			out.write(select);
			out.write(">");
			out.write(itemLabel);
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

	public String getItemLabel() {
		return itemLabel;
	}

	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
