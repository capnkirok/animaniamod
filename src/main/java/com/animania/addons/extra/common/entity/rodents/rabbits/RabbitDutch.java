package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.World;

public class RabbitDutch
{

	public static class EntityRabbitKitDutch extends EntityRabbitKitBase
	{
	
		public EntityRabbitKitDutch(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.DUTCH;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 16777215;
		}
		
	}

	public static class EntityRabbitDoeDutch extends EntityRabbitDoeBase
	{
	
		public EntityRabbitDoeDutch(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.DUTCH;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 16777215;
		}
		
	}

	public static class EntityRabbitBuckDutch extends EntityRabbitBuckBase
	{
	
		public EntityRabbitBuckDutch(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.DUTCH;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 16777215;
		}
	}

}
