package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.level.Level;

public class RabbitRex
{

	public static class RabbitEntityBuckRex extends RabbitEntityBuckBase
	{
	
		public RabbitEntityBuckRex(Level worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.REX;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 13419709;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 5389358;
		}
	}

	public static class RabbitEntityKitRex extends RabbitEntityKitBase
	{
	
		public RabbitEntityKitRex(Level worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.REX;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 13419709;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 5389358;
		}
		
	}

	public static class RabbitEntityDoeRex extends RabbitEntityDoeBase
	{
	
		public RabbitEntityDoeRex(Level worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.REX;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 13419709;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 5389358;
		}
		
	}

}
