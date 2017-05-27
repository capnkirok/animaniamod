package com.animania.common.entities.cows.ai;

import java.util.Random;

import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHereford;
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.common.entities.cows.EntityBullLonghorn;
import com.animania.common.entities.cows.EntityCalfAngus;
import com.animania.common.entities.cows.EntityCalfFriesian;
import com.animania.common.entities.cows.EntityCalfHereford;
import com.animania.common.entities.cows.EntityCalfHolstein;
import com.animania.common.entities.cows.EntityCalfLonghorn;
import com.animania.common.entities.cows.EntityCowAngus;
import com.animania.common.entities.cows.EntityCowFriesian;
import com.animania.common.entities.cows.EntityCowHereford;
import com.animania.common.entities.cows.EntityCowHolstein;
import com.animania.common.entities.cows.EntityCowLonghorn;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityTrough;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fluids.FluidRegistry;

public class EntityAIFindWater extends EntityAIBase
{
	private final EntityCreature temptedEntity;
	private final double         speed;
	private double               targetX;
	private double               targetY;
	private double               targetZ;
	private double               pitch;
	private double               yaw;
	private EntityPlayer         temptingPlayer;
	private int                  delayTemptCounter;
	private boolean              isRunning;

	public EntityAIFindWater(EntityCreature temptedEntityIn, double speedIn) {
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute() {

		delayTemptCounter++;
		if (this.delayTemptCounter < 40) {
			return false;
		} else if (delayTemptCounter > 40) {
			if (temptedEntity instanceof EntityCowHolstein) {
				EntityCowHolstein ech = (EntityCowHolstein)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCowFriesian) {
				EntityCowFriesian ech = (EntityCowFriesian)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCowHereford) {
				EntityCowHereford ech = (EntityCowHereford)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCowLonghorn) {
				EntityCowLonghorn ech = (EntityCowLonghorn)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCowAngus) {
				EntityCowAngus ech = (EntityCowAngus)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityBullHolstein) {
				EntityBullHolstein ech = (EntityBullHolstein)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityBullFriesian) {
				EntityBullFriesian ech = (EntityBullFriesian)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityBullHereford) {
				EntityBullHereford ech = (EntityBullHereford)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityBullLonghorn) {
				EntityBullLonghorn ech = (EntityBullLonghorn)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityBullAngus) {
				EntityBullAngus ech = (EntityBullAngus)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfHolstein) {
				EntityCalfHolstein ech = (EntityCalfHolstein)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfFriesian) {
				EntityCalfFriesian ech = (EntityCalfFriesian)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfHereford) {
				EntityCalfHereford ech = (EntityCalfHereford)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfLonghorn) {
				EntityCalfLonghorn ech = (EntityCalfLonghorn)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfAngus) {
				EntityCalfAngus ech = (EntityCalfAngus)temptedEntity;
				if (ech.getWatered()) {
					this.delayTemptCounter = 0;
					return false;
				}
			}
			Random rand = new Random();

			Biome biomegenbase = this.temptedEntity.world.getBiome(new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ));

			BlockPos currentpos = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ);
			BlockPos currentposlower = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY - 1, this.temptedEntity.posZ);
			Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();
			Block poschk2 = this.temptedEntity.world.getBlockState(currentposlower).getBlock();

