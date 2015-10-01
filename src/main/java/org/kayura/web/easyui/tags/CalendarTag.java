package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/13.
 */
public class CalendarTag extends TagRender {

	private static final long serialVersionUID = 5819540280070023017L;
	public static final String TAG = "calendar";

	private String classStyle;
	private String style;
	private Boolean fit;
	private Boolean border;
	private int firstDay = -1;
	private Object weeks;
	private Object months;
	private int year = -1;
	private int month = -1;
	private Object current;

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
		options.put("fit", getFit());
		options.put("border", getBorder());
		options.put("firstDay", getFirstDay());
		options.put("weeks", getWeeks());
		options.put("months", getMonths());
		options.put("year", getYear());
		options.put("month", getMonth());
		options.put("current", getCurrent());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<div ");
			if (getId() != null) {
				out.write(" id=\"" + getId() + "\"");
			}
			if (getClassStyle() != null) {
				out.write(" class=\"" + getClassStyle() + "\"");
			}
			if (getStyle() != null) {
				out.write(" style=\"" + getStyle() + "\"");
			}
			out.write(" data-options=\"" + optionsToString());
			out.write("\"");
			out.write(">");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) {
		try {
			out.write("</div>");
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public Boolean getFit() {
		return fit;
	}

	public void setFit(Boolean fit) {
		this.fit = fit;
	}

	public Boolean getBorder() {
		return border;
	}

	public void setBorder(Boolean border) {
		this.border = border;
	}

	public int getFirstDay() {
		return firstDay;
	}

	public void setFirstDay(int firstDay) {
		this.firstDay = firstDay;
	}

	public Object getWeeks() {
		return weeks;
	}

	public void setWeeks(Object weeks) {
		this.weeks = weeks;
	}

	public Object getMonths() {
		return months;
	}

	public void setMonths(Object months) {
		this.months = months;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Object getCurrent() {
		return current;
	}

	public void setCurrent(Object current) {
		this.current = current;
	}
}
