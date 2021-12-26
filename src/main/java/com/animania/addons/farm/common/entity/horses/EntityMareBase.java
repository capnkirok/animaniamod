package com.animania.addons.farm.common.entity.horses;

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
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityEntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EntityMareBase extends EntityAnimaniaHorse implements TOPInfoProviderMateable, IMateable, IImpregnable
{

	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	private static final String[] HORSE_TEXTURES = { "black", "bw1", "bw2", "grey", "red", "white" };
	public int dryTimerMare;
	protected static final EntityDataAccessor<Boolean> PREGNANT = SynchedEntityData.<Boolean> defineId(EntityMareBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> HAS_KIDS = SynchedEntityData.<Boolean> defineId(EntityMareBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> FERTILE = SynchedEntityData.<Boolean> defineId(EntityMareBase.class, EntityEntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> GESTATION_TIMER = SynchedEntityData.<Integer> defineId(EntityMareBase.class, EntityEntityDataSerializers.INT);
	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.<Optional<UUID>> defineId(EntityMareBase.class, EntityEntityDataSerializers.OPTIONAL_UUID);

	public EntityMareBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(1.8F, 2.2F);
		this.width = 1.8F;
		this.height = 2.2F;
		this.stepHeight = 1.2F;
		this.mateable = true;
		this.gender = EntityGender.FEMALE;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityMareBase.PREGNANT, false);
		this.dataManager.register(EntityMareBase.HAS_KIDS, false);
		this.dataManager.register(EntityMareBase.FERTILE, true);
		this.dataManager.register(EntityMareBase.GESTATION_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(400)));
		this.dataManager.register(EntityMareBase.MATE_UNIQUE_ID, Optional.<UUID> absent());

	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata)
	{
		GenericBehavior.initialSpawnFemale(this, EntityAnimaniaHorse.class);

		return livingdata;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	@Override
	public int getGestation()
	{
		return this.getIntFromDataManager(GESTATION_TIMER);
	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{

		ItemStack stack = player.getItemInHand(hand);

		if (!this.isChild() && this.isHorseSaddled())
		{

			if (player.isSneaking())
			{
				this.openGUI(player);
				return true;
			}
			else
			{
				return super.processInteract(player, hand);
			}

		}
		else if (!player.isSneaking() && stack != null && this.isHorseSaddled() && !this.getSleeping() && !this.isBeingRidden() && this.getWatered() && this.getFed())
		{
			this.mountTo(player);
			// player.addStat(AnimaniaAchievements.Horseriding, 1);
			return true;
		}
		else
		{
			return super.processInteract(player, hand);
		}
	}

	@Override
	public void setGestation(int gestation)
	{
		this.dataManager.set(EntityMareBase.GESTATION_TIMER, Integer.valueOf(gestation));
	}

	@Override
	public boolean getPregnant()
	{
		return this.getBoolFromDataManager(PREGNANT);
	}

	@Override
	public void setPregnant(boolean preggers)
	{
		if (preggers)
		{
			this.setGestation(AnimaniaConfig.careAndFeeding.gestationTimer + rand.nextInt(200));
		}
		this.dataManager.set(EntityMareBase.PREGNANT, Boolean.valueOf(preggers));
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
	public double getMountedYOffset()
	{
		return this.height * 0.60D;
	}

	@Override
	public void openGUI(Player Player)
	{
		if (!this.level.isClientSide && (!this.isBeingRidden() || this.isPassenger(Player)))
		{
			this.horseChest.setCustomName(this.getName());
			Player.openGuiHorseInventory(this, this.horseChest);
		}
	}

	@Override
	public int getVerticalFaceSpeed()
	{
		return super.getVerticalFaceSpeed();
	}

	@Override
	@Nullable
	public Entity getControllingPassenger()
	{
		return this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
	}

	@Override
	public boolean canBeSteered()
	{
		Entity entity = this.getControllingPassenger();

		if (!(entity instanceof Player) || !this.isHorseSaddled())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public void setInLove(Player player)
	{
		this.level.broadcastEntityEvent(this, (byte) 18);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_HORSE_STEP, 0.20F, 0.8F);
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null && !this.getSleeping())
		{
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .05F);
		}
	}

	@Override
	public ResourceLocation getResourceLocation()
	{
		return this.resourceLocation;
	}

	@Override
	public ResourceLocation getResourceLocationBlink()
	{
		return this.resourceLocationBlink;
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.getColorNumber() > 5)
		{
			this.setColorNumber(0);
		}

		GenericBehavior.livingUpdateFemale(this, EntityStallionBase.class);

		super.onLivingUpdate();
	}

	@Override
	public EntityMareBase createChild(AgeableEntity p_90011_1_)
	{
		return null;
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid = CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level level, Entity entity, IProbeHitEntityData data)
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
		return this.dryTimerMare;
	}

	@Override
	public void setDryTimer(int i)
	{
		this.dryTimerMare = i;
	}

	@Override
	public EntityDataAccessor<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}

}
