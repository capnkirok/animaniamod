package com.animania.common.blocks;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AnimaniaContainer extends BaseEntityBlock
{
	public AnimaniaContainer(String name, Material blockMaterialIn, MaterialColor blockMaterialColorIn)
	{
		super(BlockBehaviour.Properties.of(blockMaterialIn, blockMaterialColorIn));
		this.setRegistryName(Animania.MODID + ":" + name);
		// TODO: Move these to Items
		//this.setUnlocalizedName(Animania.MODID + "_" + name);
		//this.setCreativeTab(Animania.TabAnimaniaResources);

		BlockHandler.blocks.add(this);
		Item item = new BlockItem(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, name));
		ForgeRegistries.ITEMS.register(item);
	}

	public AnimaniaContainer(String name, Material blockMaterialIn, MaterialColor blockMaterialColorIn, boolean BlockItem)
	{
		super(BlockBehaviour.Properties.of(blockMaterialIn, blockMaterialColorIn));
		this.setRegistryName(Animania.MODID + ":" + name);
		// Same here
		// this.setUnlocalizedName(Animania.MODID + "_" + name);

		BlockHandler.blocks.add(this);
		if (BlockItem)
		{
			Item item = new BlockItem(this);
			item.setRegistryName(new ResourceLocation(Animania.MODID, name));
			ForgeRegistries.ITEMS.register(item);
		}
	}
}
