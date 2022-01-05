package com.animania.catsdogs.common.entity.canids;

import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.api.interfaces.IConvertable;
import com.animania.api.interfaces.IVariant;
import com.animania.catsdogs.config.CatsDogsConfig;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.*;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;

public class EntityAnimaniaDog extends Wolf implements IAnimaniaAnimalBase, IVariant, IConvertable
{

	protected static final EntityDataAccessor<Boolean> FED = SynchedEntityData.defineId(EntityAnimaniaDog.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> WATERED = SynchedEntityData.defineId(EntityAnimaniaDog.class, EntityDataSerializers.BOOLEAN);
	// protected static final EntityDataAccessor<Boolean> TAMED =
	// SynchedEntityData.defineId(EntityAnimaniaDog.class,
	// EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> HANDFED = SynchedEntityData.defineId(EntityAnimaniaDog.class, EntityDataSerializers.BOOLEAN);
	// protected static final EntityDataAccessor<Boolean> SITTING =
	// SynchedEntityData.defineId(EntityAnimaniaDog.class,
	// EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityAnimaniaDog.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(EntityAnimaniaDog.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Float> SLEEPTIMER = SynchedEntityData.defineId(EntityAnimaniaDog.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntityAnimaniaDog.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Boolean> INTERACTED = SynchedEntityData.defineId(EntityAnimaniaDog.class, EntityDataSerializers.BOOLEAN);

	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(CatsDogsConfig.catsdogs.dogFood));

	protected int fedTimer;
	protected int wateredTimer;
	protected int happyTimer;
	protected int tamedTimer;
	public int blinkTimer;
	public int eatTimer;
	public GenericAIEatGrass<EntityAnimaniaDog> entityAIEatGrass;
	protected int damageTimer;
	protected DogType type;
	protected EntityGender gender;

	public EntityAnimaniaDog(Level levelIn)
	{
		super(levelIn);
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.getRandom().nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.getRandom().nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + this.getRandom().nextInt(80);
		this.setPersistenceRequired();
		this.entityAIEatGrass = new GenericAIEatGrass<>(this, false);
		this.goalSelector.addGoal(11, this.entityAIEatGrass);

		this.initAI();
	}

