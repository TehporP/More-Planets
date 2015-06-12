/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.core;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.util.MPCreativeTab;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.diona.util.DionaConfigManager;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleDiona
{
	public static String CONFIG_FILE = "SteveMCCommander/MorePlanet/diona.cfg";

	public static MPCreativeTab dionaTab;

	public static void preLoad(FMLPreInitializationEvent event)
	{
		new DionaConfigManager(new File(event.getModConfigurationDirectory(), ModuleDiona.CONFIG_FILE));
	}

	public static void load(FMLInitializationEvent event)
	{
		ModuleDiona.dionaTab = new MPCreativeTab(CreativeTabs.getNextID(), "MPDiona", DionaItems.rocketT3.itemID, 0);
	}
}