package com.animania.addons.extra.common.events;

import com.animania.Animania;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;
import com.animania.addons.extra.network.CapSyncPacket;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.RegistryHelper.RItem;

import net.minecraft.core.BlockPos;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.registries.ForgeRegistries;

public class CarryInteractHandler
{
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{
		Player player = event.getPlayer();
		ICapabilityPlayer props = CapabilityRefs.getPlayerCaps(player);
		Level level = event.getLevel();

		if (props.isCarrying() && player.isSneaking())
		{
			player.swingArm(InteractionHand.MAIN_HAND);
			player.playSound(SoundEvents.ENTITY_ITEM_PICKUP, 1.0F, (Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F);

			if (!level.isClientSide)
			{
				Entity e = EntityList.createEntityByIDFromName(new ResourceLocation(Animania.MODID, props.getType()), level);

				if (e != null)
				{
					e.readFromNBT(props.getAnimal());

					BlockPos pos = event.getPos();
					e.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);

					AnimaniaHelper.spawnEntity(level, e);
				}

				props.setAnimal(new CompoundTag());
				props.setCarrying(false);
				props.setType("");

				Animania.network.sendToAllAround(new CapSyncPacket(props, player.getEntityId()), new NetworkRegistry.TargetPoint(player.level.provider.getDimension(), player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 64));
			}

			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void missingMapping(RegistryEvent.MissingMappings<RItem> event)
	{
		for (MissingMappings.Mapping<RItem> entry : event.getAllMappings())
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
			}

		}
	}
}
