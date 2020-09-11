package com.animania.addons.extra.common.entity.rodents.rabbits;

import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitBuckLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitDoeLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitKitLop;
import com.animania.addons.extra.common.handler.ExtraAddonSoundHandler;
import com.animania.addons.extra.config.ExtraConfig;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.api.interfaces.IConvertable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIAvoidEntity;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
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
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaRabbit extends EntityRabbit implements IAnimaniaAnimalBase, IConvertable
{

	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(ExtraConfig.settings.rabbitFood));
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean> createKey(EntityAnimaniaRabbit.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean> createKey(EntityAnimaniaRabbit.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer> createKey(EntityAnimaniaRabbit.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer> createKey(EntityAnimaniaRabbit.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> HANDFED = EntityDataManager.<Boolean> createKey(EntityAnimaniaRabbit.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean> createKey(EntityAnimaniaRabbit.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float> createKey(EntityAnimaniaRabbit.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> INTERACTED = EntityDataManager.<Boolean> createKey(EntityAnimaniaRabbit.class, DataSerializers.BOOLEAN);

	private static final String[] RABBIT_TEXTURES = new String[] { "black", "brown", "golden", "olive", "patch_black", "patch_brown", "patch_grey" };

	protected int happyTimer;
	public int blinkTimer;
	public int eatTimer;
	protected int fedTimer;
	protected int wateredTimer;
	protected int damageTimer;
	public RabbitType rabbitType;
	public GenericAIEatGrass<EntityAnimaniaRabbit> entityAIEatGrass;
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
		this.entityAIEatGrass = new GenericAIEatGrass<EntityAnimaniaRabbit>(this, false);

		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.tasks.addTask(2, new GenericAIFindWater<EntityAnimaniaRabbit>(this, 1.4D, entityAIEatGrass, EntityAnimaniaRabbit.class, true));
			this.tasks.addTask(3, new GenericAIFindFood<EntityAnimaniaRabbit>(this, 1.4D, entityAIEatGrass, true));
		}

		if (!this.getCustomNameTag().equals("Killer"))
		{
			this.tasks.addTask(3, new GenericAIPanic<EntityAnimaniaRabbit>(this, 2.5D));
			this.tasks.addTask(4, new GenericAIWanderAvoidWater(this, 1.8D));
			this.tasks.addTask(5, new EntityAISwimming(this));
			this.tasks.addTask(7, new GenericAITempt<EntityAnimaniaRabbit>(this, 1.25D, false, EntityAnimaniaRabbit.TEMPTATION_ITEMS));
			this.tasks.addTask(8, this.entityAIEatGrass);
			this.tasks.addTask(9, new GenericAIAvoidEntity<EntityWolf>(this, EntityWolf.class, 24.0F, 3.0D, 3.5D));
			this.tasks.addTask(9, new GenericAIAvoidEntity<EntityMob>(this, EntityMob.class, 16.0F, 2.2D, 2.2D));
			this.tasks.addTask(10, new GenericAIWatchClosest(this, EntityPlayer.class, 6.0F));
			this.tasks.addTask(11, new GenericAILookIdle<EntityAnimaniaRabbit>(this));
			if (AnimaniaConfig.gameRules.animalsSleep)
			{
				this.tasks.addTask(12, new GenericAISleep(this, 0.8, Block.getBlockFromName(ExtraConfig.settings.rabbitBed), Block.getBlockFromName(ExtraConfig.settings.rabbitBed2), EntityAnimaniaRabbit.class, new Function<Long, Boolean>() {

					@Override
					public Boolean apply(Long worldtime)
					{		
						return (worldtime > 20000 && worldtime < 24000) || (worldtime > 10000 && worldtime < 15000);
					}
					
				}));
					
			}
		}else

	{
		this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.7F));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 2.0D, true));
		this.tasks.addTask(3, new GenericAIWanderAvoidWater(this, 1.8D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 20.0F));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		this.setHealth(50);
	}

	this.fedTimer=AnimaniaConfig.careAndFeeding.feedTimer+this.rand.nextInt(100);this.wateredTimer=AnimaniaConfig.careAndFeeding.waterTimer+this.rand.nextInt(100);this.happyTimer=60;this.blinkTimer=100+this.rand.nextInt(100);this.enablePersistence();

	this.jumpHelper=new EntityAnimaniaRabbit.RabbitJumpHelper(this);this.moveHelper=new EntityAnimaniaRabbit.RabbitMoveHelper(this);this.setMovementSpeed(0.0D);
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

	@Override
	protected void initEntityAI()
	{
	}

	@Override
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

	@Override
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
		} else
		{
			return 0.4F;
		}
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, ExtraAddonSoundHandler.rabbit1, ExtraAddonSoundHandler.rabbit2, ExtraAddonSoundHandler.rabbit3, ExtraAddonSoundHandler.rabbit4);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(ExtraAddonSoundHandler.rabbitHurt1, ExtraAddonSoundHandler.rabbitHurt2);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GenericBehavior.getRandomSound(ExtraAddonSoundHandler.rabbitHurt1, ExtraAddonSoundHandler.rabbitHurt2);
	}

	@Override
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

	@Override
	@SideOnly(Side.CLIENT)
	public float setJumpCompletion(float p_175521_1_)
	{
		return this.jumpDuration == 0 ? 0.0F : (this.jumpTicks + p_175521_1_) / this.jumpDuration;
	}

	@Override
	public void setMovementSpeed(double newSpeed)
	{
		this.getNavigator().setSpeed(newSpeed);
		this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), newSpeed);
	}

	@Override
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

	@Override
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
		if (this instanceof EntityRabbitBuckLop || this instanceof EntityRabbitKitLop || this instanceof EntityRabbitDoeLop)
		{
			this.dataManager.register(EntityAnimaniaRabbit.COLOR_NUM, Integer.valueOf(rand.nextInt(7)));
		} else
		{
			this.dataManager.register(EntityAnimaniaRabbit.COLOR_NUM, 0);
		}
		this.dataManager.register(EntityAnimaniaRabbit.FED, true);
		this.dataManager.register(EntityAnimaniaRabbit.HANDFED, false);
		this.dataManager.register(EntityAnimaniaRabbit.WATERED, true);
		this.dataManager.register(EntityAnimaniaRabbit.AGE, Integer.valueOf(0));
		this.dataManager.register(EntityAnimaniaRabbit.SLEEPING, false);
		this.dataManager.register(EntityAnimaniaRabbit.SLEEPTIMER, Float.valueOf(0.0F));
		this.dataManager.register(INTERACTED, false);
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityRabbitKitBase ? null : this.rabbitType.isPrime ? new ResourceLocation("extra/" + Animania.MODID, "rabbit_prime") : new ResourceLocation("extra/" + Animania.MODID, "rabbit_regular");
	}

	@Override
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
			} else if (!entityrabbit$rabbitjumphelper.canJump())
			{
				this.enableJumpControl();
			}
		}

		this.wasOnGround = this.onGround;
	}

	@Override
	public DataParameter<Integer> getAgeParam()
	{
		return AGE;
	}

	@Override
	public DataParameter<Boolean> getSleepingParam()
	{
		return SLEEPING;
	}

	@Override
	public DataParameter<Float> getSleepTimerParam()
	{
		return SLEEPTIMER;
	}

	@Override
	public DataParameter<Boolean> getHandFedParam()
	{
		return HANDFED;
	}

	@Override
	public DataParameter<Boolean> getFedParam()
	{
		return FED;
	}

	@Override
	public DataParameter<Boolean> getWateredParam()
	{
		return WATERED;
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	public int getColorNumber()
	{
		return this.getIntFromDataManager(COLOR_NUM);
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
		} else
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
		GenericBehavior.livingUpdateCommon(this);

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

		if (this.jumpTicks != this.jumpDuration)
		{
			++this.jumpTicks;
		} else if (this.jumpDuration != 0)
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
			} else
			{
				EntityLiving entityliving = this;
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

		return GenericBehavior.interactCommon(this, entityplayer, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
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
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);

		compound.setInteger("ColorNumber", getColorNumber());

		GenericBehavior.writeCommonNBT(compound, this);

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		this.setColorNumber(compound.getInteger("ColorNumber"));

		GenericBehavior.readCommonNBT(compound, this);
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
		@Override
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

		@Override
		protected double getAttackReachSqr(EntityLivingBase attackTarget)
		{
			return 4.0F + attackTarget.width;
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
		@Override
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
		@Override
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
		@Override
		public boolean shouldContinueExecuting()
		{
			return this.canRaid && super.shouldContinueExecuting();
		}

		/**
		 * Updates the task
		 */
		@Override
		public void updateTask()
		{
			super.updateTask();
			this.rabbit.getLookHelper().setLookPosition(this.destinationBlock.getX() + 0.5D, this.destinationBlock.getY() + 1, this.destinationBlock.getZ() + 0.5D, 10.0F, this.rabbit.getVerticalFaceSpeed());

			if (this.getIsAboveDestination())
			{
				World world = this.rabbit.world;
				BlockPos blockpos = this.destinationBlock.up();
				IBlockState iblockstate = world.getBlockState(blockpos);
				Block block = iblockstate.getBlock();

				if (this.canRaid && block instanceof BlockCarrot)
				{
					Integer integer = iblockstate.getValue(BlockCarrot.AGE);

					if (integer.intValue() == 0)
					{
						world.setBlockState(blockpos, Blocks.AIR.getDefaultState(), 2);
						world.destroyBlock(blockpos, true);
					} else
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
		@Override
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
		@Override
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

		@Override
		public void onUpdateMoveHelper()
		{
			if (this.theEntity.onGround && !this.theEntity.isJumping && !((EntityAnimaniaRabbit.RabbitJumpHelper) this.theEntity.jumpHelper).getIsJumping())
			{
				this.theEntity.setMovementSpeed(0.0D);
			} else if (this.isUpdating())
			{
				this.theEntity.setMovementSpeed(this.nextJumpSpeed);
			}

			super.onUpdateMoveHelper();
		}

		/**
		 * Sets the speed and location to move to
		 */
		@Override
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
		return stack != ItemStack.EMPTY && AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, stack);
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

	@Override
	public Set<ItemStack> getFoodItems()
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
	public void setBlinkTimer(int i)
	{
		blinkTimer = i;
	}

	@Override
	public Class[] getFoodBlocks()
	{
		return new Class[] { BlockCarrot.class, BlockTallGrass.class, BlockFlower.class };
	}

	@Override
	public int getEatTimer()
	{
		return eatTimer;
	}

	@Override
	public void setEatTimer(int i)
	{
		eatTimer = i;
	}

	@Override
	public int getFedTimer()
	{
		return fedTimer;
	}

	@Override
	public void setFedTimer(int i)
	{
		fedTimer = i;
	}

	@Override
	public DataParameter<Boolean> getInteractedParam()
	{
		return INTERACTED;
	}

	@Override
	public int getWaterTimer()
	{
		return wateredTimer;
	}

	@Override
	public void setWaterTimer(int i)
	{
		wateredTimer = i;
	}

	@Override
	public int getDamageTimer()
	{
		return damageTimer;
	}

	@Override
	public void setDamageTimer(int i)
	{
		damageTimer = i;
	}

	@Override
	public int getHappyTimer()
	{
		return happyTimer;
	}

	@Override
	public void setHappyTimer(int i)
	{
		happyTimer = i;
	}

	@Override
	public AnimaniaType getAnimalType()
	{
		return this.rabbitType;
	}

	@Override
	public Entity convertToVanilla()
	{
		EntityRabbit entity = new EntityRabbit(this.world);
		entity.setPosition(this.posX, this.posY, this.posZ);
		if (entity.hasCustomName())
			entity.setCustomNameTag(this.getCustomNameTag());
		return entity;
	}

}
