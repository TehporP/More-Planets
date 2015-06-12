/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.blocks;

import micdoodle8.mods.galacticraft.core.blocks.GCCoreBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP;
import stevekung.mods.moreplanets.core.blocks.BlockStairsMP.StairsCategory;
import stevekung.mods.moreplanets.core.init.MPBlocks;
import stevekung.mods.moreplanets.core.items.MPItemRarity;
import stevekung.mods.moreplanets.nibiru.blocks.NibiruBlockLog.NibiruLogCategory;
import stevekung.mods.moreplanets.nibiru.itemblocks.NibiruItemBlock;
import stevekung.mods.moreplanets.nibiru.itemblocks.NibiruItemBlockAppleLeaves;
import stevekung.mods.moreplanets.nibiru.itemblocks.NibiruItemBlockLog;
import stevekung.mods.moreplanets.nibiru.itemblocks.NibiruItemBlockOilStone;
import stevekung.mods.moreplanets.nibiru.itemblocks.NibiruItemBlockOrangeLeaves;
import stevekung.mods.moreplanets.nibiru.itemblocks.NibiruItemBlockPlank;
import stevekung.mods.moreplanets.nibiru.itemblocks.NibiruItemBlockPowerCrystalGen;
import stevekung.mods.moreplanets.nibiru.itemblocks.NibiruItemBlockSapling;
import stevekung.mods.moreplanets.nibiru.util.NibiruConfigManager;

public class NibiruBlocks
{
	public static Block nibiruBasicBlock;
	public static Block nibiruCobblestoneStairs;
	public static Block whiteWoodStairs;
	public static Block nibiruTreasureChest;
	public static Block nibiruAncientChest;
	public static Block nibiruBlockSapling;
	public static Block heliumBlock;
	public static Block infectedWormEggStone;
	public static Block infectedNibiruZombieEgg;
	public static Block nibiruAppleLeaves;
	public static Block nibiruBlockWood;
	public static Block nibiruBlockPlanks;
	public static Block powerCrystalGenerator;
	public static Block ichoriusTorch;
	public static Block nibiruOrangeLeaves;
	public static Block nibiruOrangeWoodStairs;
	public static Block infectedGrass;
	public static Block infectedDirt;
	public static Block oilStone;
	public static Block infectedVine;
	public static Block infectedVineDirt;
	public static Block infectedHeliumDirt;

	public static void init()
	{
		initBlocks();
		setHarvestLevels();
		registerBlocks();
	}

	private static void initBlocks()
	{
		NibiruBlocks.nibiruBasicBlock = new NibiruBlock(NibiruConfigManager.idNibiruBlock).setUnlocalizedName("nr");
		NibiruBlocks.nibiruTreasureChest = new NibiruBlockT5TreasureChest(NibiruConfigManager.idNibiruTreasureChest).setUnlocalizedName("nibiruTreasureChest");
		NibiruBlocks.nibiruAncientChest = new NibiruBlockAncientChest(NibiruConfigManager.idNibiruEpicChest).setUnlocalizedName("nibiruAncientChest");
		NibiruBlocks.nibiruBlockSapling = new NibiruBlockSapling(NibiruConfigManager.idBlockNibiruSapling).setUnlocalizedName("nibiruSapling");
		NibiruBlocks.heliumBlock = new NibiruBlockHelium(NibiruConfigManager.idNibiruHelium).setUnlocalizedName("heliumBlock");
		NibiruBlocks.infectedWormEggStone = new NibiruBlockInfectedWormEggStone(NibiruConfigManager.idInfectedWormEgg).setUnlocalizedName("infectedWormEgg");
		NibiruBlocks.infectedNibiruZombieEgg = new NibiruBlockInfectedZombieEgg(NibiruConfigManager.idInfectedZombieEgg).setUnlocalizedName("infectedZombieEgg");
		NibiruBlocks.nibiruAppleLeaves = new NibiruBlockAppleLeaves(NibiruConfigManager.idNibiruLeaves).setUnlocalizedName("appleLeaves");
		NibiruBlocks.nibiruBlockWood = new NibiruBlockLog(NibiruConfigManager.idBlockNibiruWood, NibiruLogCategory.CAT1).setUnlocalizedName("nibiruLog");
		NibiruBlocks.nibiruBlockPlanks = new NibiruBlockPlanks(NibiruConfigManager.idNibiruWoodPlanks).setUnlocalizedName("nibiruPlanks");
		NibiruBlocks.powerCrystalGenerator = new NibiruBlockPowerCrystalGenerator(NibiruConfigManager.idIchoriusGenerator).setUnlocalizedName("powerCrystalGenerator");
		NibiruBlocks.ichoriusTorch = new NibiruBlockIchoriusTorch(NibiruConfigManager.idIchoriusTorch).setUnlocalizedName("ichoriusTorch");
		NibiruBlocks.whiteWoodStairs = new BlockStairsMP(NibiruConfigManager.idWhiteWoodStairs, NibiruBlocks.nibiruBlockPlanks, StairsCategory.WHITE).setHardness(2.0F).setUnlocalizedName("whiteWoodStairs");
		NibiruBlocks.nibiruCobblestoneStairs = new BlockStairsMP(NibiruConfigManager.idNibiruCobblstoneStairs, GCCoreBlocks.blockMoon, StairsCategory.NIBIRU).setHardness(2.0F).setUnlocalizedName("nibiruCobblestoneStairs");
		NibiruBlocks.nibiruOrangeLeaves = new NibiruBlockOrangeLeaves(NibiruConfigManager.idBlockOrangeLeaves).setUnlocalizedName("orangeLeaves");
		NibiruBlocks.nibiruOrangeWoodStairs = new BlockStairsMP(NibiruConfigManager.idBlockOrangeWoodStairs, NibiruBlocks.nibiruBlockPlanks, StairsCategory.ORANGE).setHardness(2.0F).setUnlocalizedName("orangeWoodStairs");
		NibiruBlocks.infectedGrass = new NibiruBlockInfectedGrass(NibiruConfigManager.idBlockInfectedGrass).setUnlocalizedName("infectedGrass");
		NibiruBlocks.infectedDirt = new NibiruBlockInfectedDirt(NibiruConfigManager.idBlockInfectedDirt).setUnlocalizedName("infectedDirt");
		NibiruBlocks.oilStone = new NibiruBlockOilStone(NibiruConfigManager.idBlockOilStone).setUnlocalizedName("oilStone");
		NibiruBlocks.infectedVine = new NibiruBlockInfectedVine(NibiruConfigManager.idBlockInfectedVine).setUnlocalizedName("infectedVine");
		NibiruBlocks.infectedVineDirt = new NibiruBlockInfectedVineDirt(NibiruConfigManager.idBlockInfectedVineDirt).setUnlocalizedName("infectedVineDirt");
		NibiruBlocks.infectedHeliumDirt = new NibiruBlockInfectedHeliumDirt(NibiruConfigManager.idBlockInfectedHeliumDirt).setUnlocalizedName("infectedHeliumDirt");
	}

