/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liangxia@live.com
 *
 */
public class PostResult implements Serializable {

	private static final long serialVersionUID = -5947820764143437187L;
	private String type;
	private String message;
	private Map<String, String> attr;

	public PostResult() {
		attr = new HashMap<String, String>();
	}

	public String getType() {
		return type;
	}

	public void setError(String message) {
		this.type = "error";
		this.message = message;
	}

	public void setSuccess(String message) {
		this.type = "success";
		this.message = message;
	}

	public void setFailed(String message) {
		this.type = "failed";
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public Map<String, String> getAttr() {
		return attr;
	}

	public void addAttr(String key, String value) {
		attr.put(key, value);
	}
}
