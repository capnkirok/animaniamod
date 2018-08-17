package com.animania.common.helper;

import java.util.ArrayList;
import java.util.List;

import com.animania.Animania;
import com.animania.common.capabilities.CapabilityRefs;
import com.animania.common.capabilities.ICapabilityPlayer;
import com.animania.common.entities.props.EntityCart;
import com.animania.common.entities.props.EntityTiller;
import com.animania.common.entities.props.EntityWagon;
import com.animania.network.client.TileEntitySyncPacket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.versioning.VersionRange;

public class AnimaniaHelper
{
	
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	public static ItemStack getItem(String name)
	{
		ItemStack stack = ItemStack.EMPTY;
		int metaLoc = 0;
		boolean metaFlag = false;
		String metaVal = "";

		metaLoc = name.indexOf("#");

		if (metaLoc > -1)
		{
			metaVal = name.substring(metaLoc);
			name = name.replace(metaVal, "");
			metaVal = metaVal.replace("#", "");
			metaFlag = true;
		}

		Item item = Item.getByNameOrId(name);

		if (item != null)
		{

			if (metaFlag)
			{
				stack = new ItemStack(item, 1, Integer.parseInt(metaVal));
			}
			else
			{
				stack = new ItemStack(item, 1);
			}
		}

		return stack;

	}
	
	public static void sendTileEntityUpdate(TileEntity tile)
	{
		if (tile.getWorld() != null && !tile.getWorld().isRemote)
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
	
	  public static ItemStack getItemStack(JsonObject json)
	    {
	        String itemName = JsonUtils.getString(json, "item");

	        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName));

	        if (item == null)
	            throw new JsonSyntaxException("Unknown item '" + itemName + "'");

	        if (item.getHasSubtypes() && !json.has("data"))
	            throw new JsonParseException("Missing data for item '" + itemName + "'");

	        if (json.has("nbt"))
	        {
	            // Lets hope this works? Needs test
	            try
	            {
	                JsonElement element = json.get("nbt");
	                NBTTagCompound nbt;
	                if(element.isJsonObject())
	                    nbt = JsonToNBT.getTagFromJson(GSON.toJson(element));
	                else
	                    nbt = JsonToNBT.getTagFromJson(element.getAsString());

	                NBTTagCompound tmp = new NBTTagCompound();
	                if (nbt.hasKey("ForgeCaps"))
	                {
	                    tmp.setTag("ForgeCaps", nbt.getTag("ForgeCaps"));
	                    nbt.removeTag("ForgeCaps");
	                }

	                tmp.setTag("tag", nbt);
	                tmp.setString("id", itemName);
	                tmp.setInteger("Count", JsonUtils.getInt(json, "count", 1));
	                tmp.setInteger("Damage", JsonUtils.getInt(json, "data", 0));

	                return new ItemStack(tmp);
	            }
	            catch (NBTException e)
	            {
	                throw new JsonSyntaxException("Invalid NBT Entry: " + e.toString());
	            }
	        }

