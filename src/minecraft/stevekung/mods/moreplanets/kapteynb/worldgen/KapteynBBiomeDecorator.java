/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.worldgen;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.core.world.gen.GCCoreWorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.kapteynb.blocks.KapteynBBlocks;
import stevekung.mods.moreplanets.kapteynb.util.KapteynBConfigManager;

public class KapteynBBiomeDecorator
{
	protected World worldObj;
	protected Random randomGenerator;

	protected int chunkX;
	protected int chunkZ;

	protected WorldGenerator dirtGen;
	protected WorldGenerator iridiumGen;
	protected WorldGenerator copperGen;
	protected WorldGenerator tinGen;
	protected WorldGenerator frozenIronGen;
	protected WorldGenerator elementiumGen;
	protected WorldGenerator uraniumGen;
	protected WorldGenerator diamondGen;
	protected WorldGenerator goldGen;
	protected WorldGenerator redstoneGen;
	protected WorldGenerator iceGen;

	public KapteynBBiomeDecorator(BiomeGenBase par1BiomeGenBase)
	{
		this.iridiumGen = new GCCoreWorldGenMinableMeta(KapteynBBlocks.kapteynBBasicBlock.blockID, 6, 6, true, KapteynBBlocks.kapteynBBasicBlock.blockID, 2);
		this.tinGen = new GCCoreWorldGenMinableMeta(KapteynBBlocks.kapteynBBasicBlock.blockID, 6, 10, true, KapteynBBlocks.kapteynBBasicBlock.blockID, 2);
		this.copperGen = new GCCoreWorldGenMinableMeta(KapteynBBlocks.kapteynBBasicBlock.blockID, 6, 11, true, KapteynBBlocks.kapteynBBasicBlock.blockID, 2);
		this.dirtGen = new GCCoreWorldGenMinableMeta(KapteynBBlocks.kapteynBBasicBlock.blockID, 32, 1, true, KapteynBBlocks.kapteynBBasicBlock.blockID, 2);
		this.frozenIronGen = new GCCoreWorldGenMinableMeta(KapteynBBlocks.kapteynBBasicBlock.blockID, 3, 8, true, KapteynBBlocks.kapteynBBasicBlock.blockID, 2);
		this.elementiumGen = new GCCoreWorldGenMinableMeta(KapteynBBlocks.kapteynBBasicBlock.blockID, 2, 7, true, KapteynBBlocks.kapteynBBasicBlock.blockID, 2);
		this.uraniumGen = new GCCoreWorldGenMinableMeta(KapteynBBlocks.kapteynBBasicBlock.blockID, 1, 9, true, KapteynBBlocks.kapteynBBasicBlock.blockID, 2);
		this.diamondGen = new GCCoreWorldGenMinableMeta(KapteynBBlocks.kapteynBBasicBlock.blockID, 4, 12, true, KapteynBBlocks.kapteynBBasicBlock.blockID, 2);
		this.goldGen = new GCCoreWorldGenMinableMeta(KapteynBBlocks.kapteynBBasicBlock.blockID, 4, 13, true, KapteynBBlocks.kapteynBBasicBlock.blockID, 2);
		this.redstoneGen = new GCCoreWorldGenMinableMeta(KapteynBBlocks.kapteynBBasicBlock.blockID, 6, 14, true, KapteynBBlocks.kapteynBBasicBlock.blockID, 2);
		this.iceGen = new GCCoreWorldGenMinableMeta(KapteynBBlocks.kapteynBIce.blockID, 12, 0, true, KapteynBBlocks.kapteynBBasicBlock.blockID, 0);
		this.iceGen = new GCCoreWorldGenMinableMeta(KapteynBBlocks.kapteynBIce.blockID, 12, 0, true, KapteynBBlocks.kapteynBBasicBlock.blockID, 2);
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
			this.generateKapteynB();
			this.worldObj = null;
			this.randomGenerator = null;
		}
	}

	protected void genStandardOre1(int amountPerChunk, WorldGenerator worldGenerator, int minY, int maxY)
	{
		for (int var5 = 0; var5 < amountPerChunk; ++var5)
		{
			final int var6 = this.chunkX + this.randomGenerator.nextInt(16);
			final int var7 = this.randomGenerator.nextInt(maxY - minY) + minY;
			final int var8 = this.chunkZ + this.randomGenerator.nextInt(16);
			worldGenerator.generate(this.worldObj, this.randomGenerator, var6, var7, var8);
		}
	}

	protected void generateKapteynB()
	{
		MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Pre(this.worldObj, this.randomGenerator, this.chunkX, this.chunkZ));

		if (KapteynBConfigManager.enableCopperGen)
		{
			this.genStandardOre1(16, this.copperGen, 0, 48);
		}
		if (KapteynBConfigManager.enableTinGen)
		{
			this.genStandardOre1(16, this.tinGen, 0, 48);
		}
		if (KapteynBConfigManager.enableDiamondGen)
		{
			this.genStandardOre1(6, this.diamondGen, 0, 16);
		}
		if (KapteynBConfigManager.enableGoldGen)
		{
			this.genStandardOre1(10, this.goldGen, 0, 24);
		}
		if (KapteynBConfigManager.enableRedstoneGen)
		{
			this.genStandardOre1(10, this.redstoneGen, 0, 18);
		}

		this.genStandardOre1(32, this.dirtGen, 0, 256);
		this.genStandardOre1(12, this.iridiumGen, 0, 32);
		this.genStandardOre1(12, this.frozenIronGen, 0, 24);
		this.genStandardOre1(8, this.elementiumGen, 24, 32);
		this.genStandardOre1(1, this.uraniumGen, 0, 54);
		this.genStandardOre1(32, this.iceGen, 0, 256);
		MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Post(this.worldObj, this.randomGenerator, this.chunkX, this.chunkZ));
	}
}