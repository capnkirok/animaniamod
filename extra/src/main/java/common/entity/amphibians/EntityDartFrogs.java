package common.entity.amphibians;

import com.animania.Animania;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.common.items.ItemEntityEgg;
import common.handler.ExtraAddonSoundHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.init.PotionTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class EntityDartFrogs extends EntityAmphibian
{

	private static final EntityDataAccessor<Integer> FROGS_TYPE = SynchedEntityData.<Integer> defineId(EntityDartFrogs.class, EntityDataSerializers.INT);
	public int poisonTimer;
	private int jumpTicks;
	private int jumpDuration;
	private boolean canEntityJump;

	public EntityDartFrogs(Level levelIn)
	{
		super(levelIn, true);
		this.poisonTimer = 2;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.entityData.register(EntityDartFrogs.FROGS_TYPE, Integer.valueOf(this.rand.nextInt(3)));
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(CompoundTag compound)
	{
		super.writeEntityToNBT(compound);
		compound.putInt("FrogsType", this.getFrogsType());
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

	public int getFrogsType()
	{
		return this.entityData.get(EntityDartFrogs.FROGS_TYPE).intValue();
	}

	public void setFrogsType(int frogsId)
	{
		this.entityData.set(EntityDartFrogs.FROGS_TYPE, Integer.valueOf(frogsId));
	}

	@Override
	public boolean processInteract(Player player, InteractionHand hand)
	{

		ItemStack stack = player.getItemInHand(hand);
		Player Player = player;

		if (!stack.isEmpty() && stack.getItem() == Items.ARROW && this.poisonTimer <= 1)
		{
			this.poisonTimer = 800;
			player.playSound(SoundEvents.ENTITY_MAGMACUBE_SQUISH, 0.2F, 1.8F);
			ItemStack bob = new ItemStack(Items.TIPPED_ARROW);
			PotionUtils.addPotionToItemStack(bob, PotionTypes.POISON);
			stack.shrink(1);

			if (stack.getCount() == 0)
			{
				player.setHeldItem(hand, bob);
				return true;
			}
			else if (!player.inventory.addItemStackToInventory(bob))
			{
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
	protected void collideWithEntity(Entity entityIn)
	{

		if (entityIn instanceof Player player && entityIn != this)
		{
			player.addMobEffectInstance(new MobEffectInstance(MobEffects.POISON, 200, 1, false, false));
		}
		entityIn.applyEntityCollision(this);
	}

	@Override
	public void onLivingUpdate()
	{

		if (this.poisonTimer > 1)
			this.poisonTimer--;

		if (this.canEntityJump)
			if (this.jumpTicks != this.jumpDuration)
				++this.jumpTicks;
			else if (this.jumpDuration != 0)
			{
				this.jumpTicks = 0;
				this.jumpDuration = 0;
				this.setJumping(false);
			}

		this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
		this.prevSquishFactor = this.squishFactor;
		super.onLivingUpdate();

		if (this.onGround)
			this.squishAmount = -0.5F;
		else
			this.squishAmount = 0.5F;

		this.alterSquishAmount();

	}

	@Override
	protected ResourceLocation getLootTable()
	{
		return new ResourceLocation("extra/" + Animania.MODID, "dart_frog");
	}

	/**
	 * Called only once on an entity when first time spawned, via egg, mob
	 * spawner, natural spawning etc, but not called when entity is reloaded
	 * from nbt. Mainly used for initializing attributes and inventory
	 */
	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(DifficultyInstance difficulty, @Nullable ILivingEntityData livingdata)
	{
		livingdata = super.onInitialSpawn(difficulty, livingdata);

		this.setFrogsType(this.rand.nextInt(3));

		return livingdata;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{

		int chooser = Animania.RANDOM.nextInt(5);

		switch (chooser)
		{
		case 0:
			return ExtraAddonSoundHandler.dartfrogLiving1;
		case 1:
			return ExtraAddonSoundHandler.dartfrogLiving2;
		case 2:
			return ExtraAddonSoundHandler.dartfrogLiving3;
		case 3:
			return ExtraAddonSoundHandler.dartfrogLiving4;
		default:
			return null;
		}
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
		return ItemEntityEgg.ANIMAL_EGGS.get(new AnimalContainer(AmphibianType.DART_FROG, EntityGender.NONE));
	}

	@Override
	public boolean usesEggColor()
	{
		return false;
	}

	@Override
	public AnimaniaType getAnimalType()
	{
		return AmphibianType.DART_FROG;
	}

}
