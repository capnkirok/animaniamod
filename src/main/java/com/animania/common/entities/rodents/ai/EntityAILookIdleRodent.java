package com.animania.common.entities.rodents.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAILookIdleRodent extends EntityAIBase
{
	private final EntityAnimal idleEntity;
	private double             lookX;
	private double             lookZ;
	private int                idleTime;

	public EntityAILookIdleRodent(EntityAnimal entitylivingIn) {
		this.idleEntity = entitylivingIn;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		return this.idleEntity.getRNG().nextFloat() < 0.02F && !this.idleEntity.isRiding();
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.idleTime >= 0;
	}

	@Override
	public void startExecuting() {
		double d0 = Math.PI * 2D * this.idleEntity.getRNG().nextDouble();
		this.lookX = Math.cos(d0);
		this.lookZ = Math.sin(d0);
		this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
	}

	@Override
	public void updateTask() {
		--this.idleTime;
		this.idleEntity.getLookHelper().setLookPosition(this.idleEntity.posX + this.lookX, this.idleEntity.posY + this.idleEntity.getEyeHeight(),
				this.idleEntity.posZ + this.lookZ, this.idleEntity.getHorizontalFaceSpeed(), this.idleEntity.getVerticalFaceSpeed());
	}
}