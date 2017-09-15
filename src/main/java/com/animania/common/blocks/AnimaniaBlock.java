package com.animania.common.blocks;

import com.animania.Animania;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class AnimaniaBlock extends Block
{

	public AnimaniaBlock(String name, Material blockMaterialIn, MapColor blockMapColorIn)
	{
		super(blockMaterialIn, blockMapColorIn);
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);

		ForgeRegistries.BLOCKS.register(this);
		Item item = new ItemBlock(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, name));
		ForgeRegistries.ITEMS.register(item);
	}

	public AnimaniaBlock(String name, Material blockMaterialIn, MapColor blockMapColorIn, boolean itemblock)
	{
		super(blockMaterialIn, blockMapColorIn);
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);

		ForgeRegistries.BLOCKS.register(this);
		if (itemblock)
		{
			Item item = new ItemBlock(this);
			item.setRegistryName(new ResourceLocation(Animania.MODID, name));
			ForgeRegistries.ITEMS.register(item);
		}
	}

}
