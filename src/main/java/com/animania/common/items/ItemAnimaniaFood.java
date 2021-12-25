package com.animania.common.items;

import java.util.List;

import com.animania.Animania;
import com.animania.common.handler.FoodValueHandler;
import com.animania.common.helper.RomanNumberHelper;
import com.animania.common.helper.TimeHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.Player;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.ChatFormatting;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemAnimaniaFood extends ItemFood
{

	private PotionEffect[] effects;
	private String name;

	public ItemAnimaniaFood(int amount, float saturation, boolean isWolfFood, String name, boolean creativeTab, PotionEffect... potionEffects)
	{
		super(amount, saturation, isWolfFood);
		this.setRegistryName(new ResourceLocation(Animania.MODID, name));
		ForgeRegistries.ITEMS.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + name);
		this.effects = potionEffects;
		this.name = name;
		if (AnimaniaConfig.gameRules.eatFoodAnytime)
			this.setAlwaysEdible();
		this.setCreativeTab(Animania.TabAnimaniaResources);

	}

	@Override
	public int getHealAmount(ItemStack stack)
	{
		RItem item = stack.getItem();
		if (FoodValueHandler.hasOverride(item))
			return FoodValueHandler.getHealAmount(item);
		else
			return super.getHealAmount(stack);
	}

	@Override
	public float getSaturationModifier(ItemStack stack)
	{
		RItem item = stack.getItem();
		if (FoodValueHandler.hasOverride(item))
			return FoodValueHandler.getSaturation(item);
		else
			return super.getSaturationModifier(stack);
	}

	public ItemAnimaniaFood(int amount, float saturation, boolean isWolfFood, String name)
	{
		this(amount, saturation, isWolfFood, name, true);
	}

	public ItemAnimaniaFood(int amount, float saturation, String name)
	{
		this(amount, saturation, true, name, true);
	}

	public ItemAnimaniaFood(int amount, float saturation, String name, boolean creativeTab, PotionEffect... potionEffects)
	{
		this(amount, saturation, true, name, creativeTab, potionEffects);
	}

	public ItemAnimaniaFood(int amount, float saturation, String name, PotionEffect... potionEffects)
	{
		this(amount, saturation, true, name, true, potionEffects);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack)
	{
		return EnumAction.EAT;
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	protected void onFoodEaten(ItemStack itemstack, Level levelObj, Player Player)
	{
		if (!levelObj.isClientSide && AnimaniaConfig.gameRules.foodsGiveBonusEffects && this.effects != null)
			for (PotionEffect effect : this.effects.clone())
			{
				Potion pot = effect.getPotion();
				int duration = effect.getDuration();
				int amplifier = effect.getAmplifier();
				boolean isAmbient = effect.getIsAmbient();
				Player.addPotionEffect(new PotionEffect(pot, duration, amplifier, isAmbient, false));
			}
	}

	@Override
	public void addInformation(ItemStack stack, Level levelIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		if (AnimaniaConfig.gameRules.foodsGiveBonusEffects && this.effects != null)
			for (PotionEffect effect : this.effects.clone())
			{
				Potion pot = effect.getPotion();
				int duration = effect.getDuration();
				int amplifier = effect.getAmplifier();
				boolean isInstant = pot.isInstant();
				boolean isPositive = pot.isBeneficial();
				String name = pot.getRegistryName().getResourcePath().replace("_", "");
				if (isPositive)
					tooltip.add(ChatFormatting.GREEN + I18n.translateToLocal("tooltip.an." + name) + " " + RomanNumberHelper.toRoman(amplifier + 1) + (!isInstant ? " (" + TimeHelper.getTime(duration) + ")" : ""));
			}

		if (AnimaniaConfig.gameRules.eatFoodAnytime)
			tooltip.add(ChatFormatting.GOLD + I18n.translateToLocal("tooltip.an.edibleanytime"));

	}

}
