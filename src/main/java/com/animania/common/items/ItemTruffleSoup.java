package com.animania.common.items;

import java.util.List;

import javax.annotation.Nullable;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemTruffleSoup extends ItemFood
{
	private final String name = "truffle_soup";

	public ItemTruffleSoup() {
		super(10, 10F, true);
		this.setAlwaysEdible();
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		GameRegistry.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setMaxStackSize(1);

	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.EAT;
	}
	
	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			if (!worldIn.isRemote && AnimaniaConfig.gameRules.foodsGiveBonusEffects) {
				entityplayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1200, 1, false, false));
			}
		}
		
		stack.shrink(1);
		stack = new ItemStack(Items.BOWL);
		return stack;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
		if (AnimaniaConfig.gameRules.foodsGiveBonusEffects)
			list.add(TextFormatting.GREEN + I18n.translateToLocal("tooltip.an.regeneration"));
		list.add(TextFormatting.GOLD + I18n.translateToLocal("tooltip.an.edibleanytime"));

	}

}
