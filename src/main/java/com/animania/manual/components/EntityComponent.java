package com.animania.manual.components;

import com.animania.Animania;
import com.animania.client.render.item.RenderAnimatedEgg;
import com.animania.manual.gui.GuiManual;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityComponent implements IManualComponent
{
	private GuiManual manual;
	private int x;
	private int y;

	private int absoluteX;
	private int absoluteY;

	private int objectWidth = GuiManual.MANUAL_MAX_X - 4;
	private int objectHeight = 35;

	private ResourceLocation[] entityLoc;

	private Minecraft mc;

	private float[] multiplier;

	private Entity[] entities;

	public static int renderTimer = 0;
	private int index = 0;

	private Entity currentEntity;

	public EntityComponent(int x, int y, ResourceLocation... entities)
	{
		this.manual = GuiManual.INSTANCE;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;

		this.x = x;
		this.y = y;

		this.entityLoc = entities;

		this.entities = new Entity[entities.length];
		this.multiplier = new float[entities.length];

		this.mc = Minecraft.getInstance();
	}

	@Override
	public void init()
	{
		for (int i = 0; i < entityLoc.length; i++)
		{
			try
			{
				Level world = mc.level;
				Entity e = EntityList.createEntityByIDFromName(entityLoc[i], world);
				if (e != null)
				{
					float height = e.height;
					float width = e.width;
					float size = width * height;

					if (height > width)
						this.multiplier[i] = (float) (17.5 / height);
					else if (width > height)
						this.multiplier[i] = (float) (17.5 / width);
					else
						this.multiplier[i] = (float) (17.5 / Math.sqrt(size));

					e.setPosition(-2, -2, -2);
					this.entities[i] = e;
				}
			}
			catch (Exception e)
			{
				Animania.LOGGER.error(e);
			}
		}

		this.currentEntity = entities[0];
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks)
	{
		if (currentEntity != null)
		{
			int border = (GuiManual.MANUAL_MAX_X - objectWidth) / 2;
			GlStateManager.pushMatrix();
			GlStateManager.color(1, 1, 1);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
			RenderHelper.enableStandardItemLighting();

			int size = 10;

			GlStateManager.scale(multiplier[index], multiplier[index], multiplier[index]);
			GlStateManager.translate((manual.guiLeft + absoluteX + manual.MANUAL_MAX_X / 2) / (multiplier[index]), (manual.guiTop + absoluteY + 27) / (multiplier[index]), 2);
			GlStateManager.rotate(180, 0f, 0, 1f);
			GlStateManager.rotate(360 * RenderAnimatedEgg.renderTimer, 0, 1f, 0);
			renderEntityStatic(currentEntity);
			GlStateManager.disableLighting();
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
			GlStateManager.popMatrix();
		}

	}

	private void updateRenderEntity()
	{
		if (!GuiScreen.isShiftKeyDown())
		{
			if (renderTimer >= 79)
			{
				index++;
				if (index == entityLoc.length)
					index = 0;
			}

			currentEntity = entities[index];
		}
	}

	@Override
	public void update()
	{
		updateRenderEntity();
	}

	@Override
	public void drawLater(int mouseX, int mouseY, float partialTicks)
	{
		if (manual.isHovering(this, mouseX, mouseY))
		{
			GlStateManager.pushMatrix();
			manual.drawHoveringText(I18n.translateToLocal("entity." + entityLoc[index] + ".name"), mouseX, mouseY);
			GlStateManager.disableLighting();
			GlStateManager.popMatrix();
		}
	}

	@Override
	public void onLeftClick()
	{
		this.init();
	}

	@Override
	public void onRightClick()
	{

	}

	@Override
	public int getObjectWidth()
	{
		return objectWidth;
	}

	@Override
	public int getObjectHeight()
	{
		return objectHeight;
	}

	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}

	@Override
	public IManualComponent setX(int x)
	{
		this.x = x;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		return this;
	}

	@Override
	public IManualComponent setY(int y)
	{
		this.y = y;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;
		return this;
	}

	@SideOnly(Dist.CLIENT)
	private void renderEntityStatic(Entity entity)
	{
		Minecraft.getMinecraft().getRenderManager().renderEntity(entity, 0.0D, 0.0D, 0.0D, 0, 0.0F, true);

	}

	@SideOnly(Dist.CLIENT)
	private int getBrightnessForRender(Entity entity, PlayerEntity player)
	{
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(player.getX()), 0, MathHelper.floor(player.getZ()));

		if (entity.level.isBlockLoaded(blockpos$mutableblockpos))
		{
			blockpos$mutableblockpos.setY(MathHelper.floor(player.getY() + entity.getEyeHeight()));
			return entity.level.getCombinedLight(blockpos$mutableblockpos, 0);
		}
		else
		{
			return 0;
		}
	}

	@SideOnly(Dist.CLIENT)
	private void setLightmapDisabled(boolean disabled)
	{
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);

		if (disabled)
		{
			GlStateManager.disableTexture2D();
		}
		else
		{
			GlStateManager.enableTexture2D();
		}

		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
}
