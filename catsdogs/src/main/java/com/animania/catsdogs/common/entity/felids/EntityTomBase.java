package com.animania.catsdogs.common.entity.felids;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.google.common.base.Optional;
import net.minecraft.entity.Attributes;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

import java.util.Iterator;
import java.util.UUID;

public class EntityTomBase extends EntityAnimaniaCat implements TOPInfoProviderMateable, IMateable, ISterilizable
{
	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.<Optional<UUID>> defineId(EntityTomBase.class, EntityDataSerializers.OPTIONAL_UUID);
	protected static final EntityDataAccessor<Boolean> STERILIZED = SynchedEntityData.<Boolean> defineId(EntityTomBase.class, EntityDataSerializers.BOOLEAN);

	public EntityTomBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(1.0F, 1.0F);
		this.width = 1.0F;
		this.height = 1.0F;
		this.stepHeight = 1.1F;
		this.gender = EntityGender.MALE;

		if (!this.getSterilized())
			this.goalSelector.addGoal(8, new GenericAIMate<EntityTomBase, EntityQueenBase>(this, 1.0D, EntityQueenBase.class, EntityKittenBase.class, EntityAnimaniaCat.class));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
		this.entityData.register(STERILIZED, false);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(MATE_UNIQUE_ID, Optional.<UUID> absent());

	}

	@Override
	public EntityDataAccessor<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateMateable(this, EntityQueenBase.class);

		super.onLivingUpdate();
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

}