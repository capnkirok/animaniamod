package com.animania.addons.catsdogs.common.entity.cats;

import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.generic.ai.GenericAIFollowParents;
import com.animania.common.entities.interfaces.IChild;
import com.animania.common.entities.pigs.EntityHogBase;
import com.animania.common.entities.pigs.EntityPigletBase;
import com.animania.common.entities.pigs.EntitySowBase;
import com.animania.compat.top.providers.entity.TOPInfoProviderChild;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityKittenBase extends EntityAnimaniaCat implements TOPInfoProviderChild, IChild
{

	protected static final DataParameter<Optional<UUID>> PARENT_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityKittenBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected static final DataParameter<Float> AGE = EntityDataManager.<Float>createKey(EntityKittenBase.class, DataSerializers.FLOAT);
	protected int ageTimer;

	
	public EntityKittenBase(World worldIn, CatType type)
	{
		super(worldIn);
		this.setSize(0.8f, 0.8f); 
		this.width = 0.8f;
		this.height = 0.8f;
		this.stepHeight = 1.1F;
		this.ageTimer = 0;
		this.gender = EntityGender.CHILD;
		this.type = type;
		this.tasks.addTask(1, new GenericAIFollowParents<EntityKittenBase, EntityQueenBase>(this, 1.1D, EntityQueenBase.class));


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
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.315D);
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(AGE, Float.valueOf(0));
		this.dataManager.register(PARENT_UNIQUE_ID, Optional.<UUID>absent());

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setFloat("Age", this.getEntityAge());
		if (this.getParentUniqueId() != null)
			if (this.getParentUniqueId() != null)
				compound.setString("ParentUUID", this.getParentUniqueId().toString());

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.setEntityAge(compound.getFloat("Age"));
		String s;

		if (compound.hasKey("ParentUUID", 8))
			s = compound.getString("ParentUUID");
		else
		{
			String s1 = compound.getString("Parent");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty())
			this.setParentUniqueId(UUID.fromString(s));

	}
	
	@Nullable
	public UUID getParentUniqueId()
	{
		try
		{
			UUID id = (UUID) ((Optional) this.dataManager.get(PARENT_UNIQUE_ID)).orNull();
			return id;
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public void setParentUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(PARENT_UNIQUE_ID, Optional.fromNullable(uniqueId));
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
		this.dataManager.set(AGE, Float.valueOf(age));
	}

	
	//TODO: SOUND
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
			return ModSoundEvents.piglet1;
		else if (chooser == 1)
			return ModSoundEvents.piglet2;
		else if (chooser == 2)
			return ModSoundEvents.piglet3;
		else if (chooser == 3)
			return ModSoundEvents.pig1;
		else if (chooser == 4)
			return ModSoundEvents.pig2;
		else
			return null;

	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		int chooser = Animania.RANDOM.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.pigletHurt1;
		else if (chooser == 1)
			return ModSoundEvents.pigletHurt2;
		else
			return ModSoundEvents.pigletHurt3;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		int chooser = Animania.RANDOM.nextInt(3);

		if (chooser == 0)
			return ModSoundEvents.pigletHurt1;
		else if (chooser == 1)
			return ModSoundEvents.pigletHurt2;
		else
			return ModSoundEvents.pigletHurt3;
	}
	
	@Override
	public void onLivingUpdate()
	{

		boolean fed = this.getFed();
		boolean watered = this.getWatered();
		this.growingAge = -24000;
		this.ageTimer++;
		if (this.ageTimer >= AnimaniaConfig.careAndFeeding.childGrowthTick)
		{
			if (fed && watered)
			{
				this.ageTimer = 0;
				float age = this.getEntityAge();
				age = age + .01F;
				this.setEntityAge(age);

				if (age >= .8 && !this.world.isRemote)
				{
					this.setDead();

					if (this.rand.nextInt(2) < 1)
					{
						EntityQueenBase female = this.type.getFemale(world);
						if (female != null)
						{
							female.setPosition(this.posX, this.posY + .5, this.posZ);
							String name = this.getCustomNameTag();
							if (name != "")
								female.setCustomNameTag(name);
							
							female.setAge(1);
							this.world.spawnEntity(female);
							this.playLivingSound();
						}
					}
					else
					{
						EntityTomBase entityPig = this.type.getMale(world);
						if (entityPig != null)
						{
							entityPig.setPosition(this.posX, this.posY + .5, this.posZ);
							String name = this.getCustomNameTag();
							if (name != "")
								entityPig.setCustomNameTag(name);
							
							entityPig.setAge(1);
							this.world.spawnEntity(entityPig);
							this.playLivingSound();
						}
					}

				}
			}
		}

		super.onLivingUpdate();
	}

}
