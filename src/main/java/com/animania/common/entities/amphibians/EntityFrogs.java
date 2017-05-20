package com.animania.common.entities.amphibians;

import javax.annotation.Nullable;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityFrogs extends EntityAmphibian {

	private static final DataParameter<Integer> FROGS_TYPE = EntityDataManager.<Integer>createKey(EntityFrogs.class, DataSerializers.VARINT);
	

	public EntityFrogs(World worldIn) {
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

		this.setFrogsType(this.rand.nextInt(5));

		return livingdata;
	}

}
