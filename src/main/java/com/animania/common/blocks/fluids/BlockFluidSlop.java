package com.animania.common.blocks.fluids;

import com.animania.common.handler.BlockHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.level.IBlockAccess;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;

public class BlockFluidSlop extends BlockFluidBase
{

    public BlockFluidSlop() 
    {
        super(BlockHandler.fluidSlop, Material.WATER, "slop");
        this.quantaPerBlock = 3;
        this.renderLayer = BlockRenderLayer.TRANSLUCENT;
    }

    @Override
	public void onEntityCollidedWithBlock(Level level, BlockPos pos, BlockState state, Entity entity) {
		Vec3d vec = this.getFlowVector(level, pos);
		entity.addVelocity(vec.x / 500, vec.y / 500, vec.z / 500);
	}

    @Override
    public boolean shouldSideBeRendered(BlockState state, IBlockAccess level, BlockPos pos, Direction side) {
        return true;
    }

    @Override
    public MaterialColor getMaterialColor(BlockState state, IBlockAccess levelIn, BlockPos pos)
    {
    	return MaterialColor.BROWN;
    }
}
