package com.animania.common.entities.amphibians;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.EntityGender;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemEntityEgg;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityDartFrogs extends EntityAmphibian
{

	private static final DataParameter<Integer> FROGS_TYPE = EntityDataManager.<Integer> createKey(EntityDartFrogs.class, DataSerializers.VARINT);
	public int                                  poisonTimer;
	private int                                 jumpTicks;
	private int                                 jumpDuration;
	private boolean                             canEntityJump;

	public EntityDartFrogs(World worldIn) {
		super(worldIn, true);
		this.poisonTimer = 2;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(EntityDartFrogs.FROGS_TYPE, Integer.valueOf(this.rand.nextInt(3)));
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("FrogsType", this.getFrogsType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		this.setFrogsType(compound.getInteger("FrogsType"));
	}

	public int getFrogsType() {
		return this.dataManager.get(EntityDartFrogs.FROGS_TYPE).intValue();
	}

	public void setFrogsType(int frogsId) {
		this.dataManager.set(EntityDartFrogs.FROGS_TYPE, Integer.valueOf(frogsId));
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {

		ItemStack stack = player.getHeldItem(hand);
		EntityPlayer entityplayer = player;

		if (!stack.isEmpty() && stack.getItem() == Items.ARROW && this.poisonTimer <= 1) {
			this.poisonTimer = 800;
			player.playSound(SoundEvents.ENTITY_MAGMACUBE_SQUISH, 0.2F, 1.8F);
			ItemStack bob = new ItemStack(Items.TIPPED_ARROW);
			PotionUtils.addPotionToItemStack(bob, PotionTypes.POISON);
			stack.shrink(1);

			if (stack.getCount() == 0) {
				player.setHeldItem(hand, bob);
				return true;
			}
			else if (!player.inventory.addItemStackToInventory(bob)) {
				player.dropItem(bob, false);
				return true;
			}
			else
				return super.processInteract(player, hand);
		}
		else
			return super.processInteract(player, hand);

	}

	@Override
	protected void collideWithEntity(Entity entityIn) {

		if (entityIn instanceof EntityPlayer && entityIn != this) {
			EntityPlayer player = (EntityPlayer) entityIn;
			player.addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 1, false, false));
		}
		entityIn.applyEntityCollision(this);
	}

	@Override
	public void onLivingUpdate() {

		if (this.poisonTimer > 1)
			this.poisonTimer--;

		if (this.canEntityJump)
			if (this.jumpTicks != this.jumpDuration)
				++this.jumpTicks;
			else if (this.jumpDuration != 0) {
				this.jumpTicks = 0;
				this.jumpDuration = 0;
				this.setJumping(false);
			}

		this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
		this.prevSquishFactor = this.squishFactor;
		super.onLivingUpdate();

		if (this.onGround)
			this.squishAmount = -0.5F;
		else if (!this.onGround)
			this.squishAmount = 0.5F;

		this.alterSquishAmount();

	}
	
	@Override
	protected ResourceLocation getLootTable()
	{
		return new ResourceLocation(Animania.MODID, "dart_frog");
	}

	/**
	 * Called only once on an entity when first time spawned, via egg, mob
	 * spawner, natural spawning etc, but not called when entity is reloaded
	 * from nbt. Mainly used for initializing attributes and inventory
	 */
	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		livingdata = super.onInitialSpawn(difficulty, livingdata);

		this.setFrogsType(this.rand.nextInt(3));

		return livingdata;
	}

	@Override
	protected SoundEvent getAmbientSound() {

		Random rand = new Random();
		int chooser = rand.nextInt(5);

		if (chooser == 0)
			return ModSoundEvents.dartfrogLiving1;
		else if (chooser == 1)
			return ModSoundEvents.dartfrogLiving2;
		else if (chooser == 2)
			return ModSoundEvents.dartfrogLiving3;
		else if (chooser == 3)
			return ModSoundEvents.dartfrogLiving4;
		else
			return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Override
	public void playLivingSound() {
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - this.getGrowingAge() * 2);
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.04F, 1.1F);
	}

	@Override
	protected float getSoundVolume() {
		return 0.4F;
	}
	
	@Override
	public Item getSpawnEgg()
	{
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(AmphibianType.DART_FROG, EntityGender.NONE));
	}
	
	@Override
	public boolean usesEggColor()
	{
		return false;
	}

}
