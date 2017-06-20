package com.animania.common.items;

import java.util.List;
import java.util.Random;

import com.animania.Animania;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.AnimaniaType;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.amphibians.EntityDartFrogs;
import com.animania.common.entities.amphibians.EntityFrogs;
import com.animania.common.entities.amphibians.EntityToad;
import com.animania.common.entities.chickens.ChickenType;
import com.animania.common.entities.chickens.EntityChickLeghorn;
import com.animania.common.entities.chickens.EntityChickOrpington;
import com.animania.common.entities.chickens.EntityChickPlymouthRock;
import com.animania.common.entities.chickens.EntityChickRhodeIslandRed;
import com.animania.common.entities.chickens.EntityChickWyandotte;
import com.animania.common.entities.chickens.EntityHenLeghorn;
import com.animania.common.entities.chickens.EntityHenOrpington;
import com.animania.common.entities.chickens.EntityHenPlymouthRock;
import com.animania.common.entities.chickens.EntityHenRhodeIslandRed;
import com.animania.common.entities.chickens.EntityHenWyandotte;
import com.animania.common.entities.chickens.EntityRoosterLeghorn;
import com.animania.common.entities.chickens.EntityRoosterOrpington;
import com.animania.common.entities.chickens.EntityRoosterPlymouthRock;
import com.animania.common.entities.chickens.EntityRoosterRhodeIslandRed;
import com.animania.common.entities.chickens.EntityRoosterWyandotte;
import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.cows.EntityBullAngus;
import com.animania.common.entities.cows.EntityBullFriesian;
import com.animania.common.entities.cows.EntityBullHereford;
import com.animania.common.entities.cows.EntityBullHolstein;
import com.animania.common.entities.cows.EntityBullLonghorn;
import com.animania.common.entities.cows.EntityCalfAngus;
import com.animania.common.entities.cows.EntityCalfFriesian;
import com.animania.common.entities.cows.EntityCalfHereford;
import com.animania.common.entities.cows.EntityCalfHolstein;
import com.animania.common.entities.cows.EntityCalfLonghorn;
import com.animania.common.entities.cows.EntityCowAngus;
import com.animania.common.entities.cows.EntityCowFriesian;
import com.animania.common.entities.cows.EntityCowHereford;
import com.animania.common.entities.cows.EntityCowHolstein;
import com.animania.common.entities.cows.EntityCowLonghorn;
import com.animania.common.entities.horses.EntityFoalDraftHorse;
import com.animania.common.entities.horses.EntityMareDraftHorse;
import com.animania.common.entities.horses.EntityStallionDraftHorse;
import com.animania.common.entities.peacocks.EntityPeachickBlue;
import com.animania.common.entities.peacocks.EntityPeachickWhite;
import com.animania.common.entities.peacocks.EntityPeacockBlue;
import com.animania.common.entities.peacocks.EntityPeacockWhite;
import com.animania.common.entities.peacocks.EntityPeafowlBlue;
import com.animania.common.entities.peacocks.EntityPeafowlWhite;
import com.animania.common.entities.pigs.EntityHogDuroc;
import com.animania.common.entities.pigs.EntityHogHampshire;
import com.animania.common.entities.pigs.EntityHogLargeBlack;
import com.animania.common.entities.pigs.EntityHogLargeWhite;
import com.animania.common.entities.pigs.EntityHogOldSpot;
import com.animania.common.entities.pigs.EntityHogYorkshire;
import com.animania.common.entities.pigs.EntityPigletDuroc;
import com.animania.common.entities.pigs.EntityPigletHampshire;
import com.animania.common.entities.pigs.EntityPigletLargeBlack;
import com.animania.common.entities.pigs.EntityPigletLargeWhite;
import com.animania.common.entities.pigs.EntityPigletOldSpot;
import com.animania.common.entities.pigs.EntityPigletYorkshire;
import com.animania.common.entities.pigs.EntitySowDuroc;
import com.animania.common.entities.pigs.EntitySowHampshire;
import com.animania.common.entities.pigs.EntitySowLargeBlack;
import com.animania.common.entities.pigs.EntitySowLargeWhite;
import com.animania.common.entities.pigs.EntitySowOldSpot;
import com.animania.common.entities.pigs.EntitySowYorkshire;
import com.animania.common.entities.pigs.PigType;
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemEntityEgg extends Item
{

	private String name = "entity_egg";
	public AnimaniaType type;
	public EntityGender gender;

	public ItemEntityEgg(String atype, AnimaniaType animal, EntityGender gender)
	{
		super();
		this.setCreativeTab(Animania.TabAnimaniaEggs);
		this.maxStackSize = 64;
		this.name = this.name + "_" + atype;
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		GameRegistry.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.type = animal;
		this.gender = gender;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		pos = pos.offset(facing);

		ItemStack stack = playerIn.getHeldItem(hand);

		if (world.isRemote)
			return EnumActionResult.SUCCESS;

		EntityLivingBase entity = null;

		if (this.gender == EntityGender.RANDOM)
		{
			Random rand = new Random();
			if (type instanceof CowType)
			{
				entity = EntityGender.getEntity(CowType.values()[rand.nextInt(((CowType) type).values().length)], gender, world);
			}
			if (type instanceof PigType)
			{
				entity = EntityGender.getEntity(PigType.values()[rand.nextInt(((PigType) type).values().length)], gender, world);
			}
			if (type instanceof ChickenType)
			{
				entity = EntityGender.getEntity(ChickenType.values()[rand.nextInt(((ChickenType) type).values().length)], gender, world);
			}
		}
		else
		{
			entity = EntityGender.getEntity(type, gender, world);
		}
		if (entity != null)
		{
			
			entity.setLocationAndAngles(pos.getX() + .5, pos.getY(), pos.getZ() + .5, MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
			
			if (stack.hasDisplayName())
				((EntityLivingBase) entity).setCustomNameTag(stack.getDisplayName());

			if (!playerIn.isCreative())
				stack.shrink(1);

			Random rand = new Random();
			world.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundEvents.combo, SoundCategory.PLAYERS, 0.8F, ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			entity.rotationYawHead = entity.rotationYaw;
			entity.renderYawOffset = entity.rotationYaw;
			world.spawnEntity(entity);
			return EnumActionResult.SUCCESS;

		}

		return EnumActionResult.FAIL;
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add(TextFormatting.GOLD + I18n.translateToLocal("item.animania_entity_egg.desc1") + " " + TextFormatting.DARK_GRAY + I18n.translateToLocal("item.animania_entity_egg.desc2"));
	}
}
