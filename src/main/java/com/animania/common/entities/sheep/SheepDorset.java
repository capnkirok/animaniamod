package com.animania.common.entities.sheep;

import java.util.ArrayList;
import java.util.List;

import com.animania.common.handler.BlockHandler;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SheepDorset
{

	public static class EntityRamDorset extends EntityRamBase
	{
	
		public EntityRamDorset(World worldIn)
		{
			super(worldIn);
			this.sheepType = SheepType.DORSET;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 4863280;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 15790320;
		}
		
		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
	
			int i = 1 + this.rand.nextInt(2);
	
			List<ItemStack> woolDrops = new ArrayList<ItemStack>();
	
			switch (this.getColorNumber()) {
			case 0:
				woolDrops.add(new ItemStack((Blocks.WOOL), i, this.getDyeColor().getMetadata()));
				break;
			case 1:
				woolDrops.add(new ItemStack((BlockHandler.blockAnimaniaWool), i, 0));
				break;
			}
	
			this.setSheared(true);
	
			return woolDrops;
		}
	
		@Override
		public boolean isDyeable()
		{
			switch (this.getColorNumber())
			{
			case 0:
				return true;
			default:
				return false;
			}
		}
	
		
	}

	public static class EntityLambDorset extends EntityLambBase
	{
	
		public EntityLambDorset(World worldIn)
		{
			super(worldIn);
			this.sheepType = SheepType.DORSET;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 4863280;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 15790320;
		}
	}

	public static class EntityEweDorset extends EntityEweBase
	{
	
		public EntityEweDorset(World worldIn)
		{
			super(worldIn);
			this.sheepType = SheepType.DORSET;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 4863280;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 15790320;
		}
		
		@Override
		public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
	
			int i = 1 + this.rand.nextInt(2);
	
			List<ItemStack> woolDrops = new ArrayList<ItemStack>();
	
			switch (this.getColorNumber()) {
			case 0:
				woolDrops.add(new ItemStack((Blocks.WOOL), i, this.getDyeColor().getMetadata()));
				break;
			case 1:
				woolDrops.add(new ItemStack((BlockHandler.blockAnimaniaWool), i, 0));
				break;
			}
	
			this.setSheared(true);
	
			return woolDrops;
		}
	
		@Override
		public boolean isDyeable()
		{
			switch (this.getColorNumber())
			{
			case 0:
				return true;
			default:
				return false;
			}
		}
	}

}
