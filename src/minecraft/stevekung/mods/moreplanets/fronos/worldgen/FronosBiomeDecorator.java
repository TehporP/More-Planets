/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.worldgen;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.REED;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SAND;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.world.gen.GCCoreWorldGenMinableMeta;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlocks;
import stevekung.mods.moreplanets.fronos.util.FronosConfigManager;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenCandyCane1;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenCandyCane2;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenCandyCane3;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenColoredSpaceShell;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenCoral;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenFlowers;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenKelp;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenMelon;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenPumpkin;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenSand2;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenSplashTopBlock;
import stevekung.mods.moreplanets.fronos.worldgen.feature.FronosWorldGenStrawberryCloud;
import stevekung.mods.moreplanets.fronos.worldgen.tree.FronosWorldGenCoconutTree1;
import stevekung.mods.moreplanets.fronos.worldgen.tree.FronosWorldGenCoconutTree2;
import stevekung.mods.moreplanets.fronos.worldgen.tree.FronosWorldGenRedMapleTree;

public class FronosBiomeDecorator extends BiomeDecorator
{
	public World currentWorld;
	public Random randomGenerator;
	public int chunk_X;
	public int chunk_Z;
	public BiomeGenBase biome;

	public WorldGenerator normalSandGen;
	public WorldGenerator fronosSandGen;
	public WorldGenerator fronosClayGen;
	public WorldGenerator oilStoneGen;
	public WorldGenerator pinkButterCupGen;
	public WorldGenerator orangeFlowerGen;
	public WorldGenerator goldMilkGen;
	public WorldGenerator littleSunFlowerGen;
	public WorldGenerator skyMushroomGen;
	public WorldGenerator purpleSpikeFlowerGen;
	public WorldGenerator skyJungleIrisGen;
	public WorldGenerator bluePoisonMushroomGen;

	public WorldGenerator spaceOysterGen;
	public WorldGenerator spaceOysterCloseGen;
	public WorldGenerator orangeDandelionGen;
	public WorldGenerator pinkDandelionGen;
	public WorldGenerator candyCaneGen1;
	public WorldGenerator candyCaneGen2;
	public WorldGenerator candyCaneGen3;
	public WorldGenerator strawberryCloudGen;

	public WorldGenerator coloredSpaceShellGen1;
	public WorldGenerator coloredSpaceShellGen2;
	public WorldGenerator coloredSpaceShellGen3;
	public WorldGenerator coloredSpaceShellGen4;
	public WorldGenerator coloredSpaceShellGen5;

	public WorldGenerator purpleCoralGen;
	public WorldGenerator waterlilyGen;

	public WorldGenerator frostedCakeGen1;
	public WorldGenerator frostedCakeGen2;

	public WorldGenerator fronosShortGrassGen;
	public WorldGenerator fronosMediumGrassGen;
	public WorldGenerator fronosTallGrassGen;

	public WorldGenerator coconutTreeGen1;
	public WorldGenerator coconutTreeGen2;

	public WorldGenerator redMapleTreeGen;

	public WorldGenerator kelpGen;
	public WorldGenerator coralGen;

	public WorldGenerator greenTopazGen;
	public WorldGenerator jellyBerryGen;
	public WorldGenerator jellyStrawberryGen;
	public WorldGenerator jellyRaspberryGen;
	public WorldGenerator jellyGrapeGen;
	public WorldGenerator jellyLimeGen;
	public WorldGenerator jellyOrangeGen;
	public WorldGenerator fronosDirtGen;

	//PerChunk
	public int greenTopazPerChunk;
	public int jellyBerryPerChunk;
	public int jellyStrawberryPerChunk;
	public int jellyRaspberryPerChunk;
	public int jellyGrapePerChunk;
	public int jellyLimePerChunk;
	public int jellyOrangePerChunk;
	public int fronosDirtPerChunk;

	public int waterlilyPerChunk;

	public int frostedCakePerChunk1;
	public int frostedCakePerChunk2;

	public int pinkButterCupPerChunk;
	public int orangeFlowerPerChunk;
	public int goldMilkPerChunk;
	public int littleSunFlowerPerChunk;
	public int skyMushroomPerChunk;
	public int purpleSpikeFlowerPerChunk;
	public int skyJungleIrisPerChunk;
	public int bluePoisonMushroomPerChunk;

	public int kelpPerChunk;
	public int pumpkinsPerChunk;
	public int coralPerChunk;
	public int spaceOysterPerChunk;
	public int spaceOysterClosePerChunk;
	public int coloredSpaceShellPerChunk1;
	public int coloredSpaceShellPerChunk2;
	public int coloredSpaceShellPerChunk3;
	public int coloredSpaceShellPerChunk4;
	public int coloredSpaceShellPerChunk5;

