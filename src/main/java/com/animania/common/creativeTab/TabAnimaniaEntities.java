package com.animania.common.creativeTab;

import com.animania.common.handler.ItemHandler;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class TabAnimaniaEntities extends CreativeModeTab
{
	public TabAnimaniaEntities(int par1, String par2Str)
	{
		super(par1, par2Str);
	}

	/* TODO: Move this to lang file.
	@Override
	public String getTranslatedTabLabel()
	{
		return I18n.translateToLocal("tab.animania_entities.label");
	}*/

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemHandler.entityEggList.isEmpty() ? Items.EGG : ItemHandler.entityEggList.get(0), 1);
	}

}