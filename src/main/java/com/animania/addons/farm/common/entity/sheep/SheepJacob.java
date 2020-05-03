package com.animania.addons.farm.common.entity.sheep;

import java.util.ArrayList;
import java.util.List;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SheepJacob
{

	public static class EntityRamJacob extends EntityRamBase
	{
	
		public EntityRamJacob(World worldIn)
		{
			super(worldIn);
			this.sheepType = SheepType.JACOB;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 15921647;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 2368548;
		}
		
		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
			
			this.setSheared(true);
			int i = 1 + this.rand.nextInt(2);
			
			List<ItemStack> woolDrops = new ArrayList<ItemStack>();
			woolDrops.add(new ItemStack(FarmAddonBlockHandler.blockAnimaniaWool, i, 3));
			
			return woolDrops;
		}
		
	}

	public static class EntityLambJacob extends EntityLambBase
	{
	
		public EntityLambJacob(World worldIn)
		{
			super(worldIn);
			this.sheepType = SheepType.JACOB;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 15921647;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 2368548;
		}
	
	}

	public static class EntityEweJacob extends EntityEweBase
	{
	
		public EntityEweJacob(World worldIn)
		{
			super(worldIn);
			this.sheepType = SheepType.JACOB;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 15921647;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 2368548;
		}
	
		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
			
			this.setSheared(true);
			int i = 1 + this.rand.nextInt(2);
			
			List<ItemStack> woolDrops = new ArrayList<ItemStack>();
			woolDrops.add(new ItemStack(FarmAddonBlockHandler.blockAnimaniaWool, i, 3));
			
			return woolDrops;
		}
		
	}

}
