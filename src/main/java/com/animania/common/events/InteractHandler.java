package com.animania.common.events;

import com.animania.Animania;
import com.animania.api.interfaces.IAnimaniaAnimal;
import com.animania.common.blocks.BlockSeeds;
import com.animania.common.handler.AdvancementHandler;
import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@EventBusSubscriber(modid = Animania.MODID)
public class InteractHandler
{
	@SubscribeEvent
	public static void notify(PlayerInteractEvent.RightClickItem event)
	{

		ItemStack stack = event.getItemStack();
		EntityPlayer player = event.getEntityPlayer();
		World world = event.getWorld();

		if (stack != ItemStack.EMPTY && (stack.getItem() == Items.WHEAT_SEEDS || stack.getItem() == Items.PUMPKIN_SEEDS || stack.getItem() == Items.MELON_SEEDS || stack.getItem() == Items.BEETROOT_SEEDS) && (AnimaniaConfig.gameRules.shiftSeedPlacement ? player.isSneaking() : true))
		{
			Item item = stack.getItem();
			RayTraceResult ray = AnimaniaHelper.rayTrace(player, player.isCreative() ? 5.5 : 4.5);

			if (ray != null && ray.typeOfHit == RayTraceResult.Type.BLOCK)
			{
				EnumFacing facing = ray.sideHit;
				BlockPos pos = ray.getBlockPos();

				if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos))
					pos = pos.offset(facing);
				BlockPos below = pos.down();

				if (world.getBlockState(below).isFullBlock() && world.getBlockState(below).isOpaqueCube() && !(world.getBlockState(below).getBlock() instanceof BlockFarmland) && !(world.getBlockState(below).getBlock() instanceof IPlantable))
				{
					if (world.getBlockState(pos).getBlock().isReplaceable(world, pos))
					{
						if (item == Items.WHEAT_SEEDS)
							world.setBlockState(pos, BlockHandler.blockSeeds.getDefaultState());
						else if (item == Items.PUMPKIN_SEEDS)
							world.setBlockState(pos, BlockHandler.blockSeeds.getDefaultState().withProperty(BlockSeeds.VARIANT, BlockSeeds.EnumType.PUMPKIN));
						else if (item == Items.MELON_SEEDS)
							world.setBlockState(pos, BlockHandler.blockSeeds.getDefaultState().withProperty(BlockSeeds.VARIANT, BlockSeeds.EnumType.MELON));
						else if (item == Items.BEETROOT_SEEDS)
							world.setBlockState(pos, BlockHandler.blockSeeds.getDefaultState().withProperty(BlockSeeds.VARIANT, BlockSeeds.EnumType.BEETROOT));
						player.swingArm(event.getHand());
						if (!player.isCreative())
							stack.shrink(1);

						event.getWorld().playSound(null, event.getEntityPlayer().posX, event.getEntityPlayer().posY, event.getEntityPlayer().posZ, SoundEvents.BLOCK_GRASS_FALL, SoundCategory.PLAYERS, 0.2F, ((Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F) / 0.8F);
					}
				}
			}

		}

	}

	@SubscribeEvent
	public static void onPlayerRightClickEntity(PlayerInteractEvent.EntityInteract event)
	{
		EntityPlayer player = event.getEntityPlayer();
		ItemStack stack = player.getHeldItemMainhand();
		Entity target = event.getTarget();
		ResourceLocation loc = EntityList.getKey(target);
		EntityEntry entry = ForgeRegistries.ENTITIES.getValue(loc);

		if (player instanceof EntityPlayerMP && target instanceof IAnimaniaAnimal)
		{
			if (entry != null)
			{
				AdvancementHandler.feedAnimal.trigger((EntityPlayerMP) player, stack, entry);
			}
		}
	}

	@SubscribeEvent
	public static void missingMapping(RegistryEvent.MissingMappings<Item> event)
	{
		for (MissingMappings.Mapping<Item> entry : event.getAllMappings())
		{

			String key = entry.key.toString();

			if (key.contains("animania:entity_egg_peafowl"))
			{
				ResourceLocation egg = new ResourceLocation(key.replace("peafowl", "peahen"));
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
				continue;
			}
			if (key.contains("animania:entity_egg_") && key.contains("newzealand"))
			{
				ResourceLocation egg = new ResourceLocation(key.replace("newzealand", "new_zealand"));
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
				continue;
			}
			if (key.contains("animania:entity_egg_") && key.contains("red"))
			{
				ResourceLocation egg = new ResourceLocation(key.replace("red", "rhode_island_red"));
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
				continue;
			}
			if (key.contains("animania:entity_egg_") && key.contains("nigeriandwarf"))
			{
				ResourceLocation egg = new ResourceLocation(key.replace("nigeriandwarf", "nigerian_dwarf"));
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
			}
			if (key.contains("animania:entity_egg_") && key.contains("largewhite"))
			{
				ResourceLocation egg = new ResourceLocation(key.replace("largewhite", "large_white"));
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
				continue;
			}
			if (key.equals("animania:entity_egg_draft_horse_mare"))
			{
				ResourceLocation egg = new ResourceLocation("animania:entity_egg_mare_draft");
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
				continue;
			}
			if (key.equals("animania:entity_egg_draft_horse_foal"))
			{
				ResourceLocation egg = new ResourceLocation("animania:entity_egg_foal_draft");
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
				continue;
			}
			if (key.equals("animania:entity_egg_draft_horse_stallion"))
			{
				ResourceLocation egg = new ResourceLocation("animania:entity_egg_stallion_draft");
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
				continue;
			}
			if (key.contains("animania:entity_egg_") && key.contains("largeblack"))
			{
				ResourceLocation egg = new ResourceLocation(key.replace("largeblack", "large_black"));
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
				continue;
			}
			if (key.contains("animania:entity_egg_") && key.contains("oldspot"))
			{
				ResourceLocation egg = new ResourceLocation(key.replace("oldspot", "old_spot"));
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
				continue;
			}
			if (key.contains("animania:entity_egg_") && key.contains("plymouth"))
			{
				ResourceLocation egg = new ResourceLocation(key.replace("plymouth", "plymouth_rock"));
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
				continue;
			}
			if (key.equals("animania:entity_egg_dart_frog"))
			{
				ResourceLocation egg = new ResourceLocation("animania:entity_egg_dartfrog");
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
				continue;
			}

		}
	}

}