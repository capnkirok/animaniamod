package com.animania.common.entities.peacocks;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeachickPeach extends EntityPeachickBase
{

	public EntityPeachickPeach(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.PEACH;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_peach.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_peach_blink.png");
	}
}