package com.animania.common.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.animania.common.advancements.criterion.FeedAnimalTrigger;
import com.animania.common.helper.ReflectionUtil;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.CriterionTriggerInstance;

public class AdvancementHandler
{
	
	public static FeedAnimalTrigger feedAnimal;
	
	
	public static void registerCriteria()
	{
		feedAnimal = (FeedAnimalTrigger) registerTrigger(new FeedAnimalTrigger());
	}
	

	public static <T extends CriterionTriggerInstance> CriterionTrigger<T> registerTrigger(CriterionTrigger<T> trigger)
	{
		Method method;

		method = ReflectionUtil.findMethod(CriteriaTriggers.class, "register", "register", CriterionTrigger.class);

		try
		{
			trigger = (CriterionTrigger<T>) method.invoke(null, trigger);
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			System.out.println("Failed to register trigger " + trigger.getId() + "!");
			e.printStackTrace();
		}
		return trigger;
	}

}
