/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.itemblocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.common.itemblocks.ItemBlockMorePlanets;

public class ItemBlockCrystalLeaves extends ItemBlockMorePlanets
{
	public ItemBlockCrystalLeaves(Block block)
	{
		super(block);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta | 4;
	}
}