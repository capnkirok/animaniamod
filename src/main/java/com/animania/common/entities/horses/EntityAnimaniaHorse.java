package com.animania.common.entities.horses;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.AnimaniaAnimal;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.ISpawnable;
import com.animania.common.entities.genericAi.EntityAnimaniaAvoidWater;
import com.animania.common.entities.horses.ai.EntityAIFindFood;
import com.animania.common.entities.horses.ai.EntityAIFindSaltLickHorses;
import com.animania.common.entities.horses.ai.EntityAIFindWater;
import com.animania.common.entities.horses.ai.EntityAIFollowMateHorses;
import com.animania.common.entities.horses.ai.EntityAILookIdleHorses;
import com.animania.common.entities.horses.ai.EntityAIMateHorses;
import com.animania.common.entities.horses.ai.EntityAIPanicHorses;
import com.animania.common.entities.horses.ai.EntityAISwimmingHorse;
import com.animania.common.entities.horses.ai.EntityAITemptHorses;
import com.animania.common.entities.horses.ai.EntityAIWanderHorses;
import com.animania.common.entities.horses.ai.EntityHorseEatGrass;
import com.animania.common.entities.props.EntityWagon;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.inventory.ContainerHorseCart;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAnimaniaHorse extends EntityHorse implements ISpawnable, AnimaniaAnimal
{
	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.horseFood));
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaHorse.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaHorse.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityAnimaniaHorse.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer>createKey(EntityAnimaniaHorse.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> AGE = EntityDataManager.<Integer>createKey(EntityAnimaniaHorse.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> HANDFED = EntityDataManager.<Boolean>createKey(EntityAnimaniaHorse.class, DataSerializers.BOOLEAN);
	private static final String[] HORSE_TEXTURES = new String[] {"black", "bw1", "bw2", "grey", "red", "white"};

	protected int happyTimer;
	public int blinkTimer;
	public int eatTimer;
	protected int fedTimer;
	protected int wateredTimer;
	protected int damageTimer;
	public EntityHorseEatGrass entityAIEatGrass;
	public HorseType horseType;
	protected boolean mateable = false;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	protected EntityGender gender;
	protected ContainerHorseCart cartChest;

	public EntityAnimaniaHorse(World worldIn)
	{
		super(worldIn);
		this.stepHeight = 1.2F;
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new EntityHorseEatGrass(this);
		if (!AnimaniaConfig.gameRules.ambianceMode) {
			this.tasks.addTask(2, new EntityAIFindWater(this, 1.0D));
			this.tasks.addTask(3, new EntityAIFindFood(this, 1.0D));
		}
		this.tasks.addTask(2, new EntityAIPanicHorses(this, 2.0D));
		this.tasks.addTask(3, new EntityAIMateHorses(this, 1.0D));
		this.tasks.addTask(4, new EntityAIFollowMateHorses(this, 1.1D));
		this.tasks.addTask(6, new EntityAIWanderHorses(this, 1.0D));
		this.tasks.addTask(7, new EntityAISwimmingHorse(this));
		this.tasks.addTask(8, new EntityAITemptHorses(this, 1.25D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(9, this.entityAIEatGrass);
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(11, new EntityAnimaniaAvoidWater(this));
		this.tasks.addTask(11, new EntityAILookIdleHorses(this));
		this.tasks.addTask(12, new EntityAIFindSaltLickHorses(this, 1.0));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + rand.nextInt(80);
		this.enablePersistence();

	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaHorse.COLOR_NUM, Integer.valueOf(rand.nextInt(6)));
		this.dataManager.register(EntityAnimaniaHorse.FED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaHorse.HANDFED, Boolean.valueOf(false));
		this.dataManager.register(EntityAnimaniaHorse.WATERED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaHorse.MATE_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(EntityAnimaniaHorse.AGE, Integer.valueOf(0));
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28499999403953552D);
	}

	@Override
	public void updatePassenger(Entity passenger)
	{
		if (this.isPassenger(passenger))
		{
			
			float f = 0.0F;
			float f1 = (float)((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());
			if (passenger instanceof EntityPlayer) {
				f1 = (float)((this.isDead ? 0.009999999776482582D : 1.6D) + passenger.getYOffset());
			}

			if (passenger instanceof EntityPlayer) {
				
				EntityPlayer player = (EntityPlayer) passenger;	
				List wagons = AnimaniaHelper.getWagonsInRange(EntityWagon.class, 3, world, this);
				
				if (!wagons.isEmpty()) {
					if (wagons.size() >= 0) {
						for (int i = 0; i < wagons.size(); i++) {
							EntityWagon tempWagon = (EntityWagon) wagons.get(i);
							if (tempWagon.pulled && tempWagon.puller == this) {
								
								f = (float)((double)f + 1.82D);

								Vec3d vec3d = (new Vec3d((double)f, 0.0D, 0.0D)).rotateYaw(-tempWagon.rotationYaw * 0.017453292F - ((float)Math.PI / 2F));
								passenger.setPosition(tempWagon.posX + vec3d.x, tempWagon.posY + (double)f1, tempWagon.posZ + vec3d.z);

							} else {
							passenger.setPosition(this.posX, this.posY + this.getMountedYOffset() + passenger.getYOffset(), this.posZ);
							}
						}
					}
				} else {
					passenger.setPosition(this.posX, this.posY + this.getMountedYOffset() + passenger.getYOffset(), this.posZ);
				}
			} else {
				passenger.setPosition(this.posX, this.posY + this.getMountedYOffset() + passenger.getYOffset(), this.posZ);
			}

		}
	}


	@Override
	protected ResourceLocation getLootTable()
	{
		return null;
	}

	@Override
	public boolean canJump()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setJumpPower(int jumpPowerIn)
	{

		if (jumpPowerIn < 0)
		{
			jumpPowerIn = 0;
		}
		else
		{
			//do nothing
		}

		if (jumpPowerIn >= 90)
		{
			this.jumpPower = 1.0F;
		}
		else
		{
			this.jumpPower = 0.4F + 0.4F * (float)jumpPowerIn / 90.0F;
		}

	}

	@Nullable
	public UUID getMateUniqueId()
	{
		if(mateable)
		{
			try
			{
				UUID id = (UUID) ((Optional) this.dataManager.get(EntityAnimaniaHorse.MATE_UNIQUE_ID)).orNull();
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
		this.dataManager.set(EntityAnimaniaHorse.MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		this.setHandFed(true);
		this.entityAIEatGrass.startExecuting();
		this.eatTimer = 80;
		this.setInLove(player);

		if (!player.capabilities.isCreativeMode)
			stack.setCount(stack.getCount() - 1);
	}

	public boolean getFed()
	{
		return this.dataManager.get(EntityAnimaniaHorse.FED).booleanValue();
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityAnimaniaHorse.FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		} else
			this.dataManager.set(EntityAnimaniaHorse.FED, Boolean.valueOf(false));
	}

	public boolean getHandFed()
	{
		return this.dataManager.get(EntityAnimaniaHorse.HANDFED).booleanValue();
	}

	public void setHandFed(boolean handfed)
	{
		this.dataManager.set(EntityAnimaniaHorse.HANDFED, Boolean.valueOf(handfed));
	}

	public boolean getWatered()
	{
		return this.dataManager.get(EntityAnimaniaHorse.WATERED).booleanValue();
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(EntityAnimaniaHorse.WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		} else
			this.dataManager.set(EntityAnimaniaHorse.WATERED, Boolean.valueOf(false));
	}

	public int getAge()
	{
		return this.dataManager.get(EntityAnimaniaHorse.AGE).intValue();
	}

	public void setAge(int age)
	{
		this.dataManager.set(EntityAnimaniaHorse.AGE, Integer.valueOf(age));
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

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	public int getColorNumber()
	{
		return ((Integer)this.dataManager.get(COLOR_NUM)).intValue();
	}

	public void setColorNumber(int color)
	{
		this.dataManager.set(COLOR_NUM, Integer.valueOf(color));
	}

	public ResourceLocation getResourceLocation()
	{
		return resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink()
	{
		return resourceLocationBlink;
	}


	@Override
	protected Item getDropItem()
	{
		return Items.LEATHER;
	}

	@Override
	public void travel(float p_191986_1_, float p_191986_2_, float p_191986_3_)
	{
		if (this.isBeingRidden() && this.canBeSteered()) // && this.isHorseSaddled())
		{
			EntityLivingBase entitylivingbase = (EntityLivingBase)this.getControllingPassenger();
			this.rotationYaw = entitylivingbase.rotationYaw;
			this.prevRotationYaw = this.rotationYaw;
			this.rotationPitch = entitylivingbase.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.renderYawOffset = this.rotationYaw;
			this.rotationYawHead = this.renderYawOffset;
			p_191986_1_ = entitylivingbase.moveStrafing * 0.5F;
			p_191986_3_ = entitylivingbase.moveForward * 4F;

			if (p_191986_3_ <= 0.0F)
			{
				p_191986_3_ *= 0.25F;
				this.gallopTime = 0;
			}

			if (this.onGround && this.jumpPower == 0.0F && this.isRearing())
			{
				p_191986_1_ = 0.0F;
				p_191986_3_ = 0.0F;
			}

			if (this.jumpPower > 0.0F && !this.isHorseJumping() && this.onGround)
			{
				this.motionY = this.getHorseJumpStrength() * (double)this.jumpPower;

				if (this.isPotionActive(MobEffects.JUMP_BOOST))
				{
					this.motionY += (double)((float)(this.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
				}

				this.setHorseJumping(true);
				this.isAirBorne = true;

				if (p_191986_3_ > 0.0F)
				{
					float f = MathHelper.sin(this.rotationYaw * 0.017453292F);
					float f1 = MathHelper.cos(this.rotationYaw * 0.017453292F);
					this.motionX += (double)(-0.4F * f * this.jumpPower);
					this.motionZ += (double)(0.4F * f1 * this.jumpPower);
					this.playSound(SoundEvents.ENTITY_HORSE_JUMP, 0.4F, 1.0F);
				}

				this.jumpPower = 0.0F;
			}

			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (this.canPassengerSteer() || this.getLeashed())
			{
				this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
				super.travel(p_191986_1_, p_191986_2_/8, p_191986_3_);
			}
			else if (entitylivingbase instanceof EntityPlayer)
			{
				this.motionX = 0.0D;
				this.motionY = 0.0D;
				this.motionZ = 0.0D;
			}

			if (this.onGround)
			{
				this.jumpPower = 0.0F;
				this.setHorseJumping(false);
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.posX - this.prevPosX;
			double d0 = this.posZ - this.prevPosZ;
			float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

			if (f2 > 1.0F)
			{
				f2 = 1.0F;
			}

			// this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
			//  this.limbSwing += this.limbSwingAmount;
		}
		else
		{
			this.jumpMovementFactor = 0.02F;
			if (!this.isBeingRidden()) {
				p_191986_3_ = this.moveForward;
			}
			super.travel(p_191986_1_, p_191986_2_, p_191986_3_);

		}
	}

	protected ItemStack getItem(String moditem) {

		ItemStack foundStack = null;
		String item = "";
		String mod = "";
		int sepLoc = 0;
		int metaLoc = 0;
		boolean metaFlag = false;
		String metaVal = "";

		sepLoc = moditem.indexOf(":");
		metaLoc = moditem.indexOf("#");

		if (!moditem.contains(":")) {
			return new ItemStack(Blocks.AIR, 1);
		}

		mod = moditem.substring(0, sepLoc);

		if (metaLoc > 0) {
			item = moditem.substring(sepLoc+1, metaLoc);
		} else {
			item = moditem;
		}
		if (metaLoc > 0) {
			metaFlag = true;
			metaVal = moditem.substring(metaLoc+1, moditem.length());
		}

		Item bob = Item.getByNameOrId(item);

		if (bob != null) {

			if (metaFlag) {
				foundStack = new ItemStack(bob, 1, Integer.parseInt(metaVal));
			} else {
				foundStack = new ItemStack(bob, 1);
			}
		} else {
			foundStack = new ItemStack(Blocks.AIR, 1);
		}

		return foundStack;
	}




	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET)
		{
			if (stack.getCount() == 1 && !player.capabilities.isCreativeMode)
				player.setHeldItem(hand, new ItemStack(Items.BUCKET));
			else if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET)))
				player.dropItem(new ItemStack(Items.BUCKET), false);

			this.eatTimer = 40;
			this.entityAIEatGrass.startExecuting();
			this.setWatered(true);
			this.setInLove(player);
			return true;
		} 
		else if(stack != ItemStack.EMPTY && stack.getItem() == Items.BUCKET)
		{
			return false;
		}
		else if(!stack.isEmpty() && isHorseBreedingItem(stack.getItem()))
		{
			consumeItemFromStack(player, stack);
			return true;
		}
		else
			return super.processInteract(player, hand);
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


	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != null && this.isHorseBreedingItem(stack.getItem());
	}

	private boolean isHorseBreedingItem(Item itemIn)
	{
		return TEMPTATION_ITEMS.contains(itemIn);
	}


	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);

		if (this.getMateUniqueId() != null) {
			compound.setString("MateUUID", this.getMateUniqueId().toString());
		}
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Handfed", this.getHandFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setInteger("ColorNumber", getColorNumber());
		compound.setInteger("Age", this.getAge());


	}

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

		this.setColorNumber(compound.getInteger("ColorNumber"));
		this.setFed(compound.getBoolean("Fed"));
		this.setHandFed(compound.getBoolean("Handfed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setAge(compound.getInteger("Age"));

	}


	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		int happyDrops = 0;

		if (this.getWatered()) {
			happyDrops++;
		} 
		if (this.getFed()) {
			happyDrops++;
		} 

		ItemStack dropItem;
		if (AnimaniaConfig.drops.customMobDrops)
		{
			String drop = AnimaniaConfig.drops.horseDrop;
			dropItem = AnimaniaHelper.getItem(drop);
		} else {
			dropItem = ItemStack.EMPTY;
		}

		if (happyDrops == 2 && dropItem !=null) {
			dropItem.setCount(1 + lootlevel);
			EntityItem entityitem = new EntityItem(this.world, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, dropItem);
			world.spawnEntity(entityitem);
			this.dropItem(Items.LEATHER, 1);
		} else if (happyDrops == 1 && dropItem !=null) {
			if (this.isBurning())
			{
				this.dropItem(Items.LEATHER, 1 + lootlevel);
			}
			else
			{
				this.dropItem(Items.LEATHER, 1 + lootlevel);
			}
		} else if (happyDrops == 0) {
			this.dropItem(Items.LEATHER, 1 + lootlevel);
		}

	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.horseType, this.gender));
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


}
