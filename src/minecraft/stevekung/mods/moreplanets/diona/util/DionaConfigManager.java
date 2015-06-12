/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.diona.util;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class DionaConfigManager
{
	public static boolean loaded;

	static Configuration configuration;

	/**@Dimensions**/
	public static int idDimensionDiona;

	//Blocks
	public static int idBlockDiona;
	public static int idBlockDionaTreasureChest;
	public static int idBlockDionaStairs;
	public static int idBlockSmoothQuontoniumStairs;
	public static int idBlockBrickQuontoniumStairs;
	public static int idBlockDionaTNT;
	public static int idBlockQuontoniumFence;
	public static int idBlockMinionCreeperEgg;
	public static int idBlockTitaniumFence;
	public static int idBlockDionaAncientChest;

	// ITEMS
	public static int idItemBasicDiona;
	public static int idItemThaiFlag;
	public static int idItemDionaKeyTreasure;
	public static int idItemSpaceshipTier3;
	public static int idItemSchematicT3;
	public static int idItemRocketT3Part;
	public static int idItemBaronsiumBucket;
	public static int idItemLaserGun;
	public static int idItemLaserCharge;

	// ARMOR
	public static int idArmorQuontoniumHelmet;
	public static int idArmorQuontoniumChestplate;
	public static int idArmorQuontoniumLeggings;
	public static int idArmorQuontoniumBoots;
	public static int idArmorFronisiumHelmet;
	public static int idArmorFronisiumChestplate;
	public static int idArmorFronisiumLeggings;
	public static int idArmorFronisiumBoots;
	public static int idArmorBreathableQuontoniumHelmet;
	public static int idArmorBreathableFronisiumHelmet;

	// TOOLS
	public static int idToolQuontoniumPickaxe;
	public static int idToolQuontoniumAxe;
	public static int idToolQuontoniumHoe;
	public static int idToolQuontoniumSpade;
	public static int idToolQuontoniumSword;
	public static int idToolFronisiumPickaxe;
	public static int idToolFronisiumAxe;
	public static int idToolFronisiumHoe;
	public static int idToolFronisiumSpade;
	public static int idToolFronisiumSword;

	/**@Entities**/
	public static int idEntitySpaceWolf;
	public static int idEntitySpaceEnderman;
	public static int idEntityDelriumWorm;
	public static int idEntityCreeperBoss;
	public static int idEntityMinionCreeper;

	/**@NonMobEntities**/
	public static int idEntityMPFlag;
	public static int idEntitySpaceshipTier3;
	public static int idEntityProjectileFronisiumTNT;
	public static int idEntityFronisiumTNT;
	public static int idEntityLaser;
	public static int idEntitySpaceshipTier3Unflag;

	/**@General**/
	public static boolean generateOtherMods;
	public static boolean enableAluminumGen;
	public static boolean enableSiliconGen;

	/**@Schematics**/
	public static int idSchematicRocketT3;
	public static int idSchematicRocketT3NoFlag;

	/**@GUI**/
	public static int idGuiRocketT3;
	public static int idGuiRocketT3NoFlag;

	public DionaConfigManager(File file)
	{
		if (!DionaConfigManager.loaded)
		{
			DionaConfigManager.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	private void setDefaultValues()
	{
		try
		{
			DionaConfigManager.configuration.load();

			/**@Dimensions**/
			DionaConfigManager.idDimensionDiona = DionaConfigManager.configuration.get("Dimensions", "Diona Dimension", -6).getInt(-6);

			//BLOCK
			DionaConfigManager.idBlockDiona = DionaConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Diona Basic Block", 1010).getInt(1010);
			DionaConfigManager.idBlockDionaTreasureChest = DionaConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Diona Treasure Chest", 1011).getInt(1011);
			DionaConfigManager.idBlockDionaTNT = DionaConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Diona TNT", 1012).getInt(1012);
			DionaConfigManager.idBlockMinionCreeperEgg = DionaConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Minion Creeper Egg", 1013).getInt(1013);
			DionaConfigManager.idBlockDionaStairs = DionaConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Diona Stone Stairs", 1014).getInt(1014);
			DionaConfigManager.idBlockQuontoniumFence = DionaConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Quontonium Fence", 1015).getInt(1015);
			DionaConfigManager.idBlockTitaniumFence = DionaConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Titanium Fence", 1016).getInt(1016);
			DionaConfigManager.idBlockSmoothQuontoniumStairs = DionaConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Smooth Quontonium Stairs", 1017).getInt(1017);
			DionaConfigManager.idBlockBrickQuontoniumStairs = DionaConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Brick Quontonium Stairs", 1018).getInt(1018);
			DionaConfigManager.idBlockDionaAncientChest = DionaConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Diona Ancient Chest", 1019).getInt(1019);

			//ITEM
			DionaConfigManager.idItemBasicDiona = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Diona Basic Item", 4860).getInt(4860);
			DionaConfigManager.idItemSchematicT3 = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Schematic Tier 3", 4861).getInt(4861);
			DionaConfigManager.idItemRocketT3Part = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Rocket Part Tier 3", 4862).getInt(4862);
			DionaConfigManager.idItemSpaceshipTier3 = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Spaceship Tier 3", 4863).getInt(4863);
			DionaConfigManager.idItemThaiFlag = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Thailand Flag", 4864).getInt(4864);
			DionaConfigManager.idItemBaronsiumBucket = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Baronsium Bucket", 4865).getInt(4865);
			DionaConfigManager.idItemLaserGun = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Laser Gun", 4866).getInt(4866);
			DionaConfigManager.idItemLaserCharge = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Laser Charge", 4867).getInt(4867);
			DionaConfigManager.idItemDionaKeyTreasure = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Diona Dungeon Key", 4868).getInt(4868);

			//TOOL
			DionaConfigManager.idToolQuontoniumSpade = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Quontonium Spade", 5240).getInt(5240);
			DionaConfigManager.idToolQuontoniumPickaxe = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Quontonium Pickaxe", 5241).getInt(5241);
			DionaConfigManager.idToolQuontoniumAxe = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Quontonium Axe", 5242).getInt(5242);
			DionaConfigManager.idToolQuontoniumHoe = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Quontonium Hoe", 5243).getInt(5243);
			DionaConfigManager.idToolQuontoniumSword = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Quontonium Sword", 5244).getInt(5244);
			DionaConfigManager.idToolFronisiumSpade = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Fronisium Spade", 5245).getInt(5245);
			DionaConfigManager.idToolFronisiumPickaxe = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Fronisium Pickaxe", 5246).getInt(5246);
			DionaConfigManager.idToolFronisiumAxe = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Fronisium Axe", 5247).getInt(5247);
			DionaConfigManager.idToolFronisiumHoe = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Fronisium Hoe", 5248).getInt(5248);
			DionaConfigManager.idToolFronisiumSword = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Fronisium Sword", 5249).getInt(5249);

			//ARMOR
			DionaConfigManager.idArmorQuontoniumHelmet = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Quontonium Helmet", 5763).getInt(5763);
			DionaConfigManager.idArmorQuontoniumChestplate = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Quontonium Chestplate", 5764).getInt(5764);
			DionaConfigManager.idArmorQuontoniumLeggings = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Quontonium Leggings", 5765).getInt(5765);
			DionaConfigManager.idArmorQuontoniumBoots = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Quontonium Boots", 5766).getInt(5766);
			DionaConfigManager.idArmorFronisiumHelmet = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Fronisium Helmet", 5767).getInt(5767);
			DionaConfigManager.idArmorFronisiumChestplate = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Fronisium Chestplate", 5768).getInt(5768);
			DionaConfigManager.idArmorFronisiumLeggings = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Fronisium Leggings", 5769).getInt(5769);
			DionaConfigManager.idArmorFronisiumBoots = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Fronisium Boots", 5770).getInt(5770);
			DionaConfigManager.idArmorBreathableQuontoniumHelmet = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Breathable Quontonium Helmet", 5771).getInt(5771);
			DionaConfigManager.idArmorBreathableFronisiumHelmet = DionaConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Breathable Fronisium Helmet", 5772).getInt(5772);

			/**@General**/
			DionaConfigManager.generateOtherMods = DionaConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Generate other mod's features on Diona", false).getBoolean(false);
			DionaConfigManager.enableAluminumGen = DionaConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Aluminum Ore Gen on Diona", true).getBoolean(true);
			DionaConfigManager.enableSiliconGen = DionaConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Silicon Ore Gen on Diona", true).getBoolean(true);

			/**@NonMobEntities**/
			DionaConfigManager.idEntityFronisiumTNT = DionaConfigManager.configuration.get("Non-Entities", "Fronisium TNT", 461).getInt(461);
			DionaConfigManager.idEntityMPFlag = DionaConfigManager.configuration.get("Non-Entities", "MP Flag", 462).getInt(462);
			DionaConfigManager.idEntitySpaceshipTier3 = DionaConfigManager.configuration.get("Non-Entities", "Spaceship Tier 3", 463).getInt(463);
			DionaConfigManager.idEntityProjectileFronisiumTNT = DionaConfigManager.configuration.get("Non-Entities", "Projectile Fronisium TNT", 464).getInt(464);
			DionaConfigManager.idEntityLaser = DionaConfigManager.configuration.get("Non-Entities", "Laser", 465).getInt(465);
			DionaConfigManager.idEntitySpaceshipTier3Unflag = DionaConfigManager.configuration.get("Non-Entities", "Spaceship Tier 3 Unflag", 466).getInt(466);

			/**@Entities**/
			DionaConfigManager.idEntitySpaceWolf = DionaConfigManager.configuration.get("Entities", "Space Wolf", 101).getInt(101);
			DionaConfigManager.idEntitySpaceEnderman = DionaConfigManager.configuration.get("Entities", "Space Enderman", 102).getInt(102);
			DionaConfigManager.idEntityDelriumWorm = DionaConfigManager.configuration.get("Entities", "Delrium Worm", 103).getInt(103);
			DionaConfigManager.idEntityCreeperBoss = DionaConfigManager.configuration.get("Entities", "Diona Creeper Boss", 104).getInt(104);
			DionaConfigManager.idEntityMinionCreeper = DionaConfigManager.configuration.get("Entities", "Diona Minion Creeper", 105).getInt(105);

			/**@Schematics**/
			DionaConfigManager.idSchematicRocketT3 = DionaConfigManager.configuration.get("Schematics", "Rocket Tier 3", 784).getInt(784);
			DionaConfigManager.idSchematicRocketT3NoFlag = DionaConfigManager.configuration.get("Schematics", "Rocket Tier 3 No Flag", 785).getInt(785);

			/**@GUI**/
			DionaConfigManager.idGuiRocketT3 = DionaConfigManager.configuration.get("GUI", "GUI Rocket Tier 3", 428).getInt(428);
			DionaConfigManager.idGuiRocketT3NoFlag = DionaConfigManager.configuration.get("GUI", "GUI Rocket Tier 3 No Flag", 429).getInt(429);
		}

		catch (final Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "More Planet's has a problem loading it's configuration");
		}

		finally
		{
			if (DionaConfigManager.configuration.hasChanged())
			{
				DionaConfigManager.configuration.save();
			}

			DionaConfigManager.loaded = true;
		}
	}
}