package org.kayura.web.easyui.tags;

import java.util.Map;

/**
 * Created by 枫 on 2014/8/8.
 */
public class WindowTag extends PanelTag {

	private static final long serialVersionUID = 4420407710902884619L;
	private final static String TAG = "window";

	private int zIndex = -1;
	private Boolean draggable;
	private Boolean resizable;
	private Boolean shadow;
	private Boolean inline;
	private Boolean modal;

	@Override
	public String getEasyuiTag() {
		return TAG;
	}

	@Override
	public Map<String, Object> getOptions() {
		Map<String, Object> options = super.getOptions();
		options.put("zIndex", getzIndex());
		options.put("draggable", getDraggable());
		options.put("resizable", getResizable());
		options.put("shadow", getShadow());
		options.put("inline", getInline());
		options.put("modal", getModal());
		return options;
	}

	public int getzIndex() {
		return zIndex;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	public Boolean getDraggable() {
		return draggable;
	}

	public void setDraggable(Boolean draggable) {
		this.draggable = draggable;
	}

	public Boolean getResizable() {
		return resizable;
	}

	public void setResizable(Boolean resizable) {
		this.resizable = resizable;
	}

	public Boolean getShadow() {
		return shadow;
	}

	public void setShadow(Boolean shadow) {
		this.shadow = shadow;
	}

	public Boolean getInline() {
		return inline;
	}

	public void setInline(Boolean inline) {
		this.inline = inline;
	}

	public Boolean getModal() {
		return modal;
	}

	public void setModal(Boolean modal) {
		this.modal = modal;
	}
}
