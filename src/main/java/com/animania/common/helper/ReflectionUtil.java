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
	@SuppressWarnings("rawtypes")
	private static final HashMap<Class, HashMap<String, Method>> methodCache = new HashMap<Class, HashMap<String, Method>>();
	@SuppressWarnings("rawtypes")
	private static final HashMap<Class, HashMap<String, Field>> fieldCache = new HashMap<Class, HashMap<String, Field>>();
    /**
     * Get a {@link Method} from a {@link Class}.
     *
     * @param clazz
     *            The class
     * @param methodNames
     *            The possible names of the method
     * @param methodTypes
     *            The argument types of the method
     * @param <T>
     *            The class
     * @return The Method
     */
	public static Method findMethod(final Class<?> clazz, final String methodName, @Nullable final String methodObfName, final Class<?>... parameterTypes)
	{
		HashMap<String, Method> classMethods = methodCache.get(clazz);
		if(classMethods == null)
		{
			classMethods = new HashMap<String, Method>();
			methodCache.put(clazz, classMethods);
		}
		
		Method method = classMethods.get(methodName);
		if(method == null)
		{
			method = ReflectionHelper.findMethod(clazz, methodName, methodObfName, parameterTypes);
			classMethods.put(methodName, method);
		}
		return method;
		
	}

    /**
     * Get a {@link Field} from a {@link Class}.
     *
     * @param clazz
     *            The class
     * @param fieldNames
     *            The possible names of the field
     * @return The Field
     */
    public static Field findField(Class<?> clazz, String... fieldNames) {
    	HashMap<String, Field> classFields = fieldCache.get(clazz);
		if(classFields == null)
		{
			classFields = new HashMap<String, Field>();
			fieldCache.put(clazz, classFields);
		}
		
		Field field = classFields.get(fieldNames[0]);
		if(field == null)
		{
			field = ReflectionHelper.findField(clazz, fieldNames);
			classFields.put(fieldNames[0], field);
		}
		return field;
    }
}