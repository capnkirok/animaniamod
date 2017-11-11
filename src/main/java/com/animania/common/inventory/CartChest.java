package com.animania.common.inventory;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CartChest extends InventoryBasic
{
    public CartChest(String inventoryName, int slotCount)
    {
        super(inventoryName, false, slotCount);
    }

    @SideOnly(Side.CLIENT)
    public CartChest(ITextComponent invTitle, int slotCount)
    {
        super(invTitle, slotCount);
    }
}