package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.World;

public class RabbitChinchilla
{

	public static class RabbitEntityKitChinchilla extends RabbitEntityKitBase
	{
	
		public RabbitEntityKitChinchilla(World worldIn)
		{
			super(worldIn);
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
	
		public RabbitEntityDoeChinchilla(World worldIn)
		{
			super(worldIn);
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
	
		public RabbitEntityBuckChinchilla(World worldIn)
		{
			super(worldIn);
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