	protected void initAI()
	{
		this.aiSit = new GenericAISit(this);
		this.goalSelector.addGoal(0, new RandomSwimmingGoal(this));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.goalSelector.addGoal(1, new GenericAIFindWater<>(this, 1.0D, this.entityAIEatGrass, EntityAnimaniaDog.class, true));
			this.goalSelector.addGoal(3, new GenericAIFindFood<>(this, 1.0D, this.entityAIEatGrass, false));
		}
		this.goalSelector.addGoal(4, this.aiSit);
		this.goalSelector.addGoal(5, new LeapAtTargetGoal(this, 0.4F));
		this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(7, new GenericAIFollowOwner<>(this, 1.5D, 5.0F, 30.0F));
		this.goalSelector.addGoal(8, new GenericAIPanic<>(this, 1.5D));
		this.goalSelector.addGoal(10, new GenericAITempt<>(this, 1.2D, false, TEMPTATION_ITEMS)); // TODO
		this.goalSelector.addGoal(12, new GenericAIWanderAvoidWater(this, 1.2D));
		this.goalSelector.addGoal(13, new GenericAIWatchClosest(this, Player.class, 6.0F));
		this.goalSelector.addGoal(14, new GenericAILookIdle<>(this));
		this.targetSelector.addGoal(1, new GenericAIOwnerHurtByTarget(this));
		this.targetSelector.addGoal(2, new GenericAIOwnerHurtTarget(this));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(5, new GenericAINearestAttackableTarget<>(this, AbstractSkeleton.class, false));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.goalSelector.addGoal(14, new GenericAISleep<EntityAnimaniaDog>(this, 0.8, AnimaniaHelper.getBlock(CatsDogsConfig.catsdogs.dogBed), AnimaniaHelper.getBlock(CatsDogsConfig.catsdogs.dogBed2), EntityAnimaniaDog.class));
		}
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.isTame())
		{
			this.targetSelector.addGoal(4, new GenericAITargetNonTamed(this, Animal.class, false, entity -> entity instanceof Sheep || entity instanceof Rabbit));
		}
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(18.0D);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.5D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.set(FED, true);
		this.entityData.set(WATERED, true);
		// this.entityData.register(TAMED, false);
		// this.entityData.register(SITTING, false);
		this.entityData.set(SLEEPING, false);
		this.entityData.set(HANDFED, false);
		this.entityData.set(INTERACTED, false);
		this.entityData.set(AGE, 0);
		this.entityData.set(SLEEPTIMER, 0.0F);
		if (this.getVariantCount() > 0)
			this.entityData.set(VARIANT, this.getRandom().nextInt(this.getVariantCount()));
		else
			this.entityData.set(VARIANT, 0);
	}



	@Override
	public void writeEntityToNBT(CompoundTag compound)
	{
		super.writeEntityToNBT(compound);
		compound.putBoolean("IsTamed", this.isTamed());
		compound.putBoolean("IsSitting", this.isSitting());
		compound.putInt("Variant", this.getVariant());

		GenericBehavior.writeCommonNBT(compound, this);
	}

	@Override
	public void readEntityFromNBT(CompoundTag compound)
	{
		super.readEntityFromNBT(compound);
		this.setTamed(compound.getBoolean("IsTamed"));
		this.setSitting(compound.getBoolean("IsSitting"));
		this.setVariant(compound.getInteger("Variant"));

		GenericBehavior.readCommonNBT(compound, this);
	}

	@Override
	public boolean removeWhenFarAway(double p_27598_) {
		return false;
	}

	@Override
	public void setInLove(Player player)
	{
		this.level.broadcastEntityEvent(this, (byte) 18);
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		return entity.hurt(DamageSource.mobAttack(this), 2.5F);
	}

	@Override
	protected void customServerAiStep() {
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.customServerAiStep();
	}

	@Override
	public EntityDataAccessor<Integer> getAgeParam()
	{
		return AGE;
	}

	@Override
	public EntityDataAccessor<Boolean> getHandFedParam()
	{
		return HANDFED;
	}

	@Override
	public EntityDataAccessor<Boolean> getSleepingParam()
	{
		return SLEEPING;
	}

	@Override
	public EntityDataAccessor<Float> getSleepTimerParam()
	{
		return SLEEPTIMER;
	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{
		return GenericBehavior.interactCommon(this, player, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
	}

	@Override
	public boolean isFood(@NotNull ItemStack stack) {
		return stack != ItemStack.EMPTY && AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, stack);
	}

	@Override
	public EntityDataAccessor<Boolean> getFedParam()
	{
		return FED;
	}

	@Override
	public EntityDataAccessor<Boolean> getWateredParam()
	{
		return WATERED;
	}

	@Override
	public EntityDataAccessor<Integer> getVariantParam()
	{
		return VARIANT;
	}

	@Override
	public void tick() {
		if (this.isInSittingPose() || this.isPassenger())
		{
			if (this.getVehicle() != null)
				this.setYRot(this.getVehicle().getYRot());
			this.navigation.stop();
			this.navigation.setSpeedModifier(0);
		}

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
			{
				this.blinkTimer = 100 + this.getRandom().nextInt(100);
			}
		}

		GenericBehavior.livingUpdateCommon(this);

		super.tick();
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch() - 0.2F);
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 10) {
			this.eatTimer = 80;
		} else {
			super.handleEntityEvent(id);
		}
	}

	@Override
	public boolean canBeLeashed(@NotNull Player player)
	{
		return true;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.WOLF_STEP, 0.02F, 1.5F);
	}

	@Override
	public Wolf getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob ageable) {
		return null;
	}

	@Override
	public void setSleepingPos(BlockPos pos)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<BlockPos> getSleepingPos()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ItemStack> getFoodItems()
	{
		return TEMPTATION_ITEMS;
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.type, this.gender));
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		return new ItemStack(this.getSpawnEgg());
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 0;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 0;
	}

	@Override
	public EntityGender getEntityGender()
	{
		return null;
	}

	@Override
	public int getBlinkTimer()
	{
		return this.blinkTimer;
	}

	@Override
	public int getEatTimer()
	{
		return this.eatTimer;
	}

	@Override
	public void setEatTimer(int i)
	{
		this.eatTimer = i;
	}

	@Override
	public int getFedTimer()
	{
		return this.fedTimer;
	}

	@Override
	public void setFedTimer(int i)
	{
		this.fedTimer = i;
	}

	@Override
	public EntityDataAccessor<Boolean> getInteractedParam()
	{
		return INTERACTED;
	}

	@Override
	public int getWaterTimer()
	{
		return this.wateredTimer;
	}

	@Override
	public void setWaterTimer(int i)
	{
		this.wateredTimer = i;
	}

	@Override
	public int getDamageTimer()
	{
		return this.damageTimer;
	}

	@Override
	public void setDamageTimer(int i)
	{
		this.damageTimer = i;
	}

	@Override
	public int getHappyTimer()
	{
		return this.happyTimer;
	}

	@Override
	public void setHappyTimer(int i)
	{
		this.happyTimer = i;
	}

	@Override
	public void setBlinkTimer(int i)
	{
		this.blinkTimer = i;
	}

	@Override
	public AnimaniaType getAnimalType()
	{
		return this.type;
	}

	@Override
	public Entity convertToVanilla()
	{
		Wolf entity = new Wolf(EntityType.WOLF, this.level);
		entity.setPos(this.getX(), this.getY(), this.getZ());
		if (entity.hasCustomName())
			entity.setCustomName(this.getCustomName());
		return entity;
	}

}
