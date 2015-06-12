/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.core;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.util.MPCreativeTab;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;
import stevekung.mods.moreplanets.polongnius.util.PolongniusConfigManager;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModulePolongnius
{
	public static final String CONFIG_FILE = "SteveMCCommander/MorePlanet/polongnius.cfg";

	public static MPCreativeTab polongniusTab;

	public static void preLoad(FMLPreInitializationEvent event)
	{
		new PolongniusConfigManager(new File(event.getModConfigurationDirectory(), ModulePolongnius.CONFIG_FILE));
	}

	public static void load(FMLInitializationEvent event)
	{
		ModulePolongnius.polongniusTab = new MPCreativeTab(CreativeTabs.getNextID(), "MPPolongnius", PolongniusItems.rocketTier4.itemID, 4);
	}
}