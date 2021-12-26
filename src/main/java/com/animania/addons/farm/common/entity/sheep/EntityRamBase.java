package com.animania.addons.farm.common.entity.sheep;

import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.addons.farm.common.entity.sheep.ai.ButtHeadsSheepGoal;
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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityEntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EntityRamBase extends EntityAnimaniaSheep implements TOPInfoProviderMateable, IMateable, ISterilizable
{
	protected static final EntityDataAccessor<Boolean> FIGHTING = SynchedEntityData.<Boolean> defineId(EntityRamBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> STERILIZED = SynchedEntityData.<Boolean> defineId(EntityRamBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.<Optional<UUID>> defineId(EntityRamBase.class, EntityEntityDataSerializers.OPTIONAL_UUID);

	public EntityRamBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(1.2F, 1.0F);
		this.width = 1.2F;
		this.height = 1.0F;
		this.stepHeight = 1.1F;
		this.gender = EntityGender.MALE;
		this.headbutting = true;
		this.mateable = true;
		// this.goalSelector.addGoal(3, new FollowMateSheepGoal(this, 1.1D));
		if (!this.getSterilized())
			this.goalSelector.addGoal(5, new GenericAIMate<EntityRamBase, EntityEweBase>(this, 1.0D, EntityEweBase.class, EntityLambBase.class, EntityAnimaniaSheep.class));

		if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.getSterilized())
		{
			this.goalSelector.addGoal(3, new ButtHeadsSheepGoal(this, 1.3D));
		}
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(EntityRamBase.FIGHTING, false);
		this.entityData.register(STERILIZED, false);
		this.entityData.register(MATE_UNIQUE_ID, Optional.<UUID> absent());

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
			this.entityData.set(EntityRamBase.FIGHTING, true);
		else
			this.entityData.set(EntityRamBase.FIGHTING, false);
	}

	@Nullable
	public UUID getRivalUniqueId()
	{
		return (UUID) ((Optional) this.entityData.get(EntityAnimaniaSheep.RIVAL_UNIQUE_ID)).orNull();
	}

	public void setRivalUniqueId(@Nullable UUID uniqueId)
	{
		this.entityData.set(EntityAnimaniaSheep.RIVAL_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, FarmAddonSoundHandler.sheepLiving1, FarmAddonSoundHandler.sheepLiving2, FarmAddonSoundHandler.sheepLiving3, FarmAddonSoundHandler.sheepLiving4, FarmAddonSoundHandler.sheepLiving5, FarmAddonSoundHandler.sheepLiving6, FarmAddonSoundHandler.sheepLiving7);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.sheepHurt1, FarmAddonSoundHandler.sheepLiving7);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.sheepHurt1, FarmAddonSoundHandler.sheepLiving7);
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .21F);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.10F, 0.8F);
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateMateable(this, EntityEweBase.class);

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
			if (this.getSheared())
			{
				if (this.getWoolRegrowthTimer() > 0)
				{
					int bob = this.getWoolRegrowthTimer();
					probeInfo.text(I18n.translateToLocal("text.waila.wool1") + " (" + bob + " " + I18n.translateToLocal("text.waila.wool2") + ")");
				}
			}
			else if (!this.getSheared())
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
			if (ai instanceof GenericAIMate || ai instanceof ButtHeadsSheepGoal)
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
