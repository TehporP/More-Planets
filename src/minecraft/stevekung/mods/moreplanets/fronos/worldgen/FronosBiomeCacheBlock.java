/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen;

import net.minecraft.world.biome.BiomeGenBase;

public class FronosBiomeCacheBlock
{
	public float[] temperatureValues;
	public float[] rainfallValues;
	public BiomeGenBase[] biomes;
	public int xPosition;
	public int zPosition;
	public long lastAccessTime;
	final FronosBiomeCache theBiomeCache;

	public FronosBiomeCacheBlock(FronosBiomeCache par1BiomeCache, int par2, int par3)
	{
		this.theBiomeCache = par1BiomeCache;
		this.temperatureValues = new float[256];
		this.rainfallValues = new float[256];
		this.biomes = new BiomeGenBase[256];
		this.xPosition = par2;
		this.zPosition = par3;
		FronosBiomeCache.getChunkManager(par1BiomeCache).getTemperatures(this.temperatureValues, par2 << 4, par3 << 4, 16, 16);
		FronosBiomeCache.getChunkManager(par1BiomeCache).getRainfall(this.rainfallValues, par2 << 4, par3 << 4, 16, 16);
		FronosBiomeCache.getChunkManager(par1BiomeCache).getBiomeGenAt(this.biomes, par2 << 4, par3 << 4, 16, 16, false);
	}

	public BiomeGenBase getBiomeGenAt(int par1, int par2)
	{
		return this.biomes[par1 & 15 | (par2 & 15) << 4];
	}
}