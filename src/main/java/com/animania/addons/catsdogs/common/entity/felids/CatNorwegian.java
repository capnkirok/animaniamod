package com.animania.addons.catsdogs.common.entity.felids;

import net.minecraft.world.level.Level;

public class CatNorwegian
{

	public static class EntityTomNorwegian extends EntityTomBase
	{
		public EntityTomNorwegian(Level worldIn)
		{
			super(worldIn);
			this.type = CatType.NORWEGIAN;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 0x3B2D25;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 0x987862;
		}
	}

	public static class EntityQueenNorwegian extends EntityQueenBase {
		public EntityQueenNorwegian(Level worldIn)
		{
			super(worldIn);
			this.type = CatType.NORWEGIAN;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0x3B2D25;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 0x987862;
		}
	}

	public static class EntityKittenNorwegian extends EntityKittenBase {
		public EntityKittenNorwegian(Level worldIn)
		{
			super(worldIn);
			this.type = CatType.NORWEGIAN;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0x3B2D25;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 0x987862;
		}
	}

}
