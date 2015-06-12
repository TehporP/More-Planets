/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.core;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.util.MPCreativeTab;
import stevekung.mods.moreplanets.fronos.items.FronosItems;
import stevekung.mods.moreplanets.fronos.util.FronosConfigManager;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleFronos
{
	public static final String CONFIG_FILE = "SteveMCCommander/MorePlanet/fronos.cfg";

	public static MPCreativeTab fronosTab;

	public static void preLoad(FMLPreInitializationEvent event)
	{
		new FronosConfigManager(new File(event.getModConfigurationDirectory(), ModuleFronos.CONFIG_FILE));
	}

	public static void load(FMLInitializationEvent event)
	{
		ModuleFronos.fronosTab = new MPCreativeTab(CreativeTabs.getNextID(), "MPFronos", FronosItems.bearryEgg.itemID, 0);
	}
}