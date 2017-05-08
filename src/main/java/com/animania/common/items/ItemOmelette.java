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

public class ItemOmelette extends ItemFood {
	private String name = "omelette";

	public ItemOmelette(String type) {
		super(5, 5F, true);
		this.setAlwaysEdible();
		name = type + "_" + name;
		this.setRegistryName(new ResourceLocation(Animania.MODID, name));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.MODID + "_" + name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setMaxStackSize(64);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.EAT;
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, World worldObj, EntityPlayer entityplayer) {
		if (!worldObj.isRemote && AnimaniaConfig.gameRules.foodsGiveBonusEffects) {
			if (itemstack.getItem() == ItemHandler.cheeseOmelette) {
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 2, 2, false, false));
			} else if (itemstack.getItem() == ItemHandler.baconOmelette) {
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false));
			} else if (itemstack.getItem() == ItemHandler.truffleOmelette) {
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 1, false, false));
			} else if (itemstack.getItem() == ItemHandler.ultimateOmelette) {
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 1, false, false));
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false));
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 1, false, false));
			}

		}
	}

	public String getName() {
		return name;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
		if (AnimaniaConfig.gameRules.foodsGiveBonusEffects) {
			if (itemstack.getItem() == ItemHandler.cheeseOmelette
					|| itemstack.getItem() == ItemHandler.ultimateOmelette) {
				list.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.cheeseomelette"));
			}
			if (itemstack.getItem() == ItemHandler.baconOmelette
					|| itemstack.getItem() == ItemHandler.ultimateOmelette) {
				list.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.baconomelette"));
			}
			if (itemstack.getItem() == ItemHandler.truffleOmelette
					|| itemstack.getItem() == ItemHandler.ultimateOmelette) {
				list.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.truffleomelette"));
			}

		}

		list.add(TextFormatting.GOLD + I18n.translateToLocal("tooltip.an.edibleanytime"));

	}

}
