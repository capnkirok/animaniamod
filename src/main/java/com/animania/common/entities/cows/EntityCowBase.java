package com.animania.common.entities.cows;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.ModSoundEvents;
import com.animania.common.entities.cows.ai.EntityAIMateCows;
import com.animania.common.entities.pigs.EntityHogBase;
import com.animania.common.entities.pigs.EntityPigletBase;
import com.animania.common.entities.pigs.PigType;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.compat.top.providers.entity.TOPInfoProviderMateable;
import com.animania.config.AnimaniaConfig;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCowBase extends EntityAnimaniaCow implements TOPInfoProviderMateable
{

	protected int gestationTimer;
	protected ItemStack milk = new ItemStack(Items.MILK_BUCKET);

	public EntityCowBase(World worldIn)
	{
		super(worldIn);
		this.setSize(1.4F, 1.8F);
		this.stepHeight = 1.1F;
		this.gestationTimer = AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(200);
		this.tasks.addTask(5, new EntityAIMateCows(this, 1.0D));
		this.mateable = true;

	}

	public int getGestationTimer()
	{
		return gestationTimer;
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
	}

	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{
		if (this.world.isRemote)
			return null;

		int cowCount = 0;
		int esize = this.world.loadedEntityList.size();
		for (int k = 0; k <= esize - 1; k++)
		{
			Entity entity = this.world.loadedEntityList.get(k);
			if (entity.getName().contains("Holstein") || entity.getName().contains("Friesian") || entity.getName().contains("Angus") || entity.getName().contains("Longhorn") || entity.getName().contains("Hereford"))
			{
				EntityAnimal ea = (EntityAnimal) entity;
				if (ea.hasCustomName() || ea.isInLove())
				{
					// cowCount = cowCount - 1;
				}
				else
					cowCount = cowCount + 1;
			}
		}

		if (cowCount <= AnimaniaConfig.spawn.spawnLimitCows)
		{

			int chooser = this.rand.nextInt(5);

			if (chooser == 0)
			{
				EntityBullBase entityCow = this.cowType.getMale(world);
				entityCow.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityCow);
				entityCow.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityCow.getUniqueID());
			}
			else if (chooser == 1)
			{
				EntityCalfBase entityCow = this.cowType.getChild(world);
				entityCow.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityCow);
				entityCow.setParentUniqueId(this.entityUniqueID);
			}
			else if (chooser > 2)
			{
				EntityBullBase entityCow = this.cowType.getMale(world);
				entityCow.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityCow);
				entityCow.setMateUniqueId(this.entityUniqueID);
				this.setMateUniqueId(entityCow.getUniqueID());
				EntityCalfBase entityCalf = this.cowType.getChild(world);
				entityCalf.setPosition(this.posX, this.posY, this.posZ);
				this.world.spawnEntity(entityCalf);
				entityCalf.setParentUniqueId(this.entityUniqueID);
			}

			this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));

			if (this.rand.nextFloat() < 0.05F)
				this.setLeftHanded(true);
			else
				this.setLeftHanded(false);

		}

		return livingdata;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		int happy = 0;
		int num = 0;

		if (this.getWatered())
			happy++;
		if (this.getFed())
			happy++;

		if (happy == 2)
			num = 16;
		else if (happy == 1)
			num = 32;
		else
			num = 60;

		Random rand = new Random();
		int chooser = rand.nextInt(num);

		if (chooser == 0)
			return ModSoundEvents.moo1;
		else if (chooser == 1)
			return ModSoundEvents.moo3;
		else if (chooser == 2)
			return ModSoundEvents.moo4;
		else if (chooser == 3)
			return ModSoundEvents.moo4;
		else if (chooser == 4)
			return ModSoundEvents.moo5;
		else if (chooser == 5)
			return ModSoundEvents.moo6;
		else if (chooser == 6)
			return ModSoundEvents.moo7;
		else if (chooser == 7)
			return ModSoundEvents.moo8;
		else
			return null;

	}

	@Override
	protected SoundEvent getHurtSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(2);

		if (chooser == 0)
			return ModSoundEvents.cowHurt1;
		else
			return ModSoundEvents.cowHurt2;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(2);

		if (chooser == 0)
			return ModSoundEvents.cowDeath1;
		else
			return ModSoundEvents.cowDeath2;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.10F, 1.0F);
	}

	@Override
	public void onLivingUpdate()
	{

		if (this.gestationTimer > -1 && this.getMateUniqueId() != null)
		{
			this.gestationTimer--;
			if (this.gestationTimer == 0)
			{

				this.gestationTimer = AnimaniaConfig.careAndFeeding.gestationTimer + this.rand.nextInt(2000);
				UUID MateID = this.getMateUniqueId();
				List entities = AnimaniaHelper.getEntitiesInRange(EntityBullBase.class, 16, this.world, this);
				int esize = entities.size();
				for (int k = 0; k <= esize - 1; k++) 
				{
					EntityBullBase entity = (EntityBullBase)entities.get(k);
					if (entity !=null && this.getFed() && this.getWatered() && entity.getPersistentID().equals(MateID)) {

						this.setInLove(null);
						CowType maleType = ((EntityAnimaniaCow) entity).cowType;
						CowType babyType = CowType.breed(maleType, this.cowType);

						EntityCalfBase entityCalf = babyType.getChild(world);
						entityCalf.setPosition(this.posX, this.posY + .2, this.posZ);
						this.world.spawnEntity(entityCalf);
						entityCalf.setParentUniqueId(this.getPersistentID());
						this.playSound(ModSoundEvents.mooCalf1, 0.50F, 1.1F);
						
						//BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(this, (EntityLiving) entity, entityCalf);
						//MinecraftForge.EVENT_BUS.post(event);


					}
				}
			}
		}

		super.onLivingUpdate();
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (this.getFed() && this.getWatered() && stack != ItemStack.EMPTY && stack.getItem() == Items.BUCKET)
		{
			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
			stack.shrink(1);

			if (stack.getCount() == 0)
				player.setHeldItem(hand, this.milk.copy());
			else if (!player.inventory.addItemStackToInventory(this.milk.copy()))
				player.dropItem(this.milk.copy(), false);

			this.setWatered(false);

			return true;
		}
		else if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET)
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

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{
		return this.eatTimer <= 0 ? 0.0F : this.eatTimer >= 4 && this.eatTimer <= 156 ? 1.0F : this.eatTimer < 4 ? (this.eatTimer - p_70894_1_) / 4.0F : -(this.eatTimer - 160 - p_70894_1_) / 4.0F;
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 156)
		{
			float f = (this.eatTimer - 4 - p_70890_1_) / 64.0F;
			return (float) Math.PI / 5F + (float) Math.PI * 7F / 100F * MathHelper.sin(f * 28.7F);
		}
		else
			return this.eatTimer > 0 ? (float) Math.PI / 5F : this.rotationPitch * 0.017453292F;
	}

	@Override
	public EntityCowBase createChild(EntityAgeable ageable)
	{

		return null;
	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data)
	{
		if (mode == ProbeMode.EXTENDED)
		{
			if (this.getWatered() && this.getFed())
			{
				probeInfo.text(TextFormatting.GREEN + I18n.translateToLocal("text.waila.milkable"));
			}
			/*		if(this.getGestationTimer() > -1)
			{
				probeInfo.text(TextFormatting.GREEN + I18n.translateToLocal("text.waila.pregnant1") + ", " + this.getGestationTimer() + " " + I18n.translateToLocal("text.waila.pregnant2"));
			} */
		}
		TOPInfoProviderMateable.super.addProbeInfo(mode, probeInfo, player, world, entity, data);
	}

}
