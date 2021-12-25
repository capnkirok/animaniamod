package com.animania.addons.catsdogs.common.entity.felids;

public class CatNorwegian
{

	public static class EntityTomNorwegian extends EntityTomBase
	{
		public EntityTomNorwegian(Level levelIn)
		{
			super(levelIn);
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

	public static class EntityQueenNorwegian extends EntityQueenBase
	{
		public EntityQueenNorwegian(Level levelIn)
		{
			super(levelIn);
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

	public static class EntityKittenNorwegian extends EntityKittenBase
	{
		public EntityKittenNorwegian(Level levelIn)
		{
			super(levelIn);
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
