package com.animania.common.entities.rodents;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.rodents.ai.EntityAIFindWater;
import com.animania.common.entities.rodents.ai.EntityAILookIdleRodent;
import com.animania.common.entities.rodents.ai.EntityAIPanicRodents;
import com.animania.common.entities.rodents.ai.EntityAISwimmingRodents;
import com.animania.common.entities.rodents.ai.EntityAITemptHamster;
import com.animania.common.entities.rodents.ai.EntityAIWanderRodent;
import com.animania.common.entities.rodents.ai.EntityAIWatchClosestFromSide;
import com.animania.common.handler.ItemHandler;
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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraft.world.World;

public class EntityHamster extends EntityTameable {
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
	private static final String[] HAMSTER_TEXTURES = new String[] { "black", "brown", "darkbrown", "darkgray", "gray",
			"plum", "tarou", "white" };

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

	public EntityHamster(World world) {
		super(world);
		setHealth(10);
		yOffset = 0.1F;
		setSize(0.5F, 0.3F);
		this.stepHeight = 1.0F;
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		looksWithInterest = false;
		stackCount = 20;
		eatCount = 5000;
		standCount = 30;
		isStanding = false;
		breeding = 0;
		this.happyTimer = 60;
		this.tamedTimer = 120;
		this.blinkTimer = 70 + rand.nextInt(70);
	}

