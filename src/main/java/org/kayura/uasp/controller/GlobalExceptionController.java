/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.controller;

import org.kayura.core.PostAction;
import org.kayura.core.PostResult;
import org.kayura.web.BaseController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liangxia@live.com
 */
@ControllerAdvice
public class GlobalExceptionController extends BaseController {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView maxUploadSizeException(Exception ex) {

		ModelAndView view = new ModelAndView("views/file/error");

		execute(view.getModel(), new PostAction() {

			@Override
			public void invoke(PostResult r) {
				r.setError("上传的文件超过大小.", ex);
			}
		});

		return view;
	}

}
