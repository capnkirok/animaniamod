package com.animania.common.entities.props;

import java.util.List;

import com.animania.Animania;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.helper.AnimaniaHelper;
import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.AnimationHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityWagon extends AnimatedEntityBase
{
	protected static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(EntityWagon.class);
	public boolean pulled;
	public Entity puller;
	private double turnSpeed;
	protected static final DataParameter<Integer> PULLER_TYPE = EntityDataManager.<Integer>createKey(EntityWagon.class, DataSerializers.VARINT);

	static {
		EntityWagon.animHandler.addAnim(Animania.MODID, "anim_wagon", "model_wagon", true);
	}

	public EntityWagon(World par1World) {
		super(par1World);
		this.preventEntitySpawning = true;
		this.setSize(3.0F, 3.05F);
		this.stepHeight = 1.0F;
		this.puller = null;
		this.pulled = false;

	}

	public EntityWagon(World worldIn, double x, double y, double z)
	{
		this(worldIn);
		setPosition(x, y, z);
	}

	@Override
	public AnimationHandler getAnimationHandler() {
		return EntityWagon.animHandler;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return true;
	}

	public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
	{

		ItemStack stack = player.getHeldItem(hand);
		List horses = AnimaniaHelper.getEntitiesInRange(EntityHorse.class, 3, world, player);
		List pigs = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 3, world, player);

		if (player.isSneaking()) {
			return false;
		} else if (player.isRiding() && this.puller != player) {
			this.pulled = true;
			this.puller = player.getRidingEntity();
			if (this.puller instanceof EntityHorse) {
				this.setPullerType(1);
			} else if (this.puller instanceof EntityAnimaniaPig) {
				this.setPullerType(3);
			} 
			return true;
		} else if (player.isRiding() && this.puller == player) {
			this.pulled = false;
			this.puller = null;
			this.setPullerType(0);
			return true;
		} else if (stack.isEmpty() && !player.isRiding() && this.puller != player) {
			this.pulled = true;
			this.puller = player;
			this.setPullerType(determinePullerType(this.puller));
			return true;
		} else if (stack.isEmpty() && !player.isRiding() && this.puller == player) {
			this.pulled = false;
			this.puller = null;
			this.setPullerType(0);
			return true;
		} else if (stack.getItem() == Items.LEAD && !horses.isEmpty()) {

			EntityHorse horse = (EntityHorse)horses.get(0);
			if (horse.getLeashed()) {
				this.pulled = true;
				this.puller = horse;
				this.setPullerType(1);
				horse.clearLeashed(true, false);
				return true;
			}
			return true;
		} else if (stack.getItem() == Items.LEAD && !pigs.isEmpty()) {

			EntityAnimaniaPig pig = (EntityAnimaniaPig)pigs.get(0);
			if (pig.getLeashed()) {
				this.pulled = true;
				this.puller = pig;
				this.setPullerType(3);
				pig.clearLeashed(true, false);
				return true;
			}
			return true;
		} else {
			this.pulled = false;
			this.puller = null;
			this.setPullerType(0);
			return true;
		}

	}

	@Override
	public void onUpdate() {

		super.onUpdate();

		if (!this.pulled  && this.puller == null && this.getPullerType() > 0) {

			if (this.getPullerType() == 1) {
				List entities = AnimaniaHelper.getEntitiesInRange(EntityHorse.class, 3, this.world, this);
				if (!entities.isEmpty()) {
					this.puller = (Entity) entities.get(0);
					this.pulled = true;
				} 
			} else if (this.getPullerType() == 2) {
				List entities = AnimaniaHelper.getEntitiesInRange(EntityPlayer.class, 3, this.world, this);
				if (!entities.isEmpty()) {
					this.puller = (Entity) entities.get(0);
					this.pulled = true;
				} 
			} else if (this.getPullerType() == 3) {
				List entities = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 3, this.world, this);
				if (!entities.isEmpty()) {
					this.puller = (Entity) entities.get(0);
					this.pulled = true;
				} 

			} else {
				this.pulled = false;
				this.setPullerType(0);
			}
		}


		boolean entityMoving = false;

		if (this.pulled && this.puller != null && this.puller.isRiding()) {
			this.puller = this.puller.getRidingEntity();
			if (this.puller instanceof EntityHorse) {
				this.setPullerType(1);
			} else if (this.puller instanceof EntityAnimaniaPig){
				this.setPullerType(3);
			}
		}

		if (this.pulled && this.puller != null) {
			if (Math.abs(this.puller.motionX) < .01 && (Math.abs(this.puller.motionZ) < .01)) {
				entityMoving = false;
			} else {
				entityMoving = true;
			}
		}

		if (this.isWorldRemote() && !this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))  {
			System.out.println("started");
			this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon", this);
		}
		/*
		if (this.isWorldRemote() && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this) && !entityMoving)  {
			this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon", this);
		} else if (this.isWorldRemote() && !this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this) && entityMoving)  {
			this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon", this);
		} else if (this.isWorldRemote() && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this) && !this.pulled)  {
			this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon", this);
		}
		*/
		if (this.pulled)
		{

			double temp = -Math.atan2(this.puller.posX - this.posX, this.puller.posZ - this.posZ);
			this.rotationYaw = ((float)Math.toDegrees(temp));
			Vec3d moveVec = new Vec3d(this.puller.posX, this.puller.posY, this.puller.posZ).subtract(new Vec3d(this.posX, this.posY, this.posZ)).add(new Vec3d(0.0D, 0.0D, -4.0D).rotateYaw((float)-temp));
			this.turnSpeed = Math.sqrt(moveVec.x * moveVec.x + moveVec.z * moveVec.z);
			if (this.turnSpeed < 0.01D)
			{
				this.turnSpeed = 0.0D;
			}
			this.motionX = moveVec.x;
			this.motionY = moveVec.y;
			this.motionZ = moveVec.z;

			double threshold = .01D;

			if (Math.abs(this.motionY) < threshold) {
				this.motionY = 0;
			}

			move(MoverType.PLAYER, this.motionX, this.motionY, this.motionZ);


			if ((moveVec.subtract(new Vec3d(1.0, 0.0D, 0.0D).rotateYaw((float)temp)).lengthVector() > 0.75D) && (this.posY == this.puller.posY))
			{
				this.turnSpeed = (-this.turnSpeed);
			}

		}



		if (!this.onGround)
		{
			move(MoverType.PLAYER, 0.0D, -0.8D, 0.0D);
		}
		collideWithNearbyEntities();

	}


	protected void collideWithNearbyEntities()
	{
		List list = this.world.getEntitiesInAABBexcluding(this, getEntityBoundingBox(), EntitySelectors.getTeamCollisionPredicate(this));

		if (!list.isEmpty())
		{
			for (int j = 0; j < list.size(); j++)
			{
				Entity entity = (Entity)list.get(j);

				double d0 = entity.posX - this.posX;
				double d1 = entity.posZ - this.posZ;
				double d2 = MathHelper.absMax(d0, d1);

				if (d2 >= 0.009999999776482582D)
				{
					d2 = MathHelper.sqrt(d2);
					d0 /= d2;
					d1 /= d2;
					double d3 = 1.0D / d2;

					if (d3 > 1.0D)
					{
						d3 = 1.0D;
					}

					d0 *= d3;
					d1 *= d3;
					d0 *= 0.0500000007450581D;
					d1 *= 0.0500000007450581D;
					d0 *= (1.0F - this.entityCollisionReduction);
					d1 *= (1.0F - this.entityCollisionReduction);
					if (!entity.isBeingRidden())
					{
						entity.addVelocity(d0, 0.0D, d1);
					}
				}
			}
		}
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(EntityWagon.PULLER_TYPE, Integer.valueOf(0));
	}

	public int getPullerType()
	{
		return this.dataManager.get(EntityWagon.PULLER_TYPE).intValue();
	}

	public void setPullerType(int pullerType)
	{

		this.dataManager.set(EntityWagon.PULLER_TYPE, Integer.valueOf(pullerType));
	}


	public void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setInteger("PullerType", this.getPullerType());
	}

	public void readEntityFromNBT(NBTTagCompound compound)
	{
		this.setPullerType(compound.getInteger("PullerType"));
	}

	public int determinePullerType(Entity entity)
	{
		int pullerType = 0;
		if (entity instanceof EntityHorse) {
			pullerType = 1;
		} else if (entity instanceof EntityPlayer){
			pullerType = 2;
		} else if (entity instanceof EntityAnimaniaPig){
			pullerType = 3;
		}

		return pullerType;
	}

}