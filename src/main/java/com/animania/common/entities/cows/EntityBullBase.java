package com.animania.common.entities.cows;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

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
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.Animania;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.cows.ai.EntityAIAttackMeleeBulls;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.common.entities.interfaces.IMateable;
import com.animania.common.entities.interfaces.ISterilizable;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;

public class EntityBullBase extends EntityAnimaniaCow implements TOPInfoProviderMateable, IMateable, ISterilizable
{

	protected static final DataParameter<Boolean> FIGHTING = EntityDataManager.<Boolean> createKey(EntityBullBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> STERILIZED = EntityDataManager.<Boolean> createKey(EntityBullBase.class, DataSerializers.BOOLEAN);

	public EntityBullBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.6F, 1.8F);
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
		this.dataManager.register(EntityBullBase.FIGHTING, Boolean.valueOf(false));
		this.dataManager.register(EntityBullBase.STERILIZED, Boolean.valueOf(false));

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
		try
		{
			return (this.getBoolFromDataManager(FIGHTING));
		} catch (Exception e)
		{
			return false;
		}
	}

	public void setFighting(boolean fighting)
	{
		if (fighting)
			this.dataManager.set(EntityBullBase.FIGHTING, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityBullBase.FIGHTING, Boolean.valueOf(false));
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
			flag = entityIn.attackEntityFrom(DamageSourceHandler.bullDamage, 5.0F);

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
		int happy = 0;
		int num = 1;

		if (this.getWatered())
			happy++;
		if (this.getFed())
			happy++;

		if (happy == 2)
			num = 18;
		else if (happy == 1)
			num = 36;
		else
			num = 60;

		int chooser = Animania.RANDOM.nextInt(num);
		if (chooser == 0)
			return ModSoundEvents.bullMoo1;
		else if (chooser == 1)
			return ModSoundEvents.bullMoo2;
		else if (chooser == 2)
			return ModSoundEvents.bullMoo3;
		else if (chooser == 3)
			return ModSoundEvents.bullMoo4;
		else if (chooser == 4)
			return ModSoundEvents.bullMoo5;
		else if (chooser == 5)
			return ModSoundEvents.bullMoo6;
		else if (chooser == 6)
			return ModSoundEvents.bullMoo7;
		else if (chooser == 7)
			return ModSoundEvents.bullMoo8;
		else if (chooser == 8)
			return ModSoundEvents.moo4;
		else if (chooser == 9)
			return ModSoundEvents.moo8;
		else if (chooser == 10)
			return ModSoundEvents.moo4;
		else
			return ModSoundEvents.moo8;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		int chooser = Animania.RANDOM.nextInt(2);

		if (chooser == 0)
			return ModSoundEvents.angryBull1;
		else if (chooser == 1)
			return ModSoundEvents.angryBull2;
		else
			return ModSoundEvents.angryBull3;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return Animania.RANDOM.nextBoolean() ? ModSoundEvents.cowDeath1 : ModSoundEvents.cowDeath2;
	}

	@Override
	public void onLivingUpdate()
	{

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
			{
				this.blinkTimer = 100 + this.rand.nextInt(100);

				// Check for Mate
				if (this.getMateUniqueId() != null)
				{
					UUID mate = this.getMateUniqueId();
					boolean mateReset = true;

					List<EntityLivingBase> entities = AnimaniaHelper.getEntitiesInRange(EntityCowBase.class, 20, world, this);
					for (int k = 0; k <= entities.size() - 1; k++)
					{
						Entity entity = entities.get(k);
						if (entity != null)
						{
							UUID id = entity.getPersistentID();
							if (id.equals(this.getMateUniqueId()) && !entity.isDead)
							{
								mateReset = false;
								EntityCowBase fem = (EntityCowBase) entity;
								if (fem.getPregnant())
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
		} else
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
	public boolean getSterilized()
	{
		return this.getBoolFromDataManager(STERILIZED);
	}

	@Override
	public void setSterilized(boolean sterilized)
	{
		this.dataManager.set(EntityBullBase.STERILIZED, Boolean.valueOf(sterilized));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setBoolean("Sterilized", getSterilized());
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		this.setSterilized(compound.getBoolean("Sterilized"));
		super.readFromNBT(compound);
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

}