	@Override
	protected void initEntityAI() {

		this.tasks.addTask(1, new EntityAIPanicRodents(this, 1.4D));
		this.tasks.addTask(2, new EntityAISwimmingRodents(this));
		this.tasks.addTask(3, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWanderRodent(this, 1.2D));
		this.tasks.addTask(5, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(6, new EntityAITemptHamster(this, 1.2D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(2, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(8, new EntityAIWatchClosestFromSide(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(9, new EntityAILookIdleRodent(this));

	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();
	}

	@Override
	public int getVerticalFaceSpeed() {
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack) {

		this.setFed(true);
		this.setOwnerId(player.getUniqueID());
		this.setIsTamed(true);
		this.setTamed(true);

		player.addStat(AnimaniaAchievements.Hamsters, 1);

		if (!player.capabilities.isCreativeMode) {
			stack.setCount(stack.getCount() - 1);
		}
	}

	@Override
	public void setInLove(EntityPlayer player) {
		this.world.setEntityState(this, (byte) 18);
	}

	@Override
	public void setPosition(double x, double y, double z) {
		super.setPosition(x, y, z);
		// System.out.println(x + "-" + y + "-" + z);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
	}

	public ResourceLocation getResourceLocation() {
		return resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink() {
		return resourceLocationBlink;
	}

	@Override
	protected void entityInit() {
		Random rand = new Random();
		super.entityInit();
		this.dataManager.register(IN_BALL, Boolean.valueOf(false));
		this.dataManager.register(SITTING, Boolean.valueOf(false));
		this.dataManager.register(TAMED, Boolean.valueOf(false));
		this.dataManager.register(COLOR_NUM, Integer.valueOf(0));
		this.dataManager.register(FOOD_STACK_COUNT, Integer.valueOf(0));
		this.dataManager.register(IN_LOVE, Integer.valueOf(0));
		this.dataManager.register(BALL_COLOR, Integer.valueOf(0));
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));
		this.dataManager.register(RIDING, Boolean.valueOf(false));

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setBoolean("Sitting", isHamsterSitting());
		nbttagcompound.setBoolean("InBall", isInBall());
		nbttagcompound.setInteger("ColorNumber", getColorNumber());
		nbttagcompound.setInteger("foodStackCount", getFoodStackCount());
		nbttagcompound.setInteger("BallColor", getBallColor());
		nbttagcompound.setBoolean("Fed", this.getFed());
		nbttagcompound.setBoolean("Watered", this.getWatered());
		nbttagcompound.setBoolean("IsTamed", this.getIsTamed());
		nbttagcompound.setBoolean("IsRiding", this.getIsRiding());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);

		setHamsterSitting(nbttagcompound.getBoolean("Sitting"));
		setInBall(nbttagcompound.getBoolean("InBall"));
		this.setColorNumber(nbttagcompound.getInteger("ColorNumber"));
		setFoodStackCount(nbttagcompound.getInteger("foodStackCount"));
		setBallColor(nbttagcompound.getInteger("BallColor"));
		this.setFed(nbttagcompound.getBoolean("Fed"));
		this.setWatered(nbttagcompound.getBoolean("Watered"));
		this.setIsTamed(nbttagcompound.getBoolean("IsTamed"));
		this.setIsRiding(nbttagcompound.getBoolean("IsRiding"));
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public float getEyeHeight() {
		return height * 0.8F;
	}

	@Override
	public double getYOffset() {
		return yOffset;
	}



	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {

		ItemStack itemstack = player.getHeldItem(hand);

		if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.NAME_TAG) {

			if (!itemstack.hasDisplayName()) {
				return false;
			} else {
				EntityLiving entityliving = this;
				entityliving.setCustomNameTag(itemstack.getDisplayName());
				entityliving.enablePersistence();
				if (!player.capabilities.isCreativeMode) {
					itemstack.setCount(itemstack.getCount() - 1);
				}
				this.setIsTamed(true);
				this.setTamed(true);
				this.setOwnerId(player.getUniqueID());
				return true;
			}
		}

		if (itemstack != ItemStack.EMPTY && isBreedingItem(itemstack) && getGrowingAge() == 0) {

			if (!player.capabilities.isCreativeMode) {
				itemstack.shrink(1);

				if (itemstack.getCount() <= 0) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
				}
			}

			player.addStat(AnimaniaAchievements.Hamsters, 1);
			this.setInLove(player);
			this.setIsTamed(true);
			this.setTamed(true);
			this.setOwnerId(player.getUniqueID());

			return true;
		} else if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.WATER_BUCKET) {
			{
				this.setWatered(true);
				this.setInLove(player);
				return true;
			}
		} else if (itemstack == ItemStack.EMPTY && this.isTamed() && !this.isHamsterSitting() && !player.isSneaking()) {
			this.setHamsterSitting(true);
			this.setSitting(true);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			return true;
		} else if (itemstack == ItemStack.EMPTY && this.isTamed() && this.isHamsterSitting() && !player.isSneaking()) {
			this.setHamsterSitting(false);
			this.setSitting(false);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			return true;
		} else if (itemstack == ItemStack.EMPTY && this.isTamed() && !this.isRiding() && player.isSneaking()) {

			if (!this.getIsRiding()) {
				this.setIsRiding(true);
			}
			return interactRide(player);
		} else if (itemstack == ItemStack.EMPTY && this.isTamed() && this.isRiding() && player.isSneaking()) {

			if (this.getIsRiding()) {
				this.setIsRiding(false);
			}
			return interactRide(player);

		}

		if (!getIsTamed()) {
			if (itemstack != ItemStack.EMPTY && itemstack.getItem() == ItemHandler.hamsterFood) {
				addFoodStack();
				player.addStat(AnimaniaAchievements.Hamsters, 1);
				return interactSeedsNotTamed(itemstack, player);
			} else {
				return super.processInteract(player, hand);
			}
		} else if (!getIsTamed() && itemstack.getItem() == Items.LEAD) {
			return super.processInteract(player, hand);
		}

		if (itemstack != ItemStack.EMPTY && itemstack.getItem() == ItemHandler.hamsterBall && !isInBall()) {
			setInBall(true);
			setBallColor(itemstack.getItemDamage());
			itemstack.damageItem(1, player);
		}
		if (itemstack != ItemStack.EMPTY && itemstack.getItem() == ItemHandler.hamsterFood) {
			addFoodStack();
			player.addStat(AnimaniaAchievements.Hamsters, 1);
			return interactSeedsTamed(itemstack, player);
		}



		return super.processInteract(player, hand);


	}

	private boolean interactSeedsNotTamed(ItemStack itemstack, EntityPlayer entityplayer) {

		if (!entityplayer.capabilities.isCreativeMode) {
			itemstack.shrink(1);
		}
		entityplayer.addStat(AnimaniaAchievements.Hamsters, 1);
		this.setHamsterStanding(true);
		this.standCount = 100;

		if (itemstack.getCount() <= 0 && entityplayer.inventory != null) {
			entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, ItemStack.EMPTY);
		}

		if (rand.nextInt(3) == 0) {
			this.navigator.clearPathEntity();
			isJumping = false;
			// TODO entityToAttack = null;
			// showHeartsOrSmokeFX("note", 1, false);
			world.setEntityState(this, (byte) 7);

		} else {
			// showHeartsOrSmokeFX("note", 1, false);
			world.setEntityState(this, (byte) 7);
		}
		heal(1);

		return true;
	}

	private boolean interactSeedsTamed(ItemStack itemstack, EntityPlayer entityplayer) {
		if (!entityplayer.capabilities.isCreativeMode) {
			itemstack.shrink(1);
		}
		if (itemstack.getCount() <= 0) {
			entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, ItemStack.EMPTY);
		}
		// showHeartsOrSmokeFX("note", 1, false);

		heal(1);
		return true;
	}

