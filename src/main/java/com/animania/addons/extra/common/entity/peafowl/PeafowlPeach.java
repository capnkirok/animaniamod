package com.animania.addons.extra.common.entity.peafowl;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PeafowlPeach
{
	public static class EntityPeachickPeach extends EntityPeachickBase
	{

		public EntityPeachickPeach(World worldIn)
		{
			super(worldIn);
			this.type = PeacockType.PEACH;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_peach.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_blink.png");
			this.lidCol = 0xB49B7F;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 12419159;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 6111535;
		}
	}

	public static class EntityPeafowlPeach extends EntityPeafowlBase
	{

		public EntityPeafowlPeach(World worldIn)
		{
			super(worldIn);
			this.type = PeacockType.PEACH;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_peach.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_peach_blink.png");
			this.lidCol = 0x7F5A41;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 12419159;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 6111535;
		}
	}

	public static class EntityPeacockPeach extends EntityPeacockBase
	{

		public EntityPeacockPeach(World worldIn)
		{
			super(worldIn);
			this.type = PeacockType.PEACH;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_peach.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_peach_blink.png");
			this.lidCol = 0x7F5A41;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 12419159;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 6111535;
		}
	}

}