package com.animania.common.entities.sheep;

import java.util.ArrayList;
import java.util.List;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityRamSuffolk extends EntityRamBase
{

	public EntityRamSuffolk(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.SUFFOLK;
		this.dropRaw = ItemHandler.rawMutton;
		this.dropCooked = ItemHandler.cookedMutton;
	}

	@Override
	public int getPrimaryEggColor()
	{
		return 4336416;
	}

	@Override
	public int getSecondaryEggColor()
	{
		return 2757652;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {

		int i = 1 + this.rand.nextInt(2);

		List<ItemStack> woolDrops = new ArrayList<ItemStack>();

		switch (this.getColorNumber()) {
		case 0:
			woolDrops.add(new ItemStack((Blocks.WOOL), i));
			break;
		case 1:
			woolDrops.add(new ItemStack((BlockHandler.blockAnimaniaWool), i, 6));
			break;
		}

		this.setSheared(true);

		return woolDrops;
	}
	
}
