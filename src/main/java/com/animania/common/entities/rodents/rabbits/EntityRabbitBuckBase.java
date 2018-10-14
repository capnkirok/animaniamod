package com.animania.common.entities.rodents.rabbits;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.common.ModSoundEvents;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.IMateable;
import com.animania.common.entities.ISterilizable;
import com.animania.common.entities.cows.EntityBullBase;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;

public class EntityRabbitBuckBase extends EntityAnimaniaRabbit implements TOPInfoProviderMateable, IMateable, ISterilizable
{
	protected static final DataParameter<Boolean> STERILIZED = EntityDataManager.<Boolean> createKey(EntityRabbitBuckBase.class, DataSerializers.BOOLEAN);

	public EntityRabbitBuckBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.7F, 0.6F);
		this.stepHeight = 1.1F;
		this.mateable = true;
		this.gender = EntityGender.MALE;

		if (!getSterilized())
			this.tasks.addTask(6, new GenericAIMate<EntityRabbitBuckBase, EntityRabbitDoeBase>(this, 1.0D, EntityRabbitDoeBase.class, EntityRabbitKitBase.class, EntityAnimaniaRabbit.class));
		// this.tasks.addTask(3, new EntityAIFollowMateRabbits(this, 1.1D));
	}


	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(STERILIZED, Boolean.valueOf(false));

	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.265D);
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
			num = 8;
		else if (happy == 1)
			num = 16;
		else
			num = 32;

		Random rand = new Random();
		int chooser = rand.nextInt(num);

		if (chooser == 0)
			return ModSoundEvents.rabbit1;
		else if (chooser == 1)
			return ModSoundEvents.rabbit2;
		else if (chooser == 2)
			return ModSoundEvents.rabbit3;
		else if (chooser == 3)
			return ModSoundEvents.rabbit4;
		else
			return null;

	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		Random rand = new Random();
		int chooser = rand.nextInt(2);

		if (chooser == 0)
			return ModSoundEvents.rabbitHurt1;
		else
			return ModSoundEvents.rabbitHurt2;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(2);

		if (chooser == 0)
			return ModSoundEvents.rabbitHurt1;
		else
			return ModSoundEvents.rabbitHurt2;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.8F);
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

					List<EntityLivingBase> entities = AnimaniaHelper.getEntitiesInRange(EntityRabbitDoeBase.class, 64, world, this);
					for (int k = 0; k <= entities.size() - 1; k++)
					{
						Entity entity = entities.get(k);
						if (entity != null)
						{
							UUID id = entity.getPersistentID();
							if (id.toString().equals(this.getMateUniqueId().toString()) && !entity.isDead)
							{
								mateReset = false;
								EntityRabbitDoeBase fem = (EntityRabbitDoeBase) entity;
								if (fem.getPregnant())
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
		return stack != ItemStack.EMPTY && (EntityAnimaniaRabbit.TEMPTATION_ITEMS.contains(stack.getItem()));
	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{
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
			if (ai instanceof GenericAIMate)
			{
				entry.using = false;
				ai.resetTask();
				it.remove();
			}
		}
		setSterilized(true);
	}

}
