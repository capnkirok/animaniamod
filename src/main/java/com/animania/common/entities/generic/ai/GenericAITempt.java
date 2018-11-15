package com.animania.common.entities.generic.ai;

import java.util.Set;

import com.animania.common.entities.interfaces.ISleeping;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.item.Item;

public class GenericAITempt<T extends EntityCreature & ISleeping> extends EntityAITempt
{
    /** The entity using this AI that is tempted by the player. */
    private final T temptedEntity;

    public GenericAITempt(T temptedEntityIn, double speedIn, Item temptItemIn, boolean scaredByPlayerMovementIn)
    {
        super(temptedEntityIn, speedIn, temptItemIn, scaredByPlayerMovementIn);
        this.temptedEntity = temptedEntityIn;
    }

    public GenericAITempt(T temptedEntityIn, double speedIn, boolean scaredByPlayerMovementIn, Set<Item> temptItemIn)
    {
        super(temptedEntityIn, speedIn, scaredByPlayerMovementIn, temptItemIn);
        this.temptedEntity = temptedEntityIn;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	if(temptedEntity.getSleeping())
    		return false;
    	
    	return super.shouldExecute();
    }
}