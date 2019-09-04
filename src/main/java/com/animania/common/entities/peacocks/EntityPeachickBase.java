package com.animania.common.entities.peacocks;

import java.util.UUID;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.common.entities.generic.GenericBehavior;
import com.google.common.base.Optional;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPeachickBase extends EntityAnimaniaPeacock implements IChild
{
	protected static final DataParameter<Float> AGE = EntityDataManager.<Float>createKey(EntityPeachickBase.class, DataSerializers.FLOAT);
	protected int ageTimer;

	public EntityPeachickBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.7F, 1.0F); 
		this.width = 0.7F;
		this.height = 1.0F;
		this.ageTimer = 0;
		this.type = PeacockType.BLUE;
		this.gender = EntityGender.CHILD;

	}

	@Override
	public boolean isChild()
	{
		return true;
	}
	
	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityPeachickBase.AGE, Float.valueOf(0));

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);


	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		

	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateChild(this, null);

		super.onLivingUpdate();
	}


	@Override
	public int getAgeTimer()
	{
		return ageTimer;
	}

	@Override
	public void setAgeTimer(int i)
	{
		ageTimer = i;
	}
	


	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume() - .9F, this.getSoundPitch() + .4F - this.getEntityAge() * 2);
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		return;
	}

	@Override
	public UUID getParentUniqueId()
	{
		return null;
	}

	@Override
	public void setParentUniqueId(UUID id)
	{
		
	}

	@Override
	public DataParameter<Optional<UUID>> getParentUniqueIdParam()
	{
		return null;
	}

	@Override
	public DataParameter<Float> getEntityAgeParam()
	{
		return AGE;
	}

}
