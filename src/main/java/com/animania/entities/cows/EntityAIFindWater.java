package com.animania.entities.cows;

import java.util.Random;

import com.animania.Animania;
import com.animania.tileentities.TileEntityTrough;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class EntityAIFindWater extends EntityAIBase 
{
	private final EntityCreature temptedEntity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private int delayTemptCounter;
	private boolean isRunning;



	public EntityAIFindWater(EntityCreature temptedEntityIn, double speedIn)
	{
		this.temptedEntity = temptedEntityIn;
		this.speed = speedIn;
		this.setMutexBits(3);
		this.delayTemptCounter = 0;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute()
	{

		delayTemptCounter++;
		if (delayTemptCounter > 20) {
			if (temptedEntity instanceof EntityCowHolstein) {
				EntityCowHolstein ech = (EntityCowHolstein)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCowFriesian) {
				EntityCowFriesian ech = (EntityCowFriesian)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCowHereford) {
				EntityCowHereford ech = (EntityCowHereford)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCowLonghorn) {
				EntityCowLonghorn ech = (EntityCowLonghorn)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCowAngus) {
				EntityCowAngus ech = (EntityCowAngus)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityBullHolstein) {
				EntityBullHolstein ech = (EntityBullHolstein)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityBullFriesian) {
				EntityBullFriesian ech = (EntityBullFriesian)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityBullHereford) {
				EntityBullHereford ech = (EntityBullHereford)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityBullLonghorn) {
				EntityBullLonghorn ech = (EntityBullLonghorn)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityBullAngus) {
				EntityBullAngus ech = (EntityBullAngus)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfHolstein) {
				EntityCalfHolstein ech = (EntityCalfHolstein)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfFriesian) {
				EntityCalfFriesian ech = (EntityCalfFriesian)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfHereford) {
				EntityCalfHereford ech = (EntityCalfHereford)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfLonghorn) {
				EntityCalfLonghorn ech = (EntityCalfLonghorn)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfAngus) {
				EntityCalfAngus ech = (EntityCalfAngus)temptedEntity;
				if (ech.getWatered()) {
					return false;
				}
			}

			Random rand = new Random();

			Biome biomegenbase = temptedEntity.world.getBiome(new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ)); 

			BlockPos currentpos = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ);
			BlockPos currentposlower = new BlockPos(temptedEntity.posX, temptedEntity.posY - 1, temptedEntity.posZ);
			Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();
			Block poschk2 = temptedEntity.world.getBlockState(currentposlower).getBlock();

			if (poschk == Animania.blockTrough) {
				TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(currentpos);
				if (te !=null && te.getTroughType() == 3) {

					te.setType(0);
					te.markDirty();
					temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 0);
					temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);

					if (temptedEntity instanceof EntityCowHolstein) {
						EntityCowHolstein ech = (EntityCowHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCowFriesian) {
						EntityCowFriesian ech = (EntityCowFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCowHereford) {
						EntityCowHereford ech = (EntityCowHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCowLonghorn) {
						EntityCowLonghorn ech = (EntityCowLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCowAngus) {
						EntityCowAngus ech = (EntityCowAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullHolstein) {
						EntityBullHolstein ech = (EntityBullHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullFriesian) {
						EntityBullFriesian ech = (EntityBullFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullHereford) {
						EntityBullHereford ech = (EntityBullHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
					} else if (temptedEntity instanceof EntityBullLonghorn) {
						EntityBullLonghorn ech = (EntityBullLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullAngus) {
						EntityBullAngus ech = (EntityBullAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfHolstein) {
						EntityCalfHolstein ech = (EntityCalfHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfFriesian) {
						EntityCalfFriesian ech = (EntityCalfFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfHereford) {
						EntityCalfHereford ech = (EntityCalfHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfLonghorn) {
						EntityCalfLonghorn ech = (EntityCalfLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfAngus) {
						EntityCalfAngus ech = (EntityCalfAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}

					return false;

				} else if (te !=null && te.getTroughType() == 2) {
					te.setType(3);
					te.markDirty();
					temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 3);
					temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
					if (temptedEntity instanceof EntityCowHolstein) {
						EntityCowHolstein ech = (EntityCowHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCowFriesian) {
						EntityCowFriesian ech = (EntityCowFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCowHereford) {
						EntityCowHereford ech = (EntityCowHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCowLonghorn) {
						EntityCowLonghorn ech = (EntityCowLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCowAngus) {
						EntityCowAngus ech = (EntityCowAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullHolstein) {
						EntityBullHolstein ech = (EntityBullHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullFriesian) {
						EntityBullFriesian ech = (EntityBullFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullHereford) {
						EntityBullHereford ech = (EntityBullHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullLonghorn) {
						EntityBullLonghorn ech = (EntityBullLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullAngus) {
						EntityBullAngus ech = (EntityBullAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
					} else if (temptedEntity instanceof EntityCalfHolstein) {
						EntityCalfHolstein ech = (EntityCalfHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfFriesian) {
						EntityCalfFriesian ech = (EntityCalfFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfHereford) {
						EntityCalfHereford ech = (EntityCalfHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfLonghorn) {
						EntityCalfLonghorn ech = (EntityCalfLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfAngus) {
						EntityCalfAngus ech = (EntityCalfAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					return false;

				} else if (te !=null && te.getTroughType() == 1) {
					te.setType(2);
					te.markDirty();

					temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 2);
					temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
					if (temptedEntity instanceof EntityCowHolstein) {
						EntityCowHolstein ech = (EntityCowHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCowFriesian) {
						EntityCowFriesian ech = (EntityCowFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCowHereford) {
						EntityCowHereford ech = (EntityCowHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCowLonghorn) {
						EntityCowLonghorn ech = (EntityCowLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCowAngus) {
						EntityCowAngus ech = (EntityCowAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullHolstein) {
						EntityBullHolstein ech = (EntityBullHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullFriesian) {
						EntityBullFriesian ech = (EntityBullFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullHereford) {
						EntityBullHereford ech = (EntityBullHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullLonghorn) {
						EntityBullLonghorn ech = (EntityBullLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityBullAngus) {
						EntityBullAngus ech = (EntityBullAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfHolstein) {
						EntityCalfHolstein ech = (EntityCalfHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfFriesian) {
						EntityCalfFriesian ech = (EntityCalfFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfHereford) {
						EntityCalfHereford ech = (EntityCalfHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfLonghorn) {
						EntityCalfLonghorn ech = (EntityCalfLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					} else if (temptedEntity instanceof EntityCalfAngus) {
						EntityCalfAngus ech = (EntityCalfAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setWatered(true);
					}
					return false;
				}
			} else if (poschk2 == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN) && !BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {

				if (temptedEntity instanceof EntityCowHolstein) {
					EntityCowHolstein ech = (EntityCowHolstein)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityCowFriesian) {
					EntityCowFriesian ech = (EntityCowFriesian)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityCowHereford) {
					EntityCowHereford ech = (EntityCowHereford)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityCowLonghorn) {
					EntityCowLonghorn ech = (EntityCowLonghorn)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityCowAngus) {
					EntityCowAngus ech = (EntityCowAngus)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityBullHolstein) {
					EntityBullHolstein ech = (EntityBullHolstein)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityBullFriesian) {
					EntityBullFriesian ech = (EntityBullFriesian)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityBullHereford) {
					EntityBullHereford ech = (EntityBullHereford)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityBullLonghorn) {
					EntityBullLonghorn ech = (EntityBullLonghorn)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityBullAngus) {
					EntityBullAngus ech = (EntityBullAngus)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityCalfHolstein) {
					EntityCalfHolstein ech = (EntityCalfHolstein)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityCalfFriesian) {
					EntityCalfFriesian ech = (EntityCalfFriesian)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityCalfHereford) {
					EntityCalfHereford ech = (EntityCalfHereford)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityCalfLonghorn) {
					EntityCalfLonghorn ech = (EntityCalfLonghorn)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				} else if (temptedEntity instanceof EntityCalfAngus) {
					EntityCalfAngus ech = (EntityCalfAngus)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setWatered(true);
				}

				if (temptedEntity.world.getGameRules().getBoolean("mobGriefing"))
				{
					temptedEntity.world.setBlockToAir(currentposlower);
				}


				return false;
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean waterFound = false;

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == Blocks.WATER) {
							waterFound = true;
							if (rand.nextInt(20) == 0) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							} else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0 && this.temptedEntity.motionZ == 0 ) {
								this.delayTemptCounter = 0;
								this.resetTask();
								return false;
							} else {
								return true;
							}
						} else if (blockchk == Animania.blockTrough) {
							TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(pos);
							if (te != null && (te.getTroughType() == 1 || te.getTroughType() == 2 || te.getTroughType() == 3)) {
								waterFound = true;
								if (rand.nextInt(20) == 0) {
									this.delayTemptCounter = 0;
									this.resetTask();
									return false;
								} else if (this.temptedEntity.isCollidedHorizontally && this.temptedEntity.motionX == 0 && this.temptedEntity.motionZ == 0) {
									this.delayTemptCounter = 0;
									this.resetTask();
									return false;
								} else {
									return true;
								}
							}
						}

					}

				}
			}

			if (!waterFound) {
				return false;
			}
		}

		return false;
	}



	public boolean continueExecuting()
	{

		return this.shouldExecute();
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{	
		this.isRunning = true;
	}

	/**
	 * Resets the task
	 */
	public void resetTask()
	{
		this.temptingPlayer = null;
		this.temptedEntity.getNavigator().clearPathEntity();
		this.isRunning = false;
	}


	public void updateTask()
	{

		double x = this.temptedEntity.posX;
		double y = this.temptedEntity.posY;
		double z = this.temptedEntity.posZ;

		BlockPos currentpos = new BlockPos(x, y, z);
		Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();

		if (poschk != Blocks.WATER) {

			boolean waterFound = false;
			int loc = 24;
			int newloc = 24;
			BlockPos pos = new BlockPos(x, y, z);
			BlockPos waterPos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);
						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						Biome biomegenbase = temptedEntity.world.getBiome(pos); 

						if (blockchk == Animania.blockTrough) {
							TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(pos);
							if (te !=null && (te.getTroughType() == 1 || te.getTroughType() == 2 || te.getTroughType() == 3)) {
								waterFound = true;
								newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

								if (newloc < loc) {

									loc = newloc;

									if (temptedEntity.posX < waterPos.getX()) {
										BlockPos waterPoschk = new BlockPos(x + i + 1, y + j, z + k);
										Block waterBlockchk = temptedEntity.world.getBlockState(waterPoschk).getBlock();
										if (waterBlockchk == Animania.blockTrough ) {
											i = i + 1;
										}
									} 

									if (temptedEntity.posZ < waterPos.getZ()) {
										BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
										Block waterBlockchk = temptedEntity.world.getBlockState(waterPoschk).getBlock();
										if (waterBlockchk == Animania.blockTrough ) {
											k = k + 1;
										} 
									}

									waterPos = new BlockPos(x + i, y + j, z + k);

								}

							}
						}

						if (blockchk == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN) && !BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
							waterFound = true;
							newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (temptedEntity.posX < waterPos.getX()) {
									BlockPos waterPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block waterBlockchk = temptedEntity.world.getBlockState(waterPoschk).getBlock();
									if (waterBlockchk == Blocks.WATER ) {
										i = i + 1;
									}
								} 

								if (temptedEntity.posZ < waterPos.getZ()) {
									BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block waterBlockchk = temptedEntity.world.getBlockState(waterPoschk).getBlock();
									if (waterBlockchk == Blocks.WATER ) {
										k = k + 1;
									} 
								}

								waterPos = new BlockPos(x + i, y + j, z + k);

							}

						} 

					}

				}


			}

			if (waterFound) {

				Block waterBlockchk = temptedEntity.world.getBlockState(waterPos).getBlock();
				Biome biomegenbase = temptedEntity.world.getBiome(waterPos); 

				if (waterBlockchk == Animania.blockTrough) {
					if(this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false) {
						this.resetTask();
					} else {
						this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);
					}

				} else if (waterBlockchk == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN) && !BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {
					if(this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false) {
						this.resetTask();
					} else {
						this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);
					}
				}
			}

		}



	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}