package common.entity.cows;

import common.handler.FarmAddonBlockHandler;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class CowHolstein
{

	public static class EntityBullHolstein extends EntityBullBase
	{

		public EntityBullHolstein(Level level)
		{
			super(level);
			this.cowType = CowType.HOLSTEIN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15987699;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 2236962;
		}

	}

	public static class EntityCalfHolstein extends EntityCalfBase
	{

		public EntityCalfHolstein(Level level)
		{
			super(level);
			this.cowType = CowType.HOLSTEIN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15987699;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 2236962;
		}

	}

	public static class CowEntityHolstein extends CowEntityBase
	{

		public CowEntityHolstein(Level level)
		{
			super(level);
			this.cowType = CowType.HOLSTEIN;
			this.milk = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, FarmAddonBlockHandler.fluidMilkHolstein);
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15987699;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 2236962;
		}

	}
}