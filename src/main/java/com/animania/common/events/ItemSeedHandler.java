package com.animania.common.events;

import java.util.Random;

import com.animania.common.entities.horses.EntityMareDraftHorse;
import com.animania.common.entities.horses.EntityStallionDraftHorse;
import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogHampshire;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogLargeWhite;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowHampshire;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowLargeWhite;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.BlockFarmland;
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
	public void notify(PlayerInteractEvent.RightClickBlock event) {

		ItemStack stack = event.getItemStack();
		EntityPlayer player = event.getEntityPlayer();
		BlockPos pos = event.getPos();
		World world = event.getWorld();

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.WHEAT_SEEDS
				&& (AnimaniaConfig.gameRules.shiftSeedPlacement ? player.isSneaking() : true)) {

			if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos))
				pos = pos.offset(event.getFace());

			BlockPos below = pos.down();

			if (world.getBlockState(below).isFullBlock() && world.getBlockState(below).isOpaqueCube()
					&& !(world.getBlockState(below).getBlock() instanceof BlockFarmland)
					&& !(world.getBlockState(below).getBlock() instanceof IPlantable))
				if (world.getBlockState(pos).getBlock().isReplaceable(world, pos)) {
					world.setBlockState(pos, BlockHandler.blockSeeds.getDefaultState());
					player.swingArm(event.getHand());
					if (!player.isCreative())
						stack.shrink(1);

					Random rand = new Random();
					event.getWorld().playSound(null, event.getEntityPlayer().posX, event.getEntityPlayer().posY, event.getEntityPlayer().posZ,
							SoundEvents.BLOCK_GRASS_FALL, SoundCategory.PLAYERS, 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
				}

		}

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.CARROT_ON_A_STICK && player.isRiding()) {
			if (player.getRidingEntity() instanceof EntitySowYorkshire) {
				EntitySowYorkshire ep = (EntitySowYorkshire)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			} else if (player.getRidingEntity() instanceof EntityHogYorkshire) {
				EntityHogYorkshire ep = (EntityHogYorkshire)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			} else if (player.getRidingEntity() instanceof EntitySowDuroc) {
				EntitySowDuroc ep = (EntitySowDuroc)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			} else if (player.getRidingEntity() instanceof EntityHogDuroc) {
				EntityHogDuroc ep = (EntityHogDuroc)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			} else if (player.getRidingEntity() instanceof EntitySowHampshire) {
				EntitySowHampshire ep = (EntitySowHampshire)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			} else if (player.getRidingEntity() instanceof EntityHogHampshire) {
				EntityHogHampshire ep = (EntityHogHampshire)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			} else if (player.getRidingEntity() instanceof EntitySowLargeBlack) {
				EntitySowLargeBlack ep = (EntitySowLargeBlack)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			} else if (player.getRidingEntity() instanceof EntityHogLargeBlack) {
				EntityHogLargeBlack ep = (EntityHogLargeBlack)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			} else if (player.getRidingEntity() instanceof EntitySowLargeWhite) {
				EntitySowLargeWhite ep = (EntitySowLargeWhite)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			} else if (player.getRidingEntity() instanceof EntityHogLargeWhite) {
				EntityHogLargeWhite ep = (EntityHogLargeWhite)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			} else if (player.getRidingEntity() instanceof EntitySowOldSpot) {
				EntitySowOldSpot ep = (EntitySowOldSpot)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			} else if (player.getRidingEntity() instanceof EntityHogOldSpot) {
				EntityHogOldSpot ep = (EntityHogOldSpot)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			
			}

		} else if (stack != null && stack.getItem() == ItemHandler.ridingCrop && player.isRiding()) {
			if (player.getRidingEntity() instanceof EntityStallionDraftHorse) {
				EntityStallionDraftHorse ep = (EntityStallionDraftHorse)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			}
		}
		
		if (stack != null && stack.getItem() == ItemHandler.ridingCrop && player.isRiding()) {
			if (player.getRidingEntity() instanceof EntityMareDraftHorse) {
				EntityMareDraftHorse ep = (EntityMareDraftHorse)player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode) {
					stack.damageItem(1, player);
				}
			}
		}

	}
}