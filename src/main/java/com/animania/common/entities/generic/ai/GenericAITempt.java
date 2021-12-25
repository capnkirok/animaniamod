package com.animania.common.entities.generic.ai;

import java.util.Collections;
import java.util.Set;

import com.animania.api.interfaces.IFoodEating;
import com.animania.api.interfaces.ISleeping;
import com.animania.common.helper.AnimaniaHelper;
import com.google.common.collect.Sets;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.item.ItemStack;

public class GenericAITempt<T extends PathfinderMob & ISleeping & IFoodEating> extends TemptGoal
{
	/** The entity using this AI that is tempted by the player. */
	private final T temptedEntity;

	private Set<ItemStack> temptSet;

	public GenericAITempt(T temptedEntityIn, double speedIn, ItemStack temptItemIn, boolean scaredByPlayerMovementIn)
	{
		super(temptedEntityIn, speedIn, Items.AIR, scaredByPlayerMovementIn);
		this.temptedEntity = temptedEntityIn;
		this.temptSet = Sets.newHashSet(temptItemIn.copy());
	}

	public GenericAITempt(T temptedEntityIn, double speedIn, boolean scaredByPlayerMovementIn, Set<ItemStack> temptItemIn)
	{
		super(temptedEntityIn, speedIn, scaredByPlayerMovementIn, Collections.EMPTY_SET);
		this.temptedEntity = temptedEntityIn;

		this.temptSet = Sets.newHashSet();
		for (ItemStack s : temptItemIn)
			this.temptSet.add(s.copy());
	}

	@Override
	protected boolean isTempting(ItemStack stack)
	{
		return AnimaniaHelper.containsItemStack(this.temptSet, stack);
	}

	/**
	 * Returns whether the Goal should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{
		if (this.temptedEntity.getSleeping() || this.temptedEntity instanceof TameableEntity && ((TameableEntity) this.temptedEntity).isSitting())
			return false;

		return super.shouldExecute();
	}

	@Override
	public void updateTask()
	{
		if (!this.temptedEntity.getInteracted())
			this.temptedEntity.setInteracted(true);

		super.updateTask();
	}
}