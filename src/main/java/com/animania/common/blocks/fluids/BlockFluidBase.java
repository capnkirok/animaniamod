package com.animania.common.blocks.fluids;

import com.animania.Animania;

import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockFluidBase extends BlockFluidClassic {

	public BlockFluidBase(Fluid fluid, Material material, String name) {
		super(fluid, material);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setUnlocalizedName(name);
		GameRegistry.register(this, (new ResourceLocation(Animania.MODID, name)));
		
	}
	
	
	

}
