package com.animania.entities.cows;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.animania.Animania;
import com.animania.AnimaniaAchievements;
import com.animania.ModSoundEvents;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;

public class EntityBullAngus extends EntityAnimal
{

	private static final DataParameter<Optional<UUID>> MATE_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityBullAngus.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<Boolean> FIGHTING = EntityDataManager.<Boolean>createKey(EntityBullAngus.class, DataSerializers.BOOLEAN);
	private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(new Item[] {Items.WHEAT});
	private static final DataParameter<Boolean> WATERED = EntityDataManager.<Boolean>createKey(EntityBullAngus.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> FED = EntityDataManager.<Boolean>createKey(EntityBullAngus.class, DataSerializers.BOOLEAN);
	private int happyTimer;
	public int blinkTimer;

	public EntityBullAngus(World world)
	{
		super(world);
		this.setSize(1.6F, 1.8F);
		this.stepHeight = 1.1F;
		this.entityAIEatGrass = new EntityCowEatGrass(this);
		this.tasks.addTask(0, new EntityAIAttackMeleeBulls(this, 2.3D, true));
		this.tasks.addTask(1, new EntityAIFindFood(this, 1.1D));
		this.tasks.addTask(1, new EntityAIFollowMateCows(this, 1.1D));
		this.tasks.addTask(3, new EntityAIFindWater(this, 1.0D));
		this.tasks.addTask(4, new EntityAIWanderCow(this, 1.0D));
		this.tasks.addTask(5, new EntityAISwimmingCows(this));
		this.tasks.addTask(6, new EntityAIMateCows(this, 1.0D));
		this.tasks.addTask(7, new EntityAITempt(this, 1.25D, false, TEMPTATION_ITEMS));
		this.tasks.addTask(6, new EntityAITempt(this, 1.25D, Item.getItemFromBlock(Blocks.YELLOW_FLOWER), false));
		this.tasks.addTask(6, new EntityAITempt(this, 1.25D, Item.getItemFromBlock(Blocks.RED_FLOWER), false));
		this.tasks.addTask(8, this.entityAIEatGrass);
		this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(11, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, EntityPlayer.class));
		this.fedTimer = Animania.feedTimer + rand.nextInt(100);
		this.wateredTimer = Animania.waterTimer + rand.nextInt(100);
		this.happyTimer = 60;
		this.blinkTimer = 100 + rand.nextInt(100);
	}

	public int eatTimer;
	private int fedTimer;
	private int wateredTimer;
	public EntityCowEatGrass entityAIEatGrass;



