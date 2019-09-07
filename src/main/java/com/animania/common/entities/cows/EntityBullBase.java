package com.animania.common.entities.cows;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.cows.ai.EntityAIAttackMeleeBulls;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBullBase extends EntityAnimaniaCow implements TOPInfoProviderMateable, IMateable, ISterilizable
{

	protected static final DataParameter<Boolean> FIGHTING = EntityDataManager.<Boolean>createKey(EntityBullBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> STERILIZED = EntityDataManager.<Boolean>createKey(EntityBullBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityBullBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	public EntityBullBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.6F, 1.8F);
		this.width = 1.6F;
		this.height = 1.8F;
		this.gender = EntityGender.MALE;
		this.stepHeight = 1.1F;
		this.mateable = true;

		if (AnimaniaConfig.gameRules.animalsCanAttackOthers && !getSterilized())
		{
			this.tasks.addTask(0, new EntityAIAttackMeleeBulls(this, 1.8D, false));
		}
		// this.tasks.addTask(1, new EntityAIFollowMateCows(this, 1.1D));
		if (!getSterilized())
			this.tasks.addTask(3, new GenericAIMate<EntityBullBase, EntityCowBase>(this, 1.0D, EntityCowBase.class, EntityCalfBase.class, EntityAnimaniaCow.class));
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
		this.dataManager.register(EntityBullBase.FIGHTING, false);
		this.dataManager.register(EntityBullBase.STERILIZED, false);
		this.dataManager.register(EntityBullBase.MATE_UNIQUE_ID, Optional.<UUID>absent());

	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		if (!this.getFighting() && !this.getSleeping())
		{
			this.world.setEntityState(this, (byte) 18);
		}
	}

	public boolean getFighting()
	{
		return this.getBoolFromDataManager(FIGHTING);
	}

	public void setFighting(boolean fighting)
	{
		this.dataManager.set(EntityBullBase.FIGHTING, fighting);
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
	{
		super.setAttackTarget(entitylivingbaseIn);
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
			if (entityIn instanceof EntityPlayer)
				((EntityLivingBase) entityIn).knockBack(this, 1, (this.posX - entityIn.posX) / 2, (this.posZ - entityIn.posZ) / 2);
		}

		return flag;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, ModSoundEvents.bullMoo1, ModSoundEvents.bullMoo2, ModSoundEvents.bullMoo3, ModSoundEvents.bullMoo4, ModSoundEvents.bullMoo5, ModSoundEvents.bullMoo6, ModSoundEvents.bullMoo7, ModSoundEvents.bullMoo8, ModSoundEvents.moo4, ModSoundEvents.moo8, ModSoundEvents.moo4, ModSoundEvents.moo8);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(ModSoundEvents.angryBull1, ModSoundEvents.angryBull2, ModSoundEvents.angryBull3);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return Animania.RANDOM.nextBoolean() ? ModSoundEvents.cowDeath1 : ModSoundEvents.cowDeath2;
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateMateable(this, EntityCowBase.class);

		super.onLivingUpdate();
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{

		if (!this.getFighting())
			return this.eatTimer <= 0 ? 0.0F : this.eatTimer >= 4 && this.eatTimer <= 156 ? 1.0F : this.eatTimer < 4 ? (this.eatTimer - p_70894_1_) / 4.0F : -(this.eatTimer - 160 - p_70894_1_) / 4.0F;
		else
			return 0.0F;

	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 156 && !this.getFighting())
		{
			float f = (this.eatTimer - 4 - p_70890_1_) / 64.0F;
			return (float) Math.PI / 5F + (float) Math.PI * 7F / 100F * MathHelper.sin(f * 28.7F);
		}
		else
			return this.eatTimer > 0 ? (float) Math.PI / 5F : this.rotationPitch * 0.017453292F;
	}

	@Override
	public EntityBullBase createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);

		compound.setBoolean("Fighting", this.getFighting());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		this.setFighting(compound.getBoolean("Fighting"));
	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking())
		{

			if (this.getMateUniqueId() != null)
				probeInfo.text(I18n.translateToLocal("text.waila.mated"));
		}
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

	@Override
	public DataParameter<Boolean> getSterilizedParam()
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
			EntityAIBase ai = entry.action;
			if (ai instanceof GenericAIMate || ai instanceof EntityAIAttackMeleeBulls)
			{
				entry.using = false;
				ai.resetTask();
				it.remove();
			}
		}
		setSterilized(true);
	}

	@Override
	public DataParameter<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}

}
