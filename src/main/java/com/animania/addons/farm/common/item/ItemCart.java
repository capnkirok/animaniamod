package com.animania.addons.farm.common.item;

import com.animania.Animania;
import com.animania.addons.farm.common.entity.pullables.EntityCart;
import com.animania.common.ModSoundEvents;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemCart extends Item
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
	 * public ActionResult<ItemStack> onItemRightClick(World worldIn,
	 * EntityPlayer playerIn, EnumHand handIn) { ItemStack itemstack =
	 * playerIn.getHeldItem(handIn); float f = 1.0F; float f1 =
	 * playerIn.prevRotationPitch + (playerIn.rotationPitch -
	 * playerIn.prevRotationPitch) * 1.0F; float f2 = playerIn.prevRotationYaw +
	 * (playerIn.rotationYaw - playerIn.prevRotationYaw) * 1.0F; double d0 =
	 * playerIn.prevPosX + (playerIn.posX - playerIn.prevPosX) * 1.0D; double d1
	 * = playerIn.prevPosY + (playerIn.posY - playerIn.prevPosY) * 1.0D +
	 * (double) playerIn.getEyeHeight(); double d2 = playerIn.prevPosZ +
	 * (playerIn.posZ - playerIn.prevPosZ) * 1.0D; Vec3d vec3d = new Vec3d(d0,
	 * d1, d2); float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
	 * float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI); float f5
	 * = -MathHelper.cos(-f1 * 0.017453292F); float f6 = MathHelper.sin(-f1 *
	 * 0.017453292F); float f7 = f4 * f5; float f8 = f3 * f5; double d3 = 5.0D;
	 * Vec3d vec3d1 = vec3d.addVector((double) f7 * 5.0D, (double) f6 * 5.0D,
	 * (double) f8 * 5.0D); RayTraceResult raytraceresult =
	 * worldIn.rayTraceBlocks(vec3d, vec3d1, true);
	 * 
	 * if (raytraceresult == null) { return new
	 * ActionResult(EnumActionResult.PASS, itemstack); } else { Vec3d vec3d2 =
	 * playerIn.getLook(1.0F); boolean flag = false; List<Entity> list =
	 * worldIn.getEntitiesWithinAABBExcludingEntity(playerIn,
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
	 * if (flag) { return new ActionResult(EnumActionResult.PASS, itemstack);
	 * 
	 * } else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
	 * return new ActionResult(EnumActionResult.PASS, itemstack); } else { Block
	 * block = worldIn.getBlockState(raytraceresult.getBlockPos()).getBlock();
	 * boolean flag1 = false; EntityCart EntityCart = new EntityCart(worldIn);
	 * EntityCart.rotationYaw = playerIn.rotationYaw;
	 * EntityCart.setLocationAndAngles(d0, d1, d2,
	 * MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
	 * 
	 * if (!worldIn.isRemote) {AnimaniaHelper.spawnEntity( worldIn, EntityCart);
	 * }
	 * 
	 * if (!playerIn.capabilities.isCreativeMode) { itemstack.shrink(1); }
	 * 
	 * playerIn.addStat(StatList.getObjectUseStats(this)); return new
	 * ActionResult(EnumActionResult.SUCCESS, itemstack); } } }
	 */

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		pos = pos.offset(facing);

		ItemStack stack = playerIn.getHeldItem(hand);

		if (world.isRemote)
			return EnumActionResult.SUCCESS;

		EntityCart entity = new EntityCart(world);

		entity.setLocationAndAngles(pos.getX() + .5, pos.getY(), pos.getZ() + .5, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);

		if (stack.hasDisplayName())
			entity.setCustomNameTag(stack.getDisplayName());

		if (!playerIn.isCreative())
			stack.shrink(1);

		world.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundEvents.combo, SoundCategory.PLAYERS, 0.8F, ((Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F) / 0.8F);
		entity.rotationYaw = entity.rotationYaw;
		entity.deltaRotation = entity.rotationYaw;
		AnimaniaHelper.spawnEntity(world, entity);
		return EnumActionResult.SUCCESS;

	}

}