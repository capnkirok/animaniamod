package com.animania.manual.components;

import com.animania.Animania;
import com.animania.client.render.item.RenderAnimatedEgg;
import com.animania.manual.gui.GuiManual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
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

	private ResourceLocation entityLoc;

	private Minecraft mc;

	private float multiplier = 1;
	
	private Entity entity;

	public EntityComponent(int x, int y, ResourceLocation entity)
	{
		this.manual = GuiManual.INSTANCE;
		this.absoluteX = x + GuiManual.START_OFFSET_X;
		this.absoluteY = y + GuiManual.START_OFFSET_Y;

		this.x = x;
		this.y = y;

		this.entityLoc = entity;

		this.mc = Minecraft.getMinecraft();
		
		
	}

	@Override
	public void init()
	{
		try
		{
			World world = mc.world;
			Entity e = EntityList.createEntityByIDFromName(entityLoc, world);
			if (e != null)
			{
				float height = e.height;
				float width = e.width;
				float size = width * height;

				if (height > width)
					this.multiplier = (float) (17.5 / height);
				else if (width > height)
					this.multiplier = (float) (17.5 / width);
				else
					this.multiplier = (float) (17.5 / Math.sqrt(size));
								
				e.setPosition(-2, -2, -2);
				this.entity = e;
			}
		}
		catch (Exception e)
		{
			Animania.LOGGER.error(e);
		}
	}

	@Override
	public void draw(int mouseX, int mouseY, float partialTicks)
	{
		int border = (GuiManual.MANUAL_MAX_X - objectWidth) / 2;
		GlStateManager.pushMatrix();
		GlStateManager.color(1, 1, 1);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
		RenderHelper.enableStandardItemLighting();
		
		int size = 10;
		
		GlStateManager.scale(multiplier, multiplier, multiplier);
		GlStateManager.translate((manual.guiLeft + absoluteX + manual.MANUAL_MAX_X/2)/(multiplier), (manual.guiTop + absoluteY + 27)/(multiplier), 2);
		GlStateManager.rotate(180, 0f, 0, 1f);
		GlStateManager.rotate(360 * RenderAnimatedEgg.renderTimer, 0, 1f, 0);
		renderEntityStatic(entity);
		GlStateManager.disableLighting();
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
		GlStateManager.popMatrix();
		

	}
	
	@Override
	public void drawLater(int mouseX, int mouseY, float partialTicks)
	{
		if(manual.isHovering(this, mouseX, mouseY))
		{
			GlStateManager.pushMatrix();
			manual.drawHoveringText(I18n.translateToLocal("entity." + entityLoc + ".name"), mouseX, mouseY);
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

	@SideOnly(Side.CLIENT)
	private void renderEntityStatic(Entity entity)
	{
		Minecraft.getMinecraft().getRenderManager().renderEntity(entity, 0.0D, 0.0D, 0.0D, 0, 0.0F, true);

	}

	@SideOnly(Side.CLIENT)
	private int getBrightnessForRender(Entity entity, EntityPlayer player)
	{
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(MathHelper.floor(player.posX), 0, MathHelper.floor(player.posZ));

		if (entity.world.isBlockLoaded(blockpos$mutableblockpos))
		{
			blockpos$mutableblockpos.setY(MathHelper.floor(player.posY + entity.getEyeHeight()));
			return entity.world.getCombinedLight(blockpos$mutableblockpos, 0);
		}
		else
		{
			return 0;
		}
	}

	@SideOnly(Side.CLIENT)
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
