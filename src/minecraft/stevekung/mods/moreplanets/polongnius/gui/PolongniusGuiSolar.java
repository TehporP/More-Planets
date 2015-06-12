/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.gui;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.transmission.ElectricityDisplay;
import micdoodle8.mods.galacticraft.api.transmission.ElectricityDisplay.ElectricUnit;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.network.GCCorePacketHandlerServer.EnumPacketServer;
import micdoodle8.mods.galacticraft.core.util.PacketUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import stevekung.mods.moreplanets.core.gui.MPGuiContainer;
import stevekung.mods.moreplanets.core.gui.MPInfoRegion;
import stevekung.mods.moreplanets.polongnius.tileentities.PolongniusTileEntitySolar;
import cpw.mods.fml.common.network.PacketDispatcher;

public class PolongniusGuiSolar extends MPGuiContainer
{
	private static final ResourceLocation solarGuiTexture = new ResourceLocation("polongnius:textures/gui/polongniusSolar.png");

	private final PolongniusTileEntitySolar solarPanel;

	private GuiButton buttonEnableSolar;
	private MPInfoRegion electricInfoRegion = new MPInfoRegion((this.width - this.xSize) / 2 + 107, (this.height - this.ySize) / 2 + 101, 56, 9, new ArrayList<String>(), this.width, this.height);

	public PolongniusGuiSolar(InventoryPlayer par1InventoryPlayer, PolongniusTileEntitySolar solarPanel)
	{
		super(new PolongniusContainerSolar(par1InventoryPlayer, solarPanel));
		this.solarPanel = solarPanel;
		this.ySize = 201;
		this.xSize = 176;
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		switch (par1GuiButton.id)
		{
		case 0:
			PacketDispatcher.sendPacketToServer(PacketUtil.createPacket(GalacticraftCore.CHANNEL, EnumPacketServer.UPDATE_DISABLEABLE_BUTTON, new Object[] { this.solarPanel.xCoord, this.solarPanel.yCoord, this.solarPanel.zCoord, 0 }));
			break;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		super.initGui();
		List<String> electricityDesc = new ArrayList<String>();
		electricityDesc.add(EnumChatFormatting.AQUA + "Electrical Storage");
		electricityDesc.add(EnumChatFormatting.AQUA + "Energy: " + ((int) Math.floor(this.solarPanel.getEnergyStored()) + " / " + (int) Math.floor(this.solarPanel.getMaxEnergyStored())));
		this.electricInfoRegion.tooltipStrings = electricityDesc;
		this.electricInfoRegion.xPosition = (this.width - this.xSize) / 2 + 96;
		this.electricInfoRegion.yPosition = (this.height - this.ySize) / 2 + 24;
		this.electricInfoRegion.parentWidth = this.width;
		this.electricInfoRegion.parentHeight = this.height;
		this.infoRegions.add(this.electricInfoRegion);
		List<String> batterySlotDesc = new ArrayList<String>();
		batterySlotDesc.add("Solar Panel battery slot, place battery");
		batterySlotDesc.add("here to fill it with energy.");
		this.infoRegions.add(new MPInfoRegion((this.width - this.xSize) / 2 + 151, (this.height - this.ySize) / 2 + 82, 18, 18, batterySlotDesc, this.width, this.height));
		List<String> sunGenDesc = new ArrayList<String>();
		float sunVisible = Math.round(this.solarPanel.solarStrength / 9.0F * 1000) / 10.0F;
		sunGenDesc.add(this.solarPanel.solarStrength > 0 ? EnumChatFormatting.AQUA + "Sirius visible: " + sunVisible + "%" : EnumChatFormatting.AQUA + "Sirius not visible");
		this.infoRegions.add(new MPInfoRegion((this.width - this.xSize) / 2 + 47, (this.height - this.ySize) / 2 + 20, 18, 18, sunGenDesc, this.width, this.height));
		this.buttonList.add(this.buttonEnableSolar = new GuiButton(0, this.width / 2 - 36, this.height / 2 - 10, 72, 20, StatCollector.translateToLocal("gui.button.enable.name")));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		int offsetY = 35;
		this.buttonEnableSolar.enabled = this.solarPanel.disableCooldown == 0;
		this.buttonEnableSolar.displayString = !this.solarPanel.getDisabled(0) ? StatCollector.translateToLocal("gui.button.disable.name") : StatCollector.translateToLocal("gui.button.enable.name");
		String displayString = this.solarPanel.getInvName();
		this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 7, 4210752);
		displayString = StatCollector.translateToLocal("gui.message.status.name") + ": " + this.getStatus();
		this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 45 + 23 - 46 + offsetY, 4210752);
		displayString = StatCollector.translateToLocal("gui.message.generating.name") + ": " + (this.solarPanel.generateWatts > 0 ? ElectricityDisplay.getDisplay(this.solarPanel.generateWatts * 20.0F, ElectricUnit.WATT) : "Not Generating");
		this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 34 + 23 - 46 + offsetY, 4210752);
		float boost = Math.round((this.solarPanel.getSolarBoost() - 1) * 1000) / 10.0F;
		displayString = StatCollector.translateToLocal("gui.message.environment.name") + ": " + boost + "%";
		this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 56 + 23 - 46 + offsetY, 4210752);
		displayString = ElectricityDisplay.getDisplay(this.solarPanel.getVoltage(), ElectricUnit.VOLTAGE);
		this.fontRenderer.drawString(displayString, this.xSize / 2 - this.fontRenderer.getStringWidth(displayString) / 2, 68 + 23 - 46 + offsetY, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 90, 4210752);
	}

	private String getStatus()
	{
		if (this.solarPanel.getDisabled(0))
		{
			return EnumChatFormatting.GOLD + StatCollector.translateToLocal("gui.status.disabled.name");
		}

		if (!this.solarPanel.worldObj.isDaytime())
		{
			return EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("gui.status.blockedfully.name");
		}

		if (this.solarPanel.worldObj.isRaining() || this.solarPanel.worldObj.isThundering())
		{
			return EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("gui.status.raining.name");
		}

		if (this.solarPanel.solarStrength == 0)
		{
			return EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("gui.status.blockedfully.name");
		}

		if (this.solarPanel.solarStrength < 9)
		{
			return EnumChatFormatting.DARK_RED + StatCollector.translateToLocal("gui.status.blockedpartial.name");
		}

		if (this.solarPanel.generateWatts > 0)
		{
			return EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal("gui.status.collectingenergy.name");
		}

		return EnumChatFormatting.GOLD + StatCollector.translateToLocal("gui.status.unknown.name");
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(PolongniusGuiSolar.solarGuiTexture);
		final int var5 = (this.width - this.xSize) / 2;
		final int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);

		List<String> electricityDesc = new ArrayList<String>();
		electricityDesc.add("Electrical Storage");
		electricityDesc.add(EnumChatFormatting.YELLOW + "Energy: " + ((int) Math.floor(this.solarPanel.getEnergyStored()) + " / " + (int) Math.floor(this.solarPanel.getMaxEnergyStored())));
		this.electricInfoRegion.tooltipStrings = electricityDesc;

		if (this.solarPanel.getEnergyStored() > 0)
		{
			this.drawTexturedModalRect(var5 + 83, var6 + 24, 176, 0, 11, 10);
		}

		if (this.solarPanel.solarStrength > 0)
		{
			this.drawTexturedModalRect(var5 + 48, var6 + 21, 176, 10, 16, 16);
		}

		this.drawTexturedModalRect(var5 + 97, var6 + 25, 187, 0, Math.min(this.solarPanel.getScaledElecticalLevel(54), 54), 7);
	}
}
