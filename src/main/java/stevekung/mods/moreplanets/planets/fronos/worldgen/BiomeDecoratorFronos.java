/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.event.terraingen.TerrainGen;
import stevekung.mods.moreplanets.core.worldgen.feature.WorldGenLiquidLakes;
import stevekung.mods.moreplanets.core.worldgen.feature.WorldGenTreeMP;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenCandyCane1;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenCandyCane2;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenCandyFlower;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenCavernOyster;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenClayFronos;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenCloud;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenFronosCoral;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenFronosDungeons;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenFronosFlowers;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenFronosMelon;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenFronosPumpkin;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenFronosSand;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenFrostedCake;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenMapleIvy;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenSpaceOyster;
import stevekung.mods.moreplanets.planets.fronos.worldgen.feature.WorldGenSpaceShell;
import stevekung.mods.moreplanets.planets.fronos.worldgen.tree.WorldGenCoconutTree;

public class BiomeDecoratorFronos extends BiomeDecorator
{
	public int waterlilyPerChunk;
	public int frostedCakePerChunk;
	public int pinkButterCupPerChunk;
	public int orangeFlowerPerChunk;
	public int goldMilkCapPerChunk;
	public int littleSunFlowerPerChunk;
	public int skyMushroomPerChunk;
	public int purpleSpikeFlowerPerChunk;
	public int jungleIrisPerChunk;
	public int bluePoisonMushroomPerChunk;
	public int whiteMossPerChunk;
	public int candyFlowerPerChunk;
	public int coralPerChunk;
	public int oysterPerChunk;
	public int oysterClosePerChunk;
	public int spaceShellPerChunk;
	public int mapleIvyPerChunk;
	public int candyCanePerChunk;
	public int coconutTreePerChunk;
	public int redMapleTreePerChunk;
	public int yellowMapleTreePerChunk;
	public int purpleMapleTreePerChunk;
	public int dandelionPerChunk;
	public int poppyPerChunk;
	public int deadBushPerChunk;
	public int reedsPerChunk;
	public int clayPerChunk;
	public int fronosTallGrassPerChunk;
	public int pinkTallGrassPerChunk;
	public int purpleTallGrassPerChunk;
	public int plainsTallGrassPerChunk;
	public int goldenTallGrassPerChunk;
	public int normalSandPerChunk;
	public int fronosSandPerChunk;
	public int whiteSandPerChunk;
	public int cheeseSandPerChunk;
	public int gravelPerChunk;
	public int lakesPerChunk;
	public int dungeonSpawnerPerChunk;
	public int strawberryCloudPerChunk;
	public int rainbowCloudPerChunk;

	public BiomeDecoratorFronos()
	{
		/*this.waterlilyPerChunk = 150;
		this.deadBushPerChunk = 5;
		this.reedsPerChunk = 200;
		this.normalSandPerChunk = 4;
		this.fronosSandPerChunk = 3;
		this.whiteSandPerChunk = 3;
		this.cheeseSandPerChunk = 2;
		this.gravelPerChunk = 3;
		this.clayPerChunk = 2;

		this.strawberryCloudPerChunk = 8;
		this.rainbowCloudPerChunk = 1;

		this.pinkButterCupPerChunk = 12;
		this.orangeFlowerPerChunk = 12;
		this.goldMilkCapPerChunk = 10;
		this.littleSunFlowerPerChunk = 6;
		this.skyMushroomPerChunk = 6;
		this.purpleSpikeFlowerPerChunk = 2;
		this.jungleIrisPerChunk = 4;
		this.bluePoisonMushroomPerChunk = 2;
		this.whiteMossPerChunk = 2;
		this.candyFlowerPerChunk = 2;
				this.dandelionPerChunk = 12;
		this.poppyPerChunk = 8;

		this.frostedCakePerChunk = 1;
		this.lakesPerChunk = 1;
		this.candyCanePerChunk = 180;
		this.oysterPerChunk = 500;
		this.oysterClosePerChunk = 500;

		this.redMapleTreePerChunk = 64;
		this.yellowMapleTreePerChunk = 64;

		/*this.fronosTallGrassPerChunk = 200;
		this.pinkTallGrassPerChunk = 200;
		this.purpleTallGrassPerChunk = 200;
		this.plainsTallGrassPerChunk = 200;
		this.goldenTallGrassPerChunk = 200;
		this.spaceShellPerChunk = 64;
		this.mapleIvyPerChunk = 8;
		 */
	}

	@Override
	public void decorateChunk(World world, Random rand, BiomeGenBase biome, int x, int z)
	{
		if (this.currentWorld != null)
		{
			throw new RuntimeException("Already decorating!!");
		}
		else
		{
			this.currentWorld = world;
			this.randomGenerator = rand;
			this.chunk_X = x;
			this.chunk_Z = z;
			this.genDecorations(biome);
			this.currentWorld = null;
			this.randomGenerator = null;
		}
	}

