/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.worldgen;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.core.world.gen.GCCoreWorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.nibiru.blocks.NibiruBlocks;
import stevekung.mods.moreplanets.nibiru.util.NibiruConfigManager;
import stevekung.mods.moreplanets.nibiru.worldgen.feature.NibiruWorldGenSplashTopBlock;
import stevekung.mods.moreplanets.nibiru.worldgen.tree.NibiruWorldGenAppleTree;
import stevekung.mods.moreplanets.nibiru.worldgen.tree.NibiruWorldGenOrangeTree;

public class NibiruBiomeDecorator
{
	public World worldObj;
	public Random randomGenerator;

	public int chunkX;
	public int chunkZ;

	public int oilPerChunk1;

	public int infectedGrassPerChunk;

	public int appleTreePerChunk;
	public int orangeTreePerChunk;

	public WorldGenerator dirtGen;
	public WorldGenerator gemGen;
	public WorldGenerator coalGen;
	public WorldGenerator diamondGen;
	public WorldGenerator ichoriusGen;
	public WorldGenerator noriumGen;
	public WorldGenerator heliumGen;
	public WorldGenerator oilOreGen;
	public WorldGenerator wormEggGen;

	public WorldGenerator oilStoneGen1;

	public WorldGenerator infectedGrassGen;

	public WorldGenerator appleTreeGen;
	public WorldGenerator orangeTreeGen;

	// Block,NumberOfBlock,Meta,FillBlock,FillMeta
	public NibiruBiomeDecorator(BiomeGenBase par1BiomeGenBase)
	{
		this.gemGen = new GCCoreWorldGenMinableMeta(NibiruBlocks.nibiruBasicBlock.blockID, 2, 4, true, NibiruBlocks.nibiruBasicBlock.blockID, 7);
		this.dirtGen = new GCCoreWorldGenMinableMeta(NibiruBlocks.nibiruBasicBlock.blockID, 32, 6, true, NibiruBlocks.nibiruBasicBlock.blockID, 7);
		this.coalGen = new GCCoreWorldGenMinableMeta(NibiruBlocks.nibiruBasicBlock.blockID, 12, 3, true, NibiruBlocks.nibiruBasicBlock.blockID, 7);
		this.diamondGen = new GCCoreWorldGenMinableMeta(NibiruBlocks.nibiruBasicBlock.blockID, 3, 2, true, NibiruBlocks.nibiruBasicBlock.blockID, 7);
		this.ichoriusGen = new GCCoreWorldGenMinableMeta(NibiruBlocks.nibiruBasicBlock.blockID, 6, 0, true, NibiruBlocks.nibiruBasicBlock.blockID, 7);
		this.noriumGen = new GCCoreWorldGenMinableMeta(NibiruBlocks.nibiruBasicBlock.blockID, 4, 1, true, NibiruBlocks.nibiruBasicBlock.blockID, 7);
		this.heliumGen = new GCCoreWorldGenMinableMeta(NibiruBlocks.heliumBlock.blockID, 12, 0, true, NibiruBlocks.nibiruBasicBlock.blockID, 7);
		this.oilOreGen = new GCCoreWorldGenMinableMeta(NibiruBlocks.oilStone.blockID, 6, 1, true, NibiruBlocks.nibiruBasicBlock.blockID, 7);
		this.wormEggGen = new GCCoreWorldGenMinableMeta(NibiruBlocks.infectedWormEggStone.blockID, 1, 0, true, NibiruBlocks.nibiruBasicBlock.blockID, 7);

		this.oilStoneGen1 = new NibiruWorldGenSplashTopBlock(NibiruBlocks.oilStone.blockID, 0);
		this.infectedGrassGen = new NibiruWorldGenSplashTopBlock(NibiruBlocks.infectedGrass.blockID, 0);

		this.appleTreeGen = new NibiruWorldGenAppleTree();
		this.orangeTreeGen = new NibiruWorldGenOrangeTree();

		this.oilPerChunk1 = 1;
		this.infectedGrassPerChunk = 1;
		this.appleTreePerChunk = 1;
		this.orangeTreePerChunk = 1;
	}

