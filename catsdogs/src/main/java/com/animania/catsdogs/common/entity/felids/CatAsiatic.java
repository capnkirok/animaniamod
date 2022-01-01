package com.animania.catsdogs.common.entity.felids;

import net.minecraft.world.level.Level;

public class CatAsiatic
{

	public static class EntityTomAsiatic extends EntityTomBase
	{
		public EntityTomAsiatic(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.ASIATIC;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 0x7C6450;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 0x383838;
		}
	}

	public static class EntityQueenAsiatic extends EntityQueenBase
	{
		public EntityQueenAsiatic(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.ASIATIC;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 0x7C6450;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 0x383838;
		}
	}

	public static class EntityKittenAsiatic extends EntityKittenBase
	{
		public EntityKittenAsiatic(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.ASIATIC;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 0x7C6450;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 0x383838;
		}
	}

}
