package com.animania.common.blocks;

import PropertyDirection;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;

public abstract class AnimaniaRotateable extends AnimaniaBlock
{

	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public AnimaniaRotateable(String name, Material blockMaterialIn, MaterialColor blockMaterialColorIn, boolean BlockItem)
	{
		super(name, blockMaterialIn, blockMaterialColorIn, BlockItem);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, Direction.NORTH));

	}

	public AnimaniaRotateable(String name, Material blockMaterialIn, MaterialColor blockMaterialColorIn)
	{
		this(name, blockMaterialIn, blockMaterialColorIn, true);
	}

	@Override
	public BlockState getStateForPlacement(Level levelIn, BlockPos pos, Direction facing, float hitX, float hitY, float hitZ, int meta, LivingEntity placer)
	{
		return this.defaultBlockState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockPlacedBy(Level levelIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
	{
		levelIn.setBlock(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	public BlockState getStateFromMeta(int meta)
	{
		Direction enumfacing = Direction.getFront(meta);

		if (enumfacing.getAxis() == Direction.Axis.Y)
		{
			enumfacing = Direction.NORTH;
		}

		return this.defaultBlockState().withProperty(FACING, enumfacing);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(BlockState state)
	{
		return ((Direction) state.getValue(FACING)).getIndex();
	}

	/**
	 * Returns the blockstate with the given rotation from the passed
	 * blockstate. If inapplicable, returns the passed blockstate.
	 */
	public BlockState withRotation(BlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate((Direction) state.getValue(FACING)));
	}

	/**
	 * Returns the blockstate with the given mirror of the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 */
	public BlockState withMirror(BlockState state, Mirror mirrorIn)
	{
		return state.withRotation(mirrorIn.toRotation((Direction) state.getValue(FACING)));
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

}
