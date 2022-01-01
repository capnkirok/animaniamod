package common.handler;

import com.animania.Animania;
import common.block.BlockHamsterWheel;
import common.blockentity.BlockEntityHamsterWheel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistries;

public class ExtraAddonBlockHandler
{

	public static Block blockHamsterWheel;

	/**
	 * Register Blocks <br>
	 * Register TileEntities
	 */
	public static void preInit()
	{
		blockHamsterWheel = new BlockHamsterWheel();

		Item item = new BlockItem(blockHamsterWheel);
		item.setRegistryName(new ResourceLocation(Animania.MODID, "block_hamster_wheel"));
		ForgeRegistries.ITEMS.register(item);

		GameRegistry.registerBlockEntity(BlockEntityHamsterWheel.class, "BlockEntityHamsterWheel");
	}

}
