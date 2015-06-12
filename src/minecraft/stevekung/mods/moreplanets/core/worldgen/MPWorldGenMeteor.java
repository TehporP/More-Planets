/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MPWorldGenMeteor extends WorldGenerator
{
	private int meteorBlockID;

	public MPWorldGenMeteor(int meteor)
	{
		this.meteorBlockID = meteor;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int x, int y, int z)
	{
		int i1 = x + par2Random.nextInt(8) - par2Random.nextInt(8);
		int j1 = y + par2Random.nextInt(4) - par2Random.nextInt(4);
		int k1 = z + par2Random.nextInt(8) - par2Random.nextInt(8);

		if (par1World.isAirBlock(i1, j1, k1) && (!par1World.provider.hasNoSky || j1 < 127) && Block.blocksList[this.meteorBlockID].canBlockStay(par1World, i1, j1, k1))
		{
			par1World.setBlock(i1, j1, k1, this.meteorBlockID, par2Random.nextInt(20), 2);
		}
		return true;
	}
}