package com.animania.addons.extra.common.entity.rodents.rabbits;

import java.util.Iterator;
import java.util.UUID;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RabbitEntityBuckBase extends EntityAnimaniaRabbit implements TOPInfoProviderMateable, IMateable, ISterilizable
{
	protected static final DataParameter<Boolean> STERILIZED = EntityDataManager.<Boolean> defineId(RabbitEntityBuckBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>> defineId(RabbitEntityBuckBase.class, DataSerializers.OPTIONAL_UUID);

	public RabbitEntityBuckBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.7F, 0.6F);
		this.width = 0.7F;
		this.height = 0.6F;
		this.stepHeight = 1.1F;
		this.mateable = true;
		this.gender = EntityGender.MALE;

		if (!getSterilized())
			this.tasks.addTask(6, new GenericAIMate<RabbitEntityBuckBase, RabbitEntityDoeBase>(this, 1.0D, RabbitEntityDoeBase.class, RabbitEntityKitBase.class, EntityAnimaniaRabbit.class));
		// this.tasks.addTask(3, new EntityAIFollowMateRabbits(this, 1.1D));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(STERILIZED, false);
		this.dataManager.register(MATE_UNIQUE_ID, Optional.<UUID> absent());
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.265D);
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
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.8F);
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateMateable(this, RabbitEntityDoeBase.class);

		super.onLivingUpdate();
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, Entity entity, IProbeHitEntityData data)
	{
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
			Goal ai = entry.action;
			if (ai instanceof GenericAIMate)
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
