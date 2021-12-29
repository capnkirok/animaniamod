package com.animania.common.items;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ItemAnimaniaFoodRaw extends ItemAnimaniaFood
{

	public ItemAnimaniaFoodRaw(String name)
	{
		super(1, 1f, name, new MobEffectInstance(MobEffects.CONFUSION, 200, 3, false, false));
	}

	public ItemAnimaniaFoodRaw(String name, boolean tab)
	{
		super(1, 1f, name, tab, new MobEffectInstance(MobEffects.CONFUSION, 200, 3, false, false));
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

	}

}
