package com.animania.addons.farm.common.entity.goats;

import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.addons.farm.common.entity.cows.ai.EntityAIAttackMeleeBulls;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityBuckAngora;
import com.animania.addons.farm.common.entity.goats.ai.EntityAIButtHeadsGoats;
import com.animania.addons.farm.common.entity.goats.ai.EntityAIGoatsLeapAtTarget;
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
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class EntityBuckBase extends EntityAnimaniaGoat implements TOPInfoProviderMateable, IMateable, ISterilizable
{

	protected static final DataParameter<Boolean> FIGHTING = EntityDataManager.<Boolean> defineId(EntityBuckBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> STERILIZED = EntityDataManager.<Boolean> defineId(EntityBuckBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>> defineId(EntityBuckBase.class, DataSerializers.OPTIONAL_UUID);

	public EntityBuckBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.0F, 1.0F);
		this.width = 1.0F;
		this.height = 1.0F;
		this.stepHeight = 1.1F;
		this.mateable = true;
		this.headbutting = true;
		this.gender = EntityGender.MALE;
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !getSterilized())
		{
			this.tasks.addTask(3, new EntityAIButtHeadsGoats(this, 1.3D));
			this.tasks.addTask(3, new EntityAIGoatsLeapAtTarget(this, 0.25F));
		}

		if (!getSterilized())
			this.tasks.addTask(5, new GenericAIMate<EntityBuckBase, EntityDoeBase>(this, 1.0D, EntityDoeBase.class, EntityKidBase.class, EntityAnimaniaGoat.class));
		// this.tasks.addTask(5, new EntityAIFollowMateGoats(this, 1.0D));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.265D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityBuckBase.FIGHTING, false);
		this.dataManager.register(EntityBuckBase.STERILIZED, false);
		this.dataManager.register(EntityBuckBase.MATE_UNIQUE_ID, Optional.<UUID> absent());
	}

	public boolean getFighting()
	{
		try
		{
			return (this.getBoolFromDataManager(FIGHTING));
		} catch (Exception e)
		{
			return false;
		}
	}

	public void setFighting(boolean fighting)
	{
		if (fighting)
			this.dataManager.set(EntityBuckBase.FIGHTING, true);
		else
			this.dataManager.set(EntityBuckBase.FIGHTING, false);
	}

	@Override
	@Nullable
	public UUID getMateUniqueId()
	{
		return (UUID) ((Optional) this.dataManager.get(EntityBuckBase.MATE_UNIQUE_ID)).orNull();
	}

	@Override
	public void setMateUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(EntityBuckBase.MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	@Nullable
	public UUID getRivalUniqueId()
	{
		return (UUID) ((Optional) this.dataManager.get(EntityBuckBase.RIVAL_UNIQUE_ID)).orNull();
	}

	public void setRivalUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(EntityBuckBase.RIVAL_UNIQUE_ID, Optional.fromNullable(uniqueId));
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
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, Entity entity, IProbeHitEntityData data)
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
			} else if (!this.getSheared() && this instanceof EntityBuckAngora)
			{
				probeInfo.text(I18n.translateToLocal("text.waila.wool3"));
			}

		}
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

	@Override
	public DataParameter<Boolean> getSterilizedParam()
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
			EntityAIBase ai = entry.action;
			if (ai instanceof GenericAIMate || ai instanceof EntityAIAttackMeleeBulls || ai instanceof EntityAIGoatsLeapAtTarget)
			{
				entry.using = false;
				ai.resetTask();
				it.remove();
			}
		}
		setSterilized(true);
	}

	@Override
	public DataParameter<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}

}
