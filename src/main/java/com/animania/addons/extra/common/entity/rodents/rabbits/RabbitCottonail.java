package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.World;

public class RabbitCottonail
{

	public static class EntityRabbitKitCottontail extends EntityRabbitKitBase
	{
	
		public EntityRabbitKitCottontail(World worldIn)
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

	public static class EntityRabbitDoeCottontail extends EntityRabbitDoeBase
	{
	
		public EntityRabbitDoeCottontail(World worldIn)
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

	public static class EntityRabbitBuckCottontail extends EntityRabbitBuckBase
	{
	
		public EntityRabbitBuckCottontail(World worldIn)
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
