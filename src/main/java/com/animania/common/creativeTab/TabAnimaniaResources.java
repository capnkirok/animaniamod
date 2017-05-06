package com.animania.common.creativeTab;

import java.util.Random;

import com.animania.Animania;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public final class TabAnimaniaResources extends CreativeTabs
{
	public TabAnimaniaResources(int par1, String par2Str)
	{
		super(par1, par2Str);
	}

	public String getTranslatedTabLabel()
	{
		return I18n.translateToLocal("tab.animania_resources.label");
	}
	
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Animania.cookedAngusSteak,1);
	}	

}