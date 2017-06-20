package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeafowlWhite extends EntityPeafowlBase
{

	public EntityPeafowlWhite(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.WHITE;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peafowl_white.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peafowl_white_blink.png");
	}
}