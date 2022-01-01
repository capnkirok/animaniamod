package common.entity.pigs;

import net.minecraft.world.level.Level;

public class PigLargeWhite
{

	public static class EntityHogLargeWhite extends EntityHogBase
	{

		public EntityHogLargeWhite(Level level)
		{
			super(level);
			this.pigType = PigType.LARGE_WHITE;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15061714;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 13876669;
		}

	}

	public static class PigEntityletLargeWhite extends PigEntityletBase
	{

		public PigEntityletLargeWhite(Level level)
		{
			super(level);
			this.pigType = PigType.LARGE_WHITE;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15061714;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 13876669;
		}

	}

	public static class EntitySowLargeWhite extends EntitySowBase
	{

		public EntitySowLargeWhite(Level level)
		{
			super(level);
			this.pigType = PigType.LARGE_WHITE;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15061714;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 13876669;
		}

	}

}
