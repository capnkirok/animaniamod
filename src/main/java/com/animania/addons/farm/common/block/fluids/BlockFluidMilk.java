package com.animania.addons.farm.common.block.fluids;

import com.animania.common.blocks.fluids.BlockFluidBase;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFluidMilk extends BlockFluidBase
{

	public BlockFluidMilk(Fluid fluid, String name)
	{
		super(fluid, Material.WATER, name);

		this.quantaPerBlock = 7;
		this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);

	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		Vec3d vec = this.getFlowVector(world, pos);
		entity.addVelocity(vec.x / 1000, vec.y / 1000, vec.z / 1000);
	}

	@Override
	public MaterialColor getMaterialColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		return MaterialColor.SNOW;
	}
}
