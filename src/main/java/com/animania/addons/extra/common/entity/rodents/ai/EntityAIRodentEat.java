package com.animania.addons.extra.common.entity.rodents.ai;

import com.animania.addons.extra.common.entity.rodents.EntityFerretBase;
import com.animania.addons.extra.common.entity.rodents.EntityHedgehogBase;
import com.animania.addons.extra.common.entity.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.common.handler.BlockHandler;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class RodentEatGoal extends Goal
{
	private static final Predicate<BlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.TALLGRASS).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
	private final LivingEntity                  grassEaterEntity;
	private final World                         entityWorld;
	int                                         eatingGrassTimer;

	public RodentEatGoal(LivingEntity grassEaterEntityIn) {
		this.grassEaterEntity = grassEaterEntityIn;
		this.entityWorld = grassEaterEntityIn.level;
		this.setMutexBits(7);
	}

	@Override
	public boolean shouldExecute() {
		
		if (this.grassEaterEntity instanceof EntityAnimaniaRabbit) {
			EntityAnimaniaRabbit er = (EntityAnimaniaRabbit) this.grassEaterEntity;
			if (er.getFed()) {
				return false;
			}
			if (er.getSleeping()) {
				return false;
			}
		}
		
		if (this.grassEaterEntity instanceof EntityFerretBase) {
			EntityFerretBase er = (EntityFerretBase) this.grassEaterEntity;
			if (er.getFed()) {
				return false;
			}
			if (er.getSleeping()) {
				return false;
			}
		}
		
		if (this.grassEaterEntity instanceof EntityHedgehogBase) {
			EntityHedgehogBase er = (EntityHedgehogBase) this.grassEaterEntity;
			if (er.getFed()) {
				return false;
			}
			if (er.getSleeping()) {
				return false;
			}
		}
		
		if (this.grassEaterEntity.getRandom().nextInt(this.grassEaterEntity.isChild() ? 50 : 150) != 0)
			return false;
		else {
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.getX(), this.grassEaterEntity.getY(), this.grassEaterEntity.getZ());
			return RodentEatGoal.IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)) ? true
					: this.entityWorld.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS
					|| this.entityWorld.getBlockState(blockpos.down()).getBlock() == Blocks.DIRT;
		}
	}

	@Override
	public void startExecuting() {
		this.eatingGrassTimer = 80;
		this.entityWorld.broadcastEntityEvent(this.grassEaterEntity, (byte) 10);
		this.grassEaterEntity.getNavigation().stop();
	}

	@Override
	public void resetTask() {
		this.eatingGrassTimer = 0;
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.eatingGrassTimer > 0;
	}

	public int getEatingGrassTimer() {
		return this.eatingGrassTimer;
	}

	@Override
	public void updateTask() {
		this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

		if (this.eatingGrassTimer == 4) {
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.getX(), this.grassEaterEntity.getY(), this.grassEaterEntity.getZ());

			if (EntityAIRodentEat.IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos))) {

				this.entityWorld.destroyBlock(blockpos, false);

				this.grassEaterEntity.eatGrassBonus();
			}
			else {
				BlockPos blockpos1 = blockpos.down();
				Block chkblock = this.entityWorld.getBlockState(blockpos1).getBlock();

				Biome biomegenbase = this.entityWorld.getBiome(blockpos1);

				if (chkblock == Blocks.GRASS || chkblock == Blocks.DIRT || chkblock == BlockHandler.blockMud || chkblock == Blocks.MYCELIUM || chkblock == Blocks.SAND)
					if (chkblock == Blocks.WATER) {
						this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(chkblock));
						this.entityWorld.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 2);

					}

				this.grassEaterEntity.eatGrassBonus();

			}
		}
	}
}