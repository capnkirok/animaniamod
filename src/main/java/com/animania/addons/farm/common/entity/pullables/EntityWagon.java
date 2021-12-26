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
import com.animania.client.handler.AnimationHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.leviathanstudio.craftstudio.CraftStudioApi;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityWagon extends AnimatedEntityBase implements ContainerListener
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
	private int lastLighting = 60;
	private int sleepTimer = 0;
	public int blinkTimer;

	protected static final EntityDataAccessor<Integer> PULLER_TYPE = SynchedEntityData.defineId(EntityWagon.class, EntityDataSerializers.VARINT);
	private static final EntityDataAccessor<Integer> TIME_SINCE_HIT = SynchedEntityData.defineId(EntityWagon.class, EntityDataSerializers.VARINT);
	private static final EntityDataAccessor<Float> DAMAGE_TAKEN = SynchedEntityData.defineId(EntityWagon.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> HAS_CHEST = SynchedEntityData.defineId(EntityWagon.class, EntityDataSerializers.BOOLEAN);

	// Gui Id Offset required when multiple addons are installed
	private static final int GUI_ID = 1 + FarmAddon.guiHandler.getGuiIdOffset();

	static
	{
		EntityWagon.animHandler.addAnim(Animania.MODID, "anim_wagon", "model_wagon", true);
		EntityWagon.animHandler.addAnim(Animania.MODID, "anim_wagon_back", "anim_wagon");
	}

	public EntityWagon(Level par1Level)
	{
		super(par1Level);
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

	public EntityWagon(Level levelIn, double x, double y, double z)
	{
		this(levelIn);
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
	public boolean processInitialInteract(Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		List horses = AnimaniaHelper.getEntitiesInRange(HorseEntity.class, 3, level, player);
		List wagons = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, level, this);

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
				if (!this.level.isClientSide)
				{
					player.openGui(Animania.instance, GUI_ID, player.level, this.getEntityId(), 0, 0);
					level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.PLAYERS, 0.7F, 1.0F);
				}

			}
			else if (!player.isPassenger())
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
			}

		}
		else if (!player.isSneaking() && FarmConfig.settings.sleepAllowedWagon)
		{

			if (mdiffx > 0 && mdiffx < 1 && mdiffy > 1.15 && mdiffy < 1.25 && mdiffz > 0 && mdiffz < 1.6)
			{

				if (level.provider.canRespawnHere())

				{
					boolean sleepFlag = false;
					boolean allSleeping = true;
					Player player1 = null;
					Iterator iterator = level.playerEntities.iterator();
					while (iterator.hasNext())
					{
						Player player2 = (Player) iterator.next();

						if (player2 != player && !player2.isPlayerSleeping())
						{
							allSleeping = false;
						}
					}

					Player.SleepResult player$sleepresult = this.trySleep(new BlockPos(player.getX(), player.getY(), player.getZ()), player);

					if (player$sleepresult == Player.SleepResult.OK && allSleeping)
					{

						sleepFlag = true;
						int sleepDelay = 0;

					}
					else if (player$sleepresult == Player.SleepResult.NOT_POSSIBLE_NOW)
					{
						sleepFlag = false;
						player.sendStatusMessage(new TextComponentTranslation("tile.bed.noSleep", new Object[0]), true);
					}
					else if (player$sleepresult == Player.SleepResult.NOT_SAFE)
					{
						sleepFlag = false;
						player.sendStatusMessage(new TextComponentTranslation("tile.bed.notSafe", new Object[0]), true);

					}
					else if (player$sleepresult == Player.SleepResult.NOT_POSSIBLE_HERE)
					{
						sleepFlag = false;
						player.sendStatusMessage(new TextComponentTranslation("tile.bed.notHere", new Object[0]), true);

					}

					if (player$sleepresult == Player.SleepResult.OK)
					{

						sleepFlag = true;
						if (!level.isClientSide)
						{
							player.addMobEffectInstance(new MobEffectInstance(MobEffects.BLINDNESS, 30, 1, false, false));
							level.updateAllPlayersSleepingFlag();
							Animania.proxy.Sleep(player);
							player.setSpawnPoint(getPosition(), true);

						}

						this.sleepTimer = 120;

					}

				}

			}
			else if (player.isPassenger() && this.puller != player && this.puller != player.getRidingEntity() && player.getRidingEntity() != this)
			{

				this.pulled = true;
				this.puller = player.getRidingEntity();
				if (this.puller instanceof HorseEntity)
				{
					this.setPullerType(1);
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
				this.stopwagon();
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
			else if (stack.isEmpty() && !player.isPassenger() && this.puller != player && this.getControllingPassenger() != player && !level.isClientSide)
			{

				if (mdiffx > 0 && mdiffy < 2 && mdiffz > 0)
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
				this.stopwagon();
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
				this.stopwagon();
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
		this.entityData.set(EntityWagon.DAMAGE_TAKEN, Float.valueOf(damageTaken));
	}

	public float getDamageTaken()
	{
		return this.entityData.get(EntityWagon.DAMAGE_TAKEN).floatValue();
	}

	public void setHasChest(boolean hasChest)
	{
		this.entityData.set(EntityWagon.HAS_CHEST, Boolean.valueOf(hasChest));
	}

	public boolean getHasChest()
	{
		return this.entityData.get(EntityWagon.HAS_CHEST).booleanValue();
	}

	public void setTimeSinceHit(int timeSinceHit)
	{
		this.entityData.set(EntityWagon.TIME_SINCE_HIT, Integer.valueOf(timeSinceHit));
	}

	public int getTimeSinceHit()
	{
		return this.entityData.get(EntityWagon.TIME_SINCE_HIT).intValue();
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
		}
		else if (entityIn.getEntityBoundingBox().minY <= this.getEntityBoundingBox().minY && !this.pulled)
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

		// Mounting a horse that is riding a wagon...
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

		if (this.sleepTimer > 0)
		{
			this.sleepTimer--;
		}

		// Dismount text
		if (this.isBeingRidden() && this.getControllingPassenger() instanceof Player && this.rideCooldown > 10 && level.isClientSide)
		{
			Player player = (Player) this.getControllingPassenger();
			player.sendStatusMessage(new TextComponent(I18n.format("mount.onboard", Minecraft.getMinecraft().gameSettings.keyBindSneak.getDisplayName())), true);
		}

		// Determine animation direction based on previous pos
		if (this.pulled && this.puller != null && level.isClientSide)
		{

			double movX = Math.abs(this.puller.getX() - this.puller.prevgetX());
			double movZ = Math.abs(this.puller.getZ() - this.puller.prevgetZ());

			// double moveThresh = .005D;
			double moveThresh = movX + movZ - 1; // TODO Testing

			if (this.wagonYaw < 0 && this.puller.motionX > .001 && this.puller.motionZ > .001 && movX + movZ > moveThresh)
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon", this);
				}
			}
			else if (this.wagonYaw > 0 && this.puller.motionX < -.001 && this.puller.motionZ < -.001 && movX + movZ > moveThresh)
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon", this);
				}
			}
			else if (this.wagonYaw > 0 && this.puller.motionX < -.001 && this.puller.motionZ > .001 && movX + movZ > moveThresh)
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon", this);
				}
			}
			else if (this.wagonYaw < 0 && this.puller.motionX > .001 && this.puller.motionZ < -.001 && movX + movZ > moveThresh)
			{
				if (this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
				{
					this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon_back", this);
				}
				if (!this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
				{
					this.getAnimationHandler().startAnimation(Animania.MODID, "anim_wagon", this);
				}
			}
			else if (this.wagonYaw < 0 && this.puller.motionX < -.001 && this.puller.motionZ < -.001 && movX + movZ > moveThresh)
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
			else if (this.wagonYaw > 0 && this.puller.motionX > .001 && this.puller.motionZ > .001 && movX + movZ > moveThresh)
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
			else if (this.wagonYaw > 0 && this.puller.motionX > .001 && this.puller.motionZ < -.001 && movX + movZ > moveThresh)
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
			else if (this.wagonYaw < 0 && this.puller.motionX < -.001 && this.puller.motionZ > .001 && movX + movZ > moveThresh)
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
		if (this.pulled && this.puller instanceof Player)
		{
			List wagons = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, level, this);
			Player player = (Player) this.puller;
			int totPulling = 0;
			if (!wagons.isEmpty())
			{
				if (wagons.size() >= 0)
				{
					for (Object wagon : wagons)
					{
						EntityWagon tempwagon = (EntityWagon) wagon;
						if (tempwagon.pulled && tempwagon.puller == player)
						{
							totPulling++;
						}
					}
				}
				if (totPulling > 0)
				{
					player.addMobEffectInstance(new MobEffectInstance(MobEffects.SLOWNESS, 2, wagons.size() + 1, false, false));
				}
			}
		}

		if (this.pulled && this.puller instanceof AnimalEntity)
		{
			List wagons = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, level, this);
			AnimalEntity animal = (AnimalEntity) this.puller;
			int totPulling = 0;
			if (!wagons.isEmpty())
			{
				if (wagons.size() > 1)
				{
					for (Object wagon : wagons)
					{
						EntityWagon tempwagon = (EntityWagon) wagon;
						if (tempwagon.pulled && tempwagon.puller == animal && tempwagon != this)
						{
							totPulling++;
						}
					}
				}
				if (totPulling > 0)
				{
					animal.addMobEffectInstance(new MobEffectInstance(MobEffects.SLOWNESS, 2, wagons.size() + 1, false, false));
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

			if (diffX < .005 && diffZ < .005 && movX + movZ < moveThresh && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon", this))
			{
				this.getAnimationHandler().stopAnimation(Animania.MODID, "anim_wagon", this);
			}

			if (diffX < .005 && diffZ < .005 && movX + movZ < moveThresh && this.getAnimationHandler().isAnimationActive(Animania.MODID, "anim_wagon_back", this))
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
		}

		if (this.puller != null && (Math.abs(this.puller.getX() - this.getX()) > 7 || Math.abs(this.puller.getZ() - this.getZ()) > 7))
		{
			this.pulled = false;
			this.puller = null;
			this.setPullerType(0);
			level.playSound(null, this.getX(), this.getY(), this.getZ(), FarmAddonSoundHandler.unhitch, SoundCategory.PLAYERS, 0.7F, 1.5F);
			this.stopwagon();
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
			if (passenger instanceof Player)
			{
				f1 = (float) ((this.isDead ? 0.009999999776482582D : 1.55D) + passenger.getYOffset());
			}

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

			if (passenger instanceof Player)
			{
				f = (float) (f + 1.8D);
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
						this.dropItemWithOffset(FarmAddonItemHandler.wagon, 1, 0.0F);

						if (this.getHasChest())
						{
							InventoryHelper.dropInventoryItems(level, this.getPosition(), this.wagonChest);
							// this.dropItemWithOffset(Item.getItemFromBlock(Blocks.CHEST),
							// 1, 0.0F);
						}
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
	public AxisAlignedBB getCollisionBox(Entity entityIn)
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
		this.entityData.register(EntityWagon.PULLER_TYPE, Integer.valueOf(0));
		this.entityData.register(EntityWagon.TIME_SINCE_HIT, Integer.valueOf(0));
		this.entityData.register(EntityWagon.DAMAGE_TAKEN, Float.valueOf(0.0F));
		this.entityData.register(EntityWagon.HAS_CHEST, true);
	}

	public int getPullerType()
	{
		return this.entityData.get(EntityWagon.PULLER_TYPE).intValue();
	}

	public void setPullerType(int pullerType)
	{

		this.entityData.set(EntityWagon.PULLER_TYPE, Integer.valueOf(pullerType));
	}

	@Override
	public void writeEntityToNBT(CompoundTag compound)
	{
		compound.putInteger("PullerType", this.getPullerType());

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.wagonChest.getSizeInventory(); ++i)
		{
			ItemStack itemstack = this.wagonChest.getStackInSlot(i);

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

	@Override
	public void readEntityFromNBT(CompoundTag compound)
	{
		this.setPullerType(compound.getInteger("PullerType"));

		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.initwagonChest();

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			CompoundTag CompoundTag = nbttaglist.getCompoundTagAt(i);
			int j = CompoundTag.getByte("Slot") & 255;

			if (j >= 0 && j < this.wagonChest.getSizeInventory())
			{
				this.wagonChest.setInventorySlotContents(j, new ItemStack(CompoundTag));
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

	public Player.SleepResult trySleep(BlockPos bedLocation, Player Player)
	{

		Level level = Player.level;

		Direction enumfacing = Direction.NORTH;

		if (!level.isClientSide)
		{
			if (Player.isPlayerSleeping() || !Player.isAlive())
			{
				return Player.SleepResult.OTHER_PROBLEM;
			}

			/*
			 * if (!level.provider.isSurfaceLevel()) { return
			 * Player.SleepResult.NOT_POSSIBLE_HERE; }
			 */

			if (level.isDay())
			{
				return Player.SleepResult.NOT_POSSIBLE_NOW;
			}

			double d0 = 8.0D;
			double d1 = 5.0D;
			List<EntityMob> list = level.<EntityMob> getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(bedLocation.getX() - 8.0D, bedLocation.getY() - 5.0D, bedLocation.getZ() - 8.0D, bedLocation.getX() + 8.0D, bedLocation.getY() + 5.0D, bedLocation.getZ() + 8.0D));

			if (!list.isEmpty())
			{
				return Player.SleepResult.NOT_SAFE;
			}
		}

		BlockState state = null;
		if (level.isBlockLoaded(bedLocation))
			state = level.getBlockState(bedLocation);
		if (state != null)
		{
			float f1 = 0.5F + enumfacing.getFrontOffsetX() * 0.4F;
			float f = 0.5F + enumfacing.getFrontOffsetZ() * 0.4F;
			Player.setPosition(bedLocation.getX() + f1, bedLocation.getY() + 0.6875F, bedLocation.getZ() + f);
		}
		else
		{
			Player.setPosition(bedLocation.getX() + 0.5F, bedLocation.getY() + 0.6875F, bedLocation.getZ() + 0.5F);
		}

		Player.bedLocation = bedLocation;
		Player.motionX = 0.0D;
		Player.motionY = 0.0D;
		Player.motionZ = 0.0D;

		if (!level.isClientSide)
		{
			level.updateAllPlayersSleepingFlag();
		}

		return Player.SleepResult.OK;
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

		long time = this.getEntityLevel().getLevelTime() % 24000;

		if (this.level.isBlockLoaded(blockpos$mutableblockpos))
		{
			blockpos$mutableblockpos.setY(MathHelper.floor(this.getY() + this.getEyeHeight()));

			if (Animania.RANDOM.nextInt(32) == 0 && time > 13000 && this.sleepTimer == 0)
			{
				this.lastLighting = 85 + Animania.RANDOM.nextInt(22);
				return this.level.getCombinedLight(blockpos$mutableblockpos, 0) + this.lastLighting;

			}
			else if (this.sleepTimer == 0 || time < 13000)
			{
				return this.level.getCombinedLight(blockpos$mutableblockpos, 0) + this.lastLighting;
			}
			else
			{
				return 30;
			}
		}
		else
		{
			return 0;
		}
	}

}