	@Override
	protected void genDecorations(BiomeGenBase biome)
	{
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));

		int x;
		int y;
		int z;
		int i;

		// Clay
		for (i = 0; this.getGen(EventType.CLAY) && i < this.clayPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenClayFronos(4).generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getTopSolidOrLiquidBlock(x, z), z);
		}

		// Sand
		for (i = 0; this.getGen(EventType.SAND) && i < this.normalSandPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosSand(Blocks.sand, 0, 6).generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getTopSolidOrLiquidBlock(x, z), z);
		}
		for (i = 0; this.getGen(EventType.SAND) && i < this.fronosSandPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosSand(FronosBlocks.fronos_sand, 0, 4).generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getTopSolidOrLiquidBlock(x, z), z);
		}
		for (i = 0; this.getGen(EventType.SAND) && i < this.whiteSandPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosSand(FronosBlocks.fronos_sand, 1, 6).generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getTopSolidOrLiquidBlock(x, z), z);
		}
		for (i = 0; this.getGen(EventType.SAND) && i < this.cheeseSandPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosSand(FronosBlocks.fronos_sand, 2, 4).generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getTopSolidOrLiquidBlock(x, z), z);
		}
		for (i = 0; this.getGen(EventType.SAND) && i < this.gravelPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosSand(Blocks.gravel, 0, 6).generate(this.currentWorld, this.randomGenerator, x, this.currentWorld.getTopSolidOrLiquidBlock(x, z), z);
		}

		// Lake
		for (i = 0; this.getGen(EventType.LAKE) && i < this.lakesPerChunk; ++i)
		{
			if (this.randomGenerator.nextInt(1) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenLiquidLakes(Blocks.water).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.randomGenerator.nextInt(5) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenLiquidLakes(Blocks.lava).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.randomGenerator.nextInt(8) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenLiquidLakes(FronosBlocks.mineral_water).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.randomGenerator.nextInt(10) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(48 - 16) + 16;
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenLiquidLakes(FronosBlocks.caramel).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
		}

		// Dandelion
		for (i = 0; this.getGen(EventType.FLOWERS) && i < this.dandelionPerChunk; ++i)
		{
			if (this.randomGenerator.nextInt(4) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFronosFlowers(FronosBlocks.dandelion, 0).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.randomGenerator.nextInt(4) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFronosFlowers(FronosBlocks.dandelion, 1).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.randomGenerator.nextInt(4) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFronosFlowers(FronosBlocks.dandelion, 2).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.randomGenerator.nextInt(8) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFronosFlowers(FronosBlocks.dandelion, 3).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.randomGenerator.nextInt(8) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFronosFlowers(FronosBlocks.dandelion, 4).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.randomGenerator.nextInt(8) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFronosFlowers(FronosBlocks.dandelion, 5).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
		}

		// Poppy
		for (i = 0; this.getGen(EventType.FLOWERS) && i < this.poppyPerChunk; ++i)
		{
			if (this.randomGenerator.nextInt(8) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFronosFlowers(FronosBlocks.poppy, 0).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.randomGenerator.nextInt(8) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFronosFlowers(FronosBlocks.poppy, 1).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.randomGenerator.nextInt(8) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFronosFlowers(FronosBlocks.poppy, 2).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
		}

		// Candy Cane
		for (i = 0; this.getGen(EventType.CUSTOM) && i < this.candyCanePerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenCandyCane1().generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.CUSTOM) && i < this.candyCanePerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenCandyCane2().generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

		// Oyster
		for (i = 0; this.getGen(EventType.CUSTOM) && i < this.oysterPerChunk; ++i)
		{
			if (this.randomGenerator.nextInt(1) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenSpaceOyster(FronosBlocks.space_oyster_open).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.randomGenerator.nextInt(1000) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(32 - 16) + 16;
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenCavernOyster(FronosBlocks.cavern_oyster_open).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
		}
		for (i = 0; this.getGen(EventType.CUSTOM) && i < this.oysterClosePerChunk; ++i)
		{
			if (this.randomGenerator.nextInt(1) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenSpaceOyster(FronosBlocks.space_oyster_close).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.randomGenerator.nextInt(1000) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(32 - 16) + 16;
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenCavernOyster(FronosBlocks.cavern_oyster_close).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
		}

		// Tree
		for (i = 0; this.getGen(EventType.TREE) && i < this.coconutTreePerChunk; ++i)
		{
			if (this.randomGenerator.nextInt(4) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenCoconutTree(35, 10, 1.3D).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
		}
		for (i = 0; this.getGen(EventType.TREE) && i < this.redMapleTreePerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenTreeMP(FronosBlocks.fronos_log, FronosBlocks.fronos_leaves, 1, 0, false, 5, 4, false).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.TREE) && i < this.yellowMapleTreePerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenTreeMP(FronosBlocks.fronos_log, FronosBlocks.fronos_leaves, 1, 1, false, 5, 4, false).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.TREE) && i < this.purpleMapleTreePerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenTreeMP(FronosBlocks.fronos_log, FronosBlocks.fronos_leaves, 1, 2, false, 5, 4, false).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

		// Coral
		for (i = 0; this.getGen(EventType.CUSTOM) && i < this.coralPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosCoral().generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

		// Tall Grass
		for (i = 0; this.getGen(EventType.GRASS) && i < this.fronosTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 0).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.fronosTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 1).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.fronosTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 2).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.pinkTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 3).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.pinkTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 4).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.pinkTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 5).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.purpleTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 6).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.purpleTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 7).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.purpleTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 8).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.plainsTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 9).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.plainsTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 10).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.plainsTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 11).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.goldenTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 12).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.goldenTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 13).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.GRASS) && i < this.goldenTallGrassPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_tall_grass, 14).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

		// Fronos Flower
		for (i = 0; this.getGen(EventType.FLOWERS) && i < this.pinkButterCupPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_flower, 0).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.FLOWERS) && i < this.orangeFlowerPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_flower, 1).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.FLOWERS) && i < this.goldMilkCapPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_flower, 2).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.FLOWERS) && i < this.littleSunFlowerPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_flower, 3).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.FLOWERS) && i < this.skyMushroomPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(48 - 16) + 16;
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_flower, 4).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.FLOWERS) && i < this.purpleSpikeFlowerPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_flower, 5).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.FLOWERS) && i < this.jungleIrisPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_flower, 6).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.FLOWERS) && i < this.bluePoisonMushroomPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_flower, 7).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.FLOWERS) && i < this.whiteMossPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosFlowers(FronosBlocks.fronos_flower, 8).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.FLOWERS) && i < this.candyFlowerPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenCandyFlower().generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

		// Frosted Cake
		for (i = 0; this.getGen(EventType.CUSTOM) && i < this.frostedCakePerChunk; ++i)
		{
			if (this.currentWorld.rand.nextInt(30) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFrostedCake(FronosBlocks.frosted_cake, 3).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.currentWorld.rand.nextInt(30) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFrostedCake(FronosBlocks.frosted_cake, 4).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.currentWorld.rand.nextInt(30) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFrostedCake(FronosBlocks.frosted_cake, 5).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
			if (this.currentWorld.rand.nextInt(30) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(256);
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenFrostedCake(FronosBlocks.frosted_cake, 6).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
		}

		// Space Shell
		for (i = 0; this.getGen(EventType.CUSTOM) && i < this.spaceShellPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenSpaceShell().generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

		// Dungeon Spawner
		for (i = 0; this.getGen(EventType.CUSTOM) && i < this.dungeonSpawnerPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(128);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosDungeons().generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

		// Maple Ivy
		for (i = 0; this.getGen(EventType.CUSTOM) && i < this.mapleIvyPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenMapleIvy().generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

		// Dead Bush
		for (i = 0; this.getGen(EventType.DEAD_BUSH) && i < this.deadBushPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenDeadBush(Blocks.deadbush).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

		// Sugar Cane
		for (i = 0; this.getGen(EventType.REED) && i < this.reedsPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenReed().generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

		// Cloud Block
		for (i = 0; this.getGen(EventType.CUSTOM) && i < this.strawberryCloudPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(128 - 80) + 80;
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenCloud(FronosBlocks.cloud_block, 0).generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		for (i = 0; this.getGen(EventType.CUSTOM) && i < this.rainbowCloudPerChunk; ++i)
		{
			if (this.randomGenerator.nextInt(10) == 0)
			{
				x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				y = this.randomGenerator.nextInt(128 - 80) + 80;
				z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenCloud(FronosBlocks.cloud_block, 1).generate(this.currentWorld, this.randomGenerator, x, y, z);
			}
		}

		// Pumpkin
		if (this.randomGenerator.nextInt(10) == 0)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosPumpkin().generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

		// Melon
		if (this.randomGenerator.nextInt(15) == 0)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			y = this.randomGenerator.nextInt(256);
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenFronosMelon().generate(this.currentWorld, this.randomGenerator, x, y, z);
		}

		// Lilypad
		for (i = 0; this.getGen(EventType.LILYPAD) && i < this.waterlilyPerChunk; ++i)
		{
			x = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			z = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;

			for (y = this.randomGenerator.nextInt(256); y > 0 && this.currentWorld.getBlock(x, y - 1, z) == Block.getBlockById(200); --y)
			{
			}
			new WorldGenWaterlily().generate(this.currentWorld, this.randomGenerator, x, y, z);
		}
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
	}

	private boolean getGen(EventType event)
	{
		return TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, event);
	}
}