/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.core.render.tileentities;

import micdoodle8.mods.galacticraft.core.client.model.block.GCCoreModelSolarPanel;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.polongnius.tileentities.PolongniusTileEntitySolar;

public class PolongniusTileEntitySolarPanelRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation solarPanelAdvTexture = new ResourceLocation("polongnius:textures/model/solarPanelAdvanced.png");
	public GCCoreModelSolarPanel model = new GCCoreModelSolarPanel();

	@Override
	public void renderTileEntityAt(TileEntity var1, double par2, double par4, double par6, float var8)
	{
		PolongniusTileEntitySolar panel = (PolongniusTileEntitySolar) var1;

		if (var1.getBlockType() == PolongniusBlocks.polongniusSolarPanel)
		{
			this.bindTexture(PolongniusTileEntitySolarPanelRenderer.solarPanelAdvTexture);
		}

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) par2, (float) par4, (float) par6);

		GL11.glTranslatef(0.5F, 1.0F, 0.5F);
		this.model.renderPole();
		GL11.glTranslatef(0.0F, 1.5F, 0.0F);

		GL11.glRotatef(180.0F, 0, 0, 1);
		GL11.glRotatef(-90.0F, 0, 1, 0);

		float celestialAngle = (panel.worldObj.getCelestialAngle(1.0F) - 0.784690560F) * 360.0F;
		float celestialAngle2 = panel.worldObj.getCelestialAngle(1.0F) * 360.0F;

		GL11.glRotatef(panel.currentAngle - (celestialAngle - celestialAngle2), 1.0F, 0.0F, 0.0F);

		this.model.renderPanel();

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
