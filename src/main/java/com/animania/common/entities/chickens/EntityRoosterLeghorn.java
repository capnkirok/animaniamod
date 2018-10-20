package com.animania.common.entities.chickens;

import com.animania.common.helper.AnimaniaHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityRoosterLeghorn extends EntityRoosterBase
{

	public EntityRoosterLeghorn(World worldIn)
	{
		super(worldIn);
		this.type = ChickenType.LEGHORN;
		this.resourceLocation = new ResourceLocation("animania:textures/entity/chickens/rooster_white.png");
		this.resourceLocationBlink = new ResourceLocation("animania:textures/entity/chickens/chicken_blink.png");
		this.lidCol = 0xF2F2F2;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 15724527;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 14869218;
	}
}