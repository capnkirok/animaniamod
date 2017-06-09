package com.animania.common.entities.horses;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.horses.ai.EntityAIFindFood;
import com.animania.common.entities.horses.ai.EntityAIFindWater;
import com.animania.common.entities.horses.ai.EntityAIFollowParentHorses;
import com.animania.common.entities.horses.ai.EntityAIPanicHorses;
import com.animania.common.entities.horses.ai.EntityAISwimmingHorse;
import com.animania.common.entities.horses.ai.EntityAITemptHorses;
import com.animania.common.entities.horses.ai.EntityAIWanderHorse;
import com.animania.common.entities.horses.ai.EntityHorseEatGrass;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
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

public class EntityFoalDraftHorse extends EntityAnimal
{

	private static final DataParameter<Integer> COLOR_NUM = EntityDataManager.<Integer>createKey(EntityFoalDraftHorse.class, DataSerializers.VARINT);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] {Items.WHEAT, Items.APPLE, Items.CARROT});
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityFoalDraftHorse.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityFoalDraftHorse.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Optional<UUID>> PARENT_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityFoalDraftHorse.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Float> AGE = EntityDataManager.<Float>createKey(EntityFoalDraftHorse.class, DataSerializers.FLOAT);
	private static final String[] HORSE_TEXTURES = new String[] {"black", "bw1", "bw2", "grey", "red", "white"};

	private int happyTimer;
	private int ageTimer;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	public int blinkTimer;
	
	public EntityFoalDraftHorse(World world)
	{
		super(world);
		this.setSize(1.2F, 1.4F);
		this.stepHeight = 1.1F;
		this.tasks.taskEntries.clear();
		this.entityAIEatGrass = new EntityHorseEatGrass(this);
		this.tasks.addTask(1, new EntityAIFindFood(this, 1.1D));
		this.tasks.addTask(2, new EntityAIPanicHorses(this, 2.0D));
		this.tasks.addTask(3, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWanderHorse(this, 1.0D));
		this.tasks.addTask(5, new EntityAISwimmingHorse(this));
		this.tasks.addTask(6, new EntityAIFollowParentHorses(this, 1.1D));
		this.tasks.addTask(7, new EntityAITemptHorses(this, 1.25D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(8, this.entityAIEatGrass);
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(11, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + rand.nextInt(80);
		this.ageTimer = 0;
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
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));
		this.dataManager.register(AGE, Float.valueOf(0));
		this.dataManager.register(PARENT_UNIQUE_ID, Optional.<UUID>absent());
	}

	@Override
	public boolean isChild()
	{
		return true;
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

	public int getVerticalFaceSpeed()
	{
		return super.getVerticalFaceSpeed();
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

		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setInteger("ColorNumber", getColorNumber());
		
		compound.setFloat("Age", this.getEntityAge());
		if (this.getParentUniqueId() != null)
		{
			if (this.getParentUniqueId() != null) {
				compound.setString("ParentUUID", this.getParentUniqueId().toString());
			}
		}


	}

	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		String s;

		if (compound.hasKey("ParentUUID", 8))
		{
			s = compound.getString("ParentUUID");
		}
		else
		{
			String s1 = compound.getString("Parent");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty())
		{
			this.setParentUniqueId(UUID.fromString(s));
		}

		this.setColorNumber(compound.getInteger("ColorNumber"));
		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));

	}

	@Nullable
	public UUID getParentUniqueId()
	{
		return (UUID)((Optional)this.dataManager.get(PARENT_UNIQUE_ID)).orNull();
	}

	public void setParentUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(PARENT_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	public float getEntityAge()
	{
		return ((Float)this.dataManager.get(AGE)).floatValue();
	}

	public void setEntityAge(float age)
	{
		this.dataManager.set(AGE, Float.valueOf(age));
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
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.26000000298023224D);
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
		this.playSound(SoundEvents.ENTITY_HORSE_STEP, 0.10F, 1.0F);
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
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() + .2F);
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
		return null;
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
			}
		}

		ageTimer++;
		if (ageTimer >= AnimaniaConfig.careAndFeeding.childGrowthTick) {

			if (fed && watered) {
				ageTimer = 0;
				float age = this.getEntityAge();
				age = age + .01F;
				this.setEntityAge(age);

				if (age >= 0.45 && !this.world.isRemote) {
					int color = this.getColorNumber();
					this.setDead();

					if (rand.nextInt(2) < 1) {
						EntityMareDraftHorse entityHorse = new EntityMareDraftHorse(this.world);
						entityHorse.setPosition(this.posX, this.posY + .5, this.posZ);
						String name = this.getCustomNameTag();
						if (name != "") {
							entityHorse.setCustomNameTag(name);
						}
						this.world.spawnEntity(entityHorse);
						entityHorse.setColorNumber(color);
						this.playSound(ModSoundEvents.horseliving1, 0.50F, 1.1F); 
					} else {
						EntityStallionDraftHorse entityHorse = new EntityStallionDraftHorse(this.world);
						entityHorse.setPosition(this.posX, this.posY + .5, this.posZ);
						String name = this.getCustomNameTag();
						if (name != "") {
							entityHorse.setCustomNameTag(name);
						}
						this.world.spawnEntity(entityHorse);
						entityHorse.setColorNumber(color);
						this.playSound(ModSoundEvents.horseliving2, 0.50F, 1.1F); 
					}

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
		} else {
			return super.processInteract(player, hand);
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


	public EntityFoalDraftHorse createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}
}