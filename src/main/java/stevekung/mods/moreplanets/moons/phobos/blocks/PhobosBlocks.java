/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.phobos.blocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.moons.phobos.itemblocks.ItemBlockPhobos;
import stevekung.mods.stevecore.RegisterHelper;

public class PhobosBlocks
{
	public static Block phobos_block;

	public static void init()
	{
		PhobosBlocks.initBlocks();
		PhobosBlocks.setHarvestLevels();
		PhobosBlocks.registerBlocks();
	}

	private static void initBlocks()
	{
		PhobosBlocks.phobos_block = new BlockPhobos("phobos_block");
	}

	private static void setHarvestLevels()
	{
		PhobosBlocks.phobos_block.setHarvestLevel("pickaxe", 1);
	}

	private static void registerBlocks()
	{
		RegisterHelper.registerBlock(PhobosBlocks.phobos_block, ItemBlockPhobos.class);
	}
}