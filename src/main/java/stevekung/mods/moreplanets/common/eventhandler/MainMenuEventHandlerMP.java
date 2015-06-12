/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.eventhandler;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import stevekung.mods.moreplanets.common.config.ConfigManagerMP;

public class MainMenuEventHandlerMP
{
	private static ResourceLocation[] titlePanoramaPaths = new ResourceLocation[] {new ResourceLocation("moreplanets:textures/gui/title/background/panorama_0.png"), new ResourceLocation("moreplanets:textures/gui/title/background/panorama_1.png"), new ResourceLocation("moreplanets:textures/gui/title/background/panorama_2.png"), new ResourceLocation("moreplanets:textures/gui/title/background/panorama_3.png"), new ResourceLocation("moreplanets:textures/gui/title/background/panorama_4.png"), new ResourceLocation("moreplanets:textures/gui/title/background/panorama_5.png")};

	@SubscribeEvent
	public void openMainMenu(GuiOpenEvent event)
	{
		if (event.gui instanceof GuiMainMenu && ConfigManagerMP.enableNewMainManu == true)
		{
			GuiMainMenu.titlePanoramaPaths = MainMenuEventHandlerMP.titlePanoramaPaths;
		}
	}
}