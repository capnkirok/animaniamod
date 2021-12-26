package com.animania.compat.waila.provider;

import java.util.List;

import com.animania.common.blocks.BlockSeeds;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class WailaBlockSeedProvider implements IWailaDataProvider
{

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		try
		{
			if (accessor.getBlockState() != null)
			{

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
			}
			else
			{
				return new ItemStack(Items.WHEAT_SEEDS);
			}
		}
		catch (Exception e)
		{
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
	public CompoundTag getNBTData(ServerPlayer player, BlockEntity te, CompoundTag tag, Level level, BlockPos pos)
	{
		return tag;
	}

}
