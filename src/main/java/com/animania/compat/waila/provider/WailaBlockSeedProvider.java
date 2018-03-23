package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.common.blocks.BlockSeeds;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WailaBlockSeedProvider implements IWailaDataProvider
{

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		if (accessor.getBlockState() != null) {

			switch (accessor.getBlockState().getValue(BlockSeeds.VARIANT))
			{
			case WHEAT:
				return new ItemStack(Items.WHEAT_SEEDS);
			case PUMPKIN:
				return new ItemStack(Items.PUMPKIN_SEEDS);
			case MELON:
				return new ItemStack(Items.MELON_SEEDS);
			case BEETROOT:
				return new ItemStack(Items.BEETROOT_SEEDS);
			default:
				return new ItemStack(Items.WHEAT_SEEDS);
			}
		} else {
			return new ItemStack(Items.WHEAT_SEEDS);
		}

	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos)
	{
		return tag;
	}

}
