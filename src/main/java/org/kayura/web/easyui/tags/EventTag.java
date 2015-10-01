package org.kayura.web.easyui.tags;

import com.alibaba.fastjson.JSON;
import org.kayura.web.model.Expression;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import java.util.*;

/**
 * Created by 枫 on 2014/8/8.
 */
public class EventTag extends TagRender implements Cloneable {

	private static final long serialVersionUID = -1117702831437339063L;
	private String forId;
	private String event;
	private String target;
	private String action;
	private boolean beforeListener;
	private String parentId;

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
		return null;
	}

	@Override
	public void renderStart(JspWriter out) {
		String id = UUID.randomUUID().toString().replace("-", "");
		setId(id);
		Set<ParamTag> paramArray = new HashSet<ParamTag>();
		pageContext.setAttribute(id, paramArray);
	}

	@Override
	public void renderEnd(JspWriter out) {
		try {
			Tag parent = getParent();
			if (parent instanceof TagRender) {
				setParentId(((TagRender) parent).getId());
			}
			getRenderContext().putEvent((EventTag) this.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public String getParam() {
		String params = null;
		Set<ParamTag> paramArray = (Set<ParamTag>) pageContext.getAttribute(getId());
		if (!paramArray.isEmpty()) {
			Map<String, Object> param = new HashMap<String, Object>();
			List<Object> paramArr = new ArrayList<Object>();
			for (ParamTag p : paramArray) {
				if (p.getName() != null) {
					if (p.getValue() != null || p.getExpression() != null) {
						param.put(p.getName(), p.getValue() != null ? p.getValue() : new Expression(p.getExpression()));
					}
				} else {
					if (p.getValue() != null || p.getExpression() != null) {
						paramArr.add(p.getValue() != null ? p.getValue() : new Expression(p.getExpression()));
					}
				}
			}
			paramArr.add(param);
			String s = JSON.toJSONString(paramArr, Expression.mapping);

			params = s.substring(1, s.length() - 1);
		}
		return params;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		EventTag o = null;
		try {
			o = (EventTag) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFor() {
		return forId;
	}

	public void setFor(String forId) {
		this.forId = forId;
	}

	public boolean isBeforeListener() {
		return beforeListener;
	}

	public void setBeforeListener(boolean beforeListener) {
		this.beforeListener = beforeListener;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
