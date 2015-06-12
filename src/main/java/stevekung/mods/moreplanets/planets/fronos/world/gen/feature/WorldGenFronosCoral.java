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
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class WorldGenFronosCoral extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		for (int i = 0; i < 64; ++i)
		{
			BlockPos pos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			if ((world.getBlockState(pos1) == Blocks.water.getDefaultState() || world.getBlockState(pos1) == Blocks.flowing_water.getDefaultState()) && (world.getBlockState(pos1.up()) == Blocks.water.getDefaultState() || world.getBlockState(pos1.up()) == Blocks.flowing_water.getDefaultState()) && FronosBlocks.fronos_coral.canBlockStay(world, pos1, world.getBlockState(pos1)))
			{
				world.setBlockState(pos1, FronosBlocks.fronos_coral.getStateFromMeta(rand.nextInt(2)), 2);
			}
		}
		return true;
	}
}