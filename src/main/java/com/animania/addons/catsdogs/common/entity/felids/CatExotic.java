package com.animania.addons.catsdogs.common.entity.felids;

public class CatExotic
{

	public static class EntityTomExotic extends EntityTomBase
	{
		public EntityTomExotic(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.EXOTIC;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 0xAE5B24;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 0xD79A72;
		}
	}

	public static class EntityQueenExotic extends EntityQueenBase {
		public EntityQueenExotic(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.EXOTIC;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0xAE5B24;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 0xD79A72;
		}
	}

	public static class EntityKittenExotic extends EntityKittenBase {
		public EntityKittenExotic(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.EXOTIC;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0xAE5B24;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 0xD79A72;
		}
	}

}
