package com.animania.common.entities.pigs;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IImpregnable;
import com.animania.api.interfaces.IMateable;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.handler.CompatHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderPig;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySowBase extends EntityAnimaniaPig implements TOPInfoProviderPig, IMateable, IImpregnable
{
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntitySowBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	public int dryTimerSow;
	protected static final DataParameter<Boolean> PREGNANT = EntityDataManager.<Boolean>createKey(EntitySowBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HAS_KIDS = EntityDataManager.<Boolean>createKey(EntitySowBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> FERTILE = EntityDataManager.<Boolean>createKey(EntitySowBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> GESTATION_TIMER = EntityDataManager.<Integer>createKey(EntitySowBase.class, DataSerializers.VARINT);

	public EntitySowBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.1F, 1.0F); 
		this.width = 1.1F;
		this.height = 1.0F;
		this.stepHeight = 1.1F;
		this.gender = EntityGender.FEMALE;
	}

	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{
		GenericBehavior.initialSpawnFemale(this, EntityAnimaniaPig.class);

		return livingdata;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.265D);
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

		if (!(entity instanceof EntityPlayer))
			return false;
		else
		{
			EntityPlayer entityplayer = (EntityPlayer) entity;
			ItemStack itemstack = entityplayer.getHeldItemMainhand();

			if (itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.CARROT_ON_A_STICK)
				return true;
			else
			{
				itemstack = entityplayer.getHeldItemOffhand();
				return itemstack != ItemStack.EMPTY && itemstack.getItem() == Items.CARROT_ON_A_STICK;
			}
		}
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntitySowBase.MATE_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(EntitySowBase.PREGNANT, false);
		this.dataManager.register(EntitySowBase.HAS_KIDS, false);
		this.dataManager.register(EntitySowBase.FERTILE, true);
		this.dataManager.register(EntitySowBase.GESTATION_TIMER, Integer.valueOf(AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200)));

	}

	@Override
	public DataParameter<Integer> getGestationParam()
	{
		return GESTATION_TIMER;
	}

	@Override
	public DataParameter<Boolean> getPregnantParam()
	{
		return PREGNANT;
	}
	
	@Override
	public int getDryTimer()
	{
		return dryTimerSow;
	}
	
	@Override
	public void setDryTimer(int i)
	{
		this.dryTimerSow = i;
	}

	@Override
	public DataParameter<Boolean> getFertileParam()
	{
		return FERTILE;
	}

	@Override
	public DataParameter<Boolean> getHasKidsParam()
	{
		return HAS_KIDS;
	}

	@Override
	public DataParameter<Optional<UUID>> getMateUniqueIdParam()
	{
		return EntitySowBase.MATE_UNIQUE_ID;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, ModSoundEvents.pig1, ModSoundEvents.pig2, ModSoundEvents.pig4, ModSoundEvents.pig5, ModSoundEvents.pig6, ModSoundEvents.pig7);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(ModSoundEvents.pigHurt1, ModSoundEvents.pigHurt2, ModSoundEvents.pig3);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GenericBehavior.getRandomSound(ModSoundEvents.pigHurt1, ModSoundEvents.pigHurt2, ModSoundEvents.pig3);
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		if (stack != ItemStack.EMPTY && stack.getItem() == Items.CARROT_ON_A_STICK && !this.isBeingRidden() && this.getWatered() && this.getFed())
		{
			player.startRiding(this);
			return true;
		}
		else
			return super.processInteract(player, hand);
	}

	@Override
	public void fall(float distance, float damageMultiplier)
	{
		super.fall(distance, damageMultiplier);
	}

	@Override
	public void onLivingUpdate()
	{
		GenericBehavior.livingUpdateFemale(this, EntityHogBase.class);

		super.onLivingUpdate();
	}

	

	@Override
	public EntitySowBase createChild(EntityAgeable ageable)
	{
		return null;
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && (EntityAnimaniaPig.TEMPTATION_ITEMS.contains(stack.getItem()) || ItemStack.areItemStacksEqual(stack, this.slop));
	}

	@Override
	@net.minecraftforge.fml.common.Optional.Method(modid=CompatHandler.THEONEPROBE_ID)
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
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
				if (this.getGestation() > 1) {
					int bob = this.getGestation();
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1") + " (" + bob + " " + I18n.translateToLocal("text.waila.pregnant2") + ")" );
				} else {
					probeInfo.text(I18n.translateToLocal("text.waila.pregnant1"));
				}
			} 
		}
		TOPInfoProviderPig.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}
}
