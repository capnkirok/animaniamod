package com.animania.addons.catsdogs.common.entity.felids;

import net.minecraft.world.level.Level;

public class CatAmericanShorthair
{

	public static class EntityKittenAmericanShorthair extends EntityKittenBase
	{
		public EntityKittenAmericanShorthair(Level worldIn)
		{
			super(worldIn);
			this.type = CatType.AMERICAN_SHORTHAIR;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 0x717171;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 0x000000;
		}
	}

	public static class EntityQueenAmericanShorthair extends EntityQueenBase {
		public EntityQueenAmericanShorthair(Level worldIn)
		{
			super(worldIn);
			this.type = CatType.AMERICAN_SHORTHAIR;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0x717171;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 0x000000;
		}
	}

	public static class EntityTomAmericanShorthair extends EntityTomBase
	{
		public EntityTomAmericanShorthair(Level worldIn)
		{
			super(worldIn);
			this.type = CatType.AMERICAN_SHORTHAIR;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 0x717171;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 0x000000;
		}
	}

}
