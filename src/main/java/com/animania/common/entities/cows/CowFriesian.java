package com.animania.common.entities.cows;

import com.animania.common.handler.BlockHandler;

import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public class CowFriesian
{
	public static class EntityBullFriesian extends EntityBullBase
	{

		public EntityBullFriesian(World world)
		{
			super(world);
			this.cowType = CowType.FRIESIAN;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15987699;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 3944229;
		}

	}
	
	public static class EntityCalfFriesian extends EntityCalfBase
	{

		public EntityCalfFriesian(World world)
		{
			super(world);
			this.cowType = CowType.FRIESIAN;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 15987699;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 3944229;
		}

	}
	
	public static class EntityCowFriesian extends EntityCowBase
	{

		public EntityCowFriesian(World world)
		{
			super(world);
			this.cowType = CowType.FRIESIAN;
			this.milk = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkFriesian);
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 15987699;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 3944229;
		}

	}
	
}