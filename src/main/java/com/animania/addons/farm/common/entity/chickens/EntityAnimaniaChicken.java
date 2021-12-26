package com.animania.addons.farm.common.entity.chickens;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.api.interfaces.IConvertable;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAISwimmingSmallCreatures;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EntityAnimaniaChicken extends Chicken implements IAnimaniaAnimalBase, IConvertable
{
	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(FarmConfig.settings.chickenFood));
	protected static final EntityDataAccessor<Boolean> FED = SynchedEntityData.<Boolean> createKey(EntityAnimaniaChicken.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> WATERED = SynchedEntityData.defineId(EntityAnimaniaChicken.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.defineId(EntityAnimaniaChicken.class, EntityDataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityAnimaniaChicken.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(EntityAnimaniaChicken.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> HANDFED = SynchedEntityData.defineId(EntityAnimaniaChicken.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> INTERACTED = SynchedEntityData.defineId(EntityAnimaniaChicken.class, EntityDataSerializers.BOOLEAN);

	public boolean chickenJockey;
	protected ResourceLocation resourceLocation;
	protected ResourceLocation resourceLocationBlink;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;
	private int fedTimer;
	protected int wateredTimer;
	protected int happyTimer;
	public int blinkTimer;
	private int featherTimer;
	protected int damageTimer;
	public ChickenType type;
	public EntityGender gender;
	public int lidCol;

	public EntityAnimaniaChicken(Level levelIn)
	{
		super(levelIn);
		this.tasks.taskEntries.clear();
		this.goalSelector.addGoal(0, new GenericAISwimmingSmallCreatures(this));
		this.goalSelector.addGoal(1, new GenericAIPanic<>(this, 1.4D));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.goalSelector.addGoal(2, new GenericAIFindWater<>(this, 1.0D, null, EntityAnimaniaChicken.class, true));
			this.goalSelector.addGoal(3, new GenericAIFindFood<>(this, 1.0D, null, true));
		}

		this.goalSelector.addGoal(4, new GenericAITempt<>(this, 1.2D, false, EntityAnimaniaChicken.TEMPTATION_ITEMS));
		this.goalSelector.addGoal(6, new GenericAIWanderAvoidWater(this, 1.0D));
		this.goalSelector.addGoal(7, new WatchClosestFromSideGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(11, new GenericAILookIdle<>(this));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.goalSelector.addGoal(8, new GenericAISleep<EntityAnimaniaChicken>(this, 0.8, AnimaniaHelper.getBlock(FarmConfig.settings.chickenBed), AnimaniaHelper.getBlock(FarmConfig.settings.chickenBed2), EntityAnimaniaChicken.class));
		}
		this.targetTasks.addTask(0, new HurtByTargetGoal(this, false, new Class[0]));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + this.rand.nextInt(80);
		this.featherTimer = AnimaniaConfig.careAndFeeding.featherTimer + rand.nextInt(1000);
		this.enablePersistence();
	}

	@Override
	protected void initEntityAI()
	{
	}

	@Override
	public void setPosition(double x, double y, double z)
	{
		super.setPosition(x, y, z);
	}

	@Override
	public void setInLove(Player player)
	{
		this.level.broadcastEntityEvent(this, (byte) 18);
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{
		return GenericBehavior.interactCommon(this, player, hand, null) ? true : super.processInteract(player, hand);
	}

	public ResourceLocation getResourceLocation()
	{
		return this.resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink()
	{
		return this.resourceLocationBlink;
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityChickBase ? null : this.type.isPrime ? new ResourceLocation("farm/" + Animania.MODID, "chicken_prime") : new ResourceLocation("farm/" + Animania.MODID, "chicken_regular");
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.29D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaChicken.FED, true);
		this.dataManager.register(EntityAnimaniaChicken.WATERED, true);
		this.dataManager.register(EntityAnimaniaChicken.AGE, Integer.valueOf(0));
		this.dataManager.register(EntityAnimaniaChicken.SLEEPING, false);
		this.dataManager.register(EntityAnimaniaChicken.HANDFED, false);
		this.dataManager.register(INTERACTED, false);
	}

	@Override
	public void writeEntityToNBT(CompoundTag CompoundTag)
	{
		super.writeEntityToNBT(CompoundTag);
		CompoundTag.putBoolean("IsChickenJockey", this.chickenJockey);

		GenericBehavior.writeCommonNBT(CompoundTag, this);
	}

	@Override
	public void readEntityFromNBT(CompoundTag CompoundTag)
	{
		super.readEntityFromNBT(CompoundTag);

		this.chickenJockey = CompoundTag.getBoolean("IsChickenJockey");

		GenericBehavior.readCommonNBT(CompoundTag, this);
	}

	@Override
	public EntityDataAccessor<Integer> getAgeParam()
	{
		return AGE;
	}

	@Override
	public void onLivingUpdate()
	{

		super.onLivingUpdate();

		GenericBehavior.livingUpdateCommon(this);

		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float) (this.destPos + (this.onGround || this.isPassenger() ? -1 : 4) * 0.3D);
		this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

		this.fallDistance = 0;

		if (!this.level.isClientSide && !this.isChild() && AnimaniaConfig.gameRules.birdsDropFeathers && !this.isChickenJockey() && --this.featherTimer <= 0)
		{
			this.playSound(FarmAddonSoundHandler.chickenCluck2, 0.5F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.dropItem(Items.FEATHER, 1);
			this.featherTimer = AnimaniaConfig.careAndFeeding.featherTimer + rand.nextInt(1000);
		}

		if (!this.onGround && !this.isPassenger() && this.wingRotDelta < 1.0F)
			this.wingRotDelta = 1.0F;

		this.wingRotDelta = (float) (this.wingRotDelta * 0.9D);

		if (!this.onGround && !this.isPassenger() && this.motionY < 0.0D)
			this.motionY *= 0.6D;

		this.wingRotation += this.wingRotDelta * 2.0F;
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
	public EntityDataAccessor<Boolean> getSleepingParam()
	{
		return SLEEPING;
	}

	@Override
	public EntityDataAccessor<Boolean> getHandFedParam()
	{
		return HANDFED;
	}

	protected void fall(float p_70069_1_)
	{
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, FarmAddonSoundHandler.chickenCluck1, FarmAddonSoundHandler.chickenCluck2, FarmAddonSoundHandler.chickenCluck3, FarmAddonSoundHandler.chickenCluck4, FarmAddonSoundHandler.chickenCluck5, FarmAddonSoundHandler.chickenCluck6);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return null;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return null;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.10F, 1.4F);
	}

	@Override
	public void playSound(SoundEvent soundIn, float volume, float pitch)
	{
		if (!this.isSilent() && !this.getSleeping())
		{
			this.level.playSound((Player) null, this.getX(), this.getY(), this.getZ(), soundIn, this.getSoundCategory(), volume, pitch);
		}
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, stack);
	}

	/**
	 * Get the experience points the entity currently has.
	 */
	@Override
	protected int getExperiencePoints(Player player)
	{
		return this.isChickenJockey() ? 10 : super.getExperiencePoints(player);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void updatePassenger(Entity passenger)
	{
		super.updatePassenger(passenger);
		float f = MathHelper.sin(this.renderYawOffset * 0.017453292F);
		float f1 = MathHelper.cos(this.renderYawOffset * 0.017453292F);
		float f2 = 0.1F;
		float f3 = 0.0F;
		passenger.setPosition(this.getX() + 0.1F * f, this.getY() + this.height * 0.5F + passenger.getYOffset() + 0.0D, this.getZ() - 0.1F * f1);

		if (passenger instanceof LivingEntity)
			((LivingEntity) passenger).renderYawOffset = this.renderYawOffset;
	}

	@Override
	public boolean isChickenJockey()
	{
		return this.chickenJockey;
	}

	@Override
	public void setChickenJockey(boolean jockey)
	{
		this.chickenJockey = jockey;
	}

	@Override
	public ChickenEntity createChild(AgeableEntity ageable)
	{
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSecondaryEggColor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EntityGender getEntityGender()
	{
		return this.gender;
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
	public Set<ItemStack> getFoodItems()
	{
		return TEMPTATION_ITEMS;
	}

	@Override
	public void setSleepingPos(BlockPos pos)
	{

	}

	@Override
	public BlockPos getSleepingPos()
	{
		return null;
	}

	@Override
	public Class[] getFoodBlocks()
	{
		return new Class[] { BlockSeeds.class };
	}

	@Override
	public Float getSleepTimer()
	{
		return -100f;
	}

	@Override
	public void setSleepTimer(Float timer)
	{

	}

	@Override
	public int getEatTimer()
	{
		return 0;
	}

	@Override
	public void setEatTimer(int i)
	{
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
	public EntityDataAccessor<Float> getSleepTimerParam()
	{
		return null;
	}

	@Override
	public Entity convertToVanilla()
	{
		ChickenEntity entity = new ChickenEntity(this.level);
		entity.setPosition(this.getX(), this.getY(), this.getZ());
		if (entity.hasCustomName())
			entity.setCustomNameTag(this.getCustomNameTag());
		return entity;
	}
}
