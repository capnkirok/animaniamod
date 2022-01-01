package common.handler;

import com.animania.Animania;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class ExtraAddonLootTableHandler
{
	public static void preInit()
	{
		reg("hedgehog");
		reg("hamster");
		reg("ferret");
		reg("rabbit_prime");
		reg("rabbit_regular");
		reg("peacocks/peacock_blue");
		reg("peacocks/peacock_charcoal");
		reg("peacocks/peacock_opal");
		reg("peacocks/peacock_peach");
		reg("peacocks/peacock_purple");
		reg("peacocks/peacock_taupe");
		reg("peacocks/peacock_white");
		reg("frog");
		reg("toad");
		reg("dart_frog");

	}

	private static void reg(String name)
	{
		LootTableList.register(new ResourceLocation("extra/" + Animania.MODID, name));
	}
}
