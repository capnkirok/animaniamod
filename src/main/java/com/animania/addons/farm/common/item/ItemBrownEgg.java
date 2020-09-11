package com.animania.addons.farm.common.item;

import com.animania.Animania;
import com.animania.common.helper.AnimaniaHelper;

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
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemBrownEgg extends Item
{
	private String name = "brown_egg";

	public ItemBrownEgg()
	{
		super();
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
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{

		World worldObj = worldIn;
		EntityPlayer entityplayer = playerIn;
		ItemStack itemStackIn = playerIn.getHeldItem(handIn);

		if (!playerIn.capabilities.isCreativeMode)
			itemStackIn.setCount(itemStackIn.getCount() - 1);

		worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (Item.itemRand.nextFloat() * 0.4F + 0.8F));

		if (!worldIn.isRemote)
		{
			EntityEgg entityegg = new EntityEgg(worldIn, playerIn);
			entityegg.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			AnimaniaHelper.spawnEntity(worldIn, entityegg);
		}

		playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}

}
