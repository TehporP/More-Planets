/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.feature;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.common.blocks.BlockFlowerMP;

public class WorldGenFronosFlowers extends WorldGenerator
{
	private BlockFlowerMP flower;

	public WorldGenFronosFlowers(BlockFlowerMP block)
	{
		this.flower = block;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		for (int i = 0; i < 64; ++i)
		{
			BlockPos pos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			if (world.isAirBlock(pos1) && (!world.provider.getHasNoSky() || pos1.getY() < 255) && this.flower.canBlockStay(world, pos1, world.getBlockState(pos1)))
			{
				world.setBlockState(pos1, this.flower.getDefaultState(), 2);
			}
		}
		return true;
	}
}