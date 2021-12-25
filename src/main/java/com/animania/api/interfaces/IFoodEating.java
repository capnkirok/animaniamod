package com.animania.api.interfaces;

import java.util.Set;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.IPlantable;

public interface IFoodEating extends IAnimaniaAnimal
{
	public EntityDataAccessor<Boolean> getHandFedParam();

	public EntityDataAccessor<Boolean> getFedParam();

	public EntityDataAccessor<Boolean> getWateredParam();

	public EntityDataAccessor<Boolean> getInteractedParam();

	default void setFed(boolean fed)
	{
		EntityDataAccessor<Boolean> param = this.getFedParam();
		if (param != null)
		{
			LivingEntity e = (LivingEntity) this;
			if (fed)
			{
				this.setFedTimer(AnimaniaConfig.careAndFeeding.feedTimer + Animania.RANDOM.nextInt(100));
			}
			e.getEntityData().set(param, fed);
		}
	}

	default boolean getFed()
	{
		EntityDataAccessor<Boolean> param = this.getFedParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	default void setWatered(boolean watered)
	{
		EntityDataAccessor<Boolean> param = this.getWateredParam();
		if (param != null)
		{
			LivingEntity e = (LivingEntity) this;
			if (watered)
			{
				this.setWaterTimer(AnimaniaConfig.careAndFeeding.waterTimer + Animania.RANDOM.nextInt(100));
			}
			e.getEntityData().set(param, watered);
		}
	}

	default boolean getWatered()
	{
		EntityDataAccessor<Boolean> param = this.getWateredParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	default void setHandFed(boolean handfed)
	{
		EntityDataAccessor<Boolean> param = this.getHandFedParam();
		if (param != null)
			((Entity) this).getEntityData().set(param, handfed);
	}

	default boolean getHandFed()
	{
		EntityDataAccessor<Boolean> param = this.getHandFedParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	default void setInteracted(boolean interacted)
	{
		EntityDataAccessor<Boolean> param = this.getInteractedParam();
		if (param != null)
			((Entity) this).getEntityData().set(this.getInteractedParam(), interacted);
	}

	default boolean getInteracted()
	{
		EntityDataAccessor<Boolean> param = this.getInteractedParam();
		if (param != null)
			return this.getBoolFromDataManager(this.getInteractedParam());
		return false;
	}

	default float getHeadAnchorPointY(float partialTicks)
	{
		if (((Entity) this).isBeingRidden())
		{
			return 0;
		}

		int eatTimer = this.getEatTimer();
		return eatTimer <= 0 ? 0.0F : eatTimer >= 4 && eatTimer <= 76 ? 1.0F : eatTimer < 4 ? (eatTimer - partialTicks) / 4.0F : -(eatTimer - 80 - partialTicks) / 4.0F;
	}

	default float getHeadAngleX(float partialTicks)
	{
		if (((Entity) this).isBeingRidden())
		{
			return 0;
		}

		int eatTimer = this.getEatTimer();
		if (eatTimer > 4 && eatTimer <= 76)
		{
			float f = (eatTimer - 4 - partialTicks) / 24.0F;
			return (float) Math.PI / 5F + (float) Math.PI * 7F / 150F * MathHelper.sin(f * 28.7F);
		}
		else
			return eatTimer > 0 ? (float) Math.PI / 5F : ((Entity) this).rotationPitch * 0.017453292F;
	}

	public Set<ItemStack> getFoodItems();

	default Fluid[] getFoodFluids()
	{
		return null;
	}

	default void setLiquidFed(boolean liquidFed)
	{

	}

	default Class[] getFoodBlocks()
	{
		return new Class[] { BlockCrops.class, IPlantable.class, BlockBush.class };
	}

	public int getEatTimer();

	public void setEatTimer(int i);

	public int getFedTimer();

	public void setFedTimer(int i);

	public int getWaterTimer();

	public void setWaterTimer(int i);

	public int getDamageTimer();

	public void setDamageTimer(int i);

	public int getHappyTimer();

	public void setHappyTimer(int i);
}
