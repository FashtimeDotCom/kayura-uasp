package org.kayura.security;

import org.springframework.security.core.AuthenticationException;

public class CaptchaException extends AuthenticationException {

	private static final long serialVersionUID = -2607089165870318039L;

	public CaptchaException(String msg) {
		super(msg);
	}

}
