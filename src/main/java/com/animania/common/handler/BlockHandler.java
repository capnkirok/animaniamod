package com.animania.common.handler;

import com.animania.Animania;
import com.animania.common.blocks.BlockCheese;
import com.animania.common.blocks.BlockCheeseMold;
import com.animania.common.blocks.BlockHamsterWheel;
import com.animania.common.blocks.BlockInvisiblock;
import com.animania.common.blocks.BlockMud;
import com.animania.common.blocks.BlockNest;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.blocks.BlockTrough;
import com.animania.common.blocks.fluids.BlockFluidBase;
import com.animania.common.blocks.fluids.BlockFluidMilk;
import com.animania.common.blocks.fluids.BlockFluidSlop;
import com.animania.common.blocks.fluids.FluidBase;

import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockHandler
{

	// Blocks
	public static Block blockMud;
	public static Block blockInvisiblock;
	public static Block blockSeeds;
	public static Block blockHamsterWheel;
	public static BlockFluidBase blockSlop;
	public static BlockFluidBase blockMilkHolstein;
	public static BlockFluidBase blockMilkFriesian;
	public static Block blockCheeseHolstein;
	public static Block blockCheeseFriesian;
	public static Block blockCheeseMold;
	
	// TileEntity blocks
	public static Block blockTrough;
	public static Block blockNest;

	// Fluids
	public static Fluid fluidSlop;
	public static Fluid fluidMilkHolstein;
	public static Fluid fluidMilkFriesian;

	// Item Blocks
	public static ItemBlock itemBlockMud;
	public static ItemBlock itemBlockSeeds;
	public static ItemBlock itemBlockTrough;
	public static ItemBlock itemBlockNest;
	public static ItemBlock itemInvisiblock;

	public static void preInit()
	{

		// BLOCKS
		BlockHandler.blockMud = new BlockMud().setHardness(1.0F).setResistance(1.0F);
		BlockHandler.blockTrough = new BlockTrough().setHardness(1.0F).setResistance(1.0F);
		BlockHandler.blockInvisiblock = new BlockInvisiblock().setHardness(1.0F).setResistance(1.0F);
		BlockHandler.blockNest = new BlockNest().setHardness(1.0F).setResistance(1.0F);
		BlockHandler.blockSeeds = new BlockSeeds();
		BlockHandler.blockHamsterWheel = new BlockHamsterWheel();
		BlockHandler.blockCheeseFriesian = new BlockCheese("cheese_friesian");
		BlockHandler.blockCheeseHolstein = new BlockCheese("cheese_holstein");
		ItemHandler.cheeseWheelFriesian = Item.getItemFromBlock(blockCheeseFriesian);
		ItemHandler.cheeseWheelHolstein = Item.getItemFromBlock(blockCheeseHolstein);
		BlockHandler.blockCheeseMold = new BlockCheeseMold();
		ItemHandler.cheeseMold = Item.getItemFromBlock(blockCheeseMold);

		// Fluids
		BlockHandler.fluidSlop = new FluidBase("slop").setViscosity(7000).setDensity(3000).setEmptySound(SoundEvents.BLOCK_SLIME_PLACE).setFillSound(SoundEvents.BLOCK_SLIME_FALL);
		FluidRegistry.addBucketForFluid(BlockHandler.fluidSlop);
		BlockHandler.blockSlop = new BlockFluidSlop();

		BlockHandler.fluidMilkHolstein = new FluidBase("milk_holstein").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(BlockHandler.fluidMilkHolstein);
		BlockHandler.blockMilkHolstein = new BlockFluidMilk(BlockHandler.fluidMilkHolstein, "milk_holstein");

		BlockHandler.fluidMilkFriesian = new FluidBase("milk_friesian").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(BlockHandler.fluidMilkFriesian);
		BlockHandler.blockMilkFriesian = new BlockFluidMilk(BlockHandler.fluidMilkFriesian, "milk_friesian");

		// Itemblocks
		BlockHandler.itemBlockMud = new ItemBlock(BlockHandler.blockMud);
		GameRegistry.register(BlockHandler.itemBlockMud.setRegistryName(BlockHandler.blockMud.getRegistryName()));

		BlockHandler.itemBlockTrough = new ItemBlock(BlockHandler.blockTrough);
		GameRegistry.register(BlockHandler.itemBlockTrough.setRegistryName(BlockHandler.blockTrough.getRegistryName()));

		BlockHandler.itemBlockNest = new ItemBlock(BlockHandler.blockNest);
		GameRegistry.register(BlockHandler.itemBlockNest.setRegistryName(BlockHandler.blockNest.getRegistryName()));

		GameRegistry.register(new ItemBlock(BlockHandler.blockHamsterWheel), new ResourceLocation(Animania.MODID, "block_hamster_wheel"));
	}
}
