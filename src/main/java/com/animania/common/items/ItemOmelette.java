package com.animania.common.items;

import java.util.List;

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

import com.animania.Animania;

public class ItemOmelette extends ItemFood {
	private String name = "omelette";

	public ItemOmelette(String type) {
		super (5, 5F, true); 
		this.setAlwaysEdible();
		name = type + "_" + name;
		this.setRegistryName(new ResourceLocation(Animania.modid, name));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.modid + "_" + name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setMaxStackSize(64);
	}


	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.EAT;
	}

	protected void onFoodEaten(ItemStack itemstack, World worldObj, EntityPlayer entityplayer) {
		if (!worldObj.isRemote && Animania.foodsGiveBonusEffects)
		{
			if (itemstack.getItem() == Animania.cheeseOmelette) {
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 2, 2, false, false));
			} else if (itemstack.getItem() == Animania.baconOmelette) {
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false));
			} else if (itemstack.getItem() == Animania.truffleOmelette) {
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 1, false, false));
			} else if (itemstack.getItem() == Animania.ultimateOmelette) {
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 1, false, false));
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 600, 0, false, false));
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600, 1, false, false));
			}

		}
	}

	public String getName()
	{
		return name;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		if (Animania.foodsGiveBonusEffects) {
			if (itemstack.getItem() == Animania.cheeseOmelette || itemstack.getItem() == Animania.ultimateOmelette) {
				list.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.cheeseomelette"));
			} 
			if (itemstack.getItem() == Animania.baconOmelette || itemstack.getItem() == Animania.ultimateOmelette) {
				list.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.baconomelette"));
			}
			if (itemstack.getItem() == Animania.truffleOmelette || itemstack.getItem() == Animania.ultimateOmelette) {
				list.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.truffleomelette"));
			}

		}
		
		list.add(TextFormatting.BOLD.GOLD + I18n.translateToLocal("tooltip.an.edibleanytime"));

	}


}