	        return new ItemStack(item, JsonUtils.getInt(json, "count", 1), JsonUtils.getInt(json, "data", 0));
	    }

	public static void addItem(EntityPlayer player, ItemStack stack)
	{
		if (!player.inventory.addItemStackToInventory(stack))
			player.dropItem(stack, false);
	}

	
	public static <T extends EntityLivingBase> List<T> getEntitiesInRange(Class<? extends T> filterEntity, double range, World world, Entity theEntity)
	{
		List<T> list = world.<T>getEntitiesWithinAABB(filterEntity, new AxisAlignedBB(theEntity.posX - range, theEntity.posY - range, theEntity.posZ - range, theEntity.posX + range, theEntity.posY + range, theEntity.posZ + range));
		return list;
	}
	

	public static <T extends EntityLivingBase> List<T> getEntitiesInRange(Class<? extends T> filterEntity, double range, World world, BlockPos pos)
	{
		List<T> list = world.<T>getEntitiesWithinAABB(filterEntity, new AxisAlignedBB(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX() + range, pos.getY() + range, pos.getZ() + range));
		return list;
	}
	
	public static <T extends EntityCart> List<T> getCartsInRange(Class<? extends T> filterEntity, double range, World world, Entity theEntity)
	{
		List<T> list = world.<T>getEntitiesWithinAABB(filterEntity, new AxisAlignedBB(theEntity.posX - range, theEntity.posY - range, theEntity.posZ - range, theEntity.posX + range, theEntity.posY + range, theEntity.posZ + range));
		return list;
	}
	
	public static <T extends EntityTiller> List<T> getTillersInRange(Class<? extends T> filterEntity, double range, World world, Entity theEntity)
	{
		List<T> list = world.<T>getEntitiesWithinAABB(filterEntity, new AxisAlignedBB(theEntity.posX - range, theEntity.posY - range, theEntity.posZ - range, theEntity.posX + range, theEntity.posY + range, theEntity.posZ + range));
		return list;
	}
	
	public static <T extends EntityWagon> List<T> getWagonsInRange(Class<? extends T> filterEntity, double range, World world, Entity theEntity)
	{
		List<T> list = world.<T>getEntitiesWithinAABB(filterEntity, new AxisAlignedBB(theEntity.posX - range, theEntity.posY - range, theEntity.posZ - range, theEntity.posX + range, theEntity.posY + range, theEntity.posZ + range));
		return list;
	}
	
	public static RayTraceResult rayTrace(EntityPlayer player, double blockReachDistance)
	{
		Vec3d vec3d = player.getPositionEyes(1f);
		Vec3d vec3d1 = player.getLook(1f);
		Vec3d vec3d2 = vec3d.addVector(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
		return player.world.rayTraceBlocks(vec3d, vec3d2, false, false, true);
	}

	public static boolean hasFluid(ItemStack stack, Fluid fluid)
	{
		return FluidUtil.getFluidContained(stack) != null && FluidUtil.getFluidContained(stack).amount >= 1000 && FluidUtil.getFluidContained(stack).getFluid() == fluid;
	}
	
	public static boolean isEmptyFluidContainer(ItemStack stack)
	{
		return FluidUtil.getFluidHandler(stack) != null && FluidUtil.getFluidContained(stack) == null;
	}
	
	public static boolean isWaterContainer(ItemStack stack)
	{
		return hasFluid(stack, FluidRegistry.WATER);
	}
	
	public static ItemStack emptyContainer(ItemStack stack)
	{
		ItemStack copy = stack.copy();
		copy.setCount(1);
		FluidActionResult result = FluidUtil.tryEmptyContainer(copy, FluidUtil.getFluidHandler(new ItemStack(Items.BUCKET)), 1000, null, true);
		return result.result;
	}

	public static Item[] getItemArray(String[] names)
	{
		ArrayList<Item> list = new ArrayList<Item>();
		for (String name : names)
		{
			Item i = StringParser.getItem(name);
			if (i != null)
			{
				list.add(i);
			}
		}
		
		return list.toArray(new Item[list.size()]);
	}
	
	public static void syncCap(Entity entity, ICapabilityPlayer other)
	{
		ICapabilityPlayer cap = entity.getCapability(CapabilityRefs.CAPS, null);

		cap.setAnimal(other.getAnimal());
		cap.setCarrying(other.isCarrying());
		cap.setType(other.getType());
	}
	
	public static String getLowerBound(VersionRange range)
	{
		if(range == null)
			return null;
		
		return range.getLowerBoundString();
	}
	
	public static String getUpperBound(VersionRange range)
	{
		if(range == null)
			return null;
		if(!range.isUnboundedAbove())
			return range.getRestrictions().get(range.getRestrictions().size()-1).getUpperBound().getVersionString();
		return null;
	}

	public static Type[] getBiomeTypes(String[] types)
	{
		Type[] bt = new Type[types.length];
		for (int i = 0; i < types.length; i++)
		{
			String s = types[i].toUpperCase();
			bt[i] = Type.getType(s);
		}
		return bt;
	}
	

}
