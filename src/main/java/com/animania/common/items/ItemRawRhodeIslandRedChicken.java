package com.animania.common.items;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRawRhodeIslandRedChicken extends ItemFood
{
    private final String name = "raw_rhode_island_red_chicken";

    public ItemRawRhodeIslandRedChicken() {
        super(1, 1F, true);
        this.setAlwaysEdible();
        this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
        GameRegistry.register(this);
        this.setUnlocalizedName(Animania.MODID + "_" + this.name);
        if (!AnimaniaConfig.drops.oldMeatDrops)
            this.setCreativeTab(null);
        else
            this.setCreativeTab(Animania.TabAnimaniaResources);
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.EAT;
    }

    @Override
    protected void onFoodEaten(ItemStack itemstack, World worldObj, EntityPlayer entityplayer) {
        if (!worldObj.isRemote && AnimaniaConfig.gameRules.foodsGiveBonusEffects)
            entityplayer.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 3, false, false));
    }

    public String getName() {
        return this.name;
    }

}
