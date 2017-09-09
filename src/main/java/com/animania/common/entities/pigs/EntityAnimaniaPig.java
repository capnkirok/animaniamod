package com.animania.common.entities.pigs;

import java.util.Set;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.ISpawnable;
import com.animania.common.entities.pigs.ai.EntityAIFindFood;
import com.animania.common.entities.pigs.ai.EntityAIFindMud;
import com.animania.common.entities.pigs.ai.EntityAIFindSaltLickPigs;
import com.animania.common.entities.pigs.ai.EntityAIFindWater;
import com.animania.common.entities.pigs.ai.EntityAIPanicPigs;
import com.animania.common.entities.pigs.ai.EntityAIPigSnuffle;
import com.animania.common.entities.pigs.ai.EntityAISwimmingPigs;
import com.animania.common.entities.pigs.ai.EntityAITemptItemStack;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class EntityAnimaniaPig extends EntityAnimal implements ISpawnable
{

	protected static final DataParameter<Boolean> SADDLED = EntityDataManager.<Boolean>createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> MUDDY = EntityDataManager.<Boolean>createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Float> SPLASHTIMER = EntityDataManager.<Float>createKey(EntityAnimaniaPig.class, DataSerializers.FLOAT);
	protected static final DataParameter<Float> MUDTIMER = EntityDataManager.<Float>createKey(EntityAnimaniaPig.class, DataSerializers.FLOAT);
	protected static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> PLAYED = EntityDataManager.<Boolean>createKey(EntityAnimaniaPig.class, DataSerializers.BOOLEAN);
	public static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(AnimaniaHelper.getItemArray(AnimaniaConfig.careAndFeeding.pigFood));
	protected boolean boosting;
	protected int boostTime;
	protected int totalBoostTime;
	public int eatTimer;
	public EntityAIPigSnuffle entityAIEatGrass;
	protected int fedTimer;
	protected int wateredTimer;
	protected int playedTimer;
	protected int happyTimer;
	public int blinkTimer;
	protected int damageTimer;
	protected ItemStack slop;
	protected PigType pigType;
	protected Item dropRaw = Items.PORKCHOP;
	protected Item dropCooked = Items.COOKED_PORKCHOP;
	protected Item oldDropRaw = Items.PORKCHOP;
	protected Item oldDropCooked = Items.COOKED_PORKCHOP;
	protected EntityGender gender;

	public EntityAnimaniaPig(World worldIn)
	{
		super(worldIn);
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		this.playedTimer = AnimaniaConfig.careAndFeeding.playTimer + this.rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + this.rand.nextInt(80);
		this.enablePersistence();
		this.slop = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop);
	}

	@Override
	protected void initEntityAI()
	{
		this.entityAIEatGrass = new EntityAIPigSnuffle(this);
		this.tasks.addTask(0, new EntityAISwimmingPigs(this));
		this.tasks.addTask(1, new EntityAIFindMud(this, 1.2D));
		this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(3, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(5, new EntityAIFindFood(this, 1.0D));
		this.tasks.addTask(7, new EntityAIPanicPigs(this, 1.5D));
		this.tasks.addTask(9, new EntityAITempt(this, 1.2D, Items.CARROT_ON_A_STICK, false));
		this.tasks.addTask(10, new EntityAITempt(this, 1.2D, false, EntityAnimaniaPig.TEMPTATION_ITEMS));
		this.tasks.addTask(10, new EntityAITemptItemStack(this, 1.2d, UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop)));
		this.tasks.addTask(11, this.entityAIEatGrass);
		this.tasks.addTask(12, new EntityAIFindSaltLickPigs(this, 1.0));
		this.tasks.addTask(13, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(14, new EntityAILookIdle(this));

	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);

		if (this.entityAIEatGrass != null)
		{
			this.entityAIEatGrass.startExecuting();
			this.eatTimer = 80;
		}
		player.addStat(this.pigType.getAchievement(), 1);
		if (player.hasAchievement(AnimaniaAchievements.Duroc) && player.hasAchievement(AnimaniaAchievements.Hampshire) && player.hasAchievement(AnimaniaAchievements.LargeBlack) && player.hasAchievement(AnimaniaAchievements.LargeWhite) && player.hasAchievement(AnimaniaAchievements.OldSpot) && player.hasAchievement(AnimaniaAchievements.Yorkshire))
			player.addStat(AnimaniaAchievements.Pigs, 1);

		if (!player.capabilities.isCreativeMode)
			if (stack != ItemStack.EMPTY && !ItemStack.areItemStacksEqual(stack, this.slop))
				stack.shrink(1);
			else if (stack != ItemStack.EMPTY && ItemStack.areItemStacksEqual(stack, this.slop))
				player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BUCKET));
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte) 18);
	}

	@Override
	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(EntityAnimaniaPig.SADDLED, Boolean.valueOf(false));
		this.dataManager.register(EntityAnimaniaPig.MUDDY, Boolean.valueOf(false));
		this.dataManager.register(EntityAnimaniaPig.MUDTIMER, Float.valueOf(0.0F));
		this.dataManager.register(EntityAnimaniaPig.SPLASHTIMER, Float.valueOf(0.0F));
		this.dataManager.register(EntityAnimaniaPig.FED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaPig.WATERED, Boolean.valueOf(true));
		this.dataManager.register(EntityAnimaniaPig.PLAYED, Boolean.valueOf(true));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setBoolean("Saddle", this.getSaddled());
		compound.setBoolean("Muddy", this.getMuddy());
		compound.setFloat("MudTimer", this.getMudTimer());
		compound.setFloat("SplashTimer", this.getSplashTimer());
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setBoolean("Played", this.getPlayed());

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.setSaddled(compound.getBoolean("Saddle"));
		this.setMuddy(compound.getBoolean("Muddy"));
		this.setMudTimer(compound.getFloat("MudTimer"));
		this.setSplashTimer(compound.getFloat("SplashTimer"));
		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setPlayed(compound.getBoolean("Played"));

	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET)
		{
			if (stack.getCount() == 1 && !player.capabilities.isCreativeMode)
				player.setHeldItem(hand, new ItemStack(Items.BUCKET));
			else if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET)))
				player.dropItem(new ItemStack(Items.BUCKET), false);

			if (this.entityAIEatGrass != null)
			{
				this.entityAIEatGrass.startExecuting();
				this.eatTimer = 40;
			}
			this.setWatered(true);
			this.setInLove(player);
			return true;
		}
		else if (stack != ItemStack.EMPTY && ItemStack.areItemStacksEqual(stack, this.slop))
		{
			if (!player.isCreative())
				player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BUCKET));
			if (this.entityAIEatGrass != null)
			{
				this.entityAIEatGrass.startExecuting();
				this.eatTimer = 40;
			}
			this.setFed(true);
			this.setInLove(player);
			return true;
		}
		else
			return super.processInteract(player, hand);
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source)
	{
		this.dropFewItems(wasRecentlyHit, lootingModifier);
		this.dropEquipment(wasRecentlyHit, lootingModifier);
	}

	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier)
	{
		super.dropEquipment(wasRecentlyHit, lootingModifier);

		if (this.getSaddled())
			this.dropItem(Items.SADDLE, 1);
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		int happyDrops = 0;

		if (this.getPlayed())
			happyDrops++;
		if (this.getWatered())
			happyDrops++;
		if (this.getFed())
			happyDrops++;

		ItemStack dropItem;
		if (AnimaniaConfig.drops.customMobDrops && dropRaw != Items.PORKCHOP && dropCooked != Items.COOKED_PORKCHOP)
		{
			String drop = AnimaniaConfig.drops.pigDrop;
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

		if (happyDrops == 3 && dropItem !=null)
		{
			dropItem.setCount(2 + lootlevel);
			EntityItem entityitem = new EntityItem(this.world, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, dropItem);
			world.spawnEntity(entityitem);
		}
		else if (happyDrops == 2 && dropItem !=null)
		{
			dropItem.setCount(1 + lootlevel);
			EntityItem entityitem = new EntityItem(this.world, this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, dropItem);
			world.spawnEntity(entityitem);
		}
		else if (happyDrops == 1 && dropItem !=null)
		{
			if (this.isBurning())
			{
				this.dropItem(Items.COOKED_PORKCHOP, 1 + lootlevel);
			}
			else
			{
				this.dropItem(Items.PORKCHOP, 1 + lootlevel);
			}
		}
	}

	public boolean getSaddled()
	{
		return this.dataManager.get(EntityAnimaniaPig.SADDLED).booleanValue();
	}

	/**
	 * Set or remove the saddle of the pig.
	 */
	public void setSaddled(boolean saddled)
	{
		if (saddled)
			this.dataManager.set(EntityAnimaniaPig.SADDLED, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityAnimaniaPig.SADDLED, Boolean.valueOf(false));
	}

	public boolean getFed()
	{
		return this.dataManager.get(EntityAnimaniaPig.FED).booleanValue();
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityAnimaniaPig.FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + this.rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		}
		else
			this.dataManager.set(EntityAnimaniaPig.FED, Boolean.valueOf(false));
	}

	public void setSlopFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(EntityAnimaniaPig.FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer * 2 + this.rand.nextInt(100);
		}
		else
			this.dataManager.set(EntityAnimaniaPig.FED, Boolean.valueOf(false));
	}

	public boolean getPlayed()
	{
		return this.dataManager.get(EntityAnimaniaPig.PLAYED).booleanValue();
	}

	public void setPlayed(boolean played)
	{
		if (played)
		{
			this.dataManager.set(EntityAnimaniaPig.PLAYED, Boolean.valueOf(true));
			this.playedTimer = AnimaniaConfig.careAndFeeding.playTimer + this.rand.nextInt(100);
		}
		else
			this.dataManager.set(EntityAnimaniaPig.PLAYED, Boolean.valueOf(false));
	}

	public boolean getWatered()
	{
		return this.dataManager.get(EntityAnimaniaPig.WATERED).booleanValue();
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(EntityAnimaniaPig.WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + this.rand.nextInt(100);
		}
		else
			this.dataManager.set(EntityAnimaniaPig.WATERED, Boolean.valueOf(false));
	}

	public boolean getMuddy()
	{
		return this.dataManager.get(EntityAnimaniaPig.MUDDY).booleanValue();
	}

	public void setMuddy(boolean muddy)
	{
		if (muddy)
			this.dataManager.set(EntityAnimaniaPig.MUDDY, Boolean.valueOf(true));
		else
			this.dataManager.set(EntityAnimaniaPig.MUDDY, Boolean.valueOf(false));
	}

	public Float getMudTimer()
	{
		return this.dataManager.get(EntityAnimaniaPig.MUDTIMER).floatValue();
	}

	public void setMudTimer(Float timer)
	{
		this.dataManager.set(EntityAnimaniaPig.MUDTIMER, Float.valueOf(timer));
	}

	public Float getSplashTimer()
	{
		return this.dataManager.get(EntityAnimaniaPig.SPLASHTIMER).floatValue();
	}

	public void setSplashTimer(Float timer)
	{
		this.dataManager.set(EntityAnimaniaPig.SPLASHTIMER, Float.valueOf(timer));
	}

	@Override
	public void onStruckByLightning(EntityLightningBolt lightningBolt)
	{
		if (!this.world.isRemote && !this.isDead)
		{
			EntityPigZombie entitypigzombie = new EntityPigZombie(this.world);
			entitypigzombie.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
			entitypigzombie.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			entitypigzombie.setNoAI(this.isAIDisabled());

			if (this.isChild())
			{
				entitypigzombie.setChild(true);
			}

			if (this.hasCustomName())
			{
				entitypigzombie.setCustomNameTag(this.getCustomNameTag());
				entitypigzombie.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
			}

			this.world.spawnEntity(entitypigzombie);
			this.setDead();
		}
	}

	@Override
	public void onLivingUpdate()
	{

		if (this.world.isRemote)
			this.eatTimer = Math.max(0, this.eatTimer - 1);

		if (this.blinkTimer > -1)
		{
			this.blinkTimer--;
			if (this.blinkTimer == 0)
			{
				this.blinkTimer = 100 + this.rand.nextInt(100);
			}
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

		if (this.playedTimer > -1)
		{
			this.playedTimer--;

			if (this.playedTimer == 0)
				this.setPlayed(false);
		}

		if (this.getMudTimer() > 0.0)
		{
			this.setPlayed(true);
			this.playedTimer = AnimaniaConfig.careAndFeeding.playTimer + this.rand.nextInt(100);
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();
		boolean played = this.getPlayed();

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

		BlockPos currentpos = new BlockPos(this.posX, this.posY, this.posZ);
		Block poschk = this.world.getBlockState(currentpos).getBlock();

		if (poschk != null && poschk == BlockHandler.blockMud)
			this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, 4, false, false));

		if (this.happyTimer > -1)
		{
			this.happyTimer--;
			if (this.happyTimer == 0)
			{
				this.happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && !this.getPlayed() && AnimaniaConfig.gameRules.showUnhappyParticles)
				{
					double d = this.rand.nextGaussian() * 0.02D;
					double d1 = this.rand.nextGaussian() * 0.02D;
					double d2 = this.rand.nextGaussian() * 0.02D;
					this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX + this.rand.nextFloat() * this.width - this.width, this.posY + 1.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width - this.width, d, d1, d2);
				}
			}
		}

		super.onLivingUpdate();
	}

	@Override
	protected Item getDropItem()
	{
		return null;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return null;
	}
	
	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(this.pigType, this.gender));
	}
	
	@Override
	public ItemStack getPickedResult(RayTraceResult target)
	{
		return new ItemStack(getSpawnEgg());
	}

	@Override
	public int getPrimaryEggColor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSecondaryEggColor()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
