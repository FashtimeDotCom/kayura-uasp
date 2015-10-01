package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JiangFeng on 2014/8/8.
 */
public class TreeNodeTag extends TagRender {

	private static final long serialVersionUID = -975174490073491519L;
	private String text;
	private String iconCls;
	private String href;
	private String state;
	private Boolean checked;
	private Object attributes;

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
		options.put("state", getState());
		options.put("href", getHref());
		options.put("iconCls", getIconCls());
		options.put("checked", getChecked());
		options.put("attributes", getAttributes());
		return options;
	}

	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<li ");
			if (optionsToString().length() > 0) {
				out.write(" data-options=\"" + optionsToString() + "\"");
			}
			out.write("><span>" + getText() + "</span>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int doAfterBody() throws JspException {
		String body = bodyContent.getString();
		if (body != null && body.trim().length() > 0) {
			try {
				bodyContent.getEnclosingWriter().write("<ul>" + body + "</ul>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return SKIP_BODY;
	}

	@Override
	public void renderEnd(JspWriter out) {
		try {
			out.write("</li>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
