package com.animania.common.creativeTab;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.NonNullList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.UniversalBucket;

public final class TabAnimaniaResources extends CreativeTabs
{
	private static ItemStack DISPLAYSTACK = ItemStack.EMPTY;
	
    public TabAnimaniaResources(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    public String getTranslatedTabLabel() {
        return I18n.translateToLocal("tab.animania_resources.label");
    }

    @Override
    public ItemStack getTabIconItem() {
    	
    	if(DISPLAYSTACK.isEmpty())
    		DISPLAYSTACK = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop);
    	
        return DISPLAYSTACK;
    }

    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> list) {

    	for(ItemStack stack : ItemHandler.resourceTabItems)
    		list.add(stack.copy());
    	
        list.add(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, BlockHandler.fluidSlop));
     
        super.displayAllRelevantItems(list);
    }

}