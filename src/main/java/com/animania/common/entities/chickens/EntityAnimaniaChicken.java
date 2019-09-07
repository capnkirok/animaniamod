package com.animania.common.entities.chickens;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.common.ModSoundEvents;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.entities.chickens.ai.EntityAIWatchClosestFromSide;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAISwimmingSmallCreatures;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityAnimaniaChicken extends EntityChicken implements IAnimaniaAnimalBase
{
	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.chickenFood));
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaChicken.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaChicken.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityAnimaniaChicken.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityAnimaniaChicken.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean>createKey(EntityAnimaniaChicken.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HANDFED = EntityDataManager.<Boolean>createKey(EntityAnimaniaChicken.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> INTERACTED = EntityDataManager.<Boolean>createKey(EntityAnimaniaChicken.class, DataSerializers.BOOLEAN);

	public boolean chickenJockey;
	protected ResourceLocation resourceLocation;
	protected ResourceLocation resourceLocationBlink;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;
	private int fedTimer;
	protected int wateredTimer;
	protected int happyTimer;
	public int blinkTimer;
	private int featherTimer;
	protected int damageTimer;
	public ChickenType type;
	public EntityGender gender;
	public int lidCol;
	
	public EntityAnimaniaChicken(World worldIn)
	{
		super(worldIn);
		this.tasks.taskEntries.clear();
		this.tasks.addTask(0, new GenericAISwimmingSmallCreatures(this));
		this.tasks.addTask(1, new GenericAIPanic<EntityAnimaniaChicken>(this, 1.4D));
		if (!AnimaniaConfig.gameRules.ambianceMode) {
			this.tasks.addTask(2, new GenericAIFindWater<EntityAnimaniaChicken>(this, 1.0D, null, EntityAnimaniaChicken.class, true));
			this.tasks.addTask(3, new GenericAIFindFood<EntityAnimaniaChicken>(this, 1.0D, null, true));
		}
		this.tasks.addTask(4, new GenericAITempt<EntityAnimaniaChicken>(this, 1.2D, false, EntityAnimaniaChicken.TEMPTATION_ITEMS));
		this.tasks.addTask(6, new GenericAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosestFromSide(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(11, new GenericAILookIdle<EntityAnimaniaChicken>(this));
		if (AnimaniaConfig.gameRules.animalsSleep) {
			this.tasks.addTask(8, new GenericAISleep<EntityAnimaniaChicken>(this, 0.8, AnimaniaHelper.getBlock(AnimaniaConfig.careAndFeeding.chickenBed), AnimaniaHelper.getBlock(AnimaniaConfig.careAndFeeding.chickenBed2), EntityAnimaniaChicken.class));
		}
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + this.rand.nextInt(80);
		this.featherTimer = AnimaniaConfig.careAndFeeding.featherTimer + rand.nextInt(1000);
		this.enablePersistence();
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

	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		return GenericBehavior.interactCommon(this, player, hand, null) ? true : super.processInteract(player, hand);
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
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityChickBase ? null : this.type.isPrime ? new ResourceLocation(Animania.MODID, "chicken_prime") : new ResourceLocation(Animania.MODID, "chicken_regular");
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.29D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaChicken.FED, true);
		this.dataManager.register(EntityAnimaniaChicken.WATERED, true);
		this.dataManager.register(EntityAnimaniaChicken.AGE, Integer.valueOf(0));
		this.dataManager.register(EntityAnimaniaChicken.SLEEPING, false);
		this.dataManager.register(EntityAnimaniaChicken.HANDFED, false);
		this.dataManager.register(INTERACTED, false);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setBoolean("IsChickenJockey", this.chickenJockey);

		GenericBehavior.writeCommonNBT(nbttagcompound, this);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);

		this.chickenJockey = nbttagcompound.getBoolean("IsChickenJockey");

		
		GenericBehavior.readCommonNBT(nbttagcompound, this);
	}

	@Override
	public DataParameter<Integer> getAgeParam()
	{
		return AGE;
	}

	@Override
	public void onLivingUpdate()
	{

		super.onLivingUpdate();
		
		GenericBehavior.livingUpdateCommon(this);
		
		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float) (this.destPos + ((this.onGround || this.isRiding()) ? -1 : 4) * 0.3D);
		this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

		this.fallDistance = 0;

		if (!this.world.isRemote && !this.isChild() && AnimaniaConfig.gameRules.chickensDropFeathers && !this.isChickenJockey() && --this.featherTimer <= 0)
		{
			this.playSound(ModSoundEvents.chickenCluck2, 0.5F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.dropItem(Items.FEATHER, 1);
			this.featherTimer = AnimaniaConfig.careAndFeeding.featherTimer + rand.nextInt(1000);
		}

		if (!this.onGround && !this.isRiding() && this.wingRotDelta < 1.0F)
			this.wingRotDelta = 1.0F;

		this.wingRotDelta = (float) (this.wingRotDelta * 0.9D);

		if (!this.onGround && !this.isRiding() && this.motionY < 0.0D)
			this.motionY *= 0.6D;

		this.wingRotation += this.wingRotDelta * 2.0F;
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
	public DataParameter<Boolean> getSleepingParam()
	{
		return SLEEPING;
	}

	@Override
	public DataParameter<Boolean> getHandFedParam()
	{
		return HANDFED;
	}

	protected void fall(float p_70069_1_)
	{
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, ModSoundEvents.chickenCluck1, ModSoundEvents.chickenCluck2, ModSoundEvents.chickenCluck3, ModSoundEvents.chickenCluck4, ModSoundEvents.chickenCluck5, ModSoundEvents.chickenCluck6);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return null;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return null;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.10F, 1.4F);
	}

	@Override
	public void playSound(SoundEvent soundIn, float volume, float pitch)
	{
		if (!this.isSilent() && !this.getSleeping())
		{
			this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, soundIn, this.getSoundCategory(), volume, pitch);
		}
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && EntityAnimaniaChicken.TEMPTATION_ITEMS.contains(stack.getItem());
	}

	/**
	 * Get the experience points the entity currently has.
	 */
	@Override
	protected int getExperiencePoints(EntityPlayer player)
	{
		return this.isChickenJockey() ? 10 : super.getExperiencePoints(player);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void updatePassenger(Entity passenger)
	{
		super.updatePassenger(passenger);
		float f = MathHelper.sin(this.renderYawOffset * 0.017453292F);
		float f1 = MathHelper.cos(this.renderYawOffset * 0.017453292F);
		float f2 = 0.1F;
		float f3 = 0.0F;
		passenger.setPosition(this.posX + 0.1F * f, this.posY + this.height * 0.5F + passenger.getYOffset() + 0.0D, this.posZ - 0.1F * f1);

		if (passenger instanceof EntityLivingBase)
			((EntityLivingBase) passenger).renderYawOffset = this.renderYawOffset;
	}

	public boolean isChickenJockey()
	{
		return this.chickenJockey;
	}

	public void setChickenJockey(boolean jockey)
	{
		this.chickenJockey = jockey;
	}

	@Override
	public EntityChicken createChild(EntityAgeable ageable)
	{
		return null;
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
	public Set<Item> getFoodItems()
	{
		return TEMPTATION_ITEMS;
	}

	@Override
	public void setSleepingPos(BlockPos pos)
	{
		
	}

	@Override
	public BlockPos getSleepingPos()
	{
		return null;
	}
	
	@Override
	public Class[] getFoodBlocks()
	{
		return new Class[]{BlockSeeds.class};
	}

	@Override
	public Float getSleepTimer()
	{
		return -100f;
	}

	@Override
	public void setSleepTimer(Float timer)
	{
		
	}

	@Override
	public int getEatTimer()
	{
		return 0;
	}

	@Override
	public void setEatTimer(int i)
	{
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
		return type;
	}

	@Override
	public DataParameter<Float> getSleepTimerParam()
	{
		return null;
	}
}