	private static void setHarvestLevels()
	{
		MinecraftForge.setBlockHarvestLevel(nibiruBasicBlock, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(nibiruCobblestoneStairs, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(infectedWormEggStone, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(powerCrystalGenerator, "pickaxe", 5);
		MinecraftForge.setBlockHarvestLevel(nibiruAncientChest, "axe", 0);
		MinecraftForge.setBlockHarvestLevel(whiteWoodStairs, "axe", 0);
		MinecraftForge.setBlockHarvestLevel(nibiruOrangeWoodStairs, "axe", 0);
		MinecraftForge.setBlockHarvestLevel(nibiruBlockWood, "axe", 0);
		MinecraftForge.setBlockHarvestLevel(nibiruBlockPlanks, "axe", 0);
		MinecraftForge.setBlockHarvestLevel(infectedGrass, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(infectedDirt, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(infectedVineDirt, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(infectedHeliumDirt, "shovel", 0);
		MinecraftForge.setBlockHarvestLevel(oilStone, "pickaxe", 5);
	}

	private static void registerBlocks()
	{
		MPBlocks.registerBlock(nibiruBasicBlock, NibiruItemBlock.class);
		MPBlocks.registerBlock(nibiruTreasureChest, MPItemRarity.class);
		MPBlocks.registerBlock(nibiruAncientChest, MPItemRarity.class);
		MPBlocks.registerBlock(nibiruBlockSapling, NibiruItemBlockSapling.class);
		MPBlocks.registerBlock(heliumBlock, MPItemRarity.class);
		MPBlocks.registerBlock(infectedWormEggStone, MPItemRarity.class);
		MPBlocks.registerBlock(infectedNibiruZombieEgg, MPItemRarity.class);
		MPBlocks.registerBlock(nibiruAppleLeaves, NibiruItemBlockAppleLeaves.class);
		MPBlocks.registerBlock(nibiruBlockWood, NibiruItemBlockLog.class);
		MPBlocks.registerBlock(nibiruBlockPlanks, NibiruItemBlockPlank.class);
		MPBlocks.registerBlock(powerCrystalGenerator, NibiruItemBlockPowerCrystalGen.class);
		MPBlocks.registerBlock(ichoriusTorch, MPItemRarity.class);
		MPBlocks.registerBlock(nibiruOrangeLeaves, NibiruItemBlockOrangeLeaves.class);
		MPBlocks.registerBlock(whiteWoodStairs, MPItemRarity.class);
		MPBlocks.registerBlock(nibiruCobblestoneStairs, MPItemRarity.class);
		MPBlocks.registerBlock(nibiruOrangeWoodStairs, MPItemRarity.class);
		MPBlocks.registerBlock(infectedGrass, MPItemRarity.class);
		MPBlocks.registerBlock(infectedDirt, MPItemRarity.class);
		MPBlocks.registerBlock(oilStone, NibiruItemBlockOilStone.class);
		MPBlocks.registerBlock(infectedVine, MPItemRarity.class);
		MPBlocks.registerBlock(infectedVineDirt, MPItemRarity.class);
		MPBlocks.registerBlock(infectedHeliumDirt, MPItemRarity.class);
	}
}