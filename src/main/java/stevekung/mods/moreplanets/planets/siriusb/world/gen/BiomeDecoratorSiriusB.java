/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.siriusb.world.gen;
//
//import net.minecraft.world.World;
//import net.minecraft.world.gen.feature.WorldGenerator;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
//import stevekung.mods.moreplanets.common.world.gen.feature.WorldGenLiquidLakes;
//import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
//
//public class BiomeDecoratorSiriusB extends BiomeDecoratorSpace
//{
//	private World world;
//	private WorldGenerator dirtGen;
//	private WorldGenerator cobblestoneGen;
//	private WorldGenerator diamondGen;
//	private WorldGenerator sulfurGen;
//	private WorldGenerator glowstoneGen;
//
//	private int lavaLakePerChunk;
//
//	public BiomeDecoratorSiriusB()
//	{
//		// Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
//		this.dirtGen = new WorldGenMinableMeta(SiriusBBlocks.sirius_b_block, 32, 1, true, SiriusBBlocks.sirius_b_block, 2);
//		this.cobblestoneGen = new WorldGenMinableMeta(SiriusBBlocks.sirius_b_block, 4, 3, true, SiriusBBlocks.sirius_b_block, 2);
//		this.sulfurGen = new WorldGenMinableMeta(SiriusBBlocks.sirius_b_block, 8, 4, true, SiriusBBlocks.sirius_b_block, 2);
//		this.diamondGen = new WorldGenMinableMeta(SiriusBBlocks.sirius_b_block, 8, 5, true, SiriusBBlocks.sirius_b_block, 2);
//		this.glowstoneGen = new WorldGenMinableMeta(SiriusBBlocks.sirius_b_block, 4, 6, true, SiriusBBlocks.sirius_b_block, 2);
//
//		this.lavaLakePerChunk = 8;
//	}
//
//	@Override
//	protected void setCurrentWorld(World world)
//	{
//		this.world = world;
//	}
//
//	@Override
//	protected World getCurrentWorld()
//	{
//		return this.world;
//	}
//
//	@Override
//	protected void decorate()
//	{
//		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.world, this.rand, this.chunkX, this.chunkZ));
//
//		this.generateOre(32, this.dirtGen, 0, 255);
//		this.generateOre(10, this.cobblestoneGen, 0, 128);
//		this.generateOre(12, this.sulfurGen, 0, 48);
//		this.generateOre(16, this.diamondGen, 0, 16);
//		this.generateOre(16, this.glowstoneGen, 0, 255);
//
//		int i;
//		int x;
//		int y;
//		int z;
//
//		for (i = 0; i < this.lavaLakePerChunk; ++i)
//		{
//			if (this.rand.nextInt(20) == 0)
//			{
//				x = this.chunkX + this.rand.nextInt(16) + 8;
//				y = this.rand.nextInt(32 - 16) + 16;
//				z = this.chunkZ + this.rand.nextInt(16) + 8;
//				new WorldGenLiquidLakes(SiriusBBlocks.sirius_lava).generate(this.world, this.rand, x, y, z);
//			}
//		}
//		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.world, this.rand, this.chunkX, this.chunkZ));
//	}
//}