package common.entity.cows;

import com.animania.Animania;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IImpregnable;
import com.animania.api.interfaces.IMateable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import common.handler.FarmAddonSoundHandler;
import config.FarmConfig;
import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Attributes;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidInteractionResultHolder;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.ModList;

import javax.annotation.Nullable;
import java.util.UUID;

public class CowEntityBase extends EntityAnimaniaCow implements TOPInfoProviderMateable, IMateable, IImpregnable
{

	public int dryTimer;
	protected ItemStack milk = new ItemStack(Items.MILK_BUCKET);
	protected static final EntityDataAccessor<Boolean> PREGNANT = SynchedEntityData.defineId(CowEntityBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> HAS_KIDS = SynchedEntityData.defineId(CowEntityBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> FERTILE = SynchedEntityData.defineId(CowEntityBase.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> GESTATION_TIMER = SynchedEntityData.defineId(CowEntityBase.class, EntityDataSerializers.VARINT);
	protected static final EntityDataAccessor<Optional<UUID>> MATE_UNIQUE_ID = SynchedEntityData.defineId(CowEntityBase.class, EntityDataSerializers.OPTIONAL_UNIQUE_ID);

	public CowEntityBase(Level levelIn)
	{
		super(levelIn);
		this.setSize(1.4F, 1.8F);
		this.width = 1.4F;
		this.height = 1.8F;
		this.stepHeight = 1.1F;
		this.goalSelector.addGoal(4, new AttackMeleeGoal(this, 1.2D, false));
		this.targetSelector.addTask(1, new HurtByTargetGoal(this, true, new Class[0]));
		this.mateable = true;
		this.gender = EntityGender.FEMALE;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(CowEntityBase.PREGNANT, false);
		if (FarmConfig.settings.cowsMilkableAtSpawn)
		{
			this.entityData.register(CowEntityBase.HAS_KIDS, true);
		}
		else
		{
			this.entityData.register(CowEntityBase.HAS_KIDS, false);
		}
		this.entityData.register(CowEntityBase.FERTILE, true);
		this.entityData.register(CowEntityBase.GESTATION_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200)));
		this.entityData.register(CowEntityBase.MATE_UNIQUE_ID, Optional.<UUID> absent());

	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{

		if (this.getSleeping())
		{
			this.setSleeping(false);
			this.setSleepTimer(0F);
			this.jump();
		}

		boolean flag = false;
		if (this.canEntityBeSeen(entityIn) && this.getDistance(entityIn) <= 2.0F)
		{
			flag = entityIn.attackEntityFrom(new EntityDamageSource("bull", this), 2.0F);

			if (flag)
				this.applyEnchantments(this, entityIn);

			// Custom Knockback
			if (entityIn instanceof Player)
				((LivingEntity) entityIn).knockBack(this, 0, (this.getX() - entityIn.getX()) / 2, (this.getZ() - entityIn.getZ()) / 2);
		}

		return flag;
	}

	@Override
	public void setInLove(Player player)
	{

		if (!this.getSleeping())
			this.level.broadcastEntityEvent(this, (byte) 18);
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata)
	{
		GenericBehavior.initialSpawnFemale(this, EntityAnimaniaCow.class);
		return livingdata;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(18.0D);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getAttributeMap().registerAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.0D);
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
		return GenericBehavior.getAmbientSound(this, FarmAddonSoundHandler.moo1, FarmAddonSoundHandler.moo3, FarmAddonSoundHandler.moo4, FarmAddonSoundHandler.moo4, FarmAddonSoundHandler.moo5, FarmAddonSoundHandler.moo6, FarmAddonSoundHandler.moo7, FarmAddonSoundHandler.moo8);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return Animania.RANDOM.nextBoolean() ? FarmAddonSoundHandler.cowHurt1 : FarmAddonSoundHandler.cowHurt2;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return Animania.RANDOM.nextBoolean() ? FarmAddonSoundHandler.cowDeath1 : FarmAddonSoundHandler.cowDeath2;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.10F, 1.0F);
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateFemale(this, EntityBullBase.class);
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
				if (this.getCustomNameTag().trim().toLowerCase().equals("purp"))
					result = FluidUtil.tryFillContainer(one, FluidUtil.getFluidHandler(new ItemStack(Items.LAVA_BUCKET)), 1000, player, true);
				else
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
	public CowEntityBase createChild(AgeableEntity ageable)
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

			if (this.getHasKids())
				probeInfo.text(I18n.translateToLocal("text.waila.milkable"));

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
