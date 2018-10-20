package com.animania.common.entities.chickens;

import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityHenWyandotte extends EntityHenBase
{

	public EntityHenWyandotte(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.WYANDOTTE;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/hen_brown.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
		this.lidCol = 0x362018;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 8219743;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 5129532;
	}
}