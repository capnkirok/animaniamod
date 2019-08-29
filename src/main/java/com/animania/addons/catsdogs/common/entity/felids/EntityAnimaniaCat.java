package com.animania.addons.catsdogs.common.entity.felids;

import java.util.Set;

import com.animania.addons.catsdogs.common.entity.generic.ai.GenericAISitIdle;
import com.animania.addons.catsdogs.config.CatsDogsConfig;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.chickens.EntityChickBase;
import com.animania.common.entities.generic.GenericBehavior;
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
import com.animania.common.entities.peacocks.EntityPeachickBase;
import com.animania.common.entities.rodents.EntityFerretBase;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaCat extends EntityTameable implements IAnimaniaAnimalBase
{

	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
//	protected static final DataParameter<Boolean> TAMED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HANDFED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
//	protected static final DataParameter<Boolean> SITTING = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityAnimaniaCat.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float>createKey(EntityAnimaniaCat.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> INTERACTED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCat.class, DataSerializers.BOOLEAN);
	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(CatsDogsConfig.catsdogs.catFood));

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

	}

	@Override
	protected void initEntityAI()
	{
		this.aiSit = new GenericAISit(this);
		this.tasks.addTask(0, new EntityAISwimming(this));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.tasks.addTask(1, new GenericAIFindWater<EntityAnimaniaCat>(this, 1.0D, entityAIEatGrass, EntityAnimaniaCat.class, true));
			this.tasks.addTask(3, new GenericAIFindFood<EntityAnimaniaCat>(this, 1.0D, entityAIEatGrass, false));
		}
		this.tasks.addTask(4, this.aiSit);
		this.tasks.addTask(5, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(6, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(7, new GenericAIFollowOwner<EntityAnimaniaCat>(this, 1.5D, 5.0F, 30.0F));
		this.tasks.addTask(8, new GenericAIPanic<EntityAnimaniaCat>(this, 1.5D));
		this.tasks.addTask(10, new GenericAITempt<EntityAnimaniaCat>(this, 1.2D, false, TEMPTATION_ITEMS)); // TODO
		this.tasks.addTask(12, new GenericAIWanderAvoidWater(this, 1.2D));
		this.tasks.addTask(13, new GenericAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(14, new GenericAILookIdle<EntityAnimaniaCat>(this));
		this.tasks.addTask(14, new GenericAISitIdle(this));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.tasks.addTask(14, new GenericAISleep<EntityAnimaniaCat>(this, 0.8, AnimaniaHelper.getBlock(CatsDogsConfig.catsdogs.catBed), AnimaniaHelper.getBlock(CatsDogsConfig.catsdogs.catBed2), EntityAnimaniaCat.class));
		}
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.isTamed())
		{
			this.targetTasks.addTask(4, new GenericAITargetNonTamed(this, EntityAnimal.class, false, (entity) -> entity instanceof EntityFerretBase || entity instanceof EntityHamster || entity instanceof EntityChickBase || entity instanceof EntityPeachickBase || entity instanceof EntitySilverfish || entity instanceof EntityFrogs || entity instanceof EntityToad));
		}
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
//		this.dataManager.register(TAMED, false);
//		this.dataManager.register(SITTING, false);
		this.dataManager.register(SLEEPING, false);
		this.dataManager.register(HANDFED, false);
		this.dataManager.register(AGE, Integer.valueOf(0));
		this.dataManager.register(SLEEPTIMER, Float.valueOf(0.0F));
		this.dataManager.register(INTERACTED, false);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setBoolean("IsTamed", this.isTamed());
		compound.setBoolean("IsSitting", this.isSitting());
		
		GenericBehavior.writeCommonNBT(compound, this);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
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
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2.5F);
	}

	@Override
	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	public int getAge()
	{
		return this.getIntFromDataManager(AGE);
	}

	public void setAge(int age)
	{
		this.dataManager.set(AGE, Integer.valueOf(age));
	}

	public boolean getHandFed()
	{
		return this.getBoolFromDataManager(HANDFED);
	}

	public void setHandFed(boolean handfed)
	{
		this.dataManager.set(HANDFED, Boolean.valueOf(handfed));
	}

	public boolean getSleeping()
	{
		return this.getBoolFromDataManager(SLEEPING);
	}

	public void setSleeping(boolean flag)
	{
		if (flag)
		{
			this.dataManager.set(SLEEPING, true);
		}
		else
		{
			this.dataManager.set(SLEEPING, false);
		}
	}

	public Float getSleepTimer()
	{
		return this.getFloatFromDataManager(SLEEPTIMER);
	}

	public void setSleepTimer(Float timer)
	{
		this.dataManager.set(SLEEPTIMER, Float.valueOf(timer));
	}

//	public boolean getIsTamed()
//	{
//		return this.getBoolFromDataManager(TAMED);
//	}
//
//	public void setIsTamed(boolean tamed)
//	{
//		this.dataManager.set(TAMED, Boolean.valueOf(tamed));
//	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		return GenericBehavior.interactCommon(this, player, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
	{

	}

	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return TEMPTATION_ITEMS.contains(stack.getItem());
	}

	public boolean getFed()
	{
		return this.getBoolFromDataManager(FED);
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(FED, true);
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		}
		else
			this.dataManager.set(FED, false);
	}

	public boolean getWatered()
	{
		return this.getBoolFromDataManager(WATERED);
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(WATERED, true);
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		}
		else
			this.dataManager.set(WATERED, false);
	}

//	@Override
//	public boolean isSitting()
//	{
//		return this.getBoolFromDataManager(SITTING);
//	}
//
//	@Override
//	public void setSitting(boolean flag)
//	{
//		this.dataManager.set(SITTING, Boolean.valueOf(flag));
//	}

	@Override
	public void onLivingUpdate()
	{
		if (this.isSitting() || this.isRiding())
		{
			if (this.getRidingEntity() != null)
				this.rotationYaw = this.getRidingEntity().rotationYaw;
			this.navigator.clearPath();
			this.navigator.setSpeed(0);
		}

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
			{
				this.blinkTimer = 100 + this.rand.nextInt(100);
			}
		}

		GenericBehavior.livingUpdateCommon(this);

		super.onLivingUpdate();
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
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
	public boolean canBeLeashedTo(EntityPlayer player)
	{
		return true;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.5F);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
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
	public Set<Item> getFoodItems()
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
	public void setInteracted(boolean interacted)
	{
		this.dataManager.set(INTERACTED, interacted);
	}

	@Override
	public boolean getInteracted()
	{
		return this.getBoolFromDataManager(INTERACTED);
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
}
