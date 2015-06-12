/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public abstract class MPGuiContainer extends GuiContainer
{
	public List<MPInfoRegion> infoRegions = new ArrayList<MPInfoRegion>();

	public MPGuiContainer(Container container)
	{
		super(container);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		super.drawScreen(par1, par2, par3);

		for (int k = 0; k < this.infoRegions.size(); ++k)
		{
			MPInfoRegion guibutton = this.infoRegions.get(k);
			guibutton.drawRegion(par1, par2);
		}
	}

	@Override
	public void setWorldAndResolution(Minecraft par1Minecraft, int par2, int par3)
	{
		this.infoRegions.clear();
		super.setWorldAndResolution(par1Minecraft, par2, par3);
	}
}