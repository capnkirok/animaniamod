package com.animania.addons.farm.common.entity.cows;

import net.minecraft.world.World;

public class CowAngus
{
	private static int primary = 3028024;
	private static int secondary = 2304560;
	private static CowType type = CowType.ANGUS;
	
	public static class EntityBullAngus extends EntityBullBase
	{

		public EntityBullAngus(World world)
		{
			super(world);
			this.cowType = type;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return primary;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return secondary;
		}
	}
	
	public static class EntityCowAngus extends EntityCowBase
	{
		public EntityCowAngus(World world)
		{
			super(world);
			this.cowType = type;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return primary;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return secondary;
		}
	}
	
	
	public static class EntityCalfAngus extends EntityCalfBase
	{

		public EntityCalfAngus(World world)
		{
			super(world);
			this.cowType = type;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return primary;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return secondary;
		}


	}
	

}