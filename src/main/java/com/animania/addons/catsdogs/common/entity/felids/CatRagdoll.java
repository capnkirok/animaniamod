package com.animania.addons.catsdogs.common.entity.felids;

import net.minecraft.world.World;

public class CatRagdoll
{

	public static class EntityTomRagdoll extends EntityTomBase
	{
		public EntityTomRagdoll(World worldIn)
		{
			super(worldIn);
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

	public static class EntityQueenRagdoll extends EntityQueenBase {
		public EntityQueenRagdoll(World worldIn)
		{
			super(worldIn);
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

	public static class EntityKittenRagdoll extends EntityKittenBase {
		public EntityKittenRagdoll(World worldIn)
		{
			super(worldIn);
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
