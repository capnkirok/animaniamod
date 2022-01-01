package common.entity.rodents.rabbits;

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
import net.minecraft.entity.Attributes;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.UUID;

public class RabbitEntityDoeBase extends EntityAnimaniaRabbit implements TOPInfoProviderMateable, IMateable, IImpregnable
{

	public int dryTimerDoe;
	protected static final EntityDataAccessor<Boolean> PREGNANT = SynchedEntityData.<Boolean> defineId(RabbitEntityDoeBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> HAS_KIDS = SynchedEntityData.<Boolean> defineId(RabbitEntityDoeBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> FERTILE = SynchedEntityData.<Boolean> defineId(RabbitEntityDoeBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> GESTATION_TIMER = SynchedEntityData.<Integer> defineId(RabbitEntityDoeBase.class, EntityDataSerializers.INT);
	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.<Optional<UUID>> defineId(RabbitEntityDoeBase.class, EntityDataSerializers.OPTIONAL_UUID);

	public RabbitEntityDoeBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(0.7F, 0.6F);
		this.width = 0.7F;
		this.height = 0.6F;
		this.stepHeight = 1.1F;
		this.mateable = true;
		this.gender = EntityGender.FEMALE;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(RabbitEntityDoeBase.PREGNANT, false);
		this.entityData.register(RabbitEntityDoeBase.HAS_KIDS, false);
		this.entityData.register(RabbitEntityDoeBase.FERTILE, true);
		this.entityData.register(MATE_UNIQUE_ID, Optional.<UUID> absent());
		this.entityData.register(RabbitEntityDoeBase.GESTATION_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200)));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(9.0D);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.265D);
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata)
	{
		GenericBehavior.initialSpawnFemale(this, EntityAnimaniaRabbit.class);

		return livingdata;
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
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.02F, 1.8F);
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
		GenericBehavior.livingUpdateFemale(this, RabbitEntityBuckBase.class);

		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		Player Player = player;

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET)
		{
			if (stack.getCount() == 1 && !player.capabilities.isCreativeMode)
				player.setHeldItem(hand, new ItemStack(Items.BUCKET));
			else if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET)))
				player.dropItem(new ItemStack(Items.BUCKET), false);

			this.eatTimer = 40;
			this.entityAIEatGrass.startExecuting();
			this.setWatered(true);
			this.setInLove(player);
			return true;
		}
		else
			return super.processInteract(player, hand);
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
	public EntityDataAccessor<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}
}
