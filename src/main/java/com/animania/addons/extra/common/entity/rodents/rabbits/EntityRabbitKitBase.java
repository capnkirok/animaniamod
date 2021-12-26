package com.animania.addons.extra.common.entity.rodents.rabbits;

import java.util.UUID;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFollowParents;
import com.animania.compat.top.providers.entity.TOPInfoProviderChild;
import com.google.common.base.Optional;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityEntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class RabbitEntityKitBase extends EntityAnimaniaRabbit implements TOPInfoProviderChild, IChild
{

	protected static final EntityDataAccessor<Optional<UUID>> PARENT_UNIQUE_ID = SynchedEntityData.<Optional<UUID>> defineId(RabbitEntityKitBase.class, EntityEntityDataSerializers.OPTIONAL_UUID);
	protected static final EntityDataAccessor<Float> AGE = SynchedEntityData.<Float> defineId(RabbitEntityKitBase.class, EntityEntityDataSerializers.FLOAT);
	protected int ageTimer;

	public RabbitEntityKitBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(0.8F, 0.8F);
		this.width = 0.8F;
		this.height = 0.8F;
		this.stepHeight = 1.1F;
		this.ageTimer = 0;
		this.gender = EntityGender.CHILD;
		this.goalSelector.addGoal(1, new GenericAIFollowParents<RabbitEntityKitBase, RabbitEntityDoeBase>(this, 1.1, RabbitEntityDoeBase.class));
	}

	@Override
	public boolean isChild()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.315D);

	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(RabbitEntityKitBase.AGE, Float.valueOf(0));
		this.dataManager.register(RabbitEntityKitBase.PARENT_UNIQUE_ID, Optional.<UUID> absent());

	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() + .2F);
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
	public void onLivingUpdate()
	{

		GenericBehavior.livingUpdateChild(this, RabbitEntityDoeBase.class);
		this.setSize((0.2f + this.getEntityAge()) * 2, (0.2f + this.getEntityAge()) * 2);

		super.onLivingUpdate();
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
	}

	@Override
	protected Item getDropItem()
	{
		return null;
	}

	@Override
	public EntityDataAccessor<Optional<UUID>> getParentUniqueIdParam()
	{
		return PARENT_UNIQUE_ID;
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
		return 2.36111f;
	}

}
