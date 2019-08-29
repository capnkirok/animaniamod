package com.animania.common.entities.rodents.rabbits;

import net.minecraft.world.World;

public class RabbitNewZealand
{

	public static class EntityRabbitKitNewZealand extends EntityRabbitKitBase
	{
	
		public EntityRabbitKitNewZealand(World worldIn)
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

	public static class EntityRabbitDoeNewZealand extends EntityRabbitDoeBase
	{
	
		public EntityRabbitDoeNewZealand(World worldIn)
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

	public static class EntityRabbitBuckNewZealand extends EntityRabbitBuckBase
	{
	
		public EntityRabbitBuckNewZealand(World worldIn)
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
