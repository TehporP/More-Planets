/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.itemblocks;

import net.minecraft.block.Block;

public class ItemBlockWallMP extends ItemBlockBaseMP
{
	public ItemBlockWallMP(Block block)
	{
		super(block);
	}

	@Override
	public String[] getBlockVariantsName()
	{
		return new String[] { "diona_cobblestone", "quontonium_brick", "chiseled_quontonium", "polongnius_cobblestone", "nibiru_cobblestone", "koentus_cobblestone", "koentus_ancient_stone", "koentus_ancient_stone_brick", "fronos_cobblestone", "fronos_stone_brick", "cracked_fronos_stone_brick", "kapteyn_b_cracked_ice", "sirius_b_carbon_cobblestone", "mercury_cobblestone"};
	}
}