package com.voy.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class TypeOfT<X> implements ParameterizedType {
	private Class<?> wrapped;

	public TypeOfT(Class<X> wrapped) {
		this.wrapped = wrapped;
	}

	public Type[] getActualTypeArguments() {
		return new Type[] { wrapped };
	}

	public Type getRawType() {
		return List.class;
	}

	public Type getOwnerType() {
		return null;
	}
}
