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
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;

public class FronosWorldGenKelp extends WorldGenerator
{
	public FronosWorldGenKelp(boolean var1)
	{
		super(var1);
	}

	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		int var6 = var2.nextInt(10) + 3;
		int var7 = var2.nextInt(3) + 2;
		int var8 = var6 - var7;
		int var9 = 1;
		if (var4 >= 1 && var4 + var6 + 1 <= 256)
		{
			int var11;
			int var13;
			int var15;
			int var21;

			var11 = var1.getBlockId(var3, var4 - 1, var5);

			if ((var11 == Block.sand.blockID || var11 == FronosBlocks.fronosDirt.blockID || var11 == FronosBlocks.fronosSand.blockID) && var4 < 256 - var6 - 1)
			{
				if (var1.getBlockMaterial(var3, var4, var5) != Material.water)
				{
					return false;
				}

				var21 = var2.nextInt(2);
				var13 = 1;
				boolean var22 = false;
				int block;
				int var16;
				int var999 = 0;
				int block2;
				for (var15 = 0; var15 <= var8; ++var15)
				{
					var16 = var4 + var6 - var15;

					if (var21 >= var13)
					{
						var21 = var22 ? 1 : 0;
						var22 = true;
						++var13;

						if (var13 > var9)
						{
							var13 = var9;
						}
					}
					else
					{
						++var21;
					}
				}

				var15 = var2.nextInt(3);

				for (var16 = 0; var16 < var6 - var15; ++var16)
				{
					block = var1.getBlockId(var3, var4 + var16 + 2, var5);

					if (block == Block.waterStill.blockID || block == Block.waterMoving.blockID || block == FronosBlocks.mineralWaterFluidBlock.blockID)
					{
						this.setBlockAndMetadata(var1, var3, var4, var5, FronosBlocks.fronosCoral.blockID, 0);
						this.setBlockAndMetadata(var1, var3, var4 + var16, var5, FronosBlocks.fronosCoral.blockID, 1);
						++var999;
					}
				}

				block2 = var1.getBlockId(var3, var4 + var999 + 1, var5);

				if (block2 == Block.waterStill.blockID || block2 == Block.waterMoving.blockID || block2 == FronosBlocks.mineralWaterFluidBlock.blockID)
				{
					if (var999 == 0)
					{
						this.setBlockAndMetadata(var1, var3, var4, var5, FronosBlocks.fronosCoral.blockID, 3);
					}
					else
					{
						this.setBlockAndMetadata(var1, var3, var4, var5, FronosBlocks.fronosCoral.blockID, 0);
						this.setBlockAndMetadata(var1, var3, var4 + var999, var5, FronosBlocks.fronosCoral.blockID, 2);
					}
				}
				return true;
			}
		}
		return false;
	}
}