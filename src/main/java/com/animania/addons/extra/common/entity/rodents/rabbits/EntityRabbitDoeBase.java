package com.animania.addons.extra.common.entity.rodents.rabbits;

import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IImpregnable;
import com.animania.api.interfaces.IMateable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityEntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.EnumHand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.item.ItemStack;

public class RabbitEntityDoeBase extends EntityAnimaniaRabbit implements TOPInfoProviderMateable, IMateable, IImpregnable
{

	public int dryTimerDoe;
	protected static final EntityDataAccessor<Boolean> PREGNANT = SynchedEntityData.<Boolean> defineId(RabbitEntityDoeBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> HAS_KIDS = SynchedEntityData.<Boolean> defineId(RabbitEntityDoeBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> FERTILE = SynchedEntityData.<Boolean> defineId(RabbitEntityDoeBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> GESTATION_TIMER = SynchedEntityData.<Integer> defineId(RabbitEntityDoeBase.class, EntityEntityDataSerializers.INT);
	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.<Optional<UUID>> defineId(RabbitEntityDoeBase.class, EntityEntityDataSerializers.OPTIONAL_UUID);

	public RabbitEntityDoeBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(0.7F, 0.6F);
		this.width = 0.7F;
		this.height = 0.6F;
		this.stepHeight = 1.1F;
		this.mateable = true;
		this.gender = EntityGender.FEMALE;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(RabbitEntityDoeBase.PREGNANT, false);
		this.dataManager.register(RabbitEntityDoeBase.HAS_KIDS, false);
		this.dataManager.register(RabbitEntityDoeBase.FERTILE, true);
		this.dataManager.register(MATE_UNIQUE_ID, Optional.<UUID> absent());
		this.dataManager.register(RabbitEntityDoeBase.GESTATION_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200)));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(9.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.265D);
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata)
	{
		GenericBehavior.initialSpawnFemale(this, EntityAnimaniaRabbit.class);

		return livingdata;
	}

	@Override
	public EntityDataAccessor<Integer> getGestationParam()
	{
		return GESTATION_TIMER;
	}

	@Override
	public EntityDataAccessor<Boolean> getFertileParam()
	{
		return FERTILE;
	}

	@Override
	public EntityDataAccessor<Boolean> getHasKidsParam()
	{
		return HAS_KIDS;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.8F);
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateFemale(this, RabbitEntityBuckBase.class);

		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(PlayerEntity player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		PlayerEntity PlayerEntity = player;

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET)
		{
			if (stack.getCount() == 1 && !player.capabilities.isCreativeMode)
				player.setHeldItem(hand, new ItemStack(Items.BUCKET));
			else if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET)))
				player.dropItem(new ItemStack(Items.BUCKET), false);

			this.eatTimer = 40;
			this.entityAIEatGrass.startExecuting();
			this.setWatered(true);
			this.setInLove(player);
			return true;
		}
		else
			return super.processInteract(player, hand);
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, Level level, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking())
		{

			if (this.getMateUniqueId() != null)
				probeInfo.text(I18n.translateToLocal("text.waila.mated"));

			if (this.getFertile() && !this.getPregnant())
			{
				probeInfo.text(I18n.translateToLocal("text.waila.fertile1"));
			}

			if (this.getPregnant())
			{
				if (this.getGestation() > 1)
				{
					int bob = this.getGestation();
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1") + " (" + bob + " " + I18n.translateToLocal("text.waila.pregnant2") + ")");
				}
				else
				{
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1"));
				}
			}

		}
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, level, entity, data);
	}

	@Override
	public EntityDataAccessor<Boolean> getPregnantParam()
	{
		return PREGNANT;
	}

	@Override
	public int getDryTimer()
	{
		return this.dryTimerDoe;
	}

	@Override
	public void setDryTimer(int i)
	{
		this.dryTimerDoe = i;
	}

	@Override
	public EntityDataAccessor<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}
}
