package com.animania.common.entities.generic.ai;

import com.animania.common.entities.interfaces.ISleeping;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;

public class GenericAISwim extends EntityAIBase
{
    private final EntityLiving entity;

    public GenericAISwim(EntityLiving entityIn) {
    	this.entity = entityIn;
        this.setMutexBits(4);

        if (entityIn.getNavigator() instanceof PathNavigateGround)
        {
            ((PathNavigateGround)entityIn.getNavigator()).setCanSwim(true);
        }
        else if (entityIn.getNavigator() instanceof PathNavigateFlying)
        {
            ((PathNavigateFlying)entityIn.getNavigator()).setCanFloat(true);
        }
     
    }

    @Override
    public boolean shouldExecute() {
    	
    	return this.entity.isInWater() || this.entity.isInLava();
    }

    @Override
    public void updateTask() {
        if (this.entity.getRNG().nextFloat() < 0.8F) {

        	BlockPos poschk = new BlockPos(this.entity.posX + this.entity.motionX/1.5, this.entity.posY+.1F, this.entity.posZ + this.entity.motionZ/1.5);

			Block blockchk = this.entity.world.getBlockState(poschk).getBlock();

			if (this.entity.isPushedByWater()) {
				this.entity.move(MoverType.SELF, this.entity.motionX, this.entity.motionY + .3F, this.entity.motionZ);	
			} else if (blockchk != Blocks.WATER) {
				this.entity.move(MoverType.SELF, this.entity.motionX, this.entity.motionY + .3F, this.entity.motionZ);	
				this.entity.getJumpHelper().setJumping();
			} else {
				this.entity.move(MoverType.SELF, this.entity.motionX, this.entity.motionX + .3F, this.entity.motionZ);	
			}
        }
    }
}
