package com.animania.addons.catsdogs.common.entity.felids;

import net.minecraft.world.level.Level;

public class CatExotic
{

	public static class EntityTomExotic extends EntityTomBase
	{
		public EntityTomExotic(Level worldIn)
		{
			super(worldIn);
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
		public EntityQueenExotic(Level worldIn)
		{
			super(worldIn);
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
		public EntityKittenExotic(Level worldIn)
		{
			super(worldIn);
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
