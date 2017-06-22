package com.animania.common.entities.chickens;

import com.animania.common.ModSoundEvents;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityChickBase extends EntityAnimaniaChicken implements TOPInfoProviderBase
{

	protected static final DataParameter<Float> AGE = EntityDataManager.<Float>createKey(EntityChickBase.class, DataSerializers.FLOAT);
	protected int ageTimer;

	public EntityChickBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.3F, 0.4F);
		this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
		this.ageTimer = 0;
		this.type = ChickenType.LEGHORN;

	}

	@Override
	public boolean isChild()
	{
		return true;
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return null;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityChickBase.AGE, Float.valueOf(0));
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
						EntityHenBase entityHen = type.getFemale(world);
						if (entityHen != null)
						{
							entityHen.setPosition(this.posX, this.posY + .5, this.posZ);
							String name = this.getCustomNameTag();
							if (name != "")
								entityHen.setCustomNameTag(name);
							this.world.spawnEntity(entityHen);
							this.playSound(ModSoundEvents.chickenHurt1, 0.50F, 1.1F);
						}
					}
					else
					{
						EntityRoosterBase entityRooster = type.getMale(world);
						if (entityRooster != null)
						{
							entityRooster.setPosition(this.posX, this.posY + .5, this.posZ);
							String name = this.getCustomNameTag();
							if (name != "")
								entityRooster.setCustomNameTag(name);
							this.world.spawnEntity(entityRooster);
							this.playSound(ModSoundEvents.chickenCrow1, 0.50F, 1.1F);
						}
					}

				}
			}

		super.onLivingUpdate();
	}

	public float getEntityAge()
	{
		return this.dataManager.get(EntityChickBase.AGE).floatValue();
	}

	public void setEntityAge(float age)
	{
		this.dataManager.set(EntityChickBase.AGE, Float.valueOf(age));
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume() - .3F, this.getSoundPitch() + .9F - this.getEntityAge() * 2);
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

}
