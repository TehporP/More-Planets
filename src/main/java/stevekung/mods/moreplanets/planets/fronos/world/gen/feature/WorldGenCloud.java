/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCloud extends WorldGenerator
{
	private IBlockState block;

	public WorldGenCloud(IBlockState block)
	{
		this.block = block;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		Block block = world.getBlockState(pos).getBlock();

		if (!(block instanceof BlockAir))
		{
			return false;
		}
		else
		{
			if (rand.nextInt(4) == 0)
			{
				world.setBlockState(pos, this.block, 2);
			}
			else if (rand.nextInt(6) == 0)
			{
				world.setBlockState(pos, this.block, 2);
				world.setBlockState(pos.west(), this.block, 2);
				world.setBlockState(pos.east(), this.block, 2);
				world.setBlockState(pos.north(), this.block, 2);
				world.setBlockState(pos.south(), this.block, 2);
				world.setBlockState(pos.down(), this.block, 2);
				world.setBlockState(pos.up(), this.block, 2);
			}
		}
		return true;
	}
}