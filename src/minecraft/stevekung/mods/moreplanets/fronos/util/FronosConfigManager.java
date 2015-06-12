/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.util;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class FronosConfigManager
{
	public static boolean loaded;

	static Configuration configuration;

	/**@Dimensions**/
	public static int idDimensionFronos;

	// BLOCKS
	public static int idFronosGrass;
	public static int idFronosDirt;
	public static int idFronosStone;
	public static int idFronosFarmland;
	public static int idFronosTallGrass;
	public static int idStrawberryCrop;
	public static int idSpaceOyster;
	public static int idBlockFrostedCake;
	public static int idBlockCream;
	public static int idBlockCreamLayer;
	public static int idBlockJelly;
	public static int idBlockSpaceShell;
	public static int idBlockCreamChocolate;
	public static int idBlockCreamChocolateLayer;
	public static int idBlockCookie;
	public static int idBlockCandyCane1;
	public static int idBlockCandyCane2;
	public static int idBlockFlower;
	public static int idBlockFronosDandelion;
	public static int idBlockFronosCoral;
	public static int idBlockSpaceOysterClose;
	public static int idBlockCoconutMilk;
	public static int idBlockMagicWater;
	public static int idBlockFronosLog;
	public static int idBlockCoconut;
	public static int idBlockLeaves;
	public static int idBlockPlanks;
	public static int idBlockSapling;
	public static int idBlockFronosCobblestoneStairs;
	public static int idBlockFronosTreasureChest;
	public static int idBlockCandyExtractorIdle;
	public static int idBlockCandyExtractorActive;
	public static int idBlockOvantine;
	public static int idBlockStrawberryCloud;
	public static int idBlockCoconutWoodStairs;
	public static int idBlockPinkCake;
	public static int idBlockCake;
	public static int idBlockPinkCandyTorch;
	public static int idBlockOrangeCandyTorch;
	public static int idBlockGreenCandyTorch;
	public static int idBlockYellowCandyTorch;
	public static int idBlockLightBlueCandyTorch;
	public static int idBlockBlueCandyTorch;
	public static int idBlockLightPurpleCandyTorch;
	public static int idBlockPurpleCandyTorch;
	public static int idBlockChocolate;
	public static int idBlockFronosOre;
	public static int idBlockStrawberryCream;
	public static int idBlockStrawberryCreamLayer;
	public static int idBlockFronosSand;
	public static int idBlockFronosPinkGrass;
	public static int idBlockFronosPurpleGrass;
	public static int idBlockFronosPlainGrass;
	public static int idBlockFronosGlass;
	public static int idBlockFronosGlassPane;
	public static int idBlockOvantineFluid;
	public static int idBlockGoldenCrops;
	public static int idBlockCreamHead;
	public static int idBlockOrangeCream;
	public static int idBlockOrangeCreamLayer;
	public static int idBlockGoldenGrass;
	public static int idBlockFronosLeaves;
	public static int idBlockFronosPoppy;
	public static int idBlockMapleIvy;
	public static int idBlockGlassGemCorn;

	// ITEMS
	public static int idFronosItemFood;
	public static int idStrawberrySeed;
	public static int idBearryEgg;
	public static int idItemDisc;
	public static int idItemCreamBall;
	public static int idItemPearl;
	//public static int idItemJarEmpty; TODO
	//public static int idItemJarFilled; TODO
	public static int idItemFronosDungeonKey;
	public static int idItemCandyCane;
	public static int idItemGreenTopazBucket;
	public static int idItemJelly;
	public static int idItemGem;
	public static int idItemFronosFood;
	public static int idItemPinkCake;
	public static int idItemCake;
	public static int idItemFruits;
	public static int idItemCandyBow;
	public static int idItemPoisonArrow;
	public static int idItemGoldenSeeds;
	public static int idItemGlassGemCorn;
	public static int idItemOvantineCup;

	// TOOLS
	public static int idToolsCandySpade;
	public static int idToolsCandyPickaxe;
	public static int idToolsCandyAxe;
	public static int idToolsCandyHoe;
	public static int idToolsCandySword;

	/**@Entities**/
	public static int idEntityBearry;
	public static int idEntityBerry;
	public static int idEntityMarshmallow;
	public static int idEntityKiwi;
	public static int idEntityJellyBerrySlime;
	public static int idEntityJellyStrawberrySlime;
	public static int idEntityJellyGrapeSlime;
	public static int idEntityJellyRaspberrySlime;
	public static int idEntityVanillaCreamSlime;
	public static int idEntityChocolateCreamSlime;
	public static int idEntityJellyLimeSlime;
	public static int idEntityLemonDuck;
	public static int idEntitySpaceStarfish;
	public static int idEntityStrawberryCreamSlime;
	public static int idEntityMelon;
	public static int idEntityTomato;
	public static int idEntityGrappy;
	public static int idEntitySpaceCat;
	public static int idEntityVanillaCreamGolem;
	public static int idEntityChocolateCreamGolem;
	public static int idEntityStrawberryCreamGolem;
	public static int idEntityOrangeCreamGolem;
	public static int idEntityOrangeCreamSlime;
	public static int idEntityJellyOrangeSlime;
	public static int idEntityPinkChicken;

	/**@NonMobEntities**/
	public static int idEntityVanillaCreamBall;
	public static int idEntityChocolateCreamBall;
	public static int idEntityStrawberryCreamBall;
	public static int idEntityOrangeCreamBall;
	public static int idEntityPoisonArrow;

	/**@Biomes**/
	public static int idBiomeOases;
	public static int idBiomeCherry;
	public static int idBiomeCanyon;
	public static int idBiomeMesa;

	/**@Generals**/
	public static boolean generateOtherMods;

	/**@WorldGen**/
	public static int strawberryCloudPerChunk;

	public FronosConfigManager(File file)
	{
		if (!FronosConfigManager.loaded)
		{
			FronosConfigManager.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	private void setDefaultValues()
	{
		try
		{
			FronosConfigManager.configuration.load();

			/**@Dimensions**/
			FronosConfigManager.idDimensionFronos = configuration.get("Dimensions", "Fronos Dimension", -8).getInt(-8);

			// BLOCK
			FronosConfigManager.idFronosGrass = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Grass", 1170).getInt(1170);
			FronosConfigManager.idFronosDirt = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Dirt", 1171).getInt(1171);
			FronosConfigManager.idFronosFarmland = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Farmland", 1172).getInt(1172);
			FronosConfigManager.idFronosStone = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Stone", 1173).getInt(1173);
			FronosConfigManager.idBlockFronosTreasureChest = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Treasure Chest", 1174).getInt(1174);
			FronosConfigManager.idBlockFronosCobblestoneStairs = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Cobblestone Stairs", 1175).getInt(1175);
			FronosConfigManager.idBlockFrostedCake = configuration.get(Configuration.CATEGORY_BLOCK, "Frosted Cake Block", 1176).getInt(1176);
			FronosConfigManager.idBlockCookie = configuration.get(Configuration.CATEGORY_BLOCK, "Cookie Block", 1177).getInt(1177);
			FronosConfigManager.idBlockCandyCane1 = configuration.get(Configuration.CATEGORY_BLOCK, "Candy Cane 1", 1178).getInt(1178);
			FronosConfigManager.idBlockCandyCane2 = configuration.get(Configuration.CATEGORY_BLOCK, "Candy Cane 2", 1179).getInt(1179);
			FronosConfigManager.idBlockJelly = configuration.get(Configuration.CATEGORY_BLOCK, "Jelly Block", 1180).getInt(1180);
			FronosConfigManager.idBlockCream = configuration.get(Configuration.CATEGORY_BLOCK, "Vanilla Cream Block", 1181).getInt(1181);
			FronosConfigManager.idBlockCreamChocolate = configuration.get(Configuration.CATEGORY_BLOCK, "Chocolate Cream Block", 1182).getInt(1182);
			FronosConfigManager.idBlockCreamLayer = configuration.get(Configuration.CATEGORY_BLOCK, "Vanilla Cream Layer", 1183).getInt(1183);
			FronosConfigManager.idBlockCreamChocolateLayer = configuration.get(Configuration.CATEGORY_BLOCK, "Chocolate Cream Layer", 1184).getInt(1184);
			FronosConfigManager.idBlockFronosLog = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Log", 1185).getInt(1185);
			FronosConfigManager.idBlockPlanks = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Planks", 1186).getInt(1186);
			FronosConfigManager.idBlockLeaves = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Leaves", 1187).getInt(1187);
			FronosConfigManager.idBlockSapling = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Sapling", 1188).getInt(1188);
			FronosConfigManager.idBlockCoconut = configuration.get(Configuration.CATEGORY_BLOCK, "Coconut Block", 1189).getInt(1189);
			FronosConfigManager.idFronosTallGrass = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Tall Grass", 1190).getInt(1190);
			FronosConfigManager.idBlockFlower = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Flower", 1191).getInt(1191);
			FronosConfigManager.idBlockFronosDandelion = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Dandelion", 1192).getInt(1192);
			FronosConfigManager.idStrawberryCrop = configuration.get(Configuration.CATEGORY_BLOCK, "Strawberry Crop", 1193).getInt(1193);
			FronosConfigManager.idSpaceOyster = configuration.get(Configuration.CATEGORY_BLOCK, "Space Oyster", 1194).getInt(1194);
			FronosConfigManager.idBlockSpaceOysterClose = configuration.get(Configuration.CATEGORY_BLOCK, "Space Oyster Close", 1195).getInt(1195);
			FronosConfigManager.idBlockSpaceShell = configuration.get(Configuration.CATEGORY_BLOCK, "Colored Space Shell", 1196).getInt(1196);
			FronosConfigManager.idBlockFronosCoral = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Coral", 1197).getInt(1197);
			FronosConfigManager.idBlockCoconutMilk = configuration.get(Configuration.CATEGORY_BLOCK, "Coconut Milk", 1198).getInt(1198);
			FronosConfigManager.idBlockMagicWater = configuration.get(Configuration.CATEGORY_BLOCK, "Magic Water", 1199).getInt(1199);
			FronosConfigManager.idBlockCandyExtractorIdle = configuration.get(Configuration.CATEGORY_BLOCK, "Candy Extractor Idle", 1200).getInt(1200);
			FronosConfigManager.idBlockCandyExtractorActive = configuration.get(Configuration.CATEGORY_BLOCK, "Candy Extractor Active", 1201).getInt(1201);
			FronosConfigManager.idBlockOvantine = configuration.get(Configuration.CATEGORY_BLOCK, "Ovantine Block", 1202).getInt(1202);
			FronosConfigManager.idBlockStrawberryCloud = configuration.get(Configuration.CATEGORY_BLOCK, "Strawberry Cloud", 1203).getInt(1203);
			FronosConfigManager.idBlockCoconutWoodStairs = configuration.get(Configuration.CATEGORY_BLOCK, "Coconut Wood Stairs", 1204).getInt(1204);
			FronosConfigManager.idBlockPinkCake = configuration.get(Configuration.CATEGORY_BLOCK, "Pink Cake Block", 1205).getInt(1205);
			FronosConfigManager.idBlockCake = configuration.get(Configuration.CATEGORY_BLOCK, "Cake Block", 1206).getInt(1206);
			FronosConfigManager.idBlockPinkCandyTorch = configuration.get(Configuration.CATEGORY_BLOCK, "Pink Candy Torch", 1207).getInt(1207);
			FronosConfigManager.idBlockOrangeCandyTorch = configuration.get(Configuration.CATEGORY_BLOCK, "Orange Candy Torch", 1208).getInt(1208);
			FronosConfigManager.idBlockGreenCandyTorch = configuration.get(Configuration.CATEGORY_BLOCK, "Green Candy Torch", 1209).getInt(1209);
			FronosConfigManager.idBlockYellowCandyTorch = configuration.get(Configuration.CATEGORY_BLOCK, "Yellow Candy Torch", 1210).getInt(1210);
			FronosConfigManager.idBlockLightBlueCandyTorch = configuration.get(Configuration.CATEGORY_BLOCK, "Light Blue Candy Torch", 1211).getInt(1211);
			FronosConfigManager.idBlockBlueCandyTorch = configuration.get(Configuration.CATEGORY_BLOCK, "Blue Candy Torch", 1212).getInt(1212);
			FronosConfigManager.idBlockLightPurpleCandyTorch = configuration.get(Configuration.CATEGORY_BLOCK, "Light Purple Torch", 1213).getInt(1213);
			FronosConfigManager.idBlockPurpleCandyTorch = configuration.get(Configuration.CATEGORY_BLOCK, "Purple Candy Torch", 1214).getInt(1214);
			FronosConfigManager.idBlockChocolate = configuration.get(Configuration.CATEGORY_BLOCK, "Chocolate Block", 1215).getInt(1215);
			FronosConfigManager.idBlockFronosOre = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Block Ore", 1216).getInt(1216);
			FronosConfigManager.idBlockStrawberryCream = configuration.get(Configuration.CATEGORY_BLOCK, "Strawberry Cream", 1217).getInt(1217);
			FronosConfigManager.idBlockStrawberryCreamLayer = configuration.get(Configuration.CATEGORY_BLOCK, "Strawberry Cream Layer", 1218).getInt(1218);
			FronosConfigManager.idBlockFronosSand = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Sand", 1219).getInt(1219);
			FronosConfigManager.idBlockFronosPinkGrass = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Pink Grass", 1220).getInt(1220);
			FronosConfigManager.idBlockFronosPurpleGrass = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Purple Grass", 1221).getInt(1221);
			FronosConfigManager.idBlockFronosPlainGrass = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Plain Grass", 1222).getInt(1222);
			FronosConfigManager.idBlockFronosGlass = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Glass", 1223).getInt(1223);
			FronosConfigManager.idBlockFronosGlassPane = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Glass Pane", 1224).getInt(1224);
			FronosConfigManager.idBlockOvantineFluid = configuration.get(Configuration.CATEGORY_BLOCK, "Ovantine Fluid", 1225).getInt(1225);
			FronosConfigManager.idBlockGoldenCrops = configuration.get(Configuration.CATEGORY_BLOCK, "Golden Crops", 1226).getInt(1226);
			FronosConfigManager.idBlockCreamHead = configuration.get(Configuration.CATEGORY_BLOCK, "Cream Head", 1227).getInt(1227);
			FronosConfigManager.idBlockOrangeCream = configuration.get(Configuration.CATEGORY_BLOCK, "Orange Cream", 1228).getInt(1228);
			FronosConfigManager.idBlockOrangeCreamLayer = configuration.get(Configuration.CATEGORY_BLOCK, "Orange Cream Layer", 1229).getInt(1229);
			FronosConfigManager.idBlockGoldenGrass = configuration.get(Configuration.CATEGORY_BLOCK, "Golden Grass", 1230).getInt(1230);
			FronosConfigManager.idBlockFronosLeaves = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Blocks Leaves", 1231).getInt(1231);
			FronosConfigManager.idBlockFronosPoppy = configuration.get(Configuration.CATEGORY_BLOCK, "Fronos Poppy", 1232).getInt(1232);
			FronosConfigManager.idBlockMapleIvy = configuration.get(Configuration.CATEGORY_BLOCK, "Maple Ivy", 1233).getInt(1233);
			FronosConfigManager.idBlockGlassGemCorn = configuration.get(Configuration.CATEGORY_BLOCK, "Glass Gem Corn", 1234).getInt(1234);

			// ITEMS
			FronosConfigManager.idFronosItemFood = configuration.get(Configuration.CATEGORY_ITEM, "Fronos Food", 4974).getInt(4974);
			FronosConfigManager.idBearryEgg = configuration.get(Configuration.CATEGORY_ITEM, "Bearry Egg", 4975).getInt(4975);
			FronosConfigManager.idItemDisc = configuration.get(Configuration.CATEGORY_ITEM, "Fronos Music Disc", 4976).getInt(4976);
			FronosConfigManager.idItemCreamBall = configuration.get(Configuration.CATEGORY_ITEM, "Cream Ball", 4977).getInt(4977);
			// TODO FronosConfigManager.idItemJarEmpty = configuration.get(Configuration.CATEGORY_ITEM, "Jar Empty", 4978).getInt(4978);
			// TODO FronosConfigManager.idItemJarFilled = configuration.get(Configuration.CATEGORY_ITEM, "Jar Filled", 4979).getInt(4979);
			FronosConfigManager.idStrawberrySeed = configuration.get(Configuration.CATEGORY_ITEM, "Strawberry Seed", 4980).getInt(4980);
			FronosConfigManager.idItemPearl = configuration.get(Configuration.CATEGORY_ITEM, "Pearl", 4981).getInt(4981);
			FronosConfigManager.idItemFronosDungeonKey = configuration.get(Configuration.CATEGORY_ITEM, "Fronos Dungeon Key", 4982).getInt(4982);
			FronosConfigManager.idItemCandyCane = configuration.get(Configuration.CATEGORY_ITEM, "Candy Cane", 4983).getInt(4983);
			FronosConfigManager.idItemGreenTopazBucket = configuration.get(Configuration.CATEGORY_ITEM, "Green Topaz Bucket", 4984).getInt(4984);
			FronosConfigManager.idItemJelly = configuration.get(Configuration.CATEGORY_ITEM, "Jelly", 4985).getInt(4985);
			FronosConfigManager.idItemGem = configuration.get(Configuration.CATEGORY_ITEM, "Gem", 4986).getInt(4986);
			FronosConfigManager.idItemFronosFood = configuration.get(Configuration.CATEGORY_ITEM, "Fronos Food 2", 4987).getInt(4987);
			FronosConfigManager.idItemPinkCake = configuration.get(Configuration.CATEGORY_ITEM, "Pink Cake Item", 4988).getInt(4988);
			FronosConfigManager.idItemCake = configuration.get(Configuration.CATEGORY_ITEM, "Cake Item", 4989).getInt(4989);
			FronosConfigManager.idItemFruits = configuration.get(Configuration.CATEGORY_ITEM, "Fruits", 4990).getInt(4990);
			FronosConfigManager.idItemCandyBow = configuration.get(Configuration.CATEGORY_ITEM, "Candy Bow", 4991).getInt(4991);
			FronosConfigManager.idItemPoisonArrow = configuration.get(Configuration.CATEGORY_ITEM, "Poison Arrow", 4992).getInt(4992);
			FronosConfigManager.idItemGoldenSeeds = configuration.get(Configuration.CATEGORY_ITEM, "Golden Seeds", 4993).getInt(4993);
			FronosConfigManager.idItemGlassGemCorn = configuration.get(Configuration.CATEGORY_ITEM, "Glass Gem Corn", 4994).getInt(4994);
			FronosConfigManager.idItemOvantineCup = configuration.get(Configuration.CATEGORY_ITEM, "Ovantine Cup", 4995).getInt(4995);

			// TOOLS
			FronosConfigManager.idToolsCandySpade = configuration.get(Configuration.CATEGORY_ITEM, "Candy Spade", 5340).getInt(5340);
			FronosConfigManager.idToolsCandyPickaxe = configuration.get(Configuration.CATEGORY_ITEM, "Candy Pickaxe", 5341).getInt(5341);
			FronosConfigManager.idToolsCandyAxe = configuration.get(Configuration.CATEGORY_ITEM, "Candy Axe", 5342).getInt(5342);
			FronosConfigManager.idToolsCandyHoe = configuration.get(Configuration.CATEGORY_ITEM, "Candy Hoe", 5343).getInt(5343);
			FronosConfigManager.idToolsCandySword = configuration.get(Configuration.CATEGORY_ITEM, "Candy Sword", 5344).getInt(5344);

			/**@Entities**/
			FronosConfigManager.idEntityBearry = configuration.get("Entities", "Bearry", 113).getInt(113);
			FronosConfigManager.idEntityBerry = configuration.get("Entities", "Berry", 114).getInt(114);
			FronosConfigManager.idEntityMarshmallow = configuration.get("Entities", "Marshmallow", 115).getInt(115);
			FronosConfigManager.idEntityKiwi = configuration.get("Entities", "Kiwi", 116).getInt(116);
			FronosConfigManager.idEntityJellyBerrySlime = configuration.get("Entities", "Jelly Berry Slime", 117).getInt(117);
			FronosConfigManager.idEntityJellyStrawberrySlime = configuration.get("Entities", "Jelly Strawberry Slime", 118).getInt(118);
			FronosConfigManager.idEntityJellyGrapeSlime = configuration.get("Entities", "Jelly Grape Slime", 119).getInt(119);
			FronosConfigManager.idEntityJellyRaspberrySlime = configuration.get("Entities", "Jelly Raspberry Slime", 121).getInt(121);
			FronosConfigManager.idEntityVanillaCreamSlime = configuration.get("Entities", "Vanilla Cream Slime", 122).getInt(122);
			FronosConfigManager.idEntityChocolateCreamSlime = configuration.get("Entities", "Chocolate Cream Slime", 123).getInt(123);
			FronosConfigManager.idEntityJellyLimeSlime = configuration.get("Entities", "Jelly Lime Slime", 124).getInt(124);
			FronosConfigManager.idEntityLemonDuck = configuration.get("Entities", "Lemon Duck", 125).getInt(125);
			FronosConfigManager.idEntitySpaceStarfish = configuration.get("Entities", "Space Starfish", 126).getInt(126);
			FronosConfigManager.idEntityStrawberryCreamSlime = configuration.get("Entities", "Strawberry Cream Slime", 127).getInt(127);
			FronosConfigManager.idEntityMelon = configuration.get("Entities", "Melon", 128).getInt(128);
			FronosConfigManager.idEntityTomato = configuration.get("Entities", "Tomato", 129).getInt(129);
			FronosConfigManager.idEntityGrappy = configuration.get("Entities", "Grappy", 130).getInt(130);
			FronosConfigManager.idEntitySpaceCat = configuration.get("Entities", "Space Cat", 131).getInt(131);
			FronosConfigManager.idEntityVanillaCreamGolem = configuration.get("Entities", "Vanilla Cream Golem", 132).getInt(132);
			FronosConfigManager.idEntityChocolateCreamGolem = configuration.get("Entities", "Chocolate Cream Golem", 133).getInt(133);
			FronosConfigManager.idEntityStrawberryCreamGolem = configuration.get("Entities", "Strawberry Cream Golem", 134).getInt(134);
			FronosConfigManager.idEntityOrangeCreamGolem = configuration.get("Entities", "Orange Cream Golem", 135).getInt(135);
			FronosConfigManager.idEntityOrangeCreamSlime = configuration.get("Entities", "Orange Cream Slime", 136).getInt(136);
			FronosConfigManager.idEntityJellyOrangeSlime = configuration.get("Entities", "Jelly Orange Slime", 137).getInt(137);
			FronosConfigManager.idEntityPinkChicken = configuration.get("Entities", "Pink Chicken", 138).getInt(138);

			/**@NonMobEntities**/
			FronosConfigManager.idEntityVanillaCreamBall = configuration.get("Non-Entities", "Vanilla Cream Ball", 471).getInt(471);
			FronosConfigManager.idEntityChocolateCreamBall = configuration.get("Non-Entities", "Chocolate Cream Ball", 472).getInt(472);
			FronosConfigManager.idEntityStrawberryCreamBall = configuration.get("Non-Entities", "Strawberry Cream Ball", 473).getInt(473);
			FronosConfigManager.idEntityOrangeCreamBall = configuration.get("Non-Entities", "Orange Cream Ball", 474).getInt(474);
			FronosConfigManager.idEntityPoisonArrow = configuration.get("Non-Entities", "Poison Arrow", 475).getInt(475);

			/**@Biomes**/
			FronosConfigManager.idBiomeOases = configuration.get("Biomes", "Testing", 250).getInt(250);
			FronosConfigManager.idBiomeCanyon = configuration.get("Biomes", "Canyon", 251).getInt(251);
			FronosConfigManager.idBiomeMesa = configuration.get("Biomes", "Mesa", 252).getInt(252);
			FronosConfigManager.idBiomeCherry = configuration.get("Biomes", "Cherry", 253).getInt(253);

			/**@Generals**/
			FronosConfigManager.generateOtherMods = configuration.get(Configuration.CATEGORY_GENERAL, "Generate other mod's features on Fronos", false).getBoolean(false);

			/**@WorldGen**/
			FronosConfigManager.strawberryCloudPerChunk = configuration.get("World Gen", "Strawberry Cloud Per Chunk", 3, "If strawberry cloud is too much. You can changed to lower value. Default is 3").getInt(3);
		}
		catch (final Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "More Planet's : Fronos has a problem loading it's configuration");
		}
		finally
		{
			FronosConfigManager.configuration.save();
			FronosConfigManager.loaded = true;
		}
	}
}