package com.animania.addons.farm.common.entity.goats;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GoatAngora
{

	public static class EntityBuckAngora extends EntityBuckBase implements Shearable
	{

		public EntityBuckAngora(Level worldIn)
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
		public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
		{
			return false;
		}

		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
		{
			return null;
		}

	}

	public static class EntityKidAngora extends EntityKidBase
	{

		public EntityKidAngora(World worldIn)
		{
			super(worldIn);
			this.goatType = GoatType.ANGORA;
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

	}

	public static class EntityDoeAngora extends EntityDoeBase implements IShearable
	{

		public EntityDoeAngora(World worldIn)
		{
			super(worldIn);
			this.goatType = GoatType.ANGORA;
			this.setSize(1.6F, 1.4F);
			this.width = 1.6F;
			this.height = 1.4F;

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
		public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
		{
			return false;
		}

		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
		{
			return null;
		}

	}

}
