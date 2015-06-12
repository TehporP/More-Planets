/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen.tree;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;

public class FronosWorldGenCoconutTree1 extends WorldGenerator
{
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		while (var1.isAirBlock(var3, var4, var5) && var4 > 2)
		{
			--var4;
		}

		int var6 = var1.getBlockId(var3, var4, var5);

		if (var6 != FronosBlocks.fronosGrass.blockID && var6 != FronosBlocks.fronosPinkGrass.blockID && var6 != FronosBlocks.fronosPlainsGrass.blockID && var6 != FronosBlocks.fronosPurpleGrass.blockID && var6 != FronosBlocks.goldenGrass.blockID)
		{
			return false;
		}
		else
		{
			for (int var7 = -2; var7 <= 2; ++var7)
			{
				for (int var8 = -2; var8 <= 2; ++var8)
				{
					if (var1.isAirBlock(var3 + var7, var4 - 1, var5 + var8) && var1.isAirBlock(var3 + var7, var4 - 2, var5 + var8) && !var1.isAirBlock(var3 + var7, var4, var5 + var8))
					{
						return false;
					}
				}
			}

			double strength = var2.nextInt(35) / 100D;
			double xoff = 0;
			double yoff = 0;
			int r = var2.nextInt(4);

			if (r == 0)
			{
				xoff = strength;
			}
			else if (r == 1)
			{
				xoff = -strength;
			}
			else if (r == 2)
			{
				yoff = strength;
			}
			else
			{
				yoff = -strength;
			}

			int h = 1;
			this.buildBlock (var1, var3, var4, var5, FronosBlocks.fronosDirt.blockID, 0);

			for (int b = 0; b < 10; b++)
			{
				this.buildBlock(var1, var3 + (int) Math.floor(xoff), var4 + h, var5 + (int) Math.floor(yoff), FronosBlocks.fronosBlockLog.blockID, 0);

				if (b == 9)
				{
					this.generateTop(var1, var3 + (int) Math.floor(xoff), var4 + h, var5 + (int) Math.floor(yoff));
				}
				else
				{
					h++;
					xoff *= 1.3D;
					yoff *= 1.3D;
				}
			}
			return true;
		}
	}

	public void generateTop(World world, int x, int y, int z)
	{
		this.buildBlock(world, x + 2, y - 1, z, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x - 2, y - 1, z, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x, y - 1, z + 2, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x, y - 1, z - 2, FronosBlocks.fronosColorizedLeaves.blockID, 0);

		this.buildBlock(world, x + 1, y, z, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x - 1, y, z, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x, y, z + 1, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x, y, z - 1, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x + 2, y, z + 2, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x - 2, y, z - 2, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x + 2, y, z - 2, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x - 2, y, z + 2, FronosBlocks.fronosColorizedLeaves.blockID, 0);

		this.buildBlock(world, x + 1, y + 1, z - 1, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x - 1, y + 1, z + 1, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x + 1, y + 1, z + 1, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x - 1, y + 1, z - 1, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x, y + 1, z, FronosBlocks.fronosColorizedLeaves.blockID, 0);

		this.buildBlock(world, x + 2, y + 2, z, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x - 2, y + 2, z, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x, y + 2, z + 2, FronosBlocks.fronosColorizedLeaves.blockID, 0);
		this.buildBlock(world, x, y + 2, z - 2, FronosBlocks.fronosColorizedLeaves.blockID, 0);
	}

	public void buildBlock(World world, int x, int y, int z, int id, int meta)
	{
		Material m = world.getBlockMaterial(x, y, z);

		if (m == Material.air || m == Material.leaves || m == Material.vine || m == Material.plants)
		{
			world.setBlock(x, y, z, id, meta, 2);
		}
	}
}