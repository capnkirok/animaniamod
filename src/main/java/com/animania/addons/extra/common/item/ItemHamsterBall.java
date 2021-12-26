package com.animania.addons.extra.common.item;

import java.util.List;

import com.animania.Animania;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.core.NonNullList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemHamsterBall extends Item
{
	private String name;

	public ItemHamsterBall(boolean hasSubtypes, String name)
	{
		this.name = name;
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		ForgeRegistries.ITEMS.register(this);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setMaxStackSize(1);
		this.hasSubtypes = hasSubtypes;

	}

	@SideOnly(Dist.CLIENT)
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
		if (this.hasSubtypes)
		{
			int meta = stack.getMetadata();
			String color = EnumDyeColor.byDyeDamage(meta).getName();
			return "item." + Animania.MODID + "_hamster_ball_" + color;
		}
		return "item." + Animania.MODID + "_hamster_ball_clear";
	}

	@Override
	public void addInformation(ItemStack stack, Level levelIn, List<String> list, ITooltipFlag flagIn)
	{
		list.add(ChatFormatting.BOLD.GOLD + I18n.translateToLocal("tooltip.an.hamsterball1"));
		list.add(ChatFormatting.BOLD.GOLD + I18n.translateToLocal("tooltip.an.hamsterball2"));
	}

	@SideOnly(Dist.CLIENT)
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (tab == Animania.TabAnimaniaResources)
		{
			if (this.hasSubtypes)
			{
				for (int i = 0; i < 16; ++i)
				{
					items.add(new ItemStack(this, 1, i));
				}
			}
			else
				items.add(new ItemStack(this, 1, 0));
		}

	}

}
