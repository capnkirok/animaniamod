package com.animania.api.interfaces;

import java.util.Set;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.IPlantable;

public interface IFoodEating extends IAnimaniaAnimal
{
	EntityDataAccessor<Boolean> getHandFedParam();

	EntityDataAccessor<Boolean> getFedParam();

	EntityDataAccessor<Boolean> getWateredParam();

	EntityDataAccessor<Boolean> getInteractedParam();

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
		if (((Entity) this).isVehicle())
		{
			return 0;
		}

		int eatTimer = this.getEatTimer();
		return eatTimer <= 0 ? 0.0F : eatTimer >= 4 && eatTimer <= 76 ? 1.0F : eatTimer < 4 ? (eatTimer - partialTicks) / 4.0F : -(eatTimer - 80 - partialTicks) / 4.0F;
	}

	default float getHeadAngleX(float partialTicks)
	{
		if (((Entity) this).isVehicle())
		{
			return 0;
		}

		int eatTimer = this.getEatTimer();
		if (eatTimer > 4 && eatTimer <= 76)
		{
			float f = (eatTimer - 4 - partialTicks) / 24.0F;
			return (float) Math.PI / 5F + (float) Math.PI * 7F / 150F * Mth.sin(f * 28.7F);
		}
		else
			return eatTimer > 0 ? (float) Math.PI / 5F : ((Entity) this).getXRot() * 0.017453292F;
	}

	Set<ItemStack> getFoodItems();

	default Fluid[] getFoodFluids()
	{
		return null;
	}

	default void setLiquidFed(boolean liquidFed)
	{

	}

	default Class[] getFoodBlocks()
	{
		return new Class[] { BlockCrops.class, IPlantable.class, BushBlock.class };
	}

	int getEatTimer();

	void setEatTimer(int i);

	int getFedTimer();

	void setFedTimer(int i);

	int getWaterTimer();

	void setWaterTimer(int i);

	int getDamageTimer();

	void setDamageTimer(int i);

	int getHappyTimer();

	void setHappyTimer(int i);
}
