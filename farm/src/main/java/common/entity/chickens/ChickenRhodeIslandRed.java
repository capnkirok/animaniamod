package common.entity.chickens;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class ChickenRhodeIslandRed
{

	public static class EntityChickRhodeIslandRed extends EntityChickBase
	{

		public EntityChickRhodeIslandRed(Level levelIn)
		{
			super(levelIn);
			this.type = ChickenType.RHODE_ISLAND_RED;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/chick_red.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chick_blink.png");
			this.lidCol = 0xF6C132;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 13668724;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 12480342;
		}
	}

	public static class EntityRoosterRhodeIslandRed extends EntityRoosterBase
	{

		public EntityRoosterRhodeIslandRed(Level levelIn)
		{
			super(levelIn);
			this.type = ChickenType.RHODE_ISLAND_RED;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/rooster_red.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
			this.lidCol = 0x9F4931;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 13668724;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 12480342;
		}
	}

	public static class EntityHenRhodeIslandRed extends EntityHenBase
	{

		public EntityHenRhodeIslandRed(Level levelIn)
		{
			super(levelIn);
			this.type = ChickenType.RHODE_ISLAND_RED;
			this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/hen_red.png");
			this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
			this.lidCol = 0x9F4931;
		}

		@Override
		public int getPrimaryEggColor()
		{
			return 13668724;
		}

		@Override
		public int getSecondaryEggColor()
		{
			return 12480342;
		}
	}

}