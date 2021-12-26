package com.animania.addons.farm.common.entity.sheep;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SheepDorper
{

	public static class EntityRamDorper extends EntityRamBase
	{

		public EntityRamDorper(Level levelIn)
		{
			super(levelIn);
			this.sheepType = SheepType.DORPER;
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
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess level, BlockPos pos, int fortune)
		{

			this.setSheared(true);
			int i = 1 + this.rand.nextInt(2);

			List<ItemStack> woolDrops = new ArrayList<>();
			woolDrops.add(new ItemStack(Blocks.WOOL, i, this.getDyeColor().getMetadata()));

			return woolDrops;
		}

		@Override
		public boolean isDyeable()
		{
			return true;
		}

	}

	public static class EntityLambDorper extends EntityLambBase
	{

		public EntityLambDorper(Level levelIn)
		{
			super(levelIn);
			this.sheepType = SheepType.DORPER;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15987699;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 1776411;
		}
	}

	public static class EntityEweDorper extends EntityEweBase
	{

		public EntityEweDorper(Level levelIn)
		{
			super(levelIn);
			this.sheepType = SheepType.DORPER;
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
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess level, BlockPos pos, int fortune)
		{

			this.setSheared(true);
			int i = 1 + this.rand.nextInt(2);

			List<ItemStack> woolDrops = new ArrayList<>();
			woolDrops.add(new ItemStack(Blocks.WOOL, i, this.getDyeColor().getMetadata()));

			return woolDrops;
		}

		@Override
		public boolean isDyeable()
		{
			return true;
		}

	}

}