	private boolean interactRide(EntityPlayer entityplayer)
	{
		isRemoteMountEntity(entityplayer);
		return true;
	}

	private void isRemoteMountEntity(Entity par1Entity)
	{

		if (this.getIsRiding())
		{
			this.setIsRiding(true);
			this.startRiding(par1Entity);
			this.rideCount = 0;
		} else if (!this.getIsRiding()) {
			this.dismountRidingEntity();

		}

	}

	@Override
	public boolean canRiderInteract()
	{
		return true;
	}

	private boolean interactOthersTamed() {
		if (isHamsterStanding() || !isHamsterSitting()) {
			setHamsterSitting(true);
		} else if (isHamsterSitting()) {
			setHamsterSitting(false);
		}
		isJumping = false;
		this.navigator.clearPathEntity(); // TODO
		setAttackTarget(null);
		// TODO entityToAttack = null;
		return true;
	}

	@Override
	public void heal(float f) {
		super.heal(f);
		hurtResistantTime = maxHurtResistantTime / 2;
	}

	@Override
	public boolean canBeCollidedWith() {

		if(this.getRidingEntity() != null && this.getRidingEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) this.getRidingEntity();
			ItemStack itemstack = player.inventory.getCurrentItem();
			if(itemstack != null)
			{
				return false;
			}
		}

