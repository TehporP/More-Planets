/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.worldgen;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.core.world.gen.GCCoreWorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.koentus.blocks.KoentusBlocks;
import stevekung.mods.moreplanets.koentus.util.KoentusConfigManager;

public class KoentusBiomeDecorator
{
	protected World worldObj;
	protected Random randomGenerator;

	protected int chunkX;
	protected int chunkZ;

	protected WorldGenerator dirtGen;
	protected WorldGenerator tinGen;
	protected WorldGenerator whiteCrystalGen;
	protected WorldGenerator mineralGen;
	protected WorldGenerator fossilGen;
	protected WorldGenerator blueGemGen;
	protected WorldGenerator iceGen;
	protected WorldGenerator glowIceGen;
	protected WorldGenerator sludgeGen;

	public KoentusBiomeDecorator(BiomeGenBase par1BiomeGenBase)
	{
		// Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
		this.whiteCrystalGen = new GCCoreWorldGenMinableMeta(KoentusBlocks.koentusBasicBlock.blockID, 4, 2, true, KoentusBlocks.koentusBasicBlock.blockID, 8);
		this.tinGen = new GCCoreWorldGenMinableMeta(KoentusBlocks.koentusBasicBlock.blockID, 6, 0, true, KoentusBlocks.koentusBasicBlock.blockID, 8);
		this.mineralGen = new GCCoreWorldGenMinableMeta(KoentusBlocks.koentusBasicBlock.blockID, 2, 3, true, KoentusBlocks.koentusBasicBlock.blockID, 8);
		this.dirtGen = new GCCoreWorldGenMinableMeta(KoentusBlocks.koentusBasicBlock.blockID, 32, 7, true, KoentusBlocks.koentusBasicBlock.blockID, 8);
		this.fossilGen = new GCCoreWorldGenMinableMeta(KoentusBlocks.koentusBasicBlock.blockID, 1, 4, true, KoentusBlocks.koentusBasicBlock.blockID, 8);
		this.blueGemGen = new GCCoreWorldGenMinableMeta(KoentusBlocks.koentusBasicBlock.blockID, 4, 5, true, KoentusBlocks.koentusBasicBlock.blockID, 8);
		this.iceGen = new GCCoreWorldGenMinableMeta(KoentusBlocks.koentusIce.blockID, 16, 0, true, KoentusBlocks.koentusBasicBlock.blockID, 8);
		this.iceGen = new GCCoreWorldGenMinableMeta(KoentusBlocks.koentusIce.blockID, 16, 0, true, KoentusBlocks.koentusBasicBlock.blockID, 6);
		this.glowIceGen = new GCCoreWorldGenMinableMeta(KoentusBlocks.koentusIceGlow.blockID, 16, 0, true, KoentusBlocks.koentusBasicBlock.blockID, 6);
		this.glowIceGen = new GCCoreWorldGenMinableMeta(KoentusBlocks.koentusIceGlow.blockID, 16, 0, true, KoentusBlocks.koentusBasicBlock.blockID, 8);
		this.sludgeGen = new GCCoreWorldGenMinableMeta(KoentusBlocks.koentusSludgeBlock.blockID, 32, 0, true, KoentusBlocks.koentusBasicBlock.blockID, 8);
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
			this.generateKoentus();
			this.worldObj = null;
			this.randomGenerator = null;
		}
	}

	protected void genStandardOre(int amountPerChunk, WorldGenerator worldGenerator, int minY, int maxY)
	{
		for (int var5 = 0; var5 < amountPerChunk; ++var5)
		{
			final int var6 = this.chunkX + this.randomGenerator.nextInt(16);
			final int var7 = this.randomGenerator.nextInt(maxY - minY) + minY;
			final int var8 = this.chunkZ + this.randomGenerator.nextInt(16);
			worldGenerator.generate(this.worldObj, this.randomGenerator, var6, var7, var8);
		}
	}

	protected void generateKoentus()
	{
		MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Pre(this.worldObj, this.randomGenerator, this.chunkX, this.chunkZ));

		if (KoentusConfigManager.enableTinGen)
		{
			this.genStandardOre(18, this.tinGen, 0, 54);
		}

		this.genStandardOre(32, this.dirtGen, 0, 255);
		this.genStandardOre(6, this.whiteCrystalGen, 0, 16);
		this.genStandardOre(6, this.mineralGen, 0, 16);
		this.genStandardOre(10, this.fossilGen, 24, 48);
		this.genStandardOre(8, this.blueGemGen, 0, 40);
		this.genStandardOre(24, this.iceGen, 0, 255);
		this.genStandardOre(24, this.glowIceGen, 0, 255);
		this.genStandardOre(12, this.sludgeGen, 0, 255);
		MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Post(this.worldObj, this.randomGenerator, this.chunkX, this.chunkZ));
	}
}