package com.animania.addons.farm.common.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.addons.farm.common.item.handler.FluidHandlerHoneyBottle;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.RegistryHelper.RItem;
import com.animania.common.helper.RomanNumberHelper;
import com.animania.common.helper.TimeHelper;
import com.animania.common.items.AnimaniaItem;
import com.animania.config.AnimaniaConfig;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.core.NonNullList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumAction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.potion.MobEffectInstance;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.StatList;
import net.minecraft.util.InteractionResultHolderType;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class ItemHoneyBottle extends AnimaniaItem
{

	public ItemHoneyBottle()
	{
		super("honey_bottle");
		this.setMaxStackSize(4);
	}

	@Override
	public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, CompoundTag nbt)
	{
		return new FluidHandlerHoneyBottle(stack);
	}

	@Override
	public void getSubItems(@Nullable CreativeTabs tab, @Nonnull NonNullList<ItemStack> subItems)
	{
		if (!this.isInCreativeTab(tab))
			return;

		FluidStack fs = new FluidStack(FarmAddonBlockHandler.fluidHoney, 1000);
		ItemStack stack = new ItemStack(this);
		IFluidHandlerItem fluidHandler = new FluidHandlerHoneyBottle(stack);
		if (fluidHandler.fill(fs, true) == fs.amount)
		{
			ItemStack filled = fluidHandler.getContainer();
			subItems.add(filled);
		}
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.DRINK;
	}

	@Override
	public InteractionResultHolder<ItemStack> onItemRightClick(Level levelIn, Player playerIn, InteractionHand handIn)
	{
		ItemStack itemstack = playerIn.getItemInHand(handIn);

		if (playerIn.canEat(AnimaniaConfig.gameRules.eatFoodAnytime))
		{
			playerIn.setActiveHand(handIn);
			return new InteractionResultHolder<ItemStack>(InteractionResultHolderType.SUCCESS, itemstack);
		}
		else
		{
			return new InteractionResultHolder<ItemStack>(InteractionResultHolderType.FAIL, itemstack);
		}
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, Level levelIn, LivingEntity LivingEntity)
	{
		if (LivingEntity instanceof Player)
		{
			Player Player = (Player) LivingEntity;
			Player.getFoodStats().addStats(10, 1.5f);

			if (AnimaniaConfig.gameRules.foodsGiveBonusEffects)
				Player.addMobEffectInstance(new MobEffectInstance(MobEffects.REGENERATION, 100, 1));

			Player.addStat(StatList.getObjectUseStats(this));
			levelIn.playSound((Player) null, Player.getX(), Player.getY(), Player.getZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, levelIn.rand.nextFloat() * 0.1F + 0.9F);

			if (Player instanceof ServerPlayer)
			{
				CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) Player, stack);
			}
			AnimaniaHelper.addItem((Player) LivingEntity, new ItemStack(Items.GLASS_BOTTLE));
		}

		stack.shrink(1);
		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, Level levelIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (AnimaniaConfig.gameRules.foodsGiveBonusEffects)
		{
			Potion pot = MobEffects.REGENERATION;
			int duration = 100;
			int amplifier = 1;
			String name = pot.getRegistryName().getResourcePath().replace("_", "");

			tooltip.add(ChatFormatting.GREEN + I18n.translateToLocal("tooltip.an." + name) + " " + RomanNumberHelper.toRoman(amplifier + 1) + " (" + TimeHelper.getTime(duration) + ")");
		}

		if (AnimaniaConfig.gameRules.eatFoodAnytime)
			tooltip.add(ChatFormatting.GOLD + I18n.translateToLocal("tooltip.an.edibleanytime"));

	}

	@Override
	public boolean hasContainerItem()
	{
		return true;
	}

	@Override
	public RItem getContainerItem()
	{
		return Items.GLASS_BOTTLE;
	}

}
