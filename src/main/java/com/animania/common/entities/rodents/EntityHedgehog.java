package com.animania.common.entities.rodents;

import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.rodents.ai.EntityAIFindWater;
import com.animania.common.entities.rodents.ai.EntityAIHedgehogFindFood;
import com.animania.common.entities.rodents.ai.EntityAIPanicRodents;
import com.animania.common.entities.rodents.ai.EntityAIRodentEat;
import com.animania.common.entities.rodents.ai.EntityAISwimmingRodents;
import com.animania.common.entities.rodents.ai.EntityAIWanderHedgehog;
import com.animania.common.entities.rodents.ai.EntityAIWatchClosestFromSide;
import com.animania.common.handler.ItemHandler;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.monster.EntitySilverfish;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHedgehog extends EntityTameable {
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityHedgehog.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityHedgehog.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityHedgehog.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SITTING = EntityDataManager.<Boolean>createKey(EntityHedgehog.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> RIDING = EntityDataManager.<Boolean>createKey(EntityHedgehog.class, DataSerializers.BOOLEAN);
	
	private int fedTimer;
	private int wateredTimer;
	private int happyTimer;
	private int tamedTimer;
	public int blinkTimer;
	private static final Set<Item> TEMPTATION_ITEMS = Sets
			.newHashSet(new Item[] { Items.CARROT, Items.BEETROOT, ItemHandler.brownEgg, Items.EGG });

	public EntityHedgehog(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 0.5F);
		this.stepHeight = 1.1F;
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		this.happyTimer = 60;
		this.tamedTimer = 120;
		this.blinkTimer = 80 + rand.nextInt(80);
		this.enablePersistence();

	}

	@Override
	public float getEyeHeight() {
		return this.height - .4F;
	}

	@Override
	protected void initEntityAI() {
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
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntitySilverfish.class, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityFrogs.class, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityToad.class, false));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack) {
		this.setFed(true);
		this.setOwnerId(player.getUniqueID());
		this.setIsTamed(true);
		this.setTamed(true);
		this.setSitting(true);
		this.setHedgehogSitting(true);
		player.addStat(AnimaniaAchievements.Hedgehog, 1);
		this.entityAIEatGrass.startExecuting();
		if (player.hasAchievement(AnimaniaAchievements.Hedgehog)
				&& player.hasAchievement(AnimaniaAchievements.AlbinoHedgehog)) {
			player.addStat(AnimaniaAchievements.Hedgehogs, 1);
		}

		if (!player.capabilities.isCreativeMode) {
			if (stack != ItemStack.EMPTY) {
				stack.setCount(stack.getCount() - 1);
			}
		}
	}

	public int eatTimer;
	public EntityAIRodentEat entityAIEatGrass;
	private int damageTimer;

	@Override
	protected void updateAITasks() {
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void setInLove(EntityPlayer player) {
		this.world.setEntityState(this, (byte) 18);
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
			String drop = AnimaniaConfig.drops.hedgehogDrop;
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

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));
		this.dataManager.register(TAMED, Boolean.valueOf(false));
		this.dataManager.register(SITTING, Boolean.valueOf(false)); 
		this.dataManager.register(RIDING, Boolean.valueOf(false)); 

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setBoolean("IsTamed", this.getIsTamed());
		compound.setBoolean("IsSitting", this.isHedgehogSitting());
		compound.setBoolean("Riding", this.isHedgehogRiding());

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setIsTamed(compound.getBoolean("IsTamed"));
		this.setHedgehogSitting(compound.getBoolean("IsSitting"));
		this.setHedgehogRiding(compound.getBoolean("Riding"));
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
		} else if (chooser == 1) {
			return ModSoundEvents.hedgehogLiving2;
		} else if (chooser == 2) {
			return ModSoundEvents.hedgehogLiving3;
		} else if (chooser == 3) {
			return ModSoundEvents.hedgehogLiving4;
		} else if (chooser == 4) {
			return ModSoundEvents.hedgehogLiving5;
		} else {
			return null;
		}

	}

	@Override
	protected SoundEvent getHurtSound() {
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

	@Override
	protected SoundEvent getDeathSound() {
		return ModSoundEvents.hedgehogHurt1;
	}

	@Override
	public void playLivingSound() {
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null) {
			this.playSound(soundevent, this.getSoundVolume() - .5F, this.getSoundPitch());
		}
	}

	public int getVerticalFaceSpeed()
	{
		return this.isHedgehogSitting() ? 20 : super.getVerticalFaceSpeed();
	}
	
	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.5F);
	}
	
	private boolean interactRide(EntityPlayer entityplayer)
	{
		isRemoteMountEntity(entityplayer);
		return true;
	}

	private void isRemoteMountEntity(Entity par1Entity)
	{

		if (this.isHedgehogRiding())
		{
			this.setHedgehogRiding(true);
			this.startRiding(par1Entity, true);
			

		} else if (!this.isHedgehogRiding()) {
			this.dismountRidingEntity();
		}

	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET) {
			this.setWatered(true);
			this.setInLove(player);
			return true;
		} else if (stack != ItemStack.EMPTY && stack.getItem() == Items.NAME_TAG) {
			if (!stack.hasDisplayName()) {
				return false;
			} else {
				EntityLiving entityliving = this;
				entityliving.setCustomNameTag(stack.getDisplayName());
				entityliving.enablePersistence();
				if (!player.capabilities.isCreativeMode) {
					stack.setCount(stack.getCount() - 1);
				}
				this.setIsTamed(true);
				this.setTamed(true);
				this.setOwnerId(player.getUniqueID());
				return true;
			}

		} else if (stack == ItemStack.EMPTY && this.isTamed() && !this.isHedgehogSitting() && !player.isSneaking()) {
			this.setHedgehogSitting(true);
			this.setSitting(true);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			return true;
		} else if (stack == ItemStack.EMPTY && this.isTamed() && this.isHedgehogSitting() && !player.isSneaking()) {
			this.setHedgehogSitting(false);
			this.setSitting(false);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			return true;
		} else if (stack == ItemStack.EMPTY && this.isTamed() && !this.isRiding() && player.isSneaking()) {
			if (!this.isHedgehogRiding()) {
				this.setHedgehogRiding(true);
			}
			return interactRide(player);
		} else if (stack == ItemStack.EMPTY && this.isTamed() && this.isRiding() && player.isSneaking()) {
			if (this.isHedgehogRiding()) {
				this.setHedgehogRiding(false);
			}
			return interactRide(player);
		} else if (stack != null && stack.getItem() == Items.NAME_TAG) {
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
	public void onLivingUpdate() {
		
		if (this.isSitting() || this.isHedgehogSitting() || this.isRiding()) { 
			if (this.getRidingEntity() != null) {
				this.rotationYaw = this.getRidingEntity().rotationYaw;
			}
			this.navigator.clearPathEntity();
			this.navigator.setSpeed(0);
		}
		
		if (this.world.isRemote) {
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

				if (this.getIsTamed() && AnimaniaConfig.gameRules.showUnhappyParticles) {

					double d = rand.nextGaussian() * 0.02D;
					double d1 = rand.nextGaussian() * 0.02D;
					double d2 = rand.nextGaussian() * 0.02D;
					world.spawnParticle(EnumParticleTypes.HEART, (posX + rand.nextFloat() * width) - width,
							posY + 1D + rand.nextFloat() * height, (posZ + rand.nextFloat() * width) - width, d, d1,
							d2);
				}
			}
		}

		super.onLivingUpdate();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 10) {
			this.eatTimer = 160;
		} else {
			super.handleStatusUpdate(id);
		}
	}

	public boolean isHedgehogSitting()
	{
		return ((Boolean)this.dataManager.get(SITTING)).booleanValue();
	}

	public void setHedgehogSitting(boolean flag)
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

	public boolean isHedgehogRiding()
	{
		return ((Boolean)this.dataManager.get(RIDING)).booleanValue();
	}

	public void setHedgehogRiding(boolean flag)
	{
		if (flag)
		{
			this.dataManager.set(RIDING, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(RIDING, Boolean.valueOf(false));
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

	public boolean getIsTamed() {
		return this.dataManager.get(TAMED).booleanValue();
	}

	public void setIsTamed(boolean fed) {
		if (fed) {
			this.dataManager.set(TAMED, Boolean.valueOf(true));
		} else {
			this.dataManager.set(TAMED, Boolean.valueOf(false));
		}
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_) {
		return this.eatTimer <= 0 ? 0.0F
				: (this.eatTimer >= 4 && this.eatTimer <= 156 ? 1.0F
						: (this.eatTimer < 4 ? (this.eatTimer - p_70894_1_) / 4.0F
								: -(this.eatTimer - 160 - p_70894_1_) / 4.0F));
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_) {
		if (this.eatTimer > 4 && this.eatTimer <= 156) {
			float f = (this.eatTimer - 4 - p_70890_1_) / 24.0F;
			return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 150F) * MathHelper.sin(f * 28.7F);
		} else {
			return this.eatTimer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch * 0.017453292F;
		}
	}

	@Override
	public EntityHedgehog createChild(EntityAgeable ageable) {
		return null;
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed
	 * it (wheat, carrots or seeds depending on the animal type)
	 */
	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack) {
		return stack != ItemStack.EMPTY && TEMPTATION_ITEMS.contains(stack.getItem());
	}
}