package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;

import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.passive.EntityTameable;

public class GenericAISit<T extends EntityTameable & ISleeping> extends EntityAISit
{

	T entity;
	
	public GenericAISit(T entityIn)
	{
		super(entityIn);
		this.entity = entityIn;
	}

	@Override
	public boolean shouldExecute()
	{
		if(this.entity.getSleeping())
			return false;
		
		return super.shouldExecute();
	}
	
}
