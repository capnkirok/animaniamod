package com.animania.addons.farm.common.event;

import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityMareDraftHorse;
import com.animania.addons.farm.common.entity.horses.HorseDraft.EntityStallionDraftHorse;
import com.animania.addons.farm.common.entity.pigs.EntityHogBase;
import com.animania.addons.farm.common.entity.pigs.EntitySowBase;
import com.animania.addons.farm.common.entity.sheep.EntityAnimaniaSheep;
import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.api.interfaces.ISterilizable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class FarmAddonInteractHandler
{

	@SubscribeEvent
	public static void notify(PlayerInteractEvent.RightClickItem event)
	{
		ItemStack stack = event.getItemStack();
		EntityPlayer player = event.getEntityPlayer();
		World world = event.getWorld();

		if (stack != ItemStack.EMPTY && stack.getItem() == Items.CARROT_ON_A_STICK && player.isRiding())
		{
			if (player.getRidingEntity() instanceof EntitySowBase)
			{
				EntitySowBase ep = (EntitySowBase) player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode)
				{
					stack.damageItem(1, player);
				}
			} else if (player.getRidingEntity() instanceof EntityHogBase)
			{
				EntityHogBase ep = (EntityHogBase) player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode)
				{
					stack.damageItem(1, player);
				}
			}
		}

		if (stack != null && stack.getItem() == FarmAddonItemHandler.ridingCrop && player.isRiding())
		{
			if (player.getRidingEntity() instanceof EntityStallionDraftHorse)
			{
				EntityStallionDraftHorse ep = (EntityStallionDraftHorse) player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode)
				{
					stack.damageItem(1, player);
				}
			}
		}

		if (stack != null && stack.getItem() == FarmAddonItemHandler.ridingCrop && player.isRiding())
		{
			if (player.getRidingEntity() instanceof EntityMareDraftHorse)
			{
				EntityMareDraftHorse ep = (EntityMareDraftHorse) player.getRidingEntity();
				ep.boost();
				if (!player.capabilities.isCreativeMode)
				{
					stack.damageItem(1, player);
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

		if (stack.getItem() instanceof ItemDye && target instanceof EntityAnimaniaSheep)
		{
			if (!((EntityAnimaniaSheep) target).isDyeable())
				event.setCanceled(true);
		}

		if (stack.getItem() == FarmAddonItemHandler.carvingKnife && target instanceof ISterilizable && !((ISterilizable) target).getSterilized())
		{
			if (!target.world.isRemote)
				((net.minecraft.world.WorldServer) target.world).spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, false, target.posX, target.posY + target.height / 2.0F, target.posZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
			target.playSound(SoundEvents.ENTITY_MOOSHROOM_SHEAR, 1.0F, 1.0F);
			stack.damageItem(1, player);
			((ISterilizable) target).sterilize();
		}
	}

	@SubscribeEvent
	public static void missingMapping(RegistryEvent.MissingMappings<Item> event)
	{
		for (MissingMappings.Mapping<Item> entry : event.getAllMappings())
		{

			String key = entry.key.toString();
			if (key.matches("animania:raw_(.*?)_beef"))
			{
				entry.remap(FarmAddonItemHandler.rawPrimeBeef);
				continue;
			}
			if (key.matches("animania:raw_(.*?)_steak"))
			{
				entry.remap(FarmAddonItemHandler.rawPrimeSteak);
				continue;
			}
			if (key.matches("animania:cooked_(.*?)_steak"))
			{
				entry.remap(FarmAddonItemHandler.cookedPrimeSteak);
				continue;
			}
			if (key.equals("animania:cooked_large_black_roast") || key.equals("animania:cooked_duroc_roast") || key.equals("animania:cooked_old_spot_roast") || key.equals("animania:cooked_hampshire_roast") || key.equals("animania:cooked_large_black_bacon"))
			{
				entry.remap(FarmAddonItemHandler.cookedPrimePork);
				continue;
			}
			if (key.matches("animania:cooked_(.*?)_roast"))
			{
				entry.remap(FarmAddonItemHandler.cookedPrimeBeef);
				continue;
			}
			if (key.matches("animania:cooked_(.*?)_steak"))
			{
				entry.remap(FarmAddonItemHandler.cookedPrimeSteak);
				continue;
			}
			if (key.matches("animania:raw_(.*?)_pork"))
			{
				entry.remap(FarmAddonItemHandler.rawPrimePork);
				continue;
			}
			if (key.matches("animania:raw_(.*?)_bacon"))
			{
				entry.remap(FarmAddonItemHandler.rawPrimeBacon);
				continue;
			}
			if (key.matches("animania:cooked_(.*?)_bacon"))
			{
				entry.remap(FarmAddonItemHandler.cookedPrimeBacon);
				continue;
			}
			if (key.matches("animania:raw_(.*?)_chicken"))
			{
				entry.remap(FarmAddonItemHandler.rawPrimeChicken);
				continue;
			}
			if (key.matches("animania:cooked_(.*?)_chicken"))
			{
				entry.remap(FarmAddonItemHandler.cookedPrimeChicken);
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
		}
	}

}
