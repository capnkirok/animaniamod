package com.animania.common.entities.generic.ai;

import com.animania.common.handler.BlockHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;

public class GenericAISwimmingSmallCreatures extends SwimmingGoal
{
    private final LivingEntity theEntity;

    public GenericAISwimmingSmallCreatures(LivingEntity LivingEntityIn) {
        super(LivingEntityIn);
        this.theEntity = LivingEntityIn;
    }

    @Override
    public boolean shouldExecute() {

    	BlockPos poschk = new BlockPos(this.theEntity.getX() + this.theEntity.motionX / 1.5, this.theEntity.getY() + .1F,
                this.theEntity.getZ() + this.theEntity.motionZ / 1.5);

        Block blockchk = this.theentity.level.getBlockState(poschk).getBlock();

        return blockchk == BlockHandler.blockMud || super.shouldExecute();

    }
}