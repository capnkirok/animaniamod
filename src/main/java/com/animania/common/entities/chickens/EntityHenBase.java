package com.animania.common.entities.chickens;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.common.entities.amphibians.EntityAmphibian;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.chickens.ai.EntityAIFindNest;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityHenBase extends EntityAnimaniaChicken implements TOPInfoProviderBase
{

	protected static final DataParameter<Boolean> LAID = EntityDataManager.<Boolean>createKey(EntityHenBase.class, DataSerializers.BOOLEAN);
	protected int laidTimer;

	public EntityHenBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.5F, 0.7F);
		this.tasks.addTask(6, new EntityAIFindNest(this, 1.0D));
		this.tasks.addTask(9, new EntityAILeapAtTarget(this, 0.2F));
		this.tasks.addTask(10, new EntityAIAttackMelee(this, 1.0D, true));
		this.targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntityFrogs.class, false));
		this.targetTasks.addTask(8, new EntityAINearestAttackableTarget(this, EntityToad.class, false));
		this.laidTimer = AnimaniaConfig.careAndFeeding.laidTimer / 2 + 0 + this.rand.nextInt(100);

	}

	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{

		if (this.world.isRemote)
			return null;

		int chickenCount = 0;
		List entities = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaChicken.class, 128, this.world, this);
		chickenCount = entities.size();
		
		if (chickenCount <= AnimaniaConfig.spawn.spawnLimitChickens)
		{

			int chooser = this.rand.nextInt(5);

			if (chooser == 0)
			{
				EntityRoosterBase entityChicken = this.type.getMale(world);
				entityChicken.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityChicken);
			}
			else if (chooser == 1)
			{
				EntityChickBase entityChicken = this.type.getChild(world);
				entityChicken.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityChicken);
			}
			else if (chooser > 2)
			{
				EntityRoosterBase entityChicken = this.type.getMale(world);
				entityChicken.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityChicken);
				EntityChickBase entityChick = this.type.getChild(world);
				entityChick.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityChick);
			}

			this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

			if (this.rand.nextFloat() < 0.05F)
				this.setLeftHanded(true);
			else
				this.setLeftHanded(false);

		}

		return livingdata;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);

		if (flag)
		{
			this.applyEnchantments(this, entityIn);
		}

		if (entityIn instanceof EntityAmphibian)
		{
			this.setFed(true);
		}

		// Custom Knockback
		if (entityIn instanceof EntityPlayer)
		{
			((EntityLivingBase) entityIn).knockBack(this, 1, this.posX - entityIn.posX, this.posZ - entityIn.posZ);
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
		this.dataManager.register(EntityHenBase.LAID, Boolean.valueOf(true));
		this.timeUntilNextEgg = 6000;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setBoolean("Laid", this.getLaid());
		nbttagcompound.setInteger("EggLayTime", timeUntilNextEgg);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
		this.timeUntilNextEgg = nbttagcompound.getInteger("EggLayTime");
		this.setLaid(nbttagcompound.getBoolean("Laid"));
	}

	@Override
	public void onLivingUpdate()
	{
		
		if (!AnimaniaConfig.drops.chickensDropEggs) {
			this.timeUntilNextEgg = 1000;
		}
		
		if (this.laidTimer > -1)
			this.laidTimer--;
		else
			this.setLaid(false);

		super.onLivingUpdate();
	}

	public boolean getLaid()
	{
		return this.dataManager.get(EntityHenBase.LAID).booleanValue();
	}

	public void setLaid(boolean laid)
	{
		if (laid)
		{
			this.dataManager.set(EntityHenBase.LAID, Boolean.valueOf(true));
			this.laidTimer = AnimaniaConfig.careAndFeeding.laidTimer + this.rand.nextInt(100);
		}
		else
			this.dataManager.set(EntityHenBase.LAID, Boolean.valueOf(false));
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume() - .3F, this.getSoundPitch());
	}

	

}
