package com.animania.common.entities.peacocks;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PeafowlOpal
{

	public static class EntityPeachickOpal extends EntityPeachickBase
	{

		public EntityPeachickOpal(World worldIn)
		{
			super(worldIn);
			this.type = PeacockType.OPAL;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_opal.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_blink.png");
			this.lidCol = 0xA1A1A1;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 5265772;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 7174504;
		}
	}

	public static class EntityPeafowlOpal extends EntityPeafowlBase
	{

		public EntityPeafowlOpal(World worldIn)
		{
			super(worldIn);
			this.type = PeacockType.OPAL;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_opal.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_opal_blink.png");
			this.lidCol = 0xBFB3AC;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 5265772;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 7174504;
		}
	}

	public static class EntityPeacockOpal extends EntityPeacockBase
	{

		public EntityPeacockOpal(World worldIn)
		{
			super(worldIn);
			this.type = PeacockType.OPAL;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_opal.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_opal_blink.png");
			this.lidCol = 0x495163;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 5265772;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 7174504;
		}
	}

}