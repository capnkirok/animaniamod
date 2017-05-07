package com.animania.common.events;

import java.util.Random;

import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSeedHandler {
	@SubscribeEvent
	public void notify(PlayerInteractEvent.RightClickBlock event) {

		ItemStack stack = event.getItemStack();
		EntityPlayer player = event.getEntityPlayer();
		BlockPos pos = event.getPos();
		World world = event.getWorld();

		if (AnimaniaConfig.gameRules.shiftSeedPlacement) {
			if (stack != ItemStack.EMPTY && stack.getItem() == Items.WHEAT_SEEDS && player.isSneaking()) {

				BlockPos abovePos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
				Block checkSeeds = event.getEntity().world.getBlockState(pos).getBlock();
				Block checkAbove = event.getEntity().world.getBlockState(abovePos).getBlock();

				if (checkSeeds != null && event.getFace().toString().equals("up")
						&& checkSeeds.getRegistryName() != null && checkSeeds.isFullBlock(checkSeeds.getDefaultState())
						&& checkSeeds != BlockHandler.blockSeeds
						&& !checkSeeds.getRegistryName().toString().contains("biomesoplenty:farmland")
						&& !checkSeeds.getRegistryName().toString().contains("forestry:humus")
						&& !(checkSeeds instanceof BlockFarmland) && !(checkSeeds instanceof BlockCrops)
						&& !checkSeeds.getRegistryName().toString().contains("agricraft:crop_sticks")
						&& checkAbove == Blocks.AIR && checkSeeds != Blocks.FARMLAND
						&& checkSeeds != BlockHandler.blockNest && checkSeeds != BlockHandler.blockTrough
						&& checkSeeds != Blocks.TALLGRASS
						&& checkSeeds.getMaterial(checkSeeds.getDefaultState()) != Material.WATER
						&& checkSeeds != Blocks.LAVA && checkSeeds != Blocks.WHEAT
						&& checkSeeds.isReplaceable(world, abovePos)) {

					if (!event.getEntityPlayer().capabilities.isCreativeMode) {
						if (event.getItemStack().getCount() >= 1) {
							event.getItemStack().shrink(1);
						}
					}

					IBlockState state = BlockHandler.blockSeeds.getDefaultState();
					event.getEntity().world.setBlockState(abovePos, state);
					event.getEntityPlayer().swingArm(event.getHand());

					Random rand = new Random();
					event.getWorld().playSound(null, event.getEntityPlayer().posX, event.getEntityPlayer().posY,
							event.getEntityPlayer().posZ, SoundEvents.BLOCK_GRASS_FALL, SoundCategory.PLAYERS, 0.2F,
							((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);

				}

			}
		} else {

			if (stack != ItemStack.EMPTY && stack.getItem() == Items.WHEAT_SEEDS) {

				BlockPos abovePos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
				Block checkSeeds = event.getEntity().world.getBlockState(pos).getBlock();
				Block checkAbove = event.getEntity().world.getBlockState(abovePos).getBlock();

				if (checkSeeds != null && event.getFace().toString().equals("up")
						&& checkSeeds.getRegistryName() != null && checkSeeds != BlockHandler.blockSeeds
						&& checkSeeds != BlockHandler.blockInvisiblock
						&& !checkSeeds.getRegistryName().toString().contains("biomesoplenty:farmland")
						&& !checkSeeds.getRegistryName().toString().contains("forestry:humus")
						&& !(checkSeeds instanceof BlockCrops) && !(checkSeeds instanceof BlockFarmland)
						&& !checkSeeds.getRegistryName().toString().contains("agricraft:crop_sticks")
						&& checkAbove == Blocks.AIR && checkSeeds != Blocks.FARMLAND
						&& checkSeeds != BlockHandler.blockNest && checkSeeds != BlockHandler.blockTrough
						&& checkSeeds != Blocks.TALLGRASS
						&& checkSeeds.getMaterial(checkSeeds.getDefaultState()) != Material.WATER
						&& checkSeeds != Blocks.LAVA && checkSeeds != Blocks.WHEAT && checkSeeds != Blocks.SNOW
						&& checkSeeds.isOpaqueCube(checkSeeds.getDefaultState())
						&& checkSeeds.isReplaceable(world, abovePos)) {

					if (!event.getEntityPlayer().capabilities.isCreativeMode) {
						if (event.getItemStack().getCount() >= 1) {
							event.getItemStack().shrink(1);
						}
					}

					IBlockState state = BlockHandler.blockSeeds.getDefaultState();
					event.getEntity().world.setBlockState(abovePos, state);
					event.getEntityPlayer().swingArm(event.getHand());

					Random rand = new Random();
					event.getWorld().playSound(null, event.getEntityPlayer().posX, event.getEntityPlayer().posY,
							event.getEntityPlayer().posZ, SoundEvents.BLOCK_GRASS_FALL, SoundCategory.PLAYERS, 0.2F,
							((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);

				}

			}

		}
	}

}
