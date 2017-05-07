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
import com.animania.common.entities.chickens.ai.EntityAISwimmingChicks;
import com.animania.common.entities.chickens.ai.EntityAIWanderChickens;
import com.animania.common.entities.chickens.ai.EntityAIWatchClosestFromSide;
import com.animania.config.AnimaniaConfig;
import com.google.common.collect.Sets;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ResourceInfo;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityChickWyandotte extends EntityAnimal {
	private static final DataParameter<String> COLOR = EntityDataManager.<String>createKey(EntityChickWyandotte.class,
			DataSerializers.STRING);
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityChickWyandotte.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> WATERED = EntityDataManager
			.<Boolean>createKey(EntityChickWyandotte.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Float> AGE = EntityDataManager.<Float>createKey(EntityChickWyandotte.class,
			DataSerializers.FLOAT);
	private static final Set<Item> TEMPTATION_ITEMS = Sets
			.newHashSet(new Item[] { Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS });
	public int timeUntilNextEgg;
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
	private int ageTimer;
	private int happyTimer;
	public int blinkTimer;
	private int damageTimer;

	public EntityChickWyandotte(World world) {
		super(world);
		this.setSize(0.3F, 0.4F);
		this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
		this.tasks.addTask(0, new EntityAISwimmingChicks(this));
		this.tasks.addTask(1, new EntityAIPanicChickens(this, 1.4D));
		this.tasks.addTask(2, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(4, new EntityAITempt(this, 1.2D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(3, new EntityAIFindFood(this, 1.0D));
		this.tasks.addTask(5, new EntityAIFollowParent(this, 1.1D));
		this.tasks.addTask(6, new EntityAIWanderChickens(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWatchClosestFromSide(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.fedTimer = AnimaniaConfig.entity.feedTimer / 3 + rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.entity.waterTimer / 3 + rand.nextInt(100);
		this.happyTimer = 60;
		this.ageTimer = 0;
		this.blinkTimer = 80 + rand.nextInt(80);
		String texture = null;
		if (getColor() == "") {
			setColor(getRandomColor());
			texture = getColor();
			resourceLocation = new ResourceLocation(texture);
			resourceLocationBlink = new ResourceLocation(getTextureBlink());
		}
	}

	@Override
	public boolean isChild() {
		return true;
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack) {
		this.setFed(true);

		player.addStat(AnimaniaAchievements.Wyandotte, 1);
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

	public boolean isAIEnabled() {
		return true;
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
		return "animania:textures/entity/chickens/chick_brown.png";
	}

	private String getTextureBlink() {
		return "animania:textures/entity/chickens/chick_brown_blink.png";
	}

	private void ColorInitialize() {
		if (ColorList == null) {
			ColorList = new ArrayList();
			try {
				Pattern p = Pattern.compile("assets/animania/textures/(entity/chickens/chick_.*)");

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
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.29D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(COLOR, "");
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));
		this.dataManager.register(AGE, Float.valueOf(0));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setString("Color", getColor());
		nbttagcompound.setBoolean("IsChickenJockey", this.chickenJockey);
		nbttagcompound.setInteger("EggLayTime", this.timeUntilNextEgg);
		nbttagcompound.setBoolean("Fed", this.getFed());
		nbttagcompound.setBoolean("Watered", this.getWatered());
		nbttagcompound.setFloat("Age", this.getEntityAge());
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

		this.chickenJockey = nbttagcompound.getBoolean("IsChickenJockey");

		if (nbttagcompound.hasKey("EggLayTime")) {
			this.timeUntilNextEgg = nbttagcompound.getInteger("EggLayTime");
		}

		this.setFed(nbttagcompound.getBoolean("Fed"));
		this.setWatered(nbttagcompound.getBoolean("Watered"));
		this.setEntityAge(nbttagcompound.getFloat("Age"));
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

		if (this.fedTimer > -1) {
			this.fedTimer--;

			if (fedTimer == 0) {
				this.setFed(false);
			}
		}

		if (this.blinkTimer > -1) {
			this.blinkTimer--;
			if (blinkTimer == 0) {
				this.blinkTimer = 100 + rand.nextInt(100);
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
				if (this.damageTimer >= AnimaniaConfig.entity.starvationTimer) {
					this.attackEntityFrom(DamageSource.STARVE, 4f);
					this.damageTimer = 0;
				}
				this.damageTimer++;
			}

		} else if (!fed || !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));
		}

		ageTimer++;
		if (ageTimer >= AnimaniaConfig.entity.childGrowthTick) {

			if (fed && watered) {
				ageTimer = 0;
				float age = this.getEntityAge();
				age = age + .01F;
				this.setEntityAge(age);

				if (age >= .4 && !this.world.isRemote) {
					this.setDead();

					if (rand.nextInt(2) < 1) {
						EntityHenWyandotte entityHen = new EntityHenWyandotte(this.world);
						entityHen.setPosition(this.posX, this.posY + .5, this.posZ);
						String name = this.getCustomNameTag();
						if (name != "") {
							entityHen.setCustomNameTag(name);
						}
						this.world.spawnEntity(entityHen);
						this.playSound(ModSoundEvents.chickenHurt1, 0.50F, 1.1F);
					} else {
						EntityRoosterWyandotte entityRooster = new EntityRoosterWyandotte(this.world);
						entityRooster.setPosition(this.posX, this.posY + .5, this.posZ);
						String name = this.getCustomNameTag();
						if (name != "") {
							entityRooster.setCustomNameTag(name);
						}
						this.world.spawnEntity(entityRooster);
						this.playSound(ModSoundEvents.chickenCrow1, 0.50F, 1.1F);
					}

				}
			}

		}

		if (this.happyTimer > -1) {
			this.happyTimer--;
			if (happyTimer == 0) {
				happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && AnimaniaConfig.gameRules.showUnhappyParticles) {
					double d = rand.nextGaussian() * 0.001D;
					double d1 = rand.nextGaussian() * 0.001D;
					double d2 = rand.nextGaussian() * 0.001D;
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
							(posX + rand.nextFloat() * width) - width,
							posY + 1.5D + rand.nextFloat() * height,
							(posZ + rand.nextFloat() * width) - width, d, d1, d2);
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
			this.fedTimer = AnimaniaConfig.entity.feedTimer + rand.nextInt(100);
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
			this.wateredTimer = AnimaniaConfig.entity.waterTimer + rand.nextInt(100);
		} else {
			this.dataManager.set(WATERED, Boolean.valueOf(false));
		}
	}

	public float getEntityAge() {
		return this.dataManager.get(AGE).floatValue();
	}

	public void setEntityAge(float age) {
		this.dataManager.set(AGE, Float.valueOf(age));
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
		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Override
	public void playLivingSound() {
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null) {
			this.playSound(soundevent, this.getSoundVolume() - .3F,
					this.getSoundPitch() + .9F - (this.getEntityAge() * 2));
		}
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.10F, 1.4F);
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
		passenger.setPosition(this.posX + 0.1F * f,
				this.posY + this.height * 0.5F + passenger.getYOffset() + 0.0D,
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
