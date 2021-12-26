package com.animania.addons.farm.common.entity.goats;

import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.addons.farm.common.entity.goats.GoatAngora.EntityDoeAngora;
import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.addons.farm.common.handler.FarmAddonSoundHandler;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IImpregnable;
import com.animania.api.interfaces.IMateable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidInteractionResultHolder;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.ModList;

public class EntityDoeBase extends EntityAnimaniaGoat implements TOPInfoProviderMateable, IMateable, IImpregnable
{
	protected ItemStack milk = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkGoat);
	public int dryTimerDoe;
	protected static final EntityDataAccessor<Boolean> PREGNANT = SynchedEntityData.defineId(EntityDoeBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> HAS_KIDS = SynchedEntityData.defineId(EntityDoeBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> FERTILE = SynchedEntityData.defineId(EntityDoeBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> GESTATION_TIMER = SynchedEntityData.defineId(EntityDoeBase.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.defineId(EntityDoeBase.class, EntityDataSerializers.OPTIONAL_UNIQUE_ID);

	public EntityDoeBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(1.0F, 1.0F);
		this.width = 1.0F;
		this.height = 1.0F;
		this.stepHeight = 1.1F;
		this.mateable = true;
		this.gender = EntityGender.FEMALE;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityDoeBase.PREGNANT, false);
		this.dataManager.register(EntityDoeBase.HAS_KIDS, false);
		this.dataManager.register(EntityDoeBase.FERTILE, true);
		this.dataManager.register(EntityDoeBase.GESTATION_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200)));
		this.dataManager.register(EntityDoeBase.MATE_UNIQUE_ID, Optional.<UUID> absent());

	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.265D);
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata)
	{
		GenericBehavior.initialSpawnFemale(this, EntityAnimaniaGoat.class);

		return livingdata;
	}

	@Override
	public EntityDataAccessor<Integer> getGestationParam()
	{
		return GESTATION_TIMER;
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
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, FarmAddonSoundHandler.goatLiving1, FarmAddonSoundHandler.goatLiving2, FarmAddonSoundHandler.goatLiving2, FarmAddonSoundHandler.goatLiving3, FarmAddonSoundHandler.goatLiving4, FarmAddonSoundHandler.goatLiving5);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.goatHurt1, FarmAddonSoundHandler.goatHurt2, FarmAddonSoundHandler.goatLiving3);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.goatHurt1, FarmAddonSoundHandler.goatHurt2, FarmAddonSoundHandler.goatLiving3);
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
		GenericBehavior.livingUpdateFemale(this, EntityBuckBase.class);

		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		Player Player = player;

		if (this.getFed() && this.getWatered() && stack != ItemStack.EMPTY && AnimaniaHelper.isEmptyFluidContainer(stack) && this.getHasKids())
		{
			if (!level.isClientSide)
			{
				player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);

				ItemStack one = stack.copy();
				one.setCount(1);
				FluidInteractionResultHolder result;
				result = FluidUtil.tryFillContainer(one, FluidUtil.getFluidHandler(this.milk.copy()), 1000, player, true);

				ItemStack filled;

				if (!result.success)
				{
					Item item = stack.getItem();
					if (item == Items.BUCKET)
						filled = this.milk.copy();
					else if (ModList.get().isLoaded("ceramics") && item == Item.getByNameOrId("ceramics:clay_bucket"))
						filled = new ItemStack(Item.getByNameOrId("ceramics:clay_bucket"), 1, 1);
					else
						return false;
				}
				else
					filled = result.result;
				stack.shrink(1);
				AnimaniaHelper.addItem(player, filled);
				this.setWatered(false);
			}
			return true;
		}
		else
			return super.processInteract(player, hand);
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, stack);
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data)
	{
		if (player.isSneaking())
		{

			if (this.getMateUniqueId() != null)
				probeInfo.text(I18n.translateToLocal("text.waila.mated"));

			if (this.getHasKids())
				probeInfo.text(I18n.translateToLocal("text.waila.milkable"));

			if (this.getFertile() && !this.getPregnant())
			{
				probeInfo.text(I18n.translateToLocal("text.waila.fertile1"));
			}

			if (this.getPregnant())
			{
				if (this.getGestation() > 0)
				{
					int bob = this.getGestation();
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1") + " (" + bob + " " + I18n.translateToLocal("text.waila.pregnant2") + ")");
				}
				else
				{
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1"));
				}
			}

			if (this.getSheared() && this instanceof EntityDoeAngora)
			{
				if (this.getWoolRegrowthTimer() > 1)
				{
					int bob = this.getWoolRegrowthTimer();
					probeInfo.text(I18n.translateToLocal("text.waila.wool1") + " (" + bob + " " + I18n.translateToLocal("text.waila.wool2") + ")");
				}
			}
			else if (this instanceof EntityDoeAngora)
			{
				probeInfo.text(I18n.translateToLocal("text.waila.wool3"));
			}
		}
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, level, entity, data);
	}

	@Override
	public EntityDataAccessor<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}
}
