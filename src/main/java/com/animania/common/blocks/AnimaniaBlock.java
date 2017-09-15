package com.animania.common.blocks;

import com.animania.Animania;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AnimaniaBlock extends Block
{

	public AnimaniaBlock(String name, Material blockMaterialIn, MapColor blockMapColorIn)
	{
		super(blockMaterialIn, blockMapColorIn);
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);

		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this), new ResourceLocation(Animania.MODID, name));
	}

	public AnimaniaBlock(String name, Material blockMaterialIn, MapColor blockMapColorIn, boolean itemblock)
	{
		super(blockMaterialIn, blockMapColorIn);
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);

		GameRegistry.register(this);
		if (itemblock)
		{
			GameRegistry.register(new ItemBlock(this), new ResourceLocation(Animania.MODID, name));
		}
	}

}
