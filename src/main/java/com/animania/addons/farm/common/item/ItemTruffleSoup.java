package com.animania.addons.farm.common.item;

import javax.annotation.Nullable;

import com.animania.common.items.ItemAnimaniaFood;
import com.animania.config.AnimaniaConfig;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ItemTruffleSoup extends ItemAnimaniaFood
{
	public ItemTruffleSoup()
	{
		super(10, 0.6f, "truffle_soup", new PotionEffect(MobEffects.REGENERATION, 1200, 1, false, false));
		this.setMaxStackSize(1);
		this.setContainerItem(Items.BOWL);
	}

	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, Level levelIn, LivingEntity LivingEntity)
	{

		if (LivingEntity instanceof PlayerEntity)
		{
			PlayerEntity PlayerEntity = (PlayerEntity) LivingEntity;
			if (AnimaniaConfig.gameRules.foodsGiveBonusEffects)
				PlayerEntity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1200, 1, false, false));

			if (PlayerEntity.getFoodStats() != null)
			{
				PlayerEntity.getFoodStats().addStats(this, stack);
			}
			levelIn.playSound((PlayerEntity) null, PlayerEntity.getX(), PlayerEntity.getY(), PlayerEntity.getZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, levelIn.rand.nextFloat() * 0.1F + 0.9F);
			this.onFoodEaten(stack, levelIn, PlayerEntity);
			PlayerEntity.addStat(StatList.getObjectUseStats(this));

			if (PlayerEntity instanceof ServerPlayerEntity)
			{
				CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity) PlayerEntity, stack);
			}

			if (!PlayerEntity.capabilities.isCreativeMode)
			{
				stack.shrink(1);
			}
		}

		return stack.getCount() <= 0 ? new ItemStack(Items.BOWL) : stack;
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, Level levelObj, PlayerEntity PlayerEntity)
	{

	}

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, Level levelIn, PlayerEntity playerIn, EnumHand hand)
	{
		playerIn.setActiveHand(hand);
		return new ActionResult(ActionResultType.SUCCESS, itemStackIn);
	}
}
