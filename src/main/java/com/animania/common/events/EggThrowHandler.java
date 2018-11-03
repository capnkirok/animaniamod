package com.animania.common.events;

import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.handler.ItemHandler;
import com.animania.config.AnimaniaConfig;
import com.animania.manual.gui.GuiManual;
import com.animania.manual.resources.ManualResourceLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
	public void notify(PlayerInteractEvent.RightClickItem event)
	{

		ItemStack stack = event.getItemStack();
		EntityPlayer player = event.getEntityPlayer();
		BlockPos pos = event.getPos();
		World world = event.getWorld();

		if (stack != ItemStack.EMPTY && (stack.getItem() == Items.EGG || stack.getItem() == ItemHandler.brownEgg))
		{
			

			if (AnimaniaConfig.gameRules.allowEggThrowing)
			{
				int esize = world.loadedEntityList.size();
				for (int k = 0; k <= esize - 1; k++)
				{
					Entity entity = world.loadedEntityList.get(k);

					double xt = entity.posX;
					double yt = entity.posY;
					double zt = entity.posZ;
					int x1 = MathHelper.floor(player.posX);
					int y1 = MathHelper.floor(player.posY);
					int z1 = MathHelper.floor(player.posZ);
					double x2 = Math.abs(xt - x1);
					double y2 = Math.abs(yt - y1);
					double z2 = Math.abs(zt - z1);

					if (entity != null && x2 <= 3 && y2 <= 2 && z2 <= 3 && (entity instanceof EntityFerretWhite || entity instanceof EntityFerretGrey || entity instanceof EntityHedgehog))
					{
						event.getEntityPlayer().swingArm(event.getHand());
						event.isCanceled();
						event.setCanceled(true);
					}
				}

			}
			else
			{
				// event.getEntityPlayer().swingArm(event.getHand());
				event.isCanceled();
				event.setCanceled(true);
			}

		}
	}

}
