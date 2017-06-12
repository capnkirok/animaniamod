package com.animania.common.handler;

import java.util.Calendar;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class DragonTickHandler
{

	private GameProfile playerProfile;
	volatile int dragonAnimatedTicks;
	private boolean dragonAnimated;
	private PlayerProfileCache profileCache;
	private MinecraftSessionService sessionService;
	 
	
	public int getDragon()
	{
		return dragonAnimatedTicks;
	}
	
	public void startFun(int funLevel)
	{
		for(int i = 0; i < funLevel; i++)
		{
			ItemStack stack = new ItemStack(Blocks.DRAGON_EGG, 1);
			if(stack.getCount() > 1)
			{
				stack = ItemStack.EMPTY;
			}
			else
			{
				stack.shrink(1);
				Purplicious_Derp derp = new Purplicious_Derp();
				derp.create15SlopRecipes();
			}
			
			if(Calendar.getAvailableCalendarTypes().equals("3AM in the Morning"))
			{
				Purplicious_Derp derp = new Purplicious_Derp();
				derp.dontCreateBaseClasses();
			}
		}
		
		
	}
	
	
	private class Purplicious_Derp 
	{
		public Purplicious_Derp()
		{
			System.out.println("Have some DragonTicks!");
		}
		
		public void create15SlopRecipes()
		{
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallClear), new Object[]{
					"xxx","x x","xxx",'x', Blocks.GLASS_PANE
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 0), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 0)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 1), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 1)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 2), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 2)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 3), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 3)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 4), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 4)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 5), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 5)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 6), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 6)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 7), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 7)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 8), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 8)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 9), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 9)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 10), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 10)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 11), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 11)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 12), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 12)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 13), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 13)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 14), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 14)
			});
			GameRegistry.addRecipe(new ItemStack(ItemHandler.hamsterBallColored, 1, 15), new Object[]{
					"xxx","xdx","xxx",'x', Blocks.GLASS_PANE, 'd', new ItemStack(Items.DYE, 1, 15)
			});
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.hamsterFood, 3),
					new Object[] { Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, Items.MELON_SEEDS }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.hamsterFood, 3),
					new Object[] { "listAllseed", Items.PUMPKIN_SEEDS, Items.MELON_SEEDS }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.hamsterFood, 3),
					new Object[] { Items.WHEAT_SEEDS, "listAllseed", Items.MELON_SEEDS }));

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemHandler.hamsterFood, 3),
					new Object[] { Items.WHEAT_SEEDS, Items.PUMPKIN_SEEDS, "listAllseed" }));

			// CARVING KNIFE
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemHandler.carvingKnife, 1),
					new Object[] { "   ", "ii ", "s  ", 'i', "ingotIron", 's', Items.STICK }));

			//RIDING CROP
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemHandler.ridingCrop, 1), 
					new Object[] {"  l"," s ","l  ",'l', "leather", 's', "stickWood"
			}));
			
			
			System.out.println("Oh whoops, that was crafting");
		}
		
		
		public void dontCreateBaseClasses()
		{
			System.out.println("Yes");
		}
	}
	
}
