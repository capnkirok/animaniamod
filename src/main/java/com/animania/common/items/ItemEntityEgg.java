package com.animania.common.items;

import java.util.List;
import java.util.Random;

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

import com.animania.Animania;
import com.animania.common.ModSoundEvents;
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
import com.animania.common.entities.rodents.EntityFerretGrey;
import com.animania.common.entities.rodents.EntityFerretWhite;
import com.animania.common.entities.rodents.EntityHamster;
import com.animania.common.entities.rodents.EntityHedgehog;
import com.animania.common.entities.rodents.EntityHedgehogAlbino;
public class ItemEntityEgg extends Item {

	private String name = "entity_egg";
	private String animalType = "";

	public ItemEntityEgg(String atype){
		super();
		this.setCreativeTab(Animania.TabAnimaniaEggs); 
		this.maxStackSize = 64;
		this.animalType = atype;
		this.name = name + "_" + animalType;
		this.setRegistryName(new ResourceLocation(Animania.modid, name));
		GameRegistry.register(this);
		setUnlocalizedName(Animania.modid + "_" + name);
	}


	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		pos = pos.offset(facing);
		
		ItemStack stack = playerIn.getHeldItem(hand);


		if (!playerIn.canPlayerEdit(pos, facing, stack))
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			if (worldIn.isRemote)
			{
				return EnumActionResult.SUCCESS;
			}
			else
			{

				RayTraceResult movingobjectposition = this.rayTrace(worldIn, playerIn, true);

				if (movingobjectposition == null)
				{
					return EnumActionResult.FAIL;
				}
				else
				{
					BlockPos blockpos = movingobjectposition.getBlockPos();
					if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK)
					{
						if (!worldIn.isBlockModifiable(playerIn, blockpos))
						{
							return EnumActionResult.FAIL;
						}

						if (!playerIn.canPlayerEdit(blockpos, movingobjectposition.sideHit, stack))
						{
							return EnumActionResult.FAIL;
						}

						EntityPlayer entityplayer = playerIn;
						Random rand = new Random();

						if (animalType.equals("bull_holstein")) {
							EntityBullHolstein entityB1 = new EntityBullHolstein(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("cow_holstein")) {
							EntityCowHolstein entityB1 = new EntityCowHolstein(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);

							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("calf_holstein")) {
							EntityCalfHolstein entityB1 = new EntityCalfHolstein(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else	if (animalType.equals("bull_friesian")) {
							EntityBullFriesian entityB1 = new EntityBullFriesian(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("cow_friesian")) {
							EntityCowFriesian entityB1 = new EntityCowFriesian(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("calf_friesian")) {
							EntityCalfFriesian entityB1 = new EntityCalfFriesian(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else	if (animalType.equals("bull_angus")) {
							EntityBullAngus entityB1 = new EntityBullAngus(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("cow_angus")) {
							EntityCowAngus entityB1 = new EntityCowAngus(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("calf_angus")) {
							EntityCalfAngus entityB1 = new EntityCalfAngus(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else	if (animalType.equals("bull_longhorn")) {
							EntityBullLonghorn entityB1 = new EntityBullLonghorn(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("cow_longhorn")) {
							EntityCowLonghorn entityB1 = new EntityCowLonghorn(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("calf_longhorn")) {
							EntityCalfLonghorn entityB1 = new EntityCalfLonghorn(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 

						} else	if (animalType.equals("bull_hereford")) {
							EntityBullHereford entityB1 = new EntityBullHereford(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 

						} else if (animalType.equals("cow_hereford")) {
							EntityCowHereford entityB1 = new EntityCowHereford(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 

						} else if (animalType.equals("calf_hereford")) {
							EntityCalfHereford entityB1 = new EntityCalfHereford(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 

						} else if (animalType.equals("cow_random")) {
							int chooser = rand.nextInt(15);

							if (chooser == 0) {
								EntityCalfHolstein entityB1 = new EntityCalfHolstein(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 1) {
								EntityCowHolstein entityB1 = new EntityCowHolstein(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 2) {
								EntityBullHolstein entityB1 = new EntityBullHolstein(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 3) {
								EntityCalfFriesian entityB1 = new EntityCalfFriesian(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 4) {
								EntityCowFriesian entityB1 = new EntityCowFriesian(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 5) {
								EntityBullFriesian entityB1 = new EntityBullFriesian(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 6) {
								EntityCalfHereford entityB1 = new EntityCalfHereford(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 7) {
								EntityCowHereford entityB1 = new EntityCowHereford(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 8) {
								EntityBullHereford entityB1 = new EntityBullHereford(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 9) {
								EntityCalfAngus entityB1 = new EntityCalfAngus(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 10) {
								EntityCowAngus entityB1 = new EntityCowAngus(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 11) {
								EntityBullAngus entityB1 = new EntityBullAngus(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 12) {
								EntityCalfLonghorn entityB1 = new EntityCalfLonghorn(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 13) {
								EntityCowLonghorn entityB1 = new EntityCowLonghorn(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 14) {
								EntityBullLonghorn entityB1 = new EntityBullLonghorn(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							}




						} else if (animalType.equals("chick_plymouth")) {
							EntityChickPlymouthRock entityB1 = new EntityChickPlymouthRock(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("hen_plymouth")) {
							EntityHenPlymouthRock entityB1 = new EntityHenPlymouthRock(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("rooster_plymouth")) {
							EntityRoosterPlymouthRock entityB1 = new EntityRoosterPlymouthRock(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("chick_red")) {
							EntityChickRhodeIslandRed entityB1 = new EntityChickRhodeIslandRed(worldIn);

							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("hen_red")) {
							EntityHenRhodeIslandRed entityB1 = new EntityHenRhodeIslandRed(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("rooster_red")) {
							EntityRoosterRhodeIslandRed entityB1 = new EntityRoosterRhodeIslandRed(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 

						} else if (animalType.equals("chick_leghorn")) {
							EntityChickLeghorn entityB1 = new EntityChickLeghorn(worldIn);

							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("hen_leghorn")) {
							EntityHenLeghorn entityB1 = new EntityHenLeghorn(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("rooster_leghorn")) {
							EntityRoosterLeghorn entityB1 = new EntityRoosterLeghorn(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 

						} else if (animalType.equals("chick_orpington")) {
							EntityChickOrpington entityB1 = new EntityChickOrpington(worldIn);

							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("hen_orpington")) {
							EntityHenOrpington entityB1 = new EntityHenOrpington(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("rooster_orpington")) {
							EntityRoosterOrpington entityB1 = new EntityRoosterOrpington(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 

						} else if (animalType.equals("chick_wyandotte")) {
							EntityChickWyandotte entityB1 = new EntityChickWyandotte(worldIn);

							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("hen_wyandotte")) {
							EntityHenWyandotte entityB1 = new EntityHenWyandotte(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("rooster_wyandotte")) {
							EntityRoosterWyandotte entityB1 = new EntityRoosterWyandotte(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("chicken_random")) {
							int chooser = rand.nextInt(15);

							if (chooser == 0) {
								EntityChickLeghorn entityB1 = new EntityChickLeghorn(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 1) {
								EntityHenLeghorn entityB1 = new EntityHenLeghorn(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 2) {
								EntityRoosterLeghorn entityB1 = new EntityRoosterLeghorn(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 3) {
								EntityChickOrpington entityB1 = new EntityChickOrpington(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 4) {
								EntityHenOrpington entityB1 = new EntityHenOrpington(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 5) {
								EntityRoosterOrpington entityB1 = new EntityRoosterOrpington(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 6) {
								EntityChickPlymouthRock entityB1 = new EntityChickPlymouthRock(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 7) {
								EntityHenPlymouthRock entityB1 = new EntityHenPlymouthRock(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 8) {
								EntityRoosterPlymouthRock entityB1 = new EntityRoosterPlymouthRock(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 9) {
								EntityChickRhodeIslandRed entityB1 = new EntityChickRhodeIslandRed(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 10) {
								EntityHenRhodeIslandRed entityB1 = new EntityHenRhodeIslandRed(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 11) {
								EntityRoosterRhodeIslandRed entityB1 = new EntityRoosterRhodeIslandRed(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 12) {
								EntityChickWyandotte entityB1 = new EntityChickWyandotte(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 13) {
								EntityHenWyandotte entityB1 = new EntityHenWyandotte(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 14) {
								EntityRoosterWyandotte entityB1 = new EntityRoosterWyandotte(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							}
						} else if (animalType.equals("peafowl_blue")) {
							EntityPeafowlBlue entityB1 = new EntityPeafowlBlue(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("peacock_blue")) {
							EntityPeacockBlue entityB1 = new EntityPeacockBlue(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("peachick_blue")) {
							EntityPeachickBlue entityB1 = new EntityPeachickBlue(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("peafowl_white")) {
							EntityPeafowlWhite entityB1 = new EntityPeafowlWhite(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("peacock_white")) {
							EntityPeacockWhite entityB1 = new EntityPeacockWhite(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("peachick_white")) {
							EntityPeachickWhite entityB1 = new EntityPeachickWhite(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("sow_yorkshire")) {
							EntitySowYorkshire entityB1 = new EntitySowYorkshire(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("hog_yorkshire")) {
							EntityHogYorkshire entityB1 = new EntityHogYorkshire(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("piglet_yorkshire")) {
							EntityPigletYorkshire entityB1 = new EntityPigletYorkshire(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("sow_oldspot")) {
							EntitySowOldSpot entityB1 = new EntitySowOldSpot(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("hog_oldspot")) {
							EntityHogOldSpot entityB1 = new EntityHogOldSpot(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("piglet_oldspot")) {
							EntityPigletOldSpot entityB1 = new EntityPigletOldSpot(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("sow_largeblack")) {
							EntitySowLargeBlack entityB1 = new EntitySowLargeBlack(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("hog_largeblack")) {
							EntityHogLargeBlack entityB1 = new EntityHogLargeBlack(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("piglet_largeblack")) {
							EntityPigletLargeBlack entityB1 = new EntityPigletLargeBlack(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("sow_largewhite")) {
							EntitySowLargeWhite entityB1 = new EntitySowLargeWhite(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("hog_largewhite")) {
							EntityHogLargeWhite entityB1 = new EntityHogLargeWhite(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("piglet_largewhite")) {
							EntityPigletLargeWhite entityB1 = new EntityPigletLargeWhite(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("sow_duroc")) {
							EntitySowDuroc entityB1 = new EntitySowDuroc(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("hog_duroc")) {
							EntityHogDuroc entityB1 = new EntityHogDuroc(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("piglet_duroc")) {
							EntityPigletDuroc entityB1 = new EntityPigletDuroc(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("sow_hampshire")) {
							EntitySowHampshire entityB1 = new EntitySowHampshire(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("hog_hampshire")) {
							EntityHogHampshire entityB1 = new EntityHogHampshire(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 
						} else if (animalType.equals("piglet_hampshire")) {
							EntityPigletHampshire entityB1 = new EntityPigletHampshire(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							} 

						} else if (animalType.equals("pig_random")) {
							int chooser = rand.nextInt(18);

							if (chooser == 0) {
								EntityPigletDuroc entityB1 = new EntityPigletDuroc(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 1) {
								EntitySowDuroc entityB1 = new EntitySowDuroc(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 2) {
								EntityHogDuroc entityB1 = new EntityHogDuroc(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 3) {
								EntityPigletHampshire entityB1 = new EntityPigletHampshire(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 4) {
								EntitySowHampshire entityB1 = new EntitySowHampshire(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 5) {
								EntityHogHampshire entityB1 = new EntityHogHampshire(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 6) {
								EntityPigletLargeBlack entityB1 = new EntityPigletLargeBlack(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 7) {
								EntitySowLargeBlack entityB1 = new EntitySowLargeBlack(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 8) {
								EntityHogLargeBlack entityB1 = new EntityHogLargeBlack(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 9) {
								EntityPigletLargeWhite entityB1 = new EntityPigletLargeWhite(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 10) {
								EntitySowLargeWhite entityB1 = new EntitySowLargeWhite(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 11) {
								EntityHogLargeWhite entityB1 = new EntityHogLargeWhite(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 12) {
								EntityPigletOldSpot entityB1 = new EntityPigletOldSpot(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 13) {
								EntitySowOldSpot entityB1 = new EntitySowOldSpot(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 14) {
								EntityHogOldSpot entityB1 = new EntityHogOldSpot(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 15) {
								EntityPigletYorkshire entityB1 = new EntityPigletYorkshire(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 16) {
								EntitySowYorkshire entityB1 = new EntitySowYorkshire(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							} else if (chooser == 17) {
								EntityHogYorkshire entityB1 = new EntityHogYorkshire(worldIn);
								entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
								worldIn.spawnEntity(entityB1);
								if (entityB1 != null)
								{
									if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
									{
										((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
									}

									if (!playerIn.capabilities.isCreativeMode)
									{
										stack.setCount(stack.getCount()-1);
									}
								} 
							}
							
						} else if (animalType.equals("hamster")) {
							EntityHamster entityB1 = new EntityHamster(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							}
						} else if (animalType.equals("hedgehog")) {
							EntityHedgehog entityB1 = new EntityHedgehog(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							}
						} else if (animalType.equals("hedgehog_albino")) {
							EntityHedgehogAlbino entityB1 = new EntityHedgehogAlbino(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							}
						} else if (animalType.equals("ferret_grey")) {
							EntityFerretGrey entityB1 = new EntityFerretGrey(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							}
						} else if (animalType.equals("ferret_white")) {
							EntityFerretWhite entityB1 = new EntityFerretWhite(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							}
						} else if (animalType.equals("draft_horse_stallion")) {
							EntityStallionDraftHorse entityB1 = new EntityStallionDraftHorse(worldIn);
							entityB1.setPosition(blockpos.getX() + .5, blockpos.getY() + 1, blockpos.getZ() + .5);
							worldIn.spawnEntity(entityB1);
							if (entityB1 != null)
							{
								if (entityB1 instanceof EntityLivingBase && stack.hasDisplayName())
								{
									((EntityLiving)entityB1).setCustomNameTag(stack.getDisplayName());
								}

								if (!playerIn.capabilities.isCreativeMode)
								{
									stack.setCount(stack.getCount()-1);
								}
							}
						}

						worldIn.playSound(null,entityplayer.posX, entityplayer.posY, entityplayer.posZ, ModSoundEvents.combo, SoundCategory.PLAYERS, 0.8F,  ((rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);

					}

					return EnumActionResult.SUCCESS;
				}
			}
		}
	}




	public static Entity spawnCreature(World worldIn, int entityID, double x, double y, double z)
	{
		if (!EntityList.ENTITY_EGGS.containsKey(Integer.valueOf(entityID)))
		{
			return null;
		}
		else
		{
			Entity entity = null;

			for (int j = 0; j < 1; ++j)
			{
				entity = EntityList.createEntityByID(entityID, worldIn);

				if (entity instanceof EntityLivingBase)
				{
					EntityLiving entityliving = (EntityLiving)entity;
					entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
					entityliving.rotationYawHead = entityliving.rotationYaw;
					entityliving.renderYawOffset = entityliving.rotationYaw;
					entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), (IEntityLivingData)null);
					worldIn.spawnEntity(entity);
					entityliving.playLivingSound();
				}
			}

			return entity;
		}
	}

	public String getName()
	{
		return name;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add(TextFormatting.GOLD + I18n.translateToLocal("item.animania_entity_egg.desc1") + " " + TextFormatting.DARK_GRAY + I18n.translateToLocal("item.animania_entity_egg.desc2"));
	}
}
