package com.animania.common.entities.rodents.rabbits;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.generic.ai.GenericAIAvoidEntity;
import com.animania.common.entities.generic.ai.GenericAIAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.entities.interfaces.IAnimaniaAnimalBase;
import com.animania.common.entities.rodents.ai.EntityAISleepRabbits;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCarrot;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaRabbit extends EntityRabbit implements IAnimaniaAnimalBase
{

	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.rabbitFood));
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaRabbit.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaRabbit.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityAnimaniaRabbit.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer>createKey(EntityAnimaniaRabbit.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityAnimaniaRabbit.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> HANDFED = EntityDataManager.<Boolean>createKey(EntityAnimaniaRabbit.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean>createKey(EntityAnimaniaRabbit.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float>createKey(EntityAnimaniaRabbit.class, DataSerializers.FLOAT);
	private static final String[] RABBIT_TEXTURES = new String[] { "black", "brown", "golden", "olive", "patch_black", "patch_brown", "patch_grey" };

	protected int happyTimer;
	public int blinkTimer;
	public int eatTimer;
	protected int fedTimer;
	protected int wateredTimer;
	protected int damageTimer;
	public RabbitType rabbitType;
	public GenericAIEatGrass entityAIEatGrass;
	protected boolean mateable = false;
	protected EntityGender gender;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;

	private int jumpTicks;
	private int jumpDuration;
	private boolean wasOnGround;
	private int currentMoveTypeDuration;

	private boolean mateUuidCached = false;
	private UUID mateUUID = null;

	public EntityAnimaniaRabbit(World worldIn)
	{
		super(worldIn);
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new GenericAIEatGrass(this, false);

		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.tasks.addTask(2, new GenericAIFindWater(this, 1.4D, entityAIEatGrass, EntityAnimaniaRabbit.class, true));
			this.tasks.addTask(3, new GenericAIFindFood(this, 1.4D, entityAIEatGrass, true));
		}

		if (!this.getCustomNameTag().equals("Killer"))
		{
			this.tasks.addTask(3, new GenericAIPanic(this, 2.5D));
			this.tasks.addTask(4, new GenericAIWanderAvoidWater(this, 1.8D));
			this.tasks.addTask(5, new EntityAISwimming(this));
			this.tasks.addTask(7, new GenericAITempt(this, 1.25D, false, EntityAnimaniaRabbit.TEMPTATION_ITEMS));
			this.tasks.addTask(8, this.entityAIEatGrass);
			this.tasks.addTask(9, new GenericAIAvoidEntity(this, EntityWolf.class, 24.0F, 3.0D, 3.5D));
			this.tasks.addTask(9, new GenericAIAvoidEntity(this, EntityMob.class, 16.0F, 2.2D, 2.2D));
			this.tasks.addTask(10, new GenericAIWatchClosest(this, EntityPlayer.class, 6.0F));
			this.tasks.addTask(11, new GenericAILookIdle(this));
			this.tasks.addTask(11, new GenericAIAvoidWater(this));
			if (AnimaniaConfig.gameRules.animalsSleep)
			{
				this.tasks.addTask(12, new EntityAISleepRabbits(this, 0.8));
			}
		}
		else
		{
			this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.7F));
			this.tasks.addTask(2, new EntityAIAttackMelee(this, 2.0D, true));
			this.tasks.addTask(3, new GenericAIWanderAvoidWater(this, 1.8D));
			this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 20.0F));
			this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
			this.setHealth(50);
		}

		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 100 + this.rand.nextInt(100);
		this.enablePersistence();

		this.jumpHelper = new EntityAnimaniaRabbit.RabbitJumpHelper(this);
		this.moveHelper = new EntityAnimaniaRabbit.RabbitMoveHelper(this);
		this.setMovementSpeed(0.0D);
	}

	protected void resetAI()
	{

		this.tasks.taskEntries.clear();
		this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.7F));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 2.0D, true));
		this.tasks.addTask(3, new GenericAIWanderAvoidWater(this, 1.8D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		this.setHealth(50);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.34000001192092896D);
	}

	@Override
	public void setPosition(double x, double y, double z)
	{
		super.setPosition(x, y, z);
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
	{
		super.setAttackTarget(entitylivingbaseIn);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{

		if (this.getSleeping())
		{
			this.setSleeping(false);
		}

		if (this.isEntityInvulnerable(source))
			return false;
		else
			return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSourceHandler.killerRabbitDamage, 5.0F);
		entityIn.attackEntityFrom(DamageSourceHandler.killerRabbitDamage, 5.0F);

		if (flag)
		{
			this.applyEnchantments(this, entityIn);
		}

		// Custom Knockback
		if (entityIn instanceof EntityPlayer)
		{
			((EntityLivingBase) entityIn).knockBack(this, 1, this.posX - entityIn.posX, this.posZ - entityIn.posZ);
		}

		return flag;
	}

	protected float getJumpUpwardsMotion()
	{
		if (!this.collidedHorizontally && (!this.moveHelper.isUpdating() || this.moveHelper.getY() <= this.posY + 0.4D))
		{
			Path path = this.navigator.getPath();

			if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
			{
				Vec3d vec3d = path.getPosition(this);

				if (vec3d.y > this.posY + 0.4D)
				{
					return 0.4F;
				}
			}

			return this.moveHelper.getSpeed() <= 0.6D ? 0.2F : 0.3F;
		}
		else
		{
			return 0.4F;
		}
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
				this.moveRelative(0.0F, 1.5F, 0.1F, 0.0f);
			}
		}

		if (!this.world.isRemote)
		{
			this.world.setEntityState(this, (byte) 1);
		}
	}

	@SideOnly(Side.CLIENT)
	public float setJumpCompletion(float p_175521_1_)
	{
		return this.jumpDuration == 0 ? 0.0F : ((float) this.jumpTicks + p_175521_1_) / (float) this.jumpDuration;
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
			// this.playSound(this.getJumpSound(), this.getSoundVolume(),
			// ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) *
			// 0.8F);
		}
	}

	public void startJumping()
	{
		this.setJumping(true);
		this.jumpDuration = 20;
		this.jumpTicks = 0;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		if (this instanceof EntityRabbitBuckLop || this instanceof EntityRabbitDoeLop || this instanceof EntityRabbitKitLop)
		{
			this.dataManager.register(EntityAnimaniaRabbit.COLOR_NUM, Integer.valueOf(rand.nextInt(7)));
		}
		else
		{
			this.dataManager.register(EntityAnimaniaRabbit.COLOR_NUM, 0);
		}
		this.dataManager.register(EntityAnimaniaRabbit.FED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaRabbit.HANDFED, Boolean.valueOf(false));
		this.dataManager.register(EntityAnimaniaRabbit.WATERED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaRabbit.MATE_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(EntityAnimaniaRabbit.AGE, Integer.valueOf(0));
		this.dataManager.register(EntityAnimaniaRabbit.SLEEPING, Boolean.valueOf(false));
		this.dataManager.register(EntityAnimaniaRabbit.SLEEPTIMER, Float.valueOf(0.0F));
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityRabbitKitBase ? null : this.rabbitType.isPrime ? new ResourceLocation(Animania.MODID, "rabbit_prime") : new ResourceLocation(Animania.MODID, "rabbit_regular");
	}

	public void updateAITasks()
	{

		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();

		if (this.currentMoveTypeDuration > 0)
		{
			--this.currentMoveTypeDuration;
		}

		if (this.onGround)
		{
			if (!this.wasOnGround)
			{
				this.setJumping(false);
				this.checkLandingDelay();
			}

			if (/* this.getRabbitType() == 99 && */ this.currentMoveTypeDuration == 0)
			{
				EntityLivingBase entitylivingbase = this.getAttackTarget();

				if (entitylivingbase != null && this.getDistanceSq(entitylivingbase) < 16.0D)
				{
					this.calculateRotationYaw(entitylivingbase.posX, entitylivingbase.posZ);
					this.moveHelper.setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, this.moveHelper.getSpeed());
					this.startJumping();
					this.wasOnGround = true;
				}
			}

			EntityAnimaniaRabbit.RabbitJumpHelper entityrabbit$rabbitjumphelper = (EntityAnimaniaRabbit.RabbitJumpHelper) this.jumpHelper;

			if (!entityrabbit$rabbitjumphelper.getIsJumping())
			{
				if (this.moveHelper.isUpdating() && this.currentMoveTypeDuration == 0)
				{
					Path path = this.navigator.getPath();
					Vec3d vec3d = new Vec3d(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ());

					if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
					{
						vec3d = path.getPosition(this);
					}

					this.calculateRotationYaw(vec3d.x, vec3d.z);
					this.startJumping();
				}
			}
			else if (!entityrabbit$rabbitjumphelper.canJump())
			{
				this.enableJumpControl();
			}
		}

		this.wasOnGround = this.onGround;
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		this.setHandFed(true);
		this.entityAIEatGrass.startExecuting();
		this.eatTimer = 80;
		player.addStat(rabbitType.getAchievement(), 1);

		if (!player.isCreative())
			stack.shrink(1);
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
		this.dataManager.set(EntityAnimaniaRabbit.AGE, Integer.valueOf(age));
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
			this.dataManager.set(EntityAnimaniaRabbit.SLEEPING, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(EntityAnimaniaRabbit.SLEEPING, Boolean.valueOf(false));
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
		this.dataManager.set(EntityAnimaniaRabbit.SLEEPTIMER, Float.valueOf(timer));
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
		this.dataManager.set(EntityAnimaniaRabbit.HANDFED, Boolean.valueOf(handfed));
	}

	@Nullable
	public UUID getMateUniqueId()
	{
		if (mateable)
		{
			if(this.mateUuidCached)
				return this.mateUUID;
			try
			{
				UUID id = (UUID) ((Optional) this.dataManager.get(EntityAnimaniaRabbit.MATE_UNIQUE_ID)).orNull();
				this.mateUUID = id;
				this.mateUuidCached = true;
				return id;
			}
			catch (Exception e)
			{
				return null;
			}
		}
		return null;
	}

	public void setMateUniqueId(@Nullable UUID uniqueId)
	{
		this.mateUUID = uniqueId;
		this.mateUuidCached = true;
		this.dataManager.set(EntityAnimaniaRabbit.MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
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
			this.dataManager.set(EntityAnimaniaRabbit.FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		}
		else
			this.dataManager.set(EntityAnimaniaRabbit.FED, Boolean.valueOf(false));
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
			this.dataManager.set(EntityAnimaniaRabbit.WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		}
		else
			this.dataManager.set(EntityAnimaniaRabbit.WATERED, Boolean.valueOf(false));
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	public int getColorNumber()
	{
		try
		{
			return (this.getIntFromDataManager(COLOR_NUM));
		}
		catch (Exception e)
		{
			return 0;
		}
	}

	public void setColorNumber(int color)
	{
		this.dataManager.set(COLOR_NUM, Integer.valueOf(color));
	}

	public ResourceLocation getResourceLocation()
	{
		return resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink()
	{
		return resourceLocationBlink;
	}

	private void calculateRotationYaw(double x, double z)
	{
		this.rotationYaw = (float) (MathHelper.atan2(z - this.posZ, x - this.posX) * (180D / Math.PI)) - 90.0F;
	}

	private void enableJumpControl()
	{
		((EntityAnimaniaRabbit.RabbitJumpHelper) this.jumpHelper).setCanJump(true);
	}

	private void disableJumpControl()
	{
		((EntityAnimaniaRabbit.RabbitJumpHelper) this.jumpHelper).setCanJump(false);
	}

	private void updateMoveTypeDuration()
	{
		if (this.moveHelper.getSpeed() < 2.2D)
		{
			this.currentMoveTypeDuration = 10;
		}
		else
		{
			this.currentMoveTypeDuration = 1;
		}
	}

	private void checkLandingDelay()
	{
		this.updateMoveTypeDuration();
		this.disableJumpControl();
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getAge() == 0)
		{
			this.setAge(1);
		}

		if (this.getCustomNameTag().equals("Killer"))
		{
			for (Object a : this.tasks.taskEntries.toArray())
			{
				EntityAIBase ai = ((EntityAITaskEntry) a).action;
				if (ai instanceof GenericAIPanic)
				{
					this.resetAI();
				}
			}
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

				if (!this.getFed() && !this.getWatered() && !this.getSleeping() && !this.getHandFed() && AnimaniaConfig.gameRules.showUnhappyParticles)
				{
					double d = this.rand.nextGaussian() * 0.001D;
					double d1 = this.rand.nextGaussian() * 0.001D;
					double d2 = this.rand.nextGaussian() * 0.001D;
					this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + this.rand.nextFloat() * this.width - this.width, this.posY + 1.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width - this.width, d, d1, d2);
				}
			}
		}

		if (this.jumpTicks != this.jumpDuration)
		{
			++this.jumpTicks;
		}
		else if (this.jumpDuration != 0)
		{
			this.jumpTicks = 0;
			this.jumpDuration = 0;
			this.setJumping(false);
		}

		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.NAME_TAG)
		{
			if (!stack.hasDisplayName())
			{
				return false;
			}
			else
			{
				EntityLiving entityliving = (EntityLiving) this;
				entityliving.setCustomNameTag(stack.getDisplayName());

				entityliving.enablePersistence();
				if (!player.capabilities.isCreativeMode)
				{
					stack.shrink(1);
				}

				if (stack.getDisplayName().equals("Killer"))
				{
					this.resetAI();
					this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
					this.setHealth(50);
				}

			}

		}
		else if (stack != ItemStack.EMPTY && AnimaniaHelper.isWaterContainer(stack) && !this.getSleeping())
		{

			if (!player.isCreative())
			{
				ItemStack emptied = AnimaniaHelper.emptyContainer(stack);
				stack.shrink(1);
				AnimaniaHelper.addItem(player, emptied);
			}

			this.eatTimer = 40;
			if (entityAIEatGrass != null)
				this.entityAIEatGrass.startExecuting();
			this.setWatered(true);
			this.setInLove(player);
			return true;

		}
		else if (stack != ItemStack.EMPTY && stack.getItem() == Items.BUCKET && !this.getSleeping())
		{
			return false;
		}
		else if (this.isBreedingItem(stack))
		{
			this.consumeItemFromStack(player, stack);
			this.setInLove(player);
			return true;
		}
		else
		{
			return super.processInteract(player, hand);
		}
		return super.processInteract(player, hand);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
			this.eatTimer = 160;
		else
			super.handleStatusUpdate(id);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		if (this.getMateUniqueId() != null)
		{
			compound.setString("MateUUID", this.getMateUniqueId().toString());
		}
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Handfed", this.getHandFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setInteger("ColorNumber", getColorNumber());
		compound.setInteger("Age", this.getAge());
		compound.setBoolean("Sleep", this.getSleeping());
		compound.setFloat("SleepTimer", this.getSleepTimer());

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		String s;

		if (compound.hasKey("MateUUID", 8))
		{
			s = compound.getString("MateUUID");
		}
		else
		{
			String s1 = compound.getString("Mate");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty())
		{
			this.setMateUniqueId(UUID.fromString(s));
		}

		this.setColorNumber(compound.getInteger("ColorNumber"));
		this.setFed(compound.getBoolean("Fed"));
		this.setHandFed(compound.getBoolean("Handfed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setAge(compound.getInteger("Age"));
		this.setSleeping(compound.getBoolean("Sleep"));
		this.setSleepTimer(compound.getFloat("SleepTimer"));

	}

	static class AIAvoidEntity<T extends Entity> extends GenericAIAvoidEntity<T>
	{
		private final EntityAnimaniaRabbit entityInstance;

		public AIAvoidEntity(EntityAnimaniaRabbit rabbit, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_)
		{
			super(rabbit, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
			this.entityInstance = rabbit;
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute()
		{
			return true; // this.entityInstance.getRabbitType() != 99 &&
			// super.shouldExecute();
		}
	}

	static class AIEvilAttack extends EntityAIAttackMelee
	{
		public AIEvilAttack(EntityAnimaniaRabbit rabbit)
		{
			super(rabbit, 1.4D, true);
		}

		protected double getAttackReachSqr(EntityLivingBase attackTarget)
		{
			return (double) (4.0F + attackTarget.width);
		}
	}

	static class AIPanic extends GenericAIPanic
	{
		private final EntityAnimaniaRabbit theEntity;

		public AIPanic(EntityAnimaniaRabbit rabbit, double speedIn)
		{
			super(rabbit, speedIn);
			this.theEntity = rabbit;
		}

		/**
		 * Updates the task
		 */
		public void updateTask()
		{
			super.updateTask();
			this.theEntity.setMovementSpeed(this.speed);
		}
	}

	static class AIRaidFarm extends EntityAIMoveToBlock
	{
		private final EntityAnimaniaRabbit rabbit;
		private boolean wantsToRaid;
		private boolean canRaid;

		public AIRaidFarm(EntityAnimaniaRabbit rabbitIn)
		{
			super(rabbitIn, 0.699999988079071D, 16);
			this.rabbit = rabbitIn;
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute()
		{
			if (this.runDelay <= 0)
			{
				if (!this.rabbit.world.getGameRules().getBoolean("mobGriefing"))
				{
					return false;
				}

				this.canRaid = false;
				// this.wantsToRaid = this.rabbit.isCarrotEaten();
				this.wantsToRaid = true;
			}

			return super.shouldExecute();
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean shouldContinueExecuting()
		{
			return this.canRaid && super.shouldContinueExecuting();
		}

		/**
		 * Updates the task
		 */
		public void updateTask()
		{
			super.updateTask();
			this.rabbit.getLookHelper().setLookPosition((double) this.destinationBlock.getX() + 0.5D, (double) (this.destinationBlock.getY() + 1), (double) this.destinationBlock.getZ() + 0.5D, 10.0F, (float) this.rabbit.getVerticalFaceSpeed());

			if (this.getIsAboveDestination())
			{
				World world = this.rabbit.world;
				BlockPos blockpos = this.destinationBlock.up();
				IBlockState iblockstate = world.getBlockState(blockpos);
				Block block = iblockstate.getBlock();

				if (this.canRaid && block instanceof BlockCarrot)
				{
					Integer integer = (Integer) iblockstate.getValue(BlockCarrot.AGE);

					if (integer.intValue() == 0)
					{
						world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
						world.destroyBlock(blockpos, true);
					}
					else
					{
						world.setBlockState(blockpos, iblockstate.withProperty(BlockCarrot.AGE, Integer.valueOf(integer.intValue() - 1)), 2);
						world.playEvent(2001, blockpos, Block.getStateId(iblockstate));
					}

					// this.rabbit.createEatingParticles();
				}

				this.canRaid = false;
				this.runDelay = 10;
			}
		}

		/**
		 * Return true to set given position as destination
		 */
		protected boolean shouldMoveTo(World worldIn, BlockPos pos)
		{
			Block block = worldIn.getBlockState(pos).getBlock();

			if (block == Blocks.FARMLAND && this.wantsToRaid && !this.canRaid)
			{
				pos = pos.up();
				IBlockState iblockstate = worldIn.getBlockState(pos);
				block = iblockstate.getBlock();

				if (block instanceof BlockCarrot && ((BlockCarrot) block).isMaxAge(iblockstate))
				{
					this.canRaid = true;
					return true;
				}
			}

			return false;
		}
	}

	public class RabbitJumpHelper extends EntityJumpHelper
	{
		private final EntityAnimaniaRabbit theEntity;
		private boolean canJump;

		public RabbitJumpHelper(EntityAnimaniaRabbit rabbit)
		{
			super(rabbit);
			this.theEntity = rabbit;
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
		private final EntityAnimaniaRabbit theEntity;
		private double nextJumpSpeed;

		public RabbitMoveHelper(EntityAnimaniaRabbit rabbit)
		{
			super(rabbit);
			this.theEntity = rabbit;
		}

		public void onUpdateMoveHelper()
		{
			if (this.theEntity.onGround && !this.theEntity.isJumping && !((EntityAnimaniaRabbit.RabbitJumpHelper) this.theEntity.jumpHelper).getIsJumping())
			{
				this.theEntity.setMovementSpeed(0.0D);
			}
			else if (this.isUpdating())
			{
				this.theEntity.setMovementSpeed(this.nextJumpSpeed);
			}

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

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && EntityAnimaniaRabbit.TEMPTATION_ITEMS.contains(stack.getItem());
	}

	@Override
	public EntityAnimaniaRabbit createChild(EntityAgeable ageable)
	{
		return null;
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.rabbitType, this.gender));
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
		return this.gender;
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

	@Override
	public Class[] getFoodBlocks()
	{
		return new Class[] { BlockCarrot.class, BlockTallGrass.class, BlockFlower.class };
	}

}
