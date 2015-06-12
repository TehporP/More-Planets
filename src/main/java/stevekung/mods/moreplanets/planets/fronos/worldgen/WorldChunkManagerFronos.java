/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.fronos.worldgen;
//
//import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldChunkManagerSpace;
//import net.minecraft.world.biome.BiomeGenBase;
//
//public class WorldChunkManagerFronos extends WorldChunkManagerSpace
//{
//	@Override
//	public BiomeGenBase getBiome()
//	{
//		return BiomeGenBaseFronos.fronos;
//	}
//}

//package stevekung.mods.moreplanets.planets.fronos.worldgen;
//
//import static net.minecraft.world.biome.BiomeGenBase.forest;
//import static net.minecraft.world.biome.BiomeGenBase.forestHills;
//import static net.minecraft.world.biome.BiomeGenBase.jungle;
//import static net.minecraft.world.biome.BiomeGenBase.jungleHills;
//import static net.minecraft.world.biome.BiomeGenBase.plains;
//import static net.minecraft.world.biome.BiomeGenBase.taiga;
//import static net.minecraft.world.biome.BiomeGenBase.taigaHills;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//import net.minecraft.world.ChunkPosition;
//import net.minecraft.world.World;
//import net.minecraft.world.WorldType;
//import net.minecraft.world.biome.BiomeCache;
//import net.minecraft.world.biome.BiomeGenBase;
//import net.minecraft.world.biome.WorldChunkManager;
//import net.minecraft.world.gen.layer.GenLayer;
//import net.minecraft.world.gen.layer.IntCache;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//
//public class WorldChunkManagerFronos extends WorldChunkManager
//{
//	public static ArrayList<BiomeGenBase> allowedBiomes = new ArrayList<BiomeGenBase>(Arrays.asList(BiomeGenBaseFronos.fronos));
//	private GenLayer genBiomes;
//	private GenLayer biomeIndexLayer;
//	private BiomeCache biomeCache;
//	private List biomesToSpawnIn;
//
//	protected WorldChunkManagerFronos()
//	{
//		this.biomeCache = new BiomeCache(this);
//		this.biomesToSpawnIn = new ArrayList();
//		this.biomesToSpawnIn.addAll(allowedBiomes);
//	}
//
//	public WorldChunkManagerFronos(long p_i1975_1_, WorldType p_i1975_3_)
//	{
//		this();
//		GenLayer[] agenlayer = GenLayer.initializeAllBiomeGenerators(p_i1975_1_, p_i1975_3_);
//		agenlayer = this.getModdedBiomeGenerators(p_i1975_3_, p_i1975_1_, agenlayer);
//		this.genBiomes = agenlayer[0];
//		this.biomeIndexLayer = agenlayer[1];
//	}
//
//	public WorldChunkManagerFronos(World p_i1976_1_)
//	{
//		this(p_i1976_1_.getSeed(), p_i1976_1_.getWorldInfo().getTerrainType());
//	}
//
//	@Override
//	public List getBiomesToSpawnIn()
//	{
//		return this.biomesToSpawnIn;
//	}
//
//	@Override
//	public BiomeGenBase getBiomeGenAt(int p_76935_1_, int p_76935_2_)
//	{
//		return this.biomeCache.getBiomeGenAt(p_76935_1_, p_76935_2_);
//	}
//
//	@Override
//	public float[] getRainfall(float[] p_76936_1_, int p_76936_2_, int p_76936_3_, int p_76936_4_, int p_76936_5_)
//	{
//		IntCache.resetIntCache();
//
//		if (p_76936_1_ == null || p_76936_1_.length < p_76936_4_ * p_76936_5_)
//		{
//			p_76936_1_ = new float[p_76936_4_ * p_76936_5_];
//		}
//
//		int[] aint = this.biomeIndexLayer.getInts(p_76936_2_, p_76936_3_, p_76936_4_, p_76936_5_);
//
//		for (int i1 = 0; i1 < p_76936_4_ * p_76936_5_; ++i1)
//		{
//			float f = BiomeGenBase.getBiome(aint[i1]).getIntRainfall() / 65536.0F;
//
//			if (f > 1.0F)
//			{
//				f = 1.0F;
//			}
//
//			p_76936_1_[i1] = f;
//		}
//		return p_76936_1_;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public float getTemperatureAtHeight(float p_76939_1_, int p_76939_2_)
//	{
//		return p_76939_1_;
//	}
//
//	@Override
//	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] p_76937_1_, int p_76937_2_, int p_76937_3_, int p_76937_4_, int p_76937_5_)
//	{
//		IntCache.resetIntCache();
//
//		if (p_76937_1_ == null || p_76937_1_.length < p_76937_4_ * p_76937_5_)
//		{
//			p_76937_1_ = new BiomeGenBase[p_76937_4_ * p_76937_5_];
//		}
//
//		int[] aint = this.genBiomes.getInts(p_76937_2_, p_76937_3_, p_76937_4_, p_76937_5_);
//
//		for (int i1 = 0; i1 < p_76937_4_ * p_76937_5_; ++i1)
//		{
//			p_76937_1_[i1] = BiomeGenBase.getBiome(aint[i1]);
//		}
//		return p_76937_1_;
//	}
//
//	@Override
//	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] p_76933_1_, int p_76933_2_, int p_76933_3_, int p_76933_4_, int p_76933_5_)
//	{
//		return this.getBiomeGenAt(p_76933_1_, p_76933_2_, p_76933_3_, p_76933_4_, p_76933_5_, true);
//	}
//
//	@Override
//	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] p_76931_1_, int p_76931_2_, int p_76931_3_, int p_76931_4_, int p_76931_5_, boolean p_76931_6_)
//	{
//		IntCache.resetIntCache();
//
//		if (p_76931_1_ == null || p_76931_1_.length < p_76931_4_ * p_76931_5_)
//		{
//			p_76931_1_ = new BiomeGenBase[p_76931_4_ * p_76931_5_];
//		}
//
//		if (p_76931_6_ && p_76931_4_ == 16 && p_76931_5_ == 16 && (p_76931_2_ & 15) == 0 && (p_76931_3_ & 15) == 0)
//		{
//			BiomeGenBase[] abiomegenbase1 = this.biomeCache.getCachedBiomes(p_76931_2_, p_76931_3_);
//			System.arraycopy(abiomegenbase1, 0, p_76931_1_, 0, p_76931_4_ * p_76931_5_);
//			return p_76931_1_;
//		}
//		else
//		{
//			int[] aint = this.biomeIndexLayer.getInts(p_76931_2_, p_76931_3_, p_76931_4_, p_76931_5_);
//
//			for (int i1 = 0; i1 < p_76931_4_ * p_76931_5_; ++i1)
//			{
//				p_76931_1_[i1] = BiomeGenBase.getBiome(aint[i1]);
//			}
//			return p_76931_1_;
//		}
//	}
//
//	@Override
//	public boolean areBiomesViable(int p_76940_1_, int p_76940_2_, int p_76940_3_, List p_76940_4_)
//	{
//		IntCache.resetIntCache();
//		int l = p_76940_1_ - p_76940_3_ >> 2;
//			int i1 = p_76940_2_ - p_76940_3_ >> 2;
//		int j1 = p_76940_1_ + p_76940_3_ >> 2;
//		int k1 = p_76940_2_ + p_76940_3_ >> 2;
//		int l1 = j1 - l + 1;
//		int i2 = k1 - i1 + 1;
//		int[] aint = this.genBiomes.getInts(l, i1, l1, i2);
//
//		for (int j2 = 0; j2 < l1 * i2; ++j2)
//		{
//			BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[j2]);
//
//			if (!p_76940_4_.contains(biomegenbase))
//			{
//				return false;
//			}
//		}
//		return true;
//	}
//
//	@Override
//	public ChunkPosition findBiomePosition(int p_150795_1_, int p_150795_2_, int p_150795_3_, List p_150795_4_, Random p_150795_5_)
//	{
//		IntCache.resetIntCache();
//		int l = p_150795_1_ - p_150795_3_ >> 2;
//		int i1 = p_150795_2_ - p_150795_3_ >> 2;
//		int j1 = p_150795_1_ + p_150795_3_ >> 2;
//		int k1 = p_150795_2_ + p_150795_3_ >> 2;
//		int l1 = j1 - l + 1;
//		int i2 = k1 - i1 + 1;
//		int[] aint = this.genBiomes.getInts(l, i1, l1, i2);
//		ChunkPosition chunkposition = null;
//		int j2 = 0;
//
//		for (int k2 = 0; k2 < l1 * i2; ++k2)
//		{
//			int l2 = l + k2 % l1 << 2;
//			int i3 = i1 + k2 / l1 << 2;
//			BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k2]);
//
//			if (p_150795_4_.contains(biomegenbase) && (chunkposition == null || p_150795_5_.nextInt(j2 + 1) == 0))
//			{
//				chunkposition = new ChunkPosition(l2, 0, i3);
//				++j2;
//			}
//		}
//		return chunkposition;
//	}
//
//	@Override
//	public void cleanupCache()
//	{
//		this.biomeCache.cleanupCache();
//	}
//}

