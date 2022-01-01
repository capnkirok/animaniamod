import client.ExtraAddonRenderHandler;
import com.animania.Animania;
import com.animania.api.addons.AnimaniaAddon;
import com.animania.api.addons.LoadAddon;
import com.animania.network.NetworkHandler;
import common.events.CapabilityLoadHandler;
import common.events.CarryInteractHandler;
import common.events.CarryRenderer;
import common.events.ExtraAddonSpawnHandler;
import common.handler.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import network.CapSyncPacket;
import network.CapSyncPacketHandler;

@LoadAddon
public class ExtraAddon implements AnimaniaAddon
{

	/*
	 * Registration Order: GUI Handler, Injections, Entities, Items, Blocks
	 */
	@Override
	public void preInitCommon()
	{
		MinecraftForge.EVENT_BUS.register(CapabilityLoadHandler.class);
		MinecraftForge.EVENT_BUS.register(CarryInteractHandler.class);
		MinecraftForge.EVENT_BUS.register(CarryRenderer.class);
		MinecraftForge.EVENT_BUS.register(ExtraAddonSpawnHandler.class);

		ExtraAddonInjectionHandler.preInit();
		ExtraAddonEntityHandler.preInit();
		ExtraAddonItemHandler.preInit();
		ExtraAddonBlockHandler.preInit();
		ExtraAddonSoundHandler.preInit();
		ExtraAddonLootTableHandler.preInit();
		ExtraAddonCompatHandler.preInit();
		ExtraAddonCapHandler.preInit();
	}

	@Override
	public void initCommon()
	{
		ExtraAddonOredictHandler.init();
		ExtraAddonCraftingHandler.init();

		Animania.network.registerMessage(CapSyncPacketHandler.class, CapSyncPacket.class, NetworkHandler.packetID++, Dist.CLIENT);
	}

	@Override
	public void preInitClient()
	{
		ExtraAddonRenderHandler.preInit();
	}

	@Override
	public void initClient()
	{
		ExtraAddonRenderHandler.init();
	}

	@Override
	public String getVersion()
	{
		return "GRADLE:extra_version";
	}

	@Override
	public String getAddonID()
	{
		return "extra";
	}

	@Override
	public String getAddonName()
	{
		return "Animania - Extra";
	}

	@Override
	public String getDependencies()
	{
		return "required-after:animania@[2.0.0,);required-after:minecraft@[1.12,1.13)";
	}

}