	public int candyCanePerChunk1;
	public int candyCanePerChunk2;
	public int candyCanePerChunk3;

	public int coconutTreePerChunk1;
	public int coconutTreePerChunk2;
	public int redMapleTreePerChunk;

	public int fronosDandelionPerChunk;
	public int deadBushPerChunk;
	public int mushroomsPerChunk;
	public int reedsPerChunk;
	public int fronosClayPerChunk;

	public int fronosShortGrassPerChunk;
	public int fronosMediumGrassPerChunk;
	public int fronosTallGrassPerChunk;

	public int normalSandPerChunk;
	public int fronosSandPerChunk;
	public int waterLakesPerChunk;
	public int lavaLakesPerChunk;
	public int magicWaterPerChunk;

	public boolean generateLakes;
	public boolean generatePumpkins;
	public boolean generateMelons;
	public boolean generateStrawberryClouds;

	public FronosBiomeDecorator(BiomeGenBase par1BiomeGenBase)
	{
		super(par1BiomeGenBase);
		this.normalSandGen = new FronosWorldGenSand2(7, Block.sand.blockID);
		this.fronosSandGen = new FronosWorldGenSand2(4, FronosBlocks.fronosSand.blockID);
		this.fronosClayGen = new FronosWorldGenSand2(4, Block.blockClay.blockID);

		this.coconutTreeGen1 = new FronosWorldGenCoconutTree1();
		this.coconutTreeGen2 = new FronosWorldGenCoconutTree2();
		this.redMapleTreeGen = new FronosWorldGenRedMapleTree();

		this.frostedCakeGen1 = new FronosWorldGenSplashTopBlock(FronosBlocks.frostedCakeBlock.blockID, 1);
		this.frostedCakeGen2 = new FronosWorldGenSplashTopBlock(FronosBlocks.frostedCakeBlock.blockID, 2);

		this.pinkButterCupGen = new FronosWorldGenFlowers(FronosBlocks.fronosFlower.blockID, 0);
		this.orangeFlowerGen = new FronosWorldGenFlowers(FronosBlocks.fronosFlower.blockID, 1);
		this.goldMilkGen = new FronosWorldGenFlowers(FronosBlocks.fronosFlower.blockID, 2);
		this.littleSunFlowerGen = new FronosWorldGenFlowers(FronosBlocks.fronosFlower.blockID, 3);
		this.skyMushroomGen = new FronosWorldGenFlowers(FronosBlocks.fronosFlower.blockID, 4);
		this.purpleSpikeFlowerGen = new FronosWorldGenFlowers(FronosBlocks.fronosFlower.blockID, 5);
		this.skyJungleIrisGen = new FronosWorldGenFlowers(FronosBlocks.fronosFlower.blockID, 6);
		this.bluePoisonMushroomGen = new FronosWorldGenFlowers(FronosBlocks.fronosFlower.blockID, 7);

		this.fronosShortGrassGen = new FronosWorldGenFlowers(FronosBlocks.fronosTallGrass.blockID, 0);
		this.fronosMediumGrassGen = new FronosWorldGenFlowers(FronosBlocks.fronosTallGrass.blockID, 1);
		this.fronosTallGrassGen = new FronosWorldGenFlowers(FronosBlocks.fronosTallGrass.blockID, 2);

		this.candyCaneGen1 = new FronosWorldGenCandyCane1();
		this.candyCaneGen2 = new FronosWorldGenCandyCane2();
		this.candyCaneGen3 = new FronosWorldGenCandyCane3();

		this.spaceOysterGen = new FronosWorldGenColoredSpaceShell(FronosBlocks.spaceOyster.blockID, 0);
		this.spaceOysterCloseGen = new FronosWorldGenColoredSpaceShell(FronosBlocks.spaceOysterClose.blockID, 0);
		this.orangeDandelionGen = new FronosWorldGenFlowers(FronosBlocks.fronosDandelion.blockID, 0);
		this.pinkDandelionGen = new FronosWorldGenFlowers(FronosBlocks.fronosDandelion.blockID, 1);

		this.coloredSpaceShellGen1 = new FronosWorldGenColoredSpaceShell(FronosBlocks.spaceShell.blockID, 0);
		this.coloredSpaceShellGen2 = new FronosWorldGenColoredSpaceShell(FronosBlocks.spaceShell.blockID, 3);
		this.coloredSpaceShellGen3 = new FronosWorldGenColoredSpaceShell(FronosBlocks.spaceShell.blockID, 6);
		this.coloredSpaceShellGen4 = new FronosWorldGenColoredSpaceShell(FronosBlocks.spaceShell.blockID, 8);
		this.coloredSpaceShellGen5 = new FronosWorldGenColoredSpaceShell(FronosBlocks.spaceShell.blockID, 9);

		this.strawberryCloudGen = new FronosWorldGenStrawberryCloud();

		this.coralGen = new FronosWorldGenCoral(FronosBlocks.fronosCoral.blockID, 4);
		this.kelpGen = new FronosWorldGenKelp(false);

		this.reedGen = new WorldGenReed();
		this.waterlilyGen = new WorldGenWaterlily();

		this.greenTopazPerChunk = 32;
		this.jellyBerryPerChunk = 24;
		this.jellyStrawberryPerChunk = 24;
		this.jellyRaspberryPerChunk = 24;
		this.jellyGrapePerChunk = 24;
		this.jellyLimePerChunk = 24;
		this.jellyOrangePerChunk = 24;
		this.fronosDirtPerChunk = 86;

		this.waterlilyPerChunk = 80;
		this.treesPerChunk = 5;
		this.deadBushPerChunk = 5;
		this.reedsPerChunk = 10;
		this.normalSandPerChunk = 5;
		this.fronosSandPerChunk = 2;
		this.fronosClayPerChunk = 2;
		this.pinkButterCupPerChunk = 10;
		this.orangeFlowerPerChunk = 10;
		this.goldMilkPerChunk = 10;
		this.littleSunFlowerPerChunk = 10;
		this.skyMushroomPerChunk = 10;
		this.purpleSpikeFlowerPerChunk = 1;
		this.skyJungleIrisPerChunk = 10;
		this.bluePoisonMushroomPerChunk = 1;

		this.frostedCakePerChunk1 = 2;
		this.frostedCakePerChunk2 = 2;

		this.fronosShortGrassPerChunk = 200;
		this.fronosMediumGrassPerChunk = 200;
		this.fronosTallGrassPerChunk = 200;

		this.waterLakesPerChunk = 6;
		this.lavaLakesPerChunk = 1;
		this.magicWaterPerChunk = 4;

		this.candyCanePerChunk1 = 12;
		this.candyCanePerChunk2 = 8;
		this.candyCanePerChunk3 = 2;

		this.spaceOysterPerChunk = 1;
		this.spaceOysterClosePerChunk = 10;
		this.fronosDandelionPerChunk = 20;

		this.coconutTreePerChunk1 = 2;
		this.coconutTreePerChunk2 = 2;
		this.redMapleTreePerChunk = 2;

		this.coloredSpaceShellPerChunk1 = 10;
		this.coloredSpaceShellPerChunk2 = 7;
		this.coloredSpaceShellPerChunk3 = 5;
		this.coloredSpaceShellPerChunk4 = 4;
		this.coloredSpaceShellPerChunk5 = 2;

		this.kelpPerChunk = 10;
		this.pumpkinsPerChunk = 10;
		this.coralPerChunk = 10;

		this.generateLakes = true;
		this.generatePumpkins = true;
		this.generateMelons = true;
		this.generateStrawberryClouds = true;

		// Block,NumberOfBlock,Meta,IsMeta,FillBlock,FillMeta
		this.greenTopazGen = new GCCoreWorldGenMinableMeta(FronosBlocks.fronosOreBlock.blockID, 4, 0, true, FronosBlocks.fronosStone.blockID, 0);
		this.jellyBerryGen = new GCCoreWorldGenMinableMeta(FronosBlocks.fronosOreBlock.blockID, 4, 1, true, FronosBlocks.fronosStone.blockID, 0);
		this.jellyStrawberryGen = new GCCoreWorldGenMinableMeta(FronosBlocks.fronosOreBlock.blockID, 4, 2, true, FronosBlocks.fronosStone.blockID, 0);
		this.jellyRaspberryGen = new GCCoreWorldGenMinableMeta(FronosBlocks.fronosOreBlock.blockID, 4, 4, true, FronosBlocks.fronosStone.blockID, 0);
		this.jellyGrapeGen = new GCCoreWorldGenMinableMeta(FronosBlocks.fronosOreBlock.blockID, 4, 3, true, FronosBlocks.fronosStone.blockID, 0);
		this.jellyLimeGen = new GCCoreWorldGenMinableMeta(FronosBlocks.fronosOreBlock.blockID, 4, 5, true, FronosBlocks.fronosStone.blockID, 0);
		this.jellyOrangeGen = new GCCoreWorldGenMinableMeta(FronosBlocks.fronosOreBlock.blockID, 4, 6, true, FronosBlocks.fronosStone.blockID, 0);
		this.fronosDirtGen = new GCCoreWorldGenMinableMeta(FronosBlocks.fronosDirt.blockID, 32, 0, true, FronosBlocks.fronosStone.blockID, 0);

		this.biome = par1BiomeGenBase;
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		if (this.currentWorld != null)
		{
			return;
		}
		else
		{
			this.currentWorld = par1World;
			this.randomGenerator = par2Random;
			this.chunk_X = par3;
			this.chunk_Z = par4;
			this.decorate();
			this.currentWorld = null;
			this.randomGenerator = null;
		}
	}

