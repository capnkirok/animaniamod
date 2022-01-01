package common.entity.pigs;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.common.helper.AnimaniaHelper;
import com.google.common.base.Optional;
import common.handler.FarmAddonSoundHandler;
import compat.TOPInfoProviderPig;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Attributes;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.UUID;

public class EntityHogBase extends EntityAnimaniaPig implements TOPInfoProviderPig, IMateable, ISterilizable
{

	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.<Optional<UUID>> defineId(EntityHogBase.class, EntityDataSerializers.OPTIONAL_UUID);
	protected static final EntityDataAccessor<Boolean> STERILIZED = SynchedEntityData.<Boolean> defineId(EntityHogBase.class, EntityDataSerializers.BOOLEAN);

	public EntityHogBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(1.0F, 1.0F);
		this.width = 1.0F;
		this.height = 1.0F;
		this.stepHeight = 1.1F;
		this.gender = EntityGender.MALE;

		if (!this.getSterilized())
			this.goalSelector.addGoal(3, new GenericAIMate<EntityHogBase, EntitySowBase>(this, 1.0D, EntitySowBase.class, PigEntityletBase.class, EntityAnimaniaPig.class));
		// this.goalSelector.addGoal(9, new FollowMatePigsGoal(this, 1.1D));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(14.0D);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.265D);
	}

	@Override
	@Nullable
	public Entity getControllingPassenger()
	{
		return this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
	}

	/**
	 * returns true if all the conditions for steering the entity are met. For
	 * pigs, this is true if it is being ridden by a player and the player is
	 * holding a carrot-on-a-stick
	 */
	@Override
	public boolean canBeSteered()
	{
		Entity entity = this.getControllingPassenger();

		if (!(entity instanceof Player Player))
			return false;
		else
		{
			ItemStack itemstack = Player.getMainHandItem();

			if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.CARROT_ON_A_STICK)
				return true;
			else
			{
				itemstack = Player.getItemInHandOffhand();
				return itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.CARROT_ON_A_STICK;
			}
		}
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(EntityHogBase.MATE_UNIQUE_ID, Optional.<UUID> absent());
		this.entityData.register(EntityHogBase.STERILIZED, false);

	}

	@Override
	public EntityDataAccessor<Optional<UUID>> getMateUniqueIdParam()
	{
		return EntityHogBase.MATE_UNIQUE_ID;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, FarmAddonSoundHandler.hog1, FarmAddonSoundHandler.hog2, FarmAddonSoundHandler.hog3, FarmAddonSoundHandler.hog4, FarmAddonSoundHandler.hog5, FarmAddonSoundHandler.pig1, FarmAddonSoundHandler.pig2, FarmAddonSoundHandler.pig4);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.pigHurt1, FarmAddonSoundHandler.pigHurt2, FarmAddonSoundHandler.pig3);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.pigHurt1, FarmAddonSoundHandler.pigHurt2, FarmAddonSoundHandler.pig3);
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
		this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.10F, 0.8F);
	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{

		ItemStack stack = player.getItemInHand(hand);

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.CARROT_ON_A_STICK && !this.isBeingRidden() && this.getWatered() && this.getFed())
		{
			player.startRiding(this);
			return true;
		}

		return super.processInteract(player, hand);
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateMateable(this, EntitySowBase.class);

		super.onLivingUpdate();
	}

	@Override
	public EntityHogBase createChild(AgeableEntity ageable)
	{
		return null;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && (AnimaniaHelper.containsItemStack(EntityAnimaniaPig.TEMPTATION_ITEMS, stack) || ItemStack.areItemStacksEqual(stack, this.slop));
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
