package com.animania.common.entities.rodents;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.animania.common.capabilities.CapabilityRefs;
import com.animania.common.capabilities.ICapabilityPlayer;
import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.ISpawnable;
import com.animania.common.entities.rodents.ai.EntityAIFindWater;
import com.animania.common.entities.rodents.ai.EntityAILookIdleRodent;
import com.animania.common.entities.rodents.ai.EntityAIPanicRodents;
import com.animania.common.entities.rodents.ai.EntityAISwimmingRodents;
import com.animania.common.entities.rodents.ai.EntityAITemptHamster;
import com.animania.common.entities.rodents.ai.EntityAIWatchClosestFromSide;
import com.animania.common.handler.ItemHandler;
import com.animania.common.handler.PatreonHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.common.items.ItemHamsterBall;
import com.animania.compat.top.providers.entity.TOPInfoProviderRodent;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
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

public class EntityHamster extends EntityTameable implements TOPInfoProviderRodent, ISpawnable
{
	private static final DataParameter<Boolean> IN_BALL = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SITTING = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> RIDING = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer>createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> FOOD_STACK_COUNT = EntityDataManager.<Integer>createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> IN_LOVE = EntityDataManager.<Integer>createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> BALL_COLOR = EntityDataManager.<Integer>createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] { ItemHandler.hamsterFood });
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
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
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
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
	}

	@Override
	protected void initEntityAI()
	{

		this.tasks.addTask(1, new EntityAIPanicRodents(this, 1.4D));
		this.tasks.addTask(2, new EntityAISwimmingRodents(this));
		this.tasks.addTask(3, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.1D));
		this.tasks.addTask(5, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(6, new EntityAITemptHamster(this, 1.2D, false, EntityHamster.TEMPTATION_ITEMS));
		this.tasks.addTask(2, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(8, new EntityAIWatchClosestFromSide(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(9, new EntityAILookIdleRodent(this));

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

		player.addStat(AnimaniaAchievements.Hamsters, 1);

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
		// System.out.println(x + "-" + y + "-" + z);
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
		Random rand = new Random();
		super.entityInit();
		this.dataManager.register(EntityHamster.IN_BALL, Boolean.valueOf(false));
		this.dataManager.register(EntityHamster.SITTING, Boolean.valueOf(false));
		this.dataManager.register(EntityHamster.TAMED, Boolean.valueOf(false));
		this.dataManager.register(EntityHamster.COLOR_NUM, Integer.valueOf(rand.nextInt(8)));
		this.dataManager.register(EntityHamster.FOOD_STACK_COUNT, Integer.valueOf(0));
		this.dataManager.register(EntityHamster.IN_LOVE, Integer.valueOf(0));
		this.dataManager.register(EntityHamster.BALL_COLOR, Integer.valueOf(0));
		this.dataManager.register(EntityHamster.FED, Boolean.valueOf(true));
		this.dataManager.register(EntityHamster.WATERED, Boolean.valueOf(true));
		this.dataManager.register(EntityHamster.RIDING, Boolean.valueOf(false));

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

		if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.NAME_TAG)
			if (!itemstack.hasDisplayName())
				return false;
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

		if (itemstack != ItemStack.EMPTY && this.isBreedingItem(itemstack) && this.getGrowingAge() == 0)
		{

			if (!player.capabilities.isCreativeMode)
			{
				itemstack.shrink(1);

				if (itemstack.getCount() <= 0)
					player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
			}

			player.addStat(AnimaniaAchievements.Hamsters, 1);
			this.setInLove(player);
			this.setFed(true);
			this.setIsTamed(true);
			this.setTamed(true);
			this.setOwnerId(player.getPersistentID());

			this.doPatreonCheck(player);

			return true;
		}
		else if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.WATER_BUCKET)
		{
			this.setWatered(true);
			this.setInLove(player);
			return true;
		}
		else if (itemstack == ItemStack.EMPTY && this.isTamed() && !this.isHamsterSitting() && !player.isSneaking())
		{
			this.setHamsterSitting(true);
			this.setSitting(true);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			return true;
		}
		else if (itemstack == ItemStack.EMPTY && this.isTamed() && this.isHamsterSitting() && !player.isSneaking())
		{
			this.setHamsterSitting(false);
			this.setSitting(false);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			return true;
		}
		else if (itemstack == ItemStack.EMPTY && this.isTamed() && !this.isRiding() && player.isSneaking())
		{

			if (!this.getIsRiding())
			{
				final ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
				props.setMounted(true);
				props.setPetColor(this.getColorNumber());
				props.setPetName(this.getCustomNameTag());
				props.setPetType("Hamster");
				this.setIsRiding(true);

			}
			return this.interactRide(player);
		}
		else if (itemstack == ItemStack.EMPTY && this.isTamed() && this.isRiding() && player.isSneaking())
		{

			if (this.getIsRiding())
			{
				final ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
				props.setPetColor(this.getColorNumber());
				props.setMounted(false);
				this.setIsRiding(false);
			}
			return this.interactRide(player);

		}

		if (!this.getIsTamed())
		{
			if (itemstack != ItemStack.EMPTY && itemstack.getItem() == ItemHandler.hamsterFood)
			{
				this.addFoodStack();
				player.addStat(AnimaniaAchievements.Hamsters, 1);
				return this.interactSeedsNotTamed(itemstack, player);
			}
			else
				return super.processInteract(player, hand);
		}
		else if (!this.getIsTamed() && itemstack.getItem() == Items.LEAD)
			return super.processInteract(player, hand);

		if (!itemstack.isEmpty() && itemstack.getItem() == ItemHandler.hamsterBallColored && !isInBall())
		{
			setInBall(true);
			int meta = itemstack.getMetadata();
			setBallColor(meta);
			if (!player.isCreative())
				itemstack.shrink(1);
			return true;
		}
		else if (!itemstack.isEmpty() && itemstack.getItem() == ItemHandler.hamsterBallClear && !isInBall())
		{
			setInBall(true);
			setBallColor(16);
			if (!player.isCreative())
				itemstack.shrink(1);
			return true;
		}
		else if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemHamsterBall && isInBall())
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

		if (itemstack != ItemStack.EMPTY && itemstack.getItem() == ItemHandler.hamsterFood)
		{
			addFoodStack();
			player.addStat(AnimaniaAchievements.Hamsters, 1);
			return interactSeedsTamed(itemstack, player);
		}

		return super.processInteract(player, hand);

	}

	private boolean interactSeedsNotTamed(ItemStack itemstack, EntityPlayer entityplayer)
	{

		if (!entityplayer.capabilities.isCreativeMode)
			itemstack.shrink(1);
		entityplayer.addStat(AnimaniaAchievements.Hamsters, 1);
		this.setHamsterStanding(true);
		this.standCount = 100;

		this.doPatreonCheck(entityplayer);

		if (itemstack.getCount() <= 0 && entityplayer.inventory != null)
			entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, ItemStack.EMPTY);

		if (this.rand.nextInt(3) == 0)
		{
			this.navigator.clearPathEntity();
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

	private boolean interactRide(EntityPlayer entityplayer)
	{
		this.isRemoteMountEntity(entityplayer);
		return true;
	}

	private void isRemoteMountEntity(Entity par1Entity)
	{

		if (this.getIsRiding())
		{
			this.setIsRiding(true);
			this.startRiding(par1Entity);
			this.rideCount = 0;
		}
		else if (!this.getIsRiding())
		{
			this.setIsRiding(false);
			this.dismountRidingEntity();
		}

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
		this.navigator.clearPathEntity(); // TODO
		this.setAttackTarget(null);
		// TODO entityToAttack = null;
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

		if (this.isRiding())
			this.rideCount++;

		if (this.isRiding() && this.getRidingEntity() instanceof EntityPlayer && this.rideCount > 30)
		{
			EntityPlayer player = (EntityPlayer) this.getRidingEntity();
			if (player.isSneaking())
			{
				player.removePassengers();
				this.setIsRiding(false);
				final ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
				props.setMounted(false);
			}
		}

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
				this.blinkTimer = 80 + this.rand.nextInt(80);
		}

		this.setResourceLoc();
		super.onLivingUpdate();

		if (this.getIsRiding() && this.getRidingEntity() != null || this.isHamsterSitting())
		{

			if (this.getRidingEntity() != null)
				this.rotationYaw = this.getRidingEntity().rotationYaw;
			this.navigator.clearPathEntity();
			this.navigator.setSpeed(0);
		}

		if (this.getHealth() < 10)
		{
			this.eatFood();
			this.eatCount = 5000;
		}
		if (!this.isHamsterStanding() && !this.isHamsterSitting())
		{
			if (this.rand.nextInt(20) == 0 && this.rand.nextInt(20) == 0)
			{
				this.setHamsterStanding(true);
				this.standCount = 30;
				this.navigator.clearPathEntity();
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
			Entity entity = this.getAITarget();
			if (entity instanceof EntityPlayer)
			{
				EntityPlayer entityplayer = (EntityPlayer) entity;
				ItemStack itemstack = entityplayer.inventory.getCurrentItem();
				if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.WHEAT_SEEDS)
					this.looksWithInterest = true;
			}
		}

		if (this.isHamsterSitting() | this.isHamsterStanding() && this.getNavigator() != null)
			this.getNavigator().clearPathEntity();

		if (this.fedTimer > -1)
		{
			this.fedTimer--;

			if (this.fedTimer == 0)
				this.setFed(false);
		}

		if (this.wateredTimer > -1)
		{
			this.wateredTimer--;

			if (this.wateredTimer == 0)
			{
				// this.setWatered(false);
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

				if (!this.getFed() && !this.getWatered() && AnimaniaConfig.gameRules.showUnhappyParticles)
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
					this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + this.rand.nextFloat() * this.width - this.width, this.posY + 1D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width - this.width, d, d1, d2);
				}
			}
		}

	}

	public boolean getFed()
	{
		return this.dataManager.get(EntityHamster.FED).booleanValue();
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
		return this.dataManager.get(EntityHamster.WATERED).booleanValue();
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
		return this.dataManager.get(EntityHamster.IN_BALL).booleanValue();
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
		return this.dataManager.get(EntityHamster.BALL_COLOR).intValue();
	}

	public void setBallColor(int color)
	{
		this.dataManager.set(EntityHamster.BALL_COLOR, Integer.valueOf(color));
	}

	public boolean isHamsterSitting()
	{
		return this.dataManager.get(EntityHamster.SITTING).booleanValue();
	}

	public void setHamsterSitting(boolean flag)
	{
		if (flag)
			this.dataManager.set(EntityHamster.SITTING, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityHamster.SITTING, Boolean.valueOf(false));
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
		return this.dataManager.get(EntityHamster.TAMED).booleanValue();
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
		return this.dataManager.get(EntityHamster.RIDING).booleanValue();
	}

	public void setIsRiding(boolean riding)
	{
		if (riding)
			this.dataManager.set(EntityHamster.RIDING, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityHamster.RIDING, Boolean.valueOf(false));
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
		int happyDrops = 0;

		if (this.getWatered())
			happyDrops++;
		if (this.getFed())
			happyDrops++;

		ItemStack dropItem;
		if (AnimaniaConfig.drops.customMobDrops)
		{
			String drop = AnimaniaConfig.drops.hamsterDrop;
			dropItem = AnimaniaHelper.getItem(drop);
		}
		else
			dropItem = null;

		if (happyDrops == 2 && dropItem !=null)
		{
			dropItem.setCount(1 + lootlevel);
			EntityItem entityitem = new EntityItem(this.world, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, dropItem);
			world.spawnEntity(entityitem);
		}
		else if (happyDrops == 1 && dropItem !=null)
		{
			dropItem.setCount(1 + lootlevel);
			EntityItem entityitem = new EntityItem(this.world, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, dropItem);
			world.spawnEntity(entityitem);
		}

		if (this.isInBall())
		{
			int color = this.getBallColor();
			if (color == 16)
				this.entityDropItem(new ItemStack(ItemHandler.hamsterBallClear), 0f);
			else
				this.entityDropItem(new ItemStack(ItemHandler.hamsterBallColored, 1, color), 0f);

		}

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
		return this.dataManager.get(EntityHamster.COLOR_NUM).intValue();
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

		/*
		 * EntityHamster entityhamster = (EntityHamster) entityanimal;
		 * EntityHamster entityhamster1 = new EntityHamster(worldObj); if
		 * (entityhamster.isHamsterTamed()) {
		 * entityhamster1.setHamsterTamed(true);
		 * entityhamster1.setHamsterOwner(entityhamster.getHamsterOwner()); }
		 * entityhamster1.setHamsterColor(entityhamster.getHamsterColor());
		 * entityhamster1.resourceLocation = entityhamster.resourceLocation;
		 * return entityhamster1;
		 */
		return null;
	}

	/*
	 * @Override public boolean isRiding() { return ridingEntity != null; }
	 *
	 * public boolean isRidingPlayer() { if (ridingEntity != null) { if
	 * (ridingEntity instanceof EntityPlayerSP) return true; if
	 * (ridingEntity.ridingEntity != null) { Entity e =
	 * ridingEntity.ridingEntity; while(e != null) { if (e instanceof
	 * EntityPlayerSP) return true; e = e.ridingEntity; } } } return false; }
	 *
	 * public float isRidingSpecial() { float f = -1.0F; if (ridingEntity !=
	 * null) { Entity e = ridingEntity; while(e != null) { f = e.height; e =
	 * e.ridingEntity; } } return f; }
	 *
	 * public boolean isRidingCreature() { if (ridingEntity != null) { if
	 * (ridingEntity instanceof EntityCreature) return false; } return false; }
	 *
	 * public boolean isRidingHamster() { if (ridingEntity != null) { if
	 * (ridingEntity instanceof EntityHamster) return true; } return false; }
	 */

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

		Random rand = new Random();
		int chooser = rand.nextInt(num);

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
	protected SoundEvent getHurtSound()
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

		if (soundevent != null)
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

}
