package com.animania.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.animania.Animania;

public class ItemPeacockFeather extends Item {
	private String name = "peacock_feather";

	public ItemPeacockFeather(String type) {
		super(); 
		name = type + "_" + name;
		this.setRegistryName(new ResourceLocation(Animania.modid, name));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.modid + "_" + name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setMaxStackSize(64);
	}
		
	public String getName()
	{
		return name;
	}
	
}
