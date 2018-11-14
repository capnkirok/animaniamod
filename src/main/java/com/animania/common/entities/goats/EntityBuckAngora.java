package com.animania.common.entities.goats;

import java.util.List;

import com.animania.common.handler.ItemHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class EntityBuckAngora extends EntityBuckBase implements IShearable
{

	public EntityBuckAngora(World worldIn)
	{
		super(worldIn);
		this.goatType = GoatType.ANGORA;
		this.setSize(1.6F, 1.4F); 
		this.width = 1.6F;
		this.height = 1.4F;
		this.width = 1.6F;

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
		return false;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return null;
	}

}
