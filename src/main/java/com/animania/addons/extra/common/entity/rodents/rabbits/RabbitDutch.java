package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.World;

public class RabbitDutch
{

	public static class RabbitEntityKitDutch extends RabbitEntityKitBase
	{
	
		public RabbitEntityKitDutch(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.DUTCH;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 16777215;
		}
		
	}

	public static class RabbitEntityDoeDutch extends RabbitEntityDoeBase
	{
	
		public RabbitEntityDoeDutch(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.DUTCH;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 16777215;
		}
		
	}

	public static class RabbitEntityBuckDutch extends RabbitEntityBuckBase
	{
	
		public RabbitEntityBuckDutch(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.DUTCH;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 16777215;
		}
	}

}
