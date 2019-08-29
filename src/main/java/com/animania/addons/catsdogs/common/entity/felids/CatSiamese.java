package com.animania.addons.catsdogs.common.entity.felids;

import net.minecraft.world.World;

public class CatSiamese
{

	public static class EntityTomSiamese extends EntityTomBase
	{
		public EntityTomSiamese(World worldIn)
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
		public EntityQueenSiamese(World worldIn)
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
		public EntityKittenSiamese(World worldIn)
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
