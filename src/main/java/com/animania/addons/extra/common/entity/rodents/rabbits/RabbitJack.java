package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.level.Level;

public class RabbitJack
{

	public static class RabbitEntityKitJack extends RabbitEntityKitBase
	{
	
		public RabbitEntityKitJack(Level worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.JACK;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 12692381;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 6640455;
		}
		
	}

	public static class RabbitEntityDoeJack extends RabbitEntityDoeBase
	{
	
		public RabbitEntityDoeJack(Level worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.JACK;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 12692381;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 6640455;
		}
		
	}

	public static class RabbitEntityBuckJack extends RabbitEntityBuckBase
	{
	
		public RabbitEntityBuckJack(Level worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.JACK;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 12692381;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 6640455;
		}
	}

}
