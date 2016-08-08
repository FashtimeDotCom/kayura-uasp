package org.kayura.web.rest;

import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.security.LoginUser;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.utils.StringUtils;
import org.kayura.web.ui.UISupport;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class RestResource {

	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	protected UISupport ui;

	@Autowired
	protected ObjectMapper objectMapper;

	public PageParams getPageParams(HttpServletRequest req) {
		return ui.getPageParams(req);
	}

	public void putData(Map<String, Object> model, PageList<?> pageList) {
		ui.putData(model, pageList);
	}

	public Map<String, Object> result(Integer code, String message, Object data) {

		Map<String, Object> map = new HashMap<String, Object>();

		if (code == Result.SUCCEED) {

			map.put("success", true);
		} else {

			String type = "";
			if (code == Result.SUCCEED) {
				type = "success";
			} else if (code == Result.FAILED) {
				type = "failed";
			} else if (code == Result.ERROR) {
				type = "error";
			}

			map.put("success", false);
			map.put("type", type);
		}

		if (StringUtils.isNotEmpty(message)) {
			map.put("message", message);
		}

		if (data != null) {
			map.put("data", data);
		}

		return map;
	}

	public ModelAndView errorPage(Result<?> r) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", r.getMessage());
		return errorPage(map);
	}

	public ModelAndView errorPage(String message, String details) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("details", details);

		return errorPage(map);
	}

	public ModelAndView errorPage(String message) {

		return errorPage(message, "");
	}

	public ModelAndView errorPage(Map<String, Object> map) {

		return new ModelAndView("shared/error", map);
	}

	public Map<String, Object> error(String message) {

		return result(Result.ERROR, message, null);
	}

	public Map<String, Object> success(String message, Object data) {

		return result(Result.SUCCEED, message, data);
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
