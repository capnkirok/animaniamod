package com.animania.common.entities.amphibians;

import java.util.Random;

import javax.annotation.Nullable;

import com.animania.common.ModSoundEvents;

import net.minecraft.block.Block;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityTreeFrogs extends EntityAmphibian {

	private static final DataParameter<Integer> FROGS_TYPE = EntityDataManager.<Integer>createKey(EntityTreeFrogs.class, DataSerializers.VARINT);


	public EntityTreeFrogs(World worldIn) {
		super(worldIn, true);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(FROGS_TYPE, Integer.valueOf(0));
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
		return this.dataManager.get(FROGS_TYPE).intValue();
	}

	public void setFrogsType(int frogsId) {
		this.dataManager.set(FROGS_TYPE, Integer.valueOf(frogsId));
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

	protected SoundEvent getAmbientSound()
	{

		Random rand = new Random();
		int chooser = rand.nextInt(5);

		if (chooser == 0) {
			return ModSoundEvents.treefrogLiving1;
		} else if (chooser == 1){
			return ModSoundEvents.treefrogLiving2;
		} else if (chooser == 2){
			return ModSoundEvents.treefrogLiving3;
		} else if (chooser == 3){
			return ModSoundEvents.treefrogLiving4;
		} else {
			return null;
		}
	}

	protected SoundEvent getHurtSound()
	{
		return null;
	}

	protected SoundEvent getDeathSound()
	{
		return null;
	}

	@Override
	public void playLivingSound()
	{
		SoundEvent soundevent = this.getAmbientSound();

		if (soundevent != null)
		{
			this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch() - (this.getAge() * 2));
		}
	}

	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.04F, 1.1F);
	}

	protected float getSoundVolume()
	{
		return 0.4F;
	}

}
