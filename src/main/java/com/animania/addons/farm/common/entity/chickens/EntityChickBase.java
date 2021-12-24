package com.animania.addons.farm.common.entity.chickens;

import java.util.UUID;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.google.common.base.Optional;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.level.Level;

public class EntityChickBase extends EntityAnimaniaChicken implements TOPInfoProviderBase, IChild
{

	protected static final EntityDataAccessor<Float> AGE = SynchedEntityData.<Float>defineId(EntityChickBase.class, EntityDataSerializers.FLOAT);
	protected int ageTimer;
	
	public EntityChickBase(Level worldIn)
	{
		super(worldIn);
		this.setSize(1.1F, 1.5F); 
		this.tasks.addTask(5, new FollowParentGoal(this, 1.1D));
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
	public void writeEntityToNBT(CompoundNBT CompoundNBT)
	{
		super.writeEntityToNBT(CompoundNBT);


	}

	@Override
	public void readEntityFromNBT(CompoundNBT CompoundNBT)
	{
		super.readEntityFromNBT(CompoundNBT);
		

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

	@Override
	public float getSizeDividend()
	{
		return 2.125f;
	}

}
