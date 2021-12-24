package com.animania.addons.farm.common.entity.chickens;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.addons.farm.common.entity.chickens.ai.FindNestGoal;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.api.data.EntityGender;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.animania.config.AnimaniaConfig;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityEntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;

public class EntityHenBase extends EntityAnimaniaChicken implements TOPInfoProviderBase
{

	protected static final EntityDataAccessor<Boolean> LAID = SynchedEntityData.<Boolean> defineId(EntityHenBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> LAID_TIMER = SynchedEntityData.<Integer> defineId(EntityHenBase.class, EntityEntityDataSerializers.INT);

	public EntityHenBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(0.5F, 0.7F);
		this.width = 0.5F;
		this.height = 0.7F;
		this.goalSelector.addGoal(6, new FindNestGoal(this, 1.0D));
		this.goalSelector.addGoal(9, new LeapAtTargetGoal(this, 0.2F));
		this.goalSelector.addGoal(10, new AttackMeleeGoal(this, 1.0D, true));
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers)
		{
			AddonInjectionHandler.runInjection("extra", "attackFrogs", null, this);
		}
		this.gender = EntityGender.FEMALE;

	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata)
	{
		if (this.level.isRemote)
			return null;

		List<EntityAnimaniaChicken> others = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaChicken.class, 64, this.level, this.getPosition());

		if (others.size() <= 4)
		{

			int chooser = this.rand.nextInt(3);

			if (chooser == 0)
			{
				EntityRoosterBase ChickenEntity = this.type.getMale(level);
				ChickenEntity.setPosition(this.getX(), this.getY(), this.getZ());
				AnimaniaHelper.spawnEntity(this.level, ChickenEntity);
			} else if (chooser == 1)
			{
				EntityChickBase ChickenEntity = this.type.getChild(level);
				ChickenEntity.setPosition(this.getX(), this.getY(), this.getZ());
				AnimaniaHelper.spawnEntity(this.level, ChickenEntity);
			}

		}

		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

		if (this.rand.nextFloat() < 0.05F)
			this.setLeftHanded(true);
		else
			this.setLeftHanded(false);

		return livingdata;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{

		if (this.getSleeping())
		{
			this.setSleeping(false);
		}

		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);

		if (flag)
		{
			this.applyEnchantments(this, entityIn);
		}

		AddonInjectionHandler.runInjection("extra", "eatFrogs", null, entityIn, this);

		// Custom Knockback
		if (entityIn instanceof PlayerEntity)
		{
			((LivingEntity) entityIn).knockBack(this, 1, this.getX() - entityIn.getX(), this.getZ() - entityIn.getZ());
		}

		return flag;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5D);

	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityHenBase.LAID, true);
		this.dataManager.register(EntityHenBase.LAID_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.laidTimer / 2 + 0 + this.rand.nextInt(100)));
		this.timeUntilNextEgg = 6000;
	}

	@Override
	public void writeEntityToNBT(CompoundTag CompoundTag)
	{
		super.writeEntityToNBT(CompoundTag);
		CompoundTag.putBoolean("Laid", this.getLaid());
		CompoundTag.putInteger("EggLayTime", timeUntilNextEgg);
		CompoundTag.putInteger("LaidTimer", this.getLaidTimer());
	}

	@Override
	public void readEntityFromNBT(CompoundTag CompoundTag)
	{
		super.readEntityFromNBT(CompoundTag);
		this.timeUntilNextEgg = CompoundTag.getInteger("EggLayTime");
		this.setLaid(CompoundTag.getBoolean("Laid"));
		this.setLaidTimer(CompoundTag.getInteger("LaidTimer"));
	}

	public int getLaidTimer()
	{
		return this.getIntFromDataManager(LAID_TIMER);
	}

	public void setLaidTimer(int laidtimer)
	{
		this.dataManager.set(EntityHenBase.LAID_TIMER, Integer.valueOf(laidtimer));
	}

	@Override
	public void onLivingUpdate()
	{

		if (!FarmConfig.settings.chickensDropEggs)
		{
			this.timeUntilNextEgg = 1000;
		}

		super.onLivingUpdate();

		int laidTimer = this.getLaidTimer();

		if (laidTimer > -1)
		{
			laidTimer--;
			this.setLaidTimer(laidTimer);
		} else
		{
			this.setLaid(false);
		}
	}

	public boolean getLaid()
	{
		return this.getBoolFromDataManager(LAID);
	}

	public void setLaid(boolean laid)
	{
		if (laid)
		{
			this.dataManager.set(EntityHenBase.LAID, true);
			this.setLaidTimer(AnimaniaConfig.careAndFeeding.laidTimer + this.rand.nextInt(100));
		} else
			this.dataManager.set(EntityHenBase.LAID, false);
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume() - .3F, this.getSoundPitch());
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, Level level, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking())
		{

			EntityHenBase ehb = (EntityHenBase) entity;
			int timer = ehb.getLaidTimer();
			if (timer >= 0)
			{
				probeInfo.text(I18n.translateToLocal("text.waila.egglay") + ": " + timer);
			} else
			{
				probeInfo.text(I18n.translateToLocal("text.waila.egglay2"));
			}
		}
		TOPInfoProviderBase.super.addProbeInfo(mode, probeInfo, player, level, entity, data);
	}

}
