package com.animania.addons.farm.common.inventory;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CartChest extends InventoryBasic
{
    public CartChest(String inventoryName, int slotCount)
    {
        super(inventoryName, false, slotCount);
    }

    @SideOnly(Dist.CLIENT)
    public CartChest(ITextComponent invTitle, int slotCount)
    {
        super(invTitle, slotCount);
    }
}