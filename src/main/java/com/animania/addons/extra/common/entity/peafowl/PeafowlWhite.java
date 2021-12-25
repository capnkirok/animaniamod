package com.animania.addons.extra.common.entity.peafowl;

import net.minecraft.resources.ResourceLocation;

public class PeafowlWhite
{

	public static class EntityPeachickWhite extends EntityPeachickBase
	{

		public EntityPeachickWhite(Level levelIn)
		{
			super(levelIn);
			this.type = PeacockType.WHITE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_white.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_blink.png");
			this.lidCol = 0xCCCCCC;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15658734;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 13421772;
		}
	}

	public static class EntityPeacockWhite extends EntityPeacockBase
	{

		public EntityPeacockWhite(Level levelIn)
		{
			super(levelIn);
			this.type = PeacockType.WHITE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_white.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_white_blink.png");
			this.lidCol = 0xCCCCCC;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15658734;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 13421772;
		}
	}

	public static class EntityPeafowlWhite extends EntityPeafowlBase
	{

		public EntityPeafowlWhite(Level levelIn)
		{
			super(levelIn);
			this.type = PeacockType.WHITE;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_white.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_white_blink.png");
			this.lidCol = 0xCCCCCC;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15658734;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 13421772;
		}
	}

}
