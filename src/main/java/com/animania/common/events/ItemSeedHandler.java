package com.animania.common.events;

import java.util.Random;

import com.animania.common.handler.BlockHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemSeedHandler
{
	@SubscribeEvent
	public void notify(PlayerInteractEvent.RightClickBlock event)
	{

		ItemStack stack = event.getItemStack();
		EntityPlayer player = event.getEntityPlayer();
		BlockPos pos = event.getPos();
		World world = event.getWorld();

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WHEAT_SEEDS && (AnimaniaConfig.gameRules.shiftSeedPlacement ? player.isSneaking() : true))
		{

			if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos))
				pos = pos.offset(event.getFace());

			BlockPos below = pos.down();

			if (world.getBlockState(below).isFullBlock() && world.getBlockState(below).isOpaqueCube() && !(world.getBlockState(below).getBlock() instanceof BlockFarmland) && !(world.getBlockState(below).getBlock() instanceof IPlantable))
			{
				if (world.getBlockState(pos).getBlock().isReplaceable(world, pos))
				{
					world.setBlockState(pos, BlockHandler.blockSeeds.getDefaultState());
					player.swingArm(event.getHand());
					if (!player.isCreative())
						stack.shrink(1);

					Random rand = new Random();
					event.getWorld().playSound(null, event.getEntityPlayer().posX, event.getEntityPlayer().posY, event.getEntityPlayer().posZ, SoundEvents.BLOCK_GRASS_FALL, SoundCategory.PLAYERS, 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
				}
			}

		} 
	}
	
}
