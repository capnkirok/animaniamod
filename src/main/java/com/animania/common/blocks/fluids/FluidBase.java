package com.animania.common.blocks.fluids;

import com.animania.Animania;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;

public class FluidBase extends Fluid
{

    public FluidBase(String fluidName) {
        super(fluidName, new ResourceLocation(Animania.MODID + ":fluids/" + fluidName + "_still"),
                new ResourceLocation(Animania.MODID + ":fluids/" + fluidName + "_flow"));
    }
    
  
    
    

}
