package org.kayura.uasp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private String captchaFieldName;

	public String getCaptchaFieldName() {
		return captchaFieldName;
	}

	public void setCaptchaFieldName(String captchaFieldName) {
		this.captchaFieldName = captchaFieldName;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		HttpSession session = request.getSession();
		Object needvc = session.getAttribute("needvc");
		if (needvc != null && (Boolean) needvc) {
			
			String requestCaptcha = request.getParameter(this.getCaptchaFieldName());
			String genCaptcha = (String) session.getAttribute("j_captcha");

			logger.info("开始校验验证码，生成的验证码为：" + genCaptcha + " ，输入的验证码为：" + requestCaptcha);

			if (!genCaptcha.equals(requestCaptcha)) {
				session.setAttribute("vcerror", 1);
				throw new CaptchaException(this.messages
						.getMessage("AbstractUserDetailsAuthenticationProvider.badCaptcha", null, "Default", null));
			}

			session.removeAttribute("needvc");
		}
		return super.attemptAuthentication(request, response);
	}

}