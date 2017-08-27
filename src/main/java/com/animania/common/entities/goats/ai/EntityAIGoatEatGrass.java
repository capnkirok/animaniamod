package com.animania.common.entities.goats.ai;

import com.animania.common.entities.horses.EntityAnimaniaHorse;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAIGoatEatGrass extends EntityAIBase
{
	private static final Predicate<IBlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.TALLGRASS).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
	private final EntityLiving grassEaterEntity;
	private final World entityWorld;
	int eatingGrassTimer;

	public EntityAIGoatEatGrass(EntityLiving grassEaterEntityIn)
	{
		this.grassEaterEntity = grassEaterEntityIn;
		this.entityWorld = grassEaterEntityIn.world;
		this.setMutexBits(7);
	}

	public boolean shouldExecute()
	{
		if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0)
		{
			return false;
		}
		else
		{
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
			return IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)) ? true : (this.entityWorld.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS);
		}
	}

	public void startExecuting()
	{
		this.eatingGrassTimer = 100;
		this.entityWorld.setEntityState(this.grassEaterEntity, (byte)10);
		this.grassEaterEntity.getNavigator().clearPathEntity();
	}

	public void resetTask()
	{
		this.eatingGrassTimer = 0;
	}

	public boolean continueExecuting()
	{

		return this.eatingGrassTimer > 0;
	}

	public int getEatingGrassTimer()
	{
		return this.eatingGrassTimer;
	}

	public void updateTask()
	{
		this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

		if (this.eatingGrassTimer == 4)
		{
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

			if (IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)))
			{

				this.entityWorld.destroyBlock(blockpos, false);

				if (grassEaterEntity instanceof EntityAnimaniaHorse) {
					EntityAnimaniaHorse ech = (EntityAnimaniaHorse)grassEaterEntity;
					ech.entityAIEatGrass.startExecuting();
					ech.setFed(true);
				} 
				this.grassEaterEntity.eatGrassBonus();
			}

			else

			{
				BlockPos blockpos1 = blockpos.down();

				if (this.entityWorld.getBlockState(blockpos1).getBlock() == Blocks.GRASS)
				{

					this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(Blocks.GRASS));
					this.entityWorld.setBlockState(blockpos1, Blocks.DIRT.getDefaultState(), 2);

					if (grassEaterEntity instanceof EntityAnimaniaHorse) {
						EntityAnimaniaHorse ech = (EntityAnimaniaHorse)grassEaterEntity;
						ech.entityAIEatGrass.startExecuting();
						ech.setFed(true);
					} 
					this.grassEaterEntity.eatGrassBonus();
				}
			}
		}
	}
}