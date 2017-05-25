package com.animania.common.items;

import java.util.List;

import com.animania.Animania;
import com.animania.common.handler.ItemHandler;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemCheeseWedge extends ItemFood
{
    private String name = "cheese_wedge";

    public ItemCheeseWedge(String type) {
        super(2, 2F, true);
        this.setAlwaysEdible();
        this.name = type + "_" + this.name;
        this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
        GameRegistry.register(this);
        this.setUnlocalizedName(Animania.MODID + "_" + this.name);
        this.setCreativeTab(Animania.TabAnimaniaResources);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.EAT;
    }

    @Override
    protected void onFoodEaten(ItemStack itemstack, World worldObj, EntityPlayer entityplayer) {
        if (!worldObj.isRemote && AnimaniaConfig.gameRules.foodsGiveBonusEffects)
            if (itemstack.getItem() == ItemHandler.cheeseWedgeFriesian)
                entityplayer.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 6, 2, false, false));
            else if (itemstack.getItem() == ItemHandler.cheeseWedgeHolstein)
                entityplayer.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 12, 2, false, false));
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
        if (AnimaniaConfig.gameRules.foodsGiveBonusEffects) {
            list.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.instanthealth"));
            list.add(TextFormatting.GOLD + I18n.translateToLocal("tooltip.an.edibleanytime"));
        }
    }

}
