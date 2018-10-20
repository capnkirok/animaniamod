package com.animania.manual.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.animania.Animania;
import com.animania.common.helper.AnimaniaHelper;
import com.animania.common.helper.StringParser;
import com.animania.manual.components.ConfigComponent;
import com.animania.manual.components.CraftingComponent;
import com.animania.manual.components.EntityComponent;
import com.animania.manual.components.IManualComponent;
import com.animania.manual.components.ImageComponent;
import com.animania.manual.components.ItemComponent;
import com.animania.manual.components.LinkComponent;
import com.animania.manual.components.TextComponent;
import com.animania.manual.groups.ManualPage;
import com.animania.manual.groups.ManualTopic;
import com.animania.manual.gui.GuiManual;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.IResource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

public class ManualResourceLoader
{
	public static boolean errored = false;
	private static List<ManualTopic> topics = new ArrayList<ManualTopic>();
	public static ManualTopic firstTopic = null;

	public static void loadResources()
	{
		topics.clear();
		firstTopic = null;
		errored = false;
		GuiManual.INSTANCE.manualContent.clear();
		GuiManual.INSTANCE.lastTopic = null;
		GuiManual.INSTANCE.currentTopic = null;
		GuiManual.INSTANCE.pageIndex = 0;

		FileSystem filesystem = null;

		URL url = Animania.class.getResource("/assets/animania/manual/");
		try
		{
			if (url != null)
			{
				URI uri = url.toURI();
				Path path = null;

				if ("file".equals(uri.getScheme()))
				{
					path = Paths.get(Animania.class.getResource("/assets/animania/manual").toURI());
				}
				else
				{
					if (!"jar".equals(uri.getScheme()))
					{
						return;
					}

					filesystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
					path = filesystem.getPath("/assets/animania/manual");

				}

				Iterator<Path> it = Files.walk(path).iterator();

				while (it.hasNext())
				{
					Path next = it.next();
					if (next.toString().endsWith(".json"))
					{
						String sp = next.toString();
						String resLoc = sp.substring(sp.indexOf("manual"), sp.length());

						ResourceLocation loc = new ResourceLocation(Animania.MODID, resLoc.replace("\\", "/"));

						List<IResource> resources = Minecraft.getMinecraft().getResourceManager().getAllResources(loc);
						if (resources.isEmpty())
							continue;

						InputStream stream = resources.get(resources.size() - 1).getInputStream();

						readJson(stream, loc);
					}
				}

				GuiManual m = GuiManual.INSTANCE;
				for (ManualTopic c : topics)
				{
					if (c.isPermitted())
					{
						m.manualContent.put(c.getId(), c);
					}
				}
				m.currentTopic = firstTopic;
				m.lastTopic = firstTopic;
			}
		}
		catch (Exception e)
		{
			Animania.LOGGER.error(e);
		}
		
		if(filesystem != null)
			try
			{
				filesystem.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
	}

	public static void readJson(InputStream stream, ResourceLocation loc)
	{
		try
		{
			JsonParser parser = new JsonParser();
			JsonElement e = (JsonElement) parser.parse(new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)));
			if (e.isJsonObject())
			{
				JsonObject obj = e.getAsJsonObject();

				boolean firstPage = false;
				if (obj.has("isFirstPage"))
					firstPage = obj.get("isFirstPage").getAsBoolean();

				String requiredModid = Animania.MODID;
				if (obj.has("requiredModid"))
					requiredModid = obj.get("requiredModid").getAsString();

				String name = "UNTITLED";
				if (obj.has("name"))
					name = AnimaniaHelper.translateWithFormattingCodes(obj.get("name").getAsString());

				if (obj.has("contents"))
				{
					JsonArray contents = obj.get("contents").getAsJsonArray();

					List<IManualComponent> components = new ArrayList<IManualComponent>();
					List<ManualPage> pages = new ArrayList<ManualPage>();

					for (int i = 0; i < contents.size(); i++)
					{
						JsonElement content = contents.get(i);
						List<IManualComponent> c = getComponentFromString(content.getAsString(), components);
						components.addAll(c);
					}

					IManualComponent prevComponent = null;

					int height = 0;
					for (int k = 0; k < components.size(); k++)
					{
						IManualComponent c = components.get(k);
						if (prevComponent != null)
						{
							if (c.getY() == prevComponent.getY() && c.getObjectHeight() == prevComponent.getObjectHeight())
								height -= (c.getObjectHeight() + GuiManual.LINE_Y_OFFSET);
						}
						height += c.getObjectHeight() + GuiManual.LINE_Y_OFFSET;

						prevComponent = c;
						if (height > GuiManual.MANUAL_MAX_Y)
						{
							List<IManualComponent> splitComponents = new ArrayList<IManualComponent>();
							for (int j = 0; j < k; j++)
							{
								splitComponents.add(components.get(0));
								components.remove(0);
							}
							regerenatePositions(components);
							ManualPage page = new ManualPage(splitComponents);
							pages.add(page);
							height = 0;
							height += c.getObjectHeight() + GuiManual.LINE_Y_OFFSET;
							k = 0;
						}
					}

					ManualPage finalPage = new ManualPage(components);
					pages.add(finalPage);

					ManualTopic topic = new ManualTopic(loc, firstPage, name, pages);
					topic.setRequiredModid(requiredModid);

					if (firstPage)
						firstTopic = topic;
					topics.add(topic);
				}

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			errored = true;
		}
	}

