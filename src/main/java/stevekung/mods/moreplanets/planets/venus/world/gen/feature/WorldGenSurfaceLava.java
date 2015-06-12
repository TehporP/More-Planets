/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.venus.world.gen.feature;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.venus.blocks.BlockVenus;
import stevekung.mods.moreplanets.planets.venus.blocks.VenusBlocks;

public class WorldGenSurfaceLava extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		if (world.getBlockState(pos.down()) == VenusBlocks.venus_block.getDefaultState().withProperty(BlockVenus.VARIANT, BlockVenus.BlockType.venus_surface_rock) && world.getBlockState(pos.up()) == Blocks.air.getDefaultState())
		{
			world.setBlockState(pos, Blocks.flowing_lava.getDefaultState(), 2);
		}
		return true;
	}
}