package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.World;

public class DogDachshund
{

	public static class EntityFemaleDachshund extends EntityFemaleDogBase
	{
	
		public EntityFemaleDachshund(World world)
		{
			super(world);
			this.type = DogType.DACHSHUND;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -197380;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -15988473;
		}
	}

	public static class EntityMaleDachshund extends EntityMaleDogBase
	{
	
		public EntityMaleDachshund(World world)
		{
			super(world);
			this.type = DogType.DACHSHUND;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -197380;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -15988473;
		}
	}

	public static class EntityPuppyDachshund extends EntityPuppyBase
	{
	
		public EntityPuppyDachshund(World world)
		{
			super(world);
			this.type = DogType.DACHSHUND;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -197380;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -15988473;
		}
	}

}
