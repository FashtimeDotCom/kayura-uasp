package org.kayura.web.easyui.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jf on 15/3/9. 自定义 侧边栏控件
 */
public class SidebarTag extends TagRender {

	private static final long serialVersionUID = 4561497812877887693L;
	public static final String TAG = "sidebar";

	private String classStyle;
	private String style;
	private String position;

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
		options.put("position", getPosition());
		return options;
	}

	/**
	 * <div id="typeWin"> <div class="sidebar-button"> <button></button> </div>
	 * <div class="sidebar-content" style="width: 500px; "
	 * > <e:panel id="type-set" fit="true"> <e:datagrid id="type-table" url=
	 * "${root}/workflow/processtype/table.action" fit="true"> <e:columns>
	 * <e:column field="pvtName" title="类别名称" width="200" sortable="true"/>
	 * <e:column field="pvtId" title="操作" width="100" /> </e:columns>
	 * </e:datagrid> </e:panel> </div> </div>
	 * 
	 * @param out
	 */
	@Override
	public void renderStart(JspWriter out) {
		try {
			out.write("<div ");
			if (id != null) {
				out.write(" id=\"" + id + "\"");
			}
			if (getClassStyle() != null) {
				out.write(" class=\"" + getClassStyle() + "\"");
			}
			out.write(" data-options=\"" + optionsToString());
			out.write("\"");
			out.write(">");
			out.write("<div class=\"sidebar-button\"><button></button></div><div class=\"sidebar-content\" ");
			if (getStyle() != null) {
				out.write(" style=\"" + getStyle() + " \"");
			}
			out.write("><div style=\"width:100%;height:100%;\">");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void renderEnd(JspWriter out) throws JspException {
		try {
			out.write("</div></div></div>");
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
