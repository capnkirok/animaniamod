package common.handler;

import com.animania.addons.farm.common.tileentity.BlockEntityCheeseMold;
import com.animania.addons.farm.common.tileentity.BlockEntityHive;
import com.animania.common.blocks.fluids.BlockFluidBase;
import com.animania.common.blocks.fluids.FluidBase;
import common.block.*;
import common.block.fluids.BlockFluidHoney;
import common.block.fluids.BlockFluidMilk;
import net.minecraft.world.item.Item;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.animania.addons.farm.common.handler.FarmAddonItemHandler.*;

public class FarmAddonBlockHandler
{

	public static BlockFluidBase blockMilkHolstein;
	public static BlockFluidBase blockMilkFriesian;
	public static BlockFluidBase blockMilkJersey;
	public static BlockFluidBase blockMilkGoat;
	public static BlockFluidBase blockMilkSheep;
	public static BlockFluidBase blockHoney;

	public static Block blockCheeseHolstein;
	public static Block blockCheeseFriesian;
	public static Block blockCheeseJersey;
	public static Block blockCheeseGoat;
	public static Block blockCheeseSheep;
	public static Block blockAnimaniaWool;
	public static Block blockHive;
	public static Block blockWildHive;
	public static Block blockCheeseMold;

	// Fluids
	public static Fluid fluidMilkHolstein;
	public static Fluid fluidMilkFriesian;
	public static Fluid fluidMilkJersey;
	public static Fluid fluidMilkGoat;
	public static Fluid fluidMilkSheep;
	public static Fluid fluidHoney;

	/**
	 * Register Blocks <br>
	 * Register TileEntities
	 */
	public static void preInit()
	{
		blockCheeseFriesian = new BlockCheese("cheese_friesian");
		blockCheeseHolstein = new BlockCheese("cheese_holstein");
		blockCheeseJersey = new BlockCheese("cheese_jersey");
		blockCheeseGoat = new BlockCheese("cheese_goat");
		blockCheeseSheep = new BlockCheese("cheese_sheep");

		cheeseWheelFriesian = Item.byBlock(blockCheeseFriesian);
		cheeseWheelHolstein = Item.byBlock(blockCheeseHolstein);
		cheeseWheelJersey = Item.byBlock(blockCheeseJersey);
		cheeseWheelGoat = Item.byBlock(blockCheeseGoat);
		cheeseWheelSheep = Item.byBlock(blockCheeseSheep);

		blockCheeseMold = new BlockCheeseMold();
		cheeseMold = Item.byBlock(blockCheeseMold);
		blockAnimaniaWool = new BlockAnimaniaWool();
		blockHive = new BlockHive();
		blockWildHive = new BlockWildHive();

		fluidMilkHolstein = new FluidBase("milk_holstein").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(fluidMilkHolstein);
		blockMilkHolstein = new BlockFluidMilk(fluidMilkHolstein, "milk_holstein");

		fluidMilkFriesian = new FluidBase("milk_friesian").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(fluidMilkFriesian);
		blockMilkFriesian = new BlockFluidMilk(fluidMilkFriesian, "milk_friesian");

		fluidMilkJersey = new FluidBase("milk_jersey").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(fluidMilkJersey);
		blockMilkJersey = new BlockFluidMilk(fluidMilkJersey, "milk_jersey");

		fluidMilkGoat = new FluidBase("milk_goat").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(fluidMilkGoat);
		blockMilkGoat = new BlockFluidMilk(fluidMilkGoat, "milk_goat");

		fluidMilkSheep = new FluidBase("milk_sheep").setViscosity(1000).setDensity(500);
		FluidRegistry.addBucketForFluid(fluidMilkSheep);
		blockMilkSheep = new BlockFluidMilk(fluidMilkSheep, "milk_sheep");

		fluidHoney = new FluidBase("animania_honey").setViscosity(7000).setDensity(3000);
		FluidRegistry.addBucketForFluid(fluidHoney);
		blockHoney = new BlockFluidHoney();

		// Te's
		GameRegistry.registerBlockEntity(BlockEntityCheeseMold.class, "BlockEntityCheeseMold");
		GameRegistry.registerBlockEntity(BlockEntityHive.class, "BlockEntityHive");

	}

}
