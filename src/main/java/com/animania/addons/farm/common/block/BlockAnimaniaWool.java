package com.animania.addons.farm.common.block;

import com.animania.Animania;
import com.animania.common.blocks.AnimaniaBlock;
import com.animania.common.blocks.IMetaBlockName;
import com.animania.common.items.SubtypesItemBlock;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAnimaniaWool extends AnimaniaBlock implements IMetaBlockName
{

	public static final PropertyEnum<BlockAnimaniaWool.EnumType> VARIANT = PropertyEnum.<BlockAnimaniaWool.EnumType> create("variant", BlockAnimaniaWool.EnumType.class);

	public BlockAnimaniaWool()
	{
		super("wool", Material.CLOTH, MapColor.GRAY, false);
		this.setHardness(0.8F);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setSoundType(SoundType.CLOTH);
		ForgeRegistries.ITEMS.register(new SubtypesItemBlock(this));
		this.setHarvestLevel("shears", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.DORSET_BROWN));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items)
	{

		for (BlockAnimaniaWool.EnumType blockstone$enumtype : BlockAnimaniaWool.EnumType.values())
		{
			items.add(new ItemStack(this, 1, blockstone$enumtype.getMetadata()));
		}

	}

	@Override
	public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos)
	{
		ItemStack stack = player.getHeldItemMainhand();
		if (!stack.isEmpty() && stack.getItem() instanceof ItemShears)
			return 0.16f;
		else
			return super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		worldIn.setBlockState(pos, this.getStateFromMeta(stack.getMetadata()));
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return state.getValue(VARIANT).getMetadata();
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, BlockAnimaniaWool.EnumType.byMetadata(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockAnimaniaWool.EnumType) state.getValue(VARIANT)).getMetadata();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		return ((BlockAnimaniaWool.EnumType) state.getValue(VARIANT)).getMapColor();
	}

	public static enum EnumType implements IStringSerializable
	{
		DORSET_BROWN(0, MapColor.BROWN, "dorset_brown"), FRIESIAN_BLACK(1, MapColor.BLACK, "friesian_black"), FRIESIAN_BROWN(2, MapColor.BROWN, "friesian_brown"), JACOB(3, MapColor.SNOW, "jacob"), MERINO_BROWN(4, MapColor.BROWN, "merino_brown"), MERINO_WHITE(5, MapColor.SNOW, "merino_white"), SUFFOLK_BROWN(6, MapColor.BROWN, "suffolk_brown");

		/** Array of the Block's BlockStates */
		private static final BlockAnimaniaWool.EnumType[] META = new BlockAnimaniaWool.EnumType[values().length];
		/** The BlockState's metadata. */
		private final int meta;
		/** The EnumType's name. */
		private final String name;
		private final String unlocalizedName;
		private final MapColor mapColor;

		private EnumType(int i, MapColor color, String name)
		{
			this(i, color, name, name);
		}

		private EnumType(int meta, MapColor color, String name, String name2)
		{
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = name2;
			this.mapColor = color;
		}

		/**
		 * Returns the EnumType's metadata value.
		 */
		public int getMetadata()
		{
			return this.meta;
		}

		public MapColor getMapColor()
		{
			return this.mapColor;
		}

		public String toString()
		{
			return this.name;
		}

		/**
		 * Returns an EnumType for the BlockState from a metadata value.
		 */
		public static BlockAnimaniaWool.EnumType byMetadata(int meta)
		{
			if (meta < 0 || meta >= META.length)
			{
				meta = 0;
			}

			return META[meta];
		}

		public String getName()
		{
			return this.name;
		}

		public String getUnlocalizedName()
		{
			return this.unlocalizedName;
		}

		static
		{
			for (BlockAnimaniaWool.EnumType blockstone$enumtype : values())
			{
				META[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
			}
		}

	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		switch (stack.getItemDamage())
		{
		case 0:
			return "dorset_brown";
		case 1:
			return "friesian_black";
		case 2:
			return "friesian_brown";
		case 3:
			return "jacob";
		case 4:
			return "merino_brown";
		case 5:
			return "merino_white";
		case 6:
			return "suffolk_brown";
		}

		return "";
	}

}
