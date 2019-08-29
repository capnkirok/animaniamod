package com.animania.common.entities.peacocks;

import com.animania.api.data.EntityGender;
import com.animania.common.entities.peacocks.ai.EntityAIFindPeacockNest;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.animania.config.AnimaniaConfig;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class EntityPeafowlBase extends EntityAnimaniaPeacock implements TOPInfoProviderBase
{

	private static final DataParameter<Boolean> LAID = EntityDataManager.<Boolean>createKey(EntityPeafowlBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> LAID_TIMER = EntityDataManager.<Integer>createKey(EntityPeafowlBase.class, DataSerializers.VARINT);
	protected int laidTimer;
	
	public EntityPeafowlBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.6F, 1.2F); 
		this.width = 0.6F;
		this.height = 1.2F;
		this.tasks.addTask(1, new EntityAIFindPeacockNest(this, 1.0D));
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
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setBoolean("Laid", this.getLaid());
		nbttagcompound.setInteger("LaidTimer", this.getLaidTimer());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		this.setLaid(nbttagcompound.getBoolean("Laid"));
		this.setLaidTimer(nbttagcompound.getInteger("LaidTimer"));
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

		if (laidTimer > -1) {
			laidTimer--;
			this.setLaidTimer(laidTimer); 
		} else {
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
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking())
		{

			EntityPeafowlBase ehb = (EntityPeafowlBase)entity;
			int timer = ehb.getLaidTimer();
			if (timer >= 0) { 
				probeInfo.text(I18n.translateToLocal("text.waila.egglay") + ": " + timer);
			} else {
				probeInfo.text(I18n.translateToLocal("text.waila.egglay2"));
			}
		}
		TOPInfoProviderBase.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

}
