/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.common.blocks.IFronosGrass;

public class WorldGenSpaceOyster extends WorldGenerator
{
	private IBlockState block;

	public WorldGenSpaceOyster(IBlockState block)
	{
		this.block = block;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (!(block instanceof IFronosGrass))
		{
			return false;
		}
		else
		{
			world.setBlockState(pos.up(), this.block, 2);
			return true;
		}
	}
}