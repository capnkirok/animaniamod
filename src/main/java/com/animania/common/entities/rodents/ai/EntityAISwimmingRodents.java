package com.animania.common.entities.rodents.ai;

import com.animania.Animania;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;

public class EntityAISwimmingRodents extends EntityAIBase
{
	private final EntityLiving theEntity;

	public EntityAISwimmingRodents(EntityLiving entitylivingIn)
	{
		this.theEntity = entitylivingIn;
		this.setMutexBits(4);
		((PathNavigateGround)entitylivingIn.getNavigator()).setCanSwim(true);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{

		BlockPos poschk = new BlockPos(this.theEntity.posX + this.theEntity.motionX/1.5, this.theEntity.posY+.1F, this.theEntity.posZ + this.theEntity.motionZ/1.5);

		Block blockchk = this.theEntity.world.getBlockState(poschk).getBlock();

		boolean isInMud = false;
		if (blockchk == Animania.blockMud) {
			isInMud = true;
		}

		return this.theEntity.isInWater() || this.theEntity.isInLava() || isInMud;

	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask()
	{
		if (this.theEntity.getRNG().nextFloat() < 0.9F)
		{

			BlockPos poschk = new BlockPos(this.theEntity.posX + this.theEntity.motionX/1.5, this.theEntity.posY+.1F, this.theEntity.posZ + this.theEntity.motionZ/1.5);

			Block blockchk = this.theEntity.world.getBlockState(poschk).getBlock();

			if (blockchk != Blocks.WATER && blockchk != Animania.blockMud) {
				this.theEntity.setPositionAndUpdate(this.theEntity.posX + this.theEntity.motionX/2, this.theEntity.posY+.15F, this.theEntity.posZ + this.theEntity.motionZ/2);
			} else {
				if (blockchk == Animania.blockMud) {
					this.theEntity.setPositionAndUpdate(this.theEntity.posX + this.theEntity.motionX/2, this.theEntity.posY+.12F, this.theEntity.posZ + this.theEntity.motionZ/2);
				} else {
					this.theEntity.setPositionAndUpdate(this.theEntity.posX + this.theEntity.motionX/2, this.theEntity.posY+.6F, this.theEntity.posZ + this.theEntity.motionZ/2);
				}
			}
		}

	}
}
