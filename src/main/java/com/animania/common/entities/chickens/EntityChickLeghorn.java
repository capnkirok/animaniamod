package com.animania.common.entities.chickens;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityChickLeghorn extends EntityChickBase
{

	public EntityChickLeghorn(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.LEGHORN;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/chick_white.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chick_white_blink.png");
	}
}