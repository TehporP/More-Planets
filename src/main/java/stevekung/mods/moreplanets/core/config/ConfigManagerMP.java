/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class ConfigManagerMP
{
	public static boolean loaded;

	static Configuration configuration;

	//Dimensions
	public static int idDimensionDiona;
	public static int idDimensionPolongnius;
	public static int idDimensionKoentus;
	public static int idDimensionNibiru;
	public static int idDimensionFronos;
	public static int idDimensionKapteynB;
	public static int idDimensionSiriusB;

	public static int idDimensionMercury;
	public static int idDimensionVenus;
	public static int idDimensionPluto;

	public static int idDimensionDeimos;
	public static int idDimensionPhobos;

	//Biomes
	public static int idDionaBiome;
	public static int idPolongniusBiome;
	public static int idKoentusBiome;
	public static int idNibiruBiome;
	public static int idCoconutForestBiome;
	public static int idGoldenFieldBiome;
	public static int idPurpleMapleForestBiome;
	public static int idMapleForestBiome;
	public static int idGrassyPlainsBiome;
	public static int idCandyLandBiome;
	public static int idBiomeKapteynB;
	public static int idBiomeSiriusB;

	public static int idMercuryBiome;
	public static int idVenusBiome;
	public static int idPlutoBiome;

	public static int idDeimosBiome;
	public static int idPhobosBiome;
	public static int idIoBiome;

	//Schematics
	public static int idSchematicTier4Rocket;
	public static int idSchematicTier4RocketNoFlag;
	public static int idSchematicTier5Rocket;
	public static int idSchematicTier5RocketNoFlag;
	public static int idSchematicTier6Rocket;
	public static int idSchematicTier6RocketNoFlag;
	public static int idSchematicTier7Rocket;
	public static int idSchematicTier8Rocket;

	//GUI
	public static int idGuiSchematicTier4Rocket;
	public static int idGuiSchematicTier4RocketNoFlag;
	public static int idGuiSchematicTier5Rocket;
	public static int idGuiSchematicTier5RocketNoFlag;
	public static int idGuiSchematicTier6Rocket;
	public static int idGuiSchematicTier6RocketNoFlag;
	public static int idGuiSchematicTier7Rocket;
	public static int idGuiSchematicTier8Rocket;

	//General
	public static boolean enableRocketWithThaiFlag;
	public static boolean enableThaiFlagAndCanvas;
	public static boolean disableKoentusVillageGen;
	public static boolean disableMultipleCandyCaneRecipe;
	public static boolean allowMobCreatureSpawningOnFronos;
	public static boolean enableServerVersionCheck;
	public static boolean enableClientVersionCheck;
	public static boolean enableNewMainManu;
	public static boolean enableMorePlanetsBasicPlanets;

	public ConfigManagerMP(File file)
	{
		if (!ConfigManagerMP.loaded)
		{
			ConfigManagerMP.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	private void setDefaultValues()
	{
		try
		{
			ConfigManagerMP.configuration.load();

			//Dimensions
			ConfigManagerMP.idDimensionDiona = ConfigManagerMP.configuration.get("Dimensions", "Diona Dimension", -6).getInt(-6);
			ConfigManagerMP.idDimensionKoentus = ConfigManagerMP.configuration.get("Dimensions", "Koentus Dimension", -7).getInt(-7);
			ConfigManagerMP.idDimensionPolongnius = ConfigManagerMP.configuration.get("Dimensions", "Polongnius Dimension", -8).getInt(-8);
			ConfigManagerMP.idDimensionNibiru = ConfigManagerMP.configuration.get("Dimensions", "Nibiru Dimension", -9).getInt(-9);
			ConfigManagerMP.idDimensionFronos = ConfigManagerMP.configuration.get("Dimensions", "Fronos Dimension", -10).getInt(-10);
			ConfigManagerMP.idDimensionKapteynB = ConfigManagerMP.configuration.get("Dimensions", "Kapteyn B Dimension", -11).getInt(-11);
			ConfigManagerMP.idDimensionSiriusB = ConfigManagerMP.configuration.get("Dimensions", "Sirius B Dimension", -12).getInt(-12);

			ConfigManagerMP.idDimensionMercury = ConfigManagerMP.configuration.get("Dimensions", "Mercury Dimension", -20).getInt(-20);
			ConfigManagerMP.idDimensionVenus = ConfigManagerMP.configuration.get("Dimensions", "Venus Dimension", -21).getInt(-21);
			ConfigManagerMP.idDimensionPluto = ConfigManagerMP.configuration.get("Dimensions", "Pluto Dimension", -22).getInt(-22);

			ConfigManagerMP.idDimensionDeimos = ConfigManagerMP.configuration.get("Dimensions", "Deimos Dimension", -40).getInt(-40);
			ConfigManagerMP.idDimensionPhobos = ConfigManagerMP.configuration.get("Dimensions", "Phobos Dimension", -41).getInt(-41);

			//General
			ConfigManagerMP.enableRocketWithThaiFlag = ConfigManagerMP.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Rocket with Thai Flag", false, "If true, Rocket with Thai Flag will show in the creative tabs.").setRequiresMcRestart(true).getBoolean(false);
			ConfigManagerMP.enableThaiFlagAndCanvas = ConfigManagerMP.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Thai Flag and Thai Canvas", false, "If true, Thai Flag and Thai Canvas will show in the creative tabs.").setRequiresMcRestart(true).getBoolean(false);
			ConfigManagerMP.disableKoentusVillageGen = ConfigManagerMP.configuration.get(Configuration.CATEGORY_GENERAL, "Disable Koentus Village Gen", false).getBoolean(false);
			ConfigManagerMP.disableMultipleCandyCaneRecipe = ConfigManagerMP.configuration.get(Configuration.CATEGORY_GENERAL, "Disable Multiple Candy Tools Recipe", true, "If set this to false. Candy Tools, Candy Bow and Poison Arrow can crafted by any candy cane. Default is true.").getBoolean(true);
			ConfigManagerMP.allowMobCreatureSpawningOnFronos = ConfigManagerMP.configuration.get(Configuration.CATEGORY_GENERAL, "Allow Overworld Mobs/Creatures Spawning on Fronos Planet", false, "If set this to true. Overworld mobs and creatures will be spawning on Fronos by default, but this will disable Galacticraft evolved mob spawning. Default is false.").getBoolean(false);
			ConfigManagerMP.enableServerVersionCheck = ConfigManagerMP.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Server Version Check", true).getBoolean(true);
			ConfigManagerMP.enableClientVersionCheck = ConfigManagerMP.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Client Version Check", true).getBoolean(true);
			ConfigManagerMP.enableNewMainManu = ConfigManagerMP.configuration.get(Configuration.CATEGORY_GENERAL, "Enable New Main Manu Screen", true).getBoolean(true);
			ConfigManagerMP.enableMorePlanetsBasicPlanets = ConfigManagerMP.configuration.get(Configuration.CATEGORY_GENERAL, "Enable More Planets basic planets", true, "If set this to false. Mercury, Venus etc. its will disabled from Celestial Screen and cannot reach that planets. Default is true.").getBoolean(true);

			//Biomes
			ConfigManagerMP.idDionaBiome = ConfigManagerMP.configuration.get("Biomes", "Diona Biome", 220).getInt(220);
			ConfigManagerMP.idPolongniusBiome = ConfigManagerMP.configuration.get("Biomes", "Polongnius Biome", 221).getInt(221);
			ConfigManagerMP.idNibiruBiome = ConfigManagerMP.configuration.get("Biomes", "Nibiru Biome", 222).getInt(222);
			ConfigManagerMP.idKoentusBiome = ConfigManagerMP.configuration.get("Biomes", "Koentus Biome", 223).getInt(223);
			ConfigManagerMP.idCoconutForestBiome = ConfigManagerMP.configuration.get("Biomes", "Coconut Forest (Fronos Biome)", 224).getInt(224);
			ConfigManagerMP.idBiomeKapteynB = ConfigManagerMP.configuration.get("Biomes", "Kapteyn B Biome", 225).getInt(225);
			ConfigManagerMP.idBiomeSiriusB = ConfigManagerMP.configuration.get("Biomes", "Sirius B Biome", 226).getInt(226);

			ConfigManagerMP.idGoldenFieldBiome = ConfigManagerMP.configuration.get("Biomes", "Golden Field (Fronos Biome)", 244).getInt(244);
			ConfigManagerMP.idPurpleMapleForestBiome = ConfigManagerMP.configuration.get("Biomes", "Purple Maple Forest (Fronos Biome)", 245).getInt(245);
			ConfigManagerMP.idMapleForestBiome = ConfigManagerMP.configuration.get("Biomes", "Maple Forest (Fronos Biome)", 246).getInt(246);
			ConfigManagerMP.idGrassyPlainsBiome = ConfigManagerMP.configuration.get("Biomes", "Grassy Plains (Fronos Biome)", 247).getInt(247);
			ConfigManagerMP.idCandyLandBiome = ConfigManagerMP.configuration.get("Biomes", "Candy Land (Fronos Biome)", 248).getInt(248);

			ConfigManagerMP.idMercuryBiome = ConfigManagerMP.configuration.get("Biomes", "Mercury Biome", 240).getInt(240);
			ConfigManagerMP.idVenusBiome = ConfigManagerMP.configuration.get("Biomes", "Venus Biome", 241).getInt(241);
			ConfigManagerMP.idPlutoBiome = ConfigManagerMP.configuration.get("Biomes", "Pluto Biome", 242).getInt(242);

			ConfigManagerMP.idDeimosBiome = ConfigManagerMP.configuration.get("Biomes", "Deimos Biome", 210).getInt(210);
			ConfigManagerMP.idPhobosBiome = ConfigManagerMP.configuration.get("Biomes", "Phobos Biome", 211).getInt(211);
			ConfigManagerMP.idIoBiome = ConfigManagerMP.configuration.get("Biomes", "Io Biome", 212).getInt(212);

			//Schematics
			ConfigManagerMP.idSchematicTier4Rocket = ConfigManagerMP.configuration.get("Schematics", "Schematic Tier 4 Rocket", 784).getInt(784);
			ConfigManagerMP.idSchematicTier4RocketNoFlag = ConfigManagerMP.configuration.get("Schematics", "Schematic Tier 4 Rocket No Flag", 785).getInt(785);
			ConfigManagerMP.idSchematicTier5Rocket = ConfigManagerMP.configuration.get("Schematics", "Schematic Tier 5 Rocket", 786).getInt(786);
			ConfigManagerMP.idSchematicTier5RocketNoFlag = ConfigManagerMP.configuration.get("Schematics", "Schematic Tier 5 Rocket No Flag", 787).getInt(787);
			ConfigManagerMP.idSchematicTier6Rocket = ConfigManagerMP.configuration.get("Schematics", "Schematic Tier 6 Rocket", 788).getInt(788);
			ConfigManagerMP.idSchematicTier6RocketNoFlag = ConfigManagerMP.configuration.get("Schematics", "Schematic Tier 6 Rocket No Flag", 789).getInt(789);
			ConfigManagerMP.idSchematicTier7Rocket = ConfigManagerMP.configuration.get("Schematics", "Schematic Tier 7 Rocket", 790).getInt(790);
			ConfigManagerMP.idSchematicTier8Rocket = ConfigManagerMP.configuration.get("Schematics", "Schematic Tier 8 Rocket", 791).getInt(791);

			//GUI
			ConfigManagerMP.idGuiSchematicTier4Rocket = ConfigManagerMP.configuration.get("GUI", "GUI Tier 4 Rocket", 428).getInt(428);
			ConfigManagerMP.idGuiSchematicTier4RocketNoFlag = ConfigManagerMP.configuration.get("GUI", "GUI Tier 4 Rocket No Flag", 429).getInt(429);
			ConfigManagerMP.idGuiSchematicTier5Rocket = ConfigManagerMP.configuration.get("GUI", "GUI Tier 5 Rocket", 430).getInt(430);
			ConfigManagerMP.idGuiSchematicTier5RocketNoFlag = ConfigManagerMP.configuration.get("GUI", "GUI Tier 5 Rocket No Flag", 431).getInt(431);
			ConfigManagerMP.idGuiSchematicTier6Rocket = ConfigManagerMP.configuration.get("GUI", "GUI Tier 6 Rocket", 432).getInt(432);
			ConfigManagerMP.idGuiSchematicTier6RocketNoFlag = ConfigManagerMP.configuration.get("GUI", "GUI Tier 6 Rocket No Flag", 433).getInt(433);
			ConfigManagerMP.idGuiSchematicTier7Rocket = ConfigManagerMP.configuration.get("GUI", "GUI Tier 7 Rocket", 434).getInt(434);
			ConfigManagerMP.idGuiSchematicTier8Rocket = ConfigManagerMP.configuration.get("GUI", "GUI Tier 8 Rocket", 435).getInt(435);

		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "More Planet's has a problem loading it's configuration");
		}
		finally
		{
			ConfigManagerMP.configuration.save();
			ConfigManagerMP.loaded = true;
		}
	}
}