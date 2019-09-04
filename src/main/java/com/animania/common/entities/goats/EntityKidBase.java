package com.animania.common.entities.goats;

import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.goats.ai.EntityAIFollowParentGoats;
import com.animania.compat.top.providers.entity.TOPInfoProviderChild;
import com.google.common.base.Optional;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityKidBase extends EntityAnimaniaGoat implements TOPInfoProviderChild, IChild
{

	protected static final DataParameter<Optional<UUID>> PARENT_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityKidBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Float> AGE = EntityDataManager.<Float>createKey(EntityKidBase.class, DataSerializers.FLOAT);
	protected int ageTimer;

	public EntityKidBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.0F, 1.0F); 
		this.width = 1.0F;
		this.height = 1.0F;
		this.stepHeight = 1.1F;
		this.ageTimer = 0;
		this.gender = EntityGender.CHILD;
		this.tasks.addTask(3, new EntityAIFollowParentGoats(this, 1.1D));

	}

	@Override
	public boolean isChild()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.315D);

	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityKidBase.AGE, Float.valueOf(0));
		this.dataManager.register(EntityKidBase.PARENT_UNIQUE_ID, Optional.<UUID>absent());

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);

		if (this.getParentUniqueId() != null)
			if (this.getParentUniqueId() != null)
				compound.setString("ParentUUID", this.getParentUniqueId().toString());

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		
		

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

		int chooser = Animania.RANDOM.nextInt(num);

		if (chooser == 0)
			return ModSoundEvents.kidLiving1;
		else if (chooser == 1)
			return ModSoundEvents.kidLiving2;
		else if (chooser == 2)
			return ModSoundEvents.kidLiving3;
		else
			return null;

	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		int chooser = Animania.RANDOM.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.kidHurt1;
		else if (chooser == 1)
			return ModSoundEvents.kidHurt2;
		else
			return null;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		int chooser = Animania.RANDOM.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.kidHurt1;
		else 
			return ModSoundEvents.kidHurt2;

	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
	}
	
	@Override
	public int getAgeTimer()
	{
		return ageTimer;
	}

	@Override
	public void setAgeTimer(int i)
	{
		ageTimer = i;
	}
	
	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateChild(this, null);

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
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		return;
	}

	@Override
	protected Item getDropItem()
	{
		return null;
	}

	@Override
	public DataParameter<Optional<UUID>> getParentUniqueIdParam()
	{
		return PARENT_UNIQUE_ID;
	}

	@Override
	public DataParameter<Float> getEntityAgeParam()
	{
		return AGE;
	}

}
