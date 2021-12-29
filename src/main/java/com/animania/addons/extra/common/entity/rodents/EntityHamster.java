package com.animania.addons.extra.common.entity.rodents;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;
import com.animania.addons.extra.common.entity.rodents.ai.LookIdleRodentGoal;
import com.animania.addons.extra.common.handler.ExtraAddonItemHandler;
import com.animania.addons.extra.common.handler.ExtraAddonSoundHandler;
import com.animania.addons.extra.compat.top.TOPInfoProviderRodent;
import com.animania.addons.extra.config.ExtraConfig;
import com.animania.addons.extra.network.CapSyncPacket;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAIFollowOwner;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAISwimmingSmallCreatures;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.handler.PatreonHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkRegistry;

public class EntityHamster extends TamableAnimal implements TOPInfoProviderRodent, IAnimaniaAnimalBase
{
	private static final EntityDataAccessor<Boolean> IN_BALL = SynchedEntityData.<Boolean> createKey(EntityHamster.class, EntityDataSerializers.BOOLEAN);
	// private static final EntityDataAccessor<Boolean> SITTING =
	// SynchedEntityData.defineId(EntityHamster.class,
	// EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> RIDING = SynchedEntityData.defineId(EntityHamster.class, EntityDataSerializers.BOOLEAN);
	// private static final EntityDataAccessor<Boolean> TAMED =
	// SynchedEntityData.defineId(EntityHamster.class,
	// EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> COLOR_NUM = SynchedEntityData.defineId(EntityHamster.class, EntityDataSerializers.VARINT);
	private static final EntityDataAccessor<Integer> FOOD_STACK_COUNT = SynchedEntityData.defineId(EntityHamster.class, EntityDataSerializers.VARINT);
	private static final EntityDataAccessor<Integer> IN_LOVE = SynchedEntityData.defineId(EntityHamster.class, EntityDataSerializers.VARINT);
	private static final EntityDataAccessor<Integer> BALL_COLOR = SynchedEntityData.defineId(EntityHamster.class, EntityDataSerializers.VARINT);
	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(ExtraConfig.settings.hamsterFood));
	private static final EntityDataAccessor<Boolean> FED = SynchedEntityData.defineId(EntityHamster.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> WATERED = SynchedEntityData.defineId(EntityHamster.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityHamster.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(EntityHamster.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Float> SLEEPTIMER = SynchedEntityData.defineId(EntityHamster.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> INTERACTED = SynchedEntityData.defineId(EntityHamster.class, EntityDataSerializers.BOOLEAN);

	private static final String[] HAMSTER_TEXTURES = { "black", "brown", "darkbrown", "darkgray", "gray", "plum", "tarou", "white", "gold" };

	private int fedTimer;
	private int wateredTimer;
	private int happyTimer;
	private int tamedTimer;
	public int blinkTimer;
	private long rideCount;

	private int eatCount;
	private int foodStackCount;
	private int standCount;
	private EntityItem targetFood;
	private boolean looksWithInterest;
	private boolean isStanding;
	private float field_25048_b;
	private float field_25054_c;
	private static List hamsterColorList;
	private Player givemeEntity;
	private boolean mountFlag;
	private double yOffset;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	private int damageTimer;

	public EntityHamster(Level level)
	{
		super(level);
		this.setHealth(6);
		this.yOffset = 0.1F;
		this.setSize(0.5F, 0.3F);
		this.width = 0.5F;
		this.height = 0.3F;
		this.stepHeight = 1.0F;
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer * 4 + this.rand.nextInt(200);
		this.looksWithInterest = false;
		int stackCount = 20;
		this.eatCount = 5000;
		this.standCount = 30;
		this.isStanding = false;
		int breeding = 0;
		this.happyTimer = 60;
		this.tamedTimer = 120;
		this.blinkTimer = 70 + this.rand.nextInt(70);
		this.enablePersistence();

		this.initAI();
	}

	protected void initAI()
	{

		this.goalSelector.addGoal(1, new GenericAIPanic<>(this, 1.4D));
		this.goalSelector.addGoal(2, new GenericAISwimmingSmallCreatures(this));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.goalSelector.addGoal(3, new GenericAIFindWater<>(this, 1.0D, null, EntityHamster.class, true));
			this.goalSelector.addGoal(3, new GenericAIFindFood<>(this, 1.0D, null, false));
		}
		this.goalSelector.addGoal(4, new FleeSunGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new GenericAIWanderAvoidWater(this, 1.1D));
		this.goalSelector.addGoal(6, new GenericAITempt<>(this, 1.2D, false, EntityHamster.TEMPTATION_ITEMS));
		this.goalSelector.addGoal(7, new GenericAIFollowOwner<>(this, 1.0D, 10.0F, 2.0F));
		this.goalSelector.addGoal(8, new GenericAIWatchClosest(this, Player.class, 6.0F));
		this.goalSelector.addGoal(9, new LookIdleRodentGoal(this));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.goalSelector.addGoal(10, new GenericAISleep(this, 0.8, Block.getBlockFromName(ExtraConfig.settings.hamsterBed), Block.getBlockFromName(ExtraConfig.settings.hamsterBed2), EntityHamster.class, leveltime -> leveltime < 13000));
		}
		this.targetSelector.addTask(0, new HurtByTargetGoal(this, false, new Class[0]));

	}

	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();
	}

	@Override
	public int getVerticalFaceSpeed()
	{
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
	}

	@Override
	public void setInLove(Player player)
	{
		this.level.broadcastEntityEvent(this, (byte) 18);
	}

	@Override
	public void setPosition(double x, double y, double z)
	{
		super.setPosition(x, y, z);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
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
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(EntityHamster.IN_BALL, false);
		// this.entityData.register(EntityHamster.SITTING, false);
		// this.entityData.register(EntityHamster.TAMED, false);
		this.entityData.register(EntityHamster.COLOR_NUM, Integer.valueOf(this.getRandom().nextInt(8)));
		this.entityData.register(EntityHamster.FOOD_STACK_COUNT, Integer.valueOf(0));
		this.entityData.register(EntityHamster.IN_LOVE, Integer.valueOf(0));
		this.entityData.register(EntityHamster.BALL_COLOR, Integer.valueOf(0));
		this.entityData.register(EntityHamster.FED, true);
		this.entityData.register(EntityHamster.WATERED, true);
		this.entityData.register(EntityHamster.RIDING, false);
		this.entityData.register(EntityHamster.AGE, Integer.valueOf(0));
		this.entityData.register(EntityHamster.SLEEPING, false);
		this.entityData.register(EntityHamster.SLEEPTIMER, Float.valueOf(0.0F));
		this.entityData.register(INTERACTED, false);
	}

	@Override
	public void writeEntityToNBT(CompoundTag CompoundTag)
	{
		super.writeEntityToNBT(CompoundTag);
		CompoundTag.putBoolean("IsSitting", this.isSitting());
		CompoundTag.putBoolean("InBall", this.isInBall());
		CompoundTag.putInt("ColorNumber", this.getColorNumber());
		CompoundTag.putInt("foodStackCount", this.getFoodStackCount());
		CompoundTag.putInt("BallColor", this.getBallColor());
		CompoundTag.putBoolean("IsTamed", this.isTamed());
		CompoundTag.putBoolean("IsRiding", this.getIsRiding());

		GenericBehavior.writeCommonNBT(CompoundTag, this);

	}

	@Override
	public void readEntityFromNBT(CompoundTag CompoundTag)
	{
		super.readEntityFromNBT(CompoundTag);
		this.setSitting(CompoundTag.getBoolean("IsSitting"));
		this.setInBall(CompoundTag.getBoolean("InBall"));
		this.setColorNumber(CompoundTag.getInteger("ColorNumber"));
		this.setFoodStackCount(CompoundTag.getInteger("foodStackCount"));
		this.setBallColor(CompoundTag.getInteger("BallColor"));
		this.setTamed(CompoundTag.getBoolean("IsTamed"));
		this.setIsRiding(CompoundTag.getBoolean("IsRiding"));

		GenericBehavior.readCommonNBT(CompoundTag, this);
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
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public float getEyeHeight()
	{
		return this.height * 0.8F;
	}

	@Override
	public double getYOffset()
	{
		return this.yOffset;
	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{
		ItemStack itemstack = player.getItemInHand(hand);

		if (this.isBreedingItem(itemstack) && !this.getSleeping())
		{
			this.doPatreonCheck(player);
			this.setHamsterStanding(true);
			this.standCount = 100;
			this.addFoodStack();

		}

		if (itemstack == ItemStack.EMPTY && this.isTamed() && player.isSneaking() && !this.isInBall() && !this.getSleeping())
		{
			ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
			if (!props.isCarrying())
			{
				props.setAnimal(this.writeToNBT(new CompoundTag()));
				props.setCarrying(true);
				props.setType("hamster");
				this.setDead();
				player.swingArm(InteractionHand.MAIN_HAND);
				if (!player.level.isClientSide)
					Animania.network.sendToAllAround(new CapSyncPacket(props, player.getEntityId()), new NetworkRegistry.TargetPoint(player.level.provider.getDimension(), player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 64));
				return true;
			}

		}

		if (!itemstack.isEmpty() && itemstack.getItem() == ExtraAddonItemHandler.hamsterBallColored && !this.isInBall() && !this.getSleeping())
		{
			if (this.isSitting())
			{
				this.setSitting(false);
			}
			this.setInBall(true);
			int meta = itemstack.getMetadata();
			this.setBallColor(meta);
			if (!player.isCreative())
				itemstack.shrink(1);
			return true;
		}
		if (!itemstack.isEmpty() && itemstack.getItem() == ExtraAddonItemHandler.hamsterBallClear && !this.isInBall() && !this.getSleeping())
		{
			if (this.isSitting())
			{
				this.setSitting(false);
				this.setSitting(false);
			}
			this.setInBall(true);
			int meta = itemstack.getMetadata();
			this.setBallColor(16);
			if (!player.isCreative())
				itemstack.shrink(1);
			return true;
		}
		else if (itemstack.isEmpty() && this.isInBall() && !this.getSleeping())
		{
			int color = this.getBallColor();
			this.setInBall(false);
			if (!player.isCreative())
			{
				if (color == 16)
					AnimaniaHelper.addItem(player, new ItemStack(ExtraAddonItemHandler.hamsterBallClear));
				else
					AnimaniaHelper.addItem(player, new ItemStack(ExtraAddonItemHandler.hamsterBallColored, 1, color));
			}
			return true;
		}

		return GenericBehavior.interactCommon(this, player, hand, null) ? true : super.processInteract(player, hand);
	}

	@Override
	public boolean canRiderInteract()
	{
		return true;
	}

	private boolean interactOthersTamed()
	{
		if (this.isHamsterStanding() || !this.isSitting())
			this.setSitting(true);
		else if (this.isSitting())
			this.setSitting(false);
		this.isJumping = false;
		this.navigator.stop();

		return true;
	}

	@Override
	public void heal(float f)
	{
		super.heal(f);
		this.hurtResistantTime = this.maxHurtResistantTime / 2;
	}

	@Override
	public boolean canBeCollidedWith()
	{

		if (this.getRidingEntity() != null && this.getRidingEntity() instanceof Player)
		{
			Player player = (Player) this.getRidingEntity();
			ItemStack itemstack = player.inventory.getCurrentItem();
			if (itemstack != null)
				return false;
		}

		return super.canBeCollidedWith();
	}

	@Override
	public boolean isEntityInsideOpaqueBlock()
	{

		if (this.getRidingEntity() != null)
			return false;
		else
			return super.isEntityInsideOpaqueBlock();

	}

	@Override
	protected void jump()
	{
		this.motionY = 0.29999999999999999D;
	}

	@Override
	public boolean canPassengerSteer()
	{
		return false;
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateCommon(this);

		this.setResourceLoc();
		super.onLivingUpdate();

		if (this.getHealth() < 10)
		{
			this.eatFood();
			this.eatCount = 5000;
		}
		if (!this.isHamsterStanding() && !this.isSitting() && !this.getSleeping())
		{
			if (this.rand.nextInt(20) == 0 && this.rand.nextInt(20) == 0)
			{
				this.setHamsterStanding(true);
				this.standCount = 30;
				this.navigator.stop();
				this.isJumping = false;
			}
		}
		else if (this.isHamsterStanding() && this.standCount-- <= 0 && this.rand.nextInt(10) == 0)
			this.setHamsterStanding(false);
		if (this.getFoodStackCount() > 0)
			if (this.eatCount == 0)
			{
				if (this.rand.nextInt(30) == 0 && this.rand.nextInt(30) == 0)
				{
					this.eatFood();
					this.eatCount = 5000;
				}
			}
			else
				this.eatCount--;
		this.looksWithInterest = false;
		if (!this.hasPath())
		{
			Entity entity = this.getAttackTarget();
			if (entity instanceof Player Player)
			{
				ItemStack itemstack = Player.inventory.getCurrentItem();
				if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.WHEAT_SEEDS)
					this.looksWithInterest = true;
			}
		}

		if (this.isSitting() | this.isHamsterStanding() && this.getNavigation() != null)
			this.getNavigation().stop();

		if (this.tamedTimer > -1)
		{
			this.tamedTimer--;
			if (this.tamedTimer == 0)
			{
				this.tamedTimer = 120;

				if (this.isTamed() && AnimaniaConfig.gameRules.showUnhappyParticles && !this.isPassenger())
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

	}

	@Override
	public EntityDataAccessor<Boolean> getWateredParam()
	{
		return WATERED;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.field_25054_c = this.field_25048_b;
		if (this.looksWithInterest)
			this.field_25048_b = this.field_25048_b + (1.0F - this.field_25048_b) * 0.4F;
		else
			this.field_25048_b = this.field_25048_b + (0.0F - this.field_25048_b) * 0.4F;
		if (this.looksWithInterest)
		{
			// numTicksToChaseTarget = 10;
		}
		if (this.rand.nextInt(10) == 5)
			this.ticksExisted++;

		// Animania.mDebug("levelObj.isClientSide="+levelObj.isClientSide+"
		// getX()="+getX()+" getY()="+getY()+" poxZ="+getZ());
	}

	public float getInterestedAngle(float f)
	{
		return (this.field_25054_c + (this.field_25048_b - this.field_25054_c) * f) * 0.15F * 3.141593F;
	}

	@Nullable
	public UUID getHamsterOwner()
	{
		return (UUID) ((Optional) this.entityData.get(TameableEntity.OWNER_UNIQUE_ID)).orNull();
	}

	public void setHamsterOwner(@Nullable UUID uniqueId)
	{
		this.entityData.set(TameableEntity.OWNER_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	public boolean isInBall()
	{
		return this.getBoolFromDataManager(IN_BALL);
	}

	public void setInBall(boolean ball)
	{
		if (ball)
		{
			this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.30F, 1.6F);
			this.entityData.set(EntityHamster.IN_BALL, true);
		}
		else
		{
			this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.30F, 1.3F);
			this.entityData.set(EntityHamster.IN_BALL, false);
		}
	}

	public int getBallColor()
	{
		return this.getIntFromDataManager(BALL_COLOR);
	}

	public void setBallColor(int color)
	{
		this.entityData.set(EntityHamster.BALL_COLOR, Integer.valueOf(color));
	}

	// public boolean isHamsterSitting()
	// {
	// return this.getBoolFromDataManager(SITTING);
	// }
	//
	// public void setHamsterSitting(boolean flag)
	// {
	// this.entityData.set(EntityHamster.SITTING, Boolean.valueOf(flag));
	// }

	void showHeartsOrSmokeFX(String s, int i, boolean flag)
	{
		for (int j = 0; j < i; j++)
		{
			double d = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;

			if (this.rand.nextInt(2) > 0)
				this.level.playSound(null, this.getX(), this.getY() + 1, this.getZ(), ExtraAddonSoundHandler.hamsterEat1, SoundCategory.PLAYERS, 0.6F, 0.8F);
			else
				this.level.playSound(null, this.getX(), this.getY() + 1, this.getZ(), ExtraAddonSoundHandler.hamsterEat2, SoundCategory.PLAYERS, 0.6F, 0.8F);
		}

	}

	// public boolean getIsTamed()
	// {
	// return this.getBoolFromDataManager(TAMED);
	// }

	// public void setIsTamed(boolean tamed)
	// {
	// if (tamed)
	// this.entityData.set(EntityHamster.TAMED, true);
	// else
	// this.entityData.set(EntityHamster.TAMED, false);
	// }

	public boolean getIsRiding()
	{
		return this.getBoolFromDataManager(RIDING);
	}

	public void setIsRiding(boolean riding)
	{
		this.entityData.set(EntityHamster.RIDING, Boolean.valueOf(riding));
	}

	public boolean isHamsterStanding()
	{
		return this.isStanding;
	}

	public void setHamsterStanding(boolean flag)
	{
		this.isStanding = flag;
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		if (this.isInBall())
		{
			int color = this.getBallColor();
			if (color == 16)
				this.entityDropItem(new ItemStack(ExtraAddonItemHandler.hamsterBallClear), 0f);
			else
				this.entityDropItem(new ItemStack(ExtraAddonItemHandler.hamsterBallColored, 1, color), 0f);

		}
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return new ResourceLocation("extra/" + Animania.MODID, "hamster");
	}

	public int getFoodStackCount()
	{
		return this.foodStackCount;
	}

	public void setFoodStackCount(int i)
	{
		this.foodStackCount = i;
	}

	public int getColorNumber()
	{
		try
		{
			// System.out.print(this.getIntFromDataManager(COLOR_NUM));
			return this.getIntFromDataManager(COLOR_NUM);
		}
		catch (Exception e)
		{
			// System.out.print(e);
			return this.getRandom().nextInt(8);
		}
	}

	public void setColorNumber(int color)
	{
		this.entityData.set(EntityHamster.COLOR_NUM, Integer.valueOf(color));
	}

	private boolean addFoodStack()
	{
		if (this.foodStackCount != 5)
		{
			this.foodStackCount++;
			return true;
		}
		else
		{
			this.heal(1);
			return false;
		}
	}

	private boolean eatFood()
	{
		if (this.foodStackCount != 0)
		{
			this.foodStackCount--;
			this.heal(1);
			return true;
		}
		else
			return false;
	}

	@Override
	public AgeableEntity createChild(AgeableEntity AnimalEntity)
	{
		return null;
	}

	@Override
	public double getMountedYOffset()
	{

		return this.height;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, stack);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, ExtraAddonSoundHandler.hamsterLiving1, ExtraAddonSoundHandler.hamsterLiving2, ExtraAddonSoundHandler.hamsterLiving3);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return ExtraAddonSoundHandler.hamsterHurt1;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ExtraAddonSoundHandler.hamsterHurt1;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume() - .2F, this.getSoundPitch());
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.8F);
	}

	private void doPatreonCheck(Player player)
	{
		if (player.isSneaking() && PatreonHandler.isPlayerPatreon(player))
		{
			this.setColorNumber(8);
			this.resourceLocation = null;
		}
	}

	public void setResourceLoc()
	{

		if (this.getColorNumber() == 9)
		{
			this.setColorNumber(8);
		}
		else if (this.getColorNumber() > 9)
		{
			this.setColorNumber(0);
		}

		if (this.resourceLocation == null)
		{
			this.resourceLocation = new ResourceLocation("animania:textures/entity/rodents/hamster_" + EntityHamster.HAMSTER_TEXTURES[this.getColorNumber()] + ".png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/rodents/hamster_" + EntityHamster.HAMSTER_TEXTURES[this.getColorNumber()] + "_blink.png");
		}
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(HamsterType.STANDARD, EntityGender.NONE));
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		return new ItemStack(this.getSpawnEgg());
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 14603464;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 14317391;
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
		return HamsterType.STANDARD;
	}

	@Override
	public EntityDataAccessor<Boolean> getHandFedParam()
	{
		return null;
	}

	@Override
	public EntityDataAccessor<Boolean> getFedParam()
	{
		return FED;
	}

}
