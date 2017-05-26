package com.animania.common.entities.chickens.ai;

import java.util.List;

import com.animania.common.entities.chickens.EntityHenLeghorn;
import com.animania.common.entities.chickens.EntityHenOrpington;
import com.animania.common.entities.chickens.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.EntityHenWyandotte;
import com.animania.common.handler.BlockHandler;
import com.animania.common.tileentities.TileEntityNest;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public class EntityAIFindNest extends EntityAIBase
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

    public EntityAIFindNest(EntityCreature temptedEntityIn, double speedIn) {
        this.temptedEntity = temptedEntityIn;
        this.speed = speedIn;
        this.setMutexBits(3);
        this.delayTemptCounter = 0;
    }

    @Override
    public boolean shouldExecute() {

        this.delayTemptCounter++;
        if (this.delayTemptCounter > 10) {

            if (!this.temptedEntity.world.isDaytime())
                return false;

            if (this.temptedEntity instanceof EntityHenLeghorn) {
                EntityHenLeghorn entity = (EntityHenLeghorn) this.temptedEntity;
                if (!entity.getWatered() || !entity.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHenOrpington) {
                EntityHenOrpington entity = (EntityHenOrpington) this.temptedEntity;
                if (!entity.getWatered() || !entity.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHenPlymouthRock) {
                EntityHenPlymouthRock entity = (EntityHenPlymouthRock) this.temptedEntity;
                if (!entity.getWatered() || !entity.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHenRhodeIslandRed) {
                EntityHenRhodeIslandRed entity = (EntityHenRhodeIslandRed) this.temptedEntity;
                if (!entity.getWatered() || !entity.getFed())
                    return false;
            }
            else if (this.temptedEntity instanceof EntityHenWyandotte) {
                EntityHenWyandotte entity = (EntityHenWyandotte) this.temptedEntity;
                if (!entity.getWatered() || !entity.getFed())
                    return false;
            }

            BlockPos currentpos = new BlockPos(this.temptedEntity.posX, this.temptedEntity.posY, this.temptedEntity.posZ);
            Block poschk = this.temptedEntity.world.getBlockState(currentpos).getBlock();

            if (poschk == BlockHandler.blockNest) {
                TileEntityNest te = (TileEntityNest) this.temptedEntity.world.getTileEntity(currentpos);

                if (te.getNestType() == 3 || te.getNestType() == 6 || te.getNestType() == 9 || te.getNestType() == 12 || te.getNestType() == 15)
                    return false;

                if (this.temptedEntity instanceof EntityHenLeghorn) {
                    EntityHenLeghorn entity = (EntityHenLeghorn) this.temptedEntity;

                    if (te != null && te.getNestType() == 0 && !entity.getLaid()) {
                        te.setType(1);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                    else if (te != null && te.getNestType() == 1 && !entity.getLaid()) {
                        te.setType(2);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                    else if (te != null && te.getNestType() == 2 && !entity.getLaid()) {
                        te.setType(3);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                }
                else if (this.temptedEntity instanceof EntityHenOrpington) {
                    EntityHenOrpington entity = (EntityHenOrpington) this.temptedEntity;
                    if (te != null && te.getNestType() == 0 && !entity.getLaid()) {
                        te.setType(4);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                    else if (te != null && te.getNestType() == 4 && !entity.getLaid()) {
                        te.setType(5);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                    else if (te != null && te.getNestType() == 5 && !entity.getLaid()) {
                        te.setType(6);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }

                }
                else if (this.temptedEntity instanceof EntityHenPlymouthRock) {
                    EntityHenPlymouthRock entity = (EntityHenPlymouthRock) this.temptedEntity;
                    if (te != null && te.getNestType() == 0 && !entity.getLaid()) {
                        te.setType(7);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                    else if (te != null && te.getNestType() == 7 && !entity.getLaid()) {
                        te.setType(8);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                    else if (te != null && te.getNestType() == 8 && !entity.getLaid()) {
                        te.setType(9);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                }
                else if (this.temptedEntity instanceof EntityHenRhodeIslandRed) {
                    EntityHenRhodeIslandRed entity = (EntityHenRhodeIslandRed) this.temptedEntity;
                    if (te != null && te.getNestType() == 0 && !entity.getLaid()) {
                        te.setType(10);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                    else if (te != null && te.getNestType() == 10 && !entity.getLaid()) {
                        te.setType(11);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                    else if (te != null && te.getNestType() == 11 && !entity.getLaid()) {
                        te.setType(12);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                }
                else if (this.temptedEntity instanceof EntityHenWyandotte) {
                    EntityHenWyandotte entity = (EntityHenWyandotte) this.temptedEntity;
                    if (te != null && te.getNestType() == 0 && !entity.getLaid()) {
                        te.setType(13);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                    else if (te != null && te.getNestType() == 13 && !entity.getLaid()) {
                        te.setType(14);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                    else if (te != null && te.getNestType() == 14 && !entity.getLaid()) {
                        te.setType(15);
                        te.markDirty();
                        entity.setLaid(true);
                        this.temptedEntity.world.notifyBlockUpdate(currentpos, poschk.getDefaultState(), poschk.getDefaultState(), 1);
                        this.temptedEntity.world.updateComparatorOutputLevel(currentpos, poschk);
                        this.resetTask();
                    }
                }

                return false;
            }

            double x = this.temptedEntity.posX;
            double y = this.temptedEntity.posY;
            double z = this.temptedEntity.posZ;

            boolean nestFound = false;

            BlockPos pos = new BlockPos(x, y, z);

            for (int i = -10; i < 10; i++)
                for (int j = -3; j < 3; j++)
                    for (int k = -10; k < 10; k++) {

                        pos = new BlockPos(x + i, y + j, z + k);
                        Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

                        if (blockchk == BlockHandler.blockNest) {

                            TileEntityNest te = (TileEntityNest) this.temptedEntity.world.getTileEntity(pos);
                            int nestType = te.getNestType();

                            if (nestType == 20) {
                                // do nothing
                            }
                            else if (this.temptedEntity instanceof EntityHenLeghorn
                                    && (nestType == 0 || nestType == 1 || nestType == 2 || nestType == 3)) {
                                nestFound = true;
                                return true;
                            }
                            else if (this.temptedEntity instanceof EntityHenOrpington
                                    && (nestType == 0 || nestType == 4 || nestType == 5 || nestType == 6)) {
                                nestFound = true;
                                return true;
                            }
                            else if (this.temptedEntity instanceof EntityHenPlymouthRock
                                    && (nestType == 0 || nestType == 7 || nestType == 8 || nestType == 9)) {
                                nestFound = true;
                                return true;
                            }
                            else if (this.temptedEntity instanceof EntityHenRhodeIslandRed
                                    && (nestType == 0 || nestType == 10 || nestType == 11 || nestType == 12)) {
                                nestFound = true;
                                return true;
                            }
                            else if (this.temptedEntity instanceof EntityHenWyandotte
                                    && (nestType == 0 || nestType == 13 || nestType == 14 || nestType == 15)) {
                                nestFound = true;
                                return true;
                            }
                        }

                    }

            if (!nestFound)
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
        if (poschk != BlockHandler.blockNest) {

            boolean nestFound = false;
            int loc = 24;
            int newloc = 24;
            BlockPos pos = new BlockPos(x, y, z);
            BlockPos nestPos = new BlockPos(x, y, z);

            for (int i = -10; i < 10; i++)
                for (int j = -3; j < 3; j++)
                    for (int k = -10; k < 10; k++) {

                        pos = new BlockPos(x + i, y + j, z + k);
                        Block blockchk = this.temptedEntity.world.getBlockState(pos).getBlock();

                        if (blockchk == BlockHandler.blockNest && !this.temptedEntity.hasPath()) {

                            TileEntityNest te = (TileEntityNest) this.temptedEntity.world.getTileEntity(pos);
                            int nestType = te.getNestType();
                            if (nestType == 20) { // (nestType == 3 || nestType
                                                  // == 6 || nestType == 9 ||
                                                  // nestType == 12 ||
                                                  // nestType == 15) {
                                // do nothing
                            }
                            else if (this.temptedEntity instanceof EntityHenLeghorn
                                    && (nestType == 0 || nestType == 1 || nestType == 2 || nestType == 3))
                                nestFound = true;
                            else if (this.temptedEntity instanceof EntityHenOrpington
                                    && (nestType == 0 || nestType == 4 || nestType == 5 || nestType == 6))
                                nestFound = true;
                            else if (this.temptedEntity instanceof EntityHenPlymouthRock
                                    && (nestType == 0 || nestType == 7 || nestType == 8 | nestType == 9))
                                nestFound = true;
                            else if (this.temptedEntity instanceof EntityHenRhodeIslandRed
                                    && (nestType == 0 || nestType == 10 || nestType == 11 || nestType == 12))
                                nestFound = true;
                            else if (this.temptedEntity instanceof EntityHenWyandotte
                                    && (nestType == 0 || nestType == 13 || nestType == 14 || nestType == 15))
                                nestFound = true;

                            if (nestFound == true) {

                                newloc = Math.abs(i) + Math.abs(j) + Math.abs(k);

                                if (newloc < loc) {

                                    loc = newloc;

                                    if (this.temptedEntity.posX < nestPos.getX()) {
                                        BlockPos nestPoschk = new BlockPos(x + i + 1, y + j, z + k);
                                        Block nestBlockchk = this.temptedEntity.world.getBlockState(nestPoschk).getBlock();
                                        if (nestBlockchk == BlockHandler.blockNest)
                                            i = i + 1;
                                    }

                                    if (this.temptedEntity.posZ < nestPos.getZ()) {
                                        BlockPos nestPoschk = new BlockPos(x + i, y + j, z + k + 1);
                                        Block nestBlockchk = this.temptedEntity.world.getBlockState(nestPoschk).getBlock();
                                        if (nestBlockchk == BlockHandler.blockNest)
                                            k = k + 1;
                                    }

                                    nestPos = new BlockPos(x + i, y + j, z + k);

                                }

                            }

                        }
                    }

            if (nestFound) {

                Block nestBlockchk = this.temptedEntity.world.getBlockState(nestPos).getBlock();
                Biome biomegenbase = this.temptedEntity.world.getBiome(nestPos);

                List<Entity> nestClear = this.temptedEntity.world.getEntitiesWithinAABBExcludingEntity(this.temptedEntity,
                        this.temptedEntity.getEntityBoundingBox().expandXyz(1));

                if (nestBlockchk == BlockHandler.blockNest && nestClear.isEmpty())
                    this.temptedEntity.getNavigator().tryMoveToXYZ(nestPos.getX() + .50, nestPos.getY(), nestPos.getZ() + .50, this.speed);
                else {
                    // this.temptedEntity.getNavigator().tryMoveToXYZ(nestPos.getX(),
                    // nestPos.getY(), nestPos.getZ(), this.speed);

                }

            }
        }

    }

    public boolean isRunning() {
        return this.isRunning;
    }
}