package com.animania.common.blocks.fluids;

import com.animania.common.handler.BlockHandler;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFluidSlop extends BlockFluidBase
{

    public BlockFluidSlop() 
    {
        super(BlockHandler.fluidSlop, Material.WATER, "slop");
        this.quantaPerBlock = 3;
        this.renderLayer = BlockRenderLayer.TRANSLUCENT;
    }

    @Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, BlockState state, Entity entity) {
		Vec3d vec = this.getFlowVector(world, pos);
		entity.addVelocity(vec.x / 500, vec.y / 500, vec.z / 500);
	}

    @Override
    public boolean shouldSideBeRendered(BlockState state, IBlockAccess world, BlockPos pos, Direction side) {
        return true;
    }

    @Override
    public MaterialColor getMaterialColor(BlockState state, IBlockAccess worldIn, BlockPos pos)
    {
    	return MaterialColor.BROWN;
    }
}
