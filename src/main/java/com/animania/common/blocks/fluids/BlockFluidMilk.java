package com.animania.common.blocks.fluids;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidMilk extends BlockFluidBase
{

	public BlockFluidMilk(Fluid fluid, String name)
	{
		super(fluid, new MaterialLiquid(MapColor.SNOW), name);
		
		this.quantaPerBlock = 7;
		this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
		
	}
	
	@Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
        Vec3d vec = this.getFlowVector(world, pos);
        entity.addVelocity(vec.xCoord/60, vec.yCoord/60, vec.zCoord/60);
    }
	

}
