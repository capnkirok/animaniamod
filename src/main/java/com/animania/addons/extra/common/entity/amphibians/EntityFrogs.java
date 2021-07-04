package com.animania.addons.extra.common.entity.amphibians;

import java.util.Calendar;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.rodents.EntityFerretBase;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehog;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehogAlbino;
import com.animania.addons.extra.common.handler.ExtraAddonSoundHandler;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.common.handler.DamageSourceHandler;
import com.animania.common.items.ItemEntityEgg;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityFrogs extends EntityAmphibian
{

	private static final DataParameter<Integer> FROGS_TYPE = EntityDataManager.<Integer> defineId(EntityFrogs.class, DataSerializers.INT);

	public EntityFrogs(World worldIn)
	{
		super(worldIn, true);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityFrogs.FROGS_TYPE, Integer.valueOf(this.rand.nextInt(2)));
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata)
	{
		livingdata = super.onInitialSpawn(difficulty, livingdata);

		if (this.world.isRemote)
			return null;

		this.setFrogsType(this.rand.nextInt(2));

		return livingdata;
	}

	@Override
	public void writeEntityToNBT(CompoundNBT compound)
	{
		super.writeEntityToNBT(compound);
		compound.setInteger("FrogsType", this.getFrogsType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(CompoundNBT compound)
	{
		super.readEntityFromNBT(compound);
		this.setFrogsType(compound.getInteger("FrogsType"));
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return new ResourceLocation("extra/" + Animania.MODID, "frog");
	}

	public int getFrogsType()
	{
		return this.dataManager.get(EntityFrogs.FROGS_TYPE).intValue();
	}

	public void setFrogsType(int frogsId)
	{
		this.dataManager.set(EntityFrogs.FROGS_TYPE, Integer.valueOf(frogsId));
	}

	@Override
	protected void initEntityAI()
	{
		this.tasks.addTask(1, new EntityAISwimming(this));
		if (!this.getCustomNameTag().equals("Pepe"))
		{
			this.tasks.addTask(1, new EntityAmphibian.AIPanic(this, 2.2D));
			this.tasks.addTask(2, new EntityAIAvoidEntity<EntityPlayer>(this, EntityPlayer.class, 6.0F, 1.5D, 1.5D));
		} else if (this.getCustomNameTag().equals("Pepe"))
		{
			this.tasks.taskEntries.clear();
			this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.5F));
			this.tasks.addTask(2, new EntityAIAttackMelee(this, 2.0D, true));
			this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityFerretBase>(this, EntityFerretBase.class, true));
			this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityHedgehog>(this, EntityHedgehog.class, true));
			this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<EntityHedgehogAlbino>(this, EntityHedgehogAlbino.class, true));
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
			this.setHealth(20);
		}
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		this.tasks.addTask(5, new EntityAIWander(this, 0.6D));

	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{

		ItemStack stack = player.getHeldItem(hand);

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.NAME_TAG)
		{
			if (!stack.hasDisplayName())
			{
				return false;
			} else
			{
				LivingEntity LivingEntity = this;
				LivingEntity.setCustomNameTag(stack.getDisplayName());

				LivingEntity.enablePersistence();
				if (!player.capabilities.isCreativeMode)
				{
					stack.shrink(1);
				}

				if (stack.getDisplayName().equals("Pepe"))
				{
					this.initEntityAI();
					this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
					this.setHealth(20);
				}

			}

		}
		return super.processInteract(player, hand);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		int chooser = Animania.RANDOM.nextInt(4);
		if (this.getCustomNameTag().equals("Pepe") && 0.1 > Animania.RANDOM.nextDouble())
		{
			this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5, 4, true, false));
			return ExtraAddonSoundHandler.reeee;
		}

		if (Animania.RANDOM.nextDouble() < 0.3 && this.getCustomNameTag().equalsIgnoreCase("me_irl") && Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
		{
			return ExtraAddonSoundHandler.oooohhh;
		}

		if (chooser == 0)
			return ExtraAddonSoundHandler.frogLiving1;
		else if (chooser == 1)
			return ExtraAddonSoundHandler.frogLiving2;
		else if (chooser == 2)
			return ExtraAddonSoundHandler.frogLiving3;
		else
			return null;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSourceHandler.pepeDamage, 2.0F);
		entityIn.attackEntityFrom(DamageSourceHandler.pepeDamage, 2.0F);

		if (flag)
		{
			this.applyEnchantments(this, entityIn);
		}

		// Custom Knockback
		if (entityIn instanceof EntityPlayer)
		{
			((LivingEntity) entityIn).knockBack(this, 1, this.posX - entityIn.posX, this.posZ - entityIn.posZ);
		}

		return flag;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return null;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return null;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - this.getGrowingAge() * 2);
	}

	@Override
	public void onDeath(DamageSource cause)
	{
		// if (this.getCustomNameTag().equals("Pepe"))
		// if (cause.getEntity() != null && cause.getEntity() instanceof
		// EntityPlayer) {
		// ((EntityPlayer)
		// cause.getEntity()).addStat(AnimaniaAchievements.FeelsBadMan, 1);
		// AchievementPage.getAchievementPage("Animania").getAchievements().add(AnimaniaAchievements.FeelsBadMan);
		// }

		super.onDeath(cause);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.04F, 1.1F);
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(AmphibianType.FROG, EntityGender.NONE));
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 1860371;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 1793554;
	}

	@Override
	public AnimaniaType getAnimalType()
	{
		return AmphibianType.FROG;
	}

}
