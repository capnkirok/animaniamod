package com.animania.manual.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import com.animania.Animania;
import com.animania.manual.components.IManualComponent;
import com.animania.manual.groups.ManualPage;
import com.animania.manual.groups.ManualTopic;
import com.animania.manual.resources.ManualResourceLoader;
import com.animania.network.common.PacketCloseManual;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiManual extends GuiScreen
{

	public static final ResourceLocation TEXTURE = new ResourceLocation(Animania.MODID, "textures/gui/book.png");

	public static final int START_OFFSET_X = 36;
	public static final int START_OFFSET_Y = 16;

	public static final int LINE_Y_OFFSET = 1;
	public static final int LINE_X_OFFSET = Minecraft.getMinecraft().fontRenderer.getStringWidth(" ");

	public static final int MANUAL_MAX_X = 114;
	public static final int MANUAL_MAX_Y = 154;

	public int guiLeft;
	public int guiTop;

	public int xSize = 186;
	public int ySize = 201;

	public int x;
	public int y;

	public ManualTopic currentTopic = ManualResourceLoader.firstTopic;
	public ManualTopic lastTopic = ManualResourceLoader.firstTopic;

	private ManualPage currentPage;
	
	public boolean isPrevTopic = false;
	
	public Map<ResourceLocation, ManualTopic> manualContent = new HashMap<ResourceLocation, ManualTopic>();

	public static GuiManual INSTANCE = new GuiManual();

	public int pageIndex = 0;

	private GuiButton buttonNextPage;
	private GuiButton buttonPreviousPage;

	private GuiButton buttonPrevTopic;
	private GuiButton buttonThisTopic;
	
	
	private GuiManual()
	{
	}

	public static GuiManual getInstance(ItemStack book)
	{
		if (book.hasTagCompound())
		{
			NBTTagCompound tag = book.getTagCompound();
			if (tag.hasKey("currentTopic") && tag.hasKey("lastTopic"))
			{
				INSTANCE.currentTopic = INSTANCE.manualContent.get(new ResourceLocation(tag.getString("currentTopic")));
				INSTANCE.lastTopic = INSTANCE.manualContent.get(new ResourceLocation(tag.getString("lastTopic")));
				
				if(INSTANCE.currentTopic == null)
					INSTANCE.currentTopic = ManualResourceLoader.firstTopic;

				if(INSTANCE.lastTopic == null)
					INSTANCE.lastTopic = ManualResourceLoader.firstTopic;

			}
			else
			{
				INSTANCE.currentTopic = ManualResourceLoader.firstTopic;
				INSTANCE.lastTopic = ManualResourceLoader.firstTopic;
			}
		}
		else
		{
			INSTANCE.currentTopic = ManualResourceLoader.firstTopic;
			INSTANCE.lastTopic = ManualResourceLoader.firstTopic;
		}

		return INSTANCE;
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	@Override
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
		Animania.network.sendToServer(new PacketCloseManual(currentTopic.getId().toString(), lastTopic.getId().toString()));
	}

	@Override
	public void initGui()
	{
		ScaledResolution res = new ScaledResolution(this.mc);
		this.width = res.getScaledWidth();
		this.height = res.getScaledHeight();

		guiLeft = (this.width - this.xSize) / 2;
		guiTop = (this.height - this.ySize) / 2;

		x = guiLeft + START_OFFSET_X;
		y = guiTop + START_OFFSET_Y;

		int i = (this.width - 192) / 2;
		this.buttonNextPage = this.addButton(new NextPageButton(0, this.guiLeft + 155, this.guiTop + 164, true));
		this.buttonPreviousPage = this.addButton(new NextPageButton(1, this.guiLeft + 7, this.guiTop + 164, false));
		this.buttonPrevTopic = this.addButton(new PrevPageButton(2, this.guiLeft + 9, this.guiTop + 178, false));
		this.buttonThisTopic = this.addButton(new PrevPageButton(3, this.guiLeft + 159, this.guiTop + 178, true));
		updateButtons();

		initComponents();
	}

	public void initComponents()
	{
		ManualPage p = pageIndex >= currentTopic.getPages().size() ? currentTopic.getPages().get(0) : currentTopic.getPages().get(pageIndex);

		this.currentPage = p;
		
		for (IManualComponent c : p.getComponents())
			c.init();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int marginHorizontal = (width - xSize) / 2;
		int marginVertical = (height - ySize) / 2;
		drawTexturedModalRect(marginHorizontal, marginVertical, 0, 0, xSize, ySize);

		ManualPage p = this.currentPage;

		for (IManualComponent c : p.getComponents())
			c.draw(mouseX, mouseY, partialTicks);

		super.drawScreen(mouseX, mouseY, partialTicks);
		
		for (IManualComponent c : p.getComponents())
			c.drawLater(mouseX, mouseY, partialTicks);
	}

	public void updateButtons()
	{
		this.buttonNextPage.visible = ((pageIndex == currentTopic.getPages().size() - 1) ? false : true);
		this.buttonPreviousPage.visible = (pageIndex == 0 ? false : true);
		this.buttonPrevTopic.visible = !isPrevTopic;
		this.buttonThisTopic.visible = isPrevTopic;
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if (button.enabled)
		{
			switch (button.id)
			{
			case 0:
				pageIndex++;
				initComponents();
				break;
			case 1:
				pageIndex--;
				initComponents();
				break;
			case 2:
				ManualTopic temp = this.currentTopic;
				this.currentTopic = this.lastTopic;
				this.lastTopic = temp;
				this.isPrevTopic = true;
				pageIndex = 0;
				initComponents();
				break;
			case 3:
				ManualTopic temp2 = this.currentTopic;
				this.currentTopic = this.lastTopic;
				this.lastTopic = temp2;
				this.isPrevTopic = false;
				pageIndex = 0;
				initComponents();
				break;
			}
		}

		updateButtons();
	}

	@SideOnly(Side.CLIENT)
	static class NextPageButton extends GuiButton
	{
		private final boolean isForward;

		public NextPageButton(int buttonId, int x, int y, boolean isForwardIn)
		{
			super(buttonId, x, y, 23, 13, "");
			this.isForward = isForwardIn;
		}

		/**
		 * Draws this button to the screen.
		 */
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
		{
			if (this.visible)
			{
				boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				int i = 0;
				int j = 227;
				
				if (flag)
				{
					i += 23;
				}

				if (!this.isForward)
				{
					j += 13;
				}

				mc.getTextureManager().bindTexture(TEXTURE);
				this.drawTexturedModalRect(this.x, this.y, i, j, 23, 13);
				
				if(flag)
				{
					GlStateManager.pushMatrix();
					GuiManual.INSTANCE.drawHoveringText(I18n.translateToLocal(this.isForward ? "manual.page.next" : "manual.page.previous"), mouseX, mouseY);
					GlStateManager.disableLighting();
					GlStateManager.popMatrix();
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	static class PrevPageButton extends GuiButton
	{
		private final boolean isForward;

		public PrevPageButton(int buttonId, int x, int y, boolean isForward)
		{
			super(buttonId, x, y, 18, 11, "");
			this.isForward = isForward;
		}

		/**
		 * Draws this button to the screen.
		 */
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
		{
			if (this.visible)
			{
				boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				int i = 48;
				int j = 229;


				if (flag)
				{
					i += 22;
				}

				if (!this.isForward)
				{
					j += 13;
				}

				mc.getTextureManager().bindTexture(TEXTURE);
				this.drawTexturedModalRect(this.x, this.y, i, j, 18, 11);
				
				if(flag)
				{
					GlStateManager.pushMatrix();
					GuiManual.INSTANCE.drawHoveringText(I18n.translateToLocal("manual.topic.previous"), mouseX, mouseY);
					GlStateManager.disableLighting();
					GlStateManager.popMatrix();
				}
				
			}
		}
	}
	

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		ManualPage p = this.currentPage;

		for (IManualComponent c : p.getComponents())
		{
			if (isHovering(c, mouseX, mouseY))
			{
				if (mouseButton == 0)
					c.onLeftClick(mouseX, mouseY);
				else if (mouseButton == 1)
					c.onRightClick(mouseX, mouseY);
			}
		}

		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void updateScreen()
	{
		ManualPage p = this.currentPage;

		for (IManualComponent c : p.getComponents())
			c.update();
	}

	public boolean isHovering(IManualComponent c, int mouseX, int mouseY)
	{
		return mouseX > c.getX() + START_OFFSET_X + guiLeft && mouseX < c.getX() + START_OFFSET_X + guiLeft + c.getObjectWidth() && mouseY > c.getY() + START_OFFSET_Y + guiTop && mouseY < c.getY() + START_OFFSET_Y + guiTop + c.getObjectHeight();
	}

	public void drawItemStack(ItemStack stack, int x, int y, String altText)
	{
		GlStateManager.translate(0.0F, 0.0F, 32.0F);
		this.zLevel = 200.0F;
		this.itemRender.zLevel = 200.0F;
		net.minecraft.client.gui.FontRenderer font = stack.getItem().getFontRenderer(stack);
		if (font == null)
			font = fontRenderer;
		this.itemRender.renderItemAndEffectIntoGUI(stack, x, y);
		this.itemRender.renderItemOverlayIntoGUI(font, stack, x, y, altText);
		this.zLevel = 0.0F;
		this.itemRender.zLevel = 0.0F;
		GlStateManager.translate(0.0F, 0.0F, -32.0F);
	}

	public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight, float scale)
	{
		float f = 1.0F / textureWidth;
		float f1 = 1.0F / textureHeight;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos((double) x, (double) (y + height), 0.0D).tex((double) (u * f), (double) ((v + (float) height) * f1)).endVertex();
		bufferbuilder.pos((double) (x + width), (double) (y + height), 0.0D).tex((double) ((u + (float) width) * f), (double) ((v + (float) height) * f1)).endVertex();
		bufferbuilder.pos((double) (x + width), (double) y, 0.0D).tex((double) ((u + (float) width) * f), (double) (v * f1)).endVertex();
		bufferbuilder.pos((double) x, (double) y, 0.0D).tex((double) (u * f), (double) (v * f1)).endVertex();

		GlStateManager.scale(scale, scale, scale);
		tessellator.draw();
	}

	public void renderToolTip(ItemStack stack, int x, int y)
	{
		FontRenderer font = stack.getItem().getFontRenderer(stack);
		net.minecraftforge.fml.client.config.GuiUtils.preItemToolTip(stack);
		this.drawHoveringText(this.getItemToolTip(stack), x, y, (font == null ? fontRenderer : font));
		net.minecraftforge.fml.client.config.GuiUtils.postItemToolTip();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if (keyCode == 1 || this.mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode))
		{
			this.mc.player.closeScreen();
		}

		super.keyTyped(typedChar, keyCode);
	}

}
