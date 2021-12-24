package com.animania.addons.extra.common.entity.peafowl;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class PeafowlPurple
{

	public static class EntityPeachickPurple extends EntityPeachickBase
	{
	
		public EntityPeachickPurple(Level worldIn)
		{
			super(worldIn);
			this.type = PeacockType.PURPLE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_purple.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_blink.png");
			this.lidCol = 0x8B794D;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 2373476;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 3569227;
		}
	}

	public static class EntityPeacockPurple extends EntityPeacockBase
	{
	
		public EntityPeacockPurple(Level worldIn)
		{
			super(worldIn);
			this.type = PeacockType.PURPLE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_purple.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_purple_blink.png");
			this.lidCol = 0x846F75;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 2373476;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 3569227;
		}
	}

	public static class EntityPeafowlPurple extends EntityPeafowlBase
	{
	
		public EntityPeafowlPurple(Level worldIn)
		{
			super(worldIn);
			this.type = PeacockType.PURPLE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_purple.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_purple_blink.png");
			this.lidCol = 0x846F75;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 2373476;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 3569227;
		}
	}

}
