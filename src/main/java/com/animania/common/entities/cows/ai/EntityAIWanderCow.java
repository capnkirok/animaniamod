package com.animania.common.entities.cows.ai;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAIWanderCow extends EntityAIBase
{
    private final EntityCreature entity;
    private double               xPosition;
    private double               yPosition;
    private double               zPosition;
    private final double         speed;
    private int                  executionChance;
    private boolean              mustUpdate;

    public EntityAIWanderCow(EntityCreature creatureIn, double speedIn) {
        this(creatureIn, speedIn, 120);
    }

    public EntityAIWanderCow(EntityCreature creatureIn, double speedIn, int chance) {
        this.entity = creatureIn;
        this.speed = speedIn;
        this.executionChance = chance;
        this.setMutexBits(1);
    }
  
    @Override
    public boolean shouldExecute() {
        if (!this.mustUpdate)
            if (this.entity.getRNG().nextInt(this.executionChance) != 0)
                return false;

        Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.entity, 8, 6);

        if (vec3d == null)
            return false;
        else {
            this.xPosition = vec3d.xCoord;
            this.yPosition = vec3d.yCoord;
            this.zPosition = vec3d.zCoord;
            this.mustUpdate = false;
            return true;
        }
    }

    @Override
    public boolean continueExecuting() {
        return !this.entity.getNavigator().noPath();
    }

    @Override
    public void startExecuting() {

        double x = this.entity.posX;
        double y = this.entity.posY;
        double z = this.entity.posZ;

        BlockPos pos = new BlockPos(x, y, z);
        Block blockchk = this.entity.world.getBlockState(pos).getBlock();
        Random rand = new Random();

        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);

    }

    public void makeUpdate() {
        this.mustUpdate = true;
    }

    public void setExecutionChance(int newchance) {
        this.executionChance = newchance;
    }
}