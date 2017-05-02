package com.animania;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public final class TabAnimaniaEntities extends CreativeTabs
{
	public TabAnimaniaEntities(int par1, String par2Str)
	{
		super(par1, par2Str);
	}

	public String getTranslatedTabLabel()
	{
		return I18n.translateToLocal("tab.animania_entities.label");
	}
	
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Animania.entityeggrandomchicken,1);
	}	

}