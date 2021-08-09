package com.animania.addons.catsdogs.common.handler;

import com.animania.addons.catsdogs.common.block.BlockPetBowl;
import com.animania.addons.catsdogs.common.block.BlockProp;
import com.animania.addons.catsdogs.common.tileentity.TileEntityPetBowl;
import com.animania.addons.catsdogs.common.tileentity.TileEntityProp;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.registries.GameRegistry;

public class CatsDogsAddonBlockHandler
{

	public static Block petBowl;
	public static Block catBed1;
	public static Block catBed2;
	public static Block catTower;
	public static Block dogHouse;
	public static Block dogPillow;
	public static Block litterBox;

	/**
	 * Register Blocks <br>
	 * Register TileEntities
	 */
	public static void preInit()
	{
		petBowl = new BlockPetBowl();
		catBed1 = new BlockProp("cat_bed_1", Material.WOOL, MaterialColor.CLOTH, SoundType.CLOTH).setAABB(2, 0, 2, 14, 1, 14).setHardness(1.0f).setResistance(0.8f);
		catBed2 = new BlockProp("cat_bed_2", Material.CLOTH, MaterialColor.CLOTH, SoundType.CLOTH).setAABB(2, 0, 2, 14, 2, 14).setHardness(1.0f).setResistance(0.8f);
		catTower = new BlockProp("cat_tower", Material.WOOD, MaterialColor.WOOD, SoundType.WOOD).setAABB(0, 0, 0, 16, 24, 16).setHardness(1.4f).setResistance(0.8f);
		dogHouse = new BlockProp("dog_house", Material.WOOD, MaterialColor.WOOD, SoundType.STONE).setHardness(1.5f).setResistance(1.4f);
		dogPillow = new BlockProp("dog_pillow", Material.CLOTH, MaterialColor.CLOTH, SoundType.CLOTH).setAABB(1, 0, 1, 15, 1, 15).setHardness(1.0f).setResistance(0.8f);
		litterBox = new BlockProp("litter_box", Material.CLOTH, MaterialColor.CLOTH, SoundType.STONE).setAABB(1, 0, 1, 15, 3, 15).setHardness(1.5f).setResistance(0.8f);

		preInitTe();
	}

	private static void preInitTe()
	{
		GameRegistry.registerTileEntity(TileEntityPetBowl.class, "animania:pet_bowl");
		GameRegistry.registerTileEntity(TileEntityProp.class, "animania:pet_prop");

	}

}
