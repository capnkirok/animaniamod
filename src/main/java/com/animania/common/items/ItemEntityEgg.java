package com.animania.common.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.animania.Animania;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.AnimalContainer;
import com.animania.common.entities.AnimaniaType;
import com.animania.common.entities.EntityGender;
import com.animania.common.entities.RandomAnimalType;
import com.animania.common.entities.chickens.ChickenType;
import com.animania.common.entities.cows.CowType;
import com.animania.common.entities.goats.GoatType;
import com.animania.common.entities.interfaces.ISpawnable;
import com.animania.common.entities.peacocks.PeacockType;
import com.animania.common.entities.pigs.PigType;
import com.animania.common.entities.rodents.rabbits.RabbitType;
import com.animania.common.entities.sheep.SheepType;
import com.animania.common.handler.ItemHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEntityEgg extends Item
{

	private String name = "entity_egg";
	public AnimaniaType type;
	public EntityGender gender;

	public static Map<AnimalContainer, Item> ANIMAL_EGGS = new HashMap<AnimalContainer, Item>();
	public static Map<AnimalContainer, Integer> ANIMAL_COLOR_PRIMARY = new HashMap<AnimalContainer, Integer>();
	public static Map<AnimalContainer, Integer> ANIMAL_COLOR_SECONDARY = new HashMap<AnimalContainer, Integer>();
	public static Map<AnimalContainer, Boolean> ANIMAL_USES_COLOR = new HashMap<AnimalContainer, Boolean>();

	public ItemEntityEgg(String atype, AnimaniaType animal, EntityGender gender)
	{
		super();
		this.setCreativeTab(Animania.TabAnimaniaEggs);
		this.maxStackSize = 64;
		this.name = this.name + "_" + atype;
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		ForgeRegistries.ITEMS.register(this);
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.type = animal;
		this.gender = gender;

		ANIMAL_EGGS.put(new AnimalContainer(animal, gender), this);

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
			if (type instanceof CowType)
			{
				entity = EntityGender.getEntity(CowType.values()[Animania.RANDOM.nextInt(((CowType) type).values().length)], gender, world);
			}
			if (type instanceof PigType)
			{
				entity = EntityGender.getEntity(PigType.values()[Animania.RANDOM.nextInt(((PigType) type).values().length)], gender, world);
			}
			if (type instanceof ChickenType)
			{
				entity = EntityGender.getEntity(ChickenType.values()[Animania.RANDOM.nextInt(((ChickenType) type).values().length)], gender, world);
			}
			if(type instanceof GoatType)
			{
				entity = EntityGender.getEntity(GoatType.values()[Animania.RANDOM.nextInt(((GoatType) type).values().length)], gender, world);
			}
			if(type instanceof PeacockType)
			{
				entity = EntityGender.getEntity(PeacockType.values()[Animania.RANDOM.nextInt(((PeacockType) type).values().length)], gender, world);
			}
			if(type instanceof RabbitType)
			{
				entity = EntityGender.getEntity(RabbitType.values()[Animania.RANDOM.nextInt(((RabbitType) type).values().length)], gender, world);
			}
			if(type instanceof SheepType)
			{
				entity = EntityGender.getEntity(SheepType.values()[Animania.RANDOM.nextInt(((SheepType) type).values().length)], gender, world);
			}
			if(type instanceof RandomAnimalType)
			{
				entity = EntityGender.getEntity(type, gender, world);
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

			world.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, ModSoundEvents.combo, SoundCategory.PLAYERS, 0.8F, ((Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			entity.rotationYawHead = entity.rotationYaw;
			entity.renderYawOffset = entity.rotationYaw;
			world.spawnEntity(entity);
			return EnumActionResult.SUCCESS;

		}

		return EnumActionResult.FAIL;
	}

	public AnimalContainer getAnimal()
	{
		return new AnimalContainer(this.type, this.gender);
	}

	public String getName()
	{
		return this.name;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		return I18n.translateToLocal("entity.animania:" + stack.getItem().getRegistryName().getResourcePath().replace("entity_egg_", "") + ".name");
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.GOLD + I18n.translateToLocal("item.animania_entity_egg.desc1") + " " + TextFormatting.DARK_GRAY + I18n.translateToLocal("item.animania_entity_egg.desc2"));
	}
	
	

	@SideOnly(Side.CLIENT)
	public static class Color implements IItemColor
	{
		@Override
		public int colorMultiplier(ItemStack stack, int tintIndex)
		{
			World world = Minecraft.getMinecraft().world;
			if (!stack.isEmpty() && stack.getItem() != ItemHandler.entityeggrandomanimal)
			{
				AnimalContainer animal = ((ItemEntityEgg) stack.getItem()).getAnimal();

				if (animal.getGender() != EntityGender.RANDOM)
				{
					if (ANIMAL_USES_COLOR.containsKey(animal) && ANIMAL_USES_COLOR.get(animal).booleanValue())
					{
						switch (tintIndex)
						{
						case 0:
							return ANIMAL_COLOR_PRIMARY.get(animal).intValue();
						case 1:
							return ANIMAL_COLOR_SECONDARY.get(animal).intValue();

						}
					}
				}
			}
			return -1;
		}

	}
}
