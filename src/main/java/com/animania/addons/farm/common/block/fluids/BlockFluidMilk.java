package com.animania.addons.farm.common.block.fluids;

import com.animania.common.blocks.fluids.BlockFluidBase;

import net.minecraft.core.BlockPos;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;

public class BlockFluidMilk extends BlockFluidBase
{

	public BlockFluidMilk(Fluid fluid, String name)
	{
		super(fluid, Material.WATER, name);

		this.quantaPerBlock = 7;
		this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);

	}

	@Override
	public void onEntityCollidedWithBlock(Level level, BlockPos pos, BlockState state, Entity entity)
	{
		Vec3d vec = this.getFlowVector(level, pos);
		entity.addVelocity(vec.x / 1000, vec.y / 1000, vec.z / 1000);
	}

	@Override
	public MaterialColor getMaterialColor(BlockState state, IBlockAccess levelIn, BlockPos pos)
	{
		return MaterialColor.SNOW;
	}
}
