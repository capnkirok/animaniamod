package com.animania.addons.farm.common.item;

import java.util.List;

import com.animania.Animania;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemCarvingKnife extends ItemSword
{
	private String name = "carving_knife";
	public int numChants;
	private ItemStack emptyItem = null;

	public ItemCarvingKnife(ToolMaterial material)
	{
		super(material);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		ForgeRegistries.ITEMS.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setMaxDamage(100);
		this.setNoRepair();
		this.canRepair = false;

	}

	@Override
	public boolean hasContainerItem()
	{
		return true;
	}

	public void setEmptyItem(ItemStack ei)
	{
		this.emptyItem = ei;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack)
	{
		int dmg = stack.getItemDamage();

		if (dmg == 100)
			return new ItemStack(stack.getItem(), 0, 100);
		ItemStack tr = copyStack(stack, 1);
		tr.setItemDamage(dmg + 1);
		return tr;
	}

	public static ItemStack copyStack(ItemStack stack, int n)
	{
		return new ItemStack(stack.getItem(), n, stack.getItemDamage());
	}

	public void removeItem(PlayerEntity ep, ItemStack removeitem)
	{
		IInventory inv = ep.inventory;
		for (int i = 0; i < inv.getSizeInventory(); i++)
			if (inv.getStackInSlot(i) != ItemStack.EMPTY)
			{
				ItemStack j = inv.getStackInSlot(i);
				if (j.getItem() != null && j.getItem() == removeitem.getItem())
				{
					inv.setInventorySlotContents(i, null);
					break;
				}
			}
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.GOLD + I18n.translateToLocal("item.animania_carving_knife.desc"));
	}

}
