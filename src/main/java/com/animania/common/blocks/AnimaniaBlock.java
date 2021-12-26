package com.animania.common.blocks;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AnimaniaBlock extends Block
{

	public AnimaniaBlock(String name, Material blockMaterialIn, MaterialColor blockMaterialColorIn)
	{
		super(blockMaterialIn, blockMaterialColorIn);
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);

		BlockHandler.blocks.add(this);
		Item item = new BlockItem(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, name));
		ForgeRegistries.ITEMS.register(item);
	}

	public AnimaniaBlock(String name, Material blockMaterialIn, MaterialColor blockMaterialColorIn, boolean BlockItem)
	{
		super(blockMaterialIn, blockMaterialColorIn);
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);

		BlockHandler.blocks.add(this);
		if (BlockItem)
		{
			Item item = new BlockItem(this);
			item.setRegistryName(new ResourceLocation(Animania.MODID, name));
			ForgeRegistries.ITEMS.register(item);
		}
	}

}
