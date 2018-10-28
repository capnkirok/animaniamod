package com.animania.common.entities.generic.ai;

import com.animania.common.entities.interfaces.ISleeping;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class GenericAIAvoidWater extends EntityAIBase
{
	private final EntityCreature idleEntity;
	private double lookX;
	private double lookZ;
	private int idleTime;
	private int delayCounter = 0;

	public GenericAIAvoidWater(EntityCreature entitylivingIn)
	{
		this.idleEntity = entitylivingIn;
		this.setMutexBits(3);
	}

	public boolean shouldExecute()
	{
		delayCounter++;
		
		if (delayCounter > AnimaniaConfig.gameRules.ticksBetweenAIFirings) {
			
			
			if (idleEntity instanceof ISleeping)
			{
				if (!idleEntity.world.isDaytime() || ((ISleeping) idleEntity).getSleeping())
				{
					this.delayCounter = 0;
					return false;
				}
			}
		
			BlockPos currentpos1 = new BlockPos(idleEntity.posX, idleEntity.posY, idleEntity.posZ);
			BlockPos currentpos2 = new BlockPos(idleEntity.posX, idleEntity.posY - 1, idleEntity.posZ);
			Block poschk1 = idleEntity.world.getBlockState(currentpos1).getBlock();
			Block poschk2 = idleEntity.world.getBlockState(currentpos2).getBlock();
			
			if((poschk1 == Blocks.WATER || poschk2 == Blocks.WATER) && !idleEntity.hasPath()) {
				
				Vec3d vec3d = RandomPositionGenerator.findRandomTarget(this.idleEntity, 20, 8);

				if (vec3d != null) {
					this.idleEntity.getNavigator().tryMoveToXYZ(vec3d.x, vec3d.y, vec3d.z, 1.0);
					this.idleEntity.getLookHelper().onUpdateLook();
					this.idleEntity.getLookHelper().setLookPosition(vec3d.x, vec3d.y, vec3d.z, 0.0F, 0.0F);
					delayCounter = 0;
					return false;
				}
			}
			delayCounter = 0;
		}
		return false;
	}

	public boolean continueExecuting()
	{
		return false;
	}

	public void startExecuting()
	{
		//Do nothing
	}

	public void updateTask()
	{
		//Do Nothing
	}
}