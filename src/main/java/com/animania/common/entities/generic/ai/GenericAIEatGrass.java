package com.animania.common.entities.generic.ai;

import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.cows.EntityAnimaniaCow;
import com.animania.common.entities.interfaces.IFoodEating;
import com.animania.common.entities.interfaces.ISleeping;
import com.animania.config.AnimaniaConfig;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

public class GenericAIEatGrass<T extends EntityCreature & ISleeping & IFoodEating> extends GenericAISearchBlock
{

	private static final Predicate<IBlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.TALLGRASS).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
	private final T grassEaterEntity;
	private final World entityWorld;
	public int eatingGrassTimer;
	public boolean eatsGrass;
	private int timer;
	private boolean isEating = false;

	public GenericAIEatGrass(T grassEaterEntityIn, boolean eatsGrass)
	{
		super(grassEaterEntityIn, 1.0, 8, EnumFacing.UP);
		this.grassEaterEntity = grassEaterEntityIn;
		this.entityWorld = grassEaterEntityIn.world;
		this.eatsGrass = eatsGrass;
		this.setMutexBits(7);
	}

	public GenericAIEatGrass(T grassEaterEntityIn)
	{
		this(grassEaterEntityIn, true);
	}

	@Override
	public boolean shouldExecute()
	{
		if (++timer <= AnimaniaConfig.gameRules.ticksBetweenAIFirings)
			return false;
		if (grassEaterEntity.getSleeping() || grassEaterEntity.getFed())
		{
			timer = 0;
			return false;
		}

		if (this.grassEaterEntity.getRNG().nextInt(120) == 0)
		{
			timer = 0;
			return super.shouldExecute();
		}

		return false;
	}

	@Override
	public void startExecuting()
	{
		if (!this.destinationBlock.equals(this.NO_POS) && eatsGrass)
			super.startExecuting();
		else
		{
			this.eatingGrassTimer = 160;
			this.entityWorld.setEntityState(this.grassEaterEntity, (byte) 10);
			this.grassEaterEntity.getNavigator().clearPath();
		}
	}

	public int getEatingGrassTimer()
	{
		return this.eatingGrassTimer;
	}

	@Override
	public boolean shouldContinueExecuting()
	{
		return super.shouldContinueExecuting() || this.eatingGrassTimer > 0;
	}

	@Override
	public void resetTask()
	{
		super.resetTask();
		this.eatingGrassTimer = 0;
		this.isEating = false;
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		timer = 0;
		this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);

		if (this.isAtDestination() && shouldMoveTo(world, seekingBlockPos))
		{
			IBlockState state = world.getBlockState(seekingBlockPos);
			Block block = state.getBlock();

			if (!isEating)
			{
				this.eatingGrassTimer = 160;
				this.entityWorld.setEntityState(this.grassEaterEntity, (byte) 10);
				this.grassEaterEntity.getNavigator().clearPath();
				this.isEating = true;
			}

			if (eatsGrass)
			{
				if (this.eatingGrassTimer == 4)
				{
					this.entityWorld.playEvent(2001, this.seekingBlockPos, Block.getIdFromBlock(block));

					if (AnimaniaConfig.gameRules.plantsRemovedAfterEating)
					{

						String name = block.getRegistryName().toString();
						if (Loader.isModLoaded("desirepaths") && ((name.contains("desirepaths:grass_worn_") && (name.endsWith("1") || name.endsWith("2") || name.endsWith("3"))) || block instanceof BlockGrass) )
							this.entityWorld.setBlockState(this.seekingBlockPos, Block.getBlockFromName("desirepaths:grass_worn_6").getDefaultState());
						else
							this.entityWorld.setBlockState(this.seekingBlockPos, Blocks.DIRT.getDefaultState(), 2);
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

	@Override
	protected boolean shouldMoveTo(World worldIn, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (block instanceof BlockGrass || ((grassEaterEntity instanceof EntityAnimaniaCow && ((EntityAnimaniaCow) grassEaterEntity).cowType == CowType.MOOSHROOM) ? block == Blocks.MYCELIUM : false) || handleDesirePaths(block))
			return true;

		return false;
	}

	private boolean handleDesirePaths(Block block)
	{
		if (Loader.isModLoaded("desirepaths"))
		{
			String name = block.getRegistryName().toString();
			if (name.contains("desirepaths:grass_worn_") && (name.endsWith("1") || name.endsWith("2") || name.endsWith("3")))
				return true;
		}

		return false;
	}
}