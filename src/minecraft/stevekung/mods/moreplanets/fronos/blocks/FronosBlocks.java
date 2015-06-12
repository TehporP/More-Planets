/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.items.MPItemRarity;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlockCandyCane.CandyCategory;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlockColorizedLeaves.LeafCategory;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlockLeaves.FronosLeafCategory;
import stevekung.mods.moreplanets.fronos.blocks.FronosBlockLog.LogCategory;
import stevekung.mods.moreplanets.fronos.fluids.FronosCoconutMilkFluid;
import stevekung.mods.moreplanets.fronos.fluids.FronosMineralWaterFluid;
import stevekung.mods.moreplanets.fronos.fluids.FronosOvantineFluid;
import stevekung.mods.moreplanets.fronos.fluids.blocks.FronosBlockFluidCoconutMilk;
import stevekung.mods.moreplanets.fronos.fluids.blocks.FronosBlockFluidMineralWater;
import stevekung.mods.moreplanets.fronos.fluids.blocks.FronosBlockFluidOvantine;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockCandyCane;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockColoredSpaceShell;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockColorizedLeaves;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockCoral;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockCreamChocolate;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockCreamHead;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockCreamOrange;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockDandelion;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockFlower;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockFrostedCake;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockJelly;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockLeaves;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockLog;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockOre;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockPlanks;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockPoppy;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockSand;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockSapling;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockSpaceOysterClose;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockSpaceOysterOpen;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockStone;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockStrawberryCream;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockTallGrass;
import stevekung.mods.moreplanets.fronos.itemblocks.FronosItemBlockVanillaCream;
import stevekung.mods.moreplanets.fronos.util.FronosConfigManager;

public class FronosBlocks
{
	public static Block fronosGrass;
	public static Block fronosDirt;
	public static Block fronosStone;
	public static Block fronosFarmland;
	public static Block fronosTallGrass;
	public static Block strawberryCrop;
	public static Block spaceOyster;
	public static Block frostedCakeBlock;
	public static Block vanillaCream;
	public static Block vanillaCreamLayer;
	public static Block jellyBlock;
	public static Block spaceShell;
	public static Block chocolateCream;
	public static Block chocolateCreamLayer;
	public static Block cookieBlock;
	public static Block candyCane1;
	public static Block candyCane2;
	public static Block fronosFlower;
	public static Block fronosDandelion;
	public static Block fronosCoral;
	public static Block spaceOysterClose;
	public static Block coconutMilkFluidBlock;
	public static Block mineralWaterFluidBlock;
	public static Block fronosBlockLog;
	public static Block coconutBlock;
	public static Block fronosColorizedLeaves;
	public static Block fronosBlockSapling;
	public static Block fronosBlockPlanks;
	public static Block fronosCobblestoneStairs;
	public static Block fronosTreasureChest;
	public static Block candyExtractorIdle;
	public static Block candyExtractorActive;
	public static Block ovantineBlock;
	public static Block strawberryCloud;
	public static Block coconutWoodStairs;
	public static Block pinkCakeBlock;
	public static Block cakeBlock;
	public static Block pinkCandyTorch;
	public static Block orangeCandyTorch;
	public static Block greenCandyTorch;
	public static Block yellowCandyTorch;
	public static Block lightBlueCandyTorch;
	public static Block blueCandyTorch;
	public static Block lightPurpleCandyTorch;
	public static Block purpleCandyTorch;
	public static Block chocolateBlock;
	public static Block fronosOreBlock;
	public static Block strawberryCream;
	public static Block strawberryCreamLayer;
	public static Block fronosSand;
	public static Block fronosPinkGrass;
	public static Block fronosPurpleGrass;
	public static Block fronosPlainsGrass;
	public static Block ovantineFluidBlock;
	public static Block goldenWheatCrops;
	public static Block orangeCream;
	public static Block orangeCreamLayer;
	public static Block creamHead;
	public static Block goldenGrass;
	public static Block fronosBlockLeaves;
	public static Block fronosPoppy;
	public static Block mapleIvy;
	public static Block glassGemCorn;

