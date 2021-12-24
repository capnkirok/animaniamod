package com.animania.common.entities.generic;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAgeable;
import com.animania.api.interfaces.IAnimaniaAnimal;
import com.animania.api.interfaces.IBlinking;
import com.animania.api.interfaces.IChild;
import com.animania.api.interfaces.IFoodEating;
import com.animania.api.interfaces.IImpregnable;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISleeping;
import com.animania.api.interfaces.ISterilizable;
import com.animania.api.interfaces.IVariant;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;

public class GenericBehavior
{

	private static Random rand = Animania.RANDOM;

	public static <T extends AnimalEntity & IFoodEating & ISleeping & IAgeable & IBlinking> void livingUpdateCommon(T entity)
	{
		if (entity.level.isRemote)
			entity.setEatTimer(Math.max(0, entity.getEatTimer() - 1));

		if (entity.getLeashed() && entity.getSleeping())
			entity.setSleeping(false);

		if (entity.getLeashed() && !entity.getInteracted())
			entity.setInteracted(true);

		if (entity.isBeingRidden() && entity.getSleeping())
			entity.setSleeping(false);

		if (entity.getAge() == 0)
		{
			entity.setAge(1);
		}

		int fedTimer = entity.getFedTimer();

		if (AnimaniaConfig.gameRules.ambianceMode)
		{
			entity.setFed(true);
			entity.setWatered(true);
		}

		if (fedTimer > -1 && (AnimaniaConfig.gameRules.requireAnimalInteractionForAI ? entity.getInteracted() : true) && !AnimaniaConfig.gameRules.ambianceMode)
		{
			fedTimer--;
			entity.setFedTimer(fedTimer);

			if (fedTimer == 0)
				entity.setFed(false);
		}

		int wateredTimer = entity.getWaterTimer();

		if (wateredTimer > -1)
		{
			wateredTimer--;
			entity.setWaterTimer(wateredTimer);

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
				if (!entity.getSleeping())
				{
					damageTimer++;
					entity.setDamageTimer(damageTimer);
				}
			}

		} else if (!fed || !watered)
			entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));

		int happyTimer = entity.getHappyTimer();

		if (happyTimer > -1)
		{
			happyTimer--;
			entity.setHappyTimer(happyTimer);
			if (happyTimer == 0)
			{
				entity.setHappyTimer(60);

				if (!entity.getFed() && !entity.getWatered() && !entity.getSleeping() && AnimaniaConfig.gameRules.showUnhappyParticles && (AnimaniaConfig.gameRules.requireAnimalInteractionForAI ? entity.getInteracted() : true))
				{
					double d = rand.nextGaussian() * 0.001D;
					double d1 = rand.nextGaussian() * 0.001D;
					double d2 = rand.nextGaussian() * 0.001D;
					entity.level.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entity.getX() + rand.nextFloat() * entity.width - entity.width, entity.getY() + 1.5D + rand.nextFloat() * entity.height, entity.getZ() + rand.nextFloat() * entity.width - entity.width, d, d1, d2);
				}
			}
		}

		int blinkTimer = entity.getBlinkTimer();

		if (blinkTimer > -1)
		{
			blinkTimer--;
			entity.setBlinkTimer(blinkTimer);
			if (blinkTimer == 0)
			{
				entity.setBlinkTimer(100 + rand.nextInt(100));
			}
		}

	}

	public static <T extends AgeableEntity & IFoodEating & ISleeping & IChild> void livingUpdateChild(T entity, Class<? extends LivingEntity> motherClass)
	{
		World world = entity.level;

		entity.setGrowingAge((int) -((0.85 - entity.getEntityAge()) * 100 * AnimaniaConfig.careAndFeeding.childGrowthTick));

		boolean fed = entity.getFed();
		boolean watered = entity.getWatered();

		int ageTimer = entity.getAgeTimer();

		ageTimer++;
		entity.setAgeTimer(ageTimer);

		if (ageTimer >= AnimaniaConfig.careAndFeeding.childGrowthTick)
			if (fed && watered && !entity.getSleeping())
			{
				entity.setAgeTimer(0);
				float age = entity.getEntityAge();
				age = age + .01F;
				entity.setEntityAge(age);

				if (age >= 0.85 && !entity.level.isRemote)
				{
					entity.setDead();

					LivingEntity grownUp = null;

					if (rand.nextInt(2) == 0)
					{
						grownUp = (LivingEntity) entity.getAnimalType().getFemale(world);
					} else
					{
						grownUp = (LivingEntity) entity.getAnimalType().getMale(world);
					}

					if (IImpregnable.class.isAssignableFrom(motherClass))
					{
						List<LivingEntity> entities = AnimaniaHelper.getEntitiesInRange(motherClass, 15, world, entity);
						for (LivingEntity potentialMother : entities)
						{
							if (potentialMother.getUUID().equals(entity.getParentUniqueId()))
							{
								((IImpregnable) potentialMother).setHasKids(false);
								break;
							}
						}
					}

					if (grownUp != null)
					{
						if (entity instanceof IVariant)
						{
							((IVariant) grownUp).setVariant(((IVariant) entity).getVariant());
						}

						grownUp.setPosition(entity.getX(), entity.getY() + .5, entity.getZ());
						String name = entity.getCustomNameTag();
						if (name != "")
							grownUp.setCustomNameTag(name);

						if (grownUp instanceof IAgeable)
							((IAgeable) grownUp).setAge(1);

						if (grownUp instanceof IFoodEating)
							((IFoodEating) grownUp).setInteracted(entity.getInteracted());

						AnimaniaHelper.spawnEntity(world, grownUp);
						grownUp.playLivingSound();
					}

				}
			}
	}

	public static <T extends AnimalEntity & IFoodEating & ISleeping & IMateable> void livingUpdateMateable(T entity, Class<? extends LivingEntity> partnerClass)
	{

		World world = entity.level;

		if (rand.nextInt(200) == 0)
		{
			// Mate resetter
			if (entity.getMateUniqueId() != null)
			{
				UUID mateUUID = entity.getMateUniqueId();
				boolean mateReset = true;

				List<LivingEntity> entities = AnimaniaHelper.getEntitiesInRange(partnerClass, 30, world, entity);
				for (int k = 0; k <= entities.size() - 1; k++)
				{
					Entity mate = entities.get(k);
					if (mate != null)
					{
						UUID id = mate.getUUID();
						if (id.equals(entity.getMateUniqueId()) && !mate.isDead)
						{
							mateReset = false;
							break;
						}
					}
				}

				if (mateReset)
					entity.setMateUniqueId(null);
			}
		}
	}

	public static <T extends AnimalEntity & IFoodEating & ISleeping & IMateable & IImpregnable> void livingUpdateFemale(T entity, Class<? extends LivingEntity> partnerClass)
	{
		livingUpdateMateable(entity, partnerClass);

		World world = entity.level;

		int dryTimer = entity.getDryTimer();

		if (!entity.getFertile() && dryTimer > -1)
		{
			dryTimer--;
			entity.setDryTimer(dryTimer);
		} else
		{
			entity.setFertile(true);
			entity.setDryTimer(AnimaniaConfig.careAndFeeding.gestationTimer / 9 + rand.nextInt(50));
		}

		int gestationTimer = entity.getGestation();
		if (gestationTimer > -1 && entity.getPregnant())
		{
			gestationTimer--;
			entity.setGestation(gestationTimer);

			if (gestationTimer < 200 && entity.getSleeping())
			{
				entity.setSleeping(false);
			}

			if (gestationTimer == 0)
			{
				UUID MateID = entity.getMateUniqueId();
				List<LivingEntity> entities = AnimaniaHelper.getEntitiesInRange(partnerClass, 30, world, entity);
				LivingEntity male = null;
				for (LivingEntity potentialMate : entities)
				{
					if (potentialMate != null && potentialMate.getUUID().equals(MateID))
					{
						male = (LivingEntity) potentialMate;
						break;
					}

				}

				if (!entity.getFed() && !entity.getWatered())
				{
					if (rand.nextDouble() <= AnimaniaConfig.careAndFeeding.animalLossChance)
					{
						entity.setPregnant(false);
						entity.setFertile(false);
						entity.setHasKids(false);
						return;
					}
				}

				double birthChance = 1;
				while (rand.nextDouble() <= birthChance)
				{
					birthChance *= AnimaniaConfig.careAndFeeding.birthMultipleChance;

					entity.setInLove(null);
					AnimaniaType maleType = male == null ? entity.getAnimalType() : ((IAnimaniaAnimal) male).getAnimalType();
					AnimaniaType babyType = maleType.breed(entity.getAnimalType());

					AgeableEntity entityKid = (AgeableEntity) babyType.getChild(entity.level);
					entityKid.setPosition(entity.getX(), entity.getY() + .2, entity.getZ());

					((IChild) entityKid).setParentUniqueId(entity.getUUID());
					entityKid.playLivingSound();

					if (entityKid instanceof IFoodEating)
						((IFoodEating) entityKid).setInteracted(entity.getInteracted());

					if (!world.isRemote)
					{
						AnimaniaHelper.spawnEntity(world, entityKid);
					}

					entity.setPregnant(false);
					entity.setFertile(false);
					entity.setHasKids(true);

					BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(entity, (male == null ? entity : male), entityKid);
					MinecraftForge.EVENT_BUS.post(event);
				}

			}
		} else if (gestationTimer < 0)
		{
			entity.setGestation(1);
		}
	}

	public static <T extends AnimalEntity & IFoodEating & ISleeping> boolean interactCommon(T entity, PlayerEntity player, EnumHand hand, @Nullable GenericAIEatGrass<T> eatAI)
	{
		if (!entity.getInteracted())
			entity.setInteracted(true);

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
		} else if (entity.isBreedingItem(stack))
		{
			if (entity.getSleeping())
				return true;

			if (!player.isCreative())
				stack.shrink(1);

			entity.setFed(true);
			entity.setHandFed(true);
			if (eatAI != null)
				eatAI.startExecuting();
			entity.setEatTimer(80);

			if (entity instanceof TameableEntity)
			{
				TameableEntity tame = (TameableEntity) entity;
				if (!tame.isTamed())
				{
					tame.setOwnerId(player.getUUID());
					tame.setTamed(true);
				}
			}

			entity.setInLove(player);
			return true;
		} else if (entity instanceof TameableEntity)
		{
			TameableEntity tame = (TameableEntity) entity;

			if (stack.isEmpty() && tame.isTamed() && !tame.isSitting() && !player.isSneaking() && !entity.getSleeping())
			{
				tame.setSitting(true);
				tame.setJumping(false);
				tame.getNavigation().stop();
				return true;
			} else if (stack.isEmpty() && tame.isTamed() && tame.isSitting() && !player.isSneaking() && !entity.getSleeping())
			{

				tame.setSitting(false);
				tame.setJumping(false);
				tame.getNavigation().stop();
				return true;
			}
		}

		return false;
	}

	public static <T extends AnimalEntity & IFoodEating & ISleeping & IImpregnable & IAnimaniaAnimal> void initialSpawnFemale(T entity, Class<? extends LivingEntity> baseClass)
	{
		if (entity.level.isRemote)
			return;

		int chooser = rand.nextInt(3);

		List<LivingEntity> others = AnimaniaHelper.getEntitiesInRange(baseClass, 64, entity.level, entity.getPosition());

		if ((others.size() <= 8))
		{
			LivingEntity animal = null;

			if (chooser == 0)
			{
				animal = entity.getAnimalType().getMale(entity.level);
				IMateable mateable = (IMateable) animal;
				mateable.setMateUniqueId(entity.getUniqueID());
			} else if (chooser == 1)
			{
				animal = entity.getAnimalType().getChild(entity.level);
				((IChild) animal).setParentUniqueId(entity.getUniqueID());
				entity.setHasKids(true);
			}

			if (animal != null)
			{
				animal.setPosition(entity.getX(), entity.getY(), entity.getZ());
				AnimaniaHelper.spawnEntity(entity.level, animal);
			}
		}
	}

	public static <T extends AnimalEntity & IFoodEating & ISleeping & IAgeable> void writeCommonNBT(CompoundNBT tag, T entity)
	{
		tag.putBoolean("Fed", entity.getFed());
		tag.putBoolean("Interacted", entity.getInteracted());
		tag.putBoolean("Handfed", entity.getHandFed());
		tag.putBoolean("Watered", entity.getWatered());
		tag.putInteger("Age", entity.getAge());
		tag.putBoolean("Sleep", entity.getSleeping());
		tag.setFloat("SleepTimer", entity.getSleepTimer());

		if (entity instanceof IImpregnable)
		{
			IImpregnable preg = (IImpregnable) entity;
			tag.putBoolean("Pregnant", preg.getPregnant());
			tag.putBoolean("HasKids", preg.getHasKids());
			tag.putBoolean("Fertile", preg.getFertile());
			tag.putInteger("Gestation", preg.getGestation());
		}

		if (entity instanceof IMateable)
		{
			IMateable mateable = (IMateable) entity;
			UUID mate = mateable.getMateUniqueId();
			if (mate != null)
				tag.setString("MateUUID", mate.toString());
		}

		if (entity instanceof ISterilizable)
		{
			tag.putBoolean("Sterilized", ((ISterilizable) entity).getSterilized());
		}

		if (entity instanceof IChild)
		{
			IChild child = (IChild) entity;

			tag.setFloat("Age", child.getEntityAge());
			if (child.getParentUniqueId() != null)
				tag.setString("ParentUUID", child.getParentUniqueId().toString());
		}
	}

	public static <T extends AnimalEntity & IFoodEating & ISleeping & IAgeable> void readCommonNBT(CompoundNBT tag, T entity)
	{
		entity.setFed(tag.getBoolean("Fed"));
		entity.setInteracted(tag.getBoolean("Interacted"));
		entity.setHandFed(tag.getBoolean("Handfed"));
		entity.setWatered(tag.getBoolean("Watered"));
		entity.setAge(tag.getInteger("Age"));
		entity.setSleeping(tag.getBoolean("Sleep"));
		entity.setSleepTimer(tag.getFloat("SleepTimer"));

		if (entity instanceof IImpregnable)
		{
			IImpregnable preg = (IImpregnable) entity;
			preg.setPregnant(tag.getBoolean("Pregnant"));
			preg.setHasKids(tag.getBoolean("HasKids"));
			preg.setFertile(tag.getBoolean("Fertile"));
			preg.setGestation(tag.getInteger("Gestation"));
		}

		if (entity instanceof IMateable)
		{
			IMateable mateable = (IMateable) entity;

			String s = "";
			if (tag.hasKey("MateUUID"))
				s = tag.getString("MateUUID");

			if (!s.isEmpty())
				mateable.setMateUniqueId(UUID.fromString(s));
		}

		if (entity instanceof ISterilizable)
		{
			((ISterilizable) entity).setSterilized(tag.getBoolean("Sterilized"));
		}

		if (entity instanceof IChild)
		{
			IChild child = (IChild) entity;
			String s = "";
			if (tag.hasKey("ParentUUID"))
				s = tag.getString("ParentUUID");

			if (!s.isEmpty())
				child.setParentUniqueId(UUID.fromString(s));

			child.setEntityAge(tag.getFloat("Age"));
		}

	}

	public static SoundEvent getRandomSound(SoundEvent... sounds)
	{
		if (sounds == null || sounds.length == 0)
			return null;

		return sounds[rand.nextInt(sounds.length)];
	}

	public static <T extends AnimalEntity & IFoodEating & ISleeping> SoundEvent getAmbientSound(T entity, SoundEvent... sounds)
	{
		if (sounds == null || sounds.length == 0)
			return null;

		int len = sounds.length * 8;
		int num = len;

		if (entity.getWatered())
			num -= len / 3;
		if (entity.getFed())
			num -= len / 3;

		if (entity.getSleeping())
			return null;

		int index = rand.nextInt(num);
		if (index > sounds.length - 1)
			return null;

		return sounds[index];
	}

}
