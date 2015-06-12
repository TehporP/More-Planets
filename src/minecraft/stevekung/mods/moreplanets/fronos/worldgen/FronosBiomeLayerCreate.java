/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen;

import net.minecraft.world.gen.layer.IntCache;

public class FronosBiomeLayerCreate extends FronosBiomeLayer
{
	public FronosBiomeLayerCreate(long par1, boolean o)
	{
		super(par1);
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		int[] var5 = IntCache.getIntCache(par3 * par4);

		for (int var6 = 0; var6 < par4; ++var6)
		{
			for (int var7 = 0; var7 < par3; ++var7)
			{
				this.initChunkSeed(par1 + var7, par2 + var6);
				var5[var7 + var6 * par3] = 1;
			}
		}

		if (par1 > -par3 && par1 <= 0 && par2 > -par4 && par2 <= 0)
		{
			var5[-par1 + -par2 * par3] = 1;
		}

		return var5;
	}
}