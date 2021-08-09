package com.animania.addons.farm.common.entity.pullables;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.FarmAddon;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.addons.farm.common.inventory.CartChest;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.common.helper.AnimaniaHelper;
import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.AnimationHandler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import AnimationHandler;

public class EntityWagon extends AnimatedEntityBase implements IInventoryChangedListener
{
	protected static AnimationHandler animHandler = CraftStudioApi.getNewAnimationHandler(EntityWagon.class);
	public boolean pulled;
	public Entity puller;
	public CartChest wagonChest;
	public float deltaRotation;
	private int lerpSteps;
	private double wagonPitch;
	private double lerpY;
	private double lerpZ;
	public double wagonYaw;
	private double lerpXRot;
	private int sleepDelay = 0;
	private boolean sleepFlag = true;
	private boolean allSleeping = false;
	private int lastLighting = 60;
	private int sleepTimer = 0;
	public int blinkTimer;

	protected static final DataParameter<Integer> PULLER_TYPE = EntityDataManager.<Integer> createKey(EntityWagon.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> TIME_SINCE_HIT = EntityDataManager.<Integer> createKey(EntityWagon.class, DataSerializers.VARINT);
	private static final DataParameter<Float> DAMAGE_TAKEN = EntityDataManager.<Float> createKey(EntityWagon.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> HAS_CHEST = EntityDataManager.<Boolean> createKey(EntityWagon.class, DataSerializers.BOOLEAN);

	// Gui Id Offset required when multiple addons are installed
	private static final int GUI_ID = 1 + FarmAddon.guiHandler.getGuiIdOffset();

	static
	{
		EntityWagon.animHandler.addAnim(Animania.MODID, "anim_wagon", "model_wagon", true);
		EntityWagon.animHandler.addAnim(Animania.MODID, "anim_wagon_back", "anim_wagon");
	}

	public EntityWagon(World par1World)
	{
		super(par1World);
		this.preventEntitySpawning = true;
		this.setSize(2.5F, 1.2F);
		this.width = 2.5F;
		this.height = 1.2F;
		this.stepHeight = 1.2F;
		this.puller = null;
		this.pulled = false;
		this.initwagonChest();
		this.blinkTimer = 15;

	}

	public EntityWagon(World worldIn, double x, double y, double z)
	{
		this(worldIn);
		setPosition(x, y, z);
	}

	@Override
	public AnimationHandler getAnimationHandler()
	{
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
		return !this.isDead;
	}

	@Override
	public double getMountedYOffset()
	{
		return 0.9D;
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
		List horses = AnimaniaHelper.getEntitiesInRange(HorseEntity.class, 3, world, player);
		List wagons = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, world, this);

		HorseEntity horse = null;
		if (!horses.isEmpty())
		{
			horse = (HorseEntity) horses.get(0);
		}

		double diffx = this.getX() - player.getX();
		double diffy = this.getY() - player.getY();
		double diffz = this.getZ() - player.getZ();

		double mdiffx = Math.abs(this.getX() - player.getX());
		double mdiffy = Math.abs(this.getY() - player.getY());
		double mdiffz = Math.abs(this.getZ() - player.getZ());

		if (player.isSneaking())
		{

			// Add in check for front of cart vs. Back

			if (mdiffx > 0 && mdiffx < 2.4 && mdiffy < 1.25 && mdiffz > 0 && mdiffz < 2.4)
			{
				this.wagonChest.setCustomName(this.getName());
				if (!this.world.isRemote)
				{
					player.openGui(Animania.instance, GUI_ID, player.level, this.getEntityId(), 0, 0);
					world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.PLAYERS, 0.7F, 1.0F);
				}
				return true;

			} else if (!player.isRiding())
			{
				player.startRiding(this);

				this.rideCooldown = 20;
				if (this.puller == player)
				{
					this.puller = null;
					this.pulled = false;
					this.setPullerType(0);
					this.stopwagon();
				}
				return true;
			}
			return true;

		} else if (!player.isSneaking() && FarmConfig.settings.sleepAllowedWagon)
		{

			if (mdiffx > 0 && mdiffx < 1 && mdiffy > 1.15 && mdiffy < 1.25 && mdiffz > 0 && mdiffz < 1.6)
			{

				if (world.provider.canRespawnHere())

				{
					sleepFlag = false;
					allSleeping = true;
					PlayerEntity player1 = null;
					Iterator iterator = world.playerEntities.iterator();
					while (iterator.hasNext())
					{
						PlayerEntity player2 = (PlayerEntity) iterator.next();

						if (player2 != player)
						{
							if (!player2.isPlayerSleeping())
							{
								allSleeping = false;
							}
						}
					}

					PlayerEntity.SleepResult player$sleepresult = trySleep(new BlockPos(player.getX(), player.getY(), player.getZ()), player);

					if (player$sleepresult == PlayerEntity.SleepResult.OK && allSleeping == true)
					{

						sleepFlag = true;
						sleepDelay = 0;

					} else
					{
						if (player$sleepresult == PlayerEntity.SleepResult.NOT_POSSIBLE_NOW)
						{
							sleepFlag = false;
							player.sendStatusMessage(new TextComponentTranslation("tile.bed.noSleep", new Object[0]), true);
						} else if (player$sleepresult == PlayerEntity.SleepResult.NOT_SAFE)
						{
							sleepFlag = false;
							player.sendStatusMessage(new TextComponentTranslation("tile.bed.notSafe", new Object[0]), true);

						} else if (player$sleepresult == PlayerEntity.SleepResult.NOT_POSSIBLE_HERE)
						{
							sleepFlag = false;
							player.sendStatusMessage(new TextComponentTranslation("tile.bed.notHere", new Object[0]), true);

						}

					}

					if (player$sleepresult == PlayerEntity.SleepResult.OK)
					{

						sleepFlag = true;
						if (!world.isRemote)
						{
							player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30, 1, false, false));
							world.updateAllPlayersSleepingFlag();
							Animania.proxy.Sleep(player);
							player.setSpawnPoint(getPosition(), true);

						}

						this.sleepTimer = 120;

					}

				}

				return true;

			} else if (player.isRiding() && this.puller != player && this.puller != player.getRidingEntity() && player.getRidingEntity() != this)
			{

				this.pulled = true;
				this.puller = player.getRidingEntity();
				if (this.puller instanceof HorseEntity)
				{
					this.setPullerType(1);
				}
				if (!world.isRemote)
				{
					world.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}

				return true;
			} else if (player.isRiding() && this.puller == player.getRidingEntity() && player.getRidingEntity() != this)
			{
				this.pulled = false;
				this.puller = null;
				this.setPullerType(0);
				if (!world.isRemote)
				{
					world.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				stopwagon();
				return true;
			} else if ((stack.getItem() == Items.AIR || stack.getItem() == Items.LEAD) && horse != null && horse.getLeashHolder() == player)
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
					world.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				return true;
			} else if (stack.isEmpty() && !player.isRiding() && this.puller != player && this.getControllingPassenger() != player && !world.isRemote)
			{

				if (mdiffx > 0 && mdiffy < 2 && mdiffz > 0)
				{
					this.pulled = true;
					this.puller = player;
					this.setPullerType(2);
					if (!world.isRemote)
					{
						world.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);

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
					world.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				stopwagon();
				return true;
			} else
			{
				this.pulled = false;
				this.puller = null;
				this.setPullerType(0);
				if (!world.isRemote)
				{
					world.playSound(null, player.getX(), player.getY(), player.getZ(), FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}
				stopwagon();
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
		this.dataManager.set(EntityWagon.DAMAGE_TAKEN, Float.valueOf(damageTaken));
	}

	public float getDamageTaken()
	{
		return this.dataManager.get(EntityWagon.DAMAGE_TAKEN).floatValue();
	}

	public void setHasChest(boolean hasChest)
	{
		this.dataManager.set(EntityWagon.HAS_CHEST, Boolean.valueOf(hasChest));
	}

	public boolean getHasChest()
	{
		return (this.dataManager.get(EntityWagon.HAS_CHEST).booleanValue());
	}

	public void setTimeSinceHit(int timeSinceHit)
	{
		this.dataManager.set(EntityWagon.TIME_SINCE_HIT, Integer.valueOf(timeSinceHit));
	}

	public int getTimeSinceHit()
	{
		return this.dataManager.get(EntityWagon.TIME_SINCE_HIT).intValue();
	}

	@Override
	public void applyEntityCollision(Entity entityIn)
	{
		if (entityIn instanceof EntityWagon)
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

		if (this.blinkTimer > 0)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
			{
				this.blinkTimer = 15;
			}
		}

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

		// Mounting a horse that is riding a wagon...
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

		if (sleepTimer > 0)
		{
			sleepTimer--;
		}

		// Dismount text
		if (this.isBeingRidden() && this.getControllingPassenger() instanceof PlayerEntity && this.rideCooldown > 10 && world.isRemote)
		{
			PlayerEntity player = (PlayerEntity) this.getControllingPassenger();
			player.sendStatusMessage(new TextComponentString(I18n.format("mount.onboard", Minecraft.getMinecraft().gameSettings.keyBindSneak.getDisplayName())), true);
		}

		// Determine animation direction based on previous pos
		if (this.pulled && this.puller != null && world.isRemote)
		{

			double movX = Math.abs(this.puller.getX() - this.puller.prevgetX());
			double movZ = Math.abs(this.puller.getZ() - this.puller.prevgetZ());

			// double moveThresh = .005D;
			double moveThresh = (movX + movZ - 1); // TODO Testing

			if ((this.wagonYaw < 0 && this.puller.motionX > .001 && this.puller.motionZ > .001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon", this);
				}
			} else if ((this.wagonYaw > 0 && this.puller.motionX < -.001 && this.puller.motionZ < -.001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon", this);
				}
			} else if ((this.wagonYaw > 0 && this.puller.motionX < -.001 && this.puller.motionZ > .001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon", this);
				}
			} else if ((this.wagonYaw < 0 && this.puller.motionX > .001 && this.puller.motionZ < -.001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon", this);
				}
			} else if ((this.wagonYaw < 0 && this.puller.motionX < -.001 && this.puller.motionZ < -.001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon_back", this);
				}
			} else if ((this.wagonYaw > 0 && this.puller.motionX > .001 && this.puller.motionZ > .001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon_back", this);
				}
			} else if ((this.wagonYaw > 0 && this.puller.motionX > .001 && this.puller.motionZ < -.001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon_back", this);
				}
			} else if ((this.wagonYaw < 0 && this.puller.motionX < -.001 && this.puller.motionZ > .001) && (movX + movZ > moveThresh))
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon_back", this);
				}

			}

		}

