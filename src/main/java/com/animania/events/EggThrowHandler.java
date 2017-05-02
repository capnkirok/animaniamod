package com.animania.events;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.animania.Animania;
import com.animania.entities.rodents.EntityFerretGrey;
import com.animania.entities.rodents.EntityFerretWhite;
import com.animania.entities.rodents.EntityHedgehog;

public class EggThrowHandler 
{


	@SubscribeEvent
	public void notify(PlayerInteractEvent.RightClickItem event)
	{		

		ItemStack stack = event.getItemStack();
		EntityPlayer player = event.getEntityPlayer();
		BlockPos pos = event.getPos();
		World world = event.getWorld();

		if (stack != ItemStack.EMPTY && (stack.getItem() == Items.EGG || stack.getItem() == Animania.brownEgg)) {

			if (Animania.allowEggThrowing) {
				int esize = world.loadedEntityList.size();
				for (int k = 0; k <= esize - 1; k++) {
					Entity entity = (Entity) world.loadedEntityList.get(k);

					double xt = entity.posX;
					double yt = entity.posY;
					double zt = entity.posZ;
					int x1 = MathHelper.floor(player.posX);
					int y1 = MathHelper.floor(player.posY);
					int z1 = MathHelper.floor(player.posZ);
					double x2 = Math.abs(xt - x1);
					double y2 = Math.abs(yt - y1);
					double z2 = Math.abs(zt - z1);

					if (entity !=null && (entity instanceof EntityFerretWhite || entity instanceof EntityFerretGrey || entity instanceof EntityHedgehog) && x2 <= 3 && y2 <=2 && z2 <=3) {
						event.getEntityPlayer().swingArm(event.getHand());
						event.isCanceled();
						event.setCanceled(true);
					}
				}

			} else {
				//event.getEntityPlayer().swingArm(event.getHand());
				event.isCanceled();
				event.setCanceled(true);
			}

		}

	}

}
