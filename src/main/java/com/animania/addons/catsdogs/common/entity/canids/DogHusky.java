package com.animania.addons.catsdogs.common.entity.canids;

import net.minecraft.world.World;

public class DogHusky
{

	public static class EntityPuppyHusky extends EntityPuppyBase
	{
	
		public EntityPuppyHusky(World world)
		{
			super(world);
			this.type = DogType.HUSKY;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -14606304;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -1118482;
		}
	}

	public static class EntityMaleHusky extends EntityMaleDogBase
	{
	
		public EntityMaleHusky(World world)
		{
			super(world);
			this.type = DogType.HUSKY;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -14606304;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -1118482;
		}
	}

	public static class EntityFemaleHusky extends EntityFemaleDogBase
	{
	
		public EntityFemaleHusky(World world)
		{
			super(world);
			this.type = DogType.HUSKY;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return -14606304;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return -1118482;
		}
	}

}
