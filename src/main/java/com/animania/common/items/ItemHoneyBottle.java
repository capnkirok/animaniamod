package com.animania.common.items;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.animania.common.handler.BlockHandler;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.RomanNumberHelper;
import com.animania.common.helper.TimeHelper;
import com.animania.common.items.handler.FluidHandlerHoneyBottle;
import com.animania.config.AnimaniaConfig;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
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
	public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, NBTTagCompound nbt)
	{
		return new FluidHandlerHoneyBottle(stack);
	}

	@Override
	public void getSubItems(@Nullable CreativeTabs tab, @Nonnull NonNullList<ItemStack> subItems)
	{
		if (!this.isInCreativeTab(tab))
			return;

		FluidStack fs = new FluidStack(BlockHandler.fluidHoney, 1000);
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
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		if (playerIn.canEat(true))
		{
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
		else
		{
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
		if (entityLiving instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			entityplayer.getFoodStats().addStats(10, 10);

			if (AnimaniaConfig.gameRules.foodsGiveBonusEffects)
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));

			entityplayer.addStat(StatList.getObjectUseStats(this));
			worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);

			if (entityplayer instanceof EntityPlayerMP)
			{
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP) entityplayer, stack);
			}
		}

		stack.shrink(1);
		AnimaniaHelper.addItem((EntityPlayer) entityLiving, new ItemStack(Items.GLASS_BOTTLE));
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
