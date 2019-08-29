package com.animania.common.entities.generic;

import java.util.Random;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import com.animania.api.interfaces.IAgeable;
import com.animania.api.interfaces.IAnimaniaAnimal;
import com.animania.api.interfaces.IBlinking;
import com.animania.api.interfaces.IChild;
import com.animania.api.interfaces.IFoodEating;
import com.animania.api.interfaces.ISleeping;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;

public class GenericBehavior
{

	private static Random rand = new Random();

	public static <T extends EntityAnimal & IFoodEating & ISleeping & IAgeable & IBlinking> void livingUpdateCommon(T entity)
	{
		if (entity.world.isRemote && (AnimaniaConfig.gameRules.requireAnimalInteractionForAI ? entity.getInteracted() : true))
			entity.setEatTimer(Math.max(0, entity.getEatTimer() - 1));

		if (entity.getLeashed() && entity.getSleeping())
			entity.setSleeping(false);

		if (entity.getLeashed() && !entity.getInteracted())
		{
			entity.setInteracted(true);
			;
		}

		if (entity.getAge() == 0)
		{
			entity.setAge(1);
		}

		int fedTimer = entity.getFedTimer();

		if (fedTimer > -1 && (AnimaniaConfig.gameRules.requireAnimalInteractionForAI ? entity.getInteracted() : true) && !AnimaniaConfig.gameRules.ambianceMode)
		{
			entity.setFedTimer(fedTimer--);

			if (fedTimer == 0)
				entity.setFed(false);
		}

		int wateredTimer = entity.getWaterTimer();

		if (wateredTimer > -1)
		{
			entity.setWaterTimer(wateredTimer--);

			if (wateredTimer == 0 && (AnimaniaConfig.gameRules.requireAnimalInteractionForAI ? entity.getInteracted() : true) && !AnimaniaConfig.gameRules.ambianceMode)
				entity.setWatered(false);
		}

		boolean fed = entity.getFed();
		boolean watered = entity.getWatered();

		if (!fed && !watered)
		{
			entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
			if (AnimaniaConfig.gameRules.animalsStarve)
			{
				int damageTimer = entity.getDamageTimer();

				if (damageTimer >= AnimaniaConfig.careAndFeeding.starvationTimer)
				{
					entity.attackEntityFrom(DamageSource.STARVE, 4f);
					entity.setDamageTimer(0);
				}
				entity.setDamageTimer(damageTimer++);
			}

		}
		else if (!fed || !watered)
			entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));

		int happyTimer = entity.getHappyTimer();

		if (happyTimer > -1)
		{
			entity.setHappyTimer(happyTimer--);
			if (happyTimer == 0)
			{
				entity.setHappyTimer(60);

				if (!entity.getFed() && !entity.getWatered() && !entity.getSleeping() && AnimaniaConfig.gameRules.showUnhappyParticles && (AnimaniaConfig.gameRules.requireAnimalInteractionForAI ? entity.getInteracted() : true))
				{
					double d = rand.nextGaussian() * 0.001D;
					double d1 = rand.nextGaussian() * 0.001D;
					double d2 = rand.nextGaussian() * 0.001D;
					entity.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entity.posX + rand.nextFloat() * entity.width - entity.width, entity.posY + 1.5D + rand.nextFloat() * entity.height, entity.posZ + rand.nextFloat() * entity.width - entity.width, d, d1, d2);
				}
			}
		}

		int blinkTimer = entity.getBlinkTimer();

		if (blinkTimer > -1)
		{
			entity.setBlinkTimer(blinkTimer--);
			if (blinkTimer == 0)
			{
				entity.setBlinkTimer(100 + rand.nextInt(100));
			}
		}

	}

	public static <T extends EntityAgeable & IFoodEating & ISleeping & IChild & IAnimaniaAnimal> void livingUpdateChild(T entity, Consumer<T> sizeFunction)
	{
		entity.setGrowingAge(-24000);

		boolean fed = entity.getFed();
		boolean watered = entity.getWatered();

		int ageTimer = entity.getAgeTimer();

		entity.setAgeTimer(ageTimer++);

		if (ageTimer >= AnimaniaConfig.careAndFeeding.childGrowthTick)
			if (fed && watered)
			{
				entity.setAgeTimer(0);
				float age = entity.getEntityAge();
				age = age + .01F;
				entity.setEntityAge(age);

				if (sizeFunction != null)
					sizeFunction.accept(entity);

				if (age >= 0.85 && !entity.world.isRemote)
				{
					entity.setDead();

					EntityLiving grownUp = null;

					if (rand.nextInt(2) == 0)
					{
						grownUp = (EntityLiving) entity.getAnimalType().getFemale(entity.world);
					}
					else
					{
						grownUp = (EntityLiving) entity.getAnimalType().getMale(entity.world);
					}

					if (grownUp != null)
					{
						grownUp.setPosition(entity.posX, entity.posY + .5, entity.posZ);
						String name = entity.getCustomNameTag();
						if (name != "")
							grownUp.setCustomNameTag(name);

						if (grownUp instanceof IAgeable)
							((IAgeable) grownUp).setAge(1);

						entity.world.spawnEntity(grownUp);
						grownUp.playLivingSound();
					}

				}
			}
	}

	public static <T extends EntityAnimal & IFoodEating & ISleeping> boolean interactCommon(T entity, EntityPlayer player, EnumHand hand, @Nullable GenericAIEatGrass<T> eatAI)
	{
		if (!entity.getInteracted())
			entity.setInteracted(true);
		
		System.out.println(entity.getClass());

		ItemStack stack = player.getHeldItem(hand);

		if (stack != ItemStack.EMPTY && AnimaniaHelper.isWaterContainer(stack) && !entity.getSleeping())
		{
			if (!player.isCreative())
			{
				ItemStack emptied = AnimaniaHelper.emptyContainer(stack);
				stack.shrink(1);
				AnimaniaHelper.addItem(player, emptied);
			}

			entity.setEatTimer(40);
			if (eatAI != null)
				eatAI.startExecuting();
			entity.setWatered(true);
			entity.setInLove(player);
			return true;
		}
		else if (entity.isBreedingItem(stack) && !entity.getSleeping())
		{
			if (!player.isCreative())
				stack.shrink(1);

			entity.setFed(true);
			entity.setHandFed(true);
			if (eatAI != null)
				eatAI.startExecuting();
			entity.setEatTimer(80);

			entity.heal(1);

			if (entity instanceof EntityTameable)
			{
				EntityTameable tame = (EntityTameable) entity;
				if (!tame.isTamed())
				{
					tame.setOwnerId(player.getPersistentID());
					tame.setTamed(true);
				}
			}

			entity.setInLove(player);
			return true;
		}
		else if (entity instanceof EntityTameable)
		{
			EntityTameable tame = (EntityTameable) entity;

			if (stack.isEmpty() && tame.isTamed() && !tame.isSitting() && !player.isSneaking() && !entity.getSleeping())
			{
				tame.setSitting(true);
				tame.setJumping(false);
				tame.getNavigator().clearPath();
				return true;
			}
			else if (stack.isEmpty() && tame.isTamed() && tame.isSitting() && !player.isSneaking() && !entity.getSleeping())
			{

				tame.setSitting(false);
				tame.setJumping(false);
				tame.getNavigator().clearPath();
				return true;
			}
		}

		return false;
	}

	public static <T extends EntityAnimal & IFoodEating & ISleeping & IAgeable> void writeCommonNBT(NBTTagCompound tag, T entity)
	{
		tag.setBoolean("Fed", entity.getFed());
		tag.setBoolean("Interacted", entity.getInteracted());
		tag.setBoolean("Handfed", entity.getHandFed());
		tag.setBoolean("Watered", entity.getWatered());
		tag.setInteger("Age", entity.getAge());
		tag.setBoolean("Sleep", entity.getSleeping());
		tag.setFloat("SleepTimer", entity.getSleepTimer());
	}

	public static <T extends EntityAnimal & IFoodEating & ISleeping & IAgeable> void readCommonNBT(NBTTagCompound tag, T entity)
	{
		entity.setFed(tag.getBoolean("Fed"));
		entity.setInteracted(tag.getBoolean("Interacted"));
		entity.setHandFed(tag.getBoolean("Handfed"));
		entity.setWatered(tag.getBoolean("Watered"));
		entity.setAge(tag.getInteger("Age"));
		entity.setSleeping(tag.getBoolean("Sleep"));
		entity.setSleepTimer(tag.getFloat("SleepTimer"));
	}
	
	public static SoundEvent getRandomSound(SoundEvent... sounds)
	{
		if(sounds == null || sounds.length == 0)
			return null;
		
		return sounds[rand.nextInt(sounds.length)];
	}
	
	public static <T extends EntityAnimal & IFoodEating & ISleeping> SoundEvent getAmbientSound(T entity, SoundEvent... sounds)
	{
		if(sounds == null || sounds.length == 0)
			return null;
		
		int num = 24;

		if (entity.getWatered())
			num -= 8;
		if (entity.getFed())
			num -= 8;

		if(entity.getSleeping())
			return null;
		
		int index = rand.nextInt(num);
		if(index > sounds.length-1)
			return null;
		
		return sounds[index];
	}

}
