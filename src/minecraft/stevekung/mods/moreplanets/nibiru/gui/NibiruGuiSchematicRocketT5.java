/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.gui;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicResultPage;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.nibiru.inventory.NibiruContainerRocketBenchT5;

public class NibiruGuiSchematicRocketT5 extends GuiContainer implements ISchematicResultPage
{
	private static final ResourceLocation tier5SchematicTexture = new ResourceLocation("nibiru:textures/gui/schematicRocketT5.png");

	private int pageIndex;

	public NibiruGuiSchematicRocketT5(InventoryPlayer par1InventoryPlayer, int x, int y, int z)
	{
		super(new NibiruContainerRocketBenchT5(par1InventoryPlayer, x, y, z));
		this.ySize = 238;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 130, this.height / 2 - 30 + 27 - 12, 40, 20, "Back"));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 130, this.height / 2 - 30 + 27 + 12, 40, 20, "Next"));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		if (par1GuiButton.enabled)
		{
			switch (par1GuiButton.id)
			{
			case 0:
				SchematicRegistry.flipToLastPage(this.pageIndex);
				break;
			case 1:
				SchematicRegistry.flipToNextPage(this.pageIndex);
				break;
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString("Tier 5 Rocket", 7, -20 + 27, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, 220 - 104 + 2 + 27, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(tier5SchematicTexture);
		final int var5 = (this.width - this.xSize) / 2;
		final int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	}

	@Override
	public void setPageIndex(int index)
	{
		this.pageIndex = index;
	}
}