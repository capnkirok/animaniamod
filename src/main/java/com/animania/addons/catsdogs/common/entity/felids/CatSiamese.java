package com.animania.addons.catsdogs.common.entity.felids;

import net.minecraft.world.level.Level;

public class CatSiamese
{

	public static class EntityTomSiamese extends EntityTomBase
	{
		public EntityTomSiamese(Level worldIn)
		{
			super(worldIn);
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

	public static class EntityQueenSiamese extends EntityQueenBase {
		public EntityQueenSiamese(Level worldIn)
		{
			super(worldIn);
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

	public static class EntityKittenSiamese extends EntityKittenBase {
		public EntityKittenSiamese(Level worldIn)
		{
			super(worldIn);
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
