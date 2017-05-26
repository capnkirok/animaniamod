package com.animania.common.items;

import com.animania.Animania;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemPeacockFeather extends Item
{
    private String name = "peacock_feather";

    public ItemPeacockFeather(String type) {
        super();
        this.name = type + "_" + this.name;
        this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
        GameRegistry.register(this);
        this.setUnlocalizedName(Animania.MODID + "_" + this.name);
        this.setCreativeTab(Animania.TabAnimaniaResources);
        this.setMaxStackSize(64);
    }

    public String getName() {
        return this.name;
    }

}
