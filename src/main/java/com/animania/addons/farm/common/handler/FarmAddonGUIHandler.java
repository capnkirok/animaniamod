package com.animania.addons.farm.common.handler;

import com.animania.addons.farm.client.gui.GuiCartChest;
import com.animania.addons.farm.common.entity.pullables.EntityCart;
import com.animania.addons.farm.common.entity.pullables.EntityTiller;
import com.animania.addons.farm.common.entity.pullables.EntityWagon;
import com.animania.addons.farm.common.inventory.ContainerHorseCart;
import com.animania.api.addons.IAddonGuiHandler;

import net.minecraft.inventory.IInventory;
import net.minecraft.world.entity.player.Player;

public class FarmAddonGUIHandler implements IAddonGuiHandler
{

	private int idOffset = 0;

	@Override
	public Object getServerGuiElement(int id, Player player, Level level, int x, int y, int z)
	{
		int esize;
		Entity entity;
		int entityID = 0;

		switch (id)
		{
		case 0:
			esize = level.loadedEntityList.size();
			entityID = 0;
			for (int k = 0; k <= esize - 1; k++)
			{

				entity = level.loadedEntityList.get(k);
				entityID = entity.getEntityId();
				if (entityID == x)
				{
					EntityCart cart = (EntityCart) entity;
					IInventory cartInv = cart.cartChest;
					return new ContainerHorseCart(player.inventory, cartInv, player);
				}
			}
			break;

		case 1:
			esize = level.loadedEntityList.size();
			entityID = 0;
			for (int k = 0; k <= esize - 1; k++)
			{

				entity = level.loadedEntityList.get(k);
				entityID = entity.getEntityId();
				if (entityID == x)
				{
					EntityWagon wagon = (EntityWagon) entity;
					IInventory wagonInv = wagon.wagonChest;
					return new ContainerHorseCart(player.inventory, wagonInv, player);
				}
			}
			break;

		case 2:
			esize = level.loadedEntityList.size();
			entityID = 0;
			for (int k = 0; k <= esize - 1; k++)
			{

				entity = level.loadedEntityList.get(k);
				entityID = entity.getEntityId();
				if (entityID == x)
				{
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
	public Object getClientGuiElement(int id, Player player, Level level, int x, int y, int z)
	{
		int esize;
		Entity entity;
		int entityID = 0;

		switch (id)
		{
		case 0:
			esize = level.loadedEntityList.size();
			entityID = 0;
			for (int k = 0; k <= esize - 1; k++)
			{

				entity = level.loadedEntityList.get(k);
				entityID = entity.getEntityId();
				if (entityID == x)
				{
					EntityCart cart = (EntityCart) entity;
					IInventory cartInv = cart.cartChest;
					return new GuiCartChest(player.inventory, cartInv);
				}

			}
			break;

		case 1:
			esize = level.loadedEntityList.size();
			entityID = 0;
			for (int k = 0; k <= esize - 1; k++)
			{

				entity = level.loadedEntityList.get(k);
				entityID = entity.getEntityId();
				if (entityID == x)
				{
					EntityWagon wagon = (EntityWagon) entity;
					IInventory wagonInv = wagon.wagonChest;
					return new GuiCartChest(player.inventory, wagonInv);
				}

			}
			break;

		case 2:
			esize = level.loadedEntityList.size();
			entityID = 0;
			for (int k = 0; k <= esize - 1; k++)
			{

				entity = level.loadedEntityList.get(k);
				entityID = entity.getEntityId();
				if (entityID == x)
				{
					EntityTiller tiller = (EntityTiller) entity;
					IInventory cartInv = tiller.cartChest;
					return new GuiCartChest(player.inventory, cartInv);
				}

			}
			break;
		}

		return null;
	}

	@Override
	public void setGuiIdOffset(int offset)
	{
		this.idOffset = offset;
	}

	@Override
	public int getGuiIdOffset()
	{
		return this.idOffset;
	}

}
