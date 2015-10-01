package org.kayura.web.easyui.tags;

import org.kayura.web.model.Column;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/8.
 */
public class ColumnsTag extends TagRender {

	private static final long serialVersionUID = 3238112299403828830L;
	private Boolean frozen;
	private List<Column> columns;

	@Override
	public String getEasyuiTag() {
		return null;
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("frozen", getFrozen());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		Tag parent = getParent();
		try {
			if (parent instanceof DataGridTag) {
				out.write("<thead");
				String options = optionsToString();
				if (options.length() > 0) {
					out.write(" data-options=\"");
					out.write(options);
					out.write("\"");
				}
				out.write(">");
				out.write("<tr>");
			} else if (parent instanceof ComboGridTag) {
				columns = new ArrayList<Column>();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) {
		try {
			Tag parent = getParent();
			if (parent instanceof DataGridTag) {
				if (bodyContent != null && bodyContent.getString() != null) {
					out.write(bodyContent.getString());
				}
				out.write("</tr>");
				out.write("</thead>");
			} else if (parent instanceof ComboGridTag) {
				ComboGridTag comboGridTag = (ComboGridTag) parent;
				comboGridTag.getColumns().clear();
				comboGridTag.getColumns().add(columns);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean getFrozen() {
		return frozen;
	}

	public void setFrozen(Boolean frozen) {
		this.frozen = frozen;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
}
