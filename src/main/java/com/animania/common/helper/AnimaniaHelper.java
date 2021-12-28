package com.animania.common.helper;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;
import com.animania.network.client.BlockEntitySyncPacket;
import com.google.gson.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.util.JsonUtils;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class AnimaniaHelper
{

	private static Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	public static ItemStack getItem(String name)
	{
		ItemStack stack = ItemStack.EMPTY;
		int metaLoc;
		boolean metaFlag = false;
		String metaVal = "";

		metaLoc = name.indexOf("#");

		if (metaLoc > -1)
		{
			metaVal = name.substring(metaLoc);
			name = name.replace(metaVal, "");
			metaVal = metaVal.replace("#", "");
			metaFlag = true;
		}

		Item item = Item.getByNameOrId(name);

		if (item != null)
		{

			if (metaFlag)
			{
				stack = new ItemStack(item, 1, Integer.parseInt(metaVal));
			}
			else
			{
				stack = new ItemStack(item, 1);
			}
		}

		return stack;

	}

	public static Block getBlock(String name)
	{
		if (name.contains("#"))
		{
			name = name.substring(0, name.indexOf("#"));
		}

		Block b = Block.getBlockFromName(name);

		if (b == null)
			return Blocks.AIR;

		return b;
	}

	public static BlockState getBlockState(String name)
	{
		int meta = 0;
		if (name.contains("#"))
		{
			try
			{
				meta = Integer.parseInt(name.substring(name.indexOf("#")).replace("#", ""));
			}
			catch (Exception e)
			{
			}

			name = name.substring(0, name.indexOf("#"));
		}

		Block b = Block.getBlockFromName(name);

		if (b == null)
			return Blocks.AIR.defaultBlockState();

		return b.getStateFromMeta(meta);
	}

	public static void sendBlockEntityUpdate(BlockEntity blockEntity)
	{
		if (blockEntity != null && blockEntity.getLevel() != null && !blockEntity.getLevel().isClientSide)
		{
			CompoundTag compound = new CompoundTag();
			blockEntity.deserializeNBT(compound);
			compound = blockEntity.serializeNBT();

			CompoundTag data = new CompoundTag();
			data.put("data", compound);
			data.putInt("x", blockEntity.getBlockPos().getX());
			data.putInt("y", blockEntity.getBlockPos().getY());
			data.putInt("z", blockEntity.getBlockPos().getZ());
			Animania.network.sendToAllAround(new BlockEntitySyncPacket(data), new NetworkRegistry.TargetPoint(blockEntity.getLevel().provider.getDimension(), blockEntity.getPos().getX(), blockEntity.getPos().getY(), blockEntity.getPos().getZ(), 64));
		}

	}

	public static ItemStack getItemStack(JsonObject json)
	{
		String itemName = JsonUtils.readNBT(json, "item").get("name").getAsString();

		Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName));

		if (item == null)
			throw new JsonSyntaxException("Unknown item '" + itemName + "'");

		if (item.getHasSubtypes() && !json.has("data"))
			throw new JsonParseException("Missing data for item '" + itemName + "'");

		if (json.has("nbt"))
		{
			// Lets hope this works? Needs test
			try
			{
				JsonElement element = json.get("nbt");
				CompoundTag nbt;
				if (element.isJsonObject())
					nbt = TagParser.parseTag(GSON.toJson(element));
				else
					nbt = TagParser.parseTag(element.getAsString());

				CompoundTag tmp = new CompoundTag();
				if (nbt.contains("ForgeCaps"))
				{
					tmp.put("ForgeCaps", nbt.get("ForgeCaps"));
					nbt.remove("ForgeCaps");
				}

				tmp.put("tag", nbt);
				tmp.putString("id", itemName);
				tmp.putInt("Count", JsonUtils.getInt(json, "count", 1));
				tmp.putInt("Damage", JsonUtils.getInt(json, "data", 0));

				return new ItemStack(tmp);
			}
			catch (CommandSyntaxException e)
			{
				throw new JsonSyntaxException("Invalid NBT Entry: " + e);
			}
		}

		return new ItemStack(item, JsonUtils.getInt(json, "count", 1), JsonUtils.getInt(json, "data", 0));
	}

	public static void addItem(Player player, ItemStack stack)
	{
		if (!player.getInventory().add(stack))
			player.drop(stack, false);
	}

	public static <T extends LivingEntity> List<T> getEntitiesInRange(Class<? extends T> filterEntity, double range, Level level, Entity theEntity)
	{
		List<T> list = level.<T> getEntitiesOfClass(filterEntity, new AABB(theEntity.getX() - range, theEntity.getY() - range, theEntity.getZ() - range, theEntity.getX() + range, theEntity.getY() + range, theEntity.getZ() + range));
		list.removeIf(e -> e == theEntity);
		return list;
	}

	public static <T extends LivingEntity> List<T> getEntitiesInRangeWithPredicate(Class<? extends T> filterEntity, double range, Level level, Entity theEntity, Predicate<T> predicate)
	{
		List<T> list = getEntitiesInRange(filterEntity, range, level, theEntity);
		list.removeIf(predicate.negate());
		return list;
	}

	public static <T extends LivingEntity> List<T> getEntitiesInRange(Class<? extends T> filterEntity, double range, Level level, BlockPos pos)
	{
		return level.<T> getEntitiesOfClass(filterEntity, new AABB(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX() + range, pos.getY() + range, pos.getZ() + range));
	}

	public static <T extends Entity> List<T> getEntitiesInRangeGeneric(Class<? extends T> filterEntity, double range, Level level, Entity theEntity)
	{
		return level.<T> getEntitiesOfClass(filterEntity, new AABB(theEntity.getX() - range, theEntity.getY() - range, theEntity.getZ() - range, theEntity.getX() + range, theEntity.getY() + range, theEntity.getZ() + range));
	}

	public static RayTraceResult rayTrace(Player player, double blockReachDistance)
	{
		Vec3 vec3d = player.getEyePosition(1f);
		Vec3 vec3d1 = player.getLookAngle(1f);
		Vec3 vec3d2 = vec3d.add(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
		return player.level.rayTraceBlocks(vec3d, vec3d2, false, false, true);
	}

	public static boolean hasFluid(ItemStack stack, Fluid fluid)
	{
		return FluidUtil.getFluidContained(stack).isPresent() && FluidUtil.getFluidContained(stack).get().getAmount() >= 1000 && FluidUtil.getFluidContained(stack).get().getFluid() == fluid;
	}

	public static boolean isEmptyFluidContainer(ItemStack stack)
	{
		return FluidUtil.getFluidHandler(stack) != null && !FluidUtil.getFluidContained(stack).isPresent();
	}

	public static boolean isWaterContainer(ItemStack stack)
	{
		return hasFluid(stack, FluidRegistry.WATER);
	}

	public static ItemStack emptyContainer(ItemStack stack)
	{
		ItemStack copy = stack.copy();
		copy.setCount(1);
		IFluidHandler fluidHandler = FluidUtil.getFluidHandler(new ItemStack(Items.BUCKET)).resolve().get();
		FluidActionResult result = FluidUtil.tryEmptyContainer(copy, fluidHandler, 1000, null, true);
		return result.result;
	}

	public static boolean containsItemStack(Collection<ItemStack> stacks, ItemStack toSearch)
	{
		for (ItemStack s : stacks)
		{
			if (ItemStack.areItemsEqual(s, toSearch))
				return true;
		}
		return false;
	}

	public static Item[] getItemArray(String[] names)
	{
		ArrayList<Item> list = new ArrayList<>();
		for (String name : names)
		{
			Item i = StringParser.getItem(name);
			if (i != null)
			{
				list.add(i);
			}
			else
			{
				NonNullList<ItemStack> stacks = OreDictionary.getOres(name);
				if (!stacks.isEmpty())
				{
					for (ItemStack s : stacks)
						list.add(s.getItem());
				}
			}
		}

		return list.toArray(new Item[list.size()]);
	}

	public static ItemStack[] getItemStackArray(String[] names)
	{
		ArrayList<ItemStack> list = new ArrayList<>();
		for (String name : names)
		{
			ItemStack i = StringParser.getItemStack(name);
			if (!i.isEmpty())
			{
				list.add(i);
			}
			else
			{
				NonNullList<ItemStack> stacks = OreDictionary.getOres(name);
				if (!stacks.isEmpty())
				{
					list.addAll(stacks);
				}
			}
		}

		return list.toArray(new ItemStack[list.size()]);
	}

	public static Type[] getBiomeTypes(String[] types)
	{
		Type[] bt = new Type[types.length];
		for (int i = 0; i < types.length; i++)
		{
			String s = types[i].toUpperCase();
			bt[i] = Type.getType(s);
		}

		return bt;
	}

	public static String translateWithFormattingCodes(String s)
	{
		String[] split = s.split(" ");
		String result = s;
		for (String ss : split)
		{
			String stripped = ChatFormatting.stripFormatting(ss);
			String translated = new TranslatableComponent(stripped);
			result = result.replace(stripped, translated);
		}
		return result;
	}

	public static Object getConfigValue(String configName)
	{

		List<Configuration> configs = AnimaniaConfig.EventHandler.getConfiguration();
		for (Configuration config : configs)
		{
			if (config != null)
			{
				String[] firstSplit = configName.split(",");
				String[] output = new String[firstSplit.length];
				boolean doReturn = false;
				if (firstSplit.length > 1)
				{
					for (int i = 0; i < firstSplit.length; i++)
					{
						String conf = firstSplit[i];
						String[] split2 = conf.split(";");
						if (split2.length > 1 && config.hasCategory(split2[0]) && config.getCategory(split2[0]).containsKey(split2[1]))
						{
							Property prop = config.getCategory(split2[0]).get(split2[1]);
							if (prop != null)
							{
								output[i] = prop.getString();
								doReturn = true;
							}
						}
					}
					if (doReturn)
						return output;
				}

				String[] split = configName.split(";");
				if (split.length > 1 && config.hasCategory(split[0]) && config.getCategory(split[0]).containsKey(split[1]))
				{
					Property prop = config.getCategory(split[0]).get(split[1]);
					if (prop != null)
					{
						if (prop.isBooleanList())
							return prop.getBooleanList();
						if (prop.isBooleanValue())
							return prop.getBoolean();
						if (prop.isDoubleList())
							return prop.getDoubleList();
						if (prop.isDoubleValue())
							return prop.getDouble();
						if (prop.isIntList())
							return prop.getIntList();
						if (prop.isIntValue())
							return prop.getInt();
						if (prop.isList())
							return prop.getStringList();
						if (prop.isLongValue())
							return prop.getLong();

						if (prop.getString() != null)
							return prop.getString();
					}
				}
			}
		}
		return "";

	}

	public static List<Recipe<?>> getRecipesForOutput(String output)
	{
		List<Recipe<?>> recipes = new ArrayList<>();

		String[] split = output.split(",");
		for (String s : split)
		{
			ItemStack stack = StringParser.getItemStack(s);
			if (stack.isEmpty())
			{
				Recipe<?> re = ForgeRegistries.RECIPE_SERIALIZERS.getValue(new ResourceLocation(s));
				if (re != null)
				{
					recipes.add(re);
				}
				continue;
			}

			stack.setCount(1);

			for (IRecipe r : ForgeRegistries.RECIPES)
			{
				ItemStack outputStack = r.getRecipeOutput().copy();
				outputStack.setCount(1);
				if (ItemStack.matches(outputStack, stack))
				{
					recipes.add(r);
				}
			}
		}

		return recipes;
	}

	public static ItemStack[] getItemsForLoottable(ResourceLocation table)
	{
		List<ItemStack> stacks = new ArrayList<>();

		table = new ResourceLocation(table.getNamespace(), "loot_tables/" + table.getPath() + ".json");

		try
		{
			InputStream stream = Minecraft.getInstance().getResourceManager().getResource(table).getInputStream();
			JsonParser parser = new JsonParser();
			JsonObject base = (JsonObject) parser.parse(new InputStreamReader(stream));
			JsonArray pools = base.getAsJsonArray("pools");
			for (int i = 0; i < pools.size(); i++)
			{
				JsonArray entries = pools.get(i).getAsJsonObject().getAsJsonArray("entries");
				for (int k = 0; k < entries.size(); k++)
				{
					String name = entries.get(k).getAsJsonObject().get("name").getAsString();
					Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(name));
					if (item != null)
					{
						ItemStack stack = new ItemStack(item);
						if (item == Item.BY_BLOCK.get(Blocks.WHITE_WOOL))
							stack = addTooltipToStack(stack, I18n.translateToLocal("manual.blocks.wool.colored"));
						stacks.add(stack);
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return stacks.toArray(new ItemStack[stacks.size()]);
	}

	public static ItemStack addTooltipToStack(ItemStack stack, String tooltip)
	{
		CompoundTag tag = stack.hasTag() ? stack.getTag() : new CompoundTag();
		CompoundTag display = new CompoundTag();
		ListTag lore = new ListTag();
		lore.add(StringTag.valueOf(ChatFormatting.GRAY + tooltip));
		display.put("Lore", lore);
		tag.put("display", display);
		stack.deserializeNBT(tag);
		return stack;
	}

	public static ResourceLocation[] getResourceLocations(String... strings)
	{
		ResourceLocation[] res = new ResourceLocation[strings.length];
		for (int i = 0; i < strings.length; i++)
		{
			res[i] = new ResourceLocation(strings[i]);
		}

		return res;
	}

	public static boolean hasBiomeType(Biome biome, Type... types)
	{
		for (Type t : types)
		{
			if (BiomeDictionary.hasType(ForgeRegistries.BIOMES.getResourceKey(biome).get(), t))
				return true;
		}

		return false;
	}

	/*
	 * Spawns entity and ensures the unique UUID. Does not re-roll the UUID on
	 * remote levels.
	 */
	public static boolean spawnEntity(Level level, Entity entity)
	{
		if (!level.isClientSide)
		{
			ServerLevel ws = (ServerLevel) level;
			while (ws.getEntity(entity.getUUID()) != null)
			{
				entity.setUUID(Mth.createInsecureUUID(Animania.RANDOM));
			}
		}

		return level.spawnEntity(entity);
	}
}
