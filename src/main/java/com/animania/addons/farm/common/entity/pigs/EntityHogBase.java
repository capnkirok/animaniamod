package com.animania.addons.farm.common.entity.pigs;

import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.addons.farm.compat.TOPInfoProviderPig;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.common.helper.AnimaniaHelper;
import com.google.common.base.Optional;

import net.minecraft.block.Block;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityHogBase extends EntityAnimaniaPig implements TOPInfoProviderPig, IMateable, ISterilizable
{

	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>> defineId(EntityHogBase.class, DataSerializers.OPTIONAL_UUID);
	protected static final DataParameter<Boolean> STERILIZED = EntityDataManager.<Boolean> defineId(EntityHogBase.class, DataSerializers.BOOLEAN);

	public EntityHogBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.0F, 1.0F);
		this.width = 1.0F;
		this.height = 1.0F;
		this.stepHeight = 1.1F;
		this.gender = EntityGender.MALE;

		if (!getSterilized())
			this.tasks.addTask(3, new GenericAIMate<EntityHogBase, EntitySowBase>(this, 1.0D, EntitySowBase.class, PigEntityletBase.class, EntityAnimaniaPig.class));
		// this.tasks.addTask(9, new EntityAIFollowMatePigs(this, 1.1D));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(14.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.265D);
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

		if (!(entity instanceof PlayerEntity))
			return false;
		else
		{
			PlayerEntity PlayerEntity = (PlayerEntity) entity;
			ItemStack itemstack = PlayerEntity.getHeldItemMainhand();

			if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.CARROT_ON_A_STICK)
				return true;
			else
			{
				itemstack = PlayerEntity.getHeldItemOffhand();
				return itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.CARROT_ON_A_STICK;
			}
		}
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityHogBase.MATE_UNIQUE_ID, Optional.<UUID> absent());
		this.dataManager.register(EntityHogBase.STERILIZED, false);

	}

	@Override
	public DataParameter<Optional<UUID>> getMateUniqueIdParam()
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
	public boolean processInteract(PlayerEntity player, EnumHand hand)
	{

		ItemStack stack = player.getHeldItem(hand);

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
			Goal ai = entry.action;
			if (ai instanceof GenericAIMate)
			{
				entry.using = false;
				ai.resetTask();
				it.remove();
			}
		}
		setSterilized(true);
	}

}
