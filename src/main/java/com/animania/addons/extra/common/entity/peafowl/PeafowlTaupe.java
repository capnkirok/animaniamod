package com.animania.addons.extra.common.entity.peafowl;

import net.minecraft.resources.ResourceLocation;

public class PeafowlTaupe
{

	public static class EntityPeafowlTaupe extends EntityPeafowlBase
	{
	
		public EntityPeafowlTaupe(Level levelIn)
		{
			super(levelIn);
			this.type = PeacockType.TAUPE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_taupe.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_taupe_blink.png");
			this.lidCol = 0xA7988E;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 12427148;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 7102038;
		}
	}

	public static class EntityPeacockTaupe extends EntityPeacockBase
	{
	
		public EntityPeacockTaupe(Level levelIn)
		{
			super(levelIn);
			this.type = PeacockType.TAUPE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_taupe.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_taupe_blink.png");
			this.lidCol = 0xA7988E;
	
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 12427148;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 7102038;
		}
	}

	public static class EntityPeachickTaupe extends EntityPeachickBase
	{
	
		public EntityPeachickTaupe(Level levelIn)
		{
			super(levelIn);
			this.type = PeacockType.TAUPE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_taupe.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_blink.png");
			this.lidCol = 0xA7988E;
		}
		
		@Override
		public int getPrimaryEggColor()
		{
			return 12427148;
		}
		
		@Override
		public int getSecondaryEggColor()
		{
			return 7102038;
		}
	}

}
