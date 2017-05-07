package com.animania.common.handler;

import com.animania.common.blocks.BlockInvisiblock;
import com.animania.common.blocks.BlockMud;
import com.animania.common.blocks.BlockNest;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.blocks.BlockTrough;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockHandler {

	public static Block blockMud;
	public static Block blockInvisiblock;
	public static Block blockSeeds;

	// TileEntity blocks
	public static Block blockTrough;
	public static Block blockNest;

	// Item Blocks
	public static ItemBlock itemBlockMud;
	public static ItemBlock itemBlockSeeds;
	public static ItemBlock itemBlockTrough;
	public static ItemBlock itemBlockNest;
	public static ItemBlock itemInvisiblock;

	public static void preInit() {

		// BLOCKS
		blockMud = new BlockMud().setHardness(1.0F).setResistance(1.0F);
		blockTrough = new BlockTrough().setHardness(1.0F).setResistance(1.0F);
		blockInvisiblock = new BlockInvisiblock().setHardness(1.0F).setResistance(1.0F);
		blockNest = new BlockNest().setHardness(1.0F).setResistance(1.0F);
		blockSeeds = new BlockSeeds();

		itemBlockMud = new ItemBlock(blockMud);
		GameRegistry.register(itemBlockMud.setRegistryName(blockMud.getRegistryName()));

		itemBlockTrough = new ItemBlock(blockTrough);
		GameRegistry.register(itemBlockTrough.setRegistryName(blockTrough.getRegistryName()));

		itemBlockNest = new ItemBlock(blockNest);
		GameRegistry.register(itemBlockNest.setRegistryName(blockNest.getRegistryName()));

	}
}
