package com.animania.common.events;


import java.util.Random;

import com.animania.Animania;
import com.animania.common.entities.chickens.EntityChickLeghorn;
import com.animania.common.entities.chickens.EntityChickOrpington;
import com.animania.common.entities.chickens.EntityChickPlymouthRock;
import com.animania.common.entities.chickens.EntityChickRhodeIslandRed;
import com.animania.common.entities.chickens.EntityChickWyandotte;
import com.animania.common.entities.chickens.EntityHenLeghorn;
import com.animania.common.entities.chickens.EntityHenOrpington;
import com.animania.common.entities.chickens.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.EntityHenWyandotte;
import com.animania.common.entities.chickens.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.EntityRoosterOrpington;
import com.animania.common.entities.chickens.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.EntityRoosterWyandotte;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class VanillaEggHandler 
{
	Random rand = new Random();
	@SubscribeEvent(priority = EventPriority.LOW)
	public void eggDrop(EntityJoinWorldEvent event)
	{		

		if (event.getEntity() instanceof EntityItem) {

			EntityItem bob = (EntityItem)event.getEntity();

			if (bob.getEntityItem().getItem() == Items.EGG) {

				int esize = event.getEntity().world.loadedEntityList.size();
				for (int k = 0; k <= esize - 1; k++) {
					Entity entity = (Entity) event.getEntity().world.loadedEntityList.get(k);

					if(entity instanceof EntityChicken) {

						if (entity.getClass().equals(EntityChickLeghorn.class) || entity.getClass().equals(EntityHenLeghorn.class) || entity.getClass().equals(EntityRoosterLeghorn.class)
								|| entity.getClass().equals(EntityChickOrpington.class) || entity.getClass().equals(EntityHenOrpington.class) || entity.getClass().equals(EntityRoosterOrpington.class)
								|| entity.getClass().equals(EntityChickWyandotte.class) || entity.getClass().equals(EntityHenWyandotte.class) || entity.getClass().equals(EntityRoosterWyandotte.class)
								|| entity.getClass().equals(EntityChickRhodeIslandRed.class) || entity.getClass().equals(EntityHenRhodeIslandRed.class) || entity.getClass().equals(EntityRoosterRhodeIslandRed.class)
								|| entity.getClass().equals(EntityChickPlymouthRock.class) || entity.getClass().equals(EntityHenPlymouthRock.class) || entity.getClass().equals(EntityRoosterPlymouthRock.class)) {

							double xt = entity.posX;
							double yt = entity.posY;
							double zt = entity.posZ;
							int x1 = MathHelper.floor(bob.posX);
							int y1 = MathHelper.floor(bob.posY);
							int z1 = MathHelper.floor(bob.posZ);
							double x2 = xt - x1;
							double y2 = yt - y1;
							double z2 = zt - z1;

							if (MathHelper.abs((int)x2) < 2 && MathHelper.abs((int)z2) < 2 && MathHelper.abs((int)y2) < 2) {

								if (!AnimaniaConfig.drops.chickensDropEggs) {
									event.setCanceled(true);
								}

								if (rand.nextInt(2) < 4 && AnimaniaConfig.drops.chickensDropFeathers) {
									ItemStack bob2 = new ItemStack(Items.FEATHER);
									EntityItem entityitem = new EntityItem(event.getEntity().world, entity.posX + 0.5D, entity.posY + 0.5D, entity.posZ + 0.5D, bob2);
									event.getEntity().world.spawnEntity(entityitem);
								}
								break;

							}
						}

					}
				}
			}

		}

	}

}


