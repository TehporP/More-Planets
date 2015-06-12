/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class FronosWorldGenSand1 extends WorldGenerator
{
	private int sandID;
	private int radius;

	public FronosWorldGenSand1(int par1, int par2)
	{
		this.sandID = par2;
		this.radius = par1;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		if (par1World.getBlockMaterial(par3, par4, par5) != Material.water) {
			return false;
		} else
		{
			int var6 = par2Random.nextInt(this.radius - 2) + 2;
			byte var7 = 2;

			for (int var8 = par3 - var6; var8 <= par3 + var6; ++var8)
			{
				for (int var9 = par5 - var6; var9 <= par5 + var6; ++var9)
				{
					int var10 = var8 - par3;
					int var11 = var9 - par5;

					if (var10 * var10 + var11 * var11 <= var6 * var6)
					{
						for (int var12 = par4 - var7; var12 <= par4 + var7; ++var12)
						{
							int var13 = par1World.getBlockId(var8, var12, var9);

							if (var13 == Block.sand.blockID)
							{
								par1World.setBlock(var8, var12, var9, this.sandID);
							}
						}
					}
				}
			}
			return true;
		}
	}
}