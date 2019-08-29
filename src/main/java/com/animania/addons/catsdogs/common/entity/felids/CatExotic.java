package com.animania.addons.catsdogs.common.entity.felids;

import net.minecraft.world.World;

public class CatExotic
{

	public static class EntityTomExotic extends EntityTomBase
	{
		public EntityTomExotic(World worldIn)
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
		public EntityQueenExotic(World worldIn)
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
		public EntityKittenExotic(World worldIn)
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
