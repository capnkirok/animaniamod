package com.animania.common.entities.generic.ai;

import com.animania.api.interfaces.ISleeping;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.handler.BlockHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIWatchClosest;

public class GenericAIWatchClosest extends EntityAIWatchClosest
{
    public GenericAIWatchClosest(EntityLiving entityIn, Class <? extends Entity > watchTargetClass, float maxDistance)
    {
        super(entityIn, watchTargetClass, maxDistance);
    }

    public GenericAIWatchClosest(EntityLiving entityIn, Class <? extends Entity > watchTargetClass, float maxDistance, float chanceIn)
    {
    	super(entityIn, watchTargetClass, maxDistance, chanceIn);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        
    	boolean isSleeping = false;
    	
    	if(((ISleeping) entity).getSleeping())
    	{
    		isSleeping = true;
    	}
    	
    	else if(entity instanceof EntityAnimaniaPig)
    	{
    		if(entity.world.getBlockState(entity.getPosition().down()).getBlock() == BlockHandler.blockMud)
    			isSleeping = true;
    	}

		if (isSleeping) {
			return false;
		}
    	
    	return super.shouldExecute();
    }
}