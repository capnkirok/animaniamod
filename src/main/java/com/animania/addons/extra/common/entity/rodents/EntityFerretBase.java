package com.animania.addons.extra.common.entity.rodents;

import java.util.Set;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;
import com.animania.addons.extra.common.entity.amphibians.EntityAmphibian;
import com.animania.addons.extra.common.entity.amphibians.EntityFrogs;
import com.animania.addons.extra.common.entity.amphibians.EntityToad;
import com.animania.addons.extra.common.entity.rodents.ai.FerretFindNestsGoal;
import com.animania.addons.extra.common.entity.rodents.ai.RodentEatGoal;
import com.animania.addons.extra.common.handler.ExtraAddonSoundHandler;
import com.animania.addons.extra.compat.top.TOPInfoProviderRodent;
import com.animania.addons.extra.config.ExtraConfig;
import com.animania.addons.extra.network.CapSyncPacket;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAIFollowOwner;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAINearestAttackableTarget;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAISwimmingSmallCreatures;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityEntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.network.NetworkRegistry;

public class EntityFerretBase extends TamableAnimal implements TOPInfoProviderRodent, IAnimaniaAnimalBase
{

	protected static final EntityDataAccessor<Boolean> FED = SynchedEntityData.<Boolean> createKey(EntityFerretBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> WATERED = SynchedEntityData.defineId(EntityFerretBase.class, EntityDataSerializers.BOOLEAN);
	// protected static final EntityDataAccessor<Boolean> TAMED =
	// SynchedEntityData.defineId(EntityFerretBase.class,
	// EntityDataSerializers.BOOLEAN);
	// protected static final EntityDataAccessor<Boolean> SITTING =
	// SynchedEntityData.defineId(EntityFerretBase.class,
	// EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> RIDING = EntityEntityDataSerializers.<Boolean> createKey(EntityFerretBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityFerretBase.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(EntityFerretBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Float> SLEEPTIMER = SynchedEntityData.defineId(EntityFerretBase.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> INTERACTED = SynchedEntityData.defineId(EntityFerretBase.class, EntityDataSerializers.BOOLEAN);

	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(ExtraConfig.settings.ferretFood));
	protected int fedTimer;
	protected int wateredTimer;
	protected int happyTimer;
	protected int tamedTimer;
	public int blinkTimer;
	public int eatTimer;
	public GenericAIEatGrass<EntityFerretBase> entityAIEatGrass;
	protected int damageTimer;
	protected FerretType type;

	public EntityFerretBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(.75F, .40F);
		this.stepHeight = 1.1F;
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer * 2 + this.rand.nextInt(200);
		this.happyTimer = 60;
		this.tamedTimer = 120;
		this.blinkTimer = 70 + this.rand.nextInt(70);
		this.enablePersistence();
		this.entityAIEatGrass = new GenericAIEatGrass(this, false);
		this.goalSelector.addGoal(11, this.entityAIEatGrass);

