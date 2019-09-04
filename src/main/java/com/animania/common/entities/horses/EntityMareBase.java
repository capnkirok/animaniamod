package com.animania.common.entities.horses;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IImpregnable;
import com.animania.api.interfaces.IMateable;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.horses.HorseDraft.EntityFoalDraftHorse;
import com.animania.common.entities.horses.HorseDraft.EntityStallionDraftHorse;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMareBase extends EntityAnimaniaHorse implements TOPInfoProviderMateable, IMateable, IImpregnable
{	

	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	private static final String[] HORSE_TEXTURES = new String[] {"black", "bw1", "bw2", "grey", "red", "white"};
	private boolean boosting;
	private int boostTime;
	private int totalBoostTime;
	public int dryTimerMare;
	protected static final DataParameter<Boolean> PREGNANT = EntityDataManager.<Boolean>createKey(EntityMareBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HAS_KIDS = EntityDataManager.<Boolean>createKey(EntityMareBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FERTILE = EntityDataManager.<Boolean>createKey(EntityMareBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> GESTATION_TIMER = EntityDataManager.<Integer>createKey(EntityMareBase.class, DataSerializers.VARINT);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityMareBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);


	public EntityMareBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.8F, 2.2F); 
		this.width = 1.8F;
		this.height = 2.2F;
		this.stepHeight = 1.2F;
		this.mateable = true;
		this.gender = EntityGender.FEMALE;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityMareBase.PREGNANT, false);
		this.dataManager.register(EntityMareBase.HAS_KIDS, false);
		this.dataManager.register(EntityMareBase.FERTILE, true);
		this.dataManager.register(EntityMareBase.GESTATION_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(400)));
		this.dataManager.register(EntityMareBase.MATE_UNIQUE_ID, Optional.<UUID>absent());

	}

	@Override
	protected void initEntityAI()
	{
		super.initEntityAI();
	}

	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{
		if (this.world.isRemote)
			return null;

		List<EntityAnimaniaHorse> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaHorse.class, 64, this.world, this.getPosition());
		
		if (others.size() <= 4 ) {


			int chooser = rand.nextInt(3);

			if (chooser == 0) {
				EntityStallionDraftHorse entityHorse = new EntityStallionDraftHorse(this.world);
				entityHorse.setPosition(this.posX,  this.posY, this.posZ);
				this.world.spawnEntity(entityHorse);
				entityHorse.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityHorse.getPersistentID());
			} else if (chooser == 1) {
				EntityFoalDraftHorse entityHorse = new EntityFoalDraftHorse(this.world);
				entityHorse.setPosition(this.posX,  this.posY, this.posZ);
				this.world.spawnEntity(entityHorse);
				entityHorse.setParentUniqueId(this.entityUniqueID);
			} 
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

		return livingdata;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	public int getGestation()
	{
		return this.getIntFromDataManager(GESTATION_TIMER);
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
		} else if (!this.isChild() && this.isHorseSaddled()) {

			if (player.isSneaking())
			{
				this.openGUI(player);
				return true;
			} else {
				return super.processInteract(player, hand);
			}

		}
		else if (!player.isSneaking() && stack != null && this.isHorseSaddled() && !this.getSleeping() && !this.isBeingRidden() && this.getWatered() && this.getFed()) {
			this.mountTo(player);
			//player.addStat(AnimaniaAchievements.Horseriding, 1);
			return true;
		} else {
			return super.processInteract(player, hand);
		}
	}

	public void setGestation(int gestation)
	{
		this.dataManager.set(EntityMareBase.GESTATION_TIMER, Integer.valueOf(gestation));
	}

	public boolean getPregnant()
	{
		return this.getBoolFromDataManager(PREGNANT);
	}

	public void setPregnant(boolean preggers)
	{
		if (preggers) {
			this.setGestation(AnimaniaConfig.careAndFeeding.gestationTimer + rand.nextInt(200));
		}
		this.dataManager.set(EntityMareBase.PREGNANT, Boolean.valueOf(preggers));
	}

	@Override
	public DataParameter<Boolean> getFertileParam()
	{
		return FERTILE;
	}

	@Override
	public DataParameter<Boolean> getHasKidsParam()
	{
		return HAS_KIDS;
	}

	@Override
	public double getMountedYOffset()
	{
		return (double)this.height * 0.60D;
	}

	@Override
	public void openGUI(EntityPlayer playerEntity)
	{
		if (!this.world.isRemote && (!this.isBeingRidden() || this.isPassenger(playerEntity)))
		{
			this.horseChest.setCustomName(this.getName());
			playerEntity.openGuiHorseInventory(this, this.horseChest);
		}
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
		else if (this.isHorseSaddled())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void travel(float strafe, float forward, float friction)
	{
		Entity entity = this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);

		if (this.isHorseSaddled() && this.isBeingRidden() && this.canBeSteered())
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
				super.travel(0.0F, 1.0F, 0.0f);
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
			super.travel(strafe, forward, 0.0f);
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
		int num = 1;

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

		int chooser = Animania.RANDOM.nextInt(num);

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

	protected SoundEvent getHurtSound(DamageSource source)
	{
		int chooser = Animania.RANDOM.nextInt(3);

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
		int chooser = Animania.RANDOM.nextInt(3);

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

		if (soundevent != null && !this.getSleeping())
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
		
		if (this.getLeashed() && this.getSleeping())
			this.setSleeping(false);
		
		if (this.isBeingRidden() && this.getSleeping())
			this.setSleeping(false);
	

		if (!this.getFertile() && this.dryTimerMare > -1) {
			this.dryTimerMare--;
		} else {
			this.setFertile(true);
			this.dryTimerMare = AnimaniaConfig.careAndFeeding.gestationTimer/5 + rand.nextInt(50);
		}

		if (this.getColorNumber() > 5) {
			this.setColorNumber(0);
		}

		if (this.blinkTimer > -1) {
			this.blinkTimer--;
			if (blinkTimer == 0) {
				this.blinkTimer = 80 + rand.nextInt(80);

				// Check for Mate
				if (this.getMateUniqueId() != null)
				{
					UUID mate = this.getMateUniqueId();
					boolean mateReset = true;

					List<EntityLivingBase> entities = AnimaniaHelper.getEntitiesInRange(EntityStallionBase.class, 20, world, this);
					for (int k = 0; k <= entities.size() - 1; k++)
					{
						Entity entity = entities.get(k);
						if (entity != null)
						{
							UUID id = entity.getPersistentID();
							if (id.equals(this.getMateUniqueId()) && !entity.isDead)
							{
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

		int gestationTimer = this.getGestation();

		if (gestationTimer > -1 && this.getPregnant())
		{
			gestationTimer--;
			this.setGestation(gestationTimer);
			
			if (gestationTimer < 200 && this.getSleeping()) {
				this.setSleeping(false);
			}
			
			if (gestationTimer == 0)
			{

				List list = this.world.loadedEntityList;	
				int horseCount = 0;
				int num = 0;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) instanceof EntityAnimaniaHorse) {
						num++;
					}
				}
				horseCount = num;

				UUID MateID = this.getMateUniqueId();
				List entities = AnimaniaHelper.getEntitiesInRange(EntityStallionBase.class, 30, this.world, this);
				int esize = entities.size();
				Boolean mateFound = false;
				for (int k = 0; k <= esize - 1; k++) 
				{
					EntityStallionBase entity = (EntityStallionBase)entities.get(k);
					if (entity !=null && this.getFed() && this.getWatered() && entity.getPersistentID().equals(MateID)) {

						this.setInLove(null);
						HorseType maleType = ((EntityAnimaniaHorse) entity).horseType;
						HorseType babyType =HorseType.breed(maleType, this.horseType);
						EntityFoalBase entityFoal = babyType.getChild(world);
						;
						entityFoal.setPosition(this.posX, this.posY + .2, this.posZ);
						if (!world.isRemote) {
							this.world.spawnEntity(entityFoal);
						}
						entityFoal.setParentUniqueId(this.getPersistentID());
						this.playSound(ModSoundEvents.horseliving2, 0.50F, 1.1F);

						this.setPregnant(false);
						this.setFertile(false);
						this.setHasKids(true);

						BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, entityFoal);
						MinecraftForge.EVENT_BUS.post(event);
						k = esize;
						mateFound = true;
						break;

					}
				}

				if (!mateFound && this.getFed() && this.getWatered()) {

					this.setInLove(null);
					HorseType babyType = HorseType.breed(this.horseType, this.horseType);
					EntityFoalBase entityKid = babyType.getChild(world);
					entityKid.setPosition(this.posX, this.posY + .2, this.posZ);
					if (!world.isRemote) {
						this.world.spawnEntity(entityKid);
					}

					entityKid.setParentUniqueId(this.getPersistentID());
					this.playSound(ModSoundEvents.horseliving2, 0.50F, 1.1F);

					this.setPregnant(false);
					this.setFertile(false);
					this.setHasKids(true);

					BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, (EntityLiving) entityKid, entityKid);
					MinecraftForge.EVENT_BUS.post(event);
					mateFound = true;

				}
			}

		} else if (gestationTimer < 0){
			this.setGestation(1);
		}

		super.onLivingUpdate();
	}



	@Override
	public EntityMareBase createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking())
		{

			if (this.getMateUniqueId() != null) 
				probeInfo.text(I18n.translateToLocal("text.waila.mated"));

			if (this.getFertile() && !this.getPregnant())
			{
				probeInfo.text(I18n.translateToLocal("text.waila.fertile1"));
			} 

			if (this.getPregnant())
			{
				if (this.getGestation() > 1) {
					int bob = this.getGestation();
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1") + " (" + bob + " " + I18n.translateToLocal("text.waila.pregnant2") + ")" );
				} else {
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1"));
				}
			} 
		}
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

	@Override
	public DataParameter<Integer> getGestationParam()
	{
		return GESTATION_TIMER;
	}

	@Override
	public DataParameter<Boolean> getPregnantParam()
	{
		return PREGNANT;
	}

	@Override
	public DataParameter<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}

}
