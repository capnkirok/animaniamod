package com.animania.common.entities.peacocks;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPeacockPurple extends EntityPeacockBase
{

	public EntityPeacockPurple(World worldIn)
	{
		super(worldIn);
		this.type = PeacockType.PURPLE;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/peacocks/peacock_purple.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/peacocks/peacock_purple_blink.png");
		this.lidCol = 0x846F75;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 2373476;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 3569227;
	}
}