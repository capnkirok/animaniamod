package com.animania.common.entities.sheep;

import java.util.ArrayList;
import java.util.List;

import com.animania.common.handler.BlockHandler;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityEweMerino extends EntityEweBase
{

	public EntityEweMerino(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.MERINO;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15526109;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 11904114;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {

		int i = 1 + this.rand.nextInt(2);

		List<ItemStack> woolDrops = new ArrayList<ItemStack>();

		switch (this.getColorNumber()) {
		case 0:
			woolDrops.add(new ItemStack((BlockHandler.blockAnimaniaWool), i, 5));
			break;
		case 1:
			woolDrops.add(new ItemStack((BlockHandler.blockAnimaniaWool), i, 5));
			break;
		}

		this.setSheared(true);

		return woolDrops;
	}
	
}
