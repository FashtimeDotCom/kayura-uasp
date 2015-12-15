/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.vo.convert;

import java.util.ArrayList;
import java.util.List;

import org.kayura.type.PageList;
import org.kayura.uasp.po.User;
import org.kayura.uasp.vo.UserVo;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * @author liangxia@live.com
 */
public class UserConvert {

	private static ModelMapper tv = new ModelMapper();
	private static ModelMapper te = new ModelMapper();

	static {
		
		tv.addMappings(new PropertyMap<User, UserVo>() {
			@Override
			protected void configure() {
				
				// 此处添加 Entity 到 Vo 的属性映射关系.
			}
		});

		te.addMappings(new PropertyMap<UserVo, User>() {
			@Override
			protected void configure() {
				
				// 此处添加 Vo 到 Entity 的属性映射关系.
			}
		});
	}

	public static PageList<UserVo> toVos(PageList<User> list) {

		List<UserVo> voList = new ArrayList<UserVo>();
		List<User> rows = list.getRows();
		for (User u : rows) {
			UserVo to = tv.map(u, UserVo.class);
			voList.add(to);
		}

		return new PageList<UserVo>(voList, list.getPaginator());
	}

	public static UserVo toVo(User entity) {

		UserVo vo = tv.map(entity, UserVo.class);
		return vo;
	}

	public static User toEntity(UserVo vo) {

		User entity = te.map(vo, User.class);
		return entity;
	}
}
