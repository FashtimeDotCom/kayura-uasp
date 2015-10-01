package org.kayura.web.easyui.tags;

import org.kayura.web.model.ProxyAble;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 枫 on 2014/8/13.
 */
public class TooltipTag extends TagRender implements ProxyAble {

	private static final long serialVersionUID = 3018727036397639162L;
	public static final String TAG = "tooltip";

	private String forId;
	private String position;
	private String content;
	private Boolean trackMouse;
	private int deltaX = -1;
	private int deltaY = -1;
	private String showEvent;
	private String hideEvent;
	private int showDelay = -1;
	private int hideDelay = -1;

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
		options.put("content", getContent());
		options.put("trackMouse", getTrackMouse());
		options.put("deltaX", getDeltaX());
		options.put("deltaY", getDeltaY());
		options.put("showEvent", getShowEvent());
		options.put("hideEvent", getHideEvent());
		options.put("showDelay", getShowDelay());
		options.put("hideDelay", getHideDelay());
		return super.getOptions();
	}

	@Override
	public void renderStart(JspWriter out) {
		if (getFor() != null) {
			setId(getFor());
		} else {
			try {
				out.write("<a id='" + getId() + "' href='javascript:void(0)'>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void renderEnd(JspWriter out) {
		if (getFor() != null) {
		} else {
			try {
				out.write("</a>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getFor() {
		return forId;
	}

	public void setFor(String forId) {
		this.forId = forId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getTrackMouse() {
		return trackMouse;
	}

	public void setTrackMouse(Boolean trackMouse) {
		this.trackMouse = trackMouse;
	}

	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

	public String getShowEvent() {
		return showEvent;
	}

	public void setShowEvent(String showEvent) {
		this.showEvent = showEvent;
	}

	public String getHideEvent() {
		return hideEvent;
	}

	public void setHideEvent(String hideEvent) {
		this.hideEvent = hideEvent;
	}

	public int getShowDelay() {
		return showDelay;
	}

	public void setShowDelay(int showDelay) {
		this.showDelay = showDelay;
	}

	public int getHideDelay() {
		return hideDelay;
	}

	public void setHideDelay(int hideDelay) {
		this.hideDelay = hideDelay;
	}
}
