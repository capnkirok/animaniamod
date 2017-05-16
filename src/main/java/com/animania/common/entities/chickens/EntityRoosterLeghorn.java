package com.animania.common.entities.chickens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.chickens.ai.EntityAIFindFood;
import com.animania.common.entities.chickens.ai.EntityAIFindWater;
import com.animania.common.entities.chickens.ai.EntityAIPanicChickens;
import com.animania.common.entities.chickens.ai.EntityAISwimmingChickens;
import com.animania.common.entities.chickens.ai.EntityAIWanderChickens;
import com.animania.common.entities.chickens.ai.EntityAIWatchClosestFromSide;
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ResourceInfo;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityAnimal;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityRoosterLeghorn extends EntityAnimal {
	private static final DataParameter<String> COLOR = EntityDataManager.<String>createKey(EntityRoosterLeghorn.class,
			DataSerializers.STRING);
	private static final DataParameter<Integer> CROWTIMER = EntityDataManager
			.<Integer>createKey(EntityRoosterLeghorn.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> CROWDURATION = EntityDataManager
			.<Integer>createKey(EntityRoosterLeghorn.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityRoosterLeghorn.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WATERED = EntityDataManager
			.<Boolean>createKey(EntityRoosterLeghorn.class, DataSerializers.BOOLEAN);
	private static final Set<Item> TEMPTATION_ITEMS = Sets
			.newHashSet(new Item[] { Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS });

	public boolean chickenJockey;
	private static List ColorList;
	private ResourceLocation resourceLocation;
	private ResourceLocation resourceLocationBlink;
	public float wingRotation;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;
	private int fedTimer;
	private int wateredTimer;
	private int happyTimer;
	public int blinkTimer;
	private int damageTimer;

	public EntityRoosterLeghorn(World world) {
		super(world);
		this.setSize(0.6F, 0.8F);
		this.setTimeUntilNextCrow(this.rand.nextInt(200) + 200);
		this.tasks.addTask(0, new EntityAISwimmingChickens(this));
		this.tasks.addTask(1, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(2, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.2F));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(4, new EntityAIFindFood(this, 1.0D));
		this.tasks.addTask(5, new EntityAIPanicChickens(this, 1.6D));
		this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWanderChickens(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosestFromSide(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityHedgehog.class, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityHedgehogAlbino.class, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityFerretWhite.class, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityFerretGrey.class, false));

		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer / 2 + rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer / 2 + rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 80 + rand.nextInt(80);
		this.enablePersistence();
		String texture = null;
		if (getColor() == "") {
			setColor(getRandomColor());
			texture = getColor();
			resourceLocation = new ResourceLocation(texture);
			resourceLocationBlink = new ResourceLocation(getTextureBlink());
		}
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack) {
		this.setFed(true);

		player.addStat(AnimaniaAchievements.Leghorn, 1);
		if (player.hasAchievement(AnimaniaAchievements.Leghorn) && player.hasAchievement(AnimaniaAchievements.Orpington)
				&& player.hasAchievement(AnimaniaAchievements.PlymouthRock)
				&& player.hasAchievement(AnimaniaAchievements.RhodeIslandRed)
				&& player.hasAchievement(AnimaniaAchievements.Wyandotte)) {
			player.addStat(AnimaniaAchievements.Chickens, 1);
		}
		if (!player.capabilities.isCreativeMode) {
			stack.setCount(stack.getCount() - 1);
		}
	}

	@Override
	public void setInLove(EntityPlayer player) {
		this.world.setEntityState(this, (byte) 18);
	}

	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET) {
			{
				this.setWatered(true);
				this.setInLove(player);
				return true;
			}
		} else {
			return super.processInteract(player, hand);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 1.0F);
		entityIn.attackEntityFrom(DamageSource.GENERIC, 1.0F);

		if (flag) {
			this.applyEnchantments(this, entityIn);
		}

		// Custom Knockback
		if (entityIn instanceof EntityPlayer) {
			((EntityLivingBase) entityIn).knockBack(this, 1, this.posX - entityIn.posX, this.posZ - entityIn.posZ);
		}

		return flag;
	}

	public String getColor() {
		return (this.dataManager.get(COLOR));
	}

	public void setColor(String color) {
		this.dataManager.set(COLOR, String.valueOf(color));
	}

	public ResourceLocation getResourceLocation() {
		return resourceLocation;
	}

	public ResourceLocation getResourceLocationBlink() {
		return resourceLocationBlink;
	}

	private String getRandomColor() {
		return "animania:textures/entity/chickens/rooster_white.png";
	}

	private String getTextureBlink() {
		return "animania:textures/entity/chickens/rooster_white_blink.png";
	}

	private void ColorInitialize() {
		if (ColorList == null) {
			ColorList = new ArrayList();
			try {
				Pattern p = Pattern.compile("assets/animania/textures/(entity/chickens/rooster_.*)");

				for (ResourceInfo i : ClassPath.from(getClass().getClassLoader()).getResources()) {
					Matcher m = p.matcher(i.getResourceName());
					if (m.matches()) {
						String s = m.group(1);
						ColorList.add(s);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(COLOR, "");
		dataManager.register(CROWTIMER, Integer.valueOf(0));
		dataManager.register(CROWDURATION, Integer.valueOf(0));
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setString("Color", getColor());
		nbttagcompound.setBoolean("IsChickenJockey", this.chickenJockey);
		nbttagcompound.setInteger("CrowTime", this.getTimeUntilNextCrow());
		nbttagcompound.setInteger("CrowDuration", this.getCrowDuration());
		nbttagcompound.setBoolean("Fed", this.getFed());
		nbttagcompound.setBoolean("Watered", this.getWatered());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		String s1 = nbttagcompound.getString("Color");

		ColorInitialize();
		if (s1.length() > 0) {

			if (ColorList.contains(s1)) {

				setColor(s1);
				resourceLocation = new ResourceLocation(s1);
			} else {
				// setDead();
			}
		}

		this.setTimeUntilNextCrow(nbttagcompound.getInteger("CrowTime"));
		this.setCrowDuration(nbttagcompound.getInteger("CrowDuration"));
		this.chickenJockey = nbttagcompound.getBoolean("IsChickenJockey");
		this.setFed(nbttagcompound.getBoolean("Fed"));
		this.setWatered(nbttagcompound.getBoolean("Watered"));

	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float) (this.destPos + (this.onGround ? -1 : 4) * 0.3D);
		this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);

		this.fallDistance = 0;

		if (!this.onGround && this.wingRotDelta < 1.0F) {
			this.wingRotDelta = 1.0F;
		}

		this.wingRotDelta = (float) (this.wingRotDelta * 0.9D);

		if (!this.onGround && this.motionY < 0.0D) {
			this.motionY *= 0.6D;
		}

		this.wingRotation += this.wingRotDelta * 2.0F;

		if (this.blinkTimer > -1) {
			this.blinkTimer--;
			if (blinkTimer == 0) {
				this.blinkTimer = 100 + rand.nextInt(100);
			}
		}

		long currentTime = this.world.getWorldTime() % 23999;

		if (this.getTimeUntilNextCrow() > 0) {
			this.setTimeUntilNextCrow(getTimeUntilNextCrow() - 1);
		}

		if ((currentTime > 23250 || currentTime < 500) && this.getTimeUntilNextCrow() == 0) {

			float modular = rand.nextFloat() * rand.nextInt(3);
			boolean direction = rand.nextBoolean();

			if (direction) {
				modular = modular / 10;
			} else {
				modular = modular / 10 * (-1);
			}

			this.setCrowDuration(50);

			int crowChooser = rand.nextInt(3);
			if (crowChooser == 0) {
				this.world.playSound(null, this.posX, this.posY, this.posZ, ModSoundEvents.chickenCrow1,
						SoundCategory.PLAYERS, 0.7F, 0.95F + modular);
			} else if (crowChooser == 1) {
				this.world.playSound(null, this.posX, this.posY, this.posZ, ModSoundEvents.chickenCrow2,
						SoundCategory.PLAYERS, 0.65F, 0.9F + modular);
			} else if (crowChooser == 2) {
				this.world.playSound(null, this.posX, this.posY, this.posZ, ModSoundEvents.chickenCrow3,
						SoundCategory.PLAYERS, 0.6F, 1.05F + modular);
			}
			this.setTimeUntilNextCrow(this.rand.nextInt(200) + 200);

		}

		if (this.getCrowDuration() > 0) {
			this.setCrowDuration(this.getCrowDuration() - 1);
		}

		if (this.fedTimer > -1) {
			this.fedTimer--;

			if (fedTimer == 0) {
				this.setFed(false);
			}
		}

		if (this.wateredTimer > -1) {
			this.wateredTimer--;

			if (wateredTimer == 0) {
				this.setWatered(false);
			}
		}

		boolean fed = this.getFed();
		boolean watered = this.getWatered();

		if (!fed && !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
			if (AnimaniaConfig.gameRules.animalsStarve) {
				if (this.damageTimer >= AnimaniaConfig.careAndFeeding.starvationTimer) {
					this.attackEntityFrom(DamageSource.STARVE, 4f);
					this.damageTimer = 0;
				}
				this.damageTimer++;
			}

		} else if (!fed || !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));
		}

		if (this.happyTimer > -1) {
			this.happyTimer--;
			if (happyTimer == 0) {
				happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && AnimaniaConfig.gameRules.showUnhappyParticles) {
					double d = rand.nextGaussian() * 0.001D;
					double d1 = rand.nextGaussian() * 0.001D;
					double d2 = rand.nextGaussian() * 0.001D;
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (posX + rand.nextFloat() * width) - width,
							posY + 1.5D + rand.nextFloat() * height, (posZ + rand.nextFloat() * width) - width, d, d1,
							d2);
				}
			}
		}

	}

	public boolean getFed() {
		return this.dataManager.get(FED).booleanValue();
	}

	public void setFed(boolean fed) {
		if (fed) {
			this.dataManager.set(FED, Boolean.valueOf(true));
			this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
			this.setHealth(this.getHealth() + 1.0F);
		} else {
			this.dataManager.set(FED, Boolean.valueOf(false));
		}
	}

	public boolean getWatered() {
		return this.dataManager.get(WATERED).booleanValue();
	}

	public void setWatered(boolean watered) {
		if (watered) {
			this.dataManager.set(WATERED, Boolean.valueOf(true));
			this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		} else {
			this.dataManager.set(WATERED, Boolean.valueOf(false));
		}
	}

	public int getCrowDuration() {
		return (this.dataManager.get(CROWDURATION).intValue());
	}

	public void setCrowDuration(int duration) {
		this.dataManager.set(CROWDURATION, Integer.valueOf(duration));
	}

	public int getTimeUntilNextCrow() {
		return (this.dataManager.get(CROWTIMER).intValue());
	}

	public void setTimeUntilNextCrow(int timer) {
		this.dataManager.set(CROWTIMER, Integer.valueOf(timer));
	}

	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	protected void fall(float p_70069_1_) {
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected SoundEvent getAmbientSound() {
		int happy = 0;
		int num = 0;

		if (this.getWatered()) {
			happy++;
		}
		if (this.getFed()) {
			happy++;
		}

		if (happy == 2) {
			num = 6;
		} else if (happy == 1) {
			num = 12;
		} else {
			num = 24;
		}

		Random rand = new Random();
		int chooser = rand.nextInt(num);

		if (chooser == 0) {
			return ModSoundEvents.chickenCluck1;
		} else if (chooser == 1) {
			return ModSoundEvents.chickenCluck2;
		} else if (chooser == 2) {
			return ModSoundEvents.chickenCluck3;
		} else if (chooser == 3) {
			return ModSoundEvents.chickenCluck4;
		} else if (chooser == 4) {
			return ModSoundEvents.chickenCluck5;
		} else {
			return ModSoundEvents.chickenCluck6;
		}
	}

	@Override
	protected SoundEvent getHurtSound() {
		Random rand = new Random();
		int chooser = rand.nextInt(2);

		if (chooser == 0) {
			return ModSoundEvents.chickenHurt1;
		} else {
			return ModSoundEvents.chickenHurt2;
		}
	}

	@Override
	protected SoundEvent getDeathSound() {
		Random rand = new Random();
		int chooser = rand.nextInt(2);

		if (chooser == 0) {
			return ModSoundEvents.chickenDeath1;
		} else {
			return ModSoundEvents.chickenDeath2;
		}
	}

	@Override
	public void playLivingSound() {
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null) {
			this.playSound(soundevent, this.getSoundVolume() - .3F, this.getSoundPitch() - .2F);
		}
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
	}

	@Override
	protected Item getDropItem() {
		return Items.FEATHER;
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel) {

		int happyDrops = 0;

		if (this.getWatered()) {
			happyDrops++;
		}
		if (this.getFed()) {
			happyDrops++;
		}

		int j;
		int k;

		j = happyDrops + lootlevel;

		for (k = 0; k < j; ++k) {
			if (this.isBurning()) {
				this.dropItem(Items.COOKED_CHICKEN, 1);
				this.dropItem(Items.FEATHER, 1);
			} else {
				this.dropItem(Items.CHICKEN, 1);
				this.dropItem(Items.FEATHER, 1);
			}
		}
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack) {
		return stack != ItemStack.EMPTY && TEMPTATION_ITEMS.contains(stack.getItem());
	}

	/**
	 * Get the experience points the entity currently has.
	 */
	@Override
	protected int getExperiencePoints(EntityPlayer player) {
		return this.isChickenJockey() ? 10 : super.getExperiencePoints(player);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void updatePassenger(Entity passenger) {
		super.updatePassenger(passenger);
		float f = MathHelper.sin(this.renderYawOffset * 0.017453292F);
		float f1 = MathHelper.cos(this.renderYawOffset * 0.017453292F);
		float f2 = 0.1F;
		float f3 = 0.0F;
		passenger.setPosition(this.posX + 0.1F * f, this.posY + this.height * 0.5F + passenger.getYOffset() + 0.0D,
				this.posZ - 0.1F * f1);

		if (passenger instanceof EntityLivingBase) {
			((EntityLivingBase) passenger).renderYawOffset = this.renderYawOffset;
		}
	}

	public boolean isChickenJockey() {
		return this.chickenJockey;
	}

	public void setChickenJockey(boolean jockey) {
		this.chickenJockey = jockey;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		// TODO Auto-generated method stub
		return null;
	}
}
