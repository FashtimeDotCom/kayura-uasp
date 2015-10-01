package org.kayura.web.model;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by JiangFeng on 2014/9/29.
 */
public class JsonSerializeConfig {

	public static SerializeConfig mapping = new SerializeConfig();

	static {
		mapping.put(JsFunction.class, new ObjectSerializer() {

			@Override
			public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
					throws IOException {
				serializer.getWriter().write(object.toString());
			}
		});
		mapping.put(Expression.class, new ObjectSerializer() {

			@Override
			public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
					throws IOException {
				serializer.getWriter().write(object.toString());
			}
		});
	}
}
