package com.animania.common.events;

import com.animania.Animania;
import com.animania.api.interfaces.IAnimaniaAnimal;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.handler.AdvancementHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.EntityEntry;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = Animania.MODID)
public class InteractHandler
{
	@SubscribeEvent
	public static void notify(PlayerInteractEvent.RightClickItem event)
	{

		ItemStack stack = event.getItemStack();
		Player player = event.getPlayer();
		Level level = event.getLevel();

		if (stack != ItemStack.EMPTY && (stack.getItem() == Items.WHEAT_SEEDS || stack.getItem() == Items.PUMPKIN_SEEDS || stack.getItem() == Items.MELON_SEEDS || stack.getItem() == Items.BEETROOT_SEEDS) && (AnimaniaConfig.gameRules.shiftSeedPlacement ? player.isSneaking() : true))
		{
			Item item = stack.getItem();
			RayTraceResult ray = AnimaniaHelper.rayTrace(player, player.isCreative() ? 5.5 : 4.5);

			if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK)
			{
				Direction facing = ray.sideHit;
				BlockPos pos = ray.getBlockPosition();

				if (!level.getBlockState(pos).getBlock().canBeReplaced(level, pos))
					pos = pos.offset(facing);
				BlockPos below = pos.down();

				if (level.getBlockState(below).isFullBlock() && level.getBlockState(below).isOpaqueCube() && !(level.getBlockState(below).getBlock() instanceof BlockFarmland) && !(level.getBlockState(below).getBlock() instanceof IPlantable))
				{
					if (level.getBlockState(pos).getBlock().canBeReplaced(level, pos))
					{
						if (item == Items.WHEAT_SEEDS)
							level.setBlock(pos, BlockHandler.blockSeeds.defaultBlockState());
						else if (item == Items.PUMPKIN_SEEDS)
							level.setBlock(pos, BlockHandler.blockSeeds.defaultBlockState().withProperty(BlockSeeds.VARIANT, BlockSeeds.EnumType.PUMPKIN));
						else if (item == Items.MELON_SEEDS)
							level.setBlock(pos, BlockHandler.blockSeeds.defaultBlockState().withProperty(BlockSeeds.VARIANT, BlockSeeds.EnumType.MELON));
						else if (item == Items.BEETROOT_SEEDS)
							level.setBlock(pos, BlockHandler.blockSeeds.defaultBlockState().withProperty(BlockSeeds.VARIANT, BlockSeeds.EnumType.BEETROOT));
						player.swing(event.getHand());
						if (!player.isCreative())
							stack.shrink(1);

						event.getLevel().playSound(null, event.getPlayer().getX(), event.getPlayer().getY(), event.getPlayer().getZ(), SoundEvents.BLOCK_GRASS_FALL, SoundCategory.PLAYERS, 0.2F, ((Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F) / 0.8F);

						event.setCanceled(true);
						event.setCancellationResult(ActionResultType.SUCCESS);
					}
				}
			}

		}

	}

	@SubscribeEvent
	public static void onPlayerRightClickEntity(PlayerInteractEvent.EntityInteract event)
	{
		PlayerEntity player = event.getPlayer();
		ItemStack stack = player.getMainHandItem();
		Entity target = event.getTarget();
		ResourceLocation loc = EntityList.getKey(target);
		EntityEntry entry = ForgeRegistries.ENTITIES.getValue(loc);

		if (player instanceof ServerPlayerEntity && target instanceof IAnimaniaAnimal && entry != null)
		{
			AdvancementHandler.feedAnimal.trigger((ServerPlayerEntity) player, stack, entry, target);
		}
	}

}