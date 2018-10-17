package com.animania.common.entities.generic.ai;

import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.interfaces.IFoodEating;
import com.animania.common.entities.interfaces.ISleeping;
import com.animania.common.entities.pigs.EntityAnimaniaPig;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.ItemHelper;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class GenericAIEatGrass extends EntityAIBase
{
	private static final Predicate<IBlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.TALLGRASS).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
	private final EntityCreature grassEaterEntity;
	private final World entityWorld;
	public int eatingGrassTimer;
	public boolean eatsGrass;
	
	public GenericAIEatGrass(EntityCreature grassEaterEntityIn, boolean eatsGrass)
	{
		this.grassEaterEntity = grassEaterEntityIn;
		this.entityWorld = grassEaterEntityIn.world;
		this.eatsGrass = eatsGrass;
		this.setMutexBits(7);
	}
	
	public GenericAIEatGrass(EntityCreature grassEaterEntityIn)
	{
		this(grassEaterEntityIn, true);
	}
	

	@Override
	public boolean shouldExecute()
	{

		if (grassEaterEntity instanceof IFoodEating)
		{
			if (((IFoodEating)grassEaterEntity).getFed())
			{
				return false;
			}
		}

		if (grassEaterEntity instanceof ISleeping)
		{
			if (!this.grassEaterEntity.world.isDaytime() || ((ISleeping) this.grassEaterEntity).getSleeping())
			{
				return false;
			}
		}

		if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0)
			return false;
		else
		{
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
			return IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)) ? true : this.entityWorld.getBlockState(blockpos.down()).getBlock() != Blocks.WATER && this.entityWorld.getBlockState(blockpos.down()).getBlock() != Blocks.LAVA;
		}
	}

	@Override
	public void startExecuting()
	{
		this.eatingGrassTimer = 160;
		this.entityWorld.setEntityState(this.grassEaterEntity, (byte) 10);
		this.grassEaterEntity.getNavigator().clearPath();
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
			BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

			if (IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)) && eatsGrass)
			{

				if (AnimaniaConfig.gameRules.plantsRemovedAfterEating)
				{
					this.entityWorld.destroyBlock(blockpos, false);
				}

				this.grassEaterEntity.eatGrassBonus();

				if (grassEaterEntity instanceof IFoodEating)
				{
					this.startExecuting();
					((IFoodEating) grassEaterEntity).setFed(true);
				}
			}
			else if(eatsGrass)
			{
				BlockPos blockpos1 = blockpos.down();
				Block blockBelow = this.entityWorld.getBlockState(blockpos1).getBlock();
				
				
				if (blockBelow == Blocks.GRASS || ((grassEaterEntity instanceof EntityAnimaniaCow && ((EntityAnimaniaCow)grassEaterEntity).cowType == CowType.MOOSHROOM) ? blockBelow == Blocks.MYCELIUM : false))
				{

					this.entityWorld.playEvent(2001, blockpos1, Block.getIdFromBlock(blockBelow));

					if (AnimaniaConfig.gameRules.plantsRemovedAfterEating)
					{
						this.entityWorld.setBlockState(blockpos1, Blocks.DIRT.getDefaultState(), 2);
					}

					if (grassEaterEntity instanceof IFoodEating)
					{
						this.startExecuting();
						((IFoodEating) grassEaterEntity).setFed(true);
					}

					this.grassEaterEntity.eatGrassBonus();
				}
			}
		}
	}
}
