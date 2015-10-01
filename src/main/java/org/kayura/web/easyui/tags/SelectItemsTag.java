package org.kayura.web.easyui.tags;

import org.kayura.web.model.SelectItem;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/12.
 */
public class SelectItemsTag extends TagRender {

	private static final long serialVersionUID = -8869715827285763112L;
	private Object value;

	@Override
	public String getEasyuiTag() {
		return null;
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_INCLUDE;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void renderStart(JspWriter out) {
		if (value != null) {
			try {
				if (value instanceof List) {
					List<SelectItem> data = (List<SelectItem>) value;
					for (SelectItem item : data) {
						String selected = (item.getSelected() != null && item.getSelected().booleanValue())
								? " selected='selected'" : "";
						out.write("<option value='" + item.getId() + "' " + selected + ">" + item.getText()
								+ "</options>");

					}
				} else if (value instanceof Map) {
					Map data = (Map) value;
					for (Iterator<Map.Entry> iterator = data.entrySet().iterator(); iterator.hasNext();) {
						Map.Entry entry = iterator.next();
						out.write("<option value='" + entry.getKey() + "' >" + entry.getValue() + "</options>");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void renderEnd(JspWriter out) {

	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
