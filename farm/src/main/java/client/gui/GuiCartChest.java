package client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Dist.CLIENT)
public class GuiCartChest extends ContainerScreen
{
	/** The ResourceLocation containing the chest GUI texture. */
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
	private final Inventory upperChestInventory;
	private final Inventory lowerChestInventory;
	/**
	 * window height is calculated with these values; the more rows, the heigher
	 */
	private final int inventoryRows;

	public GuiCartChest(Inventory upperInv, Inventory lowerInv)
	{
		super(new ChestMenu(Minecraft.getInstance().player), upperInv, lowerInv);
		this.upperChestInventory = upperInv;
		this.lowerChestInventory = lowerInv;
		this.allowUserInput = false;
		int i = 222;
		int j = 114;
		this.inventoryRows = lowerInv.getContainerSize() / 9;
		this.ySize = 114 + this.inventoryRows * 18;
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		this.fontRenderer.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
		this.fontRenderer.drawString(this.upperChestInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	/**
	 * Draws the background layer of this container (behind the items).
	 */
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	}
}