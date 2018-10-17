package com.animania.common.entities.amphibians;

import java.util.Random;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.common.entities.EntityGender;
import com.animania.common.entities.chickens.EntityAnimaniaChicken;
import com.animania.common.entities.interfaces.IAnimaniaAnimal;
import com.animania.common.entities.interfaces.IGendered;
import com.animania.common.entities.interfaces.ISpawnable;
import com.animania.common.entities.peacocks.EntityAnimaniaPeacock;

public class EntityAmphibian extends EntityAnimal implements ISpawnable, IAnimaniaAnimal, IGendered
{
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityAmphibian.class, DataSerializers.VARINT);
	private int     jumpTicks;
	private int     jumpDuration;
	private boolean wasOnGround, canEntityJump;
	private int     currentMoveTypeDuration;
	public float    squishAmount;
	public float    squishFactor;
	public float    prevSquishFactor;

	/**
	 * Default constructor
	 */
	public EntityAmphibian(World worldIn) {
		super(worldIn);
	}

	/**
	 * Constructor just for jumping amphibians, frogs and toads
	 */
	public EntityAmphibian(World worldIn, boolean canEntityJumpIn) {
		this(worldIn);
		this.setSize(0.3F, 0.3F);
		this.setMovementSpeed(0.0D);
		this.jumpHelper = new EntityAmphibian.FrogJumpHelper(this);
		this.moveHelper = new EntityAmphibian.FrogMoveHelper(this);
		this.canEntityJump = canEntityJumpIn;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAmphibian.AGE, Integer.valueOf(0));
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
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAmphibian.AIPanic(this, 2.2D));
		if (!this.getCustomNameTag().equals("Pepe")) {
			this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 6.0F, 1.5D, 1.5D));
		}
		this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.6D));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.tasks.addTask(5, new EntityAIAvoidEntity(this, EntityAnimaniaPeacock.class, 10.0F, 3.0D, 3.5D));
		this.tasks.addTask(6, new EntityAIAvoidEntity(this, EntityAnimaniaChicken.class, 10.0F, 3.0D, 3.5D));
	}

	@Override
	protected float getJumpUpwardsMotion() {
		if (!this.collidedHorizontally && (!this.moveHelper.isUpdating() || this.moveHelper.getY() <= this.posY + 0.5D)) {
			Path path = this.navigator.getPath();

			if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength()) {
				Vec3d vec3d = path.getPosition(this);

				if (vec3d.x > this.posY + 0.5D)
					return 0.5F;
			}

			return this.moveHelper.getSpeed() <= 0.6D ? 0.2F : 0.3F;
		}
		else
			return 0.5F;
	}


	public int getAge()
	{
		return this.dataManager.get(EntityAmphibian.AGE).intValue();
	}

	public void setAge(int age)
	{
		this.dataManager.set(EntityAmphibian.AGE, Integer.valueOf(age));
	}


	@Override
	protected void jump() {
		super.jump();
		double d0 = this.moveHelper.getSpeed();

		if (d0 > 0.0D) {
			double d1 = this.motionX * this.motionX + this.motionZ * this.motionZ;

			if (d1 < 0.010000000000000002D)
				this.moveRelative(0.0F, 1.0F, 0.1F, 0f);
		}

		if (!this.world.isRemote)
			this.world.setEntityState(this, (byte) 1);
	}

	@SideOnly(Side.CLIENT)
	public float setJumpCompletion(float p_175521_1_) {
		return this.jumpDuration == 0 ? 0.0F : (this.jumpTicks + p_175521_1_) / this.jumpDuration;
	}

	public void setMovementSpeed(double newSpeed) {
		this.getNavigator().setSpeed(newSpeed);
		this.moveHelper.setMoveTo(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ(), newSpeed);
	}

	@Override
	public void setJumping(boolean jumping) {
		super.setJumping(jumping);
	}

	public void startJumping() {
		this.setJumping(true);
		this.jumpDuration = 10;
		this.jumpTicks = 0;
	}

	@Override
	public void updateAITasks() {
		if (this.canEntityJump) {
			if (this.currentMoveTypeDuration > 0)
				--this.currentMoveTypeDuration;

			if (this.onGround) {
				if (!this.wasOnGround) {
					this.setJumping(false);
					this.checkLandingDelay();
				}

				EntityAmphibian.FrogJumpHelper jumphelper = (EntityAmphibian.FrogJumpHelper) this.jumpHelper;

				if (!jumphelper.getIsJumping()) {
					if (this.moveHelper.isUpdating() && this.currentMoveTypeDuration == 0) {
						Path path = this.navigator.getPath();

						Vec3d vec3d = new Vec3d(this.moveHelper.getX(), this.moveHelper.getY(), this.moveHelper.getZ());

						if (path != null && path.getCurrentPathIndex() < path.getCurrentPathLength())
							vec3d = path.getPosition(this);

						this.calculateRotationYaw(vec3d.x, vec3d.z);
						this.startJumping();
					}
				}
				else if (!jumphelper.canJump())
					this.enableJumpControl();
			}
			this.wasOnGround = this.onGround;

		}
	}

	private void calculateRotationYaw(double x, double z) {
		this.rotationYaw = (float) (MathHelper.atan2(z - this.posZ, x - this.posX) * (180D / Math.PI)) - 90.0F;
	}

	private void enableJumpControl() {
		((EntityAmphibian.FrogJumpHelper) this.jumpHelper).setCanJump(true);
	}

	private void disableJumpControl() {
		((EntityAmphibian.FrogJumpHelper) this.jumpHelper).setCanJump(false);
	}

	private void updateMoveTypeDuration() {
		if (this.moveHelper.getSpeed() < 2.2D)
			this.currentMoveTypeDuration = 10;
		else
			this.currentMoveTypeDuration = 1;
	}

	private void checkLandingDelay() {
		this.updateMoveTypeDuration();
		this.disableJumpControl();
	}

	/**
	 * Called frequently so the entity can update its state every tick as
	 * required. For example, zombies and skeletons use this to react to
	 * sunlight and start to burn.
	 */
	@Override
	public void onLivingUpdate() {

		if (this.getAge() == 0) {
			this.setAge(1);
		}
		
	
		
		if (this.getCustomNameTag().equals("Pepe") && this.getMaxHealth() != 20.0D)
		{
			this.initEntityAI();
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
			this.setHealth(20);
		}

		
		if (this.canEntityJump)
			if (this.jumpTicks != this.jumpDuration)
				++this.jumpTicks;
			else if (this.jumpDuration != 0) {
				this.jumpTicks = 0;
				this.jumpDuration = 0;
				this.setJumping(false);
			}

		this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
		this.prevSquishFactor = this.squishFactor;
		super.onLivingUpdate();

		if (this.onGround)
			this.squishAmount = -0.5F;
		else if (!this.onGround)
			this.squishAmount = 0.5F;

		this.alterSquishAmount();

	}

	protected void alterSquishAmount() {
		this.squishAmount *= 0.6F;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
	}

	protected SoundEvent getJumpSound() {
		return null;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Override
	public SoundCategory getSoundCategory() {
		return SoundCategory.NEUTRAL;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return this.isEntityInvulnerable(source) ? false : super.attackEntityFrom(source, amount);
	}

	@Override
	public EntityAmphibian createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setInteger("Age", this.getAge());

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.setAge(compound.getInteger("Age"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 1) {
			// this.createRunningParticles();
			this.jumpDuration = 3;
			this.jumpTicks = 0;
		}
		else
			super.handleStatusUpdate(id);
	}

	static class AIPanic extends EntityAIPanic
	{
		private final EntityAmphibian theEntity;

		public AIPanic(EntityAmphibian amphibian, double speedIn) {
			super(amphibian, speedIn);
			this.theEntity = amphibian;
		}

		/**
		 * Updates the task
		 */
		@Override
		public void updateTask() {
			super.updateTask();
			this.theEntity.setMovementSpeed(this.speed);
		}
	}

	public class FrogJumpHelper extends EntityJumpHelper
	{
		private final EntityAmphibian theEntity;
		private boolean               canJump;

		public FrogJumpHelper(EntityAmphibian rabbit) {
			super(rabbit);
			this.theEntity = rabbit;
		}

		public boolean getIsJumping() {
			return this.isJumping;
		}

		public boolean canJump() {
			return this.canJump;
		}

		public void setCanJump(boolean canJumpIn) {
			this.canJump = canJumpIn;
		}

		/**
		 * Called to actually make the entity jump if isJumping is true.
		 */
		@Override
		public void doJump() {
			if (this.isJumping) {
				this.theEntity.startJumping();
				this.isJumping = false;
			}
		}
	}

	static class FrogMoveHelper extends EntityMoveHelper
	{
		private final EntityAmphibian theEntity;
		private double                nextJumpSpeed;

		public FrogMoveHelper(EntityAmphibian rabbit) {
			super(rabbit);
			this.theEntity = rabbit;
		}

		@Override
		public void onUpdateMoveHelper() {
			if (this.theEntity.onGround && !this.theEntity.isJumping && !((EntityAmphibian.FrogJumpHelper) this.theEntity.jumpHelper).getIsJumping())
				this.theEntity.setMovementSpeed(0.0D);
			else if (this.isUpdating())
				this.theEntity.setMovementSpeed(this.nextJumpSpeed);

			super.onUpdateMoveHelper();
		}

		/**
		 * Sets the speed and location to move to
		 */
		@Override
		public void setMoveTo(double x, double y, double z, double speedIn) {
			if (this.theEntity.isInWater())
				speedIn = 1.5D;

			super.setMoveTo(x, y, z, speedIn);

			if (speedIn > 0.0D) {
				Random rand = new Random();
				float distance = rand.nextFloat() / 25;
				this.nextJumpSpeed = speedIn + distance;
			}
		}
	}

	public static class RabbitTypeData implements IEntityLivingData
	{
		public int typeData;

		public RabbitTypeData(int type) {
			this.typeData = type;
		}
	}

	@Override
	public Item getSpawnEgg()
	{
		return null;
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
		return EntityGender.NONE;
	}
}