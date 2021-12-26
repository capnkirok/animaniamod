package com.animania.common.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;

public class ItemAnimaniaFoodRaw extends ItemAnimaniaFood
{

	public ItemAnimaniaFoodRaw(String name)
	{
		super(1, 1f, name, new MobEffectInstance(MobEffects.NAUSEA, 200, 3, false, false));
	}

	public ItemAnimaniaFoodRaw(String name, boolean tab)
	{
		super(1, 1f, name, tab, new MobEffectInstance(MobEffects.NAUSEA, 200, 3, false, false));
	}

	@Override
	public void addInformation(ItemStack stack, Level levelIn, List<String> tooltip, ITooltipFlag flagIn)
	{

	}

}
