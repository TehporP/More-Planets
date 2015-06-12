/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.LongHashMap;
import net.minecraft.world.biome.BiomeGenBase;

public class FronosBiomeCache
{
	private final FronosWorldChunkManager chunkManager;
	private long lastCleanupTime = 0L;
	private LongHashMap cacheMap = new LongHashMap();

	@SuppressWarnings("rawtypes")
	private List cache = new ArrayList();

	public FronosBiomeCache(FronosWorldChunkManager par1WorldChunkManager)
	{
		this.chunkManager = par1WorldChunkManager;
	}

	@SuppressWarnings("unchecked")
	public FronosBiomeCacheBlock getBiomeCacheBlock(int par1, int par2)
	{
		par1 >>= 4;
		par2 >>= 4;
		long var3 = par1 & 4294967295L | (par2 & 4294967295L) << 32;
		FronosBiomeCacheBlock var5 = (FronosBiomeCacheBlock)this.cacheMap.getValueByKey(var3);

		if (var5 == null)
		{
			var5 = new FronosBiomeCacheBlock(this, par1, par2);
			this.cacheMap.add(var3, var5);
			this.cache.add(var5);
		}
		var5.lastAccessTime = System.currentTimeMillis();
		return var5;
	}

	public BiomeGenBase getBiomeGenAt(int par1, int par2)
	{
		return this.getBiomeCacheBlock(par1, par2).getBiomeGenAt(par1, par2);
	}

	public void cleanupCache()
	{
		long var1 = System.currentTimeMillis();
		long var3 = var1 - this.lastCleanupTime;

		if (var3 > 7500L || var3 < 0L)
		{
			this.lastCleanupTime = var1;

			for (int var5 = 0; var5 < this.cache.size(); ++var5)
			{
				FronosBiomeCacheBlock var6 = (FronosBiomeCacheBlock)this.cache.get(var5);
				long var7 = var1 - var6.lastAccessTime;

				if (var7 > 30000L || var7 < 0L)
				{
					this.cache.remove(var5--);
					long var9 = var6.xPosition & 4294967295L | (var6.zPosition & 4294967295L) << 32;
					this.cacheMap.remove(var9);
				}
			}
		}
	}

	public BiomeGenBase[] getCachedBiomes(int par1, int par2)
	{
		return this.getBiomeCacheBlock(par1, par2).biomes;
	}

	static FronosWorldChunkManager getChunkManager(FronosBiomeCache par0BiomeCache)
	{
		return par0BiomeCache.chunkManager;
	}
}