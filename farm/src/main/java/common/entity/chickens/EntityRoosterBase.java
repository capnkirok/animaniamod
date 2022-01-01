package common.entity.chickens;

import com.animania.Animania;
import com.animania.api.data.EntityGender;
import com.animania.common.handler.AddonInjectionHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Predicate;
import common.entity.cows.EntityAnimaniaCow;
import common.handler.FarmAddonSoundHandler;
import config.FarmConfig;
import net.minecraft.entity.Attributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class EntityRoosterBase extends EntityAnimaniaChicken implements TOPInfoProviderBase
{

	protected static final EntityDataAccessor<Integer> CROWTIMER = SynchedEntityData.<Integer> defineId(EntityRoosterBase.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Integer> CROWDURATION = SynchedEntityData.<Integer> defineId(EntityRoosterBase.class, EntityDataSerializers.INT);

	public EntityRoosterBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(0.6F, 0.8F);
		this.width = 0.6F;
		this.height = 0.8F;
		this.setTimeUntilNextCrow(this.rand.nextInt(200) + 200);
		this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.2F));
		this.goalSelector.addGoal(3, new AttackMeleeGoal(this, 1.0D, true));
		this.goalSelector.addGoal(6, new MateGoal(this, 1.0D));
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers)
		{
			// AddonInjectionHandler.runInjection("extra", "attackRodents",
			// null, this);
			AddonInjectionHandler.runInjection("extra", "attackFrogs", null, this);
		}
		if (FarmConfig.settings.roostersFight)
			this.targetSelector.addTask(8, new NearestAttackableTargetGoal(this, EntityRoosterBase.class, 80, false, true, (Predicate) null));

		this.gender = EntityGender.MALE;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);

		if (flag)
			this.applyEnchantments(this, entityIn);

		AddonInjectionHandler.runInjection("extra", "eatFrogs", null, entityIn, this);

		// Custom Knockback
		if (entityIn instanceof Player)
			((LivingEntity) entityIn).knockBack(this, 1, this.getX() - entityIn.getX(), this.getZ() - entityIn.getZ());

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
		this.getAttributeMap().registerAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.0D);

	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(EntityRoosterBase.CROWTIMER, Integer.valueOf(0));
		this.entityData.register(EntityRoosterBase.CROWDURATION, Integer.valueOf(0));
	}

	@Override
	public void writeEntityToNBT(CompoundTag CompoundTag)
	{
		super.writeEntityToNBT(CompoundTag);
		CompoundTag.putInt("CrowTime", this.getTimeUntilNextCrow());
		CompoundTag.putInt("CrowDuration", this.getCrowDuration());
	}

	@Override
	public void readEntityFromNBT(CompoundTag CompoundTag)
	{
		super.readEntityFromNBT(CompoundTag);
		this.setTimeUntilNextCrow(CompoundTag.getInteger("CrowTime"));
		this.setCrowDuration(CompoundTag.getInteger("CrowDuration"));
	}

	@Override
	public void onLivingUpdate()
	{
		this.timeUntilNextEgg = 1000;
		long currentTime = this.level.getLevelTime() % 23999;

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
			switch (crowChooser)
			{
			case 0:
				this.level.playSound(null, this.getX(), this.getY(), this.getZ(), FarmAddonSoundHandler.chickenCrow1, SoundCategory.PLAYERS, 0.7F, 0.95F + modular);
				break;
			case 1:
				this.level.playSound(null, this.getX(), this.getY(), this.getZ(), FarmAddonSoundHandler.chickenCrow2, SoundCategory.PLAYERS, 0.65F, 0.9F + modular);
				break;
			case 2:
				this.level.playSound(null, this.getX(), this.getY(), this.getZ(), FarmAddonSoundHandler.chickenCrow3, SoundCategory.PLAYERS, 0.6F, 1.05F + modular);
				break;
			default:
				break;
			}
			this.setTimeUntilNextCrow(this.rand.nextInt(200) + 200);

			List list = AnimaniaHelper.getEntitiesInRange(EntityAnimaniaCow.class, 30, this.level, this.getPosition());

			for (Object element : list)
			{
				if (element instanceof EntityAnimaniaCow CowEntity && CowEntity.getSleeping() && currentTime > 24000)
				{
					CowEntity.setSleeping(false);
					CowEntity.setSleepTimer(0F);
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
		this.entityData.set(EntityRoosterBase.CROWDURATION, Integer.valueOf(duration));
	}

	public int getTimeUntilNextCrow()
	{
		return this.getIntFromDataManager(CROWTIMER);
	}

	public void setTimeUntilNextCrow(int timer)
	{
		this.entityData.set(EntityRoosterBase.CROWTIMER, Integer.valueOf(timer));
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