	public static void regerenatePositions(List<IManualComponent> components)
	{
		int offsetY = 0;

		int prevPosY = 0;
		int prevHeight = 0;

		for (int i = 0; i < components.size(); i++)
		{

			IManualComponent lastComponent = i == 0 ? null : components.get(i - 1);
			IManualComponent thisComponent = components.get(i);

			if (lastComponent != null)
			{
				if (prevPosY == thisComponent.getY() && prevHeight == thisComponent.getObjectHeight())
				{
					offsetY = lastComponent.getY();
				}
				else
					offsetY = lastComponent.getY() + lastComponent.getObjectHeight() + GuiManual.LINE_Y_OFFSET;

			}
			prevPosY = thisComponent.getY();
			prevHeight = thisComponent.getObjectHeight();

			thisComponent.setY(offsetY);
		}
	}

	public static List<IManualComponent> getComponentFromString(String s, List<IManualComponent> otherComponents)
	{
		int offsetY = 0;

		if (!otherComponents.isEmpty())
		{
			IManualComponent lastComponent = otherComponents.get(otherComponents.size() - 1);
			offsetY = lastComponent.getY() + lastComponent.getObjectHeight();
		}

		if (s.startsWith("@link@"))
		{
			if (s.contains("#"))
			{
				boolean prevLine = false;
				s = s.replace("@link@", "");

				if (s.contains("#prevline"))
				{
					s = s.replace("#prevline", "");
					prevLine = true;
				}

				String requiredModid = Animania.MODID;
				if (s.contains("$"))
				{
					requiredModid = s.substring(s.indexOf("$") + 1);
					s = s.replace("$" + requiredModid, "");
				}

				if (!ManualTopic.isPermitted(requiredModid))
					return Collections.EMPTY_LIST;

				String linkLoc = s.substring(0, s.indexOf("#"));
				ResourceLocation loc = new ResourceLocation(linkLoc);
				String display = s.substring(s.indexOf("#") + 1);
				display = AnimaniaHelper.translateWithFormattingCodes(display);
				
				List<IManualComponent> textComps = getTextComp(display, otherComponents, prevLine);
				
				List<IManualComponent> linkComps = new ArrayList<IManualComponent>();
				
				for(IManualComponent comp : textComps)
				{
					String displ = ((TextComponent)comp).toString();
					linkComps.add(new LinkComponent(comp.getX(), comp.getY(), displ, loc));
				}
				
				return linkComps;
			}
		}
		else if (s.startsWith("@image@"))
		{
			s = s.replace("@image@", "");
			ResourceLocation loc = new ResourceLocation(s);
			ImageComponent i = new ImageComponent(0, offsetY + GuiManual.LINE_Y_OFFSET, loc);
			return getList(i);
		}
		else if (s.startsWith("@entity@"))
		{
			s = s.replace("@entity@", "");
			ResourceLocation loc = new ResourceLocation(s);
			EntityComponent i = new EntityComponent(0, offsetY + GuiManual.LINE_Y_OFFSET, loc);
			return getList(i);
		}
		else if (s.startsWith("@crafting@"))
		{
			s = s.replace("@crafting@", "");
			List<IRecipe> recipes = AnimaniaHelper.getRecipesForOutput(s);
			if (!recipes.isEmpty())
			{
				CraftingComponent i = new CraftingComponent(0, offsetY + GuiManual.LINE_Y_OFFSET, recipes);
				return getList(i);
			}
		}
		else if (s.startsWith("@item@"))
		{
			s = s.replace("@item@", "");
			String[] items = s.split(",");
			ItemStack[] stacks = AnimaniaHelper.getItemStackArray(items);
			if (stacks.length > 6)
			{
				List<IManualComponent> itemComps = new ArrayList<IManualComponent>();
				List<ItemStack[]> splitStacks = new ArrayList<ItemStack[]>();

				while (stacks.length > 6)
				{
					ItemStack[] sA = new ItemStack[6];
					System.arraycopy(stacks, 0, sA, 0, 6);
					splitStacks.add(sA);
					ItemStack[] rest = new ItemStack[stacks.length - 6];
					System.arraycopy(stacks, 6, rest, 0, stacks.length - 6);
					stacks = rest;
				}
				if (stacks.length > 0)
					splitStacks.add(stacks);

				for (ItemStack[] split : splitStacks)
				{
					ItemComponent iC = new ItemComponent(0, offsetY + GuiManual.LINE_Y_OFFSET, split);
					itemComps.add(iC);
					offsetY = iC.getY() + iC.getObjectHeight();
				}
				return itemComps;
			}
			ItemComponent i = new ItemComponent(0, offsetY + GuiManual.LINE_Y_OFFSET, stacks);
			return getList(i);
		}
		else if (s.startsWith("@configitem@"))
		{
			s = s.replace("@configitem@", "");

			Object configObject = AnimaniaHelper.getConfigValue(s);
			if (configObject != null && configObject instanceof String[] || configObject instanceof String)
			{
				if (configObject instanceof String)
				{
					ItemStack stack = StringParser.getItemStack((String) configObject);
					ItemComponent i = new ItemComponent(0, offsetY + GuiManual.LINE_Y_OFFSET, new ItemStack[] { stack });
					return getList(i);
				}

				String[] items = (String[]) configObject;
				ItemStack[] stacks = AnimaniaHelper.getItemStackArray(items);
				if (stacks.length > 6)
				{
					List<IManualComponent> itemComps = new ArrayList<IManualComponent>();
					List<ItemStack[]> splitStacks = new ArrayList<ItemStack[]>();

					while (stacks.length > 6)
					{
						ItemStack[] sA = new ItemStack[6];
						System.arraycopy(stacks, 0, sA, 0, 6);
						splitStacks.add(sA);
						ItemStack[] rest = new ItemStack[stacks.length - 6];
						System.arraycopy(stacks, 6, rest, 0, stacks.length - 6);
						stacks = rest;
					}
					if (stacks.length > 0)
						splitStacks.add(stacks);

					for (ItemStack[] split : splitStacks)
					{
						ItemComponent iC = new ItemComponent(0, offsetY + GuiManual.LINE_Y_OFFSET, split);
						itemComps.add(iC);
						offsetY = iC.getY() + iC.getObjectHeight();
					}
					return itemComps;
				}
				ItemComponent i = new ItemComponent(0, offsetY + GuiManual.LINE_Y_OFFSET, stacks);
				return getList(i);
			}
		}
		else if (s.startsWith("@config@"))
		{

			boolean prevLine = false;
			s = s.replace("@config@", "");

			if (s.contains("#prevline"))
			{
				s = s.replace("#prevline", "");
				prevLine = true;
			}

			Object o = AnimaniaHelper.getConfigValue(s);
			if (o instanceof Number)
			{
				double d = ((Number) o).doubleValue();
				d *= 100;
				d = Math.round(d);
				d /= 100;

				if (d == (int) d)
					o = (int) d;
				else
					o = d;
			}
			if(o.getClass().isArray())
			{
				o = Arrays.toString((Object[])o).replace("[", "").replace("]", "");
			}
			
			String display = o + "";

			if (display.isEmpty())
				return Collections.EMPTY_LIST;

			List<IManualComponent> textComps = getTextComp(display, otherComponents, prevLine);
			
			List<IManualComponent> configComps = new ArrayList<IManualComponent>();
			
			for(IManualComponent comp : textComps)
			{
				String displ = ((TextComponent)comp).toString();
				configComps.add(new ConfigComponent(comp.getX(), comp.getY(), displ, s));
			}
			
			return configComps;
		}
		else if (s.startsWith("@configitemname@"))
		{

			boolean prevLine = false;
			s = s.replace("@configitemname@", "");

			if (s.contains("#prevline"))
			{
				s = s.replace("#prevline", "");
				prevLine = true;
			}

			Object o = AnimaniaHelper.getConfigValue(s);
			String itemname = o + "";

			if (itemname.isEmpty())
				return Collections.EMPTY_LIST;

			ItemStack stack = StringParser.getItemStack(itemname);
			if (stack.isEmpty())
				return Collections.EMPTY_LIST;

			String display = stack.getDisplayName();

			TextComponent c = new TextComponent(0, offsetY + GuiManual.LINE_Y_OFFSET, display);

			if (prevLine)
			{
				IManualComponent textComp = getTextComp(display, otherComponents, true).get(0);
				c.setX(textComp.getX()).setY(textComp.getY());
				if (Minecraft.getMinecraft().fontRenderer.getStringWidth(display) > GuiManual.MANUAL_MAX_X - c.getX())
					c.setX(0).setY(offsetY + GuiManual.LINE_Y_OFFSET);
			}
			return getList(c);
		}
		else if (s.startsWith("@text@"))
		{
			s = s.replace("@text@", "");
			boolean prevLine = false;
			if (s.contains("#prevline"))
			{
				prevLine = true;
				s = s.replace("#prevline", "");
			}
			return getTextComp(AnimaniaHelper.translateWithFormattingCodes(s), otherComponents, prevLine);
		}
		else
		{
			String translated = AnimaniaHelper.translateWithFormattingCodes(s);
			return getTextComp(translated, otherComponents, false);
		}

		return Collections.EMPTY_LIST;
	}

