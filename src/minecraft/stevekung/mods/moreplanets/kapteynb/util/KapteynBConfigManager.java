/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.kapteynb.util;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class KapteynBConfigManager
{
	public static boolean loaded;

	static Configuration configuration;

	/**@Dimensions**/
	public static int idDimensionKapteynB;

	// BLOCKS
	public static int idBlockKapteynB;
	public static int idBlockKapteynBIce;
	public static int idBlockKapteynBTreasureChest;
	public static int idBlockKapteynBOre;
	public static int idBlockKapteynBCobblestoneStairs;
	public static int idBlockKapteynBDirtyIce;
	public static int idBlockKapteynBFrozenWater;

	// ITEMS
	public static int idItemKapteynB;
	public static int idItemKapteynBDungeonKey;
	public static int idItemUraniumBattery;
	public static int idItemFrozenWaterBucket;

	/**@Generals**/
	public static boolean generateOtherMods;
	public static boolean enableTinGen;
	public static boolean enableCopperGen;
	public static boolean enableDiamondGen;
	public static boolean enableGoldGen;
	public static boolean enableRedstoneGen;

	public KapteynBConfigManager(File file)
	{
		if (!KapteynBConfigManager.loaded)
		{
			KapteynBConfigManager.configuration = new Configuration(file);
			this.setDefaultValues();
		}
	}

	private void setDefaultValues()
	{
		try
		{
			KapteynBConfigManager.configuration.load();

			/**@Dimensions**/
			KapteynBConfigManager.idDimensionKapteynB = KapteynBConfigManager.configuration.get("Dimensions", "Kapteyn B Dimension", -13).getInt(-13);

			// ITEMS
			KapteynBConfigManager.idItemKapteynB = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Kapteyn B Item", 17645).getInt(17645);
			KapteynBConfigManager.idItemKapteynBDungeonKey = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Kapteyn B Dungeon Key", 17646).getInt(17646);
			KapteynBConfigManager.idItemUraniumBattery = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Uranium Battery", 17647).getInt(17647);
			KapteynBConfigManager.idItemFrozenWaterBucket = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_ITEM, "Frozen Water Bucket", 17648).getInt(17648);

			// BLOCKS
			KapteynBConfigManager.idBlockKapteynB = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Kapteyn B Block", 1724).getInt(1724);
			KapteynBConfigManager.idBlockKapteynBIce = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Kapteyn B Ice", 1725).getInt(1725);
			KapteynBConfigManager.idBlockKapteynBTreasureChest = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Kapteyn B Treasure Chest", 1726).getInt(1726);
			KapteynBConfigManager.idBlockKapteynBOre = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Kapteyn B Ore Block", 1727).getInt(1727);
			KapteynBConfigManager.idBlockKapteynBCobblestoneStairs = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Kapteyn B Cobblestone Stairs", 1728).getInt(1728);
			KapteynBConfigManager.idBlockKapteynBDirtyIce = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Kapteyn B Dirty Ice", 1729).getInt(1729);
			KapteynBConfigManager.idBlockKapteynBFrozenWater = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_BLOCK, "Kapteyn B Frozen Water", 1730).getInt(1730);

			/**@Generals**/
			KapteynBConfigManager.generateOtherMods = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Generate other mod's features on Kapteyn B", false).getBoolean(false);
			KapteynBConfigManager.enableCopperGen = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Copper Ore Gen on Kapteyn B", true).getBoolean(true);
			KapteynBConfigManager.enableDiamondGen = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Diamond Ore Gen on Kapteyn B", true).getBoolean(true);
			KapteynBConfigManager.enableGoldGen = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Gold Ore Gen on Kapteyn B", true).getBoolean(true);
			KapteynBConfigManager.enableRedstoneGen = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Redstone Ore Gen on Kapteyn B", true).getBoolean(true);
			KapteynBConfigManager.enableTinGen = KapteynBConfigManager.configuration.get(Configuration.CATEGORY_GENERAL, "Enable Tin Ore Gen on Kapteyn B", true).getBoolean(true);

		}
		catch (final Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "More Planet's : Kapteyn B has a problem loading it's configuration");
		}
		finally
		{
			if (KapteynBConfigManager.configuration.hasChanged())
			{
				KapteynBConfigManager.configuration.save();
			}
			KapteynBConfigManager.loaded = true;
		}
	}
}