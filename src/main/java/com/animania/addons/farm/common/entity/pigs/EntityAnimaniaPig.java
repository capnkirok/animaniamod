package com.animania.addons.farm.common.entity.pigs;

import java.util.Set;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.pigs.ai.FindMudGoal;
import com.animania.addons.farm.common.entity.pigs.ai.PigSnuffleGoal;
import com.animania.addons.farm.common.entity.pigs.ai.TemptItemStackGoal;
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
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.RegistryHelper.RItem;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.PigZombieEntity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.potion.MobEffectInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaPig extends Pig implements IAnimaniaAnimalBase, IConvertable
{

	protected static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.<Boolean> createKey(EntityAnimaniaPig.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> MUDDY = SynchedEntityData.defineId(EntityAnimaniaPig.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Float> SPLASHTIMER = SynchedEntityData.defineId(EntityAnimaniaPig.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Float> MUDTIMER = SynchedEntityData.defineId(EntityAnimaniaPig.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> FED = SynchedEntityData.defineId(EntityAnimaniaPig.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> WATERED = SynchedEntityData.defineId(EntityAnimaniaPig.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> PLAYED = SynchedEntityData.defineId(EntityAnimaniaPig.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityAnimaniaPig.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Boolean> HANDFED = SynchedEntityData.defineId(EntityAnimaniaPig.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(EntityAnimaniaPig.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Float> SLEEPTIMER = SynchedEntityData.defineId(EntityAnimaniaPig.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> INTERACTED = SynchedEntityData.defineId(EntityAnimaniaPig.class, EntityDataSerializers.BOOLEAN);

	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(FarmConfig.settings.pigFood));

	protected boolean boosting;
	protected int boostTime;
	protected int totalBoostTime;
	public int eatTimer;
	public PigSnuffleGoal entityAIEatGrass;
	protected int fedTimer;
	protected int wateredTimer;
	protected int playedTimer;
	protected int happyTimer;
	public int blinkTimer;
	protected int damageTimer;
	protected ItemStack slop;
	protected PigType pigType;
	protected EntityGender gender;

	public EntityAnimaniaPig(Level levelIn)
	{
		super(levelIn);
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.playedTimer = AnimaniaConfig.careAndFeeding.playTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + this.rand.nextInt(80);
		this.enablePersistence();
		this.slop = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop);
		this.entityAIEatGrass = new PigSnuffleGoal(this);
		this.goalSelector.addGoal(11, this.entityAIEatGrass);

		this.initAI();
	}

	protected void initAI()
	{
		this.goalSelector.addGoal(0, new SwimmingGoal(this));
		this.goalSelector.addGoal(1, new FindMudGoal(this, 1.2D));
		this.goalSelector.addGoal(2, new GenericAIWanderAvoidWater(this, 1.0D));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.goalSelector.addGoal(3, new GenericAIFindWater<>(this, 1.0D, this.entityAIEatGrass, EntityAnimaniaPig.class));
			this.goalSelector.addGoal(3, new GenericAIFindFood<>(this, 1.0D, this.entityAIEatGrass, true));
		}
		this.goalSelector.addGoal(4, new GenericAIPanic<>(this, 1.5D));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.goalSelector.addGoal(8, new GenericAISleep<EntityAnimaniaPig>(this, 0.8, AnimaniaHelper.getBlock(FarmConfig.settings.pigBed), AnimaniaHelper.getBlock(FarmConfig.settings.pigBed2), EntityAnimaniaPig.class));
		}
		this.goalSelector.addGoal(9, new GenericAITempt<EntityAnimaniaPig>(this, 1.2D, new ItemStack(Items.CARROT_ON_A_STICK), false));
		this.goalSelector.addGoal(10, new GenericAITempt<>(this, 1.2D, false, EntityAnimaniaPig.TEMPTATION_ITEMS));
		this.goalSelector.addGoal(10, new TemptItemStackGoal(this, 1.2d, UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop)));
		this.goalSelector.addGoal(12, new GenericAIFindSaltLick<>(this, 1.0, this.entityAIEatGrass));
		this.goalSelector.addGoal(13, new GenericAIWatchClosest(this, Player.class, 6.0F));
		this.goalSelector.addGoal(15, new GenericAILookIdle<>(this));
		this.targetTasks.addTask(16, new HurtByTargetGoal(this, false, new Class[0]));
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
			}
			else
			{
				this.motionX = 0.0D;
				this.motionY = 0.0D;
				this.motionZ = 0.0D;
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.getX() - this.prevgetX();
			double d0 = this.getZ() - this.prevgetZ();
			float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

			if (f1 > 1.0F)
			{
				f1 = 1.0F;
			}

			this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		}
		else
		{
			this.stepHeight = 0.5F;
			p_191986_3_ = this.moveForward;
			this.jumpMovementFactor = 0.02F;
			super.travel(p_191986_1_, p_191986_2_, p_191986_3_);
		}
	}

	@Override
	public void setInLove(Player player)
	{
		this.level.broadcastEntityEvent(this, (byte) 18);
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
	public void writeEntityToNBT(CompoundTag compound)
	{
		super.writeEntityToNBT(compound);
		GenericBehavior.writeCommonNBT(compound, this);
		compound.putBoolean("Saddle", this.getSaddled());
		compound.putBoolean("Muddy", this.getMuddy());
		compound.setFloat("MudTimer", this.getMudTimer());
		compound.setFloat("SplashTimer", this.getSplashTimer());
		compound.putBoolean("Played", this.getPlayed());
	}

	@Override
	public void readEntityFromNBT(CompoundTag compound)
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
	public EntityDataAccessor<Integer> getAgeParam()
	{
		return AGE;
	}

	@Override
	public EntityDataAccessor<Boolean> getHandFedParam()
	{
		return HANDFED;
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
	public boolean processInteract(Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		Player Player = player;

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
			if (this.entityAIEatGrass != null)
				this.entityAIEatGrass.startExecuting();
			this.setHandFed(true);
			this.setFed(true);
			this.setInLove(player);
			return true;
		}

		return GenericBehavior.interactCommon(this, Player, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof PigEntityletBase ? null : this.pigType.isPrime ? new ResourceLocation("farm/" + Animania.MODID, "pig_prime") : new ResourceLocation("farm/" + Animania.MODID, "pig_regular");
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
	public EntityDataAccessor<Boolean> getFedParam()
	{
		return FED;
	}

	public void setSlopFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityAnimaniaPig.FED, true);
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer * 2 + this.rand.nextInt(100);
		}
		else
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
		}
		else
			this.dataManager.set(EntityAnimaniaPig.PLAYED, false);
	}

	@Override
	public EntityDataAccessor<Boolean> getWateredParam()
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
		this.dataManager.set(EntityAnimaniaPig.MUDTIMER, timer);
	}

	public Float getSplashTimer()
	{
		return this.getFloatFromDataManager(SPLASHTIMER);
	}

	public void setSplashTimer(Float timer)
	{
		this.dataManager.set(EntityAnimaniaPig.SPLASHTIMER, timer);
	}

	@Override
	public void onStruckByLightning(EntityLightningBolt lightningBolt)
	{
		if (!this.level.isClientSide && !this.isDead)
		{
			PigZombieEntity PigZombieEntity = new PigZombieEntity(this.level);
			PigZombieEntity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
			PigZombieEntity.setLocationAndAngles(this.getX(), this.getY(), this.getZ(), this.rotationYaw, this.rotationPitch);
			PigZombieEntity.setNoAI(this.isAIDisabled());

			if (this.isChild())
			{
				PigZombieEntity.setChild(true);
			}

			if (this.hasCustomName())
			{
				PigZombieEntity.setCustomNameTag(this.getCustomNameTag());
				PigZombieEntity.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
			}

			AnimaniaHelper.spawnEntity(this.level, PigZombieEntity);
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

		BlockPos currentpos = new BlockPos(this.getX(), this.getY(), this.getZ());
		Block poschk = this.level.getBlockState(currentpos).getBlock();

		if (poschk != null && (poschk == BlockHandler.blockMud || poschk.getUnlocalizedName().equals("tile.mud")))
			this.addMobEffectInstance(new MobEffectInstance(MobEffects.SLOWNESS, 2, 4, false, false));

		super.onLivingUpdate();
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

	@Override
	protected RItem getDropItem()
	{
		return null;
	}

	@Override
	public PigEntity createChild(AgeableEntity ageable)
	{
		return null;
	}

	@Override
	public RItem getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.pigType, this.gender));
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		return new ItemStack(this.getSpawnEgg());
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
		return this.pigType;
	}

	@Override
	public Entity convertToVanilla()
	{
		PigEntity entity = new PigEntity(this.level);
		entity.setPosition(this.getX(), this.getY(), this.getZ());
		if (entity.hasCustomName())
			entity.setCustomNameTag(this.getCustomNameTag());
		return entity;
	}
}
