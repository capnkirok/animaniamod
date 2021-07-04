package com.animania.common.entities.generic.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Nullable;

import com.animania.api.interfaces.ISleeping;
import com.google.common.base.Function;
import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;

import LivingEntity;

public class GenericAINearestAttackableTarget<T extends LivingEntity> extends EntityAITarget
{
	protected final Class<T> targetClass;
	private final int targetChance;
	/** Instance of EntityAINearestAttackableTargetSorter. */
	protected final GenericAINearestAttackableTarget.Sorter sorter;
	protected final Predicate<? super T> targetEntitySelector;
	protected T targetEntity;

	public GenericAINearestAttackableTarget(CreatureEntity creature, Class<T> classTarget, boolean checkSight)
	{
		this(creature, classTarget, checkSight, false);
	}

	public GenericAINearestAttackableTarget(CreatureEntity creature, Class<T> classTarget, boolean checkSight, boolean onlyNearby)
	{
		this(creature, classTarget, 10, checkSight, onlyNearby, (Predicate) null);
	}

	public GenericAINearestAttackableTarget(CreatureEntity creature, Class<T> classTarget, int chance, boolean checkSight, boolean onlyNearby, @Nullable final Predicate<? super T> targetSelector)
	{
		super(creature, checkSight, onlyNearby);
		this.targetClass = classTarget;
		this.targetChance = chance;
		this.sorter = new GenericAINearestAttackableTarget.Sorter(creature);
		this.setMutexBits(1);
		this.targetEntitySelector = (@Nullable T p_apply_1_) -> {
			if (p_apply_1_ == null)
			{
				return false;
			} else if (targetSelector != null && !targetSelector.apply(p_apply_1_))
			{
				return false;
			} else
			{
				return !EntitySelectors.NOT_SPECTATING.apply(p_apply_1_) ? false : GenericAINearestAttackableTarget.this.isSuitableTarget(p_apply_1_, false);
			}
		};
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{

		if (this.taskOwner instanceof ISleeping)
			if (((ISleeping) this.taskOwner).getSleeping())
				return false;

		if (this.taskOwner instanceof TameableEntity)
			if (((TameableEntity) this.taskOwner).isSitting())
				return false;

		if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0)
		{
			return false;
		} else if (this.targetClass != EntityPlayer.class && this.targetClass != EntityPlayerMP.class)
		{
			List<T> list = this.taskOwner.world.<T> getEntitiesWithinAABB(this.targetClass, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector);

			if (list.isEmpty())
			{
				return false;
			} else
			{
				Collections.sort(list, this.sorter);
				this.targetEntity = list.get(0);
				return true;
			}
		} else
		{
			this.targetEntity = (T) this.taskOwner.world.getNearestAttackablePlayer(this.taskOwner.posX, this.taskOwner.posY + this.taskOwner.getEyeHeight(), this.taskOwner.posZ, this.getTargetDistance(), this.getTargetDistance(), new Function<EntityPlayer, Double>() {
				@Override
				@Nullable
				public Double apply(@Nullable EntityPlayer p_apply_1_)
				{
					ItemStack itemstack = p_apply_1_.getItemStackFromSlot(EntityEquipmentSlot.HEAD);

					if (itemstack.getItem() == Items.SKULL)
					{
						int i = itemstack.getItemDamage();
						boolean flag = GenericAINearestAttackableTarget.this.taskOwner instanceof EntitySkeleton && i == 0;
						boolean flag1 = GenericAINearestAttackableTarget.this.taskOwner instanceof EntityZombie && i == 2;
						boolean flag2 = GenericAINearestAttackableTarget.this.taskOwner instanceof EntityCreeper && i == 4;

						if (flag || flag1 || flag2)
						{
							return 0.5D;
						}
					}

					return 1.0D;
				}
			}, (Predicate<EntityPlayer>) this.targetEntitySelector);
			return this.targetEntity != null;
		}
	}

	protected AxisAlignedBB getTargetableArea(double targetDistance)
	{
		return this.taskOwner.getEntityBoundingBox().grow(targetDistance, 4.0D, targetDistance);
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting()
	{
		this.taskOwner.setAttackTarget(this.targetEntity);
		super.startExecuting();
	}

	public static class Sorter implements Comparator<Entity>
	{
		private final Entity entity;

		public Sorter(Entity entityIn)
		{
			this.entity = entityIn;
		}

		@Override
		public int compare(Entity p_compare_1_, Entity p_compare_2_)
		{
			double d0 = this.entity.getDistanceSq(p_compare_1_);
			double d1 = this.entity.getDistanceSq(p_compare_2_);

			if (d0 < d1)
			{
				return -1;
			} else
			{
				return d0 > d1 ? 1 : 0;
			}
		}
	}
}