package com.animania.common.entities.peacocks;

import com.animania.common.entities.chickens.EntityHenBase;
import com.animania.common.entities.peacocks.ai.EntityAIFindPeacockNest;
import com.animania.config.AnimaniaConfig;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPeafowlBase extends EntityAnimaniaPeacock
{

	private static final DataParameter<Boolean> LAID = EntityDataManager.<Boolean>createKey(EntityPeafowlBase.class, DataSerializers.BOOLEAN);
	protected int laidTimer;
	
	public EntityPeafowlBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.6F, 1.2F);
		this.tasks.addTask(1, new EntityAIFindPeacockNest(this, 1.0D));
		this.laidTimer = AnimaniaConfig.careAndFeeding.laidTimer / 2 + 0 + this.rand.nextInt(100);
		
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityPeafowlBase.LAID, Boolean.valueOf(true));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setBoolean("Laid", this.getLaid());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		this.setLaid(nbttagcompound.getBoolean("Laid"));
	}

	@Override
	public void onLivingUpdate()
	{
		
		if (this.laidTimer > -1)
			this.laidTimer--;
		else
			this.setLaid(false);

		super.onLivingUpdate();
	}

	public boolean getLaid()
	{
		return this.dataManager.get(EntityPeafowlBase.LAID).booleanValue();
	}

	public void setLaid(boolean laid)
	{
		if (laid)
		{
			this.dataManager.set(EntityPeafowlBase.LAID, Boolean.valueOf(true));
			this.laidTimer = AnimaniaConfig.careAndFeeding.laidTimer + this.rand.nextInt(100);
		}
		else
			this.dataManager.set(EntityPeafowlBase.LAID, Boolean.valueOf(false));
	}
	
	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume() - .8F, this.getSoundPitch() + .2F);
	}
	
	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		return;
	}

}
