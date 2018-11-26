package com.animania.common.entities.rodents;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.common.ModSoundEvents;
import com.animania.common.capabilities.CapabilityRefs;
import com.animania.common.capabilities.ICapabilityPlayer;
import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.amphibians.EntityAmphibian;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.chickens.EntityChickLeghorn;
import com.animania.common.entities.chickens.EntityChickOrpington;
import com.animania.common.entities.chickens.EntityChickPlymouthRock;
import com.animania.common.entities.chickens.EntityChickRhodeIslandRed;
import com.animania.common.entities.chickens.EntityChickWyandotte;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAIFollowOwner;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAINearestAttackableTarget;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAISwimmingSmallCreatures;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.entities.rodents.ai.EntityAIFerretFindNests;
import com.animania.common.entities.rodents.ai.EntityAIRodentEat;
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
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFerretBase extends EntityTameable implements TOPInfoProviderRodent, IAnimaniaAnimalBase
{

	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityFerretBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityFerretBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityFerretBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> SITTING = EntityDataManager.<Boolean>createKey(EntityFerretBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> RIDING = EntityDataManager.<Boolean>createKey(EntityFerretBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> AGE = EntityDataManager.<Boolean>createKey(EntityFerretBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean>createKey(EntityFerretBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float>createKey(EntityFerretBase.class, DataSerializers.FLOAT);
	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.ferretFood));
	protected int fedTimer;
	protected int wateredTimer;
	protected int happyTimer;
	protected int tamedTimer;
	public int blinkTimer;
	public int eatTimer;
	public GenericAIEatGrass<EntityFerretBase> entityAIEatGrass;
	protected int damageTimer;
	protected FerretType type;
	private int delayCount;

	public EntityFerretBase(World worldIn)
	{
		super(worldIn);
		this.setSize(.75F, .40F);
		this.stepHeight = 1.1F;
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = (AnimaniaConfig.careAndFeeding.waterTimer * 2) + this.rand.nextInt(200);
		this.happyTimer = 60;
		this.tamedTimer = 120;
		this.blinkTimer = 70 + this.rand.nextInt(70);
		this.enablePersistence();
		this.delayCount = 5;
		this.entityAIEatGrass = new GenericAIEatGrass(this, false);
		this.tasks.addTask(11, this.entityAIEatGrass);
	}

	@Override
	protected void initEntityAI()
	{
		this.aiSit = new EntityAISit(this);
		this.tasks.addTask(0, new GenericAISwimmingSmallCreatures(this));
		if (!AnimaniaConfig.gameRules.ambianceMode) {
			this.tasks.addTask(1, new GenericAIFindWater<EntityFerretBase>(this, 1.0D, entityAIEatGrass, EntityFerretBase.class, true));
			this.tasks.addTask(2, new EntityAIFerretFindNests(this, 1.0D));
			this.tasks.addTask(3, new GenericAIFindFood<EntityFerretBase>(this, 1.0D, entityAIEatGrass, false));
		}
		this.tasks.addTask(4, this.aiSit);
		this.tasks.addTask(5, new EntityAILeapAtTarget(this, 0.2F));
		this.tasks.addTask(6, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(7, new GenericAIFollowOwner<EntityFerretBase>(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(8, new GenericAIPanic<EntityFerretBase>(this, 1.5D));
		this.tasks.addTask(9, new EntityAIRodentEat(this));
		this.tasks.addTask(10, new GenericAITempt<EntityFerretBase>(this, 1.2D, false, EntityFerretBase.TEMPTATION_ITEMS));
		this.tasks.addTask(12, new GenericAIWanderAvoidWater(this, 1.2D));
		this.tasks.addTask(13, new GenericAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(14, new GenericAILookIdle<EntityFerretBase>(this));
		if (AnimaniaConfig.gameRules.animalsSleep) {
			this.tasks.addTask(15, new GenericAISleep<EntityFerretBase>(this, 0.8, AnimaniaHelper.getBlock(AnimaniaConfig.careAndFeeding.ferretBed), AnimaniaHelper.getBlock(AnimaniaConfig.careAndFeeding.ferretBed2), EntityFerretBase.class));
		}
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers) {
			this.targetTasks.addTask(1, new GenericAINearestAttackableTarget<EntityChickLeghorn>(this, EntityChickLeghorn.class, false));
			this.targetTasks.addTask(2, new GenericAINearestAttackableTarget<EntityChickOrpington>(this, EntityChickOrpington.class, false));
			this.targetTasks.addTask(3, new GenericAINearestAttackableTarget<EntityChickPlymouthRock>(this, EntityChickPlymouthRock.class, false));
			this.targetTasks.addTask(4, new GenericAINearestAttackableTarget<EntityChickRhodeIslandRed>(this, EntityChickRhodeIslandRed.class, false));
			this.targetTasks.addTask(5, new GenericAINearestAttackableTarget<EntityChickWyandotte>(this, EntityChickWyandotte.class, false));
			this.targetTasks.addTask(6, new GenericAINearestAttackableTarget<EntitySilverfish>(this, EntitySilverfish.class, false));
			this.targetTasks.addTask(7, new GenericAINearestAttackableTarget<EntityFrogs>(this, EntityFrogs.class, false));
			this.targetTasks.addTask(8, new GenericAINearestAttackableTarget<EntityToad>(this, EntityToad.class, false));
		}
		this.targetTasks.addTask(9, new EntityAIHurtByTarget(this, false, new Class[0]));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.5D);
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
	public int getVerticalFaceSpeed()
	{
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		if (!this.isTamed()) {
			this.setOwnerId(player.getPersistentID());
			this.setIsTamed(true);
			this.setTamed(true);
			this.setInLove(player);
		}

		this.setSitting(false);
		this.setFerretSitting(false);
		this.entityAIEatGrass.startExecuting();
		if (!player.capabilities.isCreativeMode)
			if (stack != ItemStack.EMPTY)
				stack.setCount(stack.getCount() - 1);

		delayCount = 10;
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
	protected ResourceLocation getLootTable()
	{
		return new ResourceLocation(Animania.MODID, "ferret");
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack != ItemStack.EMPTY && AnimaniaHelper.isWaterContainer(stack) && delayCount == 0 && !this.getSleeping())
		{
			if(!player.isCreative())
			{
				ItemStack emptied = AnimaniaHelper.emptyContainer(stack);
				stack.shrink(1);
				AnimaniaHelper.addItem(player, emptied);
			}

			this.delayCount = 5;
			this.setWatered(true);
			this.setInLove(player);
			return true;
		}
		else if (stack == ItemStack.EMPTY && this.isTamed() && !this.isFerretSitting() && !player.isSneaking() && delayCount == 0 && !this.getSleeping())
		{
			delayCount = 5;
			this.setFerretSitting(true);
			this.setSitting(true);
			this.isJumping = false;
			this.navigator.clearPath();
			return true;
		}
		else if (stack == ItemStack.EMPTY && this.isTamed() && this.isFerretSitting() && !player.isSneaking() && delayCount == 0 && !this.getSleeping())
		{

			delayCount = 5;
			this.setFerretSitting(false);
			this.setSitting(false);
			this.isJumping = false;
			this.navigator.clearPath();
			return true;
		}
		else if (stack == ItemStack.EMPTY && this.isTamed() && player.isSneaking() && delayCount == 0 && !this.getSleeping())
		{
			delayCount = 5;
			ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
			if (!props.isCarrying())
			{
				props.setAnimal(this.writeToNBT(new NBTTagCompound()));
				props.setCarrying(true);
				props.setType(EntityList.getKey(this).getResourcePath());
				this.setDead();
				player.swingArm(EnumHand.MAIN_HAND);
				Animania.network.sendToAllAround(new CapSyncPacket(props, player.getEntityId()), new NetworkRegistry.TargetPoint(player.world.provider.getDimension(), player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 64));
				return true;
			}
		}
		else if (this.isBreedingItem(stack))
		{
			this.consumeItemFromStack(player, stack);
			this.setInLove(player);
			return true;
		}
		return super.processInteract(player, hand);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);

		if (flag)
			this.applyEnchantments(this, entityIn);

		if (entityIn instanceof EntityAmphibian)
		{
			this.setFed(true);
		}

		// Custom Knockback
		if (entityIn instanceof EntityPlayer)
			((EntityLivingBase) entityIn).knockBack(this, 0.3f, this.posX - entityIn.posX, this.posZ - entityIn.posZ);

		return flag;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityFerretBase.FED, Boolean.valueOf(true));
		this.dataManager.register(EntityFerretBase.WATERED, Boolean.valueOf(true));
		this.dataManager.register(EntityFerretBase.TAMED, Boolean.valueOf(false));
		this.dataManager.register(EntityFerretBase.SITTING, Boolean.valueOf(false));
		this.dataManager.register(EntityFerretBase.RIDING, Boolean.valueOf(false));
		this.dataManager.register(EntityFerretBase.AGE, Boolean.valueOf(false));
		this.dataManager.register(EntityFerretBase.SLEEPING, Boolean.valueOf(false));
		this.dataManager.register(EntityFerretBase.SLEEPTIMER, Float.valueOf(0.0F));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setBoolean("IsTamed", this.getIsTamed());
		compound.setBoolean("IsSitting", this.isFerretSitting());
		compound.setBoolean("Riding", this.isFerretRiding());
		compound.setBoolean("Age", this.getAge());
		compound.setBoolean("Sleep", this.getSleeping());
		compound.setFloat("SleepTimer", this.getSleepTimer());

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
		this.setFerretSitting(compound.getBoolean("IsSitting"));
		this.setFerretRiding(compound.getBoolean("Riding"));
		this.setAge(compound.getBoolean("Age"));
		this.setSleeping(compound.getBoolean("Sleep"));
		this.setSleepTimer(compound.getFloat("SleepTimer"));

	}

	public boolean getAge()
	{
		try {
			return (this.getBoolFromDataManager(AGE));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setAge(boolean age)
	{
		this.dataManager.set(EntityFerretBase.AGE, Boolean.valueOf(age));
	}

	public boolean getSleeping()
	{
		try {
			return (this.getBoolFromDataManager(SLEEPING));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setSleeping(boolean flag)
	{
		if (flag)
		{
			this.dataManager.set(EntityFerretBase.SLEEPING, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(EntityFerretBase.SLEEPING, Boolean.valueOf(false));
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
		this.dataManager.set(EntityFerretBase.SLEEPTIMER, Float.valueOf(timer));
	}


	@Override
	public boolean canBeLeashedTo(EntityPlayer player)
	{
		return true;
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
			num = 10;
		else if (happy == 1)
			num = 20;
		else
			num = 40;

		int chooser = Animania.RANDOM.nextInt(num);

		if (chooser == 0)
			return ModSoundEvents.ferretLiving1;
		else if (chooser == 1)
			return ModSoundEvents.ferretLiving2;
		else if (chooser == 2)
			return ModSoundEvents.ferretLiving3;
		else if (chooser == 3)
			return ModSoundEvents.ferretLiving4;
		else if (chooser == 4)
			return ModSoundEvents.ferretLiving5;
		else if (chooser == 5)
			return ModSoundEvents.ferretLiving6;
		else
			return null;

	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		int chooser = Animania.RANDOM.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.ferretHurt1;
		else if (chooser == 1)
			return ModSoundEvents.ferretHurt1;
		else
			return null;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.ferretHurt1;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
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

		if (this.isFerretRiding())
		{
			this.setFerretRiding(true);
			this.startRiding(par1Entity);
		}
		else if (!this.isFerretRiding())
			this.dismountRidingEntity();

	}

	@Override
	public void onLivingUpdate()
	{
		if (!this.getAge()) {
			this.setAge(true);
		}

		delayCount--;
		if (delayCount <= 0) {
			delayCount = 0;
		}

		if (this.isFerretSitting() || this.isRiding())
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
				this.blinkTimer = 100 + this.rand.nextInt(100);
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

				if (!this.getFed() && !this.getWatered() && !this.getSleeping() && this.getIsTamed() && AnimaniaConfig.gameRules.showUnhappyParticles)
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
					//this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + this.rand.nextFloat() * this.width - this.width, this.posY + 1D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width - this.width, d, d1, d2);
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

	public boolean isFerretSitting()
	{
		try {
			return (this.getBoolFromDataManager(SITTING));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setFerretSitting(boolean flag)
	{
		if (flag)
			this.dataManager.set(EntityFerretBase.SITTING, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityFerretBase.SITTING, Boolean.valueOf(false));
	}

	public boolean isFerretRiding()
	{
		try {
			return (this.getBoolFromDataManager(RIDING));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setFerretRiding(boolean flag)
	{
		if (flag)
			this.dataManager.set(EntityFerretBase.RIDING, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityFerretBase.RIDING, Boolean.valueOf(false));
	}

	public boolean getFed()
	{
		try {
			return (this.getBoolFromDataManager(FED));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityFerretBase.FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		}
		else
			this.dataManager.set(EntityFerretBase.FED, Boolean.valueOf(false));
	}

	public boolean getWatered()
	{
		try {
			return (this.getBoolFromDataManager(WATERED));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(EntityFerretBase.WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		}
		else
			this.dataManager.set(EntityFerretBase.WATERED, Boolean.valueOf(false));
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
			this.dataManager.set(EntityFerretBase.TAMED, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityFerretBase.TAMED, Boolean.valueOf(false));
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
	public EntityFerretBase createChild(EntityAgeable ageable)
	{
		return null;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && EntityFerretBase.TEMPTATION_ITEMS.contains(stack.getItem());
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.type, EntityGender.NONE));
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		return new ItemStack(getSpawnEgg());
	}

	@Override
	public int getPrimaryEggColor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSecondaryEggColor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EntityGender getEntityGender()
	{
		return EntityGender.NONE;
	}

	// ==================================================
	//     Data Manager Trapper (borrowed from Lycanites)
	// ==================================================

	public boolean getBoolFromDataManager(DataParameter<Boolean> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return false;
		}
	}

	public byte getByteFromDataManager(DataParameter<Byte> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return 0;
		}
	}

	public int getIntFromDataManager(DataParameter<Integer> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return 0;
		}
	}

	public float getFloatFromDataManager(DataParameter<Float> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return 0;
		}
	}

	public String getStringFromDataManager(DataParameter<String> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return null;
		}
	}

	public Optional<UUID> getUUIDFromDataManager(DataParameter<Optional<UUID>> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return null;
		}
	}

	public ItemStack getItemStackFromDataManager(DataParameter<ItemStack> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return ItemStack.EMPTY;
		}
	}

	public Optional<BlockPos> getBlockPosFromDataManager(DataParameter<Optional<BlockPos>> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return Optional.absent();
		}
	}

	@Override
	public void setHandFed(boolean handfed)
	{
		setFed(handfed);
	}

	@Override
	public boolean getHandFed()
	{
		return getFed();
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
