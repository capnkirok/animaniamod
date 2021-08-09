package com.animania.addons.catsdogs.common.entity.felids;

import java.util.Set;

import javax.annotation.Nullable;

import com.animania.addons.catsdogs.common.entity.felids.ai.CatAttackGoal;
import com.animania.addons.catsdogs.config.CatsDogsConfig;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.api.interfaces.IConvertable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIAvoidEntity;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAIFollowOwner;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISit;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAITargetNonTamed;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaCat extends OcelotEntity implements IAnimaniaAnimalBase, IConvertable
{

	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean> createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean> createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	// protected static final DataParameter<Boolean> TAMED =
	// EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class,
	// DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HANDFED = EntityDataManager.<Boolean> createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	// protected static final DataParameter<Boolean> SITTING =
	// EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class,
	// DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer> createKey(EntityAnimaniaCat.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean> createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float> createKey(EntityAnimaniaCat.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> INTERACTED = EntityDataManager.<Boolean> createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(CatsDogsConfig.catsdogs.catFood));

	protected int fedTimer;
	protected int wateredTimer;
	protected int happyTimer;
	protected int tamedTimer;
	public int blinkTimer;
	public int eatTimer;
	public GenericAIEatGrass<EntityAnimaniaCat> entityAIEatGrass;
	protected int damageTimer;
	public CatType type;
	public EntityGender gender;
	private Goal avoidEntity;

	public EntityAnimaniaCat(World worldIn)
	{
		super(worldIn);
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + this.rand.nextInt(80);
		this.enablePersistence();
		this.entityAIEatGrass = new GenericAIEatGrass<EntityAnimaniaCat>(this, false);
		this.tasks.addTask(11, this.entityAIEatGrass);

		this.initAI();
	}

	protected void initAI()
	{
		this.aiSit = new GenericAISit(this);
		this.tasks.addTask(0, new SwimmingGoal(this));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.tasks.addTask(1, new GenericAIFindWater<EntityAnimaniaCat>(this, 1.0D, entityAIEatGrass, EntityAnimaniaCat.class, true));
			this.tasks.addTask(3, new GenericAIFindFood<EntityAnimaniaCat>(this, 1.0D, entityAIEatGrass, false));
		}
		this.tasks.addTask(4, this.aiSit);
		this.tasks.addTask(5, new LeapAtTargetGoal(this, 0.4F));
		this.tasks.addTask(6, new CatAttackGoal(this));
		this.tasks.addTask(7, new GenericAIFollowOwner<EntityAnimaniaCat>(this, 1.5D, 5.0F, 30.0F));
		this.tasks.addTask(8, new GenericAIPanic<EntityAnimaniaCat>(this, 1.5D));
		this.tasks.addTask(3, new GenericAITempt<EntityAnimaniaCat>(this, 0.6D, true, TEMPTATION_ITEMS));
		this.tasks.addTask(12, new GenericAIWanderAvoidWater(this, 1.2D));
		this.tasks.addTask(13, new GenericAIWatchClosest(this, PlayerEntity.class, 6.0F));
		this.tasks.addTask(14, new GenericAILookIdle<EntityAnimaniaCat>(this));
		// this.tasks.addTask(14, new GenericAISitIdle(this));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.tasks.addTask(14, new GenericAISleep<EntityAnimaniaCat>(this, 0.8, AnimaniaHelper.getBlock(CatsDogsConfig.catsdogs.catBed), AnimaniaHelper.getBlock(CatsDogsConfig.catsdogs.catBed2), EntityAnimaniaCat.class));
		}
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.isTamed())
		{
			AddonInjectionHandler.runInjection("farm", "attackChicks", null, this);
			AddonInjectionHandler.runInjection("extra", "attackFrogs", null, this);
			AddonInjectionHandler.runInjection("extra", "attackPeachicks", null, this);
			AddonInjectionHandler.runInjection("extra", "attackRodents", null, this);

			this.targetTasks.addTask(4, new GenericAITargetNonTamed(this, AnimalEntity.class, false, (entity) -> entity instanceof SilverfishEntity));
		}
		this.tasks.taskEntries.removeIf(task -> task.action instanceof OcelotSitGoal);
	}

	@Override
	protected void initEntityAI()
	{
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.5D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(FED, true);
		this.dataManager.register(WATERED, true);
		// this.dataManager.register(TAMED, false);
		// this.dataManager.register(SITTING, false);
		this.dataManager.register(SLEEPING, false);
		this.dataManager.register(HANDFED, false);
		this.dataManager.register(AGE, Integer.valueOf(0));
		this.dataManager.register(SLEEPTIMER, Float.valueOf(0.0F));
		this.dataManager.register(INTERACTED, false);
	}

	@Override
	public void writeEntityToNBT(CompoundNBT compound)
	{
		super.writeEntityToNBT(compound);
		compound.putBoolean("IsTamed", this.isTamed());
		compound.putBoolean("IsSitting", this.isSitting());

		GenericBehavior.writeCommonNBT(compound, this);
	}

	@Override
	public void readEntityFromNBT(CompoundNBT compound)
	{
		super.readEntityFromNBT(compound);
		this.setTamed(compound.getBoolean("IsTamed"));
		this.setSitting(compound.getBoolean("IsSitting"));

		GenericBehavior.readCommonNBT(compound, this);
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
	public void setInLove(PlayerEntity player)
	{
		this.level.broadcastEntityEvent(this, (byte) 18);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2.5F);
	}

	@Override
	public void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
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
	public boolean processInteract(PlayerEntity player, EnumHand hand)
	{
		return GenericBehavior.interactCommon(this, player, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
	{

	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return null;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, stack);
	}

	@Override
	public DataParameter<Boolean> getFedParam()
	{
		return FED;
	}

	@Override
	public DataParameter<Boolean> getWateredParam()
	{
		return WATERED;
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.isSitting() || this.isPassenger())
		{
			if (this.getRidingEntity() != null)
				this.rotationYaw = this.getRidingEntity().rotationYaw;
			this.navigator.stop();
			this.navigator.setSpeed(0);
		}

		GenericBehavior.livingUpdateCommon(this);

		super.onLivingUpdate();
	}

	@Override
	protected void setupTamedAI()
	{
		if (this.avoidEntity == null)
		{
			this.avoidEntity = new GenericAIAvoidEntity<PlayerEntity>(this, PlayerEntity.class, 16.0F, 0.8D, 1.33D);
		}

		this.tasks.removeTask(this.avoidEntity);

		if (!this.isTamed())
		{
			this.tasks.addTask(4, this.avoidEntity);
		}
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
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
	protected Item getDropItem()
	{
		return null;
	}

	@Override
	public boolean canBeLeashedTo(PlayerEntity player)
	{
		return true;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.5F);
	}

	@Override
	public OcelotEntity createChild(AgeableEntity ageable)
	{
		return null;
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
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.type, this.gender));
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
		return null;
	}

	@Override
	public int getBlinkTimer()
	{
		return this.blinkTimer;
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
	public void setBlinkTimer(int i)
	{
		this.blinkTimer = i;
	}

	@Override
	public AnimaniaType getAnimalType()
	{
		return type;
	}

	@Override
	public Entity convertToVanilla()
	{
		OcelotEntity entity = new OcelotEntity(this.level);
		entity.setPosition(this.getX(), this.getY(), this.getZ());
		if (entity.hasCustomName())
			entity.setCustomNameTag(this.getCustomNameTag());
		return entity;
	}
}
