package com.animania.api.interfaces;

import java.util.Iterator;

import com.animania.common.entities.cows.ai.EntityAIAttackMeleeBulls;
import com.animania.common.entities.generic.ai.GenericAIMate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.network.datasync.DataParameter;

public interface ISterilizable extends IAnimaniaAnimal
{
	public DataParameter<Boolean> getSterilizedParam();
	
	default boolean getSterilized()
	{
		DataParameter<Boolean> param = getSterilizedParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}
	
	default void setSterilized(boolean sterilized)
	{
		DataParameter<Boolean> param = getSterilizedParam();
		if (param != null)
			((Entity) this).getDataManager().set(param, sterilized);
	}
	
	public void sterilize();
	
}
