package com.animania.common.entities.rodents;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.ModSoundEvents;
import com.animania.common.capabilities.CapabilityRefs;
import com.animania.common.capabilities.ICapabilityPlayer;
import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.generic.ai.GenericAIAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAIFollowOwner;
import com.animania.common.entities.generic.ai.GenericAIHurtByTarget;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISwimmingSmallCreatures;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.entities.interfaces.IAnimaniaAnimalBase;
import com.animania.common.entities.rodents.ai.EntityAILookIdleRodent;
import com.animania.common.entities.rodents.ai.EntityAISleepHamsters;
import com.animania.common.handler.ItemHandler;
import com.animania.common.handler.PatreonHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.compat.top.providers.entity.TOPInfoProviderRodent;
import com.animania.config.AnimaniaConfig;
import com.animania.network.client.CapSyncPacket;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class EntityHamster extends EntityTameable implements TOPInfoProviderRodent, IAnimaniaAnimalBase
{
	private static final DataParameter<Boolean> IN_BALL = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SITTING = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> RIDING = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer>createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> FOOD_STACK_COUNT = EntityDataManager.<Integer>createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> IN_LOVE = EntityDataManager.<Integer>createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> BALL_COLOR = EntityDataManager.<Integer>createKey(EntityHamster.class, DataSerializers.VARINT);
	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.hamsterFood));
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> AGE = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float>createKey(EntityHamster.class, DataSerializers.FLOAT);
	private static final String[] HAMSTER_TEXTURES = new String[] { "black", "brown", "darkbrown", "darkgray", "gray", "plum", "tarou", "white", "gold" };

	private int fedTimer;
	private int wateredTimer;
	private int happyTimer;
	private int tamedTimer;
	public int blinkTimer;
	private long rideCount;
	private int delayCount;

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
	private EntityPlayer givemeEntity;
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
		this.delayCount = 5;
		this.enablePersistence();
	}

	@Override
	protected void initEntityAI()
	{

		this.tasks.addTask(1, new GenericAIPanic(this, 1.4D));
		this.tasks.addTask(2, new GenericAISwimmingSmallCreatures(this));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.tasks.addTask(3, new GenericAIFindWater(this, 1.0D, null, EntityHamster.class, true));
			this.tasks.addTask(4, new GenericAIFindFood(this, 1.0D, null, false));
		}
		this.tasks.addTask(5, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(6, new GenericAIWanderAvoidWater(this, 1.1D));
		this.tasks.addTask(7, new GenericAITempt(this, 1.2D, false, EntityHamster.TEMPTATION_ITEMS));
		this.tasks.addTask(8, new GenericAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(9, new GenericAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(10, new EntityAILookIdleRodent(this));
		this.tasks.addTask(11, new GenericAIAvoidWater(this));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.tasks.addTask(12, new EntityAISleepHamsters(this, 0.8));
		}
		this.targetTasks.addTask(13, new GenericAIHurtByTarget(this, false, new Class[0]));

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
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		this.setOwnerId(player.getPersistentID());
		this.setIsTamed(true);
		this.setTamed(true);
		this.doPatreonCheck(player);
		this.setInLove(player);

		if (!player.capabilities.isCreativeMode)
			stack.setCount(stack.getCount() - 1);
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
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
		this.dataManager.register(EntityHamster.IN_BALL, Boolean.valueOf(false));
		this.dataManager.register(EntityHamster.SITTING, Boolean.valueOf(false));
		this.dataManager.register(EntityHamster.TAMED, Boolean.valueOf(false));
		this.dataManager.register(EntityHamster.COLOR_NUM, Integer.valueOf(this.getRNG().nextInt(8)));
		this.dataManager.register(EntityHamster.FOOD_STACK_COUNT, Integer.valueOf(0));
		this.dataManager.register(EntityHamster.IN_LOVE, Integer.valueOf(0));
		this.dataManager.register(EntityHamster.BALL_COLOR, Integer.valueOf(0));
		this.dataManager.register(EntityHamster.FED, Boolean.valueOf(true));
		this.dataManager.register(EntityHamster.WATERED, Boolean.valueOf(true));
		this.dataManager.register(EntityHamster.RIDING, Boolean.valueOf(false));
		this.dataManager.register(EntityHamster.AGE, Boolean.valueOf(false));
		this.dataManager.register(EntityHamster.SLEEPING, Boolean.valueOf(false));
		this.dataManager.register(EntityHamster.SLEEPTIMER, Float.valueOf(0.0F));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setBoolean("IsSitting", this.isHamsterSitting());
		nbttagcompound.setBoolean("InBall", this.isInBall());
		nbttagcompound.setInteger("ColorNumber", this.getColorNumber());
		nbttagcompound.setInteger("foodStackCount", this.getFoodStackCount());
		nbttagcompound.setInteger("BallColor", this.getBallColor());
		nbttagcompound.setBoolean("Fed", this.getFed());
		nbttagcompound.setBoolean("Watered", this.getWatered());
		nbttagcompound.setBoolean("IsTamed", this.getIsTamed());
		nbttagcompound.setBoolean("IsRiding", this.getIsRiding());
		nbttagcompound.setBoolean("Age", this.getAge());
		nbttagcompound.setBoolean("Sleep", this.getSleeping());
		nbttagcompound.setFloat("SleepTimer", this.getSleepTimer());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		this.setHamsterSitting(nbttagcompound.getBoolean("IsSitting"));
		this.setInBall(nbttagcompound.getBoolean("InBall"));
		this.setColorNumber(nbttagcompound.getInteger("ColorNumber"));
		this.setFoodStackCount(nbttagcompound.getInteger("foodStackCount"));
		this.setBallColor(nbttagcompound.getInteger("BallColor"));
		this.setFed(nbttagcompound.getBoolean("Fed"));
		this.setWatered(nbttagcompound.getBoolean("Watered"));
		this.setIsTamed(nbttagcompound.getBoolean("IsTamed"));
		this.setIsRiding(nbttagcompound.getBoolean("IsRiding"));
		this.setAge(nbttagcompound.getBoolean("Age"));
		this.setSleeping(nbttagcompound.getBoolean("Sleep"));
		this.setSleepTimer(nbttagcompound.getFloat("SleepTimer"));
	}

	public boolean getAge()
	{
		try
		{
			return (this.getBoolFromDataManager(AGE));
		}
		catch (Exception e)
		{
			return false;
		}

	}

	public void setAge(boolean age)
	{
		this.dataManager.set(EntityHamster.AGE, Boolean.valueOf(age));
	}

	public boolean getSleeping()
	{
		try
		{
			return (this.getBoolFromDataManager(SLEEPING));
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public void setSleeping(boolean flag)
	{
		if (flag)
		{
			this.dataManager.set(EntityHamster.SLEEPING, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(EntityHamster.SLEEPING, Boolean.valueOf(false));
		}
	}

	public Float getSleepTimer()
	{
		try
		{
			return (this.getFloatFromDataManager(SLEEPTIMER));
		}
		catch (Exception e)
		{
			return 0F;
		}
	}

	public void setSleepTimer(Float timer)
	{
		this.dataManager.set(EntityHamster.SLEEPTIMER, Float.valueOf(timer));
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
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{

		ItemStack itemstack = player.getHeldItem(hand);

		if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.NAME_TAG && delayCount == 0 && !this.getSleeping())
		{
			delayCount = 5;
			if (!itemstack.hasDisplayName())
			{
				return false;

			}
			else
			{
				EntityLiving entityliving = this;
				entityliving.setCustomNameTag(itemstack.getDisplayName());
				entityliving.enablePersistence();
				if (!player.capabilities.isCreativeMode)
					itemstack.setCount(itemstack.getCount() - 1);
				this.setIsTamed(true);
				this.setTamed(true);
				this.setOwnerId(player.getPersistentID());
				return true;
			}
		}

		if (itemstack != ItemStack.EMPTY && this.isBreedingItem(itemstack) && delayCount == 0 && !this.getSleeping())
		{
			delayCount = 5;
			if (!player.capabilities.isCreativeMode)
			{
				itemstack.shrink(1);

				if (itemstack.getCount() <= 0)
				{
					player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
				}
			}
			this.setInLove(player);
			this.setFed(true);
			this.setIsTamed(true);
			this.setTamed(true);
			this.setOwnerId(player.getPersistentID());
			this.doPatreonCheck(player);
			return true;

		}
		else if (itemstack != ItemStack.EMPTY && AnimaniaHelper.isWaterContainer(itemstack) && delayCount == 0 && !this.getSleeping())
		{
			if (!player.isCreative())
			{
				ItemStack emptied = AnimaniaHelper.emptyContainer(itemstack);
				itemstack.shrink(1);
				AnimaniaHelper.addItem(player, emptied);
			}

			delayCount = 5;
			this.setWatered(true);
			this.setInLove(player);
			return true;
		}
		else if (itemstack == ItemStack.EMPTY && this.isTamed() && !this.isHamsterSitting() && !player.isSneaking() && delayCount == 0 && !this.isInBall() && !this.getSleeping())
		{
			delayCount = 5;
			this.setHamsterSitting(true);
			this.setSitting(true);
			this.isJumping = false;
			return true;

		}
		else if (itemstack == ItemStack.EMPTY && this.isTamed() && this.isHamsterSitting() && !player.isSneaking() && delayCount == 0 && !this.isInBall() && !this.getSleeping())
		{
			delayCount = 5;
			this.setHamsterSitting(false);
			this.setSitting(false);
			this.isJumping = false;
			return true;
		}
		else if (itemstack == ItemStack.EMPTY && this.isTamed() && player.isSneaking() && delayCount == 0 && !this.isInBall() && !this.getSleeping())
		{

			ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
			if (!props.isCarrying())
			{
				props.setAnimal(this.writeToNBT(new NBTTagCompound()));
				props.setCarrying(true);
				props.setType("hamster");
				this.setDead();
				player.swingArm(EnumHand.MAIN_HAND);
				Animania.network.sendToAllAround(new CapSyncPacket(props, player.getEntityId()), new NetworkRegistry.TargetPoint(player.world.provider.getDimension(), player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 64));
				return true;
			}

		}

		if (!itemstack.isEmpty() && itemstack.getItem() == ItemHandler.hamsterBallColored && !isInBall() && !this.getSleeping())
		{
			if (this.isHamsterSitting())
			{
				this.setHamsterSitting(false);
				this.setSitting(false);
			}
			setInBall(true);
			int meta = itemstack.getMetadata();
			setBallColor(meta);
			if (!player.isCreative())
				itemstack.shrink(1);
			return true;
		}
		if (!itemstack.isEmpty() && itemstack.getItem() == ItemHandler.hamsterBallClear && !isInBall() && !this.getSleeping())
		{
			if (this.isHamsterSitting())
			{
				this.setHamsterSitting(false);
				this.setSitting(false);
			}
			setInBall(true);
			int meta = itemstack.getMetadata();
			setBallColor(16);
			if (!player.isCreative())
				itemstack.shrink(1);
			return true;
		}
		else if (itemstack.isEmpty() && isInBall() && !this.getSleeping())
		{
			int color = this.getBallColor();
			setInBall(false);
			if (!player.isCreative())
			{
				if (color == 16)
					AnimaniaHelper.addItem(player, new ItemStack(ItemHandler.hamsterBallClear));
				else
					AnimaniaHelper.addItem(player, new ItemStack(ItemHandler.hamsterBallColored, 1, color));
			}
			return true;
		}

		if (!this.getIsTamed())
		{
			if (itemstack != ItemStack.EMPTY && itemstack.getItem() == ItemHandler.hamsterFood)
			{
				this.addFoodStack();
				return this.interactSeedsNotTamed(itemstack, player);
			}
			else
				return super.processInteract(player, hand);
		}
		else if (!this.getIsTamed() && itemstack.getItem() == Items.LEAD)
			return super.processInteract(player, hand);

		if (itemstack != ItemStack.EMPTY && itemstack.getItem() == ItemHandler.hamsterFood)
		{
			addFoodStack();
			// player.addStat(AnimaniaAchievements.Hamsters, 1);
			return interactSeedsTamed(itemstack, player);
		}
		else if (this.isBreedingItem(itemstack))
		{
			this.consumeItemFromStack(player, itemstack);
			this.setInLove(player);
			return true;
		}
		return super.processInteract(player, hand);

	}

	private boolean interactSeedsNotTamed(ItemStack itemstack, EntityPlayer entityplayer)
	{

		if (!entityplayer.capabilities.isCreativeMode)
			itemstack.shrink(1);
		// entityplayer.addStat(AnimaniaAchievements.Hamsters, 1);
		this.setHamsterStanding(true);
		this.standCount = 100;

		this.doPatreonCheck(entityplayer);

		if (itemstack.getCount() <= 0 && entityplayer.inventory != null)
			entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, ItemStack.EMPTY);

		if (this.rand.nextInt(3) == 0)
		{
			this.navigator.clearPath();
			this.isJumping = false;
			// TODO entityToAttack = null;
			// showHeartsOrSmokeFX("note", 1, false);
			this.world.setEntityState(this, (byte) 7);

		}
		else
			// showHeartsOrSmokeFX("note", 1, false);
			this.world.setEntityState(this, (byte) 7);
		this.heal(1);

		return true;
	}

	private boolean interactSeedsTamed(ItemStack itemstack, EntityPlayer entityplayer)
	{
		if (!entityplayer.capabilities.isCreativeMode)
			itemstack.shrink(1);
		if (itemstack.getCount() <= 0)
			entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, ItemStack.EMPTY);

		this.heal(1);
		return true;
	}

	@Override
	public boolean canRiderInteract()
	{
		return true;
	}

	private boolean interactOthersTamed()
	{
		if (this.isHamsterStanding() || !this.isHamsterSitting())
			this.setHamsterSitting(true);
		else if (this.isHamsterSitting())
			this.setHamsterSitting(false);
		this.isJumping = false;
		this.navigator.clearPath();

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

		if (this.getRidingEntity() != null && this.getRidingEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) this.getRidingEntity();
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
		if (!this.getAge())
		{
			this.setAge(true);
		}

		delayCount--;
		if (delayCount <= 0)
		{
			delayCount = 0;
		}

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
				this.blinkTimer = 80 + this.rand.nextInt(80);
		}

		this.setResourceLoc();
		super.onLivingUpdate();

		if (this.getHealth() < 10)
		{
			this.eatFood();
			this.eatCount = 5000;
		}
		if (!this.isHamsterStanding() && !this.isHamsterSitting() && !this.getSleeping())
		{
			if (this.rand.nextInt(20) == 0 && this.rand.nextInt(20) == 0)
			{
				this.setHamsterStanding(true);
				this.standCount = 30;
				this.navigator.clearPath();
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
			if (entity instanceof EntityPlayer)
			{
				EntityPlayer entityplayer = (EntityPlayer) entity;
				ItemStack itemstack = entityplayer.inventory.getCurrentItem();
				if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.WHEAT_SEEDS)
					this.looksWithInterest = true;
			}
		}

		if (this.isHamsterSitting() | this.isHamsterStanding() && this.getNavigator() != null)
			this.getNavigator().clearPath();

		if (this.fedTimer > -1 && !AnimaniaConfig.gameRules.ambianceMode)
		{
			this.fedTimer--;

			if (this.fedTimer == 0)
				this.setFed(false);
		}

		if (this.wateredTimer > -1)
		{
			this.wateredTimer--;

			if (this.wateredTimer == 0 && !AnimaniaConfig.gameRules.ambianceMode)
			{
				this.setWatered(false);
			}
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();

		if (!fed && !watered)
		{
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
			if (AnimaniaConfig.gameRules.animalsStarve)
			{
				if (this.damageTimer >= AnimaniaConfig.careAndFeeding.starvationTimer)
				{
					this.attackEntityFrom(DamageSource.STARVE, 4f);
					this.damageTimer = 0;
				}
				this.damageTimer++;
			}

		}
		else if (!fed || !watered)
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));

		if (this.happyTimer > -1)
		{
			this.happyTimer--;
			if (this.happyTimer == 0)
			{
				this.happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && !this.getSleeping() && this.isTamed() && AnimaniaConfig.gameRules.showUnhappyParticles)
				{
					double d = this.rand.nextGaussian() * 0.001D;
					double d1 = this.rand.nextGaussian() * 0.001D;
					double d2 = this.rand.nextGaussian() * 0.001D;
					this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + this.rand.nextFloat() * this.width - this.width, this.posY + 1.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width - this.width, d, d1, d2);
				}
			}
		}

		if (this.tamedTimer > -1)
		{
			this.tamedTimer--;
			if (this.tamedTimer == 0)
			{
				this.tamedTimer = 120;

				if (this.getIsTamed() && AnimaniaConfig.gameRules.showUnhappyParticles && !this.isRiding())
				{
					double d = this.rand.nextGaussian() * 0.02D;
					double d1 = this.rand.nextGaussian() * 0.02D;
					double d2 = this.rand.nextGaussian() * 0.02D;
					// this.world.spawnParticle(EnumParticleTypes.HEART,
					// this.posX + this.rand.nextFloat() * this.width -
					// this.width, this.posY + 1D + this.rand.nextFloat() *
					// this.height, this.posZ + this.rand.nextFloat() *
					// this.width - this.width, d, d1, d2);
				}
			}
		}

	}

	public boolean getFed()
	{
		try
		{
			return (this.getBoolFromDataManager(FED));
		}
		catch (Exception e)
		{
			return false;
		}

	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityHamster.FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		}
		else
			this.dataManager.set(EntityHamster.FED, Boolean.valueOf(false));
	}

	public boolean getWatered()
	{
		try
		{
			return (this.getBoolFromDataManager(WATERED));
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(EntityHamster.WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		}
		else
			this.dataManager.set(EntityHamster.WATERED, Boolean.valueOf(false));
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
		// posX="+posX+" posY="+posY+" poxZ="+posZ);
	}

	public float getInterestedAngle(float f)
	{
		return (this.field_25054_c + (this.field_25048_b - this.field_25054_c) * f) * 0.15F * 3.141593F;
	}

	@Nullable
	public UUID getHamsterOwner()
	{
		return (UUID) ((Optional) this.dataManager.get(EntityTameable.OWNER_UNIQUE_ID)).orNull();
	}

	public void setHamsterOwner(@Nullable UUID uniqueId)
	{
		this.dataManager.set(EntityTameable.OWNER_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	public boolean isInBall()
	{
		try
		{
			return (this.getBoolFromDataManager(IN_BALL));
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public void setInBall(boolean ball)
	{
		if (ball)
		{
			this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.30F, 1.6F);
			this.dataManager.set(EntityHamster.IN_BALL, Boolean.valueOf(true));
		}
		else
		{
			this.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.30F, 1.3F);
			this.dataManager.set(EntityHamster.IN_BALL, Boolean.valueOf(false));
		}
	}

	public int getBallColor()
	{
		try
		{
			return (this.getIntFromDataManager(BALL_COLOR));
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	public void setBallColor(int color)
	{
		this.dataManager.set(EntityHamster.BALL_COLOR, Integer.valueOf(color));
	}

	public boolean isHamsterSitting()
	{
		try
		{
			return (this.getBoolFromDataManager(SITTING));
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public void setHamsterSitting(boolean flag)
	{
		this.dataManager.set(EntityHamster.SITTING, Boolean.valueOf(flag));
	}

	void showHeartsOrSmokeFX(String s, int i, boolean flag)
	{
		for (int j = 0; j < i; j++)
		{
			double d = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;

			if (this.rand.nextInt(2) > 0)
				this.world.playSound(null, this.posX, this.posY + 1, this.posZ, ModSoundEvents.hamsterEat1, SoundCategory.PLAYERS, 0.6F, 0.8F);
			else
				this.world.playSound(null, this.posX, this.posY + 1, this.posZ, ModSoundEvents.hamsterEat2, SoundCategory.PLAYERS, 0.6F, 0.8F);
		}

	}

	public boolean getIsTamed()
	{
		try
		{
			return (this.getBoolFromDataManager(TAMED));
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public void setIsTamed(boolean tamed)
	{
		if (tamed)
			this.dataManager.set(EntityHamster.TAMED, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityHamster.TAMED, Boolean.valueOf(false));
	}

	public boolean getIsRiding()
	{
		try
		{
			return (this.getBoolFromDataManager(RIDING));
		}
		catch (Exception e)
		{
			return false;
		}
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
				this.entityDropItem(new ItemStack(ItemHandler.hamsterBallClear), 0f);
			else
				this.entityDropItem(new ItemStack(ItemHandler.hamsterBallColored, 1, color), 0f);

		}
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return new ResourceLocation(Animania.MODID, "hamster");
	}

	private ItemStack getItem(String moditem)
	{

		ItemStack foundStack = null;
		String item = "";
		String mod = "";
		int sepLoc = 0;
		int metaLoc = 0;
		boolean metaFlag = false;
		String metaVal = "";

		sepLoc = moditem.indexOf(":");
		metaLoc = moditem.indexOf("#");

		if (!moditem.contains(":"))
		{
			return new ItemStack(Blocks.AIR, 1);
		}

		mod = moditem.substring(0, sepLoc);

		if (metaLoc > 0)
		{
			item = moditem.substring(sepLoc + 1, metaLoc);
		}
		else
		{
			item = moditem.substring(sepLoc + 1, moditem.length());
		}
		if (metaLoc > 0)
		{
			metaFlag = true;
			metaVal = moditem.substring(metaLoc + 1, moditem.length());
		}

		Item bob = Item.getByNameOrId(item);

		if (bob != null)
		{

			if (metaFlag)
			{
				foundStack = new ItemStack(bob, 1, Integer.parseInt(metaVal));
			}
			else
			{
				foundStack = new ItemStack(bob, 1);
			}
		}
		else
		{
			foundStack = new ItemStack(Blocks.AIR, 1);
		}

		return foundStack;
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
		}
		catch (Exception e)
		{
			// System.out.print(e);
			return this.getRNG().nextInt(8);
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
	public EntityAgeable createChild(EntityAgeable entityanimal)
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
		return stack != ItemStack.EMPTY && EntityHamster.TEMPTATION_ITEMS.contains(stack.getItem());
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		int happy = 0;
		int num = 1;

		if (this.getWatered())
			happy++;
		if (this.getFed())
			happy++;

		if (happy == 2)
			num = 6;
		else if (happy == 1)
			num = 12;
		else
			num = 24;

		int chooser = Animania.RANDOM.nextInt(num);

		if (chooser == 0)
			return ModSoundEvents.hamsterLiving1;
		else if (chooser == 1)
			return ModSoundEvents.hamsterLiving2;
		else if (chooser == 2)
			return ModSoundEvents.hamsterLiving3;
		else
			return null;

	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return ModSoundEvents.hamsterHurt1;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return null;
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

	private void doPatreonCheck(EntityPlayer player)
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

	// ==================================================
	// Data Manager Trapper (borrowed from Lycanites)
	// ==================================================

	public boolean getBoolFromDataManager(DataParameter<Boolean> key)
	{
		try
		{
			return this.getDataManager().get(key);
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public byte getByteFromDataManager(DataParameter<Byte> key)
	{
		try
		{
			return this.getDataManager().get(key);
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	public int getIntFromDataManager(DataParameter<Integer> key)
	{
		try
		{
			return this.getDataManager().get(key);
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	public float getFloatFromDataManager(DataParameter<Float> key)
	{
		try
		{
			return this.getDataManager().get(key);
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	public String getStringFromDataManager(DataParameter<String> key)
	{
		try
		{
			return this.getDataManager().get(key);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public Optional<UUID> getUUIDFromDataManager(DataParameter<Optional<UUID>> key)
	{
		try
		{
			return this.getDataManager().get(key);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public ItemStack getItemStackFromDataManager(DataParameter<ItemStack> key)
	{
		try
		{
			return this.getDataManager().get(key);
		}
		catch (Exception e)
		{
			return ItemStack.EMPTY;
		}
	}

	public Optional<BlockPos> getBlockPosFromDataManager(DataParameter<Optional<BlockPos>> key)
	{
		try
		{
			return this.getDataManager().get(key);
		}
		catch (Exception e)
		{
			return Optional.absent();
		}
	}

	@Override
	public void setHandFed(boolean handfed)
	{
		this.setFed(handfed);
	}

	@Override
	public boolean getHandFed()
	{
		return this.getFed();
	}

	@Override
	public Set<Item> getFoodItems()
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

}
