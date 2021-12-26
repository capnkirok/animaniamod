package com.animania.common.blocks;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.RegistryHelper.RItem;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AnimaniaContainer extends BaseEntityBlock
{
	public AnimaniaContainer(String name, Material blockMaterialIn, MaterialColor blockMaterialColorIn)
	{
		super(blockMaterialIn, blockMaterialColorIn);
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);
		this.setCreativeTab(Animania.TabAnimaniaResources);

		BlockHandler.blocks.add(this);
		RItem item = new BlockItem(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, name));
		ForgeRegistries.ITEMS.register(item);
	}

	public AnimaniaContainer(String name, Material blockMaterialIn, MaterialColor blockMaterialColorIn, boolean BlockItem)
	{
		super(blockMaterialIn, blockMaterialColorIn);
		this.setRegistryName(Animania.MODID + ":" + name);
		this.setUnlocalizedName(Animania.MODID + "_" + name);

		BlockHandler.blocks.add(this);
		if (BlockItem)
		{
			RItem item = new BlockItem(this);
			item.setRegistryName(new ResourceLocation(Animania.MODID, name));
			ForgeRegistries.ITEMS.register(item);
		}
	}
}
