package com.animania.addons.extra.common.events;

import com.animania.Animania;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;
import com.animania.addons.extra.network.CapSyncPacket;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.registries.ForgeRegistries;

public class CarryInteractHandler
{
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{
		PlayerEntity player = event.getPlayer();
		ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
		World world = event.getWorld();

		if (props.isCarrying() && player.isSneaking())
		{
			player.swingArm(EnumHand.MAIN_HAND);
			player.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0F, (Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F);

			if (!world.isRemote)
			{
				Entity e = EntityList.createEntityByIDFromName(new ResourceLocation(Animania.MODID, props.getType()), world);

				if (e != null)
				{
					e.readFromNBT(props.getAnimal());

					BlockPos pos = event.getPos();
					e.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);

					AnimaniaHelper.spawnEntity(world, e);
				}

				props.setAnimal(new CompoundNBT());
				props.setCarrying(false);
				props.setType("");

				Animania.network.sendToAllAround(new CapSyncPacket(props, player.getEntityId()), new NetworkRegistry.TargetPoint(player.level.provider.getDimension(), player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 64));
			}

			event.setCanceled(true);
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
			if (key.equals("animania:entity_egg_dart_frog"))
			{
				ResourceLocation egg = new ResourceLocation("animania:entity_egg_dartfrog");
				entry.remap(ForgeRegistries.ITEMS.getValue(egg));
				continue;
			}

		}
	}
}
