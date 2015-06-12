/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen;

import java.util.ArrayList;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.fronos.biomes.FronosBiomes;
import stevekung.mods.moreplanets.fronos.util.FronosConfigManager;

public class FronosBiomeLayerBiomes extends FronosBiomeLayer
{
	private int dimension = 0;

	private static ArrayList<BiomeGenBase> promisedBiomes = new ArrayList<BiomeGenBase>();

	public FronosBiomeLayerBiomes(long par1, FronosBiomeLayer par3GenLayer, int dim)
	{
		super(par1);
		this.parent = par3GenLayer;
		this.dimension = dim;

		//PROMISED BIOMES
		if (FronosBiomes.canyon.isPresent())
		{
			promisedBiomes.add(FronosBiomes.canyon.get());
		}
		if (FronosBiomes.cherryBlossomGrove.isPresent())
		{
			promisedBiomes.add(FronosBiomes.cherryBlossomGrove.get());
		}
		if (FronosBiomes.mesa.isPresent())
		{
			promisedBiomes.add(FronosBiomes.mesa.get());
		}
		if (FronosBiomes.testingBiome.isPresent())
		{
			promisedBiomes.add(FronosBiomes.testingBiome.get());
		}
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		int[] var6 = IntCache.getIntCache(par3 * par4);

		for (int var7 = 0; var7 < par4; ++var7)
		{
			for (int var8 = 0; var8 < par3; ++var8)
			{
				this.initChunkSeed(var8 + par1, var7 + par2);
				if (this.dimension == FronosConfigManager.idDimensionFronos) //PROMISED BIOMES
				{
					var6[var8 + var7 * par3] = promisedBiomes.get(this.nextInt(promisedBiomes.size())).biomeID;
				}
			}
		}
		return var6;
	}
}