	public static void registerFixesCow(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityBullAngus.class);
    }

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(MATE_UNIQUE_ID, Optional.<UUID>absent());
		this.dataManager.register(FIGHTING, Boolean.valueOf(false));
		this.dataManager.register(FED, Boolean.valueOf(true));
		this.dataManager.register(WATERED, Boolean.valueOf(true));

	}

	@Override
	protected void consumeItemFromStack(EntityPlayer player, ItemStack stack)
	{
		this.setFed(true);
		this.entityAIEatGrass.startExecuting();
		eatTimer = 80;
		player.addStat(AnimaniaAchievements.Angus, 1);

		if (player.hasAchievement(AnimaniaAchievements.Angus) && player.hasAchievement(AnimaniaAchievements.Friesian) && player.hasAchievement(AnimaniaAchievements.Hereford) && player.hasAchievement(AnimaniaAchievements.Holstein) && player.hasAchievement(AnimaniaAchievements.Longhorn)) {
			player.addStat(AnimaniaAchievements.Cows, 1);
		}

		if (!player.capabilities.isCreativeMode)
		{
			stack.setCount(stack.getCount()-1);
		}
	}

	@Override
	public void setInLove(EntityPlayer player)
	{
		this.world.setEntityState(this, (byte)18);
	}

	@Nullable
	public UUID getMateUniqueId()
	{
		return (UUID)((Optional)this.dataManager.get(MATE_UNIQUE_ID)).orNull();
	}

	public void setMateUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(MATE_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		if (this.getMateUniqueId() != null)
		{
			if (this.getMateUniqueId() != null) {
				compound.setString("MateUUID", this.getMateUniqueId().toString());
			}
			compound.setBoolean("Fighting", this.getFighting());
			compound.setBoolean("Fed", this.getFed());
			compound.setBoolean("Watered", this.getWatered());
		}

	}

	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		String s;

		if (compound.hasKey("MateUUID", 8))
		{
			s = compound.getString("MateUUID");
		}
		else
		{
			String s1 = compound.getString("Mate");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty())
		{
			this.setMateUniqueId(UUID.fromString(s));
		}

		this.setFighting(compound.getBoolean("Fighting"));
		this.setFed(compound.getBoolean("Fed"));
		this.setWatered(compound.getBoolean("Watered"));

	}

	public boolean getFighting()
	{
		return ((Boolean)this.dataManager.get(FIGHTING)).booleanValue();
	}

	public void setFighting(boolean fighting)
	{
		if (fighting)
		{
			this.dataManager.set(FIGHTING, Boolean.valueOf(true));
		}
		else
		{
			this.dataManager.set(FIGHTING, Boolean.valueOf(false));
		}
	}

	public boolean getFed()
	{
		return ((Boolean)this.dataManager.get(FED)).booleanValue();
	}

	public void setFed(boolean fed)
	{
		if (fed)
		{
			this.dataManager.set(FED, Boolean.valueOf(true));
			this.fedTimer = Animania.feedTimer + rand.nextInt(100);
			this.setHealth(this.getHealth()+1.0F);
		}
		else
		{
			this.dataManager.set(FED, Boolean.valueOf(false));
		}
	}

	public boolean getWatered()
	{
		return ((Boolean)this.dataManager.get(WATERED)).booleanValue();
	}

	public void setWatered(boolean watered)
	{
		if (watered)
		{
			this.dataManager.set(WATERED, Boolean.valueOf(true));
			this.wateredTimer = Animania.waterTimer + rand.nextInt(100);
		}
		else
		{
			this.dataManager.set(WATERED, Boolean.valueOf(false));
		}
	}


	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	protected void updateAITasks()
	{
		this.eatTimer = this.entityAIEatGrass.getEatingGrassTimer();
		super.updateAITasks();
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn)
	{
		super.setAttackTarget(entitylivingbaseIn);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isEntityInvulnerable(source))
		{
			return false;
		}
		else
		{
			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn)
	{
		boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 4.0F);
		entityIn.attackEntityFrom(Animania.bullDamage, 4.0F);

		if (flag)
		{
			this.applyEnchantments(this, entityIn);
		}

		//Custom Knockback		
		if (entityIn instanceof EntityPlayer) {
			((EntityLivingBase) entityIn).knockBack(this, 1, this.posX - entityIn.posX, this.posZ - entityIn.posZ);
		}


		return flag;
	}

	protected SoundEvent getAmbientSound()
	{
		int happy = 0;
		int num = 0;

		if (this.getWatered()) {
			happy++;
		} 
		if (this.getFed()) {
			happy++;
		} 

		if (happy == 2) {
			num = 18;
		} else if (happy == 1) {
			num = 36;
		} else {
			num = 60;
		}

		Random rand = new Random();
		int chooser = rand.nextInt(num);
		if (chooser == 0) {
			return ModSoundEvents.bullMoo1;
		} else if (chooser == 1){
			return ModSoundEvents.bullMoo2;
		} else if (chooser == 2){
			return ModSoundEvents.bullMoo3;
		} else if (chooser == 3){
			return ModSoundEvents.bullMoo4;
		} else if (chooser == 4){
			return ModSoundEvents.bullMoo5;
		} else if (chooser == 5){
			return ModSoundEvents.bullMoo6;
		} else if (chooser == 6){
			return ModSoundEvents.bullMoo7;
		} else if (chooser == 7){
			return ModSoundEvents.bullMoo8;
		} else if (chooser == 8){
			return ModSoundEvents.moo4;
		} else if (chooser == 9){
			return ModSoundEvents.moo8;
		} else if (chooser == 10){
			return ModSoundEvents.moo4;
		} else {
			return ModSoundEvents.moo8;
		}
	}

	protected SoundEvent getHurtSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(2);

		if (chooser == 0) {
			return ModSoundEvents.angryBull1;
		} else if (chooser == 1) {
			return ModSoundEvents.angryBull2;
		} else {
			return ModSoundEvents.angryBull3;
		}
	}

	protected SoundEvent getDeathSound()
	{
		Random rand = new Random();
		int chooser = rand.nextInt(2);

		if (chooser == 0) {
			return ModSoundEvents.cowDeath1;
		} else {
			return ModSoundEvents.cowDeath2;
		}
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
		{
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - .2F);
		}
	}
	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	protected Item getDropItem()
	{
		return Items.LEATHER;
	}

	@Override
	protected void dropFewItems(boolean hit, int lootlevel)
	{
		int happyDrops = 0;

		if (this.getWatered()) {
			happyDrops++;
		} 
		if (this.getFed()) {
			happyDrops++;
		} 


		Item dropItem;
		if (Animania.customMobDrops) {
			String drop = Animania.cowDrop;
			dropItem = Item.getByNameOrId(drop);
			if (this.isBurning() && drop.equals("animania:raw_prime_beef")) {
				drop = "animania:cooked_prime_beef";
				dropItem = Item.getByNameOrId(drop);
			}
		} else {
			dropItem = Animania.rawAngusBeef;
			if (this.isBurning()) {
				dropItem = Animania.cookedAngusRoast;
			}
		}

		
		if (happyDrops == 2) {
			this.dropItem(dropItem, 1 + lootlevel);
			this.dropItem(Items.LEATHER, 1);
		} else if (happyDrops == 1) {
			if (this.isBurning())
			{
				this.dropItem(Items.COOKED_BEEF, 1 + lootlevel);
				this.dropItem(Items.LEATHER, 1 + lootlevel);
			}
			else
			{
				this.dropItem(Items.BEEF, 1 + lootlevel);
				this.dropItem(Items.LEATHER, 1 + lootlevel);
			}
		} else if (happyDrops == 0) {
			this.dropItem(Items.LEATHER, 1 + lootlevel);
		}

	}


	public void onLivingUpdate()
	{
		if (this.world.isRemote)
		{
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

		if (!fed || !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 0, false, false));
			//this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, 0, true, true));
		} else if (!fed && !watered) {
			this.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 2, 1, false, false));
			//this.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2, 0, false, false));
		}

		if (this.happyTimer > -1) {
			this.happyTimer--;
			if (happyTimer == 0) {
				happyTimer = 60;

				if (!this.getFed() && !this.getWatered() && Animania.showUnhappyParticles) {
					double d = rand.nextGaussian() * 0.001D;
					double d1 = rand.nextGaussian() * 0.001D;
					double d2 = rand.nextGaussian() * 0.001D;
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (posX + (double)(rand.nextFloat() * width)) - (double)width, posY + 1.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width)) - (double)width, d, d1, d2);
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

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WATER_BUCKET) {
			{
				if (stack.getCount() == 1 && !player.capabilities.isCreativeMode)
				{
					player.setHeldItem(hand, new ItemStack(Items.BUCKET));
				}
				else if (!player.capabilities.isCreativeMode && !player.inventory.addItemStackToInventory(new ItemStack(Items.BUCKET)))
				{
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

	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 10)
		{
			this.eatTimer = 160;
		}
		else
		{
			super.handleStatusUpdate(id);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationPointY(float p_70894_1_)
	{

		if (!this.getFighting()) { 
			return this.eatTimer <= 0 ? 0.0F : (this.eatTimer >= 4 && this.eatTimer <= 156 ? 1.0F : (this.eatTimer < 4 ? ((float)this.eatTimer - p_70894_1_) / 4.0F : -((float)(this.eatTimer - 160) - p_70894_1_) / 4.0F));
		} else {
			return 0.0F;
		}

	}

	@SideOnly(Side.CLIENT)
	public float getHeadRotationAngleX(float p_70890_1_)
	{
		if (this.eatTimer > 4 && this.eatTimer <= 156 && !this.getFighting())
		{
			float f = ((float)(this.eatTimer - 4) - p_70890_1_) / 64.0F;
			return ((float)Math.PI / 5F) + ((float)Math.PI * 7F / 100F) * MathHelper.sin(f * 28.7F);
		}
		else
		{
			return this.eatTimer > 0 ? ((float)Math.PI / 5F) : this.rotationPitch * 0.017453292F;
		}
	}

	public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return stack != ItemStack.EMPTY && this.isCowBreedingItem(stack.getItem());
	}

	private boolean isCowBreedingItem(Item itemIn)
	{
		return itemIn == Items.WHEAT || itemIn == Item.getItemFromBlock(Blocks.YELLOW_FLOWER) || itemIn == Item.getItemFromBlock(Blocks.RED_FLOWER);
	}


	public EntityBullAngus createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}
}