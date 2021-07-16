package com.animania.addons.farm.common.entity.cows;

import net.minecraft.world.World;

public class CowHighland
{

	public static class EntityBullHighland extends EntityBullBase
	{

		public EntityBullHighland(World world)
		{
			super(world);
			this.cowType = CowType.HIGHLAND;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8340777;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 2760475;
		}

	}

	public static class CowEntityHighland extends CowEntityBase
	{

		public CowEntityHighland(World world)
		{
			super(world);
			this.cowType = CowType.HIGHLAND;

		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8340777;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 2760475;
		}

	}

	public static class EntityCalfHighland extends EntityCalfBase
	{

		public EntityCalfHighland(World world)
		{
			super(world);
			this.cowType = CowType.HIGHLAND;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8340777;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 2760475;
		}

	}

}