/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class MainMenuEventHandlerMP
{
	static ResourceLocation[] bopTitlePanoramaPaths = new ResourceLocation[] {new ResourceLocation("mpcore:textures/gui/title/background/panorama_0.png"), new ResourceLocation("mpcore:textures/gui/title/background/panorama_1.png"), new ResourceLocation("mpcore:textures/gui/title/background/panorama_2.png"), new ResourceLocation("mpcore:textures/gui/title/background/panorama_3.png"), new ResourceLocation("mpcore:textures/gui/title/background/panorama_4.png"), new ResourceLocation("mpcore:textures/gui/title/background/panorama_5.png")};
	static String[] titlePanoramaPaths = new String[] { "titlePanoramaPaths", "field_73978_o" };

	@SubscribeEvent
	public void openMainMenu(GuiOpenEvent event)
	{
		if (event.gui instanceof GuiMainMenu && ConfigManagerMP.enableNewMainManu == true)
		{
			GuiMainMenu mainMenu = (GuiMainMenu)event.gui;
			GUIReflectionHelper.setPrivateFinalValue(GuiMainMenu.class, mainMenu, bopTitlePanoramaPaths, titlePanoramaPaths);
		}
	}

	static class GUIReflectionHelper
	{
		public static <T, E> void setPrivateFinalValue(Class <? super T > classToAccess, T instance, E value, String... fieldNames)
		{
			Field field = ReflectionHelper.findField(classToAccess, ObfuscationReflectionHelper.remapFieldNames(classToAccess.getName(), fieldNames));

			try
			{
				Field modifiersField = Field.class.getDeclaredField("modifiers");
				modifiersField.setAccessible(true);
				modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
				field.set(instance, value);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}