package com.animania.catsdogs.common.blockentity;

import net.minecraft.world.level.block.entity.BlockEntity;

public class BlockEntityProp extends BlockEntity
{

	public PropType type;

	public BlockEntityProp()
	{

	}

	@Override
	public Block getBlockType()
	{
		Block b = super.getBlockType();

		String name = b.getRegistryName().getPath();

		for (PropType t : PropType.values())
		{
			if (t.block.equals(name))
				this.type = t;
		}

		return b;
	}

	public static enum PropType
	{
		CAT_BED_1("cat_bed_1"), CAT_BED_2("cat_bed_2"), CAT_TOWER("cat_tower"), DOG_HOUSE("dog_house"), DOG_PILLOW("dog_pillow"), LITTER_BOX("litter_box");

		public String block;

		private PropType(String type)
		{
			this.block = type;
		}
	}

}
