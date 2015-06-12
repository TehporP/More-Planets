/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks.template;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBreakableMP extends Block
{
	public BlockBreakableMP(int par1, Material par2Material, boolean isLight)
	{
		super(par1, par2Material);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}