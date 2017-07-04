package com.animania.common.items;

import java.util.List;

import com.animania.Animania;
import com.animania.common.handler.ItemHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
