package com.animania.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.animania.Animania;

public class ItemRawAngusBeef extends ItemFood {
	private final String name = "raw_angus_beef";

	public ItemRawAngusBeef() {
		super (1, 1F, true); 
		this.setAlwaysEdible();
		this.setRegistryName(new ResourceLocation(Animania.modid, name));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.modid + "_" + name);
		if (Animania.customMobDrops) {
			this.setCreativeTab(null);
		} else {
			this.setCreativeTab(Animania.TabAnimaniaResources);
		}
	}
	

	public EnumAction getItemUseAction(ItemStack itemstack) {
		return EnumAction.EAT;
	}

	protected void onFoodEaten(ItemStack itemstack, World worldObj, EntityPlayer entityplayer) {
		if (!worldObj.isRemote && Animania.foodsGiveBonusEffects)
		{
			entityplayer.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 200, 3, false, false));
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	
}