package stevekung.mods.moreplanets.planets.fronos.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldChunkManagerSpace;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import stevekung.mods.moreplanets.planets.fronos.worldgen.biome.BiomeGenBaseFronos;
import stevekung.mods.moreplanets.planets.fronos.worldgen.layer.GenLayerFronos;

public class WorldChunkManagerFronos extends WorldChunkManagerSpace
{
	private GenLayer unzoomedBiomes;
	private GenLayer zoomedBiomes;
	private BiomeCache myBiomeCache;
	private List<BiomeGenBase> myBiomesToSpawnIn;

	protected WorldChunkManagerFronos()
	{
		this.myBiomeCache = new BiomeCache(this);
		this.myBiomesToSpawnIn = new ArrayList();
		this.myBiomesToSpawnIn.add(BiomeGenBaseFronos.coconutForest);
		this.myBiomesToSpawnIn.add(BiomeGenBaseFronos.goldenField);
		this.myBiomesToSpawnIn.add(BiomeGenBaseFronos.purpleMapleForest);
	}

	public WorldChunkManagerFronos(long seed)
	{
		this();
		GenLayer[] agenlayer;
		agenlayer = GenLayerFronos.makeTheWorld(seed);
		this.unzoomedBiomes = agenlayer[0];
		this.zoomedBiomes = agenlayer[1];
	}

