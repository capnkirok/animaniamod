package com.animania.common.blocks.fluids;

import com.animania.Animania;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.Fluid;

public class FluidBase extends Fluid
{

    public FluidBase(String fluidName) {
        super(fluidName, new ResourceLocation(Animania.MODID + ":fluids/" + fluidName + "_still"),
                new ResourceLocation(Animania.MODID + ":fluids/" + fluidName + "_flow"));
    }
    
  
    
    

}