	/**@FluidStuff**/
	public static Fluid coconutMilkFluid;
	public static Fluid mineralWaterFluid;
	public static Fluid ovantineFluid;

	public static void init()
	{
		initBlocks();
		setHarvestLevels();
		registerBlocks();
	}

	private static void initBlocks()
	{
		FronosBlocks.fronosGrass = new FronosBlockGrass(FronosConfigManager.idFronosGrass).setUnlocalizedName("fronosGrass");
		FronosBlocks.fronosDirt = new FronosBlockDirt(FronosConfigManager.idFronosDirt).setUnlocalizedName("fronosDirt");
		FronosBlocks.fronosFarmland = new FronosBlockFarmland(FronosConfigManager.idFronosFarmland).setUnlocalizedName("fronosFarmland");
		FronosBlocks.fronosTallGrass = new FronosBlockTallGrass(FronosConfigManager.idFronosTallGrass).setUnlocalizedName("tg");
		FronosBlocks.strawberryCrop = new FronosBlockStrawberryCrop(FronosConfigManager.idStrawberryCrop).setUnlocalizedName("strawberryCrops");
		FronosBlocks.fronosStone = new FronosBlockStone(FronosConfigManager.idFronosStone, Material.rock).setUnlocalizedName("fs");
		FronosBlocks.spaceOyster = new FronosBlockSpaceOyster(FronosConfigManager.idSpaceOyster).setUnlocalizedName("spaceOyster");
		FronosBlocks.frostedCakeBlock = new FronosBlockFrostedCake(FronosConfigManager.idBlockFrostedCake).setUnlocalizedName("cb");
		FronosBlocks.vanillaCream = new FronosBlockVanillaCream(FronosConfigManager.idBlockCream).setUnlocalizedName("vanillaCream");
		FronosBlocks.vanillaCreamLayer = new FronosBlockVanillaCreamLayer(FronosConfigManager.idBlockCreamLayer).setUnlocalizedName("vanillaCreamLayer");
		FronosBlocks.jellyBlock = new FronosBlockJelly(FronosConfigManager.idBlockJelly).setUnlocalizedName("jelly");
		FronosBlocks.spaceShell = new FronosBlockColoredSpaceShell(FronosConfigManager.idBlockSpaceShell).setUnlocalizedName("coloredSpaceShell");
		FronosBlocks.chocolateCream = new FronosBlockChocolateCream(FronosConfigManager.idBlockCreamChocolate).setUnlocalizedName("chocolateCream");
		FronosBlocks.chocolateCreamLayer = new FronosBlockChocolateCreamLayer(FronosConfigManager.idBlockCreamChocolateLayer).setUnlocalizedName("chocolateCreamLayer");
		FronosBlocks.cookieBlock = new FronosBlockCookie(FronosConfigManager.idBlockCookie).setUnlocalizedName("cookieBlock");
		FronosBlocks.candyCane1 = new FronosBlockCandyCane(FronosConfigManager.idBlockCandyCane1, CandyCategory.CAT1).setUnlocalizedName("candyCane1");
		FronosBlocks.candyCane2 = new FronosBlockCandyCane(FronosConfigManager.idBlockCandyCane2, CandyCategory.CAT2).setUnlocalizedName("candyCane2");
		FronosBlocks.fronosFlower = new FronosBlockFlower(FronosConfigManager.idBlockFlower).setUnlocalizedName("fronosFlower");
		FronosBlocks.fronosDandelion = new FronosBlockDandelion(FronosConfigManager.idBlockFronosDandelion).setUnlocalizedName("fronosDandelion");
		FronosBlocks.fronosCoral = new FronosBlockCoral(FronosConfigManager.idBlockFronosCoral).setUnlocalizedName("fronosCoral");
		FronosBlocks.spaceOysterClose = new FronosBlockSpaceOysterClose(FronosConfigManager.idBlockSpaceOysterClose).setUnlocalizedName("spaceOysterClose");
		FronosBlocks.fronosBlockLog = new FronosBlockLog(FronosConfigManager.idBlockFronosLog, LogCategory.CAT1).setUnlocalizedName("fronosLog");
		FronosBlocks.coconutBlock = new FronosBlockCoconut(FronosConfigManager.idBlockCoconut).setUnlocalizedName("coconutBlock");
		FronosBlocks.fronosColorizedLeaves = new FronosBlockColorizedLeaves(FronosConfigManager.idBlockLeaves, LeafCategory.CAT1).setUnlocalizedName("fronosLeaves");
		FronosBlocks.fronosBlockSapling = new FronosBlockSapling(FronosConfigManager.idBlockSapling).setUnlocalizedName("fronosSapling");
		FronosBlocks.fronosBlockPlanks = new FronosBlockPlanks(FronosConfigManager.idBlockPlanks).setUnlocalizedName("fronosPlanks");
		FronosBlocks.fronosCobblestoneStairs = new BlockStairsMP(FronosConfigManager.idBlockFronosCobblestoneStairs, GCCoreBlocks.blockMoon, StairsCategory.FRONOS).setHardness(3.0F).setUnlocalizedName("fronosCobblestoneStairs");
		FronosBlocks.fronosTreasureChest = new FronosBlockT9TreasureChest(FronosConfigManager.idBlockFronosTreasureChest).setUnlocalizedName("fronosTreasureChest");
		FronosBlocks.candyExtractorIdle = new FronosBlockCandyExtractor(FronosConfigManager.idBlockCandyExtractorIdle, false).setUnlocalizedName("candyExtractorIdle");
		FronosBlocks.candyExtractorActive = new FronosBlockCandyExtractor(FronosConfigManager.idBlockCandyExtractorActive, true).setUnlocalizedName("candyExtractorActive").setLightValue(1.0F);
		FronosBlocks.ovantineBlock = new FronosBlockOvantine(FronosConfigManager.idBlockOvantine).setUnlocalizedName("ovantineBlock");
		FronosBlocks.strawberryCloud = new FronosBlockColdStrawberryCloud(FronosConfigManager.idBlockStrawberryCloud).setUnlocalizedName("strawberryCloud");
		FronosBlocks.coconutWoodStairs = new BlockStairsMP(FronosConfigManager.idBlockCoconutWoodStairs, GCCoreBlocks.blockMoon, StairsCategory.COCONUT).setHardness(2.0F).setUnlocalizedName("coconutWoodStairs").setStepSound(Block.soundWoodFootstep);
		FronosBlocks.pinkCakeBlock = new FronosBlockPinkCake(FronosConfigManager.idBlockPinkCake, Material.cake).setUnlocalizedName("pinkCake");
		FronosBlocks.cakeBlock = new FronosBlockCake(FronosConfigManager.idBlockCake, Material.cake).setUnlocalizedName("cakeBlock");
		FronosBlocks.pinkCandyTorch = new FronosBlockPinkCandyTorch(FronosConfigManager.idBlockPinkCandyTorch).setUnlocalizedName("pinkCandyTorch");
		FronosBlocks.orangeCandyTorch = new FronosBlockOrangeCandyTorch(FronosConfigManager.idBlockOrangeCandyTorch).setUnlocalizedName("orangeCandyTorch");
		FronosBlocks.greenCandyTorch = new FronosBlockGreenCandyTorch(FronosConfigManager.idBlockGreenCandyTorch).setUnlocalizedName("greenCandyTorch");
		FronosBlocks.yellowCandyTorch = new FronosBlockYellowCandyTorch(FronosConfigManager.idBlockYellowCandyTorch).setUnlocalizedName("yellowCandyTorch");
		FronosBlocks.lightBlueCandyTorch = new FronosBlockLightBlueCandyTorch(FronosConfigManager.idBlockLightBlueCandyTorch).setUnlocalizedName("lightBlueCandyTorch");
		FronosBlocks.blueCandyTorch = new FronosBlockBlueCandyTorch(FronosConfigManager.idBlockBlueCandyTorch).setUnlocalizedName("blueCandyTorch");
		FronosBlocks.lightPurpleCandyTorch = new FronosBlockLightPurpleCandyTorch(FronosConfigManager.idBlockLightPurpleCandyTorch).setUnlocalizedName("lightPurpleCandyTorch");
		FronosBlocks.purpleCandyTorch = new FronosBlockPurpleCandyTorch(FronosConfigManager.idBlockPurpleCandyTorch).setUnlocalizedName("purpleCandyTorch");
		FronosBlocks.chocolateBlock = new FronosBlockChocolate(FronosConfigManager.idBlockChocolate).setUnlocalizedName("chocolateBlock");
		FronosBlocks.fronosOreBlock = new FronosBlockOre(FronosConfigManager.idBlockFronosOre).setUnlocalizedName("fo");
		FronosBlocks.strawberryCream = new FronosBlockStrawberryCream(FronosConfigManager.idBlockStrawberryCream).setUnlocalizedName("strawberryCream");
		FronosBlocks.strawberryCreamLayer = new FronosBlockStrawberryCreamLayer(FronosConfigManager.idBlockStrawberryCreamLayer).setUnlocalizedName("strawberryCreamLayer");
		FronosBlocks.fronosSand = new FronosBlockSand(FronosConfigManager.idBlockFronosSand).setUnlocalizedName("sand");
		FronosBlocks.fronosPinkGrass = new FronosBlockPinkGrass(FronosConfigManager.idBlockFronosPinkGrass).setUnlocalizedName("fronosPinkGrass");
		FronosBlocks.fronosPurpleGrass = new FronosBlockPurpleGrass(FronosConfigManager.idBlockFronosPurpleGrass).setUnlocalizedName("fronosPurpleGrass");
		FronosBlocks.fronosPlainsGrass = new FronosBlockPlainsGrass(FronosConfigManager.idBlockFronosPlainGrass).setUnlocalizedName("fronosPlainsGrass");
		FronosBlocks.goldenWheatCrops = new FronosBlockCrops(FronosConfigManager.idBlockGoldenCrops).setUnlocalizedName("goldenWheatCrops");
		FronosBlocks.creamHead = new FronosBlockGolemCreamHead(FronosConfigManager.idBlockCreamHead).setUnlocalizedName("creamHead");
		FronosBlocks.orangeCream = new FronosBlockOrangeCream(FronosConfigManager.idBlockOrangeCream).setUnlocalizedName("orangeCream");
		FronosBlocks.orangeCreamLayer = new FronosBlockOrangeCreamLayer(FronosConfigManager.idBlockOrangeCreamLayer).setUnlocalizedName("orangeCreamLayer");
		FronosBlocks.goldenGrass = new FronosBlockGoldenGrass(FronosConfigManager.idBlockGoldenGrass).setUnlocalizedName("goldenGrass");
		FronosBlocks.fronosBlockLeaves = new FronosBlockLeaves(FronosConfigManager.idBlockFronosLeaves, FronosLeafCategory.CAT1).setUnlocalizedName("fronosLeaves2");
		FronosBlocks.fronosPoppy = new FronosBlockPoppy(FronosConfigManager.idBlockFronosPoppy).setUnlocalizedName("fronosPoppy");
		FronosBlocks.mapleIvy = new FronosBlockMapleIvy(FronosConfigManager.idBlockMapleIvy).setUnlocalizedName("mapleIvy");
		FronosBlocks.glassGemCorn = new FronosBlockGlassGemCorn(FronosConfigManager.idBlockGlassGemCorn).setUnlocalizedName("glassGemCorn");

		FronosBlocks.coconutMilkFluid = new FronosCoconutMilkFluid("coconutMilkFluid").setBlockID(FronosConfigManager.idBlockCoconutMilk);
		FluidRegistry.registerFluid(FronosBlocks.coconutMilkFluid);
		FronosBlocks.coconutMilkFluidBlock = new FronosBlockFluidCoconutMilk(FronosConfigManager.idBlockCoconutMilk, FronosBlocks.coconutMilkFluid, Material.water).setUnlocalizedName("coconutMilkFluid");

		FronosBlocks.mineralWaterFluid = new FronosMineralWaterFluid("mineralWaterFluid").setBlockID(FronosConfigManager.idBlockMagicWater);
		FluidRegistry.registerFluid(FronosBlocks.mineralWaterFluid);
		FronosBlocks.mineralWaterFluidBlock = new FronosBlockFluidMineralWater(FronosConfigManager.idBlockMagicWater, FronosBlocks.mineralWaterFluid, Material.water).setUnlocalizedName("mineralWaterFluid");

		FronosBlocks.ovantineFluid = new FronosOvantineFluid("ovantineFluid").setBlockID(FronosConfigManager.idBlockOvantineFluid);
		FluidRegistry.registerFluid(FronosBlocks.ovantineFluid);
		FronosBlocks.ovantineFluidBlock = new FronosBlockFluidOvantine(FronosConfigManager.idBlockOvantineFluid, FronosBlocks.ovantineFluid, Material.water).setUnlocalizedName("ovantineFluid");
	}

