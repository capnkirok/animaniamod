package com.animania.addons.farm.common.entity.pullables;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.FarmAddon;
import com.animania.addons.farm.common.entity.cows.EntityAnimaniaCow;
import com.animania.addons.farm.common.entity.horses.EntityAnimaniaHorse;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.addons.farm.common.inventory.CartChest;
import com.animania.client.handler.AnimationHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.leviathanstudio.craftstudio.CraftStudioApi;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AABB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityTiller extends AnimatedEntityBase implements ContainerListener
{
	protected static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(EntityTiller.class);
	public boolean pulled;
	public Entity puller;
	public CartChest cartChest;
	public float deltaRotation;
	private int lerpSteps;
	private double cartPitch;
	private double lerpY;
	private double lerpZ;
	public double cartYaw;
	private double lerpXRot;
	private MutableBlockPos lastPosMid = new MutableBlockPos(0, 0, 0);
	private MutableBlockPos lastPosRight = new MutableBlockPos(0, 0, 0);
	private MutableBlockPos lastPosLeft = new MutableBlockPos(0, 0, 0);
	protected static final EntityDataAccessor<Integer> PULLER_TYPE = SynchedEntityData.defineId(EntityTiller.class, EntityDataSerializers.VARINT);
	private static final EntityDataAccessor<Integer> TIME_SINCE_HIT = SynchedEntityData.defineId(EntityTiller.class, EntityDataSerializers.VARINT);
	private static final EntityDataAccessor<Float> DAMAGE_TAKEN = SynchedEntityData.defineId(EntityTiller.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> HAS_CHEST = SynchedEntityData.defineId(EntityTiller.class, EntityDataSerializers.BOOLEAN);

	// Gui Id Offset required when multiple addons are installed
	private static final int GUI_ID = 2 + FarmAddon.guiHandler.getGuiIdOffset();

	static
	{
		EntityTiller.animHandler.addAnim(Animania.MODID, "anim_tiller", "model_tiller", true);
		EntityTiller.animHandler.addAnim(Animania.MODID, "anim_tiller_back", "anim_tiller");
	}

	public EntityTiller(Level par1Level)
	{
		super(par1Level);
		this.preventEntitySpawning = true;
		this.setSize(2.0F, 1.2F);
		this.width = 2.0F;
		this.height = 1.2F;
		this.stepHeight = 1.2F;
		this.puller = null;
		this.pulled = false;
		this.initCartChest();

	}

	public EntityTiller(Level levelIn, double x, double y, double z)
	{
		this(levelIn);
		setPosition(x, y, z);
	}

	@Override
	public AnimationHandler getAnimationHandler()
	{
		return EntityTiller.animHandler;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	@Override
	public double getMountedYOffset()
	{
		return +0.62D;
	}

	@Override
	public boolean canRiderInteract()
	{
		return true;
	}

	@Override
	public boolean processInitialInteract(Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		List horses = AnimaniaHelper.getEntitiesInRange(HorseEntity.class, 3, level, player);
		List cows = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaCow.class, 3, level, player);
		List tillers = AnimaniaHelper.getEntitiesInRangeGeneric(EntityTiller.class, 3, level, this);

		HorseEntity horse = null;
		EntityAnimaniaCow cow = null;
		if (!horses.isEmpty())
		{
			horse = (HorseEntity) horses.get(0);
		}
		if (!cows.isEmpty())
		{
			cow = (EntityAnimaniaCow) cows.get(0);
		}

		if (player.isSneaking())
		{
			if (!this.level.isClientSide)
			{
				this.cartChest.setCustomName(this.getName());
				player.openGui(Animania.instance, GUI_ID, player.level, this.getEntityId(), 0, 0);
				level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.PLAYERS, 0.7F, 1.0F);
			}

			/*
			 * else if (player.getRidingEntity() != this) {
			 *
			 * player.startRiding(this);
			 *
			 * this.rideCooldown = 20; if (this.puller == player) { this.puller
			 * = null; this.pulled = false; this.setPullerType(0);
			 * this.stopCart(); } }
			 */
		}
		else if (!player.isSneaking())
		{

			if (player.isPassenger() && this.puller != player && this.puller != player.getRidingEntity() && player.getRidingEntity() != this)
			{

				this.pulled = true;
				this.puller = player.getRidingEntity();
				if (this.puller instanceof HorseEntity)
				{
					this.setPullerType(1);
				}
				else if (this.puller instanceof EntityAnimaniaCow)
				{
					this.setPullerType(3);
				}
				if (!level.isClientSide)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
			}
			else if (player.isPassenger() && this.puller == player.getRidingEntity() && player.getRidingEntity() != this)
			{
				this.pulled = false;
				this.puller = null;
				this.setPullerType(0);
				if (!level.isClientSide)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				this.stopCart();
			}
			else if ((stack.getItem() == Items.AIR || stack.getItem() == Items.LEAD) && horse != null && horse.getLeashHolder() == player)
			{
				this.pulled = true;
				this.puller = horse;
				this.setPullerType(1);
				horse.clearLeashed(true, false);
				if (!player.isCreative())
				{
					player.inventory.addItemStackToInventory(new ItemStack(Items.LEAD, 1));
				}
				if (!level.isClientSide)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
			}
			else if ((stack.getItem() == Items.AIR || stack.getItem() == Items.LEAD) && cow != null && cow.getLeashHolder() == player)
			{
				this.pulled = true;
				this.puller = cow;
				this.setPullerType(3);
				cow.clearLeashed(true, false);
				if (!player.isCreative())
				{
					player.inventory.addItemStackToInventory(new ItemStack(Items.LEAD, 1));
				}
				if (!level.isClientSide)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
			}
			else if (stack.isEmpty() && !player.isPassenger() && this.puller != player && this.getControllingPassenger() != player && !level.isClientSide)
			{

				double diffx = Math.abs(this.getX() - player.getX());
				double diffy = Math.abs(this.getY() - player.getY());
				double diffz = Math.abs(this.getZ() - player.getZ());

				if (diffx > 0 && diffy < 2 && diffz > 0)
				{
					this.pulled = true;
					this.puller = player;
					this.setPullerType(2);
					if (!level.isClientSide)
					{
						level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);

					}
				}
			}
			else if (stack.isEmpty() && !player.isPassenger() && this.puller == player && this.getControllingPassenger() != player && !level.isClientSide)
			{
				this.pulled = false;
				this.puller = null;
				this.setPullerType(0);
				if (!level.isClientSide)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				this.stopCart();
			}
			else
			{
				this.pulled = false;
				this.puller = null;
				this.setPullerType(0);
				if (!level.isClientSide)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				this.stopCart();
			}
		}
		return true;

	}

	public void setDamageTaken(float damageTaken)
	{
		this.entityData.set(EntityTiller.DAMAGE_TAKEN, Float.valueOf(damageTaken));
	}

	public float getDamageTaken()
	{
		return this.entityData.get(EntityTiller.DAMAGE_TAKEN).floatValue();
	}

	public void setHasChest(boolean hasChest)
	{
		this.entityData.set(EntityTiller.HAS_CHEST, Boolean.valueOf(hasChest));
	}

	public boolean getHasChest()
	{
		return this.entityData.get(EntityTiller.HAS_CHEST).booleanValue();
	}

	public void setTimeSinceHit(int timeSinceHit)
	{
		this.entityData.set(EntityTiller.TIME_SINCE_HIT, Integer.valueOf(timeSinceHit));
	}

	public int getTimeSinceHit()
	{
		return this.entityData.get(EntityTiller.TIME_SINCE_HIT).intValue();
	}

	@Override
	public void applyEntityCollision(Entity entityIn)
	{
		if (entityIn instanceof EntityTiller)
		{
			if (entityIn.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY && !this.pulled)
			{
				super.applyEntityCollision(entityIn);

			}
		}
		else if (entityIn.getEntityBoundingBox().minY <= this.getEntityBoundingBox().minY && !this.pulled)
		{
			super.applyEntityCollision(entityIn);

		}
	}

	public void tillGround(BlockPos pos, MutableBlockPos lastPos, Player player)
	{
		if (!pos.equals(lastPos) && (this.level.getBlockState(pos).getBlock() instanceof GrassBlock || this.level.getBlockState(pos).getBlock() instanceof BlockFarmland) || this.level.getBlockState(pos).getBlock() instanceof BlockDirt)
		{
			lastPos.setPos(pos);
			this.level.setBlock(pos, Blocks.FARMLAND.getStateFromMeta(7));

			if (this.level.getBlockState(pos.up()).getBlock() instanceof BlockTallGrass || this.level.getBlockState(pos.up()).getBlock() instanceof BlockDoublePlant)
			{
				this.level.destroyBlock(this.getPosition(), false);
			}
			else if (this.level.getBlockState(pos.up()).getBlock() instanceof BlockCrops)
			{
				// do nothing
			}
			else
			{
				for (int j = 0; j < 10; j++)
				{
					ItemStack seeds = this.cartChest.getStackInSlot(j);
					if (seeds != ItemStack.EMPTY && (seeds.getItem() instanceof ItemSeeds || seeds.getItem() instanceof ItemSeedFood))
					{
						if (seeds.getItem() instanceof ItemSeeds)
						{
							ItemSeeds seedy = (ItemSeeds) seeds.getItem();
							this.level.setBlock(pos.up(), seedy.getPlant(level, pos.up()));
						}
						else
						{
							ItemSeedFood seedy = (ItemSeedFood) seeds.getItem();
							this.level.setBlock(pos.up(), seedy.getPlant(level, pos.up()));
						}

						if (player != null && !player.isCreative())
						{
							seeds.shrink(1);
						}
						break;
					}
				}
			}
		}
	}

	@Override
	public void onUpdate()
	{
		if (!this.level.isClientSide && this.pulled && this.puller != null && (this.puller instanceof EntityAnimaniaHorse || this.puller instanceof EntityAnimaniaCow))
		{
			Player player = level.getClosestPlayer(this.getX(), this.getY(), this.getZ(), 20, false);

			Vec3d up = new Vec3d(0, 1, 0);
			Vec3d forward = new Vec3d(1, 0, 0).rotateYaw(this.rotationYaw).normalize();
			Vec3d right = up.crossProduct(forward).normalize();

			Vec3d posvec = new Vec3d(this.getPosition().down());
			posvec = posvec.addVector(0.5, 0, 0.5);

			this.tillGround(new BlockPos(posvec), this.lastPosMid, player);
			this.tillGround(new BlockPos(posvec.add(right)), this.lastPosRight, player);
			this.tillGround(new BlockPos(posvec.subtract(right)), this.lastPosLeft, player);
		}

		if (!this.getHasChest())
		{
			this.setHasChest(true);
		}

		// Dismount if sneak pressed and riding this
		if (this.isBeingRidden() && this.getControllingPassenger() instanceof Player && this.rideCooldown == 0)
		{
			Player player = (Player) this.getControllingPassenger();
			if (player.isSneaking())
			{
				player.applyEntityCollision(this);
				this.dismountRidingEntity();
				player.dismountEntity(this);
				this.removePassengers();

			}
		}

		// Mounting a horse that is riding a cart...
		if (this.isBeingRidden() && this.getControllingPassenger() instanceof AnimalEntity)
		{

			AnimalEntity AnimalEntity = (AnimalEntity) this.getControllingPassenger();
			if (AnimalEntity.isBeingRidden() && AnimalEntity.getControllingPassenger() instanceof Player)
			{
				AnimalEntity.applyEntityCollision(this);
				AnimalEntity.dismountRidingEntity();
				this.dismountRidingEntity();
				AnimalEntity.dismountEntity(this);
				AnimalEntity.removePassengers();
				this.removePassengers();
			}

		}

		// Dismount text
		if (this.isBeingRidden() && this.getControllingPassenger() instanceof Player && this.rideCooldown > 10 && level.isClientSide)
		{
			Player player = (Player) this.getControllingPassenger();
			player.sendStatusMessage(new TextComponent(I18n.format("mount.onboard", Minecraft.getInstance().gameSettings.keyBindSneak.getDisplayName())), true);
		}

		// Determine animation direction based on previous pos
		if (this.pulled && this.puller != null && level.isClientSide)
		{

			double movX = Math.abs(this.getX() - this.prevgetX());
			double movZ = Math.abs(this.getZ() - this.prevgetZ());

			double moveThresh = .005D;

			if (this.cartYaw < 0 && this.motionX > .001 && this.motionZ > .001 && movX + movZ > moveThresh)
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_tiller_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_tiller", this);
				}
			}
			else if (this.cartYaw > 0 && this.motionX < -.001 && this.motionZ < -.001 && movX + movZ > moveThresh)
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_tiller_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_tiller", this);
				}
			}
			else if (this.cartYaw > 0 && this.motionX < -.001 && this.motionZ > .001 && movX + movZ > moveThresh)
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_tiller_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_tiller", this);
				}
			}
			else if (this.cartYaw < 0 && this.motionX > .001 && this.motionZ < -.001 && movX + movZ > moveThresh)
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_tiller_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_tiller", this);
				}
			}
			else if (this.cartYaw < 0 && this.motionX < -.001 && this.motionZ < -.001 && movX + movZ > moveThresh)
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_tiller", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller_back", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_tiller_back", this);
				}
			}
			else if (this.cartYaw > 0 && this.motionX > .001 && this.motionZ > .001 && movX + movZ > moveThresh)
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_tiller", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller_back", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_tiller_back", this);
				}
			}
			else if (this.cartYaw > 0 && this.motionX > .001 && this.motionZ < -.001 && movX + movZ > moveThresh)
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_tiller", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller_back", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_tiller_back", this);
				}
			}
			else if (this.cartYaw < 0 && this.motionX < -.001 && this.motionZ > .001 && movX + movZ > moveThresh)
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_tiller", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller_back", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_tiller_back", this);
				}

			}

		}

		// Add slowness if multiple carts being pulled
		if (this.pulled && this.puller instanceof Player)
		{
			List carts = AnimaniaHelper.getEntitiesInRangeGeneric(EntityTiller.class, 3, level, this);
			Player player = (Player) this.puller;
			int totPulling = 0;
			if (!carts.isEmpty())
			{
				if (carts.size() > 1)
				{
					for (Object cart : carts)
					{
						EntityTiller tempCart = (EntityTiller) cart;
						if (tempCart.pulled && tempCart.puller == player && tempCart != this)
						{
							totPulling++;
						}
					}
				}
				if (totPulling > 0)
				{
					player.addMobEffectInstance(new MobEffectInstance(MobEffects.SLOWNESS, 2, carts.size() + 1, false, false));
				}
			}
		}

		if (this.pulled && this.puller instanceof AnimalEntity)
		{
			List carts = AnimaniaHelper.getEntitiesInRangeGeneric(EntityTiller.class, 3, level, this);
			AnimalEntity animal = (AnimalEntity) this.puller;
			int totPulling = 0;
			if (!carts.isEmpty())
			{
				if (carts.size() > 1)
				{
					for (Object cart : carts)
					{
						EntityTiller tempCart = (EntityTiller) cart;
						if (tempCart.pulled && tempCart.puller == animal && tempCart != this)
						{
							totPulling++;
						}
					}
				}
				if (totPulling > 0)
				{
					animal.addMobEffectInstance(new MobEffectInstance(MobEffects.SLOWNESS, 2, carts.size() + 1, false, false));
				}
			}
		}

		// Stop Animation if not pulling or moving

		if (this.level.isClientSide && this.pulled)
		{
			double diffX = this.getX() - this.prevgetX();
			double diffZ = this.getZ() - this.prevgetZ();

			double movX = Math.abs(this.getX() - this.prevgetX());
			double movZ = Math.abs(this.getZ() - this.prevgetZ());

			double moveThresh = .008D;

			if (diffX < .005 && diffZ < .005 && movX + movZ < moveThresh && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller", this))
			{
				this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_tiller", this);
			}

			if (diffX < .005 && diffZ < .005 && movX + movZ < moveThresh && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller_back", this))
			{
				this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_tiller_back", this);
			}
		}

		if (this.getTimeSinceHit() > 0)
		{
			this.setTimeSinceHit(this.getTimeSinceHit() - 1);
		}

		if (this.getDamageTaken() > 0.0F)
		{
			this.setDamageTaken(this.getDamageTaken() - 1.0F);
		}

		if (!this.pulled && this.puller == null && this.getPullerType() > 0)
		{

			if (this.getPullerType() == 1)
			{
				List entities = AnimaniaHelper.getEntitiesInRange(HorseEntity.class, 3, this.level, this);
				if (!entities.isEmpty())
				{
					this.puller = (Entity) entities.get(0);
					this.pulled = true;
					this.setPullerType(1);
				}
			}
			else if (this.getPullerType() == 2)
			{
				List entities = AnimaniaHelper.getEntitiesInRange(Player.class, 3, this.level, this);
				if (!entities.isEmpty())
				{
					this.puller = (Entity) entities.get(0);
					this.pulled = true;
					this.setPullerType(2);
				}
			}
			else if (this.getPullerType() == 3)
			{
				List entities = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaCow.class, 3, this.level, this);
				if (!entities.isEmpty())
				{
					this.puller = (Entity) entities.get(0);
					this.pulled = true;
					this.setPullerType(3);
				}

			}
			else
			{
				this.pulled = false;
				this.setPullerType(0);
			}
		}

		if (this.pulled && this.puller != null && this.puller.isPassenger())
		{
			this.puller = this.puller.getRidingEntity();
			if (this.puller instanceof HorseEntity)
			{
				this.setPullerType(1);
			}
			else if (this.puller instanceof EntityAnimaniaCow)
			{
				this.setPullerType(3);
			}
		}

		if (this.puller != null && (Math.abs(this.puller.getX() - this.getX()) > 5 || Math.abs(this.puller.getZ() - this.getZ()) > 5))
		{
			this.pulled = false;
			this.puller = null;
			this.setPullerType(0);
			level.playSound(null, this.getX(), this.getY(), this.getZ(), FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
			this.stopCart();
		}

		if (this.pulled)
		{
			double deltaAngle = -Math.atan2(this.puller.getX() - this.getX(), this.puller.getZ() - this.getZ());

			Vec3d vec = new Vec3d(this.puller.getX(), this.puller.getY(), this.puller.getZ()).subtract(new Vec3d(this.getX(), this.getY(), this.getZ())).add(new Vec3d(0.0D, 0.0D, -2.5D).rotateYaw((float) -deltaAngle));
			this.motionX = vec.x / 1;
			this.motionY = vec.y;
			this.motionZ = vec.z / 1;
			move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

		}

		List<Entity> list = this.level.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D), EntitySelectors.getTeamCollisionPredicate(this));

		if (!list.isEmpty())
		{
			boolean flag = !this.level.isClientSide;

			for (int j = 0; j < list.size(); ++j)
			{
				Entity entity = list.get(j);

				if (entity instanceof AnimalEntity)
				{

					AnimalEntity AnimalEntity = (AnimalEntity) entity;
					if (!entity.isPassenger(this))
					{
						if (flag && this.getPassengers().size() < 2 && this.puller != entity && AnimalEntity.getLeashed() && AnimalEntity.getLeashHolder() instanceof Player && !entity.isPassenger() && entity.width < this.width && entity instanceof LivingEntity && !(entity instanceof Player))
						{
							entity.startRiding(this);
						}
						else
						{
							this.applyEntityCollision(entity);
						}
					}
				}
			}
		}

		if (!this.pulled)
		{
			this.motionY = this.motionY - .05D;
			move(MoverType.SELF, 0.0D, this.motionY, 0.0D);
		}

		super.onUpdate();
		this.tickLerp();

	}

	public void stopCart()
	{
		if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_tiller_back", this))
		{
			this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_tiller_back", this);
		}
	}

	private void tickLerp()
	{
		if (this.puller != null)
		{
			double deltaAngle = -Math.atan2(this.puller.getX() - this.getX(), this.puller.getZ() - this.getZ());
			this.rotationYaw = (float) Math.toDegrees(deltaAngle);
		}

		if (this.lerpSteps > 0 && !this.canPassengerSteer())
		{
			double d0 = this.getX() + (this.cartPitch - this.getX()) / this.lerpSteps;
			double d1 = this.getY() + (this.lerpY - this.getY()) / this.lerpSteps;
			double d2 = this.getZ() + (this.lerpZ - this.getZ()) / this.lerpSteps;
			double d3 = MathHelper.wrapDegrees(this.cartYaw - this.rotationYaw);
			this.rotationPitch = (float) (this.rotationPitch + (this.lerpXRot - this.rotationPitch) / this.lerpSteps);
			--this.lerpSteps;
			this.setPosition(d0, d1, d2);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		}
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport)
	{
		this.cartPitch = x;
		this.lerpY = y;
		this.lerpZ = z;
		this.cartYaw = yaw;
		this.lerpXRot = pitch;
		this.lerpSteps = 10;
	}

	@Override
	public void updatePassenger(Entity passenger)
	{
		if (this.isPassenger(passenger))
		{
			float f = 0.0F;
			float f1 = (float) ((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());

			if (this.getPassengers().size() > 1)
			{
				int i = this.getPassengers().indexOf(passenger);

				if (i == 0)
				{
					f = 0.2F;
				}
				else
				{
					f = -0.6F;
				}

				if (passenger instanceof AnimalEntity)
				{
					f = (float) (f + 0.2D);
				}
			}

			Vec3d vec3d = new Vec3d(f, 0.0D, 0.0D).rotateYaw(-this.rotationYaw * 0.017453292F - (float) Math.PI / 2F);
			passenger.setPosition(this.getX() + vec3d.x, this.getY() + f1, this.getZ() + vec3d.z);
			passenger.rotationYaw += this.deltaRotation;
			passenger.setRotationYawHead(passenger.getRotationYawHead() + this.deltaRotation);
			this.applyYawToEntity(passenger);

			if (passenger instanceof AnimalEntity && this.getPassengers().size() > 1)
			{

				int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
				passenger.setRenderYawOffset(((AnimalEntity) passenger).renderYawOffset + j);
				passenger.setRotationYawHead(passenger.getRotationYawHead() + j);
			}
		}
	}

	protected void applyYawToEntity(Entity entityToUpdate)
	{
		entityToUpdate.setRenderYawOffset(this.rotationYaw);
		float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
		float f1 = MathHelper.clamp(f, 0.0F, 0.0F);
		if (entityToUpdate instanceof Player)
		{
			f1 = MathHelper.clamp(f, -105.0F, 105.0F);
		}
		entityToUpdate.prevRotationYaw += f1 - f;
		entityToUpdate.rotationYaw += f1 - f;
		entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public void applyOrientationToEntity(Entity entityToUpdate)
	{
		this.applyYawToEntity(entityToUpdate);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isEntityInvulnerable(source))
		{
			return false;
		}
		else if (!this.level.isClientSide && !this.isDead)
		{
			if (source instanceof EntityDamageSourceIndirect && source.getTrueSource() != null)
			{
				return false;
			}
			else
			{

				this.setTimeSinceHit(10);
				this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
				this.markVelocityChanged();
				boolean flag = source.getTrueSource() instanceof Player && ((Player) source.getTrueSource()).capabilities.isCreativeMode;

				if (flag || this.getDamageTaken() > 40.0F)
				{
					if (!flag && this.level.getGameRules().getBoolean("doEntityDrops"))
					{
						this.dropItemWithOffset(FarmAddonItemHandler.tiller, 1, 0.0F);
					}

					this.setDead();
				}

				return true;
			}
		}
		else
		{
			return true;
		}
	}

	@Override
	@Nullable
	public AABB getCollisionBox(Entity entityIn)
	{

		double movX = Math.abs(this.getX() - this.prevgetX());
		double movZ = Math.abs(this.getZ() - this.prevgetZ());

		if (entityIn == this.puller || this.pulled)
		{
			return null;
		}
		else
		{
			return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
		}
	}

	/**
	 * Returns the collision bounding box for this entity
	 */
	@Override
	@Nullable
	public AABB getCollisionBoundingBox()
	{
		return this.getEntityBoundingBox();
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		if (this instanceof EntityTiller)
		{
			return new ItemStack(FarmAddonItemHandler.tiller);
		}

		return ItemStack.EMPTY;
	}

	@Override
	protected void entityInit()
	{
		this.entityData.register(EntityTiller.PULLER_TYPE, Integer.valueOf(0));
		this.entityData.register(EntityTiller.TIME_SINCE_HIT, Integer.valueOf(0));
		this.entityData.register(EntityTiller.DAMAGE_TAKEN, Float.valueOf(0.0F));
		this.entityData.register(EntityTiller.HAS_CHEST, true);
	}

	public int getPullerType()
	{
		return this.entityData.get(EntityTiller.PULLER_TYPE).intValue();
	}

	public void setPullerType(int pullerType)
	{

		this.entityData.set(EntityTiller.PULLER_TYPE, Integer.valueOf(pullerType));
	}

	@Override
	public void writeEntityToNBT(CompoundTag compound)
	{
		compound.putInt("PullerType", this.getPullerType());

		ListTag ListTag = new ListTag();

		for (int i = 0; i < this.cartChest.getSizeInventory(); ++i)
		{
			ItemStack itemstack = this.cartChest.getStackInSlot(i);

			if (!itemstack.isEmpty())
			{
				CompoundTag CompoundTag = new CompoundTag();
				CompoundTag.setByte("Slot", (byte) i);
				itemstack.writeToNBT(CompoundTag);
				ListTag.appendTag(CompoundTag);
			}
		}
		compound.putBoolean("HasChest", this.getHasChest());

		compound.put("Items", ListTag);
	}

	@Override
	public void readEntityFromNBT(CompoundTag compound)
	{
		this.setPullerType(compound.getInteger("PullerType"));

		ListTag ListTag = compound.getTagList("Items", 10);
		this.initCartChest();

		for (int i = 0; i < ListTag.tagCount(); ++i)
		{
			CompoundTag CompoundTag = ListTag.getCompoundTagAt(i);
			int j = CompoundTag.getByte("Slot") & 255;

			if (j >= 0 && j < this.cartChest.getSizeInventory())
			{
				this.cartChest.setInventorySlotContents(j, new ItemStack(CompoundTag));
			}
		}
		this.setHasChest(compound.getBoolean("HasChest"));
	}

	public int determinePullerType(Entity entity)
	{
		int pullerType = 0;
		if (entity instanceof HorseEntity)
		{
			pullerType = 1;
		}
		else if (entity instanceof Player)
		{
			pullerType = 2;
		}
		else if (entity instanceof EntityAnimaniaCow)
		{
			pullerType = 3;
		}

		return pullerType;
	}

	public void initCartChest()
	{
		CartChest horsecartchest = this.cartChest;
		this.cartChest = new CartChest("cartChest", 9);
		this.cartChest.setCustomName(this.getName());

		if (horsecartchest != null)
		{
			horsecartchest.removeInventoryChangeListener(this);
			int i = Math.min(horsecartchest.getSizeInventory(), this.cartChest.getSizeInventory());

			for (int j = 0; j < i; ++j)
			{
				ItemStack itemstack = horsecartchest.getStackInSlot(j);

				if (itemstack != null)
				{
					this.cartChest.setInventorySlotContents(j, itemstack.copy());
				}
			}

			horsecartchest = null;

		}

		this.cartChest.addInventoryChangeListener(this);

	}

	@Override
	@SideOnly(Dist.CLIENT)
	public void performHurtAnimation()
	{
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11.0F);
	}

	@Override
	@Nullable
	public Entity getControllingPassenger()
	{
		List<Entity> list = this.getPassengers();
		return list.isEmpty() ? null : (Entity) list.get(0);
	}

	@Override
	public void onInventoryChanged(IInventory invBasic)
	{
		// do nothing
	}

}