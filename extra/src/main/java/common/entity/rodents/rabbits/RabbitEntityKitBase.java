package common.entity.rodents.rabbits;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFollowParents;
import com.animania.compat.top.providers.entity.TOPInfoProviderChild;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.Optional;
import java.util.UUID;

public class RabbitEntityKitBase extends EntityAnimaniaRabbit implements TOPInfoProviderChild, IChild
{

	protected static final EntityDataAccessor<Optional<UUID>> PARENT_UNIQUE_ID = SynchedEntityData.defineId(RabbitEntityKitBase.class, EntityDataSerializers.OPTIONAL_UUID);
	protected static final EntityDataAccessor<Float> AGE = SynchedEntityData.defineId(RabbitEntityKitBase.class, EntityDataSerializers.FLOAT);
	protected int ageTimer;

	public RabbitEntityKitBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(0.8F, 0.8F);
		this.width = 0.8F;
		this.height = 0.8F;
		this.stepHeight = 1.1F;
		this.ageTimer = 0;
		this.gender = EntityGender.CHILD;
		this.goalSelector.addGoal(1, new GenericAIFollowParents<RabbitEntityKitBase, RabbitEntityDoeBase>(this, 1.1, RabbitEntityDoeBase.class));
	}

	@Override
	public boolean isBaby()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(3.0D);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.315D);

	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.set(RabbitEntityKitBase.AGE, (float) 0);
		this.entityData.set(RabbitEntityKitBase.PARENT_UNIQUE_ID, Optional.empty());

	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch() + .2F);
	}

	@Override
	public int getAgeTimer()
	{
		return this.ageTimer;
	}

	@Override
	public void setAgeTimer(int i)
	{
		this.ageTimer = i;
	}

	@Override
	public void onLivingUpdate()
	{

		GenericBehavior.livingUpdateChild(this, RabbitEntityDoeBase.class);
		this.setSize((0.2f + this.getEntityAge()) * 2, (0.2f + this.getEntityAge()) * 2);

		super.onLivingUpdate();
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
	}

	@Override
	protected Item getDropItem()
	{
		return null;
	}

	@Override
	public EntityDataAccessor<Optional<UUID>> getParentUniqueIdParam()
	{
		return PARENT_UNIQUE_ID;
	}

	@Override
	public EntityDataAccessor<Float> getEntityAgeParam()
	{
		return AGE;
	}

	@Override
	public void ageUp(int growthSeconds, boolean updateForcedAge)
	{
		float entityAge = this.getEntityAge();
		entityAge += 0.05f;
		this.setEntityAge(entityAge);
	}

	@Override
	public float getSizeDividend()
	{
		return 2.36111f;
	}

}
