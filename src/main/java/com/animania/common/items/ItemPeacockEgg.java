package com.animania.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.animania.Animania;

public class ItemPeacockEgg extends Item{
	private String name = "peacock_egg";

	public ItemPeacockEgg(String type) {
		super();
		name = name + "_" + type;
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		GameRegistry.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.maxStackSize = 16;

	}

	public String getName()
	{
		return name;
	}	


}
