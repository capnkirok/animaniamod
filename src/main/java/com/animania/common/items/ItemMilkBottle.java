package com.animania.common.items;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.helper.RomanNumberHelper;
import com.animania.common.helper.TimeHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemMilkBottle extends ItemAnimaniaFood
{
	public ItemMilkBottle() {
		super(4, 4f, "milk_bottle");
		this.setMaxStackSize(4);
		this.setContainerItem(Items.GLASS_BOTTLE);
	}

	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {

		if(entityLiving instanceof EntityPlayer)
			((EntityPlayer)entityLiving).clearActivePotions();

		if (entityLiving instanceof EntityPlayer && !((EntityPlayer)entityLiving).capabilities.isCreativeMode)
		{
			stack.shrink(1);
		}

		return new ItemStack(Items.GLASS_BOTTLE);
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, World worldObj, EntityPlayer entityplayer)
	{

	}

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		playerIn.setActiveHand(hand);
		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{

		tooltip.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.removeall"));
		tooltip.add(TextFormatting.GOLD + I18n.translateToLocal("tooltip.an.edibleanytime"));

	}
}
