/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen.feature;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;

public class FronosWorldGenCandyCane2 extends WorldGenerator
{
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		while (var1.isAirBlock(var3, var4, var5)&& var4 > 2)
		{
			--var4;
		}

		int var6 = var1.getBlockId(var3, var4, var5);

		if (var6 != FronosBlocks.fronosGrass.blockID)
		{
			return false;
		}
		else
		{
			for (int var7 = -2; var7 <= 2; ++var7)
			{
				for (int var8 = -2; var8 <= 2; ++var8)
				{
					if (!var1.isAirBlock(var3 + var7, var4 + 1, var5 + var8)) {
						return false;
					}
				}
			}

			int var999 = var2.nextInt(2);
			int var1000 = var2.nextInt(4);

			if (var999 == 0)
			{
				var1.setBlock(var3, var4 + 1, var5, FronosBlocks.candyCane2.blockID, 0, 2);
				var1.setBlock(var3, var4 + 2, var5, FronosBlocks.candyCane2.blockID, 0, 2);
				var1.setBlock(var3, var4 + 3, var5, FronosBlocks.candyCane2.blockID, 1, 2);
				var1.setBlock(var3, var4 + 4, var5, FronosBlocks.candyCane2.blockID, 1, 2);
				var1.setBlock(var3, var4 + 5, var5, FronosBlocks.candyCane2.blockID, 2, 2);
				var1.setBlock(var3, var4 + 6, var5, FronosBlocks.candyCane2.blockID, 2, 2);
				var1.setBlock(var3, var4 + 7, var5, FronosBlocks.candyCane2.blockID, 3, 2);
				var1.setBlock(var3, var4 + 8, var5, FronosBlocks.candyCane2.blockID, 3, 2);
				var1.setBlock(var3, var4 + 9, var5, FronosBlocks.vanillaCream.blockID, 0, 2);
				var1.setBlock(var3, var4 + 10, var5, FronosBlocks.jellyBlock.blockID, 1, 2);
			}
			else if (var1000 == 0)
			{
				var1.setBlock(var3, var4 + 1, var5, FronosBlocks.candyCane2.blockID, 0, 2);
				var1.setBlock(var3, var4 + 2, var5, FronosBlocks.candyCane2.blockID, 0, 2);
				var1.setBlock(var3, var4 + 3, var5, FronosBlocks.candyCane2.blockID, 1, 2);
				var1.setBlock(var3, var4 + 4, var5, FronosBlocks.candyCane2.blockID, 1, 2);
				var1.setBlock(var3, var4 + 5, var5, FronosBlocks.candyCane2.blockID, 2, 2);
				var1.setBlock(var3, var4 + 6, var5, FronosBlocks.candyCane2.blockID, 2, 2);
				var1.setBlock(var3, var4 + 7, var5, FronosBlocks.candyCane2.blockID, 3, 2);
				var1.setBlock(var3, var4 + 8, var5, FronosBlocks.candyCane2.blockID, 3, 2);
				var1.setBlock(var3, var4 + 9, var5, FronosBlocks.chocolateCream.blockID, 0, 2);
				var1.setBlock(var3, var4 + 10, var5, FronosBlocks.jellyBlock.blockID, 2, 2);
			}
			return true;
		}
	}
}