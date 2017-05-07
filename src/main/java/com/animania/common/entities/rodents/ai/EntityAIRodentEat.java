package com.animania.common.entities.rodents.ai;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class EntityAIRodentEat extends EntityAIBase {
	private static final Predicate<IBlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.TALLGRASS)
			.where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
	/** The entity owner of this AITask */
	private final EntityLiving grassEaterEntity;
	/** The world the grass eater entity is eating from */
	private final World entityWorld;
	/** Number of ticks since the entity started to eat grass */
	int eatingGrassTimer;

	public EntityAIRodentEat(EntityLiving grassEaterEntityIn) {
		this.grassEaterEntity = grassEaterEntityIn;
		this.entityWorld = grassEaterEntityIn.world;
		this.setMutexBits(7);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute() {
		if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0) {
			return false;
		} else {
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY,
					this.grassEaterEntity.posZ);
			return IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)) ? true
					: (this.entityWorld.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS
							|| this.entityWorld.getBlockState(blockpos.down()).getBlock() == Blocks.DIRT);
		}
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	@Override
	public void startExecuting() {
		this.eatingGrassTimer = 80;
		this.entityWorld.setEntityState(this.grassEaterEntity, (byte) 10);
		this.grassEaterEntity.getNavigator().clearPathEntity();
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask() {
		this.eatingGrassTimer = 0;
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting() {
		return this.eatingGrassTimer > 0;
	}

	/**
	 * Number of ticks since the entity started to eat grass
	 */
	public int getEatingGrassTimer() {
		return this.eatingGrassTimer;
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask() {
		this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

		if (this.eatingGrassTimer == 4) {
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY,
					this.grassEaterEntity.posZ);

			if (IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos))) {
				if (this.entityWorld.getGameRules().getBoolean("mobGriefing")) {
					this.entityWorld.destroyBlock(blockpos, false);
				}

				this.grassEaterEntity.eatGrassBonus();
			} else {
				BlockPos blockpos1 = blockpos.down();
				Block chkblock = this.entityWorld.getBlockState(blockpos1).getBlock();

				Biome biomegenbase = this.entityWorld.getBiome(blockpos1);

				if (chkblock == Blocks.GRASS || chkblock == Blocks.DIRT || chkblock == BlockHandler.blockMud
						|| chkblock == Blocks.MYCELIUM || chkblock == Blocks.SAND) {
					if (this.entityWorld.getGameRules().getBoolean("mobGriefing")) {

						if (chkblock == Blocks.WATER) {
							this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(chkblock));
							this.entityWorld.setBlockState(blockpos1, Blocks.AIR.getDefaultState(), 2);

							// TODO remove truffles
						} else if (chkblock == Blocks.GRASS && biomegenbase.getBiomeName().contains("Forest")) {
							this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(chkblock));
							ItemStack bob2 = new ItemStack(ItemHandler.truffle, 1);
							EntityItem entityitem = new EntityItem(this.entityWorld, blockpos1.getX() + 0.5D,
									blockpos1.getY(), blockpos.getZ() + 0.5D, bob2);
							// entityitem.height = 1;
							this.entityWorld.spawnEntity(entityitem);
						}

						this.grassEaterEntity.eatGrassBonus();
					}
				}
			}
		}
	}
}