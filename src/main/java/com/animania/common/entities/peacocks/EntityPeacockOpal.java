package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeacockOpal extends EntityPeacockBase
{

	public EntityPeacockOpal(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.OPAL;
		this.drop = ItemHandler.peacockFeatherOpal;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_opal.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_opal_blink.png");
	}
}