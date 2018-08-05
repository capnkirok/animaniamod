package com.animania.common.handler;

import com.animania.client.gui.GuiCartChest;
import com.animania.common.entities.props.EntityCart;
import com.animania.common.entities.props.EntityTiller;
import com.animania.common.entities.props.EntityWagon;
import com.animania.common.inventory.ContainerHorseCart;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


public class GuiHandlerAnimania implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		int esize;
		Entity entity;
		int entityID = 0;

		switch(id)
		{
		case 0:
			esize = world.loadedEntityList.size(); 
			entityID = 0;
			for (int k = 0; k <= esize - 1; k++) {

				entity = (Entity)world.loadedEntityList.get(k);
				entityID = entity.getEntityId();
				if (entityID == x) {
					EntityCart cart = (EntityCart) entity;
					IInventory cartInv = cart.cartChest;
					return new ContainerHorseCart(player.inventory, cartInv, player);
				}
			}
			break;

		case 1:
			esize = world.loadedEntityList.size(); 
			entityID = 0;
			for (int k = 0; k <= esize - 1; k++) {

				entity = (Entity)world.loadedEntityList.get(k);
				entityID = entity.getEntityId();
				if (entityID == x) {
					EntityWagon wagon = (EntityWagon) entity;
					IInventory wagonInv = wagon.wagonChest;
					return new ContainerHorseCart(player.inventory, wagonInv, player);
				}
			}
			break;
			
		case 2:
			esize = world.loadedEntityList.size(); 
			entityID = 0;
			for (int k = 0; k <= esize - 1; k++) {

				entity = (Entity)world.loadedEntityList.get(k);
				entityID = entity.getEntityId();
				if (entityID == x) {
					EntityTiller tiller = (EntityTiller) entity;
					IInventory cartInv = tiller.cartChest;
					return new ContainerHorseCart(player.inventory, cartInv, player);
				}
			}
			break;
		}
		return null;
	}





	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{

		int esize;
		Entity entity;
		int entityID = 0;

		switch(id)
		{
		case 0:
			esize = world.loadedEntityList.size(); 
			entityID = 0;
			for (int k = 0; k <= esize - 1; k++) {

				entity = (Entity)world.loadedEntityList.get(k);
				entityID = entity.getEntityId();
				if (entityID == x) {
					EntityCart cart = (EntityCart) entity;
					IInventory cartInv = cart.cartChest;
					return new GuiCartChest(player.inventory, cartInv);
				}

			}
			break;
			
		case 1:
			esize = world.loadedEntityList.size(); 
			entityID = 0;
			for (int k = 0; k <= esize - 1; k++) {

				entity = (Entity)world.loadedEntityList.get(k);
				entityID = entity.getEntityId();
				if (entityID == x) {
					EntityWagon wagon = (EntityWagon) entity;
					IInventory wagonInv = wagon.wagonChest;
					return new GuiCartChest(player.inventory, wagonInv);
				}

			}
			break;
			
		case 2:
			esize = world.loadedEntityList.size(); 
			entityID = 0;
			for (int k = 0; k <= esize - 1; k++) {

				entity = (Entity)world.loadedEntityList.get(k);
				entityID = entity.getEntityId();
				if (entityID == x) {
					EntityTiller tiller = (EntityTiller) entity;
					IInventory cartInv = tiller.cartChest;
					return new GuiCartChest(player.inventory, cartInv);
				}

			}
			break;

		}

		return null;
	}
}
