package com.animania.addons.extra.common.entity.rodents.rabbits;

import net.minecraft.world.World;

public class RabbitLop
{

	public static class EntityRabbitKitLop extends EntityRabbitKitBase
	{
	
		public EntityRabbitKitLop(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.LOP;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 16513763;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 12883817;
		}
		
	}

	public static class EntityRabbitDoeLop extends EntityRabbitDoeBase
	{
	
		public EntityRabbitDoeLop(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.LOP;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 16513763;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 12883817;
		}
		
	}

	public static class EntityRabbitBuckLop extends EntityRabbitBuckBase
	{
	
		public EntityRabbitBuckLop(World worldIn)
		{
			super(worldIn);
			this.rabbitType = RabbitType.LOP;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 16513763;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 12883817;
		}
	}

}
