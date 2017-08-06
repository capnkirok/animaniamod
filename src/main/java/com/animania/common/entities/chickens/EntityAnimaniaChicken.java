package com.animania.common.entities.chickens;

import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.chickens.ai.EntityAIFindFood;
import com.animania.common.entities.chickens.ai.EntityAIFindWater;
import com.animania.common.entities.chickens.ai.EntityAIPanicChickens;
import com.animania.common.entities.chickens.ai.EntityAISwimmingChicks;
import com.animania.common.entities.chickens.ai.EntityAIWanderChickens;
import com.animania.common.entities.chickens.ai.EntityAIWatchClosestFromSide;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAnimaniaChicken extends EntityChicken
{

	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaChicken.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaChicken.class, DataSerializers.BOOLEAN);
	protected static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] { Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS });
	public boolean chickenJockey;
	protected ResourceLocation resourceLocation;
	protected ResourceLocation resourceLocationBlink;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;
	protected int fedTimer;
	protected int wateredTimer;
	protected int happyTimer;
	public int blinkTimer;
	private int featherTimer;
	protected int damageTimer;
	protected ChickenType type;
	protected Item dropRaw = Items.CHICKEN;
	protected Item dropCooked = Items.COOKED_CHICKEN;
	protected Item oldDropRaw = Items.CHICKEN;
	protected Item oldDropCooked = Items.COOKED_CHICKEN;

	public EntityAnimaniaChicken(World worldIn)
	{
		super(worldIn);
		this.tasks.taskEntries.clear();
		this.tasks.addTask(0, new EntityAISwimmingChicks(this));
		this.tasks.addTask(1, new EntityAIPanicChickens(this, 1.4D));
		this.tasks.addTask(2, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(4, new EntityAITempt(this, 1.2D, false, EntityAnimaniaChicken.TEMPTATION_ITEMS));
		this.tasks.addTask(3, new EntityAIFindFood(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWanderChickens(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosestFromSide(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + this.rand.nextInt(80);
		this.featherTimer = AnimaniaConfig.careAndFeeding.featherTimer + rand.nextInt(1000);
		this.enablePersistence();
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);

		player.addStat(this.type.getAchievement(), 1);
		if (player.hasAchievement(AnimaniaAchievements.Leghorn) && player.hasAchievement(AnimaniaAchievements.Orpington) && player.hasAchievement(AnimaniaAchievements.PlymouthRock) && player.hasAchievement(AnimaniaAchievements.RhodeIslandRed) && player.hasAchievement(AnimaniaAchievements.Wyandotte))
			player.addStat(AnimaniaAchievements.Chickens, 1);
		if (!player.capabilities.isCreativeMode)
			stack.setCount(stack.getCount() - 1);
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET)
		{
			this.setWatered(true);
			this.setInLove(player);
			return true;
		}
		else
			return super.processInteract(player, hand);
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
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.29D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaChicken.FED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaChicken.WATERED, Boolean.valueOf(true));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setBoolean("IsChickenJockey", this.chickenJockey);
		nbttagcompound.setBoolean("Fed", this.getFed());
		nbttagcompound.setBoolean("Watered", this.getWatered());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);

		this.chickenJockey = nbttagcompound.getBoolean("IsChickenJockey");

		this.setFed(nbttagcompound.getBoolean("Fed"));
		this.setWatered(nbttagcompound.getBoolean("Watered"));
	}

	@Override
	public void onLivingUpdate()
	{

		super.onLivingUpdate();
		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float) (this.destPos + (this.onGround ? -1 : 4) * 0.3D);
		this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

		this.fallDistance = 0;

		if (!this.world.isRemote && !this.isChild() && AnimaniaConfig.drops.chickensDropFeathers && !this.isChickenJockey() && --this.featherTimer <= 0)
		{
			this.playSound(ModSoundEvents.chickenCluck2, 0.5F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.dropItem(Items.FEATHER, 1);
			this.featherTimer = AnimaniaConfig.careAndFeeding.featherTimer + rand.nextInt(1000);
		}

		if (!this.onGround && this.wingRotDelta < 1.0F)
			this.wingRotDelta = 1.0F;

		this.wingRotDelta = (float) (this.wingRotDelta * 0.9D);

		if (!this.onGround && this.motionY < 0.0D)
			this.motionY *= 0.6D;

		this.wingRotation += this.wingRotDelta * 2.0F;

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
				this.blinkTimer = 100 + this.rand.nextInt(100);
		}

		if (this.fedTimer > -1)
		{
			this.fedTimer--;

			if (this.fedTimer == 0)
				this.setFed(false);
		}

		if (this.wateredTimer > -1)
		{
			this.wateredTimer--;

			if (this.wateredTimer == 0)
				this.setWatered(false);
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();

		if (!fed && !watered)
		{
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
			if (AnimaniaConfig.gameRules.animalsStarve)
			{
				if (this.damageTimer >= AnimaniaConfig.careAndFeeding.starvationTimer)
				{
					this.attackEntityFrom(DamageSource.STARVE, 4f);
					this.damageTimer = 0;
				}
				this.damageTimer++;
			}

		}
		else if (!fed || !watered)
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));

		if (this.happyTimer > -1)
		{
			this.happyTimer--;
			if (this.happyTimer == 0)
			{
				this.happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && AnimaniaConfig.gameRules.showUnhappyParticles)
				{
					double d = this.rand.nextGaussian() * 0.001D;
					double d1 = this.rand.nextGaussian() * 0.001D;
					double d2 = this.rand.nextGaussian() * 0.001D;
					this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + this.rand.nextFloat() * this.width - this.width, this.posY + 1.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width - this.width, d, d1, d2);
				}
			}
		}

	}

	public boolean getFed()
	{
		return this.dataManager.get(EntityAnimaniaChicken.FED).booleanValue();
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityAnimaniaChicken.FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		}
		else
			this.dataManager.set(EntityAnimaniaChicken.FED, Boolean.valueOf(false));
	}

	public boolean getWatered()
	{
		return this.dataManager.get(EntityAnimaniaChicken.WATERED).booleanValue();
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(EntityAnimaniaChicken.WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		}
		else
			this.dataManager.set(EntityAnimaniaChicken.WATERED, Boolean.valueOf(false));
	}

	protected void fall(float p_70069_1_)
	{
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
			num = 6;
		else if (happy == 1)
			num = 12;
		else
			num = 24;

		Random rand = new Random();
		int chooser = rand.nextInt(num);

		if (chooser == 0)
			return ModSoundEvents.chickenCluck1;
		else if (chooser == 1)
			return ModSoundEvents.chickenCluck2;
		else if (chooser == 2)
			return ModSoundEvents.chickenCluck3;
		else if (chooser == 3)
			return ModSoundEvents.chickenCluck4;
		else if (chooser == 4)
			return ModSoundEvents.chickenCluck5;
		else
			return ModSoundEvents.chickenCluck6;
	}

	@Override
	protected SoundEvent getHurtSound()
	{
		return null;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return null;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.10F, 1.4F);
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && EntityAnimaniaChicken.TEMPTATION_ITEMS.contains(stack.getItem());
	}

	/**
	 * Get the experience points the entity currently has.
	 */
	@Override
	protected int getExperiencePoints(EntityPlayer player)
	{
		return this.isChickenJockey() ? 10 : super.getExperiencePoints(player);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void updatePassenger(Entity passenger)
	{
		super.updatePassenger(passenger);
		float f = MathHelper.sin(this.renderYawOffset * 0.017453292F);
		float f1 = MathHelper.cos(this.renderYawOffset * 0.017453292F);
		float f2 = 0.1F;
		float f3 = 0.0F;
		passenger.setPosition(this.posX + 0.1F * f, this.posY + this.height * 0.5F + passenger.getYOffset() + 0.0D, this.posZ - 0.1F * f1);

		if (passenger instanceof EntityLivingBase)
			((EntityLivingBase) passenger).renderYawOffset = this.renderYawOffset;
	}

	public boolean isChickenJockey()
	{
		return this.chickenJockey;
	}

	public void setChickenJockey(boolean jockey)
	{
		this.chickenJockey = jockey;
	}

	@Override
	public EntityChicken createChild(EntityAgeable ageable)
	{
		return null;
	}

	@Override
	protected Item getDropItem()
	{
		return Items.FEATHER;
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
	{
		this.dropFewItems(wasRecentlyHit, lootingModifier);
		this.dropEquipment(wasRecentlyHit, lootingModifier);
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		int happyDrops = 0;

		if (this.getWatered())
			happyDrops++;
		if (this.getFed())
			happyDrops++;

		ItemStack dropItem;
		if (AnimaniaConfig.drops.customMobDrops)
		{
			String drop = AnimaniaConfig.drops.chickenDrop;
			dropItem = AnimaniaHelper.getItem(drop);
			if (this.isBurning() && drop.equals(this.dropRaw.getRegistryName().toString()))
			{
				drop = this.dropCooked.getRegistryName().toString();
				dropItem = AnimaniaHelper.getItem(drop);
			}
		}
		else
		{
			if (AnimaniaConfig.drops.oldMeatDrops)
			{
				dropItem = new ItemStack(this.oldDropRaw, 1);
				if (this.isBurning())
					dropItem = new ItemStack(this.oldDropCooked, 1);
			}
			else
			{
				dropItem = new ItemStack(this.dropRaw, 1);
				if (this.isBurning())
					dropItem = new ItemStack(this.dropCooked, 1);
			}
		}

		if (happyDrops == 2 && dropItem !=null)
		{
			dropItem.setCount(1 + lootlevel);
			EntityItem entityitem = new EntityItem(this.world, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, dropItem);
			world.spawnEntity(entityitem);
			this.dropItem(Items.FEATHER, 1);
		}
		else if (happyDrops == 1 && dropItem !=null)
		{
			if (this.isBurning())
			{
				this.dropItem(Items.COOKED_CHICKEN, 1 + lootlevel);
				this.dropItem(Items.FEATHER, 1 + lootlevel);
			}
			else
			{
				this.dropItem(Items.CHICKEN, 1 + lootlevel);
				this.dropItem(Items.FEATHER, 1 + lootlevel);
			}
		}
		else if (happyDrops == 0)
			this.dropItem(Items.FEATHER, 1 + lootlevel);

	}

}
