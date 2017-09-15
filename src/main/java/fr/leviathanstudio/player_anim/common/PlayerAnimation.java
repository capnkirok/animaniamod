package fr.leviathanstudio.player_anim.common;

import com.leviathanstudio.craftstudio.client.json.CSReadedAnim;
import com.leviathanstudio.craftstudio.client.json.CSReadedModel;

import fr.leviathanstudio.player_anim.client.render.player.PlayerRenderEvent;
import fr.leviathanstudio.player_anim.proxy.PACommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = PlayerAnimation.MODID)
@Mod(modid = PlayerAnimation.MODID, name = PlayerAnimation.NAME, version = PlayerAnimation.VERSION)
public class PlayerAnimation
{
    public static final String     MODID = "player_anim", NAME = "Player Animation", VERSION = "0.0.1";

    @SidedProxy(clientSide = "fr.leviathanstudio.player_anim.proxy.PAClientProxy", serverSide = "fr.leviathanstudio.player_anim.proxy.PACommonProxy")
    private static PACommonProxy   proxy;

    @Instance(MODID)
    private static PlayerAnimation instance;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(RegistryEvent.Register<CSReadedModel> e)
    {
        getProxy().registerModels();
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerAnims(RegistryEvent.Register<CSReadedAnim> e)
    {
        getProxy().registerAnims();
    }

    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        MinecraftForge.EVENT_BUS.register(new PlayerRenderEvent());
    }

    public static PACommonProxy getProxy()
    {
        return proxy;
    }

    public static PlayerAnimation getInstance()
    {
        return instance;
    }

}