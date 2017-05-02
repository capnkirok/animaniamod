package com.animania.entities.rodents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
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
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.animania.Animania;
import com.animania.AnimaniaAchievements;
import com.animania.ModSoundEvents;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ResourceInfo;

public class EntityHamster extends EntityTameable
{
	private static final DataParameter<Boolean> IN_BALL = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SITTING = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer>createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> FOOD_STACK_COUNT = EntityDataManager.<Integer>createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> IN_LOVE = EntityDataManager.<Integer>createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> BALL_COLOR = EntityDataManager.<Integer>createKey(EntityHamster.class, DataSerializers.VARINT);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] {Animania.hamsterFood});
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityHamster.class, DataSerializers.BOOLEAN);
	private static final String[] HAMSTER_TEXTURES = new String[] {"black", "brown", "darkbrown", "darkgray", "gray", "plum", "tarou", "white"};

	private int fedTimer;
	private int wateredTimer;
	private int happyTimer;
	private int tamedTimer;
	public int blinkTimer;

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


	public EntityHamster(World world)
	{
		super(world);
		setHealth(10);
		yOffset = 0.1F;
		setSize(0.5F, 0.3F);
		this.stepHeight = 1.0F;
		this.fedTimer = Animania.feedTimer + rand.nextInt(100);
		this.wateredTimer = Animania.waterTimer + rand.nextInt(100);
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

	protected void initEntityAI()
	{

		this.tasks.addTask(1, new EntityAIPanicRodents(this, 1.4D));
		this.tasks.addTask(2, new EntityAISwimmingRodents(this));
		//this.tasks.addTask(3, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWanderRodent(this, 1.2D));
		this.tasks.addTask(5, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(6, new EntityAITemptHamster(this, 1.2D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(2, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));

	}

	protected void updateAITasks()
	{
		super.updateAITasks();
	}

	public int getVerticalFaceSpeed()
	{
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{

		this.setFed(true);
		this.setOwnerId(player.getUniqueID());
		this.setIsTamed(true);
		this.setTamed(true);

		player.addStat(AnimaniaAchievements.Hamsters, 1);

		if (!player.capabilities.isCreativeMode)
		{
			stack.setCount(stack.getCount()-1);
		}
	}



	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte)18);
	}

	public void setPosition(double x, double y, double z){
		super.setPosition(x, y, z);
		//System.out.println(x + "-" + y + "-" + z);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
	}

	public ResourceLocation getResourceLocation()
	{
		return resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink()
	{
		return resourceLocationBlink;
	}


	@Override
	protected void entityInit()
	{
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


	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setBoolean("Sitting", isHamsterSitting());
		nbttagcompound.setBoolean("InBall", isInBall());
		nbttagcompound.setInteger("ColorNumber", getColorNumber());
		nbttagcompound.setInteger("foodStackCount", getFoodStackCount());
		nbttagcompound.setInteger("BallColor", getBallColor());
		nbttagcompound.setBoolean("Fed", this.getFed());
		nbttagcompound.setBoolean("Watered", this.getWatered());
		nbttagcompound.setBoolean("IsTamed", this.getIsTamed());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);

		setHamsterSitting(nbttagcompound.getBoolean("Sitting"));
		setInBall(nbttagcompound.getBoolean("InBall"));
		this.setColorNumber(nbttagcompound.getInteger("ColorNumber"));
		setFoodStackCount(nbttagcompound.getInteger("foodStackCount"));
		setBallColor(nbttagcompound.getInteger("BallColor"));
		this.setFed(nbttagcompound.getBoolean("Fed"));
		this.setWatered(nbttagcompound.getBoolean("Watered"));
		this.setIsTamed(nbttagcompound.getBoolean("IsTamed"));
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public float getEyeHeight()
	{
		return height * 0.8F;
	}

	@Override
	public double getYOffset()
	{
		return (double)yOffset;
	}


	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.NAME_TAG) {

			if (!stack.hasDisplayName())
			{
				return false;
			}
			else 
			{
				EntityLiving entityliving = (EntityLiving)this;
				entityliving.setCustomNameTag(stack.getDisplayName());
				entityliving.enablePersistence();
				stack.setCount(stack.getCount()-1);
				this.setIsTamed(true);
				this.setTamed(true);
				this.setOwnerId(player.getUniqueID());
				return true;
			}	
		}

		if (itemstack != ItemStack.EMPTY && isBreedingItem(itemstack) && getGrowingAge() == 0)
		{

			if (!player.capabilities.isCreativeMode)
			{
				itemstack.shrink(1);

				if (itemstack.getCount() <= 0)
				{
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}
			}
			setAttackTarget(null);
			player.addStat(AnimaniaAchievements.Hamsters, 1);
			this.setInLove(player);
			this.setIsTamed(true);
			this.setTamed(true);
			this.setOwnerId(player.getUniqueID());

			return true;
		} else if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET) {
			{
				this.setWatered(true);
				this.setInLove(player);
				return true;
			}

		} else if (!this.world.isRemote && !this.isBreedingItem(stack) && !this.isHamsterSitting() && !player.isSneaking()) {
			//System.out.println("sit");
			this.setHamsterSitting(true);
			this.setSitting(true);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			this.setAttackTarget((EntityLivingBase)null);
			return super.processInteract(player, hand);
		} else if (!this.world.isRemote && !this.isBreedingItem(stack) && this.isHamsterSitting() && !player.isSneaking()) {
			//System.out.println("stand");
			this.setHamsterSitting(false);
			this.setSitting(false);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			this.setAttackTarget((EntityLivingBase)null);
			return super.processInteract(player, hand);
		} else if (!this.world.isRemote && !this.isBreedingItem(stack) && player.isSneaking()) {
			//System.out.println("free");
			this.setHamsterSitting(false);
			this.setSitting(false);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			this.setAttackTarget((EntityLivingBase)null);
			this.setOwnerId(null);
			this.setIsTamed(false);
			this.setTamed(false);
			return super.processInteract(player, hand);
		}

		if(!getIsTamed())
		{
			if(itemstack != ItemStack.EMPTY && itemstack.getItem() == Animania.hamsterFood)
			{
				addFoodStack();
				player.addStat(AnimaniaAchievements.Hamsters, 1);
				return interactSeedsNotTamed(itemstack, player);
			} else
			{
				return super.processInteract(player, hand);
			}
		}

		if(itemstack != ItemStack.EMPTY && itemstack.getItem() == Animania.hamsterBall && !isInBall())
		{
			setInBall(true);
			setBallColor(itemstack.getItemDamage());
			itemstack.damageItem(1, player);
		}
		if(itemstack != ItemStack.EMPTY && itemstack.getItem() == Animania.hamsterFood)
		{
			addFoodStack();
			player.addStat(AnimaniaAchievements.Hamsters, 1);
			return interactSeedsTamed(itemstack, player);
		}

		if(itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.PAPER)
		{
			if(isInBall()){
				setInBall(false);
				return true;
			}
			//return interactPaperTamed(player);

			return super.processInteract(player, hand);

		} else
		{

			/*//TODO
        	if(itemstack != ItemStack.EMPTY && itemstack.getItem() > 400) {
        		itemstack.func_111282_a(player, this);
        	}
			 */

			return interactOthersTamed();
		}


	}


	private boolean interactSeedsNotTamed(ItemStack itemstack, EntityPlayer entityplayer)
	{

		if (!entityplayer.capabilities.isCreativeMode) {
			itemstack.shrink(1);
		}
		entityplayer.addStat(AnimaniaAchievements.Hamsters, 1);
		this.setHamsterStanding(true);
		this.standCount = 100;

		if(itemstack.getCount() <= 0 && entityplayer.inventory != null)
		{
			entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, ItemStack.EMPTY);
		}

		if(rand.nextInt(3) == 0)
		{
			this.navigator.clearPathEntity();
			isJumping = false;
			//TODO entityToAttack = null;
			//showHeartsOrSmokeFX("note", 1, false);
			world.setEntityState(this, (byte)7);

		} else
		{
			//showHeartsOrSmokeFX("note", 1, false);
			world.setEntityState(this, (byte)7);
		}
		heal(1);

		return true;
	}

	private boolean interactSeedsTamed(ItemStack itemstack, EntityPlayer entityplayer)
	{
		if (!entityplayer.capabilities.isCreativeMode) {
			itemstack.shrink(1);
		}
		if(itemstack.getCount() <= 0)
		{
			entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, ItemStack.EMPTY);
		}
		//showHeartsOrSmokeFX("note", 1, false);

		heal(1);
		return true;
	}

	/*
	private boolean interactPaperTamed(EntityPlayer entityplayer)
	{
		isRemoteMountEntity(entityplayer);
		return true;
	}


	private void isRemoteMountEntity(Entity par1Entity)
	{
		if (ridingEntity == par1Entity)
		{
			setLocationAndAngles(ridingEntity.posX, ridingEntity.boundingBox.minY + (double)ridingEntity.height, ridingEntity.posZ, rotationYaw, rotationPitch);
			//Animania.mDebug("isRemoteMountEntity x="+ridingEntity.posX+" y="+(ridingEntity.boundingBox.minY + (double)ridingEntity.height)+" z="+ridingEntity.posZ);
			ridingEntity = null;
		}
		else if (ridingEntity == null)
		{
			mountEntity(par1Entity);
			posX = par1Entity.posX;
			posY = par1Entity.boundingBox.minY + (double)height;
			posZ = par1Entity.posZ;
			setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
		}
	}
	 */

	private boolean interactOthersTamed() {
		if (isHamsterStanding() || !isHamsterSitting()) {
			setHamsterSitting(true);
		} else if (isHamsterSitting()) {
			setHamsterSitting(false);
		}
		isJumping = false;
		this.navigator.clearPathEntity(); //TODO
		setAttackTarget(null);
		//TODO entityToAttack = null;
		return true;
	}




	@Override
	public void heal(float f)
	{
		super.heal(f);
		hurtResistantTime = maxHurtResistantTime / 2;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		/*
		if(ridingEntity != null && ridingEntity instanceof EntityPlayer)
		{
			ItemStack itemstack = ((EntityPlayer)ridingEntity).inventory.getCurrentItem();
			if(itemstack == null || itemstack != ItemStack.EMPTY && itemstack.getItem() != Items.paper)
			{
				return false;
			}
		}
		return super.canBeCollidedWith();
		 */
		return true;
	}

	@Override
	public boolean isEntityInsideOpaqueBlock()
	{
		/*
		if(ridingEntity != null)
		{
			return false;
		} else
		{
			return super.isEntityInsideOpaqueBlock();
		}
		 */
		return super.isEntityInsideOpaqueBlock();

	}



	public boolean entityLivingBaseAttackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (this.isEntityInvulnerable(par1DamageSource))
		{
			return false;
		}
		else if (this.world.isRemote)
		{
			return false;
		}
		else
		{
			this.entityAge = 0;

			if (this.getHealth() <= 0.0F)
			{
				return false;
			}
			else if (par1DamageSource.isFireDamage() && this.isPotionActive(MobEffects.FIRE_RESISTANCE))
			{
				return false;
			}
			else
			{
				/*//TODO
            	if ((par1DamageSource == DamageSource.anvil || par1DamageSource == DamageSource.fallingBlock) && this.getArmorItemForSlot(4, 4) != null)
                {
                    this.getCurrentItemOrArmor(4).damageItem((int)(par2 * 4.0F + this.rand.nextFloat() * par2 * 2.0F), this);
                    par2 *= 0.75F;
                }
				 */

				this.limbSwingAmount = 1.5F;
				boolean var3 = true;

				if ((float)this.hurtResistantTime > (float)this.maxHurtResistantTime / 2.0F)
				{
					if (par2 <= this.lastDamage)
					{
						return false;
					}

					this.damageEntity(par1DamageSource, par2 - this.lastDamage);
					this.lastDamage = par2;
					var3 = false;
				}
				else
				{
					this.lastDamage = par2;
					//TODO this.get = this.getHealth();
					this.hurtResistantTime = this.maxHurtResistantTime;
					this.damageEntity(par1DamageSource, par2);
					this.hurtTime = this.maxHurtTime = 10;
				}

				this.attackedAtYaw = 0.0F;
				Entity var4 = par1DamageSource.getEntity();

				if (var4 != null)
				{
					if (var4 instanceof EntityLivingBase)
					{
						this.setRevengeTarget((EntityLivingBase)var4);
					}

					if (var4 instanceof EntityPlayer)
					{
						this.recentlyHit = 100;
						this.attackingPlayer = (EntityPlayer)var4;
					}
					else if (var4 instanceof EntityWolf)
					{
						EntityWolf var5 = (EntityWolf)var4;

						if (var5.isTamed())
						{
							this.recentlyHit = 100;
							this.attackingPlayer = null;
						}
					}
				}

				if (var3)
				{
					this.world.setEntityState(this, (byte)2);

					if (par1DamageSource != DamageSource.DROWN)
					{
						this.setBeenAttacked();
					}

					if (var4 != null)
					{
						double var9 = var4.posX - this.posX;
						double var7;

						for (var7 = var4.posZ - this.posZ; var9 * var9 + var7 * var7 < 1.0E-4D; var7 = (Math.random() - Math.random()) * 0.01D)
						{
							var9 = (Math.random() - Math.random()) * 0.01D;
						}

						this.attackedAtYaw = (float)(Math.atan2(var7, var9) * 180.0D / Math.PI) - this.rotationYaw;
						this.knockBack(var4, par2, var9, var7);
					}
					else
					{
						this.attackedAtYaw = (float)((int)(Math.random() * 2.0D) * 180);
					}
				}

				if (this.getHealth() <= 0.0F)
				{
					if (var3)
					{
						//TODO	this.playSound(this.getDeathSound(), this.getSoundVolume(), this.getSoundPitch());
					}

					this.onDeath(par1DamageSource);
				}
				else if (var3)
				{
					//TODO this.playSound(this.getHurtSound(), this.getSoundVolume(), this.getSoundPitch());
				}

				return true;
			}
		}
	}




	@Override
	protected void jump()
	{
		motionY = 0.29999999999999999D;
	}

	@Override
	public void onLivingUpdate()
	{

		if (this.blinkTimer > -1) {
			this.blinkTimer--;
			if (blinkTimer == 0) {
				this.blinkTimer = 80 + rand.nextInt(80);
			}
		}

		if(getColorNumber() == 0) {
			Random rand = new Random();
			int bob2 = rand.nextInt(8) + 1;
			this.setColorNumber(bob2);
			resourceLocation = new ResourceLocation("animania:textures/entity/rodents/hamster_" + this.HAMSTER_TEXTURES[bob2-1] + ".png");
			resourceLocationBlink = new ResourceLocation("animania:textures/entity/rodents/hamster_" + this.HAMSTER_TEXTURES[bob2-1] + "_blink.png");
		} else if (resourceLocation == null) {
			resourceLocation = new ResourceLocation("animania:textures/entity/rodents/hamster_" + this.HAMSTER_TEXTURES[this.getColorNumber()-1] + ".png");
			resourceLocationBlink = new ResourceLocation("animania:textures/entity/rodents/hamster_" + this.HAMSTER_TEXTURES[this.getColorNumber()-1] + "_blink.png");
		}



		super.onLivingUpdate();
		if(getHealth() < 10)
		{
			eatFood();
			eatCount = 5000;
		}
		if(!isHamsterStanding() && !isHamsterSitting())
		{
			if(rand.nextInt(20) == 0 && rand.nextInt(20) == 0)
			{
				setHamsterStanding(true);
				standCount = 30;
				this.navigator.clearPathEntity();
				isJumping = false;
			}
		} else
			if(isHamsterStanding() && standCount-- <= 0 && rand.nextInt(10) == 0)
			{
				setHamsterStanding(false);
			}
		if(getFoodStackCount() > 0)
		{
			if(eatCount == 0)
			{
				if(rand.nextInt(30) == 0 && rand.nextInt(30) == 0)
				{
					eatFood();
					eatCount = 5000;
				}
			} else
			{
				eatCount--;
			}
		}
		looksWithInterest = false;
		if(!hasPath())
		{
			Entity entity = getAITarget();
			if(entity instanceof EntityPlayer)
			{
				EntityPlayer entityplayer = (EntityPlayer)entity;
				ItemStack itemstack = entityplayer.inventory.getCurrentItem();
				if(itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.WHEAT_SEEDS)
				{
					looksWithInterest = true;
				}
			}
		}

		if (isHamsterSitting() 
				| isHamsterStanding()
				&& getNavigator() != null) getNavigator().clearPathEntity();

		if (this.fedTimer > -1) {
			this.fedTimer--;

			if (fedTimer == 0) {
				this.setFed(false);
			}
		}

		if (this.wateredTimer > -1) {
			this.wateredTimer--;

			if (wateredTimer == 0) {
				//this.setWatered(false);
			}
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();

		if (!fed || !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));
			//this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, 0, true, true));
		} else if (!fed && !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
			//this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, 0, false, false));
		}

		if (this.happyTimer > -1) {
			this.happyTimer--;
			if (happyTimer == 0) {
				happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && Animania.showUnhappyParticles) {
					double d = rand.nextGaussian() * 0.001D;
					double d1 = rand.nextGaussian() * 0.001D;
					double d2 = rand.nextGaussian() * 0.001D;
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (posX + (double)(rand.nextFloat() * width)) - (double)width, posY + 1.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width)) - (double)width, d, d1, d2);
				}
			}
		}

		if (this.tamedTimer > -1) {
			this.tamedTimer--;
			if (tamedTimer == 0) {
				tamedTimer = 120;

				if (this.getIsTamed() && Animania.showUnhappyParticles) {
					double d = rand.nextGaussian() * 0.02D;
					double d1 = rand.nextGaussian() * 0.02D;
					double d2 = rand.nextGaussian() * 0.02D;
					world.spawnParticle(EnumParticleTypes.HEART, (posX + (double)(rand.nextFloat() * width)) - (double)width, posY + 1D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width)) - (double)width, d, d1, d2);
				}
			}
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

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		field_25054_c = field_25048_b;
		if(looksWithInterest)
		{
			field_25048_b = field_25048_b + (1.0F - field_25048_b) * 0.4F;
		} else
		{
			field_25048_b = field_25048_b + (0.0F - field_25048_b) * 0.4F;
		}
		if(looksWithInterest)
		{
			//numTicksToChaseTarget = 10;
		}
		if(rand.nextInt(10) == 5)
		{
			ticksExisted++;
		}

		//Animania.mDebug("worldObj.isRemote="+worldObj.isRemote+" posX="+posX+" posY="+posY+" poxZ="+posZ);
	}

	public float getInterestedAngle(float f)
	{
		return (field_25054_c + (field_25048_b - field_25054_c) * f) * 0.15F * 3.141593F;
	}

	/*
	@Override
	protected boolean isMovementCeased()
	{
		return isHamsterSitting() || isHamsterStanding();
	}
	 */

	@Nullable
	public UUID getHamsterOwner()
	{
		return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
	}

	public void setHamsterOwner(@Nullable UUID uniqueId)
	{
		this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}


	public boolean isInBall()
	{
		return ((Boolean)this.dataManager.get(IN_BALL)).booleanValue();
	}

	public void setInBall(boolean ball)
	{
		if (ball)
		{
			this.dataManager.set(IN_BALL, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(IN_BALL, Boolean.valueOf(false));
		}
	}

	public int getBallColor()
	{
		return ((Integer)this.dataManager.get(BALL_COLOR)).intValue();
	}

	public void setBallColor(int color)
	{
		this.dataManager.set(BALL_COLOR, Integer.valueOf(color));
	}


	public boolean isHamsterSitting()
	{
		return ((Boolean)this.dataManager.get(SITTING)).booleanValue();
	}

	public void setHamsterSitting(boolean flag)
	{
		if (flag)
		{
			this.dataManager.set(SITTING, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(SITTING, Boolean.valueOf(false));
		}
	}


	void showHeartsOrSmokeFX(String s, int i, boolean flag)
	{
		for(int j = 0; j < i; j++)
		{
			double d = rand.nextGaussian() * 0.02D;
			double d1 = rand.nextGaussian() * 0.02D;
			double d2 = rand.nextGaussian() * 0.02D;

			//world.spawnParticle(EnumParticleTypes.HEART, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);

			if (rand.nextInt(2) > 0) {
				world.playSound(null, this.posX, this.posY+1, this.posZ, ModSoundEvents.hamsterEat1, SoundCategory.PLAYERS, 0.6F, 0.8F);
			} else {
				world.playSound(null, this.posX, this.posY+1, this.posZ, ModSoundEvents.hamsterEat2, SoundCategory.PLAYERS, 0.6F, 0.8F);
			}
		}

	}

	public boolean getIsTamed()
	{
		return ((Boolean)this.dataManager.get(TAMED)).booleanValue();
	}

	public void setIsTamed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(TAMED, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(TAMED, Boolean.valueOf(false));
		}
	}


	public boolean isHamsterStanding()
	{
		return isStanding;
	}

	public void setHamsterStanding(boolean flag)
	{
		isStanding = flag;
	}


	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		int happyDrops = 0;

		if (this.getWatered()) {
			happyDrops++;
		} 
		if (this.getFed()) {
			happyDrops++;
		} 

		Item dropItem;
		if (Animania.customMobDrops) {
			String drop = Animania.hamsterDrop;
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

	public int getFoodStackCount()
	{
		return foodStackCount;
	}

	public void setFoodStackCount(int i)
	{
		foodStackCount = i;
	}

	public int getColorNumber()
	{
		return ((Integer)this.dataManager.get(COLOR_NUM)).intValue();
	}

	public void setColorNumber(int color)
	{
		this.dataManager.set(COLOR_NUM, Integer.valueOf(color));
	}

	private boolean addFoodStack()
	{
		if(foodStackCount != 5)
		{
			foodStackCount++;
			return true;
		} else
		{
			heal(1);
			return false;
		}
	}

	private boolean eatFood()
	{
		if(foodStackCount != 0)
		{
			foodStackCount--;
			heal(1);
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityanimal) {

		/*
		EntityHamster entityhamster = (EntityHamster) entityanimal;
		EntityHamster entityhamster1 = new EntityHamster(worldObj);
		if (entityhamster.isHamsterTamed()) {
			entityhamster1.setHamsterTamed(true);
			entityhamster1.setHamsterOwner(entityhamster.getHamsterOwner());
		}
		entityhamster1.setHamsterColor(entityhamster.getHamsterColor());
		entityhamster1.resourceLocation = entityhamster.resourceLocation;
		return entityhamster1;
		 */
		return null;
	}

	/*
	@Override
	public boolean isRiding()
	{
		return ridingEntity != null;
	}

	public boolean isRidingPlayer()
	{
		if (ridingEntity != null) {
			if (ridingEntity instanceof EntityPlayerSP) return true;
			if (ridingEntity.ridingEntity != null) {
				Entity e = ridingEntity.ridingEntity;
				while(e != null) {
					if (e instanceof EntityPlayerSP) return true;
					e = e.ridingEntity;
				}
			}
		}
		return false;
	}

	public float isRidingSpecial()
	{
		float f = -1.0F;
		if (ridingEntity != null) {
			Entity e = ridingEntity;
			while(e != null) {
				f = e.height;
				e = e.ridingEntity;
			}
		}
		return f;
	}

	public boolean isRidingCreature()
	{
		if (ridingEntity != null) {
			if (ridingEntity instanceof EntityCreature) return false;
		}
		return false;
	}

	public boolean isRidingHamster()
	{
		if (ridingEntity != null) {
			if (ridingEntity instanceof EntityHamster) return true;
		}
		return false;
	}
	 */

	@Override
	public double getMountedYOffset()
	{

		return (double)height;
	}

	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && TEMPTATION_ITEMS.contains(stack.getItem());
	}

	private void procreate(EntityHamster par1EntityAnimal)
	{
		EntityHamster entityhamster = (EntityHamster) createChild(par1EntityAnimal);

		if (entityhamster != null)
		{
			setGrowingAge(6000);
			par1EntityAnimal.setGrowingAge(6000);
			breeding = 0;
			//entityToAttack = null;
			//par1EntityAnimal.entityToAttack = null;
			par1EntityAnimal.breeding = 0;
			entityhamster.setGrowingAge(-24000);
			entityhamster.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);

			for (int i = 0; i < 7; i++)
			{
				double d = rand.nextGaussian() * 0.02D;
				double d1 = rand.nextGaussian() * 0.02D;
				double d2 = rand.nextGaussian() * 0.02D;
				//world.spawnParticle(EnumParticleTypes.HEART, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
			}

			world.spawnEntity(entityhamster);
		}
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
		} else if (chooser == 1){
			return ModSoundEvents.hamsterLiving2;
		} else if (chooser == 2){
			return ModSoundEvents.hamsterLiving3;
		} else {
			return null;
		}

	}

	protected SoundEvent getHurtSound()
	{
		return ModSoundEvents.hamsterHurt1;
	}

	protected SoundEvent getDeathSound()
	{
		return null;
	}


	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
		{
			this.playSound(soundevent, this.getSoundVolume() -.2F, this.getSoundPitch());
		}
	}

	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.8F);
	}

}
