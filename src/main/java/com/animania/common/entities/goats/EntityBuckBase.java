package com.animania.common.entities.goats;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.ModSoundEvents;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.goats.ai.EntityAIButtHeadsGoats;
import com.animania.common.entities.goats.ai.EntityAIGoatsLeapAtTarget;
import com.animania.common.entities.goats.ai.EntityAIMateGoats;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBuckBase extends EntityAnimaniaGoat implements TOPInfoProviderMateable
{

	protected static final DataParameter<Boolean> FIGHTING = EntityDataManager.<Boolean>createKey(EntityBuckBase.class, DataSerializers.BOOLEAN);

	public EntityBuckBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.0F, 1.0F);
		this.stepHeight = 1.1F;
		this.mateable = true;
		this.headbutting = true;
		this.gender = EntityGender.MALE;
	}
	
	@Override
	protected void initEntityAI()
	{
		super.initEntityAI();
		this.tasks.addTask(3, new EntityAIButtHeadsGoats(this, 1.3D));
		this.tasks.addTask(3, new EntityAIGoatsLeapAtTarget(this, 0.25F));
		this.tasks.addTask(5, new EntityAIMateGoats(this, 1.0D));
		

	}
	
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.265D);
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityBuckBase.FIGHTING, Boolean.valueOf(false));
	}
	
	public boolean getFighting()
	{
		return this.dataManager.get(EntityBuckBase.FIGHTING).booleanValue();
	}

	public void setFighting(boolean fighting)
	{
		if (fighting)
			this.dataManager.set(EntityBuckBase.FIGHTING, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityBuckBase.FIGHTING, Boolean.valueOf(false));
	}

	
	@Nullable
	public UUID getMateUniqueId()
	{
		return (UUID) ((Optional) this.dataManager.get(EntityBuckBase.MATE_UNIQUE_ID)).orNull();
	}

	public void setMateUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(EntityBuckBase.MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}
	
	@Nullable
	public UUID getRivalUniqueId()
	{
		return (UUID) ((Optional) this.dataManager.get(EntityBuckBase.RIVAL_UNIQUE_ID)).orNull();
	}

	public void setRivalUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(EntityBuckBase.RIVAL_UNIQUE_ID, Optional.fromNullable(uniqueId));
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
			return ModSoundEvents.hog1;
		else if (chooser == 1)
			return ModSoundEvents.hog2;
		else if (chooser == 2)
			return ModSoundEvents.hog3;
		else if (chooser == 3)
			return ModSoundEvents.hog4;
		else if (chooser == 4)
			return ModSoundEvents.hog5;
		else if (chooser == 5)
			return ModSoundEvents.pig1;
		else if (chooser == 6)
			return ModSoundEvents.pig2;
		else if (chooser == 7)
			return ModSoundEvents.pig4;
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
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
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
					String mate = this.getMateUniqueId().toString();
					boolean mateReset = true;

					List<EntityLivingBase> entities = AnimaniaHelper.getEntitiesInRange(EntityDoeBase.class, 64, world, this);
					for (int k = 0; k <= entities.size() - 1; k++)
					{
						Entity entity = entities.get(k);
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
		}
		else
			return this.eatTimer > 0 ? (float) Math.PI / 5F : this.rotationPitch * 0.017453292F;
	}
	
	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && (EntityAnimaniaGoat.TEMPTATION_ITEMS.contains(stack.getItem()));
	}
	
	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

}
