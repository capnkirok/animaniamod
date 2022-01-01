package common.entity.rodents.rabbits;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.common.handler.CompatHandler;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.google.common.base.Optional;
import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Iterator;
import java.util.UUID;

public class RabbitEntityBuckBase extends EntityAnimaniaRabbit implements TOPInfoProviderMateable, IMateable, ISterilizable
{
	protected static final EntityDataAccessor<Boolean> STERILIZED = SynchedEntityData.<Boolean> defineId(RabbitEntityBuckBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.<Optional<UUID>> defineId(RabbitEntityBuckBase.class, EntityDataSerializers.OPTIONAL_UUID);

	public RabbitEntityBuckBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(0.7F, 0.6F);
		this.width = 0.7F;
		this.height = 0.6F;
		this.stepHeight = 1.1F;
		this.mateable = true;
		this.gender = EntityGender.MALE;

		if (!this.getSterilized())
			this.goalSelector.addGoal(6, new GenericAIMate<RabbitEntityBuckBase, RabbitEntityDoeBase>(this, 1.0D, RabbitEntityDoeBase.class, RabbitEntityKitBase.class, EntityAnimaniaRabbit.class));
		// this.goalSelector.addGoal(3, new FollowMateRabbitsGoal(this, 1.1D));
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.set(STERILIZED, false);
		this.entityData.set(MATE_UNIQUE_ID, Optional.<UUID> absent());
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.265D);
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.WOLF_STEP, 0.02F, 1.8F);
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateMateable(this, RabbitEntityDoeBase.class);

		super.onLivingUpdate();
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data)
	{
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
			if (ai instanceof GenericAIMate)
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
