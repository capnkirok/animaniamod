package com.animania.common.entities.horses;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.horses.ai.EntityAIFindFood;
import com.animania.common.entities.horses.ai.EntityAIFindWater;
import com.animania.common.entities.horses.ai.EntityAIFollowMateHorses;
import com.animania.common.entities.horses.ai.EntityAIMateHorses;
import com.animania.common.entities.horses.ai.EntityAIPanicHorses;
import com.animania.common.entities.horses.ai.EntityAISwimmingHorse;
import com.animania.common.entities.horses.ai.EntityAITemptHorses;
import com.animania.common.entities.horses.ai.EntityAIWanderHorse;
import com.animania.common.entities.horses.ai.EntityHorseEatGrass;
import com.animania.common.handler.ItemHandler;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
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
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityStallionDraftHorse extends EntityAnimal
{

	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer>createKey(EntityStallionDraftHorse.class, DataSerializers.VARINT);
	private static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityStallionDraftHorse.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] {Items.WHEAT, Items.APPLE, Items.CARROT});
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityStallionDraftHorse.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityStallionDraftHorse.class, DataSerializers.BOOLEAN);
	private static final String[] HORSE_TEXTURES = new String[] {"black", "bw1", "bw2", "grey", "red", "white"};

	private int happyTimer;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	public int blinkTimer;
	private boolean boosting;
	private int boostTime;
	private int totalBoostTime;

	public EntityStallionDraftHorse(World world)
	{
		super(world);
		this.setSize(1.6F, 2.2F);
		this.stepHeight = 1.1F;
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new EntityHorseEatGrass(this);
		this.tasks.addTask(1, new EntityAIFindFood(this, 1.1D));
		this.tasks.addTask(1, new EntityAIPanicHorses(this, 2.0D));
		this.tasks.addTask(5, new EntityAIMateHorses(this, 1.0D));
		this.tasks.addTask(1, new EntityAIFollowMateHorses(this, 1.1D));
		this.tasks.addTask(3, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWanderHorse(this, 1.0D));
		this.tasks.addTask(5, new EntityAISwimmingHorse(this));
		this.tasks.addTask(7, new EntityAITemptHorses(this, 1.25D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(8, this.entityAIEatGrass);
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(11, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + rand.nextInt(80);
		this.enablePersistence();

	}

	public int eatTimer;
	private int fedTimer;
	private int wateredTimer;
	public EntityHorseEatGrass entityAIEatGrass;

	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(COLOR_NUM, Integer.valueOf(0));
		this.dataManager.register(MATE_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		this.entityAIEatGrass.startExecuting();
		eatTimer = 80;

		player.addStat(AnimaniaAchievements.Horses, 1);

		if (!player.capabilities.isCreativeMode)
		{
			stack.shrink(1);
		}
	}
	
	@Override
	public double getMountedYOffset()
	{
		return (double)this.height * 0.72D;
	}

	public int getVerticalFaceSpeed()
	{
		return super.getVerticalFaceSpeed();
	}

	@Nullable
	public Entity getControllingPassenger()
	{
		return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
	}

	public boolean canBeSteered()
	{
		Entity entity = this.getControllingPassenger();

		if (!(entity instanceof EntityPlayer))
		{
			return false;
		}
		else
		{
			EntityPlayer entityplayer = (EntityPlayer)entity;
			ItemStack itemstack = entityplayer.getHeldItemMainhand();

			if (itemstack != null && itemstack.getItem() == ItemHandler.ridingCrop)
			{
				return true;
			}
			else
			{
				itemstack = entityplayer.getHeldItemOffhand();
				return itemstack != null && itemstack.getItem() == ItemHandler.ridingCrop;
			}
		}
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
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte)18);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);

		if (this.getMateUniqueId() != null) {
			compound.setString("MateUUID", this.getMateUniqueId().toString());
		}
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setInteger("ColorNumber", getColorNumber());


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
		this.setWatered(compound.getBoolean("Watered"));

	}

	@Nullable
	public UUID getMateUniqueId()
	{
		return (UUID)((Optional)this.dataManager.get(MATE_UNIQUE_ID)).orNull();
	}

	public void setMateUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	public boolean getFed()
	{
		return ((Boolean)this.dataManager.get(FED)).booleanValue();
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
			this.setHealth(this.getHealth()+1.0F);
		}
		else
		{
			this.dataManager.set(FED, Boolean.valueOf(false));
		}
	}

	public boolean getWatered()
	{
		return ((Boolean)this.dataManager.get(WATERED)).booleanValue();
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		}
		else
		{
			this.dataManager.set(WATERED, Boolean.valueOf(false));
		}
	}


	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}


	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
	{
		super.setAttackTarget(entitylivingbaseIn);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isEntityInvulnerable(source))
		{
			return false;
		}
		else
		{
			return super.attackEntityFrom(source, amount);
		}
	}

	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_HORSE_STEP, 0.20F, 0.8F);
	}

	protected SoundEvent getAmbientSound()
	{
		int happy = 0;
		int num = 0;

		if (this.getWatered()) {
			happy++;
		} 
		if (this.getFed()) {
			happy++;
		} 

		if (happy == 2) {
			num = 18;
		} else if (happy == 1) {
			num = 36;
		} else {
			num = 60;
		}

		Random rand = new Random();
		int chooser = rand.nextInt(num);

		if (chooser == 0) {
			return ModSoundEvents.horseliving1;
		} else if (chooser == 1){
			return ModSoundEvents.horseliving2;
		} else if (chooser == 2){
			return ModSoundEvents.horseliving3;
		} else if (chooser == 3){
			return ModSoundEvents.horseliving4;
		} else if (chooser == 4){
			return ModSoundEvents.horseliving5;
		} else {
			return ModSoundEvents.horseliving6;
		} 
	}

	protected SoundEvent getHurtSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0) {
			return ModSoundEvents.horsehurt1;
		} else if (chooser == 1) {
			return ModSoundEvents.horsehurt2;
		} else {
			return ModSoundEvents.horsehurt3;
		}
	}

	protected SoundEvent getDeathSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0) {
			return ModSoundEvents.horsehurt1;
		} else if (chooser == 1) {
			return ModSoundEvents.horsehurt2;
		} else {
			return ModSoundEvents.horsehurt3;
		}
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
		{
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
		}
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	protected Item getDropItem()
	{
		return Items.LEATHER;
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
		if (AnimaniaConfig.drops.customMobDrops) {
			String drop = AnimaniaConfig.drops.horseDrop;
			dropItem = getItem(drop);
		} else {
			dropItem = null;
		}

		if (happyDrops == 2) {
			dropItem.setCount(1 + lootlevel);
    		EntityItem entityitem = new EntityItem(this.world, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, dropItem);
    		world.spawnEntity(entityitem);
			this.dropItem(Items.LEATHER, 1);
		} else if (happyDrops == 1) {
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

	private ItemStack getItem(String moditem) {

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
			item = moditem.substring(sepLoc+1, moditem.length());
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


	public void onLivingUpdate()
	{

		if(getColorNumber() == 0) {
			Random rand = new Random();
			int bob2 = rand.nextInt(6) + 1;
			this.setColorNumber(bob2);
			resourceLocation = new ResourceLocation("animania:textures/entity/horses/draft_horse_" + this.HORSE_TEXTURES[bob2-1] + ".png");
			resourceLocationBlink = new ResourceLocation("animania:textures/entity/horses/draft_horse_" + this.HORSE_TEXTURES[bob2-1] + "_blink.png");
		} else if (resourceLocation == null) {
			resourceLocation = new ResourceLocation("animania:textures/entity/horses/draft_horse_" + this.HORSE_TEXTURES[this.getColorNumber()-1] + ".png");
			resourceLocationBlink = new ResourceLocation("animania:textures/entity/horses/draft_horse_" + this.HORSE_TEXTURES[this.getColorNumber()-1] + "_blink.png");
		}

		if (this.world.isRemote)
		{
			this.eatTimer = Math.max(0, this.eatTimer - 1);
		}

		if (this.fedTimer > -1) {
			this.fedTimer--;

			if (fedTimer == 0) {
				this.setFed(false);
			}
		}

		if (this.wateredTimer > -1) {
			this.wateredTimer--;

			if (wateredTimer == 0) {
				this.setWatered(false);
			}
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();

		if (!fed || !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));
		} else if (!fed && !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
		}

		if (this.blinkTimer > -1) {
			this.blinkTimer--;
			if (blinkTimer == 0) {
				this.blinkTimer = 80 + rand.nextInt(80);
				
				// Check for Mate
                if (this.getMateUniqueId() != null) {
                    String mate = this.getMateUniqueId().toString();
                    boolean mateReset = true;

                    int esize = this.world.loadedEntityList.size();
                    for (int k = 0; k <= esize - 1; k++) {
                        Entity entity = this.world.loadedEntityList.get(k);
                        if (entity != null) {
                            UUID id = entity.getPersistentID();
                            if (id.toString().equals(this.getMateUniqueId().toString()) && !entity.isDead) {
                                mateReset = false;
                                break;
                            }
                        }
                    }

                    if (mateReset)
                        this.setMateUniqueId(null);

                }
			}
		}

		if (this.happyTimer > -1) {
			this.happyTimer--;
			if (happyTimer == 0) {
				happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && AnimaniaConfig.gameRules.showUnhappyParticles) {
					double d = rand.nextGaussian() * 0.001D;
					double d1 = rand.nextGaussian() * 0.001D;
					double d2 = rand.nextGaussian() * 0.001D;
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (posX + (double)(rand.nextFloat() * width)) - (double)width, posY + 1.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width)) - (double)width, d, d1, d2);
				}
			}
		}

		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{

		ItemStack stack = player.getHeldItem(hand);
		
		 if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET) {
			{
				if (stack.getCount() == 1 && !player.capabilities.isCreativeMode)
				{
					player.setHeldItem(hand, new ItemStack(Items.BUCKET));
				}
				else if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET)))
				{
					player.dropItem(new ItemStack(Items.BUCKET), false);
				}

				if (this.entityAIEatGrass != null) {
					this.entityAIEatGrass.startExecuting();
					eatTimer = 40;
				}
				this.setWatered(true);
				this.setInLove(player);
				return true;
			}
		} else if (stack != null && stack.getItem() == ItemHandler.ridingCrop && !this.isBeingRidden() && this.getWatered() && this.getFed()) {
			player.startRiding(this);
			player.addStat(AnimaniaAchievements.Horseriding, 1);
			return true;
		} else {
			return super.processInteract(player, hand);
		}
	}

	@Override
	public void moveEntityWithHeading(float strafe, float forward)
	{
		Entity entity = this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);

		if (this.isBeingRidden() && this.canBeSteered())
		{
			this.rotationYaw = entity.rotationYaw;
			this.prevRotationYaw = this.rotationYaw;
			this.rotationPitch = entity.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.renderYawOffset = this.rotationYaw;
			this.rotationYawHead = this.rotationYaw;
			this.stepHeight = 1.0F;
			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (this.canPassengerSteer())
			{
				float f = (float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 0.7F;

				if (this.boosting)
				{
					if (this.boostTime++ > this.totalBoostTime)
					{
						this.boosting = false;
					}

					f += f * 1.15F * MathHelper.sin((float)this.boostTime / (float)this.totalBoostTime * (float)Math.PI);
				}

				this.setAIMoveSpeed(f);
				super.moveEntityWithHeading(0.0F, 1.0F);
			}
			else
			{
				this.motionX = 0.0D;
				this.motionY = 0.0D;
				this.motionZ = 0.0D;
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.posX - this.prevPosX;
			double d0 = this.posZ - this.prevPosZ;
			float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

			if (f1 > 1.0F)
			{
				f1 = 1.0F;
			}

			this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		}
		else
		{
			this.stepHeight = 1.0F;
			this.jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(strafe, forward);
		}
	}

	public boolean boost()
	{
		if (this.boosting)
		{
			return false;
		}
		else
		{
			this.boosting = true;
			this.boostTime = 0;
			this.totalBoostTime = this.getRNG().nextInt(841) + 140;
			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
		{
			this.eatTimer = 160;
		}
		else
		{
			super.handleStatusUpdate(id);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		if (this.isBeingRidden()) {
			return 0;
		} 

		return this.eatTimer <= 0 ? 0.0F : (this.eatTimer >= 4 && this.eatTimer <= 156 ? 1.0F : (this.eatTimer < 4 ? ((float)this.eatTimer - p_70894_1_) / 4.0F : -((float)(this.eatTimer - 160) - p_70894_1_) / 4.0F));
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		
		if (this.isBeingRidden()) {
			return 0;
		} 

		
		if (this.eatTimer > 4 && this.eatTimer <= 156)
		{
			float f = ((float)(this.eatTimer - 4) - p_70890_1_) / 80.0F;
			return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 500F) * MathHelper.sin(f * 28.7F);
		}
		else
		{
			return this.eatTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * 0.017453292F;
		}
	}

	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != null && this.isHorseBreedingItem(stack.getItem());
	}

	private boolean isHorseBreedingItem(Item itemIn)
	{
		return itemIn == Items.WHEAT || itemIn == Items.APPLE || itemIn == Items.CARROT; 
	}


	public EntityStallionDraftHorse createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}
}