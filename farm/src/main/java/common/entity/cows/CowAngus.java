package common.entity.cows;

import net.minecraft.world.level.Level;

public class CowAngus
{
	private static int primary = 3028024;
	private static int secondary = 2304560;
	private static CowType type = CowType.ANGUS;

	public static class EntityBullAngus extends EntityBullBase
	{

		public EntityBullAngus(Level level)
		{
			super(level);
			this.cowType = type;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return primary;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return secondary;
		}
	}

	public static class CowEntityAngus extends CowEntityBase
	{
		public CowEntityAngus(Level level)
		{
			super(level);
			this.cowType = type;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return primary;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return secondary;
		}
	}

	public static class EntityCalfAngus extends EntityCalfBase
	{

		public EntityCalfAngus(Level level)
		{
			super(level);
			this.cowType = type;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return primary;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return secondary;
		}

	}

}