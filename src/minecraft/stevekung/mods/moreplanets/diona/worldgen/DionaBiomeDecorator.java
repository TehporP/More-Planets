/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.worldgen;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.core.world.gen.GCCoreWorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.diona.blocks.DionaBlocks;
import stevekung.mods.moreplanets.diona.util.DionaConfigManager;

public class DionaBiomeDecorator
{
	protected World worldObj;
	protected Random randomGenerator;

	protected int chunkX;
	protected int chunkZ;

	protected WorldGenerator dirtGen;
	protected WorldGenerator quontoniumGen;
	protected WorldGenerator fronisiumGen;
	protected WorldGenerator aluminiumGen;
	protected WorldGenerator siliconGen;
	protected WorldGenerator baronsiumGen;

	public DionaBiomeDecorator(BiomeGenBase par1BiomeGenBase)
	{
		/**Block/NumberOfBLock/Meta/IsMeta/FillBlock/FillMeta**/
		this.quontoniumGen = new GCCoreWorldGenMinableMeta(DionaBlocks.dionaBasicBlock.blockID, 8, 0, true, DionaBlocks.dionaBasicBlock.blockID, 8);
		this.dirtGen = new GCCoreWorldGenMinableMeta(DionaBlocks.dionaBasicBlock.blockID, 32, 7, true, DionaBlocks.dionaBasicBlock.blockID, 8);
		this.siliconGen = new GCCoreWorldGenMinableMeta(DionaBlocks.dionaBasicBlock.blockID, 2, 1, true, DionaBlocks.dionaBasicBlock.blockID, 8);
		this.aluminiumGen = new GCCoreWorldGenMinableMeta(DionaBlocks.dionaBasicBlock.blockID, 8, 2, true, DionaBlocks.dionaBasicBlock.blockID, 8);
		this.baronsiumGen = new GCCoreWorldGenMinableMeta(DionaBlocks.dionaBasicBlock.blockID, 6, 4, true, DionaBlocks.dionaBasicBlock.blockID, 8);
		this.fronisiumGen = new GCCoreWorldGenMinableMeta(DionaBlocks.dionaBasicBlock.blockID, 6, 5, true, DionaBlocks.dionaBasicBlock.blockID, 8);
	}

	public void decorate(World worldObj, Random rand, int chunkX, int chunkZ)
	{
		if (this.worldObj != null)
		{
			throw new RuntimeException("Already decorating!");
		}
		else
		{
			this.worldObj = worldObj;
			this.randomGenerator = rand;
			this.chunkX = chunkX;
			this.chunkZ = chunkZ;
			this.generateDiona();
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

	protected void generateDiona()
	{
		MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Pre(this.worldObj, this.randomGenerator, this.chunkX, this.chunkZ));
		this.genStandardOre(32, this.dirtGen, 0, 256);

		if (DionaConfigManager.enableAluminumGen)
		{
			this.genStandardOre(16, this.aluminiumGen, 0, 48);
		}
		if (DionaConfigManager.enableSiliconGen)
		{
			this.genStandardOre(12, this.siliconGen, 0, 36);
		}
		this.genStandardOre(24, this.quontoniumGen, 0, 128);
		this.genStandardOre(16, this.baronsiumGen, 0, 64);
		this.genStandardOre(8, this.fronisiumGen, 0, 36);

		MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Post(this.worldObj, this.randomGenerator, this.chunkX, this.chunkZ));
	}
}