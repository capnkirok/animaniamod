package com.animania.addons.catsdogs.common.entity.canids;

import java.util.UUID;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.api.interfaces.IPlaying;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFollowParents;
import com.animania.common.entities.generic.ai.GenericAIPlay;
import com.animania.compat.top.providers.entity.TOPInfoProviderChild;
import com.google.common.base.Optional;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityEntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;

public class EntityPuppyBase extends EntityAnimaniaDog implements TOPInfoProviderChild, IChild, IPlaying
{

	protected static final EntityDataAccessor<Optional<UUID>> PARENT_UNIQUE_ID = SynchedEntityData.<Optional<UUID>> defineId(EntityPuppyBase.class, EntityEntityDataSerializers.OPTIONAL_UUID);
	protected static final EntityDataAccessor<Float> AGE = SynchedEntityData.<Float> defineId(EntityPuppyBase.class, EntityEntityDataSerializers.FLOAT);
	protected int ageTimer;

	protected GenericAIPlay playAI;

	public EntityPuppyBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(1f, 1f);
		this.width = 1f;
		this.height = 1f;
		this.stepHeight = 1.1F;
		this.ageTimer = 0;
		this.gender = EntityGender.CHILD;
		this.goalSelector.addGoal(1, new GenericAIFollowParents<EntityPuppyBase, EntityFemaleDogBase>(this, 1.1D, EntityFemaleDogBase.class));
		this.goalSelector.addGoal(8, playAI = new GenericAIPlay(this, EntityPuppyBase.class));
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
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.315D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(AGE, Float.valueOf(0));
		this.dataManager.register(PARENT_UNIQUE_ID, Optional.<UUID> absent());

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
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateChild(this, EntityFemaleDogBase.class);

		super.onLivingUpdate();
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
		return 1f;
	}

	@Override
	public GenericAIPlay getPlayAI()
	{
		return this.playAI;
	}

}
