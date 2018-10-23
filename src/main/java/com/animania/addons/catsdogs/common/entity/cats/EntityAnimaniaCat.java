package com.animania.addons.catsdogs.common.entity.cats;

import java.util.Set;
import java.util.UUID;

import com.animania.addons.catsdogs.config.CatsDogsConfig;
import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.chickens.EntityChickBase;
import com.animania.common.entities.generic.ai.GenericAIAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAIFollowOwner;
import com.animania.common.entities.generic.ai.GenericAIHurtByTarget;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAINearestAttackableTarget;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAISwim;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.entities.interfaces.IAnimaniaAnimalBase;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaCat extends EntityTameable implements IAnimaniaAnimalBase
{

	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HANDFED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> SITTING = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityAnimaniaCat.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float>createKey(EntityAnimaniaCat.class, DataSerializers.FLOAT);
	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(CatsDogsConfig.catsdogs.catFood));

	protected int fedTimer;
	protected int wateredTimer;
	protected int happyTimer;
	protected int tamedTimer;
	public int blinkTimer;
	public int eatTimer;
	public GenericAIEatGrass entityAIEatGrass;
	protected int damageTimer;
	protected CatType type;
	protected EntityGender gender;

	public EntityAnimaniaCat(World worldIn)
	{
		super(worldIn);
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + this.rand.nextInt(80);
		this.enablePersistence();
	}

	@Override
	protected void initEntityAI()
	{
		this.aiSit = new EntityAISit(this);
		this.entityAIEatGrass = new GenericAIEatGrass(this, false);
		this.tasks.addTask(0, new GenericAISwim(this));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.tasks.addTask(1, new GenericAIFindWater(this, 1.0D, entityAIEatGrass, EntityAnimaniaCat.class, true));
			this.tasks.addTask(3, new GenericAIFindFood(this, 1.0D, entityAIEatGrass, false));
		}
		this.tasks.addTask(4, this.aiSit);
		this.tasks.addTask(5, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(6, new EntityAIAttackMelee(this, 1.5D, true));
		this.tasks.addTask(7, new GenericAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(8, new GenericAIPanic(this, 1.5D));
		this.tasks.addTask(10, new GenericAITempt(this, 1.2D, false, EntityFerretBase.TEMPTATION_ITEMS)); //TODO
		this.tasks.addTask(11, this.entityAIEatGrass);
		this.tasks.addTask(12, new GenericAIWanderAvoidWater(this, 1.2D));
		this.tasks.addTask(13, new GenericAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(14, new GenericAILookIdle(this));
		this.tasks.addTask(15, new GenericAIAvoidWater(this));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.tasks.addTask(15, new GenericAISleep(this, 0.8, AnimaniaHelper.getBlock(CatsDogsConfig.catsdogs.catBed), AnimaniaHelper.getBlock(CatsDogsConfig.catsdogs.catBed2), EntityAnimaniaCat.class));
		}
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.isTamed())
		{
			this.targetTasks.addTask(1, new GenericAINearestAttackableTarget(this, EntityFerretBase.class, false));
			this.targetTasks.addTask(2, new GenericAINearestAttackableTarget(this, EntityChickBase.class, false));
			this.targetTasks.addTask(6, new GenericAINearestAttackableTarget(this, EntitySilverfish.class, false));
			this.targetTasks.addTask(7, new GenericAINearestAttackableTarget(this, EntityFrogs.class, false));
			this.targetTasks.addTask(8, new GenericAINearestAttackableTarget(this, EntityToad.class, false));
		}
		this.targetTasks.addTask(9, new GenericAIHurtByTarget(this, false, new Class[0]));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.5D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));
		this.dataManager.register(TAMED, Boolean.valueOf(false));
		this.dataManager.register(SITTING, Boolean.valueOf(false));
		this.dataManager.register(SLEEPING, Boolean.valueOf(false));
		this.dataManager.register(HANDFED, Boolean.valueOf(false));
		this.dataManager.register(AGE, Integer.valueOf(0));
		this.dataManager.register(SLEEPTIMER, Float.valueOf(0.0F));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Handfed", this.getHandFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setBoolean("IsTamed", this.getIsTamed());
		compound.setBoolean("IsSitting", this.isSitting());
		compound.setInteger("Age", this.getAge());
		compound.setBoolean("Sleep", this.getSleeping());
		compound.setFloat("SleepTimer", this.getSleepTimer());

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.setFed(compound.getBoolean("Fed"));
		this.setHandFed(compound.getBoolean("Handfed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setAge(compound.getInteger("Age"));
		this.setIsTamed(compound.getBoolean("IsTamed"));
		this.setSitting(compound.getBoolean("IsSitting"));
		this.setSleeping(compound.getBoolean("Sleep"));
		this.setSleepTimer(compound.getFloat("SleepTimer"));
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void setPosition(double x, double y, double z)
	{
		super.setPosition(x, y, z);
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		if (!this.isTamed())
		{
			this.setOwnerId(player.getPersistentID());
			this.setIsTamed(true);
			this.setTamed(true);
			this.setInLove(player);
		}

		this.setSitting(false);
		this.setSitting(false);
		this.entityAIEatGrass.startExecuting();
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

	public int getAge()
	{
		try
		{
			return (this.getIntFromDataManager(AGE));
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	public void setAge(int age)
	{
		this.dataManager.set(AGE, Integer.valueOf(age));
	}

	public boolean getHandFed()
	{
		try
		{
			return (this.getBoolFromDataManager(HANDFED));
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public void setHandFed(boolean handfed)
	{
		this.dataManager.set(HANDFED, Boolean.valueOf(handfed));
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
			this.dataManager.set(SLEEPING, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(SLEEPING, Boolean.valueOf(false));
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
		this.dataManager.set(SLEEPTIMER, Float.valueOf(timer));
	}
	
	public boolean getIsTamed()
	{
		try {
			return (this.getBoolFromDataManager(TAMED));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setIsTamed(boolean fed)
	{
		if (fed)
			this.dataManager.set(TAMED, Boolean.valueOf(true));
		else
			this.dataManager.set(TAMED, Boolean.valueOf(false));
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack != ItemStack.EMPTY && AnimaniaHelper.isWaterContainer(stack) && !this.getSleeping())
		{
			if (!player.isCreative())
			{
				ItemStack emptied = AnimaniaHelper.emptyContainer(stack);
				stack.shrink(1);
				AnimaniaHelper.addItem(player, emptied);
			}

			this.setWatered(true);
			this.setInLove(player);
			return true;
		}
		else if (stack == ItemStack.EMPTY && this.isTamed() && !this.isSitting() && !player.isSneaking() && !this.getSleeping())
		{
			this.setSitting(true);
			this.setSitting(true);
			this.isJumping = false;
			this.navigator.clearPath();
			return true;
		}
		else if (stack == ItemStack.EMPTY && this.isTamed() && this.isSitting() && !player.isSneaking() && !this.getSleeping())
		{
			this.setSitting(false);
			this.setSitting(false);
			this.isJumping = false;
			this.navigator.clearPath();
			return true;
		}

		return super.processInteract(player, hand);
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
	{

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
			this.dataManager.set(FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		}
		else
			this.dataManager.set(FED, Boolean.valueOf(false));
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
			this.dataManager.set(WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		}
		else
			this.dataManager.set(WATERED, Boolean.valueOf(false));
	}

	public boolean isSitting()
	{
		try {
			return (this.getBoolFromDataManager(SITTING));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setSitting(boolean flag)
	{
		if (flag)
			this.dataManager.set(SITTING, Boolean.valueOf(true));
		else
			this.dataManager.set(SITTING, Boolean.valueOf(false));
	}
	
	@Override
	public void onLivingUpdate()
	{

		if (this.getLeashed() && this.getSleeping())
			this.setSleeping(false);

		if (this.getAge() == 0)
		{
			this.setAge(1);
		}

		if (this.isSitting() || this.isRiding())
		{
			if (this.getRidingEntity() != null)
				this.rotationYaw = this.getRidingEntity().rotationYaw;
			this.navigator.clearPath();
			this.navigator.setSpeed(0);
		}

		if (this.world.isRemote)
			this.eatTimer = Math.max(0, this.eatTimer - 1);

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
			{
				this.blinkTimer = 100 + this.rand.nextInt(100);
			}
		}

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

				if (!this.getFed() && !this.getWatered() && !this.getSleeping() && this.getHandFed() && AnimaniaConfig.gameRules.showUnhappyParticles)
				{
					double d = this.rand.nextGaussian() * 0.02D;
					double d1 = this.rand.nextGaussian() * 0.02D;
					double d2 = this.rand.nextGaussian() * 0.02D;
					this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + this.rand.nextFloat() * this.width - this.width, this.posY + 1.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width - this.width, d, d1, d2);
				}
			}
		}

		super.onLivingUpdate();
	}
	
	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
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

	@Override
	protected Item getDropItem()
	{
		return null;
	}

	@Override
	public boolean canBeLeashedTo(EntityPlayer player)
	{
		return true;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.5F);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return null;
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
	public Set<Item> getFoodItems()
	{
		return TEMPTATION_ITEMS;
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.type, this.gender));
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		return new ItemStack(getSpawnEgg());
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 0;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 0;
	}

	@Override
	public EntityGender getEntityGender()
	{
		return null;
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
	public int getBlinkTimer()
	{
		return this.blinkTimer;
	}
}
