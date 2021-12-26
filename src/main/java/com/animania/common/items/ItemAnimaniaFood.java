package com.animania.common.items;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.common.handler.FoodValueHandler;
import com.animania.common.helper.RomanNumberHelper;
import com.animania.common.helper.TimeHelper;
import com.animania.config.AnimaniaConfig;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ItemAnimaniaFood extends Item
{

	private MobEffectInstance[] effects;
	private String name;

	public ItemAnimaniaFood(int amount, float saturation, boolean isWolfFood, String name, MobEffectInstance... MobEffectInstances)
	{
		super(props(name, amount, saturation, isWolfFood, MobEffectInstances));
		this.setRegistryName(new ResourceLocation(Animania.MODID, name));
		this.effects = MobEffectInstances;
		this.name = name;
	}

	private static Item.Properties props(String name, int amount, float saturation, boolean isWolfFood, MobEffectInstance... MobEffectInstances)
	{
		var food = new FoodProperties.Builder();
		if (AnimaniaConfig.gameRules.eatFoodAnytime)
			food.alwaysEat();
		if (isWolfFood)
			food.meat();
		food.nutrition(FoodValueHandler.hasOverride(name) ? FoodValueHandler.getHealAmount(name) : amount);
		food.saturationMod(FoodValueHandler.hasOverride(name) ? FoodValueHandler.getSaturation(name) : saturation);

		for (var m : MobEffectInstances)
		{
			food.effect(() -> {
				return new MobEffectInstance(m);
			}, 1.0f);
		}

		var prop = new Item.Properties().tab(Animania.TabAnimaniaResources).food(food.build());

		return prop;
	}

	public ItemAnimaniaFood(int amount, float saturation, boolean isWolfFood, String name)
	{
		this(amount, saturation, isWolfFood, name, new MobEffectInstance[0]);
	}

	public ItemAnimaniaFood(int amount, float saturation, String name)
	{
		this(amount, saturation, true, name);
	}

	public ItemAnimaniaFood(int amount, float saturation, String name, boolean isWolfFood, MobEffectInstance... MobEffectInstances)
	{
		this(amount, saturation, isWolfFood, name, MobEffectInstances);
	}
	
	public ItemAnimaniaFood(int amount, float saturation, String name, MobEffectInstance... MobEffectInstances)
	{
		this(amount, saturation, true, name, MobEffectInstances);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack pStack)
	{
		return UseAnim.EAT;
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
	{
		if (AnimaniaConfig.gameRules.foodsGiveBonusEffects && this.effects != null)
			for (MobEffectInstance effect : this.effects.clone())
			{
				MobEffect pot = effect.getEffect();
				int duration = effect.getDuration();
				int amplifier = effect.getAmplifier();
				boolean isInstant = pot.isInstantenous();
				boolean isPositive = pot.isBeneficial();
				String name = pot.getRegistryName().getPath().replace("_", "");
				if (isPositive)
					pTooltipComponents.add(new TranslatableComponent("tooltip.an." + name).withStyle(ChatFormatting.GREEN).append(new TextComponent(" " + RomanNumberHelper.toRoman(amplifier + 1) + (!isInstant ? " (" + TimeHelper.getTime(duration) + ")" : ""))));
			}

		if (AnimaniaConfig.gameRules.eatFoodAnytime)
			pTooltipComponents.add(new TranslatableComponent("tooltip.an.edibleanytime"));

	}

}
