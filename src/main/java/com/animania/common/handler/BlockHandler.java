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

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;

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

	// BlockEntity blocks
	public static Block blockTrough;
	public static Block blockNest;
	public static Block blockSaltLick;

	// TODO Add own buckets

	// Item Blocks
	public static BlockItem BlockItemMud;
	public static BlockItem BlockItemSeeds;
	public static BlockItem BlockItemTrough;
	public static BlockItem BlockItemNest;
	public static BlockItem BlockItemStraw;
	public static BlockItem itemInvisiblock;

	public static List<Block> blocks = new ArrayList<>();

	public static void preInit()
	{

		// Fluids
		fluidSlop = new FluidBase("slop").setViscosity(7000).setDensity(3000).setEmptySound(SoundEvents.SLIME_BLOCK_PLACE).setFillSound(SoundEvents.SLIME_BLOCK_FALL);
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

		// BlockItems
		BlockHandler.BlockItemMud = new BlockItem(BlockHandler.blockMud);
		BlockHandler.BlockItemMud.setRegistryName(BlockHandler.blockMud.getRegistryName());
		ForgeRegistries.ITEMS.register(BlockItemMud);

		BlockHandler.BlockItemTrough = new BlockItem(BlockHandler.blockTrough);
		BlockHandler.BlockItemTrough.setRegistryName(BlockHandler.blockTrough.getRegistryName());
		ForgeRegistries.ITEMS.register(BlockHandler.BlockItemTrough);

		BlockHandler.BlockItemNest = new BlockItem(BlockHandler.blockNest);
		BlockHandler.BlockItemNest.setRegistryName(BlockHandler.blockNest.getRegistryName());
		ForgeRegistries.ITEMS.register(BlockHandler.BlockItemNest);
	}

	@SubscribeEvent
	public static void onRegister(RegistryEvent.Register<Block> event)
	{
		for (Block b : blocks)
			event.getRegistry().register(b);
	}
}
