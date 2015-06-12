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

public class FronosWorldGenLog extends WorldGenerator
{
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		while (var1.isAirBlock(var3, var4, var5) && var4 > 2)
		{
			--var4;
		}

		int var6 = var1.getBlockId(var3, var4, var5);
		int var61 = var1.getBlockId(var3 - 1, var4, var5);
		int var62 = var1.getBlockId(var3 + 1, var4, var5);
		int var63 = var1.getBlockId(var3 - 2, var4, var5);
		int var64 = var1.getBlockId(var3 + 2, var4, var5);

		if (var6 == FronosBlocks.fronosGrass.blockID)
		{
			if (var61 == FronosBlocks.fronosGrass.blockID)
			{
				if (var62 == FronosBlocks.fronosGrass.blockID)
				{
					if (var63 == FronosBlocks.fronosGrass.blockID)
					{
						if (var64 == FronosBlocks.fronosGrass.blockID)
						{
							for (int var7 = -2; var7 <= 2; ++var7)
							{
								for (int var8 = -2; var8 <= 2; ++var8)
								{
									if (!var1.isAirBlock(var3, var4 + 1, var5 + var8) && !var1.isAirBlock(var3 - 1, var4 + 1, var5 + var8) && !var1.isAirBlock(var3 + 1, var4 + 1, var5 + var8)) {
										return false;
									}
								}
							}

							var1.setBlock(var3, var4 + 1, var5, Block.wood.blockID, 4, 2);
							var1.setBlock(var3 - 1, var4 + 1, var5, Block.wood.blockID, 4, 2);
							var1.setBlock(var3 + 1, var4 + 1, var5, Block.wood.blockID, 4, 2);
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}