/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.blocks;

import net.minecraft.block.Block;
import stevekung.mods.moreplanets.common.blocks.BlockFarmlandMP;

public class BlockCrystalFarmland extends BlockFarmlandMP
{
	public BlockCrystalFarmland(String name)
	{
		super();
		this.setUnlocalizedName(name);
	}

	@Override
	public Block getDirtBlock()
	{
		return KoentusBlocks.crystal_dirt;
	}
}