	public void decorate(World worldObj, Random rand, int chunkX, int chunkZ)
	{
		if (this.worldObj != null)
		{
			throw new RuntimeException("Already decorating!!");
		}
		else
		{
			this.worldObj = worldObj;
			this.randomGenerator = rand;
			this.chunkX = chunkX;
			this.chunkZ = chunkZ;
			this.generateNibiru();
			this.worldObj = null;
			this.randomGenerator = null;
		}
	}

	protected void generateNibiru()
	{
		MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Pre(this.worldObj, this.randomGenerator, this.chunkX, this.chunkZ));

		if (NibiruConfigManager.enableDiamondGen)
		{
			this.genNibiruOre(6, this.diamondGen, 0, 16);
		}
		if (NibiruConfigManager.enableCoalGen)
		{
			this.genNibiruOre(12, this.coalGen, 0, 64);
		}

		this.genNibiruOre(32, this.dirtGen, 0, 256);
		this.genNibiruOre(6, this.gemGen, 0, 24);
		this.genNibiruOre(6, this.ichoriusGen, 0, 24);
		this.genNibiruOre(2, this.noriumGen, 0, 8);
		this.genNibiruOre(32, this.heliumGen, 0, 256);
		this.genNibiruOre(1, this.oilOreGen, 0, 256);
		this.genNibiruOre(2, this.wormEggGen, 0, 64);
		MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Post(this.worldObj, this.randomGenerator, this.chunkX, this.chunkZ));

		int var2;
		int var3;
		int var4;
		int var5;

		for (var2 = 0; var2 < this.oilPerChunk1; ++var2)
		{
			if (this.worldObj.rand.nextInt(20) == 0)
			{
				var3 = this.chunkX + this.randomGenerator.nextInt(16) + 8;
				var4 = this.randomGenerator.nextInt(256);
				var5 = this.chunkZ + this.randomGenerator.nextInt(16) + 8;
				this.oilStoneGen1.generate(this.worldObj, this.randomGenerator, var3, var4, var5);
			}
		}
		for (var2 = 0; var2 < this.infectedGrassPerChunk; ++var2)
		{
			if (this.worldObj.rand.nextInt(10) == 0)
			{
				var3 = this.chunkX + this.randomGenerator.nextInt(16) + 8;
				var4 = this.randomGenerator.nextInt(256);
				var5 = this.chunkZ + this.randomGenerator.nextInt(16) + 8;
				this.infectedGrassGen.generate(this.worldObj, this.randomGenerator, var3, var4, var5);
			}
		}
		for (var2 = 0; var2 < this.appleTreePerChunk; ++var2)
		{
			var3 = this.chunkX + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunkZ + this.randomGenerator.nextInt(16) + 8;
			this.appleTreeGen.generate(this.worldObj, this.randomGenerator, var3, var4, var5);

		}
		for (var2 = 0; var2 < this.orangeTreePerChunk; ++var2)
		{
			var3 = this.chunkX + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunkZ + this.randomGenerator.nextInt(16) + 8;
			this.orangeTreeGen.generate(this.worldObj, this.randomGenerator, var3, var4, var5);
		}
	}

	protected void genNibiruOre(int amountPerChunk, WorldGenerator worldGenerator, int minY, int maxY)
	{
		for (int var5 = 0; var5 < amountPerChunk; ++var5)
		{
			final int var6 = this.chunkX + this.randomGenerator.nextInt(16);
			final int var7 = this.randomGenerator.nextInt(maxY - minY) + minY;
			final int var8 = this.chunkZ + this.randomGenerator.nextInt(16);
			worldGenerator.generate(this.worldObj, this.randomGenerator, var6, var7, var8);
		}
	}
}