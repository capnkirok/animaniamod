package com.animania.common.entities.props;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.ModSoundEvents;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.inventory.CartChest;
import com.animania.config.AnimaniaConfig;
import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.AnimationHandler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	public double getMountedYOffset()
	{
		return 0.9D;
	}

	@Override
	public boolean canRiderInteract()
	{
		return true;
	}

	public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		List horses = AnimaniaHelper.getEntitiesInRange(EntityHorse.class, 3, world, player);
		List wagons = AnimaniaHelper.getWagonsInRange(EntityWagon.class, 3, world, this);

		EntityHorse horse = null;
		if (!horses.isEmpty())
		{
			horse = (EntityHorse) horses.get(0);
		}

		double diffx = this.posX - player.posX;
		double diffy = this.posY - player.posY;
		double diffz = this.posZ - player.posZ;

		double mdiffx = Math.abs(this.posX - player.posX);
		double mdiffy = Math.abs(this.posY - player.posY);
		double mdiffz = Math.abs(this.posZ - player.posZ);

		if (player.isSneaking())
		{

			// Add in check for front of cart vs. Back

			if (mdiffx > 0 && mdiffx < 2.4 && mdiffy < 1.25 && mdiffz > 0 && mdiffz < 2.4)
			{
				this.wagonChest.setCustomName(this.getName());
				if (!this.world.isRemote)
				{
					player.openGui(Animania.instance, Animania.coveredWagonGUI_ID, player.world, this.getEntityId(), 0, 0);
					world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.PLAYERS, 0.7F, 1.0F);
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

		} else if (!player.isSneaking() && AnimaniaConfig.gameRules.sleepAllowedWagon)
		{

			if (mdiffx > 0 && mdiffx < 1 && mdiffy > 1.15 && mdiffy < 1.25 && mdiffz > 0 && mdiffz < 1.6)
			{

				if (world.provider.canRespawnHere())

				{
					sleepFlag = false;
					allSleeping = true;
					EntityPlayer player1 = null;
					Iterator iterator = world.playerEntities.iterator();
					while (iterator.hasNext())
					{
						EntityPlayer player2 = (EntityPlayer) iterator.next();

						if (player2 != player)
						{
							if (!player2.isPlayerSleeping())
							{
								allSleeping = false;
							}
						}
					}

					EntityPlayer.SleepResult player$sleepresult = trySleep(new BlockPos(player.posX, player.posY, player.posZ), player);

					if (player$sleepresult == EntityPlayer.SleepResult.OK && allSleeping == true)
					{

						sleepFlag = true;
						sleepDelay = 0;

					} else
					{
						if (player$sleepresult == EntityPlayer.SleepResult.NOT_POSSIBLE_NOW)
						{
							sleepFlag = false;
							player.sendStatusMessage(new TextComponentTranslation("tile.bed.noSleep", new Object[0]), true);
						} else if (player$sleepresult == EntityPlayer.SleepResult.NOT_SAFE)
						{
							sleepFlag = false;
							player.sendStatusMessage(new TextComponentTranslation("tile.bed.notSafe", new Object[0]), true);

						} else if (player$sleepresult == EntityPlayer.SleepResult.NOT_POSSIBLE_HERE)
						{
							sleepFlag = false;
							player.sendStatusMessage(new TextComponentTranslation("tile.bed.notHere", new Object[0]), true);

						}

					}

					if (player$sleepresult == EntityPlayer.SleepResult.OK)
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
				if (this.puller instanceof EntityHorse)
				{
					this.setPullerType(1);
				}
				if (!world.isRemote)
				{
					world.playSound(null, player.posX, player.posY, player.posZ, ModSoundEvents.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
				}

				return true;
			} else if (player.isRiding() && this.puller == player.getRidingEntity() && player.getRidingEntity() != this)
			{
				this.pulled = false;
				this.puller = null;
				this.setPullerType(0);
				if (!world.isRemote)
				{
					world.playSound(null, player.posX, player.posY, player.posZ, ModSoundEvents.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
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
					world.playSound(null, player.posX, player.posY, player.posZ, ModSoundEvents.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
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
						world.playSound(null, player.posX, player.posY, player.posZ, ModSoundEvents.hitch, SoundCategory.PLAYERS, 0.7F, 1.5F);

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
					world.playSound(null, player.posX, player.posY, player.posZ, ModSoundEvents.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
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
					world.playSound(null, player.posX, player.posY, player.posZ, ModSoundEvents.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
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
		return ((Float) this.dataManager.get(EntityWagon.DAMAGE_TAKEN)).floatValue();
	}

	public void setHasChest(boolean hasChest)
	{
		this.dataManager.set(EntityWagon.HAS_CHEST, Boolean.valueOf(hasChest));
	}

	public boolean getHasChest()
	{
		return ((boolean) this.dataManager.get(EntityWagon.HAS_CHEST).booleanValue());
	}

	public void setTimeSinceHit(int timeSinceHit)
	{
		this.dataManager.set(EntityWagon.TIME_SINCE_HIT, Integer.valueOf(timeSinceHit));
	}

	public int getTimeSinceHit()
	{
		return ((Integer) this.dataManager.get(EntityWagon.TIME_SINCE_HIT)).intValue();
	}

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

		// Mounting a horse that is riding a wagon...
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

		if (sleepTimer > 0)
		{
			sleepTimer--;
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

			double movX = Math.abs(this.puller.posX - this.puller.prevPosX);
			double movZ = Math.abs(this.puller.posZ - this.puller.prevPosZ);

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
		if (this.pulled && this.puller instanceof EntityPlayer)
		{
			List wagons = AnimaniaHelper.getWagonsInRange(EntityWagon.class, 3, world, this);
			EntityPlayer player = (EntityPlayer) this.puller;
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

		if (this.pulled && this.puller instanceof EntityAnimal)
		{
			List wagons = AnimaniaHelper.getWagonsInRange(EntityWagon.class, 3, world, this);
			EntityAnimal animal = (EntityAnimal) this.puller;
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
			double diffX = (this.posX - this.prevPosX);
			double diffZ = (this.posZ - this.prevPosZ);

			double movX = Math.abs(this.posX - this.prevPosX);
			double movZ = Math.abs(this.posZ - this.prevPosZ);

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
			}
		}

		if (this.puller != null && (Math.abs(this.puller.posX - this.posX) > 7 || Math.abs(this.puller.posZ - this.posZ) > 7))
		{
			this.pulled = false;
			this.puller = null;
			this.setPullerType(0);
			world.playSound(null, this.posX, this.posY, this.posZ, ModSoundEvents.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
			stopwagon();
		}

		if (this.pulled)
		{
			double deltaAngle = -Math.atan2(this.puller.posX - this.posX, this.puller.posZ - this.posZ);

			Vec3d vec = new Vec3d(this.puller.posX, this.puller.posY, this.puller.posZ).subtract(new Vec3d(this.posX, this.posY, this.posZ)).add(new Vec3d(0.0D, 0.0D, -3.2D).rotateYaw((float) -deltaAngle));
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
			double d0 = this.posX + (this.wagonPitch - this.posX) / (double) this.lerpSteps;
			double d1 = this.posY + (this.lerpY - this.posY) / (double) this.lerpSteps;
			double d2 = this.posZ + (this.lerpZ - this.posZ) / (double) this.lerpSteps;
			double d3 = MathHelper.wrapDegrees(this.wagonYaw - (double) this.rotationYaw);
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
		this.wagonPitch = x;
		this.lerpY = y;
		this.lerpZ = z;
		this.wagonYaw = (double) yaw;
		this.lerpXRot = (double) pitch;
		this.lerpSteps = 10;
	}

	public void updatePassenger(Entity passenger)
	{
		if (this.isPassenger(passenger))
		{
			float f = 0.0F;
			float f1 = (float) ((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());
			if (passenger instanceof EntityPlayer)
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

				if (passenger instanceof EntityAnimal)
				{
					f = (float) ((double) f + 0.2D);
				}

			}

			if (passenger instanceof EntityPlayer)
			{
				f = (float) ((double) f + 1.8D);
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
						this.dropItemWithOffset(ItemHandler.wagon, 1, 0.0F);

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

	/**
	 * Returns the collision bounding box for this entity
	 */
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
			return new ItemStack(ItemHandler.wagon);
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

	public void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setInteger("PullerType", this.getPullerType());

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.wagonChest.getSizeInventory(); ++i)
		{
			ItemStack itemstack = this.wagonChest.getStackInSlot(i);

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
		this.initwagonChest();

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;

			if (j >= 0 && j < this.wagonChest.getSizeInventory())
			{
				this.wagonChest.setInventorySlotContents(j, new ItemStack(nbttagcompound));
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

	public EntityPlayer.SleepResult trySleep(BlockPos bedLocation, EntityPlayer entityplayer)
	{

		World world = entityplayer.world;

		EnumFacing enumfacing = EnumFacing.NORTH;

		if (!world.isRemote)
		{
			if (entityplayer.isPlayerSleeping() || !entityplayer.isEntityAlive())
			{
				return EntityPlayer.SleepResult.OTHER_PROBLEM;
			}

			/*
			 * if (!world.provider.isSurfaceWorld()) { return
			 * EntityPlayer.SleepResult.NOT_POSSIBLE_HERE; }
			 */

			if (world.isDaytime())
			{
				return EntityPlayer.SleepResult.NOT_POSSIBLE_NOW;
			}

			double d0 = 8.0D;
			double d1 = 5.0D;
			List<EntityMob> list = world.<EntityMob> getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB((double) bedLocation.getX() - 8.0D, (double) bedLocation.getY() - 5.0D, (double) bedLocation.getZ() - 8.0D, (double) bedLocation.getX() + 8.0D, (double) bedLocation.getY() + 5.0D, (double) bedLocation.getZ() + 8.0D));

			if (!list.isEmpty())
			{
				return EntityPlayer.SleepResult.NOT_SAFE;
			}
		}

		IBlockState state = null;
		if (world.isBlockLoaded(bedLocation))
			state = world.getBlockState(bedLocation);
		if (state != null)
		{
			float f1 = 0.5F + (float) enumfacing.getFrontOffsetX() * 0.4F;
			float f = 0.5F + (float) enumfacing.getFrontOffsetZ() * 0.4F;
			entityplayer.setPosition((double) ((float) bedLocation.getX() + f1), (double) ((float) bedLocation.getY() + 0.6875F), (double) ((float) bedLocation.getZ() + f));
		} else
		{
			entityplayer.setPosition((double) ((float) bedLocation.getX() + 0.5F), (double) ((float) bedLocation.getY() + 0.6875F), (double) ((float) bedLocation.getZ() + 0.5F));
		}

		entityplayer.bedLocation = bedLocation;
		entityplayer.motionX = 0.0D;
		entityplayer.motionY = 0.0D;
		entityplayer.motionZ = 0.0D;

		if (!world.isRemote)
		{
			world.updateAllPlayersSleepingFlag();
		}

		return EntityPlayer.SleepResult.OK;
	}

	@Override
	public void onInventoryChanged(IInventory invBasic)
	{
		// do nothing
	}

	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender()
	{
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));

		long time = this.getEntityWorld().getWorldTime() % 24000;

		if (this.world.isBlockLoaded(blockpos$mutableblockpos))
		{
			blockpos$mutableblockpos.setY(MathHelper.floor(this.posY + (double) this.getEyeHeight()));

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