/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.fronos.inventory.container.ContainerCandyExtractor;
import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityCandyExtractor;

@SideOnly(Side.CLIENT)
public class GuiCandyExtractor extends GuiContainer
{
	private ResourceLocation candyExtractorGuiTextures = new ResourceLocation("moreplanets:textures/gui/candy_extractor.png");
	private InventoryPlayer playerInventory;
	private IInventory tileFurnace;

	public GuiCandyExtractor(InventoryPlayer playerInv, IInventory furnaceInv)
	{
		super(new ContainerCandyExtractor(playerInv, furnaceInv));
		this.playerInventory = playerInv;
		this.tileFurnace = furnaceInv;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String s = this.tileFurnace.getDisplayName().getUnformattedText();
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(this.candyExtractorGuiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;

		if (TileEntityCandyExtractor.isBurning(this.tileFurnace))
		{
			i1 = this.func_175382_i(13);
			this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
		}
		i1 = this.func_175381_h(24);
		this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}

	private int func_175381_h(int p_175381_1_)
	{
		int j = this.tileFurnace.getField(2);
		int k = this.tileFurnace.getField(3);
		return k != 0 && j != 0 ? j * p_175381_1_ / k : 0;
	}

	private int func_175382_i(int p_175382_1_)
	{
		int j = this.tileFurnace.getField(1);

		if (j == 0)
		{
			j = 200;
		}
		return this.tileFurnace.getField(0) * p_175382_1_ / j;
	}
}