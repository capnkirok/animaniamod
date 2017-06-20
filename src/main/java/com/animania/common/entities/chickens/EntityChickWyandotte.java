package com.animania.common.entities.chickens;

import java.util.Random;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityChickWyandotte extends EntityChickBase
{

	public EntityChickWyandotte(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.WYANDOTTE;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/chick_brown.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chick_brown_blink.png");
	}
}