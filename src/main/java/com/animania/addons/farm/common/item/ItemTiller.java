package com.animania.addons.farm.common.item;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.pullables.EntityTiller;
import com.animania.common.ModSoundEvents;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.RegistryHelper.RItem;

import net.minecraft.core.BlockPos;
import net.minecraft.util.InteractionResultHolderType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemTiller extends RItem
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
	public InteractionResultHolderType onItemUse(Player playerIn, Level level, BlockPos pos, InteractionHand hand, Direction facing, float hitX, float hitY, float hitZ)
	{
		pos = pos.offset(facing);

		ItemStack stack = playerIn.getItemInHand(hand);

		if (level.isClientSide)
			return InteractionResultHolderType.SUCCESS;

		EntityTiller entity = new EntityTiller(level);

		entity.setLocationAndAngles(pos.getX() + .5, pos.getY(), pos.getZ() + .5, MathHelper.wrapDegrees(level.rand.nextFloat() * 360.0F), 0.0F);

		if (stack.hasDisplayName())
			entity.setCustomNameTag(stack.getDisplayName());

		if (!playerIn.isCreative())
			stack.shrink(1);

		level.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), ModSoundEvents.combo, SoundCategory.PLAYERS, 0.8F, ((Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F) / 0.8F);
		entity.rotationYaw = entity.rotationYaw;
		entity.deltaRotation = entity.rotationYaw;
		AnimaniaHelper.spawnEntity(level, entity);
		return InteractionResultHolderType.SUCCESS;

	}

}