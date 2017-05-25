package com.animania.common.items;

import com.animania.Animania;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBucketSlop extends Item
{
    private String name = "bucket_slop";

    public ItemBucketSlop() {
        super();
        // setCreativeTab(Animania.TabAnimaniaResources);
        this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
        GameRegistry.register(this);
        this.setUnlocalizedName(Animania.MODID + "_" + this.name);
        this.setMaxStackSize(1);
    }

    public String getName() {
        return this.name;
    }

}
