package com.animania.addons.farm.common.entity.horses;

import java.util.Set;
import java.util.UUID;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFollowParents;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityEntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class EntityFoalBase extends EntityAnimaniaHorse implements TOPInfoProviderBase, IChild
{
	private static final EntityDataAccessor<Integer> COLOR_NUM = SynchedEntityData.<Integer> defineId(EntityFoalBase.class, EntityEntityDataSerializers.INT);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.WHEAT, Items.APPLE, Items.CARROT);
	private static final EntityDataAccessor<Optional<UUID>> PARENT_UNIQUE_ID = SynchedEntityData.defineId(EntityFoalBase.class, EntityDataSerializers.OPTIONAL_UNIQUE_ID);
	private static final EntityDataAccessor<Float> AGE = SynchedEntityData.defineId(EntityFoalBase.class, EntityDataSerializers.FLOAT);
	protected int ageTimer;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	private static final String[] HORSE_TEXTURES = { "black", "bw1", "bw2", "grey", "red", "white" };

	public EntityFoalBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(2.2F, 3.0F);
		this.width = 2.2F;
		this.height = 3.0F;
		this.stepHeight = 1.1F;
		this.goalSelector.addGoal(1, new GenericAIFollowParents<EntityFoalBase, EntityMareBase>(this, 1.1, EntityMareBase.class));
		this.ageTimer = 0;
		this.horseType = HorseType.DRAFT;
		this.gender = EntityGender.CHILD;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityFoalBase.AGE, Float.valueOf(0));
		this.dataManager.register(EntityFoalBase.PARENT_UNIQUE_ID, Optional.<UUID> absent());
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	@Override
	public void setInLove(Player player)
	{
		this.level.broadcastEntityEvent(this, (byte) 18);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_HORSE_STEP, 0.05F, 1.1F);
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
		{
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() + .2F - this.getEntityAge() * 2);
		}
	}

	@Override
	public EntityDataAccessor<Optional<UUID>> getParentUniqueIdParam()
	{
		return EntityFoalBase.PARENT_UNIQUE_ID;
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
	public ResourceLocation getResourceLocation()
	{
		return this.resourceLocation;
	}

	@Override
	public ResourceLocation getResourceLocationBlink()
	{
		return this.resourceLocationBlink;
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getColorNumber() > 5)
		{
			this.setColorNumber(0);
		}

		GenericBehavior.livingUpdateChild(this, EntityMareBase.class);

		this.setSize((1f + this.getEntityAge()) * 2, (1.35f + this.getEntityAge()) * 2);

		super.onLivingUpdate();
	}

	@Override
	public EntityFoalBase createChild(AgeableEntity p_90011_1_)
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
		return 1.889f;
	}

}
