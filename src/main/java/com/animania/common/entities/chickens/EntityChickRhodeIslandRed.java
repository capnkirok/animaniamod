package com.animania.common.entities.chickens;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityChickRhodeIslandRed extends EntityChickBase
{

	public EntityChickRhodeIslandRed(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.RHODE_ISLAND_RED;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/chick_red.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chick_red_blink.png");
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