package com.animania.common.helper;

import java.util.List;

import com.animania.Animania;
import com.animania.common.entities.goats.EntityBuckBase;
import com.animania.network.client.TileEntitySyncPacket;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidUtil;
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

	
	public static <T extends EntityLivingBase> List<T> getEntitiesInRange(Class<? extends T> filterEntity, double range, World world, Entity theEntity)
    {
        List<T> list = world.<T>getEntitiesWithinAABB(filterEntity, theEntity.getEntityBoundingBox().expandXyz(range));
        return list;
    }
	
	
	public static RayTraceResult rayTrace(EntityPlayer player, double blockReachDistance)
    {
        Vec3d vec3d = player.getPositionEyes(1f);
        Vec3d vec3d1 = player.getLook(1f);
        Vec3d vec3d2 = vec3d.addVector(vec3d1.xCoord * blockReachDistance, vec3d1.yCoord * blockReachDistance, vec3d1.zCoord * blockReachDistance);
        return player.world.rayTraceBlocks(vec3d, vec3d2, false, false, true);
    }
	
	
	public static boolean hasFluid(ItemStack stack, Fluid fluid)
	{
		return FluidUtil.getFluidContained(stack) != null && FluidUtil.getFluidContained(stack).amount >= 1000 && FluidUtil.getFluidContained(stack).getFluid() == fluid;
	}
	

}
