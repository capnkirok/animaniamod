package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeacockBlue extends EntityPeacockBase
{

	public EntityPeacockBlue(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.BLUE;
		this.drop = ItemHandler.peacockFeatherBlue;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_blue.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_blue_blink.png");
	}
}