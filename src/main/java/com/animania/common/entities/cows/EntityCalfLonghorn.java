package com.animania.common.entities.cows;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.animania.common.AnimaniaAchievements;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.cows.ai.EntityAIFindFood;
import com.animania.common.entities.cows.ai.EntityAIFindWater;
import com.animania.common.entities.cows.ai.EntityAIFollowParentCows;
import com.animania.common.entities.cows.ai.EntityAIPanicCows;
import com.animania.common.entities.cows.ai.EntityAISwimmingCows;
import com.animania.common.entities.cows.ai.EntityAIWanderCow;
import com.animania.common.entities.cows.ai.EntityAICowEatGrass;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityCalfLonghorn extends EntityAnimal {
	private static final DataParameter<Optional<UUID>> PARENT_UNIQUE_ID = EntityDataManager
			.<Optional<UUID>>createKey(EntityCalfLonghorn.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityCalfLonghorn.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityCalfLonghorn.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Float> AGE = EntityDataManager.<Float>createKey(EntityCalfLonghorn.class,
			DataSerializers.FLOAT);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] { Items.WHEAT });
	private int happyTimer;
	private int ageTimer;
	public int blinkTimer;

	public EntityCalfLonghorn(World world) {
		super(world);
		this.setSize(0.7F, 1.1F);
		this.stepHeight = 1.1F;
		this.tasks.addTask(1, new EntityAIFollowParentCows(this, 1.1D));
		this.tasks.addTask(1, new EntityAIFindFood(this, 1.2D));
		this.entityAIEatGrass = new EntityAICowEatGrass(this);
		this.tasks.addTask(2, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWanderCow(this, 1.0D));
		this.tasks.addTask(1, new EntityAISwimmingCows(this));
		this.tasks.addTask(2, new EntityAIPanicCows(this, 2.2D));
		this.tasks.addTask(4, new EntityAITempt(this, 1.25D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(6, new EntityAITempt(this, 1.25D, Item.getItemFromBlock(Blocks.YELLOW_FLOWER), false));
		this.tasks.addTask(6, new EntityAITempt(this, 1.25D, Item.getItemFromBlock(Blocks.RED_FLOWER), false));
		this.tasks.addTask(0, this.entityAIEatGrass);
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.fedTimer = AnimaniaConfig.careAndFeeding.feedTimer + rand.nextInt(100);
		this.wateredTimer = AnimaniaConfig.careAndFeeding.waterTimer + rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 100 + rand.nextInt(100);
		this.enablePersistence();
		this.ageTimer = 0;
	}

	@Override
	public boolean isChild() {
		return true;
	}

	public static void registerFixesCow(DataFixer fixer) {
		EntityLiving.registerFixesMob(fixer, EntityCalfLonghorn.class);
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	public int eatTimer;
	private int fedTimer;
	private int wateredTimer;
	public EntityAICowEatGrass entityAIEatGrass;
	private int damageTimer;

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));
		this.dataManager.register(AGE, Float.valueOf(0));
		this.dataManager.register(PARENT_UNIQUE_ID, Optional.<UUID>absent());
	}

	@Override
	protected void applyEntityAttributes() {

		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.26000000298023224D);
	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack) {
		this.setFed(true);
		this.entityAIEatGrass.startExecuting();
		eatTimer = 80;
		player.addStat(AnimaniaAchievements.Longhorn, 1);
		if (player.hasAchievement(AnimaniaAchievements.Longhorn) && player.hasAchievement(AnimaniaAchievements.Friesian)
				&& player.hasAchievement(AnimaniaAchievements.Hereford)
				&& player.hasAchievement(AnimaniaAchievements.Holstein)
				&& player.hasAchievement(AnimaniaAchievements.Longhorn)) {
			player.addStat(AnimaniaAchievements.Cows, 1);
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
	protected void updateAITasks() {
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("Fed", this.getFed());
		compound.setBoolean("Watered", this.getWatered());
		compound.setFloat("Age", this.getEntityAge());
		if (this.getParentUniqueId() != null) {
			if (this.getParentUniqueId() != null) {
				compound.setString("ParentUUID", this.getParentUniqueId().toString());
			}
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));
		this.setEntityAge(compound.getFloat("Age"));
		String s;

		if (compound.hasKey("ParentUUID", 8)) {
			s = compound.getString("ParentUUID");
		} else {
			String s1 = compound.getString("Parent");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty()) {
			this.setParentUniqueId(UUID.fromString(s));
		}
	}

	@Nullable
	public UUID getParentUniqueId() {
		return (UUID) ((Optional) this.dataManager.get(PARENT_UNIQUE_ID)).orNull();
	}

	public void setParentUniqueId(@Nullable UUID uniqueId) {
		this.dataManager.set(PARENT_UNIQUE_ID, Optional.fromNullable(uniqueId));
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

	public float getEntityAge() {
		return this.dataManager.get(AGE).floatValue();
	}

	public void setEntityAge(float age) {
		this.dataManager.set(AGE, Float.valueOf(age));
	}

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
			return ModSoundEvents.mooCalf1;
		} else if (chooser == 1) {
			return ModSoundEvents.mooCalf2;
		} else if (chooser == 2) {
			return ModSoundEvents.mooCalf3;
		} else {
			return null;
		}
	}

	@Override
	protected SoundEvent getHurtSound() {
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0) {
			return ModSoundEvents.mooCalf1;
		} else if (chooser == 1) {
			return ModSoundEvents.mooCalf2;
		} else {
			return ModSoundEvents.mooCalf3;
		}
	}

	@Override
	protected SoundEvent getDeathSound() {
		Random rand = new Random();
		int chooser = rand.nextInt(3);

		if (chooser == 0) {
			return ModSoundEvents.mooCalf1;
		} else if (chooser == 1) {
			return ModSoundEvents.mooCalf2;
		} else {
			return ModSoundEvents.mooCalf3;
		}
	}

	@Override
	public void playLivingSound() {
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null) {
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() + .2F - (this.getEntityAge() * 2));
		}
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.5F, 1.2F);
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}

	@Override
	protected Item getDropItem() {
		return null;
	}

	@Override
	public void onLivingUpdate() {
		if (this.world.isRemote) {
			this.eatTimer = Math.max(0, this.eatTimer - 1);
		}

		if (this.blinkTimer > -1) {
			this.blinkTimer--;
			if (blinkTimer == 0) {
				this.blinkTimer = 100 + rand.nextInt(100);
			}
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

		ageTimer++;
		if (ageTimer >= AnimaniaConfig.careAndFeeding.childGrowthTick) {

			if (fed && watered) {
				ageTimer = 0;
				float age = this.getEntityAge();
				age = age + .01F;
				this.setEntityAge(age);

				if (age >= 1.0 && !this.world.isRemote) {
					this.setDead();

					if (rand.nextInt(2) < 1) {
						EntityCowLonghorn entityCow = new EntityCowLonghorn(this.world);
						entityCow.setPosition(this.posX, this.posY + .5, this.posZ);
						String name = this.getCustomNameTag();
						if (name != "") {
							entityCow.setCustomNameTag(name);
						}
						this.world.spawnEntity(entityCow);
						this.playSound(ModSoundEvents.moo1, 0.50F, 1.1F);
					} else {
						EntityBullLonghorn entityBull = new EntityBullLonghorn(this.world);
						entityBull.setPosition(this.posX, this.posY + .5, this.posZ);
						String name = this.getCustomNameTag();
						if (name != "") {
							entityBull.setCustomNameTag(name);
						}
						this.world.spawnEntity(entityBull);
						this.playSound(ModSoundEvents.bullMoo1, 0.50F, 1.1F);
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
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (posX + rand.nextFloat() * width) - width,
							posY + 1.5D + rand.nextFloat() * height, (posZ + rand.nextFloat() * width) - width, d, d1,
							d2);
				}
			}
		}

		super.onLivingUpdate();
	}

	@Override
	protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
		return;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET) {
			{
				if (stack.getCount() == 1 && !player.capabilities.isCreativeMode) {
					player.setHeldItem(hand, new ItemStack(Items.BUCKET));
				} else if (!player.capabilities.isCreativeMode
						&& !player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET))) {
					player.dropItem(new ItemStack(Items.BUCKET), false);
				}
				eatTimer = 40;
				this.entityAIEatGrass.startExecuting();
				this.setWatered(true);
				this.setInLove(player);
				return true;
			}
		} else {
			return super.processInteract(player, hand);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 10) {
			this.eatTimer = 160;
		} else {
			super.handleStatusUpdate(id);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_) {
		return this.eatTimer <= 0 ? 0.0F
				: (this.eatTimer >= 4 && this.eatTimer <= 156 ? 1.0F
						: (this.eatTimer < 4 ? (this.eatTimer - p_70894_1_) / 4.0F
								: -(this.eatTimer - 160 - p_70894_1_) / 4.0F));
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_) {
		if (this.eatTimer > 4 && this.eatTimer <= 156) {
			float f = (this.eatTimer - 4 - p_70890_1_) / 64.0F;
			return ((float) Math.PI / 5F) + ((float) Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
		} else {
			return this.eatTimer > 0 ? ((float) Math.PI / 5F) : this.rotationPitch * 0.017453292F;
		}
	}

	@Override
	public boolean isBreedingItem(@Nullable ItemStack stack) {
		return stack != ItemStack.EMPTY && this.isCowBreedingItem(stack.getItem());
	}

	private boolean isCowBreedingItem(Item itemIn) {
		return itemIn == Items.WHEAT || itemIn == Item.getItemFromBlock(Blocks.YELLOW_FLOWER)
				|| itemIn == Item.getItemFromBlock(Blocks.RED_FLOWER);
	}

	@Override
	public EntityCalfLonghorn createChild(EntityAgeable p_90011_1_) {
		return null;
	}
}