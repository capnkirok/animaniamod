package com.animania.addons.extra.common.entity.peafowl;

import java.util.Set;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.addons.extra.common.entity.amphibians.EntityAmphibian;
import com.animania.addons.extra.common.handler.ExtraAddonItemHandler;
import com.animania.addons.extra.common.handler.ExtraAddonSoundHandler;
import com.animania.addons.extra.config.ExtraConfig;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IAnimaniaAnimalBase;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.entities.generic.GenericBehavior;
import com.animania.common.entities.generic.ai.GenericAIFindFood;
import com.animania.common.entities.generic.ai.GenericAIFindWater;
import com.animania.common.entities.generic.ai.GenericAILookIdle;
import com.animania.common.entities.generic.ai.GenericAIPanic;
import com.animania.common.entities.generic.ai.GenericAISleep;
import com.animania.common.entities.generic.ai.GenericAITempt;
import com.animania.common.entities.generic.ai.GenericAIWanderAvoidWater;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.compat.top.providers.entity.TOPInfoProviderBase;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EntityAnimaniaPeacock extends Animal implements TOPInfoProviderBase, IAnimaniaAnimalBase
{
	protected static final EntityDataAccessor<Boolean> FED = SynchedEntityData.<Boolean> createKey(EntityAnimaniaPeacock.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> HANDFED = SynchedEntityData.defineId(EntityAnimaniaPeacock.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Boolean> WATERED = SynchedEntityData.defineId(EntityAnimaniaPeacock.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityAnimaniaPeacock.class, EntityDataSerializers.VARINT);
	public static final Set<ItemStack> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemStackArray(ExtraConfig.settings.peacockFood));
	protected static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(EntityAnimaniaPeacock.class, EntityDataSerializers.BOOLEAN);
	protected static final EntityDataAccessor<Float> SLEEPTIMER = SynchedEntityData.defineId(EntityAnimaniaPeacock.class, EntityDataSerializers.FLOAT);
	protected static final EntityDataAccessor<Boolean> INTERACTED = SynchedEntityData.defineId(EntityAnimaniaPeacock.class, EntityDataSerializers.BOOLEAN);

	protected ResourceLocation resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_blue.png");
	protected ResourceLocation resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_blue_blink.png");
	public float wingRotation;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;
	protected int fedTimer;
	protected int wateredTimer;
	protected int happyTimer;
	public int blinkTimer;
	protected int damageTimer;
	public PeacockType type;
	private int featherCounter;
	protected EntityGender gender;
	public int lidCol;

	public EntityAnimaniaPeacock(Level levelIn)
	{
		super(levelIn);

		this.goalSelector.addGoal(0, new SwimmingGoal(this));
		if (!AnimaniaConfig.gameRules.ambianceMode)
		{
			this.goalSelector.addGoal(1, new GenericAIFindWater<>(this, 1.0D, null, EntityAnimaniaPeacock.class, true));
			this.goalSelector.addGoal(1, new GenericAIFindFood<>(this, 1.0D, null, true));
		}
		this.goalSelector.addGoal(2, new GenericAIPanic<>(this, 1.4D));
		this.goalSelector.addGoal(3, new GenericAITempt<>(this, 1.2D, false, EntityAnimaniaPeacock.TEMPTATION_ITEMS));
		this.goalSelector.addGoal(4, new GenericAIWanderAvoidWater(this, 1.0D));
		this.goalSelector.addGoal(5, new WatchClosestFromSideGoal(this, Player.class, 6.0F));
		if (AnimaniaConfig.gameRules.animalsSleep)
		{
			this.goalSelector.addGoal(6, new GenericAISleep<EntityAnimaniaPeacock>(this, 0.8, AnimaniaHelper.getBlock(ExtraConfig.settings.peacockBed), AnimaniaHelper.getBlock(ExtraConfig.settings.peacockBed2), EntityAnimaniaPeacock.class));
		}
		this.goalSelector.addGoal(11, new GenericAILookIdle<>(this));
		if (AnimaniaConfig.gameRules.animalsCanAttackOthers)
		{
			this.goalSelector.addGoal(8, new LeapAtTargetGoal(this, 0.2F));
			this.goalSelector.addGoal(9, new AttackMeleeGoal(this, 1.0D, true));
		}
		this.targetTasks.addTask(0, new HurtByTargetGoal(this, false, new Class[0]));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer * 2 + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer * 2 + this.rand.nextInt(100);
		this.blinkTimer = 80 + this.rand.nextInt(80);
		this.enablePersistence();
		this.happyTimer = 60;
		this.featherCounter = AnimaniaConfig.careAndFeeding.featherTimer;

	}

	@Override
	protected void initEntityAI()
	{
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void setPosition(double x, double y, double z)
	{
		super.setPosition(x, y, z);
	}

	@Override
	public void setInLove(Player player)
	{
		this.level.broadcastEntityEvent(this, (byte) 18);
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void fall(float distance, float damageMultiplier)
	{

	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{
		return GenericBehavior.interactCommon(this, player, hand, null) ? true : super.processInteract(player, hand);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0F);

		if (flag)
		{
			this.applyEnchantments(this, entityIn);
		}

		if (entityIn instanceof EntityAmphibian)
		{
			this.setFed(true);
		}

		// Custom Knockback
		if (entityIn instanceof Player)
		{
			((LivingEntity) entityIn).knockBack(this, 1, this.getX() - entityIn.getX(), this.getZ() - entityIn.getZ());
		}

		return flag;
	}

	public ResourceLocation getResourceLocation()
	{
		return this.resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink()
	{
		return this.resourceLocationBlink;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(7.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaPeacock.FED, true);
		this.dataManager.register(EntityAnimaniaPeacock.HANDFED, false);
		this.dataManager.register(EntityAnimaniaPeacock.WATERED, true);
		this.dataManager.register(EntityAnimaniaPeacock.AGE, Integer.valueOf(0));
		this.dataManager.register(EntityAnimaniaPeacock.SLEEPING, false);
		this.dataManager.register(EntityAnimaniaPeacock.SLEEPTIMER, Float.valueOf(0.0F));
		this.dataManager.register(INTERACTED, false);
	}

	@Override
	public void writeEntityToNBT(CompoundTag CompoundTag)
	{
		super.writeEntityToNBT(CompoundTag);
		GenericBehavior.writeCommonNBT(CompoundTag, this);

	}

	@Override
	public void readEntityFromNBT(CompoundTag CompoundTag)
	{
		super.readEntityFromNBT(CompoundTag);
		GenericBehavior.readCommonNBT(CompoundTag, this);
	}

	@Override
	public EntityDataAccessor<Integer> getAgeParam()
	{
		return AGE;
	}

	@Override
	public EntityDataAccessor<Boolean> getSleepingParam()
	{
		return SLEEPING;
	}

	@Override
	public EntityDataAccessor<Boolean> getHandFedParam()
	{
		return HANDFED;
	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return this instanceof EntityPeachickBase ? null : new ResourceLocation("extra/" + Animania.MODID, "peacocks/peacock_" + this.type.toString().toLowerCase());
	}

	@Override
	public void onLivingUpdate()
	{

		super.onLivingUpdate();

		GenericBehavior.livingUpdateCommon(this);

		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float) (this.destPos + (this.onGround || this.isPassenger() ? -1 : 4) * 0.3D);
		this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

		if (!this.onGround && !this.isPassenger() && this.wingRotDelta < 1.0F)
			this.wingRotDelta = 1.0F;

		this.wingRotDelta = (float) (this.wingRotDelta * 0.9D);

		if (!this.onGround && !this.isPassenger() && this.motionY < 0.0D)
			this.motionY *= 0.6D;

		this.wingRotation += this.wingRotDelta * 2.0F;

		if (this instanceof EntityPeacockBase && AnimaniaConfig.gameRules.birdsDropFeathers)
		{
			this.featherCounter--;
			if (this.featherCounter <= 0)
			{
				this.featherCounter = AnimaniaConfig.careAndFeeding.featherTimer;
				Item feather;

				feather = switch (this.type)
				{
				case BLUE -> ExtraAddonItemHandler.peacockFeatherBlue;
				case CHARCOAL -> ExtraAddonItemHandler.peacockFeatherCharcoal;
				case OPAL -> ExtraAddonItemHandler.peacockFeatherOpal;
				case PEACH -> ExtraAddonItemHandler.peacockFeatherPeach;
				case PURPLE -> ExtraAddonItemHandler.peacockFeatherPurple;
				case TAUPE -> ExtraAddonItemHandler.peacockFeatherTaupe;
				default -> ExtraAddonItemHandler.peacockFeatherWhite;
				};

				if (!this.level.isClientSide)
				{
					ItemStack item = new ItemStack(feather, 1);
					EntityItem entityitem = new EntityItem(this.level, this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D, item);
					AnimaniaHelper.spawnEntity(this.level, entityitem);
				}
			}

		}
	}

	@Override
	public EntityDataAccessor<Boolean> getFedParam()
	{
		return FED;
	}

	@Override
	public EntityDataAccessor<Boolean> getWateredParam()
	{
		return WATERED;
	}

	protected void fall(float p_70069_1_)
	{
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.05F, 1.0F);
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed
	 * it (wheat, carrots or seeds depending on the animal type)
	 */
	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && AnimaniaHelper.containsItemStack(TEMPTATION_ITEMS, stack);
	}

	@Override
	public void updatePassenger(Entity passenger)
	{
		super.updatePassenger(passenger);
		float f = MathHelper.sin(this.renderYawOffset * 0.017453292F);
		float f1 = MathHelper.cos(this.renderYawOffset * 0.017453292F);
		float f2 = 0.1F;
		float f3 = 0.0F;
		passenger.setPosition(this.getX() + 0.1F * f, this.getY() + this.height * 0.5F + passenger.getYOffset() + 0.0D, this.getZ() - 0.1F * f1);

		if (passenger instanceof LivingEntity)
			((LivingEntity) passenger).renderYawOffset = this.renderYawOffset;
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable)
	{
		return null;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return GenericBehavior.getAmbientSound(this, ExtraAddonSoundHandler.peacock1, ExtraAddonSoundHandler.peacock2, ExtraAddonSoundHandler.peacock3, ExtraAddonSoundHandler.peacock4, ExtraAddonSoundHandler.peacock5, ExtraAddonSoundHandler.peacock7, ExtraAddonSoundHandler.peacock8, ExtraAddonSoundHandler.peacock9, ExtraAddonSoundHandler.peacock10);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return GenericBehavior.getRandomSound(ExtraAddonSoundHandler.peacockHurt1, ExtraAddonSoundHandler.peacockHurt2);
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return GenericBehavior.getRandomSound(ExtraAddonSoundHandler.peacockHurt1, ExtraAddonSoundHandler.peacockHurt2);
	}

	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.type, this.gender));
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		return new ItemStack(this.getSpawnEgg());
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 0;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 0;
	}

	@Override
	public EntityGender getEntityGender()
	{
		return this.gender;
	}

	@Override
	public Set<ItemStack> getFoodItems()
	{
		return TEMPTATION_ITEMS;
	}

	@Override
	public void setSleepingPos(BlockPos pos)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public BlockPos getSleepingPos()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class[] getFoodBlocks()
	{
		return new Class[] { BlockSeeds.class };
	}

	@Override
	public int getBlinkTimer()
	{
		return this.blinkTimer;
	}

	@Override
	public void setBlinkTimer(int i)
	{
		this.blinkTimer = i;
	}

	@Override
	public int getEatTimer()
	{
		return 0;
	}

	@Override
	public void setEatTimer(int i)
	{
	}

	@Override
	public int getFedTimer()
	{
		return this.fedTimer;
	}

	@Override
	public void setFedTimer(int i)
	{
		this.fedTimer = i;
	}

	@Override
	public EntityDataAccessor<Boolean> getInteractedParam()
	{
		return INTERACTED;
	}

	@Override
	public int getWaterTimer()
	{
		return this.wateredTimer;
	}

	@Override
	public void setWaterTimer(int i)
	{
		this.wateredTimer = i;
	}

	@Override
	public int getDamageTimer()
	{
		return this.damageTimer;
	}

	@Override
	public void setDamageTimer(int i)
	{
		this.damageTimer = i;
	}

	@Override
	public int getHappyTimer()
	{
		return this.happyTimer;
	}

	@Override
	public void setHappyTimer(int i)
	{
		this.happyTimer = i;
	}

	@Override
	public AnimaniaType getAnimalType()
	{
		return this.type;
	}

	@Override
	public EntityDataAccessor<Float> getSleepTimerParam()
	{
		return SLEEPTIMER;
	}

}
