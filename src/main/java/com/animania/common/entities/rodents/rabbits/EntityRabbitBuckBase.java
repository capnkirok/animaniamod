package com.animania.common.entities.rodents.rabbits;

import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.animania.common.capabilities.CapabilityRefs;
import com.animania.common.capabilities.ICapabilityPlayer;
import com.animania.common.entities.amphibians.EntityAmphibian;
import com.animania.common.entities.rodents.RabbitType;
import com.animania.common.entities.rodents.ai.EntityAIFindWater;
import com.animania.common.entities.rodents.ai.EntityAILookIdleRodent;
import com.animania.common.entities.rodents.ai.EntityAIRodentEat;
import com.animania.common.entities.rodents.ai.EntityAISwimmingRodents;
import com.animania.common.entities.rodents.ai.EntityAITemptRodents;
import com.animania.common.entities.rodents.ai.EntityAIWatchClosestFromSide;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderRodent;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityRabbit;
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

public class EntityRabbitBuckBase extends EntityTameable implements TOPInfoProviderRodent
{

	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityRabbitBuckBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityRabbitBuckBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityRabbitBuckBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> SITTING = EntityDataManager.<Boolean>createKey(EntityRabbitBuckBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> RIDING = EntityDataManager.<Boolean>createKey(EntityRabbitBuckBase.class, DataSerializers.BOOLEAN);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] {Items.WHEAT, Items.APPLE, Items.CARROT, Items.BEETROOT});
	protected int fedTimer;
	protected int wateredTimer;
	protected int happyTimer;
	protected int tamedTimer;
	public int blinkTimer;
	public int eatTimer;
	public EntityAIRodentEat entityAIEatGrass;
	protected RabbitType type;
	private int jumpTicks;
	private int jumpDuration;
	private boolean wasOnGround;
	private int currentMoveTypeDuration;
	protected int damageTimer;
	protected Item dropRaw = Items.BEEF;
	protected Item dropCooked = Items.COOKED_BEEF;

	public EntityRabbitBuckBase(World worldIn)
	{
		super(worldIn);
		this.setSize(.75F, .40F);
		this.stepHeight = 1.1F;
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.tamedTimer = 120;
		this.blinkTimer = 70 + this.rand.nextInt(70);
		this.enablePersistence();
		this.jumpHelper = new EntityRabbitBuckBase.RabbitJumpHelper(this);
		this.moveHelper = new EntityRabbitBuckBase.RabbitMoveHelper(this);
		this.setMovementSpeed(0.0D);
	}

	@Override
	protected void initEntityAI()
	{
		this.aiSit = new EntityAISit(this);
		this.tasks.addTask(0, new EntityAISwimmingRodents(this));
		this.tasks.addTask(1, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(2, this.aiSit);
		this.entityAIEatGrass = new EntityAIRodentEat(this);
		//this.tasks.addTask(5, new EntityAIRabbitFindFood(this, 1.0D));

		this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(7, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(8, new EntityAIRodentEat(this));
		this.tasks.addTask(9, new EntityAITemptRodents(this, 1.2D, false, EntityRabbitBuckBase.TEMPTATION_ITEMS));
		this.tasks.addTask(10, this.entityAIEatGrass);
		this.tasks.addTask(11, new EntityAIWanderAvoidWater(this, 1.2D));
		this.tasks.addTask(12, new EntityAIWatchClosestFromSide(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(13, new EntityAILookIdleRodent(this));

	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public int getVerticalFaceSpeed()
	{
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
	}

	protected void jump()
	{
		super.jump();
		double d0 = this.moveHelper.getSpeed();

		if (d0 > 0.0D)
		{
			double d1 = this.motionX * this.motionX + this.motionZ * this.motionZ;

			if (d1 < 0.010000000000000002D)
			{
				this.moveRelative(0.0F, 1.0F, 0.1F);
			}
		}

		if (!this.world.isRemote)
		{
			this.world.setEntityState(this, (byte)1);
		}
	}

	protected SoundEvent getJumpSound()
	{
		return SoundEvents.ENTITY_RABBIT_JUMP;
	}

	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.ENTITY_RABBIT_AMBIENT;
	}

	protected SoundEvent getHurtSound()
	{
		return SoundEvents.ENTITY_RABBIT_HURT;
	}

	protected SoundEvent getDeathSound()
	{
		return SoundEvents.ENTITY_RABBIT_DEATH;
	}

	@SideOnly(Side.CLIENT)
	public float setJumpCompletion(float p_175521_1_)
	{
		return this.jumpDuration == 0 ? 0.0F : ((float)this.jumpTicks + p_175521_1_) / (float)this.jumpDuration;
	}

	public void setMovementSpeed(double newSpeed)
	{
		this.getNavigator().setSpeed(newSpeed);
		this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), newSpeed);
	}

	public void setJumping(boolean jumping)
	{
		super.setJumping(jumping);

		if (jumping)
		{
			this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
		}
	}

	public void startJumping()
	{
		this.setJumping(true);
		this.jumpDuration = 10;
		this.jumpTicks = 0;
	}


	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		this.setOwnerId(player.getPersistentID());
		this.setIsTamed(true);
		this.setTamed(true);
		this.setSitting(false);
		this.setRabbitSitting(false);
		player.addStat(type.getAchievement(), 1);
		this.entityAIEatGrass.startExecuting();
		/*
		if (player.hasAchievement(AnimaniaAchievements.WhiteRabbit) && player.hasAchievement(AnimaniaAchievements.GreyRabbit))
			player.addStat(AnimaniaAchievements.Rabbits, 1);
		*/
		if (!player.capabilities.isCreativeMode)
			if (stack != ItemStack.EMPTY)
				stack.setCount(stack.getCount() - 1);
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
	}

	@Override
	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
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
			String drop = AnimaniaConfig.drops.rabbitDrop;
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

	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET)
		{
			this.setWatered(true);
			this.setInLove(player);
			return true;
		}
		else if (stack == ItemStack.EMPTY && this.isTamed() && !this.isRabbitSitting() && !player.isSneaking())
		{
			this.setRabbitSitting(true);
			this.setSitting(true);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			return true;
		}
		else if (stack == ItemStack.EMPTY && this.isTamed() && this.isRabbitSitting() && !player.isSneaking())
		{
			this.setRabbitSitting(false);
			this.setSitting(false);
			this.isJumping = false;
			this.navigator.clearPathEntity();
			return true;
		}
		else if (stack == ItemStack.EMPTY && this.isTamed() && !this.isRiding() && player.isSneaking())
		{
			if (!this.isRabbitRiding())
			{
				final ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
				props.setMounted(true);
				props.setPetName(this.getCustomNameTag());
				props.setPetType("Rabbit" + type.toString().toLowerCase().substring(0, 1).toUpperCase() + type.toString().toLowerCase().substring(1));
				this.setRabbitRiding(true);
			}
			return this.interactRide(player);
		}
		else if (stack == ItemStack.EMPTY && this.isTamed() && this.isRiding() && player.isSneaking())
		{
			if (this.isRabbitRiding())
			{
				final ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
				props.setMounted(false);
				this.setRabbitRiding(false);
			}
			return this.interactRide(player);
		}
		else
			return super.processInteract(player, hand);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);

		if (flag)
			this.applyEnchantments(this, entityIn);

		if (entityIn instanceof EntityAmphibian)
		{
			this.setFed(true);
		}

		// Custom Knockback
		if (entityIn instanceof EntityPlayer)
			((EntityLivingBase) entityIn).knockBack(this, 1, this.posX - entityIn.posX, this.posZ - entityIn.posZ);

		return flag;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityRabbitBuckBase.FED, Boolean.valueOf(true));
		this.dataManager.register(EntityRabbitBuckBase.WATERED, Boolean.valueOf(true));
		this.dataManager.register(EntityRabbitBuckBase.TAMED, Boolean.valueOf(false));
		this.dataManager.register(EntityRabbitBuckBase.SITTING, Boolean.valueOf(false));
		this.dataManager.register(EntityRabbitBuckBase.RIDING, Boolean.valueOf(false));

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setBoolean("IsTamed", this.getIsTamed());
		compound.setBoolean("IsSitting", this.isRabbitSitting());
		compound.setBoolean("Riding", this.isRabbitRiding());

	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setIsTamed(compound.getBoolean("IsTamed"));
		this.setRabbitSitting(compound.getBoolean("IsSitting"));
		this.setRabbitRiding(compound.getBoolean("Riding"));

	}

	@Override
	public boolean canBeLeashedTo(EntityPlayer player)
	{
		return true;
	}

	

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume() - .3F, this.getSoundPitch());
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.5F);
	}

	private boolean interactRide(EntityPlayer entityplayer)
	{
		this.isRemoteMountEntity(entityplayer);
		return true;
	}

	private void isRemoteMountEntity(Entity par1Entity)
	{

		if (this.isRabbitRiding())
		{
			this.setRabbitRiding(true);
			this.startRiding(par1Entity);
		}
		else if (!this.isRabbitRiding())
			this.dismountRidingEntity();

	}

	@Override
	public void onLivingUpdate()
	{

		if (this.isRabbitSitting() || this.isRiding())
		{
			if (this.getRidingEntity() != null)
				this.rotationYaw = this.getRidingEntity().rotationYaw;
			this.navigator.clearPathEntity();
			this.navigator.setSpeed(0);
		}

		if (this.world.isRemote)
			this.eatTimer = Math.max(0, this.eatTimer - 1);

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
				this.blinkTimer = 100 + this.rand.nextInt(100);
		}

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
				this.setWatered(false);
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

				if (this.getIsTamed() && AnimaniaConfig.gameRules.showUnhappyParticles)
				{
					double d = this.rand.nextGaussian() * 0.02D;
					double d1 = this.rand.nextGaussian() * 0.02D;
					double d2 = this.rand.nextGaussian() * 0.02D;
					this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + this.rand.nextFloat() * this.width - this.width, this.posY + 1D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width - this.width, d, d1, d2);
				}
			}
		}

		super.onLivingUpdate();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
			this.eatTimer = 80;
		else
			super.handleStatusUpdate(id);
	}

	public boolean isRabbitSitting()
	{
		return this.dataManager.get(EntityRabbitBuckBase.SITTING).booleanValue();
	}

	public void setRabbitSitting(boolean flag)
	{
		if (flag)
			this.dataManager.set(EntityRabbitBuckBase.SITTING, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityRabbitBuckBase.SITTING, Boolean.valueOf(false));
	}

	public boolean isRabbitRiding()
	{
		return this.dataManager.get(EntityRabbitBuckBase.RIDING).booleanValue();
	}

	public void setRabbitRiding(boolean flag)
	{
		if (flag)
			this.dataManager.set(EntityRabbitBuckBase.RIDING, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityRabbitBuckBase.RIDING, Boolean.valueOf(false));
	}

	public boolean getFed()
	{
		return this.dataManager.get(EntityRabbitBuckBase.FED).booleanValue();
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityRabbitBuckBase.FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		}
		else
			this.dataManager.set(EntityRabbitBuckBase.FED, Boolean.valueOf(false));
	}

	public boolean getWatered()
	{
		return this.dataManager.get(EntityRabbitBuckBase.WATERED).booleanValue();
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(EntityRabbitBuckBase.WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		}
		else
			this.dataManager.set(EntityRabbitBuckBase.WATERED, Boolean.valueOf(false));
	}

	public boolean getIsTamed()
	{
		return this.dataManager.get(EntityRabbitBuckBase.TAMED).booleanValue();
	}

	public void setIsTamed(boolean fed)
	{
		if (fed)
			this.dataManager.set(EntityRabbitBuckBase.TAMED, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityRabbitBuckBase.TAMED, Boolean.valueOf(false));
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		return this.eatTimer <= 0 ? 0.0F : this.eatTimer >= 4 && this.eatTimer <= 176 ? 1.0F : this.eatTimer < 4 ? (this.eatTimer - p_70894_1_) / 4.0F : -(this.eatTimer - 80 - p_70894_1_) / 4.0F;
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 176)
		{
			float f = (this.eatTimer - 4 - p_70890_1_) / 24.0F;
			return (float) Math.PI / 5F + (float) Math.PI * 7F / 150F * MathHelper.sin(f * 28.7F);
		}
		else
			return this.eatTimer > 0 ? (float) Math.PI / 5F : this.rotationPitch * 0.017453292F;
	}

	@Override
	public EntityRabbitBuckBase createChild(EntityAgeable ageable)
	{
		return null;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && EntityRabbitBuckBase.TEMPTATION_ITEMS.contains(stack.getItem());
	}

	public class RabbitJumpHelper extends EntityJumpHelper
	{
		private final EntityRabbitBuckBase theEntity;
		private boolean canJump;

		public RabbitJumpHelper(EntityRabbitBuckBase entityRabbitBase)
		{
			super(entityRabbitBase);
			this.theEntity = entityRabbitBase;
		}

		public boolean getIsJumping()
		{
			return this.isJumping;
		}

		public boolean canJump()
		{
			return this.canJump;
		}

		public void setCanJump(boolean canJumpIn)
		{
			this.canJump = canJumpIn;
		}

		/**
		 * Called to actually make the entity jump if isJumping is true.
		 */
		public void doJump()
		{
			if (this.isJumping)
			{
				this.theEntity.startJumping();
				this.isJumping = false;
			}
		}
	}

	static class RabbitMoveHelper extends EntityMoveHelper
	{
		private final EntityRabbitBuckBase theEntity;
		private double nextJumpSpeed;

		public RabbitMoveHelper(EntityRabbitBuckBase entityRabbitBase)
		{
			super(entityRabbitBase);
			this.theEntity = entityRabbitBase;
		}

		public void onUpdateMoveHelper()
		{
			/*
			if (this.theEntity.onGround && !this.theEntity.isJumping && !((EntityRabbit.RabbitJumpHelper)this.theEntity.jumpHelper).getIsJumping())
			{
				this.theEntity.setMovementSpeed(0.0D);
			}
			else if (this.isUpdating())
			{
				this.theEntity.setMovementSpeed(this.nextJumpSpeed);
			} */

			super.onUpdateMoveHelper();
		}

		/**
		 * Sets the speed and location to move to
		 */
		public void setMoveTo(double x, double y, double z, double speedIn)
		{
			if (this.theEntity.isInWater())
			{
				speedIn = 1.5D;
			}

			super.setMoveTo(x, y, z, speedIn);

			if (speedIn > 0.0D)
			{
				this.nextJumpSpeed = speedIn;
			}
		}
	}


}
