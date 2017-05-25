package com.animania.common.items;

import com.animania.Animania;

import net.minecraft.item.Item;

public class ItemHamsterBall extends Item
{

    public ItemHamsterBall() {
        super();
        this.setHasSubtypes(true);
        this.setCreativeTab(Animania.TabAnimaniaResources);
    }

    // TODO
    /*
     * @Override public int getColorFromItemStack(ItemStack par1ItemStack, int
     * par2) { float[] color =
     * EntitySheep.fleeceColorTable[par1ItemStack.getItemDamage()]; return new
     * Color(color[0], color[1], color[2]).getRGB(); }
     *
     * @Override public boolean requiresMultipleRenderPasses() { return true; }
     */

    /*
     * @Override public void getSubItems(int par1, CreativeTabs
     * par2CreativeTabs, List par3List) { for (int var4 = 0; var4 < 16; ++var4)
     * { par3List.add(new ItemStack(par1, 1, var4)); } }
     */

}
