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

        this.delayTemptCounter++;
        if (this.delayTemptCounter > 20) {
            if (this.temptedEntity instanceof EntitySowYorkshire) {
                EntitySowYorkshire sow = (EntitySowYorkshire) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowOldSpot) {
                EntitySowOldSpot sow = (EntitySowOldSpot) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowDuroc) {
                EntitySowDuroc sow = (EntitySowDuroc) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowHampshire) {
                EntitySowHampshire sow = (EntitySowHampshire) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowYorkshire) {
                EntitySowYorkshire sow = (EntitySowYorkshire) this.temptedEntity;
                sow.entityAIEatGrass.startExecuting();
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowLargeBlack) {
                EntitySowLargeBlack sow = (EntitySowLargeBlack) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntitySowLargeWhite) {
                EntitySowLargeWhite sow = (EntitySowLargeWhite) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogOldSpot) {
                EntityHogOldSpot sow = (EntityHogOldSpot) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogDuroc) {
                EntityHogDuroc sow = (EntityHogDuroc) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogHampshire) {
                EntityHogHampshire sow = (EntityHogHampshire) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogYorkshire) {
                EntityHogYorkshire sow = (EntityHogYorkshire) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogLargeBlack) {
                EntityHogLargeBlack sow = (EntityHogLargeBlack) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHogLargeWhite) {
                EntityHogLargeWhite sow = (EntityHogLargeWhite) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletOldSpot) {
                EntityPigletOldSpot sow = (EntityPigletOldSpot) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletDuroc) {
                EntityPigletDuroc sow = (EntityPigletDuroc) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletHampshire) {
                EntityPigletHampshire sow = (EntityPigletHampshire) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletYorkshire) {
                EntityPigletYorkshire sow = (EntityPigletYorkshire) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletLargeBlack) {
                EntityPigletLargeBlack sow = (EntityPigletLargeBlack) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityPigletLargeWhite) {
                EntityPigletLargeWhite sow = (EntityPigletLargeWhite) this.temptedEntity;
                if (sow.getWatered())
                    return false;
            }

            Random rand = new Random();

            Biome biomegenbase = this.temptedEntity.world
                    .getBiome(new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ));

            BlockPos currentpos = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ);
            BlockPos currentposlower = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY - 1, this.temptedEntity.posZ);
            Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();
            Block poschk2 = this.temptedEntity.world.getBlockState(currentposlower).getBlock();

            if (poschk == BlockHandler.blockTrough) {
                TileEntityTrough te = (TileEntityTrough) this.temptedEntity.world.getTileEntity(currentpos);
                if (te != null && te.fluidHandler.getFluid() != null && te.fluidHandler.getFluid().getFluid() == FluidRegistry.WATER) {

                    te.fluidHandler.drain(335, true);

                    if (this.temptedEntity instanceof EntitySowOldSpot) {
                        EntitySowOldSpot ech = (EntitySowOldSpot) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntitySowDuroc) {
                        EntitySowDuroc ech = (EntitySowDuroc) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntitySowHampshire) {
                        EntitySowHampshire ech = (EntitySowHampshire) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntitySowYorkshire) {
                        EntitySowYorkshire ech = (EntitySowYorkshire) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntitySowLargeBlack) {
                        EntitySowLargeBlack ech = (EntitySowLargeBlack) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntitySowLargeWhite) {
                        EntitySowLargeWhite ech = (EntitySowLargeWhite) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHogOldSpot) {
                        EntityHogOldSpot ech = (EntityHogOldSpot) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHogDuroc) {
                        EntityHogDuroc ech = (EntityHogDuroc) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHogHampshire) {
                        EntityHogHampshire ech = (EntityHogHampshire) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHogYorkshire) {
                        EntityHogYorkshire ech = (EntityHogYorkshire) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHogLargeBlack) {
                        EntityHogLargeBlack ech = (EntityHogLargeBlack) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityHogLargeWhite) {
                        EntityHogLargeWhite ech = (EntityHogLargeWhite) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityPigletOldSpot) {
                        EntityPigletOldSpot ech = (EntityPigletOldSpot) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityPigletDuroc) {
                        EntityPigletDuroc ech = (EntityPigletDuroc) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityPigletHampshire) {
                        EntityPigletHampshire ech = (EntityPigletHampshire) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityPigletYorkshire) {
                        EntityPigletYorkshire ech = (EntityPigletYorkshire) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityPigletLargeBlack) {
                        EntityPigletLargeBlack ech = (EntityPigletLargeBlack) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }
                    else if (this.temptedEntity instanceof EntityPigletLargeWhite) {
                        EntityPigletLargeWhite ech = (EntityPigletLargeWhite) this.temptedEntity;
                        ech.entityAIEatGrass.startExecuting();
                        ech.setWatered(true);
                    }

                    return false;

                }
            }
            else if (poschk2 == Blocks.WATER && !BiomeDictionary.hasType(biomegenbase, Type.OCEAN)
                    && !BiomeDictionary.hasType(biomegenbase, Type.BEACH)) {

                if (this.temptedEntity instanceof EntitySowOldSpot) {
                    EntitySowOldSpot ech = (EntitySowOldSpot) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntitySowDuroc) {
                    EntitySowDuroc ech = (EntitySowDuroc) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntitySowHampshire) {
                    EntitySowHampshire ech = (EntitySowHampshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntitySowYorkshire) {
                    EntitySowYorkshire ech = (EntitySowYorkshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntitySowLargeBlack) {
                    EntitySowLargeBlack ech = (EntitySowLargeBlack) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntitySowLargeWhite) {
                    EntitySowLargeWhite ech = (EntitySowLargeWhite) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityHogOldSpot) {
                    EntityHogOldSpot ech = (EntityHogOldSpot) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityHogDuroc) {
                    EntityHogDuroc ech = (EntityHogDuroc) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityHogHampshire) {
                    EntityHogHampshire ech = (EntityHogHampshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityHogYorkshire) {
                    EntityHogYorkshire ech = (EntityHogYorkshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityHogLargeBlack) {
                    EntityHogLargeBlack ech = (EntityHogLargeBlack) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityHogLargeWhite) {
                    EntityHogLargeWhite ech = (EntityHogLargeWhite) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityPigletOldSpot) {
                    EntityPigletOldSpot ech = (EntityPigletOldSpot) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityPigletDuroc) {
                    EntityPigletDuroc ech = (EntityPigletDuroc) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityPigletHampshire) {
                    EntityPigletHampshire ech = (EntityPigletHampshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityPigletYorkshire) {
                    EntityPigletYorkshire ech = (EntityPigletYorkshire) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityPigletLargeBlack) {
                    EntityPigletLargeBlack ech = (EntityPigletLargeBlack) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }
                else if (this.temptedEntity instanceof EntityPigletLargeWhite) {
                    EntityPigletLargeWhite ech = (EntityPigletLargeWhite) this.temptedEntity;
                    ech.entityAIEatGrass.startExecuting();
                    ech.setWatered(true);
                }

                if (this.temptedEntity.world.getGameRules().getBoolean("mobGriefing"))
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
                            if (rand.nextInt(50) == 0) {
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
            boolean spcFlag = false;
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
                                        if (waterBlockchk == BlockHandler.blockTrough) {
                                            spcFlag = true;
                                            i = i + 1;
                                        }
                                    }

                                    if (this.temptedEntity.posZ < waterPos.getZ()) {
                                        BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
                                        Block waterBlockchk = this.temptedEntity.world.getBlockState(waterPoschk).getBlock();
                                        if (waterBlockchk == BlockHandler.blockTrough) {
                                            spcFlag = true;
                                            k = k + 1;
                                        }
                                    }

                                    waterPos = new BlockPos(x + i, y + j, z + k);

                                    if (spcFlag) {
                                        i = 10;
                                        j = 3;
                                        k = 10;
                                    }

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
                                    if (waterBlockchk == Blocks.WATER) {
                                        spcFlag = true;
                                        i = i + 1;
                                    }
                                }

                                if (this.temptedEntity.posZ < waterPos.getZ()) {
                                    BlockPos waterPoschk = new BlockPos(x + i, y + j, z + k + 1);
                                    Block waterBlockchk = this.temptedEntity.world.getBlockState(waterPoschk).getBlock();
                                    if (waterBlockchk == Blocks.WATER) {
                                        spcFlag = true;
                                        k = k + 1;
                                    }
                                }

                                waterPos = new BlockPos(x + i, y + j, z + k);

                                if (spcFlag) {
                                    i = 10;
                                    j = 3;
                                    k = 10;
                                }

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
                    if (this.temptedEntity instanceof EntityPigletYorkshire || this.temptedEntity instanceof EntityPigletDuroc
                            || this.temptedEntity instanceof EntityPigletHampshire || this.temptedEntity instanceof EntityPigletLargeBlack
                            || this.temptedEntity instanceof EntityPigletLargeWhite || this.temptedEntity instanceof EntityPigletOldSpot) {
                        if (this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false)
                            this.resetTask();
                        else
                            this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed);
                    }
                    else if (this.temptedEntity.getNavigator().tryMoveToXYZ(waterPos.getX(), waterPos.getY(), waterPos.getZ(), this.speed) == false)
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