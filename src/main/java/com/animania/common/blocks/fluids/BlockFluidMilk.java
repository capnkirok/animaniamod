package com.animania.common.blocks.fluids;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidMilk extends BlockFluidBase
{

	public BlockFluidMilk(Fluid fluid, String name)
	{
		super(fluid, new MaterialLiquid(MapColor.SNOW), name);
		
		this.quantaPerBlock = 7;
		this.setRenderLayer(BlockRenderLayer.TRANSLUCENT);
		
	}
	
	
	

}
