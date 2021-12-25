package com.animania.common.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.animania.Animania;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.api.interfaces.AnimaniaType;
import com.animania.api.interfaces.IFoodEating;
import com.animania.common.ModSoundEvents;
import com.animania.common.entities.RandomAnimalType;
import com.animania.common.handler.ItemHandler;
import com.animania.common.helper.AnimaniaHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemEntityEgg extends Item
{

	private String name = "entity_egg";
	public AnimaniaType type;
	public EntityGender gender;

	public static Map<AnimalContainer, Item> ANIMAL_EGGS = new HashMap<AnimalContainer, Item>();
	public static Map<AnimalContainer, Integer> ANIMAL_COLOR_PRIMARY = new HashMap<>();
	public static Map<AnimalContainer, Integer> ANIMAL_COLOR_SECONDARY = new HashMap<>();
	public static Map<AnimalContainer, Boolean> ANIMAL_USES_COLOR = new HashMap<>();

	public ItemEntityEgg(String atype, AnimaniaType animal, EntityGender gender)
	{
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
	public ActionResultType onItemUse(PlayerEntity playerIn, Level level, BlockPos pos, EnumHand hand, Direction facing, float hitX, float hitY, float hitZ)
	{
		pos = pos.offset(facing);

		ItemStack stack = playerIn.getHeldItem(hand);

		if (level.isRemote)
			return ActionResultType.SUCCESS;

		LivingEntity entity = null;

		if (this.gender == EntityGender.RANDOM)
		{
			Class<? extends AnimaniaType> clazz = this.type.getClass();
			AnimaniaType[] types = clazz.getEnumConstants();

			if (this.type instanceof RandomAnimalType)
				entity = EntityGender.getEntity(this.type, this.gender, level);
			else
				entity = EntityGender.getEntity(types[Animania.RANDOM.nextInt(types.length)], this.gender, level);
		}
		else
		{
			entity = EntityGender.getEntity(this.type, this.gender, level);
		}
		if (entity != null)
		{

			entity.setLocationAndAngles(pos.getX() + .5, pos.getY(), pos.getZ() + .5, MathHelper.wrapDegrees(level.rand.nextFloat() * 360.0F), 0.0F);

			if (stack.hasDisplayName())
				entity.setCustomNameTag(stack.getDisplayName());

			if (!playerIn.isCreative())
				stack.shrink(1);

			level.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), ModSoundEvents.combo, SoundCategory.PLAYERS, 0.8F, ((Animania.RANDOM.nextFloat() - Animania.RANDOM.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			entity.rotationYawHead = entity.rotationYaw;
			entity.renderYawOffset = entity.rotationYaw;

			if (entity instanceof IFoodEating foodEating)
			{
				foodEating.setInteracted(true);
			}

			AnimaniaHelper.spawnEntity(level, entity);
			return ActionResultType.SUCCESS;

		}

		return ActionResultType.FAIL;
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
	public void addInformation(ItemStack stack, Level levelIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.GOLD + I18n.translateToLocal("item.animania_entity_egg.desc1") + " " + TextFormatting.DARK_GRAY + I18n.translateToLocal("item.animania_entity_egg.desc2"));
	}

	@SideOnly(Dist.CLIENT)
	public static class Color implements IItemColor
	{
		@Override
		public int colorMultiplier(ItemStack stack, int tintIndex)
		{
			Level level = Minecraft.getMinecraft().level;
			if (!stack.isEmpty() && stack.getItem() != ItemHandler.entityeggrandomanimal)
			{
				AnimalContainer animal = ((ItemEntityEgg) stack.getItem()).getAnimal();

				if (animal.getGender() != EntityGender.RANDOM && ANIMAL_USES_COLOR.containsKey(animal) && ANIMAL_USES_COLOR.get(animal).booleanValue())
				{
					switch (tintIndex)
					{
					case 0:
						return ANIMAL_COLOR_PRIMARY.get(animal);
					case 1:
						return ANIMAL_COLOR_SECONDARY.get(animal);

					}
				}
			}
			return -1;
		}

	}
}
