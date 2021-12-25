package com.animania.common.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.ReflectionHelper;

/**
 * Utility methods for reflection.
 *
 * @author Choonster & V10lator
 */
public class ReflectionUtil
{
	private static final HashMap<Class, HashMap<String, Method>> methodCache = new HashMap<>();
	private static final HashMap<Class, HashMap<String, Field>> fieldCache = new HashMap<>();

	public static Method findMethod(final Class<?> clazz, final String methodName, @Nullable final String methodObfName, final Class<?>... parameterTypes)
	{
		HashMap<String, Method> classMethods = methodCache.get(clazz);
		if (classMethods == null)
		{
			classMethods = new HashMap<>();
			methodCache.put(clazz, classMethods);
		}

		Method method = classMethods.get(methodName);
		if (method == null)
		{
			method = ReflectionHelper.findMethod(clazz, methodName, methodObfName, parameterTypes);
			classMethods.put(methodName, method);
		}
		return method;

	}

	public static Field findField(Class<?> clazz, String... fieldNames)
	{
		HashMap<String, Field> classFields = fieldCache.get(clazz);
		if (classFields == null)
		{
			classFields = new HashMap<>();
			fieldCache.put(clazz, classFields);
		}

		Field field = classFields.get(fieldNames[0]);
		if (field == null)
		{
			field = ReflectionHelper.findField(clazz, fieldNames);
			classFields.put(fieldNames[0], field);
		}
		return field;
	}
}