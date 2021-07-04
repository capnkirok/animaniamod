package com.animania.common.entities.generic.ai;

import com.animania.common.handler.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.util.math.BlockPos;

public class GenericAISwimmingSmallCreatures extends EntityAISwimming
{
    private final LivingEntity theEntity;

    public GenericAISwimmingSmallCreatures(LivingEntity LivingEntityIn) {
        super(LivingEntityIn);
        this.theEntity = LivingEntityIn;
    }

    @Override
    public boolean shouldExecute() {

    	BlockPos poschk = new BlockPos(this.theEntity.posX + this.theEntity.motionX / 1.5, this.theEntity.posY + .1F,
                this.theEntity.posZ + this.theEntity.motionZ / 1.5);

        Block blockchk = this.theEntity.world.getBlockState(poschk).getBlock();

        return blockchk == BlockHandler.blockMud || super.shouldExecute();

    }
}