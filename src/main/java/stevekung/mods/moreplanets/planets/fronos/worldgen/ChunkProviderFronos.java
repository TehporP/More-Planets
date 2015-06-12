/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.fronos.worldgen;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
//import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
//import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
//import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
//import micdoodle8.mods.galacticraft.core.perlin.generator.Gradient;
//import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockFalling;
//import net.minecraft.entity.EnumCreatureType;
//import net.minecraft.entity.monster.EntityCreeper;
//import net.minecraft.entity.monster.EntityEnderman;
//import net.minecraft.entity.monster.EntitySkeleton;
//import net.minecraft.entity.monster.EntitySpider;
//import net.minecraft.entity.monster.EntityWitch;
//import net.minecraft.entity.monster.EntityZombie;
//import net.minecraft.entity.passive.EntityChicken;
//import net.minecraft.entity.passive.EntityCow;
//import net.minecraft.entity.passive.EntityPig;
//import net.minecraft.entity.passive.EntitySheep;
//import net.minecraft.entity.passive.EntitySquid;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.IProgressUpdate;
//import net.minecraft.world.World;
//import net.minecraft.world.biome.BiomeGenBase;
//import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
//import net.minecraft.world.chunk.Chunk;
//import net.minecraft.world.chunk.IChunkProvider;
//import net.minecraft.world.gen.ChunkProviderGenerate;
//import net.minecraftforge.common.util.ForgeDirection;
//import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
//import stevekung.mods.moreplanets.core.worldgen.dungeon.RoomEmptyMP;
//import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;
//import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityBearry;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityBerry;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamCat;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamSlime;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityGrappy;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityJellySlime;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityKiwi;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityLemonDuck;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityMarshmallow;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityMelon;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityStarfish;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityStrawberryChicken;
//import stevekung.mods.moreplanets.planets.fronos.entities.EntityTomato;
//import stevekung.mods.moreplanets.planets.fronos.worldgen.dungeon.RoomBossFronos;
//import stevekung.mods.moreplanets.planets.fronos.worldgen.dungeon.RoomChestsFronos;
//import stevekung.mods.moreplanets.planets.fronos.worldgen.dungeon.RoomSpawnerFronos;
//import stevekung.mods.moreplanets.planets.fronos.worldgen.dungeon.RoomTreasureFronos;
//import stevekung.mods.moreplanets.planets.fronos.worldgen.village.MapGenFronosVillage;
//
//public class ChunkProviderFronos extends ChunkProviderGenerate
//{
//	Block topBlockID = FronosBlocks.fronos_grass;
//	byte topBlockMeta = 0;
//	Block fillBlockID = FronosBlocks.fronos_dirt;
//	byte fillBlockMeta = 0;
//	Block lowerBlockID = FronosBlocks.fronos_block;
//	byte lowerBlockMeta = 0;
//
//	private Random rand;
//
//	private Gradient noiseGen1;
//	private Gradient noiseGen2;
//	private Gradient noiseGen3;
//	private Gradient noiseGen4;
//	private Gradient noiseGen5;
//	private Gradient noiseGen6;
//	private Gradient noiseGen7;
//
//	private World worldObj;
//	public BiomeDecoratorFronos biomedecoratorplanet = new BiomeDecoratorFronos();
//	private MapGenCaveFronos caveGenerator = new MapGenCaveFronos();
//	private MapGenCavernFronos cavernGenerator = new MapGenCavernFronos();
//	private MapGenFronosVillage villageGenerator = new MapGenFronosVillage();
//
//	private MapGenDungeon dungeonGenerator = new MapGenDungeon(FronosBlocks.fronos_block, 14, 8, 24, 4);
//	{
//		this.dungeonGenerator.otherRooms.add(new RoomEmptyMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomChestsFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.otherRooms.add(new RoomChestsFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.bossRooms.add(new RoomBossFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//		this.dungeonGenerator.treasureRooms.add(new RoomTreasureFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
//	}
//
//	private BiomeGenBase[] biomesForGeneration = { BiomeGenBaseFronos.fronos };
//
//	private static double TERRAIN_HEIGHT_MOD = 12;
//	private static double SMALL_FEATURE_HEIGHT_MOD = 16;
//	private static double MOUNTAIN_HEIGHT_MOD = 24;
//	private static double VALLEY_HEIGHT_MOD = 20;
//
//	// DO NOT CHANGE
//	private static int MID_HEIGHT = 64;
//	private static int CHUNK_SIZE_X = 16;
//	private static int CHUNK_SIZE_Y = 256;
//	private static int CHUNK_SIZE_Z = 16;
//	private static double MAIN_FEATURE_FILTER_MOD = 2;
//	private static double LARGE_FEATURE_FILTER_MOD = 2;
//	private static double SMALL_FEATURE_FILTER_MOD = 2;
//
//	public ChunkProviderFronos(World par1World, long par2, boolean par4)
//	{
//		super(par1World, par2, par4);
//		this.worldObj = par1World;
//		this.rand = new Random(par2);
//
//		this.noiseGen1 = new Gradient(this.rand.nextLong(), 4, 0.25F);
//		this.noiseGen2 = new Gradient(this.rand.nextLong(), 4, 0.25F);
//		this.noiseGen3 = new Gradient(this.rand.nextLong(), 4, 0.25F);
//		this.noiseGen4 = new Gradient(this.rand.nextLong(), 2, 0.25F);
//		this.noiseGen5 = new Gradient(this.rand.nextLong(), 1, 0.25F);
//		this.noiseGen6 = new Gradient(this.rand.nextLong(), 1, 0.25F);
//		this.noiseGen7 = new Gradient(this.rand.nextLong(), 1, 0.25F);
//	}
//
//	public void generateTerrain(int chunkX, int chunkZ, Block[] idArray, byte[] metaArray)
//	{
//		this.noiseGen1.setFrequency(0.0125F);
//		this.noiseGen2.setFrequency(0.015F);
//		this.noiseGen3.setFrequency(0.01F);
//		this.noiseGen4.setFrequency(0.02F);
//		this.noiseGen5.setFrequency(0.01F);
//		this.noiseGen6.setFrequency(0.001F);
//		this.noiseGen7.setFrequency(0.005F);
//
//		for (int x = 0; x < ChunkProviderFronos.CHUNK_SIZE_X; x++)
//		{
//			for (int z = 0; z < ChunkProviderFronos.CHUNK_SIZE_Z; z++)
//			{
//				double baseHeight = this.noiseGen1.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderFronos.TERRAIN_HEIGHT_MOD;
//				double smallHillHeight = this.noiseGen2.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderFronos.SMALL_FEATURE_HEIGHT_MOD;
//				double mountainHeight = Math.abs(this.noiseGen3.getNoise(chunkX * 16 + x, chunkZ * 16 + z));
//				double valleyHeight = Math.abs(this.noiseGen4.getNoise(chunkX * 16 + x, chunkZ * 16 + z));
//				double featureFilter = this.noiseGen5.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderFronos.MAIN_FEATURE_FILTER_MOD;
//				double largeFilter = this.noiseGen6.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderFronos.LARGE_FEATURE_FILTER_MOD;
//				double smallFilter = this.noiseGen7.getNoise(chunkX * 16 + x, chunkZ * 16 + z) * ChunkProviderFronos.SMALL_FEATURE_FILTER_MOD - 0.5;
//				mountainHeight = this.lerp(smallHillHeight, mountainHeight * ChunkProviderFronos.MOUNTAIN_HEIGHT_MOD, this.fade(this.clamp(mountainHeight * 2, 0, 1)));
//				valleyHeight = this.lerp(smallHillHeight, valleyHeight * ChunkProviderFronos.VALLEY_HEIGHT_MOD - ChunkProviderFronos.VALLEY_HEIGHT_MOD + 9, this.fade(this.clamp((valleyHeight + 2) * 4, 0, 1)));
//
//				double yDev = this.lerp(valleyHeight, mountainHeight, this.fade(largeFilter));
//				yDev = this.lerp(smallHillHeight, yDev, smallFilter);
//				yDev = this.lerp(baseHeight, yDev, featureFilter);
//
//				for (int y = 0; y < ChunkProviderFronos.CHUNK_SIZE_Y; y++)
//				{
//					if (y < ChunkProviderFronos.MID_HEIGHT + yDev)
//					{
//						idArray[this.getIndex(x, y, z)] = this.lowerBlockID;
//						metaArray[this.getIndex(x, y, z)] = this.lowerBlockMeta;
//					}
//				}
//			}
//		}
//	}
//
//	private double lerp(double d1, double d2, double t)
//	{
//		if (t < 0.0)
//		{
//			return d1;
//		}
//		else if (t > 1.0)
//		{
//			return d2;
//		}
//		else
//		{
//			return d1 + (d2 - d1) * t;
//		}
//	}
//
//	private double fade(double n)
//	{
//		return n * n * n * (n * (n * 6 - 15) + 10);
//	}
//
//	private double clamp(double x, double min, double max)
//	{
//		if (x < min)
//		{
//			return min;
//		}
//		if (x > max)
//		{
//			return max;
//		}
//		return x;
//	}
//
//	@Override
//	public void replaceBlocksForBiome(int par1, int par2, Block[] arrayOfIDs, byte[] arrayOfMeta, BiomeGenBase[] par4ArrayOfBiomeGenBase)
//	{
//		int var5 = 20;
//		float var6 = 0.03125F;
//		this.noiseGen4.setFrequency(var6 * 2);
//		for (int var8 = 0; var8 < 16; ++var8)
//		{
//			for (int var9 = 0; var9 < 16; ++var9)
//			{
//				int var12 = (int) (this.noiseGen4.getNoise(par1 * 16 + var8, par2 * 16 + var9) / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
//				int var13 = -1;
//				Block var14 = this.topBlockID;
//				byte var14m = this.topBlockMeta;
//				Block var15 = this.fillBlockID;
//				byte var15m = this.fillBlockMeta;
//
//				for (int var16 = ChunkProviderFronos.CHUNK_SIZE_Y - 1; var16 >= 0; --var16)
//				{
//					int index = this.getIndex(var8, var16, var9);
//
//					if (var16 <= 0 + this.rand.nextInt(5))
//					{
//						arrayOfIDs[index] = Blocks.bedrock;
//					}
//					else
//					{
//						Block var18 = arrayOfIDs[index];
//
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
//								else
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
//	public Chunk provideChunk(int par1, int par2)
//	{
//		this.rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
//		Block[] ids = new Block[32768 * 2];
//		byte[] meta = new byte[32768 * 2];
//		this.generateTerrain(par1, par2, ids, meta);
//		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
//		this.replaceBlocksForBiome(par1, par2, ids, meta, this.biomesForGeneration);
//		this.caveGenerator.generate(this, this.worldObj, par1, par2, ids, meta);
//		this.cavernGenerator.generate(this, this.worldObj, par1, par2, ids, meta);
//		this.dungeonGenerator.generateUsingArrays(this.worldObj, this.worldObj.getSeed(), par1 * 16, 30, par2 * 16, par1, par2, ids, meta);
//
//		Chunk var4 = new Chunk(this.worldObj, ids, meta, par1, par2);
//		byte[] var5 = var4.getBiomeArray();
//
//		for (int var6 = 0; var6 < var5.length; ++var6)
//		{
//			var5[var6] = (byte) this.biomesForGeneration[var6].biomeID;
//		}
//
//		var4.generateSkylightMap();
//		return var4;
//	}
//
//	private int getIndex(int x, int y, int z)
//	{
//		return (x * 16 + z) * 256 + y;
//	}
//
//	@Override
//	public boolean chunkExists(int par1, int par2)
//	{
//		return true;
//	}
//
//	public void decoratePlanet(World par1World, Random par2Random, int par3, int par4)
//	{
//		this.biomedecoratorplanet.decorate(par1World, par2Random, par3, par4);
//	}
//
//	@Override
//	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
//	{
//		BlockFalling.fallInstantly = true;
//		int var4 = par2 * 16;
//		int var5 = par3 * 16;
//		this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
//		this.rand.setSeed(this.worldObj.getSeed());
//		long var7 = this.rand.nextLong() / 2L * 2L + 1L;
//		long var9 = this.rand.nextLong() / 2L * 2L + 1L;
//		this.rand.setSeed(par2 * var7 + par3 * var9 ^ this.worldObj.getSeed());
//		this.dungeonGenerator.handleTileEntities(this.rand);
//		this.decoratePlanet(this.worldObj, this.rand, var4, var5);
//		this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
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
//		return "FronosLevelSource";
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Override
//	public List getPossibleCreatures(EnumCreatureType type, int i, int j, int k)
//	{
//		if (type == EnumCreatureType.monster)
//		{
//			List monsters = new ArrayList();
//
//			if (!ConfigManagerMP.allowMobCreatureSpawningOnFronos)
//			{
//				monsters.add(new SpawnListEntry(EntityEvolvedZombie.class, 100, 4, 4));
//				monsters.add(new SpawnListEntry(EntityEvolvedSpider.class, 100, 4, 4));
//				monsters.add(new SpawnListEntry(EntityEvolvedSkeleton.class, 100, 4, 4));
//				monsters.add(new SpawnListEntry(EntityEvolvedCreeper.class, 100, 4, 4));
//				monsters.add(new SpawnListEntry(EntityEvolvedEnderman.class, 100, 1, 4));
//			}
//			else
//			{
//				monsters.add(new SpawnListEntry(EntityZombie.class, 100, 4, 4));
//				monsters.add(new SpawnListEntry(EntitySpider.class, 100, 4, 4));
//				monsters.add(new SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
//				monsters.add(new SpawnListEntry(EntityCreeper.class, 100, 4, 4));
//				monsters.add(new SpawnListEntry(EntityEnderman.class, 100, 1, 4));
//				monsters.add(new SpawnListEntry(EntityWitch.class, 5, 1, 1));
//			}
//			monsters.add(new SpawnListEntry(EntityCreamSlime.class, 100, 4, 4));
//			monsters.add(new SpawnListEntry(EntityJellySlime.class, 100, 4, 4));
//			return monsters;
//		}
//		else if (type == EnumCreatureType.creature)
//		{
//			List creatures = new ArrayList();
//			creatures.add(new SpawnListEntry(EntityBearry.class, 8, 4, 4));
//			creatures.add(new SpawnListEntry(EntityBerry.class, 8, 4, 4));
//			creatures.add(new SpawnListEntry(EntityMarshmallow.class, 8, 4, 4));
//			creatures.add(new SpawnListEntry(EntityKiwi.class, 8, 4, 4));
//			creatures.add(new SpawnListEntry(EntityLemonDuck.class, 8, 4, 4));
//			creatures.add(new SpawnListEntry(EntityTomato.class, 8, 4, 4));
//			creatures.add(new SpawnListEntry(EntityMelon.class, 8, 4, 4));
//			creatures.add(new SpawnListEntry(EntityGrappy.class, 12, 4, 4));
//			creatures.add(new SpawnListEntry(EntityCreamCat.class, 2, 2, 2));
//			creatures.add(new SpawnListEntry(EntityStrawberryChicken.class, 10, 4, 4));
//			creatures.add(new SpawnListEntry(EntityStarfish.class, 8, 4, 4));
//
//			if (ConfigManagerMP.allowMobCreatureSpawningOnFronos == true)
//			{
//				creatures.add(new SpawnListEntry(EntitySheep.class, 12, 4, 4));
//				creatures.add(new SpawnListEntry(EntityPig.class, 10, 4, 4));
//				creatures.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
//				creatures.add(new SpawnListEntry(EntityCow.class, 8, 4, 4));
//			}
//			else
//			{
//				return null;
//			}
//			return creatures;
//		}
//		else if (type == EnumCreatureType.waterCreature)
//		{
//			List waterCreatures = new ArrayList();
//			waterCreatures.add(new SpawnListEntry(EntitySquid.class, 10, 4, 4));
//			return waterCreatures;
//		}
//		return null;
//	}
//
//	@Override
//	public void recreateStructures(int par1, int par2)
//	{
//		this.villageGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[]) null);
//	}
//}

