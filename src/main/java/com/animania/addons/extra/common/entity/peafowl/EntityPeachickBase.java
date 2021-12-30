package com.animania.addons.extra.common.entity.peafowl;

import java.util.UUID;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.common.entities.generic.GenericBehavior;
import com.google.common.base.Optional;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.level.Level;

public class EntityPeachickBase extends EntityAnimaniaPeacock implements IChild
{
	protected static final EntityDataAccessor<Float> AGE = SynchedEntityData.<Float> defineId(EntityPeachickBase.class, EntityDataSerializers.FLOAT);
	protected int ageTimer;

	public EntityPeachickBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(1.4F, 1.6F);
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
		this.entityData.set(EntityPeachickBase.AGE, (float) 0);

	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateChild(this, EntityPeafowlBase.class);

		super.onLivingUpdate();
	}

	@Override
	public int getAgeTimer()
	{
		return this.ageTimer;
	}

	@Override
	public void setAgeTimer(int i)
	{
		this.ageTimer = i;
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
	public EntityDataAccessor<Optional<UUID>> getParentUniqueIdParam()
	{
		return null;
	}

	@Override
	public EntityDataAccessor<Float> getEntityAgeParam()
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
