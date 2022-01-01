package com.animania.catsdogs.common.entity.felids;

import net.minecraft.world.level.Level;

public class CatOcelot
{

	public static class EntityTomOcelot extends EntityTomBase
	{
		public EntityTomOcelot(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.OCELOT;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 0xB1834F;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 0x4C3822;
		}
	}

	public static class EntityQueenOcelot extends EntityQueenBase
	{
		public EntityQueenOcelot(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.OCELOT;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 0xB1834F;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 0x4C3822;
		}
	}

	public static class EntityKittenOcelot extends EntityKittenBase
	{
		public EntityKittenOcelot(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.OCELOT;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 0xB1834F;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 0x4C3822;
		}
	}

}
