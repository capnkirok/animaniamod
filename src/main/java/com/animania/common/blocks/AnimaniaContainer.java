package com.animania.common.blocks;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public abstract class AnimaniaContainer extends BlockContainer
{
	public AnimaniaContainer(String name, Material blockMaterialIn, MapColor blockMapColorIn)
	{
		super(blockMaterialIn, blockMapColorIn);
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		
		BlockHandler.blocks.add(this);
		Item item = new ItemBlock(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, name));
		ForgeRegistries.ITEMS.register(item);
	}

	public AnimaniaContainer(String name, Material blockMaterialIn, MapColor blockMapColorIn, boolean itemblock)
	{
		super(blockMaterialIn, blockMapColorIn);
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);

		BlockHandler.blocks.add(this);
		if (itemblock)
		{
			Item item = new ItemBlock(this);
			item.setRegistryName(new ResourceLocation(Animania.MODID, name));
			ForgeRegistries.ITEMS.register(item);
		}
	}
}
