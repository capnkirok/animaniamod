package com.animania.addons.farm.common.entity.pullables;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.FarmAddon;
import com.animania.addons.farm.common.entity.pigs.EntityAnimaniaPig;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.addons.farm.common.inventory.CartChest;
import com.animania.client.handler.AnimationHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.leviathanstudio.craftstudio.CraftStudioApi;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.potion.PotionEffect;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCart extends AnimatedEntityBase implements ContainerListener
{
	protected static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(EntityCart.class);
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
	protected static final EntityDataAccessor<Integer> PULLER_TYPE = SynchedEntityData.defineId(EntityCart.class, EntityDataSerializers.VARINT);
	private static final EntityDataAccessor<Integer> TIME_SINCE_HIT = SynchedEntityData.defineId(EntityCart.class, EntityDataSerializers.VARINT);
	private static final EntityDataAccessor<Float> DAMAGE_TAKEN = SynchedEntityData.defineId(EntityCart.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> HAS_CHEST = SynchedEntityData.defineId(EntityCart.class, EntityDataSerializers.BOOLEAN);

	// Gui Id Offset required when multiple addons are installed
	private static final int GUI_ID = 0 + FarmAddon.guiHandler.getGuiIdOffset();

	static
	{
		EntityCart.animHandler.addAnim(Animania.MODID, "anim_cart", "model_cart", true);
		EntityCart.animHandler.addAnim(Animania.MODID, "anim_cart_back", "anim_cart");
		EntityCart.animHandler.addAnim(Animania.MODID, "anim_cart_chest", "model_cart_chest", true);
		EntityCart.animHandler.addAnim(Animania.MODID, "anim_cart_chest_back", "anim_cart_chest");
	}

	public EntityCart(Level par1Level)
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

	public EntityCart(Level levelIn, double x, double y, double z)
	{
		this(levelIn);
		setPosition(x, y, z);
	}

	@Override
	public AnimationHandler getAnimationHandler()
	{
		return EntityCart.animHandler;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

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
	public boolean processInitialInteract(PlayerEntity player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		boolean isPulling = false;
		List horses = AnimaniaHelper.getEntitiesInRange(HorseEntity.class, 3, level, player);
		List pigs = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 3, level, player);
		List carts = AnimaniaHelper.getEntitiesInRangeGeneric(EntityCart.class, 3, level, this);
		List wagons = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, level, this);
		List tillers = AnimaniaHelper.getEntitiesInRangeGeneric(EntityTiller.class, 3, level, this);

		if (!carts.isEmpty())
		{
			for (int i = 0; i < carts.size(); i++)
			{
				EntityCart tempCart = (EntityCart) carts.get(i);
				if (tempCart.pulled && tempCart.puller == player.getRidingEntity())
				{
					isPulling = true;
				}
			}
		}

		if (!wagons.isEmpty())
		{
			for (int i = 0; i < wagons.size(); i++)
			{
				EntityWagon tempWagon = (EntityWagon) wagons.get(i);
				if (tempWagon.pulled && tempWagon.puller == player.getRidingEntity())
				{
					isPulling = true;
				}
			}
		}

		if (!tillers.isEmpty())
		{
			for (int i = 0; i < tillers.size(); i++)
			{
				EntityTiller tempTiller = (EntityTiller) tillers.get(i);
				if (tempTiller.pulled && tempTiller.puller == player.getRidingEntity())
				{
					isPulling = true;
				}
			}
		}

		HorseEntity horse = null;
		EntityAnimaniaPig pig = null;
		if (!horses.isEmpty())
		{
			horse = (HorseEntity) horses.get(0);
		}
		if (!pigs.isEmpty())
		{
			pig = (EntityAnimaniaPig) pigs.get(0);
		}

		if (player.isSneaking())
		{
			if (!this.level.isRemote && this.getHasChest())
			{
				this.cartChest.setCustomName(this.getName());
				player.openGui(Animania.instance, GUI_ID, player.level, this.getEntityId(), 0, 0);
				level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.PLAYERS, 0.7F, 1.0F);
			} else if (!this.getHasChest() && stack.getItem() != Item.getItemFromBlock(Blocks.CHEST) && player.getRidingEntity() != this)
			{
				player.startRiding(this);

				this.rideCooldown = 20;
				if (this.puller == player)
				{
					this.puller = null;
					this.pulled = false;
					this.setPullerType(0);
					this.stopCart();
				}
			}

			return true;
		} else if (!player.isSneaking())
		{

			if (stack.getItem() == Item.getItemFromBlock(Blocks.CHEST) && !this.getHasChest() && !this.isBeingRidden())
			{
				this.setHasChest(true);
				if (!player.isCreative())
				{
					stack.shrink(1);
				}

				if (!level.isRemote)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_CHEST_LOCKED, SoundCategory.PLAYERS, 0.7F, 1.0F);
				}
				return true;
			} else if (player.isPassenger() && this.puller != player && this.puller != player.getRidingEntity() && player.getRidingEntity() != this && !isPulling)
			{

				this.pulled = true;
				this.puller = player.getRidingEntity();
				if (this.puller instanceof HorseEntity)
				{
					this.setPullerType(1);
				} else if (this.puller instanceof EntityAnimaniaPig)
				{
					this.setPullerType(3);
				}
				if (!level.isRemote)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}

				return true;
			} else if (player.isPassenger() && this.puller == player.getRidingEntity() && player.getRidingEntity() != this)
			{
				this.pulled = false;
				this.puller = null;
				this.setPullerType(0);
				if (!level.isRemote)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				stopCart();
				return true;
			} else if ((stack.getItem() == Items.AIR || stack.getItem() == Items.LEAD) && horse != null && horse.getLeashHolder() == player && !isPulling)
			{
				this.pulled = true;
				this.puller = horse;
				this.setPullerType(1);
				horse.clearLeashed(true, false);
				if (!player.isCreative())
				{
					player.inventory.addItemStackToInventory(new ItemStack(Items.LEAD, 1));
				}
				if (!level.isRemote)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				return true;
			} else if ((stack.getItem() == Items.AIR || stack.getItem() == Items.LEAD) && pig != null && pig.getLeashHolder() == player && !isPulling)
			{
				this.pulled = true;
				this.puller = pig;
				this.setPullerType(3);
				pig.clearLeashed(true, false);
				if (!player.isCreative())
				{
					player.inventory.addItemStackToInventory(new ItemStack(Items.LEAD, 1));
				}
				if (!level.isRemote)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				return true;
			} else if (stack.isEmpty() && !player.isPassenger() && this.puller != player && this.getControllingPassenger() != player && !level.isRemote && !isPulling)
			{

				double diffx = Math.abs(this.getX() - player.getX());
				double diffy = Math.abs(this.getY() - player.getY());
				double diffz = Math.abs(this.getZ() - player.getZ());

				if (diffx > 0 && diffy < 2 && diffz > 0)
				{
					this.pulled = true;
					this.puller = player;
					this.setPullerType(2);
					if (!level.isRemote)
					{
						level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);

					}
				}
				return true;
			} else if (stack.isEmpty() && !player.isPassenger() && this.puller == player && this.getControllingPassenger() != player && !level.isRemote)
			{
				this.pulled = false;
				this.puller = null;
				this.setPullerType(0);
				if (!level.isRemote)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				stopCart();
				return true;
			} else
			{
				this.pulled = false;
				this.puller = null;
				this.setPullerType(0);
				if (!level.isRemote)
				{
					level.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				stopCart();
				return true;
			}
		}
		return true;

	}

	private void controlBoat()
	{
		if (this.isBeingRidden())
		{
			float f = 0.0F;

		}
	}

	public void setDamageTaken(float damageTaken)
	{
		this.dataManager.set(EntityCart.DAMAGE_TAKEN, Float.valueOf(damageTaken));
	}

	public float getDamageTaken()
	{
		return ((Float) this.dataManager.get(EntityCart.DAMAGE_TAKEN)).floatValue();
	}

	public void setHasChest(boolean hasChest)
	{
		this.dataManager.set(EntityCart.HAS_CHEST, Boolean.valueOf(hasChest));
	}

	public boolean getHasChest()
	{
		return ((boolean) this.dataManager.get(EntityCart.HAS_CHEST).booleanValue());
	}

	public void setTimeSinceHit(int timeSinceHit)
	{
		this.dataManager.set(EntityCart.TIME_SINCE_HIT, Integer.valueOf(timeSinceHit));
	}

	public int getTimeSinceHit()
	{
		return ((Integer) this.dataManager.get(EntityCart.TIME_SINCE_HIT)).intValue();
	}

	public void applyEntityCollision(Entity entityIn)
	{
		if (entityIn instanceof EntityCart)
		{
			if (entityIn.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY && !this.pulled)
			{
				super.applyEntityCollision(entityIn);

			}
		} else if (entityIn.getEntityBoundingBox().minY <= this.getEntityBoundingBox().minY && !this.pulled)
		{
			super.applyEntityCollision(entityIn);

		}
	}

	@Override
	public void onUpdate()
	{

		// Dismount if sneak pressed and riding this
		if (this.isBeingRidden() && this.getControllingPassenger() instanceof PlayerEntity && this.rideCooldown == 0)
		{
			PlayerEntity player = (PlayerEntity) this.getControllingPassenger();
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
			if (AnimalEntity.isBeingRidden() && AnimalEntity.getControllingPassenger() instanceof PlayerEntity)
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
		if (this.isBeingRidden() && this.getControllingPassenger() instanceof PlayerEntity && this.rideCooldown > 10 && level.isRemote)
		{
			PlayerEntity player = (PlayerEntity) this.getControllingPassenger();
			player.sendStatusMessage(new TextComponentString(I18n.format("mount.onboard", Minecraft.getMinecraft().gameSettings.keyBindSneak.getDisplayName())), true);
		}

		// Determine animation direction based on previous pos
		if (this.pulled && this.puller != null && level.isRemote)
		{

			double movX = Math.abs(this.getX() - this.prevgetX());
			double movZ = Math.abs(this.getZ() - this.prevgetZ());

			double moveThresh = .005D;

			if ((this.cartYaw < 0 && this.motionX > .001 && this.motionZ > .001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart_chest_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_cart_chest", this);
				}
			} else if ((this.cartYaw > 0 && this.motionX < -.001 && this.motionZ < -.001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart_chest_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_cart_chest", this);
				}
			} else if ((this.cartYaw > 0 && this.motionX < -.001 && this.motionZ > .001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart_chest_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_cart_chest", this);
				}
			} else if ((this.cartYaw < 0 && this.motionX > .001 && this.motionZ < -.001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart_chest_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_cart_chest", this);
				}
			} else if ((this.cartYaw < 0 && this.motionX < -.001 && this.motionZ < -.001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart_chest", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest_back", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_cart_chest_back", this);
				}
			} else if ((this.cartYaw > 0 && this.motionX > .001 && this.motionZ > .001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart_chest", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest_back", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_cart_chest_back", this);
				}
			} else if ((this.cartYaw > 0 && this.motionX > .001 && this.motionZ < -.001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart_chest", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest_back", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_cart_chest_back", this);
				}
			} else if ((this.cartYaw < 0 && this.motionX < -.001 && this.motionZ > .001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart_chest", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest_back", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_cart_chest_back", this);
				}

			}

		}

		// Add slowness if multiple carts being pulled
		if (this.pulled && this.puller instanceof PlayerEntity)
		{
			List carts = AnimaniaHelper.getEntitiesInRangeGeneric(EntityCart.class, 3, level, this);

			PlayerEntity player = (PlayerEntity) this.puller;
			int totPulling = 0;
			if (!carts.isEmpty())
			{
				if (carts.size() > 1)
				{
					for (int i = 0; i < carts.size(); i++)
					{
						EntityCart tempCart = (EntityCart) carts.get(i);
						if (tempCart.pulled && tempCart.puller == player && tempCart != this)
						{
							totPulling++;
						}
					}
				}
				if (totPulling > 0)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, carts.size() + 1, false, false));
				}
			}
		}

		// TODO didn't work

		if (this.pulled && this.puller instanceof AnimalEntity)
		{
			List carts = AnimaniaHelper.getEntitiesInRangeGeneric(EntityCart.class, 3, level, this);
			List wagons = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, level, this);
			List tillers = AnimaniaHelper.getEntitiesInRangeGeneric(EntityTiller.class, 3, level, this);

			AnimalEntity animal = (AnimalEntity) this.puller;

			// System.out.println(carts.size() + wagons.size() +
			// tillers.size());

			if (carts.size() + wagons.size() + tillers.size() > 1)
			{

				if (!carts.isEmpty())
				{
					if (carts.size() > 1)
					{
						for (int i = 0; i < carts.size(); i++)
						{
							EntityCart tempCart = (EntityCart) carts.get(i);
							if (tempCart.pulled && tempCart.puller == animal && tempCart != this)
							{
								tempCart.pulled = false;
								tempCart.puller = null;
							}
						}
					}
				}

				if (!wagons.isEmpty())
				{
					if (wagons.size() > 1)
					{
						for (int i = 0; i < wagons.size(); i++)
						{
							EntityWagon tempWagon = (EntityWagon) wagons.get(i);
							if (tempWagon.pulled && tempWagon.puller == animal)
							{
								tempWagon.pulled = false;
								tempWagon.puller = null;
							}
						}
					}
				}

				if (!tillers.isEmpty())
				{
					if (tillers.size() > 1)
					{
						for (int i = 0; i < tillers.size(); i++)
						{
							EntityTiller tempTiller = (EntityTiller) tillers.get(i);
							if (tempTiller.pulled && tempTiller.puller == animal)
							{
								tempTiller.pulled = false;
								tempTiller.puller = null;
							}
						}
					}
				}
			}

		}

		// Stop Animation if not pulling or moving

		if (this.level.isRemote && this.pulled)
		{
			double diffX = (this.getX() - this.prevgetX());
			double diffZ = (this.getZ() - this.prevgetZ());

			double movX = Math.abs(this.getX() - this.prevgetX());
			double movZ = Math.abs(this.getZ() - this.prevgetZ());

			double moveThresh = .008D;

			if ((diffX < .005 && diffZ < .005) && (movX + movZ < moveThresh) && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest", this))
			{
				this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart_chest", this);
			}

			if ((diffX < .005 && diffZ < .005) && (movX + movZ < moveThresh) && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest_back", this))
			{
				this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart_chest_back", this);
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
			} else if (this.getPullerType() == 2)
			{
				List entities = AnimaniaHelper.getEntitiesInRange(PlayerEntity.class, 3, this.level, this);
				if (!entities.isEmpty())
				{
					this.puller = (Entity) entities.get(0);
					this.pulled = true;
					this.setPullerType(2);
				}
			} else if (this.getPullerType() == 3)
			{
				List entities = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 3, this.level, this);
				if (!entities.isEmpty())
				{
					this.puller = (Entity) entities.get(0);
					this.pulled = true;
					this.setPullerType(3);
				}

			} else
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
			} else if (this.puller instanceof EntityAnimaniaPig)
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
			stopCart();
		}

		if (this.pulled)
		{
			double deltaAngle = -Math.atan2(this.puller.getX() - this.getX(), this.puller.getZ() - this.getZ());

			Vec3d vec = new Vec3d(this.puller.getX(), this.puller.getY(), this.puller.getZ()).subtract(new Vec3d(this.getX(), this.getY(), this.getZ())).add(new Vec3d(0.0D, 0.0D, -2.5D).rotateYaw((float) -deltaAngle));
			this.motionX = vec.x;
			this.motionY = vec.y;
			this.motionZ = vec.z;
			move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

		}

		List<Entity> list = this.level.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D), EntitySelectors.getTeamCollisionPredicate(this));

		if (!list.isEmpty())
		{
			boolean flag = !this.level.isRemote;

			for (int j = 0; j < list.size(); ++j)
			{
				Entity entity = list.get(j);

				if (entity instanceof AnimalEntity)
				{

					AnimalEntity AnimalEntity = (AnimalEntity) entity;
					if (!entity.isPassenger(this))
					{
						if (flag && this.getPassengers().size() < 2 && this.puller != entity && AnimalEntity.getLeashed() && AnimalEntity.getLeashHolder() instanceof PlayerEntity && !entity.isPassenger() && entity.width < this.width && entity instanceof LivingEntity && !(entity instanceof PlayerEntity))
						{
							entity.startRiding(this);
						} else
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
		if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest", this))
		{
			this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart_chest", this);
		}

		if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_cart_chest_back", this))
		{
			this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_cart_chest_back", this);
		}
	}

	private void tickLerp()
	{
		if (this.lerpSteps > 0 && !this.canPassengerSteer())
		{

			double d0 = this.getX() + (this.cartPitch - this.getX()) / (double) this.lerpSteps;
			double d1 = this.getY() + (this.lerpY - this.getY()) / (double) this.lerpSteps;
			double d2 = this.getZ() + (this.lerpZ - this.getZ()) / (double) this.lerpSteps;
			double d3 = MathHelper.wrapDegrees(this.cartYaw - (double) this.rotationYaw);
			if (this.puller != null)
			{
				double deltaAngle = -Math.atan2(this.puller.getX() - this.getX(), this.puller.getZ() - this.getZ());
				this.rotationYaw = (float) Math.toDegrees(deltaAngle);
			}
			this.rotationPitch = (float) ((double) this.rotationPitch + (this.lerpXRot - (double) this.rotationPitch) / (double) this.lerpSteps);
			--this.lerpSteps;
			this.setPosition(d0, d1, d2);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		}
	}

	@SideOnly(Dist.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport)
	{
		this.cartPitch = x;
		this.lerpY = y;
		this.lerpZ = z;
		this.cartYaw = (double) yaw;
		this.lerpXRot = (double) pitch;
		this.lerpSteps = 10;
	}

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
				} else
				{
					f = -0.6F;
				}

				if (passenger instanceof AnimalEntity)
				{
					f = (float) ((double) f + 0.2D);
				}
			}

			Vec3d vec3d = (new Vec3d((double) f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
			passenger.setPosition(this.getX() + vec3d.x, this.getY() + (double) f1, this.getZ() + vec3d.z);
			passenger.rotationYaw += this.deltaRotation;
			passenger.setRotationYawHead(passenger.getRotationYawHead() + this.deltaRotation);
			this.applyYawToEntity(passenger);

			if (passenger instanceof AnimalEntity && this.getPassengers().size() > 1)
			{

				int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
				passenger.setRenderYawOffset(((AnimalEntity) passenger).renderYawOffset + (float) j);
				passenger.setRotationYawHead(passenger.getRotationYawHead() + (float) j);
			}
		}
	}

	protected void applyYawToEntity(Entity entityToUpdate)
	{
		entityToUpdate.setRenderYawOffset(this.rotationYaw);
		float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
		float f1 = MathHelper.clamp(f, 0.0F, 0.0F);
		if (entityToUpdate instanceof PlayerEntity)
		{
			f1 = MathHelper.clamp(f, -105.0F, 105.0F);
		}
		entityToUpdate.prevRotationYaw += f1 - f;
		entityToUpdate.rotationYaw += f1 - f;
		entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
	}

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
		} else if (!this.level.isRemote && !this.isDead)
		{
			if (source instanceof EntityDamageSourceIndirect && source.getTrueSource() != null)
			{
				return false;
			} else
			{

				this.setTimeSinceHit(10);
				this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
				this.markVelocityChanged();
				boolean flag = source.getTrueSource() instanceof PlayerEntity && ((PlayerEntity) source.getTrueSource()).capabilities.isCreativeMode;

				if (flag || this.getDamageTaken() > 40.0F)
				{
					if (!flag && this.level.getGameRules().getBoolean("doEntityDrops"))
					{
						this.dropItemWithOffset(FarmAddonItemHandler.cart, 1, 0.0F);

						if (this.getHasChest())
						{
							InventoryHelper.dropInventoryItems(level, this.getPosition(), cartChest);
							this.dropItemWithOffset(Item.getItemFromBlock(Blocks.CHEST), 1, 0.0F);
						}
					}

					this.setDead();
				}

				return true;
			}
		} else
		{
			return true;
		}
	}

	@Nullable
	public AxisAlignedBB getCollisionBox(Entity entityIn)
	{

		double movX = Math.abs(this.getX() - this.prevgetX());
		double movZ = Math.abs(this.getZ() - this.prevgetZ());

		if (entityIn == this.puller)
		{
			return null;
		} else if (this.pulled)
		{
			return null;
		} else
		{
			return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
		}
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox()
	{
		return this.getEntityBoundingBox();
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		if (this instanceof EntityCart)
		{
			return new ItemStack(FarmAddonItemHandler.cart);
		}

		return ItemStack.EMPTY;
	}

	@Override
	protected void entityInit()
	{
		this.dataManager.register(EntityCart.PULLER_TYPE, Integer.valueOf(0));
		this.dataManager.register(EntityCart.TIME_SINCE_HIT, Integer.valueOf(0));
		this.dataManager.register(EntityCart.DAMAGE_TAKEN, Float.valueOf(0.0F));
		this.dataManager.register(EntityCart.HAS_CHEST, false);
	}

	public int getPullerType()
	{
		return this.dataManager.get(EntityCart.PULLER_TYPE).intValue();
	}

	public void setPullerType(int pullerType)
	{

		this.dataManager.set(EntityCart.PULLER_TYPE, Integer.valueOf(pullerType));
	}

	public void writeEntityToNBT(CompoundTag compound)
	{
		compound.putInteger("PullerType", this.getPullerType());

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.cartChest.getSizeInventory(); ++i)
		{
			ItemStack itemstack = this.cartChest.getStackInSlot(i);

			if (!itemstack.isEmpty())
			{
				CompoundTag CompoundTag = new CompoundTag();
				CompoundTag.setByte("Slot", (byte) i);
				itemstack.writeToNBT(CompoundTag);
				nbttaglist.appendTag(CompoundTag);
			}
		}
		compound.putBoolean("HasChest", this.getHasChest());

		compound.putTag("Items", nbttaglist);
	}

	public void readEntityFromNBT(CompoundTag compound)
	{
		this.setPullerType(compound.getInteger("PullerType"));

		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.initCartChest();

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			CompoundTag CompoundTag = nbttaglist.getCompoundTagAt(i);
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
		} else if (entity instanceof PlayerEntity)
		{
			pullerType = 2;
		} else if (entity instanceof EntityAnimaniaPig)
		{
			pullerType = 3;
		}

		return pullerType;
	}

	public void initCartChest()
	{
		CartChest horsecartchest = this.cartChest;
		this.cartChest = new CartChest("cartChest", 36);
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

	@SideOnly(Dist.CLIENT)
	public void performHurtAnimation()
	{
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11.0F);
	}

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