package com.animania.addons.farm.common.entity.goats;

import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.addons.farm.common.entity.cows.ai.AttackMeleeBullsGoal;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityBuckAngora;
import com.animania.addons.farm.common.entity.goats.ai.ButtHeadsGoatsGoal;
import com.animania.addons.farm.common.entity.goats.ai.GoatsLeapAtTargetGoal;
import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.Attributes;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EntityBuckBase extends EntityAnimaniaGoat implements TOPInfoProviderMateable, IMateable, ISterilizable
{

	protected static final EntityDataAccessor<Boolean> FIGHTING = SynchedEntityData.<Boolean> defineId(EntityBuckBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> STERILIZED = SynchedEntityData.<Boolean> defineId(EntityBuckBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.<Optional<UUID>> defineId(EntityBuckBase.class, EntityDataSerializers.OPTIONAL_UUID);

	public EntityBuckBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(1.0F, 1.0F);
		this.width = 1.0F;
		this.height = 1.0F;
		this.stepHeight = 1.1F;
		this.mateable = true;
		this.headbutting = true;
		this.gender = EntityGender.MALE;
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.getSterilized())
		{
			this.goalSelector.addGoal(3, new ButtHeadsGoatsGoal(this, 1.3D));
			this.goalSelector.addGoal(3, new GoatsLeapAtTargetGoal(this, 0.25F));
		}

		if (!this.getSterilized())
			this.goalSelector.addGoal(5, new GenericAIMate<EntityBuckBase, EntityDoeBase>(this, 1.0D, EntityDoeBase.class, EntityKidBase.class, EntityAnimaniaGoat.class));
		// this.goalSelector.addGoal(5, new FollowMateGoatsGoal(this, 1.0D));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.265D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(EntityBuckBase.FIGHTING, false);
		this.entityData.register(EntityBuckBase.STERILIZED, false);
		this.entityData.register(EntityBuckBase.MATE_UNIQUE_ID, Optional.<UUID> absent());
	}

	public boolean getFighting()
	{
		try
		{
			return this.getBoolFromDataManager(FIGHTING);
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public void setFighting(boolean fighting)
	{
		if (fighting)
			this.entityData.set(EntityBuckBase.FIGHTING, true);
		else
			this.entityData.set(EntityBuckBase.FIGHTING, false);
	}

	@Override
	@Nullable
	public UUID getMateUniqueId()
	{
		return (UUID) ((Optional) this.entityData.get(EntityBuckBase.MATE_UNIQUE_ID)).orNull();
	}

	@Override
	public void setMateUniqueId(@Nullable UUID uniqueId)
	{
		this.entityData.set(EntityBuckBase.MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	@Nullable
	public UUID getRivalUniqueId()
	{
		return (UUID) ((Optional) this.entityData.get(EntityAnimaniaGoat.RIVAL_UNIQUE_ID)).orNull();
	}

	public void setRivalUniqueId(@Nullable UUID uniqueId)
	{
		this.entityData.set(EntityAnimaniaGoat.RIVAL_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, FarmAddonSoundHandler.goatLiving1, FarmAddonSoundHandler.goatLiving2, FarmAddonSoundHandler.goatLiving2, FarmAddonSoundHandler.goatLiving3, FarmAddonSoundHandler.goatLiving4, FarmAddonSoundHandler.goatLiving5);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.goatHurt1, FarmAddonSoundHandler.goatHurt2, FarmAddonSoundHandler.goatLiving3);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.goatHurt1, FarmAddonSoundHandler.goatHurt2, FarmAddonSoundHandler.goatLiving3);
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.10F, 0.8F);
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateMateable(this, EntityDoeBase.class);

		super.onLivingUpdate();
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, stack);
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking())
		{

			if (this.getSheared() && this instanceof EntityBuckAngora)
			{
				if (this.getWoolRegrowthTimer() > 0)
				{
					int bob = this.getWoolRegrowthTimer();
					probeInfo.text(I18n.translateToLocal("text.waila.wool1") + " (" + bob + " " + I18n.translateToLocal("text.waila.wool2") + ")");
				}
			}
			else if (!this.getSheared() && this instanceof EntityBuckAngora)
			{
				probeInfo.text(I18n.translateToLocal("text.waila.wool3"));
			}

		}
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, level, entity, data);
	}

	@Override
	public EntityDataAccessor<Boolean> getSterilizedParam()
	{
		return STERILIZED;
	}

	@Override
	public void sterilize()
	{
		Iterator<EntityAITaskEntry> it = this.tasks.taskEntries.iterator();
		while (it.hasNext())
		{
			EntityAITaskEntry entry = it.next();
			Goal ai = entry.action;
			if (ai instanceof GenericAIMate || ai instanceof AttackMeleeBullsGoal || ai instanceof GoatsLeapAtTargetGoal)
			{
				entry.using = false;
				ai.resetTask();
				it.remove();
			}
		}
		this.setSterilized(true);
	}

	@Override
	public EntityDataAccessor<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}

}
