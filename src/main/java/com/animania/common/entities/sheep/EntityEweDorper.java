package com.animania.common.entities.sheep;

import java.util.ArrayList;
import java.util.List;

import com.animania.common.handler.ItemHandler;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityEweDorper extends EntityEweBase
{

	public EntityEweDorper(World worldIn)
	{
		super(worldIn);
		this.sheepType = SheepType.DORPER;
		this.dropRaw = ItemHandler.rawMutton;
		this.dropCooked = ItemHandler.cookedMutton;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15987699;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 13552319;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		
		this.setSheared(true);
		int i = 1 + this.rand.nextInt(2);
		
		List<ItemStack> woolDrops = new ArrayList<ItemStack>();
		woolDrops.add(new ItemStack((Blocks.WOOL), i, this.getDyeColor().getMetadata()));
		
		return woolDrops;
	}
	
	@Override
	public boolean isDyeable()
	{
		return true;
	}
	
}
