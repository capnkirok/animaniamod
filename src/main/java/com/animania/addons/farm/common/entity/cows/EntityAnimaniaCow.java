package com.animania.addons.farm.common.entity.cows;

import java.util.Set;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.cows.CowFriesian.CowEntityFriesian;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityBullFriesian;
import com.animania.addons.farm.common.entity.cows.CowFriesian.EntityCalfFriesian;
import com.animania.addons.farm.common.entity.cows.CowHolstein.CowEntityHolstein;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityBullHolstein;
import com.animania.addons.farm.common.entity.cows.CowHolstein.EntityCalfHolstein;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.CowEntityMooshroom;
import com.animania.addons.farm.common.entity.cows.CowMooshroom.EntityBullMooshroom;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.api.interfaces.IConvertable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindSaltLick;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemShears;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaCow extends Cow implements IAnimaniaAnimalBase, IConvertable
{
	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(FarmConfig.settings.cowFood));
	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.<Integer> createKey(EntityAnimaniaCow.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Boolean> WATERED = SynchedEntityData.defineId(EntityAnimaniaCow.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> FED = SynchedEntityData.defineId(EntityAnimaniaCow.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> HANDFED = SynchedEntityData.defineId(EntityAnimaniaCow.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(EntityAnimaniaCow.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Float> SLEEPTIMER = SynchedEntityData.defineId(EntityAnimaniaCow.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> INTERACTED = SynchedEntityData.defineId(EntityAnimaniaCow.class, EntityDataSerializers.BOOLEAN);

	protected int happyTimer;
	public int blinkTimer;
	public int eatTimer;
	protected int fedTimer;
	protected int wateredTimer;
	protected int damageTimer;
	public GenericAIEatGrass<EntityAnimaniaCow> entityAIEatGrass;
	public CowType cowType;
	protected boolean mateable = false;
	protected EntityGender gender;

	public EntityAnimaniaCow(Level levelIn)
	{
		super(levelIn);
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new GenericAIEatGrass<>(this);
		this.goalSelector.addGoal(1, new GenericAIPanic<>(this, 2.0D));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.goalSelector.addGoal(2, new GenericAIFindWater<>(this, 1.0D, this.entityAIEatGrass, EntityAnimaniaCow.class));
			this.goalSelector.addGoal(3, new GenericAIFindFood<>(this, 1.0, this.entityAIEatGrass, true));
		}
		this.goalSelector.addGoal(4, new GenericAIWanderAvoidWater(this, 1.0D));
		this.goalSelector.addGoal(5, new SwimmingGoal(this));
		this.goalSelector.addGoal(7, new GenericAITempt<>(this, 1.25D, false, EntityAnimaniaCow.TEMPTATION_ITEMS));
		this.goalSelector.addGoal(6, new GenericAITempt<EntityAnimaniaCow>(this, 1.25D, new ItemStack(Blocks.YELLOW_FLOWER), false));
		this.goalSelector.addGoal(6, new GenericAITempt<EntityAnimaniaCow>(this, 1.25D, new ItemStack(Blocks.RED_FLOWER), false));
		this.goalSelector.addGoal(8, this.entityAIEatGrass);
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.goalSelector.addGoal(9, new GenericAISleep<EntityAnimaniaCow>(this, 0.8, AnimaniaHelper.getBlock(FarmConfig.settings.cowBed), AnimaniaHelper.getBlock(FarmConfig.settings.cowBed2), EntityAnimaniaCow.class));
		}
		this.goalSelector.addGoal(10, new GenericAIWatchClosest(this, Player.class, 6.0F));
		this.goalSelector.addGoal(11, new GenericAILookIdle<>(this));
		this.goalSelector.addGoal(12, new GenericAIFindSaltLick<>(this, 1.0, this.entityAIEatGrass));
		this.targetSelector.addTask(14, new HurtByTargetGoal(this, false, new Class[0]));
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers)
		{
			this.targetSelector.addTask(1, new HurtByTargetGoal(this, false, Player.class));
		}
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 100 + this.rand.nextInt(100);
		this.enablePersistence();
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
		this.entityData.register(EntityAnimaniaCow.FED, true);
		this.entityData.register(INTERACTED, false);
		this.entityData.register(EntityAnimaniaCow.HANDFED, false);
		this.entityData.register(EntityAnimaniaCow.WATERED, true);
		this.entityData.register(EntityAnimaniaCow.SLEEPING, false);
		this.entityData.register(EntityAnimaniaCow.SLEEPTIMER, Float.valueOf(0.0F));
		this.entityData.register(EntityAnimaniaCow.AGE, Integer.valueOf(0));
	}

	@Override
	protected void initEntityAI()
	{
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

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityCalfBase ? null : this.cowType.isPrime ? new ResourceLocation("farm/" + Animania.MODID, "cow_prime") : new ResourceLocation("farm/" + Animania.MODID, "cow_regular");
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateCommon(this);

		if (this.getCustomNameTag().toLowerCase().trim().equals("purp") && (this instanceof CowEntityFriesian || this instanceof EntityBullFriesian || this instanceof CowEntityHolstein || this instanceof EntityBullHolstein || this instanceof EntityCalfFriesian || this instanceof EntityCalfHolstein))
		{
			this.addMobEffectInstance(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 4, 2, false, false));
			if (!this.isWet() && !this.isInWater())
				this.setOnFireFromLava();
		}

		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		Player Player = player;

		if (this instanceof EntityBullBase ebb && ebb.getFighting())
		{
			return true;
		}
		if (stack != ItemStack.EMPTY && AnimaniaHelper.isEmptyFluidContainer(stack))
		{
			return true;
		}
		else if (stack != ItemStack.EMPTY && (this instanceof CowEntityMooshroom || this instanceof EntityBullMooshroom) && stack.getItem() instanceof ItemShears && this.getGrowingAge() >= 0) // onSheared
		{
			this.setDead();
			this.level.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.getX(), this.getY() + this.height / 2.0F, this.getZ(), 0.0D, 0.0D, 0.0D, new int[0]);

			if (!this.level.isClientSide)
			{
				EntityAnimaniaCow CowEntity = null;

				if (this instanceof CowEntityMooshroom)
					CowEntity = new CowEntityFriesian(this.level);
				else
					CowEntity = new EntityBullFriesian(this.level);

				CowEntity.setLocationAndAngles(this.getX(), this.getY(), this.getZ(), this.rotationYaw, this.rotationPitch);
				CowEntity.setHealth(this.getHealth());
				CowEntity.renderYawOffset = this.renderYawOffset;
				if (this.hasCustomName())
				{
					CowEntity.setCustomNameTag(this.getCustomNameTag());
				}
				AnimaniaHelper.spawnEntity(this.level, CowEntity);

				for (int i = 0; i < 5; ++i)
				{
					AnimaniaHelper.spawnEntity(this.level, new EntityItem(this.level, this.getX(), this.getY() + this.height, this.getZ(), new ItemStack(Blocks.RED_MUSHROOM)));
				}

				stack.damageItem(1, player);
				this.playSound(SoundEvents.ENTITY_MOOSHROOM_SHEAR, 1.0F, 1.0F);
			}
			return true;
		}

		return GenericBehavior.interactCommon(this, Player, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
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
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return this.mateable && stack != ItemStack.EMPTY && this.isCowBreedingItem(stack);
	}

	private boolean isCowBreedingItem(ItemStack itemIn)
	{
		return AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, itemIn) || itemIn.getItem() == Item.getItemFromBlock(Blocks.YELLOW_FLOWER) || itemIn.getItem() == Item.getItemFromBlock(Blocks.RED_FLOWER);
	}

	@Override
	public void writeEntityToNBT(CompoundTag compound)
	{
		super.writeEntityToNBT(compound);

		GenericBehavior.writeCommonNBT(compound, this);
	}

	@Override
	public void readEntityFromNBT(CompoundTag compound)
	{
		super.readEntityFromNBT(compound);

		GenericBehavior.readCommonNBT(compound, this);
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.cowType, this.gender));
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
		return this.cowType;
	}

	@Override
	public EntityDataAccessor<Boolean> getHandFedParam()
	{
		return HANDFED;
	}

	@Override
	public EntityDataAccessor<Boolean> getFedParam()
	{
		return FED;
	}

	@Override
	public EntityDataAccessor<Boolean> getWateredParam()
	{
		return WATERED;
	}

	@Override
	public EntityDataAccessor<Boolean> getInteractedParam()
	{
		return INTERACTED;
	}

	@Override
	public EntityDataAccessor<Integer> getAgeParam()
	{
		return AGE;
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
	public Entity convertToVanilla()
	{
		CowEntity entity = new CowEntity(this.level);
		entity.setPosition(this.getX(), this.getY(), this.getZ());
		if (entity.hasCustomName())
			entity.setCustomNameTag(this.getCustomNameTag());
		return entity;
	}

}
