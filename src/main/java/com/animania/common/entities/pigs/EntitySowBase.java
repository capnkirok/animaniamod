package com.animania.common.entities.pigs;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.ModSoundEvents;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.horses.EntityFoalDraftHorse;
import com.animania.common.entities.horses.EntityStallionBase;
import com.animania.common.entities.pigs.ai.EntityAIMatePigs;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderPig;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySowBase extends EntityAnimaniaPig implements TOPInfoProviderPig
{
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntitySowBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected int gestationTimer;

	public EntitySowBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.1F, 1.0F);
		this.stepHeight = 1.1F;
		this.gestationTimer = AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200);
		this.gender = EntityGender.FEMALE;
	}

	@Override
	protected void initEntityAI()
	{
		super.initEntityAI();
		this.tasks.addTask(8, new EntityAIMatePigs(this, 1.0D));

	}

	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{

		if (this.world.isRemote)
			return null;

		int pigCount = 0;
		List entities = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaPig.class, 128, this.world, this);
		pigCount = entities.size();

		if (pigCount <= AnimaniaConfig.spawn.spawnLimitPigs)
		{

			int chooser = this.rand.nextInt(5);

			if (chooser == 0)
			{
				EntityHogBase entityPig = this.pigType.getMale(world);
				entityPig.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityPig);
				entityPig.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityPig.getUniqueID());
			}
			else if (chooser == 1)
			{
				EntityPigletBase entityPig = this.pigType.getChild(world);
				entityPig.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityPig);
				entityPig.setParentUniqueId(this.entityUniqueID);
			}
			else if (chooser > 2)
			{
				EntityHogBase entityPig = this.pigType.getMale(world);
				entityPig.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityPig);
				entityPig.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityPig.getUniqueID());
				EntityPigletBase entityPiglet = this.pigType.getChild(world);
				entityPiglet.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityPiglet);
				entityPiglet.setParentUniqueId(this.entityUniqueID);
			}

			this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

			if (this.rand.nextFloat() < 0.05F)
				this.setLeftHanded(true);
			else
				this.setLeftHanded(false);
		}
		return livingdata;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.265D);
	}

	@Override
	@Nullable
	public Entity getControllingPassenger()
	{
		return this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
	}

	@Override
	public boolean canBeSteered()
	{
		Entity entity = this.getControllingPassenger();

		if (!(entity instanceof EntityPlayer))
			return false;
		else
		{
			EntityPlayer entityplayer = (EntityPlayer) entity;
			ItemStack itemstack = entityplayer.getHeldItemMainhand();

			if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.CARROT_ON_A_STICK)
				return true;
			else
			{
				itemstack = entityplayer.getHeldItemOffhand();
				return itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.CARROT_ON_A_STICK;
			}
		}
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntitySowBase.MATE_UNIQUE_ID, Optional.<UUID>absent());

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		if (this.getMateUniqueId() != null)
			compound.setString("MateUUID", this.getMateUniqueId().toString());

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		String s;

		if (compound.hasKey("MateUUID", 8))
			s = compound.getString("MateUUID");
		else
		{
			String s1 = compound.getString("Mate");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

	}

	@Nullable
	public UUID getMateUniqueId()
	{
		return (UUID) ((Optional) this.dataManager.get(EntitySowBase.MATE_UNIQUE_ID)).orNull();
	}

	public void setMateUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(EntitySowBase.MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		int happy = 0;
		int num = 0;

		if (this.getWatered())
			happy++;
		if (this.getFed())
			happy++;

		if (happy == 2)
			num = 10;
		else if (happy == 1)
			num = 20;
		else
			num = 40;

		Random rand = new Random();
		int chooser = rand.nextInt(num);

		if (chooser == 0)
			return ModSoundEvents.pig1;
		else if (chooser == 1)
			return ModSoundEvents.pig2;
		else if (chooser == 2)
			return ModSoundEvents.pig4;
		else if (chooser == 3)
			return ModSoundEvents.pig5;
		else if (chooser == 4)
			return ModSoundEvents.pig6;
		else if (chooser == 5)
			return ModSoundEvents.pig7;
		else
			return null;

	}

	@Override
	protected SoundEvent getHurtSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.pigHurt1;
		else if (chooser == 1)
			return ModSoundEvents.pigHurt2;
		else
			return ModSoundEvents.pig3;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.pigHurt1;
		else if (chooser == 1)
			return ModSoundEvents.pigHurt2;
		else
			return ModSoundEvents.pig3;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		if (stack != ItemStack.EMPTY && stack.getItem() == Items.CARROT_ON_A_STICK && !this.isBeingRidden() && this.getWatered() && this.getFed())
		{
			player.startRiding(this);
			return true;
		}
		else
			return super.processInteract(player, hand);
	}

	@Override
	public void fall(float distance, float damageMultiplier)
	{
		super.fall(distance, damageMultiplier);

		if (distance > 5.0F)
			for (EntityPlayer entityplayer : this.getRecursivePassengersByType(EntityPlayer.class))
				entityplayer.addStat(AchievementList.FLY_PIG);
	}

	@Override
	public void moveEntityWithHeading(float strafe, float forward)
	{
		Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);

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
				float f = (float) this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 0.5F;

				if (this.boosting)
				{
					if (this.boostTime++ > this.totalBoostTime)
						this.boosting = false;

					f += f * 1.15F * MathHelper.sin((float) this.boostTime / (float) this.totalBoostTime * (float) Math.PI);
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
				f1 = 1.0F;

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
	public void onLivingUpdate()
	{

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
			{
				this.blinkTimer = 200 + this.rand.nextInt(200);

				// Check for Mate
				if (this.getMateUniqueId() != null)
				{
					String mate = this.getMateUniqueId().toString();
					boolean mateReset = true;

					int esize = this.world.loadedEntityList.size();
					for (int k = 0; k <= esize - 1; k++)
					{
						Entity entity = this.world.loadedEntityList.get(k);
						if (entity != null)
						{
							UUID id = entity.getPersistentID();
							if (id.toString().equals(this.getMateUniqueId().toString()) && !entity.isDead)
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

		boolean fed = this.getFed();
		boolean watered = this.getWatered();
		boolean played = this.getPlayed();

		if (this.gestationTimer > -1 && this.getMateUniqueId() != null)
		{
			this.gestationTimer--;
			if (this.gestationTimer == 0)
			{
				this.gestationTimer = AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(2000);
				UUID MateID = this.getMateUniqueId();
				List entities = AnimaniaHelper.getEntitiesInRange(EntityHogBase.class, 16, this.world, this);
				int esize = entities.size();
				for (int k = 0; k <= esize - 1; k++) 
				{
					EntityHogBase entity = (EntityHogBase)entities.get(k);
					if (entity !=null && this.getFed() && this.getWatered() && entity.getPersistentID().equals(MateID)) {

						this.setInLove(null);

						if (!this.world.isRemote)
						{

							PigType maleType = ((EntityHogBase) entity).pigType;
							PigType babyType = PigType.breed(maleType, this.pigType);
							EntityPigletBase piglet = babyType.getChild(world);
							piglet.setPosition(this.posX, this.posY + .2, this.posZ);
							this.world.spawnEntity(piglet);
							piglet.setParentUniqueId(this.getPersistentID());
							this.playSound(ModSoundEvents.piglet1, 0.50F, 1.1F);

						}
					}
				}
			}
		}

		super.onLivingUpdate();
	}

	public boolean boost()
	{
		if (this.boosting)
			return false;
		else
		{
			this.boosting = true;
			this.boostTime = 0;
			this.totalBoostTime = this.getRNG().nextInt(841) + 140;
			return true;
		}
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

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		return this.eatTimer <= 0 ? 0.0F : this.eatTimer >= 4 && this.eatTimer <= 76 ? 1.0F : this.eatTimer < 4 ? (this.eatTimer - p_70894_1_) / 4.0F : -(this.eatTimer - 80 - p_70894_1_) / 4.0F;
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 76)
		{
			float f = (this.eatTimer - 4 - p_70890_1_) / 24.0F;
			return (float) Math.PI / 5F + (float) Math.PI * 7F / 150F * MathHelper.sin(f * 28.7F);
		}
		else
			return this.eatTimer > 0 ? (float) Math.PI / 5F : this.rotationPitch * 0.017453292F;
	}

	@Override
	public EntitySowBase createChild(EntityAgeable ageable)
	{
		return null;
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed
	 * it (wheat, carrots or seeds depending on the animal type)
	 */
	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && (EntityAnimaniaPig.TEMPTATION_ITEMS.contains(stack.getItem()) || ItemStack.areItemStacksEqual(stack, this.slop));
	}
}
