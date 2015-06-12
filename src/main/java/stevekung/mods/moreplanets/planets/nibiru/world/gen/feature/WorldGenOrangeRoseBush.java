/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.world.gen.feature;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.nibiru.blocks.BlockInfectedOrangeRoseBush;
import stevekung.mods.moreplanets.planets.nibiru.blocks.NibiruBlocks;

public class WorldGenOrangeRoseBush extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		for (int i = 0; i < 64; ++i)
		{
			BlockPos blockpos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			if (world.isAirBlock(blockpos1) && (!world.provider.getHasNoSky() || blockpos1.getY() < 254) && NibiruBlocks.infected_orange_rose_bush.canPlaceBlockAt(world, blockpos1))
			{
				world.setBlockState(blockpos1, NibiruBlocks.infected_orange_rose_bush.getDefaultState().withProperty(BlockInfectedOrangeRoseBush.VARIANT, BlockInfectedOrangeRoseBush.BlockType.orange_rose_bush_bottom), 2);
				world.setBlockState(blockpos1.up(), NibiruBlocks.infected_orange_rose_bush.getDefaultState().withProperty(BlockInfectedOrangeRoseBush.VARIANT, BlockInfectedOrangeRoseBush.BlockType.orange_rose_bush_top), 2);
			}
		}
		return true;
	}
}