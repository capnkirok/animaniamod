package com.animania.addons.farm.common.entity.sheep;

import java.util.ArrayList;
import java.util.List;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SheepSuffolk
{

	public static class EntityRamSuffolk extends EntityRamBase
	{
	
		public EntityRamSuffolk(Level worldIn)
		{
			super(worldIn);
			this.sheepType = SheepType.SUFFOLK;
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
				woolDrops.add(new ItemStack((Blocks.WOOL), i, this.getDyeColor().getMetadata()));
				break;
			case 1:
				woolDrops.add(new ItemStack((FarmAddonBlockHandler.blockAnimaniaWool), i, 6));
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

	public static class EntityLambSuffolk extends EntityLambBase
	{
	
		public EntityLambSuffolk(World worldIn)
		{
			super(worldIn);
			this.sheepType = SheepType.SUFFOLK;
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
	
	
	}

	public static class EntityEweSuffolk extends EntityEweBase
	{
	
		public EntityEweSuffolk(World worldIn)
		{
			super(worldIn);
			this.sheepType = SheepType.SUFFOLK;
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
				woolDrops.add(new ItemStack((Blocks.WOOL), i, this.getDyeColor().getMetadata()));
				break;
			case 1:
				woolDrops.add(new ItemStack((FarmAddonBlockHandler.blockAnimaniaWool), i, 6));
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
