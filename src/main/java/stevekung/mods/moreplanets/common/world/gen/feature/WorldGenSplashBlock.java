/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.world.gen.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSplashBlock extends WorldGenerator
{
	private IBlockState block;
	private IBlockState blockToGen;

	public WorldGenSplashBlock(IBlockState block, IBlockState blockToGen)
	{
		super();
		this.block = block;
		this.blockToGen = blockToGen;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		for (int i = 0; i < 64; i++)
		{
			int x1 = pos.getX() + rand.nextInt(8) - rand.nextInt(8);
			int y1 = pos.getY() + rand.nextInt(4) - rand.nextInt(4);
			int z1 = pos.getZ() + rand.nextInt(8) - rand.nextInt(8);
			BlockPos pos1 = new BlockPos(x1, y1, z1);

			if (world.getBlockState(pos1.down()) == this.blockToGen)
			{
				world.setBlockState(pos1.down(), this.block, 2);
			}
		}
		return true;
	}
}