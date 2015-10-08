/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.vo.convert;

import java.util.ArrayList;
import java.util.List;

import org.kayura.type.PageList;
import org.kayura.uasp.po.Employee;
import org.kayura.uasp.po.User;
import org.kayura.uasp.vo.UserVo;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * @author liangxia@live.com
 */
public class UserConvert {

	static ModelMapper toVoMapper() {

		ModelMapper mm = new ModelMapper();
		mm.addMappings(new PropertyMap<User, UserVo>() {
			@Override
			protected void configure() {
				// this.map().setEmployeeId(this.source.getEmployee().getId());
			}
		});
		return mm;
	}

	static ModelMapper toEntityMapper() {

		ModelMapper mm = new ModelMapper();
		mm.addMappings(new PropertyMap<UserVo, User>() {
			@Override
			protected void configure() {
				this.map().setEmployee(new Employee(this.source.getEmployeeId()));
			}
		});
		return mm;
	}

	public static PageList<UserVo> toVos(PageList<User> list) {

		ModelMapper mm = toVoMapper();

		List<UserVo> voList = new ArrayList<UserVo>();
		List<User> rows = list.getRows();
		for (User u : rows) {
			UserVo to = mm.map(u, UserVo.class);
			voList.add(to);
		}

		return new PageList<UserVo>(voList, list.getPaginator());
	}

	public static UserVo toVo(User entity) {

		ModelMapper mm = toVoMapper();
		UserVo vo = mm.map(entity, UserVo.class);
		return vo;
	}

	public static User toEntity(UserVo vo) {

		ModelMapper mm = toEntityMapper();
		User entity = mm.map(vo, User.class);
		return entity;
	}
}
