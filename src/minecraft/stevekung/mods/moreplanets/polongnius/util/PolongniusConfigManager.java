/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.polongnius.util;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class PolongniusConfigManager
{
	public static boolean loaded;

	static Configuration configuration;

	/**@Dimension**/
	public static int idDimensionPolongnius;

	//BLOCKS
	public static int idBlockPolongnius;
	public static int idBlockPolongniusTreasureChest;
	public static int idBlockPolongniusFallenMeteor;
	public static int idBlockCheeseOfMilkCake;
	public static int idBlockFloniumTorch;
	public static int idBlockPolongniusCobblestoneStairs;
	public static int idBlockSolarPanel;
	public static int idBlockCheeseOfMilkFluid;
	public static int idBlockPolongniusEpicChest;
	public static int idBlockPolongniusSlime;
	public static int idBlockCheeseSlimeEgg;

	//ITEMS
	public static int idItemPolongnius;
	public static int idItemPolongnius2;
	public static int idItemPolongniusFood;
	public static int idItemPolongniusDungeonKey;
	public static int idItemCheeseOfMilkCake;
	public static int idItemCheeseSlimeball;
	public static int idItemRocketT4;
	public static int idItemRocketPartT4;
	public static int idItemSchematicT4;

	//TOOLS
	public static int idToolMeteorPickaxe;
	public static int idToolMeteorAxe;
	public static int idToolMeteorHoe;
	public static int idToolMeteorSpade;
	public static int idToolMeteorSword;
	public static int idToolPalladiumPickaxe;
	public static int idToolPalladiumAxe;
	public static int idToolPalladiumHoe;
	public static int idToolPalladiumSpade;
	public static int idToolPalladiumSword;

	//ARMOR
	public static int idArmorLeatherOfCheeseHelmet;
	public static int idArmorLeatherOfCheeseChestplate;
	public static int idArmorLeatherOfCheeseLeggings;
	public static int idArmorLeatherOfCheeseBoots;
	public static int idArmorPolongniusMeteorHelmet;
	public static int idArmorPolongniusMeteorChestplate;
	public static int idArmorPolongniusMeteorLeggings;
	public static int idArmorPolongniusMeteorBoots;
	public static int idArmorPalladiumHelmet;
	public static int idArmorPalladiumChestplate;
	public static int idArmorPalladiumLeggings;
	public static int idArmorPalladiumBoots;
	public static int idArmorBreathablePolongniusMeteorHelmet;
	public static int idArmorBreathablePalladiumHelmet;

	/**@Entities**/
	public static int idEntityCheeseCow;
	public static int idEntityCheeseSlime;
	public static int idEntityCheeseCubeBoss;

	/**@NonMobEntities**/
	public static int idEntityRocketT4;
	public static int idEntityRocketT4Unflag;
	public static int idEntityCheeseSpore;

	/**@Generals**/
	public static boolean generateOtherMods;
	public static boolean enableTinGen;
	public static boolean enableCopperGen;
	public static boolean enableIronGen;

	/**@Schematics**/
	public static int idSchematicRocketT4;
	public static int idSchematicRocketT4NoFlag;

	/**@GUI**/
	public static int idGuiRocketT4;
	public static int idGuiRocketT4NoFlag;

	public PolongniusConfigManager(File file)
	{
		if (!loaded)
		{
			configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	private void setDefaultValues()
	{
		try
		{
			PolongniusConfigManager.configuration.load();

			// BLOCKS
			PolongniusConfigManager.idBlockPolongnius = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Polongnius Basic Block", 1064).getInt(1064);
			PolongniusConfigManager.idBlockPolongniusTreasureChest = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Polongnius Treasure Chest", 1065).getInt(1065);
			PolongniusConfigManager.idBlockSolarPanel = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Polongnius Solar Panel", 1066).getInt(1066);
			PolongniusConfigManager.idBlockPolongniusEpicChest = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Polongnius Epic Chest", 1067).getInt(1067);
			PolongniusConfigManager.idBlockPolongniusFallenMeteor = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Polongnius Fallen Meteor", 1068).getInt(1068);
			PolongniusConfigManager.idBlockFloniumTorch = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Flonium Torch", 1069).getInt(1069);
			PolongniusConfigManager.idBlockPolongniusCobblestoneStairs = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Polongnius Cobblestone Stairs", 1070).getInt(1070);
			PolongniusConfigManager.idBlockCheeseOfMilkCake = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Cheese Of Milk Cake", 1072).getInt(1072);
			PolongniusConfigManager.idBlockCheeseOfMilkFluid = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Cheese of Milk Fluid", 1073).getInt(1073);
			PolongniusConfigManager.idBlockPolongniusSlime = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Polongnius Slime Block", 1074).getInt(1074);
			PolongniusConfigManager.idBlockCheeseSlimeEgg = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Cheese Slime Egg", 1075).getInt(1075);

			/**@Dimension**/
			PolongniusConfigManager.idDimensionPolongnius = PolongniusConfigManager.configuration.get("Dimensions", "Polongnius Dimension", -7).getInt(-7);

			// ITEMS
			PolongniusConfigManager.idItemPolongnius = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongius Basic Item", 4890).getInt(4890);
			PolongniusConfigManager.idItemPolongnius2 = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongius Solar Module", 4891).getInt(4891);
			PolongniusConfigManager.idItemPolongniusFood = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongius Food", 4892).getInt(4892);
			PolongniusConfigManager.idItemCheeseOfMilkCake = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Cheese of Milk Cake Item", 4893).getInt(4893);
			PolongniusConfigManager.idItemCheeseSlimeball = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Cheese Slimeball", 4894).getInt(4894);
			PolongniusConfigManager.idItemPolongniusDungeonKey = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongnius Dungeon Key", 4895).getInt(4895);
			PolongniusConfigManager.idItemRocketT4 = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Rocket Tier 4", 4896).getInt(4896);
			PolongniusConfigManager.idItemRocketPartT4 = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Rocket Part Tier 4", 4897).getInt(4897);
			PolongniusConfigManager.idItemSchematicT4 = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Schematic Rocket Tier 4", 4898).getInt(4898);

			// TOOLS
			PolongniusConfigManager.idToolMeteorSpade = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongius Meteor Spade", 5270).getInt(5270);
			PolongniusConfigManager.idToolMeteorPickaxe = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongius Meteor Pickaxe", 5271).getInt(5271);
			PolongniusConfigManager.idToolMeteorAxe = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongius Meteor Axe", 5272).getInt(5272);
			PolongniusConfigManager.idToolMeteorHoe = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongnius Meteor Hoe", 5273).getInt(5273);
			PolongniusConfigManager.idToolMeteorSword = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongius Meteor Sword", 5274).getInt(5274);
			PolongniusConfigManager.idToolPalladiumSpade = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Palladium Spade", 5275).getInt(5275);
			PolongniusConfigManager.idToolPalladiumPickaxe = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Palladium Pickaxe", 5276).getInt(5276);
			PolongniusConfigManager.idToolPalladiumAxe = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Palladium Axe", 5277).getInt(5277);
			PolongniusConfigManager.idToolPalladiumHoe = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Palladium Hoe", 5278).getInt(5278);
			PolongniusConfigManager.idToolPalladiumSword = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Palladium Sword", 5279).getInt(5279);

			// ARMOR
			PolongniusConfigManager.idArmorLeatherOfCheeseHelmet = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Leather of Cheese Helmet", 5796).getInt(5796);
			PolongniusConfigManager.idArmorLeatherOfCheeseChestplate = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Leather of Cheese Chestplate", 5797).getInt(5797);
			PolongniusConfigManager.idArmorLeatherOfCheeseLeggings = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Leather of Cheese Leggings", 5798).getInt(5798);
			PolongniusConfigManager.idArmorLeatherOfCheeseBoots = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Leather of Cheese Boots", 5799).getInt(5799);
			PolongniusConfigManager.idArmorPolongniusMeteorHelmet = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongnius Meteor Helmet", 5800).getInt(5800);
			PolongniusConfigManager.idArmorPolongniusMeteorChestplate = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongnius Meteor Chestplate", 5801).getInt(5801);
			PolongniusConfigManager.idArmorPolongniusMeteorLeggings = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongnius Meteor Leggings", 5802).getInt(5802);
			PolongniusConfigManager.idArmorPolongniusMeteorBoots = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Polongnius Meteor Boots", 5803).getInt(5803);
			PolongniusConfigManager.idArmorPalladiumHelmet = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Palladium Helmet", 5804).getInt(5804);
			PolongniusConfigManager.idArmorPalladiumChestplate = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Palladium Chestplate", 5805).getInt(5805);
			PolongniusConfigManager.idArmorPalladiumLeggings = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Palladium Leggings", 5806).getInt(5806);
			PolongniusConfigManager.idArmorPalladiumBoots = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Palladium Boots", 5807).getInt(5807);
			PolongniusConfigManager.idArmorBreathablePolongniusMeteorHelmet = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Breathable Polongnius Meteor Helmet", 5808).getInt(5808);
			PolongniusConfigManager.idArmorBreathablePalladiumHelmet = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Breathable Palladium Helmet", 5809).getInt(5809);

			/**@Entities**/
			PolongniusConfigManager.idEntityCheeseCow = PolongniusConfigManager.configuration.get("Entities", "Cheese Cow", 106).getInt(106);
			PolongniusConfigManager.idEntityCheeseSlime = PolongniusConfigManager.configuration.get("Entities", "Cheese Slime", 107).getInt(107);
			PolongniusConfigManager.idEntityCheeseCubeBoss = PolongniusConfigManager.configuration.get("Entities", "Cheese Cube Boss", 184).getInt(184);

			/**@NonMobEntities**/
			PolongniusConfigManager.idEntityRocketT4 = PolongniusConfigManager.configuration.get("Non-Entities", "Rocket Tier 4", 467).getInt(467);
			PolongniusConfigManager.idEntityRocketT4Unflag = PolongniusConfigManager.configuration.get("Non-Entities", "Rocket Tier 4 Unflag", 468).getInt(468);
			PolongniusConfigManager.idEntityCheeseSpore = PolongniusConfigManager.configuration.get("Non-Entities", "Cheese Spore", 478).getInt(478);

			/**@Schematics**/
			PolongniusConfigManager.idSchematicRocketT4 = PolongniusConfigManager.configuration.get("Schematics", "Schematic Rocket Tier 4", 786).getInt(786);
			PolongniusConfigManager.idSchematicRocketT4NoFlag = PolongniusConfigManager.configuration.get("Schematics", "Schematic Rocket Tier 4 No Flag", 787).getInt(787);

			/**@GUI**/
			PolongniusConfigManager.idGuiRocketT4 = PolongniusConfigManager.configuration.get("GUI", "GUI Rocket Tier 4", 430).getInt(430);
			PolongniusConfigManager.idGuiRocketT4NoFlag = PolongniusConfigManager.configuration.get("GUI", "GUI Rocket Tier 4 No Flag", 431).getInt(431);

			/**@Generals**/
			PolongniusConfigManager.generateOtherMods = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Generate other mod's features on Polongnius", false).getBoolean(false);
			PolongniusConfigManager.enableTinGen = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Tin Ore Gen on Polongnius", true).getBoolean(true);
			PolongniusConfigManager.enableCopperGen = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Copper Ore Gen on Polongnius", true).getBoolean(true);
			PolongniusConfigManager.enableIronGen = PolongniusConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Iron Ore Gen on Polongnius", true).getBoolean(true);
		}
		catch (final Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "More Planet's : Polongnius has a problem loading it's configuration");
		}
		finally
		{
			if (PolongniusConfigManager.configuration.hasChanged())
			{
				PolongniusConfigManager.configuration.save();
			}
			PolongniusConfigManager.loaded = true;
		}
	}
}