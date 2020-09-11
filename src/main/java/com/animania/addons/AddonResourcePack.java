package com.animania.addons;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.annotation.Nullable;

import org.apache.commons.io.filefilter.DirectoryFileFilter;

import com.animania.Animania;
import com.animania.api.addons.AnimaniaAddon;
import com.google.common.collect.Sets;

import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.common.Loader;

public class AddonResourcePack
{

	public static File getAddonFile(AnimaniaAddon addon)
	{
		try
		{
			String classpath = addon.getClass().getResource(addon.getClass().getSimpleName() + ".class").getFile();

			// System.out.println("Classpath for " + addon.getAddonName() + "
			// is: " + classpath);

			classpath = classpath.substring(0, classpath.indexOf("!"));
			classpath = classpath.substring(classpath.indexOf(":") + 1, classpath.length());

			classpath = classpath.replace("/./", "/");
			// classpath.length());

			classpath = URLDecoder.decode(classpath);

			// System.out.println("Edited classpath for " + addon.getAddonName()
			// + " is: " + classpath);

			String path = classpath;
			// String path = ".\\mods\\" + classpath;

			File f = new File(path);

			return f;
		} catch (Exception e)
		{
			System.out.println("Failed to get addon file!");
			Animania.LOGGER.error(e);
			return null;
		}
	}

	public static Tuple<FileSystem, Path> getAddonPath(AnimaniaAddon addon)
	{
		try
		{
			URI classpath = addon.getClass().getResource("/assets/" + addon.getAddonID() + "/").toURI();

			System.out.println("Path classpath: " + classpath);

			FileSystem fs = null;

			Path myPath;
			if (classpath.getScheme().equals("jar"))
			{
				fs = FileSystems.newFileSystem(classpath, Collections.<String, Object> emptyMap());
				// System.out.println("Filesystem: " + fs);
				// myPath = fs.getPath(".");
				myPath = fs.getPath(".");
			} else
			{

				myPath = Paths.get(classpath);
			}

			// System.out.println("Edited Path classpath: " + myPath);

			return new Tuple<FileSystem, Path>(fs, myPath);
		} catch (Exception e)
		{
			System.out.println("Failed to get addon path!");
			Animania.LOGGER.error(e);
			e.printStackTrace();
			return null;
		}
	}

	public static class Jar extends FileResourcePack
	{

		private AnimaniaAddon addon;
		private ZipFile resourcePackZipFile;

		public List<ResourceLocation> manualFiles = new ArrayList<ResourceLocation>();

		public Jar(AnimaniaAddon addon)
		{
			super(getAddonFile(addon));
			this.addon = addon;
		}

		@Override
		public String getPackName()
		{
			return "AnimaniaAddonResourcePack:" + addon.getAddonID();
		}

		@Override
		public boolean hasResourceName(String name)
		{
			try
			{
				return this.getResourcePackZipFile().getEntry(name.replace("assets/", "assets/" + addon.getAddonID() + "/")) != null;
			} catch (IOException var3)
			{
				var3.printStackTrace();
				return false;
			}
		}

		@Override
		public Set<String> getResourceDomains()
		{
			ZipFile zipfile;

			this.manualFiles.clear();

			try
			{
				zipfile = this.getResourcePackZipFile();
			} catch (IOException var8)
			{
				var8.printStackTrace();
				return Collections.<String> emptySet();
			}

			Enumeration<? extends ZipEntry> enumeration = zipfile.entries();
			Set<String> set = Sets.<String> newHashSet();

			while (enumeration.hasMoreElements())
			{
				ZipEntry zipentry = enumeration.nextElement();
				String s = zipentry.getName();

				if (s.startsWith("assets/" + addon.getAddonID() + "/"))
				{
					String[] list = s.split("/");

					if (s.contains("/manual/") && s.endsWith(".json"))
					{
						String resLoc = s.substring(s.indexOf("manual"), s.length());
						ResourceLocation loc = new ResourceLocation(Animania.MODID, resLoc.replace("\\", "/"));
						manualFiles.add(loc);
					}

					if (list.length == 3)
					{
						String s1 = list[2];

						if (s1.equals(s1.toLowerCase(java.util.Locale.ROOT)))
						{
							set.add(s1);
						} else
						{
							this.logNameNotLowercase(s1);
						}
					}
				}
			}

			return set;
		}

		@Override
		protected InputStream getInputStreamByName(String resourceName) throws IOException
		{

			if (resourceName.contains("pack.mcmeta"))
			{
				return Animania.class.getResourceAsStream("/addons.mcmeta");
			}

			resourceName = resourceName.replace("assets/", "assets/" + addon.getAddonID() + "/");

			return super.getInputStreamByName(resourceName);

		}

		private ZipFile getResourcePackZipFile() throws IOException
		{
			if (this.resourcePackZipFile == null)
			{
				this.resourcePackZipFile = new ZipFile(this.resourcePackFile);
			}

			return this.resourcePackZipFile;
		}

	}

	public static class Folder extends FolderResourcePack
	{

		private AnimaniaAddon addon;
		public List<ResourceLocation> manualFiles = new ArrayList<ResourceLocation>();

		public Folder(AnimaniaAddon addon)
		{
			super(getAddonFile(addon));
			this.addon = addon;
		}

		@Override
		public String getPackName()
		{
			return "AnimaniaAddonResourcePack:" + addon.getAddonID();
		}

		private static File getAddonFile(AnimaniaAddon addon)
		{
			File animania = Loader.instance().activeModContainer().getSource();

			return animania;
		}

		@Override
		protected InputStream getInputStreamByName(String resourceName) throws IOException
		{

			if (resourceName.equals("pack.mcmeta"))
			{
				return Animania.class.getResourceAsStream("/addons.mcmeta");
			}

			resourceName = resourceName.replace("assets/", "assets/" + addon.getAddonID() + "/");

			return super.getInputStreamByName(resourceName);

		}

		@Override
		protected boolean hasResourceName(String name)
		{
			return this.getFile(name.replace("assets/", "assets/" + addon.getAddonID() + "/")) != null;
		}

		@Nullable
		private File getFile(String name)
		{
			try
			{
				File file1 = new File(this.resourcePackFile, name);

				if (file1.isFile() && validatePath(file1, name))
				{
					return file1;
				}
			} catch (IOException var3)
			{

			}

			return null;
		}

		@Override
		public Set<String> getResourceDomains()
		{
			Set<String> set = Sets.<String> newHashSet();
			File file1 = new File(this.resourcePackFile, "assets/" + addon.getAddonID() + "/");

			this.manualFiles.clear();

			if (file1.isDirectory())
			{
				Iterator<Path> it;
				try
				{
					it = Files.walk(file1.toPath()).iterator();

					while (it.hasNext())
					{
						String s = it.next().toString();
						if (s.contains("manual") && s.endsWith(".json"))
						{
							String resLoc = s.substring(s.indexOf("manual"), s.length());
							ResourceLocation loc = new ResourceLocation(Animania.MODID, resLoc.replace("\\", "/"));
							manualFiles.add(loc);
						}
					}
				} catch (IOException e)
				{
					e.printStackTrace();
				}

				for (File file2 : file1.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY))
				{
					String s = getRelativeName(file1, file2);

					if (s.equals(s.toLowerCase(java.util.Locale.ROOT)))
					{
						set.add(s.substring(0, s.length() - 1));
					} else
					{
						this.logNameNotLowercase(s);
					}
				}
			}

			return set;
		}

	}
}