	private static List<IManualComponent> getTextComp(String s, List<IManualComponent> otherComponents, boolean prevLine)
	{
		FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
		int offsetY = 0;
		int offsetX = 0;

		if (s.isEmpty())
			return Collections.EMPTY_LIST;

		if (!otherComponents.isEmpty())
		{
			IManualComponent lastComponent = otherComponents.get(otherComponents.size() - 1);
			if (!prevLine)
				offsetY = lastComponent.getY() + lastComponent.getObjectHeight();
			if (prevLine)
			{
				offsetY = lastComponent.getY();
				offsetX = lastComponent.getX() + lastComponent.getObjectWidth() + GuiManual.LINE_X_OFFSET;
			}
		}

		if (offsetX >= GuiManual.MANUAL_MAX_X)
			offsetX = 0;

		int strWidth = fr.getStringWidth(s);
		if (strWidth > GuiManual.MANUAL_MAX_X - offsetX)
		{
			String firstWrapped = "";
			List<String> wrappedStrings;
			if (fr.getStringWidth(s.split(" ")[0]) < GuiManual.MANUAL_MAX_X - offsetX)
			{
				wrappedStrings = fr.listFormattedStringToWidth(s, GuiManual.MANUAL_MAX_X - offsetX);
				firstWrapped = wrappedStrings.get(0);
				s = s.replace(firstWrapped, "");
			}
			String format = fr.getFormatFromString(s);
			wrappedStrings = fr.listFormattedStringToWidth(format + s, GuiManual.MANUAL_MAX_X);
			List<IManualComponent> comps = new ArrayList<IManualComponent>();

			if (!firstWrapped.isEmpty())
			{
				if (fr.getStringWidth(firstWrapped) > GuiManual.MANUAL_MAX_X - offsetX)
				{
					if (!otherComponents.isEmpty())
					{
						IManualComponent lastComponent = otherComponents.get(otherComponents.size() - 1);
						offsetY = lastComponent.getY() + lastComponent.getObjectHeight();
					}

					TextComponent first = new TextComponent(0, offsetY, firstWrapped);
					comps.add(first);
					offsetX = 0;
				}
				else
				{
					TextComponent first = new TextComponent(offsetX, offsetY, firstWrapped);
					offsetY = first.getY() + first.getObjectHeight();
					comps.add(first);
					offsetX = 0;
				}
			}

			for (String str : wrappedStrings)
			{
				if (str.isEmpty())
					continue;
				TextComponent comp = new TextComponent(0, offsetY + GuiManual.LINE_Y_OFFSET, str);
				offsetY = comp.getY() + comp.getObjectHeight();
				comps.add(comp);
			}
			return comps;
		}
		else
		{
			if (!prevLine)
			{
				TextComponent comp = new TextComponent(0, offsetY + GuiManual.LINE_Y_OFFSET, s);
				return getList(comp);
			}
			else
			{
				TextComponent first = new TextComponent(offsetX, offsetY, s);
				return getList(first);
			}
		}

	}

	public static List<IManualComponent> getList(IManualComponent... components)
	{
		return Arrays.asList(components);
	}

}
