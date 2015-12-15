/**
 * Copyright 2015-2015 the original author or authors.
 * HomePage: http://www.kayura.org
 */
package org.kayura.uasp.vo.convert;

import org.kayura.uasp.po.RememberMe;
import org.kayura.uasp.vo.RememberMeVo;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * 
 * @author liangxia@live.com
 */
public final class RememberMeConvert {

	private static ModelMapper tv = new ModelMapper();
	private static ModelMapper te = new ModelMapper();

	static {
		
		tv.addMappings(new PropertyMap<RememberMe, RememberMeVo>() {
			@Override
			protected void configure() {
				
				// 此处添加 Entity 到 Vo 的属性映射关系.				
			}
		});

		te.addMappings(new PropertyMap<RememberMeVo, RememberMe>() {
			@Override
			protected void configure() {
				
				// 此处添加 Vo 到 Entity 的属性映射关系.
			}
		});
	}

	public static RememberMeVo toVo(RememberMe entity) {

		RememberMeVo vo = tv.map(entity, RememberMeVo.class);
		return vo;
	}

	public static RememberMe toEntity(RememberMeVo vo) {

		RememberMe entity = te.map(vo, RememberMe.class);
		return entity;
	}
}
