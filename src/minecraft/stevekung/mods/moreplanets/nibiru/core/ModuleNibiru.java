/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.core;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.util.MPCreativeTab;
import stevekung.mods.moreplanets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.nibiru.util.NibiruConfigManager;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleNibiru
{
	public static final String CONFIG_FILE = "SteveMCCommander/MorePlanet/nibiru.cfg";

	public static MPCreativeTab nibiruTab;

	public static void preLoad(FMLPreInitializationEvent event)
	{
		new NibiruConfigManager(new File(event.getModConfigurationDirectory(), ModuleNibiru.CONFIG_FILE));
	}

	public static void load(FMLInitializationEvent event)
	{
		ModuleNibiru.nibiruTab = new MPCreativeTab(CreativeTabs.getNextID(), "MPNibiru", NibiruItems.rocketT5.itemID, 0);
	}
}