package com.animania.common.recipes;

import java.util.ArrayList;
import java.util.Iterator;

import com.animania.common.handler.BlockHandler;
import com.animania.common.handler.ItemHandler;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class CheeseRecipe4 implements IRecipe
{

    private final ItemStack recipeOutput;
    public final ArrayList  recipeItems = new ArrayList();
    private int             knifeSlotJ;
    private int             knifeSlotI;
    private ItemStack       knifeStack;

    public CheeseRecipe4() {
        this.recipeOutput = new ItemStack(ItemHandler.cheeseWedgeFriesian, 4);
        this.recipeItems.add(new ItemStack(ItemHandler.carvingKnife));
        this.recipeItems.add(new ItemStack(Item.getItemFromBlock(BlockHandler.blockCheeseFriesian)));

    }

    @Override
    public boolean matches(InventoryCrafting inv, World world) {

        ArrayList arraylist = new ArrayList(this.recipeItems);

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 3; ++j) {
                ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

                if (itemstack != ItemStack.EMPTY) {
                    boolean flag = false;
                    Iterator iterator = arraylist.iterator();

                    while (iterator.hasNext()) {
                        ItemStack itemstack1 = (ItemStack) iterator.next();

                        if (itemstack.getItem() == itemstack1.getItem()) {
                            flag = true;

                            if (itemstack.getItem() == ItemHandler.carvingKnife) {
                                this.knifeSlotJ = j;
                                this.knifeSlotI = i;
                                this.knifeStack = itemstack.copy();
                            }

                            arraylist.remove(itemstack1);
                            break;
                        }
                    }

                    if (!flag)
                        return false;
                }
            }

        return arraylist.isEmpty();
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {

        NonNullList<ItemStack> bob = NonNullList.<ItemStack> withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        this.knifeStack.setItemDamage(this.knifeStack.getItemDamage() + 1);

        if (this.knifeStack.getItemDamage() >= this.knifeStack.getMaxDamage())
            this.knifeStack = ItemStack.EMPTY;

        bob.set(this.knifeSlotJ + this.knifeSlotI * (int) Math.sqrt(inv.getSizeInventory()), this.knifeStack);

        return bob;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return this.recipeOutput.copy();
    }

    @Override
    public int getRecipeSize() {
        return this.recipeItems.size();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
}
