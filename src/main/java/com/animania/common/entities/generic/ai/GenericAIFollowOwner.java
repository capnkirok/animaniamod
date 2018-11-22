package com.animania.common.entities.generic.ai;

import com.animania.common.entities.interfaces.ISleeping;

import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.passive.EntityTameable;

public class GenericAIFollowOwner<T extends EntityTameable & ISleeping> extends EntityAIFollowOwner
{
	private final T tameable;

	public GenericAIFollowOwner(T tameableIn, double followSpeedIn, float minDistIn, float maxDistIn)
	{
		super(tameableIn, followSpeedIn, minDistIn, maxDistIn);
		this.tameable = tameableIn;
	}

	public boolean shouldExecute()
	{

		if (this.tameable.getSleeping())
			return false;
		
		return super.shouldExecute();
	}
}