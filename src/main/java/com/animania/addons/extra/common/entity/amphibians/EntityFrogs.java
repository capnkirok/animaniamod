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
import com.animania.common.helper.RegistryHelper.RItem;
import com.animania.common.items.ItemEntityEgg;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityEntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.potion.MobEffectInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class EntityFrogs extends EntityAmphibian
{

	private static final EntityDataAccessor<Integer> FROGS_TYPE = SynchedEntityData.<Integer> defineId(EntityFrogs.class, EntityEntityDataSerializers.INT);

	public EntityFrogs(Level levelIn)
	{
		super(levelIn, true);
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

		if (this.level.isClientSide)
			return null;

		this.setFrogsType(this.rand.nextInt(2));

		return livingdata;
	}

	@Override
	public void writeEntityToNBT(CompoundTag compound)
	{
		super.writeEntityToNBT(compound);
		compound.putInteger("FrogsType", this.getFrogsType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(CompoundTag compound)
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
		this.goalSelector.addGoal(1, new SwimmingGoal(this));
		if (!this.getCustomNameTag().equals("Pepe"))
		{
			this.goalSelector.addGoal(1, new EntityAmphibian.AIPanic(this, 2.2D));
			this.goalSelector.addGoal(2, new AvoidEntityGoal<Player>(this, Player.class, 6.0F, 1.5D, 1.5D));
		}
		else if (this.getCustomNameTag().equals("Pepe"))
		{
			this.tasks.taskEntries.clear();
			this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.5F));
			this.goalSelector.addGoal(2, new AttackMeleeGoal(this, 2.0D, true));
			this.targetTasks.addTask(1, new HurtByTargetGoal(this, false, new Class[0]));
			this.targetTasks.addTask(2, new NearestAttackableTargetGoal<>(this, EntityFerretBase.class, true));
			this.targetTasks.addTask(3, new NearestAttackableTargetGoal<>(this, EntityHedgehog.class, true));
			this.targetTasks.addTask(4, new NearestAttackableTargetGoal<>(this, EntityHedgehogAlbino.class, true));
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
			this.setHealth(20);
		}
		this.goalSelector.addGoal(4, new WatchClosestGoal(this, Player.class, 10.0F));
		this.goalSelector.addGoal(5, new WanderGoal(this, 0.6D));

	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{

		ItemStack stack = player.getItemInHand(hand);

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.NAME_TAG)
		{
			if (!stack.hasDisplayName())
			{
				return false;
			}
			else
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
			this.addMobEffectInstance(new MobEffectInstance(MobEffects.SPEED, 5, 4, true, false));
			return ExtraAddonSoundHandler.reeee;
		}

		if (Animania.RANDOM.nextDouble() < 0.3 && this.getCustomNameTag().equalsIgnoreCase("me_irl") && Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
		{
			return ExtraAddonSoundHandler.oooohhh;
		}

		switch (chooser)
		{
		case 0:
			return ExtraAddonSoundHandler.frogLiving1;
		case 1:
			return ExtraAddonSoundHandler.frogLiving2;
		case 2:
			return ExtraAddonSoundHandler.frogLiving3;
		default:
			return null;
		}
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
		if (entityIn instanceof Player)
		{
			((LivingEntity) entityIn).knockBack(this, 1, this.getX() - entityIn.getX(), this.getZ() - entityIn.getZ());
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
		// Player) {
		// ((Player)
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
	public RItem getSpawnEgg()
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
