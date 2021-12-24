package com.animania.addons.farm.common.entity.horses;

import net.minecraft.world.level.Level;

public class HorseDraft
{

	public static class EntityFoalDraftHorse extends EntityFoalBase
	{

		public EntityFoalDraftHorse(Level world)
		{
			super(world);
			this.horseType = HorseType.DRAFT;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8600606;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 12829635;
		}

	}

	public static class EntityStallionDraftHorse extends EntityStallionBase
	{

		public EntityStallionDraftHorse(Level world)
		{
			super(world);
			this.horseType = horseType.DRAFT;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8600606;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 12829635;
		}

	}

	public static class EntityMareDraftHorse extends EntityMareBase
	{

		public EntityMareDraftHorse(Level world)
		{
			super(world);
			this.horseType = HorseType.DRAFT;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 8600606;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 12829635;
		}

	}

}
