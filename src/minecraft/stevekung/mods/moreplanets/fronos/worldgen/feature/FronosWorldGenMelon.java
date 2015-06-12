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

public class FronosWorldGenMelon extends WorldGenerator
{
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		for (int var6 = 0; var6 < 64; ++var6)
		{
			int var7 = var3 + var2.nextInt(8) - var2.nextInt(8);
			int var8 = var4 + var2.nextInt(4) - var2.nextInt(4);
			int var9 = var5 + var2.nextInt(8) - var2.nextInt(8);

			if (var1.isAirBlock(var7, var8, var9) && var1.getBlockId(var7, var8 - 1, var9) == FronosBlocks.fronosGrass.blockID && Block.melon.canPlaceBlockAt(var1, var7, var8, var9))
			{
				var1.setBlock(var7, var8, var9, Block.melon.blockID);
			}
		}
		return true;
	}
}