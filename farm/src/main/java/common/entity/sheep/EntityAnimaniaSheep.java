package common.entity.sheep;

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
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.api.interfaces.IConvertable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.*;
import com.animania.common.handler.AddonHandler;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import config.FarmConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemShears;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

	private static final String[] SHEEP_TEXTURES = { "black", "white", "brown" };

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
		this.entityAIEatGrass = new GenericAIEatGrass<>(this);
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.goalSelector.addGoal(2, new GenericAIFindWater<>(this, 1.0D, this.entityAIEatGrass, EntityAnimaniaSheep.class));
			this.goalSelector.addGoal(3, new GenericAIFindFood<>(this, 1.0D, this.entityAIEatGrass, true));
		}
		this.goalSelector.addGoal(4, new GenericAIWanderAvoidWater(this, 1.0D));
		this.goalSelector.addGoal(5, new SwimmingGoal(this));
		this.goalSelector.addGoal(6, new GenericAIPanic<>(this, 2.2D));
		this.goalSelector.addGoal(7, new GenericAITempt<>(this, 1.25D, false, EntityAnimaniaSheep.TEMPTATION_ITEMS));
		this.goalSelector.addGoal(6, new GenericAITempt<EntityAnimaniaSheep>(this, 1.25D, new ItemStack(Blocks.YELLOW_FLOWER), false));
		this.goalSelector.addGoal(6, new GenericAITempt<EntityAnimaniaSheep>(this, 1.25D, new ItemStack(Blocks.RED_FLOWER), false));
		this.goalSelector.addGoal(8, this.entityAIEatGrass);
		this.goalSelector.addGoal(9, new GenericAIAvoidEntity<WolfEntity>(this, WolfEntity.class, 24.0F, 2.0D, 2.2D));
		this.goalSelector.addGoal(10, new GenericAIWatchClosest(this, Player.class, 6.0F));
		this.goalSelector.addGoal(11, new GenericAILookIdle<>(this));
		this.goalSelector.addGoal(12, new GenericAIFindSaltLick<>(this, 1.0, this.entityAIEatGrass));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.goalSelector.addGoal(11, new GenericAISleep<EntityAnimaniaSheep>(this, 0.8, AnimaniaHelper.getBlock(FarmConfig.settings.sheepBed), AnimaniaHelper.getBlock(FarmConfig.settings.sheepBed2), EntityAnimaniaSheep.class));
		}
		if (AddonHandler.isAddonLoaded("catsdogs"))
		{
			AddonInjectionHandler.runInjection("catsdogs", "addHerdingBehavior", null, this, 1);
		}
		this.targetSelector.addTask(0, new HurtByTargetGoal(this, false, new Class[0]));
		this.targetSelector.addTask(1, new HurtByTargetGoal(this, true, Player.class));
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
		this.entityData.register(EntityAnimaniaSheep.FED, true);
		this.entityData.register(EntityAnimaniaSheep.HANDFED, false);
		this.entityData.register(EntityAnimaniaSheep.WATERED, true);
		this.entityData.register(EntityAnimaniaSheep.RIVAL_UNIQUE_ID, Optional.<UUID> absent());
		this.entityData.register(EntityAnimaniaSheep.SHEARED, false);
		this.entityData.register(EntityAnimaniaSheep.SHEARED_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.woolRegrowthTimer + this.rand.nextInt(500)));
		this.entityData.register(EntityAnimaniaSheep.SLEEPING, false);
		this.entityData.register(EntityAnimaniaSheep.SLEEPTIMER, Float.valueOf(0.0F));
		this.entityData.register(EntityAnimaniaSheep.DYE_COLOR, Integer.valueOf(EnumDyeColor.WHITE.getMetadata()));
		this.entityData.register(INTERACTED, false);

		if (this instanceof EntityRamFriesian || this instanceof EntityEweFriesian || this instanceof EntityLambFriesian)
		{
			this.entityData.register(EntityAnimaniaSheep.COLOR_NUM, Integer.valueOf(rand.nextInt(3)));
		}
		else if (this instanceof EntityRamDorset || this instanceof EntityEweDorset || this instanceof EntityLambDorset)
		{
			this.entityData.register(EntityAnimaniaSheep.COLOR_NUM, Integer.valueOf(rand.nextInt(2)));
		}
		else if (this instanceof EntityRamMerino || this instanceof EntityEweMerino || this instanceof EntityLambMerino)
		{
			this.entityData.register(EntityAnimaniaSheep.COLOR_NUM, Integer.valueOf(rand.nextInt(2)));
		}
		else if (this instanceof EntityRamSuffolk || this instanceof EntityEweSuffolk || this instanceof EntityLambSuffolk)
		{
			this.entityData.register(EntityAnimaniaSheep.COLOR_NUM, Integer.valueOf(rand.nextInt(2)));
		}
		else
		{
			this.entityData.register(EntityAnimaniaSheep.COLOR_NUM, 0);
		}

		this.entityData.register(EntityAnimaniaSheep.AGE, Integer.valueOf(0));

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
			this.entityData.set(EntityAnimaniaSheep.SHEARED, true);
			this.setWoolRegrowthTimer(AnimaniaConfig.careAndFeeding.woolRegrowthTimer + this.rand.nextInt(500));
		}
		else
			this.entityData.set(EntityAnimaniaSheep.SHEARED, false);
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
		this.entityData.set(EntityAnimaniaSheep.SHEARED_TIMER, Integer.valueOf(time));
	}

	public int getDyeColorNum()
	{
		return this.getIntFromDataManager(EntityAnimaniaSheep.DYE_COLOR);
	}

	public void setDyeColorNum(int col)
	{
		this.entityData.set(EntityAnimaniaSheep.DYE_COLOR, Integer.valueOf(col));
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
		if (this.color == null)
		{
			this.color = EnumDyeColor.byMetadata(this.getDyeColorNum());
		}

		return this.color;
	}

	@Override
	public void onLivingUpdate()
	{
		if (!this.hasRemovedBOP && ModList.get().isLoaded("biomesoplenty"))
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
				}
				catch (Exception e)
				{
				}
			}

			this.hasRemovedBOP = true;
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
	public boolean processInteract(Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		Player Player = player;

		if (stack.getItem() instanceof ItemShears && !this.getSheared() && !this.isChild())
		{
			if (!this.level.isClientSide)
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
			if (this.isDyeable())
			{
				EnumDyeColor col = EnumDyeColor.byDyeDamage(stack.getItemDamage());
				if (this.getDyeColor() != col)
				{
					if (!player.isCreative())
						stack.shrink(1);

					this.color = col;
					this.setDyeColorNum(col.getMetadata());
				}
				return true;
			}
			else
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
	public void setInLove(Player player)
	{
		if (!this.getSleeping())
			this.level.broadcastEntityEvent(this, (byte) 18);
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return this.mateable && stack != ItemStack.EMPTY && this.isSheepBreedingItem(stack);
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
		compound.putInt("ColorNumber", this.getColorNumber());
		compound.putInt("DyeColor", this.getDyeColorNum());

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
		this.entityData.set(COLOR_NUM, Integer.valueOf(color));
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.sheepType, this.gender));
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
	public boolean isShearable(ItemStack item, IBlockAccess level, BlockPos pos)
	{
		if (!this.getSheared() && !this.isChild())
		{
			return true;
		}
		else
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
	public java.util.Optional<BlockPos> getSleepingPos()
	{
		// TODO Auto-generated method stub
		return null;
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
		return this.sheepType;
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
