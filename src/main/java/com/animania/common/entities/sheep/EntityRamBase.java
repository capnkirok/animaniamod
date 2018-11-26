package com.animania.common.entities.sheep;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.Animania;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.cows.EntityBullBase;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.common.entities.sheep.ai.EntityAIButtHeadsSheep;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

public class EntityRamBase extends EntityAnimaniaSheep implements TOPInfoProviderMateable, IMateable, ISterilizable
{
	protected static final DataParameter<Boolean> FIGHTING = EntityDataManager.<Boolean> createKey(EntityRamBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> STERILIZED = EntityDataManager.<Boolean> createKey(EntityRamBase.class, DataSerializers.BOOLEAN);

	public EntityRamBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.2F, 1.0F); 
		this.width = 1.2F;
		this.height = 1.0F;
		this.stepHeight = 1.1F;
		this.gender = EntityGender.MALE;
		this.headbutting = true;
		this.mateable = true;
		// this.tasks.addTask(3, new EntityAIFollowMateSheep(this, 1.1D));
		if (!getSterilized())
			this.tasks.addTask(5, new GenericAIMate<EntityRamBase, EntityEweBase>(this, 1.0D, EntityEweBase.class, EntityLambBase.class, EntityAnimaniaSheep.class));

		if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !getSterilized())
		{
			this.tasks.addTask(3, new EntityAIButtHeadsSheep(this, 1.3D));
		}
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityRamBase.FIGHTING, Boolean.valueOf(false));
		this.dataManager.register(STERILIZED, Boolean.valueOf(false));

	}

	public boolean getFighting()
	{
		try
		{
			return (this.getBoolFromDataManager(FIGHTING));
		} catch (Exception e)
		{
			return false;
		}
	}

	public void setFighting(boolean fighting)
	{
		if (fighting)
			this.dataManager.set(EntityRamBase.FIGHTING, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityRamBase.FIGHTING, Boolean.valueOf(false));
	}

	@Nullable
	public UUID getRivalUniqueId()
	{
		return (UUID) ((Optional) this.dataManager.get(EntityRamBase.RIVAL_UNIQUE_ID)).orNull();
	}

	public void setRivalUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(EntityRamBase.RIVAL_UNIQUE_ID, Optional.fromNullable(uniqueId));
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
			num = 10;
		else if (happy == 1)
			num = 20;
		else
			num = 40;

		int chooser = Animania.RANDOM.nextInt(num);

		if (chooser == 0)
			return ModSoundEvents.sheepLiving1;
		else if (chooser == 1)
			return ModSoundEvents.sheepLiving2;
		else if (chooser == 2)
			return ModSoundEvents.sheepLiving3;
		else if (chooser == 3)
			return ModSoundEvents.sheepLiving4;
		else if (chooser == 4)
			return ModSoundEvents.sheepLiving5;
		else if (chooser == 5)
			return ModSoundEvents.sheepLiving6;
		else if (chooser == 6)
			return ModSoundEvents.sheepLiving7;
		else
			return null;

	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		int chooser = Animania.RANDOM.nextInt(2);

		if (chooser == 0)
			return ModSoundEvents.sheepHurt1;
		else
			return ModSoundEvents.sheepLiving7;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		int chooser = Animania.RANDOM.nextInt(3);
		if (chooser == 0)
			return ModSoundEvents.sheepHurt1;
		else
			return ModSoundEvents.sheepLiving7;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .21F);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.10F, 0.8F);
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
					UUID mate = this.getMateUniqueId();
					boolean mateReset = true;

					List<EntityLivingBase> entities = AnimaniaHelper.getEntitiesInRange(EntityEweBase.class, 30, world, this);
					for (int k = 0; k <= entities.size() - 1; k++)
					{
						Entity entity = entities.get(k);
						if (entity != null)
						{
							UUID id = entity.getPersistentID();
							if (id.equals(this.getMateUniqueId()) && !entity.isDead)
							{
								mateReset = false;

								EntityEweBase ewe = (EntityEweBase) entity;
								if (ewe.getPregnant())
								{
									this.setHandFed(false);
								}

								break;
							}
						}
					}

					if (mateReset)
						this.setMateUniqueId(null);

				}
			}
		}

		super.onLivingUpdate();
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
		} else
			return this.eatTimer > 0 ? (float) Math.PI / 5F : this.rotationPitch * 0.017453292F;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && (EntityAnimaniaSheep.TEMPTATION_ITEMS.contains(stack.getItem()));
	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking())
		{
			if (this.getSheared())
			{
				if (this.getWoolRegrowthTimer() > 0)
				{
					int bob = this.getWoolRegrowthTimer();
					probeInfo.text(I18n.translateToLocal("text.waila.wool1") + " (" + bob + " " + I18n.translateToLocal("text.waila.wool2") + ")");
				}
			} else if (!this.getSheared())
			{
				probeInfo.text(I18n.translateToLocal("text.waila.wool3"));
			}

		}

		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

	@Override
	public boolean getSterilized()
	{
		return this.getBoolFromDataManager(STERILIZED);
	}

	@Override
	public void setSterilized(boolean sterilized)
	{
		this.dataManager.set(STERILIZED, Boolean.valueOf(sterilized));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setBoolean("Sterilized", getSterilized());
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		this.setSterilized(compound.getBoolean("Sterilized"));
		super.readFromNBT(compound);
	}

	@Override
	public void sterilize()
	{
		Iterator<EntityAITaskEntry> it = this.tasks.taskEntries.iterator();
		while (it.hasNext())
		{
			EntityAITaskEntry entry = it.next();
			EntityAIBase ai = entry.action;
			if (ai instanceof GenericAIMate || ai instanceof EntityAIButtHeadsSheep)
			{
				entry.using = false;
				ai.resetTask();
				it.remove();
			}
		}
		setSterilized(true);
	}

}
