package com.animania.addons.extra.common.entity.rodents;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;
import com.animania.addons.extra.common.entity.rodents.ai.EntityAILookIdleRodent;
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

import net.minecraft.block.Block;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkRegistry;

public class EntityHamster extends TameableEntity implements TOPInfoProviderRodent, IAnimaniaAnimalBase
{
	private static final DataParameter<Boolean> IN_BALL = EntityDataManager.<Boolean> createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	// private static final DataParameter<Boolean> SITTING =
	// EntityDataManager.<Boolean>createKey(EntityHamster.class,
	// DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> RIDING = EntityDataManager.<Boolean> createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	// private static final DataParameter<Boolean> TAMED =
	// EntityDataManager.<Boolean>createKey(EntityHamster.class,
	// DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer> createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> FOOD_STACK_COUNT = EntityDataManager.<Integer> createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> IN_LOVE = EntityDataManager.<Integer> createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> BALL_COLOR = EntityDataManager.<Integer> createKey(EntityHamster.class, DataSerializers.VARINT);
	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(ExtraConfig.settings.hamsterFood));
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean> createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean> createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer> createKey(EntityHamster.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean> createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float> createKey(EntityHamster.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> INTERACTED = EntityDataManager.<Boolean> createKey(EntityHamster.class, DataSerializers.BOOLEAN);

	private static final String[] HAMSTER_TEXTURES = new String[] { "black", "brown", "darkbrown", "darkgray", "gray", "plum", "tarou", "white", "gold" };

	private int fedTimer;
	private int wateredTimer;
	private int happyTimer;
	private int tamedTimer;
	public int blinkTimer;
	private long rideCount;

	private int stackCount;
	private int eatCount;
	private int foodStackCount;
	private int standCount;
	private EntityItem targetFood;
	private boolean looksWithInterest;
	private boolean isStanding;
	private float field_25048_b;
	private float field_25054_c;
	private static List hamsterColorList;
	private PlayerEntity givemeEntity;
	private int breeding;
	private boolean mountFlag;
	private double yOffset;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	private int damageTimer;

	public EntityHamster(World world)
	{
		super(world);
		this.setHealth(6);
		this.yOffset = 0.1F;
		this.setSize(0.5F, 0.3F);
		this.width = 0.5F;
		this.height = 0.3F;
		this.stepHeight = 1.0F;
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = (AnimaniaConfig.careAndFeeding.waterTimer * 4) + this.rand.nextInt(200);
		this.looksWithInterest = false;
		this.stackCount = 20;
		this.eatCount = 5000;
		this.standCount = 30;
		this.isStanding = false;
		this.breeding = 0;
		this.happyTimer = 60;
		this.tamedTimer = 120;
		this.blinkTimer = 70 + this.rand.nextInt(70);
		this.enablePersistence();

		this.initAI();
	}

	protected void initAI()
	{

		this.tasks.addTask(1, new GenericAIPanic<EntityHamster>(this, 1.4D));
		this.tasks.addTask(2, new GenericAISwimmingSmallCreatures(this));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.tasks.addTask(3, new GenericAIFindWater<EntityHamster>(this, 1.0D, null, EntityHamster.class, true));
			this.tasks.addTask(3, new GenericAIFindFood<EntityHamster>(this, 1.0D, null, false));
		}
		this.tasks.addTask(4, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(5, new GenericAIWanderAvoidWater(this, 1.1D));
		this.tasks.addTask(6, new GenericAITempt<EntityHamster>(this, 1.2D, false, EntityHamster.TEMPTATION_ITEMS));
		this.tasks.addTask(7, new GenericAIFollowOwner<EntityHamster>(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(8, new GenericAIWatchClosest(this, PlayerEntity.class, 6.0F));
		this.tasks.addTask(9, new EntityAILookIdleRodent(this));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.tasks.addTask(10, new GenericAISleep(this, 0.8, Block.getBlockFromName(ExtraConfig.settings.hamsterBed), Block.getBlockFromName(ExtraConfig.settings.hamsterBed2), EntityHamster.class, new Function<Long, Boolean>() {

				@Override
				public Boolean apply(Long worldtime)
				{		
					return (worldtime < 13000);
				}
				
			}));
		}
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false, new Class[0]));

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
	public void setInLove(PlayerEntity player)
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
		this.dataManager.register(EntityHamster.IN_BALL, false);
		// this.dataManager.register(EntityHamster.SITTING, false);
		// this.dataManager.register(EntityHamster.TAMED, false);
		this.dataManager.register(EntityHamster.COLOR_NUM, Integer.valueOf(this.getRandom().nextInt(8)));
		this.dataManager.register(EntityHamster.FOOD_STACK_COUNT, Integer.valueOf(0));
		this.dataManager.register(EntityHamster.IN_LOVE, Integer.valueOf(0));
		this.dataManager.register(EntityHamster.BALL_COLOR, Integer.valueOf(0));
		this.dataManager.register(EntityHamster.FED, true);
		this.dataManager.register(EntityHamster.WATERED, true);
		this.dataManager.register(EntityHamster.RIDING, false);
		this.dataManager.register(EntityHamster.AGE, Integer.valueOf(0));
		this.dataManager.register(EntityHamster.SLEEPING, false);
		this.dataManager.register(EntityHamster.SLEEPTIMER, Float.valueOf(0.0F));
		this.dataManager.register(INTERACTED, false);
	}

	@Override
	public void writeEntityToNBT(CompoundNBT CompoundNBT)
	{
		super.writeEntityToNBT(CompoundNBT);
		CompoundNBT.putBoolean("IsSitting", this.isSitting());
		CompoundNBT.putBoolean("InBall", this.isInBall());
		CompoundNBT.putInteger("ColorNumber", this.getColorNumber());
		CompoundNBT.putInteger("foodStackCount", this.getFoodStackCount());
		CompoundNBT.putInteger("BallColor", this.getBallColor());
		CompoundNBT.putBoolean("IsTamed", this.isTamed());
		CompoundNBT.putBoolean("IsRiding", this.getIsRiding());

		GenericBehavior.writeCommonNBT(CompoundNBT, this);

	}

	@Override
	public void readEntityFromNBT(CompoundNBT CompoundNBT)
	{
		super.readEntityFromNBT(CompoundNBT);
		this.setSitting(CompoundNBT.getBoolean("IsSitting"));
		this.setInBall(CompoundNBT.getBoolean("InBall"));
		this.setColorNumber(CompoundNBT.getInteger("ColorNumber"));
		this.setFoodStackCount(CompoundNBT.getInteger("foodStackCount"));
		this.setBallColor(CompoundNBT.getInteger("BallColor"));
		this.setTamed(CompoundNBT.getBoolean("IsTamed"));
		this.setIsRiding(CompoundNBT.getBoolean("IsRiding"));

		GenericBehavior.readCommonNBT(CompoundNBT, this);
	}

	@Override
	public DataParameter<Integer> getAgeParam()
	{
		return AGE;
	}

	@Override
	public DataParameter<Boolean> getSleepingParam()
	{
		return SLEEPING;
	}

	@Override
	public DataParameter<Float> getSleepTimerParam()
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
	public boolean processInteract(PlayerEntity player, EnumHand hand)
	{
		ItemStack itemstack = player.getHeldItem(hand);

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
				props.setAnimal(this.writeToNBT(new CompoundNBT()));
				props.setCarrying(true);
				props.setType("hamster");
				this.setDead();
				player.swingArm(EnumHand.MAIN_HAND);
				if(!player.level.isRemote)
					Animania.network.sendToAllAround(new CapSyncPacket(props, player.getEntityId()), new NetworkRegistry.TargetPoint(player.level.provider.getDimension(), player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 64));
				return true;
			}

		}

		if (!itemstack.isEmpty() && itemstack.getItem() == ExtraAddonItemHandler.hamsterBallColored && !isInBall() && !this.getSleeping())
		{
			if (this.isSitting())
			{
				this.setSitting(false);
			}
			setInBall(true);
			int meta = itemstack.getMetadata();
			setBallColor(meta);
			if (!player.isCreative())
				itemstack.shrink(1);
			return true;
		}
		if (!itemstack.isEmpty() && itemstack.getItem() == ExtraAddonItemHandler.hamsterBallClear && !isInBall() && !this.getSleeping())
		{
			if (this.isSitting())
			{
				this.setSitting(false);
				this.setSitting(false);
			}
			setInBall(true);
			int meta = itemstack.getMetadata();
			setBallColor(16);
			if (!player.isCreative())
				itemstack.shrink(1);
			return true;
		} else if (itemstack.isEmpty() && isInBall() && !this.getSleeping())
		{
			int color = this.getBallColor();
			setInBall(false);
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

		if (this.getRidingEntity() != null && this.getRidingEntity() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) this.getRidingEntity();
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
		} else if (this.isHamsterStanding() && this.standCount-- <= 0 && this.rand.nextInt(10) == 0)
			this.setHamsterStanding(false);
		if (this.getFoodStackCount() > 0)
			if (this.eatCount == 0)
			{
				if (this.rand.nextInt(30) == 0 && this.rand.nextInt(30) == 0)
				{
					this.eatFood();
					this.eatCount = 5000;
				}
			} else
				this.eatCount--;
		this.looksWithInterest = false;
		if (!this.hasPath())
		{
			Entity entity = this.getAttackTarget();
			if (entity instanceof PlayerEntity)
			{
				PlayerEntity PlayerEntity = (PlayerEntity) entity;
				ItemStack itemstack = PlayerEntity.inventory.getCurrentItem();
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

				if (this.isTamed() && AnimaniaConfig.gameRules.showUnhappyParticles && !this.isRiding())
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
	public DataParameter<Boolean> getWateredParam()
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

		// Animania.mDebug("worldObj.isRemote="+worldObj.isRemote+"
		// getX()="+getX()+" getY()="+getY()+" poxZ="+getZ());
	}

	public float getInterestedAngle(float f)
	{
		return (this.field_25054_c + (this.field_25048_b - this.field_25054_c) * f) * 0.15F * 3.141593F;
	}

	@Nullable
	public UUID getHamsterOwner()
	{
		return (UUID) ((Optional) this.dataManager.get(TameableEntity.OWNER_UNIQUE_ID)).orNull();
	}

	public void setHamsterOwner(@Nullable UUID uniqueId)
	{
		this.dataManager.set(TameableEntity.OWNER_UNIQUE_ID, Optional.fromNullable(uniqueId));
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
			this.dataManager.set(EntityHamster.IN_BALL, true);
		} else
		{
			this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.30F, 1.3F);
			this.dataManager.set(EntityHamster.IN_BALL, false);
		}
	}

	public int getBallColor()
	{
		return this.getIntFromDataManager(BALL_COLOR);
	}

	public void setBallColor(int color)
	{
		this.dataManager.set(EntityHamster.BALL_COLOR, Integer.valueOf(color));
	}

	// public boolean isHamsterSitting()
	// {
	// return this.getBoolFromDataManager(SITTING);
	// }
	//
	// public void setHamsterSitting(boolean flag)
	// {
	// this.dataManager.set(EntityHamster.SITTING, Boolean.valueOf(flag));
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
	// this.dataManager.set(EntityHamster.TAMED, true);
	// else
	// this.dataManager.set(EntityHamster.TAMED, false);
	// }

	public boolean getIsRiding()
	{
		return this.getBoolFromDataManager(RIDING);
	}

	public void setIsRiding(boolean riding)
	{
		this.dataManager.set(EntityHamster.RIDING, Boolean.valueOf(riding));
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
			return (this.getIntFromDataManager(COLOR_NUM));
		} catch (Exception e)
		{
			// System.out.print(e);
			return this.getRandom().nextInt(8);
		}
	}

	public void setColorNumber(int color)
	{
		this.dataManager.set(EntityHamster.COLOR_NUM, Integer.valueOf(color));
	}

	private boolean addFoodStack()
	{
		if (this.foodStackCount != 5)
		{
			this.foodStackCount++;
			return true;
		} else
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
		} else
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

	private void doPatreonCheck(PlayerEntity player)
	{
		if (player.isSneaking())
		{
			if (PatreonHandler.isPlayerPatreon(player))
			{
				this.setColorNumber(8);
				this.resourceLocation = null;
			}
		}
	}

	public void setResourceLoc()
	{

		if (this.getColorNumber() == 9)
		{
			this.setColorNumber(8);
		} else if (this.getColorNumber() > 9)
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
		return new ItemStack(getSpawnEgg());
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
		return blinkTimer;
	}

	@Override
	public void setBlinkTimer(int i)
	{
		blinkTimer = i;
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
		return fedTimer;
	}

	@Override
	public void setFedTimer(int i)
	{
		fedTimer = i;
	}

	@Override
	public DataParameter<Boolean> getInteractedParam()
	{
		return INTERACTED;
	}

	@Override
	public int getWaterTimer()
	{
		return wateredTimer;
	}

	@Override
	public void setWaterTimer(int i)
	{
		wateredTimer = i;
	}

	@Override
	public int getDamageTimer()
	{
		return damageTimer;
	}

	@Override
	public void setDamageTimer(int i)
	{
		damageTimer = i;
	}

	@Override
	public int getHappyTimer()
	{
		return happyTimer;
	}

	@Override
	public void setHappyTimer(int i)
	{
		happyTimer = i;
	}

	@Override
	public AnimaniaType getAnimalType()
	{
		return HamsterType.STANDARD;
	}

	@Override
	public DataParameter<Boolean> getHandFedParam()
	{
		return null;
	}

	@Override
	public DataParameter<Boolean> getFedParam()
	{
		return FED;
	}

}
