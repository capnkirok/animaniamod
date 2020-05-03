package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.World;

public class RabbitChinchilla
{

	public static class EntityRabbitKitChinchilla extends EntityRabbitKitBase
	{
	
		public EntityRabbitKitChinchilla(World worldIn)
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

	public static class EntityRabbitDoeChinchilla extends EntityRabbitDoeBase
	{
	
		public EntityRabbitDoeChinchilla(World worldIn)
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

	public static class EntityRabbitBuckChinchilla extends EntityRabbitBuckBase
	{
	
		public EntityRabbitBuckChinchilla(World worldIn)
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
