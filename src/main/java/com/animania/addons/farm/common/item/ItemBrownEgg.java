package com.animania.addons.farm.common.item;

import com.animania.Animania;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemBrownEgg extends Item
{
	private String name = "brown_egg";

	public ItemBrownEgg()
	{
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		ForgeRegistries.ITEMS.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.maxStackSize = 16;
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(Level levelIn, PlayerEntity playerIn, EnumHand handIn)
	{

		Level levelObj = levelIn;
		PlayerEntity PlayerEntity = playerIn;
		ItemStack itemStackIn = playerIn.getHeldItem(handIn);

		if (!playerIn.capabilities.isCreativeMode)
			itemStackIn.setCount(itemStackIn.getCount() - 1);

		levelIn.playSound((PlayerEntity) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (Item.itemRand.nextFloat() * 0.4F + 0.8F));

		if (!levelIn.isRemote)
		{
			EntityEgg entityegg = new EntityEgg(levelIn, playerIn);
			entityegg.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			AnimaniaHelper.spawnEntity(levelIn, entityegg);
		}

		playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult(ActionResultType.SUCCESS, itemStackIn);
	}

}