//package stevekung.mods.moreplanets.planets.fronos.worldgen;
//
//import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE;
//import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT;
//import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE;
//import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE;
//import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD;
//import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.VILLAGE;
//import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS;
//import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.DUNGEON;
//import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE;
//import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE;
//import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAVA;
//
//import java.util.List;
//import java.util.Random;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockFalling;
//import net.minecraft.entity.EnumCreatureType;
//import net.minecraft.init.Blocks;
//import net.minecraft.util.IProgressUpdate;
//import net.minecraft.util.MathHelper;
//import net.minecraft.world.ChunkPosition;
//import net.minecraft.world.SpawnerAnimals;
//import net.minecraft.world.World;
//import net.minecraft.world.WorldType;
//import net.minecraft.world.biome.BiomeGenBase;
//import net.minecraft.world.chunk.Chunk;
//import net.minecraft.world.chunk.IChunkProvider;
//import net.minecraft.world.gen.ChunkProviderGenerate;
//import net.minecraft.world.gen.MapGenBase;
//import net.minecraft.world.gen.MapGenCaves;
//import net.minecraft.world.gen.MapGenRavine;
//import net.minecraft.world.gen.NoiseGenerator;
//import net.minecraft.world.gen.NoiseGeneratorOctaves;
//import net.minecraft.world.gen.NoiseGeneratorPerlin;
//import net.minecraft.world.gen.feature.WorldGenDungeons;
//import net.minecraft.world.gen.feature.WorldGenLakes;
//import net.minecraft.world.gen.structure.MapGenMineshaft;
//import net.minecraft.world.gen.structure.MapGenScatteredFeature;
//import net.minecraft.world.gen.structure.MapGenStronghold;
//import net.minecraft.world.gen.structure.MapGenVillage;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.terraingen.ChunkProviderEvent;
//import net.minecraftforge.event.terraingen.PopulateChunkEvent;
//import net.minecraftforge.event.terraingen.TerrainGen;
//import cpw.mods.fml.common.eventhandler.Event.Result;
//
//public class ChunkProviderFronos extends ChunkProviderGenerate
//{
//	private Random rand;
//	private NoiseGeneratorOctaves field_147431_j;
//	private NoiseGeneratorOctaves field_147432_k;
//	private NoiseGeneratorOctaves field_147429_l;
//	private NoiseGeneratorPerlin field_147430_m;
//	public NoiseGeneratorOctaves noiseGen5;
//	public NoiseGeneratorOctaves noiseGen6;
//	public NoiseGeneratorOctaves mobSpawnerNoise;
//	private World worldObj;
//	private boolean mapFeaturesEnabled;
//	private WorldType field_147435_p;
//	private double[] field_147434_q;
//	private float[] parabolicField;
//	private double[] stoneNoise = new double[256];
//	private MapGenBase caveGenerator = new MapGenCaves();
//	private MapGenStronghold strongholdGenerator = new MapGenStronghold();
//	private MapGenVillage villageGenerator = new MapGenVillage();
//	private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
//	private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
//	private MapGenBase ravineGenerator = new MapGenRavine();
//	private BiomeGenBase[] biomesForGeneration;
//	double[] field_147427_d;
//	double[] field_147428_e;
//	double[] field_147425_f;
//	double[] field_147426_g;
//	int[][] field_73219_j = new int[32][32];
//
//	{
//		this.caveGenerator = TerrainGen.getModdedMapGen(this.caveGenerator, CAVE);
//		this.strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(this.strongholdGenerator, STRONGHOLD);
//		this.villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(this.villageGenerator, VILLAGE);
//		this.mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(this.mineshaftGenerator, MINESHAFT);
//		this.scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(this.scatteredFeatureGenerator, SCATTERED_FEATURE);
//		this.ravineGenerator = TerrainGen.getModdedMapGen(this.ravineGenerator, RAVINE);
//	}
//
//	public ChunkProviderFronos(World p_i2006_1_, long p_i2006_2_, boolean p_i2006_4_)
//	{
//		super(p_i2006_1_, p_i2006_2_, p_i2006_4_);
//		this.worldObj = p_i2006_1_;
//		this.mapFeaturesEnabled = p_i2006_4_;
//		this.field_147435_p = p_i2006_1_.getWorldInfo().getTerrainType();
//		this.rand = new Random(p_i2006_2_);
//		this.field_147431_j = new NoiseGeneratorOctaves(this.rand, 16);
//		this.field_147432_k = new NoiseGeneratorOctaves(this.rand, 16);
//		this.field_147429_l = new NoiseGeneratorOctaves(this.rand, 8);
//		this.field_147430_m = new NoiseGeneratorPerlin(this.rand, 4);
//		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
//		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
//		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
//		this.field_147434_q = new double[825];
//		this.parabolicField = new float[25];
//
//		for (int j = -2; j <= 2; ++j)
//		{
//			for (int k = -2; k <= 2; ++k)
//			{
//				float f = 10.0F / MathHelper.sqrt_float(j * j + k * k + 0.2F);
//				this.parabolicField[j + 2 + (k + 2) * 5] = f;
//			}
//		}
//
//		NoiseGenerator[] noiseGens = {this.field_147431_j, this.field_147432_k, this.field_147429_l, this.field_147430_m, this.noiseGen5, this.noiseGen6, this.mobSpawnerNoise};
//		noiseGens = TerrainGen.getModdedNoiseGenerators(p_i2006_1_, this.rand, noiseGens);
//		this.field_147431_j = (NoiseGeneratorOctaves)noiseGens[0];
//		this.field_147432_k = (NoiseGeneratorOctaves)noiseGens[1];
//		this.field_147429_l = (NoiseGeneratorOctaves)noiseGens[2];
//		this.field_147430_m = (NoiseGeneratorPerlin)noiseGens[3];
//		this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
//		this.noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
//		this.mobSpawnerNoise = (NoiseGeneratorOctaves)noiseGens[6];
//	}
//
//	@Override
//	public void func_147424_a(int p_147424_1_, int p_147424_2_, Block[] p_147424_3_)
//	{
//		byte b0 = 63;
//		this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, p_147424_1_ * 4 - 2, p_147424_2_ * 4 - 2, 10, 10);
//		this.func_147423_a(p_147424_1_ * 4, 0, p_147424_2_ * 4);
//
//		for (int k = 0; k < 4; ++k)
//		{
//			int l = k * 5;
//			int i1 = (k + 1) * 5;
//
//			for (int j1 = 0; j1 < 4; ++j1)
//			{
//				int k1 = (l + j1) * 33;
//				int l1 = (l + j1 + 1) * 33;
//				int i2 = (i1 + j1) * 33;
//				int j2 = (i1 + j1 + 1) * 33;
//
//				for (int k2 = 0; k2 < 32; ++k2)
//				{
//					double d0 = 0.125D;
//					double d1 = this.field_147434_q[k1 + k2];
//					double d2 = this.field_147434_q[l1 + k2];
//					double d3 = this.field_147434_q[i2 + k2];
//					double d4 = this.field_147434_q[j2 + k2];
//					double d5 = (this.field_147434_q[k1 + k2 + 1] - d1) * d0;
//					double d6 = (this.field_147434_q[l1 + k2 + 1] - d2) * d0;
//					double d7 = (this.field_147434_q[i2 + k2 + 1] - d3) * d0;
//					double d8 = (this.field_147434_q[j2 + k2 + 1] - d4) * d0;
//
//					for (int l2 = 0; l2 < 8; ++l2)
//					{
//						double d9 = 0.25D;
//						double d10 = d1;
//						double d11 = d2;
//						double d12 = (d3 - d1) * d9;
//						double d13 = (d4 - d2) * d9;
//
//						for (int i3 = 0; i3 < 4; ++i3)
//						{
//							int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
//							short short1 = 256;
//							j3 -= short1;
//							double d14 = 0.25D;
//							double d16 = (d11 - d10) * d14;
//							double d15 = d10 - d16;
//
//							for (int k3 = 0; k3 < 4; ++k3)
//							{
//								if ((d15 += d16) > 0.0D)
//								{
//									p_147424_3_[j3 += short1] = Blocks.stone;
//								}
//								else if (k2 * 8 + l2 < b0)
//								{
//									p_147424_3_[j3 += short1] = Blocks.water;
//								}
//								else
//								{
//									p_147424_3_[j3 += short1] = null;
//								}
//							}
//
//							d10 += d12;
//							d11 += d13;
//						}
//
//						d1 += d5;
//						d2 += d6;
//						d3 += d7;
//						d4 += d8;
//					}
//				}
//			}
//		}
//	}
//
//	@Override
//	public void replaceBlocksForBiome(int p_147422_1_, int p_147422_2_, Block[] p_147422_3_, byte[] p_147422_4_, BiomeGenBase[] p_147422_5_)
//	{
//		ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, p_147422_1_, p_147422_2_, p_147422_3_, p_147422_4_, p_147422_5_, this.worldObj);
//		MinecraftForge.EVENT_BUS.post(event);
//		if (event.getResult() == Result.DENY) {
//			return;
//		}
//
//		double d0 = 0.03125D;
//		this.stoneNoise = this.field_147430_m.func_151599_a(this.stoneNoise, p_147422_1_ * 16, p_147422_2_ * 16, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
//
//		for (int k = 0; k < 16; ++k)
//		{
//			for (int l = 0; l < 16; ++l)
//			{
//				BiomeGenBase biomegenbase = p_147422_5_[l + k * 16];
//				biomegenbase.genTerrainBlocks(this.worldObj, this.rand, p_147422_3_, p_147422_4_, p_147422_1_ * 16 + k, p_147422_2_ * 16 + l, this.stoneNoise[l + k * 16]);
//			}
//		}
//	}
//
//	/**
//	 * loads or generates the chunk at the chunk location specified
//	 */
//	@Override
//	public Chunk loadChunk(int p_73158_1_, int p_73158_2_)
//	{
//		return this.provideChunk(p_73158_1_, p_73158_2_);
//	}
//
//	/**
//	 * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
//	 * specified chunk from the map seed and chunk seed
//	 */
//	@Override
//	public Chunk provideChunk(int p_73154_1_, int p_73154_2_)
//	{
//		this.rand.setSeed(p_73154_1_ * 341873128712L + p_73154_2_ * 132897987541L);
//		Block[] ablock = new Block[65536];
//		byte[] abyte = new byte[65536];
//		this.func_147424_a(p_73154_1_, p_73154_2_, ablock);
//		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
//		this.replaceBlocksForBiome(p_73154_1_, p_73154_2_, ablock, abyte, this.biomesForGeneration);
//		this.caveGenerator.func_151539_a(this, this.worldObj, p_73154_1_, p_73154_2_, ablock);
//		this.ravineGenerator.func_151539_a(this, this.worldObj, p_73154_1_, p_73154_2_, ablock);
//
//		if (this.mapFeaturesEnabled)
//		{
//			this.mineshaftGenerator.func_151539_a(this, this.worldObj, p_73154_1_, p_73154_2_, ablock);
//			this.villageGenerator.func_151539_a(this, this.worldObj, p_73154_1_, p_73154_2_, ablock);
//			this.strongholdGenerator.func_151539_a(this, this.worldObj, p_73154_1_, p_73154_2_, ablock);
//			this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, p_73154_1_, p_73154_2_, ablock);
//		}
//
//		Chunk chunk = new Chunk(this.worldObj, ablock, abyte, p_73154_1_, p_73154_2_);
//		byte[] abyte1 = chunk.getBiomeArray();
//
//		for (int k = 0; k < abyte1.length; ++k)
//		{
//			abyte1[k] = (byte)this.biomesForGeneration[k].biomeID;
//		}
//
//		chunk.generateSkylightMap();
//		return chunk;
//	}
//
//	private void func_147423_a(int p_147423_1_, int p_147423_2_, int p_147423_3_)
//	{
//		this.field_147426_g = this.noiseGen6.generateNoiseOctaves(this.field_147426_g, p_147423_1_, p_147423_3_, 5, 5, 200.0D, 200.0D, 0.5D);
//		this.field_147427_d = this.field_147429_l.generateNoiseOctaves(this.field_147427_d, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
//		this.field_147428_e = this.field_147431_j.generateNoiseOctaves(this.field_147428_e, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D, 684.412D, 684.412D);
//		this.field_147425_f = this.field_147432_k.generateNoiseOctaves(this.field_147425_f, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D, 684.412D, 684.412D);
//		int l = 0;
//		int i1 = 0;
//		for (int j1 = 0; j1 < 5; ++j1)
//		{
//			for (int k1 = 0; k1 < 5; ++k1)
//			{
//				float f = 0.0F;
//				float f1 = 0.0F;
//				float f2 = 0.0F;
//				byte b0 = 2;
//				BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];
//
//				for (int l1 = -b0; l1 <= b0; ++l1)
//				{
//					for (int i2 = -b0; i2 <= b0; ++i2)
//					{
//						BiomeGenBase biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
//						float f3 = biomegenbase1.rootHeight;
//						float f4 = biomegenbase1.heightVariation;
//
//						if (this.field_147435_p == WorldType.AMPLIFIED && f3 > 0.0F)
//						{
//							f3 = 1.0F + f3 * 2.0F;
//							f4 = 1.0F + f4 * 4.0F;
//						}
//
//						float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0F);
//
//						if (biomegenbase1.rootHeight > biomegenbase.rootHeight)
//						{
//							f5 /= 2.0F;
//						}
//
//						f += f4 * f5;
//						f1 += f3 * f5;
//						f2 += f5;
//					}
//				}
//
//				f /= f2;
//				f1 /= f2;
//				f = f * 0.9F + 0.1F;
//				f1 = (f1 * 4.0F - 1.0F) / 8.0F;
//				double d12 = this.field_147426_g[i1] / 8000.0D;
//
//				if (d12 < 0.0D)
//				{
//					d12 = -d12 * 0.3D;
//				}
//
//				d12 = d12 * 3.0D - 2.0D;
//
//				if (d12 < 0.0D)
//				{
//					d12 /= 2.0D;
//
//					if (d12 < -1.0D)
//					{
//						d12 = -1.0D;
//					}
//
//					d12 /= 1.4D;
//					d12 /= 2.0D;
//				}
//				else
//				{
//					if (d12 > 1.0D)
//					{
//						d12 = 1.0D;
//					}
//
//					d12 /= 8.0D;
//				}
//
//				++i1;
//				double d13 = f1;
//				double d14 = f;
//				d13 += d12 * 0.2D;
//				d13 = d13 * 8.5D / 8.0D;
//				double d5 = 8.5D + d13 * 4.0D;
//
//				for (int j2 = 0; j2 < 33; ++j2)
//				{
//					double d6 = (j2 - d5) * 12.0D * 128.0D / 256.0D / d14;
//
//					if (d6 < 0.0D)
//					{
//						d6 *= 4.0D;
//					}
//
//					double d7 = this.field_147428_e[l] / 512.0D;
//					double d8 = this.field_147425_f[l] / 512.0D;
//					double d9 = (this.field_147427_d[l] / 10.0D + 1.0D) / 2.0D;
//					double d10 = MathHelper.denormalizeClamp(d7, d8, d9) - d6;
//
//					if (j2 > 29)
//					{
//						double d11 = (j2 - 29) / 3.0F;
//						d10 = d10 * (1.0D - d11) + -10.0D * d11;
//					}
//
//					this.field_147434_q[l] = d10;
//					++l;
//				}
//			}
//		}
//	}
//
//	/**
//	 * Checks to see if a chunk exists at x, y
//	 */
//	@Override
//	public boolean chunkExists(int p_73149_1_, int p_73149_2_)
//	{
//		return true;
//	}
//
//	/**
//	 * Populates chunk with ores etc etc
//	 */
//	@Override
//	public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
//	{
//		BlockFalling.fallInstantly = true;
//		int k = p_73153_2_ * 16;
//		int l = p_73153_3_ * 16;
//		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
//		this.rand.setSeed(this.worldObj.getSeed());
//		long i1 = this.rand.nextLong() / 2L * 2L + 1L;
//		long j1 = this.rand.nextLong() / 2L * 2L + 1L;
//		this.rand.setSeed(p_73153_2_ * i1 + p_73153_3_ * j1 ^ this.worldObj.getSeed());
//		boolean flag = false;
//
//		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(p_73153_1_, this.worldObj, this.rand, p_73153_2_, p_73153_3_, flag));
//
//		if (this.mapFeaturesEnabled)
//		{
//			this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, p_73153_2_, p_73153_3_);
//			flag = this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, p_73153_2_, p_73153_3_);
//			this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.rand, p_73153_2_, p_73153_3_);
//			this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.rand, p_73153_2_, p_73153_3_);
//		}
//
//		int k1;
//		int l1;
//		int i2;
//
//		if (biomegenbase != BiomeGenBase.desert && biomegenbase != BiomeGenBase.desertHills && !flag && this.rand.nextInt(4) == 0
//				&& TerrainGen.populate(p_73153_1_, this.worldObj, this.rand, p_73153_2_, p_73153_3_, flag, LAKE))
//		{
//			k1 = k + this.rand.nextInt(16) + 8;
//			l1 = this.rand.nextInt(256);
//			i2 = l + this.rand.nextInt(16) + 8;
//			new WorldGenLakes(Blocks.water).generate(this.worldObj, this.rand, k1, l1, i2);
//		}
//
//		if (TerrainGen.populate(p_73153_1_, this.worldObj, this.rand, p_73153_2_, p_73153_3_, flag, LAVA) && !flag && this.rand.nextInt(8) == 0)
//		{
//			k1 = k + this.rand.nextInt(16) + 8;
//			l1 = this.rand.nextInt(this.rand.nextInt(248) + 8);
//			i2 = l + this.rand.nextInt(16) + 8;
//
//			if (l1 < 63 || this.rand.nextInt(10) == 0)
//			{
//				new WorldGenLakes(Blocks.lava).generate(this.worldObj, this.rand, k1, l1, i2);
//			}
//		}
//
//		boolean doGen = TerrainGen.populate(p_73153_1_, this.worldObj, this.rand, p_73153_2_, p_73153_3_, flag, DUNGEON);
//		for (k1 = 0; doGen && k1 < 8; ++k1)
//		{
//			l1 = k + this.rand.nextInt(16) + 8;
//			i2 = this.rand.nextInt(256);
//			int j2 = l + this.rand.nextInt(16) + 8;
//			new WorldGenDungeons().generate(this.worldObj, this.rand, l1, i2, j2);
//		}
//
//		biomegenbase.decorate(this.worldObj, this.rand, k, l);
//		if (TerrainGen.populate(p_73153_1_, this.worldObj, this.rand, p_73153_2_, p_73153_3_, flag, ANIMALS))
//		{
//			SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomegenbase, k + 8, l + 8, 16, 16, this.rand);
//		}
//		k += 8;
//		l += 8;
//
//		doGen = TerrainGen.populate(p_73153_1_, this.worldObj, this.rand, p_73153_2_, p_73153_3_, flag, ICE);
//		for (k1 = 0; doGen && k1 < 16; ++k1)
//		{
//			for (l1 = 0; l1 < 16; ++l1)
//			{
//				i2 = this.worldObj.getPrecipitationHeight(k + k1, l + l1);
//
//				if (this.worldObj.isBlockFreezable(k1 + k, i2 - 1, l1 + l))
//				{
//					this.worldObj.setBlock(k1 + k, i2 - 1, l1 + l, Blocks.ice, 0, 2);
//				}
//
//				if (this.worldObj.func_147478_e(k1 + k, i2, l1 + l, true))
//				{
//					this.worldObj.setBlock(k1 + k, i2, l1 + l, Blocks.snow_layer, 0, 2);
//				}
//			}
//		}
//
//		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(p_73153_1_, this.worldObj, this.rand, p_73153_2_, p_73153_3_, flag));
//
//		BlockFalling.fallInstantly = false;
//	}
//
//	/**
//	 * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
//	 * Return true if all chunks have been saved.
//	 */
//	@Override
//	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
//	{
//		return true;
//	}
//
//	/**
//	 * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
//	 * unimplemented.
//	 */
//	@Override
//	public void saveExtraData() {}
//
//	/**
//	 * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
//	 */
//	@Override
//	public boolean unloadQueuedChunks()
//	{
//		return false;
//	}
//
//	/**
//	 * Returns if the IChunkProvider supports saving.
//	 */
//	@Override
//	public boolean canSave()
//	{
//		return true;
//	}
//
//	/**
//	 * Converts the instance data to a readable string.
//	 */
//	@Override
//	public String makeString()
//	{
//		return "RandomLevelSource";
//	}
//
//	/**
//	 * Returns a list of creatures of the specified type that can spawn at the given location.
//	 */
//	@Override
//	public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
//	{
//		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_73155_2_, p_73155_4_);
//		return p_73155_1_ == EnumCreatureType.monster && this.scatteredFeatureGenerator.func_143030_a(p_73155_2_, p_73155_3_, p_73155_4_) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : biomegenbase.getSpawnableList(p_73155_1_);
//	}
//
//	@Override
//	public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
//	{
//		return "Stronghold".equals(p_147416_2_) && this.strongholdGenerator != null ? this.strongholdGenerator.func_151545_a(p_147416_1_, p_147416_3_, p_147416_4_, p_147416_5_) : null;
//	}
//
//	@Override
//	public int getLoadedChunkCount()
//	{
//		return 0;
//	}
//
//	@Override
//	public void recreateStructures(int p_82695_1_, int p_82695_2_)
//	{
//		if (this.mapFeaturesEnabled)
//		{
//			this.mineshaftGenerator.func_151539_a(this, this.worldObj, p_82695_1_, p_82695_2_, (Block[])null);
//			this.villageGenerator.func_151539_a(this, this.worldObj, p_82695_1_, p_82695_2_, (Block[])null);
//			this.strongholdGenerator.func_151539_a(this, this.worldObj, p_82695_1_, p_82695_2_, (Block[])null);
//			this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, p_82695_1_, p_82695_2_, (Block[])null);
//		}
//	}
//}

