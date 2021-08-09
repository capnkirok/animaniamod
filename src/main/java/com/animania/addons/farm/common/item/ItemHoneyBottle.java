package com.animania.addons.farm.common.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.animania.addons.farm.common.handler.FarmAddonBlockHandler;
import com.animania.addons.farm.common.item.handler.FluidHandlerHoneyBottle;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.RomanNumberHelper;
import com.animania.common.helper.TimeHelper;
import com.animania.common.items.AnimaniaItem;
import com.animania.config.AnimaniaConfig;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
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
	public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, CompoundNBT nbt)
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
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, EnumHand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		if (playerIn.canEat(AnimaniaConfig.gameRules.eatFoodAnytime))
		{
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		} else
		{
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity LivingEntity)
	{
		if (LivingEntity instanceof PlayerEntity)
		{
			PlayerEntity PlayerEntity = (PlayerEntity) LivingEntity;
			PlayerEntity.getFoodStats().addStats(10, 1.5f);

			if (AnimaniaConfig.gameRules.foodsGiveBonusEffects)
				PlayerEntity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));

			PlayerEntity.addStat(StatList.getObjectUseStats(this));
			worldIn.playSound((PlayerEntity) null, PlayerEntity.getX(), PlayerEntity.getY(), PlayerEntity.getZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);

			if (PlayerEntity instanceof ServerPlayerEntity)
			{
				CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity) PlayerEntity, stack);
			}
			AnimaniaHelper.addItem((PlayerEntity) LivingEntity, new ItemStack(Items.GLASS_BOTTLE));
		}

		stack.shrink(1);
		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (AnimaniaConfig.gameRules.foodsGiveBonusEffects)
		{
			Potion pot = MobEffects.REGENERATION;
			int duration = 100;
			int amplifier = 1;
			String name = pot.getRegistryName().getResourcePath().replace("_", "");

			tooltip.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an." + name) + " " + RomanNumberHelper.toRoman(amplifier + 1) + " (" + TimeHelper.getTime(duration) + ")");
		}

		if (AnimaniaConfig.gameRules.eatFoodAnytime)
			tooltip.add(TextFormatting.GOLD + I18n.translateToLocal("tooltip.an.edibleanytime"));

	}

	@Override
	public boolean hasContainerItem()
	{
		return true;
	}

	@Override
	public Item getContainerItem()
	{
		return Items.GLASS_BOTTLE;
	}

}
