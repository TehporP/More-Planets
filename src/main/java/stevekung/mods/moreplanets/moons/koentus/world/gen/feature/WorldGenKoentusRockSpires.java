/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.world.gen.feature;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.moons.koentus.blocks.KoentusBlocks;

public class WorldGenKoentusRockSpires extends WorldGenerator
{
	private IBlockState state;

	public WorldGenKoentusRockSpires(IBlockState state)
	{
		super(false);
		this.state = state;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		while (true)
		{
			if (pos.getY() > 3)
			{
				label47:
				{
				if (!world.isAirBlock(pos.down()))
				{
					IBlockState state = world.getBlockState(pos.down());

					if (state == KoentusBlocks.koentus_block.getDefaultState())
					{
						break label47;
					}
				}
				pos = pos.down();
				continue;
				}
			}

			if (pos.getY() <= 3)
			{
				return false;
			}

			int i1 = 0;

			for (int i = 0; i1 >= 0 && i < 3; ++i)
			{
				int j = i1 + rand.nextInt(2);
				int k = i1 + rand.nextInt(2);
				int l = i1 + rand.nextInt(2);
				float f = (j + k + l) * 0.333F + 0.5F;
				Iterator iterator = BlockPos.getAllInBox(pos.add(-j, -k, -l), pos.add(j, k, l)).iterator();

				while (iterator.hasNext())
				{
					BlockPos blockpos1 = (BlockPos)iterator.next();

					if (blockpos1.distanceSq(pos) <= f * f)
					{
						world.setBlockState(blockpos1, this.state, 4);
					}
				}
				pos = pos.add(-(i1 + 1) + rand.nextInt(2 + i1 * 2), 0 - rand.nextInt(2), -(i1 + 1) + rand.nextInt(2 + i1 * 2));
			}
			return true;
		}
	}
}