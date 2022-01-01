package com.animania.addons.catsdogs.common.block;

import com.animania.Animania;
import com.animania.addons.catsdogs.common.blockentity.BlockEntityProp;
import com.animania.common.blocks.AnimaniaRotateable;

import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.core.BlockPos;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;

public class BlockProp extends AnimaniaRotateable implements EntityBlock
{
	private AABB AABB = Block.FULL_BLOCK_AABB;

	public BlockProp(String name, Material blockMaterialIn, MaterialColor blockMaterialColorIn, SoundType sound)
	{
		super(name, blockMaterialIn, blockMaterialColorIn);
		this.hasBlockEntity = true;
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setSoundType(sound);
	}

	public Block setAABB(double x1, double y1, double z1, double x2, double y2, double z2)
	{
		this.AABB = new AABB(x1 / 16, y1 / 16, z1 / 16, x2 / 16, y2 / 16, z2 / 16);
		return this;
	}

	@Override
	public BlockEntity createNewBlockEntity(Level levelIn, int meta)
	{
		return new BlockEntityProp();
	}

	@Override
	public boolean isOpaqueCube(BlockState state)
	{
		return false;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, BlockState p_193383_2_, BlockPos p_193383_3_, Direction p_193383_4_)
	{
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean isFullCube(BlockState state)
	{
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(BlockState state)
	{
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	public AABB getBoundingBox(BlockState state, IBlockAccess source, BlockPos pos)
	{
		return this.AABB;
	}

	@Override
	public AABB getCollisionBoundingBox(BlockState blockState, IBlockAccess levelIn, BlockPos pos)
	{
		return this.AABB;
	}

}
