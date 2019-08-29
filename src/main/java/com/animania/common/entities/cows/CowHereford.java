package com.animania.common.entities.cows;

import net.minecraft.world.World;

public class CowHereford
{

	public static class EntityBullHereford extends EntityBullBase
	{

		public EntityBullHereford(World world)
		{
			super(world);
			this.cowType = CowType.HEREFORD;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 4461056;
		}

		@Override
		public int getSecondaryEggColor()
		{

			return 15987699;
		}

	}

	public static class EntityCowHereford extends EntityCowBase
	{

		public EntityCowHereford(World world)
		{
			super(world);
			this.cowType = CowType.HEREFORD;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 4461056;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 15987699;
		}

	}

	public static class EntityCalfHereford extends EntityCalfBase
	{

		public EntityCalfHereford(World world)
		{
			super(world);
			this.cowType = CowType.HEREFORD;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 4461056;
		}

		@Override
		public int getSecondaryEggColor()
		{

			return 15987699;
		}

	}

}