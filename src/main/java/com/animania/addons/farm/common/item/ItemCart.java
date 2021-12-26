package com.animania.addons.farm.common.item;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.pullables.EntityCart;
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

public class ItemCart extends RItem
{
	public ItemCart()
	{
		this.maxStackSize = 1;
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setRegistryName(Animania.MODID, "item_cart");
		this.setUnlocalizedName("cart");
		ForgeRegistries.ITEMS.register(this);
	}

	/*
	 * public InteractionResultHolder<ItemStack> onItemRightClick(Level levelIn,
	 * Player playerIn, InteractionHand handIn) { ItemStack itemstack =
	 * playerIn.getItemInHand(handIn); float f = 1.0F; float f1 =
	 * playerIn.prevRotationPitch + (playerIn.rotationPitch -
	 * playerIn.prevRotationPitch) * 1.0F; float f2 = playerIn.prevRotationYaw +
	 * (playerIn.rotationYaw - playerIn.prevRotationYaw) * 1.0F; double d0 =
	 * playerIn.prevgetX() + (playerIn.getX() - playerIn.prevgetX()) * 1.0D;
	 * double d1 = playerIn.prevgetY() + (playerIn.getY() - playerIn.prevgetY())
	 * * 1.0D + (double) playerIn.getEyeHeight(); double d2 =
	 * playerIn.prevgetZ() + (playerIn.getZ() - playerIn.prevgetZ()) * 1.0D;
	 * Vec3d vec3d = new Vec3d(d0, d1, d2); float f3 = MathHelper.cos(-f2 *
	 * 0.017453292F - (float) Math.PI); float f4 = MathHelper.sin(-f2 *
	 * 0.017453292F - (float) Math.PI); float f5 = -MathHelper.cos(-f1 *
	 * 0.017453292F); float f6 = MathHelper.sin(-f1 * 0.017453292F); float f7 =
	 * f4 * f5; float f8 = f3 * f5; double d3 = 5.0D; Vec3d vec3d1 =
	 * vec3d.addVector((double) f7 * 5.0D, (double) f6 * 5.0D, (double) f8 *
	 * 5.0D); RayTraceResult raytraceresult = levelIn.rayTraceBlocks(vec3d,
	 * vec3d1, true);
	 *
	 * if (raytraceresult == null) { return new
	 * InteractionResultHolder(InteractionResultHolderType.PASS, itemstack); } else { Vec3d vec3d2 =
	 * playerIn.getLook(1.0F); boolean flag = false; List<Entity> list =
	 * levelIn.getEntitiesWithinAABBExcludingEntity(playerIn,
	 * playerIn.getEntityBoundingBox().grow(vec3d2.x * 5.0D, vec3d2.y * 5.0D,
	 * vec3d2.z * 5.0D).grow(1.0D));
	 *
	 * for (int i = 0; i < list.size(); ++i) { Entity entity = (Entity)
	 * list.get(i);
	 *
	 * if (entity.canBeCollidedWith()) { AxisAlignedBB axisalignedbb =
	 * entity.getEntityBoundingBox().grow((double)
	 * entity.getCollisionBorderSize());
	 *
	 * if (axisalignedbb.contains(vec3d)) { flag = true; } } }
	 *
	 * if (flag) { return new InteractionResultHolder(InteractionResultHolderType.PASS, itemstack);
	 *
	 * } else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
	 * return new InteractionResultHolder(InteractionResultHolderType.PASS, itemstack); } else { Block
	 * block = levelIn.getBlockState(raytraceresult.getBlockPos()).getBlock();
	 * boolean flag1 = false; EntityCart EntityCart = new EntityCart(levelIn);
	 * EntityCart.rotationYaw = playerIn.rotationYaw;
	 * EntityCart.setLocationAndAngles(d0, d1, d2,
	 * MathHelper.wrapDegrees(levelIn.rand.nextFloat() * 360.0F), 0.0F);
	 *
	 * if (!levelIn.isClientSide) {AnimaniaHelper.spawnEntity( levelIn, EntityCart);
	 * }
	 *
	 * if (!playerIn.capabilities.isCreativeMode) { itemstack.shrink(1); }
	 *
	 * playerIn.addStat(StatList.getObjectUseStats(this)); return new
	 * InteractionResultHolder(InteractionResultHolderType.SUCCESS, itemstack); } } }
	 */

	@Override
	public InteractionResultHolderType onItemUse(Player playerIn, Level level, BlockPos pos, InteractionHand hand, Direction facing, float hitX, float hitY, float hitZ)
	{
		pos = pos.offset(facing);

		ItemStack stack = playerIn.getItemInHand(hand);

		if (level.isClientSide)
			return InteractionResultHolderType.SUCCESS;

		EntityCart entity = new EntityCart(level);

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