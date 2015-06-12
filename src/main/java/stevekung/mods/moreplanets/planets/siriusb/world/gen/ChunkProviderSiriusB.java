/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.siriusb.world.gen;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockFalling;
//import net.minecraft.entity.EnumCreatureType;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.BlockPos;
//import net.minecraft.util.IProgressUpdate;
//import net.minecraft.world.World;
//import net.minecraft.world.biome.BiomeGenBase;
//import net.minecraft.world.chunk.Chunk;
//import net.minecraft.world.chunk.IChunkProvider;
//import net.minecraft.world.gen.ChunkProviderGenerate;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.terraingen.PopulateChunkEvent;
//import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType;
//import net.minecraftforge.event.terraingen.TerrainGen;
//import stevekung.mods.moreplanets.common.world.gen.feature.WorldGenSplashBlock;
//import stevekung.mods.moreplanets.planets.siriusb.blocks.BlockSiriusB;
//import stevekung.mods.moreplanets.planets.siriusb.blocks.SiriusBBlocks;
//import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusBlaze;
//import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusCreeper;
//import stevekung.mods.moreplanets.planets.siriusb.entities.EntitySiriusMagmaCube;
//
//public class ChunkProviderSiriusB extends ChunkProviderGenerate
//{
//	Block topBlockID = SiriusBBlocks.sirius_b_block;
//	byte topBlockMeta = 0;
//	Block fillBlockID = SiriusBBlocks.sirius_b_block;
//	byte fillBlockMeta = 1;
//	Block lowerBlockID = SiriusBBlocks.sirius_b_block;
//	byte lowerBlockMeta = 2;
//
//	private Random rand;
//
//	private NoiseModule noiseGen1;
//	private NoiseModule noiseGen2;
//	private NoiseModule noiseGen3;
//	private NoiseModule noiseGen4;
//
//	public BiomeDecoratorSiriusB biomedecoratorplanet = new BiomeDecoratorSiriusB();
//
//	private MapGenSiriusBlazePit blazePit = new MapGenSiriusBlazePit();
//
//	private World worldObj;
//	private MapGenDungeon dungeonGenerator = new MapGenDungeon(SiriusBBlocks.sirius_b_block, 9, 8, 24, 4);
//
//	{
//		this.dungeonGenerator.otherRooms.add(new RoomEmptyMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomChestsSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomChestsSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.bossRooms.add(new RoomBossSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.treasureRooms.add(new RoomTreasureSiriusB(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//	}
//
//	private BiomeGenBase[] biomesForGeneration = { BiomeGenBaseSiriusB.siriusB };
//
//	// DO NOT CHANGE
//	private static int MID_HEIGHT = 64;
//	private static int CHUNK_SIZE_X = 16;
//	private static int CHUNK_SIZE_Y = 128;
//	private static int CHUNK_SIZE_Z = 16;
//
//	public ChunkProviderSiriusB(World par1World, long par2, boolean par4)
//	{
//		super(par1World, par2, par4, "sirius");
//		this.worldObj = par1World;
//		this.rand = new Random(par2);
//		this.noiseGen1 = new Gradient(this.rand.nextLong(), 4, 0.25F);
//		this.noiseGen2 = new Gradient(this.rand.nextLong(), 4, 0.25F);
//		this.noiseGen3 = new Gradient(this.rand.nextLong(), 1, 0.25F);
//		this.noiseGen4 = new Gradient(this.rand.nextLong(), 1, 0.25F);
//	}
//
//	public void generateTerrain(int chunkX, int chunkZ, Block[] idArray, byte[] metaArray)
//	{
//		this.noiseGen1.setFrequency(0.0125F);
//		this.noiseGen2.setFrequency(0.015F);
//		this.noiseGen3.setFrequency(0.01F);
//		this.noiseGen4.setFrequency(0.02F);
//
//		for (int x = 0; x < ChunkProviderSiriusB.CHUNK_SIZE_X; x++)
//		{
//			for (int z = 0; z < ChunkProviderSiriusB.CHUNK_SIZE_Z; z++)
//			{
//				double d = this.noiseGen1.getNoise(x + chunkX * 16, z + chunkZ * 16) * 8;
//				double d2 = this.noiseGen2.getNoise(x + chunkX * 16, z + chunkZ * 16) * 24;
//				double d3 = this.noiseGen3.getNoise(x + chunkX * 16, z + chunkZ * 16) - 0.1;
//				d3 *= 4;
//
//				double yDev = 0;
//
//				if (d3 < 0.0D)
//				{
//					yDev = d;
//				}
//				else if (d3 > 1.0D)
//				{
//					yDev = d2;
//				}
//				else
//				{
//					yDev = d + (d2 - d) * d3;
//				}
//
//				for (int y = 0; y < ChunkProviderSiriusB.CHUNK_SIZE_Y; y++)
//				{
//					if (y < ChunkProviderSiriusB.MID_HEIGHT + yDev)
//					{
//						idArray[this.getIndex(x, y, z)] = this.lowerBlockID;
//						metaArray[this.getIndex(x, y, z)] = this.lowerBlockMeta;
//					}
//				}
//			}
//		}
//	}
//
//	@Override
//	public void replaceBlocksForBiome(int par1, int par2, Block[] arrayOfIDs, byte[] arrayOfMeta, BiomeGenBase[] par4ArrayOfBiomeGenBase)
//	{
//		int var5 = 20;
//		for (int var8 = 0; var8 < 16; ++var8)
//		{
//			for (int var9 = 0; var9 < 16; ++var9)
//			{
//				int var12 = (int) (this.noiseGen4.getNoise(var8 + par1 * 16, var9 * par2 * 16) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
//				int var13 = -1;
//				Block var14 = this.topBlockID;
//				byte var14m = this.topBlockMeta;
//				Block var15 = this.fillBlockID;
//				byte var15m = this.fillBlockMeta;
//
//				for (int var16 = 127; var16 >= 0; --var16)
//				{
//					int index = this.getIndex(var8, var16, var9);
//					arrayOfMeta[index] = 0;
//
//					if (var16 <= 0 + this.rand.nextInt(5))
//					{
//						arrayOfIDs[index] = Blocks.bedrock;
//					}
//					else
//					{
//						Block var18 = arrayOfIDs[index];
//						if (Blocks.air == var18)
//						{
//							var13 = -1;
//						}
//						else if (var18 == this.lowerBlockID)
//						{
//							arrayOfMeta[index] = this.lowerBlockMeta;
//
//							if (var13 == -1)
//							{
//								if (var12 <= 0)
//								{
//									var14 = Blocks.air;
//									var14m = 0;
//									var15 = this.lowerBlockID;
//									var15m = this.lowerBlockMeta;
//								}
//								else if (var16 >= var5 - -16 && var16 <= var5 + 1)
//								{
//									var14 = this.topBlockID;
//									var14m = this.topBlockMeta;
//									var14 = this.fillBlockID;
//									var14m = this.fillBlockMeta;
//								}
//
//								var13 = var12;
//
//								if (var16 >= var5 - 1)
//								{
//									arrayOfIDs[index] = var14;
//									arrayOfMeta[index] = var14m;
//								}
//								else if (var16 < var5 - 1 && var16 >= var5 - 2)
//								{
//									arrayOfIDs[index] = var15;
//									arrayOfMeta[index] = var15m;
//								}
//							}
//							else if (var13 > 0)
//							{
//								--var13;
//								arrayOfIDs[index] = var15;
//								arrayOfMeta[index] = var15m;
//							}
//						}
//					}
//				}
//			}
//		}
//	}
//
//	@Override
//	public Chunk provideChunk(int x, int z)
//	{
//		this.rand.setSeed(x * 341873128712L + z * 132897987541L);
//		Arrays.fill(ids, Blocks.air);
//		this.generateTerrain(x, z, ids, meta);
//		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);
//		this.replaceBlocksForBiome(x, z, ids, meta, this.biomesForGeneration);
//		this.dungeonGenerator.generateUsingArrays(this.worldObj, this.worldObj.getSeed(), x * 16, 25, z * 16, x, z, ids, meta);
//		this.blazePit.generate(this, this.worldObj, x, z, ids, meta);
//		Chunk var4 = new Chunk(this.worldObj, x, z);
//		var4.generateSkylightMap();
//		return var4;
//	}
//
//	@Override
//	public boolean chunkExists(int par1, int par2)
//	{
//		return true;
//	}
//
//	@Override
//	public boolean unloadQueuedChunks()
//	{
//		return false;
//	}
//
//	@Override
//	public int getLoadedChunkCount()
//	{
//		return 0;
//	}
//
//	private int getIndex(int x, int y, int z)
//	{
//		return (x * 16 + z) * 256 + y;
//	}
//
//	public void decoratePlanet(World par1World, Random par2Random, int par3, int par4)
//	{
//		this.biomedecoratorplanet.decorate(par1World, par2Random, par3, par4);
//	}
//
//	@Override
//	public void populate(IChunkProvider chunk, int x, int z)
//	{
//		BlockFalling.fallInstantly = true;
//		int x1 = x * 16;
//		int z1 = z * 16;
//		BlockPos pos = new BlockPos(x1, 0, z1);
//		this.worldObj.getBiomeGenForCoords(pos.add(16, 0, 16));
//		this.rand.setSeed(this.worldObj.getSeed());
//		long var7 = this.rand.nextLong() / 2L * 2L + 1L;
//		long var9 = this.rand.nextLong() / 2L * 2L + 1L;
//		this.rand.setSeed(x * var7 + z * var9 ^ this.worldObj.getSeed());
//		this.dungeonGenerator.handleTileEntities(this.rand);
//		this.blazePit.generateStructuresInChunk(this.worldObj, new Random(), x, z);
//		this.decoratePlanet(this.worldObj, this.rand, x1, z1);
//		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(chunk, this.worldObj, this.rand, x, z, false));
//
//		boolean doGen = TerrainGen.populate(chunk, this.worldObj, this.rand, x, z, false, EventType.FIRE);
//
//		//Spot Gen
//		for (int i = 0; doGen && i < 4; i++)
//		{
//			if (this.rand.nextInt(5) == 0)
//			{
//				int x2 = x1 + this.rand.nextInt(16) + 8;
//				int y2 = this.rand.nextInt(128 - 32) + 32;
//				int z2 = z1 + this.rand.nextInt(16) + 8;
//				new WorldGenSplashBlock(SiriusBBlocks.sirius_b_block.getDefaultState().withProperty(BlockSiriusB.VARIANT, BlockSiriusB.BlockType.sirius_spot), SiriusBBlocks.sirius_b_block.getDefaultState()).generate(this.worldObj, this.rand, pos.add(x2, y2, z2));
//			}
//		}
//		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(chunk, this.worldObj, this.rand, x, z, false));
//		BlockFalling.fallInstantly = false;
//	}
//
//	@Override
//	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
//	{
//		return true;
//	}
//
//	@Override
//	public boolean canSave()
//	{
//		return true;
//	}
//
//	@Override
//	public String makeString()
//	{
//		return "SiriusBLevelSource";
//	}
//
//	@Override
//	public List func_177458_a(EnumCreatureType type, BlockPos pos)
//	{
//		if (type == EnumCreatureType.MONSTER)
//		{
//			List monsters = new ArrayList();
//			monsters.add(new BiomeGenBase.SpawnListEntry(EntitySiriusBlaze.class, 100, 4, 4));
//			monsters.add(new BiomeGenBase.SpawnListEntry(EntitySiriusCreeper.class, 100, 4, 4));
//			monsters.add(new BiomeGenBase.SpawnListEntry(EntitySiriusMagmaCube.class, 100, 4, 4));
//			return monsters;
//		}
//		return null;
//	}
//
//	@Override
//	public void recreateStructures(Chunk chunk, int x, int z)
//	{
//	}
//}