/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.vo.convert;

import org.kayura.uasp.po.AutoLogin;
import org.kayura.uasp.vo.AutoLoginVo;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * 
 * @author liangxia@live.com
 */
public final class AutoLoginConvert {

	private static ModelMapper tv = new ModelMapper();
	private static ModelMapper te = new ModelMapper();

	static {

		tv.addMappings(new PropertyMap<AutoLogin, AutoLoginVo>() {
			@Override
			protected void configure() {

				// 此处添加 Entity 到 Vo 的属性映射关系.
			}
		});

		te.addMappings(new PropertyMap<AutoLoginVo, AutoLogin>() {
			@Override
			protected void configure() {

				// 此处添加 Vo 到 Entity 的属性映射关系.
			}
		});
	}

	public static AutoLoginVo toVo(AutoLogin entity) {

		AutoLoginVo vo = tv.map(entity, AutoLoginVo.class);
		return vo;
	}

	public static AutoLogin toEntity(AutoLoginVo vo) {

		AutoLogin entity = te.map(vo, AutoLogin.class);
		return entity;
	}
}
