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
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/hen_brown_blink.png");
		this.oldDropRaw = ItemHandler.rawWyandotteChicken;
		this.oldDropCooked = ItemHandler.cookedWyandotteChicken;
		this.dropRaw = ItemHandler.rawPrimeChicken;
		this.dropCooked = ItemHandler.cookedPrimeChicken;
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