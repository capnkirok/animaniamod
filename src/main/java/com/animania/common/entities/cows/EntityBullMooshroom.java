package com.animania.common.entities.cows;

import com.animania.Animania;

import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBullMooshroom extends EntityBullBase
{

	public EntityBullMooshroom(World world)
	{
		super(world);
		this.cowType = CowType.MOOSHROOM;
	}
	
	@Override
	public int getPrimaryEggColor()
	{
		return 12325394;
	}
	
	@Override
	public int getSecondaryEggColor()
	{
		return 12627887;
	}
	
	@Override
	protected ResourceLocation getLootTable()
	{
		return new ResourceLocation(Animania.MODID, "cow_mooshroom");
	}


}