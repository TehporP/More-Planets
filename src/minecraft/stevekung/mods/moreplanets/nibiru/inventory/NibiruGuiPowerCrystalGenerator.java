/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.inventory;

import micdoodle8.mods.galacticraft.api.transmission.ElectricityDisplay;
import micdoodle8.mods.galacticraft.api.transmission.ElectricityDisplay.ElectricUnit;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityPowerCrystalGenerator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NibiruGuiPowerCrystalGenerator extends GuiContainer
{
	private static final ResourceLocation powerCrystalGeneratorTexture = new ResourceLocation("nibiru:textures/gui/powerCrystalGenerator.png");

	private NibiruTileEntityPowerCrystalGenerator tileEntity;

	private int containerWidth;
	private int containerHeight;

	public NibiruGuiPowerCrystalGenerator(InventoryPlayer par1InventoryPlayer, NibiruTileEntityPowerCrystalGenerator tileEntity)
	{
		super(new NibiruContainerPowerCrystalGenerator(par1InventoryPlayer, tileEntity));
		this.tileEntity = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString(this.tileEntity.getInvName(), 55, 6, 4210752);
		String displayText = "Collecting";
		this.fontRenderer.drawString(displayText, 122 - this.fontRenderer.getStringWidth(displayText) / 2, 33, 4210752);

		if (this.tileEntity.generateWatts <= 0)
		{
			displayText = "Not Collecting";
		}
		else if (this.tileEntity.generateWatts < NibiruTileEntityPowerCrystalGenerator.MIN_GENERATE_WATTS)
		{
			displayText = "Power: " + (int) (this.tileEntity.generateWatts / NibiruTileEntityPowerCrystalGenerator.MIN_GENERATE_WATTS * 100) + "%";
		}
		else
		{
			displayText = ElectricityDisplay.getDisplay(this.tileEntity.generateWatts * 20, ElectricUnit.WATT);
		}

		this.fontRenderer.drawString(displayText, 122 - this.fontRenderer.getStringWidth(displayText) / 2, 45, 4210752);
		displayText = "Voltage: " + (int) (this.tileEntity.getVoltage() * 1000.0F);
		this.fontRenderer.drawString(displayText, 122 - this.fontRenderer.getStringWidth(displayText) / 2, 60, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		this.mc.renderEngine.bindTexture(powerCrystalGeneratorTexture);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
	}
}