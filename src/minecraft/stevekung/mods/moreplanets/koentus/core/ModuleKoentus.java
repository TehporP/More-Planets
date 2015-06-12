/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.core;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.util.MPCreativeTab;
import stevekung.mods.moreplanets.koentus.items.KoentusItems;
import stevekung.mods.moreplanets.koentus.util.KoentusConfigManager;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleKoentus
{
	public static final String CONFIG_FILE = "SteveMCCommander/MorePlanet/koentus.cfg";

	public static MPCreativeTab koentusTab;

	public static void preLoad(FMLPreInitializationEvent event)
	{
		new KoentusConfigManager(new File(event.getModConfigurationDirectory(), ModuleKoentus.CONFIG_FILE));
	}

	public static void load(FMLInitializationEvent event)
	{
		ModuleKoentus.koentusTab = new MPCreativeTab(CreativeTabs.getNextID(), "MPKoentus", KoentusItems.koentusBasicItem.itemID, 0);
	}
}