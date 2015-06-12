/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.feature;

import java.util.Random;

import net.minecraft.block.BlockDirectional;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.common.blocks.IFronosGrass;

public class WorldGenFronosPumpkin extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		for (int i = 0; i < 64; ++i)
		{
			BlockPos blockpos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

			if (world.isAirBlock(blockpos1) && world.getBlockState(blockpos1.down()).getBlock() instanceof IFronosGrass && Blocks.pumpkin.canPlaceBlockAt(world, blockpos1))
			{
				world.setBlockState(blockpos1, Blocks.pumpkin.getDefaultState().withProperty(BlockDirectional.FACING, EnumFacing.Plane.HORIZONTAL.random(rand)), 2);
			}
		}
		return true;
	}
}