		// Add slowness if multiple wagons being pulled or if player
		if (this.pulled && this.puller instanceof PlayerEntity)
		{
			List wagons = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, world, this);
			PlayerEntity player = (PlayerEntity) this.puller;
			int totPulling = 0;
			if (!wagons.isEmpty())
			{
				if (wagons.size() >= 0)
				{
					for (int i = 0; i < wagons.size(); i++)
					{
						EntityWagon tempwagon = (EntityWagon) wagons.get(i);
						if (tempwagon.pulled && tempwagon.puller == player)
						{
							totPulling++;
						}
					}
				}
				if (totPulling > 0)
				{
					player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, wagons.size() + 1, false, false));
				}
			}
		}

		if (this.pulled && this.puller instanceof AnimalEntity)
		{
			List wagons = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, world, this);
			AnimalEntity animal = (AnimalEntity) this.puller;
			int totPulling = 0;
			if (!wagons.isEmpty())
			{
				if (wagons.size() > 1)
				{
					for (int i = 0; i < wagons.size(); i++)
					{
						EntityWagon tempwagon = (EntityWagon) wagons.get(i);
						if (tempwagon.pulled && tempwagon.puller == animal && tempwagon != this)
						{
							totPulling++;
						}
					}
				}
				if (totPulling > 0)
				{
					animal.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, wagons.size() + 1, false, false));
				}
			}
		}

		// Stop Animation if not pulling or moving

		if (this.world.isRemote && this.pulled)
		{
			double diffX = (this.getX() - this.prevgetX());
			double diffZ = (this.getZ() - this.prevgetZ());

			double movX = Math.abs(this.getX() - this.prevgetX());
			double movZ = Math.abs(this.getZ() - this.prevgetZ());

			double moveThresh = .008D;

			if ((diffX < .005 && diffZ < .005) && (movX + movZ < moveThresh) && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
			{
				this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon", this);
			}

			if ((diffX < .005 && diffZ < .005) && (movX + movZ < moveThresh) && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
			{
				this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon_back", this);
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
				List entities = AnimaniaHelper.getEntitiesInRange(HorseEntity.class, 3, this.world, this);
				if (!entities.isEmpty())
				{
					this.puller = (Entity) entities.get(0);
					this.pulled = true;
					this.setPullerType(1);
				}
			} else if (this.getPullerType() == 2)
			{
				List entities = AnimaniaHelper.getEntitiesInRange(PlayerEntity.class, 3, this.world, this);
				if (!entities.isEmpty())
				{
					this.puller = (Entity) entities.get(0);
					this.pulled = true;
					this.setPullerType(2);
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
			if (this.puller instanceof HorseEntity)
			{
				this.setPullerType(1);
			}
		}

		if (this.puller != null && (Math.abs(this.puller.getX() - this.getX()) > 7 || Math.abs(this.puller.getZ() - this.getZ()) > 7))
		{
			this.pulled = false;
			this.puller = null;
			this.setPullerType(0);
			world.playSound(null, this.getX(), this.getY(), this.getZ(), FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
			stopwagon();
		}

		if (this.pulled)
		{
			double deltaAngle = -Math.atan2(this.puller.getX() - this.getX(), this.puller.getZ() - this.getZ());

			Vec3d vec = new Vec3d(this.puller.getX(), this.puller.getY(), this.puller.getZ()).subtract(new Vec3d(this.getX(), this.getY(), this.getZ())).add(new Vec3d(0.0D, 0.0D, -3.2D).rotateYaw((float) -deltaAngle));
			this.motionX = vec.x / 1;
			this.motionY = vec.y;
			this.motionZ = vec.z / 1;
			move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		}

		if (!this.pulled)
		{
			this.motionY = this.motionY - .05D;
			move(MoverType.SELF, 0.0D, this.motionY, 0.0D);
		}

		super.onUpdate();
		this.tickLerp();

	}

	public void stopwagon()
	{
		if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
		{
			this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon", this);
		}

		if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
		{
			this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon_back", this);
		}
	}

	private void tickLerp()
	{
		if (this.lerpSteps > 0 && !this.canPassengerSteer())
		{
			double d0 = this.getX() + (this.wagonPitch - this.getX()) / this.lerpSteps;
			double d1 = this.getY() + (this.lerpY - this.getY()) / this.lerpSteps;
			double d2 = this.getZ() + (this.lerpZ - this.getZ()) / this.lerpSteps;
			double d3 = MathHelper.wrapDegrees(this.wagonYaw - this.rotationYaw);
			if (this.puller != null)
			{
				double deltaAngle = -Math.atan2(this.puller.getX() - this.getX(), this.puller.getZ() - this.getZ());
				this.rotationYaw = (float) Math.toDegrees(deltaAngle);
			}
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
		this.wagonPitch = x;
		this.lerpY = y;
		this.lerpZ = z;
		this.wagonYaw = yaw;
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
			if (passenger instanceof PlayerEntity)
			{
				f1 = (float) ((this.isDead ? 0.009999999776482582D : 1.55D) + passenger.getYOffset());
			}

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
					f = (float) (f + 0.2D);
				}

			}

			if (passenger instanceof PlayerEntity)
			{
				f = (float) (f + 1.8D);
			}

			Vec3d vec3d = (new Vec3d(f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
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
		if (entityToUpdate instanceof PlayerEntity)
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
				boolean flag = source.getTrueSource() instanceof PlayerEntity && ((PlayerEntity) source.getTrueSource()).capabilities.isCreativeMode;

				if (flag || this.getDamageTaken() > 40.0F)
				{
					if (!flag && this.world.getGameRules().getBoolean("doEntityDrops"))
					{
						this.dropItemWithOffset(FarmAddonItemHandler.wagon, 1, 0.0F);

						if (this.getHasChest())
						{
							InventoryHelper.dropInventoryItems(world, this.getPosition(), wagonChest);
							// this.dropItemWithOffset(Item.getItemFromBlock(Blocks.CHEST),
							// 1, 0.0F);
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

	@Override
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

	/**
	 * Returns the collision bounding box for this entity
	 */
	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox()
	{
		return this.getEntityBoundingBox();
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		if (this instanceof EntityWagon)
		{
			return new ItemStack(FarmAddonItemHandler.wagon);
		}

		return ItemStack.EMPTY;
	}

	@Override
	protected void entityInit()
	{
		this.dataManager.register(EntityWagon.PULLER_TYPE, Integer.valueOf(0));
		this.dataManager.register(EntityWagon.TIME_SINCE_HIT, Integer.valueOf(0));
		this.dataManager.register(EntityWagon.DAMAGE_TAKEN, Float.valueOf(0.0F));
		this.dataManager.register(EntityWagon.HAS_CHEST, true);
	}

	public int getPullerType()
	{
		return this.dataManager.get(EntityWagon.PULLER_TYPE).intValue();
	}

	public void setPullerType(int pullerType)
	{

		this.dataManager.set(EntityWagon.PULLER_TYPE, Integer.valueOf(pullerType));
	}

	@Override
	public void writeEntityToNBT(CompoundNBT compound)
	{
		compound.putInteger("PullerType", this.getPullerType());

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.wagonChest.getSizeInventory(); ++i)
		{
			ItemStack itemstack = this.wagonChest.getStackInSlot(i);

			if (!itemstack.isEmpty())
			{
				CompoundNBT CompoundNBT = new CompoundNBT();
				CompoundNBT.setByte("Slot", (byte) i);
				itemstack.writeToNBT(CompoundNBT);
				nbttaglist.appendTag(CompoundNBT);
			}
		}
		compound.putBoolean("HasChest", this.getHasChest());

		compound.putTag("Items", nbttaglist);
	}

	@Override
	public void readEntityFromNBT(CompoundNBT compound)
	{
		this.setPullerType(compound.getInteger("PullerType"));

		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.initwagonChest();

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			CompoundNBT CompoundNBT = nbttaglist.getCompoundTagAt(i);
			int j = CompoundNBT.getByte("Slot") & 255;

			if (j >= 0 && j < this.wagonChest.getSizeInventory())
			{
				this.wagonChest.setInventorySlotContents(j, new ItemStack(CompoundNBT));
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
		}

		return pullerType;
	}

	public void initwagonChest()
	{
		CartChest horsewagonchest = this.wagonChest;
		this.wagonChest = new CartChest("wagonChest", 54);
		this.wagonChest.setCustomName(this.getName());

		if (horsewagonchest != null)
		{
			horsewagonchest.removeInventoryChangeListener(this);
			int i = Math.min(horsewagonchest.getSizeInventory(), this.wagonChest.getSizeInventory());

			for (int j = 0; j < i; ++j)
			{
				ItemStack itemstack = horsewagonchest.getStackInSlot(j);

				if (itemstack != null)
				{
					this.wagonChest.setInventorySlotContents(j, itemstack.copy());
				}
			}

			horsewagonchest = null;

		}

		this.wagonChest.addInventoryChangeListener(this);

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

	public PlayerEntity.SleepResult trySleep(BlockPos bedLocation, PlayerEntity PlayerEntity)
	{

		World world = Playerentity.level;

		EnumFacing enumfacing = EnumFacing.NORTH;

		if (!world.isRemote)
		{
			if (PlayerEntity.isPlayerSleeping() || !PlayerEntity.isEntityAlive())
			{
				return PlayerEntity.SleepResult.OTHER_PROBLEM;
			}

			/*
			 * if (!world.provider.isSurfaceWorld()) { return
			 * PlayerEntity.SleepResult.NOT_POSSIBLE_HERE; }
			 */

			if (world.isDaytime())
			{
				return PlayerEntity.SleepResult.NOT_POSSIBLE_NOW;
			}

			double d0 = 8.0D;
			double d1 = 5.0D;
			List<EntityMob> list = world.<EntityMob> getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(bedLocation.getX() - 8.0D, bedLocation.getY() - 5.0D, bedLocation.getZ() - 8.0D, bedLocation.getX() + 8.0D, bedLocation.getY() + 5.0D, bedLocation.getZ() + 8.0D));

			if (!list.isEmpty())
			{
				return PlayerEntity.SleepResult.NOT_SAFE;
			}
		}

		IBlockState state = null;
		if (world.isBlockLoaded(bedLocation))
			state = world.getBlockState(bedLocation);
		if (state != null)
		{
			float f1 = 0.5F + enumfacing.getFrontOffsetX() * 0.4F;
			float f = 0.5F + enumfacing.getFrontOffsetZ() * 0.4F;
			PlayerEntity.setPosition(bedLocation.getX() + f1, bedLocation.getY() + 0.6875F, bedLocation.getZ() + f);
		} else
		{
			PlayerEntity.setPosition(bedLocation.getX() + 0.5F, bedLocation.getY() + 0.6875F, bedLocation.getZ() + 0.5F);
		}

		PlayerEntity.bedLocation = bedLocation;
		PlayerEntity.motionX = 0.0D;
		PlayerEntity.motionY = 0.0D;
		PlayerEntity.motionZ = 0.0D;

		if (!world.isRemote)
		{
			world.updateAllPlayersSleepingFlag();
		}

		return PlayerEntity.SleepResult.OK;
	}

	@Override
	public void onInventoryChanged(IInventory invBasic)
	{
		// do nothing
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public int getBrightnessForRender()
	{
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(this.getX()), 0, MathHelper.floor(this.getZ()));

		long time = this.getEntityWorld().getWorldTime() % 24000;

		if (this.world.isBlockLoaded(blockpos$mutableblockpos))
		{
			blockpos$mutableblockpos.setY(MathHelper.floor(this.getY() + this.getEyeHeight()));

			if (Animania.RANDOM.nextInt(32) == 0 && time > 13000 && sleepTimer == 0)
			{
				lastLighting = 85 + Animania.RANDOM.nextInt(22);
				return this.world.getCombinedLight(blockpos$mutableblockpos, 0) + lastLighting;

			} else if (sleepTimer == 0 || time < 13000)
			{
				return this.world.getCombinedLight(blockpos$mutableblockpos, 0) + lastLighting;
			} else
			{
				return 30;
			}
		} else
		{
			return 0;
		}
	}

}