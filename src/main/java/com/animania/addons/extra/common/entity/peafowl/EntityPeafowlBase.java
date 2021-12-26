package com.animania.addons.extra.common.entity.peafowl;

import com.animania.addons.extra.common.entity.peafowl.ai.FindPeacockNestGoal;
import com.animania.api.data.EntityGender;
import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.animania.config.AnimaniaConfig;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityEntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.player.Player;

public class EntityPeafowlBase extends EntityAnimaniaPeacock implements TOPInfoProviderBase
{

	private static final EntityDataAccessor<Boolean> LAID = SynchedEntityData.<Boolean> defineId(EntityPeafowlBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> LAID_TIMER = SynchedEntityData.<Integer> defineId(EntityPeafowlBase.class, EntityEntityDataSerializers.INT);
	protected int laidTimer;

	public EntityPeafowlBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(0.6F, 1.2F);
		this.width = 0.6F;
		this.height = 1.2F;
		this.goalSelector.addGoal(1, new FindPeacockNestGoal(this, 1.0D));
		this.laidTimer = AnimaniaConfig.careAndFeeding.laidTimer / 2 + 0 + this.rand.nextInt(100);
		this.gender = EntityGender.FEMALE;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityPeafowlBase.LAID, true);
		this.dataManager.register(EntityPeafowlBase.LAID_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.laidTimer / 2 + 0 + this.rand.nextInt(100)));
	}

	@Override
	public void writeEntityToNBT(CompoundTag CompoundTag)
	{
		super.writeEntityToNBT(CompoundTag);
		CompoundTag.putBoolean("Laid", this.getLaid());
		CompoundTag.putInteger("LaidTimer", this.getLaidTimer());
	}

	@Override
	public void readEntityFromNBT(CompoundTag CompoundTag)
	{
		super.readEntityFromNBT(CompoundTag);
		this.setLaid(CompoundTag.getBoolean("Laid"));
		this.setLaidTimer(CompoundTag.getInteger("LaidTimer"));
	}

	public int getLaidTimer()
	{
		return this.getIntFromDataManager(LAID_TIMER);
	}

	public void setLaidTimer(int laidtimer)
	{
		this.dataManager.set(EntityPeafowlBase.LAID_TIMER, Integer.valueOf(laidtimer));
	}

	@Override
	public void onLivingUpdate()
	{
		int laidTimer = this.getLaidTimer();

		if (laidTimer > -1)
		{
			laidTimer--;
			this.setLaidTimer(laidTimer);
		}
		else
		{
			this.setLaid(false);
		}

		super.onLivingUpdate();
	}

	public boolean getLaid()
	{
		return this.getBoolFromDataManager(LAID);
	}

	public void setLaid(boolean laid)
	{
		if (laid)
		{
			this.dataManager.set(EntityPeafowlBase.LAID, true);
			this.setLaidTimer(AnimaniaConfig.careAndFeeding.laidTimer + this.rand.nextInt(100));
		}
		else
			this.dataManager.set(EntityPeafowlBase.LAID, false);
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume() - .8F, this.getSoundPitch() + .2F);
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking())
		{

			EntityPeafowlBase ehb = (EntityPeafowlBase) entity;
			int timer = ehb.getLaidTimer();
			if (timer >= 0)
			{
				probeInfo.text(I18n.translateToLocal("text.waila.egglay") + ": " + timer);
			}
			else
			{
				probeInfo.text(I18n.translateToLocal("text.waila.egglay2"));
			}
		}
		TOPInfoProviderBase.super.addProbeInfo(mode, probeInfo, player, level, entity, data);
	}

}
