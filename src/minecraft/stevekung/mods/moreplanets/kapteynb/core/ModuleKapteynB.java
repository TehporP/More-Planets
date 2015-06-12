/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.core;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import stevekung.mods.moreplanets.core.util.MPCreativeTab;
import stevekung.mods.moreplanets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.kapteynb.util.KapteynBConfigManager;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModuleKapteynB
{
	public static final String CONFIG_FILE = "SteveMCCommander/MorePlanet/kapteynb.cfg";

	public static MPCreativeTab kapteynB;

	public static void preLoad(FMLPreInitializationEvent event)
	{
		new KapteynBConfigManager(new File(event.getModConfigurationDirectory(), ModuleKapteynB.CONFIG_FILE));
	}

	public static void load(FMLInitializationEvent event)
	{
		ModuleKapteynB.kapteynB = new MPCreativeTab(CreativeTabs.getNextID(), "MPKapteynB", KapteynBBlocks.kapteynBBasicBlock.blockID, 0);
	}
}