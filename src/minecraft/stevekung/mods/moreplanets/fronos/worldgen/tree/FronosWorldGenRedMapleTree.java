/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;

public class FronosWorldGenRedMapleTree extends WorldGenerator
{
	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		int var6 = par2Random.nextInt(3) + 5;
		boolean var7 = true;

		if (par4 >= 1 && par4 + var6 + 1 <= 256)
		{
			int var8;
			int var10;
			int var11;
			int var12;

			for (var8 = par4; var8 <= par4 + 1 + var6; ++var8)
			{
				byte var9 = 1;

				if (var8 == par4)
				{
					var9 = 0;
				}

				if (var8 >= par4 + 1 + var6 - 2)
				{
					var9 = 2;
				}

				for (var10 = par3 - var9; var10 <= par3 + var9 && var7; ++var10)
				{
					for (var11 = par5 - var9; var11 <= par5 + var9 && var7; ++var11)
					{
						if (var8 >= 0 && var8 < 256)
						{
							var12 = par1World.getBlockId(var10, var8, var11);

							if (var12 != 0 && var12 != FronosBlocks.fronosBlockLeaves.blockID)
							{
								var7 = false;
							}
						}
						else
						{
							var7 = false;
						}
					}
				}
			}

			if (!var7)
			{
				return false;
			}
			else
			{
				var8 = par1World.getBlockId(par3, par4 - 1, par5);

				if ((var8 == FronosBlocks.fronosGrass.blockID || var8 == FronosBlocks.fronosDirt.blockID || var8 == FronosBlocks.fronosPinkGrass.blockID || var8 == FronosBlocks.fronosPlainsGrass.blockID || var8 == FronosBlocks.fronosPurpleGrass.blockID || var8 == FronosBlocks.goldenGrass.blockID) && par4 < 256 - var6 - 1)
				{
					this.setBlock(par1World, par3, par4 - 1, par5, FronosBlocks.fronosDirt.blockID);
					int var16;

					for (var16 = par4 - 3 + var6; var16 <= par4 + var6; ++var16)
					{
						var10 = var16 - (par4 + var6);
						var11 = 1 - var10 / 2;

						for (var12 = par3 - var11; var12 <= par3 + var11; ++var12)
						{
							int var13 = var12 - par3;

							for (int var14 = par5 - var11; var14 <= par5 + var11; ++var14)
							{
								int var15 = var14 - par5;

								if ((Math.abs(var13) != var11 || Math.abs(var15) != var11 || par2Random.nextInt(2) != 0 && var10 != 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var12, var16, var14)])
								{
									this.setBlockAndMetadata(par1World, var12, var16, var14, FronosBlocks.fronosBlockLeaves.blockID, 1);
								}
							}
						}
					}

					for (var16 = 0; var16 < var6; ++var16)
					{
						var10 = par1World.getBlockId(par3, par4 + var16, par5);

						if (var10 == 0 || var10 == FronosBlocks.fronosBlockLeaves.blockID)
						{
							this.setBlockAndMetadata(par1World, par3, par4 + var16, par5, FronosBlocks.fronosBlockLog.blockID, 1);
						}
					}
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}
}