package com.animania.common.creativeTab;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public final class TabAnimaniaResources extends CreativeTabs
{
    public TabAnimaniaResources(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    public String getTranslatedTabLabel() {
        return I18n.translateToLocal("tab.animania_resources.label");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemHandler.cookedAngusSteak, 1);
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> list) {

        list.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop));
        list.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkFriesian));
        list.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidMilkHolstein));

        super.displayAllRelevantItems(list);
    }

}