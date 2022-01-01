package com.animania.catsdogs.common.entity.felids;

import net.minecraft.world.level.Level;

public class CatRagdoll
{

	public static class EntityTomRagdoll extends EntityTomBase
	{
		public EntityTomRagdoll(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.RAGDOLL;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 13948116;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 8741209;
		}
	}

	public static class EntityQueenRagdoll extends EntityQueenBase
	{
		public EntityQueenRagdoll(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.RAGDOLL;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 13948116;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 8741209;
		}
	}

	public static class EntityKittenRagdoll extends EntityKittenBase
	{
		public EntityKittenRagdoll(Level levelIn)
		{
			super(levelIn);
			this.type = CatType.RAGDOLL;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 13948116;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 8741209;
		}
	}

}
