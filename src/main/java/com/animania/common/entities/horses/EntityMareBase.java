package com.animania.common.entities.horses;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.cows.ai.EntityAIMateCows;
import com.animania.common.handler.ItemHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMareBase extends EntityAnimaniaHorse
{	
	
	protected int gestationTimer;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	private static final String[] HORSE_TEXTURES = new String[] {"black", "bw1", "bw2", "grey", "red", "white"};
	private boolean boosting;
	private int boostTime;
	private int totalBoostTime;

	public EntityMareBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.6F, 2.2F);
		this.stepHeight = 1.1F;
		this.gestationTimer = AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200);
		this.tasks.addTask(5, new EntityAIMateCows(this, 1.0D));
		this.mateable = true;
	}

	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{

		if (this.world.isRemote) {
			return null;
		}

		int horseCount = 0;
		int esize = this.world.loadedEntityList.size();
		for (int k = 0; k <= esize - 1; k++) {
			Entity entity = (Entity) this.world.loadedEntityList.get(k);
			if (entity.getName().contains("Draft")) {
				EntityAnimal ea = (EntityAnimal) entity;
				if (ea.hasCustomName() || ea.isInLove()) {
					//do nothing
				} else {
					horseCount = horseCount + 1;
				}
			}
		}

		if (horseCount <= AnimaniaConfig.spawn.spawnLimitHorses) {

			int chooser = rand.nextInt(5);

			if (chooser == 0) {
				EntityStallionDraftHorse entityHorse = new EntityStallionDraftHorse(this.world);
				entityHorse.setPosition(this.posX,  this.posY, this.posZ);
				this.world.spawnEntity(entityHorse);
				entityHorse.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityHorse.getUniqueID());
			} else if (chooser == 1) {
				EntityFoalDraftHorse entityHorse = new EntityFoalDraftHorse(this.world);
				entityHorse.setPosition(this.posX,  this.posY, this.posZ);
				this.world.spawnEntity(entityHorse);
				entityHorse.setParentUniqueId(this.entityUniqueID);
			} else if (chooser > 2) {
				EntityStallionDraftHorse entityHorse = new EntityStallionDraftHorse(this.world);
				entityHorse.setPosition(this.posX,  this.posY, this.posZ);
				this.world.spawnEntity(entityHorse);
				entityHorse.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityHorse.getUniqueID());
				EntityFoalDraftHorse entityFoal = new EntityFoalDraftHorse(this.world);
				entityFoal.setPosition(this.posX,  this.posY, this.posZ);
				this.world.spawnEntity(entityFoal);
				entityFoal.setParentUniqueId(this.entityUniqueID);
			} 

			this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

			if (this.rand.nextFloat() < 0.05F)
			{
				this.setLeftHanded(true);
			}
			else
			{
				this.setLeftHanded(false);
			}
		}

		return livingdata;
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
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
	public double getMountedYOffset()
	{
		return (double)this.height * 0.60D;
	}

	
	public int getVerticalFaceSpeed()
	{
		return super.getVerticalFaceSpeed();
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

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
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
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .05F);
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
	
	public ResourceLocation getResourceLocation()
	{
		return resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink()
	{
		return resourceLocationBlink;
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

		if (this.gestationTimer > -1 && this.getMateUniqueId() != null) {
			this.gestationTimer--;
			if (gestationTimer == 0 ) {

				gestationTimer = AnimaniaConfig.careAndFeeding.gestationTimer + rand.nextInt(2000);

				String MateID = this.getMateUniqueId().toString();

				int esize = this.world.loadedEntityList.size();
				for (int k = 0; k <= esize - 1; k++) {
					Entity entity = (Entity) this.world.loadedEntityList.get(k);

					double xt = entity.posX;
					double yt = entity.posY;
					double zt = entity.posZ;
					int x1 = MathHelper.floor(this.posX);
					int y1 = MathHelper.floor(this.posY);
					int z1 = MathHelper.floor(this.posZ);
					double x2 = xt - x1;
					double y2 = yt - y1;
					double z2 = zt - z1;

					if (entity !=null && this.getFed() && this.getWatered() && entity.getPersistentID().toString().equals(MateID) && x2 <= 10 && y2 <=10 && z2 <=10) {

						this.setInLove(null);

						if (!this.world.isRemote) {

							EntityFoalDraftHorse entityFoal = new EntityFoalDraftHorse(this.world);
							entityFoal.setPosition(this.posX,  this.posY + .2, this.posZ);
							this.world.spawnEntity(entityFoal);
							entityFoal.setParentUniqueId(this.getPersistentID());
							this.playSound(ModSoundEvents.mooCalf1, 0.50F, 1.1F); //TODO

						}
					}
				}
			}

		}

		super.onLivingUpdate();
	}



	@Override
	public EntityMareBase createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}


	
}
