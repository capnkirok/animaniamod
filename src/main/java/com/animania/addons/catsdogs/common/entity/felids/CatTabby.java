package com.animania.addons.catsdogs.common.entity.felids;

import net.minecraft.world.level.Level;

public class CatTabby
{

	public static class EntityTomTabby extends EntityTomBase
	{
		public EntityTomTabby(Level worldIn)
		{
			super(worldIn);
			this.type = CatType.TABBY;
		}
	
		@Override
		public int getPrimaryEggColor()
		{
			return 0x41332B;
		}
	
		@Override
		public int getSecondaryEggColor()
		{
			return 0x3E3028;
		}
	}

	public static class EntityQueenTabby extends EntityQueenBase {
		public EntityQueenTabby(Level worldIn)
		{
			super(worldIn);
			this.type = CatType.TABBY;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0x41332B;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 0x3E3028;
		}
	}

	public static class EntityKittenTabby extends EntityKittenBase {
		public EntityKittenTabby(Level worldIn)
		{
			super(worldIn);
			this.type = CatType.TABBY;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 0x41332B;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 0x3E3028;
		}
	}

}
