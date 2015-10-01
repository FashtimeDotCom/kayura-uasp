package org.kayura.web.model;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by JiangFeng on 2014/8/19.
 */
public class Expression {

	public static SerializeConfig mapping = new SerializeConfig();

	static {
		mapping.put(Expression.class, new ObjectSerializer() {

			@Override
			public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features)
					throws IOException {
				serializer.getWriter().write(object.toString());

			}
		});
	}

	private String value;

	public Expression(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		if (value != null) {
			return value;
		} else {
			return "";
		}
	}
}
