package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.level.Level;

public class RabbitCottonail
{

	public static class RabbitEntityKitCottontail extends RabbitEntityKitBase
	{
	
		public RabbitEntityKitCottontail(Level worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.COTTONTAIL;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 11310726;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 7559493;
		}
		
	}

	public static class RabbitEntityDoeCottontail extends RabbitEntityDoeBase
	{
	
		public RabbitEntityDoeCottontail(Level worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.COTTONTAIL;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 11310726;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 7559493;
		}
		
	}

	public static class RabbitEntityBuckCottontail extends RabbitEntityBuckBase
	{
	
		public RabbitEntityBuckCottontail(Level worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.COTTONTAIL;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 11310726;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 7559493;
		}
	}

}
