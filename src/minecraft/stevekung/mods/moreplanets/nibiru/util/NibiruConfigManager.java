/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.util;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class NibiruConfigManager
{
	public static boolean loaded;

	static Configuration configuration;

	// BLOCKS
	public static int idNibiruBlock;
	public static int idNibiruTreasureChest;
	public static int idNibiruEpicChest;
	public static int idBlockNibiruSapling;
	public static int idNibiruHelium;
	public static int idInfectedWormEgg;
	public static int idInfectedZombieEgg;
	public static int idNibiruLeaves;
	public static int idBlockNibiruWood;
	public static int idNibiruWoodPlanks;
	public static int idWhiteWoodStairs;
	public static int idNibiruCobblstoneStairs;
	public static int idIchoriusGenerator;
	public static int idIchoriusTorch;
	public static int idBlockOrangeLeaves;
	public static int idBlockOrangeWoodStairs;
	public static int idBlockInfectedGrass;
	public static int idBlockInfectedDirt;
	public static int idBlockOilStone;
	public static int idBlockInfectedVine;
	public static int idBlockInfectedVineDirt;
	public static int idBlockInfectedHeliumDirt;

	/**@Dimensions**/
	public static int idDimensionNibiru;

	// ITEMS
	public static int idNibiruBasicItem;
	public static int idNibiruDungeonKey;
	public static int idNibiruFood;
	public static int idIchoriusGem;
	public static int idItemRocketT5;
	public static int idItemRocketPartT5;
	public static int idItemSchematicT5;
	public static int idItemRockyCycle;

	// TOOLS
	public static int idToolRedGemPickaxe;
	public static int idToolRedGemAxe;
	public static int idToolRedGemHoe;
	public static int idToolRedGemSpade;
	public static int idToolRedGemSword;
	public static int idToolNoriumPickaxe;
	public static int idToolNoriumAxe;
	public static int idToolNoriumHoe;
	public static int idToolNoriumSpade;
	public static int idToolNoriumSword;

	// ARMOR
	public static int idArmorRedGemHelmet;
	public static int idArmorRedGemChestplate;
	public static int idArmorRedGemLeggings;
	public static int idArmorRedGemBoots;
	public static int idArmorNoriumHelmet;
	public static int idArmorNoriumChestplate;
	public static int idArmorNoriumLeggings;
	public static int idArmorNoriumBoots;
	public static int idArmorBreathableRedGemHelmet;
	public static int idArmorBreathableNoriumHelmet;

	/**@Entities**/
	public static int idInfectedWorm;
	public static int idGiantWorm;
	public static int idInfectedZombie;
	public static int idEntityGiantSpikeBoss;

	/**@NonMobEntities**/
	public static int idRocketT5;
	public static int idRocketT5Unflag;
	public static int idEntityGravityInfectionDart;
	public static int idEntityInfectionDart;

	/**@Generals**/
	public static boolean generateOtherMods;
	public static boolean enableCoalGen;
	public static boolean enableDiamondGen;

	/**@Schematics**/
	public static int idSchematicRocketT5;
	public static int idSchematicRocketT5NoFlag;

	/**@GUI**/
	public static int idGuiRocketT5;
	public static int idGuiRocketT5NoFlag;

	public NibiruConfigManager(File file)
	{
		if (!NibiruConfigManager.loaded)
		{
			NibiruConfigManager.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	private void setDefaultValues()
	{
		try
		{
			NibiruConfigManager.configuration.load();

			// BLOCKS
			NibiruConfigManager.idNibiruBlock = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Nibiru Basic Block", 1098).getInt(1098);
			NibiruConfigManager.idNibiruTreasureChest = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Nibiru Treasure Chest", 1099).getInt(1099);
			NibiruConfigManager.idNibiruEpicChest = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Nibiru Epic Chest", 1100).getInt(1100);
			NibiruConfigManager.idIchoriusGenerator = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Ichorius Generator", 1101).getInt(1101);
			NibiruConfigManager.idInfectedWormEgg = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Infected Worm Egg", 1102).getInt(1102);
			NibiruConfigManager.idNibiruHelium = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Helium Block", 1103).getInt(1103);
			NibiruConfigManager.idBlockNibiruWood = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Nibiru Wood", 1104).getInt(1104);
			NibiruConfigManager.idNibiruWoodPlanks = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Nibiru Planks", 1105).getInt(1105);
			NibiruConfigManager.idNibiruLeaves = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Nibiru Apple Leaves", 1106).getInt(1106);
			NibiruConfigManager.idBlockOrangeLeaves = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Nibiru Orange Leaves", 1107).getInt(1107);
			NibiruConfigManager.idBlockNibiruSapling = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Nibiru Sapling", 1108).getInt(1108);
			NibiruConfigManager.idWhiteWoodStairs = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "White Wood Stairs", 1109).getInt(1109);
			NibiruConfigManager.idBlockOrangeWoodStairs = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Orange Wood Stairs", 1110).getInt(1110);
			NibiruConfigManager.idNibiruCobblstoneStairs = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Nibiru Cobblestone Stairs", 1111).getInt(1111);
			NibiruConfigManager.idInfectedZombieEgg = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Infected Zombie Egg", 1112).getInt(1112);
			NibiruConfigManager.idIchoriusTorch = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Ichorius Torch", 1113).getInt(1113);
			NibiruConfigManager.idBlockOilStone = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Oil Stone", 1114).getInt(1114);
			NibiruConfigManager.idBlockInfectedGrass = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Infected Grass", 1115).getInt(1115);
			NibiruConfigManager.idBlockInfectedDirt = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Infected Dirt", 1116).getInt(1116);
			NibiruConfigManager.idBlockInfectedVine = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Infected Vine", 1117).getInt(1117);
			NibiruConfigManager.idBlockInfectedVineDirt = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Infected Vine Dirt", 1118).getInt(1118);
			NibiruConfigManager.idBlockInfectedHeliumDirt = NibiruConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Infected Helium Dirt", 1119).getInt(1119);

			/**@Dimensions**/
			NibiruConfigManager.idDimensionNibiru = NibiruConfigManager.configuration.get("Dimensions", "Nibiru Dimension", -9).getInt(-9);

			// ITEMS
			NibiruConfigManager.idNibiruBasicItem = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Nibiru Basic Item", 4924).getInt(4924);
			NibiruConfigManager.idIchoriusGem = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Power Gem", 4925).getInt(4925);
			NibiruConfigManager.idNibiruFood = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Nibiru Food", 4926).getInt(4926);
			NibiruConfigManager.idNibiruDungeonKey = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Nibiru Treasure Key", 4927).getInt(4927);
			NibiruConfigManager.idItemRocketT5 = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Spaceship Tier 5", 4928).getInt(4928);
			NibiruConfigManager.idItemRocketPartT5 = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Rocket Part Tier 5", 4929).getInt(4929);
			NibiruConfigManager.idItemSchematicT5 = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Schematic Tier 5", 4930).getInt(4930);
			NibiruConfigManager.idItemRockyCycle = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Rocky Cycle", 4931).getInt(4931);

			// TOOLS
			NibiruConfigManager.idToolRedGemSpade = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Red Gem Spade", 5300).getInt(5300);
			NibiruConfigManager.idToolRedGemPickaxe = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Red Gem Pickaxe", 5301).getInt(5301);
			NibiruConfigManager.idToolRedGemAxe = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Red Gem Axe", 5302).getInt(5302);
			NibiruConfigManager.idToolRedGemHoe = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Red Gem Hoe", 5303).getInt(5303);
			NibiruConfigManager.idToolRedGemSword = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Red Gem Sword", 5304).getInt(5304);
			NibiruConfigManager.idToolNoriumSpade = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Norium Spade", 5305).getInt(5305);
			NibiruConfigManager.idToolNoriumPickaxe = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Norium Pickaxe", 5306).getInt(5306);
			NibiruConfigManager.idToolNoriumAxe = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Norium Axe", 5307).getInt(5307);
			NibiruConfigManager.idToolNoriumHoe = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Norium Hoe", 5308).getInt(5308);
			NibiruConfigManager.idToolNoriumSword = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Norium Sword", 5309).getInt(5309);

			// ARMOR
			NibiruConfigManager.idArmorRedGemHelmet = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Red Gem Helmet", 5818).getInt(5818);
			NibiruConfigManager.idArmorRedGemChestplate = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Red Gem Chestplate", 5819).getInt(5819);
			NibiruConfigManager.idArmorRedGemLeggings = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Red Gem Leggings", 5820).getInt(5820);
			NibiruConfigManager.idArmorRedGemBoots = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Red Gem Boots", 5821).getInt(5821);
			NibiruConfigManager.idArmorNoriumHelmet = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Norium Helmet", 5822).getInt(5822);
			NibiruConfigManager.idArmorNoriumChestplate = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Norium Chestplate", 5823).getInt(5823);
			NibiruConfigManager.idArmorNoriumLeggings = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Norium Leggings", 5824).getInt(5824);
			NibiruConfigManager.idArmorNoriumBoots = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Norium Boots", 5825).getInt(5825);
			NibiruConfigManager.idArmorBreathableRedGemHelmet = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Breathable Red Gem Helmet", 5826).getInt(5826);
			NibiruConfigManager.idArmorBreathableNoriumHelmet = NibiruConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Breathable Norium Helmet", 5827).getInt(5827);

			/**@Entities**/
			NibiruConfigManager.idInfectedWorm = NibiruConfigManager.configuration.get("Entities", "Infected Worm", 108).getInt(108);
			NibiruConfigManager.idGiantWorm = NibiruConfigManager.configuration.get("Entities", "Giant Worm", 109).getInt(109);
			NibiruConfigManager.idInfectedZombie = NibiruConfigManager.configuration.get("Entities", "Infected Zombie", 110).getInt(110);
			NibiruConfigManager.idEntityGiantSpikeBoss = NibiruConfigManager.configuration.get("Entities", "Giant Spike Boss", 164).getInt(164);

			/**@NonMobEntities**/
			NibiruConfigManager.idRocketT5 = NibiruConfigManager.configuration.get("Non-Entities", "Rocket Tier 5", 469).getInt(469);
			NibiruConfigManager.idRocketT5Unflag = NibiruConfigManager.configuration.get("Non-Entities", "Rocket Tier 5 Unflag", 470).getInt(470);
			NibiruConfigManager.idEntityGravityInfectionDart = NibiruConfigManager.configuration.get("Non-Entities", "Gravity Infection Dart", 476).getInt(476);
			NibiruConfigManager.idEntityInfectionDart = NibiruConfigManager.configuration.get("Non-Entities", "Infection Dart", 477).getInt(477);

			/**@Generals**/
			NibiruConfigManager.generateOtherMods = NibiruConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Generate other mod's features on Nibiru", false).getBoolean(false);
			NibiruConfigManager.enableDiamondGen = NibiruConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Diamond Ore Gen on Nibiru", true).getBoolean(true);
			NibiruConfigManager.enableCoalGen = NibiruConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Coal Ore Gen on Nibiru", true).getBoolean(true);

			/**@Schematics**/
			NibiruConfigManager.idSchematicRocketT5 = NibiruConfigManager.configuration.get("Schematics", "Schematic Rocket Tier 5", 788).getInt(788);
			NibiruConfigManager.idSchematicRocketT5NoFlag = NibiruConfigManager.configuration.get("Schematics", "Schematic Rocket Tier 5 No Flag", 789).getInt(789);

			/**@GUI**/
			NibiruConfigManager.idGuiRocketT5 = NibiruConfigManager.configuration.get("GUI", "GUI Rocket Tier 5", 432).getInt(432);
			NibiruConfigManager.idGuiRocketT5NoFlag = NibiruConfigManager.configuration.get("GUI", "GUI Rocket Tier 5 No Flag", 433).getInt(433);
		}
		catch (final Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "More Planet's : Nibiru has a problem loading it's configuration");
		}
		finally
		{
			NibiruConfigManager.configuration.save();
			NibiruConfigManager.loaded = true;
		}
	}
}