package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeacockWhite extends EntityPeacockBase
{

	public EntityPeacockWhite(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.WHITE;
		this.drop = ItemHandler.peacockFeatherWhite;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_white.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_white_blink.png");
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15658734;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 13421772;
	}
}