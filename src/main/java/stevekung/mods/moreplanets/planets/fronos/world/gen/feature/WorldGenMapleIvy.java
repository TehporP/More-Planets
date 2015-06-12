/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.planets.fronos.blocks.BlockMapleIvy;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class WorldGenMapleIvy extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		for (; pos.getY() < 128; pos = pos.up())
		{
			if (world.isAirBlock(pos))
			{
				EnumFacing[] aenumfacing = EnumFacing.Plane.HORIZONTAL.facings();
				int i = aenumfacing.length;

				for (int j = 0; j < i; ++j)
				{
					EnumFacing enumfacing = aenumfacing[j];

					if (FronosBlocks.maple_ivy.canPlaceBlockOnSide(world, pos, enumfacing))
					{
						IBlockState iblockstate = FronosBlocks.maple_ivy.getDefaultState().withProperty(BlockMapleIvy.NORTH, Boolean.valueOf(enumfacing == EnumFacing.NORTH)).withProperty(BlockMapleIvy.EAST, Boolean.valueOf(enumfacing == EnumFacing.EAST)).withProperty(BlockMapleIvy.SOUTH, Boolean.valueOf(enumfacing == EnumFacing.SOUTH)).withProperty(BlockMapleIvy.WEST, Boolean.valueOf(enumfacing == EnumFacing.WEST));
						world.setBlockState(pos, iblockstate, 2);
						break;
					}
				}
			}
			else
			{
				pos = pos.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
			}
		}
		return true;
	}
}