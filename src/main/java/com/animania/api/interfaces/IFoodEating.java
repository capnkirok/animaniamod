package com.animania.api.interfaces;

import java.util.Set;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fluids.Fluid;

public interface IFoodEating extends IAnimaniaAnimal
{
	public DataParameter<Boolean> getHandFedParam();

	public DataParameter<Boolean> getFedParam();

	public DataParameter<Boolean> getWateredParam();

	public DataParameter<Boolean> getInteractedParam();

	default void setFed(boolean fed)
	{
		DataParameter<Boolean> param = getFedParam();
		if (param != null)
		{
			EntityLivingBase e = (EntityLivingBase) this;
			if (fed)
			{
				this.setFedTimer(AnimaniaConfig.careAndFeeding.feedTimer + Animania.RANDOM.nextInt(100));
			}
			e.getDataManager().set(param, fed);
		}
	}

	default boolean getFed()
	{
		DataParameter<Boolean> param = getFedParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	default void setWatered(boolean watered)
	{
		DataParameter<Boolean> param = getWateredParam();
		if (param != null)
		{
			EntityLivingBase e = (EntityLivingBase) this;
			if (watered)
			{
				this.setWaterTimer(AnimaniaConfig.careAndFeeding.waterTimer + Animania.RANDOM.nextInt(100));
			}
			e.getDataManager().set(param, watered);
		}
	}

	default boolean getWatered()
	{
		DataParameter<Boolean> param = getWateredParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	default void setHandFed(boolean handfed)
	{
		DataParameter<Boolean> param = getHandFedParam();
		if (param != null)
			((Entity) this).getDataManager().set(param, handfed);
	}

	default boolean getHandFed()
	{
		DataParameter<Boolean> param = getHandFedParam();
		if (param != null)
			return this.getBoolFromDataManager(param);
		return false;
	}

	default void setInteracted(boolean interacted)
	{
		DataParameter<Boolean> param = getInteractedParam();
		if (param != null)
			((Entity) this).getDataManager().set(getInteractedParam(), interacted);
	}

	default boolean getInteracted()
	{
		DataParameter<Boolean> param = getInteractedParam();
		if (param != null)
			return this.getBoolFromDataManager(getInteractedParam());
		return false;
	}

	default float getHeadAnchorPointY(float partialTicks)
	{
		if (((Entity) this).isBeingRidden())
		{
			return 0;
		}

		int eatTimer = this.getEatTimer();
		float val = eatTimer <= 0 ? 0.0F : eatTimer >= 4 && eatTimer <= 76 ? 1.0F : eatTimer < 4 ? (eatTimer - partialTicks) / 4.0F : -(eatTimer - 80 - partialTicks) / 4.0F;
		return val;
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
		} else
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
