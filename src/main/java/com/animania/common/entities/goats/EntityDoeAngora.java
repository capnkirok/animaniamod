package com.animania.common.entities.goats;

import java.util.List;

import com.animania.common.handler.ItemHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class EntityDoeAngora extends EntityDoeBase implements IShearable
{

	public EntityDoeAngora(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.ANGORA;
		this.setSize(1.5F, 1.4F);
		this.dropRaw = ItemHandler.rawChevon;
		this.dropCooked = ItemHandler.cookedChevon;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 16776179;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 13814191;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		// TODO Auto-generated method stub
		return null;
	}


}
