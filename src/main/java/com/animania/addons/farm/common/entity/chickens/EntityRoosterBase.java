package com.animania.addons.farm.common.entity.chickens;

import java.util.List;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.amphibians.EntityAmphibian;
import com.animania.addons.extra.common.entity.amphibians.EntityFrogs;
import com.animania.addons.extra.common.entity.amphibians.EntityToad;
import com.animania.addons.extra.common.entity.rodents.EntityFerretGrey;
import com.animania.addons.extra.common.entity.rodents.EntityFerretWhite;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehog;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehogAlbino;
import com.animania.addons.farm.common.entity.cows.EntityAnimaniaCow;
import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.api.data.EntityGender;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityRoosterBase extends EntityAnimaniaChicken implements TOPInfoProviderBase
{

	protected static final DataParameter<Integer> CROWTIMER = EntityDataManager.<Integer> createKey(EntityRoosterBase.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer> CROWDURATION = EntityDataManager.<Integer> createKey(EntityRoosterBase.class, DataSerializers.VARINT);

	public EntityRoosterBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.6F, 0.8F);
		this.width = 0.6F;
		this.height = 0.8F;
		this.setTimeUntilNextCrow(this.rand.nextInt(200) + 200);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.2F));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers)
		{
			this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityHedgehog.class, false));
			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityHedgehogAlbino.class, false));
			this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityFerretWhite.class, false));
			this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityFerretGrey.class, false));
			this.targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntityFrogs.class, false));
			this.targetTasks.addTask(8, new EntityAINearestAttackableTarget(this, EntityToad.class, false));
		}
		if (FarmConfig.settings.roostersFight)
			this.targetTasks.addTask(8, new EntityAINearestAttackableTarget(this, EntityRoosterBase.class, 80, false, true, (Predicate) null));

		this.gender = EntityGender.MALE;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);

		if (flag)
			this.applyEnchantments(this, entityIn);

		if (entityIn instanceof EntityAmphibian)
		{
			this.setFed(true);
		}

		// Custom Knockback
		if (entityIn instanceof EntityPlayer)
			((EntityLivingBase) entityIn).knockBack(this, 1, this.posX - entityIn.posX, this.posZ - entityIn.posZ);

		int i = 0b0011;
		int j = 30;

		int k = i ^ j;

		boolean a = true;
		boolean c = false;

		return flag;

	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);

	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityRoosterBase.CROWTIMER, Integer.valueOf(0));
		this.dataManager.register(EntityRoosterBase.CROWDURATION, Integer.valueOf(0));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("CrowTime", this.getTimeUntilNextCrow());
		nbttagcompound.setInteger("CrowDuration", this.getCrowDuration());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		this.setTimeUntilNextCrow(nbttagcompound.getInteger("CrowTime"));
		this.setCrowDuration(nbttagcompound.getInteger("CrowDuration"));
	}

	@Override
	public void onLivingUpdate()
	{
		this.timeUntilNextEgg = 1000;
		long currentTime = this.world.getWorldTime() % 23999;

		if (this.getTimeUntilNextCrow() > 0)
			this.setTimeUntilNextCrow(this.getTimeUntilNextCrow() - 1);

		if ((currentTime > 23250 || currentTime < 500) && this.getTimeUntilNextCrow() == 0)
		{

			float modular = this.rand.nextFloat() * this.rand.nextInt(3);
			boolean direction = this.rand.nextBoolean();

			if (direction)
				modular = modular / 10;
			else
				modular = modular / 10 * -1;

			this.setCrowDuration(50);

			int crowChooser = this.rand.nextInt(3);
			if (crowChooser == 0)
				this.world.playSound(null, this.posX, this.posY, this.posZ, FarmAddonSoundHandler.chickenCrow1, SoundCategory.PLAYERS, 0.7F, 0.95F + modular);
			else if (crowChooser == 1)
				this.world.playSound(null, this.posX, this.posY, this.posZ, FarmAddonSoundHandler.chickenCrow2, SoundCategory.PLAYERS, 0.65F, 0.9F + modular);
			else if (crowChooser == 2)
				this.world.playSound(null, this.posX, this.posY, this.posZ, FarmAddonSoundHandler.chickenCrow3, SoundCategory.PLAYERS, 0.6F, 1.05F + modular);
			this.setTimeUntilNextCrow(this.rand.nextInt(200) + 200);

			List list = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaCow.class, 30, world, this.getPosition());

			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i) instanceof EntityAnimaniaCow)
				{
					EntityAnimaniaCow entityCow = (EntityAnimaniaCow) list.get(i);
					if (entityCow.getSleeping() && currentTime > 24000)
					{
						entityCow.setSleeping(false);
						entityCow.setSleepTimer(0F);
					}
				}
			}

		}

		if (this.getCrowDuration() > 0)
			this.setCrowDuration(this.getCrowDuration() - 1);

		super.onLivingUpdate();
	}

	public int getCrowDuration()
	{
		return this.getIntFromDataManager(CROWDURATION);
	}

	public void setCrowDuration(int duration)
	{
		this.dataManager.set(EntityRoosterBase.CROWDURATION, Integer.valueOf(duration));
	}

	public int getTimeUntilNextCrow()
	{
		return this.getIntFromDataManager(CROWTIMER);
	}

	public void setTimeUntilNextCrow(int timer)
	{
		this.dataManager.set(EntityRoosterBase.CROWTIMER, Integer.valueOf(timer));
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return Animania.RANDOM.nextBoolean() ? FarmAddonSoundHandler.chickenHurt1 : FarmAddonSoundHandler.chickenHurt2;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return Animania.RANDOM.nextBoolean() ? FarmAddonSoundHandler.chickenDeath1 : FarmAddonSoundHandler.chickenDeath2;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume() - .3F, this.getSoundPitch() - .2F);
	}

}