	public WorldChunkManagerFronos(World world)
	{
		this(world.getSeed());
	}

	@Override
	public BiomeGenBase getBiome()
	{
		return BiomeGenBaseFronos.coconutForest;
	}

	@Override
	public List getBiomesToSpawnIn()
	{
		return this.myBiomesToSpawnIn;
	}

	@Override
	public BiomeGenBase getBiomeGenAt(int par1, int par2)
	{
		BiomeGenBase biome = this.myBiomeCache.getBiomeGenAt(par1, par2);

		if (biome == null)
		{
			return BiomeGenBaseFronos.coconutForest;
		}
		return biome;
	}

	@Override
	public float[] getRainfall(float[] par1, int x, int z, int width, int depth)
	{
		IntCache.resetIntCache();
		int[] aint = this.zoomedBiomes.getInts(x, z, width, depth);

		if (par1 == null || par1.length < width * depth)
		{
			par1 = new float[width * depth];
		}
		for (int i1 = 0; i1 < width * depth; ++i1)
		{
			float f = BiomeGenBase.getBiome(aint[i1]).getIntRainfall() / 65536.0F;

			if (f > 1.0F)
			{
				f = 1.0F;
			}
			par1[i1] = f;
		}
		return par1;
	}

	@Override
	public float getTemperatureAtHeight(float par1, int par2)
	{
		return par1;
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int x, int z, int length, int width)
	{
		int[] arrayOfInts = this.unzoomedBiomes.getInts(x, z, length, width);

		if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < length * width)
		{
			par1ArrayOfBiomeGenBase = new BiomeGenBase[length * width];
		}
		for (int i = 0; i < length * width; i++)
		{
			if (arrayOfInts[i] >= 0)
			{
				par1ArrayOfBiomeGenBase[i] = BiomeGenBase.getBiome(arrayOfInts[i]);
			}
			else
			{
				par1ArrayOfBiomeGenBase[i] = BiomeGenBaseFronos.coconutForest;
			}
		}
		return par1ArrayOfBiomeGenBase;
	}

	@Override
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
	{
		return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
	}

	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int x, int y, int width, int length, boolean cacheFlag)
	{
		int[] ai = this.zoomedBiomes.getInts(x, y, width, length);

		if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < width * length)
		{
			par1ArrayOfBiomeGenBase = new BiomeGenBase[width * length];
		}
		if (cacheFlag && width == 16 && length == 16 && (x & 0xF) == 0 && (y & 0xF) == 0)
		{
			BiomeGenBase[] abiomegenbase = this.myBiomeCache.getCachedBiomes(x, y);
			System.arraycopy(abiomegenbase, 0, par1ArrayOfBiomeGenBase, 0, width * length);
			return par1ArrayOfBiomeGenBase;
		}
		for (int i = 0; i < width * length; i++)
		{
			if (ai[i] >= 0)
			{
				par1ArrayOfBiomeGenBase[i] = BiomeGenBase.getBiome(ai[i]);
			}
			else
			{
				par1ArrayOfBiomeGenBase[i] = BiomeGenBaseFronos.coconutForest;
			}
		}
		return par1ArrayOfBiomeGenBase;
	}

	@Override
	public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
	{
		int i = par1 - par3 >> 2;
		int j = par2 - par3 >> 2;
		int k = par1 + par3 >> 2;
		int l = par2 + par3 >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		int[] ai = this.unzoomedBiomes.getInts(i, j, i1, j1);

		for (int k1 = 0; k1 < i1 * j1; k1++)
		{
			BiomeGenBase biomegenbase = BiomeGenBase.getBiome(ai[k1]);

			if (!par4List.contains(biomegenbase))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random)
	{
		int i = par1 - par3 >> 2;
		int j = par2 - par3 >> 2;
		int k = par1 + par3 >> 2;
		int l = par2 + par3 >> 2;
		int i1 = k - i + 1;
		int j1 = l - j + 1;
		int[] ai = this.unzoomedBiomes.getInts(i, j, i1, j1);
		ChunkPosition chunkposition = null;
		int k1 = 0;

		for (int l1 = 0; l1 < ai.length; l1++)
		{
			int i2 = i + l1 % i1 << 2;
			int j2 = j + l1 / i1 << 2;
			BiomeGenBase biomegenbase = BiomeGenBase.getBiome(ai[l1]);

			if (par4List.contains(biomegenbase) && (chunkposition == null || par5Random.nextInt(k1 + 1) == 0))
			{
				chunkposition = new ChunkPosition(i2, 0, j2);
				k1++;
			}
		}
		return chunkposition;
	}

	@Override
	public void cleanupCache()
	{
		this.myBiomeCache.cleanupCache();
	}
}