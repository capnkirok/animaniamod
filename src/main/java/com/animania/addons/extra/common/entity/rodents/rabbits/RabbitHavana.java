package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.World;

public class RabbitHavana
{

	public static class EntityRabbitKitHavana extends EntityRabbitKitBase
	{
	
		public EntityRabbitKitHavana(World worldIn)
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

	public static class EntityRabbitDoeHavana extends EntityRabbitDoeBase
	{
	
		public EntityRabbitDoeHavana(World worldIn)
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

	public static class EntityRabbitBuckHavana extends EntityRabbitBuckBase
	{
	
		public EntityRabbitBuckHavana(World worldIn)
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
