package com.animania.common.entities.goats;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.Animania;
import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.generic.ai.GenericAIAvoidEntity;
import com.animania.common.entities.generic.ai.GenericAIAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIEatGrass;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindSaltLick;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAIHurtByTarget;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAISwim;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.entities.generic.ai.GenericAIWatchClosest;
import com.animania.common.entities.interfaces.IAnimaniaAnimalBase;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

public class EntityAnimaniaGoat extends EntitySheep implements IAnimaniaAnimalBase
{

	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.goatFood));
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaGoat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaGoat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityAnimaniaGoat.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Optional<UUID>> RIVAL_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityAnimaniaGoat.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Boolean> SHEARED = EntityDataManager.<Boolean>createKey(EntityAnimaniaGoat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> SHEARED_TIMER = EntityDataManager.<Integer>createKey(EntityAnimaniaGoat.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> SPOOKED = EntityDataManager.<Boolean>createKey(EntityAnimaniaGoat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SPOOKED_TIMER = EntityDataManager.<Float>createKey(EntityAnimaniaGoat.class, DataSerializers.FLOAT);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityAnimaniaGoat.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> HANDFED = EntityDataManager.<Boolean>createKey(EntityAnimaniaGoat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> SLEEPING = EntityDataManager.<Boolean>createKey(EntityAnimaniaGoat.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SLEEPTIMER = EntityDataManager.<Float>createKey(EntityAnimaniaGoat.class, DataSerializers.FLOAT);


	protected int happyTimer;
	public int blinkTimer;
	public int eatTimer;
	protected int fedTimer;
	protected int wateredTimer;
	protected int damageTimer;
	public GoatType goatType;
	public GenericAIEatGrass entityAIEatGrass;
	protected boolean mateable = false;
	protected boolean headbutting = false;
	protected EntityGender gender;
	private boolean hasRemovedBOP;

	public EntityAnimaniaGoat(World worldIn)
	{
		super(worldIn);
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new GenericAIEatGrass(this);
		this.tasks.addTask(3, new GenericAIPanic(this, 1.4D));
		if (!AnimaniaConfig.gameRules.ambianceMode) {
			this.tasks.addTask(2, new GenericAIFindWater<EntityAnimaniaGoat>(this, 1.0D, entityAIEatGrass, EntityAnimaniaGoat.class));
			this.tasks.addTask(3, new GenericAIFindFood<EntityAnimaniaGoat>(this, 1.0D, entityAIEatGrass, true));
		}
		this.tasks.addTask(4, new GenericAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(5, new GenericAISwim(this));
		this.tasks.addTask(7, new GenericAITempt(this, 1.25D, false, EntityAnimaniaGoat.TEMPTATION_ITEMS));
		this.tasks.addTask(8, this.entityAIEatGrass);
		this.tasks.addTask(9, new GenericAIAvoidEntity(this, EntityWolf.class, 20.0F, 2.2D, 2.2D));
		this.tasks.addTask(10, new GenericAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(11, new GenericAIAvoidWater(this));
		this.tasks.addTask(11, new GenericAILookIdle(this));
		this.tasks.addTask(12, new GenericAIFindSaltLick(this, 1.0, entityAIEatGrass));
		if (AnimaniaConfig.gameRules.animalsSleep) {
			this.tasks.addTask(13, new GenericAISleep<EntityAnimaniaGoat>(this, 0.8, AnimaniaHelper.getBlock(AnimaniaConfig.careAndFeeding.goatBed), AnimaniaHelper.getBlock(AnimaniaConfig.careAndFeeding.goatBed2), EntityAnimaniaGoat.class));
		}
		this.targetTasks.addTask(14, new GenericAIHurtByTarget(this, false, new Class[0]));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 100 + this.rand.nextInt(100);
		this.setPosition(0.0D, 0.0D, 0.0D); //TODO Try
		this.enablePersistence();
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void setPosition(double x, double y, double z) //TODO Try
	{
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		float f = this.width / 2.0F;
		float f1 = this.height;
		this.setEntityBoundingBox(new AxisAlignedBB(x - (double)f, y, z - (double)f, x + (double)f, y + (double)f1, z + (double)f));
	}



	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaGoat.FED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaGoat.HANDFED, Boolean.valueOf(false));
		this.dataManager.register(EntityAnimaniaGoat.WATERED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaGoat.MATE_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(EntityAnimaniaGoat.RIVAL_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(EntityAnimaniaGoat.SHEARED, Boolean.valueOf(false));
		this.dataManager.register(EntityAnimaniaGoat.SHEARED_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.woolRegrowthTimer + this.rand.nextInt(500)));
		this.dataManager.register(EntityAnimaniaGoat.SPOOKED, Boolean.valueOf(false));
		this.dataManager.register(EntityAnimaniaGoat.SPOOKED_TIMER, 0.0F);
		this.dataManager.register(EntityAnimaniaGoat.AGE, Integer.valueOf(0));
		this.dataManager.register(EntityAnimaniaGoat.SLEEPING, Boolean.valueOf(false));
		this.dataManager.register(EntityAnimaniaGoat.SLEEPTIMER, Float.valueOf(0.0F));
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityKidBase ? null : this.goatType.isPrime ? new ResourceLocation(Animania.MODID, "goat_prime") : new ResourceLocation(Animania.MODID, "goat_regular");
	}


	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		this.setHandFed(true);
		this.entityAIEatGrass.startExecuting();
		this.eatTimer = 80;
		player.addStat(goatType.getAchievement(), 1);

		if (!player.isCreative())
			stack.shrink(1);;
	}

	public void setSpooked(boolean spooked)
	{
		this.dataManager.set(EntityAnimaniaGoat.SPOOKED, Boolean.valueOf(spooked));
	} 
	
	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return mateable && (stack != ItemStack.EMPTY && this.isGoatBreedingItem(stack.getItem()));
	}

	private boolean isGoatBreedingItem(Item itemIn)
	{
		return TEMPTATION_ITEMS.contains(itemIn) || itemIn == Item.getItemFromBlock(Blocks.YELLOW_FLOWER) || itemIn == Item.getItemFromBlock(Blocks.RED_FLOWER);
	}

	public boolean getSpooked()
	{
		try {
			return (this.getBoolFromDataManager(SPOOKED));
		}
		catch (Exception e) {
			return false;
		}
	}

	public Float getSpookedTimer()
	{
		try {
			return (this.getFloatFromDataManager(SPOOKED_TIMER));
		}
		catch (Exception e) {
			return 0F;
		}
	}

	public int getAge()
	{
		try {
			return (this.getIntFromDataManager(AGE));
		}
		catch (Exception e) {
			return 0;
		}
	}

	public void setAge(int age)
	{
		this.dataManager.set(EntityAnimaniaGoat.AGE, Integer.valueOf(age));
	}

	public void setSpookedTimer(Float timer)
	{
		this.dataManager.set(EntityAnimaniaGoat.SPOOKED_TIMER, Float.valueOf(timer));
	}

	public boolean getSleeping()
	{
		try {
			return (this.getBoolFromDataManager(SLEEPING));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setSleeping(boolean flag)
	{
		if (flag)
		{
			this.dataManager.set(EntityAnimaniaGoat.SLEEPING, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(EntityAnimaniaGoat.SLEEPING, Boolean.valueOf(false));
		}
	}

	public Float getSleepTimer()
	{
		try
		{
			return (this.getFloatFromDataManager(SLEEPTIMER));
		}
		catch (Exception e)
		{
			return 0F;
		}
	}

	public void setSleepTimer(Float timer)
	{
		this.dataManager.set(EntityAnimaniaGoat.SLEEPTIMER, Float.valueOf(timer));
	}


	public boolean getHandFed()
	{
		try {
			return (this.getBoolFromDataManager(HANDFED));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setHandFed(boolean handfed)
	{
		this.dataManager.set(EntityAnimaniaGoat.HANDFED, Boolean.valueOf(handfed));
	}

	@Nullable
	public UUID getMateUniqueId()
	{
		if(mateable)
		{
			try
			{
				UUID id = (UUID) ((Optional) this.dataManager.get(EntityAnimaniaGoat.MATE_UNIQUE_ID)).orNull();
				return id;
			}
			catch(Exception e)
			{
				return null;
			}
		}
		return null;
	}

	public void setMateUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(EntityAnimaniaGoat.MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	public boolean getFed()
	{
		try {
			return (this.getBoolFromDataManager(FED));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityAnimaniaGoat.FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		} else
			this.dataManager.set(EntityAnimaniaGoat.FED, Boolean.valueOf(false));
	}

	public boolean getWatered()
	{
		try {
			return (this.getBoolFromDataManager(WATERED));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setWatered(boolean watered)
	{
		if (watered) {
			this.dataManager.set(EntityAnimaniaGoat.WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		} else {
			this.dataManager.set(EntityAnimaniaGoat.WATERED, Boolean.valueOf(false));
		}
	}

	@Override
	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
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
			if (Loader.isModLoaded("biomesoplenty"))
			{
				Iterator<EntityAITaskEntry> it = this.tasks.taskEntries.iterator();
				while (it.hasNext())
				{
					EntityAITaskEntry entry = it.next();
					EntityAIBase ai = entry.action;
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
				
				hasRemovedBOP = true;
			}
		}
		
		if (this.getAge() == 0) {
			this.setAge(1);
		}

		if (this.world.isRemote)
			this.eatTimer = Math.max(0, this.eatTimer - 1);

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
			{
				this.blinkTimer = 100 + this.rand.nextInt(100);
			}
		}

		if (this.getSpooked()) {

			if (this.getSpookedTimer() == 1.0F) {
				this.setJumping(true);
			} else {
				this.setJumping(false);
			}

			this.getNavigator().clearPath();
			this.setNoAI(true);

			this.setSpookedTimer(this.getSpookedTimer() - 0.01F);

			if (this.getSpookedTimer() <= 0.20F && this.getSpookedTimer() > 0.1F) {
				this.setJumping(true);
			} else if (this.getSpookedTimer() <= 0.0F) {
				this.setSpooked(false);
				this.setSpookedTimer(0.0F);
				this.setNoAI(false);
				this.setJumping(false);

			}
		}

		if (this.fedTimer > -1 && !AnimaniaConfig.gameRules.ambianceMode)
		{
			this.fedTimer--;

			if (this.fedTimer == 0)
				this.setFed(false);
		}

		if (this.wateredTimer > -1)
		{
			this.wateredTimer--;

			if (this.wateredTimer == 0 && !AnimaniaConfig.gameRules.ambianceMode)
				this.setWatered(false);
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();


		if (!fed && !watered)
		{
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
			if (AnimaniaConfig.gameRules.animalsStarve)
			{
				if (this.damageTimer >= AnimaniaConfig.careAndFeeding.starvationTimer)
				{
					this.attackEntityFrom(DamageSource.STARVE, 4f);
					this.damageTimer = 0;
				}
				this.damageTimer++;
			}

		} else if (!fed || !watered)
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));

		if (this.happyTimer > -1)
		{
			this.happyTimer--;
			if (this.happyTimer == 0)
			{
				this.happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && !this.getSleeping() && this.getHandFed() && AnimaniaConfig.gameRules.showUnhappyParticles)
				{
					double d = this.rand.nextGaussian() * 0.001D;
					double d1 = this.rand.nextGaussian() * 0.001D;
					double d2 = this.rand.nextGaussian() * 0.001D;
					this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + this.rand.nextFloat() * this.width - this.width, this.posY + 1.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width - this.width, d, d1, d2);
				}
			}
		}

		boolean sheared = this.getSheared();
		if (sheared) {
			int shearedTimer = this.getWoolRegrowthTimer();
			shearedTimer--;
			this.setWoolRegrowthTimer(shearedTimer);
			if (shearedTimer < 0) {
				this.setSheared(false);

			}
		}


		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack.getItem() instanceof ItemShears && !this.getSheared() && !this.isChild() && (this instanceof EntityBuckAngora || this instanceof EntityDoeAngora))   //Forge: Moved to onSheared
		{
			if (!this.world.isRemote)
			{
				this.setSheared(true);
				int i = 1 + this.rand.nextInt(2);

				for (int j = 0; j < i; ++j)
				{
					//EntityItem entityitem = this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, this.getFleeceColor().getMetadata()), 1.0F);
					EntityItem entityitem = this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1), 1.0F);
					entityitem.motionY += (double)(this.rand.nextFloat() * 0.05F);
					entityitem.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
					entityitem.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
				}
			}

			player.swingArm(hand);
			stack.damageItem(1, player);
			this.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0F, 1.0F);
			if (this.getSleeping()) {
				this.setSleeping(false);
			}
			return true;
		}
		else if(stack.getItem() instanceof ItemShears)
		{
			return true;
		}

		if (stack != ItemStack.EMPTY && AnimaniaHelper.isWaterContainer(stack) && !this.getSleeping())
		{
			if(!player.isCreative())
			{
				ItemStack emptied = AnimaniaHelper.emptyContainer(stack);
				stack.shrink(1);
				AnimaniaHelper.addItem(player, emptied);
			}

			this.eatTimer = 40;
			this.entityAIEatGrass.startExecuting();
			this.setWatered(true);
			this.setInLove(player);
			return true;
		}
		/*
		else if(stack != ItemStack.EMPTY && stack.getItem() == Items.BUCKET)
		{
			return false;
		}
		 */
		else if (this.isBreedingItem(stack))
		{
			this.consumeItemFromStack(player, stack);
			this.setInLove(player);
			return true;
		}
		else
			return super.processInteract(player, hand);
	}

	@Override
	public void eatGrassBonus()
	{

	}
	
	@Override
	public void setInLove(EntityPlayer player)
	{

		if (!this.getSleeping())
			this.world.setEntityState(this, (byte) 18);
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
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		if (this.getMateUniqueId() != null) {
			compound.setString("MateUUID", this.getMateUniqueId().toString());
		}
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Handfed", this.getHandFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setBoolean("Sheared", this.getSheared());
		compound.setBoolean("Spooked", this.getSpooked());
		compound.setInteger("Age", this.getAge());

	}

	public boolean getSheared()
	{
		try {
			return (this.getBoolFromDataManager(SHEARED));
		}
		catch (Exception e) {
			return false;
		}
	}

	public void setSheared(boolean sheared)
	{
		if (sheared)
		{
			this.dataManager.set(EntityAnimaniaGoat.SHEARED, Boolean.valueOf(true));
			this.setWoolRegrowthTimer(AnimaniaConfig.careAndFeeding.woolRegrowthTimer + this.rand.nextInt(500));
		} else
			this.dataManager.set(EntityAnimaniaGoat.SHEARED, Boolean.valueOf(false));
	}

	public int getWoolRegrowthTimer()
	{
		try {
			return (this.getIntFromDataManager(SHEARED_TIMER));
		}
		catch (Exception e) {
			return 0;
		}
	}

	public void setWoolRegrowthTimer(int time)
	{
		this.dataManager.set(EntityAnimaniaGoat.SHEARED_TIMER, Integer.valueOf(time));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		String s;

		if (compound.hasKey("MateUUID", 8))
		{
			s = compound.getString("MateUUID");
		}
		else
		{
			String s1 = compound.getString("Mate");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty())
		{
			this.setMateUniqueId(UUID.fromString(s));
		}

		this.setFed(compound.getBoolean("Fed"));
		this.setHandFed(compound.getBoolean("Handfed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setSheared(compound.getBoolean("Sheared"));
		this.setSpooked(compound.getBoolean("Spooked"));
		this.setAge(compound.getInteger("Age"));

	}


	@Override
	public EntityAnimaniaGoat createChild(EntityAgeable ageable) {
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

	// ==================================================
	//     Data Manager Trapper (borrowed from Lycanites)
	// ==================================================

	public boolean getBoolFromDataManager(DataParameter<Boolean> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return false;
		}
	}

	public byte getByteFromDataManager(DataParameter<Byte> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return 0;
		}
	}

	public int getIntFromDataManager(DataParameter<Integer> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return 0;
		}
	}

	public float getFloatFromDataManager(DataParameter<Float> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return 0;
		}
	}

	public String getStringFromDataManager(DataParameter<String> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return null;
		}
	}

	public Optional<UUID> getUUIDFromDataManager(DataParameter<Optional<UUID>> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return null;
		}
	}

	public ItemStack getItemStackFromDataManager(DataParameter<ItemStack> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return ItemStack.EMPTY;
		}
	}

	public Optional<BlockPos> getBlockPosFromDataManager(DataParameter<Optional<BlockPos>> key) {
		try {
			return this.getDataManager().get(key);
		}
		catch (Exception e) {
			return Optional.absent();
		}
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
	public int getBlinkTimer()
	{
		return blinkTimer;
	}

}
