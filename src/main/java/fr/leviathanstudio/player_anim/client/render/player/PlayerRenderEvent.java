package fr.leviathanstudio.player_anim.client.render.player;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlayerRenderEvent
{

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void preRenderPlayer(RenderPlayerEvent.Pre event)
    {
        RenderAnimatedPlayer playerRenderer = new RenderAnimatedPlayer(event.getRenderer().getRenderManager());
        float scale = 0.0625F;

        if (event.getEntityPlayer().isRiding())
        {
            event.setCanceled(true);
            playerRenderer.doRenderRiding((AbstractClientPlayer) event.getEntityPlayer(), event.getX(), event.getY(), event.getZ(),
                    event.getEntityPlayer().rotationYaw, scale);
        }
        else
        {
            event.setCanceled(false);
        }
    }
}
