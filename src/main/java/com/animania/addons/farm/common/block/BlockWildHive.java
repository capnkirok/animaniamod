package com.animania.addons.farm.common.block;

import com.animania.Animania;
import com.animania.common.handler.BlockHandler;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockWildHive extends BlockHive
{
	private String name = "block_wild_hive";

	public BlockWildHive()
	{
		super(Material.SPONGE, MaterialColor.YELLOW);
		this.setSoundType(SoundType.PLANT);
		this.setRegistryName(new ResourceLocation(Animania.MODID, this.name));
		this.setUnlocalizedName(Animania.MODID + "_" + this.name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, Direction.NORTH));
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setHardness(1.3f);
		this.setResistance(0.3f);
		BlockHandler.blocks.add(this);
		Item item = new BlockItem(this);
		item.setRegistryName(new ResourceLocation(Animania.MODID, "wild_hive"));
		ForgeRegistries.ITEMS.register(item);
	}
}
