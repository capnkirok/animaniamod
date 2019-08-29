package com.animania.common.entities.peacocks;

import java.util.Set;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.common.ModSoundEvents;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.entities.amphibians.EntityAmphibian;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.peacocks.ai.EntityAIWatchClosestFromSide;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityAnimaniaPeacock extends EntityAnimal implements TOPInfoProviderBase, IAnimaniaAnimalBase
{
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaPeacock.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HANDFED = EntityDataManager.<Boolean>createKey(EntityAnimaniaPeacock.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaPeacock.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityAnimaniaPeacock.class, DataSerializers.VARINT);
	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.peacockFood));
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean>createKey(EntityAnimaniaPeacock.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float>createKey(EntityAnimaniaPeacock.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> INTERACTED = EntityDataManager.<Boolean>createKey(EntityAnimaniaPeacock.class, DataSerializers.BOOLEAN);

	protected ResourceLocation resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_blue.png");
	protected ResourceLocation resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_blue_blink.png");
	public float wingRotation;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;
	protected int fedTimer;
	protected int wateredTimer;
	protected int happyTimer;
	public int blinkTimer;
	protected int damageTimer;
	public PeacockType type;
	private int featherCounter;
	protected EntityGender gender;
	public int lidCol;

	public EntityAnimaniaPeacock(World worldIn)
	{
		super(worldIn);

		this.tasks.addTask(0, new EntityAISwimming(this));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.tasks.addTask(1, new GenericAIFindWater<EntityAnimaniaPeacock>(this, 1.0D, null, EntityAnimaniaPeacock.class, true));
			this.tasks.addTask(1, new GenericAIFindFood<EntityAnimaniaPeacock>(this, 1.0D, null, true));
		}
		this.tasks.addTask(2, new GenericAIPanic<EntityAnimaniaPeacock>(this, 1.4D));
		this.tasks.addTask(3, new GenericAITempt<EntityAnimaniaPeacock>(this, 1.2D, false, EntityAnimaniaPeacock.TEMPTATION_ITEMS));
		this.tasks.addTask(4, new GenericAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(5, new EntityAIWatchClosestFromSide(this, EntityPlayer.class, 6.0F));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.tasks.addTask(6, new GenericAISleep<EntityAnimaniaPeacock>(this, 0.8, AnimaniaHelper.getBlock(AnimaniaConfig.careAndFeeding.peacockBed), AnimaniaHelper.getBlock(AnimaniaConfig.careAndFeeding.peacockBed2), EntityAnimaniaPeacock.class));
		}
		this.tasks.addTask(11, new GenericAILookIdle<EntityAnimaniaPeacock>(this));
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers)
		{
			this.tasks.addTask(8, new EntityAILeapAtTarget(this, 0.2F));
			this.tasks.addTask(9, new EntityAIAttackMelee(this, 1.0D, true));
		}
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer * 2 + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer * 2 + this.rand.nextInt(100);
		this.blinkTimer = 80 + this.rand.nextInt(80);
		this.enablePersistence();
		this.happyTimer = 60;
		this.featherCounter = AnimaniaConfig.careAndFeeding.featherTimer;

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

	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void fall(float distance, float damageMultiplier)
	{

	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		return GenericBehavior.interactCommon(this, player, hand, null) ? true : super.processInteract(player, hand);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);

		if (flag)
		{
			this.applyEnchantments(this, entityIn);
		}

		if (entityIn instanceof EntityAmphibian)
		{
			this.setFed(true);
		}

		// Custom Knockback
		if (entityIn instanceof EntityPlayer)
		{
			((EntityLivingBase) entityIn).knockBack(this, 1, this.posX - entityIn.posX, this.posZ - entityIn.posZ);
		}

		return flag;
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
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(7.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaPeacock.FED, true);
		this.dataManager.register(EntityAnimaniaPeacock.HANDFED, false);
		this.dataManager.register(EntityAnimaniaPeacock.WATERED, true);
		this.dataManager.register(EntityAnimaniaPeacock.AGE, Integer.valueOf(0));
		this.dataManager.register(EntityAnimaniaPeacock.SLEEPING, false);
		this.dataManager.register(EntityAnimaniaPeacock.SLEEPTIMER, Float.valueOf(0.0F));
		this.dataManager.register(INTERACTED, false);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		GenericBehavior.writeCommonNBT(nbttagcompound, this);
		
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		GenericBehavior.readCommonNBT(nbttagcompound, this);
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
	public DataParameter<Boolean> getHandFedParam()
	{
		return HANDFED;
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityPeachickBase ? null : new ResourceLocation(Animania.MODID, "peacocks/peacock_" + this.type.toString().toLowerCase());
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

		if (!this.onGround && !this.isRiding() && this.wingRotDelta < 1.0F)
			this.wingRotDelta = 1.0F;

		this.wingRotDelta = (float) (this.wingRotDelta * 0.9D);

		if (!this.onGround && !this.isRiding() && this.motionY < 0.0D)
			this.motionY *= 0.6D;

		this.wingRotation += this.wingRotDelta * 2.0F;

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
				this.blinkTimer = 100 + this.rand.nextInt(100);
		}

		if (this instanceof EntityPeacockBase && AnimaniaConfig.gameRules.chickensDropFeathers)
		{
			this.featherCounter--;
			if (featherCounter <= 0)
			{
				featherCounter = AnimaniaConfig.careAndFeeding.featherTimer;
				Item feather = null;

				switch (this.type)
				{
				case BLUE:
					feather = ItemHandler.peacockFeatherBlue;
					break;
				case CHARCOAL:
					feather = ItemHandler.peacockFeatherCharcoal;
					break;
				case OPAL:
					feather = ItemHandler.peacockFeatherOpal;
					break;
				case PEACH:
					feather = ItemHandler.peacockFeatherPeach;
					break;
				case PURPLE:
					feather = ItemHandler.peacockFeatherPurple;
					break;
				case TAUPE:
					feather = ItemHandler.peacockFeatherTaupe;
					break;
				default:
					feather = ItemHandler.peacockFeatherWhite;
					break;
				}

				if (!world.isRemote)
				{
					ItemStack item = new ItemStack(feather, 1);
					EntityItem entityitem = new EntityItem(world, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, item);
					world.spawnEntity(entityitem);
				}
			}

		}
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

	protected void fall(float p_70069_1_)
	{
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.05F, 1.0F);
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed
	 * it (wheat, carrots or seeds depending on the animal type)
	 */
	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && EntityAnimaniaPeacock.TEMPTATION_ITEMS.contains(stack.getItem());
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

	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return null;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{

		int happy = 0;
		int num = 1;

		if (this.getWatered())
			happy++;
		if (this.getFed())
			happy++;

		if (happy == 2)
			num = 20;
		else if (happy == 1)
			num = 40;
		else
			num = 100;

		int chooser = Animania.RANDOM.nextInt(num);

		if (chooser == 0)
			return ModSoundEvents.peacock1;
		else if (chooser == 1)
			return ModSoundEvents.peacock2;
		else if (chooser == 2)
			return ModSoundEvents.peacock3;
		else if (chooser == 3)
			return ModSoundEvents.peacock4;
		else if (chooser == 4)
			return ModSoundEvents.peacock5;
		else if (chooser == 5)
			return null;
		else if (chooser == 6)
			return ModSoundEvents.peacock7;
		else if (chooser == 7)
			return ModSoundEvents.peacock8;
		else if (chooser == 8)
			return ModSoundEvents.peacock9;
		else if (chooser == 9)
			return ModSoundEvents.peacock10;
		else
			return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		int chooser = Animania.RANDOM.nextInt(2);

		if (chooser == 0)
			return ModSoundEvents.peacockHurt1;
		else
			return ModSoundEvents.peacockHurt2;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		int chooser = Animania.RANDOM.nextInt(2);

		if (chooser == 0)
			return ModSoundEvents.peacockHurt1;
		else
			return ModSoundEvents.peacockHurt2;
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
	public Set<Item> getFoodItems()
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
	public Class[] getFoodBlocks()
	{
		return new Class[] { BlockSeeds.class };
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
		return SLEEPTIMER;
	}
	
}
