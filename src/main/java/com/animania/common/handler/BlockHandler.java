package com.animania.common.handler;

import java.util.ArrayList;
import java.util.List;

import com.animania.Animania;
import com.animania.common.blocks.BlockInvisiblock;
import com.animania.common.blocks.BlockMud;
import com.animania.common.blocks.BlockNest;
import com.animania.common.blocks.BlockSaltLick;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.blocks.BlockStraw;
import com.animania.common.blocks.BlockTrough;
import com.animania.common.blocks.fluids.BlockFluidBase;
import com.animania.common.blocks.fluids.BlockFluidSlop;
import com.animania.common.blocks.fluids.FluidBase;

import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import Fluid;
import ItemBlock;

@EventBusSubscriber(modid = Animania.MODID)
public class BlockHandler
{

	public static BlockFluidBase blockSlop;
	public static Fluid fluidSlop;

	// Blocks
	public static Block blockMud;
	public static Block blockInvisiblock;
	public static Block blockSeeds;
	public static Block blockStraw;

	// TileEntity blocks
	public static Block blockTrough;
	public static Block blockNest;
	public static Block blockSaltLick;

	// TODO Add own buckets

	// Item Blocks
	public static ItemBlock itemBlockMud;
	public static ItemBlock itemBlockSeeds;
	public static ItemBlock itemBlockTrough;
	public static ItemBlock itemBlockNest;
	public static ItemBlock itemBlockStraw;
	public static ItemBlock itemInvisiblock;

	public static List<Block> blocks = new ArrayList<Block>();

	public static void preInit()
	{

		// Fluids
		fluidSlop = new FluidBase("slop").setViscosity(7000).setDensity(3000).setEmptySound(SoundEvents.BLOCK_SLIME_PLACE).setFillSound(SoundEvents.BLOCK_SLIME_FALL);
		FluidRegistry.addBucketForFluid(fluidSlop);
		blockSlop = new BlockFluidSlop();

		// BLOCKS
		BlockHandler.blockMud = new BlockMud().setHardness(1.0F).setResistance(1.0F);
		BlockHandler.blockTrough = new BlockTrough().setHardness(1.0F).setResistance(1.0F);
		BlockHandler.blockInvisiblock = new BlockInvisiblock().setHardness(1.0F).setResistance(1.0F);
		BlockHandler.blockNest = new BlockNest().setHardness(1.0F).setResistance(1.0F);
		BlockHandler.blockSeeds = new BlockSeeds();
		BlockHandler.blockStraw = new BlockStraw();
		BlockHandler.blockSaltLick = new BlockSaltLick();

		// Itemblocks
		BlockHandler.itemBlockMud = new ItemBlock(BlockHandler.blockMud);
		BlockHandler.itemBlockMud.setRegistryName(BlockHandler.blockMud.getRegistryName());
		ForgeRegistries.ITEMS.register(itemBlockMud);

		BlockHandler.itemBlockTrough = new ItemBlock(BlockHandler.blockTrough);
		BlockHandler.itemBlockTrough.setRegistryName(BlockHandler.blockTrough.getRegistryName());
		ForgeRegistries.ITEMS.register(BlockHandler.itemBlockTrough);

		BlockHandler.itemBlockNest = new ItemBlock(BlockHandler.blockNest);
		BlockHandler.itemBlockNest.setRegistryName(BlockHandler.blockNest.getRegistryName());
		ForgeRegistries.ITEMS.register(BlockHandler.itemBlockNest);
	}

	@SubscribeEvent
	public static void onRegister(RegistryEvent.Register<Block> event)
	{
		for (Block b : blocks)
			event.getRegistry().register(b);
	}
}
