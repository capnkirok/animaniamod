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

public class ItemCheeseWheel extends ItemFood {
	private String name = "cheese_wheel";

	public ItemCheeseWheel(String type) {
		super (8, 8F, true); 
		this.setAlwaysEdible();
		name = type + "_" + name;
		this.setRegistryName(new ResourceLocation(Animania.modid, name));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.modid + "_" + name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
	}


	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.EAT;
	}


	protected void onFoodEaten(ItemStack itemstack, World worldObj, EntityPlayer entityplayer) {
		if (!worldObj.isRemote && Animania.foodsGiveBonusEffects)
		{
			if (itemstack.getItem() == Animania.cheeseWedgeFriesian) {
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 10, 2, false, false));
			} else if (itemstack.getItem() == Animania.cheeseWedgeHolstein) {
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 16, 2, false, false));
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
			list.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.instanthealth"));
			list.add(TextFormatting.BOLD.GOLD + I18n.translateToLocal("tooltip.an.edibleanytime"));
		}
	}
}
