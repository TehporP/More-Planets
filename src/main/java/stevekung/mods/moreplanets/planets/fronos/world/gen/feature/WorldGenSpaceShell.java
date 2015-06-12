/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.feature;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockFronosSand;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class WorldGenSpaceShell extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		BlockPos pos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

		for (int i = 0; i < 64; ++i)
		{
			if (world.isAirBlock(pos1) && (world.getBlockState(pos1.down()) == Blocks.sand.getDefaultState() || world.getBlockState(pos1.down()) == FronosBlocks.fronos_sand.getDefaultState().withProperty(BlockFronosSand.VARIANT, BlockFronosSand.BlockType.white_sand)) && FronosBlocks.space_shell.canPlaceBlockAt(world, pos1))
			{
				world.setBlockState(pos1, FronosBlocks.space_shell.getStateFromMeta(rand.nextInt(16)), 2);
			}
		}
		return true;
	}
}