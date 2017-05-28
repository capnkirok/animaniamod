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

public class EntityAIFindFood extends EntityAIBase
{
    private final EntityCreature temptedEntity;
    private final double         speed;
    private double               targetX;
    private double               targetY;
    private double               targetZ;
    private double               pitch;
    private double               yaw;
    private EntityPlayer         temptingPlayer;
    private boolean              isRunning;
    private int                  delayTemptCounter;

    public EntityAIFindFood(EntityCreature temptedEntityIn, double speedIn) {
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
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCowFriesian) {
				EntityCowFriesian ech = (EntityCowFriesian)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCowHereford) {
				EntityCowHereford ech = (EntityCowHereford)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCowLonghorn) {
				EntityCowLonghorn ech = (EntityCowLonghorn)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCowAngus) {
				EntityCowAngus ech = (EntityCowAngus)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityBullHolstein) {
				EntityBullHolstein ech = (EntityBullHolstein)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityBullFriesian) {
				EntityBullFriesian ech = (EntityBullFriesian)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityBullHereford) {
				EntityBullHereford ech = (EntityBullHereford)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityBullLonghorn) {
				EntityBullLonghorn ech = (EntityBullLonghorn)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityBullAngus) {
				EntityBullAngus ech = (EntityBullAngus)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfHolstein) {
				EntityCalfHolstein ech = (EntityCalfHolstein)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfFriesian) {
				EntityCalfFriesian ech = (EntityCalfFriesian)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfHereford) {
				EntityCalfHereford ech = (EntityCalfHereford)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfLonghorn) {
				EntityCalfLonghorn ech = (EntityCalfLonghorn)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			} else if (temptedEntity instanceof EntityCalfAngus) {
				EntityCalfAngus ech = (EntityCalfAngus)temptedEntity;
				if (ech.getFed()) {
					this.delayTemptCounter = 0;
					return false;
				}
			}

