package common.entity.rodents.rabbits;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityBuckLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityDoeLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.RabbitEntityKitLop;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.api.interfaces.IConvertable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.*;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;
import common.handler.ExtraAddonSoundHandler;
import config.ExtraConfig;
import net.minecraft.block.BlockCarrot;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Attributes;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class EntityAnimaniaRabbit extends Rabbit implements IAnimaniaAnimalBase, IConvertable
{

	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(ExtraConfig.settings.rabbitFood));
	protected static final EntityDataAccessor<Boolean> WATERED = SynchedEntityData.defineId(EntityAnimaniaRabbit.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> FED = SynchedEntityData.defineId(EntityAnimaniaRabbit.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> COLOR_NUM = SynchedEntityData.defineId(EntityAnimaniaRabbit.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityAnimaniaRabbit.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Boolean> HANDFED = SynchedEntityData.defineId(EntityAnimaniaRabbit.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(EntityAnimaniaRabbit.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Float> SLEEPTIMER = SynchedEntityData.defineId(EntityAnimaniaRabbit.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> INTERACTED = SynchedEntityData.defineId(EntityAnimaniaRabbit.class, EntityDataSerializers.BOOLEAN);

	private static final String[] RABBIT_TEXTURES = { "black", "brown", "golden", "olive", "patch_black", "patch_brown", "patch_grey" };

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

	public EntityAnimaniaRabbit(Level levelIn)
	{
		super(levelIn);
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new GenericAIEatGrass<>(this, false);

		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.goalSelector.addGoal(2, new GenericAIFindWater<>(this, 1.4D, this.entityAIEatGrass, EntityAnimaniaRabbit.class, true));
			this.goalSelector.addGoal(3, new GenericAIFindFood<>(this, 1.4D, this.entityAIEatGrass, true));
		}

		if (this.getCustomName() != null && !this.getCustomName().toString().equals("Killer"))
		{
			this.goalSelector.addGoal(3, new GenericAIPanic<>(this, 2.5D));
			this.goalSelector.addGoal(4, new GenericAIWanderAvoidWater(this, 1.8D));
			this.goalSelector.addGoal(5, new RandomSwimmingGoal(this));
			this.goalSelector.addGoal(7, new GenericAITempt<>(this, 1.25D, false, EntityAnimaniaRabbit.TEMPTATION_ITEMS));
			this.goalSelector.addGoal(8, this.entityAIEatGrass);
			this.goalSelector.addGoal(9, new GenericAIAvoidEntity<>(this, Wolf.class, 24.0F, 3.0D, 3.5D));
			this.goalSelector.addGoal(9, new GenericAIAvoidEntity<>(this, Mob.class, 16.0F, 2.2D, 2.2D));
			this.goalSelector.addGoal(10, new GenericAIWatchClosest(this, Player.class, 6.0F));
			this.goalSelector.addGoal(11, new GenericAILookIdle<>(this));
			if (AnimaniaConfig.gameRules.animalsSleep)
			{
				this.goalSelector.addGoal(12, new GenericAISleep(this, 0.8, Block.getBlockFromName(ExtraConfig.settings.rabbitBed), Block.getBlockFromName(ExtraConfig.settings.rabbitBed2), EntityAnimaniaRabbit.class, leveltime -> leveltime > 20000 && leveltime < 24000 || leveltime > 10000 && leveltime < 15000));

			}
		}
		else

		{
			this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.7F));
			this.goalSelector.addGoal(2, new AttackMeleeGoal(this, 2.0D, true));
			this.goalSelector.addGoal(3, new GenericAIWanderAvoidWater(this, 1.8D));
			this.goalSelector.addGoal(4, new WatchClosestGoal(this, Player.class, 20.0F));
			this.targetSelector.addTask(1, new HurtByTargetGoal(this, false, new Class[0]));
			this.targetSelector.addTask(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
			this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(50.0D);
			this.setHealth(50);
		}

		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 100 + this.rand.nextInt(100);
		this.enablePersistence();

		this.jumpHelper = new RabbitJumpHelper(this);
		this.moveHelper = new RabbitMoveHelper(this);
		this.setMovementSpeed(0.0D);
	}

	protected void resetAI()
	{

		this.tasks.taskEntries.clear();
		this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.7F));
		this.goalSelector.addGoal(2, new AttackMeleeGoal(this, 2.0D, true));
		this.goalSelector.addGoal(3, new GenericAIWanderAvoidWater(this, 1.8D));
		this.goalSelector.addGoal(4, new WatchClosestGoal(this, Player.class, 10.0F));
		this.targetSelector.addTask(1, new HurtByTargetGoal(this, false, new Class[0]));
		this.targetSelector.addTask(2, new NearestAttackableTargetGoal(this, Player.class, true));
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(50.0D);
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
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(3.0D);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.34000001192092896D);
	}

	@Override
	public void setPosition(double x, double y, double z)
	{
		super.setPosition(x, y, z);
	}

	@Override
	public void setAttackTarget(@Nullable LivingEntity LivingEntityIn)
	{
		super.setAttackTarget(LivingEntityIn);
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
		if (entityIn instanceof Player)
		{
			((LivingEntity) entityIn).knockBack(this, 1, this.getX() - entityIn.getX(), this.getZ() - entityIn.getZ());
		}

		return flag;
	}

	@Override
	protected float getJumpUpwardsMotion()
	{
		if (!this.collidedHorizontally && (!this.moveHelper.isUpdating() || this.moveHelper.getY() <= this.getY() + 0.4D))
		{
			Path path = this.navigator.getPath();

			if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
			{
				Vec3d vec3d = path.getPosition(this);

				if (vec3d.y > this.getY() + 0.4D)
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

		if (!this.level.isClientSide)
		{
			this.level.broadcastEntityEvent(this, (byte) 1);
		}
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public float setJumpCompletion(float p_175521_1_)
	{
		return this.jumpDuration == 0 ? 0.0F : (this.jumpTicks + p_175521_1_) / this.jumpDuration;
	}

	@Override
	public void setMovementSpeed(double newSpeed)
	{
		this.getNavigation().setSpeed(newSpeed);
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
		if (this instanceof RabbitEntityBuckLop || this instanceof RabbitEntityKitLop || this instanceof RabbitEntityDoeLop)
		{
			this.entityData.register(EntityAnimaniaRabbit.COLOR_NUM, Integer.valueOf(rand.nextInt(7)));
		}
		else
		{
			this.entityData.register(EntityAnimaniaRabbit.COLOR_NUM, 0);
		}
		this.entityData.register(EntityAnimaniaRabbit.FED, true);
		this.entityData.register(EntityAnimaniaRabbit.HANDFED, false);
		this.entityData.register(EntityAnimaniaRabbit.WATERED, true);
		this.entityData.register(EntityAnimaniaRabbit.AGE, Integer.valueOf(0));
		this.entityData.register(EntityAnimaniaRabbit.SLEEPING, false);
		this.entityData.register(EntityAnimaniaRabbit.SLEEPTIMER, Float.valueOf(0.0F));
		this.entityData.register(INTERACTED, false);
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof RabbitEntityKitBase ? null : this.rabbitType.isPrime ? new ResourceLocation("extra/" + Animania.MODID, "rabbit_prime") : new ResourceLocation("extra/" + Animania.MODID, "rabbit_regular");
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
				LivingEntity LivingEntity = this.getAttackTarget();

				if (LivingEntity != null && this.getDistanceSq(LivingEntity) < 16.0D)
				{
					this.calculateRotationYaw(LivingEntity.getX(), LivingEntity.getZ());
					this.moveHelper.setMoveTo(LivingEntity.getX(), LivingEntity.getY(), LivingEntity.getZ(), this.moveHelper.getSpeed());
					this.startJumping();
					this.wasOnGround = true;
				}
			}

			RabbitJumpHelper RabbitEntity$rabbitjumphelper = (RabbitJumpHelper) this.jumpHelper;

			if (!RabbitEntity$rabbitjumphelper.getIsJumping())
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
			else if (!RabbitEntity$rabbitjumphelper.canJump())
			{
				this.enableJumpControl();
			}
		}

		this.wasOnGround = this.onGround;
	}

	@Override
	public EntityDataAccessor<Integer> getAgeParam()
	{
		return AGE;
	}

	@Override
	public EntityDataAccessor<Boolean> getSleepingParam()
	{
		return SLEEPING;
	}

	@Override
	public EntityDataAccessor<Float> getSleepTimerParam()
	{
		return SLEEPTIMER;
	}

	@Override
	public EntityDataAccessor<Boolean> getHandFedParam()
	{
		return HANDFED;
	}

	@Override
	public EntityDataAccessor<Boolean> getFedParam()
	{
		return FED;
	}

	@Override
	public EntityDataAccessor<Boolean> getWateredParam()
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
		this.entityData.set(COLOR_NUM, Integer.valueOf(color));
	}

	public ResourceLocation getResourceLocation()
	{
		return this.resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink()
	{
		return this.resourceLocationBlink;
	}

	private void calculateRotationYaw(double x, double z)
	{
		this.rotationYaw = (float) (MathHelper.atan2(z - this.getZ(), x - this.getX()) * (180D / Math.PI)) - 90.0F;
	}

	private void enableJumpControl()
	{
		((RabbitJumpHelper) this.jumpHelper).setCanJump(true);
	}

	private void disableJumpControl()
	{
		((RabbitJumpHelper) this.jumpHelper).setCanJump(false);
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
		GenericBehavior.livingUpdateCommon(this);

		if (this.getCustomNameTag().equals("Killer"))
		{
			for (Object a : this.tasks.taskEntries.toArray())
			{
				Goal ai = ((EntityAITaskEntry) a).action;
				if (ai instanceof GenericAIPanic)
				{
					this.resetAI();
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
	public boolean processInteract(Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		Player Player = player;

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.NAME_TAG)
		{
			if (!stack.hasDisplayName())
			{
				return false;
			}
			else
			{
				LivingEntity LivingEntity = this;
				LivingEntity.setCustomNameTag(stack.getDisplayName());

				LivingEntity.enablePersistence();
				if (!player.capabilities.isCreativeMode)
				{
					stack.shrink(1);
				}

				if (stack.getDisplayName().equals("Killer"))
				{
					this.resetAI();
					this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(50.0D);
					this.setHealth(50);
				}
			}

		}

		return GenericBehavior.interactCommon(this, Player, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
			this.eatTimer = 80;
		else
			super.handleStatusUpdate(id);
	}

	@Override
	public void writeEntityToNBT(CompoundTag compound)
	{
		super.writeEntityToNBT(compound);

		compound.putInt("ColorNumber", this.getColorNumber());

		GenericBehavior.writeCommonNBT(compound, this);

	}

	@Override
	public void readEntityFromNBT(CompoundTag compound)
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
		 * Returns whether the Goal should begin execution.
		 */
		@Override
		public boolean shouldExecute()
		{
			return true; // this.entityInstance.getRabbitType() != 99 &&
			// super.shouldExecute();
		}
	}

	static class AIEvilAttack extends AttackMeleeGoal
	{
		public AIEvilAttack(EntityAnimaniaRabbit rabbit)
		{
			super(rabbit, 1.4D, true);
		}

		@Override
		protected double getAttackReachSqr(LivingEntity attackTarget)
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

	static class AIRaidFarm extends MoveToBlockGoal
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
		 * Returns whether the Goal should begin execution.
		 */
		@Override
		public boolean shouldExecute()
		{
			if (this.runDelay <= 0)
			{
				if (!this.rabbit.level.getGameRules().getBoolean("mobGriefing"))
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
		 * Returns whether an in-progress Goal should continue executing
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
				Level level = this.rabbit.level;
				BlockPos blockpos = this.destinationBlock.up();
				BlockState BlockState = level.getBlockState(blockpos);
				Block block = BlockState.getBlock();

				if (this.canRaid && block instanceof BlockCarrot)
				{
					Integer integer = BlockState.getValue(BlockCarrot.AGE);

					if (integer.intValue() == 0)
					{
						level.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 2);
						level.destroyBlock(blockpos, true);
					}
					else
					{
						level.setBlock(blockpos, BlockState.withProperty(BlockCarrot.AGE, Integer.valueOf(integer.intValue() - 1)), 2);
						level.playEvent(2001, blockpos, Block.getStateId(BlockState));
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
		protected boolean shouldMoveTo(Level levelIn, BlockPos pos)
		{
			Block block = levelIn.getBlockState(pos).getBlock();

			if (block == Blocks.FARMLAND && this.wantsToRaid && !this.canRaid)
			{
				pos = pos.up();
				BlockState BlockState = levelIn.getBlockState(pos);
				block = BlockState.getBlock();

				if (block instanceof BlockCarrot && ((BlockCarrot) block).isMaxAge(BlockState))
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
			if (this.theEntity.onGround && !this.theEntity.isJumping && !((RabbitJumpHelper) this.theEntity.jumpHelper).getIsJumping())
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
	public EntityAnimaniaRabbit createChild(AgeableEntity ageable)
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
		return new ItemStack(this.getSpawnEgg());
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
	public Optional<BlockPos> getSleepingPos()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBlinkTimer()
	{
		return this.blinkTimer;
	}

	@Override
	public void setBlinkTimer(int i)
	{
		this.blinkTimer = i;
	}

	@Override
	public Class[] getFoodBlocks()
	{
		return new Class[] { BlockCarrot.class, BlockTallGrass.class, BlockFlower.class };
	}

	@Override
	public int getEatTimer()
	{
		return this.eatTimer;
	}

	@Override
	public void setEatTimer(int i)
	{
		this.eatTimer = i;
	}

	@Override
	public int getFedTimer()
	{
		return this.fedTimer;
	}

	@Override
	public void setFedTimer(int i)
	{
		this.fedTimer = i;
	}

	@Override
	public EntityDataAccessor<Boolean> getInteractedParam()
	{
		return INTERACTED;
	}

	@Override
	public int getWaterTimer()
	{
		return this.wateredTimer;
	}

	@Override
	public void setWaterTimer(int i)
	{
		this.wateredTimer = i;
	}

	@Override
	public int getDamageTimer()
	{
		return this.damageTimer;
	}

	@Override
	public void setDamageTimer(int i)
	{
		this.damageTimer = i;
	}

	@Override
	public int getHappyTimer()
	{
		return this.happyTimer;
	}

	@Override
	public void setHappyTimer(int i)
	{
		this.happyTimer = i;
	}

	@Override
	public AnimaniaType getAnimalType()
	{
		return this.rabbitType;
	}

	@Override
	public Entity convertToVanilla()
	{
		RabbitEntity entity = new RabbitEntity(this.level);
		entity.setPosition(this.getX(), this.getY(), this.getZ());
		if (entity.hasCustomName())
			entity.setCustomNameTag(this.getCustomNameTag());
		return entity;
	}

}
