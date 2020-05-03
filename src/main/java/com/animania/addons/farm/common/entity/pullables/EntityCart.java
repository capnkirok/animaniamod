package com.animania.addons.farm.common.entity.pullables;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.FarmAddon;
import com.animania.addons.farm.common.entity.pigs.EntityAnimaniaPig;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.addons.farm.common.inventory.CartChest;
import com.animania.common.helper.AnimaniaHelper;
import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.AnimationHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCart extends AnimatedEntityBase implements IInventoryChangedListener
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
	protected static final DataParameter<Integer> PULLER_TYPE = EntityDataManager.<Integer> createKey(EntityCart.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> TIME_SINCE_HIT = EntityDataManager.<Integer> createKey(EntityCart.class, DataSerializers.VARINT);
	private static final DataParameter<Float> DAMAGE_TAKEN = EntityDataManager.<Float> createKey(EntityCart.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> HAS_CHEST = EntityDataManager.<Boolean> createKey(EntityCart.class, DataSerializers.BOOLEAN);

	// Gui Id Offset required when multiple addons are installed
	private static final int GUI_ID = 0 + FarmAddon.guiHandler.getGuiIdOffset();

	static
	{
		EntityCart.animHandler.addAnim(Animania.MODID, "anim_cart", "model_cart", true);
		EntityCart.animHandler.addAnim(Animania.MODID, "anim_cart_back", "anim_cart");
		EntityCart.animHandler.addAnim(Animania.MODID, "anim_cart_chest", "model_cart_chest", true);
		EntityCart.animHandler.addAnim(Animania.MODID, "anim_cart_chest_back", "anim_cart_chest");
	}

	public EntityCart(World par1World)
	{
		super(par1World);
		this.preventEntitySpawning = true;
		this.setSize(2.0F, 1.2F);
		this.width = 2.0F;
		this.height = 1.2F;
		this.stepHeight = 1.2F;
		this.puller = null;
		this.pulled = false;
		this.initCartChest();

	}

	public EntityCart(World worldIn, double x, double y, double z)
	{
		this(worldIn);
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
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		boolean isPulling = false;
		List horses = AnimaniaHelper.getEntitiesInRange(EntityHorse.class, 3, world, player);
		List pigs = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 3, world, player);
		List carts = AnimaniaHelper.getEntitiesInRangeGeneric(EntityCart.class, 3, world, this);
		List wagons = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, world, this);
		List tillers = AnimaniaHelper.getEntitiesInRangeGeneric(EntityTiller.class, 3, world, this);

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

		EntityHorse horse = null;
		EntityAnimaniaPig pig = null;
		if (!horses.isEmpty())
		{
			horse = (EntityHorse) horses.get(0);
		}
		if (!pigs.isEmpty())
		{
			pig = (EntityAnimaniaPig) pigs.get(0);
		}

		if (player.isSneaking())
		{
			if (!this.world.isRemote && this.getHasChest())
			{
				this.cartChest.setCustomName(this.getName());
				player.openGui(Animania.instance, GUI_ID, player.world, this.getEntityId(), 0, 0);
				world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.PLAYERS, 0.7F, 1.0F);
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

				if (!world.isRemote)
				{
					world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_CHEST_LOCKED, SoundCategory.PLAYERS, 0.7F, 1.0F);
				}
				return true;
			} else if (player.isRiding() && this.puller != player && this.puller != player.getRidingEntity() && player.getRidingEntity() != this && !isPulling)
			{

				this.pulled = true;
				this.puller = player.getRidingEntity();
				if (this.puller instanceof EntityHorse)
				{
					this.setPullerType(1);
				} else if (this.puller instanceof EntityAnimaniaPig)
				{
					this.setPullerType(3);
				}
				if (!world.isRemote)
				{
					world.playSound(null, player.posX, player.posY, player.posZ, FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}

				return true;
			} else if (player.isRiding() && this.puller == player.getRidingEntity() && player.getRidingEntity() != this)
			{
				this.pulled = false;
				this.puller = null;
				this.setPullerType(0);
				if (!world.isRemote)
				{
					world.playSound(null, player.posX, player.posY, player.posZ, FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
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
				if (!world.isRemote)
				{
					world.playSound(null, player.posX, player.posY, player.posZ, FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
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
				if (!world.isRemote)
				{
					world.playSound(null, player.posX, player.posY, player.posZ, FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				return true;
			} else if (stack.isEmpty() && !player.isRiding() && this.puller != player && this.getControllingPassenger() != player && !world.isRemote && !isPulling)
			{

				double diffx = Math.abs(this.posX - player.posX);
				double diffy = Math.abs(this.posY - player.posY);
				double diffz = Math.abs(this.posZ - player.posZ);

				if (diffx > 0 && diffy < 2 && diffz > 0)
				{
					this.pulled = true;
					this.puller = player;
					this.setPullerType(2);
					if (!world.isRemote)
					{
						world.playSound(null, player.posX, player.posY, player.posZ, FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);

					}
				}
				return true;
			} else if (stack.isEmpty() && !player.isRiding() && this.puller == player && this.getControllingPassenger() != player && !world.isRemote)
			{
				this.pulled = false;
				this.puller = null;
				this.setPullerType(0);
				if (!world.isRemote)
				{
					world.playSound(null, player.posX, player.posY, player.posZ, FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				stopCart();
				return true;
			} else
			{
				this.pulled = false;
				this.puller = null;
				this.setPullerType(0);
				if (!world.isRemote)
				{
					world.playSound(null, player.posX, player.posY, player.posZ, FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
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
		if (this.isBeingRidden() && this.getControllingPassenger() instanceof EntityPlayer && this.rideCooldown == 0)
		{
			EntityPlayer player = (EntityPlayer) this.getControllingPassenger();
			if (player.isSneaking())
			{
				player.applyEntityCollision(this);
				this.dismountRidingEntity();
				player.dismountEntity(this);
				this.removePassengers();

			}
		}

		// Mounting a horse that is riding a cart...
		if (this.isBeingRidden() && this.getControllingPassenger() instanceof EntityAnimal)
		{

			EntityAnimal entityanimal = (EntityAnimal) this.getControllingPassenger();
			if (entityanimal.isBeingRidden() && entityanimal.getControllingPassenger() instanceof EntityPlayer)
			{
				entityanimal.applyEntityCollision(this);
				entityanimal.dismountRidingEntity();
				this.dismountRidingEntity();
				entityanimal.dismountEntity(this);
				entityanimal.removePassengers();
				this.removePassengers();
			}

		}

		// Dismount text
		if (this.isBeingRidden() && this.getControllingPassenger() instanceof EntityPlayer && this.rideCooldown > 10 && world.isRemote)
		{
			EntityPlayer player = (EntityPlayer) this.getControllingPassenger();
			player.sendStatusMessage(new TextComponentString(I18n.format("mount.onboard", Minecraft.getMinecraft().gameSettings.keyBindSneak.getDisplayName())), true);
		}

		// Determine animation direction based on previous pos
		if (this.pulled && this.puller != null && world.isRemote)
		{

			double movX = Math.abs(this.posX - this.prevPosX);
			double movZ = Math.abs(this.posZ - this.prevPosZ);

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
		if (this.pulled && this.puller instanceof EntityPlayer)
		{
			List carts = AnimaniaHelper.getEntitiesInRangeGeneric(EntityCart.class, 3, world, this);

			EntityPlayer player = (EntityPlayer) this.puller;
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

		if (this.pulled && this.puller instanceof EntityAnimal)
		{
			List carts = AnimaniaHelper.getEntitiesInRangeGeneric(EntityCart.class, 3, world, this);
			List wagons = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, world, this);
			List tillers = AnimaniaHelper.getEntitiesInRangeGeneric(EntityTiller.class, 3, world, this);

			EntityAnimal animal = (EntityAnimal) this.puller;

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

		if (this.world.isRemote && this.pulled)
		{
			double diffX = (this.posX - this.prevPosX);
			double diffZ = (this.posZ - this.prevPosZ);

			double movX = Math.abs(this.posX - this.prevPosX);
			double movZ = Math.abs(this.posZ - this.prevPosZ);

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
				List entities = AnimaniaHelper.getEntitiesInRange(EntityHorse.class, 3, this.world, this);
				if (!entities.isEmpty())
				{
					this.puller = (Entity) entities.get(0);
					this.pulled = true;
					this.setPullerType(1);
				}
			} else if (this.getPullerType() == 2)
			{
				List entities = AnimaniaHelper.getEntitiesInRange(EntityPlayer.class, 3, this.world, this);
				if (!entities.isEmpty())
				{
					this.puller = (Entity) entities.get(0);
					this.pulled = true;
					this.setPullerType(2);
				}
			} else if (this.getPullerType() == 3)
			{
				List entities = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 3, this.world, this);
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

		if (this.pulled && this.puller != null && this.puller.isRiding())
		{
			this.puller = this.puller.getRidingEntity();
			if (this.puller instanceof EntityHorse)
			{
				this.setPullerType(1);
			} else if (this.puller instanceof EntityAnimaniaPig)
			{
				this.setPullerType(3);
			}
		}

		if (this.puller != null && (Math.abs(this.puller.posX - this.posX) > 5 || Math.abs(this.puller.posZ - this.posZ) > 5))
		{
			this.pulled = false;
			this.puller = null;
			this.setPullerType(0);
			world.playSound(null, this.posX, this.posY, this.posZ, FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
			stopCart();
		}

		if (this.pulled)
		{
			double deltaAngle = -Math.atan2(this.puller.posX - this.posX, this.puller.posZ - this.posZ);

			Vec3d vec = new Vec3d(this.puller.posX, this.puller.posY, this.puller.posZ).subtract(new Vec3d(this.posX, this.posY, this.posZ)).add(new Vec3d(0.0D, 0.0D, -2.5D).rotateYaw((float) -deltaAngle));
			this.motionX = vec.x;
			this.motionY = vec.y;
			this.motionZ = vec.z;
			move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);

		}

		List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D), EntitySelectors.getTeamCollisionPredicate(this));

		if (!list.isEmpty())
		{
			boolean flag = !this.world.isRemote;

			for (int j = 0; j < list.size(); ++j)
			{
				Entity entity = list.get(j);

				if (entity instanceof EntityAnimal)
				{

					EntityAnimal entityanimal = (EntityAnimal) entity;
					if (!entity.isPassenger(this))
					{
						if (flag && this.getPassengers().size() < 2 && this.puller != entity && entityanimal.getLeashed() && entityanimal.getLeashHolder() instanceof EntityPlayer && !entity.isRiding() && entity.width < this.width && entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer))
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

			double d0 = this.posX + (this.cartPitch - this.posX) / (double) this.lerpSteps;
			double d1 = this.posY + (this.lerpY - this.posY) / (double) this.lerpSteps;
			double d2 = this.posZ + (this.lerpZ - this.posZ) / (double) this.lerpSteps;
			double d3 = MathHelper.wrapDegrees(this.cartYaw - (double) this.rotationYaw);
			if (this.puller != null)
			{
				double deltaAngle = -Math.atan2(this.puller.posX - this.posX, this.puller.posZ - this.posZ);
				this.rotationYaw = (float) Math.toDegrees(deltaAngle);
			}
			this.rotationPitch = (float) ((double) this.rotationPitch + (this.lerpXRot - (double) this.rotationPitch) / (double) this.lerpSteps);
			--this.lerpSteps;
			this.setPosition(d0, d1, d2);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		}
	}

	@SideOnly(Side.CLIENT)
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

				if (passenger instanceof EntityAnimal)
				{
					f = (float) ((double) f + 0.2D);
				}
			}

			Vec3d vec3d = (new Vec3d((double) f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
			passenger.setPosition(this.posX + vec3d.x, this.posY + (double) f1, this.posZ + vec3d.z);
			passenger.rotationYaw += this.deltaRotation;
			passenger.setRotationYawHead(passenger.getRotationYawHead() + this.deltaRotation);
			this.applyYawToEntity(passenger);

			if (passenger instanceof EntityAnimal && this.getPassengers().size() > 1)
			{

				int j = passenger.getEntityId() % 2 == 0 ? 90 : 270;
				passenger.setRenderYawOffset(((EntityAnimal) passenger).renderYawOffset + (float) j);
				passenger.setRotationYawHead(passenger.getRotationYawHead() + (float) j);
			}
		}
	}

	protected void applyYawToEntity(Entity entityToUpdate)
	{
		entityToUpdate.setRenderYawOffset(this.rotationYaw);
		float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
		float f1 = MathHelper.clamp(f, 0.0F, 0.0F);
		if (entityToUpdate instanceof EntityPlayer)
		{
			f1 = MathHelper.clamp(f, -105.0F, 105.0F);
		}
		entityToUpdate.prevRotationYaw += f1 - f;
		entityToUpdate.rotationYaw += f1 - f;
		entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
	}

	@SideOnly(Side.CLIENT)
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
		} else if (!this.world.isRemote && !this.isDead)
		{
			if (source instanceof EntityDamageSourceIndirect && source.getTrueSource() != null)
			{
				return false;
			} else
			{

				this.setTimeSinceHit(10);
				this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
				this.markVelocityChanged();
				boolean flag = source.getTrueSource() instanceof EntityPlayer && ((EntityPlayer) source.getTrueSource()).capabilities.isCreativeMode;

				if (flag || this.getDamageTaken() > 40.0F)
				{
					if (!flag && this.world.getGameRules().getBoolean("doEntityDrops"))
					{
						this.dropItemWithOffset(FarmAddonItemHandler.cart, 1, 0.0F);

						if (this.getHasChest())
						{
							InventoryHelper.dropInventoryItems(world, this.getPosition(), cartChest);
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

		double movX = Math.abs(this.posX - this.prevPosX);
		double movZ = Math.abs(this.posZ - this.prevPosZ);

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

	public void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setInteger("PullerType", this.getPullerType());

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.cartChest.getSizeInventory(); ++i)
		{
			ItemStack itemstack = this.cartChest.getStackInSlot(i);

			if (!itemstack.isEmpty())
			{
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				itemstack.writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}
		compound.setBoolean("HasChest", this.getHasChest());

		compound.setTag("Items", nbttaglist);
	}

	public void readEntityFromNBT(NBTTagCompound compound)
	{
		this.setPullerType(compound.getInteger("PullerType"));

		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.initCartChest();

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;

			if (j >= 0 && j < this.cartChest.getSizeInventory())
			{
				this.cartChest.setInventorySlotContents(j, new ItemStack(nbttagcompound));
			}
		}
		this.setHasChest(compound.getBoolean("HasChest"));
	}

	public int determinePullerType(Entity entity)
	{
		int pullerType = 0;
		if (entity instanceof EntityHorse)
		{
			pullerType = 1;
		} else if (entity instanceof EntityPlayer)
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

	@SideOnly(Side.CLIENT)
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