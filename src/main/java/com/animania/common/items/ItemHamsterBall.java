package com.animania.common.items;

import java.util.List;

import com.animania.Animania;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHamsterBall extends Item
{
	private String name;

	public ItemHamsterBall(boolean hasSubtypes, String name) {
		this.name = name;
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		GameRegistry.register(this);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setMaxStackSize(1);
		this.hasSubtypes = hasSubtypes;

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

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		if(this.hasSubtypes)
		{
			int meta = stack.getMetadata();
			String color = EnumDyeColor.byDyeDamage(meta).getName();
			return "item." + Animania.MODID + "_hamster_ball_" + color;
		}
		return "item." + Animania.MODID + "_hamster_ball_clear";
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add(TextFormatting.BOLD.GOLD + I18n.translateToLocal("tooltip.an.hamsterball1"));
		list.add(TextFormatting.BOLD.GOLD + I18n.translateToLocal("tooltip.an.hamsterball2"));
	}


	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems)
	{
		if(this.hasSubtypes)
		{
			for (int i = 0; i < 16; ++i)
			{
				subItems.add(new ItemStack(itemIn, 1, i));
			}
		}
		else
			subItems.add(new ItemStack(itemIn, 1, 0));

	}


}
