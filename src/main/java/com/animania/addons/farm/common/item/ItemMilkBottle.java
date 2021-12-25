package com.animania.addons.farm.common.item;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.items.ItemAnimaniaFood;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.Player;
import net.minecraft.item.EnumAction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.StatList;
import net.minecraft.util.InteractionResultHolder;
import net.minecraft.util.InteractionResultHolderType;
import net.minecraft.util.InteractionHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ChatFormatting;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ItemMilkBottle extends ItemAnimaniaFood
{
	public ItemMilkBottle()
	{
		super(4, 1f, "milk_bottle");
		this.setMaxStackSize(4);
		this.setContainerItem(Items.GLASS_BOTTLE);
		this.setAlwaysEdible();
	}

	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, Level levelIn, LivingEntity LivingEntity)
	{

		if (LivingEntity instanceof Player)
		{
			Player Player = (Player) LivingEntity;
			Player.clearActivePotions();

			if (!Player.capabilities.isCreativeMode)
			{

				if (!levelIn.isClientSide)
				{
					EntityItem entityitem = new EntityItem(levelIn, LivingEntity.getX() + 0.5D, LivingEntity.getY() + 0.5D, LivingEntity.getZ() + 0.5D, new ItemStack(Items.GLASS_BOTTLE));
					AnimaniaHelper.spawnEntity(levelIn, entityitem);
				}

				if (Player.getFoodStats() != null)
				{
					Player.getFoodStats().addStats(this, stack);
				}
				levelIn.playSound((Player) null, Player.getX(), Player.getY(), Player.getZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, levelIn.rand.nextFloat() * 0.1F + 0.9F);
				this.onFoodEaten(stack, levelIn, Player);
				Player.addStat(StatList.getObjectUseStats(this));
				stack.shrink(1);
			}
		}

		return stack;
	}

	public InteractionResultHolder<ItemStack> onItemRightClick(ItemStack itemStackIn, Level levelIn, Player playerIn, InteractionHand hand)
	{
		playerIn.setActiveHand(hand);
		return new InteractionResultHolder(InteractionResultHolderType.SUCCESS, itemStackIn);
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, Level levelObj, Player Player)
	{

	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.DRINK;

	}

	@Override
	public void addInformation(ItemStack stack, Level levelIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(ChatFormatting.GREEN + I18n.translateToLocal("tooltip.an.removeall"));

		if (AnimaniaConfig.gameRules.eatFoodAnytime)
			tooltip.add(ChatFormatting.GOLD + I18n.translateToLocal("tooltip.an.edibleanytime"));

	}
}
