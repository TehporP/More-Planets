/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.europa.blocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.moons.europa.itemblocks.ItemBlockEuropaPrismarine;
import stevekung.mods.stevecore.RegisterHelper;

public class EuropaBlocks
{
	public static Block europa_prismarine;
	public static Block europa_sea_lantern;

	public static void init()
	{
		EuropaBlocks.initBlocks();
		EuropaBlocks.setHarvestLevels();
		EuropaBlocks.registerBlocks();
	}

	private static void initBlocks()
	{
		EuropaBlocks.europa_prismarine = new BlockEuropaPrismarine("europa_prismarine");
		EuropaBlocks.europa_sea_lantern = new BlockEuropaSeaLantern("europa_sea_lantern");
	}

	private static void setHarvestLevels()
	{
		EuropaBlocks.europa_prismarine.setHarvestLevel("pickaxe", 0);
	}

	private static void registerBlocks()
	{
		RegisterHelper.registerBlock(EuropaBlocks.europa_prismarine, ItemBlockEuropaPrismarine.class);
		RegisterHelper.registerBlock(EuropaBlocks.europa_sea_lantern);
	}
}