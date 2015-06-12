/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.worldgen;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.core.world.gen.GCCoreWorldGenMinableMeta;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.polongnius.blocks.PolongniusBlocks;
import stevekung.mods.moreplanets.polongnius.util.PolongniusConfigManager;

public class PolongniusBiomeDecorator
{
	protected World worldObj;
	protected Random randomGenerator;

	protected int chunkX;
	protected int chunkZ;

	protected WorldGenerator dirtGen;
	protected WorldGenerator copperGen;
	protected WorldGenerator tinGen;
	protected WorldGenerator ironGen;
	protected WorldGenerator palladiumGen;
	protected WorldGenerator floniumGen;
	protected WorldGenerator cheeseGen;
	protected WorldGenerator meteorGen;
	protected WorldGenerator cheeseOfMilkGen;

	// Block,NumberOfBlock,Meta,FillBlock,FillMeta
	public PolongniusBiomeDecorator(BiomeGenBase par1BiomeGenBase)
	{
		this.copperGen = new GCCoreWorldGenMinableMeta(PolongniusBlocks.polongniusBasicBlock.blockID, 6, 0, true, PolongniusBlocks.polongniusBasicBlock.blockID, 9);
		this.tinGen = new GCCoreWorldGenMinableMeta(PolongniusBlocks.polongniusBasicBlock.blockID, 6, 1, true, PolongniusBlocks.polongniusBasicBlock.blockID, 9);
		this.cheeseGen = new GCCoreWorldGenMinableMeta(PolongniusBlocks.polongniusBasicBlock.blockID, 6, 5, true, PolongniusBlocks.polongniusBasicBlock.blockID, 9);
		this.dirtGen = new GCCoreWorldGenMinableMeta(PolongniusBlocks.polongniusBasicBlock.blockID, 32, 8, true, PolongniusBlocks.polongniusBasicBlock.blockID, 9);
		this.ironGen = new GCCoreWorldGenMinableMeta(PolongniusBlocks.polongniusBasicBlock.blockID, 6, 2, true, PolongniusBlocks.polongniusBasicBlock.blockID, 9);
		this.palladiumGen = new GCCoreWorldGenMinableMeta(PolongniusBlocks.polongniusBasicBlock.blockID, 8, 3, true, PolongniusBlocks.polongniusBasicBlock.blockID, 9);
		this.floniumGen = new GCCoreWorldGenMinableMeta(PolongniusBlocks.polongniusBasicBlock.blockID, 2, 4, true, PolongniusBlocks.polongniusBasicBlock.blockID, 9);
		this.meteorGen = new GCCoreWorldGenMinableMeta(PolongniusBlocks.polongniusBasicBlock.blockID, 2, 6, true, PolongniusBlocks.polongniusBasicBlock.blockID, 9);
		this.cheeseOfMilkGen = new GCCoreWorldGenMinableMeta(PolongniusBlocks.cheeseOfMilkFluidBlock.blockID, 8, 0, true, PolongniusBlocks.polongniusBasicBlock.blockID, 9);
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
			this.generatePolongnius();
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

	protected void generatePolongnius()
	{
		MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Pre(this.worldObj, this.randomGenerator, this.chunkX, this.chunkZ));

		if (PolongniusConfigManager.enableTinGen)
		{
			this.genStandardOre(20, this.tinGen, 0, 56);
		}
		if (PolongniusConfigManager.enableCopperGen)
		{
			this.genStandardOre(20, this.copperGen, 0, 56);
		}
		if (PolongniusConfigManager.enableIronGen)
		{
			this.genStandardOre(20, this.ironGen, 0, 48);
		}
		this.genStandardOre(24, this.dirtGen, 0, 256);
		this.genStandardOre(12, this.cheeseGen, 0, 24);
		this.genStandardOre(12, this.palladiumGen, 0, 24);
		this.genStandardOre(12, this.floniumGen, 0, 16);
		this.genStandardOre(6, this.meteorGen, 0, 32);
		this.genStandardOre(8, this.cheeseOfMilkGen, 0, 32);
		MinecraftForge.EVENT_BUS.post(new GCCoreEventPopulate.Post(this.worldObj, this.randomGenerator, this.chunkX, this.chunkZ));
	}
}