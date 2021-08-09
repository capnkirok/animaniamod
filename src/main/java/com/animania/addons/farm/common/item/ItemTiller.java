package com.animania.addons.farm.common.item;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.pullables.EntityTiller;
import com.animania.common.ModSoundEvents;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemTiller extends Item
{
	public ItemTiller()
	{
		this.maxStackSize = 1;
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setRegistryName(Animania.MODID, "item_tiller");
		this.setUnlocalizedName("tiller");
		ForgeRegistries.ITEMS.register(this);
	}

	@Override
	public ActionResultType onItemUse(PlayerEntity playerIn, World world, BlockPos pos, EnumHand hand, Direction facing, float hitX, float hitY, float hitZ)
	{
		pos = pos.offset(facing);

		ItemStack stack = playerIn.getHeldItem(hand);

		if (world.isRemote)
			return ActionResultType.SUCCESS;

		EntityTiller entity = new EntityTiller(world);

		entity.setLocationAndAngles(pos.getX() + .5, pos.getY(), pos.getZ() + .5, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);

		if (stack.hasDisplayName())
			entity.setCustomNameTag(stack.getDisplayName());

		if (!playerIn.isCreative())
			stack.shrink(1);

		world.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), ModSoundEvents.combo, SoundCategory.PLAYERS, 0.8F, ((Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F) / 0.8F);
		entity.rotationYaw = entity.rotationYaw;
		entity.deltaRotation = entity.rotationYaw;
		AnimaniaHelper.spawnEntity(world, entity);
		return ActionResultType.SUCCESS;

	}

}