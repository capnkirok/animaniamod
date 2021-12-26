package com.animania.addons.farm.common.entity.cows;

import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.cows.ai.AttackMeleeBullsGoal;
import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityEntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBullBase extends EntityAnimaniaCow implements TOPInfoProviderMateable, IMateable, ISterilizable
{

	protected static final EntityDataAccessor<Boolean> FIGHTING = SynchedEntityData.<Boolean> defineId(EntityBullBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> STERILIZED = SynchedEntityData.<Boolean> defineId(EntityBullBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.<Optional<UUID>> defineId(EntityBullBase.class, EntityEntityDataSerializers.OPTIONAL_UUID);

	public EntityBullBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(1.6F, 1.8F);
		this.width = 1.6F;
		this.height = 1.8F;
		this.gender = EntityGender.MALE;
		this.stepHeight = 1.1F;
		this.mateable = true;

		if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !this.getSterilized())
		{
			this.goalSelector.addGoal(0, new AttackMeleeBullsGoal(this, 1.8D, false));
		}
		// this.goalSelector.addGoal(1, new FollowMateCowsGoal(this, 1.1D));
		if (!this.getSterilized())
			this.goalSelector.addGoal(3, new GenericAIMate<EntityBullBase, CowEntityBase>(this, 1.0D, CowEntityBase.class, EntityCalfBase.class, EntityAnimaniaCow.class));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(EntityBullBase.FIGHTING, false);
		this.entityData.register(EntityBullBase.STERILIZED, false);
		this.entityData.register(EntityBullBase.MATE_UNIQUE_ID, Optional.<UUID> absent());

	}

	@Override
	public void setInLove(Player player)
	{
		if (!this.getFighting() && !this.getSleeping())
		{
			this.level.broadcastEntityEvent(this, (byte) 18);
		}
	}

	public boolean getFighting()
	{
		return this.getBoolFromDataManager(FIGHTING);
	}

	public void setFighting(boolean fighting)
	{
		this.entityData.set(EntityBullBase.FIGHTING, fighting);
	}

	@Override
	public void setAttackTarget(@Nullable LivingEntity LivingEntityIn)
	{
		super.setAttackTarget(LivingEntityIn);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.getSleeping())
		{
			this.setSleeping(false);
		}

		if (this.isEntityInvulnerable(source))
			return false;
		else
			return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = false;
		if (this.canEntityBeSeen(entityIn) && this.getDistance(entityIn) <= 2.0F)
		{
			flag = entityIn.attackEntityFrom(new EntityDamageSource("bull", this), 5.0F);

			if (flag)
				this.applyEnchantments(this, entityIn);

			// Custom Knockback
			if (entityIn instanceof Player)
				((LivingEntity) entityIn).knockBack(this, 1, (this.getX() - entityIn.getX()) / 2, (this.getZ() - entityIn.getZ()) / 2);
		}

		return flag;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, FarmAddonSoundHandler.bullMoo1, FarmAddonSoundHandler.bullMoo2, FarmAddonSoundHandler.bullMoo3, FarmAddonSoundHandler.bullMoo4, FarmAddonSoundHandler.bullMoo5, FarmAddonSoundHandler.bullMoo6, FarmAddonSoundHandler.bullMoo7, FarmAddonSoundHandler.bullMoo8, FarmAddonSoundHandler.moo4, FarmAddonSoundHandler.moo8, FarmAddonSoundHandler.moo4, FarmAddonSoundHandler.moo8);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.angryBull1, FarmAddonSoundHandler.angryBull2, FarmAddonSoundHandler.angryBull3);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return Animania.RANDOM.nextBoolean() ? FarmAddonSoundHandler.cowDeath1 : FarmAddonSoundHandler.cowDeath2;
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateMateable(this, CowEntityBase.class);

		super.onLivingUpdate();
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public float getHeadAnchorPointY(float p_70894_1_)
	{
		if (this.getFighting())
			return 0;
		else
			return super.getHeadAnchorPointY(p_70894_1_);
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public float getHeadAngleX(float p_70890_1_)
	{
		if (this.getFighting())
			return this.eatTimer > 0 ? (float) Math.PI / 5F : this.rotationPitch * 0.017453292F;
		else
			return super.getHeadAngleX(p_70890_1_);
	}

	@Override
	public EntityBullBase createChild(AgeableEntity p_90011_1_)
	{
		return null;
	}

	@Override
	public void writeEntityToNBT(CompoundTag compound)
	{
		super.writeEntityToNBT(compound);

		compound.putBoolean("Fighting", this.getFighting());
	}

	@Override
	public void readEntityFromNBT(CompoundTag compound)
	{
		super.readEntityFromNBT(compound);

		this.setFighting(compound.getBoolean("Fighting"));
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking() && this.getMateUniqueId() != null)
			probeInfo.text(I18n.translateToLocal("text.waila.mated"));
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, level, entity, data);
	}

	@Override
	public EntityDataAccessor<Boolean> getSterilizedParam()
	{
		return STERILIZED;
	}

	@Override
	public void sterilize()
	{
		Iterator<EntityAITaskEntry> it = this.tasks.taskEntries.iterator();
		while (it.hasNext())
		{
			EntityAITaskEntry entry = it.next();
			Goal ai = entry.action;
			if (ai instanceof GenericAIMate || ai instanceof AttackMeleeBullsGoal)
			{
				entry.using = false;
				ai.resetTask();
				it.remove();
			}
		}
		this.setSterilized(true);
	}

	@Override
	public EntityDataAccessor<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}

}
