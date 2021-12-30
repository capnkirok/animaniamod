package com.animania.addons.farm.common.entity.pigs;

import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IChild;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFollowParents;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderChild;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Attributes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PigEntityletBase extends EntityAnimaniaPig implements TOPInfoProviderChild, IChild
{

	protected static final EntityDataAccessor<Optional<UUID>> PARENT_UNIQUE_ID = SynchedEntityData.<Optional<UUID>> defineId(PigEntityletBase.class, EntityDataSerializers.OPTIONAL_UUID);
	protected static final EntityDataAccessor<Float> AGE = SynchedEntityData.<Float> defineId(PigEntityletBase.class, EntityDataSerializers.FLOAT);
	protected int ageTimer;

	public PigEntityletBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(1.1F, 1.1F);
		this.width = 1.1F;
		this.height = 1.1F;
		this.stepHeight = 1.1F;
		this.ageTimer = 0;
		this.pigType = PigType.YORKSHIRE;
		this.gender = EntityGender.CHILD;
		this.goalSelector.addGoal(1, new GenericAIFollowParents<PigEntityletBase, EntitySowBase>(this, 1.1, EntitySowBase.class));
	}

	@Override
	public boolean isChild()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.315D);

	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(PigEntityletBase.AGE, Float.valueOf(0));
		this.entityData.register(PigEntityletBase.PARENT_UNIQUE_ID, Optional.<UUID> absent());

	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, FarmAddonSoundHandler.piglet1, FarmAddonSoundHandler.piglet2, FarmAddonSoundHandler.piglet3, FarmAddonSoundHandler.pig1, FarmAddonSoundHandler.pig2);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.pigletHurt1, FarmAddonSoundHandler.pigletHurt2, FarmAddonSoundHandler.pigletHurt3);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.pigletHurt1, FarmAddonSoundHandler.pigletHurt2, FarmAddonSoundHandler.pigletHurt3);
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() + .2F);
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
		GenericBehavior.livingUpdateChild(this, EntitySowBase.class);

		this.setSize((0.3f + this.getEntityAge()) * 2, (0.3f + this.getEntityAge()) * 2);

		super.onLivingUpdate();
	}

	@Override
	public PigEntityletBase createChild(AgeableEntity ageable)
	{
		return null;
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed
	 * it (wheat, carrots or seeds depending on the animal type)
	 */
	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && (AnimaniaHelper.containsItemStack(EntityAnimaniaPig.TEMPTATION_ITEMS, stack) || ItemStack.areItemStacksEqual(stack, this.slop));
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
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data)
	{
		if (this.getPlayed())
			probeInfo.text(ChatFormatting.GREEN + I18n.translateToLocal("text.waila.played"));
		else
			probeInfo.text(ChatFormatting.YELLOW + I18n.translateToLocal("text.waila.bored"));

		TOPInfoProviderChild.super.addProbeInfo(mode, probeInfo, player, level, entity, data);
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
		return 1.0625f;
	}

}
