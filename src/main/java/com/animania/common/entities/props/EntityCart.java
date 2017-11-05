package com.animania.common.entities.props;

import java.util.List;

import com.animania.Animania;
import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.animania.common.helper.AnimaniaHelper;
import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.AnimationHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityCart extends AnimatedEntityBase
{
	protected static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(EntityCart.class);
	public boolean pulled;
	public Entity puller;
	private double turnSpeed;

	static {
		EntityCart.animHandler.addAnim(Animania.MODID, "anim_cart", "model_cart", true);
	}

	public EntityCart(World par1World) {
		super(par1World);
		this.preventEntitySpawning = true;
		this.setSize(2.0F, 1.2F);
		this.stepHeight = 1.0F;
		this.pulled = false;
		this.puller = null;


	}

	public EntityCart(World worldIn, double x, double y, double z)
	{
		this(worldIn);
		setPosition(x, y, z);
	}

	@Override
	public AnimationHandler getAnimationHandler() {
		return EntityCart.animHandler;
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

		if (player.isSneaking()) {
			return false;
		} else if (player.isRiding() && this.puller != player) {
			this.pulled = true;
			this.puller = player.getRidingEntity();
			return true;
		} else if (player.isRiding() && this.puller == player) {
			this.pulled = false;
			this.puller = null;
			return true;
		} else if (stack.isEmpty() && !player.isRiding() && this.puller != player) {
			this.pulled = true;
			this.puller = player;
			return true;
		} else if (stack.isEmpty() && !player.isRiding() && this.puller == player) {
			this.pulled = false;
			this.puller = null;
			return true;
		} else if (stack.getItem() == Items.LEAD && !horses.isEmpty()) {

			EntityHorse horse = (EntityHorse)horses.get(0);
			if (horse.getLeashed()) {
				this.pulled = true;
				this.puller = horse;
				horse.clearLeashed(true, false);
				return true;
			}
			return true;
		} else {
			this.pulled = false;
			this.puller = player;
			return true;
		}

	}

	@Override
	public void onUpdate() {

		boolean entityMoving = false;

		if (this.pulled && this.puller != null && this.puller.isRiding()) {
			this.puller = this.puller.getRidingEntity();
		}

		if (this.pulled && this.puller != null) {
			if (Math.abs(this.puller.motionX) < .01 && (Math.abs(this.puller.motionZ) < .01)) {
				entityMoving = false;
			} else {
				entityMoving = true;
			}
		}


		if (this.isWorldRemote() && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart", this) && !entityMoving)  {
			this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart", this);
		} else if (this.isWorldRemote() && !this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart", this) && entityMoving)  {
			this.getAnimationHandler().startAnimation(Animania.MODID, "anim_cart", this);
		} else if (this.isWorldRemote() && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart", this) && !this.pulled)  {
			this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart", this);
		}

		if (this.pulled)
		{

			double temp = -Math.atan2(this.puller.posX - this.posX, this.puller.posZ - this.posZ);
			this.rotationYaw = ((float)Math.toDegrees(temp));
			Vec3d moveVec = new Vec3d(this.puller.posX, this.puller.posY, this.puller.posZ).subtract(new Vec3d(this.posX, this.posY, this.posZ)).add(new Vec3d(0.0D, 0.0D, -2.8D).rotateYaw((float)-temp));
			this.turnSpeed = Math.sqrt(moveVec.x * moveVec.x + moveVec.z * moveVec.z);
			if (this.turnSpeed < 0.01D)
			{
				this.turnSpeed = 0.0D;
			}
			this.motionX = moveVec.x;
			this.motionY = moveVec.y;
			this.motionZ = moveVec.z;

			double threshold = .02D;

			if (Math.abs(this.motionX) > threshold || (Math.abs(this.motionZ) > threshold)) {
				move(MoverType.PLAYER, this.motionX, this.motionY, this.motionZ);
			}

			if ((moveVec.subtract(new Vec3d(1.0, 0.0D, 0.0D).rotateYaw((float)temp)).lengthVector() > 0.5D) && (this.posY == this.puller.posY))
			{
				this.turnSpeed = (-this.turnSpeed);
			}

			/*
	      if ((this.speed > 3.0D) && (!this.world.isRemote))
	      {
	        setPulling(null);
	        ((WorldServer)this.world).getEntityTracker().sendToTracking(this, AstikoorPacketHandler.INSTANCE.getPacketFrom(new EntityCartUpdatePacket(-1, getEntityId())));
	      }
			 */
		}


		super.onUpdate();
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
		// TODO Auto-generated method stub

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}
}