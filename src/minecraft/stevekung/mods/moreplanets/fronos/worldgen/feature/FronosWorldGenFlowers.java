/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class FronosWorldGenFlowers extends WorldGenerator
{
	private int plantBlockId;
	private int plantBlockMeta;

	public FronosWorldGenFlowers(int par1, int meta)
	{
		this.plantBlockId = par1;
		this.plantBlockMeta = meta;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		for (int l = 0; l < 64; ++l)
		{
			int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int j1 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(i1, j1, k1) && (!par1World.provider.hasNoSky || j1 < 127) && (par1World.getFullBlockLightValue(i1, j1, k1) >= 8 || par1World.canBlockSeeTheSky(i1, j1, k1))
					&& Block.blocksList[this.plantBlockId].canPlaceBlockOnSide(par1World, i1, j1, k1, 1, new ItemStack(this.plantBlockId, 1, this.plantBlockMeta)))
			{
				par1World.setBlock(i1, j1, k1, this.plantBlockId, this.plantBlockMeta, 2);
			}
		}

		return true;
	}
}
