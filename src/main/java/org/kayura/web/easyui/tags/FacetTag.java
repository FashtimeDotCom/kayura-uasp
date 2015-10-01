package org.kayura.web.easyui.tags;

import org.kayura.web.model.ScriptWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import java.util.UUID;

/**
 * Created by 枫 on 2014/8/18.
 */
public class FacetTag extends TagRender {

	private static final long serialVersionUID = 1235470717382649967L;

	private String name;
	private String parentId;
	private String body;

	@Override
	public String getEasyuiTag() {
		return null;
	}

	@Override
	public int renderBody() {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public void renderStart(JspWriter out) {
		Tag parent = getParent();
		parentId = ((TagRender) parent).getId();
		if (getName().equals("tools")) {
			if (parent instanceof TabTag) {
				TabTag tag = (TabTag) parent;
				tag.setTools("#" + getId());
			}
		}
	}

	@Override
	public void renderEnd(JspWriter out) throws JspException {
		try {
			Tag parent = getParent();
			if (getName().equals("tools") || getName().equals("menu") || getName().equals("toolbar")) {
				String body = "<div id='" + getId() + "'>" + bodyContent.getString() + "</div>";
				setBody(body);
			} else if (getName().equals("formatter")) {
				if (parent instanceof ColumnTag) {
					ColumnTag columnTag = (ColumnTag) parent;
					ScriptWriter sw = new ScriptWriter(false);
					String random = UUID.randomUUID().toString().replace("-", "");
					sw.write("function ").write("columnFormatter").write(random).write("(val,row,index){\n");
					sw.write("return '").write(bodyContent.getString().trim().replace("\\n", "").replace("'", "\\'"))
							.write("'");
					sw.write("\n}");
					getRenderContext().getScriptBeforeWriter().append(sw.toString());
					columnTag.setFormatter("columnFormatter" + random);
				}
			}
			getRenderContext().putFacet((FacetTag) this.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String doAfterTagRenderScript() {
		StringBuilder sb = new StringBuilder();
		Tag parent = getParent();
		if (getName().equals("menu")) {
			if (parent instanceof TabTag) {
				TabTag tab = (TabTag) parent;
				if (tab.getParent() instanceof TabsTag) {
					TabsTag tabs = (TabsTag) tab.getParent();
					sb.append("$(function(){\n");
					sb.append("var tab = $('#" + tabs.getId() + "').tabs('getTab','" + tab.getTitle() + "');\n");
					sb.append("var mb = $(tab).panel('options').tab.find('a.tabs-inner');\n");
					sb.append("mb.menubutton({menu:'#" + getId() + "'");
					if (tab.getIconCls() != null) {
						sb.append(",iconCls:'").append(tab.getIconCls()).append("'");
					}
					sb.append("}).click(function(){$('#" + tabs.getId() + "').tabs('select','" + tab.getTitle()
							+ "');});");
					sb.append("\n});");
				}
			}
		}
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getId() {
		return getParentId() + getName();
	}

	public void setId(String id) {
		this.id = id;
	}
}
