package com.animania.common.entities.sheep;

import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityRamFriesian extends EntityRamBase
{

	public EntityRamFriesian(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.FRIESIAN;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 2039583;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 4013373;
	}
	
	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return null;
	}

}
