package com.animania.client;

import com.animania.Animania;
import com.animania.api.data.AnimalContainer;
import com.animania.api.data.EntityGender;
import com.animania.common.blocks.IMetaBlockName;
import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;
import com.animania.common.items.ItemEntityEgg;
import com.animania.common.items.ItemEntityEggAnimated;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.NonNullList;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class AnimaniaTextures
{

	public static void registerTextures()
	{

		// Items
		register(ItemHandler.animaniaManual);

		registerEntityEggs();

		register(ItemHandler.entityeggrandomanimal);

		// Blocks
		register(Item.byBlock(BlockHandler.blockMud));
		register(Item.byBlock(BlockHandler.blockTrough));
		register(Item.byBlock(BlockHandler.blockNest));
		register(Item.byBlock(BlockHandler.blockStraw));
		register(Item.byBlock(BlockHandler.blockSaltLick));

		Animania.proxy.registerFluidBlockRendering(BlockHandler.blockSlop, "slop");
	}

	/**
	 * Registers Render for an Item
	 *
	 * @param item
	 */
	public static void register(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	public static void register(Item item, String name, int meta)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name, "inventory"));
	}

	public static void registerColored(Item item, String name)
	{
		for (int meta = 0; meta < 16; meta++)
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Animania.MODID + ":" + name + "_" + EnumDyeColor.byDyeDamage(meta).getName(), "inventory"));
	}

	public static void regSpecial(Block block)
	{
		NonNullList<ItemStack> list = NonNullList.create();

		block.getSubBlocks(Animania.TabAnimaniaResources, list);

		for (int i = 0; i < list.size(); i++)
		{
			ItemStack stack = list.get(i);

			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), stack.getMetadata(), new ModelResourceLocation(block.getRegistryName().toString() + "_" + ((IMetaBlockName) block).getSpecialName(stack), "inventory"));
			ModelBakery.registerItemVariants(Item.getItemFromBlock(block), new ResourceLocation(block.getRegistryName().toString() + "_" + ((IMetaBlockName) block).getSpecialName(stack)));

		}

	}

	public static void registerEntityEggs()
	{
		for (Item item : ItemHandler.entityEggList)
		{
			if (item instanceof ItemEntityEgg && !(item instanceof ItemEntityEggAnimated))
			{
				AnimalContainer animal = ((ItemEntityEgg) item).getAnimal();
				EntityGender gender = animal.getGender();

				if (gender != EntityGender.RANDOM)
				{

					switch (gender)
					{
					case MALE:
						ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Animania.MODID + ":" + "entity_egg_male", "inventory"));
						break;
					case FEMALE:
						ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Animania.MODID + ":" + "entity_egg_female", "inventory"));
						break;
					default:
						ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Animania.MODID + ":" + "entity_egg_genderless", "inventory"));
						break;
					}
				}

			}
		}
	}

}