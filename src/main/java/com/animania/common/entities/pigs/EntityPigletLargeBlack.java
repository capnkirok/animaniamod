package com.animania.common.entities.pigs;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.Animania;
import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

public class EntityPigletLargeBlack extends EntityAnimal
{
	private static final DataParameter<Optional<UUID>> PARENT_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityPigletLargeBlack.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Boolean> SADDLED = EntityDataManager.<Boolean>createKey(EntityPigletLargeBlack.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> MUDDY = EntityDataManager.<Boolean>createKey(EntityPigletLargeBlack.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Float> SPLASHTIMER = EntityDataManager.<Float>createKey(EntityPigletLargeBlack.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> MUDTIMER = EntityDataManager.<Float>createKey(EntityPigletLargeBlack.class, DataSerializers.FLOAT);
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityPigletLargeBlack.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityPigletLargeBlack.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> PLAYED = EntityDataManager.<Boolean>createKey(EntityPigletLargeBlack.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Float> AGE = EntityDataManager.<Float>createKey(EntityPigletLargeBlack.class, DataSerializers.FLOAT);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] {Items.CARROT, Items.POTATO, Items.BEETROOT, Items.POISONOUS_POTATO, Animania.bucketSlop});
	private boolean boosting;
	private int boostTime;
	private int totalBoostTime;
	private int fedTimer;
	private int wateredTimer;
	private int playedTimer;
	private int ageTimer;
	private int happyTimer;
	public int blinkTimer;

	public EntityPigletLargeBlack(World worldIn)
	{
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		this.stepHeight = 1.1F;
		this.fedTimer = Animania.feedTimer + rand.nextInt(100);
		this.wateredTimer = Animania.waterTimer + rand.nextInt(100);
		this.playedTimer = Animania.playTimer + rand.nextInt(100);
		this.ageTimer = 0;
		this.happyTimer = 60;
		this.blinkTimer = 80 + rand.nextInt(80);

	}

	protected void initEntityAI()
	{
		this.entityAIEatGrass = new EntityPigSnuffle(this);
		this.tasks.addTask(1, new EntityAIFollowParentPigs(this, 1.1D));
		this.tasks.addTask(0, new EntityAISwimmingPigs(this));
		this.tasks.addTask(1, new EntityAIFindMud(this, 1.2D));
		this.tasks.addTask(2, new EntityAIWanderPig(this, 1.0D));
		this.tasks.addTask(3, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.tasks.addTask(5, new EntityAIFindFood(this, 1.0D));
		this.tasks.addTask(7, new EntityAIPanicPigs(this, 1.5D));
		this.tasks.addTask(8, new EntityAITempt(this, 1.2D, Items.CARROT_ON_A_STICK, false));
		this.tasks.addTask(9, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(10, this.entityAIEatGrass);
		this.tasks.addTask(12, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(13, new EntityAILookIdle(this));
	}
	
	@Override
	public boolean isChild()
	{
		return true;
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.32D);
	}
	
	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		
		this.setFed(true);

		if (this.entityAIEatGrass != null) {
			this.entityAIEatGrass.startExecuting();
			eatTimer = 80;
		}
		player.addStat(AnimaniaAchievements.LargeBlack, 1);
		if (player.hasAchievement(AnimaniaAchievements.Duroc) && player.hasAchievement(AnimaniaAchievements.Hampshire) && player.hasAchievement(AnimaniaAchievements.LargeBlack) && player.hasAchievement(AnimaniaAchievements.LargeWhite) && player.hasAchievement(AnimaniaAchievements.OldSpot) && player.hasAchievement(AnimaniaAchievements.Yorkshire)) {
			player.addStat(AnimaniaAchievements.Pigs, 1);
		}
		if (!player.capabilities.isCreativeMode)
		{
			if (stack != ItemStack.EMPTY && stack.getItem() != Animania.bucketSlop) {
				stack.setCount(stack.getCount()-1); 
			} else if (stack != ItemStack.EMPTY && stack.getItem() == Animania.bucketSlop) {
				stack.setCount(stack.getCount()-1); 
				if (!player.world.isRemote) {
					ItemStack itemstack = new ItemStack(Items.BUCKET, 1);
					EntityItem entityitem = new EntityItem(player.world, player.posX + 0.5D, player.posY, player.posZ + 0.5D, itemstack);
					player.world.spawnEntity(entityitem);
				}
			}
		}
	}
	
	@Override
	public void setInLove(EntityPlayer player)
    {
        this.world.setEntityState(this, (byte)18);
    }

	public int eatTimer;
	public EntityPigSnuffle entityAIEatGrass;
	private int damageTimer;

	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	/**
	 * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
	 * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
	 */
	@Nullable
	public Entity getControllingPassenger()
	{
		return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
	}

	/**
	 * returns true if all the conditions for steering the entity are met. For pigs, this is true if it is being ridden
	 * by a player and the player is holding a carrot-on-a-stick
	 */
	public boolean canBeSteered()
	{
		Entity entity = this.getControllingPassenger();

		if (!(entity instanceof EntityPlayer))
		{
			return false;
		}
		else
		{
			EntityPlayer entityplayer = (EntityPlayer)entity;
			ItemStack itemstack = entityplayer.getHeldItemMainhand();

			if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.CARROT_ON_A_STICK)
			{
				return true;
			}
			else
			{
				itemstack = entityplayer.getHeldItemOffhand();
				return itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.CARROT_ON_A_STICK;
			}
		}
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(SADDLED, Boolean.valueOf(false));
		this.dataManager.register(MUDDY, Boolean.valueOf(false));
		this.dataManager.register(MUDTIMER, Float.valueOf(0.0F));
		this.dataManager.register(SPLASHTIMER, Float.valueOf(0.0F));
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));
		this.dataManager.register(PLAYED, Boolean.valueOf(true));
		this.dataManager.register(AGE, Float.valueOf(0));
		this.dataManager.register(PARENT_UNIQUE_ID, Optional.<UUID>absent());
		
	}

	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setBoolean("Saddle", this.getSaddled());
		compound.setBoolean("Muddy", this.getMuddy());
		compound.setFloat("MudTimer", this.getMudTimer());
		compound.setFloat("SplashTimer", this.getSplashTimer());
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setBoolean("Played", this.getPlayed());
		compound.setFloat("Age", this.getEntityAge());
		if (this.getParentUniqueId() != null)
		{
			if (this.getParentUniqueId() != null) {
				compound.setString("ParentUUID", this.getParentUniqueId().toString());
			}
		}

	}
	
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.setSaddled(compound.getBoolean("Saddle"));
		this.setMuddy(compound.getBoolean("Muddy"));
		this.setMudTimer(compound.getFloat("MudTimer"));
		this.setSplashTimer(compound.getFloat("SplashTimer"));
		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setPlayed(compound.getBoolean("Played"));
		this.setEntityAge(compound.getFloat("Age"));
		String s;

		if (compound.hasKey("ParentUUID", 8))
		{
			s = compound.getString("ParentUUID");
		}
		else
		{
			String s1 = compound.getString("Parent");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty())
		{
			this.setParentUniqueId(UUID.fromString(s));
		}

	}

	
	@Nullable
	public UUID getParentUniqueId()
	{
		return (UUID)((Optional)this.dataManager.get(PARENT_UNIQUE_ID)).orNull();
	}

	public void setParentUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(PARENT_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	protected SoundEvent getAmbientSound()
	{
		int happy = 0;
		int num = 0;
		
		if (this.getWatered()) {
			happy++;
		} 
		if (this.getFed()) {
			happy++;
		} 
		
		if (happy == 2) {
			num = 8;
		} else if (happy == 1) {
			num = 16;
		} else {
			num = 32;
		}
		
		Random rand = new Random();
		int chooser = rand.nextInt(num);

		if (chooser == 0) {
			return ModSoundEvents.piglet1;
		} else if (chooser == 1){
			return ModSoundEvents.piglet2;
		} else if (chooser == 2){
			return ModSoundEvents.piglet3;
		} else if (chooser == 3){
			return ModSoundEvents.pig1;
		} else if (chooser == 4){
			return ModSoundEvents.pig2;
		} else {
			return null;
		}

	}


	protected SoundEvent getHurtSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0) {
			return ModSoundEvents.pigletHurt1;
		} else if (chooser == 1) {
			return ModSoundEvents.pigletHurt2;
		} else {
			return ModSoundEvents.pigletHurt3;
		}
	}

	protected SoundEvent getDeathSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0) {
			return ModSoundEvents.pigletHurt1;
		} else if (chooser == 1) {
			return ModSoundEvents.pigletHurt2;
		} else {
			return ModSoundEvents.pigletHurt3;
		}
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
		{
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() + .2F - (this.getEntityAge() * 2));
		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET) {
			{
				if (stack.getCount() == 1 && !player.capabilities.isCreativeMode)
				{
					player.setHeldItem(hand, new ItemStack(Items.BUCKET));
				}
				else if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET)))
				{
					player.dropItem(new ItemStack(Items.BUCKET), false);
				}

				if (this.entityAIEatGrass != null) {
					this.entityAIEatGrass.startExecuting();
					eatTimer = 40;
				}
				this.setWatered(true);
				this.setInLove(player);
				return true;
			}
		} else {
			return super.processInteract(player, hand);
		}
	}
	
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier)
	{
		super.dropEquipment(wasRecentlyHit, lootingModifier);

		if (this.getSaddled())
		{
			this.dropItem(Items.SADDLE, 1);
		}
	}

	/**
	 * Returns true if the pig is saddled.
	 */


	public boolean getSaddled()
	{
		return ((Boolean)this.dataManager.get(SADDLED)).booleanValue();
	}

	/**
	 * Set or remove the saddle of the pig.
	 */
	public void setSaddled(boolean saddled)
	{
		if (saddled)
		{
			this.dataManager.set(SADDLED, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(SADDLED, Boolean.valueOf(false));
		}
	}
	
	public boolean getFed()
	{
		return ((Boolean)this.dataManager.get(FED)).booleanValue();
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(FED, Boolean.valueOf(true));
			this.fedTimer = Animania.feedTimer + rand.nextInt(100);
			this.setHealth(this.getHealth()+1.0F);
		}
		else
		{
			this.dataManager.set(FED, Boolean.valueOf(false));
		}
	}

	public void setSlopFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(FED, Boolean.valueOf(true));
			this.fedTimer = (Animania.feedTimer * 2) + rand.nextInt(100);
		}
		else
		{
			this.dataManager.set(FED, Boolean.valueOf(false));
		}
	}
	
	public boolean getPlayed()
	{
		return ((Boolean)this.dataManager.get(PLAYED)).booleanValue();
	}

	public void setPlayed(boolean played)
	{
		if (played)
		{
			this.dataManager.set(PLAYED, Boolean.valueOf(true));
			this.playedTimer = Animania.playTimer + rand.nextInt(100);
		}
		else
		{
			this.dataManager.set(PLAYED, Boolean.valueOf(false));
		}
	}

	public boolean getWatered()
	{
		return ((Boolean)this.dataManager.get(WATERED)).booleanValue();
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(WATERED, Boolean.valueOf(true));
			this.wateredTimer = Animania.waterTimer + rand.nextInt(100);
		}
		else
		{
			this.dataManager.set(WATERED, Boolean.valueOf(false));
		}
	}
	
	public float getEntityAge()
	{
		return ((Float)this.dataManager.get(AGE)).floatValue();
	}

	public void setEntityAge(float age)
	{
		this.dataManager.set(AGE, Float.valueOf(age));
	}


	public boolean getMuddy()
	{
		return ((Boolean)this.dataManager.get(MUDDY)).booleanValue();
	}

	public void setMuddy(boolean muddy)
	{
		if (muddy)
		{
			this.dataManager.set(MUDDY, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(MUDDY, Boolean.valueOf(false));
		}
	}
	
	

	public Float getMudTimer()
	{
		return ((Float)this.dataManager.get(MUDTIMER)).floatValue();
	}

	public void setMudTimer(Float timer)
	{
		this.dataManager.set(MUDTIMER, Float.valueOf(timer));
	}

	public Float getSplashTimer()
	{
		return ((Float)this.dataManager.get(SPLASHTIMER)).floatValue();
	}

	public void setSplashTimer(Float timer)
	{
		this.dataManager.set(SPLASHTIMER, Float.valueOf(timer));
	}


	/**
	 * Called when a lightning bolt hits the entity.
	 */
	public void onStruckByLightning(EntityLightningBolt lightningBolt)
	{
		if (!this.world.isRemote && !this.isDead)
		{
			EntityPigZombie entitypigzombie = new EntityPigZombie(this.world);
			entitypigzombie.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
			entitypigzombie.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			entitypigzombie.setNoAI(this.isAIDisabled());

			if (this.hasCustomName())
			{
				entitypigzombie.setCustomNameTag(this.getCustomNameTag());
				entitypigzombie.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
			}

			this.world.spawnEntity(entitypigzombie);
			this.setDead();
		}
	}

	public void fall(float distance, float damageMultiplier)
	{
		super.fall(distance, damageMultiplier);

		if (distance > 5.0F)
		{
			for (EntityPlayer entityplayer : this.getRecursivePassengersByType(EntityPlayer.class))
			{
				entityplayer.addStat(AchievementList.FLY_PIG);
			}
		}
	}

	/**
	 * Moves the entity based on the specified heading.
	 */
	public void moveEntityWithHeading(float strafe, float forward)
	{
		Entity entity = this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);

		if (this.isBeingRidden() && this.canBeSteered())
		{
			this.rotationYaw = entity.rotationYaw;
			this.prevRotationYaw = this.rotationYaw;
			this.rotationPitch = entity.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.renderYawOffset = this.rotationYaw;
			this.rotationYawHead = this.rotationYaw;
			this.stepHeight = 1.0F;
			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (this.canPassengerSteer())
			{
				float f = (float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 0.225F;

				if (this.boosting)
				{
					if (this.boostTime++ > this.totalBoostTime)
					{
						this.boosting = false;
					}

					f += f * 1.15F * MathHelper.sin((float)this.boostTime / (float)this.totalBoostTime * (float)Math.PI);
				}

				this.setAIMoveSpeed(f);
				super.moveEntityWithHeading(0.0F, 1.0F);
			}
			else
			{
				this.motionX = 0.0D;
				this.motionY = 0.0D;
				this.motionZ = 0.0D;
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.posX - this.prevPosX;
			double d0 = this.posZ - this.prevPosZ;
			float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

			if (f1 > 1.0F)
			{
				f1 = 1.0F;
			}

			this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		}
		else
		{
			this.stepHeight = 1.0F;
			this.jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(strafe, forward);
		}
	}

	@Override
	public void onLivingUpdate()
	{
	
		if (this.world.isRemote)
		{
			this.eatTimer = Math.max(0, this.eatTimer - 1);
		}
		
		if (this.blinkTimer > -1) {
			this.blinkTimer--;
			if (blinkTimer == 0) {
				this.blinkTimer = 100 + rand.nextInt(100);
			}
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
				this.setWatered(false);
			}
		}


		if (this.playedTimer > -1) {
			this.playedTimer--;

			if (playedTimer == 0) {
				this.setPlayed(false);
			}
		}

		if (this.getMudTimer() > 0.0) {
			this.setPlayed(true);
			this.playedTimer = Animania.playTimer + rand.nextInt(100);
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();
		boolean played = this.getPlayed();
		
		if (!fed && !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
			if(Animania.animalsStarve)
			{
				if(this.damageTimer >= Animania.starvationTimer)
				{
					this.attackEntityFrom(DamageSource.STARVE, 4f);
					this.damageTimer = 0;
				}
				this.damageTimer++;
			}

		}
		else if (!fed || !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));
		}

		BlockPos currentpos = new BlockPos(this.posX, this.posY, this.posZ);
		Block poschk = this.world.getBlockState(currentpos).getBlock();
		
		
		if (poschk != null && poschk == Animania.blockMud) {
			this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, 4, false, false));
		}
	
		ageTimer++;
		if (ageTimer >= Animania.childGrowthTick) {

			if (fed && watered) {
				ageTimer = 0;
				float age = this.getEntityAge();
				age = age + .01F;
				this.setEntityAge(age);

				if (age >= .8 && !this.world.isRemote) {
					this.setDead();

					if (rand.nextInt(2) < 1) {
						EntitySowLargeBlack entityPig = new EntitySowLargeBlack(this.world);
						entityPig.setPosition(this.posX, this.posY + .5, this.posZ);
						String name = this.getCustomNameTag();
						if (name != "") {
							entityPig.setCustomNameTag(name);
						}
						this.world.spawnEntity(entityPig);
						this.playSound(ModSoundEvents.pig1, 0.50F, 1.1F);
					} else {
						EntityHogLargeBlack entityPig = new EntityHogLargeBlack(this.world);
						entityPig.setPosition(this.posX, this.posY + .5, this.posZ);
						String name = this.getCustomNameTag();
						if (name != "") {
							entityPig.setCustomNameTag(name);
						}
						this.world.spawnEntity(entityPig);
						this.playSound(ModSoundEvents.hog1, 0.50F, 1.1F);
					}

				}
			}

		}
		
		if (this.happyTimer > -1) {
			this.happyTimer--;
			if (happyTimer == 0) {
				happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && !this.getPlayed() && Animania.showUnhappyParticles) {
					double d = rand.nextGaussian() * 0.02D;
					double d1 = rand.nextGaussian() * 0.02D;
					double d2 = rand.nextGaussian() * 0.02D;
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (posX + (double)(rand.nextFloat() * width)) - (double)width, posY + 1.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width)) - (double)width, d, d1, d2);
				}
			}
		}
		
		super.onLivingUpdate();
	}


	public boolean boost()
	{
		if (this.boosting)
		{
			return false;
		}
		else
		{
			this.boosting = true;
			this.boostTime = 0;
			this.totalBoostTime = this.getRNG().nextInt(841) + 140;
			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
		{
			this.eatTimer = 80;
		}
		else
		{
			super.handleStatusUpdate(id);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		return this.eatTimer <= 0 ? 0.0F : (this.eatTimer >= 4 && this.eatTimer <= 76 ? 1.0F : (this.eatTimer < 4 ? ((float)this.eatTimer - p_70894_1_) / 4.0F : -((float)(this.eatTimer - 80) - p_70894_1_) / 4.0F));
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 76)
		{
			float f = ((float)(this.eatTimer - 4) - p_70890_1_) / 24.0F;
			return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 150F) * MathHelper.sin(f * 28.7F); 
		}
		else
		{
			return this.eatTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * 0.017453292F; 
		}
	}


	public EntityPigletLargeBlack createChild(EntityAgeable ageable)
	{
		return null;
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
	 * the animal type)
	 */
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && TEMPTATION_ITEMS.contains(stack.getItem());
	}
}