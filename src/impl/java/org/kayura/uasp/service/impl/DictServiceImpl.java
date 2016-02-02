/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.GeneralResult;
import org.kayura.type.PageList;
import org.kayura.type.PageParams;
import org.kayura.type.Result;
import org.kayura.uasp.dao.DictMapper;
import org.kayura.uasp.po.DictDefine;
import org.kayura.uasp.po.DictItem;
import org.kayura.uasp.service.DictService;
import org.kayura.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DictServiceImpl
 *
 * @author liangxia@live.com
 */
@Service
public class DictServiceImpl implements DictService {

	@Autowired
	private DictMapper dictMapper;

	@Override
	public Result<List<DictDefine>> loadDictDefinces(String tenantId) {

		List<DictDefine> list = dictMapper.loadDictDefinces(tenantId);
		return new Result<List<DictDefine>>(Result.SUCCEED, list);
	}

	@Override
	public PageList<DictItem> loadDictItems(String dictId, String parentId, PageParams params) {

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("dictId", dictId);
		if (!StringUtils.isEmpty(parentId)) {
			args.put("parentId", parentId);
		}

		PageList<DictItem> list = dictMapper.findDictItems(args, new PageBounds(params));
		return list;
	}

	@Override
	public Result<DictItem> getDictItemsById(String itemId) {

		DictItem dictItem = dictMapper.getDictItemsById(itemId);
		return new Result<DictItem>(Result.SUCCEED, dictItem);
	}

	@Override
	public GeneralResult insertDictItem(DictItem dictItem) {

		dictMapper.insertDictItem(dictItem);
		return Result.succeed();
	}

	@Override
	public GeneralResult updateDictItem(DictItem dictItem) {

		dictMapper.updateDictItem(dictItem);
		return Result.succeed();
	}

	@Override
	public GeneralResult deleteDictItem(String itemId) {

		dictMapper.deleteDictItem(itemId);
		return Result.succeed();
	}

}
