package com.animania.addons.farm.common.entity.horses;

import java.util.Iterator;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.IMateable;
import com.animania.api.interfaces.ISterilizable;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIMate;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.google.common.base.Optional;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityStallionBase extends EntityAnimaniaHorse implements TOPInfoProviderMateable, IMateable, ISterilizable
{

	protected static final DataParameter<Boolean> STERILIZED = EntityDataManager.<Boolean> createKey(EntityStallionBase.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>> createKey(EntityStallionBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	private static final String[] HORSE_TEXTURES = new String[] { "black", "bw1", "bw2", "grey", "red", "white" };

	public EntityStallionBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.8F, 2.2F);
		this.width = 1.8F;
		this.height = 2.2F;
		this.stepHeight = 1.2F;
		this.mateable = true;
		this.gender = EntityGender.MALE;
		// this.tasks.addTask(1, new EntityAIFollowMateHorses(this, 1.1D));
		if (!getSterilized())
			this.tasks.addTask(3, new GenericAIMate<EntityStallionBase, EntityMareBase>(this, 1.0D, EntityMareBase.class, EntityFoalBase.class, EntityAnimaniaHorse.class));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);

	}

	@Override
	protected void entityInit()
	{
		this.dataManager.register(STERILIZED, false);
		this.dataManager.register(MATE_UNIQUE_ID, Optional.<UUID> absent());
		super.entityInit();
	}

	@Override
	public double getMountedYOffset()
	{
		return this.height * 0.72D;
	}

	@Override
	public void openGUI(EntityPlayer playerEntity)
	{
		if (!this.world.isRemote && (!this.isBeingRidden() || this.isPassenger(playerEntity)))
		{
			this.horseChest.setCustomName(this.getName());
			playerEntity.openGuiHorseInventory(this, this.horseChest);
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

		if (!(entity instanceof EntityPlayer))
		{
			return false;
		} else if (this.isHorseSaddled())
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
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
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getHeadAngleX(float p_70890_1_)
	{

		if (this.isBeingRidden())
		{
			return 0;
		}

		if (this.eatTimer > 4 && this.eatTimer <= 156)
		{
			float f = (this.eatTimer - 4 - p_70890_1_) / 80.0F;
			return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 500F) * MathHelper.sin(f * 28.7F);
		} else
		{
			return this.eatTimer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch * 0.017453292F;
		}
	}

	@Override
	public ResourceLocation getResourceLocation()
	{
		return resourceLocation;
	}

	@Override
	public ResourceLocation getResourceLocationBlink()
	{
		return resourceLocationBlink;
	}

	@Override
	public void onLivingUpdate()
	{

		if (this.getColorNumber() > 5)
		{
			this.setColorNumber(0);
		}

		GenericBehavior.livingUpdateMateable(this, EntityMareBase.class);

		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{

		ItemStack stack = player.getHeldItem(hand);

		if (!this.isChild() && this.isHorseSaddled())
		{

			if (player.isSneaking())
			{
				this.openGUI(player);
				return true;
			} else
			{
				return super.processInteract(player, hand);
			}

		} else if (!player.isSneaking() && stack != null && this.isHorseSaddled() && !this.getSleeping() && !this.isBeingRidden() && this.getWatered() && this.getFed())
		{
			this.mountTo(player);
			// player.addStat(AnimaniaAchievements.Horseriding, 1);
			return true;
		} else
		{
			return super.processInteract(player, hand);
		}
	}

	@Override
	public EntityStallionBase createChild(EntityAgeable p_90011_1_)
	{
		return null;
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
			EntityAIBase ai = entry.action;
			if (ai instanceof GenericAIMate)
			{
				entry.using = false;
				ai.resetTask();
				it.remove();
			}
		}
		setSterilized(true);
	}

	@Override
	public DataParameter<Optional<UUID>> getMateUniqueIdParam()
	{
		return MATE_UNIQUE_ID;
	}

}
