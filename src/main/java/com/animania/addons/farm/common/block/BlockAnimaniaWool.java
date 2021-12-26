package com.animania.addons.farm.common.block;

import com.animania.Animania;
import com.animania.common.blocks.AnimaniaBlock;
import com.animania.common.blocks.IMetaBlockName;
import com.animania.common.items.SubtypesBlockItem;

import PropertyEnum;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemShears;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockAnimaniaWool extends AnimaniaBlock implements IMetaBlockName
{

	public static final PropertyEnum<BlockAnimaniaWool.EnumType> VARIANT = PropertyEnum.<BlockAnimaniaWool.EnumType> create("variant", BlockAnimaniaWool.EnumType.class);

	public BlockAnimaniaWool()
	{
		super("wool", Material.CLOTH, MaterialColor.GRAY, false);
		this.setHardness(0.8F);
		this.setCreativeTab(Animania.TabAnimaniaResources);
		this.setSoundType(SoundType.CLOTH);
		ForgeRegistries.ITEMS.register(new SubtypesBlockItem(this));
		this.setHarvestLevel("shears", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.DORSET_BROWN));
	}

	@Override
	@SideOnly(Dist.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items)
	{

		for (BlockAnimaniaWool.EnumType blockstone$enumtype : BlockAnimaniaWool.EnumType.values())
		{
			items.add(new ItemStack(this, 1, blockstone$enumtype.getMetadata()));
		}

	}

	@Override
	public float getPlayerRelativeBlockHardness(BlockState state, Player player, Level levelIn, BlockPos pos)
	{
		ItemStack stack = player.getMainHandItem();
		if (!stack.isEmpty() && stack.getItem() instanceof ItemShears)
			return 0.16f;
		else
			return super.getPlayerRelativeBlockHardness(state, player, levelIn, pos);
	}

	@Override
	public void onBlockPlacedBy(Level levelIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
	{
		levelIn.setBlock(pos, this.getStateFromMeta(stack.getMetadata()));
	}

	@Override
	public int damageDropped(BlockState state)
	{
		return state.getValue(VARIANT).getMetadata();
	}

	@Override
	public BlockState getStateFromMeta(int meta)
	{
		return this.defaultBlockState().withProperty(VARIANT, BlockAnimaniaWool.EnumType.byMetadata(meta));
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(BlockState state)
	{
		return ((BlockAnimaniaWool.EnumType) state.getValue(VARIANT)).getMetadata();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

	@Override
	public MaterialColor getMaterialColor(BlockState state, IBlockAccess levelIn, BlockPos pos)
	{
		return ((BlockAnimaniaWool.EnumType) state.getValue(VARIANT)).getMaterialColor();
	}

	public static enum EnumType implements IStringSerializable
	{
		DORSET_BROWN(0, MaterialColor.BROWN, "dorset_brown"), FRIESIAN_BLACK(1, MaterialColor.BLACK, "friesian_black"), FRIESIAN_BROWN(2, MaterialColor.BROWN, "friesian_brown"), JACOB(3, MaterialColor.SNOW, "jacob"), MERINO_BROWN(4, MaterialColor.BROWN, "merino_brown"), MERINO_WHITE(5, MaterialColor.SNOW, "merino_white"), SUFFOLK_BROWN(6, MaterialColor.BROWN, "suffolk_brown");

		/** Array of the Block's BlockStates */
		private static final BlockAnimaniaWool.EnumType[] META = new BlockAnimaniaWool.EnumType[values().length];
		/** The BlockState's metadata. */
		private final int meta;
		/** The EnumType's name. */
		private final String name;
		private final String unlocalizedName;
		private final MaterialColor MaterialColor;

		private EnumType(int i, MaterialColor color, String name)
		{
			this(i, color, name, name);
		}

		private EnumType(int meta, MaterialColor color, String name, String name2)
		{
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = name2;
			this.MaterialColor = color;
		}

		/**
		 * Returns the EnumType's metadata value.
		 */
		public int getMetadata()
		{
			return this.meta;
		}

		public MaterialColor getMaterialColor()
		{
			return this.MaterialColor;
		}

		@Override
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
