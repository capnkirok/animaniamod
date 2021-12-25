package com.animania.addons.extra.common.entity.rodents.ai;

import com.animania.addons.extra.common.entity.rodents.EntityFerretBase;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehogBase;
import com.animania.addons.extra.common.entity.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.handler.BlockHandler;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;

public class RodentEatGoal extends Goal
{
	private static final Predicate<BlockState> IS_TALL_GRASS = BlockStatePredicate.forBlock(Blocks.TALLGRASS).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
	private final LivingEntity grassEaterEntity;
	private final Level entityLevel;
	int eatingGrassTimer;

	public RodentEatGoal(LivingEntity grassEaterEntityIn)
	{
		this.grassEaterEntity = grassEaterEntityIn;
		this.entityLevel = grassEaterEntityIn.level;
		this.setMutexBits(7);
	}

	@Override
	public boolean shouldExecute()
	{

		if ((this.grassEaterEntity instanceof EntityAnimaniaRabbit er) && (er.getFed() || er.getSleeping()))
		{
			return false;
		}

		if ((this.grassEaterEntity instanceof EntityFerretBase er) && (er.getFed() || er.getSleeping()))
		{
			return false;
		}

		if ((this.grassEaterEntity instanceof EntityHedgehogBase er) && (er.getFed() || er.getSleeping()))
		{
			return false;
		}

		if (this.grassEaterEntity.getRandom().nextInt(this.grassEaterEntity.isChild() ? 50 : 150) != 0)
			return false;
		else
		{
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.getX(), this.grassEaterEntity.getY(), this.grassEaterEntity.getZ());
			return RodentEatGoal.IS_TALL_GRASS.apply(this.entityLevel.getBlockState(blockpos)) ? true : this.entityLevel.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS || this.entityLevel.getBlockState(blockpos.down()).getBlock() == Blocks.DIRT;
		}
	}

	@Override
	public void startExecuting()
	{
		this.eatingGrassTimer = 80;
		this.entityLevel.broadcastEntityEvent(this.grassEaterEntity, (byte) 10);
		this.grassEaterEntity.getNavigation().stop();
	}

	@Override
	public void resetTask()
	{
		this.eatingGrassTimer = 0;
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return this.eatingGrassTimer > 0;
	}

	public int getEatingGrassTimer()
	{
		return this.eatingGrassTimer;
	}

	@Override
	public void updateTask()
	{
		this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

		if (this.eatingGrassTimer == 4)
		{
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.getX(), this.grassEaterEntity.getY(), this.grassEaterEntity.getZ());

			if (EntityAIRodentEat.IS_TALL_GRASS.apply(this.entityLevel.getBlockState(blockpos)))
			{

				this.entityLevel.destroyBlock(blockpos, false);

				this.grassEaterEntity.eatGrassBonus();
			}
			else
			{
				BlockPos blockpos1 = blockpos.down();
				Block chkblock = this.entityLevel.getBlockState(blockpos1).getBlock();

				Biome biomegenbase = this.entityLevel.getBiome(blockpos1);

				if (chkblock == Blocks.GRASS || chkblock == Blocks.DIRT || chkblock == BlockHandler.blockMud || chkblock == Blocks.MYCELIUM || chkblock == Blocks.SAND)
					if (chkblock == Blocks.WATER)
					{
						this.entityLevel.playEvent(2001, blockpos1, Block.getIdFromBlock(chkblock));
						this.entityLevel.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 2);

					}

				this.grassEaterEntity.eatGrassBonus();

			}
		}
	}
}