package com.animania.common.entities.pigs.ai;

import java.util.Random;

import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogHampshire;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogLargeWhite;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntityPigletDuroc;
import com.animania.common.entities.pigs.EntityPigletHampshire;
import com.animania.common.entities.pigs.EntityPigletLargeBlack;
import com.animania.common.entities.pigs.EntityPigletLargeWhite;
import com.animania.common.entities.pigs.EntityPigletOldSpot;
import com.animania.common.entities.pigs.EntityPigletYorkshire;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowHampshire;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowLargeWhite;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class EntityAIFindMud extends EntityAIBase
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

    public EntityAIFindMud(EntityCreature temptedEntityIn, double speedIn) {
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

        this.delayTemptCounter++;
        if (this.delayTemptCounter > 20) {
            this.delayTemptCounter = 0;
            if (this.temptedEntity instanceof EntitySowYorkshire) {
                EntitySowYorkshire sow = (EntitySowYorkshire) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowDuroc) {
                EntitySowDuroc sow = (EntitySowDuroc) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowHampshire) {
                EntitySowHampshire sow = (EntitySowHampshire) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowYorkshire) {
                EntitySowYorkshire sow = (EntitySowYorkshire) this.temptedEntity;
                sow.entityAIEatGrass.startExecuting();
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowLargeBlack) {
                EntitySowLargeBlack sow = (EntitySowLargeBlack) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowLargeWhite) {
                EntitySowLargeWhite sow = (EntitySowLargeWhite) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowOldSpot) {
                EntitySowOldSpot sow = (EntitySowOldSpot) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogOldSpot) {
                EntityHogOldSpot sow = (EntityHogOldSpot) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogDuroc) {
                EntityHogDuroc sow = (EntityHogDuroc) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogHampshire) {
                EntityHogHampshire sow = (EntityHogHampshire) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogYorkshire) {
                EntityHogYorkshire sow = (EntityHogYorkshire) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogLargeBlack) {
                EntityHogLargeBlack sow = (EntityHogLargeBlack) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogLargeWhite) {
                EntityHogLargeWhite sow = (EntityHogLargeWhite) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletOldSpot) {
                EntityPigletOldSpot sow = (EntityPigletOldSpot) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletDuroc) {
                EntityPigletDuroc sow = (EntityPigletDuroc) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletHampshire) {
                EntityPigletHampshire sow = (EntityPigletHampshire) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletYorkshire) {
                EntityPigletYorkshire sow = (EntityPigletYorkshire) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletLargeBlack) {
                EntityPigletLargeBlack sow = (EntityPigletLargeBlack) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletLargeWhite) {
                EntityPigletLargeWhite sow = (EntityPigletLargeWhite) this.temptedEntity;
                if (sow.getPlayed())
                    return false;
            }

            Random rand = new Random();
            BlockPos currentpos = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ);
            Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();
            if (poschk == BlockHandler.blockMud) {
                if (this.temptedEntity instanceof EntitySowOldSpot) {
                    EntitySowOldSpot ech = (EntitySowOldSpot) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntitySowDuroc) {
                    EntitySowDuroc ech = (EntitySowDuroc) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntitySowHampshire) {
                    EntitySowHampshire ech = (EntitySowHampshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntitySowYorkshire) {
                    EntitySowYorkshire ech = (EntitySowYorkshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntitySowLargeBlack) {
                    EntitySowLargeBlack ech = (EntitySowLargeBlack) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntitySowLargeWhite) {
                    EntitySowLargeWhite ech = (EntitySowLargeWhite) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntityHogOldSpot) {
                    EntityHogOldSpot ech = (EntityHogOldSpot) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntityHogDuroc) {
                    EntityHogDuroc ech = (EntityHogDuroc) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntityHogHampshire) {
                    EntityHogHampshire ech = (EntityHogHampshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntityHogYorkshire) {
                    EntityHogYorkshire ech = (EntityHogYorkshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntityHogLargeBlack) {
                    EntityHogLargeBlack ech = (EntityHogLargeBlack) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntityHogLargeWhite) {
                    EntityHogLargeWhite ech = (EntityHogLargeWhite) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntityPigletOldSpot) {
                    EntityPigletOldSpot ech = (EntityPigletOldSpot) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntityPigletDuroc) {
                    EntityPigletDuroc ech = (EntityPigletDuroc) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntityPigletHampshire) {
                    EntityPigletHampshire ech = (EntityPigletHampshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntityPigletYorkshire) {
                    EntityPigletYorkshire ech = (EntityPigletYorkshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntityPigletLargeBlack) {
                    EntityPigletLargeBlack ech = (EntityPigletLargeBlack) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                else if (this.temptedEntity instanceof EntityPigletLargeWhite) {
                    EntityPigletLargeWhite ech = (EntityPigletLargeWhite) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setPlayed(true);
                }
                return false;
            }

            double x = this.temptedEntity.posX;
            double y = this.temptedEntity.posY;
            double z = this.temptedEntity.posZ;

            boolean mudFound = false;

            BlockPos pos = new BlockPos(x, y, z);

            for (int i = -10; i < 10; i++)
                for (int j = -2; j < 2; j++)
                    for (int k = -10; k < 10; k++) {

                        pos = new BlockPos(x + i, y + j, z + k);
                        Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

                        if (blockchk != null && blockchk == BlockHandler.blockMud) {
                            mudFound = true;
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

            if (!mudFound)
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
        this.delayTemptCounter = 200;
        this.isRunning = false;
    }

    @Override
    public void updateTask() {

        double x = this.temptedEntity.posX;
        double y = this.temptedEntity.posY;
        double z = this.temptedEntity.posZ;

        BlockPos currentpos = new BlockPos(x, y, z);
        Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();
        if (poschk != BlockHandler.blockMud) {

            boolean mudFound = false;
            boolean spcFlag = false;
            int loc = 24;
            int newloc = 24;
            BlockPos pos = new BlockPos(x, y, z);
            BlockPos mudPos = new BlockPos(x, y, z);

            for (int i = -10; i < 10; i++)
                for (int j = -2; j < 2; j++)
                    for (int k = -10; k < 10; k++) {

                        pos = new BlockPos(x + i, y + j, z + k);
                        Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();
                        if (blockchk == BlockHandler.blockMud && !this.temptedEntity.hasPath()) {
                            mudFound = true;
                            newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

                            if (newloc < loc) {

                                loc = newloc;

                                if (this.temptedEntity.posX > mudPos.getX()) {
                                    BlockPos mudPoschk = new BlockPos(x + i + 1, y + j, z + k);
                                    Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPoschk).getBlock();
                                    if (mudBlockchk == BlockHandler.blockMud) {
                                        spcFlag = true;
                                        i = i + 1;
                                    }
                                }

                                if (this.temptedEntity.posZ > mudPos.getZ()) {
                                    BlockPos mudPoschk = new BlockPos(x + i, y + j, z + k + 1);
                                    Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPoschk).getBlock();
                                    if (mudBlockchk == BlockHandler.blockMud) {
                                        spcFlag = true;
                                        k = k + 1;
                                    }
                                }

                                mudPos = new BlockPos(x + i, y + j, z + k);

                                if (spcFlag) {
                                    i = 10;
                                    j = 3;
                                    k = 10;
                                }

                            }

                        }

                    }

            if (mudFound) {

                Block mudBlockchk = this.temptedEntity.world.getBlockState(mudPos).getBlock();
                if (mudBlockchk == BlockHandler.blockMud)
                    if (this.temptedEntity instanceof EntitySowYorkshire) {
                        EntitySowYorkshire te = (EntitySowYorkshire) this.temptedEntity;

                        if (te.getMuddy() == false) {
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);

                            this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);

                        }
                    }
                    else if (this.temptedEntity instanceof EntitySowDuroc) {
                        EntitySowDuroc te = (EntitySowDuroc) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
                    }
                    else if (this.temptedEntity instanceof EntitySowLargeBlack) {
                        EntitySowLargeBlack te = (EntitySowLargeBlack) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
                    }
                    else if (this.temptedEntity instanceof EntitySowLargeWhite) {
                        EntitySowLargeWhite te = (EntitySowLargeWhite) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
                    }
                    else if (this.temptedEntity instanceof EntitySowOldSpot) {
                        EntitySowOldSpot te = (EntitySowOldSpot) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
                    }
                    else if (this.temptedEntity instanceof EntitySowHampshire) {
                        EntitySowHampshire te = (EntitySowHampshire) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
                    }
                    else if (this.temptedEntity instanceof EntityPigletYorkshire) {
                        EntityPigletYorkshire te = (EntityPigletYorkshire) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
                    }
                    else if (this.temptedEntity instanceof EntityPigletDuroc) {
                        EntityPigletDuroc te = (EntityPigletDuroc) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
                    }
                    else if (this.temptedEntity instanceof EntityPigletLargeBlack) {
                        EntityPigletLargeBlack te = (EntityPigletLargeBlack) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
                    }
                    else if (this.temptedEntity instanceof EntityPigletLargeWhite) {
                        EntityPigletLargeWhite te = (EntityPigletLargeWhite) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
                    }
                    else if (this.temptedEntity instanceof EntityPigletOldSpot) {
                        EntityPigletOldSpot te = (EntityPigletOldSpot) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
                    }
                    else if (this.temptedEntity instanceof EntityPigletHampshire) {
                        EntityPigletHampshire te = (EntityPigletHampshire) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 2, mudPos.getY(), mudPos.getZ() + 2, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
                    }
                    else if (this.temptedEntity instanceof EntityHogYorkshire) {
                        EntityHogYorkshire te = (EntityHogYorkshire) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 3, mudPos.getY(), mudPos.getZ() + 3, this.speed);
                            else if (te.posX - mudPos.getX() > 0 && te.posZ - mudPos.getZ() > 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
                    }
                    else if (this.temptedEntity instanceof EntityHogDuroc) {
                        EntityHogDuroc te = (EntityHogDuroc) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 3, mudPos.getY(), mudPos.getZ() + 3, this.speed);
                            else if (te.posX - mudPos.getX() > 0 && te.posZ - mudPos.getZ() > 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
                    }
                    else if (this.temptedEntity instanceof EntityHogLargeBlack) {
                        EntityHogLargeBlack te = (EntityHogLargeBlack) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 3, mudPos.getY(), mudPos.getZ() + 3, this.speed);
                            else if (te.posX - mudPos.getX() > 0 && te.posZ - mudPos.getZ() > 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
                    }
                    else if (this.temptedEntity instanceof EntityHogLargeWhite) {
                        EntityHogLargeWhite te = (EntityHogLargeWhite) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 3, mudPos.getY(), mudPos.getZ() + 3, this.speed);
                            else if (te.posX - mudPos.getX() > 0 && te.posZ - mudPos.getZ() > 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
                    }
                    else if (this.temptedEntity instanceof EntityHogOldSpot) {
                        EntityHogOldSpot te = (EntityHogOldSpot) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 3, mudPos.getY(), mudPos.getZ() + 3, this.speed);
                            else if (te.posX - mudPos.getX() > 0 && te.posZ - mudPos.getZ() > 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
                    }
                    else if (this.temptedEntity instanceof EntityHogHampshire) {
                        EntityHogHampshire te = (EntityHogHampshire) this.temptedEntity;
                        if (te.getMuddy() == false)
                            if (te.posX - mudPos.getX() < 0 && te.posZ - mudPos.getZ() < 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() + 3, mudPos.getY(), mudPos.getZ() + 3, this.speed);
                            else if (te.posX - mudPos.getX() > 0 && te.posZ - mudPos.getZ() > 0)
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX() - 1, mudPos.getY(), mudPos.getZ() - 1, this.speed);
                            else
                                this.temptedEntity.getNavigator().tryMoveToXYZ(mudPos.getX(), mudPos.getY(), mudPos.getZ(), this.speed);
                    }
            }
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}