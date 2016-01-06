/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.core;

import org.kayura.type.GeneralResult;
import org.kayura.type.Result;

/**
 * @author liangxia@live.com
 *
 */
public class PostResult extends GeneralResult {

	private static final long serialVersionUID = 247868975293321852L;

	public String getType() {

		Integer code = this.getCode();

		if (code == Result.SUCCEED) {
			return "success";
		} else if (code == Result.FAILED) {
			return "failed";
		} else if (code == Result.ERROR) {
			return "error";
		}

		return "unknown";
	}

	public void setResult(GeneralResult result) {
		this.setCode(result.getCode());
		this.setMessage(result.getMessage());
		this.setException(result.getException());
		this.setData(result.getData());
	}
}
