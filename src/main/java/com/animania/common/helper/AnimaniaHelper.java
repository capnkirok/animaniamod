package com.animania.common.helper;

import java.util.List;

import com.animania.Animania;
import com.animania.network.client.TileEntitySyncPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class AnimaniaHelper
{

	public static ItemStack getItem(String name)
	{
		ItemStack stack = ItemStack.EMPTY;
		int metaLoc = 0;
		boolean metaFlag = false;
		String metaVal = "";

		metaLoc = name.indexOf("#");

		if (metaLoc > -1) {
			metaVal = name.substring(metaLoc);
			name = name.replace(metaVal, "");
			metaVal = metaVal.replace("#", "");
			metaFlag = true;
		} 

		Item item = Item.getByNameOrId(name);

		if (item != null) {

			if (metaFlag) {
				stack = new ItemStack(item, 1, Integer.parseInt(metaVal));
			} else {
				stack = new ItemStack(item, 1);
			}
		} 

		return stack;

	}
	
	
	public static void sendTileEntityUpdate(TileEntity tile)
	{
        if(tile.getWorld() != null && !tile.getWorld().isRemote)
        {
            NBTTagCompound compound = new NBTTagCompound();
            compound = tile.writeToNBT(compound);

            NBTTagCompound data = new NBTTagCompound();
            data.setTag("data", compound);
            data.setInteger("x", tile.getPos().getX());
            data.setInteger("y", tile.getPos().getY());
            data.setInteger("z", tile.getPos().getZ());
            Animania.network.sendToAllAround(new TileEntitySyncPacket(data), new NetworkRegistry.TargetPoint(tile.getWorld().provider.getDimension(), tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), 64));
        }
    
	}
	
	public static void addItem(EntityPlayer player, ItemStack stack)
	{
		if(!player.inventory.addItemStackToInventory(stack))
			player.dropItem(stack, false);
	}

	public static List<EntityLivingBase> getEntitiesInRange(Class<? extends EntityLivingBase> filterEntity, double range, World world, Entity theEntity)
    {
        List<EntityLivingBase> list = world.<EntityLivingBase>getEntitiesWithinAABB(filterEntity, theEntity.getEntityBoundingBox().expandXyz(range));
        return list;
    }

}
