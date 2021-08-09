package com.animania.addons.catsdogs.common.block;

import com.animania.Animania;
import com.animania.addons.catsdogs.common.tileentity.TileEntityProp;
import com.animania.common.blocks.AnimaniaRotateable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockProp extends AnimaniaRotateable implements ITileEntityProvider
{
	private AxisAlignedBB AABB = Block.FULL_BLOCK_AABB;

	public BlockProp(String name, Material blockMaterialIn, MaterialColor blockMaterialColorIn, SoundType sound)
	{
		super(name, blockMaterialIn, blockMaterialColorIn);
		this.hasTileEntity = true;
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setSoundType(sound);
	}

	public Block setAABB(double x1, double y1, double z1, double x2, double y2, double z2)
	{
		this.AABB = new AxisAlignedBB(x1 / 16, y1 / 16, z1 / 16, x2 / 16, y2 / 16, z2 / 16);
		return this;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityProp();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return AABB;
	}

}
