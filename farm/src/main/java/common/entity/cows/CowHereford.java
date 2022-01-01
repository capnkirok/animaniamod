package common.entity.cows;

import net.minecraft.world.level.Level;

public class CowHereford
{

	public static class EntityBullHereford extends EntityBullBase
	{

		public EntityBullHereford(Level level)
		{
			super(level);
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

	public static class CowEntityHereford extends CowEntityBase
	{

		public CowEntityHereford(Level level)
		{
			super(level);
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

		public EntityCalfHereford(Level level)
		{
			super(level);
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