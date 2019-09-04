package com.animania.common.entities.cows;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.common.entities.cows.CowFriesian.EntityBullFriesian;
import com.animania.common.entities.cows.CowFriesian.EntityCalfFriesian;
import com.animania.common.entities.cows.CowFriesian.EntityCowFriesian;
import com.animania.common.entities.cows.CowHolstein.EntityBullHolstein;
import com.animania.common.entities.cows.CowHolstein.EntityCalfHolstein;
import com.animania.common.entities.cows.CowHolstein.EntityCowHolstein;
import com.animania.common.entities.cows.CowMooshroom.EntityBullMooshroom;
import com.animania.common.entities.cows.CowMooshroom.EntityCowMooshroom;
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
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaCow extends EntityCow implements IAnimaniaAnimalBase
{
	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.cowFood));
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityAnimaniaCow.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCow.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCow.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HANDFED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCow.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean>createKey(EntityAnimaniaCow.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float>createKey(EntityAnimaniaCow.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> INTERACTED = EntityDataManager.<Boolean>createKey(EntityAnimaniaCow.class, DataSerializers.BOOLEAN);

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

	public EntityAnimaniaCow(World worldIn)
	{
		super(worldIn);
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new GenericAIEatGrass<EntityAnimaniaCow>(this);
		this.tasks.addTask(1, new GenericAIPanic<EntityAnimaniaCow>(this, 2.0D));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.tasks.addTask(2, new GenericAIFindWater<EntityAnimaniaCow>(this, 1.0D, entityAIEatGrass, EntityAnimaniaCow.class));
			this.tasks.addTask(3, new GenericAIFindFood<EntityAnimaniaCow>(this, 1.0, entityAIEatGrass, true));
		}
		this.tasks.addTask(4, new GenericAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(5, new EntityAISwimming(this));
		this.tasks.addTask(7, new GenericAITempt<EntityAnimaniaCow>(this, 1.25D, false, EntityAnimaniaCow.TEMPTATION_ITEMS));
		this.tasks.addTask(6, new GenericAITempt<EntityAnimaniaCow>(this, 1.25D, Item.getItemFromBlock(Blocks.YELLOW_FLOWER), false));
		this.tasks.addTask(6, new GenericAITempt<EntityAnimaniaCow>(this, 1.25D, Item.getItemFromBlock(Blocks.RED_FLOWER), false));
		this.tasks.addTask(8, this.entityAIEatGrass);
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.tasks.addTask(9, new GenericAISleep<EntityAnimaniaCow>(this, 0.8, AnimaniaHelper.getBlock(AnimaniaConfig.careAndFeeding.cowBed), AnimaniaHelper.getBlock(AnimaniaConfig.careAndFeeding.cowBed2), EntityAnimaniaCow.class));
		}
		this.tasks.addTask(10, new GenericAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(11, new GenericAILookIdle<EntityAnimaniaCow>(this));
		this.tasks.addTask(12, new GenericAIFindSaltLick<EntityAnimaniaCow>(this, 1.0, entityAIEatGrass));
		this.targetTasks.addTask(14, new EntityAIHurtByTarget(this, false, new Class[0]));
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers)
		{
			this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, EntityPlayer.class));
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
		this.dataManager.register(EntityAnimaniaCow.FED, true);
		this.dataManager.register(INTERACTED, false);
		this.dataManager.register(EntityAnimaniaCow.HANDFED, false);
		this.dataManager.register(EntityAnimaniaCow.WATERED, true);
		this.dataManager.register(EntityAnimaniaCow.SLEEPING, false);
		this.dataManager.register(EntityAnimaniaCow.SLEEPTIMER, Float.valueOf(0.0F));
		this.dataManager.register(EntityAnimaniaCow.AGE, Integer.valueOf(0));
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
		return this instanceof EntityCalfBase ? null : this.cowType.isPrime ? new ResourceLocation(Animania.MODID, "cow_prime") : new ResourceLocation(Animania.MODID, "cow_regular");
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateCommon(this);

		if (this.getCustomNameTag().toLowerCase().trim().equals("purp") && (this instanceof EntityCowFriesian || this instanceof EntityBullFriesian || this instanceof EntityCowHolstein || this instanceof EntityBullHolstein || this instanceof EntityCalfFriesian || this instanceof EntityCalfHolstein))
		{
			this.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 4, 2, false, false));
			if (!this.isWet() && !this.isInWater())
				this.setOnFireFromLava();
		}

		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (this instanceof EntityBullBase)
		{
			EntityBullBase ebb = (EntityBullBase) this;
			if (ebb.getFighting())
			{
				return true;
			}
		}
		if (stack != ItemStack.EMPTY && AnimaniaHelper.isEmptyFluidContainer(stack))
		{
			return true;
		}
		else if (stack != ItemStack.EMPTY && (this instanceof EntityCowMooshroom || this instanceof EntityBullMooshroom) && stack.getItem() instanceof ItemShears && this.getGrowingAge() >= 0) // onSheared
		{
			this.setDead();
			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY + (double) (this.height / 2.0F), this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);

			if (!this.world.isRemote)
			{
				EntityAnimaniaCow entitycow = null;
				
				if (this instanceof EntityCowMooshroom)
					entitycow = new EntityCowFriesian(this.world);
				else
					entitycow = new EntityBullFriesian(this.world);
				
				entitycow.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
				entitycow.setHealth(this.getHealth());
				entitycow.renderYawOffset = this.renderYawOffset;
				if (this.hasCustomName())
				{
					entitycow.setCustomNameTag(this.getCustomNameTag());
				}
				this.world.spawnEntity(entitycow);

				for (int i = 0; i < 5; ++i)
				{
					this.world.spawnEntity(new EntityItem(this.world, this.posX, this.posY + (double) this.height, this.posZ, new ItemStack(Blocks.RED_MUSHROOM)));
				}

				stack.damageItem(1, player);
				this.playSound(SoundEvents.ENTITY_MOOSHROOM_SHEAR, 1.0F, 1.0F);
			}
			return true;
		}
		
		return GenericBehavior.interactCommon(this, entityplayer, hand, this.entityAIEatGrass) ? true : super.processInteract(player, hand);
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

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return mateable && (stack != ItemStack.EMPTY && this.isCowBreedingItem(stack.getItem()));
	}

	private boolean isCowBreedingItem(Item itemIn)
	{
		return TEMPTATION_ITEMS.contains(itemIn) || itemIn == Item.getItemFromBlock(Blocks.YELLOW_FLOWER) || itemIn == Item.getItemFromBlock(Blocks.RED_FLOWER);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);

		GenericBehavior.writeCommonNBT(compound, this);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
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
		return cowType;
	}

	@Override
	public DataParameter<Boolean> getHandFedParam()
	{
		return HANDFED;
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
	public DataParameter<Boolean> getInteractedParam()
	{
		return INTERACTED;
	}

	@Override
	public DataParameter<Integer> getAgeParam()
	{
		return AGE;
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

}
