package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.World;

public class RabbitHavana
{

	public static class RabbitEntityKitHavana extends RabbitEntityKitBase
	{
	
		public RabbitEntityKitHavana(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.HAVANA;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 4079166;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 0;
		}
		
	}

	public static class RabbitEntityDoeHavana extends RabbitEntityDoeBase
	{
	
		public RabbitEntityDoeHavana(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.HAVANA;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 4079166;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 0;
		}
		
	}

	public static class RabbitEntityBuckHavana extends RabbitEntityBuckBase
	{
	
		public RabbitEntityBuckHavana(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.HAVANA;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 4079166;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 0;
		}
	}

}
