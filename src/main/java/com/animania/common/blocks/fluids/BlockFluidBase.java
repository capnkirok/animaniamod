package com.animania.common.blocks.fluids;

import com.animania.Animania;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockFluidBase extends BlockFluidClassic
{

    public BlockFluidBase(Fluid fluid, Material material, String name) {
        super(fluid, material);
        this.setCreativeTab(Animania.TabAnimaniaResources);
        this.setUnlocalizedName(name);
        this.setHardness(100.0F);
        this.setRegistryName(new ResourceLocation(Animania.MODID, name));
        ForgeRegistries.BLOCKS.register(this);
       // BlockHandler.blocks.add(this);

    }
    

	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {

		if (world.getBlockState(pos).getMaterial().isLiquid()) {
			return false;
		}
		return super.canDisplace(world, pos);
	}
	
	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {

		if (world.getBlockState(pos).getMaterial().isLiquid()) {
			return false;
		}
		return super.displaceIfPossible(world, pos);
	}

	  @Override
	    public boolean shouldSideBeRendered(BlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
	    	BlockState st = world.getBlockState(pos.offset(side));
	    	if(st.getBlock() == this)
	    		return false;
	    	return true;
	    }

}
