package fr.leviathanstudio.player_anim.client.render.player;

import com.leviathanstudio.craftstudio.client.model.ModelCraftStudio;

import fr.leviathanstudio.player_anim.common.PlayerAnimation;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAnimatedPlayer<T extends AbstractClientPlayer> extends Render<T>
{
    private ModelCraftStudio playerMainModel = new ModelCraftStudio(PlayerAnimation.MODID, "player", 64);
    private ModelCraftStudio playerSitModel  = new ModelCraftStudio(PlayerAnimation.MODID, "player_sit", 64);

    public RenderAnimatedPlayer(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn);
    }

    public void doRenderRiding(T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y + 1.5F, (float) z);
        GlStateManager.rotate(180F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);
        this.bindTexture(entity.getLocationSkin());
        playerSitModel.render();
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity)
    {
        return entity.getLocationSkin();
    }

    public boolean shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ)
    {
        return super.shouldRender(livingEntity, camera, camX, camY, camZ);
    }
}