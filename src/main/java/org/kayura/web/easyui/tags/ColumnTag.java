package org.kayura.web.easyui.tags;

import org.kayura.web.model.Column;
import org.kayura.web.model.RawString;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/8.
 */
public class ColumnTag extends TagRender {

	private static final long serialVersionUID = -3014918557228534807L;

	private String title;
	private String field;
	private String width;
	private int rowspan = -1;
	private int colspan = -1;
	private String align;
	private String halign;
	private Boolean sortable;
	private String order;
	private Boolean resizable;
	private Boolean fixed;
	private Boolean hidden;
	private Boolean checkbox;

	private String styler;
	private String sorter;
	private String formatter;
	private String editor;

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
		
		options.put("field", getField());
		options.put("align", getAlign());
		options.put("halign", getHalign());
		options.put("sortable", getSortable());
		options.put("order", getOrder());
		options.put("resizable", getResizable());
		options.put("fixed", getFixed());
		options.put("hidden", getHidden());
		options.put("checkbox", getCheckbox());
		
		if (getFormatter() != null) {
			options.put("formatter", new RawString(getFormatter()));
		}
		if (getStyler() != null) {
			options.put("styler", new RawString(getStyler()));
		}
		if (getEditor() != null) {
			options.put("editor", new RawString(getEditor()));
		}

		options.put("sorter", getSorter());
		options.put("width", getWidth());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {

	}

	@Override
	public void renderEnd(JspWriter out) {
		try {
			Tag parent = getParent();
			if (parent.getParent() instanceof ComboGridTag) {
				ColumnsTag columnsTag = (ColumnsTag) parent;
				columnsTag.getColumns().add(new Column(this));
			} else {
				out.write("<th");
				if (getRowspan() >= 0) {
					out.write(" rowspan='" + getRowspan() + "'");
				}
				if (getColspan() >= 0) {
					out.write(" colspan='" + getColspan() + "'");
				}
				out.write(" data-options=\"");
				out.write(optionsToString());
				out.write("\"");
				out.write(">");
				if (getTitle() != null) {
					out.write(getTitle());
				}
				if (bodyContent != null && bodyContent.getString() != null) {
					out.write(bodyContent.getString());
				}
				out.println("</th>");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getHalign() {
		return halign;
	}

	public void setHalign(String halign) {
		this.halign = halign;
	}

	public Boolean getSortable() {
		return sortable;
	}

	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Boolean getResizable() {
		return resizable;
	}

	public void setResizable(Boolean resizable) {
		this.resizable = resizable;
	}

	public Boolean getFixed() {
		return fixed;
	}

	public void setFixed(Boolean fixed) {
		this.fixed = fixed;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public Boolean getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(Boolean checkbox) {
		this.checkbox = checkbox;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public String getStyler() {
		return styler;
	}

	public void setStyler(String styler) {
		this.styler = styler;
	}

	public String getSorter() {
		return sorter;
	}

	public void setSorter(String sorter) {
		this.sorter = sorter;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
}