	@Override
	protected void decorate()
	{
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));

		boolean doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, SAND);

		this.generateOres();

		int var1;
		int var2;
		int var3;
		int var4;
		int var5;

		for (var2 = 0; var2 < this.jellyOrangePerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16);
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16);
			this.jellyOrangeGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.fronosDirtPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16);
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16);
			this.fronosDirtGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.jellyLimePerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16);
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16);
			this.jellyLimeGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.jellyGrapePerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16);
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16);
			this.jellyGrapeGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.jellyRaspberryPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16);
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16);
			this.jellyRaspberryGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.jellyStrawberryPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16);
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16);
			this.jellyStrawberryGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.greenTopazPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16);
			var4 = this.randomGenerator.nextInt(64);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16);
			this.greenTopazGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.jellyBerryPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16);
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16);
			this.jellyBerryGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.waterLakesPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(240) + 8);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenLakes(Block.waterMoving.blockID).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.lavaLakesPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenLakes(Block.lavaMoving.blockID).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.magicWaterPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenLakes(FronosBlocks.mineralWaterFluidBlock.blockID).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var1 = 0; doGen && var1 < this.fronosClayPerChunk; ++var1)
		{
			if (this.currentWorld.rand.nextInt(12) == 0)
			{
				var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				this.fronosClayGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
			}
		}

		for (var1 = 0; doGen && var1 < this.normalSandPerChunk; ++var1)
		{
			var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.normalSandGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
		}

		for (var1 = 0; doGen && var1 < this.fronosSandPerChunk; ++var1)
		{
			if (this.currentWorld.rand.nextInt(10) == 0)
			{
				var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				this.fronosSandGen.generate(this.currentWorld, this.randomGenerator, var2, this.currentWorld.getTopSolidOrLiquidBlock(var2, var3), var3);
			}
		}

		var1 = this.treesPerChunk;
		if (this.randomGenerator.nextInt(10) == 0)
		{
			++var1;
		}

		doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, FLOWERS);
		for (var2 = 0; doGen && var2 < this.fronosDandelionPerChunk; ++var2)
		{
			if (this.randomGenerator.nextInt(4) == 0)
			{
				var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var4 = this.randomGenerator.nextInt(256);
				var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				this.orangeDandelionGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
			}

			if (this.randomGenerator.nextInt(4) == 0)
			{
				var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var4 = this.randomGenerator.nextInt(256);
				var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				this.pinkDandelionGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
			}
		}

		for (var2 = 0; var2 < this.kelpPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16);
			var4 = this.randomGenerator.nextInt(64);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16);
			this.kelpGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.candyCanePerChunk1; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.candyCaneGen1.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.candyCanePerChunk2; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.candyCaneGen2.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.candyCanePerChunk3; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.candyCaneGen3.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.spaceOysterPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.spaceOysterGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.spaceOysterClosePerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.spaceOysterCloseGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.coconutTreePerChunk1; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.coconutTreeGen1.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.coconutTreePerChunk2; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(64) + 64;
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.coconutTreeGen2.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.redMapleTreePerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.redMapleTreeGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.coralPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.coralGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < FronosConfigManager.strawberryCloudPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(32);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.strawberryCloudGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.fronosShortGrassPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.fronosShortGrassGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.fronosMediumGrassPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.fronosMediumGrassGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.fronosTallGrassPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.fronosTallGrassGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.pinkButterCupPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.pinkButterCupGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.orangeFlowerPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.orangeFlowerGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.goldMilkPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.goldMilkGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.littleSunFlowerPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.littleSunFlowerGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.skyMushroomPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.skyMushroomGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.purpleSpikeFlowerPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.purpleSpikeFlowerGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.skyJungleIrisPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.skyJungleIrisGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.bluePoisonMushroomPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.bluePoisonMushroomGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.frostedCakePerChunk1; ++var2)
		{
			if (this.currentWorld.rand.nextInt(10) == 0)
			{
				var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var4 = this.randomGenerator.nextInt(256);
				var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				this.frostedCakeGen1.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
			}
		}

		for (var2 = 0; var2 < this.frostedCakePerChunk2; ++var2)
		{
			if (this.currentWorld.rand.nextInt(10) == 0)
			{
				var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var4 = this.randomGenerator.nextInt(256);
				var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				this.frostedCakeGen2.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
			}
		}

		for (var2 = 0; var2 < this.coloredSpaceShellPerChunk1; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.coloredSpaceShellGen1.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.coloredSpaceShellPerChunk2; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.coloredSpaceShellGen2.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.coloredSpaceShellPerChunk3; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.coloredSpaceShellGen3.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.coloredSpaceShellPerChunk4; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.coloredSpaceShellGen4.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		for (var2 = 0; var2 < this.coloredSpaceShellPerChunk5; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.coloredSpaceShellGen5.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, DEAD_BUSH);
		for (var2 = 0; doGen && var2 < this.deadBushPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new WorldGenDeadBush(Block.deadBush.blockID).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, REED);
		for (var2 = 0; doGen && var2 < this.reedsPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			var5 = this.randomGenerator.nextInt(256);
			this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var5, var4);
		}

		for (var2 = 0; doGen && var2 < 1200; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.randomGenerator.nextInt(256);
			var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
		}

		if (this.generatePumpkins && this.randomGenerator.nextInt(18) == 0)
		{
			var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var3 = this.randomGenerator.nextInt(256);
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new FronosWorldGenPumpkin().generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
		}

		if (this.generateMelons && this.randomGenerator.nextInt(18) == 0)
		{
			var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var3 = this.randomGenerator.nextInt(256);
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
			new FronosWorldGenMelon().generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
		}

		if (this.generateStrawberryClouds)
		{
			this.genStrawberryCloudMain(1, this.strawberryCloudGen, 0, 85);
		}

		if (this.generateLakes)
		{
			for (var2 = 0; var2 < 2200; ++var2)
			{
				var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
				var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(this.randomGenerator.nextInt(112) + 8) + 8);
				var5 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
				new WorldGenLiquids(Block.lavaMoving.blockID).generate(this.currentWorld, this.randomGenerator, var3, var4, var5);
			}
		}

		doGen = TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, LILYPAD);
		for (var2 = 0; doGen && var2 < this.waterlilyPerChunk; ++var2)
		{
			var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
			var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;

			for (var5 = this.randomGenerator.nextInt(256); var5 > 0 && this.currentWorld.getBlockId(var3, var5 - 1, var4) == 200; --var5)
			{
			}
			this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, var3, var5, var4);
		}
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
	}


	@Override
	protected void genStandardOre1(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
	{
		for (int var5 = 200; var5 < par1; ++var5)
		{
			int var6 = this.chunk_X + this.randomGenerator.nextInt(16);
			int var7 = this.randomGenerator.nextInt(par4 - par3) + par3;
			int var8 = this.chunk_Z + this.randomGenerator.nextInt(16);
			par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8);
		}
	}

	@Override
	protected void genStandardOre2(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
	{
		for (int var5 = 200; var5 < par1; ++var5)
		{
			int var6 = this.chunk_X + this.randomGenerator.nextInt(16);
			int var7 = this.randomGenerator.nextInt(par4) + this.randomGenerator.nextInt(par4) + par3 - par4;
			int var8 = this.chunk_Z + this.randomGenerator.nextInt(16);
			par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8);
		}
	}

	protected void genStrawberryCloudMain(int par1, WorldGenerator par2WorldGenerator, int par3, int par4)
	{
		for (int var5 = 0; var5 < par1; ++var5)
		{
			int var6 = this.chunk_X + this.randomGenerator.nextInt(8);
			int var7 = this.randomGenerator.nextInt(par4 - par3) + par3;
			int var8 = this.chunk_Z + this.randomGenerator.nextInt(8);
			int var999 = this.randomGenerator.nextInt(5);
			if (var999 == 0)
			{
				par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8);
				par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6 + 8, var7, var8);
				par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6, var7, var8 + 8);
				par2WorldGenerator.generate(this.currentWorld, this.randomGenerator, var6 + 8, var7, var8 + 8);
			}
		}
	}

	@Override
	protected void generateOres()
	{
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z));
	}
}