package stevekung.mods.moreplanets.planets.fronos.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.core.world.gen.dungeon.MapGenDungeon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.worldgen.dungeon.RoomEmptyMP;
import stevekung.mods.moreplanets.planets.diona.entities.EntityEvolvedEnderman;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityBearry;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityBerry;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamCat;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityCreamSlime;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityGrappy;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityJellySlime;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityKiwi;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityLemonDuck;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityMarshmallow;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityMelon;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityStarfish;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityStrawberryChicken;
import stevekung.mods.moreplanets.planets.fronos.entities.EntityTomato;
import stevekung.mods.moreplanets.planets.fronos.worldgen.dungeon.RoomBossFronos;
import stevekung.mods.moreplanets.planets.fronos.worldgen.dungeon.RoomChestsFronos;
import stevekung.mods.moreplanets.planets.fronos.worldgen.dungeon.RoomSpawnerFronos;
import stevekung.mods.moreplanets.planets.fronos.worldgen.dungeon.RoomTreasureFronos;
import stevekung.mods.moreplanets.planets.fronos.worldgen.village.MapGenFronosVillage;

public class ChunkProviderFronos extends ChunkProviderGenerate
{
	private Random rand;
	private NoiseGeneratorOctaves noiseGen4;
	public NoiseGeneratorOctaves noiseGen5;
	public NoiseGeneratorOctaves noiseGen6;
	public NoiseGeneratorOctaves mobSpawnerNoise;
	private World worldObj;
	private double[] stoneNoise;
	private MapGenCaveFronos caveGenerator;
	private MapGenCavernFronos cavernGenerator;
	public BiomeDecoratorFronosOre biomedecoratorplanet = new BiomeDecoratorFronosOre();
	private MapGenFronosVillage villageGenerator = new MapGenFronosVillage();
	private MapGenFronosRavine ravineGenerator = new MapGenFronosRavine();
	private BiomeGenBase[] biomesForGeneration;
	double[] noise3;
	double[] noise1;
	double[] noise2;
	double[] noise5;
	double[] noise6;
	float[] squareTable;
	private NoiseGeneratorOctaves field_147431_j;
	private NoiseGeneratorOctaves field_147432_k;
	private NoiseGeneratorOctaves field_147429_l;
	private NoiseGeneratorPerlin field_147430_m;
	private double[] terrainCalcs;
	private float[] parabolicField;
	double[] field_147427_d;
	double[] field_147428_e;
	double[] field_147425_f;
	double[] field_147426_g;
	int[][] field_73219_j = new int[32][32];

