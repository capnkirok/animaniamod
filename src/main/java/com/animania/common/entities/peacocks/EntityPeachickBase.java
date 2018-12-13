package com.animania.common.entities.peacocks;

import com.animania.api.data.EntityGender;
import com.animania.common.ModSoundEvents;
import com.animania.config.AnimaniaConfig;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPeachickBase extends EntityAnimaniaPeacock
{
	protected static final DataParameter<Float> AGE = EntityDataManager.<Float>createKey(EntityPeachickBase.class, DataSerializers.FLOAT);
	protected int ageTimer;

	public EntityPeachickBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.7F, 1.0F); 
		this.width = 0.7F;
		this.height = 1.0F;
		this.ageTimer = 0;
		this.type = PeacockType.BLUE;
		this.gender = EntityGender.CHILD;

	}

	@Override
	public boolean isChild()
	{
		return true;
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
		this.dataManager.register(EntityPeachickBase.AGE, Float.valueOf(0));

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setFloat("Age", this.getEntityAge());

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		this.setEntityAge(nbttagcompound.getFloat("Age"));

	}

	@Override
	public void onLivingUpdate()
	{
		boolean fed = this.getFed();
		boolean watered = this.getWatered();
		this.growingAge = -24000;
		this.ageTimer++;
		if (this.ageTimer >= AnimaniaConfig.careAndFeeding.childGrowthTick)
			if (fed && watered)
			{
				this.ageTimer = 0;
				float age = this.getEntityAge();
				age = age + .01F;
				this.setEntityAge(age);

				if (age >= .4 && !this.world.isRemote)
				{

					this.setDead();

					if (this.rand.nextInt(2) < 1)
					{
						EntityPeafowlBase entityFowl = type.getFemale(world);
						if (entityFowl != null)
						{
							entityFowl.setPosition(this.posX, this.posY + .5, this.posZ);
							String name = this.getCustomNameTag();
							if (name != "")
								entityFowl.setCustomNameTag(name);
							
							entityFowl.setAge(1);
							this.world.spawnEntity(entityFowl);
							this.playSound(ModSoundEvents.peacock1, 0.50F, 1.1F);
						}
					}
					else
					{
						EntityPeacockBase entityPeacock = type.getMale(world);
						if (entityPeacock != null)
						{
							entityPeacock.setPosition(this.posX, this.posY + .5, this.posZ);
							String name = this.getCustomNameTag();
							if (name != "")
								entityPeacock.setCustomNameTag(name);
							
							entityPeacock.setAge(1);
							this.world.spawnEntity(entityPeacock);
							this.playSound(ModSoundEvents.peacock8, 0.50F, 1.1F);
						}
					}

				}
			}

		super.onLivingUpdate();
	}

	public float getEntityAge()
	{
		try {
			return (this.getFloatFromDataManager(AGE));
		}
		catch (Exception e) {
			return 0F;
		}
	}

	public void setEntityAge(float age)
	{
		this.dataManager.set(EntityPeachickBase.AGE, Float.valueOf(age));
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume() - .9F, this.getSoundPitch() + .4F - this.getEntityAge() * 2);
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		return;
	}

}
