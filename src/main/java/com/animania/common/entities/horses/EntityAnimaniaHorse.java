package com.animania.common.entities.horses;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindSaltLick;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.entities.horses.ai.EntityAIFollowMateHorses;
import com.animania.common.entities.horses.ai.EntityAILookIdleHorses;
import com.animania.common.entities.horses.ai.EntityAIWanderHorses;
import com.animania.common.entities.horses.ai.EntityHorseEatGrass;
import com.animania.common.entities.props.EntityWagon;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.inventory.ContainerHorseCart;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaHorse extends EntityHorse implements IAnimaniaAnimalBase
{
	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.horseFood));
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaHorse.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaHorse.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer>createKey(EntityAnimaniaHorse.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityAnimaniaHorse.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> HANDFED = EntityDataManager.<Boolean>createKey(EntityAnimaniaHorse.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean>createKey(EntityAnimaniaHorse.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float>createKey(EntityAnimaniaHorse.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> INTERACTED = EntityDataManager.<Boolean>createKey(EntityAnimaniaHorse.class, DataSerializers.BOOLEAN);

	protected int happyTimer;
	public int blinkTimer;
	public int eatTimer;
	protected int fedTimer;
	protected int wateredTimer;
	protected int damageTimer;
	public EntityHorseEatGrass entityAIEatGrass;
	public HorseType horseType;
	protected boolean mateable = false;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	protected EntityGender gender;
	protected ContainerHorseCart cartChest;
	protected Item dropRaw = Items.AIR;
	protected Item dropCooked = Items.AIR;

	public EntityAnimaniaHorse(World worldIn)
	{
		super(worldIn);
		this.stepHeight = 1.2F;
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new EntityHorseEatGrass(this);
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.tasks.addTask(1, new GenericAIFindWater<EntityAnimaniaHorse>(this, 1.0D, entityAIEatGrass, EntityAnimaniaHorse.class));
			this.tasks.addTask(1, new GenericAIFindFood<EntityAnimaniaHorse>(this, 1.0D, entityAIEatGrass, true));
		}
		this.tasks.addTask(0, new GenericAIPanic<EntityAnimaniaHorse>(this, 2.0D));
		this.tasks.addTask(2, new EntityAIFollowMateHorses(this, 1.1D));
		this.tasks.addTask(3, new EntityAIWanderHorses(this, 1.0D));
		this.tasks.addTask(4, new EntityAISwimming(this));
		this.tasks.addTask(5, new GenericAITempt<EntityAnimaniaHorse>(this, 1.25D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(6, this.entityAIEatGrass);
		this.tasks.addTask(7, new GenericAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdleHorses(this));
		this.tasks.addTask(9, new GenericAIFindSaltLick<EntityAnimaniaHorse>(this, 1.0, entityAIEatGrass));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.tasks.addTask(10, new GenericAISleep<EntityAnimaniaHorse>(this, 0.8, AnimaniaHelper.getBlock(AnimaniaConfig.careAndFeeding.horseBed), AnimaniaHelper.getBlock(AnimaniaConfig.careAndFeeding.horseBed2), EntityAnimaniaHorse.class));
		}
		this.tasks.addTask(11, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + rand.nextInt(80);
		this.enablePersistence();
		this.initHorseChest();

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

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28499999403953552D);
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateCommon(this);

		super.onLivingUpdate();
	}

	@Override
	public void updatePassenger(Entity passenger)
	{
		if (this.isPassenger(passenger))
		{

			float f = 0.0F;
			float f1 = (float) ((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());
			if (passenger instanceof EntityPlayer)
			{
				f1 = (float) ((this.isDead ? 0.009999999776482582D : 1.6D) + passenger.getYOffset());
			}

			if (passenger instanceof EntityPlayer)
			{

				EntityPlayer player = (EntityPlayer) passenger;
				List wagons = AnimaniaHelper.getWagonsInRange(EntityWagon.class, 3, world, this);

				if (!wagons.isEmpty())
				{
					if (wagons.size() >= 0)
					{
						for (int i = 0; i < wagons.size(); i++)
						{
							EntityWagon tempWagon = (EntityWagon) wagons.get(i);
							if (tempWagon.pulled && tempWagon.puller == this)
							{

								f = (float) ((double) f + 1.82D);

								Vec3d vec3d = (new Vec3d((double) f, 0.0D, 0.0D)).rotateYaw(-tempWagon.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
								passenger.setPosition(tempWagon.posX + vec3d.x, tempWagon.posY + (double) f1, tempWagon.posZ + vec3d.z);

							}
							else
							{
								passenger.setPosition(this.posX, this.posY + this.getMountedYOffset() + passenger.getYOffset(), this.posZ);
							}
						}
					}
				}
				else
				{
					f = (float) ((double) f - 0.42D);

					Vec3d vec3d = (new Vec3d((double) f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float) Math.PI / 2F));
					// passenger.setPosition(this.posX + vec3d.x, this.posY +
					// (double) f1, this.posZ + vec3d.z);

					passenger.setPosition(this.posX, this.posY + this.getMountedYOffset() + passenger.getYOffset(), this.posZ);
				}
			}
			else
			{
				passenger.setPosition(this.posX, this.posY + this.getMountedYOffset() + passenger.getYOffset(), this.posZ);
			}

		}
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityFoalBase ? null : new ResourceLocation(Animania.MODID, "horse");
	}

	@Override
	public boolean canJump()
	{
		return true;
	}

	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this,ModSoundEvents.horseliving1, ModSoundEvents.horseliving2, ModSoundEvents.horseliving3, ModSoundEvents.horseliving4, ModSoundEvents.horseliving5, ModSoundEvents.horseliving6);
	}

	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(ModSoundEvents.horsehurt1, ModSoundEvents.horsehurt2, ModSoundEvents.horsehurt3);
	}

	protected SoundEvent getDeathSound()
	{
		return GenericBehavior.getRandomSound(ModSoundEvents.horsehurt1, ModSoundEvents.horsehurt2, ModSoundEvents.horsehurt3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
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
			this.jumpPower = 0.4F + 0.4F * (float) jumpPowerIn / 90.0F;
		}

	}

	@Override
	public DataParameter<Boolean> getFedParam()
	{
		return FED;
	}

	@Override
	public DataParameter<Boolean> getHandFedParam()
	{
		return HANDFED;
	}

	@Override
	public DataParameter<Boolean> getWateredParam()
	{
		return WATERED;
	}

	@Override
	public DataParameter<Boolean> getSleepingParam()
	{
		return SLEEPING;
	}

	@Override
	public DataParameter<Float> getSleepTimerParam()
	{
		return SLEEPTIMER;
	}

	@Override
	public DataParameter<Integer> getAgeParam()
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
		return resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink()
	{
		return resourceLocationBlink;
	}

	@Override
	public void travel(float p_191986_1_, float p_191986_2_, float p_191986_3_)
	{
		if (this.isBeingRidden() && this.canBeSteered()) // &&
		// this.isHorseSaddled())
		{
			EntityLivingBase entitylivingbase = (EntityLivingBase) this.getControllingPassenger();
			this.rotationYaw = entitylivingbase.rotationYaw;
			this.prevRotationYaw = this.rotationYaw;
			this.rotationPitch = entitylivingbase.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.renderYawOffset = this.rotationYaw;
			this.rotationYawHead = this.renderYawOffset;
			p_191986_1_ = entitylivingbase.moveStrafing * 0.5F;
			p_191986_3_ = entitylivingbase.moveForward * 4F;

			if (p_191986_3_ <= 0.0F)
			{
				p_191986_3_ *= 0.25F;
				this.gallopTime = 0;
			}

			if (this.onGround && this.jumpPower == 0.0F && this.isRearing())
			{
				p_191986_1_ = 0.0F;
				p_191986_3_ = 0.0F;
			}

			if (this.jumpPower > 0.0F && !this.isHorseJumping() && this.onGround)
			{
				this.motionY = this.getHorseJumpStrength() * (double) this.jumpPower;

				if (this.isPotionActive(MobEffects.JUMP_BOOST))
				{
					this.motionY += (double) ((float) (this.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
				}

				this.setHorseJumping(true);
				this.isAirBorne = true;

				if (p_191986_3_ > 0.0F)
				{
					float f = MathHelper.sin(this.rotationYaw * 0.017453292F);
					float f1 = MathHelper.cos(this.rotationYaw * 0.017453292F);
					this.motionX += (double) (-0.4F * f * this.jumpPower);
					this.motionZ += (double) (0.4F * f1 * this.jumpPower);
					this.playSound(SoundEvents.ENTITY_HORSE_JUMP, 0.4F, 1.0F);
				}

				this.jumpPower = 0.0F;
			}

			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (this.canPassengerSteer() || this.getLeashed())
			{
				this.setAIMoveSpeed((float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
				super.travel(p_191986_1_, p_191986_2_ / 8, p_191986_3_);
			}
			else if (entitylivingbase instanceof EntityPlayer)
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
			double d1 = this.posX - this.prevPosX;
			double d0 = this.posZ - this.prevPosZ;
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
				p_191986_3_ = this.moveForward;
			}
			super.travel(p_191986_1_, p_191986_2_, p_191986_3_);

		}
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);

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
			this.navigator.clearPath();
			this.mountTo(player);
			return true;
		}

		return GenericBehavior.interactCommon(this, player, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
			this.eatTimer = 160;
		else
			super.handleStatusUpdate(id);
	}

	public void removeItem(EntityPlayer ep, ItemStack removeitem)
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

	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != null && this.isHorseBreedingItem(stack.getItem());
	}

	private boolean isHorseBreedingItem(Item itemIn)
	{
		return TEMPTATION_ITEMS.contains(itemIn);
	}

	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);

		
		compound.setInteger("ColorNumber", getColorNumber());

		GenericBehavior.writeCommonNBT(compound, this);

	}

	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		this.setColorNumber(compound.getInteger("ColorNumber"));

		GenericBehavior.readCommonNBT(compound, this);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
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
		return new ItemStack(getSpawnEgg());
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
	public Set<Item> getFoodItems()
	{
		return TEMPTATION_ITEMS;
	}

	@Override
	public int getBlinkTimer()
	{
		return blinkTimer;
	}

	@Override
	public void setBlinkTimer(int i)
	{
		blinkTimer = i;
	}

	@Override
	public int getEatTimer()
	{
		return eatTimer;
	}

	@Override
	public void setEatTimer(int i)
	{
		eatTimer = i;
	}

	@Override
	public int getFedTimer()
	{
		return fedTimer;
	}

	@Override
	public void setFedTimer(int i)
	{
		fedTimer = i;
	}

	@Override
	public DataParameter<Boolean> getInteractedParam()
	{
		return INTERACTED;
	}

	@Override
	public int getWaterTimer()
	{
		return wateredTimer;
	}

	@Override
	public void setWaterTimer(int i)
	{
		wateredTimer = i;
	}

	@Override
	public int getDamageTimer()
	{
		return damageTimer;
	}

	@Override
	public void setDamageTimer(int i)
	{
		damageTimer = i;
	}

	@Override
	public int getHappyTimer()
	{
		return happyTimer;
	}

	@Override
	public void setHappyTimer(int i)
	{
		happyTimer = i;
	}

	@Override
	public AnimaniaType getAnimalType()
	{
		return horseType;
	}

}
