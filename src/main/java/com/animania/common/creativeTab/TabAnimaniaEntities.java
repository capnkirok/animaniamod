package com.animania.common.creativeTab;

import com.animania.common.handler.ItemHandler;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public final class TabAnimaniaEntities extends CreativeTabs
{
    public TabAnimaniaEntities(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    public String getTranslatedTabLabel() {
        return I18n.translateToLocal("tab.animania_entities.label");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemHandler.entityEggList.isEmpty() ? Items.EGG : ItemHandler.entityEggList.get(0), 1);
    }

}