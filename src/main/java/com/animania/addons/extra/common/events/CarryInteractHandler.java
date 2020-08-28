package com.animania.addons.extra.common.events;

import com.animania.Animania;
import com.animania.addons.extra.common.capabilities.CapabilityRefs;
import com.animania.addons.extra.common.capabilities.ICapabilityPlayer;
import com.animania.addons.extra.network.CapSyncPacket;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@EventBusSubscriber(modid = Animania.MODID)
public class CarryInteractHandler
{
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
	{
		EntityPlayer player = event.getEntityPlayer();
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

				props.setAnimal(new NBTTagCompound());
				props.setCarrying(false);
				props.setType("");

				Animania.network.sendToAllAround(new CapSyncPacket(props, player.getEntityId()), new NetworkRegistry.TargetPoint(player.world.provider.getDimension(), player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 64));
			}

			event.setCanceled(true);
		}
	}
}
