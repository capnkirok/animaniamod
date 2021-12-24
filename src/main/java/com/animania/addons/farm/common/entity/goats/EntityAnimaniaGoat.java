package com.animania.addons.farm.common.entity.goats;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityBuckAngora;
import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityDoeAngora;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIAvoidEntity;
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
import com.animania.common.handler.AddonHandler;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.ai.Goal;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.level.entity.animal.Sheep;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemShears;
import net.minecraft.level.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.datasync.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.level.Level;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaGoat extends Sheep implements IAnimaniaAnimalBase
{

	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(FarmConfig.settings.goatFood));
	protected static final EntityDataAccessor<Boolean> WATERED = SynchedEntityData.<Boolean> createKey(EntityAnimaniaGoat.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> FED = SynchedEntityData.defineId(EntityAnimaniaGoat.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Optional<UUID>> RIVAL_UNIQUE_ID = SynchedEntityData.defineId(EntityAnimaniaGoat.class, EntityDataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(EntityAnimaniaGoat.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> SHEARED_TIMER = SynchedEntityData.defineId(EntityAnimaniaGoat.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Boolean> SPOOKED = SynchedEntityData.defineId(EntityAnimaniaGoat.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Float> SPOOKED_TIMER = SynchedEntityData.defineId(EntityAnimaniaGoat.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityAnimaniaGoat.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Boolean> HANDFED = SynchedEntityData.defineId(EntityAnimaniaGoat.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(EntityAnimaniaGoat.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Float> SLEEPTIMER = SynchedEntityData.defineId(EntityAnimaniaGoat.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> INTERACTED = SynchedEntityData.defineId(EntityAnimaniaGoat.class, EntityDataSerializers.BOOLEAN);

	protected int happyTimer;
	public int blinkTimer;
	public int eatTimer;
	protected int fedTimer;
	protected int wateredTimer;
	protected int damageTimer;
	public GoatType goatType;
	public GenericAIEatGrass<EntityAnimaniaGoat> entityAIEatGrass;
	protected boolean mateable = false;
	protected boolean headbutting = false;
	protected EntityGender gender;
	private boolean hasRemovedBOP;

	public EntityAnimaniaGoat(Level levelIn)
	{
		super(levelIn);
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new GenericAIEatGrass<EntityAnimaniaGoat>(this);
		this.goalSelector.addGoal(0, new GenericAIPanic<EntityAnimaniaGoat>(this, 1.4D));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.goalSelector.addGoal(2, new GenericAIFindWater<EntityAnimaniaGoat>(this, 1.0D, entityAIEatGrass, EntityAnimaniaGoat.class));
			this.goalSelector.addGoal(3, new GenericAIFindFood<EntityAnimaniaGoat>(this, 1.0D, entityAIEatGrass, true));
		}
		this.goalSelector.addGoal(4, new GenericAIWanderAvoidWater(this, 1.0D));
		this.goalSelector.addGoal(5, new SwimmingGoal(this));
		this.goalSelector.addGoal(7, new GenericAITempt<EntityAnimaniaGoat>(this, 1.25D, false, EntityAnimaniaGoat.TEMPTATION_ITEMS));
		this.goalSelector.addGoal(8, this.entityAIEatGrass);
		this.goalSelector.addGoal(9, new GenericAIAvoidEntity<WolfEntity>(this, WolfEntity.class, 20.0F, 2.2D, 2.2D));
		this.goalSelector.addGoal(10, new GenericAIWatchClosest(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(11, new GenericAILookIdle<EntityAnimaniaGoat>(this));
		this.goalSelector.addGoal(12, new GenericAIFindSaltLick<EntityAnimaniaGoat>(this, 1.0, entityAIEatGrass));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.goalSelector.addGoal(10, new GenericAISleep<EntityAnimaniaGoat>(this, 0.8, AnimaniaHelper.getBlock(FarmConfig.settings.goatBed), AnimaniaHelper.getBlock(FarmConfig.settings.goatBed2), EntityAnimaniaGoat.class));
		}
		if (AddonHandler.isAddonLoaded("catsdogs"))
		{
			AddonInjectionHandler.runInjection("catsdogs", "addHerdingBehavior", null, this, 1);
		}
		this.targetTasks.addTask(0, new HurtByTargetGoal(this, false, new Class[0]));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 100 + this.rand.nextInt(100);
		this.setPosition(0.0D, 0.0D, 0.0D); // TODO Try
		this.enablePersistence();
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
	public void setPosition(double x, double y, double z) // TODO Try
	{
		this.getX() = x;
		this.getY() = y;
		this.getZ() = z;
		float f = this.width / 2.0F;
		float f1 = this.height;
		this.setEntityBoundingBox(new AxisAlignedBB(x - f, y, z - f, x + f, y + f1, z + f));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaGoat.FED, true);
		this.dataManager.register(EntityAnimaniaGoat.HANDFED, false);
		this.dataManager.register(EntityAnimaniaGoat.WATERED, true);
		this.dataManager.register(EntityAnimaniaGoat.RIVAL_UNIQUE_ID, Optional.<UUID> absent());
		this.dataManager.register(EntityAnimaniaGoat.SHEARED, false);
		this.dataManager.register(EntityAnimaniaGoat.SHEARED_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.woolRegrowthTimer + this.rand.nextInt(500)));
		this.dataManager.register(EntityAnimaniaGoat.SPOOKED, false);
		this.dataManager.register(EntityAnimaniaGoat.SPOOKED_TIMER, 0.0F);
		this.dataManager.register(EntityAnimaniaGoat.AGE, Integer.valueOf(0));
		this.dataManager.register(EntityAnimaniaGoat.SLEEPING, false);
		this.dataManager.register(EntityAnimaniaGoat.SLEEPTIMER, Float.valueOf(0.0F));
		this.dataManager.register(INTERACTED, false);
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityKidBase ? null : this.goatType.isPrime ? new ResourceLocation("farm/" + Animania.MODID, "goat_prime") : new ResourceLocation("farm/" + Animania.MODID, "goat_regular");
	}

	public void setSpooked(boolean spooked)
	{
		this.dataManager.set(EntityAnimaniaGoat.SPOOKED, Boolean.valueOf(spooked));
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return mateable && (stack != ItemStack.EMPTY && this.isGoatBreedingItem(stack));
	}

	private boolean isGoatBreedingItem(ItemStack itemIn)
	{
		return AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, itemIn) || itemIn.getItem() == Item.getItemFromBlock(Blocks.YELLOW_FLOWER) || itemIn.getItem() == Item.getItemFromBlock(Blocks.RED_FLOWER);
	}

	public boolean getSpooked()
	{
		return this.getBoolFromDataManager(SPOOKED);
	}

	public Float getSpookedTimer()
	{
		return this.getFloatFromDataManager(SPOOKED_TIMER);
	}

	@Override
	public EntityDataAccessor<Integer> getAgeParam()
	{
		return AGE;
	}

	public void setSpookedTimer(Float timer)
	{
		this.dataManager.set(EntityAnimaniaGoat.SPOOKED_TIMER, Float.valueOf(timer));
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
	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	protected Item getDropItem()
	{
		return Items.LEATHER;
	}

	@Override
	public void onLivingUpdate()
	{
		if (!hasRemovedBOP)
		{
			if (ModList.get().isLoaded("biomesoplenty"))
			{
				Iterator<EntityAITaskEntry> it = this.tasks.taskEntries.iterator();
				while (it.hasNext())
				{
					EntityAITaskEntry entry = it.next();
					Goal ai = entry.action;
					try
					{
						if (Class.forName("biomesoplenty.common.entities.ai.EntityAIEatBOPGrass").isInstance(ai))
						{
							entry.using = false;
							ai.resetTask();
							it.remove();
						}
					} catch (Exception e)
					{
					}
				}

				hasRemovedBOP = true;
			}
		}

		if (this.getSpooked())
		{

			if (this.getSpookedTimer() == 1.0F)
			{
				this.setJumping(true);
			} else
			{
				this.setJumping(false);
			}

			this.getNavigation().stop();
			this.setNoAI(true);

			this.setSpookedTimer(this.getSpookedTimer() - 0.01F);

			if (this.getSpookedTimer() <= 0.20F && this.getSpookedTimer() > 0.1F)
			{
				this.setJumping(true);
			} else if (this.getSpookedTimer() <= 0.0F)
			{
				this.setSpooked(false);
				this.setSpookedTimer(0.0F);
				this.setNoAI(false);
				this.setJumping(false);

			}
		}

		GenericBehavior.livingUpdateCommon(this);

		boolean sheared = this.getSheared();
		if (sheared)
		{
			int shearedTimer = this.getWoolRegrowthTimer();
			shearedTimer--;
			this.setWoolRegrowthTimer(shearedTimer);
			if (shearedTimer < 0)
			{
				this.setSheared(false);

			}
		}

		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(PlayerEntity player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		PlayerEntity PlayerEntity = player;

		if (stack.getItem() instanceof ItemShears && !this.getSheared() && !this.isChild() && (this instanceof EntityBuckAngora || this instanceof EntityDoeAngora)) // Forge:
																																										// //
																																										// onSheared
		{
			if (!this.level.isRemote)
			{
				this.setSheared(true);
				int i = 1 + this.rand.nextInt(2);

				for (int j = 0; j < i; ++j)
				{
					EntityItem entityitem = this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1), 1.0F);
					entityitem.motionY += this.rand.nextFloat() * 0.05F;
					entityitem.motionX += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F;
					entityitem.motionZ += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F;
				}
			}

			player.swingArm(hand);
			stack.damageItem(1, player);
			this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
			if (this.getSleeping())
			{
				this.setSleeping(false);
			}
			return true;
		} else if (stack.getItem() instanceof ItemDye)
		{
			return true;
		} else if (stack.getItem() instanceof ItemShears)
		{
			return true;
		}

		return GenericBehavior.interactCommon(this, PlayerEntity, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
	}

	@Override
	public void eatGrassBonus()
	{

	}

	@Override
	public void setInLove(PlayerEntity player)
	{

		if (!this.getSleeping())
			this.level.broadcastEntityEvent(this, (byte) 18);
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
	public void writeEntityToNBT(CompoundTag compound)
	{
		super.writeEntityToNBT(compound);
		compound.putBoolean("Sheared", this.getSheared());
		compound.putBoolean("Spooked", this.getSpooked());

		GenericBehavior.writeCommonNBT(compound, this);
	}

	@Override
	public boolean getSheared()
	{
		return this.getBoolFromDataManager(SHEARED);
	}

	@Override
	public void setSheared(boolean sheared)
	{
		if (sheared)
		{
			this.dataManager.set(EntityAnimaniaGoat.SHEARED, true);
			this.setWoolRegrowthTimer(AnimaniaConfig.careAndFeeding.woolRegrowthTimer + this.rand.nextInt(500));
		} else
			this.dataManager.set(EntityAnimaniaGoat.SHEARED, false);
	}

	public int getWoolRegrowthTimer()
	{
		return this.getIntFromDataManager(SHEARED_TIMER);
	}

	public void setWoolRegrowthTimer(int time)
	{
		this.dataManager.set(EntityAnimaniaGoat.SHEARED_TIMER, Integer.valueOf(time));
	}

	@Override
	public void readEntityFromNBT(CompoundTag compound)
	{
		super.readEntityFromNBT(compound);

		this.setSheared(compound.getBoolean("Sheared"));
		this.setSpooked(compound.getBoolean("Spooked"));

		GenericBehavior.readCommonNBT(compound, this);

	}

	@Override
	public EntityAnimaniaGoat createChild(AgeableEntity ageable)
	{
		return null;
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.goatType, this.gender));
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
	public Set<ItemStack> getFoodItems()
	{
		return TEMPTATION_ITEMS;
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
		this.eatTimer = i;
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
	public EntityDataAccessor<Boolean> getInteractedParam()
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
		return goatType;
	}

	@Override
	public EntityDataAccessor<Boolean> getWateredParam()
	{
		return WATERED;
	}

}