            BlockPos currentpos = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ);
            BlockPos trypos1 = new BlockPos(this.temptedEntity.posX + 1, this.temptedEntity.posY, this.temptedEntity.posZ);
            BlockPos trypos2 = new BlockPos(this.temptedEntity.posX - 1, this.temptedEntity.posY, this.temptedEntity.posZ);
            BlockPos trypos3 = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ + 1);
            BlockPos trypos4 = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ - 1);
            BlockPos trypos5 = new BlockPos(this.temptedEntity.posX + 1, this.temptedEntity.posY, this.temptedEntity.posZ + 1);
            BlockPos trypos6 = new BlockPos(this.temptedEntity.posX - 1, this.temptedEntity.posY, this.temptedEntity.posZ - 1);
            BlockPos trypos7 = new BlockPos(this.temptedEntity.posX - 1, this.temptedEntity.posY, this.temptedEntity.posZ + 1);
            BlockPos trypos8 = new BlockPos(this.temptedEntity.posX + 1, this.temptedEntity.posY, this.temptedEntity.posZ - 1);
            Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();
            Block poschk1 = this.temptedEntity.world.getBlockState(trypos1).getBlock();
            Block poschk2 = this.temptedEntity.world.getBlockState(trypos2).getBlock();
            Block poschk3 = this.temptedEntity.world.getBlockState(trypos3).getBlock();
            Block poschk4 = this.temptedEntity.world.getBlockState(trypos4).getBlock();
            Block poschk5 = this.temptedEntity.world.getBlockState(trypos5).getBlock();
            Block poschk6 = this.temptedEntity.world.getBlockState(trypos6).getBlock();
            Block poschk7 = this.temptedEntity.world.getBlockState(trypos7).getBlock();
            Block poschk8 = this.temptedEntity.world.getBlockState(trypos8).getBlock();

            if (poschk == BlockHandler.blockTrough) {
                // do nothing
            }
            else if (poschk1 == BlockHandler.blockTrough)
                currentpos = trypos1;
            else if (poschk2 == BlockHandler.blockTrough)
                currentpos = trypos2;
            else if (poschk3 == BlockHandler.blockTrough)
                currentpos = trypos3;
            else if (poschk4 == BlockHandler.blockTrough)
                currentpos = trypos4;
            else if (poschk5 == BlockHandler.blockTrough)
                currentpos = trypos5;
            else if (poschk6 == BlockHandler.blockTrough)
                currentpos = trypos6;
            else if (poschk7 == BlockHandler.blockTrough)
                currentpos = trypos7;
            else if (poschk8 == BlockHandler.blockTrough)
                currentpos = trypos8;

            if (poschk == BlockHandler.blockTrough) {
                TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(currentpos);
                if (te != null && !te.itemHandler.getStackInSlot(0).isEmpty()) {
                    te.itemHandler.extractItem(0, 1, false);

                    if (this.temptedEntity instanceof EntityCowHolstein) {
                        EntityCowHolstein ech = (EntityCowHolstein) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityCowFriesian) {
                        EntityCowFriesian ech = (EntityCowFriesian) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityCowHereford) {
                        EntityCowHereford ech = (EntityCowHereford) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityCowLonghorn) {
                        EntityCowLonghorn ech = (EntityCowLonghorn) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityCowAngus) {
                        EntityCowAngus ech = (EntityCowAngus) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityBullHolstein) {
                        EntityBullHolstein ech = (EntityBullHolstein) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityBullFriesian) {
                        EntityBullFriesian ech = (EntityBullFriesian) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityBullHereford) {
                        EntityBullHereford ech = (EntityBullHereford) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityBullLonghorn) {
                        EntityBullLonghorn ech = (EntityBullLonghorn) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityBullAngus) {
                        EntityBullAngus ech = (EntityBullAngus) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityCalfHolstein) {
                        EntityCalfHolstein ech = (EntityCalfHolstein) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityCalfFriesian) {
                        EntityCalfFriesian ech = (EntityCalfFriesian) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityCalfHereford) {
                        EntityCalfHereford ech = (EntityCalfHereford) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityCalfLonghorn) {
                        EntityCalfLonghorn ech = (EntityCalfLonghorn) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setFed(true);
                    }
                    else if (this.temptedEntity instanceof EntityCalfAngus) {
                        EntityCalfAngus ech = (EntityCalfAngus) this.temptedEntity;
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
                
                temptedEntity.world.destroyBlock(currentpos, false);

                return false;
            }

            double x = this.temptedEntity.posX;
            double y = this.temptedEntity.posY;
            double z = this.temptedEntity.posZ;

            boolean foodFound = false;
            Random rand = new Random();

            BlockPos pos = new BlockPos(x, y, z);

            for (int i = -16; i < 16; i++)
                for (int j = -3; j < 3; j++)
                    for (int k = -16; k < 16; k++) {

                        pos = new BlockPos(x + i, y + j, z + k);

                        Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

                        if (blockchk == BlockHandler.blockTrough) {
                            TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(pos);
                            if (te != null && !te.itemHandler.getStackInSlot(0).isEmpty()) {
                                foodFound = true;
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

                        if (blockchk == Blocks.RED_FLOWER || blockchk == Blocks.CARROTS || blockchk == Blocks.WHEAT
                                || blockchk == Blocks.YELLOW_FLOWER) {

                            foodFound = true;
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

            if (!foodFound)
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

        boolean foodFound = false;
        int loc = 24;
        int newloc = 24;
        BlockPos pos = new BlockPos(x, y, z);
        BlockPos mudPos = new BlockPos(x, y, z);

        for (int i = -16; i < 16; i++)
            for (int j = -3; j < 3; j++)
                for (int k = -16; k < 16; k++) {

                    pos = new BlockPos(x + i, y + j, z + k);
                    Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

                    if (blockchk == BlockHandler.blockTrough) {

                        TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(pos);

                        if (te != null && !te.itemHandler.getStackInSlot(0).isEmpty()) {

                            foodFound = true;
                            newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

                            if (newloc < loc) {

                                loc = newloc;

                                if (this.temptedEntity.posX < mudPos.getX()) {
                                    BlockPos mudPoschk = new BlockPos(x + i + 1, y + j, z + k);
                                    Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPoschk).getBlock();
                                    i = i + 1;
                                }

                                if (this.temptedEntity.posZ < mudPos.getZ()) {
                                    BlockPos mudPoschk = new BlockPos(x + i, y + j, z + k + 1);
                                    Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPoschk).getBlock();
                                    k = k + 1;
                                }

                                mudPos = new BlockPos(x + i, y + j, z + k);

                            }
                        }
                    }
                    else if (blockchk == Blocks.RED_FLOWER || blockchk == Blocks.CARROTS || blockchk == Blocks.WHEAT
                            || blockchk == Blocks.YELLOW_FLOWER) {
                        foodFound = true;
                        newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

                        if (newloc < loc) {

                            loc = newloc;

                            if (this.temptedEntity.posX < mudPos.getX()) {
                                BlockPos mudPoschk = new BlockPos(x + i + 1, y + j, z + k);
                                Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPoschk).getBlock();
                                if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk == Blocks.CARROTS || mudBlockchk == Blocks.WHEAT
                                        || mudBlockchk == Blocks.YELLOW_FLOWER)
                                    i = i + 1;
                            }

                            if (this.temptedEntity.posZ < mudPos.getZ()) {
                                BlockPos mudPoschk = new BlockPos(x + i, y + j, z + k + 1);
                                Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPoschk).getBlock();
                                if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk == Blocks.CARROTS || mudBlockchk == Blocks.WHEAT
                                        || mudBlockchk == Blocks.YELLOW_FLOWER)
                                    k = k + 1;
                            }

                            mudPos = new BlockPos(x + i, y + j, z + k);

                        }

                    }

                }

        if (foodFound) {

            Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPos).getBlock();
            if (mudBlockchk == Blocks.RED_FLOWER || mudBlockchk == Blocks.CARROTS || mudBlockchk == Blocks.WHEAT
                    || mudBlockchk == Blocks.YELLOW_FLOWER || mudBlockchk == BlockHandler.blockTrough)
                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
        }

    }

    public boolean isRunning() {
        return this.isRunning;
    }
}