	private static void setHarvestLevels()
	{
		MinecraftForge.setBlockHarvestLevel(fronosGrass, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(fronosDirt, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(fronosFarmland, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(fronosPinkGrass, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(fronosPurpleGrass, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(fronosPlainsGrass, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(fronosSand, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(vanillaCream, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(chocolateCream, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(strawberryCream, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(strawberryCreamLayer, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(chocolateCreamLayer, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(ovantineBlock, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(chocolateBlock, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(cookieBlock, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(vanillaCreamLayer, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(frostedCakeBlock, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(fronosOreBlock, "pickaxe", 6);
		MinecraftForge.setBlockHarvestLevel(fronosCobblestoneStairs, "pickaxe", 6);
		MinecraftForge.setBlockHarvestLevel(candyExtractorIdle, "pickaxe", 6);
		MinecraftForge.setBlockHarvestLevel(candyExtractorActive, "pickaxe", 6);
		MinecraftForge.setBlockHarvestLevel(fronosBlockLog, "axe", 0);
		MinecraftForge.setBlockHarvestLevel(fronosBlockPlanks, "axe", 0);
		MinecraftForge.setBlockHarvestLevel(coconutWoodStairs, "axe", 0);
		MinecraftForge.setBlockHarvestLevel(orangeCreamLayer, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(orangeCream, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(goldenGrass, "shovel", 0);
	}

	private static void registerBlocks()
	{
		MPBlocks.registerBlock(fronosGrass, MPItemRarity.class);
		MPBlocks.registerBlock(fronosDirt, MPItemRarity.class);
		MPBlocks.registerBlock(fronosStone, FronosItemBlockStone.class);
		MPBlocks.registerBlock(fronosFarmland, MPItemRarity.class);
		MPBlocks.registerBlock(fronosTallGrass, FronosItemBlockTallGrass.class);
		MPBlocks.registerBlock(strawberryCrop, MPItemRarity.class);
		MPBlocks.registerBlock(spaceOyster, FronosItemBlockSpaceOysterOpen.class);
		MPBlocks.registerBlock(frostedCakeBlock, FronosItemBlockFrostedCake.class);
		MPBlocks.registerBlock(vanillaCream, MPItemRarity.class);
		MPBlocks.registerBlock(vanillaCreamLayer, FronosItemBlockVanillaCream.class);
		MPBlocks.registerBlock(jellyBlock, FronosItemBlockJelly.class);
		MPBlocks.registerBlock(spaceShell, FronosItemBlockColoredSpaceShell.class);
		MPBlocks.registerBlock(chocolateCream, MPItemRarity.class);
		MPBlocks.registerBlock(chocolateCreamLayer, FronosItemBlockCreamChocolate.class);
		MPBlocks.registerBlock(cookieBlock, MPItemRarity.class);
		MPBlocks.registerBlock(candyCane1, FronosItemBlockCandyCane.class);
		MPBlocks.registerBlock(candyCane2, FronosItemBlockCandyCane.class);
		MPBlocks.registerBlock(fronosFlower, FronosItemBlockFlower.class);
		MPBlocks.registerBlock(fronosDandelion, FronosItemBlockDandelion.class);
		MPBlocks.registerBlock(fronosCoral, FronosItemBlockCoral.class);
		MPBlocks.registerBlock(spaceOysterClose, FronosItemBlockSpaceOysterClose.class);
		MPBlocks.registerBlock(coconutMilkFluidBlock, MPItemRarity.class);
		MPBlocks.registerBlock(mineralWaterFluidBlock, MPItemRarity.class);
		MPBlocks.registerBlock(fronosBlockLog, FronosItemBlockLog.class);
		MPBlocks.registerBlock(coconutBlock, MPItemRarity.class);
		MPBlocks.registerBlock(fronosColorizedLeaves, FronosItemBlockColorizedLeaves.class);
		MPBlocks.registerBlock(fronosBlockPlanks, FronosItemBlockPlanks.class);
		MPBlocks.registerBlock(fronosBlockSapling, FronosItemBlockSapling.class);
		MPBlocks.registerBlock(fronosCobblestoneStairs, MPItemRarity.class);
		MPBlocks.registerBlock(fronosTreasureChest, MPItemRarity.class);
		MPBlocks.registerBlock(candyExtractorIdle, MPItemRarity.class);
		MPBlocks.registerBlock(candyExtractorActive, MPItemRarity.class);
		MPBlocks.registerBlock(ovantineBlock, MPItemRarity.class);
		MPBlocks.registerBlock(strawberryCloud, MPItemRarity.class);
		MPBlocks.registerBlock(coconutWoodStairs, MPItemRarity.class);
		MPBlocks.registerBlock(pinkCakeBlock, MPItemRarity.class);
		MPBlocks.registerBlock(cakeBlock, MPItemRarity.class);
		MPBlocks.registerBlock(pinkCandyTorch, MPItemRarity.class);
		MPBlocks.registerBlock(orangeCandyTorch, MPItemRarity.class);
		MPBlocks.registerBlock(greenCandyTorch, MPItemRarity.class);
		MPBlocks.registerBlock(yellowCandyTorch, MPItemRarity.class);
		MPBlocks.registerBlock(lightBlueCandyTorch, MPItemRarity.class);
		MPBlocks.registerBlock(blueCandyTorch, MPItemRarity.class);
		MPBlocks.registerBlock(lightPurpleCandyTorch, MPItemRarity.class);
		MPBlocks.registerBlock(purpleCandyTorch, MPItemRarity.class);
		MPBlocks.registerBlock(chocolateBlock, MPItemRarity.class);
		MPBlocks.registerBlock(fronosOreBlock, FronosItemBlockOre.class);
		MPBlocks.registerBlock(strawberryCream, MPItemRarity.class);
		MPBlocks.registerBlock(strawberryCreamLayer, FronosItemBlockStrawberryCream.class);
		MPBlocks.registerBlock(fronosSand, FronosItemBlockSand.class);
		MPBlocks.registerBlock(fronosPinkGrass, MPItemRarity.class);
		MPBlocks.registerBlock(fronosPurpleGrass, MPItemRarity.class);
		MPBlocks.registerBlock(fronosPlainsGrass, MPItemRarity.class);
		MPBlocks.registerBlock(ovantineFluidBlock, MPItemRarity.class);
		MPBlocks.registerBlock(goldenWheatCrops, MPItemRarity.class);
		MPBlocks.registerBlock(creamHead, FronosItemBlockCreamHead.class);
		MPBlocks.registerBlock(orangeCream, MPItemRarity.class);
		MPBlocks.registerBlock(orangeCreamLayer, FronosItemBlockCreamOrange.class);
		MPBlocks.registerBlock(goldenGrass, MPItemRarity.class);
		MPBlocks.registerBlock(fronosBlockLeaves, FronosItemBlockLeaves.class);
		MPBlocks.registerBlock(fronosPoppy, FronosItemBlockPoppy.class);
		MPBlocks.registerBlock(mapleIvy, MPItemRarity.class);
		MPBlocks.registerBlock(glassGemCorn, MPItemRarity.class);
	}
}