		this.initAI();
	}

	protected void initAI()
	{
		this.aiSit = new SitGoal(this);
		this.goalSelector.addGoal(0, new GenericAISwimmingSmallCreatures(this));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.goalSelector.addGoal(1, new GenericAIFindWater<>(this, 1.0D, this.entityAIEatGrass, EntityFerretBase.class, true));
			this.goalSelector.addGoal(2, new FerretFindNestsGoal(this, 1.0D));
			this.goalSelector.addGoal(3, new GenericAIFindFood<>(this, 1.0D, this.entityAIEatGrass, false));
		}
		this.goalSelector.addGoal(4, this.aiSit);
		this.goalSelector.addGoal(5, new LeapAtTargetGoal(this, 0.2F));
		this.goalSelector.addGoal(6, new AttackMeleeGoal(this, 1.0D, true));
		this.goalSelector.addGoal(7, new GenericAIFollowOwner<>(this, 1.0D, 10.0F, 2.0F));
		this.goalSelector.addGoal(8, new GenericAIPanic<>(this, 1.5D));
		this.goalSelector.addGoal(9, new RodentEatGoal(this));
		this.goalSelector.addGoal(10, new GenericAITempt<>(this, 1.2D, false, EntityFerretBase.TEMPTATION_ITEMS));
		this.goalSelector.addGoal(12, new GenericAIWanderAvoidWater(this, 1.2D));
		this.goalSelector.addGoal(13, new GenericAIWatchClosest(this, Player.class, 6.0F));
		this.goalSelector.addGoal(14, new GenericAILookIdle<>(this));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.goalSelector.addGoal(15, new GenericAISleep<EntityFerretBase>(this, 0.8, AnimaniaHelper.getBlock(ExtraConfig.settings.ferretBed), AnimaniaHelper.getBlock(ExtraConfig.settings.ferretBed2), EntityFerretBase.class));
		}
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers)
		{
			AddonInjectionHandler.runInjection("farm", "attackChicks", Void.class, this);

			this.targetSelector.addTask(6, new GenericAINearestAttackableTarget<SilverfishEntity>(this, SilverfishEntity.class, false));
			this.targetSelector.addTask(7, new GenericAINearestAttackableTarget<>(this, EntityFrogs.class, false));
			this.targetSelector.addTask(8, new GenericAINearestAttackableTarget<>(this, EntityToad.class, false));
		}
		this.targetSelector.addTask(9, new HurtByTargetGoal(this, false, new Class[0]));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.5D);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void setPosition(double x, double y, double z)
	{
		super.setPosition(x, y, z);
	}

	@Override
	public int getVerticalFaceSpeed()
	{
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
	}

	@Override
	protected void consumeItemFromStack(Player player, ItemStack stack)
	{
		this.setFed(true);
		if (!this.isTamed())
		{
			this.setOwnerId(player.getUUID());
			// this.setIsTamed(true);
			this.setTamed(true);
			this.setInLove(player);
		}

		this.setSitting(false);
		// this.setAnimalSitting(false);
		this.entityAIEatGrass.startExecuting();
		if (!player.capabilities.isCreativeMode)
			if (stack != ItemStack.EMPTY)
				stack.setCount(stack.getCount() - 1);

	}

	@Override
	public void setInLove(Player player)
	{
		this.level.broadcastEntityEvent(this, (byte) 18);
	}

	@Override
	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return new ResourceLocation("extra/" + Animania.MODID, "ferret");
	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		Player Player = player;

		if (stack == ItemStack.EMPTY && this.isTamed() && player.isSneaking() && !this.getSleeping())
		{
			ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
			if (!props.isCarrying())
			{
				props.setAnimal(this.writeToNBT(new CompoundTag()));
				props.setCarrying(true);
				props.setType(EntityList.getKey(this).getPath());
				this.setDead();
				player.swingArm(InteractionHand.MAIN_HAND);
				if (!player.level.isClientSide)
					Animania.network.sendToAllAround(new CapSyncPacket(props, player.getEntityId()), new NetworkRegistry.TargetPoint(player.level.provider.getDimension(), player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 64));
				return true;
			}
		}

		return GenericBehavior.interactCommon(this, player, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);

		if (flag)
			this.applyEnchantments(this, entityIn);

		if (entityIn instanceof EntityAmphibian)
		{
			this.setFed(true);
		}

		// Custom Knockback
		if (entityIn instanceof Player)
			((LivingEntity) entityIn).knockBack(this, 0.3f, this.getX() - entityIn.getX(), this.getZ() - entityIn.getZ());

		return flag;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(EntityFerretBase.FED, true);
		this.entityData.register(EntityFerretBase.WATERED, true);
		// this.entityData.register(EntityFerretBase.TAMED, false);
		// this.entityData.register(EntityFerretBase.SITTING, false);
		this.entityData.register(EntityFerretBase.RIDING, false);
		this.entityData.register(EntityFerretBase.AGE, Integer.valueOf(0));
		this.entityData.register(EntityFerretBase.SLEEPING, false);
		this.entityData.register(EntityFerretBase.SLEEPTIMER, Float.valueOf(0.0F));
		this.entityData.register(INTERACTED, false);
	}

	@Override
	public void writeEntityToNBT(CompoundTag compound)
	{
		super.writeEntityToNBT(compound);
		compound.putBoolean("IsTamed", this.isTamed());
		compound.putBoolean("IsSitting", this.isSitting());
		compound.putBoolean("Riding", this.isFerretRiding());

		GenericBehavior.writeCommonNBT(compound, this);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(CompoundTag compound)
	{
		super.readEntityFromNBT(compound);
		// this.setIsTamed(compound.getBoolean("IsTamed"));
		// this.setAnimalSitting(compound.getBoolean("IsSitting"));
		this.setFerretRiding(compound.getBoolean("Riding"));

		GenericBehavior.readCommonNBT(compound, this);
	}

	@Override
	public EntityDataAccessor<Integer> getAgeParam()
	{
		return AGE;
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
	public boolean canBeLeashedTo(Player player)
	{
		return true;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, ExtraAddonSoundHandler.ferretLiving1, ExtraAddonSoundHandler.ferretLiving2, ExtraAddonSoundHandler.ferretLiving3, ExtraAddonSoundHandler.ferretLiving4, ExtraAddonSoundHandler.ferretLiving5, ExtraAddonSoundHandler.ferretLiving6);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return ExtraAddonSoundHandler.ferretHurt1;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ExtraAddonSoundHandler.ferretHurt1;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume() - .3F, this.getSoundPitch());
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.5F);
	}

	private boolean interactRide(Player Player)
	{
		this.isClientSideMountEntity(Player);
		return true;
	}

	private void isClientSideMountEntity(Entity par1Entity)
	{

		if (this.isFerretRiding())
		{
			this.setFerretRiding(true);
			this.startRiding(par1Entity);
		}
		else if (!this.isFerretRiding())
			this.dismountRidingEntity();

	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateCommon(this);

		if (this.isSitting() || this.isPassenger())
		{
			if (this.getRidingEntity() != null)
				this.rotationYaw = this.getRidingEntity().rotationYaw;
			this.navigator.stop();
			this.navigator.setSpeed(0);
		}

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
				this.blinkTimer = 100 + this.rand.nextInt(100);
		}

		if (this.tamedTimer > -1)
		{
			this.tamedTimer--;
			if (this.tamedTimer == 0)
			{
				this.tamedTimer = 120;

				if (this.isTamed() && AnimaniaConfig.gameRules.showUnhappyParticles)
				{
					double d = this.rand.nextGaussian() * 0.02D;
					double d1 = this.rand.nextGaussian() * 0.02D;
					double d2 = this.rand.nextGaussian() * 0.02D;
					// this.level.spawnParticle(EnumParticleTypes.HEART,
					// this.getX() + this.rand.nextFloat() * this.width -
					// this.width, this.getY() + 1D + this.rand.nextFloat() *
					// this.height, this.getZ() + this.rand.nextFloat() *
					// this.width - this.width, d, d1, d2);
				}
			}
		}

		super.onLivingUpdate();
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
			this.eatTimer = 80;
		else
			super.handleStatusUpdate(id);
	}

	public boolean isFerretRiding()
	{
		return this.getBoolFromDataManager(RIDING);
	}

	public void setFerretRiding(boolean flag)
	{
		if (flag)
			this.entityData.set(EntityFerretBase.RIDING, true);
		else
			this.entityData.set(EntityFerretBase.RIDING, false);
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
	public EntityFerretBase createChild(AgeableEntity ageable)
	{
		return null;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, stack);
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.type, EntityGender.NONE));
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
		return EntityGender.NONE;
	}

	@Override
	public Set<ItemStack> getFoodItems()
	{
		return TEMPTATION_ITEMS;
	}

	@Override
	public void setSleepingPos(BlockPos pos)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public BlockPos getSleepingPos()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBlinkTimer()
	{
		return this.blinkTimer;
	}

	@Override
	public void setBlinkTimer(int i)
	{
		this.blinkTimer = i;
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
	public AnimaniaType getAnimalType()
	{
		return this.type;
	}

	@Override
	public EntityDataAccessor<Boolean> getHandFedParam()
	{
		return null;
	}
}
