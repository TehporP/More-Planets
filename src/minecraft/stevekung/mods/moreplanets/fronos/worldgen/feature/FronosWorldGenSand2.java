/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;

public class FronosWorldGenSand2 extends WorldGenerator
{
	private int sandID;
	private int radius;

	public FronosWorldGenSand2(int par1, int par2)
	{
		this.sandID = par2;
		this.radius = par1;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		if (par1World.getBlockMaterial(par3, par4, par5) != Material.water)
		{
			return false;
		}
		else
		{
			int l = par2Random.nextInt(this.radius - 2) + 2;
			byte b0 = 2;

			for (int i1 = par3 - l; i1 <= par3 + l; ++i1)
			{
				for (int j1 = par5 - l; j1 <= par5 + l; ++j1)
				{
					int k1 = i1 - par3;
					int l1 = j1 - par5;

					if (k1 * k1 + l1 * l1 <= l * l)
					{
						for (int i2 = par4 - b0; i2 <= par4 + b0; ++i2)
						{
							int j2 = par1World.getBlockId(i1, i2, j1);

							if (j2 == FronosBlocks.fronosGrass.blockID || j2 == FronosBlocks.fronosDirt.blockID)
							{
								par1World.setBlock(i1, i2, j1, this.sandID, 0, 2);
							}
						}
					}
				}
			}
			return true;
		}
	}
}