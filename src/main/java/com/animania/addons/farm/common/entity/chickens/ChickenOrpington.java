package com.animania.addons.farm.common.entity.chickens;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class ChickenOrpington
{

	public static class EntityChickOrpington extends EntityChickBase
	{

		public EntityChickOrpington(Level levelIn)
		{
			super(levelIn);
			this.type = ChickenType.ORPINGTON;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/chick_golden.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chick_blink.png");
			this.lidCol = 0xF6C132;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15980429;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 13270563;
		}
	}

	public static class EntityRoosterOrpington extends EntityRoosterBase
	{

		public EntityRoosterOrpington(Level levelIn)
		{
			super(levelIn);
			this.type = ChickenType.ORPINGTON;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/rooster_golden.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
			this.lidCol = 0xCD902F;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15980429;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 13270563;
		}
	}

	public static class EntityHenOrpington extends EntityHenBase
	{

		public EntityHenOrpington(Level levelIn)
		{
			super(levelIn);
			this.type = ChickenType.ORPINGTON;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/hen_golden.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
			this.lidCol = 0xCD902F;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 15980429;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 13270563;
		}
	}

}