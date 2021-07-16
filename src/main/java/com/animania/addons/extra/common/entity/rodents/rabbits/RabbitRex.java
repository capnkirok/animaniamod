package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.World;

public class RabbitRex
{

	public static class RabbitEntityBuckRex extends RabbitEntityBuckBase
	{
	
		public RabbitEntityBuckRex(World worldIn)
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
	
		public RabbitEntityKitRex(World worldIn)
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
	
		public RabbitEntityDoeRex(World worldIn)
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
