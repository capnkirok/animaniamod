package com.animania.common.helper;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AnimaniaHelper
{

	public static ItemStack getItem(String name)
	{
		ItemStack stack = ItemStack.EMPTY;
		int metaLoc = 0;
		boolean metaFlag = false;
		String metaVal = "";

		metaLoc = name.indexOf("#");

		if (metaLoc > -1) {
			metaVal = name.substring(metaLoc);
			name = name.replace(metaVal, "");
			metaVal = metaVal.replace("#", "");
			metaFlag = true;
		} 

		Item item = Item.getByNameOrId(name);

		if (item != null) {

			if (metaFlag) {
				stack = new ItemStack(item, 1, Integer.parseInt(metaVal));
			} else {
				stack = new ItemStack(item, 1);
			}
		} 

		return stack;

	}


}
