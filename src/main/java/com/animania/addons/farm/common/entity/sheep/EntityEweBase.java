package com.animania.addons.farm.common.entity.sheep;

import java.util.UUID;

import javax.annotation.Nullable;

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
import net.minecraft.entity.Attributes;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidInteractionResultHolder;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.ModList;

public class EntityEweBase extends EntityAnimaniaSheep implements TOPInfoProviderMateable, IMateable, IImpregnable
{
	protected ItemStack milk = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkSheep);
	public int dryTimer;
	protected static final EntityDataAccessor<Boolean> PREGNANT = SynchedEntityData.defineId(EntityEweBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> HAS_KIDS = SynchedEntityData.defineId(EntityEweBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> FERTILE = SynchedEntityData.defineId(EntityEweBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> GESTATION_TIMER = SynchedEntityData.defineId(EntityEweBase.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.defineId(EntityEweBase.class, EntityDataSerializers.OPTIONAL_UNIQUE_ID);

	public EntityEweBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(1.0F, 1.0F);
		this.width = 1.0F;
		this.height = 1.0F;
		this.stepHeight = 1.1F;
		this.gender = EntityGender.FEMALE;
		this.mateable = true;
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata)
	{
		GenericBehavior.initialSpawnFemale(this, EntityAnimaniaSheep.class);

		return livingdata;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(15.0D);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.265D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(MATE_UNIQUE_ID, Optional.<UUID> absent());
		this.entityData.register(EntityEweBase.PREGNANT, false);
		this.entityData.register(EntityEweBase.HAS_KIDS, false);
		this.entityData.register(EntityEweBase.FERTILE, true);
		this.entityData.register(EntityEweBase.GESTATION_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200)));
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
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, FarmAddonSoundHandler.sheepLiving1, FarmAddonSoundHandler.sheepLiving2, FarmAddonSoundHandler.sheepLiving3, FarmAddonSoundHandler.sheepLiving4, FarmAddonSoundHandler.sheepLiving5, FarmAddonSoundHandler.sheepLiving6, FarmAddonSoundHandler.sheepLiving7);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.sheepHurt1, FarmAddonSoundHandler.sheepLiving7);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GenericBehavior.getRandomSound(FarmAddonSoundHandler.sheepHurt1, FarmAddonSoundHandler.sheepLiving7);
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

		GenericBehavior.livingUpdateFemale(this, EntityRamBase.class);

		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		Player Player = player;

		if (this.getFed() && this.getWatered() && stack != ItemStack.EMPTY && AnimaniaHelper.isEmptyFluidContainer(stack) && this.getHasKids())
		{
			if (!this.level.isClientSide)
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

			if (this.getSheared())
			{
				if (this.getWoolRegrowthTimer() > 1)
				{
					int bob = this.getWoolRegrowthTimer();
					probeInfo.text(I18n.translateToLocal("text.waila.wool1") + " (" + bob + " " + I18n.translateToLocal("text.waila.wool2") + ")");
				}
			}
			else if (!this.getSheared())
			{
				probeInfo.text(I18n.translateToLocal("text.waila.wool3"));
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
		return this.dryTimer;
	}

	@Override
	public void setDryTimer(int i)
	{
		this.dryTimer = i;
	}

	@Override
	public EntityDataAccessor<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}
}
