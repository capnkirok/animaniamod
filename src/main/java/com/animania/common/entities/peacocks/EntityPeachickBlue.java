package com.animania.common.entities.peacocks;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeachickBlue extends EntityPeachickBase
{

	public EntityPeachickBlue(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.BLUE;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_blue.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_blue_blink.png");
	}
}