		return super.canBeCollidedWith();
	}

	@Override
	public boolean isEntityInsideOpaqueBlock() {

		if(this.getRidingEntity() != null)
		{
			return false;
		} else
		{
			return super.isEntityInsideOpaqueBlock();
		}

	}

	@Override
	protected void jump() {
		motionY = 0.29999999999999999D;
	}

	@Override
	public boolean canPassengerSteer()
	{
		return false;
	}


	@Override
	public void onLivingUpdate() {


		if (this.isRiding()) {
			rideCount++;
		}

		if (this.isRiding() && this.getRidingEntity() instanceof EntityPlayer && rideCount > 30) {
			EntityPlayer player = (EntityPlayer)this.getRidingEntity();
			if (player.isSneaking()) {
				player.removePassengers();
				this.setIsRiding(false);
			}
		}

		if (this.blinkTimer > -1) {
			this.blinkTimer--;
			if (blinkTimer == 0) {
				this.blinkTimer = 80 + rand.nextInt(80);
			}
		}

		if (getColorNumber() == 0) {
			Random rand = new Random();
			int bob2 = rand.nextInt(8) + 1;
			this.setColorNumber(bob2);
			resourceLocation = new ResourceLocation(
					"animania:textures/entity/rodents/hamster_" + EntityHamster.HAMSTER_TEXTURES[bob2 - 1] + ".png");
			resourceLocationBlink = new ResourceLocation("animania:textures/entity/rodents/hamster_"
					+ EntityHamster.HAMSTER_TEXTURES[bob2 - 1] + "_blink.png");
		} else if (resourceLocation == null) {
			resourceLocation = new ResourceLocation("animania:textures/entity/rodents/hamster_"
					+ EntityHamster.HAMSTER_TEXTURES[this.getColorNumber() - 1] + ".png");
			resourceLocationBlink = new ResourceLocation("animania:textures/entity/rodents/hamster_"
					+ EntityHamster.HAMSTER_TEXTURES[this.getColorNumber() - 1] + "_blink.png");
		}

		super.onLivingUpdate();

		if ((this.getIsRiding() && this.getRidingEntity() != null) || this.isHamsterSitting()) {

			if (this.getRidingEntity() != null) {
				this.rotationYaw = this.getRidingEntity().rotationYaw;
			}
			this.navigator.clearPathEntity();
			this.navigator.setSpeed(0);
		}

		if (getHealth() < 10) {
			eatFood();
			eatCount = 5000;
		}
		if (!isHamsterStanding() && !isHamsterSitting()) {
			if (rand.nextInt(20) == 0 && rand.nextInt(20) == 0) {
				setHamsterStanding(true);
				standCount = 30;
				this.navigator.clearPathEntity();
				isJumping = false;
			}
		} else if (isHamsterStanding() && standCount-- <= 0 && rand.nextInt(10) == 0) {
			setHamsterStanding(false);
		}
		if (getFoodStackCount() > 0) {
			if (eatCount == 0) {
				if (rand.nextInt(30) == 0 && rand.nextInt(30) == 0) {
					eatFood();
					eatCount = 5000;
				}
			} else {
				eatCount--;
			}
		}
		looksWithInterest = false;
		if (!hasPath()) {
			Entity entity = getAITarget();
			if (entity instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer) entity;
				ItemStack itemstack = entityplayer.inventory.getCurrentItem();
				if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.WHEAT_SEEDS) {
					looksWithInterest = true;
				}
			}
		}

		if (isHamsterSitting() | isHamsterStanding() && getNavigator() != null) {
			getNavigator().clearPathEntity();
		}

		if (this.fedTimer > -1) {
			this.fedTimer--;

			if (fedTimer == 0) {
				this.setFed(false);
			}
		}

		if (this.wateredTimer > -1) {
			this.wateredTimer--;

			if (wateredTimer == 0) {
				// this.setWatered(false);
			}
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();

		if (!fed && !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
			if (AnimaniaConfig.gameRules.animalsStarve) {
				if (this.damageTimer >= AnimaniaConfig.careAndFeeding.starvationTimer) {
					this.attackEntityFrom(DamageSource.STARVE, 4f);
					this.damageTimer = 0;
				}
				this.damageTimer++;
			}

		} else if (!fed || !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));
		}

		if (this.happyTimer > -1) {
			this.happyTimer--;
			if (happyTimer == 0) {
				happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && AnimaniaConfig.gameRules.showUnhappyParticles) {
					double d = rand.nextGaussian() * 0.001D;
					double d1 = rand.nextGaussian() * 0.001D;
					double d2 = rand.nextGaussian() * 0.001D;
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (posX + rand.nextFloat() * width) - width,
							posY + 1.5D + rand.nextFloat() * height, (posZ + rand.nextFloat() * width) - width, d, d1,
							d2);
				}
			}
		}

		if (this.tamedTimer > -1) {
			this.tamedTimer--;
			if (tamedTimer == 0) {
				tamedTimer = 120;

				if (this.getIsTamed() && AnimaniaConfig.gameRules.showUnhappyParticles && !this.isRiding()) {
					double d = rand.nextGaussian() * 0.02D;
					double d1 = rand.nextGaussian() * 0.02D;
					double d2 = rand.nextGaussian() * 0.02D;
					world.spawnParticle(EnumParticleTypes.HEART, (posX + rand.nextFloat() * width) - width,
							posY + 1D + rand.nextFloat() * height, (posZ + rand.nextFloat() * width) - width, d, d1,
							d2);
				}
			}
		}

	}

	public boolean getFed() {
		return this.dataManager.get(FED).booleanValue();
	}

	public void setFed(boolean fed) {
		if (fed) {
			this.dataManager.set(FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		} else {
			this.dataManager.set(FED, Boolean.valueOf(false));
		}
	}

	public boolean getWatered() {
		return this.dataManager.get(WATERED).booleanValue();
	}

	public void setWatered(boolean watered) {
		if (watered) {
			this.dataManager.set(WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		} else {
			this.dataManager.set(WATERED, Boolean.valueOf(false));
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		field_25054_c = field_25048_b;
		if (looksWithInterest) {
			field_25048_b = field_25048_b + (1.0F - field_25048_b) * 0.4F;
		} else {
			field_25048_b = field_25048_b + (0.0F - field_25048_b) * 0.4F;
		}
		if (looksWithInterest) {
			// numTicksToChaseTarget = 10;
		}
		if (rand.nextInt(10) == 5) {
			ticksExisted++;
		}

		// Animania.mDebug("worldObj.isRemote="+worldObj.isRemote+"
		// posX="+posX+" posY="+posY+" poxZ="+posZ);
	}

	public float getInterestedAngle(float f) {
		return (field_25054_c + (field_25048_b - field_25054_c) * f) * 0.15F * 3.141593F;
	}

	@Nullable
	public UUID getHamsterOwner() {
		return (UUID) ((Optional) this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
	}

	public void setHamsterOwner(@Nullable UUID uniqueId) {
		this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	public boolean isInBall() {
		return this.dataManager.get(IN_BALL).booleanValue();
	}

	public void setInBall(boolean ball) {
		if (ball) {
			this.dataManager.set(IN_BALL, Boolean.valueOf(true));
		} else {
			this.dataManager.set(IN_BALL, Boolean.valueOf(false));
		}
	}

	public int getBallColor() {
		return this.dataManager.get(BALL_COLOR).intValue();
	}

	public void setBallColor(int color) {
		this.dataManager.set(BALL_COLOR, Integer.valueOf(color));
	}

	public boolean isHamsterSitting() {
		return this.dataManager.get(SITTING).booleanValue();
	}

	public void setHamsterSitting(boolean flag) {
		if (flag) {
			this.dataManager.set(SITTING, Boolean.valueOf(true));
		} else {
			this.dataManager.set(SITTING, Boolean.valueOf(false));
		}
	}

	void showHeartsOrSmokeFX(String s, int i, boolean flag) {
		for (int j = 0; j < i; j++) {
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;

			if (rand.nextInt(2) > 0) {
				world.playSound(null, this.posX, this.posY + 1, this.posZ, ModSoundEvents.hamsterEat1,
						SoundCategory.PLAYERS, 0.6F, 0.8F);
			} else {
				world.playSound(null, this.posX, this.posY + 1, this.posZ, ModSoundEvents.hamsterEat2,
						SoundCategory.PLAYERS, 0.6F, 0.8F);
			}
		}

	}

	public boolean getIsTamed() {
		return this.dataManager.get(TAMED).booleanValue();
	}

	public void setIsTamed(boolean tamed) {
		if (tamed) {
			this.dataManager.set(TAMED, Boolean.valueOf(true));
		} else {
			this.dataManager.set(TAMED, Boolean.valueOf(false));
		}
	}

	public boolean getIsRiding()
	{
		return (Boolean)this.dataManager.get(RIDING).booleanValue();
	}

	public void setIsRiding(boolean riding)
	{
		if (riding)
		{
			this.dataManager.set(RIDING, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(RIDING, Boolean.valueOf(false));
		}
	}

	public boolean isHamsterStanding() {
		return isStanding;
	}

	public void setHamsterStanding(boolean flag) {
		isStanding = flag;
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel) {
		int happyDrops = 0;

		if (this.getWatered()) {
			happyDrops++;
		}
		if (this.getFed()) {
			happyDrops++;
		}

		Item dropItem;
		if (AnimaniaConfig.drops.customMobDrops) {
			String drop = AnimaniaConfig.drops.hamsterDrop;
			dropItem = getItem(drop);
		} else {
			dropItem = null;
		}

		if (happyDrops == 2) {
			this.dropItem(dropItem, 1 + lootlevel);
		} else if (happyDrops == 1) {
			this.dropItem(dropItem, 1 + lootlevel);
		}

	}

	private Item getItem(String moditem) {
		Item bob = Item.getByNameOrId(moditem);
		return bob;
	}

	public int getFoodStackCount() {
		return foodStackCount;
	}

	public void setFoodStackCount(int i) {
		foodStackCount = i;
	}

	public int getColorNumber() {
		return this.dataManager.get(COLOR_NUM).intValue();
	}

	public void setColorNumber(int color) {
		this.dataManager.set(COLOR_NUM, Integer.valueOf(color));
	}

	private boolean addFoodStack() {
		if (foodStackCount != 5) {
			foodStackCount++;
			return true;
		} else {
			heal(1);
			return false;
		}
	}

	private boolean eatFood() {
		if (foodStackCount != 0) {
			foodStackCount--;
			heal(1);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityanimal) {

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
	public double getMountedYOffset() {

		return height;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack) {
		return stack != ItemStack.EMPTY && TEMPTATION_ITEMS.contains(stack.getItem());
	}

	@Override
	protected SoundEvent getAmbientSound() {
		int happy = 0;
		int num = 0;

		if (this.getWatered()) {
			happy++;
		}
		if (this.getFed()) {
			happy++;
		}

		if (happy == 2) {
			num = 6;
		} else if (happy == 1) {
			num = 12;
		} else {
			num = 24;
		}

		Random rand = new Random();
		int chooser = rand.nextInt(num);

		if (chooser == 0) {
			return ModSoundEvents.hamsterLiving1;
		} else if (chooser == 1) {
			return ModSoundEvents.hamsterLiving2;
		} else if (chooser == 2) {
			return ModSoundEvents.hamsterLiving3;
		} else {
			return null;
		}

	}

	@Override
	protected SoundEvent getHurtSound() {
		return ModSoundEvents.hamsterHurt1;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Override
	public void playLivingSound() {
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null) {
			this.playSound(soundevent, this.getSoundVolume() - .2F, this.getSoundPitch());
		}
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.8F);
	}

}
