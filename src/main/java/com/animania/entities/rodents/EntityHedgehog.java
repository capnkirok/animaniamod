package com.animania.entities.rodents;

import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAITempt;
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
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.Animania;
import com.animania.AnimaniaAchievements;
import com.animania.ModSoundEvents;
import com.google.common.collect.Sets;

public class EntityHedgehog extends EntityTameable
{
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityFerretWhite.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityFerretWhite.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityFerretWhite.class, DataSerializers.BOOLEAN);
	private int fedTimer;
	private int wateredTimer;
	private int happyTimer;
	private int tamedTimer;
	public int blinkTimer;
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] {Items.CARROT, Items.BEETROOT, Animania.brownEgg, Items.EGG});

	public EntityHedgehog(World worldIn)
	{
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		this.stepHeight = 1.1F;
		this.fedTimer = Animania.feedTimer + rand.nextInt(100);
		this.wateredTimer = Animania.waterTimer + rand.nextInt(100);
		this.happyTimer = 60;
		this.tamedTimer = 120;
		this.blinkTimer = 80 + rand.nextInt(80);

	}

	public float getEyeHeight()
	{
		return this.height - .4F;
	}

	protected void initEntityAI()
	{
		this.aiSit = new EntityAISit(this);
		this.tasks.addTask(0, new EntityAISwimmingRodents(this));
		this.tasks.addTask(1, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAIHedgehogFindFood(this, 1.0D));
		this.entityAIEatGrass = new EntityAIRodentEat(this);
		this.tasks.addTask(3, new EntityAIPanic(this, 1.25D));
		this.tasks.addTask(4, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(5, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(6, new EntityAIPanicRodents(this, 1.5D));
		this.tasks.addTask(7, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(8, this.entityAIEatGrass);
		this.tasks.addTask(9, new EntityAIWanderHedgehog(this, 1.0D));
		this.tasks.addTask(10, new EntityAIWatchClosestFromSide(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(11, new EntityAILookIdle(this));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
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
		this.setOwnerId(player.getUniqueID());
		this.setIsTamed(true);

		player.addStat(AnimaniaAchievements.Hedgehog, 1);
		this.entityAIEatGrass.startExecuting();
		if (player.hasAchievement(AnimaniaAchievements.Hedgehog) && player.hasAchievement(AnimaniaAchievements.AlbinoHedgehog)) {
			player.addStat(AnimaniaAchievements.Hedgehogs, 1);
		}

		if (!player.capabilities.isCreativeMode)
		{
			if (stack != ItemStack.EMPTY) {
				stack.setCount(stack.getCount()-1); 
			}
		}
	}

	public int eatTimer;
	public EntityAIRodentEat entityAIEatGrass;

	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte)18);
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
			String drop = Animania.hedgehogDrop;
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
	
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));
		this.dataManager.register(TAMED, Boolean.valueOf(false));

	}

	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setBoolean("IsTamed", this.getIsTamed());

	}

	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setIsTamed(compound.getBoolean("IsTamed"));

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
			num = 10;
		} else if (happy == 1) {
			num = 20;
		} else {
			num = 40;
		}

		Random rand = new Random();
		int chooser = rand.nextInt(num);
		if (chooser == 0) {
			return ModSoundEvents.hedgehogLiving1;
		} else if (chooser == 1){
			return ModSoundEvents.hedgehogLiving2;
		} else if (chooser == 2){
			return ModSoundEvents.hedgehogLiving3;
		} else if (chooser == 3){
			return ModSoundEvents.hedgehogLiving4;
		} else if (chooser == 4){
			return ModSoundEvents.hedgehogLiving5;
		} else {
			return null;
		}

	}


	protected SoundEvent getHurtSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0) {
			return ModSoundEvents.hedgehogHurt1;
		} else if (chooser == 1) {
			return ModSoundEvents.hedgehogHurt2;
		} else {
			return null;
		}
	}

	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.hedgehogHurt1;	
	}


	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
		{
			this.playSound(soundevent, this.getSoundVolume() - .5F, this.getSoundPitch());
		}
	}



	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.5F);
	}


	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		
		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET) {
			this.setWatered(true);
			this.setInLove(player);
			return true;
		} else if (stack != ItemStack.EMPTY && stack.getItem() == Items.NAME_TAG) {
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

		} else if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack) && !this.isSitting() && !player.isSneaking()) {
			this.aiSit.setSitting(true);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			this.setAttackTarget((EntityLivingBase)null);
			return true;
		} else if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack) && this.isSitting() && !player.isSneaking()) {
			this.aiSit.setSitting(false);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			this.setAttackTarget((EntityLivingBase)null);
			return true;
		} else if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack) && player.isSneaking()) {
			this.aiSit.setSitting(false);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			this.setAttackTarget((EntityLivingBase)null);
			this.setOwnerId(null);
			this.setIsTamed(false);
			this.setTamed(false);
			double d = rand.nextGaussian() * 0.001D;
			double d1 = rand.nextGaussian() * 0.001D;
			double d2 = rand.nextGaussian() * 0.001D;
			return true;
		} else if (stack !=null && stack.getItem() == Items.NAME_TAG) {
			if (stack.getDisplayName().equals("Sonic")) {
				player.addStat(AnimaniaAchievements.Sonic, 1);
				return super.processInteract(player, hand);
			} else if (stack.getDisplayName().equals("Sanic")) {
				player.addStat(AnimaniaAchievements.Sanic, 1);
				return super.processInteract(player, hand);
			} else {
				return super.processInteract(player, hand);
			}


		} else {
			return super.processInteract(player, hand);
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

		boolean fed = this.getFed();
		boolean watered = this.getWatered();

		if (this.getCustomNameTag().equals("Sonic")) {
			this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2, 4, false, false));
		} else if (this.getCustomNameTag().equals("Sanic")) {
			this.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 2, 3, false, false));
			this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 2, 6, false, false));
		}

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

		super.onLivingUpdate();
	}



	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
		{
			this.eatTimer = 160;
		}
		else
		{
			super.handleStatusUpdate(id);
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

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		return this.eatTimer <= 0 ? 0.0F : (this.eatTimer >= 4 && this.eatTimer <= 156 ? 1.0F : (this.eatTimer < 4 ? ((float)this.eatTimer - p_70894_1_) / 4.0F : -((float)(this.eatTimer - 160) - p_70894_1_) / 4.0F));
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 156)
		{
			float f = ((float)(this.eatTimer - 4) - p_70890_1_) / 24.0F;
			return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 150F) * MathHelper.sin(f * 28.7F); 
		}
		else
		{
			return this.eatTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * 0.017453292F; 
		}
	}


	public EntityHedgehog createChild(EntityAgeable ageable)
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