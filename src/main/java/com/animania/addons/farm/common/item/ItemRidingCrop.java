package com.animania.addons.farm.common.item;

import java.util.List;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.horses.EntityAnimaniaHorse;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatList;
import net.minecraft.util.InteractionResultHolder;
import net.minecraft.util.InteractionResultHolderType;
import net.minecraft.util.InteractionHand;
import net.minecraft.util.text.ChatFormatting;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRidingCrop extends RItem
{
	private String name = "riding_crop";

	public ItemRidingCrop()
	{
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		ForgeRegistries.ITEMS.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setMaxStackSize(1);
		this.setMaxDamage(100);
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public boolean shouldRotateAroundWhenRendering()
	{
		return true;
	}

	@Override
	public InteractionResultHolder<ItemStack> onItemRightClick(Level levelIn, Player playerIn, InteractionHand hand)
	{

		ItemStack itemStackIn = playerIn.getHeldItem(hand);

		if (playerIn.isPassenger() && playerIn.getRidingEntity() instanceof EntityAnimaniaHorse)
		{
			EntityAnimaniaHorse HorseEntity = (EntityAnimaniaHorse) playerIn.getRidingEntity();

			if (itemStackIn.getMaxDamage() - itemStackIn.getMetadata() >= 1 && HorseEntity.boost())
			{
				itemStackIn.damageItem(1, playerIn);

				if (itemStackIn.getCount() == 0)
				{
					itemStackIn.shrink(1);
				}

				return new InteractionResultHolder(InteractionResultHolderType.SUCCESS, itemStackIn);
			}

		}

		playerIn.addStat(StatList.getObjectUseStats(this));
		return new InteractionResultHolder(InteractionResultHolderType.PASS, itemStackIn);

	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public void addInformation(ItemStack stack, Level levelIn, List<String> list, ITooltipFlag flagIn)
	{
		list.add(ChatFormatting.GREEN + I18n.translateToLocal("tooltip.an.ridingcrop1"));
		list.add(ChatFormatting.GREEN + I18n.translateToLocal("tooltip.an.ridingcrop2"));

	}

}
