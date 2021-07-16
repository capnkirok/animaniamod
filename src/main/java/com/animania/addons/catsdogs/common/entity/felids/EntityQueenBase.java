package com.animania.addons.catsdogs.common.entity.felids;

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
import net.minecraft.entity.Entity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityQueenBase extends EntityAnimaniaCat implements TOPInfoProviderMateable, IMateable, IImpregnable
{

	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>> defineId(EntityQueenBase.class, DataSerializers.OPTIONAL_UUID);
	protected static final DataParameter<Boolean> PREGNANT = EntityDataManager.<Boolean> defineId(EntityQueenBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HAS_KIDS = EntityDataManager.<Boolean> defineId(EntityQueenBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FERTILE = EntityDataManager.<Boolean> defineId(EntityQueenBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> GESTATION_TIMER = EntityDataManager.<Integer> defineId(EntityQueenBase.class, DataSerializers.INT);
	public int dryTimer;

	public EntityQueenBase(World worldIn)
	{
		super(worldIn);
		this.setSize(0.8F, 0.8F);
		this.width = 0.8F;
		this.height = 0.8F;
		this.stepHeight = 1.1F;
		this.gender = EntityGender.FEMALE;
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata)
	{
		GenericBehavior.initialSpawnFemale(this, EntityAnimaniaCat.class);

		return livingdata;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(MATE_UNIQUE_ID, Optional.<UUID> absent());
		this.dataManager.register(PREGNANT, false);
		this.dataManager.register(HAS_KIDS, false);
		this.dataManager.register(FERTILE, true);
		this.dataManager.register(GESTATION_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200)));

	}

	@Override
	public DataParameter<Integer> getGestationParam()
	{
		return GESTATION_TIMER;
	}

	@Override
	public DataParameter<Boolean> getPregnantParam()
	{
		return PREGNANT;
	}

	@Override
	public int getDryTimer()
	{
		return dryTimer;
	}

	@Override
	public void setDryTimer(int i)
	{
		this.dryTimer = i;
	}

	@Override
	public DataParameter<Boolean> getFertileParam()
	{
		return FERTILE;
	}

	@Override
	public DataParameter<Boolean> getHasKidsParam()
	{
		return HAS_KIDS;
	}

	@Override
	public DataParameter<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateFemale(this, EntityTomBase.class);

		super.onLivingUpdate();
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, PlayerEntity player, World world, Entity entity, IProbeHitEntityData data)
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
				} else
				{
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1"));
				}
			}
		}
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

}
