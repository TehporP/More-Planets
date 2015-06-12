/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core.render.gui;

import micdoodle8.mods.galacticraft.api.world.ICelestialBodyRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class FronosSlotRenderer implements ICelestialBodyRenderer
{
	@Override
	public String getPlanetName()
	{
		return "Fronos";
	}

	@Override
	public ResourceLocation getPlanetSprite()
	{
		return new ResourceLocation("fronos:textures/gui/planets/fronos.png");
	}

	@Override
	public void renderSlot(int index, int x, int y, float slotHeight, Tessellator tessellator)
	{
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 12 - slotHeight, y - 11 + slotHeight, -90.0D, 0.0, 1.0);
		tessellator.addVertexWithUV(x + 12, y - 11 + slotHeight, -90.0D, 1.0, 1.0);
		tessellator.addVertexWithUV(x + 12, y - 11, -90.0D, 1.0, 0.0);
		tessellator.addVertexWithUV(x + 12 - slotHeight, y - 11, -90.0D, 0.0, 0.0);
		tessellator.draw();
	}
}