package com.animania.addons.extra.common.entity.rodents.rabbits;

public class RabbitChinchilla
{

	public static class RabbitEntityKitChinchilla extends RabbitEntityKitBase
	{
	
		public RabbitEntityKitChinchilla(Level levelIn)
		{
			super(levelIn);
			this.rabbitType = RabbitType.CHINCHILLA;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 13750737;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 8289918;
		}
		
	}

	public static class RabbitEntityDoeChinchilla extends RabbitEntityDoeBase
	{
	
		public RabbitEntityDoeChinchilla(Level levelIn)
		{
			super(levelIn);
			this.rabbitType = RabbitType.CHINCHILLA;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 13750737;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 8289918;
		}
		
	}

	public static class RabbitEntityBuckChinchilla extends RabbitEntityBuckBase
	{
	
		public RabbitEntityBuckChinchilla(Level levelIn)
		{
			super(levelIn);
			this.rabbitType = RabbitType.CHINCHILLA;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 13750737;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 8289918;
		}
	}

}
