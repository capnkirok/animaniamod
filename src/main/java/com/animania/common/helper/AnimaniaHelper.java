package com.animania.common.helper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import com.animania.Animania;
import com.animania.config.AnimaniaConfig;
import com.animania.network.client.BlockEntitySyncPacket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fluids.FluidInteractionResultHolder;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.ForgeRegistries;

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

	public static void sendBlockEntityUpdate(BlockEntity tile)
	{
		if (tile != null && tile.getLevel() != null && !tile.getLevel().isClientSide)
		{
			CompoundTag compound = new CompoundTag();
			compound = tile.writeToNBT(compound);

			CompoundTag data = new CompoundTag();
			data.putTag("data", compound);
			data.putInteger("x", tile.getPos().getX());
			data.putInteger("y", tile.getPos().getY());
			data.putInteger("z", tile.getPos().getZ());
			Animania.network.sendToAllAround(new BlockEntitySyncPacket(data), new NetworkRegistry.TargetPoint(tile.getLevel().provider.getDimension(), tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), 64));
		}

	}

	public static ItemStack getItemStack(JsonObject json)
	{
		String itemName = JsonUtils.getString(json, "item");

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
					nbt = JsonToNBT.getTagFromJson(GSON.toJson(element));
				else
					nbt = JsonToNBT.getTagFromJson(element.getAsString());

				CompoundTag tmp = new CompoundTag();
				if (nbt.hasKey("ForgeCaps"))
				{
					tmp.putTag("ForgeCaps", nbt.getTag("ForgeCaps"));
					nbt.removeTag("ForgeCaps");
				}

				tmp.putTag("tag", nbt);
				tmp.setString("id", itemName);
				tmp.putInteger("Count", JsonUtils.getInt(json, "count", 1));
				tmp.putInteger("Damage", JsonUtils.getInt(json, "data", 0));

				return new ItemStack(tmp);
			}
			catch (NBTException e)
			{
				throw new JsonSyntaxException("Invalid NBT Entry: " + e.toString());
			}
		}

		return new ItemStack(item, JsonUtils.getInt(json, "count", 1), JsonUtils.getInt(json, "data", 0));
	}

	public static void addItem(Player player, ItemStack stack)
	{
		if (!player.inventory.addItemStackToInventory(stack))
			player.dropItem(stack, false);
	}

	public static <T extends LivingEntity> List<T> getEntitiesInRange(Class<? extends T> filterEntity, double range, Level level, Entity theEntity)
	{
		List<T> list = level.<T> getEntitiesWithinAABB(filterEntity, new AxisAlignedBB(theEntity.getX() - range, theEntity.getY() - range, theEntity.getZ() - range, theEntity.getX() + range, theEntity.getY() + range, theEntity.getZ() + range));
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
		return level.<T> getEntitiesWithinAABB(filterEntity, new AxisAlignedBB(pos.getX() - range, pos.getY() - range, pos.getZ() - range, pos.getX() + range, pos.getY() + range, pos.getZ() + range));
	}

	public static <T extends Entity> List<T> getEntitiesInRangeGeneric(Class<? extends T> filterEntity, double range, Level level, Entity theEntity)
	{
		return level.<T> getEntitiesWithinAABB(filterEntity, new AxisAlignedBB(theEntity.getX() - range, theEntity.getY() - range, theEntity.getZ() - range, theEntity.getX() + range, theEntity.getY() + range, theEntity.getZ() + range));
	}

	public static RayTraceResult rayTrace(Player player, double blockReachDistance)
	{
		Vec3d vec3d = player.getPositionEyes(1f);
		Vec3d vec3d1 = player.getLook(1f);
		Vec3d vec3d2 = vec3d.addVector(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
		return player.level.rayTraceBlocks(vec3d, vec3d2, false, false, true);
	}

	public static boolean hasFluid(ItemStack stack, Fluid fluid)
	{
		return FluidUtil.getFluidContained(stack) != null && FluidUtil.getFluidContained(stack).amount >= 1000 && FluidUtil.getFluidContained(stack).getFluid() == fluid;
	}

	public static boolean isEmptyFluidContainer(ItemStack stack)
	{
		return FluidUtil.getFluidHandler(stack) != null && FluidUtil.getFluidContained(stack) == null;
	}

	public static boolean isWaterContainer(ItemStack stack)
	{
		return hasFluid(stack, FluidRegistry.WATER);
	}

	public static ItemStack emptyContainer(ItemStack stack)
	{
		ItemStack copy = stack.copy();
		copy.setCount(1);
		FluidInteractionResultHolder result = FluidUtil.tryEmptyContainer(copy, FluidUtil.getFluidHandler(new ItemStack(Items.BUCKET)), 1000, null, true);
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
		ArrayList<Item> list = new ArrayList<Item>();
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
			String translated = I18n.get(stripped);
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

	public static List<IRecipe> getRecipesForOutput(String output)
	{
		List<IRecipe> recipes = new ArrayList<IRecipe>();

		String[] split = output.split(",");
		for (String s : split)
		{
			ItemStack stack = StringParser.getItemStack(s);
			if (stack.isEmpty())
			{
				IRecipe re;
				if ((re = ForgeRegistries.RECIPES.getValue(new ResourceLocation(s))) != null)
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
				if (ItemStack.areItemStacksEqual(outputStack, stack))
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

		table = new ResourceLocation(table.getResourceDomain(), "loot_tables/" + table.getResourcePath() + ".json");

		try
		{
			InputStream stream = Minecraft.getMinecraft().getResourceManager().getResource(table).getInputStream();
			JsonParser parser = new JsonParser();
			JsonObject base = (JsonObject) parser.parse(new InputStreamReader(stream));
			JsonArray pools = base.getAsJsonArray("pools");
			for (int i = 0; i < pools.size(); i++)
			{
				JsonArray entries = pools.get(i).getAsJsonObject().getAsJsonArray("entries");
				for (int k = 0; k < entries.size(); k++)
				{
					String name = entries.get(k).getAsJsonObject().get("name").getAsString();
					Item item = Item.getByNameOrId(name);
					if (item != null)
					{
						ItemStack stack = new ItemStack(item);
						if (item == Item.getItemFromBlock(Blocks.WOOL))
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
		CompoundTag tag = stack.hasTagCompound() ? stack.getTagCompound() : new CompoundTag();
		CompoundTag display = new CompoundTag();
		NBTTagList lore = new NBTTagList();
		lore.appendTag(new NBTTagString(ChatFormatting.GRAY + tooltip));
		display.putTag("Lore", lore);
		tag.putTag("display", display);
		stack.putTagCompound(tag);
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
			if (BiomeDictionary.hasType(biome, t))
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
			LevelServer ws = (LevelServer) level;
			while (ws.getEntityFromUuid(entity.getUniqueID()) != null)
			{
				entity.setUniqueId(MathHelper.getRandomUUID(Animania.RANDOM));
			}
		}

		return level.spawnEntity(entity);
	}
}
