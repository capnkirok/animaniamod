package com.animania.common.entities.peacocks;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeachickCharcoal extends EntityPeachickBase
{

	public EntityPeachickCharcoal(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.CHARCOAL;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peachick_charcoal.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peachick_charcoal_blink.png");
	}
}