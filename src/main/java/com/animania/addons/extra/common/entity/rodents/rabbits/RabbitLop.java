package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.World;

public class RabbitLop
{

	public static class RabbitEntityKitLop extends RabbitEntityKitBase
	{
	
		public RabbitEntityKitLop(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.LOP;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 16513763;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 12883817;
		}
		
	}

	public static class RabbitEntityDoeLop extends RabbitEntityDoeBase
	{
	
		public RabbitEntityDoeLop(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.LOP;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 16513763;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 12883817;
		}
		
	}

	public static class RabbitEntityBuckLop extends RabbitEntityBuckBase
	{
	
		public RabbitEntityBuckLop(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.LOP;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 16513763;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 12883817;
		}
	}

}
