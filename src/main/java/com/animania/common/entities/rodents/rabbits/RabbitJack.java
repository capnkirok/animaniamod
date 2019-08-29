package com.animania.common.entities.rodents.rabbits;

import net.minecraft.world.World;

public class RabbitJack
{

	public static class EntityRabbitKitJack extends EntityRabbitKitBase
	{
	
		public EntityRabbitKitJack(World worldIn)
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

	public static class EntityRabbitDoeJack extends EntityRabbitDoeBase
	{
	
		public EntityRabbitDoeJack(World worldIn)
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

	public static class EntityRabbitBuckJack extends EntityRabbitBuckBase
	{
	
		public EntityRabbitBuckJack(World worldIn)
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
