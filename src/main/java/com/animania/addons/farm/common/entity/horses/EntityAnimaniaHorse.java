package com.animania.addons.farm.common.entity.horses;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.horses.ai.FollowMateHorsesGoal;
import com.animania.addons.farm.common.entity.horses.ai.HorseEntityEatGrass;
import com.animania.addons.farm.common.entity.horses.ai.LookIdleHorsesGoal;
import com.animania.addons.farm.common.entity.horses.ai.WanderHorsesGoal;
import com.animania.addons.farm.common.entity.pullables.EntityWagon;
import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.addons.farm.common.inventory.ContainerHorseCart;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.api.interfaces.IConvertable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindSaltLick;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaHorse extends Horse implements IAnimaniaAnimalBase, IConvertable
{
	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(FarmConfig.settings.horseFood));
	protected static final EntityDataAccessor<Boolean> WATERED = SynchedEntityData.<Boolean> createKey(EntityAnimaniaHorse.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> FED = SynchedEntityData.defineId(EntityAnimaniaHorse.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> COLOR_NUM = SynchedEntityData.defineId(EntityAnimaniaHorse.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityAnimaniaHorse.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Boolean> HANDFED = SynchedEntityData.defineId(EntityAnimaniaHorse.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(EntityAnimaniaHorse.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Float> SLEEPTIMER = SynchedEntityData.defineId(EntityAnimaniaHorse.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> INTERACTED = SynchedEntityData.defineId(EntityAnimaniaHorse.class, EntityDataSerializers.BOOLEAN);

	protected int happyTimer;
	public int blinkTimer;
	public int eatTimer;
	protected int fedTimer;
	protected int wateredTimer;
	protected int damageTimer;
	public HorseEntityEatGrass entityAIEatGrass;
	public HorseType horseType;
	protected boolean mateable = false;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	protected EntityGender gender;
	protected ContainerHorseCart cartChest;
	protected Item dropRaw = Items.AIR;
	protected Item dropCooked = Items.AIR;
	protected boolean boosting;
	protected int boostTime;
	protected int totalBoostTime;

	public EntityAnimaniaHorse(Level levelIn)
	{
		super(levelIn);
		this.stepHeight = 1.2F;
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new HorseEntityEatGrass(this);
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.goalSelector.addGoal(1, new GenericAIFindWater<>(this, 1.0D, this.entityAIEatGrass, EntityAnimaniaHorse.class));
			this.goalSelector.addGoal(1, new GenericAIFindFood<>(this, 1.0D, this.entityAIEatGrass, true));
		}
		this.goalSelector.addGoal(0, new GenericAIPanic<>(this, 2.0D));
		this.goalSelector.addGoal(2, new FollowMateHorsesGoal(this, 1.1D));
		this.goalSelector.addGoal(3, new WanderHorsesGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new SwimmingGoal(this));
		this.goalSelector.addGoal(5, new GenericAITempt<>(this, 1.25D, false, TEMPTATION_ITEMS));
		this.goalSelector.addGoal(6, this.entityAIEatGrass);
		this.goalSelector.addGoal(7, new GenericAIWatchClosest(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new LookIdleHorsesGoal(this));
		this.goalSelector.addGoal(9, new GenericAIFindSaltLick<>(this, 1.0, this.entityAIEatGrass));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.goalSelector.addGoal(10, new GenericAISleep<EntityAnimaniaHorse>(this, 0.8, AnimaniaHelper.getBlock(FarmConfig.settings.horseBed), AnimaniaHelper.getBlock(FarmConfig.settings.horseBed2), EntityAnimaniaHorse.class));
		}
		this.goalSelector.addGoal(11, new HurtByTargetGoal(this, false, new Class[0]));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + rand.nextInt(80);
		this.enablePersistence();
		this.initHorseChest();

	}

	@Override
	protected void initEntityAI()
	{
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
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaHorse.COLOR_NUM, Integer.valueOf(rand.nextInt(6)));
		this.dataManager.register(EntityAnimaniaHorse.FED, true);
		this.dataManager.register(EntityAnimaniaHorse.HANDFED, false);
		this.dataManager.register(EntityAnimaniaHorse.WATERED, true);
		this.dataManager.register(EntityAnimaniaHorse.SLEEPING, false);
		this.dataManager.register(EntityAnimaniaHorse.SLEEPTIMER, Float.valueOf(0.0F));
		this.dataManager.register(EntityAnimaniaHorse.AGE, Integer.valueOf(0));
		this.dataManager.register(INTERACTED, false);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28499999403953552D);
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateCommon(this);

		if (this.boosting)
		{
			this.addMobEffectInstance(new MobEffectInstance(MobEffects.SPEED, 1, 3, false, false));
		}

		this.boostTime++;

		if (this.boostTime >= this.totalBoostTime)
		{
			this.boosting = false;
			this.boostTime = 0;
		}

		super.onLivingUpdate();
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
				f1 = (float) ((this.isDead ? 0.009999999776482582D : 1.6D) + passenger.getYOffset());
			}

			if (passenger instanceof Player player)
			{

				List wagons = AnimaniaHelper.getEntitiesInRangeGeneric(EntityWagon.class, 3, this.level, this);

				if (!wagons.isEmpty())
				{
					if (wagons.size() >= 0)
					{
						for (int i = 0; i < wagons.size(); i++)
						{
							EntityWagon tempWagon = (EntityWagon) wagons.get(i);
							if (tempWagon.pulled && tempWagon.puller == this)
							{

								f = (float) (f + 1.82D);

								Vec3d vec3d = new Vec3d(f, 0.0D, 0.0D).rotateYaw(-tempWagon.rotationYaw * 0.017453292F - (float) Math.PI / 2F);
								passenger.setPosition(tempWagon.getX() + vec3d.x, tempWagon.getY() + f1, tempWagon.getZ() + vec3d.z);

							}
							else
							{
								passenger.setPosition(this.getX(), this.getY() + this.getMountedYOffset() + passenger.getYOffset(), this.getZ());
							}
						}
					}
				}
				else
				{
					f = (float) (f - 0.42D);

					Vec3d vec3d = new Vec3d(f, 0.0D, 0.0D).rotateYaw(-this.rotationYaw * 0.017453292F - (float) Math.PI / 2F);
					// passenger.setPosition(this.getX() + vec3d.x, this.getY()
					// +
					// (double) f1, this.getZ() + vec3d.z);

					passenger.setPosition(this.getX(), this.getY() + this.getMountedYOffset() + passenger.getYOffset(), this.getZ());
				}
			}
			else
			{
				passenger.setPosition(this.getX(), this.getY() + this.getMountedYOffset() + passenger.getYOffset(), this.getZ());
			}

		}
	}

	public boolean boost()
	{
		if (this.boosting)
		{
			return false;
		}
		else
		{
			this.boosting = true;
			this.boostTime = 0;
			this.totalBoostTime = this.getRandom().nextInt(100) + 20;
			return true;
		}
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityFoalBase ? null : new ResourceLocation("farm/" + Animania.MODID, "horse");
	}

	@Override
	public boolean canJump()
	{
		return true;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, FarmAddonSoundHandler.horseliving1, FarmAddonSoundHandler.horseliving2, FarmAddonSoundHandler.horseliving3, FarmAddonSoundHandler.horseliving4, FarmAddonSoundHandler.horseliving5, FarmAddonSoundHandler.horseliving6);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.horsehurt1, FarmAddonSoundHandler.horsehurt2, FarmAddonSoundHandler.horsehurt3);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.horsehurt1, FarmAddonSoundHandler.horsehurt2, FarmAddonSoundHandler.horsehurt3);
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public void setJumpPower(int jumpPowerIn)
	{

		if (jumpPowerIn < 0)
		{
			jumpPowerIn = 0;
		}
		else
		{
			// do nothing
		}

		if (jumpPowerIn >= 90)
		{
			this.jumpPower = 1.0F;
		}
		else
		{
			this.jumpPower = 0.4F + 0.4F * jumpPowerIn / 90.0F;
		}

	}

	@Override
	public EntityDataAccessor<Boolean> getFedParam()
	{
		return FED;
	}

	@Override
	public EntityDataAccessor<Boolean> getHandFedParam()
	{
		return HANDFED;
	}

	@Override
	public EntityDataAccessor<Boolean> getWateredParam()
	{
		return WATERED;
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
	public EntityDataAccessor<Integer> getAgeParam()
	{
		return AGE;
	}

	@Override
	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
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
		this.dataManager.set(COLOR_NUM, Integer.valueOf(color));
	}

	public ResourceLocation getResourceLocation()
	{
		return this.resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink()
	{
		return this.resourceLocationBlink;
	}

	@Override
	public void travel(float strafe, float vertical, float forward)
	{
		if (this.isBeingRidden() && this.canBeSteered()) // &&
		// this.isHorseSaddled())
		{
			LivingEntity LivingEntity = (LivingEntity) this.getControllingPassenger();
			this.rotationYaw = LivingEntity.rotationYaw;
			this.prevRotationYaw = this.rotationYaw;
			this.rotationPitch = LivingEntity.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.renderYawOffset = this.rotationYaw;
			this.rotationYawHead = this.renderYawOffset;
			strafe = LivingEntity.moveStrafing * 0.5F;
			forward = LivingEntity.moveForward * 4F;

			if (forward <= 0.0F)
			{
				forward *= 0.25F;
				this.gallopTime = 0;
			}

			if (this.onGround && this.jumpPower == 0.0F && this.isRearing())
			{
				strafe = 0.0F;
				forward = 0.0F;
			}

			if (this.jumpPower > 0.0F && !this.isHorseJumping() && this.onGround)
			{
				this.motionY = this.getHorseJumpStrength() * this.jumpPower;

				if (this.isPotionActive(MobEffects.JUMP_BOOST))
				{
					this.motionY += (this.getActiveMobEffectInstance(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F;
				}

				this.setHorseJumping(true);
				this.isAirBorne = true;

				if (forward > 0.0F)
				{
					float f = MathHelper.sin(this.rotationYaw * 0.017453292F);
					float f1 = MathHelper.cos(this.rotationYaw * 0.017453292F);
					this.motionX += -0.4F * f * this.jumpPower;
					this.motionZ += 0.4F * f1 * this.jumpPower;
					this.playSound(SoundEvents.ENTITY_HORSE_JUMP, 0.4F, 1.0F);
				}

				this.jumpPower = 0.0F;
			}

			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (this.canPassengerSteer() || this.getLeashed())
			{
				this.setAIMoveSpeed((float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
				super.travel(strafe, vertical / 8, forward);
			}
			else if (LivingEntity instanceof Player)
			{
				this.motionX = 0.0D;
				this.motionY = 0.0D;
				this.motionZ = 0.0D;
			}

			if (this.onGround)
			{
				this.jumpPower = 0.0F;
				this.setHorseJumping(false);
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.getX() - this.prevgetX();
			double d0 = this.getZ() - this.prevgetZ();
			float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

			if (f2 > 1.0F)
			{
				f2 = 1.0F;
			}

			// this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
			// this.limbSwing += this.limbSwingAmount;
		}
		else
		{
			this.jumpMovementFactor = 0.02F;
			if (!this.isBeingRidden())
			{
				forward = this.moveForward;
			}
			super.travel(strafe, vertical, forward);

		}
	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.SADDLE && !this.isHorseSaddled() && !this.getSleeping())
		{
			ItemStack bob = stack.copy();
			this.horseChest.setInventorySlotContents(0, bob);
			this.setHorseSaddled(true);
			if (!player.isCreative())
				stack.shrink(1);
			this.updateHorseSlots();
			return true;
		}
		else if (stack == ItemStack.EMPTY && this.isHorseSaddled() && !this.isBeingRidden() && this.getWatered() && this.getFed() && !this.isChild() && !this.getSleeping())
		{
			this.navigator.stop();
			this.mountTo(player);
			return true;
		}

		return GenericBehavior.interactCommon(this, player, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
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

	public void removeItem(Player ep, ItemStack removeitem)
	{
		IInventory inv = ep.inventory;
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			if (inv.getStackInSlot(i) != null)
			{
				ItemStack j = inv.getStackInSlot(i);
				if (j.getItem() != null && j.getItem() == removeitem.getItem())
				{
					inv.setInventorySlotContents(i, null);
					break;
				}
			}
		}
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != null && this.isHorseBreedingItem(stack);
	}

	public boolean isHorseBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, stack);
	}

	@Override
	public void writeEntityToNBT(CompoundTag compound)
	{
		super.writeEntityToNBT(compound);

		compound.putInteger("ColorNumber", this.getColorNumber());

		GenericBehavior.writeCommonNBT(compound, this);

	}

	@Override
	public void readEntityFromNBT(CompoundTag compound)
	{
		super.readEntityFromNBT(compound);

		this.setColorNumber(compound.getInteger("ColorNumber"));

		GenericBehavior.readCommonNBT(compound, this);
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable)
	{
		return null;
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.horseType, this.gender));
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		return new ItemStack(this.getSpawnEgg());
	}

	@Override
	public int getPrimaryEggColor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSecondaryEggColor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EntityGender getEntityGender()
	{
		return this.gender;
	}

	@Override
	public void setSleepingPos(BlockPos pos)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public BlockPos getSleepingPos()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ItemStack> getFoodItems()
	{
		return TEMPTATION_ITEMS;
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
		return this.horseType;
	}

	@Override
	public Entity convertToVanilla()
	{
		HorseEntity entity = new HorseEntity(this.level);
		entity.setPosition(this.getX(), this.getY(), this.getZ());
		if (entity.hasCustomName())
			entity.setCustomNameTag(this.getCustomNameTag());
		return entity;
	}

}
