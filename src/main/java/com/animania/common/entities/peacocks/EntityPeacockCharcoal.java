package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeacockCharcoal extends EntityPeacockBase
{

	public EntityPeacockCharcoal(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.CHARCOAL;
		this.drop = ItemHandler.peacockFeatherCharcoal;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_charcoal.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_charcoal_blink.png");
	}
}