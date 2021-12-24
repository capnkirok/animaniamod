package com.animania.addons.farm.common.entity.sheep;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityEweDorset;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityLambDorset;
import com.animania.addons.farm.common.entity.sheep.SheepDorset.EntityRamDorset;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityEweFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityLambFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepFriesian.EntityRamFriesian;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityEweMerino;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityLambMerino;
import com.animania.addons.farm.common.entity.sheep.SheepMerino.EntityRamMerino;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityEweSuffolk;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityLambSuffolk;
import com.animania.addons.farm.common.entity.sheep.SheepSuffolk.EntityRamSuffolk;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.api.interfaces.IConvertable;
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

import net.minecraft.core.BlockPos;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemShears;
import net.minecraft.level.IBlockAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaSheep extends Sheep implements Shearable, IAnimaniaAnimalBase, IConvertable
{

	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(FarmConfig.settings.sheepFood));
	protected static final EntityDataAccessor<Boolean> WATERED = SynchedEntityData.<Boolean> createKey(EntityAnimaniaSheep.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> FED = SynchedEntityData.defineId(EntityAnimaniaSheep.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Optional<UUID>> RIVAL_UNIQUE_ID = SynchedEntityData.defineId(EntityAnimaniaSheep.class, EntityDataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(EntityAnimaniaSheep.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> SHEARED_TIMER = SynchedEntityData.defineId(EntityAnimaniaSheep.class, EntityDataSerializers.VARINT);
	private static final EntityDataAccessor<Integer> COLOR_NUM = SynchedEntityData.defineId(EntityAnimaniaSheep.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityAnimaniaSheep.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Boolean> HANDFED = SynchedEntityData.defineId(EntityAnimaniaSheep.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(EntityAnimaniaSheep.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Float> SLEEPTIMER = SynchedEntityData.defineId(EntityAnimaniaSheep.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Integer> DYE_COLOR = SynchedEntityData.defineId(EntityAnimaniaSheep.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Boolean> INTERACTED = SynchedEntityData.defineId(EntityAnimaniaSheep.class, EntityDataSerializers.BOOLEAN);

	private static final String[] SHEEP_TEXTURES = new String[] { "black", "white", "brown" };

	protected int happyTimer;
	public int blinkTimer;
	public int eatTimer;
	protected int fedTimer;
	protected int wateredTimer;
	protected int damageTimer;
	public SheepType sheepType;
	public GenericAIEatGrass<EntityAnimaniaSheep> entityAIEatGrass;
	protected boolean mateable = false;
	protected boolean headbutting = false;
	protected EntityGender gender;
	protected EnumDyeColor color;
	protected boolean hasRemovedBOP = false;

	public EntityAnimaniaSheep(Level levelIn)
	{
		super(levelIn);
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new GenericAIEatGrass<EntityAnimaniaSheep>(this);
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.goalSelector.addGoal(2, new GenericAIFindWater<EntityAnimaniaSheep>(this, 1.0D, entityAIEatGrass, EntityAnimaniaSheep.class));
			this.goalSelector.addGoal(3, new GenericAIFindFood<EntityAnimaniaSheep>(this, 1.0D, entityAIEatGrass, true));
		}
		this.goalSelector.addGoal(4, new GenericAIWanderAvoidWater(this, 1.0D));
		this.goalSelector.addGoal(5, new SwimmingGoal(this));
		this.goalSelector.addGoal(6, new GenericAIPanic<EntityAnimaniaSheep>(this, 2.2D));
		this.goalSelector.addGoal(7, new GenericAITempt<EntityAnimaniaSheep>(this, 1.25D, false, EntityAnimaniaSheep.TEMPTATION_ITEMS));
		this.goalSelector.addGoal(6, new GenericAITempt<EntityAnimaniaSheep>(this, 1.25D, new ItemStack(Blocks.YELLOW_FLOWER), false));
		this.goalSelector.addGoal(6, new GenericAITempt<EntityAnimaniaSheep>(this, 1.25D, new ItemStack(Blocks.RED_FLOWER), false));
		this.goalSelector.addGoal(8, this.entityAIEatGrass);
		this.goalSelector.addGoal(9, new GenericAIAvoidEntity<WolfEntity>(this, WolfEntity.class, 24.0F, 2.0D, 2.2D));
		this.goalSelector.addGoal(10, new GenericAIWatchClosest(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(11, new GenericAILookIdle<EntityAnimaniaSheep>(this));
		this.goalSelector.addGoal(12, new GenericAIFindSaltLick<EntityAnimaniaSheep>(this, 1.0, entityAIEatGrass));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.goalSelector.addGoal(11, new GenericAISleep<EntityAnimaniaSheep>(this, 0.8, AnimaniaHelper.getBlock(FarmConfig.settings.sheepBed), AnimaniaHelper.getBlock(FarmConfig.settings.sheepBed2), EntityAnimaniaSheep.class));
		}
		if (AddonHandler.isAddonLoaded("catsdogs"))
		{
			AddonInjectionHandler.runInjection("catsdogs", "addHerdingBehavior", null, this, 1);
		}
		this.targetTasks.addTask(0, new HurtByTargetGoal(this, false, new Class[0]));
		this.targetTasks.addTask(1, new HurtByTargetGoal(this, true, PlayerEntity.class));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 100 + this.rand.nextInt(100);
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
	public void setPosition(double x, double y, double z)
	{
		super.setPosition(x, y, z);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaSheep.FED, true);
		this.dataManager.register(EntityAnimaniaSheep.HANDFED, false);
		this.dataManager.register(EntityAnimaniaSheep.WATERED, true);
		this.dataManager.register(EntityAnimaniaSheep.RIVAL_UNIQUE_ID, Optional.<UUID> absent());
		this.dataManager.register(EntityAnimaniaSheep.SHEARED, false);
		this.dataManager.register(EntityAnimaniaSheep.SHEARED_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.woolRegrowthTimer + this.rand.nextInt(500)));
		this.dataManager.register(EntityAnimaniaSheep.SLEEPING, false);
		this.dataManager.register(EntityAnimaniaSheep.SLEEPTIMER, Float.valueOf(0.0F));
		this.dataManager.register(EntityAnimaniaSheep.DYE_COLOR, Integer.valueOf(EnumDyeColor.WHITE.getMetadata()));
		this.dataManager.register(INTERACTED, false);

		if (this instanceof EntityRamFriesian || this instanceof EntityEweFriesian || this instanceof EntityLambFriesian)
		{
			this.dataManager.register(EntityAnimaniaSheep.COLOR_NUM, Integer.valueOf(rand.nextInt(3)));
		} else if (this instanceof EntityRamDorset || this instanceof EntityEweDorset || this instanceof EntityLambDorset)
		{
			this.dataManager.register(EntityAnimaniaSheep.COLOR_NUM, Integer.valueOf(rand.nextInt(2)));
		} else if (this instanceof EntityRamMerino || this instanceof EntityEweMerino || this instanceof EntityLambMerino)
		{
			this.dataManager.register(EntityAnimaniaSheep.COLOR_NUM, Integer.valueOf(rand.nextInt(2)));
		} else if (this instanceof EntityRamSuffolk || this instanceof EntityEweSuffolk || this instanceof EntityLambSuffolk)
		{
			this.dataManager.register(EntityAnimaniaSheep.COLOR_NUM, Integer.valueOf(rand.nextInt(2)));
		} else
		{
			this.dataManager.register(EntityAnimaniaSheep.COLOR_NUM, 0);
		}

		this.dataManager.register(EntityAnimaniaSheep.AGE, Integer.valueOf(0));

	}

	@Override
	public EntityAnimaniaSheep createChild(AgeableEntity ageable)
	{
		return null;
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityLambBase ? null : this.sheepType.isPrime ? new ResourceLocation("farm/" + Animania.MODID, "sheep_prime") : new ResourceLocation("farm/" + Animania.MODID, "sheep_regular");
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
			this.dataManager.set(EntityAnimaniaSheep.SHEARED, true);
			this.setWoolRegrowthTimer(AnimaniaConfig.careAndFeeding.woolRegrowthTimer + this.rand.nextInt(500));
		} else
			this.dataManager.set(EntityAnimaniaSheep.SHEARED, false);
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

	public int getWoolRegrowthTimer()
	{
		return this.getIntFromDataManager(SHEARED_TIMER);
	}

	public void setWoolRegrowthTimer(int time)
	{
		this.dataManager.set(EntityAnimaniaSheep.SHEARED_TIMER, Integer.valueOf(time));
	}

	public int getDyeColorNum()
	{
		return this.getIntFromDataManager(EntityAnimaniaSheep.DYE_COLOR);
	}

	public void setDyeColorNum(int col)
	{
		this.dataManager.set(EntityAnimaniaSheep.DYE_COLOR, new Integer(col));
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
	public void eatGrassBonus()
	{

	}

	public boolean isDyeable()
	{
		return false;
	}

	public EnumDyeColor getDyeColor()
	{
		if (color == null)
		{
			color = EnumDyeColor.byMetadata(this.getDyeColorNum());
		}

		return color;
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

		if (stack.getItem() instanceof ItemShears && !this.getSheared() && !this.isChild())
		{
			if (!this.level.isRemote)
			{
				this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
			}
			player.swingArm(hand);
			if (this.getSleeping())
			{
				this.setSleeping(false);
			}
		}

		if (stack.getItem() instanceof ItemDye && !this.isChild() && !this.getSheared())
		{
			if (isDyeable())
			{
				EnumDyeColor col = EnumDyeColor.byDyeDamage(stack.getItemDamage());
				if (this.getDyeColor() != col)
				{
					if (!player.isCreative())
						stack.shrink(1);

					this.color = col;
					this.setDyeColorNum(col.getMetadata());
					return true;
				}
				return true;
			} else
				return false;
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

	@Override
	public void setInLove(PlayerEntity player)
	{
		if (!this.getSleeping())
			this.level.broadcastEntityEvent(this, (byte) 18);
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return mateable && (stack != ItemStack.EMPTY && this.isSheepBreedingItem(stack));
	}

	private boolean isSheepBreedingItem(ItemStack itemIn)
	{
		return AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, itemIn) || itemIn.getItem() == Item.getItemFromBlock(Blocks.YELLOW_FLOWER) || itemIn.getItem() == Item.getItemFromBlock(Blocks.RED_FLOWER);
	}

	@Override
	public void writeEntityToNBT(CompoundTag compound)
	{
		super.writeEntityToNBT(compound);

		compound.putBoolean("Sheared", this.getSheared());
		compound.putInteger("ColorNumber", getColorNumber());
		compound.putInteger("DyeColor", this.getDyeColorNum());

		GenericBehavior.writeCommonNBT(compound, this);

	}

	@Override
	public void readEntityFromNBT(CompoundTag compound)
	{
		super.readEntityFromNBT(compound);

		this.setColorNumber(compound.getInteger("ColorNumber"));
		this.setSheared(compound.getBoolean("Sheared"));
		this.setDyeColorNum(compound.getInteger("DyeColor"));

		GenericBehavior.readCommonNBT(compound, this);
	}

	@Override
	public EntityDataAccessor<Integer> getAgeParam()
	{
		return AGE;
	}

	public int getColorNumber()
	{
		return this.getIntFromDataManager(COLOR_NUM);
	}

	public void setColorNumber(int color)
	{
		this.dataManager.set(COLOR_NUM, Integer.valueOf(color));
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.sheepType, this.gender));
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
	public boolean isShearable(ItemStack item, IBlockAccess level, BlockPos pos)
	{
		if (!this.getSheared() && !this.isChild())
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess level, BlockPos pos, int fortune)
	{
		return null;
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
		return sheepType;
	}

	@Override
	public Entity convertToVanilla()
	{
		SheepEntity entity = new SheepEntity(this.level);
		entity.setPosition(this.getX(), this.getY(), this.getZ());
		if (entity.hasCustomName())
			entity.setCustomNameTag(this.getCustomNameTag());
		return entity;
	}

}
