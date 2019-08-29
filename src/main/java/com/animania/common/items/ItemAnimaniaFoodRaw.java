package com.animania.common.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAnimaniaFoodRaw extends ItemAnimaniaFood
{

	public ItemAnimaniaFoodRaw(String name)
	{
		super(1, 1f, name, new PotionEffect(MobEffects.NAUSEA, 200, 3, false, false));
	}

	public ItemAnimaniaFoodRaw(String name, boolean tab)
	{
		super(1, 1f, name, tab, new PotionEffect(MobEffects.NAUSEA, 200, 3, false, false));
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{

	}

}
