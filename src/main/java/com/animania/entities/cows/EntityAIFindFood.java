package com.animania.entities.cows;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

import com.animania.Animania;
import com.animania.tileentities.TileEntityTrough;

public class EntityAIFindFood extends EntityAIBase 
{
	private final EntityCreature temptedEntity;
	private final double speed;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double pitch;
	private double yaw;
	private EntityPlayer temptingPlayer;
	private boolean isRunning;
	private int delayTemptCounter;

	public EntityAIFindFood(EntityCreature temptedEntityIn, double speedIn)
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
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCowFriesian) {
				EntityCowFriesian ech = (EntityCowFriesian)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCowHereford) {
				EntityCowHereford ech = (EntityCowHereford)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCowLonghorn) {
				EntityCowLonghorn ech = (EntityCowLonghorn)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCowAngus) {
				EntityCowAngus ech = (EntityCowAngus)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityBullHolstein) {
				EntityBullHolstein ech = (EntityBullHolstein)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityBullFriesian) {
				EntityBullFriesian ech = (EntityBullFriesian)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityBullHereford) {
				EntityBullHereford ech = (EntityBullHereford)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityBullLonghorn) {
				EntityBullLonghorn ech = (EntityBullLonghorn)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityBullAngus) {
				EntityBullAngus ech = (EntityBullAngus)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfHolstein) {
				EntityCalfHolstein ech = (EntityCalfHolstein)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfFriesian) {
				EntityCalfFriesian ech = (EntityCalfFriesian)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfHereford) {
				EntityCalfHereford ech = (EntityCalfHereford)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfLonghorn) {
				EntityCalfLonghorn ech = (EntityCalfLonghorn)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfAngus) {
				EntityCalfAngus ech = (EntityCalfAngus)temptedEntity;
				if (ech.getFed()) {
					return false;
				}
			}

			BlockPos currentpos = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ);
			BlockPos trypos1 = new BlockPos(temptedEntity.posX + 1, temptedEntity.posY, temptedEntity.posZ);
			BlockPos trypos2 = new BlockPos(temptedEntity.posX - 1, temptedEntity.posY, temptedEntity.posZ);
			BlockPos trypos3 = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ + 1);
			BlockPos trypos4 = new BlockPos(temptedEntity.posX, temptedEntity.posY, temptedEntity.posZ - 1);
			BlockPos trypos5 = new BlockPos(temptedEntity.posX + 1, temptedEntity.posY, temptedEntity.posZ + 1);
			BlockPos trypos6 = new BlockPos(temptedEntity.posX - 1, temptedEntity.posY, temptedEntity.posZ - 1);
			BlockPos trypos7 = new BlockPos(temptedEntity.posX - 1, temptedEntity.posY, temptedEntity.posZ + 1);
			BlockPos trypos8 = new BlockPos(temptedEntity.posX + 1, temptedEntity.posY, temptedEntity.posZ - 1);
			Block poschk = temptedEntity.world.getBlockState(currentpos).getBlock();
			Block poschk1 = temptedEntity.world.getBlockState(trypos1).getBlock();
			Block poschk2 = temptedEntity.world.getBlockState(trypos2).getBlock();
			Block poschk3 = temptedEntity.world.getBlockState(trypos3).getBlock();
			Block poschk4 = temptedEntity.world.getBlockState(trypos4).getBlock();
			Block poschk5 = temptedEntity.world.getBlockState(trypos5).getBlock();
			Block poschk6 = temptedEntity.world.getBlockState(trypos6).getBlock();
			Block poschk7 = temptedEntity.world.getBlockState(trypos7).getBlock();
			Block poschk8 = temptedEntity.world.getBlockState(trypos8).getBlock();

			if (poschk == Animania.blockTrough) {
				//do nothing
			} else if (poschk1 == Animania.blockTrough) {
				currentpos = trypos1;
			} else if (poschk2 == Animania.blockTrough) {
				currentpos = trypos2;
			} else if (poschk3 == Animania.blockTrough) {
				currentpos = trypos3;
			} else if (poschk4 == Animania.blockTrough) {
				currentpos = trypos4;
			} else if (poschk5 == Animania.blockTrough) {
				currentpos = trypos5;
			} else if (poschk6 == Animania.blockTrough) {
				currentpos = trypos6;
			} else if (poschk7 == Animania.blockTrough) {
				currentpos = trypos7;
			} else if (poschk8 == Animania.blockTrough) {
				currentpos = trypos8;
			}

			if (poschk == Animania.blockTrough) {
				TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(currentpos);
				if (te !=null && te.getTroughType() == 4) {
					te.setType(0);
					te.markDirty();
					temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 0);
					temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);

					if (temptedEntity instanceof EntityCowHolstein) {
						EntityCowHolstein ech = (EntityCowHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCowFriesian) {
						EntityCowFriesian ech = (EntityCowFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCowHereford) {
						EntityCowHereford ech = (EntityCowHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCowLonghorn) {
						EntityCowLonghorn ech = (EntityCowLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCowAngus) {
						EntityCowAngus ech = (EntityCowAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullHolstein) {
						EntityBullHolstein ech = (EntityBullHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullFriesian) {
						EntityBullFriesian ech = (EntityBullFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullHereford) {
						EntityBullHereford ech = (EntityBullHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullLonghorn) {
						EntityBullLonghorn ech = (EntityBullLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullAngus) {
						EntityBullAngus ech = (EntityBullAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfHolstein) {
						EntityCalfHolstein ech = (EntityCalfHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfFriesian) {
						EntityCalfFriesian ech = (EntityCalfFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfHereford) {
						EntityCalfHereford ech = (EntityCalfHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfLonghorn) {
						EntityCalfLonghorn ech = (EntityCalfLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfAngus) {
						EntityCalfAngus ech = (EntityCalfAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					}

					return false;

				} else if (te !=null && te.getTroughType() == 5) {
					te.setType(4);
					te.markDirty();
					temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 4);
					temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
					if (temptedEntity instanceof EntityCowHolstein) {
						EntityCowHolstein ech = (EntityCowHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCowFriesian) {
						EntityCowFriesian ech = (EntityCowFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCowHereford) {
						EntityCowHereford ech = (EntityCowHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCowLonghorn) {
						EntityCowLonghorn ech = (EntityCowLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCowAngus) {
						EntityCowAngus ech = (EntityCowAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullHolstein) {
						EntityBullHolstein ech = (EntityBullHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullFriesian) {
						EntityBullFriesian ech = (EntityBullFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullHereford) {
						EntityBullHereford ech = (EntityBullHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullLonghorn) {
						EntityBullLonghorn ech = (EntityBullLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullAngus) {
						EntityBullAngus ech = (EntityBullAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfHolstein) {
						EntityCalfHolstein ech = (EntityCalfHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfFriesian) {
						EntityCalfFriesian ech = (EntityCalfFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfHereford) {
						EntityCalfHereford ech = (EntityCalfHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfLonghorn) {
						EntityCalfLonghorn ech = (EntityCalfLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfAngus) {
						EntityCalfAngus ech = (EntityCalfAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					}
					return false;
				} else if (te !=null && te.getTroughType() == 6) {
					te.setType(5);
					te.markDirty();
					temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 5);
					temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
					if (temptedEntity instanceof EntityCowHolstein) {
						EntityCowHolstein ech = (EntityCowHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCowFriesian) {
						EntityCowFriesian ech = (EntityCowFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCowHereford) {
						EntityCowHereford ech = (EntityCowHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCowLonghorn) {
						EntityCowLonghorn ech = (EntityCowLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCowAngus) {
						EntityCowAngus ech = (EntityCowAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullHolstein) {
						EntityBullHolstein ech = (EntityBullHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullFriesian) {
						EntityBullFriesian ech = (EntityBullFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullHereford) {
						EntityBullHereford ech = (EntityBullHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullLonghorn) {
						EntityBullLonghorn ech = (EntityBullLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityBullAngus) {
						EntityBullAngus ech = (EntityBullAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfHolstein) {
						EntityCalfHolstein ech = (EntityCalfHolstein)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfFriesian) {
						EntityCalfFriesian ech = (EntityCalfFriesian)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfHereford) {
						EntityCalfHereford ech = (EntityCalfHereford)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfLonghorn) {
						EntityCalfLonghorn ech = (EntityCalfLonghorn)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} else if (temptedEntity instanceof EntityCalfAngus) {
						EntityCalfAngus ech = (EntityCalfAngus)temptedEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					}
					return false;
				}


			} 

			if (poschk == Blocks.RED_FLOWER || poschk == Blocks.CARROTS || poschk == Blocks.WHEAT || poschk == Blocks.YELLOW_FLOWER) {

				if (temptedEntity instanceof EntityCowHolstein) {
					EntityCowHolstein ech = (EntityCowHolstein)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityCowFriesian) {
					EntityCowFriesian ech = (EntityCowFriesian)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityCowHereford) {
					EntityCowHereford ech = (EntityCowHereford)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityCowLonghorn) {
					EntityCowLonghorn ech = (EntityCowLonghorn)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityCowAngus) {
					EntityCowAngus ech = (EntityCowAngus)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityBullHolstein) {
					EntityBullHolstein ech = (EntityBullHolstein)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityBullFriesian) {
					EntityBullFriesian ech = (EntityBullFriesian)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityBullHereford) {
					EntityBullHereford ech = (EntityBullHereford)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityBullLonghorn) {
					EntityBullLonghorn ech = (EntityBullLonghorn)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityBullAngus) {
					EntityBullAngus ech = (EntityBullAngus)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityCalfHolstein) {
					EntityCalfHolstein ech = (EntityCalfHolstein)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityCalfFriesian) {
					EntityCalfFriesian ech = (EntityCalfFriesian)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityCalfHereford) {
					EntityCalfHereford ech = (EntityCalfHereford)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityCalfLonghorn) {
					EntityCalfLonghorn ech = (EntityCalfLonghorn)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} else if (temptedEntity instanceof EntityCalfAngus) {
					EntityCalfAngus ech = (EntityCalfAngus)temptedEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				}


				return false;
			}

			double x = this.temptedEntity.posX;
			double y = this.temptedEntity.posY;
			double z = this.temptedEntity.posZ;

			boolean foodFound = false;
			Random rand = new Random();

			BlockPos pos = new BlockPos(x, y, z);

			for (int i = -16; i < 16; i++) {
				for (int j = -3; j < 3; j++) {
					for (int k = -16; k < 16; k++) {

						pos = new BlockPos(x + i, y + j, z + k);

						Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();

						if (blockchk == Animania.blockTrough) {
							TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(pos);
							if (te !=null && (te.getTroughType() == 4 || te.getTroughType() == 5 || te.getTroughType() == 6)) {
								foodFound = true;
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
							}
						}

						if (blockchk == Blocks.RED_FLOWER || blockchk == Blocks.CARROTS || blockchk == Blocks.WHEAT || blockchk == Blocks.YELLOW_FLOWER) {

							foodFound = true;
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
						}

					}

				}
			}

			if (!foodFound) {
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

		boolean foodFound = false;
		int loc = 24;
		int newloc = 24;
		BlockPos pos = new BlockPos(x, y, z);
		BlockPos mudPos = new BlockPos(x, y, z);

		for (int i = -16; i < 16; i++) {
			for (int j = -3; j < 3; j++) {
				for (int k = -16; k < 16; k++) {

					pos = new BlockPos(x + i, y + j, z + k);
					Block blockchk = temptedEntity.world.getBlockState(pos).getBlock();


					if (blockchk == Animania.blockTrough) {


						TileEntityTrough te = (TileEntityTrough) temptedEntity.world.getTileEntity(pos);

						if (te !=null && (te.getTroughType() == 4 || te.getTroughType() == 5 || te.getTroughType() == 6)) {

							foodFound = true;
							newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

							if (newloc < loc) {

								loc = newloc;

								if (temptedEntity.posX < mudPos.getX()) {
									BlockPos mudPoschk = new BlockPos(x + i + 1, y + j, z + k);
									Block mudBlockchk = temptedEntity.world.getBlockState(mudPoschk).getBlock();
									i = i + 1;
								} 

								if (temptedEntity.posZ < mudPos.getZ()) {
									BlockPos mudPoschk = new BlockPos(x + i, y + j, z + k + 1);
									Block mudBlockchk = temptedEntity.world.getBlockState(mudPoschk).getBlock();
									k = k + 1;
								}

								mudPos = new BlockPos(x + i, y + j, z + k);

							}
						}
					} else if (blockchk == Blocks.RED_FLOWER || blockchk == Blocks.CARROTS || blockchk == Blocks.WHEAT || blockchk == Blocks.YELLOW_FLOWER) {
						foodFound = true;
						newloc = Math.abs(i)  +  Math.abs(j) +  Math.abs(k);

						if (newloc < loc) {

							loc = newloc;

							if (temptedEntity.posX < mudPos.getX()) {
								BlockPos mudPoschk = new BlockPos(x + i + 1, y + j, z + k);
								Block mudBlockchk = temptedEntity.world.getBlockState(mudPoschk).getBlock();
								if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk == Blocks.CARROTS || mudBlockchk == Blocks.WHEAT || mudBlockchk == Blocks.YELLOW_FLOWER) {
									i = i + 1;
								}
							} 

							if (temptedEntity.posZ < mudPos.getZ()) {
								BlockPos mudPoschk = new BlockPos(x + i, y + j, z + k + 1);
								Block mudBlockchk = temptedEntity.world.getBlockState(mudPoschk).getBlock();
								if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk == Blocks.CARROTS || mudBlockchk == Blocks.WHEAT || mudBlockchk == Blocks.YELLOW_FLOWER) {
									k = k + 1;
								} 
							}

							mudPos = new BlockPos(x + i, y + j, z + k);

						}

					}

				}

			}


		}

		if (foodFound) {

			Block mudBlockchk = temptedEntity.world.getBlockState(mudPos).getBlock();
			if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk == Blocks.CARROTS || mudBlockchk == Blocks.WHEAT || mudBlockchk == Blocks.YELLOW_FLOWER || (mudBlockchk == Animania.blockTrough)) {

				this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);

			}
		}

	}





	public boolean isRunning()
	{
		return this.isRunning;
	}
}