			if (poschk == BlockHandler.blockTrough) {
				TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(currentpos);
				if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER) {
					te.fluidHandler.drain(335, true);

					if (this.temptedEntity instanceof EntityCowHolstein) {
						EntityCowHolstein ech = (EntityCowHolstein) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityCowFriesian) {
						EntityCowFriesian ech = (EntityCowFriesian) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityCowHereford) {
						EntityCowHereford ech = (EntityCowHereford) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityCowLonghorn) {
						EntityCowLonghorn ech = (EntityCowLonghorn) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityCowAngus) {
						EntityCowAngus ech = (EntityCowAngus) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityBullHolstein) {
						EntityBullHolstein ech = (EntityBullHolstein) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityBullFriesian) {
						EntityBullFriesian ech = (EntityBullFriesian) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityBullHereford) {
						EntityBullHereford ech = (EntityBullHereford) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
					}
					else if (this.temptedEntity instanceof EntityBullLonghorn) {
						EntityBullLonghorn ech = (EntityBullLonghorn) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityBullAngus) {
						EntityBullAngus ech = (EntityBullAngus) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityCalfHolstein) {
						EntityCalfHolstein ech = (EntityCalfHolstein) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityCalfFriesian) {
						EntityCalfFriesian ech = (EntityCalfFriesian) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityCalfHereford) {
						EntityCalfHereford ech = (EntityCalfHereford) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityCalfLonghorn) {
						EntityCalfLonghorn ech = (EntityCalfLonghorn) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					else if (this.temptedEntity instanceof EntityCalfAngus) {
						EntityCalfAngus ech = (EntityCalfAngus) this.temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}

					return false;

				}
			}
			else if (poschk2 == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN)
					&& !BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {

				if (this.temptedEntity instanceof EntityCowHolstein) {
					EntityCowHolstein ech = (EntityCowHolstein) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityCowFriesian) {
					EntityCowFriesian ech = (EntityCowFriesian) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityCowHereford) {
					EntityCowHereford ech = (EntityCowHereford) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityCowLonghorn) {
					EntityCowLonghorn ech = (EntityCowLonghorn) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityCowAngus) {
					EntityCowAngus ech = (EntityCowAngus) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityBullHolstein) {
					EntityBullHolstein ech = (EntityBullHolstein) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityBullFriesian) {
					EntityBullFriesian ech = (EntityBullFriesian) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityBullHereford) {
					EntityBullHereford ech = (EntityBullHereford) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityBullLonghorn) {
					EntityBullLonghorn ech = (EntityBullLonghorn) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityBullAngus) {
					EntityBullAngus ech = (EntityBullAngus) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityCalfHolstein) {
					EntityCalfHolstein ech = (EntityCalfHolstein) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityCalfFriesian) {
					EntityCalfFriesian ech = (EntityCalfFriesian) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityCalfHereford) {
					EntityCalfHereford ech = (EntityCalfHereford) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityCalfLonghorn) {
					EntityCalfLonghorn ech = (EntityCalfLonghorn) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}
				else if (this.temptedEntity instanceof EntityCalfAngus) {
					EntityCalfAngus ech = (EntityCalfAngus) this.temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}


				this.temptedEntity.world.setBlockToAir(currentposlower);

				return false;
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean waterFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++)
				for (int j = -3; j < 3; j++)
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == Blocks.WATER) {
							waterFound = true;
							if (rand.nextInt(20) == 0) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							}
							else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0
									&& this.temptedEntity.motionZ == 0) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							}
							else
								return true;
						}
						else if (blockchk == BlockHandler.blockTrough) {
							TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(pos);
							if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER) {
								waterFound = true;
								if (rand.nextInt(20) == 0) {
									this.delayTemptCounter = 0;
									this.resetTask();
									return false;
								}
								else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0
										&& this.temptedEntity.motionZ == 0) {
									this.delayTemptCounter = 0;
									this.resetTask();
									return false;
								}
								else
									return true;
							}
						}

					}

			if (!waterFound)
				this.delayTemptCounter = 0;
			return false;
		}

		return false;
	}

	@Override
	public boolean continueExecuting() {

		return this.shouldExecute();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting() {
		this.isRunning = true;
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask() {
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPathEntity();
		this.isRunning = false;
	}

	@Override
	public void updateTask() {

		double x = this.temptedEntity.posX;
		double y = this.temptedEntity.posY;
		double z = this.temptedEntity.posZ;

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();

		if (poschk != Blocks.WATER) {

			boolean waterFound = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos waterPos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++)
				for (int j = -3; j < 3; j++)
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

						Biome biomegenbase = this.temptedEntity.world.getBiome(pos);

						if (blockchk == BlockHandler.blockTrough) {
							TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(pos);
							if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER) {
								waterFound = true;
								newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

								if (newloc < loc) {

									loc = newloc;

									if (this.temptedEntity.posX < waterPos.getX()) {
										BlockPos waterPoschk = new BlockPos(x + i + 1, y + j, z + k);
										Block waterBlockchk = this.temptedEntity.world.getBlockState(waterPoschk).getBlock();
										if (waterBlockchk == BlockHandler.blockTrough)
											i = i + 1;
									}

									if (this.temptedEntity.posZ < waterPos.getZ()) {
										BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
										Block waterBlockchk = this.temptedEntity.world.getBlockState(waterPoschk).getBlock();
										if (waterBlockchk == BlockHandler.blockTrough)
											k = k + 1;
									}

									waterPos = new BlockPos(x + i, y + j, z + k);

								}

							}
						}

						if (blockchk == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN)
								&& !BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
							waterFound = true;
							newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (this.temptedEntity.posX < waterPos.getX()) {
									BlockPos waterPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block waterBlockchk = this.temptedEntity.world.getBlockState(waterPoschk).getBlock();
									if (waterBlockchk == Blocks.WATER)
										i = i + 1;
								}

								if (this.temptedEntity.posZ < waterPos.getZ()) {
									BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block waterBlockchk = this.temptedEntity.world.getBlockState(waterPoschk).getBlock();
									if (waterBlockchk == Blocks.WATER)
										k = k + 1;
								}

								waterPos = new BlockPos(x + i, y + j, z + k);

							}

						}

					}

			if (waterFound) {

				Block waterBlockchk = this.temptedEntity.world.getBlockState(waterPos).getBlock();
				Biome biomegenbase = this.temptedEntity.world.getBiome(waterPos);

				if (waterBlockchk == BlockHandler.blockTrough) {
					if (this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false)
						this.resetTask();
					else
						this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);

				}
				else if (waterBlockchk == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN)
						&& !BiomeDictionary.hasType(biomegenbase, Type.BEACH))
					if (this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false)
						this.resetTask();
					else
						this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);
			}

		}

	}

	public boolean isRunning() {
		return this.isRunning;
	}
}