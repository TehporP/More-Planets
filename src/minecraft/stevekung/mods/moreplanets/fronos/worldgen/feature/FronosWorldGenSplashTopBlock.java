/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;

public class FronosWorldGenSplashTopBlock extends WorldGenerator
{
	private int tallGrassID;
	private int tallGrassMetadata;

	public FronosWorldGenSplashTopBlock(int par1, int par2)
	{
		this.tallGrassID = par1;
		this.tallGrassMetadata = par2;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		int var11;

		for (; ((var11 = par1World.getBlockId(par3, par4, par5)) == 0 || var11 == Block.leaves.blockID) && par4 > 0; --par4)
		{
		}

		for (int var7 = 0; var7 < 128; ++var7)
		{
			int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(var8, var9, var10) && par1World.getBlockId(var8, var9 - 1, var10) == FronosBlocks.fronosGrass.blockID)
			{
				par1World.setBlock(var8, var9 - 1, var10, this.tallGrassID, this.tallGrassMetadata, 2);
			}
		}
		return true;
	}
}