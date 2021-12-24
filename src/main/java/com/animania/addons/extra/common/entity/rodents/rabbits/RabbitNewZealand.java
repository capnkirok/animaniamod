package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.level.Level;

public class RabbitNewZealand
{

	public static class RabbitEntityKitNewZealand extends RabbitEntityKitBase
	{
	
		public RabbitEntityKitNewZealand(Level worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.NEW_ZEALAND;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 16513529;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 14211031;
		}
		
	}

	public static class RabbitEntityDoeNewZealand extends RabbitEntityDoeBase
	{
	
		public RabbitEntityDoeNewZealand(Level worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.NEW_ZEALAND;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 16513529;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 14211031;
		}
		
	}

	public static class RabbitEntityBuckNewZealand extends RabbitEntityBuckBase
	{
	
		public RabbitEntityBuckNewZealand(Level worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.NEW_ZEALAND;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 16513529;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 14211031;
		}
	}

}