	private MapGenDungeon dungeonGenerator = new MapGenDungeon(FronosBlocks.fronos_block, 14, 8, 24, 4);
	{
		this.dungeonGenerator.otherRooms.add(new RoomEmptyMP(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new RoomSpawnerFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new RoomChestsFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.otherRooms.add(new RoomChestsFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.bossRooms.add(new RoomBossFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
		this.dungeonGenerator.treasureRooms.add(new RoomTreasureFronos(null, 0, 0, 0, ForgeDirection.UNKNOWN));
	}

	public ChunkProviderFronos(World world, long seed, boolean flag)
	{
		super(world, seed, flag);
		this.stoneNoise = new double[256];
		this.caveGenerator = new MapGenCaveFronos();
		this.cavernGenerator = new MapGenCavernFronos();
		this.worldObj = world;
		this.rand = new Random(seed);
		this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
		this.field_147431_j = new NoiseGeneratorOctaves(this.rand, 16);
		this.field_147432_k = new NoiseGeneratorOctaves(this.rand, 16);
		this.field_147429_l = new NoiseGeneratorOctaves(this.rand, 8);
		this.field_147430_m = new NoiseGeneratorPerlin(this.rand, 4);
		this.terrainCalcs = new double[825];
		this.parabolicField = new float[25];

		for (int j = -2; j <= 2; j++)
		{
			for (int k = -2; k <= 2; k++)
			{
				float f = 10.0F / MathHelper.sqrt_float(j * j + k * k + 0.2F);
				this.parabolicField[j + 2 + (k + 2) * 5] = f;
			}
		}
	}

	@Override
	public Chunk provideChunk(int x, int z)
	{
		this.rand.setSeed(x * 341873128712L + z * 132897987541L);
		Block[] blockStorage = new Block[256 * 256];
		byte[] metaStorage = new byte[256 * 256];
		this.generateTerrain(x, z, blockStorage);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);
		this.replaceBlocksForBiome(x, z, blockStorage, metaStorage, this.biomesForGeneration);
		this.caveGenerator.generate(this, this.worldObj, x, z, blockStorage, metaStorage);
		this.cavernGenerator.generate(this, this.worldObj, x, z, blockStorage, metaStorage);
		this.ravineGenerator.func_151539_a(this, this.worldObj, x, z, blockStorage);
		this.dungeonGenerator.generateUsingArrays(this.worldObj, this.worldObj.getSeed(), x * 16, 30, z * 16, x, z, blockStorage, metaStorage);
		Chunk chunk = new Chunk(this.worldObj, blockStorage, metaStorage, x, z);
		byte[] chunkBiomes = chunk.getBiomeArray();

		for (int i = 0; i < chunkBiomes.length; i++)
		{
			chunkBiomes[i] = (byte)this.biomesForGeneration[i].biomeID;
		}
		chunk.generateSkylightMap();
		return chunk;
	}

	public void generateTerrain(int chunkX, int chunkZ, Block[] blockStorage)
	{
		byte seaLevel = 63;
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, 10, 10);
		this.makeLandPerBiome2(chunkX * 4, 0, chunkZ * 4);

		for (int k = 0; k < 4; k++)
		{
			int l = k * 5;
			int i1 = (k + 1) * 5;

			for (int j1 = 0; j1 < 4; j1++)
			{
				int k1 = (l + j1) * 33;
				int l1 = (l + j1 + 1) * 33;
				int i2 = (i1 + j1) * 33;
				int j2 = (i1 + j1 + 1) * 33;

				for (int k2 = 0; k2 < 32; k2++)
				{
					double d0 = 0.125D;
					double d1 = this.terrainCalcs[k1 + k2];
					double d2 = this.terrainCalcs[l1 + k2];
					double d3 = this.terrainCalcs[i2 + k2];
					double d4 = this.terrainCalcs[j2 + k2];
					double d5 = (this.terrainCalcs[k1 + k2 + 1] - d1) * d0;
					double d6 = (this.terrainCalcs[l1 + k2 + 1] - d2) * d0;
					double d7 = (this.terrainCalcs[i2 + k2 + 1] - d3) * d0;
					double d8 = (this.terrainCalcs[j2 + k2 + 1] - d4) * d0;

					for (int l2 = 0; l2 < 8; l2++)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for (int i3 = 0; i3 < 4; ++i3)
						{
							int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
							short short1 = 256;
							j3 -= short1;
							double d14 = 0.25D;
							double d16 = (d11 - d10) * d14;
							double d15 = d10 - d16;

							for (int k3 = 0; k3 < 4; ++k3)
							{
								if ((d15 += d16) > 0.0D)
								{
									blockStorage[j3 += short1] = Blocks.stone;
								}
								else if (k2 * 8 + l2 < seaLevel)
								{
									blockStorage[j3 += short1] = Blocks.water;
								}
								else
								{
									blockStorage[j3 += short1] = null;
								}
							}
							d10 += d12;
							d11 += d13;
						}
						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	private void makeLandPerBiome2(int x, int zero, int z)
	{
		this.field_147426_g = this.noiseGen6.generateNoiseOctaves(this.field_147426_g, x, z, 5, 5, 200.0D, 200.0D, 0.5D);
		this.field_147427_d = this.field_147429_l.generateNoiseOctaves(this.field_147427_d, x, zero, z, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
		this.field_147428_e = this.field_147431_j.generateNoiseOctaves(this.field_147428_e, x, zero, z, 5, 33, 5, 684.41200000000003D, 684.41200000000003D, 684.41200000000003D);
		this.field_147425_f = this.field_147432_k.generateNoiseOctaves(this.field_147425_f, x, zero, z, 5, 33, 5, 684.41200000000003D, 684.41200000000003D, 684.41200000000003D);
		int terrainIndex = 0;
		int noiseIndex = 0;

		for (int ax = 0; ax < 5; ax++)
		{
			for (int az = 0; az < 5; az++)
			{
				float totalVariation = 0.0F;
				float totalHeight = 0.0F;
				float totalFactor = 0.0F;
				byte two = 2;
				BiomeGenBase biomegenbase = this.biomesForGeneration[ax + 2 + (az + 2) * 10];

				for (int ox = -two; ox <= two; ox++)
				{
					for (int oz = -two; oz <= two; oz++)
					{
						BiomeGenBase biomegenbase1 = this.biomesForGeneration[ax + ox + 2 + (az + oz + 2) * 10];
						float rootHeight = biomegenbase1.rootHeight;
						float heightVariation = biomegenbase1.heightVariation;
						float heightFactor = this.parabolicField[ox + 2 + (oz + 2) * 5] / (rootHeight + 2.0F);

						if (biomegenbase1.rootHeight > biomegenbase.rootHeight)
						{
							heightFactor /= 2.0F;
						}
						totalVariation += heightVariation * heightFactor;
						totalHeight += rootHeight * heightFactor;
						totalFactor += heightFactor;
					}
				}
				totalVariation /= totalFactor;
				totalHeight /= totalFactor;
				totalVariation = totalVariation * 0.9F + 0.1F;
				totalHeight = (totalHeight * 4.0F - 1.0F) / 8.0F;
				double terrainNoise = this.field_147426_g[noiseIndex] / 8000.0D;

				if (terrainNoise < 0.0D)
				{
					terrainNoise = -terrainNoise * 0.3D;
				}

				terrainNoise = terrainNoise * 3.0D - 2.0D;

				if (terrainNoise < 0.0D)
				{
					terrainNoise /= 2.0D;

					if (terrainNoise < -1.0D)
					{
						terrainNoise = -1.0D;
					}
					terrainNoise /= 1.4D;
					terrainNoise /= 2.0D;
				}
				else
				{
					if (terrainNoise > 1.0D)
					{
						terrainNoise = 1.0D;
					}
					terrainNoise /= 8.0D;
				}
				noiseIndex++;
				double heightCalc = totalHeight;
				double variationCalc = totalVariation;
				heightCalc += terrainNoise * 0.2D;
				heightCalc = heightCalc * 8.5D / 8.0D;
				double d5 = 8.5D + heightCalc * 4.0D;

				for (int ay = 0; ay < 33; ay++)
				{
					double d6 = (ay - d5) * 12.0D * 128.0D / 256.0D / variationCalc;

					if (d6 < 0.0D)
					{
						d6 *= 4.0D;
					}
					double d7 = this.field_147428_e[terrainIndex] / 512.0D;
					double d8 = this.field_147425_f[terrainIndex] / 512.0D;
					double d9 = (this.field_147427_d[terrainIndex] / 10.0D + 1.0D) / 2.0D;
					double terrainCalc = MathHelper.denormalizeClamp(d7, d8, d9) - d6;

					if (ay > 29)
					{
						double d11 = (ay - 29) / 3.0F;
						terrainCalc = terrainCalc * (1.0D - d11) + -10.0D * d11;
					}
					this.terrainCalcs[terrainIndex] = terrainCalc;
					terrainIndex++;
				}
			}
		}
	}

	@Override
	public void replaceBlocksForBiome(int chunkX, int chunkZ, Block[] blockStorage, byte[] metaStorage, BiomeGenBase[] biomes)
	{
		double d0 = 0.03125D;
		this.stoneNoise = this.field_147430_m.func_151599_a(this.stoneNoise, chunkX * 16, chunkZ * 16, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

		for (int z = 0; z < 16; z++)
		{
			for (int x = 0; x < 16; x++)
			{
				BiomeGenBase biomegenbase = biomes[x + z * 16];
				biomegenbase.genTerrainBlocks(this.worldObj, this.rand, blockStorage, metaStorage, chunkX * 16 + z, chunkZ * 16 + x, this.stoneNoise[x + z * 16]);
			}
		}
	}

	@Override
	public Chunk loadChunk(int x, int z)
	{
		return this.provideChunk(x, z);
	}

	@Override
	public boolean chunkExists(int x, int z)
	{
		return true;
	}

	@Override
	public void populate(IChunkProvider chunk, int x, int z)
	{
		BlockFalling.fallInstantly = true;
		int var4 = x * 16;
		int var5 = z * 16;
		BiomeGenBase biomeGen = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
		this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
		this.rand.setSeed(this.worldObj.getSeed());
		long var7 = this.rand.nextLong() / 2L * 2L + 1L;
		long var9 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed(x * var7 + z * var9 ^ this.worldObj.getSeed());
		this.dungeonGenerator.handleTileEntities(this.rand);
		biomeGen.decorate(this.worldObj, this.rand, var4, var5);
		this.decoratePlanet(this.worldObj, this.rand, var4, var5);
		SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomeGen, var4 + 8, var5 + 8, 16, 16, this.rand);
		this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, x, z);
		BlockFalling.fallInstantly = false;
	}

	public void decoratePlanet(World world, Random rand, int x, int z)
	{
		this.biomedecoratorplanet.decorate(world, rand, x, z);
	}

	@Override
	public boolean saveChunks(boolean flag, IProgressUpdate progress)
	{
		return true;
	}

	@Override
	public boolean canSave()
	{
		return true;
	}

	@Override
	public String makeString()
	{
		return "FronosLevelSource";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType type, int x, int y, int z)
	{
		if (type == EnumCreatureType.monster)
		{
			List monsters = new ArrayList();

			if (!ConfigManagerMP.allowMobCreatureSpawningOnFronos)
			{
				monsters.add(new SpawnListEntry(EntityEvolvedZombie.class, 100, 4, 4));
				monsters.add(new SpawnListEntry(EntityEvolvedSpider.class, 100, 4, 4));
				monsters.add(new SpawnListEntry(EntityEvolvedSkeleton.class, 100, 4, 4));
				monsters.add(new SpawnListEntry(EntityEvolvedCreeper.class, 100, 4, 4));
				monsters.add(new SpawnListEntry(EntityEvolvedEnderman.class, 100, 1, 4));
			}
			else
			{
				monsters.add(new SpawnListEntry(EntityZombie.class, 100, 4, 4));
				monsters.add(new SpawnListEntry(EntitySpider.class, 100, 4, 4));
				monsters.add(new SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
				monsters.add(new SpawnListEntry(EntityCreeper.class, 100, 4, 4));
				monsters.add(new SpawnListEntry(EntityEnderman.class, 100, 1, 4));
				monsters.add(new SpawnListEntry(EntityWitch.class, 5, 1, 1));
			}
			monsters.add(new SpawnListEntry(EntityCreamSlime.class, 100, 4, 4));
			monsters.add(new SpawnListEntry(EntityJellySlime.class, 100, 4, 4));
			return monsters;
		}
		else if (type == EnumCreatureType.creature)
		{
			List creatures = new ArrayList();
			creatures.add(new SpawnListEntry(EntityBearry.class, 8, 4, 4));
			creatures.add(new SpawnListEntry(EntityBerry.class, 8, 4, 4));
			creatures.add(new SpawnListEntry(EntityMarshmallow.class, 8, 4, 4));
			creatures.add(new SpawnListEntry(EntityKiwi.class, 8, 4, 4));
			creatures.add(new SpawnListEntry(EntityLemonDuck.class, 8, 4, 4));
			creatures.add(new SpawnListEntry(EntityTomato.class, 8, 4, 4));
			creatures.add(new SpawnListEntry(EntityMelon.class, 8, 4, 4));
			creatures.add(new SpawnListEntry(EntityGrappy.class, 12, 4, 4));
			creatures.add(new SpawnListEntry(EntityCreamCat.class, 2, 2, 2));
			creatures.add(new SpawnListEntry(EntityStrawberryChicken.class, 10, 4, 4));
			creatures.add(new SpawnListEntry(EntityStarfish.class, 8, 4, 4));

			if (ConfigManagerMP.allowMobCreatureSpawningOnFronos == true)
			{
				creatures.add(new SpawnListEntry(EntitySheep.class, 12, 4, 4));
				creatures.add(new SpawnListEntry(EntityPig.class, 10, 4, 4));
				creatures.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
				creatures.add(new SpawnListEntry(EntityCow.class, 8, 4, 4));
			}
			else
			{
				return null;
			}
			return creatures;
		}
		else if (type == EnumCreatureType.waterCreature)
		{
			List waterCreatures = new ArrayList();
			waterCreatures.add(new SpawnListEntry(EntitySquid.class, 10, 4, 4));
			return waterCreatures;
		}
		return null;
	}

	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}

	@Override
	public void recreateStructures(int x, int z)
	{
		this.villageGenerator.func_151539_a(this, this.worldObj, x, z, (Block[]) null);
	}

	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	@Override
	public void saveExtraData() {}

	@Override
	public ChunkPosition func_147416_a(World world, String string, int x, int y, int z)
	{
		return null;
	}
}