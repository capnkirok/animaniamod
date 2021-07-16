package com.animania.addons.farm.common.event;

import com.animania.addons.farm.common.handler.FarmAddonItemHandler;
import com.animania.addons.farm.config.FarmConfig;
import com.animania.common.handler.AddonInjectionHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EggThrowHandler
{

	@SubscribeEvent
	public static void notify(PlayerInteractEvent.RightClickItem event)
	{

		ItemStack stack = event.getItemStack();
		PlayerEntity player = event.getPlayerEntity();
		BlockPos pos = event.getPos();
		World world = event.getWorld();

		if (stack != ItemStack.EMPTY && (stack.getItem() == Items.EGG || stack.getItem() == FarmAddonItemHandler.brownEgg))
		{

			if (FarmConfig.settings.allowEggThrowing)
			{
				int esize = world.loadedEntityList.size();
				for (int k = 0; k <= esize - 1; k++)
				{
					Entity entity = world.loadedEntityList.get(k);

					double xt = entity.getX();
					double yt = entity.getY();
					double zt = entity.getZ();
					int x1 = MathHelper.floor(player.getX());
					int y1 = MathHelper.floor(player.getY());
					int z1 = MathHelper.floor(player.getZ());
					int x2 = (int) Math.abs(xt - x1);
					int y2 = (int) Math.abs(yt - y1);
					int z2 = (int) Math.abs(zt - z1);

					AddonInjectionHandler.runInjection("extra", "rodentEggEvent", null, entity, x2, y2, z2, event);
				}

			} else
			{
				// event.getPlayerEntity().swingArm(event.getHand());
				event.isCanceled();
				event.setCanceled(true);
			}

		}
	}

}
