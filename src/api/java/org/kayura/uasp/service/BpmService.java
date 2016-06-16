/**
 * Copyright 2015-2016 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service;

import java.util.List;

import org.kayura.type.GeneralResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.uasp.po.BizForm;

/**
 * @author Kayura
 *
 */
public interface BpmService {

	Result<PageList<BizForm>> findBizForms(String tenantId, String keyword, PageParams pageParams);

	Result<List<BizForm>> loadBizForms(String tenantId);
	
	Result<BizForm> getBizFormsById(String bizFormId);

	GeneralResult insertBizForm(BizForm bizForm);

	GeneralResult updateBizForm(BizForm bizForm);

	GeneralResult deleteBizForm(String bizFormId);

}
