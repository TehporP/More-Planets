/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen.biome;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import stevekung.mods.moreplanets.core.config.ConfigManagerMP;
import stevekung.mods.moreplanets.core.world.biome.BiomeGenBaseMP;
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
import stevekung.mods.moreplanets.planets.fronos.worldgen.BiomeDecoratorFronos;

public class BiomeGenBaseFronos extends BiomeGenBaseMP
{
	public static BiomeGenBase coconutForest = new BiomeGenCoconutForest().setBiomeName("Coconut Forest").setEnableSnow().setTemperatureRainfall(0.8F, 0.9F).setHeight(new Height(0.1F, 0.2F));
	public static BiomeGenBase goldenField = new BiomeGenGoldenField().setBiomeName("Golden Field").setEnableSnow().setTemperatureRainfall(0.95F, 0.9F).setHeight(new Height(0.125F, 0.05F));
	public static BiomeGenBase purpleMapleForest = new BiomeGenPurpleMapleForest().setBiomeName("Purple Maple Forest").setTemperatureRainfall(0.8F, 0.9F).setHeight(new Height(0.1F, 0.2F));
	public static BiomeGenBase mapleForest = new BiomeGenMapleForest().setBiomeName("Maple Forest").setTemperatureRainfall(0.8F, 0.9F).setHeight(new Height(0.1F, 0.2F));
	public static BiomeGenBase grassyPlains = new BiomeGenGrassyPlains().setBiomeName("Grassy Plains").setTemperatureRainfall(0.95F, 0.9F).setHeight(new Height(0.125F, 0.05F));
	public static BiomeGenBase candyLand = new BiomeGenCandyLand().setBiomeName("Candy Land").setTemperatureRainfall(0.95F, 0.9F).setHeight(new Height(0.125F, 0.05F));

	protected Block stoneBlock;
	protected byte topMeta;
	protected byte fillerMeta;
	protected byte stoneMeta;

	public BiomeGenBaseFronos(int id)
	{
		super(id);
		this.enableRain = true;
		this.enableSnow = true;
		this.topBlock = FronosBlocks.fronos_grass;
		this.fillerBlock = FronosBlocks.fronos_dirt;
		this.getBiomeDecorator().strawberryCloudPerChunk = 8;
		this.getBiomeDecorator().rainbowCloudPerChunk = 1;
		this.getBiomeDecorator().coralPerChunk = 36;
		this.getBiomeDecorator().dungeonSpawnerPerChunk = 32;
		this.getBiomeDecorator().spaceShellPerChunk = 128;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = -999;

		if (!ConfigManagerMP.allowMobCreatureSpawningOnFronos)
		{
			this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedZombie.class, 100, 4, 4));
			this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedSpider.class, 100, 4, 4));
			this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedSkeleton.class, 100, 4, 4));
			this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedCreeper.class, 100, 4, 4));
			this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedEnderman.class, 100, 1, 4));
		}
		else
		{
			this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 100, 4, 4));
			this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 100, 4, 4));
			this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
			this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 100, 4, 4));
			this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 100, 1, 4));
			this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 5, 1, 1));

			this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 12, 4, 4));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 10, 4, 4));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 8, 4, 4));
		}
		this.spawnableMonsterList.add(new SpawnListEntry(EntityCreamSlime.class, 100, 4, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityJellySlime.class, 100, 4, 4));
		this.spawnableWaterCreatureList.add(new SpawnListEntry(EntitySquid.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityBearry.class, 8, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityBerry.class, 8, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityMarshmallow.class, 8, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityKiwi.class, 8, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityLemonDuck.class, 8, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityTomato.class, 8, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityMelon.class, 8, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityStarfish.class, 8, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityStrawberryChicken.class, 10, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityGrappy.class, 12, 4, 4));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityCreamCat.class, 2, 2, 2));
	}

	@Override
	public BiomeDecorator createBiomeDecorator()
	{
		return new BiomeDecoratorFronos();
	}

	protected BiomeDecoratorFronos getBiomeDecorator()
	{
		return (BiomeDecoratorFronos)this.theBiomeDecorator;
	}

	@Override
	public void genTerrainBlocks(World world, Random rand, Block[] block, byte[] meta, int x, int z, double stoneNoise)
	{
		this.genFronosBiomeTerrain(world, rand, block, meta, x, z, stoneNoise);
	}

	public void genFronosBiomeTerrain(World world, Random rand, Block[] block, byte[] meta, int x, int z, double stoneNoise)
	{
		Block topBlock = this.topBlock;
		byte topMeta = this.topMeta;
		Block fillerBlock = this.fillerBlock;
		byte fillerMeta = this.fillerMeta;
		int currentFillerDepth = -1;
		int maxFillerDepth = (int)(stoneNoise / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
		int maskX = x & 15;
		int maskZ = z & 15;
		int worldHeight = block.length / 256;
		int seaLevel = 32;

		for (int y = 255; y >= 0; y--)
		{
			int index = (maskZ * 16 + maskX) * worldHeight + y;

			if (y <= 0 + rand.nextInt(5))
			{
				block[index] = Blocks.bedrock;
			}
			else
			{
				Block currentBlock = block[index];

				if (currentBlock != null && currentBlock.getMaterial() != Material.air)
				{
					if (currentBlock == Blocks.stone)
					{
						if (this.stoneBlock != null)
						{
							block[index] = this.stoneBlock;
							meta[index] = this.stoneMeta;
						}
						if (currentFillerDepth == -1)
						{
							if (maxFillerDepth <= 0)
							{
								topBlock = null;
								topMeta = 0;
								fillerBlock = FronosBlocks.fronos_block;
								fillerMeta = 0;
							}
							else if (y >= seaLevel - 5 && y <= seaLevel)
							{
								topBlock = this.topBlock;
								topMeta = this.topMeta;
								fillerBlock = this.fillerBlock;
								fillerMeta = 0;
							}
							if (y < seaLevel - 1 && (topBlock == null || topBlock.getMaterial() == Material.air))
							{
								if (this.getFloatTemperature(x, y, z) < 0.15F)
								{
									topBlock = Blocks.ice;
									topMeta = 0;
								}
								else
								{
									topBlock = Blocks.water;
									topMeta = 0;
								}
							}

							currentFillerDepth = maxFillerDepth;

							if (y >= seaLevel - 2)
							{
								block[index] = topBlock;
								meta[index] = topMeta;
							}
							else if (y < seaLevel - 8 - maxFillerDepth)
							{
								topBlock = null;
								fillerBlock = FronosBlocks.fronos_block;
								fillerMeta = 0;
								block[index] = Blocks.gravel;
							}
							else
							{
								block[index] = fillerBlock;
								meta[index] = fillerMeta;
							}
						}
						else if (currentFillerDepth > 0)
						{
							currentFillerDepth--;
							block[index] = fillerBlock;
							meta[index] = fillerMeta;

							if (currentFillerDepth == 0 && fillerBlock == Blocks.sand)
							{
								currentFillerDepth = rand.nextInt(4) + Math.max(0, y - (seaLevel - 1));
								fillerBlock = Blocks.sandstone;
								fillerMeta = 0;
							}
						}
					}
				}
			}
		}
	}
}