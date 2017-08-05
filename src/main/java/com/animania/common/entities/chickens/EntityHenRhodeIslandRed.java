package com.animania.common.entities.chickens;

import com.animania.common.entities.peacocks.EntityPeachickBase;
import com.animania.common.entities.peacocks.PeacockType;
import com.animania.common.handler.ItemHandler;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityHenRhodeIslandRed extends EntityHenBase
{

	public EntityHenRhodeIslandRed(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.RHODE_ISLAND_RED;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/hen_red.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/hen_red_blink.png");
		this.dropRaw = ItemHandler.rawRhodeIslandRedChicken;
		this.dropCooked = ItemHandler.cookedRhodeIslandRedChicken;
	}
}