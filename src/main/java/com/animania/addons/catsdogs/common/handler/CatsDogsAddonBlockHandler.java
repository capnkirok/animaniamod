package com.animania.addons.catsdogs.common.handler;

import com.animania.addons.catsdogs.common.block.BlockPetBowl;
import com.animania.addons.catsdogs.common.block.BlockProp;
import com.animania.addons.catsdogs.common.tileentity.TileEntityPetBowl;
import com.animania.addons.catsdogs.common.tileentity.TileEntityProp;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
		catBed1 = new BlockProp("cat_bed_1", Material.CLOTH, MapColor.CLOTH).setAABB(2,0,2,14,1,14).setHardness(1.0f).setResistance(0.8f);
		catBed2 = new BlockProp("cat_bed_2", Material.CLOTH, MapColor.CLOTH).setAABB(2, 0, 2, 14, 2, 14).setHardness(1.0f).setResistance(0.8f);
		catTower = new BlockProp("cat_tower", Material.WOOD, MapColor.WOOD).setHardness(1.4f).setResistance(0.8f);
		dogHouse = new BlockProp("dog_house", Material.WOOD, MapColor.WOOD).setHardness(1.5f).setResistance(1.4f);
		dogPillow = new BlockProp("dog_pillow", Material.CLOTH, MapColor.CLOTH).setHardness(1.0f).setResistance(0.8f);
		litterBox = new BlockProp("litter_box", Material.CLOTH, MapColor.CLOTH).setHardness(1.5f).setResistance(0.8f);

		
		preInitTe();
	}

	private static void preInitTe()
	{
		GameRegistry.registerTileEntity(TileEntityPetBowl.class, "animania:pet_bowl");
		GameRegistry.registerTileEntity(TileEntityProp.class, "animania:pet_prop");

	}
	

}
