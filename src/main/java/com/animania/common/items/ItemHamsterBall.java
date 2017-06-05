package com.animania.common.items;

import java.util.List;

import com.animania.Animania;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHamsterBall extends Item
{
private String name = "hamster_ball";


public ItemHamsterBall(String type) {
	name = name + "_" + type;
	this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
	GameRegistry.register(this);
	this.setUnlocalizedName(Animania.MODID + "_" + this.name);
	this.setCreativeTab(Animania.TabAnimaniaResources);
	this.setMaxStackSize(1);
	this.setMaxDamage(100);
	
}

@SideOnly(Side.CLIENT)
public boolean shouldRotateAroundWhenRendering()
{
	return true;
}

public boolean isFull3D()
{
	return true;
}

public String getName()
{
	return name;
}

@Override
public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
{
	list.add(TextFormatting.BOLD.GOLD + I18n.translateToLocal("tooltip.an.hamsterball1"));
	list.add(TextFormatting.BOLD.GOLD + I18n.translateToLocal("tooltip.an.hamsterball2"));
}


}
