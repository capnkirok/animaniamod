package com.animania.common.entities.generic.ai;

import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.goats.EntityAnimaniaGoat;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.entities.interfaces.ISleeping;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehogBase;
import com.animania.common.entities.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.entities.sheep.EntityAnimaniaSheep;
import com.animania.common.handler.BlockHandler;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;

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
    	
    	if(this.entity instanceof ISleeping)
    	{
    		if(((ISleeping) entity).getSleeping())
    		{
    			isSleeping = true;
    		}
    		
    		else if(entity instanceof EntityAnimaniaPig)
    		{
    			if(entity.world.getBlockState(entity.getPosition().down()).getBlock() == BlockHandler.blockMud)
    				isSleeping = true;
    		}
    	}

		if (isSleeping) {
			return false;
		}
    	
    	return super.shouldExecute();
    }
}