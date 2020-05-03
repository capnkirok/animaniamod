package com.animania.addons.farm.common.entity.sheep;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SheepDorper
{

	public static class EntityRamDorper extends EntityRamBase
	{
	
		public EntityRamDorper(World worldIn)
		{
			super(worldIn);
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

	public static class EntityLambDorper extends EntityLambBase
	{
	
		public EntityLambDorper(World worldIn)
		{
			super(worldIn);
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
	
		public EntityEweDorper(World worldIn)
		{
			super(worldIn);
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

}
