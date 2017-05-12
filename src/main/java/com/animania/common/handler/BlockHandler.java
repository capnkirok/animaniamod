package com.animania.common.handler;

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
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockHandler {

	//Blocks
	public static Block blockMud;
	public static Block blockInvisiblock;
	public static Block blockSeeds;
	public static BlockFluidBase blockSlop;
	public static BlockFluidBase blockMilkHolstein;
	public static BlockFluidBase blockMilkFriesian;

	// TileEntity blocks
	public static Block blockTrough;
	public static Block blockNest;

	
	
	//Fluids
	public static Fluid fluidSlop;
	public static Fluid fluidMilkHolstein;
	public static Fluid fluidMilkFriesian;

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
		
		//Fluids
		fluidSlop = new FluidBase("slop").setViscosity(7000).setDensity(3000).setEmptySound(SoundEvents.BLOCK_SLIME_PLACE).setFillSound(SoundEvents.BLOCK_SLIME_FALL);
		FluidRegistry.addBucketForFluid(fluidSlop);
		blockSlop = new BlockFluidSlop();
		
		fluidMilkHolstein = new FluidBase("milk_holstein").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(fluidMilkHolstein);
		blockMilkHolstein = new BlockFluidMilk(fluidMilkHolstein, "milk_holstein");
		
		fluidMilkFriesian = new FluidBase("milk_friesian").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(fluidMilkFriesian);
		blockMilkFriesian = new BlockFluidMilk(fluidMilkFriesian, "milk_friesian");
		
		//Itemblocks
		itemBlockMud = new ItemBlock(blockMud);
		GameRegistry.register(itemBlockMud.setRegistryName(blockMud.getRegistryName()));

		itemBlockTrough = new ItemBlock(blockTrough);
		GameRegistry.register(itemBlockTrough.setRegistryName(blockTrough.getRegistryName()));

		itemBlockNest = new ItemBlock(blockNest);
		GameRegistry.register(itemBlockNest.setRegistryName(blockNest.getRegistryName()));

	}
}
