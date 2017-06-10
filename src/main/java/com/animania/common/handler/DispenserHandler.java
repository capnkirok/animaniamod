package com.animania.common.handler;

import com.animania.config.AnimaniaConfig;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockFarmland;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.Loader;

public class DispenserHandler
{

	public static void init()
	{
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.WHEAT_SEEDS, SEEDS_DISPENSER_BEHAVIOUR);
	}


	public static final IBehaviorDispenseItem SEEDS_DISPENSER_BEHAVIOUR = new BehaviorDefaultDispenseItem()
	{
		private final BehaviorDefaultDispenseItem behaviourDefaultDispenseItem = new BehaviorDefaultDispenseItem();

		public ItemStack dispenseStack(IBlockSource source, ItemStack stack)
		{
			EnumFacing enumfacing = (EnumFacing)source.getBlockState().getValue(BlockDispenser.FACING);
			World world = source.getWorld();
			BlockPos pos = source.getBlockPos().offset(enumfacing);
			BlockPos below = pos.offset(EnumFacing.DOWN);

			if(world.getBlockState(below).isFullBlock() && world.getBlockState(below).isOpaqueCube() && !(world.getBlockState(below).getBlock() instanceof BlockFarmland) && !(world.getBlockState(below).getBlock() instanceof IPlantable) && AnimaniaConfig.gameRules.allowSeedDispenserPlacement)
			{
				if (world.getBlockState(pos).getBlock().isReplaceable(world, pos))
				{
					world.setBlockState(pos, BlockHandler.blockSeeds.getDefaultState());
					stack.shrink(1);
					return stack;
				}
			}
			else if(Loader.isModLoaded("quark") && world.getBlockState(below).getBlock() instanceof BlockFarmland)
			{
				if (world.getBlockState(pos).getBlock().isReplaceable(world, pos))
				{
					world.setBlockState(pos, Blocks.WHEAT.getDefaultState());
					stack.shrink(1);
					return stack;
				}
			}


			return super.dispenseStack(source, stack);

		}

	};



}
