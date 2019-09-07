package com.animania.common.entities.chickens;

import java.util.UUID;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.google.common.base.Optional;

import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityChickBase extends EntityAnimaniaChicken implements TOPInfoProviderBase, IChild
{

	protected static final DataParameter<Float> AGE = EntityDataManager.<Float>createKey(EntityChickBase.class, DataSerializers.FLOAT);
	protected int ageTimer;
	
	public EntityChickBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.7F, 0.8F); 
		this.width = 0.7F;
		this.height = 0.8F;
		this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
		this.ageTimer = 0;
		this.type = ChickenType.LEGHORN;
		this.gender = EntityGender.CHILD;

	}

	@Override
	public boolean isChild()
	{
		return true;
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return null;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityChickBase.AGE, Float.valueOf(0));
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
		this.timeUntilNextEgg = 1000;

		GenericBehavior.livingUpdateChild(this, EntityHenBase.class);

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

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume() - .3F, this.getSoundPitch() + .9F - this.getEntityAge() * 2);
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		return;
	}

	@Override
	protected Item getDropItem()
	{
		return null;
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
	
	@Override
	public void ageUp(int growthSeconds, boolean updateForcedAge)
	{
		float entityAge = this.getEntityAge();
		entityAge += 0.05f;
		this.setEntityAge(entityAge);
	}

}
