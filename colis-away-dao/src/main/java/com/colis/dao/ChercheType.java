package com.colis.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ChercheType {

	public ChercheType() {
	}

	public static Class<?> getGenericClass(Class<?> clazz)
	{
		Type t = clazz.getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		if (pt.getActualTypeArguments()[0] instanceof Class)
			return (Class<?>) pt.getActualTypeArguments()[0];
		else
			return null;
	}
}
