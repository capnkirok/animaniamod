package com.animania.addons.extra.common.handler;

import com.animania.Animania;
import com.animania.addons.extra.common.block.BlockHamsterWheel;
import com.animania.addons.extra.common.tileentity.TileEntityHamsterWheel;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ExtraAddonBlockHandler
{

	public static Block blockHamsterWheel;

	/**
	 * Register Blocks <br>
	 * Register TileEntities
	 */
	public static void preInit()
	{
		blockHamsterWheel = new BlockHamsterWheel();

		Item item = new ItemBlock(blockHamsterWheel);
		item.setRegistryName(new ResourceLocation(Animania.MODID, "block_hamster_wheel"));
		ForgeRegistries.ITEMS.register(item);

		GameRegistry.registerTileEntity(TileEntityHamsterWheel.class, "TileEntityHamsterWheel");
	}

}
