package org.kayura.web.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.security.LoginUser;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.web.ui.UISupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class RestResource {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	protected UISupport ui;

	protected ObjectMapper objectMapper = new ObjectMapper();

	public PageParams getPageParams(HttpServletRequest req) {
		return ui.getPageParams(req);
	}

	public void putData(Map<String, Object> model, PageList<?> pageList) {
		ui.putData(model, pageList);
	}

	public void postExecute(Map<String, Object> model, PostAction postAction) {

		PostResult postResult = new PostResult();

		try {
			postAction.invoke(postResult);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			postResult.setError(e.getMessage());
		}

		model.clear();
		model.put("success", postResult.isSucceed());
		model.put("type", postResult.getType());
		model.put("message", postResult.getMessage());
		model.put("data", postResult.getData());
	}

	public String json(Object data) {
		String s = null;
		try {
			s = objectMapper.writeValueAsString(data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return s;
	}

	public LoginUser getLoginUser() {
		return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
