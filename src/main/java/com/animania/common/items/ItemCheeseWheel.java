package com.animania.common.items;

import com.animania.Animania;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemCheeseWheel extends ItemBlock
{
    private String name = "cheese_wheel";

    public ItemCheeseWheel(String type, Block block) {
        super(block);
        this.setUnlocalizedName(Animania.MODID + "_" + this.name);
        this.setCreativeTab(Animania.TabAnimaniaResources);
    }

  
    public String getName() {
        return this.name;
    }

}
