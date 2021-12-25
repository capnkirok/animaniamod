package com.animania.addons.catsdogs.common.entity.felids;

public class CatSiamese
{

	public static class EntityTomSiamese extends EntityTomBase
	{
		public EntityTomSiamese(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.SIAMESE;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 0xBE9474;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 0x372A20;
		}
	}

	public static class EntityQueenSiamese extends EntityQueenBase
	{
		public EntityQueenSiamese(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.SIAMESE;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 0xBE9474;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 0x372A20;
		}
	}

	public static class EntityKittenSiamese extends EntityKittenBase
	{
		public EntityKittenSiamese(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.SIAMESE;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 0xBE9474;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 0x372A20;
		}
	}

}
