/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.itemblocks;

import net.minecraft.block.Block;

public class ItemBlockDungeonBrickWall extends ItemBlockBaseMP
{
	public ItemBlockDungeonBrickWall(Block block)
	{
		super(block);
	}

	@Override
	public String[] getBlockVariantsName()
	{
		return new String[] { "diona_dungeon_brick", "polongnius_dungeon_brick", "nibiru_dungeon_brick", "koentus_dungeon_brick", "fronos_dungeon_brick", "kapteyn_b_dungeon_brick", "sirius_dungeon_brick", "mercury_dungeon_brick"};
	}
}