package com.animania.addons.farm.common.entity.pigs;

import java.util.Set;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.pigs.ai.EntityAIFindMud;
import com.animania.addons.farm.common.entity.pigs.ai.EntityAIPigSnuffle;
import com.animania.addons.farm.common.entity.pigs.ai.EntityAITemptItemStack;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindSaltLick;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaPig extends EntityPig implements IAnimaniaAnimalBase
{

	protected static final DataParameter<Boolean> SADDLED = EntityDataManager.<Boolean> createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> MUDDY = EntityDataManager.<Boolean> createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SPLASHTIMER = EntityDataManager.<Float> createKey(EntityAnimaniaPig.class, DataSerializers.FLOAT);
	protected static final DataParameter<Float> MUDTIMER = EntityDataManager.<Float> createKey(EntityAnimaniaPig.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean> createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean> createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> PLAYED = EntityDataManager.<Boolean> createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer> createKey(EntityAnimaniaPig.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> HANDFED = EntityDataManager.<Boolean> createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean> createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float> createKey(EntityAnimaniaPig.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> INTERACTED = EntityDataManager.<Boolean> createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);

	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(FarmConfig.settings.pigFood));
	protected boolean boosting;
	protected int boostTime;
	protected int totalBoostTime;
	public int eatTimer;
	public EntityAIPigSnuffle entityAIEatGrass;
	protected int fedTimer;
	protected int wateredTimer;
	protected int playedTimer;
	protected int happyTimer;
	public int blinkTimer;
	protected int damageTimer;
	protected ItemStack slop;
	protected PigType pigType;
	protected EntityGender gender;

	public EntityAnimaniaPig(World worldIn)
	{
		super(worldIn);
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.playedTimer = AnimaniaConfig.careAndFeeding.playTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + this.rand.nextInt(80);
		this.enablePersistence();
		this.slop = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop);
		this.entityAIEatGrass = new EntityAIPigSnuffle(this);
		this.tasks.addTask(11, this.entityAIEatGrass);

		this.initAI();
	}

	protected void initAI()
	{
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIFindMud(this, 1.2D));
		this.tasks.addTask(2, new GenericAIWanderAvoidWater(this, 1.0D));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.tasks.addTask(3, new GenericAIFindWater<EntityAnimaniaPig>(this, 1.0D, entityAIEatGrass, EntityAnimaniaPig.class));
			this.tasks.addTask(3, new GenericAIFindFood<EntityAnimaniaPig>(this, 1.0D, entityAIEatGrass, true));
		}
		this.tasks.addTask(4, new GenericAIPanic<EntityAnimaniaPig>(this, 1.5D));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.tasks.addTask(8, new GenericAISleep<EntityAnimaniaPig>(this, 0.8, AnimaniaHelper.getBlock(FarmConfig.settings.pigBed), AnimaniaHelper.getBlock(FarmConfig.settings.pigBed2), EntityAnimaniaPig.class));
		}
		this.tasks.addTask(9, new GenericAITempt<EntityAnimaniaPig>(this, 1.2D, new ItemStack(Items.CARROT_ON_A_STICK), false));
		this.tasks.addTask(10, new GenericAITempt<EntityAnimaniaPig>(this, 1.2D, false, EntityAnimaniaPig.TEMPTATION_ITEMS));
		this.tasks.addTask(10, new EntityAITemptItemStack(this, 1.2d, UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop)));
		this.tasks.addTask(12, new GenericAIFindSaltLick<EntityAnimaniaPig>(this, 1.0, entityAIEatGrass));
		this.tasks.addTask(13, new GenericAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(15, new GenericAILookIdle<EntityAnimaniaPig>(this));
		this.targetTasks.addTask(16, new EntityAIHurtByTarget(this, false, new Class[0]));
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
	public void travel(float p_191986_1_, float p_191986_2_, float p_191986_3_)
	{
		Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);

		if (this.isBeingRidden() && this.canBeSteered())
		{
			this.rotationYaw = entity.rotationYaw;
			this.prevRotationYaw = this.rotationYaw;
			this.rotationPitch = entity.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.renderYawOffset = this.rotationYaw;
			this.rotationYawHead = this.rotationYaw;
			this.stepHeight = 1.0F;
			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (this.boosting && this.boostTime++ > this.totalBoostTime)
			{
				this.boosting = false;
			}

			if (this.canPassengerSteer())
			{
				float f = (float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 0.225F;

				if (this.boosting)
				{
					f += f * 1.15F * MathHelper.sin((float) this.boostTime / (float) this.totalBoostTime * (float) Math.PI);
				}

				this.setAIMoveSpeed(f);
				super.travel(0.0F, 0.0F, 1.0F);
			} else
			{
				this.motionX = 0.0D;
				this.motionY = 0.0D;
				this.motionZ = 0.0D;
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.posX - this.prevPosX;
			double d0 = this.posZ - this.prevPosZ;
			float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

			if (f1 > 1.0F)
			{
				f1 = 1.0F;
			}

			this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		} else
		{
			this.stepHeight = 0.5F;
			p_191986_3_ = this.moveForward;
			this.jumpMovementFactor = 0.02F;
			super.travel(p_191986_1_, p_191986_2_, p_191986_3_);
		}
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
	}

	@Override
	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaPig.SADDLED, false);
		this.dataManager.register(EntityAnimaniaPig.MUDDY, false);
		this.dataManager.register(EntityAnimaniaPig.MUDTIMER, Float.valueOf(0.0F));
		this.dataManager.register(EntityAnimaniaPig.SPLASHTIMER, Float.valueOf(0.0F));
		this.dataManager.register(EntityAnimaniaPig.FED, true);
		this.dataManager.register(EntityAnimaniaPig.HANDFED, false);
		this.dataManager.register(EntityAnimaniaPig.WATERED, true);
		this.dataManager.register(EntityAnimaniaPig.PLAYED, true);
		this.dataManager.register(EntityAnimaniaPig.AGE, Integer.valueOf(0));
		this.dataManager.register(EntityAnimaniaPig.SLEEPING, false);
		this.dataManager.register(EntityAnimaniaPig.SLEEPTIMER, Float.valueOf(0.0F));
		this.dataManager.register(INTERACTED, false);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		GenericBehavior.writeCommonNBT(compound, this);
		compound.setBoolean("Saddle", this.getSaddled());
		compound.setBoolean("Muddy", this.getMuddy());
		compound.setFloat("MudTimer", this.getMudTimer());
		compound.setFloat("SplashTimer", this.getSplashTimer());
		compound.setBoolean("Played", this.getPlayed());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		GenericBehavior.readCommonNBT(compound, this);
		this.setSaddled(compound.getBoolean("Saddle"));
		this.setMuddy(compound.getBoolean("Muddy"));
		this.setMudTimer(compound.getFloat("MudTimer"));
		this.setSplashTimer(compound.getFloat("SplashTimer"));
		this.setPlayed(compound.getBoolean("Played"));
	}

	@Override
	public DataParameter<Integer> getAgeParam()
	{
		return AGE;
	}

	@Override
	public DataParameter<Boolean> getHandFedParam()
	{
		return HANDFED;
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
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (!stack.isEmpty() && stack.getItem() == Items.SADDLE)
			return true;
		else if (stack != ItemStack.EMPTY && AnimaniaHelper.hasFluid(stack, BlockHandler.fluidSlop) && !this.getSleeping())
		{
			if (!player.isCreative())
			{
				ItemStack emptied = AnimaniaHelper.emptyContainer(stack);
				stack.shrink(1);
				AnimaniaHelper.addItem(player, emptied);
			}

			this.eatTimer = 40;
			if (entityAIEatGrass != null)
				this.entityAIEatGrass.startExecuting();
			this.setHandFed(true);
			this.setFed(true);
			this.setInLove(player);
			return true;
		}

		return GenericBehavior.interactCommon(this, entityplayer, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityPigletBase ? null : this.pigType.isPrime ? new ResourceLocation("farm/" + Animania.MODID, "pig_prime") : new ResourceLocation("farm/" + Animania.MODID, "pig_regular");
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
	{
		super.dropLoot(wasRecentlyHit, lootingModifier, source);
		this.dropFewItems(wasRecentlyHit, lootingModifier);
		this.dropEquipment(wasRecentlyHit, lootingModifier);
	}

	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier)
	{
		super.dropEquipment(wasRecentlyHit, lootingModifier);

		if (this.getSaddled())
			this.dropItem(Items.SADDLE, 1);
	}

	@Override
	public boolean getSaddled()
	{
		return this.getBoolFromDataManager(SADDLED);
	}

	/**
	 * Set or remove the saddle of the pig.
	 */
	@Override
	public void setSaddled(boolean saddled)
	{
		if (saddled)
			this.dataManager.set(EntityAnimaniaPig.SADDLED, true);
		else
			this.dataManager.set(EntityAnimaniaPig.SADDLED, false);
	}

	@Override
	public DataParameter<Boolean> getFedParam()
	{
		return FED;
	}

	public void setSlopFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityAnimaniaPig.FED, true);
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer * 2 + this.rand.nextInt(100);
		} else
			this.dataManager.set(EntityAnimaniaPig.FED, false);
	}

	public boolean getPlayed()
	{
		return this.getBoolFromDataManager(PLAYED);
	}

	public void setPlayed(boolean played)
	{
		if (played)
		{
			this.dataManager.set(EntityAnimaniaPig.PLAYED, true);
			this.playedTimer = AnimaniaConfig.careAndFeeding.playTimer + this.rand.nextInt(100);
		} else
			this.dataManager.set(EntityAnimaniaPig.PLAYED, false);
	}

	@Override
	public DataParameter<Boolean> getWateredParam()
	{
		return WATERED;
	}

	public boolean getMuddy()
	{
		return this.getBoolFromDataManager(MUDDY);
	}

	public void setMuddy(boolean muddy)
	{
		if (muddy)
			this.dataManager.set(EntityAnimaniaPig.MUDDY, true);
		else
			this.dataManager.set(EntityAnimaniaPig.MUDDY, false);
	}

	public Float getMudTimer()
	{
		return this.getFloatFromDataManager(MUDTIMER);
	}

	public void setMudTimer(Float timer)
	{
		this.dataManager.set(EntityAnimaniaPig.MUDTIMER, Float.valueOf(timer));
	}

	public Float getSplashTimer()
	{
		return this.getFloatFromDataManager(SPLASHTIMER);
	}

	public void setSplashTimer(Float timer)
	{
		this.dataManager.set(EntityAnimaniaPig.SPLASHTIMER, Float.valueOf(timer));
	}

	@Override
	public void onStruckByLightning(EntityLightningBolt lightningBolt)
	{
		if (!this.world.isRemote && !this.isDead)
		{
			EntityPigZombie entitypigzombie = new EntityPigZombie(this.world);
			entitypigzombie.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
			entitypigzombie.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			entitypigzombie.setNoAI(this.isAIDisabled());

			if (this.isChild())
			{
				entitypigzombie.setChild(true);
			}

			if (this.hasCustomName())
			{
				entitypigzombie.setCustomNameTag(this.getCustomNameTag());
				entitypigzombie.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
			}

			this.world.spawnEntity(entitypigzombie);
			this.setDead();
		}
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateCommon(this);

		if (this.playedTimer > -1 && !AnimaniaConfig.gameRules.ambianceMode)
		{
			this.playedTimer--;

			if (this.playedTimer == 0)
				this.setPlayed(false);
		}

		if (this.getMudTimer() > 0.0)
		{
			this.setPlayed(true);
			this.playedTimer = AnimaniaConfig.careAndFeeding.playTimer + this.rand.nextInt(100);
		}

		boolean played = this.getPlayed();

		BlockPos currentpos = new BlockPos(this.posX, this.posY, this.posZ);
		Block poschk = this.world.getBlockState(currentpos).getBlock();

		if (poschk != null && (poschk == BlockHandler.blockMud || poschk.getUnlocalizedName().equals("tile.mud")))
			this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, 4, false, false));

		super.onLivingUpdate();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
			this.eatTimer = 80;
		else
			super.handleStatusUpdate(id);
	}

	@Override
	protected Item getDropItem()
	{
		return null;
	}

	@Override
	public EntityPig createChild(EntityAgeable ageable)
	{
		return null;
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.pigType, this.gender));
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		return new ItemStack(getSpawnEgg());
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 0;
	}

	@Override
	public int getSecondaryEggColor()
	{
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
	public void setLiquidFed(boolean liquidFed)
	{
		this.setSlopFed(liquidFed);
	}

	@Override
	public Fluid[] getFoodFluids()
	{
		return new Fluid[] { BlockHandler.fluidSlop };
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
		return pigType;
	}
}
