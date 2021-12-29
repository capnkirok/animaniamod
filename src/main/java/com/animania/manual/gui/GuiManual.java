package com.animania.manual.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.animania.Animania;
import com.animania.manual.components.IManualComponent;
import com.animania.manual.groups.ManualPage;
import com.animania.manual.groups.ManualTopic;
import com.animania.manual.resources.ManualResourceLoader;
import com.animania.network.common.PacketCloseManual;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;

import com.mojang.blaze3d.vertex.PoseStack;
import net.java.games.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Button;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiManual extends Screen
{

	public static final ResourceLocation TEXTURE = new ResourceLocation(Animania.MODID, "textures/gui/book.png");

	public static final int START_OFFSET_X = 36;
	public static final int START_OFFSET_Y = 16;

	public static final int LINE_Y_OFFSET = 1;
	public static final int LINE_X_OFFSET = Minecraft.getInstance().font.width(" ");

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

	public Map<ResourceLocation, ManualTopic> manualContent = new HashMap<>();

	public static GuiManual INSTANCE = new GuiManual();

	public int pageIndex = 0;

	private Button buttonNextPage;
	private Button buttonPreviousPage;

	private Button buttonPrevTopic;
	private Button buttonThisTopic;

	private GuiManual()
	{
	}

	public static GuiManual getInstance(ItemStack book)
	{
		if (book.hasTag())
		{
			CompoundTag tag = book.getOrCreateTag();
			if (tag.contains("currentTopic") && tag.contains("lastTopic"))
			{
				INSTANCE.currentTopic = INSTANCE.manualContent.get(new ResourceLocation(tag.getString("currentTopic")));
				INSTANCE.lastTopic = INSTANCE.manualContent.get(new ResourceLocation(tag.getString("lastTopic")));

				if (INSTANCE.currentTopic == null)
					INSTANCE.currentTopic = ManualResourceLoader.firstTopic;

				if (INSTANCE.lastTopic == null)
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
	public boolean isPauseScreen() {
		return false;
	}

	@Override
	public void onClose() {
		// Keyboard.enableRepeatEvents(false);
		Animania.network.sendToServer(new PacketCloseManual(this.currentTopic.getId().toString(), this.lastTopic.getId().toString()));
	}

	@Override
	public void initGui()
	{
		ScaledResolution res = new ScaledResolution(this.mc);
		this.width = res.getScaledWidth();
		this.height = res.getScaledHeight();

		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;

		this.x = this.guiLeft + START_OFFSET_X;
		this.y = this.guiTop + START_OFFSET_Y;

		int i = (this.width - 192) / 2;
		this.buttonNextPage = this.addWidget(new NextPageButton(0, this.guiLeft + 155, this.guiTop + 164, true));
		this.buttonPreviousPage = this.addButton(new NextPageButton(1, this.guiLeft + 7, this.guiTop + 164, false));
		this.buttonPrevTopic = this.addButton(new PrevPageButton(2, this.guiLeft + 9, this.guiTop + 178, false));
		this.buttonThisTopic = this.addButton(new PrevPageButton(3, this.guiLeft + 159, this.guiTop + 178, true));
		this.updateButtons();

		this.initComponents();
	}

	public void initComponents()
	{
		ManualPage p = this.pageIndex >= this.currentTopic.getPages().size() ? this.currentTopic.getPages().get(0) : this.currentTopic.getPages().get(this.pageIndex);

		this.currentPage = p;

		for (IManualComponent c : p.getComponents())
			c.init();
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
		super.render(poseStack, mouseX, mouseY, partialTicks);
		RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int marginHorizontal = (width - this.xSize) / 2;
		int marginVertical = (height - this.ySize) / 2;
		drawModalRectWithCustomSizedTexture(marginHorizontal, marginVertical, 0, 0, this.xSize, this.ySize);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		int marginHorizontal = (width - this.xSize) / 2;
		int marginVertical = (height - this.ySize) / 2;
		drawTexturedModalRect(marginHorizontal, marginVertical, 0, 0, this.xSize, this.ySize);

		ManualPage p = this.currentPage;

		for (IManualComponent c : p.getComponents())
			c.draw(mouseX, mouseY, partialTicks);

		super.drawScreen(mouseX, mouseY, partialTicks);

		for (IManualComponent c : p.getComponents())
			c.drawLater(mouseX, mouseY, partialTicks);
	}

	public void updateButtons()
	{
		this.buttonNextPage.visible = this.pageIndex == this.currentTopic.getPages().size() - 1 ? false : true;
		this.buttonPreviousPage.visible = this.pageIndex == 0 ? false : true;
		this.buttonPrevTopic.visible = !this.isPrevTopic;
		this.buttonThisTopic.visible = this.isPrevTopic;
	}

	@Override
	protected void actionPerformed(Button button) throws IOException
	{
		if (button.enabled)
		{
			switch (button.id)
			{
			case 0:
				this.pageIndex++;
				this.initComponents();
				break;
			case 1:
				this.pageIndex--;
				this.initComponents();
				break;
			case 2:
				ManualTopic temp = this.currentTopic;
				this.currentTopic = this.lastTopic;
				this.lastTopic = temp;
				this.isPrevTopic = true;
				this.pageIndex = 0;
				this.initComponents();
				break;
			case 3:
				ManualTopic temp2 = this.currentTopic;
				this.currentTopic = this.lastTopic;
				this.lastTopic = temp2;
				this.isPrevTopic = false;
				this.pageIndex = 0;
				this.initComponents();
				break;
			}
		}

		this.updateButtons();
	}

	@SideOnly(Dist.CLIENT)
	static class NextPageButton extends Button
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

				if (flag)
				{
					GlStateManager.pushMatrix();
					GuiManual.INSTANCE.drawHoveringText(I18n.translateToLocal(this.isForward ? "manual.page.next" : "manual.page.previous"), mouseX, mouseY);
					GlStateManager.disableLighting();
					GlStateManager.popMatrix();
				}
			}
		}
	}

	@SideOnly(Dist.CLIENT)
	static class PrevPageButton extends Button
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
		@Override
		public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
			if (this.visible)
			{
				boolean flag = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
				RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
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

				RenderSystem.setShaderTexture(0, TEXTURE);
				this.drawTexturedModalRect(this.x, this.y, i, j, 18, 11);

				if (flag)
				{
					poseStack.pushPose();
					GuiManual.INSTANCE.drawHoveringText(new TranslatableComponent("manual.topic.previous"), mouseX, mouseY);
					GlStateManager.disableLighting();
					poseStack.popPose();
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
			if (this.isHovering(c, mouseX, mouseY))
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
		return mouseX > c.getX() + START_OFFSET_X + this.guiLeft && mouseX < c.getX() + START_OFFSET_X + this.guiLeft + c.getObjectWidth() && mouseY > c.getY() + START_OFFSET_Y + this.guiTop && mouseY < c.getY() + START_OFFSET_Y + this.guiTop + c.getObjectHeight();
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
		bufferbuilder.pos((double) x, (double) (y + height), 0.0D).tex((double) (u * f), (double) ((v + height) * f1)).endVertex();
		bufferbuilder.pos((double) (x + width), (double) (y + height), 0.0D).tex((double) ((u + width) * f), (double) ((v + height) * f1)).endVertex();
		bufferbuilder.pos((double) (x + width), (double) y, 0.0D).tex((double) ((u + width) * f), (double) (v * f1)).endVertex();
		bufferbuilder.pos((double) x, (double) y, 0.0D).tex((double) (u * f), (double) (v * f1)).endVertex();

		GlStateManager.scale(scale, scale, scale);
		tessellator.draw();
	}

	public void renderToolTip(ItemStack stack, int x, int y)
	{
		FontRenderer font = stack.getItem().getFontRenderer(stack);
		net.minecraftforge.fml.client.config.GuiUtils.preItemToolTip(stack);
		this.drawHoveringText(this.getItemToolTip(stack), x, y, font == null ? fontRenderer : font);
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
