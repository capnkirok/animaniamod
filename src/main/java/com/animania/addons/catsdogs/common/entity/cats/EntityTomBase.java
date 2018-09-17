package com.animania.addons.catsdogs.common.entity.cats;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.addons.catsdogs.config.CatsDogsConfig;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.IMateable;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.google.common.base.Optional;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityTomBase extends EntityAnimaniaCat implements TOPInfoProviderMateable, IMateable
{
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityTomBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	public EntityTomBase(World worldIn, CatType type)
	{
		super(worldIn);
		this.setSize(1.0F, 1.0F);
		this.stepHeight = 1.1F;
		this.gender = EntityGender.MALE;
		this.type = type;
		this.tasks.addTask(8, new GenericAIMate<EntityTomBase, EntityQueenBase>(this, 1.0D, EntityQueenBase.class, EntityKittenBase.class, EntityAnimaniaCat.class, CatsDogsConfig.catsdogs.spawnLimitCats));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(MATE_UNIQUE_ID, Optional.<UUID>absent());

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

	@Override
	@Nullable
	public UUID getMateUniqueId()
	{
		try
		{
			UUID id = (UUID) ((Optional) this.dataManager.get(MATE_UNIQUE_ID)).orNull();
			return id;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public void setMateUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	// TODO: SOUNDS
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
	protected SoundEvent getHurtSound(DamageSource source)
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
	public void onLivingUpdate()
	{
		if (this.isBeingRidden() && this.getSleeping())
			this.setSleeping(false);

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

					List<EntityQueenBase> entities = AnimaniaHelper.getEntitiesInRange(EntityQueenBase.class, 20, world, this);
					for (int k = 0; k <= entities.size() - 1; k++)
					{
						EntityQueenBase female = entities.get(k);
						if (female != null)
						{
							UUID id = female.getPersistentID();
							if (id.equals(this.getMateUniqueId()) && !female.isDead)
							{
								mateReset = false;
								if (female.getPregnant())
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
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && (TEMPTATION_ITEMS.contains(stack.getItem()));
	}

}
